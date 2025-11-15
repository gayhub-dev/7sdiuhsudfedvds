package p065h5;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.fourthline.cling.model.ServiceReference;
import p161t5.C1925q;

/* compiled from: PathMap.java */
/* renamed from: h5.y */
/* loaded from: classes.dex */
public class C1117y extends HashMap implements Externalizable {

    /* renamed from: k */
    public static final /* synthetic */ int f2400k = 0;

    /* renamed from: e */
    public final C1925q f2401e;

    /* renamed from: f */
    public final C1925q f2402f;

    /* renamed from: g */
    public final C1925q f2403g;

    /* renamed from: h */
    public List f2404h;

    /* renamed from: i */
    public a f2405i;

    /* renamed from: j */
    public a f2406j;

    /* compiled from: PathMap.java */
    /* renamed from: h5.y$a */
    public static class a implements Map.Entry {

        /* renamed from: e */
        public final Object f2407e;

        /* renamed from: f */
        public final Object f2408f;

        /* renamed from: g */
        public String f2409g;

        /* renamed from: h */
        public transient String f2410h;

        public a(Object obj, Object obj2) {
            this.f2407e = obj;
            this.f2408f = obj2;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f2407e;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f2408f;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            if (this.f2410h == null) {
                this.f2410h = this.f2407e + "=" + this.f2408f;
            }
            return this.f2410h;
        }
    }

    public C1117y() {
        super(11);
        this.f2401e = new C1925q();
        this.f2402f = new C1925q();
        this.f2403g = new C1925q();
        this.f2404h = null;
        this.f2405i = null;
        this.f2406j = null;
        entrySet();
    }

    /* renamed from: d */
    public static boolean m1259d(String str, String str2) {
        int length = str.length() - 2;
        return str.endsWith("/*") && str2.regionMatches(0, str, 0, length) && (str2.length() == length || '/' == str2.charAt(length));
    }

    /* renamed from: b */
    public a m1260b(String str) {
        Map.Entry entryM2246b;
        Map.Entry entryM2246b2;
        Map.Entry entry;
        if (str == null) {
            return null;
        }
        int length = str.length();
        int iIndexOf = 0;
        if (length == 1 && str.charAt(0) == '/' && (entry = (Map.Entry) this.f2403g.m2245a("")) != null) {
            return (a) entry;
        }
        Map.Entry entryM2246b3 = this.f2403g.m2246b(str, 0, length);
        if (entryM2246b3 != null) {
            return (a) entryM2246b3.getValue();
        }
        int iLastIndexOf = length;
        do {
            iLastIndexOf = str.lastIndexOf(47, iLastIndexOf - 1);
            if (iLastIndexOf < 0) {
                a aVar = this.f2405i;
                if (aVar != null) {
                    return aVar;
                }
                do {
                    iIndexOf = str.indexOf(46, iIndexOf + 1);
                    if (iIndexOf <= 0) {
                        return this.f2406j;
                    }
                    entryM2246b = this.f2402f.m2246b(str, iIndexOf + 1, (length - iIndexOf) - 1);
                } while (entryM2246b == null);
                return (a) entryM2246b.getValue();
            }
            entryM2246b2 = this.f2401e.m2246b(str, 0, iLastIndexOf);
        } while (entryM2246b2 == null);
        return (a) entryM2246b2.getValue();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f2403g.clear();
        this.f2401e.clear();
        this.f2402f.clear();
        this.f2406j = null;
        this.f2404h = null;
        super.clear();
    }

    /* renamed from: g */
    public Object m1261g(String str) {
        a aVarM1260b = m1260b(str);
        if (aVarM1260b != null) {
            return aVarM1260b.f2408f;
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        String string = obj.toString();
        if ("".equals(string.trim())) {
            a aVar = new a("", obj2);
            aVar.f2409g = "";
            this.f2403g.m2247c("", aVar);
            return super.put("", obj2);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(string, ":,");
        Object obj3 = null;
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (!strNextToken.startsWith(ServiceReference.DELIMITER) && !strNextToken.startsWith("*.")) {
                throw new IllegalArgumentException(C0096a.m97a("PathSpec ", strNextToken, ". must start with '/' or '*.'"));
            }
            Object objPut = super.put(strNextToken, obj2);
            a aVar2 = new a(strNextToken, obj2);
            if (strNextToken.equals(strNextToken)) {
                if (strNextToken.equals("/*")) {
                    this.f2405i = aVar2;
                } else if (strNextToken.endsWith("/*")) {
                    String strSubstring = strNextToken.substring(0, strNextToken.length() - 2);
                    aVar2.f2409g = strSubstring;
                    this.f2401e.m2247c(strSubstring, aVar2);
                    this.f2403g.m2247c(strSubstring, aVar2);
                    this.f2403g.m2247c(strNextToken.substring(0, strNextToken.length() - 1), aVar2);
                } else if (strNextToken.startsWith("*.")) {
                    this.f2402f.m2247c(strNextToken.substring(2), aVar2);
                } else if (strNextToken.equals(ServiceReference.DELIMITER)) {
                    this.f2406j = aVar2;
                    this.f2404h = Collections.singletonList(aVar2);
                } else {
                    aVar2.f2409g = strNextToken;
                    this.f2403g.m2247c(strNextToken, aVar2);
                }
            }
            obj3 = objPut;
        }
        return obj3;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) {
        putAll((HashMap) objectInput.readObject());
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        if (obj != null) {
            String str = (String) obj;
            if (str.equals("/*")) {
                this.f2405i = null;
            } else if (str.endsWith("/*")) {
                this.f2401e.m2248d(str.substring(0, str.length() - 2));
                this.f2403g.m2248d(str.substring(0, str.length() - 1));
                this.f2403g.m2248d(str.substring(0, str.length() - 2));
            } else if (str.startsWith("*.")) {
                this.f2402f.m2248d(str.substring(2));
            } else if (str.equals(ServiceReference.DELIMITER)) {
                this.f2406j = null;
                this.f2404h = null;
            } else {
                this.f2403g.m2248d(str);
            }
        }
        return super.remove(obj);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(new HashMap(this));
    }
}
