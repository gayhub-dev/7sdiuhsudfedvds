package p166u3;

import java.net.InetSocketAddress;
import java.util.Iterator;
import p159t3.AbstractC1907f;
import sun.misc.Service;
import sun.misc.ServiceConfigurationError;

/* compiled from: HttpServerProvider.java */
/* renamed from: u3.b */
/* loaded from: classes.dex */
public abstract class AbstractC1976b {

    /* renamed from: a */
    public static final Object f5775a = new Object();

    /* renamed from: b */
    public static AbstractC1976b f5776b;

    public AbstractC1976b() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new RuntimePermission("httpServerProvider"));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: sun.misc.ServiceConfigurationError */
    /* renamed from: b */
    public static boolean m2300b() throws ServiceConfigurationError {
        Iterator itProviders = Service.providers(AbstractC1976b.class, ClassLoader.getSystemClassLoader());
        while (itProviders.hasNext()) {
            try {
                f5776b = (AbstractC1976b) itProviders.next();
                return true;
            } catch (ServiceConfigurationError e7) {
                if (!(e7.getCause() instanceof SecurityException)) {
                    throw e7;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: sun.misc.ServiceConfigurationError */
    /* renamed from: c */
    public static boolean m2301c() throws ServiceConfigurationError {
        String property = System.getProperty("com.sun.net.httpserver.HttpServerProvider");
        if (property == null) {
            return false;
        }
        try {
            f5776b = (AbstractC1976b) Class.forName(property, true, ClassLoader.getSystemClassLoader()).newInstance();
            return true;
        } catch (ClassNotFoundException e7) {
            throw new ServiceConfigurationError(e7);
        } catch (IllegalAccessException e8) {
            throw new ServiceConfigurationError(e8);
        } catch (InstantiationException e9) {
            throw new ServiceConfigurationError(e9);
        } catch (SecurityException e10) {
            throw new ServiceConfigurationError(e10);
        }
    }

    /* renamed from: a */
    public abstract AbstractC1907f mo1976a(InetSocketAddress inetSocketAddress, int i7);
}
