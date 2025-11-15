package p161t5;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import p009b.C0413b;

/* compiled from: QuotedStringTokenizer.java */
/* renamed from: t5.o */
/* loaded from: classes.dex */
public class C1923o extends StringTokenizer {

    /* renamed from: k */
    public static final char[] f5671k;

    /* renamed from: a */
    public String f5672a;

    /* renamed from: b */
    public String f5673b;

    /* renamed from: c */
    public boolean f5674c;

    /* renamed from: d */
    public boolean f5675d;

    /* renamed from: e */
    public StringBuffer f5676e;

    /* renamed from: f */
    public boolean f5677f;

    /* renamed from: g */
    public int f5678g;

    /* renamed from: h */
    public int f5679h;

    /* renamed from: i */
    public boolean f5680i;

    /* renamed from: j */
    public boolean f5681j;

    static {
        char[] cArr = new char[32];
        f5671k = cArr;
        Arrays.fill(cArr, (char) 65535);
        cArr[8] = 'b';
        cArr[9] = 't';
        cArr[10] = 'n';
        cArr[12] = 'f';
        cArr[13] = 'r';
    }

    public C1923o(String str, String str2, boolean z6, boolean z7) {
        super("");
        this.f5673b = "\t\n\r";
        this.f5674c = false;
        this.f5675d = false;
        this.f5677f = false;
        this.f5678g = 0;
        this.f5679h = 0;
        this.f5680i = true;
        this.f5681j = true;
        this.f5672a = str;
        if (str2 != null) {
            this.f5673b = str2;
        }
        this.f5675d = z6;
        this.f5674c = z7;
        if (this.f5673b.indexOf(39) < 0 && this.f5673b.indexOf(34) < 0) {
            this.f5676e = new StringBuffer(this.f5672a.length() > 1024 ? 512 : this.f5672a.length() / 2);
        } else {
            StringBuilder sbM112a = C0413b.m112a("Can't use quotes as delimiters: ");
            sbM112a.append(this.f5673b);
            throw new Error(sbM112a.toString());
        }
    }

    /* renamed from: a */
    public static boolean m2237a(char c7) {
        return c7 == 'n' || c7 == 'r' || c7 == 't' || c7 == 'f' || c7 == 'b' || c7 == '\\' || c7 == '/' || c7 == '\"' || c7 == 'u';
    }

    /* renamed from: b */
    public static void m2238b(Appendable appendable, String str) throws IOException {
        try {
            appendable.append('\"');
            for (int i7 = 0; i7 < str.length(); i7++) {
                char cCharAt = str.charAt(i7);
                if (cCharAt >= ' ') {
                    if (cCharAt == '\"' || cCharAt == '\\') {
                        appendable.append('\\');
                    }
                    appendable.append(cCharAt);
                } else {
                    char c7 = f5671k[cCharAt];
                    if (c7 == 65535) {
                        appendable.append('\\').append('u').append('0').append('0');
                        if (cCharAt < 16) {
                            appendable.append('0');
                        }
                        appendable.append(Integer.toString(cCharAt, 16));
                    } else {
                        appendable.append('\\').append(c7);
                    }
                }
            }
            appendable.append('\"');
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: c */
    public static String m2239c(String str, String str2) throws IOException {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "\"\"";
        }
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt == '\\' || cCharAt == '\"' || cCharAt == '\'' || Character.isWhitespace(cCharAt) || str2.indexOf(cCharAt) >= 0) {
                StringBuffer stringBuffer = new StringBuffer(str.length() + 8);
                m2238b(stringBuffer, str);
                return stringBuffer.toString();
            }
        }
        return str;
    }

    /* renamed from: d */
    public static boolean m2240d(Appendable appendable, String str, String str2) throws IOException {
        for (int i7 = 0; i7 < str.length(); i7++) {
            if (str2.indexOf(str.charAt(i7)) >= 0) {
                m2238b(appendable, str);
                return true;
            }
        }
        try {
            appendable.append(str);
            return false;
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: e */
    public static String m2241e(String str) {
        char cCharAt;
        if (str == null) {
            return null;
        }
        if (str.length() < 2 || (cCharAt = str.charAt(0)) != str.charAt(str.length() - 1)) {
            return str;
        }
        if (cCharAt != '\"' && cCharAt != '\'') {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() - 2);
        int i7 = 1;
        boolean z6 = false;
        while (i7 < str.length() - 1) {
            char cCharAt2 = str.charAt(i7);
            if (z6) {
                if (cCharAt2 == '\"') {
                    sb.append('\"');
                } else if (cCharAt2 == '/') {
                    sb.append('/');
                } else if (cCharAt2 == '\\') {
                    sb.append('\\');
                } else if (cCharAt2 == 'b') {
                    sb.append('\b');
                } else if (cCharAt2 == 'f') {
                    sb.append('\f');
                } else if (cCharAt2 == 'n') {
                    sb.append('\n');
                } else if (cCharAt2 == 'r') {
                    sb.append('\r');
                } else if (cCharAt2 == 't') {
                    sb.append('\t');
                } else if (cCharAt2 != 'u') {
                    sb.append(cCharAt2);
                } else {
                    int i8 = i7 + 1;
                    int i9 = i8 + 1;
                    int iM2259b = (C1927s.m2259b((byte) str.charAt(i7)) << 24) + (C1927s.m2259b((byte) str.charAt(i8)) << 16);
                    int i10 = i9 + 1;
                    sb.append((char) (C1927s.m2259b((byte) str.charAt(i10)) + iM2259b + (C1927s.m2259b((byte) str.charAt(i9)) << 8)));
                    i7 = i10 + 1;
                }
                z6 = false;
            } else if (cCharAt2 == '\\') {
                z6 = true;
            } else {
                sb.append(cCharAt2);
            }
            i7++;
        }
        return sb.toString();
    }

    /* renamed from: f */
    public static String m2242f(String str) {
        return m2243g(str, false);
    }

    /* renamed from: g */
    public static String m2243g(String str, boolean z6) {
        char cCharAt;
        if (str == null) {
            return null;
        }
        if (str.length() < 2 || (cCharAt = str.charAt(0)) != str.charAt(str.length() - 1)) {
            return str;
        }
        if (cCharAt != '\"' && cCharAt != '\'') {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() - 2);
        boolean z7 = false;
        for (int i7 = 1; i7 < str.length() - 1; i7++) {
            char cCharAt2 = str.charAt(i7);
            if (z7) {
                if (z6 && !m2237a(cCharAt2)) {
                    sb.append('\\');
                }
                sb.append(cCharAt2);
                z7 = false;
            } else if (cCharAt2 == '\\') {
                z7 = true;
            } else {
                sb.append(cCharAt2);
            }
        }
        return sb.toString();
    }

    @Override // java.util.StringTokenizer
    public int countTokens() {
        return -1;
    }

    @Override // java.util.StringTokenizer, java.util.Enumeration
    public boolean hasMoreElements() {
        return hasMoreTokens();
    }

    @Override // java.util.StringTokenizer
    public boolean hasMoreTokens() {
        if (this.f5677f) {
            return true;
        }
        this.f5679h = this.f5678g;
        char c7 = 0;
        while (true) {
            boolean z6 = false;
            while (this.f5678g < this.f5672a.length()) {
                String str = this.f5672a;
                int i7 = this.f5678g;
                this.f5678g = i7 + 1;
                char cCharAt = str.charAt(i7);
                if (c7 != 0) {
                    if (c7 == 1) {
                        this.f5677f = true;
                        if (this.f5673b.indexOf(cCharAt) >= 0) {
                            if (this.f5675d) {
                                this.f5678g--;
                            }
                            return this.f5677f;
                        }
                        if (cCharAt == '\'' && this.f5681j) {
                            if (this.f5674c) {
                                this.f5676e.append(cCharAt);
                            }
                            c7 = 2;
                        } else if (cCharAt == '\"' && this.f5680i) {
                            if (this.f5674c) {
                                this.f5676e.append(cCharAt);
                            }
                            c7 = 3;
                        } else {
                            this.f5676e.append(cCharAt);
                        }
                    } else if (c7 == 2) {
                        this.f5677f = true;
                        if (z6) {
                            this.f5676e.append(cCharAt);
                        } else if (cCharAt == '\'') {
                            if (this.f5674c) {
                                this.f5676e.append(cCharAt);
                            }
                            c7 = 1;
                        } else if (cCharAt == '\\') {
                            if (this.f5674c) {
                                this.f5676e.append(cCharAt);
                            }
                            z6 = true;
                        } else {
                            this.f5676e.append(cCharAt);
                        }
                    } else if (c7 != 3) {
                        continue;
                    } else {
                        this.f5677f = true;
                        if (z6) {
                            this.f5676e.append(cCharAt);
                        } else if (cCharAt == '\"') {
                            if (this.f5674c) {
                                this.f5676e.append(cCharAt);
                            }
                            c7 = 1;
                        } else if (cCharAt == '\\') {
                            if (this.f5674c) {
                                this.f5676e.append(cCharAt);
                            }
                            z6 = true;
                        } else {
                            this.f5676e.append(cCharAt);
                        }
                    }
                } else if (this.f5673b.indexOf(cCharAt) >= 0) {
                    if (this.f5675d) {
                        this.f5676e.append(cCharAt);
                        this.f5677f = true;
                        return true;
                    }
                } else if (cCharAt == '\'' && this.f5681j) {
                    if (this.f5674c) {
                        this.f5676e.append(cCharAt);
                    }
                    c7 = 2;
                } else if (cCharAt == '\"' && this.f5680i) {
                    if (this.f5674c) {
                        this.f5676e.append(cCharAt);
                    }
                    c7 = 3;
                } else {
                    this.f5676e.append(cCharAt);
                    this.f5677f = true;
                    c7 = 1;
                }
            }
            return this.f5677f;
        }
    }

    @Override // java.util.StringTokenizer, java.util.Enumeration
    public Object nextElement() {
        return nextToken();
    }

    @Override // java.util.StringTokenizer
    public String nextToken() {
        StringBuffer stringBuffer;
        if (!hasMoreTokens() || (stringBuffer = this.f5676e) == null) {
            throw new NoSuchElementException();
        }
        String string = stringBuffer.toString();
        this.f5676e.setLength(0);
        this.f5677f = false;
        return string;
    }

    @Override // java.util.StringTokenizer
    public String nextToken(String str) {
        this.f5673b = str;
        this.f5678g = this.f5679h;
        this.f5676e.setLength(0);
        this.f5677f = false;
        return nextToken();
    }
}
