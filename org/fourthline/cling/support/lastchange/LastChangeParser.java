package org.fourthline.cling.support.lastchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import org.fourthline.cling.model.XMLUtil;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.shared.AbstractMap;
import org.seamless.xml.AbstractC1725d;
import org.seamless.xml.C1727f;
import org.seamless.xml.C1728g;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import p009b.C0413b;
import p106m6.C1500c;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class LastChangeParser extends C1728g {
    private static final Logger log = Logger.getLogger(LastChangeParser.class.getName());

    public enum CONSTANTS {
        Event,
        InstanceID,
        val;

        public boolean equals(String str) {
            return name().equals(str);
        }
    }

    public class InstanceIDHandler extends C1728g.b<InstanceID> {
        public InstanceIDHandler(InstanceID instanceID, C1728g.b bVar) {
            super(instanceID, bVar);
        }

        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            return CONSTANTS.InstanceID.equals(str2);
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            super.startElement(str, str2, str3, attributes);
            int length = attributes.getLength();
            Map.Entry<String, String>[] entryArr = new Map.Entry[length];
            for (int i7 = 0; i7 < length; i7++) {
                entryArr[i7] = new AbstractMap.SimpleEntry(attributes.getLocalName(i7), attributes.getValue(i7));
            }
            try {
                EventedValue eventedValueCreateValue = LastChangeParser.this.createValue(str2, entryArr);
                if (eventedValueCreateValue != null) {
                    getInstance().getValues().add(eventedValueCreateValue);
                }
            } catch (Exception e7) {
                Logger logger = LastChangeParser.log;
                StringBuilder sbM112a = C0413b.m112a("Error reading event XML, ignoring value: ");
                sbM112a.append(C2074b.m2475O(e7));
                logger.warning(sbM112a.toString());
            }
        }
    }

    public Document buildDOM(Event event) throws DOMException {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setNamespaceAware(true);
        Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
        generateRoot(event, documentNewDocument);
        return documentNewDocument;
    }

    public EventedValue createValue(String str, Map.Entry<String, String>[] entryArr) {
        for (Class<? extends EventedValue> cls : getEventedVariables()) {
            if (cls.getSimpleName().equals(str)) {
                return cls.getConstructor(Map.Entry[].class).newInstance(entryArr);
            }
        }
        return null;
    }

    public String generate(Event event) {
        return XMLUtil.documentToFragmentString(buildDOM(event));
    }

    public void generateEventedValue(EventedValue eventedValue, Document document, Element element) throws DOMException {
        String name = eventedValue.getName();
        Map.Entry<String, String>[] attributes = eventedValue.getAttributes();
        if (attributes == null || attributes.length <= 0) {
            return;
        }
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, name);
        for (Map.Entry<String, String> entry : attributes) {
            elementAppendNewElement.setAttribute(entry.getKey(), AbstractC1725d.escape(entry.getValue()));
        }
    }

    public void generateInstanceIDs(Event event, Document document, Element element) throws DOMException {
        for (InstanceID instanceID : event.getInstanceIDs()) {
            if (instanceID.getId() != null) {
                Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, CONSTANTS.InstanceID.name());
                elementAppendNewElement.setAttribute(CONSTANTS.val.name(), instanceID.getId().toString());
                Iterator<EventedValue> it = instanceID.getValues().iterator();
                while (it.hasNext()) {
                    generateEventedValue(it.next(), document, elementAppendNewElement);
                }
            }
        }
    }

    public void generateRoot(Event event, Document document) throws DOMException {
        Element elementCreateElementNS = document.createElementNS(getNamespace(), CONSTANTS.Event.name());
        document.appendChild(elementCreateElementNS);
        generateInstanceIDs(event, document, elementCreateElementNS);
    }

    public Set<Class<? extends EventedValue>> getEventedVariables() {
        return Collections.EMPTY_SET;
    }

    public abstract String getNamespace();

    public Event parse(String str) throws C1727f, SAXException, IOException {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("Null or empty XML");
        }
        Event event = new Event();
        new RootHandler(event, this);
        Logger logger = log;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Parsing 'LastChange' event XML content");
            logger.fine("===================================== 'LastChange' BEGIN ============================================");
            logger.fine(str);
            logger.fine("====================================== 'LastChange' END  ============================================");
        }
        parse(new InputSource(new StringReader(str)));
        logger.fine("Parsed event with instances IDs: " + event.getInstanceIDs().size());
        if (logger.isLoggable(Level.FINEST)) {
            for (InstanceID instanceID : event.getInstanceIDs()) {
                Logger logger2 = log;
                StringBuilder sbM112a = C0413b.m112a("InstanceID '");
                sbM112a.append(instanceID.getId());
                sbM112a.append("' has values: ");
                sbM112a.append(instanceID.getValues().size());
                logger2.finest(sbM112a.toString());
                for (EventedValue eventedValue : instanceID.getValues()) {
                    log.finest(eventedValue.getName() + " => " + eventedValue.getValue());
                }
            }
        }
        return event;
    }

    public Event parseResource(String str) throws IOException {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
            Event event = parse(C1500c.m1665b(resourceAsStream));
            resourceAsStream.close();
            return event;
        } catch (Throwable th) {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
            throw th;
        }
    }

    public class RootHandler extends C1728g.b<Event> {
        public RootHandler(Event event, C1728g c1728g) {
            super(event, c1728g);
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            String value;
            super.startElement(str, str2, str3, attributes);
            if (!CONSTANTS.InstanceID.equals(str2) || (value = attributes.getValue(CONSTANTS.val.name())) == null) {
                return;
            }
            InstanceID instanceID = new InstanceID(new UnsignedIntegerFourBytes(value));
            getInstance().getInstanceIDs().add(instanceID);
            LastChangeParser.this.new InstanceIDHandler(instanceID, this);
        }

        public RootHandler(Event event) {
            super(event);
        }
    }
}
