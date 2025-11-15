package org.fourthline.cling.transport.spi;

import java.util.concurrent.ExecutorService;
import org.fourthline.cling.model.ServerClientTokens;

/* loaded from: classes.dex */
public abstract class AbstractStreamClientConfiguration implements StreamClientConfiguration {
    public int logWarningSeconds;
    public ExecutorService requestExecutorService;
    public int timeoutSeconds;

    public AbstractStreamClientConfiguration(ExecutorService executorService) {
        this.timeoutSeconds = 60;
        this.logWarningSeconds = 5;
        this.requestExecutorService = executorService;
    }

    @Override // org.fourthline.cling.transport.spi.StreamClientConfiguration
    public int getLogWarningSeconds() {
        return this.logWarningSeconds;
    }

    @Override // org.fourthline.cling.transport.spi.StreamClientConfiguration
    public ExecutorService getRequestExecutorService() {
        return this.requestExecutorService;
    }

    @Override // org.fourthline.cling.transport.spi.StreamClientConfiguration
    public int getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    @Override // org.fourthline.cling.transport.spi.StreamClientConfiguration
    public String getUserAgentValue(int i7, int i8) {
        return new ServerClientTokens(i7, i8).toString();
    }

    public void setLogWarningSeconds(int i7) {
        this.logWarningSeconds = i7;
    }

    public void setRequestExecutorService(ExecutorService executorService) {
        this.requestExecutorService = executorService;
    }

    public void setTimeoutSeconds(int i7) {
        this.timeoutSeconds = i7;
    }

    public AbstractStreamClientConfiguration(ExecutorService executorService, int i7) {
        this.timeoutSeconds = 60;
        this.logWarningSeconds = 5;
        this.requestExecutorService = executorService;
        this.timeoutSeconds = i7;
    }

    public AbstractStreamClientConfiguration(ExecutorService executorService, int i7, int i8) {
        this.timeoutSeconds = 60;
        this.logWarningSeconds = 5;
        this.requestExecutorService = executorService;
        this.timeoutSeconds = i7;
        this.logWarningSeconds = i8;
    }
}
