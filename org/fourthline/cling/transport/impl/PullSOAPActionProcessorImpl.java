package org.fourthline.cling.transport.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.action.ActionArgumentValue;
import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.control.ActionRequestMessage;
import org.fourthline.cling.model.message.control.ActionResponseMessage;
import org.fourthline.cling.model.meta.ActionArgument;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.transport.spi.SOAPActionProcessor;
import org.seamless.xml.C1729h;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PullSOAPActionProcessorImpl extends SOAPActionProcessorImpl {
    public static Logger log = Logger.getLogger(SOAPActionProcessor.class.getName());

    public String findActionArgumentValue(Map<String, String> map, ActionArgument actionArgument) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (actionArgument.isNameOrAlias(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Map<String, String> getMatchingNodes(XmlPullParser xmlPullParser, ActionArgument[] actionArgumentArr) throws XmlPullParserException, ActionException, IOException {
        ArrayList arrayList = new ArrayList();
        for (ActionArgument actionArgument : actionArgumentArr) {
            arrayList.add(actionArgument.getName().toUpperCase(Locale.ROOT));
            Iterator it = Arrays.asList(actionArgument.getAliases()).iterator();
            while (it.hasNext()) {
                arrayList.add(((String) it.next()).toUpperCase(Locale.ROOT));
            }
        }
        HashMap map = new HashMap();
        String name = xmlPullParser.getName();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && arrayList.contains(xmlPullParser.getName().toUpperCase(Locale.ROOT))) {
                map.put(xmlPullParser.getName(), xmlPullParser.nextText());
            }
            if (next == 1 || (next == 3 && xmlPullParser.getName().equals(name))) {
                break;
            }
        }
        if (map.size() >= actionArgumentArr.length) {
            return map;
        }
        ErrorCode errorCode = ErrorCode.ARGUMENT_VALUE_INVALID;
        StringBuilder sbM112a = C0413b.m112a("Invalid number of input or output arguments in XML message, expected ");
        sbM112a.append(actionArgumentArr.length);
        sbM112a.append(" but found ");
        sbM112a.append(map.size());
        throw new ActionException(errorCode, sbM112a.toString());
    }

    public void readActionInputArguments(XmlPullParser xmlPullParser, ActionInvocation actionInvocation) {
        actionInvocation.setInput(readArgumentValues(xmlPullParser, actionInvocation.getAction().getInputArguments()));
    }

    public void readActionOutputArguments(XmlPullParser xmlPullParser, ActionInvocation actionInvocation) {
        actionInvocation.setOutput(readArgumentValues(xmlPullParser, actionInvocation.getAction().getOutputArguments()));
    }

    public ActionArgumentValue[] readArgumentValues(XmlPullParser xmlPullParser, ActionArgument[] actionArgumentArr) throws XmlPullParserException, ActionException, IOException {
        Map<String, String> matchingNodes = getMatchingNodes(xmlPullParser, actionArgumentArr);
        ActionArgumentValue[] actionArgumentValueArr = new ActionArgumentValue[actionArgumentArr.length];
        for (int i7 = 0; i7 < actionArgumentArr.length; i7++) {
            ActionArgument actionArgument = actionArgumentArr[i7];
            String strFindActionArgumentValue = findActionArgumentValue(matchingNodes, actionArgument);
            if (strFindActionArgumentValue == null) {
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
            actionArgumentValueArr[i7] = createValue(actionArgument, strFindActionArgumentValue);
        }
        return actionArgumentValueArr;
    }

    @Override // org.fourthline.cling.transport.impl.SOAPActionProcessorImpl, org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void readBody(ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) {
        String messageBody = getMessageBody(actionRequestMessage);
        try {
            readBodyRequest(C1729h.m1872a(messageBody), actionRequestMessage, actionInvocation);
        } catch (Exception e7) {
            throw new UnsupportedDataException("Can't transform message payload: " + e7, e7, messageBody);
        }
    }

    public void readBodyElement(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C1729h.m1874c(xmlPullParser, "Body");
    }

    public void readBodyRequest(XmlPullParser xmlPullParser, ActionRequestMessage actionRequestMessage, ActionInvocation actionInvocation) throws XmlPullParserException, IOException {
        C1729h.m1874c(xmlPullParser, actionInvocation.getAction().getName());
        readActionInputArguments(xmlPullParser, actionInvocation);
    }

    public void readBodyResponse(XmlPullParser xmlPullParser, ActionInvocation actionInvocation) throws XmlPullParserException, ActionException, IOException {
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("Fault")) {
                    actionInvocation.setFailure(readFaultElement(xmlPullParser));
                    return;
                }
                if (xmlPullParser.getName().equals(actionInvocation.getAction().getName() + "Response")) {
                    readActionOutputArguments(xmlPullParser, actionInvocation);
                    return;
                }
            }
            if (next == 1 || (next == 3 && xmlPullParser.getName().equals("Body"))) {
                break;
            }
        }
        throw new ActionException(ErrorCode.ACTION_FAILED, String.format("Action SOAP response do not contain %s element", actionInvocation.getAction().getName() + "Response"));
    }

    public ActionException readFaultElement(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        C1729h.m1874c(xmlPullParser, "UPnPError");
        String strNextText = null;
        String strNextText2 = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("errorCode")) {
                    strNextText = xmlPullParser.nextText();
                } else if (name.equals("errorDescription")) {
                    strNextText2 = xmlPullParser.nextText();
                }
            }
            if (next == 1 || (next == 3 && xmlPullParser.getName().equals("UPnPError"))) {
                break;
            }
        }
        if (strNextText == null) {
            throw new RuntimeException("Received fault element but no error code");
        }
        try {
            int iIntValue = Integer.valueOf(strNextText).intValue();
            ErrorCode byCode = ErrorCode.getByCode(iIntValue);
            if (byCode != null) {
                log.fine("Reading fault element: " + byCode.getCode() + " - " + strNextText2);
                return new ActionException(byCode, strNextText2, false);
            }
            log.fine("Reading fault element: " + iIntValue + " - " + strNextText2);
            return new ActionException(iIntValue, strNextText2);
        } catch (NumberFormatException unused) {
            throw new RuntimeException("Error code was not a number");
        }
    }

    @Override // org.fourthline.cling.transport.impl.SOAPActionProcessorImpl, org.fourthline.cling.transport.spi.SOAPActionProcessor
    public void readBody(ActionResponseMessage actionResponseMessage, ActionInvocation actionInvocation) {
        String messageBody = getMessageBody(actionResponseMessage);
        try {
            XmlPullParser xmlPullParserM1872a = C1729h.m1872a(messageBody);
            readBodyElement(xmlPullParserM1872a);
            readBodyResponse(xmlPullParserM1872a, actionInvocation);
        } catch (Exception e7) {
            throw new UnsupportedDataException("Can't transform message payload: " + e7, e7, messageBody);
        }
    }
}
