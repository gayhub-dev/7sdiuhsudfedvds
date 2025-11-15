package org.fourthline.cling.support.messagebox.parser;

import javax.xml.xpath.XPath;
import org.seamless.xml.AbstractC1723b;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

/* loaded from: classes.dex */
public class MessageDOM extends AbstractC1723b {
    public static final String NAMESPACE_URI = "urn:samsung-com:messagebox-1-0";

    public MessageDOM(Document document) {
        super(document);
    }

    public MessageElement createRoot(XPath xPath, String str) throws DOMException {
        super.createRoot(str);
        return getRoot(xPath);
    }

    @Override // org.seamless.xml.AbstractC1723b
    public String getRootElementNamespace() {
        return NAMESPACE_URI;
    }

    @Override // org.seamless.xml.AbstractC1723b
    public MessageDOM copy() {
        return new MessageDOM((Document) getW3CDocument().cloneNode(true));
    }

    @Override // org.seamless.xml.AbstractC1723b
    public MessageElement getRoot(XPath xPath) {
        return new MessageElement(xPath, getW3CDocument().getDocumentElement());
    }
}
