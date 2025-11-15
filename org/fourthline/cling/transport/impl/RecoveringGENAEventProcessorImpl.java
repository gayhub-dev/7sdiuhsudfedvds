package org.fourthline.cling.transport.impl;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.XMLUtil;
import org.fourthline.cling.model.message.gena.IncomingEventRequestMessage;
import org.fourthline.cling.transport.spi.GENAEventProcessor;
import org.seamless.xml.C1729h;

/* loaded from: classes.dex */
public class RecoveringGENAEventProcessorImpl extends PullGENAEventProcessorImpl {
    private static Logger log = Logger.getLogger(GENAEventProcessor.class.getName());

    public String fixXMLEncodedLastChange(String str) {
        Matcher matcher = Pattern.compile("<LastChange>(.*)</LastChange>", 32).matcher(str);
        if (!matcher.find()) {
            return str;
        }
        boolean z6 = true;
        if (matcher.groupCount() != 1) {
            return str;
        }
        String strGroup = matcher.group(1);
        Logger logger = C1729h.f4910a;
        if (strGroup != null && strGroup.length() != 0) {
            z6 = false;
        }
        if (z6) {
            return str;
        }
        String strTrim = strGroup.trim();
        String strEncodeText = strTrim.charAt(0) == '<' ? XMLUtil.encodeText(strTrim) : strTrim;
        return strEncodeText.equals(strTrim) ? str : C0096a.m97a("<?xml version=\"1.0\" encoding=\"utf-8\"?><e:propertyset xmlns:e=\"urn:schemas-upnp-org:event-1-0\"><e:property><LastChange>", strEncodeText, "</LastChange></e:property></e:propertyset>");
    }

    @Override // org.fourthline.cling.transport.impl.PullGENAEventProcessorImpl, org.fourthline.cling.transport.impl.GENAEventProcessorImpl, org.fourthline.cling.transport.spi.GENAEventProcessor
    public void readBody(IncomingEventRequestMessage incomingEventRequestMessage) {
        try {
            super.readBody(incomingEventRequestMessage);
        } catch (UnsupportedDataException e7) {
            if (!incomingEventRequestMessage.isBodyNonEmptyString()) {
                throw e7;
            }
            log.warning("Trying to recover from invalid GENA XML event: " + e7);
            incomingEventRequestMessage.getStateVariableValues().clear();
            try {
                incomingEventRequestMessage.setBody(fixXMLEncodedLastChange(C1729h.m1873b(getMessageBody(incomingEventRequestMessage))));
                super.readBody(incomingEventRequestMessage);
            } catch (UnsupportedDataException unused) {
                if (incomingEventRequestMessage.getStateVariableValues().isEmpty()) {
                    throw e7;
                }
                log.warning("Partial read of GENA event properties (probably due to truncated XML)");
            }
        }
    }
}
