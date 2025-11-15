package org.fourthline.cling.model.message.header;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class UpnpHeader<T> {
    private static final Logger log = Logger.getLogger(UpnpHeader.class.getName());
    private T value;

    public enum Type {
        USN("USN", USNRootDeviceHeader.class, DeviceUSNHeader.class, ServiceUSNHeader.class, UDNHeader.class),
        NT("NT", RootDeviceHeader.class, UDADeviceTypeHeader.class, UDAServiceTypeHeader.class, DeviceTypeHeader.class, ServiceTypeHeader.class, UDNHeader.class, NTEventHeader.class),
        NTS("NTS", NTSHeader.class),
        HOST("HOST", HostHeader.class),
        SERVER("SERVER", ServerHeader.class),
        LOCATION("LOCATION", LocationHeader.class),
        MAX_AGE("CACHE-CONTROL", MaxAgeHeader.class),
        USER_AGENT("USER-AGENT", UserAgentHeader.class),
        CONTENT_TYPE("CONTENT-TYPE", ContentTypeHeader.class),
        MAN("MAN", MANHeader.class),
        MX("MX", MXHeader.class),
        ST("ST", STAllHeader.class, RootDeviceHeader.class, UDADeviceTypeHeader.class, UDAServiceTypeHeader.class, DeviceTypeHeader.class, ServiceTypeHeader.class, UDNHeader.class),
        EXT("EXT", EXTHeader.class),
        SOAPACTION("SOAPACTION", SoapActionHeader.class),
        TIMEOUT("TIMEOUT", TimeoutHeader.class),
        CALLBACK("CALLBACK", CallbackHeader.class),
        SID("SID", SubscriptionIdHeader.class),
        SEQ("SEQ", EventSequenceHeader.class),
        RANGE("RANGE", RangeHeader.class),
        CONTENT_RANGE("CONTENT-RANGE", ContentRangeHeader.class),
        PRAGMA("PRAGMA", PragmaHeader.class),
        EXT_IFACE_MAC("X-CLING-IFACE-MAC", InterfaceMacHeader.class),
        EXT_AV_CLIENT_INFO("X-AV-CLIENT-INFO", AVClientInfoHeader.class);

        private static Map<String, Type> byName = new HashMap<String, Type>() { // from class: org.fourthline.cling.model.message.header.UpnpHeader.Type.1
            {
                for (Type type : Type.values()) {
                    put(type.getHttpName(), type);
                }
            }
        };
        private Class<? extends UpnpHeader>[] headerTypes;
        private String httpName;

        @SafeVarargs
        Type(String str, Class... clsArr) {
            this.httpName = str;
            this.headerTypes = clsArr;
        }

        public static Type getByHttpName(String str) {
            if (str == null) {
                return null;
            }
            return byName.get(str.toUpperCase(Locale.ROOT));
        }

        public Class<? extends UpnpHeader>[] getHeaderTypes() {
            return this.headerTypes;
        }

        public String getHttpName() {
            return this.httpName;
        }

        public boolean isValidHeaderType(Class<? extends UpnpHeader> cls) {
            for (Class<? extends UpnpHeader> cls2 : getHeaderTypes()) {
                if (cls2.isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static UpnpHeader newInstance(Type type, String str) {
        UpnpHeader upnpHeaderNewInstance;
        Exception e7;
        UpnpHeader upnpHeader = null;
        for (int i7 = 0; i7 < type.getHeaderTypes().length && upnpHeader == null; i7++) {
            Class<? extends UpnpHeader> cls = type.getHeaderTypes()[i7];
            try {
                try {
                    log.finest("Trying to parse '" + type + "' with class: " + cls.getSimpleName());
                    upnpHeaderNewInstance = cls.newInstance();
                    if (str != null) {
                        try {
                            upnpHeaderNewInstance.setString(str);
                        } catch (Exception e8) {
                            e7 = e8;
                            Logger logger = log;
                            logger.severe("Error instantiating header of type '" + type + "' with value: " + str);
                            logger.log(Level.SEVERE, "Exception root cause: ", C2074b.m2475O(e7));
                            upnpHeader = upnpHeaderNewInstance;
                        }
                    }
                } catch (Exception e9) {
                    upnpHeaderNewInstance = upnpHeader;
                    e7 = e9;
                }
                upnpHeader = upnpHeaderNewInstance;
            } catch (InvalidHeaderException e10) {
                Logger logger2 = log;
                StringBuilder sbM112a = C0413b.m112a("Invalid header value for tested type: ");
                sbM112a.append(cls.getSimpleName());
                sbM112a.append(" - ");
                sbM112a.append(e10.getMessage());
                logger2.finest(sbM112a.toString());
                upnpHeader = null;
            }
        }
        return upnpHeader;
    }

    public abstract String getString();

    public T getValue() {
        return this.value;
    }

    public abstract void setString(String str);

    public void setValue(T t6) {
        this.value = t6;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") '");
        sbM112a.append(getValue());
        sbM112a.append("'");
        return sbM112a.toString();
    }
}
