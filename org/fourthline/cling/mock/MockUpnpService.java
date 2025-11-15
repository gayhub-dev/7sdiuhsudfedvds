package org.fourthline.cling.mock;

import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceConfiguration;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.controlpoint.ControlPointImpl;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.protocol.ProtocolFactoryImpl;
import org.fourthline.cling.protocol.async.SendingNotificationAlive;
import org.fourthline.cling.protocol.async.SendingSearch;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.registry.RegistryImpl;
import org.fourthline.cling.registry.RegistryMaintainer;
import org.fourthline.cling.transport.spi.NetworkAddressFactory;

/* loaded from: classes.dex */
public class MockUpnpService implements UpnpService {
    public final UpnpServiceConfiguration configuration;
    public final ControlPoint controlPoint;
    public final NetworkAddressFactory networkAddressFactory;
    public final ProtocolFactory protocolFactory;
    public final Registry registry;
    public final MockRouter router;

    public static class MockProtocolFactory extends ProtocolFactoryImpl {
        private boolean sendsAlive;

        public MockProtocolFactory(UpnpService upnpService, boolean z6) {
            super(upnpService);
            this.sendsAlive = z6;
        }

        @Override // org.fourthline.cling.protocol.ProtocolFactoryImpl, org.fourthline.cling.protocol.ProtocolFactory
        public SendingNotificationAlive createSendingNotificationAlive(LocalDevice localDevice) {
            return new SendingNotificationAlive(getUpnpService(), localDevice) { // from class: org.fourthline.cling.mock.MockUpnpService.MockProtocolFactory.1
                @Override // org.fourthline.cling.protocol.async.SendingNotificationAlive, org.fourthline.cling.protocol.async.SendingNotification, org.fourthline.cling.protocol.SendingAsync
                public void execute() {
                    if (MockProtocolFactory.this.sendsAlive) {
                        super.execute();
                    }
                }
            };
        }

        @Override // org.fourthline.cling.protocol.ProtocolFactoryImpl, org.fourthline.cling.protocol.ProtocolFactory
        public SendingSearch createSendingSearch(UpnpHeader upnpHeader, int i7) {
            return new SendingSearch(getUpnpService(), upnpHeader, i7) { // from class: org.fourthline.cling.mock.MockUpnpService.MockProtocolFactory.2
                @Override // org.fourthline.cling.protocol.async.SendingSearch
                public int getBulkIntervalMilliseconds() {
                    return 0;
                }
            };
        }
    }

    public MockUpnpService() {
        this(false, new MockUpnpServiceConfiguration(false, false));
    }

    public ProtocolFactory createProtocolFactory(UpnpService upnpService, boolean z6) {
        return new MockProtocolFactory(upnpService, z6);
    }

    public MockRouter createRouter() {
        return new MockRouter(getConfiguration(), getProtocolFactory());
    }

    @Override // org.fourthline.cling.UpnpService
    public UpnpServiceConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // org.fourthline.cling.UpnpService
    public ControlPoint getControlPoint() {
        return this.controlPoint;
    }

    @Override // org.fourthline.cling.UpnpService
    public ProtocolFactory getProtocolFactory() {
        return this.protocolFactory;
    }

    @Override // org.fourthline.cling.UpnpService
    public Registry getRegistry() {
        return this.registry;
    }

    @Override // org.fourthline.cling.UpnpService
    public void shutdown() {
        getRegistry().shutdown();
        getConfiguration().shutdown();
    }

    public MockUpnpService(MockUpnpServiceConfiguration mockUpnpServiceConfiguration) {
        this(false, mockUpnpServiceConfiguration);
    }

    @Override // org.fourthline.cling.UpnpService
    public MockRouter getRouter() {
        return this.router;
    }

    public MockUpnpService(boolean z6, boolean z7) {
        this(z6, new MockUpnpServiceConfiguration(z7, false));
    }

    public MockUpnpService(boolean z6, boolean z7, boolean z8) {
        this(z6, new MockUpnpServiceConfiguration(z7, z8));
    }

    public MockUpnpService(boolean z6, final MockUpnpServiceConfiguration mockUpnpServiceConfiguration) {
        this.configuration = mockUpnpServiceConfiguration;
        ProtocolFactory protocolFactoryCreateProtocolFactory = createProtocolFactory(this, z6);
        this.protocolFactory = protocolFactoryCreateProtocolFactory;
        RegistryImpl registryImpl = new RegistryImpl(this) { // from class: org.fourthline.cling.mock.MockUpnpService.1
            @Override // org.fourthline.cling.registry.RegistryImpl
            public RegistryMaintainer createRegistryMaintainer() {
                if (mockUpnpServiceConfiguration.isMaintainsRegistry()) {
                    return super.createRegistryMaintainer();
                }
                return null;
            }
        };
        this.registry = registryImpl;
        this.networkAddressFactory = mockUpnpServiceConfiguration.createNetworkAddressFactory();
        this.router = createRouter();
        this.controlPoint = new ControlPointImpl(mockUpnpServiceConfiguration, protocolFactoryCreateProtocolFactory, registryImpl);
    }
}
