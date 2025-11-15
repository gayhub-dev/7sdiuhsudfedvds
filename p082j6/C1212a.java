package p082j6;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: Headers.java */
/* renamed from: j6.a */
/* loaded from: classes.dex */
public class C1212a implements Map<String, List<String>> {

    /* renamed from: CR */
    public static final byte f2735CR = 13;

    /* renamed from: LF */
    public static final byte f2736LF = 10;
    public final Map<String, List<String>> map;
    private boolean normalizeHeaders;

    public C1212a() {
        this.map = new HashMap(32);
        this.normalizeHeaders = true;
    }

    private String normalize(String str) {
        if (!this.normalizeHeaders) {
            return str;
        }
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        if (charArray[0] >= 'a' && charArray[0] <= 'z') {
            charArray[0] = (char) (charArray[0] - ' ');
        }
        int length = str.length();
        for (int i7 = 1; i7 < length; i7++) {
            if (charArray[i7] >= 'A' && charArray[i7] <= 'Z') {
                charArray[i7] = (char) (charArray[i7] + ' ');
            }
        }
        return new String(charArray);
    }

    public static String readLine(ByteArrayInputStream byteArrayInputStream) {
        return readLine(new StringBuilder(256), byteArrayInputStream);
    }

    public void add(String str, String str2) {
        String strNormalize = normalize(str);
        List<String> linkedList = this.map.get(strNormalize);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            this.map.put(strNormalize, linkedList);
        }
        linkedList.add(str2);
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return obj != null && (obj instanceof String) && this.map.containsKey(normalize((String) obj));
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, List<String>>> entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    public int findEndOfString(String str) {
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return length;
    }

    public int findNonWhitespace(String str, int i7) {
        while (i7 < str.length() && Character.isWhitespace(str.charAt(i7))) {
            i7++;
        }
        return i7;
    }

    public String getFirstHeader(String str) {
        List<String> list = this.map.get(normalize(str));
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends List<String>> map) {
        for (Map.Entry<? extends String, ? extends List<String>> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void set(String str, String str2) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(str2);
        put(str, (List<String>) linkedList);
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    public String[] splitHeader(String str) {
        char cCharAt;
        int iFindNonWhitespace = findNonWhitespace(str, 0);
        int i7 = iFindNonWhitespace;
        while (i7 < str.length() && (cCharAt = str.charAt(i7)) != ':' && !Character.isWhitespace(cCharAt)) {
            i7++;
        }
        int i8 = i7;
        while (true) {
            if (i8 >= str.length()) {
                break;
            }
            if (str.charAt(i8) == ':') {
                i8++;
                break;
            }
            i8++;
        }
        int iFindNonWhitespace2 = findNonWhitespace(str, i8);
        int iFindEndOfString = findEndOfString(str);
        String[] strArr = new String[2];
        strArr[0] = str.substring(iFindNonWhitespace, i7);
        strArr[1] = (str.length() < iFindNonWhitespace2 || str.length() < iFindEndOfString || iFindNonWhitespace2 >= iFindEndOfString) ? null : str.substring(iFindNonWhitespace2, iFindEndOfString);
        return strArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(512);
        for (Map.Entry<String, List<String>> entry : entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("\r\n");
        }
        return sb.toString();
    }

    @Override // java.util.Map
    public Collection<List<String>> values() {
        return this.map.values();
    }

    public static String readLine(StringBuilder sb, ByteArrayInputStream byteArrayInputStream) {
        while (true) {
            int i7 = byteArrayInputStream.read();
            if (i7 == -1) {
                break;
            }
            char c7 = (char) i7;
            if (c7 == '\r') {
                if (((char) byteArrayInputStream.read()) == '\n') {
                    break;
                }
                sb.append(c7);
            } else {
                if (c7 == '\n') {
                    break;
                }
                sb.append(c7);
            }
        }
        return sb.toString();
    }

    @Override // java.util.Map
    public List<String> get(Object obj) {
        return this.map.get(normalize((String) obj));
    }

    @Override // java.util.Map
    public List<String> put(String str, List<String> list) {
        return this.map.put(normalize(str), list);
    }

    @Override // java.util.Map
    public List<String> remove(Object obj) {
        return this.map.remove(normalize((String) obj));
    }

    public C1212a(Map<String, List<String>> map) {
        this.map = new HashMap(32);
        this.normalizeHeaders = true;
        putAll(map);
    }

    public C1212a(ByteArrayInputStream byteArrayInputStream) {
        this.map = new HashMap(32);
        this.normalizeHeaders = true;
        StringBuilder sb = new StringBuilder(256);
        C1212a c1212a = new C1212a();
        String line = readLine(sb, byteArrayInputStream);
        if (line.length() != 0) {
            String str = null;
            do {
                char cCharAt = line.charAt(0);
                if (str != null && (cCharAt == ' ' || cCharAt == '\t')) {
                    List<String> list = c1212a.get((Object) str);
                    int size = list.size() - 1;
                    list.set(size, list.get(size) + line.trim());
                } else {
                    String[] strArrSplitHeader = splitHeader(line);
                    c1212a.add(strArrSplitHeader[0], strArrSplitHeader[1]);
                    str = strArrSplitHeader[0];
                }
                sb.delete(0, sb.length());
                line = readLine(sb, byteArrayInputStream);
            } while (line.length() != 0);
        }
        putAll(c1212a);
    }

    public C1212a(boolean z6) {
        this.map = new HashMap(32);
        this.normalizeHeaders = true;
        this.normalizeHeaders = z6;
    }
}
