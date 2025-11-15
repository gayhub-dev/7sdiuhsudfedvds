package p065h5;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import p065h5.C1101i;

/* compiled from: HttpFields.java */
/* renamed from: h5.k */
/* loaded from: classes.dex */
public class C1103k implements Enumeration<String> {

    /* renamed from: a */
    public C1101i.e f2305a;

    public C1103k(C1101i c1101i, C1101i.e eVar) {
        this.f2305a = eVar;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.f2305a != null;
    }

    @Override // java.util.Enumeration
    public String nextElement() {
        C1101i.e eVar = this.f2305a;
        if (eVar == null) {
            throw new NoSuchElementException();
        }
        this.f2305a = eVar.f2303c;
        return eVar.m1233a();
    }
}
