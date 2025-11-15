package p182w5;

import android.support.constraint.C0072a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.util.Properties;
import org.fourthline.cling.model.ServiceReference;
import p161t5.C1928t;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: FileResource.java */
/* renamed from: w5.b */
/* loaded from: classes.dex */
public class C2035b extends C2039f {

    /* renamed from: j */
    public static final InterfaceC2016c f5936j;

    /* renamed from: i */
    public File f5937i;

    static {
        Properties properties = C2015b.f5863a;
        f5936j = C2015b.m2349a(C2035b.class.getName());
    }

    public C2035b(URL url) throws URISyntaxException, IOException {
        super(url, null);
        try {
            this.f5937i = new File(new URI(url.toString()));
        } catch (URISyntaxException e7) {
            throw e7;
        } catch (Exception e8) {
            f5936j.mo2360k(e8);
            try {
                URI uri = new URI("file:" + C1928t.m2268k(url.toString().substring(5)));
                if (uri.getAuthority() == null) {
                    this.f5937i = new File(uri);
                } else {
                    this.f5937i = new File("//" + uri.getAuthority() + C1928t.m2267j(url.getFile()));
                }
            } catch (Exception e9) {
                f5936j.mo2360k(e9);
                mo2396g();
                Permission permission = this.f5954e.getPermission();
                this.f5937i = new File(permission == null ? url.getFile() : permission.getName());
            }
        }
        if (this.f5937i.isDirectory()) {
            if (this.f5953d.endsWith(ServiceReference.DELIMITER)) {
                return;
            }
            this.f5953d = C0072a.m92a(new StringBuilder(), this.f5953d, ServiceReference.DELIMITER);
        } else if (this.f5953d.endsWith(ServiceReference.DELIMITER)) {
            this.f5953d = this.f5953d.substring(0, r5.length() - 1);
        }
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: a */
    public boolean mo2392a() {
        return this.f5937i.exists();
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: b */
    public InputStream mo2393b() {
        return new FileInputStream(this.f5937i);
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: c */
    public long mo2394c() {
        return this.f5937i.lastModified();
    }

    @Override // p182w5.C2039f
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C2035b)) {
            return false;
        }
        Object obj2 = ((C2035b) obj).f5937i;
        File file = this.f5937i;
        if (obj2 != file) {
            return file != null && file.equals(obj2);
        }
        return true;
    }

    @Override // p182w5.C2039f
    public int hashCode() {
        File file = this.f5937i;
        return file == null ? super.hashCode() : file.hashCode();
    }

    public C2035b(URL url, URLConnection uRLConnection, File file) {
        super(url, uRLConnection);
        this.f5937i = file;
        if (!file.isDirectory() || this.f5953d.endsWith(ServiceReference.DELIMITER)) {
            return;
        }
        this.f5953d = C0072a.m92a(new StringBuilder(), this.f5953d, ServiceReference.DELIMITER);
    }
}
