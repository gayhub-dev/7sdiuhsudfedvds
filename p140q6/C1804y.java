package p140q6;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p159t3.C1903b;

/* compiled from: UnmodifiableHeaders.java */
/* renamed from: q6.y */
/* loaded from: classes.dex */
public class C1804y extends C1903b {

    /* renamed from: f */
    public C1903b f5210f;

    public C1804y(C1903b c1903b) {
        this.f5210f = c1903b;
    }

    @Override // p159t3.C1903b
    /* renamed from: a */
    public void mo2014a(String str, String str2) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override // p159t3.C1903b
    /* renamed from: b */
    public List<String> get(Object obj) {
        return this.f5210f.get(obj);
    }

    @Override // p159t3.C1903b
    /* renamed from: c */
    public String mo2016c(String str) {
        return this.f5210f.mo2016c(str);
    }

    @Override // p159t3.C1903b, java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override // p159t3.C1903b, java.util.Map
    public boolean containsKey(Object obj) {
        return this.f5210f.containsKey(obj);
    }

    @Override // p159t3.C1903b, java.util.Map
    public boolean containsValue(Object obj) {
        return this.f5210f.containsValue(obj);
    }

    @Override // p159t3.C1903b, java.util.Map
    /* renamed from: e */
    public List<String> put(String str, List<String> list) {
        return this.f5210f.put(str, list);
    }

    @Override // p159t3.C1903b, java.util.Map
    public Set<Map.Entry<String, List<String>>> entrySet() {
        return Collections.unmodifiableSet(this.f5210f.entrySet());
    }

    @Override // p159t3.C1903b, java.util.Map
    public boolean equals(Object obj) {
        return this.f5210f.equals(obj);
    }

    @Override // p159t3.C1903b
    /* renamed from: f */
    public List<String> remove(Object obj) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override // p159t3.C1903b
    /* renamed from: g */
    public void mo2019g(String str, String str2) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override // p159t3.C1903b, java.util.Map
    public List<String> get(Object obj) {
        return this.f5210f.get(obj);
    }

    @Override // p159t3.C1903b, java.util.Map
    public int hashCode() {
        return this.f5210f.hashCode();
    }

    @Override // p159t3.C1903b, java.util.Map
    public boolean isEmpty() {
        return this.f5210f.isEmpty();
    }

    @Override // p159t3.C1903b, java.util.Map
    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.f5210f.keySet());
    }

    @Override // p159t3.C1903b, java.util.Map
    public void putAll(Map<? extends String, ? extends List<String>> map) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override // p159t3.C1903b, java.util.Map
    public List<String> remove(Object obj) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override // p159t3.C1903b, java.util.Map
    public int size() {
        return this.f5210f.size();
    }

    @Override // p159t3.C1903b, java.util.Map
    public Collection<List<String>> values() {
        return Collections.unmodifiableCollection(this.f5210f.values());
    }
}
