package okhttp3;

import android.arch.lifecycle.C0063n;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public interface Dns {
    public static final Dns SYSTEM = new Dns() { // from class: okhttp3.Dns.1
        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str == null) {
                throw new UnknownHostException("hostname == null");
            }
            try {
                return Arrays.asList(InetAddress.getAllByName(str));
            } catch (NullPointerException e7) {
                UnknownHostException unknownHostException = new UnknownHostException(C0063n.m88a("Broken system behaviour for dns lookup of ", str));
                unknownHostException.initCause(e7);
                throw unknownHostException;
            }
        }
    };

    List<InetAddress> lookup(String str);
}
