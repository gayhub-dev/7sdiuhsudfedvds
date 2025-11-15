package org.fourthline.cling.model.message;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.message.header.UpnpHeader;
import p009b.C0413b;
import p082j6.C1212a;

/* loaded from: classes.dex */
public class UpnpHeaders extends C1212a {
    private static final Logger log = Logger.getLogger(UpnpHeaders.class.getName());
    public Map<UpnpHeader.Type, List<UpnpHeader>> parsedHeaders;

    public UpnpHeaders() {
    }

    @Override // p082j6.C1212a
    public void add(String str, String str2) {
        this.parsedHeaders = null;
        super.add(str, str2);
    }

    public void addParsedValue(UpnpHeader.Type type, UpnpHeader upnpHeader) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Adding parsed header: " + upnpHeader);
        }
        List<UpnpHeader> linkedList = this.parsedHeaders.get(type);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            this.parsedHeaders.put(type, linkedList);
        }
        linkedList.add(upnpHeader);
    }

    @Override // p082j6.C1212a, java.util.Map
    public void clear() {
        this.parsedHeaders = null;
        super.clear();
    }

    public boolean containsKey(UpnpHeader.Type type) {
        if (this.parsedHeaders == null) {
            parseHeaders();
        }
        return this.parsedHeaders.containsKey(type);
    }

    public List<UpnpHeader> get(UpnpHeader.Type type) {
        if (this.parsedHeaders == null) {
            parseHeaders();
        }
        return this.parsedHeaders.get(type);
    }

    public UpnpHeader[] getAsArray(UpnpHeader.Type type) {
        if (this.parsedHeaders == null) {
            parseHeaders();
        }
        return this.parsedHeaders.get(type) != null ? (UpnpHeader[]) this.parsedHeaders.get(type).toArray(new UpnpHeader[this.parsedHeaders.get(type).size()]) : new UpnpHeader[0];
    }

    public UpnpHeader getFirstHeader(UpnpHeader.Type type) {
        if (getAsArray(type).length > 0) {
            return getAsArray(type)[0];
        }
        return null;
    }

    public String getFirstHeaderString(UpnpHeader.Type type) {
        UpnpHeader firstHeader = getFirstHeader(type);
        if (firstHeader != null) {
            return firstHeader.getString();
        }
        return null;
    }

    public void log() {
        Logger logger = log;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("############################ RAW HEADERS ###########################");
            for (Map.Entry<String, List<String>> entry : entrySet()) {
                Logger logger2 = log;
                StringBuilder sbM112a = C0413b.m112a("=== NAME : ");
                sbM112a.append(entry.getKey());
                logger2.fine(sbM112a.toString());
                for (String str : entry.getValue()) {
                    log.fine("VALUE: " + str);
                }
            }
            Map<UpnpHeader.Type, List<UpnpHeader>> map = this.parsedHeaders;
            if (map != null && map.size() > 0) {
                log.fine("########################## PARSED HEADERS ##########################");
                for (Map.Entry<UpnpHeader.Type, List<UpnpHeader>> entry2 : this.parsedHeaders.entrySet()) {
                    Logger logger3 = log;
                    StringBuilder sbM112a2 = C0413b.m112a("=== TYPE: ");
                    sbM112a2.append(entry2.getKey());
                    logger3.fine(sbM112a2.toString());
                    for (UpnpHeader upnpHeader : entry2.getValue()) {
                        log.fine("HEADER: " + upnpHeader);
                    }
                }
            }
            log.fine("####################################################################");
        }
    }

    public void parseHeaders() {
        this.parsedHeaders = new LinkedHashMap();
        Logger logger = log;
        if (logger.isLoggable(Level.FINE)) {
            StringBuilder sbM112a = C0413b.m112a("Parsing all HTTP headers for known UPnP headers: ");
            sbM112a.append(size());
            logger.fine(sbM112a.toString());
        }
        for (Map.Entry<String, List<String>> entry : entrySet()) {
            if (entry.getKey() != null) {
                UpnpHeader.Type byHttpName = UpnpHeader.Type.getByHttpName(entry.getKey());
                if (byHttpName == null) {
                    Logger logger2 = log;
                    if (logger2.isLoggable(Level.FINE)) {
                        StringBuilder sbM112a2 = C0413b.m112a("Ignoring non-UPNP HTTP header: ");
                        sbM112a2.append(entry.getKey());
                        logger2.fine(sbM112a2.toString());
                    }
                } else {
                    for (String str : entry.getValue()) {
                        UpnpHeader upnpHeaderNewInstance = UpnpHeader.newInstance(byHttpName, str);
                        if (upnpHeaderNewInstance == null || upnpHeaderNewInstance.getValue() == null) {
                            Logger logger3 = log;
                            if (logger3.isLoggable(Level.FINE)) {
                                StringBuilder sbM112a3 = C0413b.m112a("Ignoring known but irrelevant header (value violates the UDA specification?) '");
                                sbM112a3.append(byHttpName.getHttpName());
                                sbM112a3.append("': ");
                                sbM112a3.append(str);
                                logger3.fine(sbM112a3.toString());
                            }
                        } else {
                            addParsedValue(byHttpName, upnpHeaderNewInstance);
                        }
                    }
                }
            }
        }
    }

    public UpnpHeaders(Map<String, List<String>> map) {
        super(map);
    }

    @Override // p082j6.C1212a, java.util.Map
    public List<String> put(String str, List<String> list) {
        this.parsedHeaders = null;
        return super.put(str, list);
    }

    @Override // p082j6.C1212a, java.util.Map
    public List<String> remove(Object obj) {
        this.parsedHeaders = null;
        return super.remove(obj);
    }

    public UpnpHeaders(ByteArrayInputStream byteArrayInputStream) {
        super(byteArrayInputStream);
    }

    public void add(UpnpHeader.Type type, UpnpHeader upnpHeader) {
        super.add(type.getHttpName(), upnpHeader.getString());
        if (this.parsedHeaders != null) {
            addParsedValue(type, upnpHeader);
        }
    }

    public <H extends UpnpHeader> H getFirstHeader(UpnpHeader.Type type, Class<H> cls) {
        UpnpHeader[] asArray = getAsArray(type);
        if (asArray.length == 0) {
            return null;
        }
        for (UpnpHeader upnpHeader : asArray) {
            H h7 = (H) upnpHeader;
            if (cls.isAssignableFrom(h7.getClass())) {
                return h7;
            }
        }
        return null;
    }

    public UpnpHeaders(boolean z6) {
        super(z6);
    }

    public void remove(UpnpHeader.Type type) {
        super.remove((Object) type.getHttpName());
        Map<UpnpHeader.Type, List<UpnpHeader>> map = this.parsedHeaders;
        if (map != null) {
            map.remove(type);
        }
    }
}
