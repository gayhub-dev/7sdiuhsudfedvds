package org.fourthline.cling.model;

import android.support.constraint.C0072a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import p009b.C0413b;

/* loaded from: classes.dex */
public class XMLUtil {
    public static Element appendNewElement(Document document, Element element, Enum r22) {
        return appendNewElement(document, element, r22.toString());
    }

    public static Element appendNewElementIfNotNull(Document document, Element element, Enum r32, Object obj) {
        return appendNewElementIfNotNull(document, element, r32, obj, (String) null);
    }

    public static String documentToFragmentString(Document document) {
        return nodeToString(document.getDocumentElement(), new HashSet(), document.getDocumentElement().getNamespaceURI());
    }

    public static String documentToString(Document document) {
        return documentToString(document, true);
    }

    public static String encodeText(String str) {
        return encodeText(str, true);
    }

    public static String getTextContent(Node node) {
        StringBuffer stringBuffer = new StringBuffer();
        NodeList childNodes = node.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 3) {
                stringBuffer.append(nodeItem.getNodeValue());
            }
        }
        return stringBuffer.toString();
    }

    public static String nodeToString(Node node, Set<String> set, String str) {
        boolean z6;
        StringBuilder sb = new StringBuilder();
        if (node == null) {
            return "";
        }
        if (node instanceof Element) {
            Element element = (Element) node;
            sb.append("<");
            sb.append(element.getNodeName());
            HashMap map = new HashMap();
            if (element.getPrefix() != null && !set.contains(element.getPrefix())) {
                map.put(element.getPrefix(), element.getNamespaceURI());
            }
            if (element.hasAttributes()) {
                NamedNodeMap attributes = element.getAttributes();
                for (int i7 = 0; i7 < attributes.getLength(); i7++) {
                    Node nodeItem = attributes.item(i7);
                    if (!nodeItem.getNodeName().startsWith("xmlns")) {
                        if (nodeItem.getPrefix() != null && !set.contains(nodeItem.getPrefix())) {
                            map.put(nodeItem.getPrefix(), element.getNamespaceURI());
                        }
                        sb.append(" ");
                        sb.append(nodeItem.getNodeName());
                        sb.append("=\"");
                        sb.append(nodeItem.getNodeValue());
                        sb.append("\"");
                    }
                }
            }
            if (str != null && !map.containsValue(str) && !str.equals(element.getParentNode().getNamespaceURI())) {
                sb.append(" xmlns=\"");
                sb.append(str);
                sb.append("\"");
            }
            for (Map.Entry entry : map.entrySet()) {
                sb.append(" xmlns:");
                sb.append((String) entry.getKey());
                sb.append("=\"");
                sb.append((String) entry.getValue());
                sb.append("\"");
                set.add((String) entry.getKey());
            }
            NodeList childNodes = element.getChildNodes();
            int i8 = 0;
            while (true) {
                if (i8 >= childNodes.getLength()) {
                    z6 = true;
                    break;
                }
                if (childNodes.item(i8).getNodeType() != 2) {
                    z6 = false;
                    break;
                }
                i8++;
            }
            if (z6) {
                sb.append("/>");
            } else {
                sb.append(">");
                for (int i9 = 0; i9 < childNodes.getLength(); i9++) {
                    sb.append(nodeToString(childNodes.item(i9), set, childNodes.item(i9).getNamespaceURI()));
                }
                sb.append("</");
                sb.append(element.getNodeName());
                sb.append(">");
            }
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                set.remove((String) it.next());
            }
        } else if (node.getNodeValue() != null) {
            sb.append(encodeText(node.getNodeValue(), node instanceof Attr));
        }
        return sb.toString();
    }

    public static Element appendNewElement(Document document, Element element, String str) throws DOMException {
        Element elementCreateElement = document.createElement(str);
        element.appendChild(elementCreateElement);
        return elementCreateElement;
    }

    public static Element appendNewElementIfNotNull(Document document, Element element, Enum r22, Object obj, String str) {
        return appendNewElementIfNotNull(document, element, r22.toString(), obj, str);
    }

    public static String documentToString(Document document, boolean z6) {
        StringBuilder sbM112a = C0413b.m112a(C0072a.m92a(C0413b.m112a("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\""), z6 ? "yes" : "no", "\"?>"));
        sbM112a.append(nodeToString(document.getDocumentElement(), new HashSet(), document.getDocumentElement().getNamespaceURI()));
        return sbM112a.toString();
    }

    public static String encodeText(String str, boolean z6) {
        String strReplaceAll = str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        return z6 ? strReplaceAll.replaceAll("'", "&apos;").replaceAll("\"", "&quot;") : strReplaceAll;
    }

    public static Element appendNewElementIfNotNull(Document document, Element element, String str, Object obj) {
        return appendNewElementIfNotNull(document, element, str, obj, (String) null);
    }

    public static Element appendNewElement(Document document, Element element, String str, Object obj) {
        return appendNewElement(document, element, str, obj, null);
    }

    public static Element appendNewElementIfNotNull(Document document, Element element, String str, Object obj, String str2) {
        return obj == null ? element : appendNewElement(document, element, str, obj, str2);
    }

    public static Element appendNewElement(Document document, Element element, String str, Object obj, String str2) throws DOMException {
        Element elementCreateElement;
        if (str2 != null) {
            elementCreateElement = document.createElementNS(str2, str);
        } else {
            elementCreateElement = document.createElement(str);
        }
        if (obj != null) {
            elementCreateElement.appendChild(document.createTextNode(obj.toString()));
        }
        element.appendChild(elementCreateElement);
        return elementCreateElement;
    }
}
