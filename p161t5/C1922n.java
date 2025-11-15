package p161t5;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Properties;
import p006a5.C0022h;
import p015b5.InterfaceC0469n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: MultiPartInputStream.java */
/* renamed from: t5.n */
/* loaded from: classes.dex */
public class C1922n {

    /* renamed from: g */
    public static final InterfaceC2016c f5652g;

    /* renamed from: a */
    public InputStream f5653a;

    /* renamed from: b */
    public C0022h f5654b;

    /* renamed from: c */
    public String f5655c;

    /* renamed from: d */
    public ConcurrentMapC1920l<String> f5656d;

    /* renamed from: e */
    public File f5657e;

    /* renamed from: f */
    public File f5658f;

    /* compiled from: MultiPartInputStream.java */
    /* renamed from: t5.n$a */
    public static class a extends InputStream {

        /* renamed from: e */
        public C1924p f5659e;

        /* renamed from: f */
        public String f5660f;

        /* renamed from: g */
        public byte[] f5661g;

        /* renamed from: h */
        public int f5662h;

        public a(C1924p c1924p) {
            this.f5659e = c1924p;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = this.f5661g;
            if (bArr == null || this.f5662h >= bArr.length) {
                String strM2244a = this.f5659e.m2244a();
                this.f5660f = strM2244a;
                if (strM2244a == null) {
                    return -1;
                }
                if (strM2244a.startsWith("--")) {
                    this.f5661g = (this.f5660f + "\r\n").getBytes();
                } else if (this.f5660f.length() == 0) {
                    this.f5661g = "\r\n".getBytes();
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((this.f5660f.length() * 4) / 3) + 2);
                    C1911c.m2207a(this.f5660f, byteArrayOutputStream);
                    byteArrayOutputStream.write(13);
                    byteArrayOutputStream.write(10);
                    this.f5661g = byteArrayOutputStream.toByteArray();
                }
                this.f5662h = 0;
            }
            byte[] bArr2 = this.f5661g;
            int i7 = this.f5662h;
            this.f5662h = i7 + 1;
            return bArr2[i7];
        }
    }

    /* compiled from: MultiPartInputStream.java */
    /* renamed from: t5.n$b */
    public class b implements InterfaceC0469n {

        /* renamed from: a */
        public String f5663a;

        /* renamed from: b */
        public String f5664b;

        /* renamed from: c */
        public File f5665c;

        /* renamed from: d */
        public OutputStream f5666d;

        /* renamed from: e */
        public C1914f f5667e;

        /* renamed from: f */
        public String f5668f;

        /* renamed from: g */
        public long f5669g = 0;

        public b(String str, String str2) {
            this.f5663a = str;
            this.f5664b = str2;
        }

        /* renamed from: a */
        public void m2234a() throws IOException {
            OutputStream outputStream;
            File fileCreateTempFile = File.createTempFile("MultiPart", "", C1922n.this.f5657e);
            this.f5665c = fileCreateTempFile;
            fileCreateTempFile.setReadable(false, false);
            this.f5665c.setReadable(true, true);
            Objects.requireNonNull(C1922n.this);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.f5665c));
            if (this.f5669g > 0 && (outputStream = this.f5666d) != null) {
                outputStream.flush();
                this.f5667e.writeTo(bufferedOutputStream);
                this.f5666d.close();
                this.f5667e = null;
            }
            this.f5666d = bufferedOutputStream;
        }

        /* renamed from: b */
        public void m2235b(int i7) throws IOException {
            Objects.requireNonNull(C1922n.this.f5654b);
            Objects.requireNonNull(C1922n.this.f5654b);
            this.f5666d.write(i7);
            this.f5669g++;
        }

        /* renamed from: c */
        public void m2236c(byte[] bArr, int i7, int i8) throws IOException {
            Objects.requireNonNull(C1922n.this.f5654b);
            Objects.requireNonNull(C1922n.this.f5654b);
            this.f5666d.write(bArr, i7, i8);
            this.f5669g += i8;
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5652g = C2015b.m2349a(C1922n.class.getName());
        System.getProperty("java.io.tmpdir");
    }

    public C1922n(InputStream inputStream, String str, C0022h c0022h, File file) {
        this.f5653a = new C1924p(inputStream);
        this.f5655c = str;
        this.f5654b = c0022h;
        this.f5658f = file;
        if (file == null) {
            this.f5658f = new File(System.getProperty("java.io.tmpdir"));
        }
        if (this.f5654b == null) {
            this.f5654b = new C0022h(this.f5658f.getAbsolutePath());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01e9, code lost:
    
        if (r7 == (-1)) goto L233;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01eb, code lost:
    
        java.util.Objects.requireNonNull(r15.f5654b);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01f0, code lost:
    
        if (r7 == 13) goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01f2, code lost:
    
        if (r7 != 10) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01f5, code lost:
    
        if (r12 < 0) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01f8, code lost:
    
        if (r12 >= r5.length) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01fc, code lost:
    
        if (r7 != r5[r12]) goto L238;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01fe, code lost:
    
        r12 = r12 + 1;
        r7 = -2;
        r8 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0203, code lost:
    
        if (r9 == false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0205, code lost:
    
        r1.m2235b(13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0208, code lost:
    
        if (r10 == false) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x020a, code lost:
    
        r1.m2235b(10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x020d, code lost:
    
        if (r12 <= 0) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x020f, code lost:
    
        r1.m2236c(r5, r2, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0212, code lost:
    
        r1.m2235b(r7);
        r7 = -2;
        r8 = -2;
        r9 = false;
        r10 = false;
        r12 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x021b, code lost:
    
        if (r7 != 13) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x021d, code lost:
    
        r6.mark(1);
        r8 = r6.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0225, code lost:
    
        if (r8 == 10) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0227, code lost:
    
        r6.reset();
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x022a, code lost:
    
        r8 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x022b, code lost:
    
        if (r12 <= 0) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0230, code lost:
    
        if (r12 < (r5.length - 2)) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0234, code lost:
    
        if (r12 != (r5.length - 1)) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0236, code lost:
    
        if (r9 == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0238, code lost:
    
        r1.m2235b(13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x023b, code lost:
    
        if (r10 == false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x023d, code lost:
    
        r1.m2235b(10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0240, code lost:
    
        r1.m2236c(r5, 0, r12);
        r9 = false;
        r10 = false;
        r12 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0247, code lost:
    
        if (r12 > 0) goto L229;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0249, code lost:
    
        if (r7 != (-1)) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x024c, code lost:
    
        if (r9 == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x024e, code lost:
    
        r1.m2235b(13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0251, code lost:
    
        if (r10 == false) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0253, code lost:
    
        r1.m2235b(10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0256, code lost:
    
        if (r7 != 13) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0258, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x025b, code lost:
    
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x025d, code lost:
    
        if (r7 == 10) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x025f, code lost:
    
        if (r8 != 10) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0262, code lost:
    
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0265, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0267, code lost:
    
        if (r8 != 10) goto L232;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0269, code lost:
    
        r8 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x026b, code lost:
    
        r2 = 0;
        r7 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0270, code lost:
    
        if (r12 != r5.length) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0272, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0273, code lost:
    
        r1.f5666d.close();
        r2 = false;
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x027c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x027d, code lost:
    
        r1.f5666d.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0282, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x028a, code lost:
    
        throw new java.io.IOException("Missing content-disposition");
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x00e7, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0104, code lost:
    
        if (r7 == null) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0106, code lost:
    
        r1 = new p161t5.C1923o(r7, ";", r2, r8);
        r7 = null;
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0112, code lost:
    
        if (r1.hasMoreTokens() == false) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0114, code lost:
    
        r12 = r1.nextToken().trim();
        r13 = r12.toLowerCase(java.util.Locale.ENGLISH);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0128, code lost:
    
        if (r12.startsWith("form-data") == false) goto L213;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x012a, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0132, code lost:
    
        if (r13.startsWith("name=") == false) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0134, code lost:
    
        r6 = m2233b(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x013f, code lost:
    
        if (r13.startsWith("filename=") == false) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0141, code lost:
    
        r7 = r12.substring(r12.indexOf(61) + (r8 ? 1 : 0)).trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0156, code lost:
    
        if (r7.matches(".??[a-z,A-Z]\\:\\\\[^\\\\].*") == false) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0158, code lost:
    
        r12 = r7.charAt(r2 ? 1 : 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0160, code lost:
    
        if (r12 == '\"') goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0162, code lost:
    
        if (r12 != '\'') goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0164, code lost:
    
        r7 = r7.substring(r8 ? 1 : 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0168, code lost:
    
        r12 = r7.charAt(r7.length() - (r8 ? 1 : 0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0171, code lost:
    
        if (r12 == '\"') goto L219;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0173, code lost:
    
        if (r12 != '\'') goto L225;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0175, code lost:
    
        r7 = r7.substring(r2 ? 1 : 0, r7.length() - (r8 ? 1 : 0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x017f, code lost:
    
        r7 = p161t5.C1923o.m2243g(r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0184, code lost:
    
        if (r11 != false) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0188, code lost:
    
        if (r6 != null) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x018c, code lost:
    
        r1 = new p161t5.C1922n.b(r15, r6, r7);
        r1.f5668f = r9;
        r15.f5656d.m2231b(r6, r1);
        r6 = r1.f5664b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x019a, code lost:
    
        if (r6 == null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01a4, code lost:
    
        if (r6.trim().length() <= 0) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01a6, code lost:
    
        r1.m2234a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01aa, code lost:
    
        r6 = new p161t5.C1914f();
        r1.f5667e = r6;
        r1.f5666d = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01b9, code lost:
    
        if ("base64".equalsIgnoreCase(r10) == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01bb, code lost:
    
        r6 = new p161t5.C1922n.a((p161t5.C1924p) r15.f5653a);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01cb, code lost:
    
        if ("quoted-printable".equalsIgnoreCase(r10) == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01cd, code lost:
    
        r6 = new p161t5.C1921m(r15, r15.f5653a);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01d5, code lost:
    
        r6 = r15.f5653a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01d7, code lost:
    
        r7 = -2;
        r8 = -2;
        r9 = false;
        r10 = false;
        r2 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01db, code lost:
    
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01dd, code lost:
    
        if (r8 == r7) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01df, code lost:
    
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01e1, code lost:
    
        r7 = r6.read();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v25, types: [java.lang.Object, t5.n$b] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v8, types: [int] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<p015b5.InterfaceC0469n> m2232a() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 803
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p161t5.C1922n.m2232a():java.util.Collection");
    }

    /* renamed from: b */
    public final String m2233b(String str) {
        return C1923o.m2242f(str.substring(str.indexOf(61) + 1).trim());
    }
}
