package org.fourthline.cling.support.igd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UDAServiceType;
import org.fourthline.cling.registry.DefaultRegistryListener;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.support.igd.callback.PortMappingAdd;
import org.fourthline.cling.support.igd.callback.PortMappingDelete;
import org.fourthline.cling.support.model.PortMapping;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PortMappingListener extends DefaultRegistryListener {
    public Map<Service, List<PortMapping>> activePortMappings;
    public PortMapping[] portMappings;
    private static final Logger log = Logger.getLogger(PortMappingListener.class.getName());
    public static final DeviceType IGD_DEVICE_TYPE = new UDADeviceType("InternetGatewayDevice", 1);
    public static final DeviceType CONNECTION_DEVICE_TYPE = new UDADeviceType("WANConnectionDevice", 1);
    public static final ServiceType IP_SERVICE_TYPE = new UDAServiceType("WANIPConnection", 1);
    public static final ServiceType PPP_SERVICE_TYPE = new UDAServiceType("WANPPPConnection", 1);

    public PortMappingListener(PortMapping portMapping) {
        this(new PortMapping[]{portMapping});
    }

    @Override // org.fourthline.cling.registry.DefaultRegistryListener, org.fourthline.cling.registry.RegistryListener
    public synchronized void beforeShutdown(Registry registry) {
        for (Map.Entry<Service, List<PortMapping>> entry : this.activePortMappings.entrySet()) {
            final Iterator<PortMapping> it = entry.getValue().iterator();
            while (it.hasNext()) {
                final PortMapping next = it.next();
                log.fine("Trying to delete port mapping on IGD: " + next);
                new PortMappingDelete(entry.getKey(), registry.getUpnpService().getControlPoint(), next) { // from class: org.fourthline.cling.support.igd.PortMappingListener.2
                    @Override // org.fourthline.cling.controlpoint.ActionCallback
                    public void failure(ActionInvocation actionInvocation, UpnpResponse upnpResponse, String str) {
                        PortMappingListener portMappingListener = PortMappingListener.this;
                        StringBuilder sbM112a = C0413b.m112a("Failed to delete port mapping: ");
                        sbM112a.append(next);
                        portMappingListener.handleFailureMessage(sbM112a.toString());
                        PortMappingListener.this.handleFailureMessage("Reason: " + str);
                    }

                    @Override // org.fourthline.cling.controlpoint.ActionCallback
                    public void success(ActionInvocation actionInvocation) {
                        Logger logger = PortMappingListener.log;
                        StringBuilder sbM112a = C0413b.m112a("Port mapping deleted: ");
                        sbM112a.append(next);
                        logger.fine(sbM112a.toString());
                        it.remove();
                    }
                }.run();
            }
        }
    }

    @Override // org.fourthline.cling.registry.DefaultRegistryListener
    public synchronized void deviceAdded(Registry registry, Device device) {
        Service serviceDiscoverConnectionService = discoverConnectionService(device);
        if (serviceDiscoverConnectionService == null) {
            return;
        }
        log.fine("Activating port mappings on: " + serviceDiscoverConnectionService);
        final ArrayList arrayList = new ArrayList();
        for (final PortMapping portMapping : this.portMappings) {
            new PortMappingAdd(serviceDiscoverConnectionService, registry.getUpnpService().getControlPoint(), portMapping) { // from class: org.fourthline.cling.support.igd.PortMappingListener.1
                @Override // org.fourthline.cling.controlpoint.ActionCallback
                public void failure(ActionInvocation actionInvocation, UpnpResponse upnpResponse, String str) {
                    PortMappingListener portMappingListener = PortMappingListener.this;
                    StringBuilder sbM112a = C0413b.m112a("Failed to add port mapping: ");
                    sbM112a.append(portMapping);
                    portMappingListener.handleFailureMessage(sbM112a.toString());
                    PortMappingListener.this.handleFailureMessage("Reason: " + str);
                }

                @Override // org.fourthline.cling.controlpoint.ActionCallback
                public void success(ActionInvocation actionInvocation) {
                    Logger logger = PortMappingListener.log;
                    StringBuilder sbM112a = C0413b.m112a("Port mapping added: ");
                    sbM112a.append(portMapping);
                    logger.fine(sbM112a.toString());
                    arrayList.add(portMapping);
                }
            }.run();
        }
        this.activePortMappings.put(serviceDiscoverConnectionService, arrayList);
    }

    @Override // org.fourthline.cling.registry.DefaultRegistryListener
    public synchronized void deviceRemoved(Registry registry, Device device) {
        for (Service service : device.findServices()) {
            Iterator<Map.Entry<Service, List<PortMapping>>> it = this.activePortMappings.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Service, List<PortMapping>> next = it.next();
                if (next.getKey().equals(service)) {
                    if (next.getValue().size() > 0) {
                        handleFailureMessage("Device disappeared, couldn't delete port mappings: " + next.getValue().size());
                    }
                    it.remove();
                }
            }
        }
    }

    public Service discoverConnectionService(Device device) {
        if (!device.getType().equals(IGD_DEVICE_TYPE)) {
            return null;
        }
        DeviceType deviceType = CONNECTION_DEVICE_TYPE;
        Device[] deviceArrFindDevices = device.findDevices(deviceType);
        if (deviceArrFindDevices.length == 0) {
            log.fine("IGD doesn't support '" + deviceType + "': " + device);
            return null;
        }
        Device device2 = deviceArrFindDevices[0];
        Logger logger = log;
        logger.fine("Using first discovered WAN connection device: " + device2);
        Service serviceFindService = device2.findService(IP_SERVICE_TYPE);
        Service serviceFindService2 = device2.findService(PPP_SERVICE_TYPE);
        if (serviceFindService == null && serviceFindService2 == null) {
            logger.fine("IGD doesn't support IP or PPP WAN connection service: " + device);
        }
        return serviceFindService != null ? serviceFindService : serviceFindService2;
    }

    public void handleFailureMessage(String str) {
        log.warning(str);
    }

    public PortMappingListener(PortMapping[] portMappingArr) {
        this.activePortMappings = new HashMap();
        this.portMappings = portMappingArr;
    }
}
