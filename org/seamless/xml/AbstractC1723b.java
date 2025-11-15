package org.seamless.xml;

import java.net.URI;
import javax.xml.xpath.XPath;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* compiled from: DOM.java */
/* renamed from: org.seamless.xml.b */
/* loaded from: classes.dex */
public abstract class AbstractC1723b {
    public static final String CDATA_BEGIN = "<![CDATA[";
    public static final String CDATA_END = "]]>";
    public static final URI XML_SCHEMA_NAMESPACE = URI.create("http://www.w3.org/2001/xml.xsd");
    private Document dom;

    public AbstractC1723b(Document document) {
        this.dom = document;
    }

    public abstract AbstractC1723b copy();

    public Element createRoot(String str) throws DOMException {
        Element elementCreateElementNS = getW3CDocument().createElementNS(getRootElementNamespace(), str);
        getW3CDocument().appendChild(elementCreateElementNS);
        return elementCreateElementNS;
    }

    public abstract AbstractC1724c getRoot(XPath xPath);

    public abstract String getRootElementNamespace();

    public Document getW3CDocument() {
        return this.dom;
    }
}
