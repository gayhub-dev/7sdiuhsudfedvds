package org.fourthline.cling.support.messagebox.parser;

import android.arch.lifecycle.C0063n;
import javax.xml.xpath.XPath;
import org.seamless.xml.AbstractC1724c;
import org.w3c.dom.Element;

/* loaded from: classes.dex */
public class MessageElement extends AbstractC1724c<MessageElement, MessageElement> {
    public static final String XPATH_PREFIX = "m";

    public MessageElement(XPath xPath, Element element) {
        super(xPath, element);
    }

    @Override // org.seamless.xml.AbstractC1724c
    public AbstractC1724c<MessageElement, MessageElement>.a<MessageElement> createChildBuilder(AbstractC1724c abstractC1724c) {
        return new AbstractC1724c<MessageElement, MessageElement>.a<MessageElement>(abstractC1724c) { // from class: org.fourthline.cling.support.messagebox.parser.MessageElement.2
            @Override // org.seamless.xml.AbstractC1724c.b
            public MessageElement build(Element element) {
                return new MessageElement(MessageElement.this.getXpath(), element);
            }

            @Override // org.seamless.xml.AbstractC1724c.a
            public MessageElement[] newChildrenArray(int i7) {
                return new MessageElement[i7];
            }
        };
    }

    @Override // org.seamless.xml.AbstractC1724c
    public AbstractC1724c<MessageElement, MessageElement>.b<MessageElement> createParentBuilder(AbstractC1724c abstractC1724c) {
        return new AbstractC1724c<MessageElement, MessageElement>.b<MessageElement>(abstractC1724c) { // from class: org.fourthline.cling.support.messagebox.parser.MessageElement.1
            @Override // org.seamless.xml.AbstractC1724c.b
            public MessageElement build(Element element) {
                return new MessageElement(MessageElement.this.getXpath(), element);
            }
        };
    }

    @Override // org.seamless.xml.AbstractC1724c
    public String prefix(String str) {
        return C0063n.m88a("m:", str);
    }
}
