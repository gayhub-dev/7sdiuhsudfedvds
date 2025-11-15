package org.fourthline.cling.binding.xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.fourthline.cling.binding.staging.MutableAction;
import org.fourthline.cling.binding.staging.MutableActionArgument;
import org.fourthline.cling.binding.staging.MutableAllowedValueRange;
import org.fourthline.cling.binding.staging.MutableService;
import org.fourthline.cling.binding.staging.MutableStateVariable;
import org.fourthline.cling.binding.xml.Descriptor;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.XMLUtil;
import org.fourthline.cling.model.meta.Action;
import org.fourthline.cling.model.meta.ActionArgument;
import org.fourthline.cling.model.meta.QueryStateVariableAction;
import org.fourthline.cling.model.meta.RemoteService;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.meta.StateVariableEventDetails;
import org.fourthline.cling.model.types.CustomDatatype;
import org.fourthline.cling.model.types.Datatype;
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
public class UDA10ServiceDescriptorBinderImpl implements ServiceDescriptorBinder, ErrorHandler {
    private static Logger log = Logger.getLogger(ServiceDescriptorBinder.class.getName());

    private void generateAction(Action action, Document document, Element element) {
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, Descriptor.Service.ELEMENT.action);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.name, action.getName());
        if (action.hasArguments()) {
            Element elementAppendNewElement2 = XMLUtil.appendNewElement(document, elementAppendNewElement, Descriptor.Service.ELEMENT.argumentList);
            for (ActionArgument actionArgument : action.getArguments()) {
                generateActionArgument(actionArgument, document, elementAppendNewElement2);
            }
        }
    }

    private void generateActionArgument(ActionArgument actionArgument, Document document, Element element) {
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, Descriptor.Service.ELEMENT.argument);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.name, actionArgument.getName());
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.direction, actionArgument.getDirection().toString().toLowerCase(Locale.ROOT));
        if (actionArgument.isReturnValue()) {
            log.warning("UPnP specification violation: Not producing <retval> element to be compatible with WMP12: " + actionArgument);
        }
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.relatedStateVariable, actionArgument.getRelatedStateVariableName());
    }

    private void generateActionList(Service service, Document document, Element element) {
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, Descriptor.Service.ELEMENT.actionList);
        for (Action action : service.getActions()) {
            if (!action.getName().equals(QueryStateVariableAction.ACTION_NAME)) {
                generateAction(action, document, elementAppendNewElement);
            }
        }
    }

    private void generateScpd(Service service, Document document) throws DOMException {
        Element elementCreateElementNS = document.createElementNS(Descriptor.Service.NAMESPACE_URI, Descriptor.Service.ELEMENT.scpd.toString());
        document.appendChild(elementCreateElementNS);
        generateSpecVersion(service, document, elementCreateElementNS);
        if (service.hasActions()) {
            generateActionList(service, document, elementCreateElementNS);
        }
        generateServiceStateTable(service, document, elementCreateElementNS);
    }

    private void generateServiceStateTable(Service service, Document document, Element element) throws DOMException {
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, Descriptor.Service.ELEMENT.serviceStateTable);
        for (StateVariable stateVariable : service.getStateVariables()) {
            generateStateVariable(stateVariable, document, elementAppendNewElement);
        }
    }

    private void generateSpecVersion(Service service, Document document, Element element) {
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, Descriptor.Service.ELEMENT.specVersion);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.major, Integer.valueOf(service.getDevice().getVersion().getMajor()));
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.minor, Integer.valueOf(service.getDevice().getVersion().getMinor()));
    }

    private void generateStateVariable(StateVariable stateVariable, Document document, Element element) throws DOMException {
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, Descriptor.Service.ELEMENT.stateVariable);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.name, stateVariable.getName());
        if (stateVariable.getTypeDetails().getDatatype() instanceof CustomDatatype) {
            XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.dataType, ((CustomDatatype) stateVariable.getTypeDetails().getDatatype()).getName());
        } else {
            XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.dataType, stateVariable.getTypeDetails().getDatatype().getBuiltin().getDescriptorName());
        }
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, Descriptor.Service.ELEMENT.defaultValue, stateVariable.getTypeDetails().getDefaultValue());
        if (stateVariable.getEventDetails().isSendEvents()) {
            elementAppendNewElement.setAttribute(Descriptor.Service.ATTRIBUTE.sendEvents.toString(), "yes");
        } else {
            elementAppendNewElement.setAttribute(Descriptor.Service.ATTRIBUTE.sendEvents.toString(), "no");
        }
        if (stateVariable.getTypeDetails().getAllowedValues() != null) {
            Element elementAppendNewElement2 = XMLUtil.appendNewElement(document, elementAppendNewElement, Descriptor.Service.ELEMENT.allowedValueList);
            for (String str : stateVariable.getTypeDetails().getAllowedValues()) {
                XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement2, Descriptor.Service.ELEMENT.allowedValue, str);
            }
        }
        if (stateVariable.getTypeDetails().getAllowedValueRange() != null) {
            Element elementAppendNewElement3 = XMLUtil.appendNewElement(document, elementAppendNewElement, Descriptor.Service.ELEMENT.allowedValueRange);
            XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement3, Descriptor.Service.ELEMENT.minimum, Long.valueOf(stateVariable.getTypeDetails().getAllowedValueRange().getMinimum()));
            XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement3, Descriptor.Service.ELEMENT.maximum, Long.valueOf(stateVariable.getTypeDetails().getAllowedValueRange().getMaximum()));
            if (stateVariable.getTypeDetails().getAllowedValueRange().getStep() >= 1) {
                XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement3, Descriptor.Service.ELEMENT.step, Long.valueOf(stateVariable.getTypeDetails().getAllowedValueRange().getStep()));
            }
        }
    }

    @Override // org.fourthline.cling.binding.xml.ServiceDescriptorBinder
    public Document buildDOM(Service service) throws DescriptorBindingException {
        try {
            log.fine("Generating XML descriptor from service model: " + service);
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
            generateScpd(service, documentNewDocument);
            return documentNewDocument;
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Could not generate service descriptor: ");
            sbM112a.append(e7.getMessage());
            throw new DescriptorBindingException(sbM112a.toString(), e7);
        }
    }

    public <S extends Service> S buildInstance(S s6, MutableService mutableService) {
        return (S) mutableService.build(s6.getDevice());
    }

    @Override // org.fourthline.cling.binding.xml.ServiceDescriptorBinder
    public <S extends Service> S describe(S s6, String str) throws ParserConfigurationException, ValidationException, DescriptorBindingException {
        if (str == null || str.length() == 0) {
            throw new DescriptorBindingException("Null or empty descriptor");
        }
        try {
            log.fine("Populating service from XML descriptor: " + s6);
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            DocumentBuilder documentBuilderNewDocumentBuilder = documentBuilderFactoryNewInstance.newDocumentBuilder();
            documentBuilderNewDocumentBuilder.setErrorHandler(this);
            return (S) describe((UDA10ServiceDescriptorBinderImpl) s6, documentBuilderNewDocumentBuilder.parse(new InputSource(new StringReader(str.trim()))));
        } catch (ValidationException e7) {
            throw e7;
        } catch (Exception e8) {
            StringBuilder sbM112a = C0413b.m112a("Could not parse service descriptor: ");
            sbM112a.append(e8.toString());
            throw new DescriptorBindingException(sbM112a.toString(), e8);
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

    @Override // org.fourthline.cling.binding.xml.ServiceDescriptorBinder
    public String generate(Service service) throws DescriptorBindingException {
        try {
            log.fine("Generating XML descriptor from service model: " + service);
            return XMLUtil.documentToString(buildDOM(service));
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Could not build DOM: ");
            sbM112a.append(e7.getMessage());
            throw new DescriptorBindingException(sbM112a.toString(), e7);
        }
    }

    public void hydrateAction(MutableAction mutableAction, Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1) {
                if (Descriptor.Service.ELEMENT.name.equals(nodeItem)) {
                    mutableAction.name = XMLUtil.getTextContent(nodeItem);
                } else if (Descriptor.Service.ELEMENT.argumentList.equals(nodeItem)) {
                    NodeList childNodes2 = nodeItem.getChildNodes();
                    for (int i8 = 0; i8 < childNodes2.getLength(); i8++) {
                        Node nodeItem2 = childNodes2.item(i8);
                        if (nodeItem2.getNodeType() == 1) {
                            MutableActionArgument mutableActionArgument = new MutableActionArgument();
                            hydrateActionArgument(mutableActionArgument, nodeItem2);
                            mutableAction.arguments.add(mutableActionArgument);
                        }
                    }
                }
            }
        }
    }

    public void hydrateActionArgument(MutableActionArgument mutableActionArgument, Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1) {
                if (Descriptor.Service.ELEMENT.name.equals(nodeItem)) {
                    mutableActionArgument.name = XMLUtil.getTextContent(nodeItem);
                } else if (Descriptor.Service.ELEMENT.direction.equals(nodeItem)) {
                    String textContent = XMLUtil.getTextContent(nodeItem);
                    try {
                        mutableActionArgument.direction = ActionArgument.Direction.valueOf(textContent.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException unused) {
                        log.warning("UPnP specification violation: Invalid action argument direction, assuming 'IN': " + textContent);
                        mutableActionArgument.direction = ActionArgument.Direction.IN;
                    }
                } else if (Descriptor.Service.ELEMENT.relatedStateVariable.equals(nodeItem)) {
                    mutableActionArgument.relatedStateVariable = XMLUtil.getTextContent(nodeItem);
                } else if (Descriptor.Service.ELEMENT.retval.equals(nodeItem)) {
                    mutableActionArgument.retval = true;
                }
            }
        }
    }

    public void hydrateActionList(MutableService mutableService, Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1 && Descriptor.Service.ELEMENT.action.equals(nodeItem)) {
                MutableAction mutableAction = new MutableAction();
                hydrateAction(mutableAction, nodeItem);
                mutableService.actions.add(mutableAction);
            }
        }
    }

    public void hydrateBasic(MutableService mutableService, Service service) {
        mutableService.serviceId = service.getServiceId();
        mutableService.serviceType = service.getServiceType();
        if (service instanceof RemoteService) {
            RemoteService remoteService = (RemoteService) service;
            mutableService.controlURI = remoteService.getControlURI();
            mutableService.eventSubscriptionURI = remoteService.getEventSubscriptionURI();
            mutableService.descriptorURI = remoteService.getDescriptorURI();
        }
    }

    public void hydrateRoot(MutableService mutableService, Element element) throws DescriptorBindingException {
        if (!Descriptor.Service.ELEMENT.scpd.equals((Node) element)) {
            StringBuilder sbM112a = C0413b.m112a("Root element name is not <scpd>: ");
            sbM112a.append(element.getNodeName());
            throw new DescriptorBindingException(sbM112a.toString());
        }
        NodeList childNodes = element.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1 && !Descriptor.Service.ELEMENT.specVersion.equals(nodeItem)) {
                if (Descriptor.Service.ELEMENT.actionList.equals(nodeItem)) {
                    hydrateActionList(mutableService, nodeItem);
                } else if (Descriptor.Service.ELEMENT.serviceStateTable.equals(nodeItem)) {
                    hydrateServiceStateTableList(mutableService, nodeItem);
                } else {
                    Logger logger = log;
                    StringBuilder sbM112a2 = C0413b.m112a("Ignoring unknown element: ");
                    sbM112a2.append(nodeItem.getNodeName());
                    logger.finer(sbM112a2.toString());
                }
            }
        }
    }

    public void hydrateServiceStateTableList(MutableService mutableService, Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1 && Descriptor.Service.ELEMENT.stateVariable.equals(nodeItem)) {
                MutableStateVariable mutableStateVariable = new MutableStateVariable();
                hydrateStateVariable(mutableStateVariable, (Element) nodeItem);
                mutableService.stateVariables.add(mutableStateVariable);
            }
        }
    }

    public void hydrateStateVariable(MutableStateVariable mutableStateVariable, Element element) {
        mutableStateVariable.eventDetails = new StateVariableEventDetails(element.getAttribute("sendEvents") != null && element.getAttribute(Descriptor.Service.ATTRIBUTE.sendEvents.toString()).toUpperCase(Locale.ROOT).equals("YES"));
        NodeList childNodes = element.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1) {
                if (Descriptor.Service.ELEMENT.name.equals(nodeItem)) {
                    mutableStateVariable.name = XMLUtil.getTextContent(nodeItem);
                } else if (Descriptor.Service.ELEMENT.dataType.equals(nodeItem)) {
                    String textContent = XMLUtil.getTextContent(nodeItem);
                    Datatype.Builtin byDescriptorName = Datatype.Builtin.getByDescriptorName(textContent);
                    mutableStateVariable.dataType = byDescriptorName != null ? byDescriptorName.getDatatype() : new CustomDatatype(textContent);
                } else if (Descriptor.Service.ELEMENT.defaultValue.equals(nodeItem)) {
                    mutableStateVariable.defaultValue = XMLUtil.getTextContent(nodeItem);
                } else if (Descriptor.Service.ELEMENT.allowedValueList.equals(nodeItem)) {
                    ArrayList arrayList = new ArrayList();
                    NodeList childNodes2 = nodeItem.getChildNodes();
                    for (int i8 = 0; i8 < childNodes2.getLength(); i8++) {
                        Node nodeItem2 = childNodes2.item(i8);
                        if (nodeItem2.getNodeType() == 1 && Descriptor.Service.ELEMENT.allowedValue.equals(nodeItem2)) {
                            arrayList.add(XMLUtil.getTextContent(nodeItem2));
                        }
                    }
                    mutableStateVariable.allowedValues = arrayList;
                } else if (Descriptor.Service.ELEMENT.allowedValueRange.equals(nodeItem)) {
                    MutableAllowedValueRange mutableAllowedValueRange = new MutableAllowedValueRange();
                    NodeList childNodes3 = nodeItem.getChildNodes();
                    for (int i9 = 0; i9 < childNodes3.getLength(); i9++) {
                        Node nodeItem3 = childNodes3.item(i9);
                        if (nodeItem3.getNodeType() == 1) {
                            if (Descriptor.Service.ELEMENT.minimum.equals(nodeItem3)) {
                                try {
                                    mutableAllowedValueRange.minimum = Long.valueOf(XMLUtil.getTextContent(nodeItem3));
                                } catch (Exception unused) {
                                }
                            } else if (Descriptor.Service.ELEMENT.maximum.equals(nodeItem3)) {
                                mutableAllowedValueRange.maximum = Long.valueOf(XMLUtil.getTextContent(nodeItem3));
                            } else if (Descriptor.Service.ELEMENT.step.equals(nodeItem3)) {
                                mutableAllowedValueRange.step = Long.valueOf(XMLUtil.getTextContent(nodeItem3));
                            }
                        }
                    }
                    mutableStateVariable.allowedValueRange = mutableAllowedValueRange;
                }
            }
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) {
        log.warning(sAXParseException.toString());
    }

    @Override // org.fourthline.cling.binding.xml.ServiceDescriptorBinder
    public <S extends Service> S describe(S s6, Document document) throws ValidationException, DescriptorBindingException {
        try {
            log.fine("Populating service from DOM: " + s6);
            MutableService mutableService = new MutableService();
            hydrateBasic(mutableService, s6);
            hydrateRoot(mutableService, document.getDocumentElement());
            return (S) buildInstance(s6, mutableService);
        } catch (ValidationException e7) {
            throw e7;
        } catch (Exception e8) {
            StringBuilder sbM112a = C0413b.m112a("Could not parse service DOM: ");
            sbM112a.append(e8.toString());
            throw new DescriptorBindingException(sbM112a.toString(), e8);
        }
    }
}
