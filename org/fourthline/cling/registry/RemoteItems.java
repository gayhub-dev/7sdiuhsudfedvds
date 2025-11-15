package org.fourthline.cling.registry;

import android.support.constraint.motion.C0080b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.RemoteGENASubscription;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.model.meta.RemoteDeviceIdentity;
import org.fourthline.cling.model.resource.Resource;
import org.fourthline.cling.model.types.UDN;
import p009b.C0413b;

/* loaded from: classes.dex */
class RemoteItems extends RegistryItems<RemoteDevice, RemoteGENASubscription> {
    private static Logger log = Logger.getLogger(Registry.class.getName());

    /* renamed from: org.fourthline.cling.registry.RemoteItems$1 */
    public class RunnableC16911 implements Runnable {
        public final /* synthetic */ RemoteDevice val$device;
        public final /* synthetic */ RegistryListener val$listener;

        public RunnableC16911(RegistryListener registryListener, RemoteDevice remoteDevice) {
            registryListener = registryListener;
            remoteDevice = remoteDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            registryListener.remoteDeviceAdded(RemoteItems.this.registry, remoteDevice);
        }
    }

    /* renamed from: org.fourthline.cling.registry.RemoteItems$2 */
    public class RunnableC16922 implements Runnable {
        public final /* synthetic */ RegistryItem val$item;
        public final /* synthetic */ RegistryListener val$listener;

        public RunnableC16922(RegistryListener registryListener, RegistryItem registryItem) {
            registryListener = registryListener;
            registryItem = registryItem;
        }

        @Override // java.lang.Runnable
        public void run() {
            registryListener.remoteDeviceUpdated(RemoteItems.this.registry, (RemoteDevice) registryItem.getItem());
        }
    }

    /* renamed from: org.fourthline.cling.registry.RemoteItems$3 */
    public class RunnableC16933 implements Runnable {
        public final /* synthetic */ RegistryItem val$outgoingSubscription;

        public RunnableC16933(RegistryItem registryItem) {
            registryItem = registryItem;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((RemoteGENASubscription) registryItem.getItem()).end(CancelReason.DEVICE_WAS_REMOVED, null);
        }
    }

    /* renamed from: org.fourthline.cling.registry.RemoteItems$4 */
    public class RunnableC16944 implements Runnable {
        public final /* synthetic */ RegistryListener val$listener;
        public final /* synthetic */ RemoteDevice val$registeredDevice;

        public RunnableC16944(RegistryListener registryListener, RemoteDevice remoteDevice) {
            registryListener = registryListener;
            remoteDevice = remoteDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            registryListener.remoteDeviceRemoved(RemoteItems.this.registry, remoteDevice);
        }
    }

    public RemoteItems(RegistryImpl registryImpl) {
        super(registryImpl);
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void maintain() {
        if (getDeviceItems().isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        for (RegistryItem<UDN, RemoteDevice> registryItem : getDeviceItems()) {
            if (log.isLoggable(Level.FINEST)) {
                Logger logger = log;
                StringBuilder sbM112a = C0413b.m112a("Device '");
                sbM112a.append(registryItem.getItem());
                sbM112a.append("' expires in seconds: ");
                sbM112a.append(registryItem.getExpirationDetails().getSecondsUntilExpiration());
                logger.finest(sbM112a.toString());
            }
            if (registryItem.getExpirationDetails().hasExpired(false)) {
                map.put(registryItem.getKey(), registryItem.getItem());
            }
        }
        for (RemoteDevice remoteDevice : map.values()) {
            if (log.isLoggable(Level.FINE)) {
                log.fine("Removing expired: " + remoteDevice);
            }
            remove(remoteDevice);
        }
        HashSet hashSet = new HashSet();
        for (RegistryItem<String, RemoteGENASubscription> registryItem2 : getSubscriptionItems()) {
            if (registryItem2.getExpirationDetails().hasExpired(true)) {
                hashSet.add(registryItem2.getItem());
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            RemoteGENASubscription remoteGENASubscription = (RemoteGENASubscription) it.next();
            if (log.isLoggable(Level.FINEST)) {
                log.fine("Renewing outgoing subscription: " + remoteGENASubscription);
            }
            renewOutgoingSubscription(remoteGENASubscription);
        }
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void removeAll() {
        removeAll(false);
    }

    public void renewOutgoingSubscription(RemoteGENASubscription remoteGENASubscription) {
        RegistryImpl registryImpl = this.registry;
        registryImpl.executeAsyncProtocol(registryImpl.getProtocolFactory().createSendingRenewal(remoteGENASubscription));
    }

    public void resume() {
        log.fine("Updating remote device expiration timestamps on resume");
        ArrayList arrayList = new ArrayList();
        Iterator<RegistryItem<UDN, RemoteDevice>> it = getDeviceItems().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getItem().getIdentity());
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            update((RemoteDeviceIdentity) it2.next());
        }
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void shutdown() {
        log.fine("Cancelling all outgoing subscriptions to remote devices during shutdown");
        ArrayList arrayList = new ArrayList();
        Iterator<RegistryItem<String, RemoteGENASubscription>> it = getSubscriptionItems().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getItem());
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.registry.getProtocolFactory().createSendingUnsubscribe((RemoteGENASubscription) it2.next()).run();
        }
        log.fine("Removing all remote devices from registry during shutdown");
        removeAll(true);
    }

    public void start() {
    }

    public boolean update(RemoteDeviceIdentity remoteDeviceIdentity) {
        Iterator<LocalDevice> it = this.registry.getLocalDevices().iterator();
        while (it.hasNext()) {
            if (it.next().findDevice(remoteDeviceIdentity.getUdn()) != null) {
                log.fine("Ignoring update, a local device graph contains UDN");
                return true;
            }
        }
        RemoteDevice root = get(remoteDeviceIdentity.getUdn(), false);
        if (root == null) {
            return false;
        }
        if (!root.isRoot()) {
            log.fine("Updating root device of embedded: " + root);
            root = root.getRoot();
        }
        RegistryItem<UDN, RemoteDevice> registryItem = new RegistryItem<>(root.getIdentity().getUdn(), root, (this.registry.getConfiguration().getRemoteDeviceMaxAgeSeconds() != null ? this.registry.getConfiguration().getRemoteDeviceMaxAgeSeconds() : remoteDeviceIdentity.getMaxAgeSeconds()).intValue());
        log.fine("Updating expiration of: " + root);
        getDeviceItems().remove(registryItem);
        getDeviceItems().add(registryItem);
        log.fine("Remote device updated, calling listeners: " + root);
        Iterator<RegistryListener> it2 = this.registry.getListeners().iterator();
        while (it2.hasNext()) {
            this.registry.getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.registry.RemoteItems.2
                public final /* synthetic */ RegistryItem val$item;
                public final /* synthetic */ RegistryListener val$listener;

                public RunnableC16922(RegistryListener registryListener, RegistryItem registryItem2) {
                    registryListener = registryListener;
                    registryItem = registryItem2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    registryListener.remoteDeviceUpdated(RemoteItems.this.registry, (RemoteDevice) registryItem.getItem());
                }
            });
        }
        return true;
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void add(RemoteDevice remoteDevice) {
        if (update(remoteDevice.getIdentity())) {
            log.fine("Ignoring addition, device already registered: " + remoteDevice);
            return;
        }
        Resource[] resources = getResources(remoteDevice);
        for (Resource resource : resources) {
            log.fine("Validating remote device resource; " + resource);
            if (this.registry.getResource(resource.getPathQuery()) != null) {
                throw new RegistrationException("URI namespace conflict with already registered resource: " + resource);
            }
        }
        for (Resource resource2 : resources) {
            this.registry.addResource(resource2);
            log.fine("Added remote device resource: " + resource2);
        }
        RegistryItem<UDN, RemoteDevice> registryItem = new RegistryItem<>(remoteDevice.getIdentity().getUdn(), remoteDevice, (this.registry.getConfiguration().getRemoteDeviceMaxAgeSeconds() != null ? this.registry.getConfiguration().getRemoteDeviceMaxAgeSeconds() : remoteDevice.getIdentity().getMaxAgeSeconds()).intValue());
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Adding hydrated remote device to registry with ");
        sbM112a.append(registryItem.getExpirationDetails().getMaxAgeSeconds());
        sbM112a.append(" seconds expiration: ");
        sbM112a.append(remoteDevice);
        logger.fine(sbM112a.toString());
        getDeviceItems().add(registryItem);
        if (log.isLoggable(Level.FINEST)) {
            StringBuilder sbM94a = C0080b.m94a("\n", "-------------------------- START Registry Namespace -----------------------------------\n");
            Iterator<Resource> it = this.registry.getResources().iterator();
            while (it.hasNext()) {
                sbM94a.append(it.next());
                sbM94a.append("\n");
            }
            sbM94a.append("-------------------------- END Registry Namespace -----------------------------------");
            log.finest(sbM94a.toString());
        }
        log.fine("Completely hydrated remote device graph available, calling listeners: " + remoteDevice);
        Iterator<RegistryListener> it2 = this.registry.getListeners().iterator();
        while (it2.hasNext()) {
            this.registry.getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.registry.RemoteItems.1
                public final /* synthetic */ RemoteDevice val$device;
                public final /* synthetic */ RegistryListener val$listener;

                public RunnableC16911(RegistryListener registryListener, RemoteDevice remoteDevice2) {
                    registryListener = registryListener;
                    remoteDevice = remoteDevice2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    registryListener.remoteDeviceAdded(RemoteItems.this.registry, remoteDevice);
                }
            });
        }
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public boolean remove(RemoteDevice remoteDevice) {
        return remove(remoteDevice, false);
    }

    public void removeAll(boolean z6) {
        for (RemoteDevice remoteDevice : (RemoteDevice[]) get().toArray(new RemoteDevice[get().size()])) {
            remove(remoteDevice, z6);
        }
    }

    public boolean remove(RemoteDevice remoteDevice, boolean z6) {
        RemoteDevice remoteDevice2 = (RemoteDevice) get(remoteDevice.getIdentity().getUdn(), true);
        if (remoteDevice2 == null) {
            return false;
        }
        log.fine("Removing remote device from registry: " + remoteDevice);
        for (Resource resource : getResources(remoteDevice2)) {
            if (this.registry.removeResource(resource)) {
                log.fine("Unregistered resource: " + resource);
            }
        }
        Iterator it = getSubscriptionItems().iterator();
        while (it.hasNext()) {
            RegistryItem registryItem = (RegistryItem) it.next();
            if (((RemoteGENASubscription) registryItem.getItem()).getService().getDevice().getIdentity().getUdn().equals(remoteDevice2.getIdentity().getUdn())) {
                Logger logger = log;
                StringBuilder sbM112a = C0413b.m112a("Removing outgoing subscription: ");
                sbM112a.append((String) registryItem.getKey());
                logger.fine(sbM112a.toString());
                it.remove();
                if (!z6) {
                    this.registry.getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.registry.RemoteItems.3
                        public final /* synthetic */ RegistryItem val$outgoingSubscription;

                        public RunnableC16933(RegistryItem registryItem2) {
                            registryItem = registryItem2;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            ((RemoteGENASubscription) registryItem.getItem()).end(CancelReason.DEVICE_WAS_REMOVED, null);
                        }
                    });
                }
            }
        }
        if (!z6) {
            Iterator<RegistryListener> it2 = this.registry.getListeners().iterator();
            while (it2.hasNext()) {
                this.registry.getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.registry.RemoteItems.4
                    public final /* synthetic */ RegistryListener val$listener;
                    public final /* synthetic */ RemoteDevice val$registeredDevice;

                    public RunnableC16944(RegistryListener registryListener, RemoteDevice remoteDevice22) {
                        registryListener = registryListener;
                        remoteDevice = remoteDevice22;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        registryListener.remoteDeviceRemoved(RemoteItems.this.registry, remoteDevice);
                    }
                });
            }
        }
        getDeviceItems().remove(new RegistryItem(remoteDevice22.getIdentity().getUdn()));
        return true;
    }
}
