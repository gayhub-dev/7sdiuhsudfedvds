package p182w5;

import android.arch.lifecycle.C0063n;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Resource.java */
/* renamed from: w5.e */
/* loaded from: classes.dex */
public abstract class AbstractC2038e {

    /* renamed from: a */
    public static final InterfaceC2016c f5949a;

    /* renamed from: b */
    public static boolean f5950b;

    static {
        Properties properties = C2015b.f5863a;
        f5949a = C2015b.m2349a(AbstractC2038e.class.getName());
        f5950b = true;
    }

    /* renamed from: d */
    public static AbstractC2038e m2398d(String str) throws IOException {
        boolean z6 = f5950b;
        try {
            return m2399e(new URL(str));
        } catch (MalformedURLException e7) {
            if (str.startsWith("ftp:") || str.startsWith("file:") || str.startsWith("jar:")) {
                f5949a.mo2356g(C0063n.m88a("Bad Resource: ", str), new Object[0]);
                throw e7;
            }
            try {
                if (str.startsWith("./")) {
                    str = str.substring(2);
                }
                File canonicalFile = new File(str).getCanonicalFile();
                URL url = canonicalFile.toURI().toURL();
                URLConnection uRLConnectionOpenConnection = url.openConnection();
                uRLConnectionOpenConnection.setUseCaches(z6);
                return new C2035b(url, uRLConnectionOpenConnection, canonicalFile);
            } catch (Exception e8) {
                f5949a.mo2355f("EXCEPTION ", e8);
                throw e7;
            }
        }
    }

    /* renamed from: e */
    public static AbstractC2038e m2399e(URL url) {
        boolean z6 = f5950b;
        if (url == null) {
            return null;
        }
        String externalForm = url.toExternalForm();
        if (externalForm.startsWith("file:")) {
            try {
                return new C2035b(url);
            } catch (Exception e7) {
                f5949a.mo2355f("EXCEPTION ", e7);
                return new C2034a(url, e7.toString());
            }
        }
        if (externalForm.startsWith("jar:file:")) {
            return new C2036c(url, z6);
        }
        if (externalForm.startsWith("jar:")) {
            return new C2037d(url, z6);
        }
        C2039f c2039f = new C2039f(url, null);
        c2039f.f5956g = z6;
        return c2039f;
    }

    /* renamed from: a */
    public abstract boolean mo2392a();

    /* renamed from: b */
    public abstract InputStream mo2393b();

    /* renamed from: c */
    public abstract long mo2394c();

    /* renamed from: f */
    public abstract void mo2395f();

    public void finalize() {
        mo2395f();
    }
}
