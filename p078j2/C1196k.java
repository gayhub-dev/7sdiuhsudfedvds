package p078j2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import p085k1.C1228b;
import p179w2.C2025a;
import p179w2.C2026b;

/* compiled from: NotificationUtils.java */
/* renamed from: j2.k */
/* loaded from: classes.dex */
public class C1196k {
    /* renamed from: a */
    public static Notification m1420a() {
        if (Build.VERSION.SDK_INT < 26) {
            return new Notification.Builder(MyApplication.f561e).setContentTitle(C2025a.m2373a()).setContentText(C2026b.m2379b(R.string.notification_description)).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.notification_small).setLargeIcon(BitmapFactory.decodeResource(MyApplication.f561e.getResources(), R.mipmap.ic_launcher)).build();
        }
        NotificationManager notificationManager = (NotificationManager) MyApplication.f561e.getSystemService("notification");
        int i7 = C1228b.f2758a;
        NotificationChannel notificationChannel = new NotificationChannel("CctvTvService", C2025a.m2373a(), 4);
        notificationChannel.setShowBadge(true);
        notificationChannel.setDescription(C2026b.m2379b(R.string.notification_description));
        notificationChannel.setLockscreenVisibility(1);
        notificationManager.createNotificationChannel(notificationChannel);
        return new Notification.Builder(MyApplication.f561e, "CctvTvService").setContentTitle(C2025a.m2373a()).setContentText(C2026b.m2379b(R.string.notification_description)).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.notification_small).setLargeIcon(BitmapFactory.decodeResource(MyApplication.f561e.getResources(), R.mipmap.ic_launcher)).build();
    }
}
