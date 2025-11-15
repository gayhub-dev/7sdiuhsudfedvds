package org.fourthline.cling.transport.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.fourthline.cling.model.Constants;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.XMLUtil;
import org.fourthline.cling.model.action.ActionArgumentValue;
import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.control.ActionMessage;
import org.fourthline.cling.model.message.control.ActionRequestMessage;
import org.fourthline.cling.model.message.control.ActionResponseMessage;
import org.fourthline.cling.model.meta.ActionArgument;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.transport.spi.SOAPActionProcessor;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SOAPActionProcessorImpl implements SOAPActionProcessor, ErrorHandler {
    private static Logger log = Logger.getLogger(SOAPActionProcessor.class.getName());

    public DocumentBuilderFactory createDocumentBuilderFactory() {
        return DocumentBuilderFactory.newInstance();
    }

    public ActionArgumentValue createValue(ActionArgument actionArgument, String str) throws ActionException {
        try {
            return new ActionArgumentValue(actionArgument, str);
        } catch (InvalidValueException e7) {
            ErrorCode errorCode = ErrorCode.ARGUMENT_VALUE_INVALID;
            StringBuilder sbM112a = C0413b.m112a("Wrong type or invalid value for '");
            sbM112a.append(actionArgument.getName());
            sbM112a.append("': ");
            sbM112a.append(e7.getMessage());
            throw new ActionException(errorCode, sbM112a.toString(), e7);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXParseException {
        throw sAXParseException;
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXParseException {
        throw sAXParseException;
    }

    public Node findActionArgumentNode(List<Node> list, ActionArgument actionArgument) {
        for (Node node : list) {
            if (actionArgument.isNameOrAlias(getUnprefixedNodeName(node))) {
                return node;
            }
        }
        return null;
    }

    public List<Node> getMatchingNodes(NodeList nodeList, ActionArgument[] actionArgumentArr) throws ActionException {
        ArrayList arrayList = new ArrayList();
        for (ActionArgument actionArgument : actionArgumentArr) {
            arrayList.add(actionArgument.getName());
            arrayList.addAll(Arrays.asList(actionArgument.getAliases()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i7 = 0; i7 < nodeList.getLength(); i7++) {
            Node nodeItem = nodeList.item(i7);
            if (nodeItem.getNodeType() == 1 && arrayList.contains(getUnprefixedNodeName(nodeItem))) {
                arrayList2.add(nodeItem);
            }
        }
        if (arrayList2.size() >= actionArgumentArr.length) {
            return arrayList2;
        }
        ErrorCode errorCode = ErrorCode.ARGUMENT_VALUE_INVALID;
        StringBuilder sbM112a = C0413b.m112a("Invalid number of input or output arguments in XML message, expected ");
        sbM112a.append(actionArgumentArr.length);
        sbM112a.append(" but found ");
        sbM112a.append(arrayList2.size());
        throw new ActionException(errorCode, sbM112a.toString());
    }

    public String getMessageBody(ActionMessage actionMessage) {
        if (actionMessage.isBodyNonEmptyString()) {
            return actionMessage.getBodyString().trim();
        }
        throw new UnsupportedDataException("Can't transform null or non-string/zero-length body of: " + actionMessage);
    }

    public String getUnprefixedNodeName(Node node) {
        return node.getPrefix() != null ? node.getNodeName().substring(node.getPrefix().length() + 1) : node.getNodeName();
    }

    public void readActionInputArguments(Element element, ActionInvocation actionInvocation) {
        actionInvocation.setInput(readArgumentValues(element.getChildNodes(), actionInvocation.getAction().getInputArguments()));
    }

    public void readActionOutputArguments(Element element, ActionInvocation actionInvocation) {
        actionInvocation.setOutput(readArgumentValues(element.getChildNodes(), actionInvocation.getAction().getOutputArguments()));
    }

    public Element readActionRequestElement(Element element, ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) {
        NodeList childNodes = element.getChildNodes();
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Looking for action request element matching namespace:");
        sbM112a.append(actionRequestMessage.getActionNamespace());
        logger.fine(sbM112a.toString());
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1) {
                String unprefixedNodeName = getUnprefixedNodeName(nodeItem);
                if (unprefixedNodeName.equals(actionInvocation.getAction().getName())) {
                    if (nodeItem.getNamespaceURI() == null || !nodeItem.getNamespaceURI().equals(actionRequestMessage.getActionNamespace())) {
                        throw new UnsupportedDataException("Illegal or missing namespace on action request element: " + nodeItem);
                    }
                    log.fine("Reading action request element: " + unprefixedNodeName);
                    return (Element) nodeItem;
                }
            }
        }
        StringBuilder sbM112a2 = C0413b.m112a("Could not read action request element matching namespace: ");
        sbM112a2.append(actionRequestMessage.getActionNamespace());
        throw new UnsupportedDataException(sbM112a2.toString());
    }

    public Element readActionResponseElement(Element element, ActionInvocation actionInvocation) {
        NodeList childNodes = element.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1) {
                if (getUnprefixedNodeName(nodeItem).equals(actionInvocation.getAction().getName() + "Response")) {
                    Logger logger = log;
                    StringBuilder sbM112a = C0413b.m112a("Reading action response element: ");
                    sbM112a.append(getUnprefixedNodeName(nodeItem));
                    logger.fine(sbM112a.toString());
                    return (Element) nodeItem;
                }
            }
        }
        log.fine("Could not read action response element");
        return null;
    }

    public ActionArgumentValue[] readArgumentValues(NodeList nodeList, ActionArgument[] actionArgumentArr) throws ActionException {
        List<Node> matchingNodes = getMatchingNodes(nodeList, actionArgumentArr);
        ActionArgumentValue[] actionArgumentValueArr = new ActionArgumentValue[actionArgumentArr.length];
        for (int i7 = 0; i7 < actionArgumentArr.length; i7++) {
            ActionArgument actionArgument = actionArgumentArr[i7];
            Node nodeFindActionArgumentNode = findActionArgumentNode(matchingNodes, actionArgument);
            if (nodeFindActionArgumentNode == null) {
                ErrorCode errorCode = ErrorCode.ARGUMENT_VALUE_INVALID;
                StringBuilder sbM112a = C0413b.m112a("Could not find argument '");
                sbM112a.append(actionArgument.getName());
                sbM112a.append("' node");
                throw new ActionException(errorCode, sbM112a.toString());
            }
            Logger logger = log;
            StringBuilder sbM112a2 = C0413b.m112a("Reading action argument: ");
            sbM112a2.append(actionArgument.getName());
            logger.fine(sbM112a2.toString());
            actionArgumentValueArr[i7] = createValue(actionArgument, XMLUtil.getTextContent(nodeFindActionArgumentNode));
        }
        return actionArgumentValueArr;
    }

    @Override // org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void readBody(ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) throws ParserConfigurationException, SAXException, IOException {
        log.fine("Reading body of " + actionRequestMessage + " for: " + actionInvocation);
        if (log.isLoggable(Level.FINER)) {
            log.finer("===================================== SOAP BODY BEGIN ============================================");
            log.finer(actionRequestMessage.getBodyString());
            log.finer("-===================================== SOAP BODY END ============================================");
        }
        String messageBody = getMessageBody(actionRequestMessage);
        try {
            DocumentBuilderFactory documentBuilderFactoryCreateDocumentBuilderFactory = createDocumentBuilderFactory();
            documentBuilderFactoryCreateDocumentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilderNewDocumentBuilder = documentBuilderFactoryCreateDocumentBuilderFactory.newDocumentBuilder();
            documentBuilderNewDocumentBuilder.setErrorHandler(this);
            Document document = documentBuilderNewDocumentBuilder.parse(new InputSource(new StringReader(messageBody)));
            readBodyRequest(document, readBodyElement(document), actionRequestMessage, actionInvocation);
        } catch (Exception e7) {
            throw new UnsupportedDataException("Can't transform message payload: " + e7, e7, messageBody);
        }
    }

    public Element readBodyElement(Document document) {
        Element documentElement = document.getDocumentElement();
        if (documentElement == null || !getUnprefixedNodeName(documentElement).equals("Envelope")) {
            throw new RuntimeException("Response root element was not 'Envelope'");
        }
        NodeList childNodes = documentElement.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1 && getUnprefixedNodeName(nodeItem).equals("Body")) {
                return (Element) nodeItem;
            }
        }
        throw new RuntimeException("Response envelope did not contain 'Body' child element");
    }

    public ActionException readBodyFailure(Document document, Element element) {
        return readFaultElement(element);
    }

    public void readBodyRequest(Document document, Element element, ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) {
        readActionInputArguments(readActionRequestElement(element, actionRequestMessage, actionInvocation), actionInvocation);
    }

    public void readBodyResponse(Document document, Element element, ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) {
        readActionOutputArguments(readActionResponseElement(element, actionInvocation), actionInvocation);
    }

    public ActionException readFaultElement(Element element) {
        NodeList childNodes = element.getChildNodes();
        String textContent = null;
        String textContent2 = null;
        boolean z6 = false;
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            short s6 = 1;
            if (nodeItem.getNodeType() == 1 && getUnprefixedNodeName(nodeItem).equals("Fault")) {
                NodeList childNodes2 = nodeItem.getChildNodes();
                int i8 = 0;
                while (i8 < childNodes2.getLength()) {
                    Node nodeItem2 = childNodes2.item(i8);
                    if (nodeItem2.getNodeType() == s6 && getUnprefixedNodeName(nodeItem2).equals("detail")) {
                        NodeList childNodes3 = nodeItem2.getChildNodes();
                        int i9 = 0;
                        while (i9 < childNodes3.getLength()) {
                            Node nodeItem3 = childNodes3.item(i9);
                            if (nodeItem3.getNodeType() == s6 && getUnprefixedNodeName(nodeItem3).equals("UPnPError")) {
                                NodeList childNodes4 = nodeItem3.getChildNodes();
                                int i10 = 0;
                                while (i10 < childNodes4.getLength()) {
                                    Node nodeItem4 = childNodes4.item(i10);
                                    if (nodeItem4.getNodeType() == s6) {
                                        if (getUnprefixedNodeName(nodeItem4).equals("errorCode")) {
                                            textContent = XMLUtil.getTextContent(nodeItem4);
                                        }
                                        if (getUnprefixedNodeName(nodeItem4).equals("errorDescription")) {
                                            textContent2 = XMLUtil.getTextContent(nodeItem4);
                                        }
                                    }
                                    i10++;
                                    s6 = 1;
                                }
                            }
                            i9++;
                            s6 = 1;
                        }
                    }
                    i8++;
                    s6 = 1;
                }
                z6 = true;
            }
        }
        if (textContent == null) {
            if (z6) {
                throw new RuntimeException("Received fault element but no error code");
            }
            return null;
        }
        try {
            int iIntValue = Integer.valueOf(textContent).intValue();
            ErrorCode byCode = ErrorCode.getByCode(iIntValue);
            if (byCode != null) {
                log.fine("Reading fault element: " + byCode.getCode() + " - " + textContent2);
                return new ActionException(byCode, textContent2, false);
            }
            log.fine("Reading fault element: " + iIntValue + " - " + textContent2);
            return new ActionException(iIntValue, textContent2);
        } catch (NumberFormatException unused) {
            throw new RuntimeException("Error code was not a number");
        }
    }

    public String toString(Document document) {
        String strDocumentToString = XMLUtil.documentToString(document);
        while (true) {
            if (!strDocumentToString.endsWith("\n") && !strDocumentToString.endsWith("\r")) {
                return strDocumentToString;
            }
            strDocumentToString = strDocumentToString.substring(0, strDocumentToString.length() - 1);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) {
        log.warning(sAXParseException.toString());
    }

    public void writeActionInputArguments(Document document, Element element, ActionInvocation actionInvocation) {
        for (ActionArgument actionArgument : actionInvocation.getAction().getInputArguments()) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Writing action input argument: ");
            sbM112a.append(actionArgument.getName());
            logger.fine(sbM112a.toString());
            XMLUtil.appendNewElement(document, element, actionArgument.getName(), actionInvocation.getInput(actionArgument) != null ? actionInvocation.getInput(actionArgument).toString() : "");
        }
    }

    public void writeActionOutputArguments(Document document, Element element, ActionInvocation actionInvocation) {
        for (ActionArgument actionArgument : actionInvocation.getAction().getOutputArguments()) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Writing action output argument: ");
            sbM112a.append(actionArgument.getName());
            logger.fine(sbM112a.toString());
            XMLUtil.appendNewElement(document, element, actionArgument.getName(), actionInvocation.getOutput(actionArgument) != null ? actionInvocation.getOutput(actionArgument).toString() : "");
        }
    }

    public Element writeActionRequestElement(Document document, Element element, ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) throws DOMException {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Writing action request element: ");
        sbM112a.append(actionInvocation.getAction().getName());
        logger.fine(sbM112a.toString());
        String actionNamespace = actionRequestMessage.getActionNamespace();
        StringBuilder sbM112a2 = C0413b.m112a("u:");
        sbM112a2.append(actionInvocation.getAction().getName());
        Element elementCreateElementNS = document.createElementNS(actionNamespace, sbM112a2.toString());
        element.appendChild(elementCreateElementNS);
        return elementCreateElementNS;
    }

    public Element writeActionResponseElement(Document document, Element element, ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) throws DOMException {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Writing action response element: ");
        sbM112a.append(actionInvocation.getAction().getName());
        logger.fine(sbM112a.toString());
        String actionNamespace = actionResponseMessage.getActionNamespace();
        StringBuilder sbM112a2 = C0413b.m112a("u:");
        sbM112a2.append(actionInvocation.getAction().getName());
        sbM112a2.append("Response");
        Element elementCreateElementNS = document.createElementNS(actionNamespace, sbM112a2.toString());
        element.appendChild(elementCreateElementNS);
        return elementCreateElementNS;
    }

    @Override // org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void writeBody(ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) {
        log.fine("Writing body of " + actionRequestMessage + " for: " + actionInvocation);
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
            writeBodyRequest(documentNewDocument, writeBodyElement(documentNewDocument), actionRequestMessage, actionInvocation);
            if (log.isLoggable(Level.FINER)) {
                log.finer("===================================== SOAP BODY BEGIN ============================================");
                log.finer(actionRequestMessage.getBodyString());
                log.finer("-===================================== SOAP BODY END ============================================");
            }
        } catch (Exception e7) {
            throw new UnsupportedDataException("Can't transform message payload: " + e7, e7);
        }
    }

    public Element writeBodyElement(Document document) throws DOMException {
        Element elementCreateElementNS = document.createElementNS(Constants.SOAP_NS_ENVELOPE, "s:Envelope");
        Attr attrCreateAttributeNS = document.createAttributeNS(Constants.SOAP_NS_ENVELOPE, "s:encodingStyle");
        attrCreateAttributeNS.setValue(Constants.SOAP_URI_ENCODING_STYLE);
        elementCreateElementNS.setAttributeNode(attrCreateAttributeNS);
        document.appendChild(elementCreateElementNS);
        Element elementCreateElementNS2 = document.createElementNS(Constants.SOAP_NS_ENVELOPE, "s:Body");
        elementCreateElementNS.appendChild(elementCreateElementNS2);
        return elementCreateElementNS2;
    }

    public void writeBodyFailure(Document document, Element element, ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) throws DOMException {
        writeFaultElement(document, element, actionInvocation);
        actionResponseMessage.setBody(toString(document));
    }

    public void writeBodyRequest(Document document, Element element, ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) {
        writeActionInputArguments(document, writeActionRequestElement(document, element, actionRequestMessage, actionInvocation), actionInvocation);
        actionRequestMessage.setBody(toString(document));
    }

    public void writeBodyResponse(Document document, Element element, ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) {
        writeActionOutputArguments(document, writeActionResponseElement(document, element, actionResponseMessage, actionInvocation), actionInvocation);
        actionResponseMessage.setBody(toString(document));
    }

    public void writeFaultElement(Document document, Element element, ActionInvocation actionInvocation) throws DOMException {
        Element elementCreateElementNS = document.createElementNS(Constants.SOAP_NS_ENVELOPE, "s:Fault");
        element.appendChild(elementCreateElementNS);
        XMLUtil.appendNewElement(document, elementCreateElementNS, "faultcode", "s:Client");
        XMLUtil.appendNewElement(document, elementCreateElementNS, "faultstring", "UPnPError");
        Element elementCreateElement = document.createElement("detail");
        elementCreateElementNS.appendChild(elementCreateElement);
        Element elementCreateElementNS2 = document.createElementNS(Constants.NS_UPNP_CONTROL_10, "UPnPError");
        elementCreateElement.appendChild(elementCreateElementNS2);
        int errorCode = actionInvocation.getFailure().getErrorCode();
        String message = actionInvocation.getFailure().getMessage();
        log.fine("Writing fault element: " + errorCode + " - " + message);
        XMLUtil.appendNewElement(document, elementCreateElementNS2, "errorCode", Integer.toString(errorCode));
        XMLUtil.appendNewElement(document, elementCreateElementNS2, "errorDescription", message);
    }

    @Override // org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void writeBody(ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) {
        log.fine("Writing body of " + actionResponseMessage + " for: " + actionInvocation);
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
            Element elementWriteBodyElement = writeBodyElement(documentNewDocument);
            if (actionInvocation.getFailure() != null) {
                writeBodyFailure(documentNewDocument, elementWriteBodyElement, actionResponseMessage, actionInvocation);
            } else {
                writeBodyResponse(documentNewDocument, elementWriteBodyElement, actionResponseMessage, actionInvocation);
            }
            if (log.isLoggable(Level.FINER)) {
                log.finer("===================================== SOAP BODY BEGIN ============================================");
                log.finer(actionResponseMessage.getBodyString());
                log.finer("-===================================== SOAP BODY END ============================================");
            }
        } catch (Exception e7) {
            throw new UnsupportedDataException("Can't transform message payload: " + e7, e7);
        }
    }

    @Override // org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void readBody(ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) throws ParserConfigurationException, SAXException, IOException {
        log.fine("Reading body of " + actionResponseMessage + " for: " + actionInvocation);
        if (log.isLoggable(Level.FINER)) {
            log.finer("===================================== SOAP BODY BEGIN ============================================");
            log.finer(actionResponseMessage.getBodyString());
            log.finer("-===================================== SOAP BODY END ============================================");
        }
        String messageBody = getMessageBody(actionResponseMessage);
        try {
            DocumentBuilderFactory documentBuilderFactoryCreateDocumentBuilderFactory = createDocumentBuilderFactory();
            documentBuilderFactoryCreateDocumentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilderNewDocumentBuilder = documentBuilderFactoryCreateDocumentBuilderFactory.newDocumentBuilder();
            documentBuilderNewDocumentBuilder.setErrorHandler(this);
            Document document = documentBuilderNewDocumentBuilder.parse(new InputSource(new StringReader(messageBody)));
            Element bodyElement = readBodyElement(document);
            ActionException bodyFailure = readBodyFailure(document, bodyElement);
            if (bodyFailure == null) {
                readBodyResponse(document, bodyElement, actionResponseMessage, actionInvocation);
            } else {
                actionInvocation.setFailure(bodyFailure);
            }
        } catch (Exception e7) {
            throw new UnsupportedDataException("Can't transform message payload: " + e7, e7, messageBody);
        }
    }
}
