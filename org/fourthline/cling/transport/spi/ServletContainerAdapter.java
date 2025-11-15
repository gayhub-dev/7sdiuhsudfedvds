package org.fourthline.cling.transport.spi;

import java.util.concurrent.ExecutorService;
import p006a5.InterfaceC0024j;

/* loaded from: classes.dex */
public interface ServletContainerAdapter {
    int addConnector(String str, int i7);

    void registerServlet(String str, InterfaceC0024j interfaceC0024j);

    void removeConnector(String str, int i7);

    void setExecutorService(ExecutorService executorService);

    void startIfNotRunning();

    void stopIfRunning();
}
