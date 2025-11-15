package org.fourthline.cling.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import org.fourthline.cling.model.DiscoveryOptions;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.LocalGENASubscription;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.resource.Resource;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.protocol.async.SendingNotificationByebye;
import p009b.C0413b;

/* loaded from: classes.dex */
class LocalItems extends RegistryItems<LocalDevice, LocalGENASubscription> {
    private static Logger log = Logger.getLogger(Registry.class.getName());
    public Map<UDN, DiscoveryOptions> discoveryOptions;
    public long lastAliveIntervalTimestamp;
    public Random randomGenerator;

    /* renamed from: org.fourthline.cling.registry.LocalItems$1 */
    public class RunnableC16851 implements Runnable {
        public final /* synthetic */ RegistryListener val$listener;
        public final /* synthetic */ LocalDevice val$localDevice;

        public RunnableC16851(RegistryListener registryListener, LocalDevice localDevice) {
            registryListener = registryListener;
            localDevice = localDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            registryListener.localDeviceAdded(LocalItems.this.registry, localDevice);
        }
    }

    /* renamed from: org.fourthline.cling.registry.LocalItems$2 */
    public class RunnableC16862 implements Runnable {
        public final /* synthetic */ RegistryItem val$incomingSubscription;

        public RunnableC16862(RegistryItem registryItem) {
            registryItem = registryItem;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((LocalGENASubscription) registryItem.getItem()).end(CancelReason.DEVICE_WAS_REMOVED);
        }
    }

    /* renamed from: org.fourthline.cling.registry.LocalItems$3 */
    public class RunnableC16873 implements Runnable {
        public final /* synthetic */ RegistryListener val$listener;
        public final /* synthetic */ LocalDevice val$localDevice;

        public RunnableC16873(RegistryListener registryListener, LocalDevice localDevice) {
            registryListener = registryListener;
            localDevice = localDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            registryListener.localDeviceRemoved(LocalItems.this.registry, localDevice);
        }
    }

    /* renamed from: org.fourthline.cling.registry.LocalItems$4 */
    public class RunnableC16884 implements Runnable {
        public final /* synthetic */ LocalDevice val$localDevice;

        public RunnableC16884(LocalDevice localDevice) {
            localDevice = localDevice;
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            try {
                LocalItems.log.finer("Sleeping some milliseconds to avoid flooding the network with ALIVE msgs");
                Thread.sleep(LocalItems.this.randomGenerator.nextInt(100));
            } catch (InterruptedException e7) {
                Logger logger = LocalItems.log;
                StringBuilder sbM112a = C0413b.m112a("Background execution interrupted: ");
                sbM112a.append(e7.getMessage());
                logger.severe(sbM112a.toString());
            }
            LocalItems.this.registry.getProtocolFactory().createSendingNotificationAlive(localDevice).run();
        }
    }

    public LocalItems(RegistryImpl registryImpl) {
        super(registryImpl);
        this.discoveryOptions = new HashMap();
        this.lastAliveIntervalTimestamp = 0L;
        this.randomGenerator = new Random();
    }

    public void advertiseAlive(LocalDevice localDevice) {
        this.registry.executeAsyncProtocol(new Runnable() { // from class: org.fourthline.cling.registry.LocalItems.4
            public final /* synthetic */ LocalDevice val$localDevice;

            public RunnableC16884(LocalDevice localDevice2) {
                localDevice = localDevice2;
            }

            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                try {
                    LocalItems.log.finer("Sleeping some milliseconds to avoid flooding the network with ALIVE msgs");
                    Thread.sleep(LocalItems.this.randomGenerator.nextInt(100));
                } catch (InterruptedException e7) {
                    Logger logger = LocalItems.log;
                    StringBuilder sbM112a = C0413b.m112a("Background execution interrupted: ");
                    sbM112a.append(e7.getMessage());
                    logger.severe(sbM112a.toString());
                }
                LocalItems.this.registry.getProtocolFactory().createSendingNotificationAlive(localDevice).run();
            }
        });
    }

    public void advertiseByebye(LocalDevice localDevice, boolean z6) {
        SendingNotificationByebye sendingNotificationByebyeCreateSendingNotificationByebye = this.registry.getProtocolFactory().createSendingNotificationByebye(localDevice);
        if (z6) {
            this.registry.executeAsyncProtocol(sendingNotificationByebyeCreateSendingNotificationByebye);
        } else {
            sendingNotificationByebyeCreateSendingNotificationByebye.run();
        }
    }

    public void advertiseLocalDevices() {
        Iterator it = this.deviceItems.iterator();
        while (it.hasNext()) {
            RegistryItem registryItem = (RegistryItem) it.next();
            if (isAdvertised((UDN) registryItem.getKey())) {
                advertiseAlive((LocalDevice) registryItem.getItem());
            }
        }
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public Collection<LocalDevice> get() {
        HashSet hashSet = new HashSet();
        Iterator<RegistryItem<UDN, LocalDevice>> it = getDeviceItems().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getItem());
        }
        return Collections.unmodifiableCollection(hashSet);
    }

    public DiscoveryOptions getDiscoveryOptions(UDN udn) {
        return this.discoveryOptions.get(udn);
    }

    public boolean isAdvertised(UDN udn) {
        return getDiscoveryOptions(udn) == null || getDiscoveryOptions(udn).isAdvertised();
    }

    public boolean isByeByeBeforeFirstAlive(UDN udn) {
        return getDiscoveryOptions(udn) != null && getDiscoveryOptions(udn).isByeByeBeforeFirstAlive();
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void maintain() {
        if (getDeviceItems().isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        int aliveIntervalMillis = this.registry.getConfiguration().getAliveIntervalMillis();
        if (aliveIntervalMillis > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.lastAliveIntervalTimestamp > aliveIntervalMillis) {
                this.lastAliveIntervalTimestamp = jCurrentTimeMillis;
                for (RegistryItem<UDN, LocalDevice> registryItem : getDeviceItems()) {
                    if (isAdvertised(registryItem.getKey())) {
                        log.finer("Flooding advertisement of local item: " + registryItem);
                        hashSet.add(registryItem);
                    }
                }
            }
        } else {
            this.lastAliveIntervalTimestamp = 0L;
            for (RegistryItem<UDN, LocalDevice> registryItem2 : getDeviceItems()) {
                if (isAdvertised(registryItem2.getKey()) && registryItem2.getExpirationDetails().hasExpired(true)) {
                    log.finer("Local item has expired: " + registryItem2);
                    hashSet.add(registryItem2);
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            RegistryItem registryItem3 = (RegistryItem) it.next();
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Refreshing local device advertisement: ");
            sbM112a.append(registryItem3.getItem());
            logger.fine(sbM112a.toString());
            advertiseAlive((LocalDevice) registryItem3.getItem());
            registryItem3.getExpirationDetails().stampLastRefresh();
        }
        HashSet hashSet2 = new HashSet();
        for (RegistryItem<String, LocalGENASubscription> registryItem4 : getSubscriptionItems()) {
            if (registryItem4.getExpirationDetails().hasExpired(false)) {
                hashSet2.add(registryItem4);
            }
        }
        Iterator it2 = hashSet2.iterator();
        while (it2.hasNext()) {
            RegistryItem registryItem5 = (RegistryItem) it2.next();
            log.fine("Removing expired: " + registryItem5);
            removeSubscription((LocalGENASubscription) registryItem5.getItem());
            ((LocalGENASubscription) registryItem5.getItem()).end(CancelReason.EXPIRED);
        }
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void removeAll() {
        removeAll(false);
    }

    public void setDiscoveryOptions(UDN udn, DiscoveryOptions discoveryOptions) {
        if (discoveryOptions != null) {
            this.discoveryOptions.put(udn, discoveryOptions);
        } else {
            this.discoveryOptions.remove(udn);
        }
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void shutdown() {
        log.fine("Clearing all registered subscriptions to local devices during shutdown");
        getSubscriptionItems().clear();
        log.fine("Removing all local devices from registry during shutdown");
        removeAll(true);
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public void add(LocalDevice localDevice) {
        add(localDevice, null);
    }

    @Override // org.fourthline.cling.registry.RegistryItems
    public boolean remove(LocalDevice localDevice) {
        return remove(localDevice, false);
    }

    public void removeAll(boolean z6) {
        for (LocalDevice localDevice : (LocalDevice[]) get().toArray(new LocalDevice[get().size()])) {
            remove(localDevice, z6);
        }
    }

    public void add(LocalDevice localDevice, DiscoveryOptions discoveryOptions) {
        setDiscoveryOptions(localDevice.getIdentity().getUdn(), discoveryOptions);
        if (this.registry.getDevice(localDevice.getIdentity().getUdn(), false) != null) {
            log.fine("Ignoring addition, device already registered: " + localDevice);
            return;
        }
        log.fine("Adding local device to registry: " + localDevice);
        for (Resource resource : getResources(localDevice)) {
            if (this.registry.getResource(resource.getPathQuery()) == null) {
                this.registry.addResource(resource);
                log.fine("Registered resource: " + resource);
            } else {
                throw new RegistrationException("URI namespace conflict with already registered resource: " + resource);
            }
        }
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Adding item to registry with expiration in seconds: ");
        sbM112a.append(localDevice.getIdentity().getMaxAgeSeconds());
        logger.fine(sbM112a.toString());
        RegistryItem<UDN, LocalDevice> registryItem = new RegistryItem<>(localDevice.getIdentity().getUdn(), localDevice, localDevice.getIdentity().getMaxAgeSeconds().intValue());
        getDeviceItems().add(registryItem);
        log.fine("Registered local device: " + registryItem);
        if (isByeByeBeforeFirstAlive(registryItem.getKey())) {
            advertiseByebye(localDevice, true);
        }
        if (isAdvertised(registryItem.getKey())) {
            advertiseAlive(localDevice);
        }
        Iterator<RegistryListener> it = this.registry.getListeners().iterator();
        while (it.hasNext()) {
            this.registry.getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.registry.LocalItems.1
                public final /* synthetic */ RegistryListener val$listener;
                public final /* synthetic */ LocalDevice val$localDevice;

                public RunnableC16851(RegistryListener registryListener, LocalDevice localDevice2) {
                    registryListener = registryListener;
                    localDevice = localDevice2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    registryListener.localDeviceAdded(LocalItems.this.registry, localDevice);
                }
            });
        }
    }

    public boolean remove(LocalDevice localDevice, boolean z6) {
        LocalDevice localDevice2 = get(localDevice.getIdentity().getUdn(), true);
        if (localDevice2 == null) {
            return false;
        }
        log.fine("Removing local device from registry: " + localDevice);
        setDiscoveryOptions(localDevice.getIdentity().getUdn(), null);
        getDeviceItems().remove(new RegistryItem(localDevice.getIdentity().getUdn()));
        for (Resource resource : getResources(localDevice)) {
            if (this.registry.removeResource(resource)) {
                log.fine("Unregistered resource: " + resource);
            }
        }
        Iterator<RegistryItem<String, LocalGENASubscription>> it = getSubscriptionItems().iterator();
        while (it.hasNext()) {
            RegistryItem<String, LocalGENASubscription> next = it.next();
            if (next.getItem().getService().getDevice().getIdentity().getUdn().equals(localDevice2.getIdentity().getUdn())) {
                Logger logger = log;
                StringBuilder sbM112a = C0413b.m112a("Removing incoming subscription: ");
                sbM112a.append(next.getKey());
                logger.fine(sbM112a.toString());
                it.remove();
                if (!z6) {
                    this.registry.getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.registry.LocalItems.2
                        public final /* synthetic */ RegistryItem val$incomingSubscription;

                        public RunnableC16862(RegistryItem next2) {
                            registryItem = next2;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            ((LocalGENASubscription) registryItem.getItem()).end(CancelReason.DEVICE_WAS_REMOVED);
                        }
                    });
                }
            }
        }
        if (isAdvertised(localDevice.getIdentity().getUdn())) {
            advertiseByebye(localDevice, !z6);
        }
        if (!z6) {
            Iterator<RegistryListener> it2 = this.registry.getListeners().iterator();
            while (it2.hasNext()) {
                this.registry.getConfiguration().getRegistryListenerExecutor().execute(new Runnable() { // from class: org.fourthline.cling.registry.LocalItems.3
                    public final /* synthetic */ RegistryListener val$listener;
                    public final /* synthetic */ LocalDevice val$localDevice;

                    public RunnableC16873(RegistryListener registryListener, LocalDevice localDevice3) {
                        registryListener = registryListener;
                        localDevice = localDevice3;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        registryListener.localDeviceRemoved(LocalItems.this.registry, localDevice);
                    }
                });
            }
        }
        return true;
    }
}
