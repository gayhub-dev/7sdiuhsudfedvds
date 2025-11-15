package p182w5;

import android.support.constraint.C0072a;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.fourthline.cling.model.ServiceReference;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: JarFileResource.java */
/* renamed from: w5.c */
/* loaded from: classes.dex */
public class C2036c extends C2037d {

    /* renamed from: r */
    public static final InterfaceC2016c f5938r;

    /* renamed from: k */
    public JarFile f5939k;

    /* renamed from: l */
    public File f5940l;

    /* renamed from: m */
    public JarEntry f5941m;

    /* renamed from: n */
    public boolean f5942n;

    /* renamed from: o */
    public String f5943o;

    /* renamed from: p */
    public String f5944p;

    /* renamed from: q */
    public boolean f5945q;

    static {
        Properties properties = C2015b.f5863a;
        f5938r = C2015b.m2349a(C2036c.class.getName());
    }

    public C2036c(URL url, boolean z6) {
        super(url, z6);
    }

    @Override // p182w5.C2037d, p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: a */
    public boolean mo2392a() throws IOException {
        boolean z6 = true;
        if (this.f5945q) {
            return true;
        }
        if (this.f5953d.endsWith("!/")) {
            try {
                return AbstractC2038e.m2398d(this.f5953d.substring(4, r0.length() - 2)).mo2392a();
            } catch (Exception e7) {
                f5938r.mo2360k(e7);
                return false;
            }
        }
        boolean zMo2396g = mo2396g();
        if (this.f5943o != null && this.f5944p == null) {
            this.f5942n = zMo2396g;
            return true;
        }
        JarFile jarFile = null;
        if (zMo2396g) {
            jarFile = this.f5939k;
        } else {
            try {
                JarURLConnection jarURLConnection = (JarURLConnection) new URL(this.f5943o).openConnection();
                jarURLConnection.setUseCaches(this.f5956g);
                jarFile = jarURLConnection.getJarFile();
            } catch (Exception e8) {
                f5938r.mo2360k(e8);
            }
        }
        if (jarFile != null && this.f5941m == null && !this.f5942n) {
            Enumeration<JarEntry> enumerationEntries = jarFile.entries();
            while (true) {
                if (!enumerationEntries.hasMoreElements()) {
                    break;
                }
                JarEntry jarEntryNextElement = enumerationEntries.nextElement();
                String strReplace = jarEntryNextElement.getName().replace('\\', '/');
                if (!strReplace.equals(this.f5944p)) {
                    if (!this.f5944p.endsWith(ServiceReference.DELIMITER)) {
                        if (strReplace.startsWith(this.f5944p) && strReplace.length() > this.f5944p.length() && strReplace.charAt(this.f5944p.length()) == '/') {
                            this.f5942n = true;
                            break;
                        }
                    } else if (strReplace.startsWith(this.f5944p)) {
                        this.f5942n = true;
                        break;
                    }
                } else {
                    this.f5941m = jarEntryNextElement;
                    this.f5942n = this.f5944p.endsWith(ServiceReference.DELIMITER);
                    break;
                }
            }
            if (this.f5942n && !this.f5953d.endsWith(ServiceReference.DELIMITER)) {
                this.f5953d = C0072a.m92a(new StringBuilder(), this.f5953d, ServiceReference.DELIMITER);
                try {
                    this.f5952c = new URL(this.f5953d);
                } catch (MalformedURLException e9) {
                    f5938r.mo2358i(e9);
                }
            }
        }
        if (!this.f5942n && this.f5941m == null) {
            z6 = false;
        }
        this.f5945q = z6;
        return z6;
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: c */
    public long mo2394c() {
        JarEntry jarEntry;
        if (!mo2396g() || this.f5940l == null) {
            return -1L;
        }
        return (!mo2392a() || (jarEntry = this.f5941m) == null) ? this.f5940l.lastModified() : jarEntry.getTime();
    }

    @Override // p182w5.C2037d, p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: f */
    public synchronized void mo2395f() {
        this.f5941m = null;
        this.f5940l = null;
        if (this.f5956g || this.f5939k == null) {
            this.f5939k = null;
            super.mo2395f();
        } else {
            try {
                f5938r.mo2351a("Closing JarFile " + this.f5939k.getName(), new Object[0]);
                this.f5939k.close();
            } catch (IOException e7) {
                f5938r.mo2360k(e7);
            }
            this.f5939k = null;
            super.mo2395f();
        }
    }

    @Override // p182w5.C2037d, p182w5.C2039f
    /* renamed from: g */
    public boolean mo2396g() {
        try {
            super.mo2396g();
            return this.f5939k != null;
        } finally {
            if (this.f5947i == null) {
                this.f5941m = null;
                this.f5940l = null;
                this.f5939k = null;
            }
        }
    }

    @Override // p182w5.C2037d
    /* renamed from: h */
    public synchronized void mo2397h() {
        this.f5947i = (JarURLConnection) this.f5954e;
        this.f5941m = null;
        this.f5940l = null;
        this.f5939k = null;
        int iIndexOf = this.f5953d.indexOf("!/") + 2;
        this.f5943o = this.f5953d.substring(0, iIndexOf);
        String strSubstring = this.f5953d.substring(iIndexOf);
        this.f5944p = strSubstring;
        if (strSubstring.length() == 0) {
            this.f5944p = null;
        }
        this.f5939k = this.f5947i.getJarFile();
        this.f5940l = new File(this.f5939k.getName());
    }
}
