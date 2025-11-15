package okhttp3.internal.tls;

import com.alibaba.fastjson.asm.Opcodes;
import javax.security.auth.x500.X500Principal;
import p009b.C0413b;

/* loaded from: classes.dex */
final class DistinguishedNameParser {
    private int beg;
    private char[] chars;
    private int cur;

    /* renamed from: dn */
    private final String f4851dn;
    private int end;
    private final int length;
    private int pos;

    public DistinguishedNameParser(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.f4851dn = name;
        this.length = name.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        r2 = r8.beg;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005d, code lost:
    
        return new java.lang.String(r1, r2, r8.end - r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String escapedAV() {
        /*
            r8 = this;
            int r0 = r8.pos
            r8.beg = r0
            r8.end = r0
        L6:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r3 = r8.end
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r8.chars
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L5e
            if (r2 == r5) goto L53
            r5 = 92
            if (r2 == r5) goto L40
            if (r2 == r4) goto L53
            if (r2 == r3) goto L53
            int r2 = r8.end
            int r3 = r2 + 1
            r8.end = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.pos = r0
            goto L6
        L40:
            int r0 = r8.end
            int r2 = r0 + 1
            r8.end = r2
            char r2 = r8.getEscaped()
            r1[r0] = r2
            int r0 = r8.pos
            int r0 = r0 + 1
            r8.pos = r0
            goto L6
        L53:
            java.lang.String r0 = new java.lang.String
            int r2 = r8.beg
            int r3 = r8.end
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L5e:
            int r2 = r8.end
            r8.cur = r2
            int r0 = r0 + 1
            r8.pos = r0
            int r0 = r2 + 1
            r8.end = r0
            r1[r2] = r6
        L6c:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 >= r1) goto L85
            char[] r2 = r8.chars
            char r7 = r2[r0]
            if (r7 != r6) goto L85
            int r1 = r8.end
            int r7 = r1 + 1
            r8.end = r7
            r2[r1] = r6
            int r0 = r0 + 1
            r8.pos = r0
            goto L6c
        L85:
            if (r0 == r1) goto L95
            char[] r1 = r8.chars
            char r2 = r1[r0]
            if (r2 == r3) goto L95
            char r2 = r1[r0]
            if (r2 == r4) goto L95
            char r0 = r1[r0]
            if (r0 != r5) goto L6
        L95:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r3 = r8.cur
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.escapedAV():java.lang.String");
    }

    private int getByte(int i7) {
        int i8;
        int i9;
        int i10 = i7 + 1;
        if (i10 >= this.length) {
            StringBuilder sbM112a = C0413b.m112a("Malformed DN: ");
            sbM112a.append(this.f4851dn);
            throw new IllegalStateException(sbM112a.toString());
        }
        char[] cArr = this.chars;
        char c7 = cArr[i7];
        if (c7 >= '0' && c7 <= '9') {
            i8 = c7 - '0';
        } else if (c7 >= 'a' && c7 <= 'f') {
            i8 = c7 - 'W';
        } else {
            if (c7 < 'A' || c7 > 'F') {
                StringBuilder sbM112a2 = C0413b.m112a("Malformed DN: ");
                sbM112a2.append(this.f4851dn);
                throw new IllegalStateException(sbM112a2.toString());
            }
            i8 = c7 - '7';
        }
        char c8 = cArr[i10];
        if (c8 >= '0' && c8 <= '9') {
            i9 = c8 - '0';
        } else if (c8 >= 'a' && c8 <= 'f') {
            i9 = c8 - 'W';
        } else {
            if (c8 < 'A' || c8 > 'F') {
                StringBuilder sbM112a3 = C0413b.m112a("Malformed DN: ");
                sbM112a3.append(this.f4851dn);
                throw new IllegalStateException(sbM112a3.toString());
            }
            i9 = c8 - '7';
        }
        return (i8 << 4) + i9;
    }

    private char getEscaped() {
        int i7 = this.pos + 1;
        this.pos = i7;
        if (i7 == this.length) {
            StringBuilder sbM112a = C0413b.m112a("Unexpected end of DN: ");
            sbM112a.append(this.f4851dn);
            throw new IllegalStateException(sbM112a.toString());
        }
        char[] cArr = this.chars;
        char c7 = cArr[i7];
        if (c7 != ' ' && c7 != '%' && c7 != '\\' && c7 != '_' && c7 != '\"' && c7 != '#') {
            switch (c7) {
                case '*':
                case '+':
                case ',':
                    break;
                default:
                    switch (c7) {
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                            break;
                        default:
                            return getUTF8();
                    }
            }
        }
        return cArr[i7];
    }

    private char getUTF8() {
        int i7;
        int i8;
        int i9 = getByte(this.pos);
        this.pos++;
        if (i9 < 128) {
            return (char) i9;
        }
        if (i9 < 192 || i9 > 247) {
            return '?';
        }
        if (i9 <= 223) {
            i8 = i9 & 31;
            i7 = 1;
        } else if (i9 <= 239) {
            i7 = 2;
            i8 = i9 & 15;
        } else {
            i7 = 3;
            i8 = i9 & 7;
        }
        for (int i10 = 0; i10 < i7; i10++) {
            int i11 = this.pos + 1;
            this.pos = i11;
            if (i11 == this.length || this.chars[i11] != '\\') {
                return '?';
            }
            int i12 = i11 + 1;
            this.pos = i12;
            int i13 = getByte(i12);
            this.pos++;
            if ((i13 & Opcodes.CHECKCAST) != 128) {
                return '?';
            }
            i8 = (i8 << 6) + (i13 & 63);
        }
        return (char) i8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
    
        r6.end = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String hexAV() {
        /*
            r6 = this;
            int r0 = r6.pos
            int r1 = r0 + 4
            int r2 = r6.length
            java.lang.String r3 = "Unexpected end of DN: "
            if (r1 >= r2) goto L9f
            r6.beg = r0
            int r0 = r0 + 1
            r6.pos = r0
        L10:
            int r0 = r6.pos
            int r1 = r6.length
            if (r0 == r1) goto L5f
            char[] r1 = r6.chars
            char r2 = r1[r0]
            r4 = 43
            if (r2 == r4) goto L5f
            char r2 = r1[r0]
            r4 = 44
            if (r2 == r4) goto L5f
            char r2 = r1[r0]
            r4 = 59
            if (r2 != r4) goto L2b
            goto L5f
        L2b:
            char r2 = r1[r0]
            r4 = 32
            if (r2 != r4) goto L48
            r6.end = r0
            int r0 = r0 + 1
            r6.pos = r0
        L37:
            int r0 = r6.pos
            int r1 = r6.length
            if (r0 >= r1) goto L61
            char[] r1 = r6.chars
            char r1 = r1[r0]
            if (r1 != r4) goto L61
            int r0 = r0 + 1
            r6.pos = r0
            goto L37
        L48:
            char r2 = r1[r0]
            r5 = 65
            if (r2 < r5) goto L5a
            char r2 = r1[r0]
            r5 = 70
            if (r2 > r5) goto L5a
            char r2 = r1[r0]
            int r2 = r2 + r4
            char r2 = (char) r2
            r1[r0] = r2
        L5a:
            int r0 = r0 + 1
            r6.pos = r0
            goto L10
        L5f:
            r6.end = r0
        L61:
            int r0 = r6.end
            int r1 = r6.beg
            int r0 = r0 - r1
            r2 = 5
            if (r0 < r2) goto L8c
            r2 = r0 & 1
            if (r2 == 0) goto L8c
            int r2 = r0 / 2
            byte[] r3 = new byte[r2]
            r4 = 0
            int r1 = r1 + 1
        L74:
            if (r4 >= r2) goto L82
            int r5 = r6.getByte(r1)
            byte r5 = (byte) r5
            r3[r4] = r5
            int r1 = r1 + 2
            int r4 = r4 + 1
            goto L74
        L82:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.chars
            int r3 = r6.beg
            r1.<init>(r2, r3, r0)
            return r1
        L8c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = p009b.C0413b.m112a(r3)
            java.lang.String r2 = r6.f4851dn
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L9f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = p009b.C0413b.m112a(r3)
            java.lang.String r2 = r6.f4851dn
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.hexAV():java.lang.String");
    }

    private String nextAT() {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        while (true) {
            i7 = this.pos;
            i8 = this.length;
            if (i7 >= i8 || this.chars[i7] != ' ') {
                break;
            }
            this.pos = i7 + 1;
        }
        if (i7 == i8) {
            return null;
        }
        this.beg = i7;
        this.pos = i7 + 1;
        while (true) {
            i9 = this.pos;
            i10 = this.length;
            if (i9 >= i10) {
                break;
            }
            char[] cArr = this.chars;
            if (cArr[i9] == '=' || cArr[i9] == ' ') {
                break;
            }
            this.pos = i9 + 1;
        }
        if (i9 >= i10) {
            StringBuilder sbM112a = C0413b.m112a("Unexpected end of DN: ");
            sbM112a.append(this.f4851dn);
            throw new IllegalStateException(sbM112a.toString());
        }
        this.end = i9;
        if (this.chars[i9] == ' ') {
            while (true) {
                i11 = this.pos;
                i12 = this.length;
                if (i11 >= i12) {
                    break;
                }
                char[] cArr2 = this.chars;
                if (cArr2[i11] == '=' || cArr2[i11] != ' ') {
                    break;
                }
                this.pos = i11 + 1;
            }
            if (this.chars[i11] != '=' || i11 == i12) {
                StringBuilder sbM112a2 = C0413b.m112a("Unexpected end of DN: ");
                sbM112a2.append(this.f4851dn);
                throw new IllegalStateException(sbM112a2.toString());
            }
        }
        this.pos++;
        while (true) {
            int i13 = this.pos;
            if (i13 >= this.length || this.chars[i13] != ' ') {
                break;
            }
            this.pos = i13 + 1;
        }
        int i14 = this.end;
        int i15 = this.beg;
        if (i14 - i15 > 4) {
            char[] cArr3 = this.chars;
            if (cArr3[i15 + 3] == '.' && ((cArr3[i15] == 'O' || cArr3[i15] == 'o') && ((cArr3[i15 + 1] == 'I' || cArr3[i15 + 1] == 'i') && (cArr3[i15 + 2] == 'D' || cArr3[i15 + 2] == 'd')))) {
                this.beg = i15 + 4;
            }
        }
        char[] cArr4 = this.chars;
        int i16 = this.beg;
        return new String(cArr4, i16, i14 - i16);
    }

    private String quotedAV() {
        int i7 = this.pos + 1;
        this.pos = i7;
        this.beg = i7;
        this.end = i7;
        while (true) {
            int i8 = this.pos;
            if (i8 == this.length) {
                StringBuilder sbM112a = C0413b.m112a("Unexpected end of DN: ");
                sbM112a.append(this.f4851dn);
                throw new IllegalStateException(sbM112a.toString());
            }
            char[] cArr = this.chars;
            if (cArr[i8] == '\"') {
                this.pos = i8 + 1;
                while (true) {
                    int i9 = this.pos;
                    if (i9 >= this.length || this.chars[i9] != ' ') {
                        break;
                    }
                    this.pos = i9 + 1;
                }
                char[] cArr2 = this.chars;
                int i10 = this.beg;
                return new String(cArr2, i10, this.end - i10);
            }
            if (cArr[i8] == '\\') {
                cArr[this.end] = getEscaped();
            } else {
                cArr[this.end] = cArr[i8];
            }
            this.pos++;
            this.end++;
        }
    }

    public String findMostSpecific(String str) {
        this.pos = 0;
        this.beg = 0;
        this.end = 0;
        this.cur = 0;
        this.chars = this.f4851dn.toCharArray();
        String strNextAT = nextAT();
        if (strNextAT == null) {
            return null;
        }
        do {
            int i7 = this.pos;
            if (i7 == this.length) {
                return null;
            }
            char c7 = this.chars[i7];
            String strEscapedAV = c7 != '\"' ? c7 != '#' ? (c7 == '+' || c7 == ',' || c7 == ';') ? "" : escapedAV() : hexAV() : quotedAV();
            if (str.equalsIgnoreCase(strNextAT)) {
                return strEscapedAV;
            }
            int i8 = this.pos;
            if (i8 >= this.length) {
                return null;
            }
            char[] cArr = this.chars;
            if (cArr[i8] != ',' && cArr[i8] != ';' && cArr[i8] != '+') {
                StringBuilder sbM112a = C0413b.m112a("Malformed DN: ");
                sbM112a.append(this.f4851dn);
                throw new IllegalStateException(sbM112a.toString());
            }
            this.pos = i8 + 1;
            strNextAT = nextAT();
        } while (strNextAT != null);
        StringBuilder sbM112a2 = C0413b.m112a("Malformed DN: ");
        sbM112a2.append(this.f4851dn);
        throw new IllegalStateException(sbM112a2.toString());
    }
}
