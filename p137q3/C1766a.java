package p137q3;

import android.arch.lifecycle.C0063n;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.cache.DiskLruCache;
import p009b.C0413b;

/* compiled from: DiskLruCache.java */
/* renamed from: q3.a */
/* loaded from: classes.dex */
public final class C1766a implements Closeable {

    /* renamed from: s */
    public static final Pattern f5011s = Pattern.compile("[a-z0-9_-]{1,64}");

    /* renamed from: t */
    public static final OutputStream f5012t = new b();

    /* renamed from: e */
    public final File f5013e;

    /* renamed from: f */
    public final File f5014f;

    /* renamed from: g */
    public final File f5015g;

    /* renamed from: h */
    public final File f5016h;

    /* renamed from: i */
    public final int f5017i;

    /* renamed from: j */
    public long f5018j;

    /* renamed from: k */
    public final int f5019k;

    /* renamed from: m */
    public Writer f5021m;

    /* renamed from: o */
    public int f5023o;

    /* renamed from: l */
    public long f5020l = 0;

    /* renamed from: n */
    public final LinkedHashMap<String, d> f5022n = new LinkedHashMap<>(0, 0.75f, true);

    /* renamed from: p */
    public long f5024p = 0;

    /* renamed from: q */
    public final ThreadPoolExecutor f5025q = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: r */
    public final Callable<Void> f5026r = new a();

    /* compiled from: DiskLruCache.java */
    /* renamed from: q3.a$a */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        public Void call() {
            synchronized (C1766a.this) {
                C1766a c1766a = C1766a.this;
                if (c1766a.f5021m == null) {
                    return null;
                }
                c1766a.m1942n();
                if (C1766a.this.m1936f()) {
                    C1766a.this.m1940k();
                    C1766a.this.f5023o = 0;
                }
                return null;
            }
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: q3.a$b */
    public static class b extends OutputStream {
        @Override // java.io.OutputStream
        public void write(int i7) {
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: q3.a$d */
    public final class d {

        /* renamed from: a */
        public final String f5033a;

        /* renamed from: b */
        public final long[] f5034b;

        /* renamed from: c */
        public boolean f5035c;

        /* renamed from: d */
        public c f5036d;

        /* renamed from: e */
        public long f5037e;

        public d(String str, a aVar) {
            this.f5033a = str;
            this.f5034b = new long[C1766a.this.f5019k];
        }

        /* renamed from: a */
        public File m1948a(int i7) {
            return new File(C1766a.this.f5013e, this.f5033a + "." + i7);
        }

        /* renamed from: b */
        public File m1949b(int i7) {
            return new File(C1766a.this.f5013e, this.f5033a + "." + i7 + ".tmp");
        }

        /* renamed from: c */
        public String m1950c() {
            StringBuilder sb = new StringBuilder();
            for (long j7 : this.f5034b) {
                sb.append(' ');
                sb.append(j7);
            }
            return sb.toString();
        }

        /* renamed from: d */
        public final IOException m1951d(String[] strArr) throws IOException {
            StringBuilder sbM112a = C0413b.m112a("unexpected journal line: ");
            sbM112a.append(Arrays.toString(strArr));
            throw new IOException(sbM112a.toString());
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: q3.a$e */
    public final class e implements Closeable {

        /* renamed from: e */
        public final InputStream[] f5039e;

        public e(C1766a c1766a, String str, long j7, InputStream[] inputStreamArr, long[] jArr, a aVar) {
            this.f5039e = inputStreamArr;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            for (InputStream inputStream : this.f5039e) {
                C1768c.m1954a(inputStream);
            }
        }
    }

    public C1766a(File file, int i7, int i8, long j7) {
        this.f5013e = file;
        this.f5017i = i7;
        this.f5014f = new File(file, DiskLruCache.JOURNAL_FILE);
        this.f5015g = new File(file, DiskLruCache.JOURNAL_FILE_TEMP);
        this.f5016h = new File(file, DiskLruCache.JOURNAL_FILE_BACKUP);
        this.f5019k = i8;
        this.f5018j = j7;
    }

    /* renamed from: a */
    public static void m1929a(C1766a c1766a, c cVar, boolean z6) {
        synchronized (c1766a) {
            d dVar = cVar.f5028a;
            if (dVar.f5036d != cVar) {
                throw new IllegalStateException();
            }
            if (z6 && !dVar.f5035c) {
                for (int i7 = 0; i7 < c1766a.f5019k; i7++) {
                    if (!cVar.f5029b[i7]) {
                        cVar.m1944a();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i7);
                    }
                    if (!dVar.m1949b(i7).exists()) {
                        cVar.m1944a();
                        return;
                    }
                }
            }
            for (int i8 = 0; i8 < c1766a.f5019k; i8++) {
                File fileM1949b = dVar.m1949b(i8);
                if (!z6) {
                    m1930c(fileM1949b);
                } else if (fileM1949b.exists()) {
                    File fileM1948a = dVar.m1948a(i8);
                    fileM1949b.renameTo(fileM1948a);
                    long j7 = dVar.f5034b[i8];
                    long length = fileM1948a.length();
                    dVar.f5034b[i8] = length;
                    c1766a.f5020l = (c1766a.f5020l - j7) + length;
                }
            }
            c1766a.f5023o++;
            dVar.f5036d = null;
            if (dVar.f5035c || z6) {
                dVar.f5035c = true;
                c1766a.f5021m.write("CLEAN " + dVar.f5033a + dVar.m1950c() + '\n');
                if (z6) {
                    long j8 = c1766a.f5024p;
                    c1766a.f5024p = 1 + j8;
                    dVar.f5037e = j8;
                }
            } else {
                c1766a.f5022n.remove(dVar.f5033a);
                c1766a.f5021m.write("REMOVE " + dVar.f5033a + '\n');
            }
            c1766a.f5021m.flush();
            if (c1766a.f5020l > c1766a.f5018j || c1766a.m1936f()) {
                c1766a.f5025q.submit(c1766a.f5026r);
            }
        }
    }

    /* renamed from: c */
    public static void m1930c(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: g */
    public static C1766a m1931g(File file, int i7, int i8, long j7) throws IOException {
        if (j7 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i8 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, DiskLruCache.JOURNAL_FILE_BACKUP);
        if (file2.exists()) {
            File file3 = new File(file, DiskLruCache.JOURNAL_FILE);
            if (file3.exists()) {
                file2.delete();
            } else {
                m1932m(file2, file3, false);
            }
        }
        C1766a c1766a = new C1766a(file, i7, i8, j7);
        if (c1766a.f5014f.exists()) {
            try {
                c1766a.m1938i();
                c1766a.m1937h();
                c1766a.f5021m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(c1766a.f5014f, true), C1768c.f5046a));
                return c1766a;
            } catch (IOException e7) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e7.getMessage() + ", removing");
                c1766a.close();
                C1768c.m1955b(c1766a.f5013e);
            }
        }
        file.mkdirs();
        C1766a c1766a2 = new C1766a(file, i7, i8, j7);
        c1766a2.m1940k();
        return c1766a2;
    }

    /* renamed from: m */
    public static void m1932m(File file, File file2, boolean z6) throws IOException {
        if (z6) {
            m1930c(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: b */
    public final void m1933b() {
        if (this.f5021m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.f5021m == null) {
            return;
        }
        Iterator it = new ArrayList(this.f5022n.values()).iterator();
        while (it.hasNext()) {
            c cVar = ((d) it.next()).f5036d;
            if (cVar != null) {
                cVar.m1944a();
            }
        }
        m1942n();
        this.f5021m.close();
        this.f5021m = null;
    }

    /* renamed from: d */
    public c m1934d(String str) {
        synchronized (this) {
            m1933b();
            m1943o(str);
            d dVar = this.f5022n.get(str);
            if (dVar == null) {
                dVar = new d(str, null);
                this.f5022n.put(str, dVar);
            } else if (dVar.f5036d != null) {
                return null;
            }
            c cVar = new c(dVar, null);
            dVar.f5036d = cVar;
            this.f5021m.write("DIRTY " + str + '\n');
            this.f5021m.flush();
            return cVar;
        }
    }

    /* renamed from: e */
    public synchronized e m1935e(String str) {
        m1933b();
        m1943o(str);
        d dVar = this.f5022n.get(str);
        if (dVar == null) {
            return null;
        }
        if (!dVar.f5035c) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.f5019k];
        for (int i7 = 0; i7 < this.f5019k; i7++) {
            try {
                inputStreamArr[i7] = new FileInputStream(dVar.m1948a(i7));
            } catch (FileNotFoundException unused) {
                for (int i8 = 0; i8 < this.f5019k && inputStreamArr[i8] != null; i8++) {
                    C1768c.m1954a(inputStreamArr[i8]);
                }
                return null;
            }
        }
        this.f5023o++;
        this.f5021m.append((CharSequence) ("READ " + str + '\n'));
        if (m1936f()) {
            this.f5025q.submit(this.f5026r);
        }
        return new e(this, str, dVar.f5037e, inputStreamArr, dVar.f5034b, null);
    }

    /* renamed from: f */
    public final boolean m1936f() {
        int i7 = this.f5023o;
        return i7 >= 2000 && i7 >= this.f5022n.size();
    }

    /* renamed from: h */
    public final void m1937h() throws IOException {
        m1930c(this.f5015g);
        Iterator<d> it = this.f5022n.values().iterator();
        while (it.hasNext()) {
            d next = it.next();
            int i7 = 0;
            if (next.f5036d == null) {
                while (i7 < this.f5019k) {
                    this.f5020l += next.f5034b[i7];
                    i7++;
                }
            } else {
                next.f5036d = null;
                while (i7 < this.f5019k) {
                    m1930c(next.m1948a(i7));
                    m1930c(next.m1949b(i7));
                    i7++;
                }
                it.remove();
            }
        }
    }

    /* renamed from: i */
    public final void m1938i() throws IOException {
        C1767b c1767b = new C1767b(new FileInputStream(this.f5014f), C1768c.f5046a);
        try {
            String strM1953b = c1767b.m1953b();
            String strM1953b2 = c1767b.m1953b();
            String strM1953b3 = c1767b.m1953b();
            String strM1953b4 = c1767b.m1953b();
            String strM1953b5 = c1767b.m1953b();
            if (!DiskLruCache.MAGIC.equals(strM1953b) || !DiskLruCache.VERSION_1.equals(strM1953b2) || !Integer.toString(this.f5017i).equals(strM1953b3) || !Integer.toString(this.f5019k).equals(strM1953b4) || !"".equals(strM1953b5)) {
                throw new IOException("unexpected journal header: [" + strM1953b + ", " + strM1953b2 + ", " + strM1953b4 + ", " + strM1953b5 + "]");
            }
            int i7 = 0;
            while (true) {
                try {
                    m1939j(c1767b.m1953b());
                    i7++;
                } catch (EOFException unused) {
                    this.f5023o = i7 - this.f5022n.size();
                    C1768c.m1954a(c1767b);
                    return;
                }
            }
        } catch (Throwable th) {
            C1768c.m1954a(c1767b);
            throw th;
        }
    }

    /* renamed from: j */
    public final void m1939j(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException(C0063n.m88a("unexpected journal line: ", str));
        }
        int i7 = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i7);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i7);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.f5022n.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i7, iIndexOf2);
        }
        d dVar = this.f5022n.get(strSubstring);
        if (dVar == null) {
            dVar = new d(strSubstring, null);
            this.f5022n.put(strSubstring, dVar);
        }
        if (iIndexOf2 == -1 || iIndexOf != 5 || !str.startsWith("CLEAN")) {
            if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
                dVar.f5036d = new c(dVar, null);
                return;
            } else {
                if (iIndexOf2 != -1 || iIndexOf != 4 || !str.startsWith("READ")) {
                    throw new IOException(C0063n.m88a("unexpected journal line: ", str));
                }
                return;
            }
        }
        String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
        dVar.f5035c = true;
        dVar.f5036d = null;
        if (strArrSplit.length != C1766a.this.f5019k) {
            dVar.m1951d(strArrSplit);
            throw null;
        }
        for (int i8 = 0; i8 < strArrSplit.length; i8++) {
            try {
                dVar.f5034b[i8] = Long.parseLong(strArrSplit[i8]);
            } catch (NumberFormatException unused) {
                dVar.m1951d(strArrSplit);
                throw null;
            }
        }
    }

    /* renamed from: k */
    public final synchronized void m1940k() {
        Writer writer = this.f5021m;
        if (writer != null) {
            writer.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f5015g), C1768c.f5046a));
        try {
            bufferedWriter.write(DiskLruCache.MAGIC);
            bufferedWriter.write("\n");
            bufferedWriter.write(DiskLruCache.VERSION_1);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f5017i));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f5019k));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (d dVar : this.f5022n.values()) {
                if (dVar.f5036d != null) {
                    bufferedWriter.write("DIRTY " + dVar.f5033a + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + dVar.f5033a + dVar.m1950c() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f5014f.exists()) {
                m1932m(this.f5014f, this.f5016h, true);
            }
            m1932m(this.f5015g, this.f5014f, false);
            this.f5016h.delete();
            this.f5021m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f5014f, true), C1768c.f5046a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* renamed from: l */
    public synchronized boolean m1941l(String str) {
        m1933b();
        m1943o(str);
        d dVar = this.f5022n.get(str);
        if (dVar != null && dVar.f5036d == null) {
            for (int i7 = 0; i7 < this.f5019k; i7++) {
                File fileM1948a = dVar.m1948a(i7);
                if (fileM1948a.exists() && !fileM1948a.delete()) {
                    throw new IOException("failed to delete " + fileM1948a);
                }
                long j7 = this.f5020l;
                long[] jArr = dVar.f5034b;
                this.f5020l = j7 - jArr[i7];
                jArr[i7] = 0;
            }
            this.f5023o++;
            this.f5021m.append((CharSequence) ("REMOVE " + str + '\n'));
            this.f5022n.remove(str);
            if (m1936f()) {
                this.f5025q.submit(this.f5026r);
            }
            return true;
        }
        return false;
    }

    /* renamed from: n */
    public final void m1942n() {
        while (this.f5020l > this.f5018j) {
            m1941l(this.f5022n.entrySet().iterator().next().getKey());
        }
    }

    /* renamed from: o */
    public final void m1943o(String str) {
        if (!f5011s.matcher(str).matches()) {
            throw new IllegalArgumentException(C0096a.m97a("keys must match regex [a-z0-9_-]{1,64}: \"", str, "\""));
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: q3.a$c */
    public final class c {

        /* renamed from: a */
        public final d f5028a;

        /* renamed from: b */
        public final boolean[] f5029b;

        /* renamed from: c */
        public boolean f5030c;

        public c(d dVar, a aVar) {
            this.f5028a = dVar;
            this.f5029b = dVar.f5035c ? null : new boolean[C1766a.this.f5019k];
        }

        /* renamed from: a */
        public void m1944a() {
            C1766a.m1929a(C1766a.this, this, false);
        }

        /* renamed from: b */
        public void m1945b() {
            if (!this.f5030c) {
                C1766a.m1929a(C1766a.this, this, true);
            } else {
                C1766a.m1929a(C1766a.this, this, false);
                C1766a.this.m1941l(this.f5028a.f5033a);
            }
        }

        /* renamed from: c */
        public InputStream m1946c(int i7) {
            synchronized (C1766a.this) {
                d dVar = this.f5028a;
                if (dVar.f5036d != this) {
                    throw new IllegalStateException();
                }
                if (!dVar.f5035c) {
                    return null;
                }
                try {
                    return new FileInputStream(this.f5028a.m1948a(i7));
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }
        }

        /* renamed from: d */
        public OutputStream m1947d(int i7) {
            FileOutputStream fileOutputStream;
            a aVar;
            synchronized (C1766a.this) {
                d dVar = this.f5028a;
                if (dVar.f5036d != this) {
                    throw new IllegalStateException();
                }
                if (!dVar.f5035c) {
                    this.f5029b[i7] = true;
                }
                File fileM1949b = dVar.m1949b(i7);
                try {
                    fileOutputStream = new FileOutputStream(fileM1949b);
                } catch (FileNotFoundException unused) {
                    C1766a.this.f5013e.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(fileM1949b);
                    } catch (FileNotFoundException unused2) {
                        return C1766a.f5012t;
                    }
                }
                aVar = new a(fileOutputStream, null);
            }
            return aVar;
        }

        /* compiled from: DiskLruCache.java */
        /* renamed from: q3.a$c$a */
        public class a extends FilterOutputStream {
            public a(OutputStream outputStream, a aVar) {
                super(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    ((FilterOutputStream) this).out.close();
                } catch (IOException unused) {
                    c.this.f5030c = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                try {
                    ((FilterOutputStream) this).out.flush();
                } catch (IOException unused) {
                    c.this.f5030c = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i7) throws IOException {
                try {
                    ((FilterOutputStream) this).out.write(i7);
                } catch (IOException unused) {
                    c.this.f5030c = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i7, int i8) throws IOException {
                try {
                    ((FilterOutputStream) this).out.write(bArr, i7, i8);
                } catch (IOException unused) {
                    c.this.f5030c = true;
                }
            }
        }
    }
}
