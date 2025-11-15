package org.fourthline.cling.protocol.async;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.message.discovery.OutgoingSearchRequest;
import org.fourthline.cling.model.message.header.MXHeader;
import org.fourthline.cling.model.message.header.STAllHeader;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.protocol.SendingAsync;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SendingSearch extends SendingAsync {
    private static final Logger log = Logger.getLogger(SendingSearch.class.getName());
    private final int mxSeconds;
    private final UpnpHeader searchTarget;

    public SendingSearch(UpnpService upnpService) {
        this(upnpService, new STAllHeader());
    }

    @Override // org.fourthline.cling.protocol.SendingAsync
    public void execute() throws InterruptedException {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Executing search for target: ");
        sbM112a.append(this.searchTarget.getString());
        sbM112a.append(" with MX seconds: ");
        sbM112a.append(getMxSeconds());
        logger.fine(sbM112a.toString());
        OutgoingSearchRequest outgoingSearchRequest = new OutgoingSearchRequest(this.searchTarget, getMxSeconds());
        prepareOutgoingSearchRequest(outgoingSearchRequest);
        for (int i7 = 0; i7 < getBulkRepeat(); i7++) {
            try {
                getUpnpService().getRouter().send(outgoingSearchRequest);
                log.finer("Sleeping " + getBulkIntervalMilliseconds() + " milliseconds");
                Thread.sleep((long) getBulkIntervalMilliseconds());
            } catch (InterruptedException unused) {
                return;
            }
        }
    }

    public int getBulkIntervalMilliseconds() {
        return 500;
    }

    public int getBulkRepeat() {
        return 5;
    }

    public int getMxSeconds() {
        return this.mxSeconds;
    }

    public UpnpHeader getSearchTarget() {
        return this.searchTarget;
    }

    public void prepareOutgoingSearchRequest(OutgoingSearchRequest outgoingSearchRequest) {
    }

    public SendingSearch(UpnpService upnpService, UpnpHeader upnpHeader) {
        this(upnpService, upnpHeader, MXHeader.DEFAULT_VALUE.intValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SendingSearch(UpnpService upnpService, UpnpHeader upnpHeader, int i7) {
        super(upnpService);
        if (UpnpHeader.Type.ST.isValidHeaderType(upnpHeader.getClass())) {
            this.searchTarget = upnpHeader;
            this.mxSeconds = i7;
        } else {
            StringBuilder sbM112a = C0413b.m112a("Given search target instance is not a valid header class for type ST: ");
            sbM112a.append(upnpHeader.getClass());
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }
}
