package okhttp3.internal.platform;

import android.arch.lifecycle.C0063n;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Platform {
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static final Platform PLATFORM = findPlatform();
    private static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());

    public static List<String> alpnProtocolNames(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            Protocol protocol = list.get(i7);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    public static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            Protocol protocol = list.get(i7);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }

    private static Platform findPlatform() {
        ConscryptPlatform conscryptPlatformBuildIfSupported;
        Platform platformBuildIfSupported = AndroidPlatform.buildIfSupported();
        if (platformBuildIfSupported != null) {
            return platformBuildIfSupported;
        }
        if (isConscryptPreferred() && (conscryptPlatformBuildIfSupported = ConscryptPlatform.buildIfSupported()) != null) {
            return conscryptPlatformBuildIfSupported;
        }
        Jdk9Platform jdk9PlatformBuildIfSupported = Jdk9Platform.buildIfSupported();
        if (jdk9PlatformBuildIfSupported != null) {
            return jdk9PlatformBuildIfSupported;
        }
        Platform platformBuildIfSupported2 = JdkWithJettyBootPlatform.buildIfSupported();
        return platformBuildIfSupported2 != null ? platformBuildIfSupported2 : new Platform();
    }

    public static Platform get() {
        return PLATFORM;
    }

    public static boolean isConscryptPreferred() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    @Nullable
    public static <T> T readFieldOrNull(Object obj, Class<T> cls, String str) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Object fieldOrNull;
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null && cls.isInstance(obj2)) {
                    return cls.cast(obj2);
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (NoSuchFieldException unused2) {
            }
        }
        if (str.equals("delegate") || (fieldOrNull = readFieldOrNull(obj, Object.class, "delegate")) == null) {
            return null;
        }
        return (T) readFieldOrNull(fieldOrNull, cls, str);
    }

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager x509TrustManager) {
        return new BasicCertificateChainCleaner(buildTrustRootIndex(x509TrustManager));
    }

    public TrustRootIndex buildTrustRootIndex(X509TrustManager x509TrustManager) {
        return new BasicTrustRootIndex(x509TrustManager.getAcceptedIssuers());
    }

    public void configureSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, @Nullable String str, List<Protocol> list) {
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i7) throws IOException {
        socket.connect(inetSocketAddress, i7);
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public SSLContext getSSLContext() {
        if ("1.7".equals(System.getProperty("java.specification.version"))) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e7) {
            throw new IllegalStateException("No TLS provider", e7);
        }
    }

    @Nullable
    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public Object getStackTraceForCloseable(String str) {
        if (logger.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public boolean isCleartextTrafficPermitted(String str) {
        return true;
    }

    public void log(int i7, String str, @Nullable Throwable th) {
        logger.log(i7 == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public void logCloseableLeak(String str, Object obj) {
        if (obj == null) {
            str = C0063n.m88a(str, " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        log(5, str, (Throwable) obj);
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    @Nullable
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Object fieldOrNull = readFieldOrNull(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
            if (fieldOrNull == null) {
                return null;
            }
            return (X509TrustManager) readFieldOrNull(fieldOrNull, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public CertificateChainCleaner buildCertificateChainCleaner(SSLSocketFactory sSLSocketFactory) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        X509TrustManager x509TrustManagerTrustManager = trustManager(sSLSocketFactory);
        if (x509TrustManagerTrustManager != null) {
            return buildCertificateChainCleaner(x509TrustManagerTrustManager);
        }
        StringBuilder sbM112a = C0413b.m112a("Unable to extract the trust manager on ");
        sbM112a.append(get());
        sbM112a.append(", sslSocketFactory is ");
        sbM112a.append(sSLSocketFactory.getClass());
        throw new IllegalStateException(sbM112a.toString());
    }
}
