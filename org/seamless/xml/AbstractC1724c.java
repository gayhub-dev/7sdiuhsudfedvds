package org.seamless.xml;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.seamless.xml.AbstractC1724c;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import p009b.C0413b;

/* compiled from: DOMElement.java */
/* renamed from: org.seamless.xml.c */
/* loaded from: classes.dex */
public abstract class AbstractC1724c<CHILD extends AbstractC1724c, PARENT extends AbstractC1724c> {
    private Element element;
    private final XPath xpath;
    public final AbstractC1724c<CHILD, PARENT>.b<PARENT> PARENT_BUILDER = createParentBuilder(this);
    public final AbstractC1724c<CHILD, PARENT>.a<CHILD> CHILD_BUILDER = createChildBuilder(this);

    /* compiled from: DOMElement.java */
    /* renamed from: org.seamless.xml.c$a */
    public abstract class a<T extends AbstractC1724c> extends AbstractC1724c<CHILD, PARENT>.b<T> {
        public a(AbstractC1724c abstractC1724c) {
            super(abstractC1724c);
        }

        public T[] buildArray(AbstractC1724c[] abstractC1724cArr) {
            T[] tArr = (T[]) newChildrenArray(abstractC1724cArr.length);
            for (int i7 = 0; i7 < tArr.length; i7++) {
                tArr[i7] = build(abstractC1724cArr[i7].getW3CElement());
            }
            return tArr;
        }

        public T[] getChildElements() {
            return (T[]) buildArray(this.element.getChildren());
        }

        public abstract T[] newChildrenArray(int i7);

        public T[] getChildElements(String str) {
            return (T[]) buildArray(this.element.getChildren(str));
        }
    }

    /* compiled from: DOMElement.java */
    /* renamed from: org.seamless.xml.c$b */
    public abstract class b<T extends AbstractC1724c> {
        public AbstractC1724c element;

        public b(AbstractC1724c abstractC1724c) {
            this.element = abstractC1724c;
        }

        public abstract T build(Element element);

        public T firstChildOrNull(String str) {
            AbstractC1724c firstChild = this.element.getFirstChild(str);
            if (firstChild != null) {
                return (T) build(firstChild.getW3CElement());
            }
            return null;
        }
    }

    public AbstractC1724c(XPath xPath, Element element) {
        this.xpath = xPath;
        this.element = element;
    }

    public CHILD adoptOrImport(Document document, CHILD child, boolean z6) {
        return document != null ? z6 ? this.CHILD_BUILDER.build((Element) document.importNode(child.getW3CElement(), true)) : this.CHILD_BUILDER.build((Element) document.adoptNode(child.getW3CElement())) : child;
    }

    public CHILD appendChild(CHILD child, boolean z6) {
        CHILD child2 = (CHILD) adoptOrImport(getW3CElement().getOwnerDocument(), child, z6);
        getW3CElement().appendChild(child2.getW3CElement());
        return child2;
    }

    public CHILD createChild(String str) {
        return (CHILD) createChild(str, null);
    }

    public abstract AbstractC1724c<CHILD, PARENT>.a<CHILD> createChildBuilder(AbstractC1724c abstractC1724c);

    public abstract AbstractC1724c<CHILD, PARENT>.b<PARENT> createParentBuilder(AbstractC1724c abstractC1724c);

    public CHILD findChildWithIdentifier(String str) {
        AbstractC1724c<CHILD, PARENT>.a<CHILD> aVar = this.CHILD_BUILDER;
        StringBuilder sbM112a = C0413b.m112a("descendant::");
        sbM112a.append(prefix("*"));
        sbM112a.append("[@id=\"");
        sbM112a.append(str);
        sbM112a.append("\"]");
        Collection<CHILD> xPathChildElements = getXPathChildElements(aVar, sbM112a.toString());
        if (xPathChildElements.size() == 1) {
            return xPathChildElements.iterator().next();
        }
        return null;
    }

    public CHILD[] findChildren(String str) {
        AbstractC1724c<CHILD, PARENT>.a<CHILD> aVar = this.CHILD_BUILDER;
        StringBuilder sbM112a = C0413b.m112a("descendant::");
        sbM112a.append(prefix(str));
        Collection<CHILD> xPathChildElements = getXPathChildElements(aVar, sbM112a.toString());
        return (CHILD[]) ((AbstractC1724c[]) xPathChildElements.toArray(this.CHILD_BUILDER.newChildrenArray(xPathChildElements.size())));
    }

    public String getAttribute(String str) {
        String attribute = getW3CElement().getAttribute(str);
        if (attribute.length() > 0) {
            return attribute;
        }
        return null;
    }

    public CHILD[] getChildren() {
        NodeList childNodes = getW3CElement().getChildNodes();
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1) {
                arrayList.add(this.CHILD_BUILDER.build((Element) nodeItem));
            }
        }
        return (CHILD[]) ((AbstractC1724c[]) arrayList.toArray(this.CHILD_BUILDER.newChildrenArray(arrayList.size())));
    }

    public String getContent() {
        return getW3CElement().getTextContent();
    }

    public String getElementName() {
        return getW3CElement().getNodeName();
    }

    public CHILD getFirstChild(String str) {
        return (CHILD) getXPathChildElement(this.CHILD_BUILDER, prefix(str) + "[1]");
    }

    public PARENT getParent() {
        return (PARENT) this.PARENT_BUILDER.build((Element) getW3CElement().getParentNode());
    }

    public CHILD getRequiredChild(String str) throws C1727f {
        AbstractC1724c[] children = getChildren(str);
        if (children.length == 1) {
            return (CHILD) children[0];
        }
        StringBuilder sbM112a = C0413b.m112a("Required single child element of '");
        sbM112a.append(getElementName());
        sbM112a.append("' not found: ");
        sbM112a.append(str);
        throw new C1727f(sbM112a.toString());
    }

    public Element getW3CElement() {
        return this.element;
    }

    public CHILD getXPathChildElement(AbstractC1724c<CHILD, PARENT>.b<CHILD> bVar, String str) {
        Node node = (Node) getXPathResult(getW3CElement(), str, XPathConstants.NODE);
        if (node == null || node.getNodeType() != 1) {
            return null;
        }
        return (CHILD) bVar.build((Element) node);
    }

    public Collection<CHILD> getXPathChildElements(AbstractC1724c<CHILD, PARENT>.b<CHILD> bVar, String str) {
        return getXPathElements(bVar, str);
    }

    public Collection getXPathElements(b bVar, String str) {
        ArrayList arrayList = new ArrayList();
        NodeList nodeList = (NodeList) getXPathResult(getW3CElement(), str, XPathConstants.NODESET);
        for (int i7 = 0; i7 < nodeList.getLength(); i7++) {
            arrayList.add(bVar.build((Element) nodeList.item(i7)));
        }
        return arrayList;
    }

    public PARENT getXPathParentElement(AbstractC1724c<CHILD, PARENT>.b<PARENT> bVar, String str) {
        Node node = (Node) getXPathResult(getW3CElement(), str, XPathConstants.NODE);
        if (node == null || node.getNodeType() != 1) {
            return null;
        }
        return (PARENT) bVar.build((Element) node);
    }

    public Collection<PARENT> getXPathParentElements(AbstractC1724c<CHILD, PARENT>.b<CHILD> bVar, String str) {
        return getXPathElements(bVar, str);
    }

    public Object getXPathResult(String str, QName qName) {
        return getXPathResult(getW3CElement(), str, qName);
    }

    public String getXPathString(XPath xPath, String str) {
        return getXPathResult(getW3CElement(), str, null).toString();
    }

    public XPath getXpath() {
        return this.xpath;
    }

    public String prefix(String str) {
        return str;
    }

    public void removeChild(CHILD child) {
        getW3CElement().removeChild(child.getW3CElement());
    }

    public void removeChildren() {
        NodeList childNodes = getW3CElement().getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            getW3CElement().removeChild(childNodes.item(i7));
        }
    }

    public CHILD replaceChild(CHILD child, CHILD child2, boolean z6) {
        CHILD child3 = (CHILD) adoptOrImport(getW3CElement().getOwnerDocument(), child2, z6);
        getW3CElement().replaceChild(child3.getW3CElement(), child.getW3CElement());
        return child3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void replaceEqualChild(AbstractC1724c abstractC1724c, String str) {
        AbstractC1724c abstractC1724cFindChildWithIdentifier = findChildWithIdentifier(str);
        abstractC1724cFindChildWithIdentifier.getParent().replaceChild(abstractC1724cFindChildWithIdentifier, abstractC1724c.findChildWithIdentifier(str), true);
    }

    public AbstractC1724c setAttribute(String str, String str2) throws DOMException {
        getW3CElement().setAttribute(str, str2);
        return this;
    }

    public AbstractC1724c<CHILD, PARENT> setContent(String str) {
        getW3CElement().setTextContent(str);
        return this;
    }

    public String toSimpleXMLString() {
        StringBuilder sbM112a = C0413b.m112a("<");
        sbM112a.append(getElementName());
        NamedNodeMap attributes = getW3CElement().getAttributes();
        for (int i7 = 0; i7 < attributes.getLength(); i7++) {
            Node nodeItem = attributes.item(i7);
            sbM112a.append(" ");
            sbM112a.append(nodeItem.getNodeName());
            sbM112a.append("=\"");
            sbM112a.append(nodeItem.getTextContent());
            sbM112a.append("\"");
        }
        if (getContent().length() > 0) {
            sbM112a.append(">");
            sbM112a.append(getContent());
            sbM112a.append("</");
            sbM112a.append(getElementName());
            sbM112a.append(">");
        } else {
            sbM112a.append("/>");
        }
        return sbM112a.toString();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") ");
        sbM112a.append(getW3CElement() == null ? "UNBOUND" : getElementName());
        return sbM112a.toString();
    }

    public CHILD createChild(String str, String str2) {
        CHILD childBuild = this.CHILD_BUILDER.build(str2 == null ? getW3CElement().getOwnerDocument().createElement(str) : getW3CElement().getOwnerDocument().createElementNS(str2, str));
        getW3CElement().appendChild(childBuild.getW3CElement());
        return childBuild;
    }

    public Object getXPathResult(Node node, String str, QName qName) {
        try {
            return qName == null ? this.xpath.evaluate(str, node) : this.xpath.evaluate(str, node, qName);
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public CHILD[] getChildren(String str) {
        Collection<CHILD> xPathChildElements = getXPathChildElements(this.CHILD_BUILDER, prefix(str));
        return (CHILD[]) ((AbstractC1724c[]) xPathChildElements.toArray(this.CHILD_BUILDER.newChildrenArray(xPathChildElements.size())));
    }
}
