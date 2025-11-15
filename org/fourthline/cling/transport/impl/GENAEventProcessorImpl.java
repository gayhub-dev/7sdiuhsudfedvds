package org.fourthline.cling.transport.impl;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.fourthline.cling.model.Constants;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.XMLUtil;
import org.fourthline.cling.model.message.UpnpMessage;
import org.fourthline.cling.model.message.gena.IncomingEventRequestMessage;
import org.fourthline.cling.model.message.gena.OutgoingEventRequestMessage;
import org.fourthline.cling.model.meta.RemoteService;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.state.StateVariableValue;
import org.fourthline.cling.transport.spi.GENAEventProcessor;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class GENAEventProcessorImpl implements GENAEventProcessor, ErrorHandler {
    private static Logger log = Logger.getLogger(GENAEventProcessor.class.getName());

    public DocumentBuilderFactory createDocumentBuilderFactory() {
        return DocumentBuilderFactory.newInstance();
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXParseException {
        throw sAXParseException;
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXParseException {
        throw sAXParseException;
    }

    public String getMessageBody(UpnpMessage upnpMessage) {
        if (upnpMessage.isBodyNonEmptyString()) {
            return upnpMessage.getBodyString().trim();
        }
        throw new UnsupportedDataException("Can't transform null or non-string/zero-length body of: " + upnpMessage);
    }

    public String getUnprefixedNodeName(Node node) {
        return node.getPrefix() != null ? node.getNodeName().substring(node.getPrefix().length() + 1) : node.getNodeName();
    }

    @Override // org.fourthline.cling.transport.spi.GENAEventProcessor
    public void readBody(IncomingEventRequestMessage incomingEventRequestMessage) throws ParserConfigurationException {
        log.fine("Reading body of: " + incomingEventRequestMessage);
        if (log.isLoggable(Level.FINER)) {
            log.finer("===================================== GENA BODY BEGIN ============================================");
            log.finer(incomingEventRequestMessage.getBody() != null ? incomingEventRequestMessage.getBody().toString() : "null");
            log.finer("-===================================== GENA BODY END ============================================");
        }
        String messageBody = getMessageBody(incomingEventRequestMessage);
        try {
            DocumentBuilderFactory documentBuilderFactoryCreateDocumentBuilderFactory = createDocumentBuilderFactory();
            documentBuilderFactoryCreateDocumentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilderNewDocumentBuilder = documentBuilderFactoryCreateDocumentBuilderFactory.newDocumentBuilder();
            documentBuilderNewDocumentBuilder.setErrorHandler(this);
            readProperties(readPropertysetElement(documentBuilderNewDocumentBuilder.parse(new InputSource(new StringReader(messageBody)))), incomingEventRequestMessage);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Can't transform message payload: ");
            sbM112a.append(e7.getMessage());
            throw new UnsupportedDataException(sbM112a.toString(), e7, messageBody);
        }
    }

    public void readProperties(Element element, IncomingEventRequestMessage incomingEventRequestMessage) {
        NodeList childNodes = element.getChildNodes();
        StateVariable<RemoteService>[] stateVariables = incomingEventRequestMessage.getService().getStateVariables();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1 && getUnprefixedNodeName(nodeItem).equals("property")) {
                NodeList childNodes2 = nodeItem.getChildNodes();
                for (int i8 = 0; i8 < childNodes2.getLength(); i8++) {
                    Node nodeItem2 = childNodes2.item(i8);
                    if (nodeItem2.getNodeType() == 1) {
                        String unprefixedNodeName = getUnprefixedNodeName(nodeItem2);
                        int length = stateVariables.length;
                        int i9 = 0;
                        while (true) {
                            if (i9 < length) {
                                StateVariable<RemoteService> stateVariable = stateVariables[i9];
                                if (stateVariable.getName().equals(unprefixedNodeName)) {
                                    log.fine("Reading state variable value: " + unprefixedNodeName);
                                    incomingEventRequestMessage.getStateVariableValues().add(new StateVariableValue(stateVariable, XMLUtil.getTextContent(nodeItem2)));
                                    break;
                                }
                                i9++;
                            }
                        }
                    }
                }
            }
        }
    }

    public Element readPropertysetElement(Document document) {
        Element documentElement = document.getDocumentElement();
        if (documentElement == null || !getUnprefixedNodeName(documentElement).equals("propertyset")) {
            throw new RuntimeException("Root element was not 'propertyset'");
        }
        return documentElement;
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

    @Override // org.fourthline.cling.transport.spi.GENAEventProcessor
    public void writeBody(OutgoingEventRequestMessage outgoingEventRequestMessage) {
        log.fine("Writing body of: " + outgoingEventRequestMessage);
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
            writeProperties(documentNewDocument, writePropertysetElement(documentNewDocument), outgoingEventRequestMessage);
            outgoingEventRequestMessage.setBody(UpnpMessage.BodyType.STRING, toString(documentNewDocument));
            if (log.isLoggable(Level.FINER)) {
                log.finer("===================================== GENA BODY BEGIN ============================================");
                log.finer(outgoingEventRequestMessage.getBody().toString());
                log.finer("====================================== GENA BODY END =============================================");
            }
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Can't transform message payload: ");
            sbM112a.append(e7.getMessage());
            throw new UnsupportedDataException(sbM112a.toString(), e7);
        }
    }

    public void writeProperties(Document document, Element element, OutgoingEventRequestMessage outgoingEventRequestMessage) throws DOMException {
        for (StateVariableValue stateVariableValue : outgoingEventRequestMessage.getStateVariableValues()) {
            Element elementCreateElementNS = document.createElementNS(Constants.NS_UPNP_EVENT_10, "e:property");
            element.appendChild(elementCreateElementNS);
            XMLUtil.appendNewElement(document, elementCreateElementNS, stateVariableValue.getStateVariable().getName(), stateVariableValue.toString());
        }
    }

    public Element writePropertysetElement(Document document) throws DOMException {
        Element elementCreateElementNS = document.createElementNS(Constants.NS_UPNP_EVENT_10, "e:propertyset");
        document.appendChild(elementCreateElementNS);
        return elementCreateElementNS;
    }
}
