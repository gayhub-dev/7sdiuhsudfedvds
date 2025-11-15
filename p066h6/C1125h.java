package p066h6;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.fourthline.cling.model.ServiceReference;
import p016b6.AbstractC0475f;

/* compiled from: ZoneInfoProvider.java */
/* renamed from: h6.h */
/* loaded from: classes.dex */
public class C1125h implements InterfaceC1123f {

    /* renamed from: a */
    public final File f2443a;

    /* renamed from: b */
    public final String f2444b;

    /* renamed from: c */
    public final ClassLoader f2445c;

    /* renamed from: d */
    public final Map<String, Object> f2446d;

    /* renamed from: e */
    public final Set<String> f2447e;

    /* compiled from: ZoneInfoProvider.java */
    /* renamed from: h6.h$a */
    public class a implements PrivilegedAction<InputStream> {

        /* renamed from: a */
        public final /* synthetic */ String f2448a;

        public a(String str) {
            this.f2448a = str;
        }

        @Override // java.security.PrivilegedAction
        public InputStream run() {
            ClassLoader classLoader = C1125h.this.f2445c;
            return classLoader != null ? classLoader.getResourceAsStream(this.f2448a) : ClassLoader.getSystemResourceAsStream(this.f2448a);
        }
    }

    public C1125h(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("File directory doesn't exist: " + file);
        }
        if (!file.isDirectory()) {
            throw new IOException("File doesn't refer to a directory: " + file);
        }
        this.f2443a = file;
        this.f2444b = null;
        this.f2445c = null;
        Map<String, Object> mapM1284d = m1284d(m1287e("ZoneInfoMap"));
        this.f2446d = mapM1284d;
        this.f2447e = Collections.unmodifiableSortedSet(new TreeSet(((ConcurrentHashMap) mapM1284d).keySet()));
    }

    /* renamed from: d */
    public static Map<String, Object> m1284d(InputStream inputStream) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            m1285f(dataInputStream, concurrentHashMap);
            concurrentHashMap.put("UTC", new SoftReference(AbstractC0475f.f314f));
            return concurrentHashMap;
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: f */
    public static void m1285f(DataInputStream dataInputStream, Map<String, Object> map) throws IOException {
        int unsignedShort = dataInputStream.readUnsignedShort();
        String[] strArr = new String[unsignedShort];
        for (int i7 = 0; i7 < unsignedShort; i7++) {
            strArr[i7] = dataInputStream.readUTF().intern();
        }
        int unsignedShort2 = dataInputStream.readUnsignedShort();
        for (int i8 = 0; i8 < unsignedShort2; i8++) {
            try {
                map.put(strArr[dataInputStream.readUnsignedShort()], strArr[dataInputStream.readUnsignedShort()]);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }

    @Override // p066h6.InterfaceC1123f
    /* renamed from: a */
    public AbstractC0475f mo1282a(String str) {
        Object obj = this.f2446d.get(str);
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof SoftReference)) {
            return str.equals(obj) ? m1286c(str) : mo1282a((String) obj);
        }
        AbstractC0475f abstractC0475f = (AbstractC0475f) ((SoftReference) obj).get();
        return abstractC0475f != null ? abstractC0475f : m1286c(str);
    }

    @Override // p066h6.InterfaceC1123f
    /* renamed from: b */
    public Set<String> mo1283b() {
        return this.f2447e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0029: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:12:0x0029 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p016b6.AbstractC0475f m1286c(java.lang.String r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.InputStream r1 = r5.m1287e(r6)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L2f
            boolean r2 = r1 instanceof java.io.DataInput     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            if (r2 == 0) goto L11
            r2 = r1
            java.io.DataInput r2 = (java.io.DataInput) r2     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            b6.f r2 = p066h6.C1119b.m1266a(r2, r6)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            goto L1a
        L11:
            java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            b6.f r2 = p066h6.C1119b.m1266a(r2, r6)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
        L1a:
            java.util.Map<java.lang.String, java.lang.Object> r3 = r5.f2446d     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            java.lang.ref.SoftReference r4 = new java.lang.ref.SoftReference     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            r3.put(r6, r4)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2b
            r1.close()     // Catch: java.io.IOException -> L27
        L27:
            return r2
        L28:
            r6 = move-exception
            r0 = r1
            goto L3f
        L2b:
            r2 = move-exception
            goto L31
        L2d:
            r6 = move-exception
            goto L3f
        L2f:
            r2 = move-exception
            r1 = r0
        L31:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L28
            java.util.Map<java.lang.String, java.lang.Object> r2 = r5.f2446d     // Catch: java.lang.Throwable -> L28
            r2.remove(r6)     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L3e
            r1.close()     // Catch: java.io.IOException -> L3e
        L3e:
            return r0
        L3f:
            if (r0 == 0) goto L44
            r0.close()     // Catch: java.io.IOException -> L44
        L44:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p066h6.C1125h.m1286c(java.lang.String):b6.f");
    }

    /* renamed from: e */
    public final InputStream m1287e(String str) throws IOException {
        if (this.f2443a != null) {
            return new FileInputStream(new File(this.f2443a, str));
        }
        String strConcat = this.f2444b.concat(str);
        InputStream inputStream = (InputStream) AccessController.doPrivileged(new a(strConcat));
        if (inputStream != null) {
            return inputStream;
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append("Resource not found: \"");
        sb.append(strConcat);
        sb.append("\" ClassLoader: ");
        ClassLoader classLoader = this.f2445c;
        sb.append(classLoader != null ? classLoader.toString() : "system");
        throw new IOException(sb.toString());
    }

    public C1125h(String str) {
        if (!str.endsWith(ServiceReference.DELIMITER)) {
            str = str + '/';
        }
        this.f2443a = null;
        this.f2444b = str;
        this.f2445c = C1125h.class.getClassLoader();
        Map<String, Object> mapM1284d = m1284d(m1287e("ZoneInfoMap"));
        this.f2446d = mapM1284d;
        this.f2447e = Collections.unmodifiableSortedSet(new TreeSet(((ConcurrentHashMap) mapM1284d).keySet()));
    }
}
