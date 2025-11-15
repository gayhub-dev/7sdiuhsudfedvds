package p098l6;

import android.arch.lifecycle.C0063n;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.fourthline.cling.model.ServiceReference;

/* compiled from: MimeType.java */
/* renamed from: l6.b */
/* loaded from: classes.dex */
public class C1448b {

    /* renamed from: a */
    public String f4204a;

    /* renamed from: b */
    public String f4205b;

    /* renamed from: c */
    public Map<String, String> f4206c;

    /* compiled from: MimeType.java */
    /* renamed from: l6.b$a */
    public class a implements Comparator<String> {
        public a(C1448b c1448b) {
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            return str.compareToIgnoreCase(str2);
        }
    }

    public C1448b(String str, String str2, Map<String, String> map) {
        this.f4204a = str == null ? "*" : str;
        this.f4205b = str2 == null ? "*" : str2;
        if (map == null) {
            this.f4206c = Collections.EMPTY_MAP;
            return;
        }
        TreeMap treeMap = new TreeMap(new a(this));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }
        this.f4206c = Collections.unmodifiableMap(treeMap);
    }

    /* renamed from: a */
    public static C1448b m1633a(String str) {
        String strTrim;
        String strTrim2;
        int iIndexOf;
        if (str == null) {
            throw new IllegalArgumentException("String value is null");
        }
        int iIndexOf2 = str.indexOf(";");
        String strTrim3 = null;
        if (iIndexOf2 > -1) {
            strTrim = str.substring(iIndexOf2 + 1).trim();
            str = str.substring(0, iIndexOf2);
        } else {
            strTrim = null;
        }
        String[] strArrSplit = str.split(ServiceReference.DELIMITER);
        if (strArrSplit.length < 2 && str.equals("*")) {
            strTrim2 = "*";
            strTrim3 = strTrim2;
        } else if (strArrSplit.length == 2) {
            strTrim3 = strArrSplit[0].trim();
            strTrim2 = strArrSplit[1].trim();
        } else {
            if (strArrSplit.length != 2) {
                throw new IllegalArgumentException(C0063n.m88a("Error parsing string: ", str));
            }
            strTrim2 = null;
        }
        if (strTrim == null || strTrim.length() <= 0) {
            return new C1448b(strTrim3, strTrim2, Collections.EMPTY_MAP);
        }
        HashMap map = new HashMap();
        for (int i7 = 0; i7 < strTrim.length(); i7 = iIndexOf) {
            iIndexOf = strTrim.indexOf(61, i7);
            int iIndexOf3 = strTrim.indexOf(59, i7);
            if (iIndexOf == -1 && iIndexOf3 == -1) {
                iIndexOf = strTrim.length();
            } else if (iIndexOf == -1 || (iIndexOf3 != -1 && iIndexOf >= iIndexOf3)) {
                iIndexOf = iIndexOf3;
            }
            String strTrim4 = strTrim.substring(i7, iIndexOf).trim();
            if (iIndexOf < strTrim.length() && strTrim.charAt(iIndexOf) == '=') {
                iIndexOf++;
            }
            StringBuilder sb = new StringBuilder(strTrim.length() - iIndexOf);
            boolean z6 = false;
            boolean z7 = false;
            while (true) {
                if (iIndexOf >= strTrim.length()) {
                    map.put(strTrim4, sb.toString().trim());
                    break;
                }
                char cCharAt = strTrim.charAt(iIndexOf);
                if (cCharAt != '\"') {
                    if (cCharAt == ';') {
                        if (!z7) {
                            map.put(strTrim4, sb.toString().trim());
                            iIndexOf++;
                            break;
                        }
                        sb.append(cCharAt);
                    } else if (cCharAt != '\\') {
                        sb.append(cCharAt);
                    } else if (z6) {
                        sb.append(cCharAt);
                        z6 = false;
                    } else {
                        z6 = true;
                    }
                } else if (z6) {
                    sb.append(cCharAt);
                    z6 = false;
                } else {
                    z7 = !z7;
                }
                iIndexOf++;
            }
        }
        return new C1448b(strTrim3, strTrim2, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1448b.class != obj.getClass()) {
            return false;
        }
        C1448b c1448b = (C1448b) obj;
        Map<String, String> map = this.f4206c;
        if (map == null ? c1448b.f4206c == null : map.equals(c1448b.f4206c)) {
            return this.f4205b.equalsIgnoreCase(c1448b.f4205b) && this.f4204a.equalsIgnoreCase(c1448b.f4204a);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (this.f4205b.toLowerCase().hashCode() + (this.f4204a.toLowerCase().hashCode() * 31)) * 31;
        Map<String, String> map = this.f4206c;
        return iHashCode + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f4204a + ServiceReference.DELIMITER + this.f4205b);
        Map<String, String> map = this.f4206c;
        if (map != null || map.size() > 0) {
            for (String str : this.f4206c.keySet()) {
                sb.append(";");
                sb.append(str);
                sb.append("=\"");
                sb.append(this.f4206c.get(str));
                sb.append("\"");
            }
        }
        return sb.toString();
    }

    public C1448b() {
        this("*", "*", Collections.EMPTY_MAP);
    }
}
