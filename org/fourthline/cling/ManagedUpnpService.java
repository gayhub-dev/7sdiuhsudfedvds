package org.fourthline.cling;

import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.registry.RegistryListener;
import org.fourthline.cling.registry.event.After;
import org.fourthline.cling.registry.event.Before;
import org.fourthline.cling.registry.event.FailedRemoteDeviceDiscovery;
import org.fourthline.cling.registry.event.LocalDeviceDiscovery;
import org.fourthline.cling.registry.event.Phase;
import org.fourthline.cling.registry.event.RegistryShutdown;
import org.fourthline.cling.registry.event.RemoteDeviceDiscovery;
import org.fourthline.cling.transport.DisableRouter;
import org.fourthline.cling.transport.EnableRouter;
import org.fourthline.cling.transport.Router;
import p188x4.InterfaceC2081a;
import p195y4.InterfaceC2132a;
import p202z4.AbstractC2156a;

/* loaded from: classes.dex */
public class ManagedUpnpService implements UpnpService {
    private static final Logger log = Logger.getLogger(ManagedUpnpService.class.getName());
    public InterfaceC2132a<UpnpServiceConfiguration> configuration;
    public InterfaceC2132a<ControlPoint> controlPointInstance;
    public InterfaceC2081a<DisableRouter> disableRouterEvent;
    public InterfaceC2081a<EnableRouter> enableRouterEvent;
    public InterfaceC2132a<ProtocolFactory> protocolFactoryInstance;
    public InterfaceC2132a<Registry> registryInstance;
    public RegistryListenerAdapter registryListenerAdapter;
    public InterfaceC2132a<Router> routerInstance;

    public static class RegistryListenerAdapter implements RegistryListener {
        public InterfaceC2081a<FailedRemoteDeviceDiscovery> failedRemoteDeviceDiscoveryEvent;
        public InterfaceC2081a<LocalDeviceDiscovery> localDeviceDiscoveryEvent;
        public InterfaceC2081a<RegistryShutdown> registryShutdownEvent;
        public InterfaceC2081a<RemoteDeviceDiscovery> remoteDeviceDiscoveryEvent;

        @Override // org.fourthline.cling.registry.RegistryListener
        public void afterShutdown() {
            this.registryShutdownEvent.m2504a(new AbstractC2156a<After>() { // from class: org.fourthline.cling.ManagedUpnpService.RegistryListenerAdapter.2
            }).m2505b(new RegistryShutdown());
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void beforeShutdown(Registry registry) {
            this.registryShutdownEvent.m2504a(new AbstractC2156a<Before>() { // from class: org.fourthline.cling.ManagedUpnpService.RegistryListenerAdapter.1
            }).m2505b(new RegistryShutdown());
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void localDeviceAdded(Registry registry, LocalDevice localDevice) {
            this.localDeviceDiscoveryEvent.m2504a(Phase.COMPLETE).m2505b(new LocalDeviceDiscovery(localDevice));
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void localDeviceRemoved(Registry registry, LocalDevice localDevice) {
            this.localDeviceDiscoveryEvent.m2504a(Phase.BYEBYE).m2505b(new LocalDeviceDiscovery(localDevice));
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void remoteDeviceAdded(Registry registry, RemoteDevice remoteDevice) {
            this.remoteDeviceDiscoveryEvent.m2504a(Phase.COMPLETE).m2505b(new RemoteDeviceDiscovery(remoteDevice));
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void remoteDeviceDiscoveryFailed(Registry registry, RemoteDevice remoteDevice, Exception exc) {
            this.failedRemoteDeviceDiscoveryEvent.m2505b(new FailedRemoteDeviceDiscovery(remoteDevice, exc));
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void remoteDeviceDiscoveryStarted(Registry registry, RemoteDevice remoteDevice) {
            this.remoteDeviceDiscoveryEvent.m2504a(Phase.ALIVE).m2505b(new RemoteDeviceDiscovery(remoteDevice));
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void remoteDeviceRemoved(Registry registry, RemoteDevice remoteDevice) {
            this.remoteDeviceDiscoveryEvent.m2504a(Phase.BYEBYE).m2505b(new RemoteDeviceDiscovery(remoteDevice));
        }

        @Override // org.fourthline.cling.registry.RegistryListener
        public void remoteDeviceUpdated(Registry registry, RemoteDevice remoteDevice) {
            this.remoteDeviceDiscoveryEvent.m2504a(Phase.UPDATED).m2505b(new RemoteDeviceDiscovery(remoteDevice));
        }
    }

    @Override // org.fourthline.cling.UpnpService
    public UpnpServiceConfiguration getConfiguration() {
        return this.configuration.get();
    }

    @Override // org.fourthline.cling.UpnpService
    public ControlPoint getControlPoint() {
        return this.controlPointInstance.get();
    }

    @Override // org.fourthline.cling.UpnpService
    public ProtocolFactory getProtocolFactory() {
        return this.protocolFactoryInstance.get();
    }

    @Override // org.fourthline.cling.UpnpService
    public Registry getRegistry() {
        return this.registryInstance.get();
    }

    @Override // org.fourthline.cling.UpnpService
    public Router getRouter() {
        return this.routerInstance.get();
    }

    @Override // org.fourthline.cling.UpnpService
    public void shutdown() {
        shutdown(null);
    }

    public void start(UpnpService.Start start) {
        Logger logger = log;
        logger.info(">>> Starting managed UPnP service...");
        getRegistry().addListener(this.registryListenerAdapter);
        this.enableRouterEvent.m2505b(new EnableRouter());
        logger.info("<<< Managed UPnP service started successfully");
    }

    public void shutdown(UpnpService.Shutdown shutdown) {
        Logger logger = log;
        logger.info(">>> Shutting down managed UPnP service...");
        getRegistry().shutdown();
        this.disableRouterEvent.m2505b(new DisableRouter());
        getConfiguration().shutdown();
        logger.info("<<< Managed UPnP service shutdown completed");
    }
}
