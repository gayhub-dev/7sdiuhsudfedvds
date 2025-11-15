package org.fourthline.cling.model.meta;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.fourthline.cling.model.Namespace;
import org.fourthline.cling.model.resource.Resource;
import org.fourthline.cling.model.resource.ServiceEventCallbackResource;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDN;
import p098l6.C1449c;

/* loaded from: classes.dex */
public class RemoteDevice extends Device<RemoteDeviceIdentity, RemoteDevice, RemoteService> {
    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity) {
        super(remoteDeviceIdentity);
    }

    @Override // org.fourthline.cling.model.meta.Device
    public Resource[] discoverResources(Namespace namespace) {
        ArrayList arrayList = new ArrayList();
        for (RemoteService remoteService : getServices()) {
            if (remoteService != null) {
                arrayList.add(new ServiceEventCallbackResource(namespace.getEventCallbackPath(remoteService), remoteService));
            }
        }
        if (hasEmbeddedDevices()) {
            for (RemoteDevice remoteDevice : getEmbeddedDevices()) {
                if (remoteDevice != null) {
                    arrayList.addAll(Arrays.asList(remoteDevice.discoverResources(namespace)));
                }
            }
        }
        return (Resource[]) arrayList.toArray(new Resource[arrayList.size()]);
    }

    @Override // org.fourthline.cling.model.meta.Device
    public /* bridge */ /* synthetic */ Device newInstance(UDN udn, UDAVersion uDAVersion, DeviceType deviceType, DeviceDetails deviceDetails, Icon[] iconArr, Service[] serviceArr, List list) {
        return newInstance(udn, uDAVersion, deviceType, deviceDetails, iconArr, (RemoteService[]) serviceArr, (List<RemoteDevice>) list);
    }

    public URL normalizeURI(URI uri) {
        return (getDetails() == null || getDetails().getBaseURL() == null) ? C1449c.m1635b(getIdentity().getDescriptorURL(), uri) : C1449c.m1635b(getDetails().getBaseURL(), uri);
    }

    @Override // org.fourthline.cling.model.meta.Device
    public /* bridge */ /* synthetic */ Device[] toDeviceArray(Collection collection) {
        return toDeviceArray((Collection<RemoteDevice>) collection);
    }

    @Override // org.fourthline.cling.model.meta.Device
    public /* bridge */ /* synthetic */ Service[] toServiceArray(Collection collection) {
        return toServiceArray((Collection<RemoteService>) collection);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, RemoteService remoteService) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, null, new RemoteService[]{remoteService});
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteDevice findDevice(UDN udn) {
        return find(udn, (UDN) this);
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteDevice[] getEmbeddedDevices() {
        D[] dArr = this.embeddedDevices;
        return dArr != 0 ? (RemoteDevice[]) dArr : new RemoteDevice[0];
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteDevice getRoot() {
        if (isRoot()) {
            return this;
        }
        RemoteDevice parentDevice = this;
        while (parentDevice.getParentDevice() != null) {
            parentDevice = parentDevice.getParentDevice();
        }
        return parentDevice;
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteService[] getServices() {
        S[] sArr = this.services;
        return sArr != 0 ? (RemoteService[]) sArr : new RemoteService[0];
    }

    @Override // org.fourthline.cling.model.meta.Device
    public /* bridge */ /* synthetic */ Service newInstance(ServiceType serviceType, ServiceId serviceId, URI uri, URI uri2, URI uri3, Action[] actionArr, StateVariable[] stateVariableArr) {
        return newInstance(serviceType, serviceId, uri, uri2, uri3, (Action<RemoteService>[]) actionArr, (StateVariable<RemoteService>[]) stateVariableArr);
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteService[] newServiceArray(int i7) {
        return new RemoteService[i7];
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteDevice[] toDeviceArray(Collection<RemoteDevice> collection) {
        return (RemoteDevice[]) collection.toArray(new RemoteDevice[collection.size()]);
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteService[] toServiceArray(Collection<RemoteService> collection) {
        return (RemoteService[]) collection.toArray(new RemoteService[collection.size()]);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, RemoteService remoteService, RemoteDevice remoteDevice) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, null, new RemoteService[]{remoteService}, new RemoteDevice[]{remoteDevice});
    }

    public RemoteDevice newInstance(UDN udn, UDAVersion uDAVersion, DeviceType deviceType, DeviceDetails deviceDetails, Icon[] iconArr, RemoteService[] remoteServiceArr, List<RemoteDevice> list) {
        return new RemoteDevice(new RemoteDeviceIdentity(udn, getIdentity()), uDAVersion, deviceType, deviceDetails, iconArr, remoteServiceArr, list.size() > 0 ? (RemoteDevice[]) list.toArray(new RemoteDevice[list.size()]) : null);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, RemoteService[] remoteServiceArr) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, null, remoteServiceArr);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, RemoteService[] remoteServiceArr, RemoteDevice[] remoteDeviceArr) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, null, remoteServiceArr, remoteDeviceArr);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon icon, RemoteService remoteService) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, new Icon[]{icon}, new RemoteService[]{remoteService});
    }

    @Override // org.fourthline.cling.model.meta.Device
    public RemoteService newInstance(ServiceType serviceType, ServiceId serviceId, URI uri, URI uri2, URI uri3, Action<RemoteService>[] actionArr, StateVariable<RemoteService>[] stateVariableArr) {
        return new RemoteService(serviceType, serviceId, uri, uri2, uri3, actionArr, stateVariableArr);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon icon, RemoteService remoteService, RemoteDevice remoteDevice) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, new Icon[]{icon}, new RemoteService[]{remoteService}, new RemoteDevice[]{remoteDevice});
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon icon, RemoteService[] remoteServiceArr) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, new Icon[]{icon}, remoteServiceArr);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon icon, RemoteService[] remoteServiceArr, RemoteDevice[] remoteDeviceArr) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, new Icon[]{icon}, remoteServiceArr, remoteDeviceArr);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon[] iconArr, RemoteService remoteService) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, iconArr, new RemoteService[]{remoteService});
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon[] iconArr, RemoteService remoteService, RemoteDevice remoteDevice) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, iconArr, new RemoteService[]{remoteService}, new RemoteDevice[]{remoteDevice});
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon[] iconArr, RemoteService[] remoteServiceArr) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, iconArr, remoteServiceArr);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, DeviceType deviceType, DeviceDetails deviceDetails, Icon[] iconArr, RemoteService[] remoteServiceArr, RemoteDevice[] remoteDeviceArr) {
        super(remoteDeviceIdentity, deviceType, deviceDetails, iconArr, remoteServiceArr, remoteDeviceArr);
    }

    public RemoteDevice(RemoteDeviceIdentity remoteDeviceIdentity, UDAVersion uDAVersion, DeviceType deviceType, DeviceDetails deviceDetails, Icon[] iconArr, RemoteService[] remoteServiceArr, RemoteDevice[] remoteDeviceArr) {
        super(remoteDeviceIdentity, uDAVersion, deviceType, deviceDetails, iconArr, remoteServiceArr, remoteDeviceArr);
    }
}
