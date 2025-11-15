package p159t3;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: Headers.java */
/* renamed from: t3.b */
/* loaded from: classes.dex */
public class C1903b implements Map<String, List<String>> {

    /* renamed from: e */
    public HashMap<String, List<String>> f5610e = new HashMap<>(32);

    /* renamed from: a */
    public void mo2014a(String str, String str2) {
        String strM2202d = m2202d(str);
        List<String> linkedList = this.f5610e.get(strM2202d);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            this.f5610e.put(strM2202d, linkedList);
        }
        linkedList.add(str2);
    }

    @Override // java.util.Map
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public List<String> get(Object obj) {
        return this.f5610e.get(m2202d((String) obj));
    }

    /* renamed from: c */
    public String mo2016c(String str) {
        List<String> list = this.f5610e.get(m2202d(str));
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    @Override // java.util.Map
    public void clear() {
        this.f5610e.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        if (obj != null && (obj instanceof String)) {
            return this.f5610e.containsKey(m2202d((String) obj));
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.f5610e.containsValue(obj);
    }

    /* renamed from: d */
    public final String m2202d(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        char[] cArr = new char[length];
        char[] charArray = str.toCharArray();
        if (charArray[0] >= 'a' && charArray[0] <= 'z') {
            charArray[0] = (char) (charArray[0] - ' ');
        }
        for (int i7 = 1; i7 < length; i7++) {
            if (charArray[i7] >= 'A' && charArray[i7] <= 'Z') {
                charArray[i7] = (char) (charArray[i7] + ' ');
            }
        }
        return new String(charArray);
    }

    @Override // java.util.Map
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public List<String> put(String str, List<String> list) {
        return this.f5610e.put(m2202d(str), list);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, List<String>>> entrySet() {
        return this.f5610e.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.f5610e.equals(obj);
    }

    @Override // java.util.Map
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public List<String> remove(Object obj) {
        return this.f5610e.remove(m2202d((String) obj));
    }

    /* renamed from: g */
    public void mo2019g(String str, String str2) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(str2);
        put(str, linkedList);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.f5610e.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.f5610e.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.f5610e.keySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends List<String>> map) {
        this.f5610e.putAll(map);
    }

    @Override // java.util.Map
    public int size() {
        return this.f5610e.size();
    }

    @Override // java.util.Map
    public Collection<List<String>> values() {
        return this.f5610e.values();
    }
}
