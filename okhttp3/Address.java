package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class Address {

    @Nullable
    public final CertificatePinner certificatePinner;
    public final List<ConnectionSpec> connectionSpecs;
    public final Dns dns;

    @Nullable
    public final HostnameVerifier hostnameVerifier;
    public final List<Protocol> protocols;

    @Nullable
    public final Proxy proxy;
    public final Authenticator proxyAuthenticator;
    public final ProxySelector proxySelector;
    public final SocketFactory socketFactory;

    @Nullable
    public final SSLSocketFactory sslSocketFactory;
    public final HttpUrl url;

    public Address(String str, int i7, Dns dns, SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable CertificatePinner certificatePinner, Authenticator authenticator, @Nullable Proxy proxy, List<Protocol> list, List<ConnectionSpec> list2, ProxySelector proxySelector) {
        this.url = new HttpUrl.Builder().scheme(sSLSocketFactory != null ? "https" : "http").host(str).port(i7).build();
        Objects.requireNonNull(dns, "dns == null");
        this.dns = dns;
        Objects.requireNonNull(socketFactory, "socketFactory == null");
        this.socketFactory = socketFactory;
        Objects.requireNonNull(authenticator, "proxyAuthenticator == null");
        this.proxyAuthenticator = authenticator;
        Objects.requireNonNull(list, "protocols == null");
        this.protocols = Util.immutableList(list);
        Objects.requireNonNull(list2, "connectionSpecs == null");
        this.connectionSpecs = Util.immutableList(list2);
        Objects.requireNonNull(proxySelector, "proxySelector == null");
        this.proxySelector = proxySelector;
        this.proxy = proxy;
        this.sslSocketFactory = sSLSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
        this.certificatePinner = certificatePinner;
    }

    @Nullable
    public CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    public Dns dns() {
        return this.dns;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Address) {
            Address address = (Address) obj;
            if (this.url.equals(address.url) && equalsNonHost(address)) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsNonHost(Address address) {
        return this.dns.equals(address.dns) && this.proxyAuthenticator.equals(address.proxyAuthenticator) && this.protocols.equals(address.protocols) && this.connectionSpecs.equals(address.connectionSpecs) && this.proxySelector.equals(address.proxySelector) && Util.equal(this.proxy, address.proxy) && Util.equal(this.sslSocketFactory, address.sslSocketFactory) && Util.equal(this.hostnameVerifier, address.hostnameVerifier) && Util.equal(this.certificatePinner, address.certificatePinner) && url().port() == address.url().port();
    }

    public int hashCode() {
        int iHashCode = (this.proxySelector.hashCode() + ((this.connectionSpecs.hashCode() + ((this.protocols.hashCode() + ((this.proxyAuthenticator.hashCode() + ((this.dns.hashCode() + ((this.url.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        Proxy proxy = this.proxy;
        int iHashCode2 = (iHashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.sslSocketFactory;
        int iHashCode3 = (iHashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.hostnameVerifier;
        int iHashCode4 = (iHashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        CertificatePinner certificatePinner = this.certificatePinner;
        return iHashCode4 + (certificatePinner != null ? certificatePinner.hashCode() : 0);
    }

    @Nullable
    public HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public List<Protocol> protocols() {
        return this.protocols;
    }

    @Nullable
    public Proxy proxy() {
        return this.proxy;
    }

    public Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public SocketFactory socketFactory() {
        return this.socketFactory;
    }

    @Nullable
    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Address{");
        sbM112a.append(this.url.host());
        sbM112a.append(":");
        sbM112a.append(this.url.port());
        if (this.proxy != null) {
            sbM112a.append(", proxy=");
            sbM112a.append(this.proxy);
        } else {
            sbM112a.append(", proxySelector=");
            sbM112a.append(this.proxySelector);
        }
        sbM112a.append("}");
        return sbM112a.toString();
    }

    public HttpUrl url() {
        return this.url;
    }
}
