package okhttp3;

import android.arch.lifecycle.C0063n;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;

/* loaded from: classes.dex */
public final class Headers {
    private final String[] namesAndValues;

    public Headers(Builder builder) {
        List<String> list = builder.namesAndValues;
        this.namesAndValues = (String[]) list.toArray(new String[list.size()]);
    }

    public static void checkName(String str) {
        Objects.requireNonNull(str, "name == null");
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt <= ' ' || cCharAt >= 127) {
                throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i7), str));
            }
        }
    }

    public static void checkValue(String str, String str2) {
        if (str == null) {
            throw new NullPointerException(C0096a.m97a("value for name ", str2, " == null"));
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if ((cCharAt <= 31 && cCharAt != '\t') || cCharAt >= 127) {
                throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt), Integer.valueOf(i7), str2, str));
            }
        }
    }

    /* renamed from: of */
    public static Headers m1865of(String... strArr) {
        Objects.requireNonNull(strArr, "namesAndValues == null");
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i7 = 0; i7 < strArr2.length; i7++) {
            if (strArr2[i7] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i7] = strArr2[i7].trim();
        }
        for (int i8 = 0; i8 < strArr2.length; i8 += 2) {
            String str = strArr2[i8];
            String str2 = strArr2[i8 + 1];
            checkName(str);
            checkValue(str2, str);
        }
        return new Headers(strArr2);
    }

    public long byteCount() {
        String[] strArr = this.namesAndValues;
        long length = strArr.length * 2;
        for (int i7 = 0; i7 < strArr.length; i7++) {
            length += this.namesAndValues[i7].length();
        }
        return length;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Headers) && Arrays.equals(((Headers) obj).namesAndValues, this.namesAndValues);
    }

    @Nullable
    public String get(String str) {
        return get(this.namesAndValues, str);
    }

    @Nullable
    public Date getDate(String str) {
        String str2 = get(str);
        if (str2 != null) {
            return HttpDate.parse(str2);
        }
        return null;
    }

    public int hashCode() {
        return Arrays.hashCode(this.namesAndValues);
    }

    public String name(int i7) {
        return this.namesAndValues[i7 * 2];
    }

    public Set<String> names() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            treeSet.add(name(i7));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        Collections.addAll(builder.namesAndValues, this.namesAndValues);
        return builder;
    }

    public int size() {
        return this.namesAndValues.length / 2;
    }

    public Map<String, List<String>> toMultimap() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            String lowerCase = name(i7).toLowerCase(Locale.US);
            List arrayList = (List) treeMap.get(lowerCase);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
                treeMap.put(lowerCase, arrayList);
            }
            arrayList.add(value(i7));
        }
        return treeMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            sb.append(name(i7));
            sb.append(": ");
            sb.append(value(i7));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String value(int i7) {
        return this.namesAndValues[(i7 * 2) + 1];
    }

    public List<String> values(String str) {
        int size = size();
        ArrayList arrayList = null;
        for (int i7 = 0; i7 < size; i7++) {
            if (str.equalsIgnoreCase(name(i7))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(value(i7));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public static final class Builder {
        public final List<String> namesAndValues = new ArrayList(20);

        public Builder add(String str) {
            int iIndexOf = str.indexOf(":");
            if (iIndexOf != -1) {
                return add(str.substring(0, iIndexOf).trim(), str.substring(iIndexOf + 1));
            }
            throw new IllegalArgumentException(C0063n.m88a("Unexpected header: ", str));
        }

        public Builder addAll(Headers headers) {
            int size = headers.size();
            for (int i7 = 0; i7 < size; i7++) {
                addLenient(headers.name(i7), headers.value(i7));
            }
            return this;
        }

        public Builder addLenient(String str) {
            int iIndexOf = str.indexOf(":", 1);
            return iIndexOf != -1 ? addLenient(str.substring(0, iIndexOf), str.substring(iIndexOf + 1)) : str.startsWith(":") ? addLenient("", str.substring(1)) : addLenient("", str);
        }

        public Builder addUnsafeNonAscii(String str, String str2) {
            Headers.checkName(str);
            return addLenient(str, str2);
        }

        public Headers build() {
            return new Headers(this);
        }

        public String get(String str) {
            for (int size = this.namesAndValues.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.namesAndValues.get(size))) {
                    return this.namesAndValues.get(size + 1);
                }
            }
            return null;
        }

        public Builder removeAll(String str) {
            int i7 = 0;
            while (i7 < this.namesAndValues.size()) {
                if (str.equalsIgnoreCase(this.namesAndValues.get(i7))) {
                    this.namesAndValues.remove(i7);
                    this.namesAndValues.remove(i7);
                    i7 -= 2;
                }
                i7 += 2;
            }
            return this;
        }

        public Builder set(String str, Date date) {
            if (date == null) {
                throw new NullPointerException(C0096a.m97a("value for name ", str, " == null"));
            }
            set(str, HttpDate.format(date));
            return this;
        }

        public Builder set(String str, String str2) {
            Headers.checkName(str);
            Headers.checkValue(str2, str);
            removeAll(str);
            addLenient(str, str2);
            return this;
        }

        public Builder add(String str, String str2) {
            Headers.checkName(str);
            Headers.checkValue(str2, str);
            return addLenient(str, str2);
        }

        public Builder addLenient(String str, String str2) {
            this.namesAndValues.add(str);
            this.namesAndValues.add(str2.trim());
            return this;
        }

        public Builder add(String str, Date date) {
            if (date != null) {
                add(str, HttpDate.format(date));
                return this;
            }
            throw new NullPointerException(C0096a.m97a("value for name ", str, " == null"));
        }
    }

    private static String get(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    private Headers(String[] strArr) {
        this.namesAndValues = strArr;
    }

    /* renamed from: of */
    public static Headers m1864of(Map<String, String> map) {
        Objects.requireNonNull(map, "headers == null");
        String[] strArr = new String[map.size() * 2];
        int i7 = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                String strTrim = entry.getKey().trim();
                String strTrim2 = entry.getValue().trim();
                checkName(strTrim);
                checkValue(strTrim2, strTrim);
                strArr[i7] = strTrim;
                strArr[i7 + 1] = strTrim2;
                i7 += 2;
            } else {
                throw new IllegalArgumentException("Headers cannot be null");
            }
        }
        return new Headers(strArr);
    }
}
