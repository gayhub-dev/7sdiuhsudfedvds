package org.fourthline.cling.transport.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.message.gena.IncomingEventRequestMessage;
import org.fourthline.cling.model.meta.RemoteService;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.state.StateVariableValue;
import org.fourthline.cling.transport.spi.GENAEventProcessor;
import org.seamless.xml.C1729h;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PullGENAEventProcessorImpl extends GENAEventProcessorImpl {
    private static Logger log = Logger.getLogger(GENAEventProcessor.class.getName());

    @Override // org.fourthline.cling.transport.impl.GENAEventProcessorImpl, org.fourthline.cling.transport.spi.GENAEventProcessor
    public void readBody(IncomingEventRequestMessage incomingEventRequestMessage) {
        log.fine("Reading body of: " + incomingEventRequestMessage);
        if (log.isLoggable(Level.FINER)) {
            log.finer("===================================== GENA BODY BEGIN ============================================");
            log.finer(incomingEventRequestMessage.getBody() != null ? incomingEventRequestMessage.getBody().toString() : null);
            log.finer("-===================================== GENA BODY END ============================================");
        }
        String messageBody = getMessageBody(incomingEventRequestMessage);
        try {
            readProperties(C1729h.m1872a(messageBody), incomingEventRequestMessage);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Can't transform message payload: ");
            sbM112a.append(e7.getMessage());
            throw new UnsupportedDataException(sbM112a.toString(), e7, messageBody);
        }
    }

    public void readProperties(XmlPullParser xmlPullParser, IncomingEventRequestMessage incomingEventRequestMessage) throws XmlPullParserException, IOException {
        StateVariable<RemoteService>[] stateVariables = incomingEventRequestMessage.getService().getStateVariables();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 2 && xmlPullParser.getName().equals("property")) {
                readProperty(xmlPullParser, incomingEventRequestMessage, stateVariables);
            }
        }
    }

    public void readProperty(XmlPullParser xmlPullParser, IncomingEventRequestMessage incomingEventRequestMessage, StateVariable[] stateVariableArr) throws XmlPullParserException, IOException {
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                int length = stateVariableArr.length;
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        break;
                    }
                    StateVariable stateVariable = stateVariableArr[i7];
                    if (stateVariable.getName().equals(name)) {
                        log.fine("Reading state variable value: " + name);
                        incomingEventRequestMessage.getStateVariableValues().add(new StateVariableValue(stateVariable, xmlPullParser.nextText()));
                        break;
                    }
                    i7++;
                }
            }
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getName().equals("property")) {
                return;
            }
        }
    }
}
