package org.fourthline.cling.mock;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.fourthline.cling.DefaultUpnpServiceConfiguration;
import org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl;
import org.fourthline.cling.transport.spi.NetworkAddressFactory;

/* loaded from: classes.dex */
public class MockUpnpServiceConfiguration extends DefaultUpnpServiceConfiguration {
    public final boolean maintainsRegistry;
    public final boolean multiThreaded;

    public MockUpnpServiceConfiguration() {
        this(false, false);
    }

    @Override // org.fourthline.cling.DefaultUpnpServiceConfiguration
    public NetworkAddressFactory createNetworkAddressFactory(int i7) {
        return new NetworkAddressFactoryImpl(i7) { // from class: org.fourthline.cling.mock.MockUpnpServiceConfiguration.1
            @Override // org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl
            public boolean isUsableAddress(NetworkInterface networkInterface, InetAddress inetAddress) {
                return inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address);
            }

            @Override // org.fourthline.cling.transport.impl.NetworkAddressFactoryImpl
            public boolean isUsableNetworkInterface(NetworkInterface networkInterface) {
                return networkInterface.isLoopback();
            }
        };
    }

    @Override // org.fourthline.cling.DefaultUpnpServiceConfiguration
    public ExecutorService getDefaultExecutorService() {
        return isMultiThreaded() ? super.getDefaultExecutorService() : new AbstractExecutorService() { // from class: org.fourthline.cling.mock.MockUpnpServiceConfiguration.3
            public boolean terminated;

            @Override // java.util.concurrent.ExecutorService
            public boolean awaitTermination(long j7, TimeUnit timeUnit) {
                shutdown();
                return this.terminated;
            }

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                runnable.run();
            }

            @Override // java.util.concurrent.ExecutorService
            public boolean isShutdown() {
                return this.terminated;
            }

            @Override // java.util.concurrent.ExecutorService
            public boolean isTerminated() {
                return this.terminated;
            }

            @Override // java.util.concurrent.ExecutorService
            public void shutdown() {
                this.terminated = true;
            }

            @Override // java.util.concurrent.ExecutorService
            public List<Runnable> shutdownNow() {
                shutdown();
                return null;
            }
        };
    }

    @Override // org.fourthline.cling.DefaultUpnpServiceConfiguration, org.fourthline.cling.UpnpServiceConfiguration
    public Executor getRegistryMaintainerExecutor() {
        return isMaintainsRegistry() ? new Executor() { // from class: org.fourthline.cling.mock.MockUpnpServiceConfiguration.2
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                new Thread(runnable).start();
            }
        } : getDefaultExecutorService();
    }

    public boolean isMaintainsRegistry() {
        return this.maintainsRegistry;
    }

    public boolean isMultiThreaded() {
        return this.multiThreaded;
    }

    public MockUpnpServiceConfiguration(boolean z6) {
        this(z6, false);
    }

    public MockUpnpServiceConfiguration(boolean z6, boolean z7) {
        super(false);
        this.maintainsRegistry = z6;
        this.multiThreaded = z7;
    }
}
