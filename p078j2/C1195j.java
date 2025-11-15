package p078j2;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import p086k2.C1231b;
import p186x2.C2073a;

/* compiled from: MyNetUtils.java */
/* renamed from: j2.j */
/* loaded from: classes.dex */
public class C1195j {
    /* renamed from: a */
    public static String m1417a() throws SocketException {
        if (!m1419c()) {
            return null;
        }
        if ("WIFI".equals(m1418b())) {
            int ipAddress = ((WifiManager) C1231b.f2761c.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getIpAddress();
            return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException e7) {
            C2073a.m2458c(e7);
            return null;
        }
    }

    /* renamed from: b */
    public static String m1418b() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) C1231b.f2761c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 9) {
                return "ETHERNET";
            }
            if (activeNetworkInfo.getType() == 0) {
                String subtypeName = activeNetworkInfo.getSubtypeName();
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        break;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        break;
                    case 13:
                        break;
                    default:
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                        }
                        break;
                }
                return "3G";
            }
        }
        return "";
    }

    /* renamed from: c */
    public static boolean m1419c() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) C1231b.f2761c.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }
}
