package p065h5;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import p065h5.C1101i;

/* compiled from: HttpFields.java */
/* renamed from: h5.l */
/* loaded from: classes.dex */
public class C1104l implements Enumeration<String> {

    /* renamed from: a */
    public C1101i.e f2306a;

    public C1104l(C1101i c1101i, C1101i.e eVar) {
        this.f2306a = eVar;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.f2306a != null;
    }

    @Override // java.util.Enumeration
    public String nextElement() {
        C1101i.e eVar = this.f2306a;
        if (eVar == null) {
            throw new NoSuchElementException();
        }
        this.f2306a = eVar.f2303c;
        return eVar.m1233a();
    }
}
