package p010b0;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p009b.C0413b;

/* compiled from: LazyHeaders.java */
/* renamed from: b0.i */
/* loaded from: classes.dex */
public final class C0423i implements InterfaceC0421g {

    /* renamed from: b */
    public final Map<String, List<InterfaceC0422h>> f216b;

    /* renamed from: c */
    public volatile Map<String, String> f217c;

    /* compiled from: LazyHeaders.java */
    /* renamed from: b0.i$a */
    public static final class a {

        /* renamed from: b */
        public static final Map<String, List<InterfaceC0422h>> f218b;

        /* renamed from: a */
        public Map<String, List<InterfaceC0422h>> f219a = f218b;

        static {
            String property = System.getProperty("http.agent");
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(property)) {
                map.put("User-Agent", Collections.singletonList(new b(property)));
            }
            f218b = Collections.unmodifiableMap(map);
        }
    }

    /* compiled from: LazyHeaders.java */
    /* renamed from: b0.i$b */
    public static final class b implements InterfaceC0422h {

        /* renamed from: a */
        public final String f220a;

        public b(String str) {
            this.f220a = str;
        }

        @Override // p010b0.InterfaceC0422h
        /* renamed from: a */
        public String mo134a() {
            return this.f220a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.f220a.equals(((b) obj).f220a);
            }
            return false;
        }

        public int hashCode() {
            return this.f220a.hashCode();
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("StringHeaderFactory{value='");
            sbM112a.append(this.f220a);
            sbM112a.append('\'');
            sbM112a.append('}');
            return sbM112a.toString();
        }
    }

    public C0423i(Map<String, List<InterfaceC0422h>> map) {
        this.f216b = Collections.unmodifiableMap(map);
    }

    @Override // p010b0.InterfaceC0421g
    /* renamed from: a */
    public Map<String, String> mo133a() {
        if (this.f217c == null) {
            synchronized (this) {
                if (this.f217c == null) {
                    this.f217c = Collections.unmodifiableMap(m135b());
                }
            }
        }
        return this.f217c;
    }

    /* renamed from: b */
    public final Map<String, String> m135b() {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<InterfaceC0422h>> entry : this.f216b.entrySet()) {
            StringBuilder sb = new StringBuilder();
            List<InterfaceC0422h> value = entry.getValue();
            int size = value.size();
            for (int i7 = 0; i7 < size; i7++) {
                String strMo134a = value.get(i7).mo134a();
                if (!TextUtils.isEmpty(strMo134a)) {
                    sb.append(strMo134a);
                    if (i7 != value.size() - 1) {
                        sb.append(',');
                    }
                }
            }
            if (!TextUtils.isEmpty(sb.toString())) {
                map.put(entry.getKey(), sb.toString());
            }
        }
        return map;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0423i) {
            return this.f216b.equals(((C0423i) obj).f216b);
        }
        return false;
    }

    public int hashCode() {
        return this.f216b.hashCode();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("LazyHeaders{headers=");
        sbM112a.append(this.f216b);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
