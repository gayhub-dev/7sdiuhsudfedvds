package org.fourthline.cling.registry;

import java.util.logging.Level;
import java.util.logging.Logger;
import p009b.C0413b;

/* loaded from: classes.dex */
public class RegistryMaintainer implements Runnable {
    private static Logger log = Logger.getLogger(RegistryMaintainer.class.getName());
    private final RegistryImpl registry;
    private final int sleepIntervalMillis;
    private volatile boolean stopped = false;

    public RegistryMaintainer(RegistryImpl registryImpl, int i7) {
        this.registry = registryImpl;
        this.sleepIntervalMillis = i7;
    }

    @Override // java.lang.Runnable
    public void run() throws InterruptedException {
        this.stopped = false;
        if (log.isLoggable(Level.FINE)) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Running registry maintenance loop every milliseconds: ");
            sbM112a.append(this.sleepIntervalMillis);
            logger.fine(sbM112a.toString());
        }
        while (!this.stopped) {
            try {
                this.registry.maintain();
                Thread.sleep(this.sleepIntervalMillis);
            } catch (InterruptedException unused) {
                this.stopped = true;
            }
        }
        log.fine("Stopped status on thread received, ending maintenance loop");
    }

    public void stop() {
        if (log.isLoggable(Level.FINE)) {
            log.fine("Setting stopped status on thread");
        }
        this.stopped = true;
    }
}
