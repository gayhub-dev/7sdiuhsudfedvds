package org.fourthline.cling.model;

import android.support.constraint.motion.C0080b;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.logging.Logger;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.Icon;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.resource.Resource;
import p098l6.C1449c;

/* loaded from: classes.dex */
public class Namespace {
    public static final String CALLBACK_FILE = "/cb";
    public static final String CONTROL = "/action";
    public static final String DESCRIPTOR_FILE = "/desc";
    public static final String DEVICE = "/dev";
    public static final String EVENTS = "/event";
    public static final String SERVICE = "/svc";
    private static final Logger log = Logger.getLogger(Namespace.class.getName());
    public final URI basePath;
    public final String decodedPath;

    public Namespace() {
        this("");
    }

    public URI appendPathToBaseURI(String str) {
        try {
            return new URI(this.basePath.getScheme(), null, this.basePath.getHost(), this.basePath.getPort(), this.decodedPath + str, null, null);
        } catch (URISyntaxException unused) {
            return URI.create(this.basePath + str);
        }
    }

    public URI getBasePath() {
        return this.basePath;
    }

    public URI getControlPath(Service service) {
        return appendPathToBaseURI(getServicePath(service) + CONTROL);
    }

    public URI getDescriptorPath(Device device) {
        return appendPathToBaseURI(getDevicePath(device.getRoot()) + DESCRIPTOR_FILE);
    }

    public String getDescriptorPathString(Device device) {
        return this.decodedPath + getDevicePath(device.getRoot()) + DESCRIPTOR_FILE;
    }

    public String getDevicePath(Device device) throws UnsupportedEncodingException {
        String string;
        if (device.getIdentity().getUdn() == null) {
            throw new IllegalStateException("Can't generate local URI prefix without UDN");
        }
        StringBuilder sbM94a = C0080b.m94a(DEVICE, ServiceReference.DELIMITER);
        String identifierString = device.getIdentity().getUdn().getIdentifierString();
        BitSet bitSet = C1449c.f4208b;
        if (identifierString == null) {
            string = null;
        } else {
            StringBuilder sb = new StringBuilder(identifierString.length() * 3);
            try {
                for (char c7 : identifierString.toCharArray()) {
                    if (bitSet.get(c7)) {
                        sb.append(c7);
                    } else {
                        for (byte b7 : String.valueOf(c7).getBytes("UTF-8")) {
                            sb.append(String.format("%%%1$02X", Integer.valueOf(b7 & 255)));
                        }
                    }
                }
                string = sb.toString();
            } catch (Exception e7) {
                throw new RuntimeException(e7);
            }
        }
        sbM94a.append(string);
        return sbM94a.toString();
    }

    public URI getEventCallbackPath(Service service) {
        return appendPathToBaseURI(getServicePath(service) + EVENTS + CALLBACK_FILE);
    }

    public String getEventCallbackPathString(Service service) {
        return this.decodedPath + getServicePath(service) + EVENTS + CALLBACK_FILE;
    }

    public URI getEventSubscriptionPath(Service service) {
        return appendPathToBaseURI(getServicePath(service) + EVENTS);
    }

    public URI getIconPath(Icon icon) {
        return appendPathToBaseURI(getDevicePath(icon.getDevice()) + ServiceReference.DELIMITER + icon.getUri().toString());
    }

    public URI getPath(Device device) {
        return appendPathToBaseURI(getDevicePath(device));
    }

    public Resource[] getResources(Device device) throws ValidationException {
        if (!device.isRoot()) {
            return null;
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        log.fine("Discovering local resources of device graph");
        for (Resource resource : device.discoverResources(this)) {
            Logger logger = log;
            logger.finer("Discovered: " + resource);
            if (!hashSet.add(resource)) {
                logger.finer("Local resource already exists, queueing validation error");
                arrayList.add(new ValidationError(getClass(), "resources", "Local URI namespace conflict between resources of device: " + resource));
            }
        }
        if (arrayList.size() <= 0) {
            return (Resource[]) hashSet.toArray(new Resource[hashSet.size()]);
        }
        throw new ValidationException("Validation of device graph failed, call getErrors() on exception", arrayList);
    }

    public String getServicePath(Service service) {
        if (service.getServiceId() == null) {
            throw new IllegalStateException("Can't generate local URI prefix without service ID");
        }
        StringBuilder sbM94a = C0080b.m94a(SERVICE, ServiceReference.DELIMITER);
        sbM94a.append(service.getServiceId().getNamespace());
        sbM94a.append(ServiceReference.DELIMITER);
        sbM94a.append(service.getServiceId().getId());
        return getDevicePath(service.getDevice()) + sbM94a.toString();
    }

    public boolean isControlPath(URI uri) {
        return uri.toString().endsWith(CONTROL);
    }

    public boolean isEventCallbackPath(URI uri) {
        return uri.toString().endsWith(CALLBACK_FILE);
    }

    public boolean isEventSubscriptionPath(URI uri) {
        return uri.toString().endsWith(EVENTS);
    }

    public URI prefixIfRelative(Device device, URI uri) {
        if (uri.isAbsolute() || uri.getPath().startsWith(ServiceReference.DELIMITER)) {
            return uri;
        }
        return appendPathToBaseURI(getDevicePath(device) + ServiceReference.DELIMITER + uri);
    }

    public Namespace(String str) {
        this(URI.create(str));
    }

    public URI getDescriptorPath(Service service) {
        return appendPathToBaseURI(getServicePath(service) + DESCRIPTOR_FILE);
    }

    public URI getPath(Service service) {
        return appendPathToBaseURI(getServicePath(service));
    }

    public Namespace(URI uri) {
        this.basePath = uri;
        this.decodedPath = uri.getPath();
    }
}
