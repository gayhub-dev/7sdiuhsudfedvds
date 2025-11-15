package org.fourthline.cling.protocol;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.binding.xml.DescriptorBindingException;
import org.fourthline.cling.binding.xml.DeviceDescriptorBinder;
import org.fourthline.cling.binding.xml.ServiceDescriptorBinder;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.message.StreamRequestMessage;
import org.fourthline.cling.model.message.StreamResponseMessage;
import org.fourthline.cling.model.message.UpnpHeaders;
import org.fourthline.cling.model.message.UpnpRequest;
import org.fourthline.cling.model.meta.Icon;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.model.meta.RemoteDeviceIdentity;
import org.fourthline.cling.model.meta.RemoteService;
import org.fourthline.cling.model.types.ServiceType;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.RegistrationException;
import org.fourthline.cling.transport.RouterException;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class RetrieveRemoteDescriptors implements Runnable {
    public List<UDN> errorsAlreadyLogged = new ArrayList();

    /* renamed from: rd */
    private RemoteDevice f4881rd;
    private final UpnpService upnpService;
    private static final Logger log = Logger.getLogger(RetrieveRemoteDescriptors.class.getName());
    private static final Set<URL> activeRetrievals = new CopyOnWriteArraySet();

    public RetrieveRemoteDescriptors(UpnpService upnpService, RemoteDevice remoteDevice) {
        this.upnpService = upnpService;
        this.f4881rd = remoteDevice;
    }

    public void describe() {
        if (getUpnpService().getRouter() == null) {
            log.warning("Router not yet initialized");
            return;
        }
        try {
            StreamRequestMessage streamRequestMessage = new StreamRequestMessage(UpnpRequest.Method.GET, this.f4881rd.getIdentity().getDescriptorURL());
            UpnpHeaders descriptorRetrievalHeaders = getUpnpService().getConfiguration().getDescriptorRetrievalHeaders(this.f4881rd.getIdentity());
            if (descriptorRetrievalHeaders != null) {
                streamRequestMessage.getHeaders().putAll(descriptorRetrievalHeaders);
            }
            Logger logger = log;
            logger.fine("Sending device descriptor retrieval message: " + streamRequestMessage);
            StreamResponseMessage streamResponseMessageSend = getUpnpService().getRouter().send(streamRequestMessage);
            if (streamResponseMessageSend == null) {
                StringBuilder sbM112a = C0413b.m112a("Device descriptor retrieval failed, no response: ");
                sbM112a.append(this.f4881rd.getIdentity().getDescriptorURL());
                logger.warning(sbM112a.toString());
                return;
            }
            if (streamResponseMessageSend.getOperation().isFailed()) {
                StringBuilder sbM112a2 = C0413b.m112a("Device descriptor retrieval failed: ");
                sbM112a2.append(this.f4881rd.getIdentity().getDescriptorURL());
                sbM112a2.append(", ");
                sbM112a2.append(streamResponseMessageSend.getOperation().getResponseDetails());
                logger.warning(sbM112a2.toString());
                return;
            }
            if (!streamResponseMessageSend.isContentTypeTextUDA()) {
                StringBuilder sbM112a3 = C0413b.m112a("Received device descriptor without or with invalid Content-Type: ");
                sbM112a3.append(this.f4881rd.getIdentity().getDescriptorURL());
                logger.fine(sbM112a3.toString());
            }
            String bodyString = streamResponseMessageSend.getBodyString();
            if (bodyString == null || bodyString.length() == 0) {
                StringBuilder sbM112a4 = C0413b.m112a("Received empty device descriptor:");
                sbM112a4.append(this.f4881rd.getIdentity().getDescriptorURL());
                logger.warning(sbM112a4.toString());
            } else {
                logger.fine("Received root device descriptor: " + streamResponseMessageSend);
                describe(bodyString);
            }
        } catch (IllegalArgumentException e7) {
            Logger logger2 = log;
            StringBuilder sbM112a5 = C0413b.m112a("Device descriptor retrieval failed: ");
            sbM112a5.append(this.f4881rd.getIdentity().getDescriptorURL());
            sbM112a5.append(", possibly invalid URL: ");
            sbM112a5.append(e7);
            logger2.warning(sbM112a5.toString());
        }
    }

    public RemoteService describeService(RemoteService remoteService) {
        try {
            URL urlNormalizeURI = remoteService.getDevice().normalizeURI(remoteService.getDescriptorURI());
            StreamRequestMessage streamRequestMessage = new StreamRequestMessage(UpnpRequest.Method.GET, urlNormalizeURI);
            UpnpHeaders descriptorRetrievalHeaders = getUpnpService().getConfiguration().getDescriptorRetrievalHeaders(remoteService.getDevice().getIdentity());
            if (descriptorRetrievalHeaders != null) {
                streamRequestMessage.getHeaders().putAll(descriptorRetrievalHeaders);
            }
            Logger logger = log;
            logger.fine("Sending service descriptor retrieval message: " + streamRequestMessage);
            StreamResponseMessage streamResponseMessageSend = getUpnpService().getRouter().send(streamRequestMessage);
            if (streamResponseMessageSend == null) {
                logger.warning("Could not retrieve service descriptor, no response: " + remoteService);
                return null;
            }
            if (streamResponseMessageSend.getOperation().isFailed()) {
                logger.warning("Service descriptor retrieval failed: " + urlNormalizeURI + ", " + streamResponseMessageSend.getOperation().getResponseDetails());
                return null;
            }
            if (!streamResponseMessageSend.isContentTypeTextUDA()) {
                logger.fine("Received service descriptor without or with invalid Content-Type: " + urlNormalizeURI);
            }
            String bodyString = streamResponseMessageSend.getBodyString();
            if (bodyString == null || bodyString.length() == 0) {
                logger.warning("Received empty service descriptor:" + urlNormalizeURI);
                return null;
            }
            logger.fine("Received service descriptor, hydrating service model: " + streamResponseMessageSend);
            return (RemoteService) getUpnpService().getConfiguration().getServiceDescriptorBinderUDA10().describe((ServiceDescriptorBinder) remoteService, bodyString);
        } catch (IllegalArgumentException unused) {
            Logger logger2 = log;
            StringBuilder sbM112a = C0413b.m112a("Could not normalize service descriptor URL: ");
            sbM112a.append(remoteService.getDescriptorURI());
            logger2.warning(sbM112a.toString());
            return null;
        }
    }

    public RemoteDevice describeServices(RemoteDevice remoteDevice) {
        RemoteDevice remoteDeviceDescribeServices;
        ArrayList arrayList = new ArrayList();
        if (remoteDevice.hasServices()) {
            for (RemoteService remoteService : filterExclusiveServices(remoteDevice.getServices())) {
                RemoteService remoteServiceDescribeService = describeService(remoteService);
                if (remoteServiceDescribeService != null) {
                    arrayList.add(remoteServiceDescribeService);
                } else {
                    log.warning("Skipping invalid service '" + remoteService + "' of: " + remoteDevice);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (remoteDevice.hasEmbeddedDevices()) {
            for (RemoteDevice remoteDevice2 : remoteDevice.getEmbeddedDevices()) {
                if (remoteDevice2 != null && (remoteDeviceDescribeServices = describeServices(remoteDevice2)) != null) {
                    arrayList2.add(remoteDeviceDescribeServices);
                }
            }
        }
        Icon[] iconArr = new Icon[remoteDevice.getIcons().length];
        for (int i7 = 0; i7 < remoteDevice.getIcons().length; i7++) {
            iconArr[i7] = remoteDevice.getIcons()[i7].deepCopy();
        }
        return remoteDevice.newInstance(((RemoteDeviceIdentity) remoteDevice.getIdentity()).getUdn(), remoteDevice.getVersion(), remoteDevice.getType(), remoteDevice.getDetails(), iconArr, remoteDevice.toServiceArray((Collection<RemoteService>) arrayList), (List<RemoteDevice>) arrayList2);
    }

    public List<RemoteService> filterExclusiveServices(RemoteService[] remoteServiceArr) {
        ServiceType[] exclusiveServiceTypes = getUpnpService().getConfiguration().getExclusiveServiceTypes();
        if (exclusiveServiceTypes == null || exclusiveServiceTypes.length == 0) {
            return Arrays.asList(remoteServiceArr);
        }
        ArrayList arrayList = new ArrayList();
        for (RemoteService remoteService : remoteServiceArr) {
            for (ServiceType serviceType : exclusiveServiceTypes) {
                if (remoteService.getServiceType().implementsVersion(serviceType)) {
                    log.fine("Including exclusive service: " + remoteService);
                    arrayList.add(remoteService);
                } else {
                    log.fine("Excluding unwanted service: " + serviceType);
                }
            }
        }
        return arrayList;
    }

    public UpnpService getUpnpService() {
        return this.upnpService;
    }

    @Override // java.lang.Runnable
    public void run() {
        URL descriptorURL = this.f4881rd.getIdentity().getDescriptorURL();
        Set<URL> set = activeRetrievals;
        if (set.contains(descriptorURL)) {
            log.finer("Exiting early, active retrieval for URL already in progress: " + descriptorURL);
            return;
        }
        if (getUpnpService().getRegistry().getRemoteDevice(this.f4881rd.getIdentity().getUdn(), true) != null) {
            log.finer("Exiting early, already discovered: " + descriptorURL);
            return;
        }
        try {
            try {
                set.add(descriptorURL);
                describe();
            } catch (RouterException e7) {
                log.log(Level.WARNING, "Descriptor retrieval failed: " + descriptorURL, (Throwable) e7);
                set = activeRetrievals;
            }
            set.remove(descriptorURL);
        } catch (Throwable th) {
            activeRetrievals.remove(descriptorURL);
            throw th;
        }
    }

    public void describe(String str) {
        RegistrationException e7;
        RemoteDevice remoteDevice;
        ValidationException e8;
        DescriptorBindingException e9;
        RemoteDevice remoteDevice2 = null;
        try {
            remoteDevice = (RemoteDevice) getUpnpService().getConfiguration().getDeviceDescriptorBinderUDA10().describe((DeviceDescriptorBinder) this.f4881rd, str);
        } catch (DescriptorBindingException e10) {
            e9 = e10;
            remoteDevice = null;
        } catch (ValidationException e11) {
            e8 = e11;
        } catch (RegistrationException e12) {
            e7 = e12;
            remoteDevice = null;
        }
        try {
            Logger logger = log;
            logger.fine("Remote device described (without services) notifying listeners: " + remoteDevice);
            boolean zNotifyDiscoveryStart = getUpnpService().getRegistry().notifyDiscoveryStart(remoteDevice);
            logger.fine("Hydrating described device's services: " + remoteDevice);
            RemoteDevice remoteDeviceDescribeServices = describeServices(remoteDevice);
            if (remoteDeviceDescribeServices == null) {
                if (!this.errorsAlreadyLogged.contains(this.f4881rd.getIdentity().getUdn())) {
                    this.errorsAlreadyLogged.add(this.f4881rd.getIdentity().getUdn());
                    logger.warning("Device service description failed: " + this.f4881rd);
                }
                if (zNotifyDiscoveryStart) {
                    getUpnpService().getRegistry().notifyDiscoveryFailure(remoteDevice, new DescriptorBindingException("Device service description failed: " + this.f4881rd));
                    return;
                }
                return;
            }
            logger.fine("Adding fully hydrated remote device to registry: " + remoteDeviceDescribeServices);
            getUpnpService().getRegistry().addDevice(remoteDeviceDescribeServices);
        } catch (DescriptorBindingException e13) {
            e9 = e13;
            Logger logger2 = log;
            StringBuilder sbM112a = C0413b.m112a("Could not hydrate device or its services from descriptor: ");
            sbM112a.append(this.f4881rd);
            logger2.warning(sbM112a.toString());
            logger2.warning("Cause was: " + C2074b.m2475O(e9));
            if (remoteDevice == null || 0 == 0) {
                return;
            }
            getUpnpService().getRegistry().notifyDiscoveryFailure(remoteDevice, e9);
        } catch (ValidationException e14) {
            e8 = e14;
            remoteDevice2 = remoteDevice;
            if (this.errorsAlreadyLogged.contains(this.f4881rd.getIdentity().getUdn())) {
                return;
            }
            this.errorsAlreadyLogged.add(this.f4881rd.getIdentity().getUdn());
            Logger logger3 = log;
            StringBuilder sbM112a2 = C0413b.m112a("Could not validate device model: ");
            sbM112a2.append(this.f4881rd);
            logger3.warning(sbM112a2.toString());
            Iterator<ValidationError> it = e8.getErrors().iterator();
            while (it.hasNext()) {
                log.warning(it.next().toString());
            }
            if (remoteDevice2 == null || 0 == 0) {
                return;
            }
            getUpnpService().getRegistry().notifyDiscoveryFailure(remoteDevice2, e8);
        } catch (RegistrationException e15) {
            e7 = e15;
            Logger logger4 = log;
            StringBuilder sbM112a3 = C0413b.m112a("Adding hydrated device to registry failed: ");
            sbM112a3.append(this.f4881rd);
            logger4.warning(sbM112a3.toString());
            logger4.warning("Cause was: " + e7.toString());
            if (remoteDevice == null || 0 == 0) {
                return;
            }
            getUpnpService().getRegistry().notifyDiscoveryFailure(remoteDevice, e7);
        }
    }
}
