package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.C0072a;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    public static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";

    @GuardedBy("sEnabledNotificationListenersLock")
    private static String sEnabledNotificationListeners;

    @GuardedBy("sLock")
    private static SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    private static final Object sEnabledNotificationListenersLock = new Object();

    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set<String> sEnabledNotificationListenerPackages = new HashSet();
    private static final Object sLock = new Object();

    public static class NotifyTask implements Task {

        /* renamed from: id */
        public final int f152id;
        public final Notification notif;
        public final String packageName;
        public final String tag;

        public NotifyTask(String str, int i7, String str2, Notification notification) {
            this.packageName = str;
            this.f152id = i7;
            this.tag = str2;
            this.notif = notification;
        }

        @Override // android.support.v4.app.NotificationManagerCompat.Task
        public void send(INotificationSideChannel iNotificationSideChannel) {
            iNotificationSideChannel.notify(this.packageName, this.f152id, this.tag, this.notif);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("NotifyTask[");
            sb.append("packageName:");
            sb.append(this.packageName);
            sb.append(", id:");
            sb.append(this.f152id);
            sb.append(", tag:");
            return C0072a.m92a(sb, this.tag, "]");
        }
    }

    public static class ServiceConnectedEvent {
        public final ComponentName componentName;
        public final IBinder iBinder;

        public ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
            this.componentName = componentName;
            this.iBinder = iBinder;
        }
    }

    public static class SideChannelManager implements Handler.Callback, ServiceConnection {
        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();
        private Set<String> mCachedEnabledPackages = new HashSet();

        public static class ListenerRecord {
            public final ComponentName componentName;
            public INotificationSideChannel service;
            public boolean bound = false;
            public ArrayDeque<Task> taskQueue = new ArrayDeque<>();
            public int retryCount = 0;

            public ListenerRecord(ComponentName componentName) {
                this.componentName = componentName;
            }
        }

        public SideChannelManager(Context context) {
            this.mContext = context;
            HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), this);
        }

        private boolean ensureServiceBound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                return true;
            }
            boolean zBindService = this.mContext.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(listenerRecord.componentName), this, 33);
            listenerRecord.bound = zBindService;
            if (zBindService) {
                listenerRecord.retryCount = 0;
            } else {
                C0413b.m112a("Unable to bind to listener ").append(listenerRecord.componentName);
                this.mContext.unbindService(this);
            }
            return listenerRecord.bound;
        }

        private void ensureServiceUnbound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                this.mContext.unbindService(this);
                listenerRecord.bound = false;
            }
            listenerRecord.service = null;
        }

        private void handleQueueTask(Task task) {
            updateListenerMap();
            for (ListenerRecord listenerRecord : this.mRecordMap.values()) {
                listenerRecord.taskQueue.add(task);
                processListenerQueue(listenerRecord);
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceConnected(ComponentName componentName, IBinder iBinder) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                listenerRecord.service = INotificationSideChannel.Stub.asInterface(iBinder);
                listenerRecord.retryCount = 0;
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                ensureServiceUnbound(listenerRecord);
            }
        }

        private void processListenerQueue(ListenerRecord listenerRecord) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                StringBuilder sbM112a = C0413b.m112a("Processing component ");
                sbM112a.append(listenerRecord.componentName);
                sbM112a.append(", ");
                sbM112a.append(listenerRecord.taskQueue.size());
                sbM112a.append(" queued tasks");
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            if (!ensureServiceBound(listenerRecord) || listenerRecord.service == null) {
                scheduleListenerRetry(listenerRecord);
                return;
            }
            while (true) {
                Task taskPeek = listenerRecord.taskQueue.peek();
                if (taskPeek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Sending task ");
                        sb.append(taskPeek);
                    }
                    taskPeek.send(listenerRecord.service);
                    listenerRecord.taskQueue.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        C0413b.m112a("Remote service has died: ").append(listenerRecord.componentName);
                    }
                } catch (RemoteException unused2) {
                    C0413b.m112a("RemoteException communicating with ").append(listenerRecord.componentName);
                }
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            scheduleListenerRetry(listenerRecord);
        }

        private void scheduleListenerRetry(ListenerRecord listenerRecord) {
            if (this.mHandler.hasMessages(3, listenerRecord.componentName)) {
                return;
            }
            int i7 = listenerRecord.retryCount + 1;
            listenerRecord.retryCount = i7;
            if (i7 <= 6) {
                Log.isLoggable(NotificationManagerCompat.TAG, 3);
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, listenerRecord.componentName), (1 << (i7 - 1)) * 1000);
                return;
            }
            StringBuilder sbM112a = C0413b.m112a("Giving up on delivering ");
            sbM112a.append(listenerRecord.taskQueue.size());
            sbM112a.append(" tasks to ");
            sbM112a.append(listenerRecord.componentName);
            sbM112a.append(" after ");
            sbM112a.append(listenerRecord.retryCount);
            sbM112a.append(" retries");
            listenerRecord.taskQueue.clear();
        }

        private void updateListenerMap() {
            Set<String> enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if (enabledListenerPackages.equals(this.mCachedEnabledPackages)) {
                return;
            }
            this.mCachedEnabledPackages = enabledListenerPackages;
            List<ResolveInfo> listQueryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
            HashSet hashSet = new HashSet();
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Permission present on component ");
                        sb.append(componentName);
                        sb.append(", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ComponentName componentName2 = (ComponentName) it.next();
                if (!this.mRecordMap.containsKey(componentName2)) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Adding listener record for ");
                        sb2.append(componentName2);
                    }
                    this.mRecordMap.put(componentName2, new ListenerRecord(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, ListenerRecord>> it2 = this.mRecordMap.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<ComponentName, ListenerRecord> next = it2.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        C0413b.m112a("Removing listener record for ").append(next.getKey());
                    }
                    ensureServiceUnbound(next.getValue());
                    it2.remove();
                }
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i7 = message.what;
            if (i7 == 0) {
                handleQueueTask((Task) message.obj);
                return true;
            }
            if (i7 == 1) {
                ServiceConnectedEvent serviceConnectedEvent = (ServiceConnectedEvent) message.obj;
                handleServiceConnected(serviceConnectedEvent.componentName, serviceConnectedEvent.iBinder);
                return true;
            }
            if (i7 == 2) {
                handleServiceDisconnected((ComponentName) message.obj);
                return true;
            }
            if (i7 != 3) {
                return false;
            }
            handleRetryListenerQueue((ComponentName) message.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Connected to service ");
                sb.append(componentName);
            }
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Disconnected from service ");
                sb.append(componentName);
            }
            this.mHandler.obtainMessage(2, componentName).sendToTarget();
        }

        public void queueTask(Task task) {
            this.mHandler.obtainMessage(0, task).sendToTarget();
        }
    }

    public interface Task {
        void send(INotificationSideChannel iNotificationSideChannel);
    }

    private NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
    }

    @NonNull
    public static NotificationManagerCompat from(@NonNull Context context) {
        return new NotificationManagerCompat(context);
    }

    @NonNull
    public static Set<String> getEnabledListenerPackages(@NonNull Context context) {
        Set<String> set;
        String string = Settings.Secure.getString(context.getContentResolver(), SETTING_ENABLED_NOTIFICATION_LISTENERS);
        synchronized (sEnabledNotificationListenersLock) {
            if (string != null) {
                if (!string.equals(sEnabledNotificationListeners)) {
                    String[] strArrSplit = string.split(":", -1);
                    HashSet hashSet = new HashSet(strArrSplit.length);
                    for (String str : strArrSplit) {
                        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                        if (componentNameUnflattenFromString != null) {
                            hashSet.add(componentNameUnflattenFromString.getPackageName());
                        }
                    }
                    sEnabledNotificationListenerPackages = hashSet;
                    sEnabledNotificationListeners = string;
                }
                set = sEnabledNotificationListenerPackages;
            } else {
                set = sEnabledNotificationListenerPackages;
            }
        }
        return set;
    }

    private void pushSideChannelQueue(Task task) {
        synchronized (sLock) {
            if (sSideChannelManager == null) {
                sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
            }
            sSideChannelManager.queueTask(task);
        }
    }

    private static boolean useSideChannelForNotification(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    public boolean areNotificationsEnabled() throws ClassNotFoundException {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.areNotificationsEnabled();
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.mContext.getSystemService("appops");
        ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
        String packageName = this.mContext.getApplicationContext().getPackageName();
        int i7 = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            Class<?> cls2 = Integer.TYPE;
            return ((Integer) cls.getMethod(CHECK_OP_NO_THROW, cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField(OP_POST_NOTIFICATION).get(Integer.class)).intValue()), Integer.valueOf(i7), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }

    public void cancel(int i7) {
        cancel(null, i7);
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
    }

    public int getImportance() {
        return Build.VERSION.SDK_INT >= 24 ? this.mNotificationManager.getImportance() : IMPORTANCE_UNSPECIFIED;
    }

    public void notify(int i7, @NonNull Notification notification) {
        notify(null, i7, notification);
    }

    public void cancel(@Nullable String str, int i7) {
        this.mNotificationManager.cancel(str, i7);
    }

    public void notify(@Nullable String str, int i7, @NonNull Notification notification) {
        if (!useSideChannelForNotification(notification)) {
            this.mNotificationManager.notify(str, i7, notification);
        } else {
            pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), i7, str, notification));
            this.mNotificationManager.cancel(str, i7);
        }
    }

    public static class CancelTask implements Task {
        public final boolean all;

        /* renamed from: id */
        public final int f151id;
        public final String packageName;
        public final String tag;

        public CancelTask(String str) {
            this.packageName = str;
            this.f151id = 0;
            this.tag = null;
            this.all = true;
        }

        @Override // android.support.v4.app.NotificationManagerCompat.Task
        public void send(INotificationSideChannel iNotificationSideChannel) {
            if (this.all) {
                iNotificationSideChannel.cancelAll(this.packageName);
            } else {
                iNotificationSideChannel.cancel(this.packageName, this.f151id, this.tag);
            }
        }

        public String toString() {
            return "CancelTask[packageName:" + this.packageName + ", id:" + this.f151id + ", tag:" + this.tag + ", all:" + this.all + "]";
        }

        public CancelTask(String str, int i7, String str2) {
            this.packageName = str;
            this.f151id = i7;
            this.tag = str2;
            this.all = false;
        }
    }
}
