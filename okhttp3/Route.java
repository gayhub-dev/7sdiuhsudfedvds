package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;
import javax.annotation.Nullable;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class Route {
    public final Address address;
    public final InetSocketAddress inetSocketAddress;
    public final Proxy proxy;

    public Route(Address address, Proxy proxy, InetSocketAddress inetSocketAddress) {
        Objects.requireNonNull(address, "address == null");
        Objects.requireNonNull(proxy, "proxy == null");
        Objects.requireNonNull(inetSocketAddress, "inetSocketAddress == null");
        this.address = address;
        this.proxy = proxy;
        this.inetSocketAddress = inetSocketAddress;
    }

    public Address address() {
        return this.address;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Route) {
            Route route = (Route) obj;
            if (route.address.equals(this.address) && route.proxy.equals(this.proxy) && route.inetSocketAddress.equals(this.inetSocketAddress)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.inetSocketAddress.hashCode() + ((this.proxy.hashCode() + ((this.address.hashCode() + 527) * 31)) * 31);
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public boolean requiresTunnel() {
        return this.address.sslSocketFactory != null && this.proxy.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress socketAddress() {
        return this.inetSocketAddress;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Route{");
        sbM112a.append(this.inetSocketAddress);
        sbM112a.append("}");
        return sbM112a.toString();
    }
}
