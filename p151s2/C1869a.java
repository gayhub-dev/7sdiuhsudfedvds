package p151s2;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import p186x2.C2073a;

/* compiled from: Utils.java */
/* renamed from: s2.a */
/* loaded from: classes.dex */
public class C1869a {

    /* renamed from: a */
    public static final String f5449a = Build.MANUFACTURER;

    /* renamed from: a */
    public static String m2141a() throws SocketException {
        String hostAddress = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && !inetAddressNextElement.isLinkLocalAddress()) {
                        C2073a.m2459d("ipTest Public IP Address: " + inetAddressNextElement.getHostAddress());
                        hostAddress = inetAddressNextElement.getHostAddress();
                        if (!TextUtils.isEmpty(hostAddress)) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        return hostAddress;
    }

    /* renamed from: b */
    public static String m2142b(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        if (wifiManager == null || !wifiManager.isWifiEnabled()) {
            return !TextUtils.isEmpty(m2141a()) ? m2141a() : "";
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return !TextUtils.isEmpty(m2141a()) ? m2141a() : "<unknown>";
        }
        int ipAddress = connectionInfo.getIpAddress();
        if (ipAddress == 0) {
            return !TextUtils.isEmpty(m2141a()) ? m2141a() : "<unknown>";
        }
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }
}
