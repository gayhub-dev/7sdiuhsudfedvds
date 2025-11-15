package p182w5;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

/* compiled from: BadResource.java */
/* renamed from: w5.a */
/* loaded from: classes.dex */
public class C2034a extends C2039f {

    /* renamed from: i */
    public String f5935i;

    public C2034a(URL url, String str) {
        super(url, null);
        this.f5935i = null;
        this.f5935i = str;
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: a */
    public boolean mo2392a() {
        return false;
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: b */
    public InputStream mo2393b() throws FileNotFoundException {
        throw new FileNotFoundException(this.f5935i);
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: c */
    public long mo2394c() {
        return -1L;
    }

    @Override // p182w5.C2039f
    public String toString() {
        return this.f5953d + "; BadResource=" + this.f5935i;
    }
}
