package org.fourthline.cling.registry.event;

import org.fourthline.cling.model.meta.Device;

/* loaded from: classes.dex */
public class DeviceDiscovery<D extends Device> {
    public D device;

    public DeviceDiscovery(D d7) {
        this.device = d7;
    }

    public D getDevice() {
        return this.device;
    }
}
