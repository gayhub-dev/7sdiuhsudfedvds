package p196y5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p161t5.C1917i;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import p186x2.C2074b;

/* compiled from: SslContextFactory.java */
/* renamed from: y5.a */
/* loaded from: classes.dex */
public class C2133a extends AbstractC1980a {

    /* renamed from: s */
    public static final TrustManager[] f6262s = {new a()};

    /* renamed from: t */
    public static final InterfaceC2016c f6263t;

    /* renamed from: u */
    public static final String f6264u;

    /* renamed from: v */
    public static final String f6265v;

    /* renamed from: j */
    public InputStream f6271j;

    /* renamed from: l */
    public InputStream f6273l;

    /* renamed from: q */
    public SSLContext f6278q;

    /* renamed from: e */
    public final Set<String> f6266e = new LinkedHashSet();

    /* renamed from: f */
    public Set<String> f6267f = new LinkedHashSet();

    /* renamed from: g */
    public final Set<String> f6268g = new LinkedHashSet();

    /* renamed from: h */
    public Set<String> f6269h = new LinkedHashSet();

    /* renamed from: i */
    public String f6270i = "JKS";

    /* renamed from: k */
    public String f6272k = "JKS";

    /* renamed from: m */
    public String f6274m = "TLS";

    /* renamed from: n */
    public String f6275n = f6264u;

    /* renamed from: o */
    public String f6276o = f6265v;

    /* renamed from: p */
    public boolean f6277p = true;

    /* renamed from: r */
    public boolean f6279r = true;

    /* compiled from: SslContextFactory.java */
    /* renamed from: y5.a$a */
    public static class a implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f6263t = C2015b.m2349a(C2133a.class.getName());
        f6264u = Security.getProperty("ssl.KeyManagerFactory.algorithm") == null ? "SunX509" : Security.getProperty("ssl.KeyManagerFactory.algorithm");
        f6265v = Security.getProperty("ssl.TrustManagerFactory.algorithm") != null ? Security.getProperty("ssl.TrustManagerFactory.algorithm") : "SunX509";
        System.getProperty("user.home");
        String str = File.separator;
    }

    /* renamed from: G */
    public void m2563G(SSLEngine sSLEngine) {
        sSLEngine.setEnabledCipherSuites(m2564H(sSLEngine.getEnabledCipherSuites(), sSLEngine.getSupportedCipherSuites()));
        sSLEngine.setEnabledProtocols(m2565J(sSLEngine.getEnabledProtocols(), sSLEngine.getSupportedProtocols()));
    }

    /* renamed from: H */
    public String[] m2564H(String[] strArr, String[] strArr2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (this.f6269h.isEmpty()) {
            linkedHashSet.addAll(Arrays.asList(strArr));
        } else {
            for (String str : this.f6269h) {
                if (Arrays.asList(strArr2).contains(str)) {
                    linkedHashSet.add(str);
                }
            }
        }
        Set<String> set = this.f6268g;
        if (set != null) {
            linkedHashSet.removeAll(set);
        }
        return (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
    }

    /* renamed from: J */
    public String[] m2565J(String[] strArr, String[] strArr2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (this.f6267f.isEmpty()) {
            linkedHashSet.addAll(Arrays.asList(strArr));
        } else {
            for (String str : this.f6267f) {
                if (Arrays.asList(strArr2).contains(str)) {
                    linkedHashSet.add(str);
                }
            }
        }
        Set<String> set = this.f6266e;
        if (set != null) {
            linkedHashSet.removeAll(set);
        }
        return (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
    }

    @Override // p168u5.AbstractC1980a
    public void doStart() throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyManagementException, KeyStoreException {
        KeyManager[] keyManagers;
        TrustManager[] trustManagers;
        TrustManager[] trustManagerArr;
        SSLContext sSLContext = this.f6278q;
        if (sSLContext == null) {
            InputStream inputStream = this.f6271j;
            if (inputStream == null && this.f6273l == null) {
                if (this.f6279r) {
                    f6263t.mo2351a("No keystore or trust store configured.  ACCEPTING UNTRUSTED CERTIFICATES!!!!!", new Object[0]);
                    trustManagerArr = f6262s;
                } else {
                    trustManagerArr = null;
                }
                SSLContext sSLContext2 = SSLContext.getInstance(this.f6274m);
                this.f6278q = sSLContext2;
                sSLContext2.init(null, trustManagerArr, null);
                return;
            }
            if (sSLContext == null) {
                if (inputStream == null) {
                    throw new IllegalStateException("SSL doesn't have a valid keystore");
                }
                if (this.f6273l == null) {
                    this.f6273l = inputStream;
                    this.f6272k = this.f6270i;
                    this.f6276o = this.f6275n;
                }
                if (inputStream == this.f6273l) {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        InputStream inputStream2 = this.f6271j;
                        byte[] bArr = new byte[C1917i.f5645b];
                        while (true) {
                            int i7 = inputStream2.read(bArr, 0, C1917i.f5645b);
                            if (i7 < 0) {
                                break;
                            } else {
                                byteArrayOutputStream.write(bArr, 0, i7);
                            }
                        }
                        this.f6271j.close();
                        this.f6271j = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        this.f6273l = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    } catch (Exception e7) {
                        throw new IllegalStateException(e7);
                    }
                }
            }
            KeyStore keyStoreM2491n = C2074b.m2491n(this.f6271j, null, this.f6270i, null, null);
            KeyStore keyStoreM2491n2 = C2074b.m2491n(this.f6273l, null, this.f6272k, null, null);
            C2074b.m2498u(null);
            if (keyStoreM2491n != null) {
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(this.f6275n);
                keyManagerFactory.init(keyStoreM2491n, null);
                keyManagers = keyManagerFactory.getKeyManagers();
            } else {
                keyManagers = null;
            }
            if (keyStoreM2491n2 != null) {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(this.f6276o);
                trustManagerFactory.init(keyStoreM2491n2);
                trustManagers = trustManagerFactory.getTrustManagers();
            } else {
                trustManagers = null;
            }
            SSLContext sSLContext3 = SSLContext.getInstance(this.f6274m);
            this.f6278q = sSLContext3;
            sSLContext3.init(keyManagers, trustManagers, null);
            SSLEngine sSLEngineCreateSSLEngine = this.f6278q.createSSLEngine();
            m2563G(sSLEngineCreateSSLEngine);
            InterfaceC2016c interfaceC2016c = f6263t;
            interfaceC2016c.mo2357h("Enabled Protocols {} of {}", Arrays.asList(sSLEngineCreateSSLEngine.getEnabledProtocols()), Arrays.asList(sSLEngineCreateSSLEngine.getSupportedProtocols()));
            if (interfaceC2016c.mo2353d()) {
                interfaceC2016c.mo2351a("Enabled Ciphers   {} of {}", Arrays.asList(sSLEngineCreateSSLEngine.getEnabledCipherSuites()), Arrays.asList(sSLEngineCreateSSLEngine.getSupportedCipherSuites()));
            }
        }
    }

    public String toString() {
        return String.format("%s@%x(%s,%s)", C2133a.class.getSimpleName(), Integer.valueOf(hashCode()), null, null);
    }
}
