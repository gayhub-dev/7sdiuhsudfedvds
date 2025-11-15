package org.fourthline.cling.support.messagebox.parser;

import javax.xml.xpath.XPath;
import org.seamless.xml.AbstractC1725d;
import org.seamless.xml.AbstractC1726e;
import org.w3c.dom.Document;

/* loaded from: classes.dex */
public class MessageDOMParser extends AbstractC1725d<MessageDOM> {
    public AbstractC1726e createDefaultNamespaceContext(String... strArr) {
        AbstractC1726e abstractC1726e = new AbstractC1726e() { // from class: org.fourthline.cling.support.messagebox.parser.MessageDOMParser.1
            @Override // org.seamless.xml.AbstractC1726e
            public String getDefaultNamespaceURI() {
                return MessageDOM.NAMESPACE_URI;
            }
        };
        for (String str : strArr) {
            abstractC1726e.put(str, MessageDOM.NAMESPACE_URI);
        }
        return abstractC1726e;
    }

    public XPath createXPath() {
        return super.createXPath(createDefaultNamespaceContext(MessageElement.XPATH_PREFIX));
    }

    @Override // org.seamless.xml.AbstractC1725d
    public MessageDOM createDOM(Document document) {
        return new MessageDOM(document);
    }
}
