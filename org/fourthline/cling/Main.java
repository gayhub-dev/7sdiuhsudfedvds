package org.fourthline.cling;

import java.io.PrintStream;
import org.fourthline.cling.model.message.header.STAllHeader;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.RemoteDevice;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.registry.RegistryListener;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Main {
    public static void main(String[] strArr) throws InterruptedException {
        RegistryListener registryListener = new RegistryListener() { // from class: org.fourthline.cling.Main.1
            @Override // org.fourthline.cling.registry.RegistryListener
            public void afterShutdown() {
                System.out.println("Shutdown of registry complete!");
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void beforeShutdown(Registry registry) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Before shutdown, the registry has devices: ");
                sbM112a.append(registry.getDevices().size());
                printStream.println(sbM112a.toString());
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void localDeviceAdded(Registry registry, LocalDevice localDevice) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Local device added: ");
                sbM112a.append(localDevice.getDisplayString());
                printStream.println(sbM112a.toString());
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void localDeviceRemoved(Registry registry, LocalDevice localDevice) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Local device removed: ");
                sbM112a.append(localDevice.getDisplayString());
                printStream.println(sbM112a.toString());
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void remoteDeviceAdded(Registry registry, RemoteDevice remoteDevice) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Remote device available: ");
                sbM112a.append(remoteDevice.getDisplayString());
                printStream.println(sbM112a.toString());
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void remoteDeviceDiscoveryFailed(Registry registry, RemoteDevice remoteDevice, Exception exc) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Discovery failed: ");
                sbM112a.append(remoteDevice.getDisplayString());
                sbM112a.append(" => ");
                sbM112a.append(exc);
                printStream.println(sbM112a.toString());
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void remoteDeviceDiscoveryStarted(Registry registry, RemoteDevice remoteDevice) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Discovery started: ");
                sbM112a.append(remoteDevice.getDisplayString());
                printStream.println(sbM112a.toString());
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void remoteDeviceRemoved(Registry registry, RemoteDevice remoteDevice) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Remote device removed: ");
                sbM112a.append(remoteDevice.getDisplayString());
                printStream.println(sbM112a.toString());
            }

            @Override // org.fourthline.cling.registry.RegistryListener
            public void remoteDeviceUpdated(Registry registry, RemoteDevice remoteDevice) {
                PrintStream printStream = System.out;
                StringBuilder sbM112a = C0413b.m112a("Remote device updated: ");
                sbM112a.append(remoteDevice.getDisplayString());
                printStream.println(sbM112a.toString());
            }
        };
        System.out.println("Starting Cling...");
        UpnpServiceImpl upnpServiceImpl = new UpnpServiceImpl(registryListener);
        System.out.println("Sending SEARCH message to all devices...");
        upnpServiceImpl.getControlPoint().search(new STAllHeader());
        System.out.println("Waiting 10 seconds before shutting down...");
        Thread.sleep(10000L);
        System.out.println("Stopping Cling...");
        upnpServiceImpl.shutdown();
    }
}
