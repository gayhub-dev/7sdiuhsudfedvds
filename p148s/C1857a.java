package p148s;

import android.arch.lifecycle.C0063n;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.cache.DiskLruCache;
import p009b.C0413b;

/* compiled from: DiskLruCache.java */
/* renamed from: s.a */
/* loaded from: classes.dex */
public final class C1857a implements Closeable {

    /* renamed from: e */
    public final File f5399e;

    /* renamed from: f */
    public final File f5400f;

    /* renamed from: g */
    public final File f5401g;

    /* renamed from: h */
    public final File f5402h;

    /* renamed from: i */
    public final int f5403i;

    /* renamed from: j */
    public long f5404j;

    /* renamed from: k */
    public final int f5405k;

    /* renamed from: m */
    public Writer f5407m;

    /* renamed from: o */
    public int f5409o;

    /* renamed from: l */
    public long f5406l = 0;

    /* renamed from: n */
    public final LinkedHashMap<String, d> f5408n = new LinkedHashMap<>(0, 0.75f, true);

    /* renamed from: p */
    public long f5410p = 0;

    /* renamed from: q */
    public final ThreadPoolExecutor f5411q = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new b(null));

    /* renamed from: r */
    public final Callable<Void> f5412r = new a();

    /* compiled from: DiskLruCache.java */
    /* renamed from: s.a$a */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        public Void call() {
            synchronized (C1857a.this) {
                C1857a c1857a = C1857a.this;
                if (c1857a.f5407m == null) {
                    return null;
                }
                c1857a.m2123m();
                if (C1857a.this.m2118f()) {
                    C1857a.this.m2122k();
                    C1857a.this.f5409o = 0;
                }
                return null;
            }
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: s.a$b */
    public static final class b implements ThreadFactory {
        public b(a aVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: s.a$c */
    public final class c {

        /* renamed from: a */
        public final d f5414a;

        /* renamed from: b */
        public final boolean[] f5415b;

        /* renamed from: c */
        public boolean f5416c;

        public c(d dVar, a aVar) {
            this.f5414a = dVar;
            this.f5415b = dVar.f5422e ? null : new boolean[C1857a.this.f5405k];
        }

        /* renamed from: a */
        public void m2124a() {
            C1857a.m2111a(C1857a.this, this, false);
        }

        /* renamed from: b */
        public File m2125b(int i7) {
            File file;
            synchronized (C1857a.this) {
                d dVar = this.f5414a;
                if (dVar.f5423f != this) {
                    throw new IllegalStateException();
                }
                if (!dVar.f5422e) {
                    this.f5415b[i7] = true;
                }
                file = dVar.f5421d[i7];
                if (!C1857a.this.f5399e.exists()) {
                    C1857a.this.f5399e.mkdirs();
                }
            }
            return file;
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: s.a$d */
    public final class d {

        /* renamed from: a */
        public final String f5418a;

        /* renamed from: b */
        public final long[] f5419b;

        /* renamed from: c */
        public File[] f5420c;

        /* renamed from: d */
        public File[] f5421d;

        /* renamed from: e */
        public boolean f5422e;

        /* renamed from: f */
        public c f5423f;

        /* renamed from: g */
        public long f5424g;

        public d(String str, a aVar) {
            this.f5418a = str;
            int i7 = C1857a.this.f5405k;
            this.f5419b = new long[i7];
            this.f5420c = new File[i7];
            this.f5421d = new File[i7];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i8 = 0; i8 < C1857a.this.f5405k; i8++) {
                sb.append(i8);
                this.f5420c[i8] = new File(C1857a.this.f5399e, sb.toString());
                sb.append(".tmp");
                this.f5421d[i8] = new File(C1857a.this.f5399e, sb.toString());
                sb.setLength(length);
            }
        }

        /* renamed from: a */
        public String m2126a() {
            StringBuilder sb = new StringBuilder();
            for (long j7 : this.f5419b) {
                sb.append(' ');
                sb.append(j7);
            }
            return sb.toString();
        }

        /* renamed from: b */
        public final IOException m2127b(String[] strArr) throws IOException {
            StringBuilder sbM112a = C0413b.m112a("unexpected journal line: ");
            sbM112a.append(Arrays.toString(strArr));
            throw new IOException(sbM112a.toString());
        }
    }

    /* compiled from: DiskLruCache.java */
    /* renamed from: s.a$e */
    public final class e {

        /* renamed from: a */
        public final File[] f5426a;

        public e(C1857a c1857a, String str, long j7, File[] fileArr, long[] jArr, a aVar) {
            this.f5426a = fileArr;
        }
    }

    public C1857a(File file, int i7, int i8, long j7) {
        this.f5399e = file;
        this.f5403i = i7;
        this.f5400f = new File(file, DiskLruCache.JOURNAL_FILE);
        this.f5401g = new File(file, DiskLruCache.JOURNAL_FILE_TEMP);
        this.f5402h = new File(file, DiskLruCache.JOURNAL_FILE_BACKUP);
        this.f5405k = i8;
        this.f5404j = j7;
    }

    /* renamed from: a */
    public static void m2111a(C1857a c1857a, c cVar, boolean z6) {
        synchronized (c1857a) {
            d dVar = cVar.f5414a;
            if (dVar.f5423f != cVar) {
                throw new IllegalStateException();
            }
            if (z6 && !dVar.f5422e) {
                for (int i7 = 0; i7 < c1857a.f5405k; i7++) {
                    if (!cVar.f5415b[i7]) {
                        cVar.m2124a();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i7);
                    }
                    if (!dVar.f5421d[i7].exists()) {
                        cVar.m2124a();
                        return;
                    }
                }
            }
            for (int i8 = 0; i8 < c1857a.f5405k; i8++) {
                File file = dVar.f5421d[i8];
                if (!z6) {
                    m2112c(file);
                } else if (file.exists()) {
                    File file2 = dVar.f5420c[i8];
                    file.renameTo(file2);
                    long j7 = dVar.f5419b[i8];
                    long length = file2.length();
                    dVar.f5419b[i8] = length;
                    c1857a.f5406l = (c1857a.f5406l - j7) + length;
                }
            }
            c1857a.f5409o++;
            dVar.f5423f = null;
            if (dVar.f5422e || z6) {
                dVar.f5422e = true;
                c1857a.f5407m.append((CharSequence) "CLEAN");
                c1857a.f5407m.append(' ');
                c1857a.f5407m.append((CharSequence) dVar.f5418a);
                c1857a.f5407m.append((CharSequence) dVar.m2126a());
                c1857a.f5407m.append('\n');
                if (z6) {
                    long j8 = c1857a.f5410p;
                    c1857a.f5410p = 1 + j8;
                    dVar.f5424g = j8;
                }
            } else {
                c1857a.f5408n.remove(dVar.f5418a);
                c1857a.f5407m.append((CharSequence) "REMOVE");
                c1857a.f5407m.append(' ');
                c1857a.f5407m.append((CharSequence) dVar.f5418a);
                c1857a.f5407m.append('\n');
            }
            c1857a.f5407m.flush();
            if (c1857a.f5406l > c1857a.f5404j || c1857a.m2118f()) {
                c1857a.f5411q.submit(c1857a.f5412r);
            }
        }
    }

    /* renamed from: c */
    public static void m2112c(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: g */
    public static C1857a m2113g(File file, int i7, int i8, long j7) throws IOException {
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
                m2114l(file2, file3, false);
            }
        }
        C1857a c1857a = new C1857a(file, i7, i8, j7);
        if (c1857a.f5400f.exists()) {
            try {
                c1857a.m2120i();
                c1857a.m2119h();
                return c1857a;
            } catch (IOException e7) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e7.getMessage() + ", removing");
                c1857a.close();
                C1859c.m2130a(c1857a.f5399e);
            }
        }
        file.mkdirs();
        C1857a c1857a2 = new C1857a(file, i7, i8, j7);
        c1857a2.m2122k();
        return c1857a2;
    }

    /* renamed from: l */
    public static void m2114l(File file, File file2, boolean z6) throws IOException {
        if (z6) {
            m2112c(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: b */
    public final void m2115b() {
        if (this.f5407m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.f5407m == null) {
            return;
        }
        Iterator it = new ArrayList(this.f5408n.values()).iterator();
        while (it.hasNext()) {
            c cVar = ((d) it.next()).f5423f;
            if (cVar != null) {
                cVar.m2124a();
            }
        }
        m2123m();
        this.f5407m.close();
        this.f5407m = null;
    }

    /* renamed from: d */
    public c m2116d(String str) {
        synchronized (this) {
            m2115b();
            d dVar = this.f5408n.get(str);
            if (dVar == null) {
                dVar = new d(str, null);
                this.f5408n.put(str, dVar);
            } else if (dVar.f5423f != null) {
                return null;
            }
            c cVar = new c(dVar, null);
            dVar.f5423f = cVar;
            this.f5407m.append((CharSequence) "DIRTY");
            this.f5407m.append(' ');
            this.f5407m.append((CharSequence) str);
            this.f5407m.append('\n');
            this.f5407m.flush();
            return cVar;
        }
    }

    /* renamed from: e */
    public synchronized e m2117e(String str) {
        m2115b();
        d dVar = this.f5408n.get(str);
        if (dVar == null) {
            return null;
        }
        if (!dVar.f5422e) {
            return null;
        }
        for (File file : dVar.f5420c) {
            if (!file.exists()) {
                return null;
            }
        }
        this.f5409o++;
        this.f5407m.append((CharSequence) "READ");
        this.f5407m.append(' ');
        this.f5407m.append((CharSequence) str);
        this.f5407m.append('\n');
        if (m2118f()) {
            this.f5411q.submit(this.f5412r);
        }
        return new e(this, str, dVar.f5424g, dVar.f5420c, dVar.f5419b, null);
    }

    /* renamed from: f */
    public final boolean m2118f() {
        int i7 = this.f5409o;
        return i7 >= 2000 && i7 >= this.f5408n.size();
    }

    /* renamed from: h */
    public final void m2119h() throws IOException {
        m2112c(this.f5401g);
        Iterator<d> it = this.f5408n.values().iterator();
        while (it.hasNext()) {
            d next = it.next();
            int i7 = 0;
            if (next.f5423f == null) {
                while (i7 < this.f5405k) {
                    this.f5406l += next.f5419b[i7];
                    i7++;
                }
            } else {
                next.f5423f = null;
                while (i7 < this.f5405k) {
                    m2112c(next.f5420c[i7]);
                    m2112c(next.f5421d[i7]);
                    i7++;
                }
                it.remove();
            }
        }
    }

    /* renamed from: i */
    public final void m2120i() {
        C1858b c1858b = new C1858b(new FileInputStream(this.f5400f), C1859c.f5433a);
        try {
            String strM2129b = c1858b.m2129b();
            String strM2129b2 = c1858b.m2129b();
            String strM2129b3 = c1858b.m2129b();
            String strM2129b4 = c1858b.m2129b();
            String strM2129b5 = c1858b.m2129b();
            if (!DiskLruCache.MAGIC.equals(strM2129b) || !DiskLruCache.VERSION_1.equals(strM2129b2) || !Integer.toString(this.f5403i).equals(strM2129b3) || !Integer.toString(this.f5405k).equals(strM2129b4) || !"".equals(strM2129b5)) {
                throw new IOException("unexpected journal header: [" + strM2129b + ", " + strM2129b2 + ", " + strM2129b4 + ", " + strM2129b5 + "]");
            }
            int i7 = 0;
            while (true) {
                try {
                    m2121j(c1858b.m2129b());
                    i7++;
                } catch (EOFException unused) {
                    this.f5409o = i7 - this.f5408n.size();
                    if (c1858b.f5431i == -1) {
                        m2122k();
                    } else {
                        this.f5407m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f5400f, true), C1859c.f5433a));
                    }
                    try {
                        c1858b.close();
                        return;
                    } catch (RuntimeException e7) {
                        throw e7;
                    } catch (Exception unused2) {
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            try {
                c1858b.close();
            } catch (RuntimeException e8) {
                throw e8;
            } catch (Exception unused3) {
            }
            throw th;
        }
    }

    /* renamed from: j */
    public final void m2121j(String str) throws IOException {
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
                this.f5408n.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i7, iIndexOf2);
        }
        d dVar = this.f5408n.get(strSubstring);
        if (dVar == null) {
            dVar = new d(strSubstring, null);
            this.f5408n.put(strSubstring, dVar);
        }
        if (iIndexOf2 == -1 || iIndexOf != 5 || !str.startsWith("CLEAN")) {
            if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
                dVar.f5423f = new c(dVar, null);
                return;
            } else {
                if (iIndexOf2 != -1 || iIndexOf != 4 || !str.startsWith("READ")) {
                    throw new IOException(C0063n.m88a("unexpected journal line: ", str));
                }
                return;
            }
        }
        String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
        dVar.f5422e = true;
        dVar.f5423f = null;
        if (strArrSplit.length != C1857a.this.f5405k) {
            dVar.m2127b(strArrSplit);
            throw null;
        }
        for (int i8 = 0; i8 < strArrSplit.length; i8++) {
            try {
                dVar.f5419b[i8] = Long.parseLong(strArrSplit[i8]);
            } catch (NumberFormatException unused) {
                dVar.m2127b(strArrSplit);
                throw null;
            }
        }
    }

    /* renamed from: k */
    public final synchronized void m2122k() {
        Writer writer = this.f5407m;
        if (writer != null) {
            writer.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f5401g), C1859c.f5433a));
        try {
            bufferedWriter.write(DiskLruCache.MAGIC);
            bufferedWriter.write("\n");
            bufferedWriter.write(DiskLruCache.VERSION_1);
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f5403i));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f5405k));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (d dVar : this.f5408n.values()) {
                if (dVar.f5423f != null) {
                    bufferedWriter.write("DIRTY " + dVar.f5418a + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + dVar.f5418a + dVar.m2126a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f5400f.exists()) {
                m2114l(this.f5400f, this.f5402h, true);
            }
            m2114l(this.f5401g, this.f5400f, false);
            this.f5402h.delete();
            this.f5407m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f5400f, true), C1859c.f5433a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* renamed from: m */
    public final void m2123m() {
        while (this.f5406l > this.f5404j) {
            String key = this.f5408n.entrySet().iterator().next().getKey();
            synchronized (this) {
                m2115b();
                d dVar = this.f5408n.get(key);
                if (dVar != null && dVar.f5423f == null) {
                    for (int i7 = 0; i7 < this.f5405k; i7++) {
                        File file = dVar.f5420c[i7];
                        if (file.exists() && !file.delete()) {
                            throw new IOException("failed to delete " + file);
                        }
                        long j7 = this.f5406l;
                        long[] jArr = dVar.f5419b;
                        this.f5406l = j7 - jArr[i7];
                        jArr[i7] = 0;
                    }
                    this.f5409o++;
                    this.f5407m.append((CharSequence) "REMOVE");
                    this.f5407m.append(' ');
                    this.f5407m.append((CharSequence) key);
                    this.f5407m.append('\n');
                    this.f5408n.remove(key);
                    if (m2118f()) {
                        this.f5411q.submit(this.f5412r);
                    }
                }
            }
        }
    }
}
