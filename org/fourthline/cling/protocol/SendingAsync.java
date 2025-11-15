package org.fourthline.cling.protocol;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class SendingAsync implements Runnable {
    private static final Logger log = Logger.getLogger(UpnpService.class.getName());
    private final UpnpService upnpService;

    public SendingAsync(UpnpService upnpService) {
        this.upnpService = upnpService;
    }

    public abstract void execute();

    public UpnpService getUpnpService() {
        return this.upnpService;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            execute();
        } catch (Exception e7) {
            Throwable thM2475O = C2074b.m2475O(e7);
            if (!(thM2475O instanceof InterruptedException)) {
                StringBuilder sbM112a = C0413b.m112a("Fatal error while executing protocol '");
                sbM112a.append(getClass().getSimpleName());
                sbM112a.append("': ");
                sbM112a.append(e7);
                throw new RuntimeException(sbM112a.toString(), e7);
            }
            Logger logger = log;
            Level level = Level.INFO;
            StringBuilder sbM112a2 = C0413b.m112a("Interrupted protocol '");
            sbM112a2.append(getClass().getSimpleName());
            sbM112a2.append("': ");
            sbM112a2.append(e7);
            logger.log(level, sbM112a2.toString(), thM2475O);
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
