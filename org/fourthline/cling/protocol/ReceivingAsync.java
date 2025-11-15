package org.fourthline.cling.protocol;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.message.UpnpMessage;
import org.fourthline.cling.model.message.header.UpnpHeader;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class ReceivingAsync<M extends UpnpMessage> implements Runnable {
    private static final Logger log = Logger.getLogger(UpnpService.class.getName());
    private M inputMessage;
    private final UpnpService upnpService;

    public ReceivingAsync(UpnpService upnpService, M m7) {
        this.upnpService = upnpService;
        this.inputMessage = m7;
    }

    public abstract void execute();

    public <H extends UpnpHeader> H getFirstHeader(UpnpHeader.Type type, Class<H> cls) {
        return (H) getInputMessage().getHeaders().getFirstHeader(type, cls);
    }

    public M getInputMessage() {
        return this.inputMessage;
    }

    public UpnpService getUpnpService() {
        return this.upnpService;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zWaitBeforeExecution;
        try {
            zWaitBeforeExecution = waitBeforeExecution();
        } catch (InterruptedException unused) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Protocol wait before execution interrupted (on shutdown?): ");
            sbM112a.append(getClass().getSimpleName());
            logger.info(sbM112a.toString());
            zWaitBeforeExecution = false;
        }
        if (zWaitBeforeExecution) {
            try {
                execute();
            } catch (Exception e7) {
                Throwable thM2475O = C2074b.m2475O(e7);
                if (!(thM2475O instanceof InterruptedException)) {
                    StringBuilder sbM112a2 = C0413b.m112a("Fatal error while executing protocol '");
                    sbM112a2.append(getClass().getSimpleName());
                    sbM112a2.append("': ");
                    sbM112a2.append(e7);
                    throw new RuntimeException(sbM112a2.toString(), e7);
                }
                Logger logger2 = log;
                Level level = Level.INFO;
                StringBuilder sbM112a3 = C0413b.m112a("Interrupted protocol '");
                sbM112a3.append(getClass().getSimpleName());
                sbM112a3.append("': ");
                sbM112a3.append(e7);
                logger2.log(level, sbM112a3.toString(), thM2475O);
            }
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(")");
        return sbM112a.toString();
    }

    public boolean waitBeforeExecution() {
        return true;
    }
}
