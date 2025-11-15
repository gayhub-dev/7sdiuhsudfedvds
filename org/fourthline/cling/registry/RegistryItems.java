package org.fourthline.cling.registry;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.gena.GENASubscription;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.resource.Resource;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDN;
import p009b.C0413b;

/* loaded from: classes.dex */
abstract class RegistryItems<D extends Device, S extends GENASubscription> {
    public final RegistryImpl registry;
    public final Set<RegistryItem<UDN, D>> deviceItems = new HashSet();
    public final Set<RegistryItem<String, S>> subscriptionItems = new HashSet();

    public RegistryItems(RegistryImpl registryImpl) {
        this.registry = registryImpl;
    }

    public abstract void add(D d7);

    public void addSubscription(S s6) {
        this.subscriptionItems.add(new RegistryItem<>(s6.getSubscriptionId(), s6, s6.getActualDurationSeconds()));
    }

    public boolean contains(D d7) {
        return contains(d7.getIdentity().getUdn());
    }

    public D get(UDN udn, boolean z6) {
        D d7;
        for (RegistryItem<UDN, D> registryItem : this.deviceItems) {
            D item = registryItem.getItem();
            if (item.getIdentity().getUdn().equals(udn)) {
                return item;
            }
            if (!z6 && (d7 = (D) registryItem.getItem().findDevice(udn)) != null) {
                return d7;
            }
        }
        return null;
    }

    public Set<RegistryItem<UDN, D>> getDeviceItems() {
        return this.deviceItems;
    }

    public Resource[] getResources(Device device) {
        try {
            return this.registry.getConfiguration().getNamespace().getResources(device);
        } catch (ValidationException e7) {
            StringBuilder sbM112a = C0413b.m112a("Resource discover error: ");
            sbM112a.append(e7.toString());
            throw new RegistrationException(sbM112a.toString(), e7);
        }
    }

    public S getSubscription(String str) {
        for (RegistryItem<String, S> registryItem : this.subscriptionItems) {
            if (registryItem.getKey().equals(str)) {
                return registryItem.getItem();
            }
        }
        return null;
    }

    public Set<RegistryItem<String, S>> getSubscriptionItems() {
        return this.subscriptionItems;
    }

    public abstract void maintain();

    public abstract boolean remove(D d7);

    public abstract void removeAll();

    public boolean removeSubscription(S s6) {
        return this.subscriptionItems.remove(new RegistryItem(s6.getSubscriptionId()));
    }

    public abstract void shutdown();

    public boolean updateSubscription(S s6) {
        if (!removeSubscription(s6)) {
            return false;
        }
        addSubscription(s6);
        return true;
    }

    public boolean contains(UDN udn) {
        return this.deviceItems.contains(new RegistryItem(udn));
    }

    public Collection<D> get(DeviceType deviceType) {
        HashSet hashSet = new HashSet();
        Iterator<RegistryItem<UDN, D>> it = this.deviceItems.iterator();
        while (it.hasNext()) {
            Device[] deviceArrFindDevices = it.next().getItem().findDevices(deviceType);
            if (deviceArrFindDevices != null) {
                hashSet.addAll(Arrays.asList(deviceArrFindDevices));
            }
        }
        return hashSet;
    }

    public Collection<D> get(ServiceType serviceType) {
        HashSet hashSet = new HashSet();
        Iterator<RegistryItem<UDN, D>> it = this.deviceItems.iterator();
        while (it.hasNext()) {
            Device[] deviceArrFindDevices = it.next().getItem().findDevices(serviceType);
            if (deviceArrFindDevices != null) {
                hashSet.addAll(Arrays.asList(deviceArrFindDevices));
            }
        }
        return hashSet;
    }

    public Collection<D> get() {
        HashSet hashSet = new HashSet();
        Iterator<RegistryItem<UDN, D>> it = this.deviceItems.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getItem());
        }
        return hashSet;
    }
}
