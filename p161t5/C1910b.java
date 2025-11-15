package p161t5;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AttributesMap.java */
/* renamed from: t5.b */
/* loaded from: classes.dex */
public class C1910b implements InterfaceC1909a {

    /* renamed from: e */
    public final Map<String, Object> f5612e = new HashMap();

    @Override // p161t5.InterfaceC1909a
    /* renamed from: a */
    public Object mo892a(String str) {
        return this.f5612e.get(str);
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: b */
    public void mo893b(String str, Object obj) {
        if (obj == null) {
            this.f5612e.remove(str);
        } else {
            this.f5612e.put(str, obj);
        }
    }

    /* renamed from: c */
    public Enumeration<String> m2206c() {
        return Collections.enumeration(this.f5612e.keySet());
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: e */
    public void mo894e(String str) {
        this.f5612e.remove(str);
    }

    public String toString() {
        return this.f5612e.toString();
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: v */
    public void mo896v() {
        this.f5612e.clear();
    }
}
