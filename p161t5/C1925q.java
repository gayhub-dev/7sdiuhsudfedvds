package p161t5;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import p009b.C0413b;

/* compiled from: StringMap.java */
/* renamed from: t5.q */
/* loaded from: classes.dex */
public class C1925q extends AbstractMap implements Externalizable {

    /* renamed from: e */
    public int f5684e = 17;

    /* renamed from: f */
    public b f5685f = new b();

    /* renamed from: g */
    public boolean f5686g = false;

    /* renamed from: h */
    public c f5687h = null;

    /* renamed from: i */
    public Object f5688i = null;

    /* renamed from: j */
    public HashSet f5689j;

    /* renamed from: k */
    public Set f5690k;

    /* compiled from: StringMap.java */
    /* renamed from: t5.q$b */
    public static class b implements Map.Entry {

        /* renamed from: e */
        public char[] f5691e;

        /* renamed from: f */
        public char[] f5692f;

        /* renamed from: g */
        public b f5693g;

        /* renamed from: h */
        public b[] f5694h;

        /* renamed from: i */
        public String f5695i;

        /* renamed from: j */
        public Object f5696j;

        public b() {
        }

        /* renamed from: a */
        public b m2249a(C1925q c1925q, int i7) {
            b bVar = new b();
            char[] cArr = this.f5691e;
            int length = cArr.length - i7;
            this.f5691e = new char[i7];
            bVar.f5691e = new char[length];
            System.arraycopy(cArr, 0, this.f5691e, 0, i7);
            System.arraycopy(cArr, i7, bVar.f5691e, 0, length);
            char[] cArr2 = this.f5692f;
            if (cArr2 != null) {
                this.f5692f = new char[i7];
                bVar.f5692f = new char[length];
                System.arraycopy(cArr2, 0, this.f5692f, 0, i7);
                System.arraycopy(cArr2, i7, bVar.f5692f, 0, length);
            }
            bVar.f5695i = this.f5695i;
            bVar.f5696j = this.f5696j;
            this.f5695i = null;
            this.f5696j = null;
            if (c1925q.f5689j.remove(this)) {
                c1925q.f5689j.add(bVar);
            }
            bVar.f5694h = this.f5694h;
            int i8 = c1925q.f5684e;
            b[] bVarArr = new b[i8];
            this.f5694h = bVarArr;
            bVarArr[bVar.f5691e[0] % i8] = bVar;
            char[] cArr3 = bVar.f5692f;
            if (cArr3 != null && bVarArr[cArr3[0] % i8] != bVar) {
                bVarArr[cArr3[0] % i8] = bVar;
            }
            return bVar;
        }

        /* renamed from: b */
        public final void m2250b(StringBuilder sb) {
            sb.append("{[");
            if (this.f5691e != null) {
                int i7 = 0;
                while (true) {
                    char[] cArr = this.f5691e;
                    if (i7 >= cArr.length) {
                        break;
                    }
                    sb.append(cArr[i7]);
                    i7++;
                }
            } else {
                sb.append('-');
            }
            sb.append(':');
            sb.append(this.f5695i);
            sb.append('=');
            sb.append(this.f5696j);
            sb.append(']');
            if (this.f5694h != null) {
                for (int i8 = 0; i8 < this.f5694h.length; i8++) {
                    sb.append('|');
                    b[] bVarArr = this.f5694h;
                    if (bVarArr[i8] != null) {
                        bVarArr[i8].m2250b(sb);
                    } else {
                        sb.append("-");
                    }
                }
            }
            sb.append('}');
            if (this.f5693g != null) {
                sb.append(",\n");
                this.f5693g.m2250b(sb);
            }
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f5695i;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f5696j;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            Object obj2 = this.f5696j;
            this.f5696j = obj;
            return obj2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            m2250b(sb);
            return sb.toString();
        }

        public b(boolean z6, String str, int i7) {
            int length = str.length() - i7;
            this.f5691e = new char[length];
            this.f5692f = new char[length];
            for (int i8 = 0; i8 < length; i8++) {
                char cCharAt = str.charAt(i7 + i8);
                this.f5691e[i8] = cCharAt;
                if (z6) {
                    if (Character.isUpperCase(cCharAt)) {
                        cCharAt = Character.toLowerCase(cCharAt);
                    } else if (Character.isLowerCase(cCharAt)) {
                        cCharAt = Character.toUpperCase(cCharAt);
                    }
                    this.f5692f[i8] = cCharAt;
                }
            }
        }
    }

    /* compiled from: StringMap.java */
    /* renamed from: t5.q$c */
    public class c implements Map.Entry {
        public c(a aVar) {
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return null;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return C1925q.this.f5688i;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            C1925q c1925q = C1925q.this;
            Object obj2 = c1925q.f5688i;
            c1925q.f5688i = obj;
            return obj2;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("[:null=");
            sbM112a.append(C1925q.this.f5688i);
            sbM112a.append("]");
            return sbM112a.toString();
        }
    }

    public C1925q() {
        HashSet hashSet = new HashSet(3);
        this.f5689j = hashSet;
        this.f5690k = Collections.unmodifiableSet(hashSet);
    }

    /* renamed from: a */
    public Object m2245a(String str) {
        if (str == null) {
            return this.f5688i;
        }
        Map.Entry entryM2246b = m2246b(str, 0, str.length());
        if (entryM2246b == null) {
            return null;
        }
        return entryM2246b.getValue();
    }

    /* renamed from: b */
    public Map.Entry m2246b(String str, int i7, int i8) {
        if (str == null) {
            return this.f5687h;
        }
        b bVar = this.f5685f;
        int i9 = -1;
        for (int i10 = 0; i10 < i8; i10++) {
            char cCharAt = str.charAt(i7 + i10);
            if (i9 == -1) {
                b[] bVarArr = bVar.f5694h;
                bVar = bVarArr == null ? null : bVarArr[cCharAt % this.f5684e];
                i9 = 0;
            }
            while (bVar != null) {
                char[] cArr = bVar.f5691e;
                if (cArr[i9] == cCharAt || (this.f5686g && bVar.f5692f[i9] == cCharAt)) {
                    i9++;
                    if (i9 == cArr.length) {
                        i9 = -1;
                    }
                } else {
                    if (i9 > 0) {
                        return null;
                    }
                    bVar = bVar.f5693g;
                }
            }
            return null;
        }
        if (i9 > 0) {
            return null;
        }
        if (bVar == null || bVar.f5695i != null) {
            return bVar;
        }
        return null;
    }

    /* renamed from: c */
    public Object m2247c(String str, Object obj) {
        if (str == null) {
            Object obj2 = this.f5688i;
            this.f5688i = obj;
            if (this.f5687h == null) {
                c cVar = new c(null);
                this.f5687h = cVar;
                this.f5689j.add(cVar);
            }
            return obj2;
        }
        b bVar = this.f5685f;
        b bVar2 = null;
        b bVar3 = null;
        int i7 = 0;
        int i8 = -1;
        while (true) {
            if (i7 >= str.length()) {
                break;
            }
            char cCharAt = str.charAt(i7);
            if (i8 == -1) {
                b[] bVarArr = bVar.f5694h;
                bVar2 = null;
                bVar3 = bVar;
                bVar = bVarArr == null ? null : bVarArr[cCharAt % this.f5684e];
                i8 = 0;
            }
            while (bVar != null) {
                char[] cArr = bVar.f5691e;
                if (cArr[i8] == cCharAt || (this.f5686g && bVar.f5692f[i8] == cCharAt)) {
                    i8++;
                    if (i8 == cArr.length) {
                        bVar2 = null;
                        i8 = -1;
                        i7++;
                    } else {
                        bVar2 = null;
                        i7++;
                    }
                } else if (i8 == 0) {
                    bVar2 = bVar;
                    bVar = bVar.f5693g;
                } else {
                    bVar.m2249a(this, i8);
                    i7--;
                    i8 = -1;
                    i7++;
                }
            }
            bVar = new b(this.f5686g, str, i7);
            if (bVar2 != null) {
                bVar2.f5693g = bVar;
            } else if (bVar3 != null) {
                if (bVar3.f5694h == null) {
                    bVar3.f5694h = new b[this.f5684e];
                }
                b[] bVarArr2 = bVar3.f5694h;
                int i9 = this.f5684e;
                bVarArr2[cCharAt % i9] = bVar;
                char[] cArr2 = bVar.f5692f;
                int i10 = cArr2[0] % i9;
                if (cArr2 != null && bVar.f5691e[0] % i9 != i10) {
                    if (bVarArr2[i10] == null) {
                        bVarArr2[i10] = bVar;
                    } else {
                        b bVar4 = bVarArr2[i10];
                        while (true) {
                            b bVar5 = bVar4.f5693g;
                            if (bVar5 == null) {
                                break;
                            }
                            bVar4 = bVar5;
                        }
                        bVar4.f5693g = bVar;
                    }
                }
            } else {
                this.f5685f = bVar;
            }
        }
        if (bVar == null) {
            return null;
        }
        if (i8 > 0) {
            bVar.m2249a(this, i8);
        }
        Object obj3 = bVar.f5696j;
        bVar.f5695i = str;
        bVar.f5696j = obj;
        this.f5689j.add(bVar);
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f5685f = new b();
        this.f5687h = null;
        this.f5688i = null;
        this.f5689j.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return obj == null ? this.f5687h != null : m2246b(obj.toString(), 0, obj.toString().length()) != null;
    }

    /* renamed from: d */
    public Object m2248d(String str) {
        if (str == null) {
            Object obj = this.f5688i;
            c cVar = this.f5687h;
            if (cVar != null) {
                this.f5689j.remove(cVar);
                this.f5687h = null;
                this.f5688i = null;
            }
            return obj;
        }
        b bVar = this.f5685f;
        int i7 = -1;
        for (int i8 = 0; i8 < str.length(); i8++) {
            char cCharAt = str.charAt(i8);
            if (i7 == -1) {
                b[] bVarArr = bVar.f5694h;
                bVar = bVarArr == null ? null : bVarArr[cCharAt % this.f5684e];
                i7 = 0;
            }
            while (bVar != null) {
                char[] cArr = bVar.f5691e;
                if (cArr[i7] == cCharAt || (this.f5686g && bVar.f5692f[i7] == cCharAt)) {
                    i7++;
                    if (i7 == cArr.length) {
                        i7 = -1;
                    }
                } else {
                    if (i7 > 0) {
                        return null;
                    }
                    bVar = bVar.f5693g;
                }
            }
            return null;
        }
        if (i7 > 0) {
            return null;
        }
        if (bVar != null && bVar.f5695i == null) {
            return null;
        }
        Object obj2 = bVar.f5696j;
        this.f5689j.remove(bVar);
        bVar.f5696j = null;
        bVar.f5695i = null;
        return obj2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        return this.f5690k;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        return obj == null ? this.f5688i : obj instanceof String ? m2245a((String) obj) : m2245a(obj.toString());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.f5689j.isEmpty();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        return obj == null ? m2247c(null, obj2) : m2247c(obj.toString(), obj2);
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) {
        boolean z6 = objectInput.readBoolean();
        HashMap map = (HashMap) objectInput.readObject();
        if (this.f5685f.f5694h != null) {
            throw new IllegalStateException("Must be set before first put");
        }
        this.f5686g = z6;
        putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        return obj == null ? m2248d(null) : m2248d(obj.toString());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f5689j.size();
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        HashMap map = new HashMap(this);
        objectOutput.writeBoolean(this.f5686g);
        objectOutput.writeObject(map);
    }
}
