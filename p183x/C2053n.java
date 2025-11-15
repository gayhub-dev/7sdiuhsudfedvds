package p183x;

import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p009b.C0413b;
import p162u.EnumC1957a;
import p162u.InterfaceC1964h;

/* compiled from: GlideException.java */
/* renamed from: x.n */
/* loaded from: classes.dex */
public final class C2053n extends Exception {

    /* renamed from: i */
    public static final StackTraceElement[] f6108i = new StackTraceElement[0];

    /* renamed from: e */
    public final List<Exception> f6109e;

    /* renamed from: f */
    public InterfaceC1964h f6110f;

    /* renamed from: g */
    public EnumC1957a f6111g;

    /* renamed from: h */
    public Class<?> f6112h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2053n(String str) {
        super(str);
        List<Exception> listEmptyList = Collections.emptyList();
        setStackTrace(f6108i);
        this.f6109e = listEmptyList;
    }

    /* renamed from: b */
    public static void m2439b(List<Exception> list, Appendable appendable) {
        try {
            m2440c(list, appendable);
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: c */
    public static void m2440c(List<Exception> list, Appendable appendable) throws IOException {
        int size = list.size();
        int i7 = 0;
        while (i7 < size) {
            int i8 = i7 + 1;
            appendable.append("Cause (").append(String.valueOf(i8)).append(" of ").append(String.valueOf(size)).append("): ");
            Exception exc = list.get(i7);
            if (exc instanceof C2053n) {
                ((C2053n) exc).m2443e(appendable);
            } else {
                m2441d(exc, appendable);
            }
            i7 = i8;
        }
    }

    /* renamed from: d */
    public static void m2441d(Exception exc, Appendable appendable) throws IOException {
        try {
            appendable.append(exc.getClass().toString()).append(": ").append(exc.getMessage()).append('\n');
        } catch (IOException unused) {
            throw new RuntimeException(exc);
        }
    }

    /* renamed from: a */
    public final void m2442a(Exception exc, List<Exception> list) {
        if (!(exc instanceof C2053n)) {
            list.add(exc);
            return;
        }
        Iterator<Exception> it = ((C2053n) exc).f6109e.iterator();
        while (it.hasNext()) {
            m2442a(it.next(), list);
        }
    }

    /* renamed from: e */
    public final void m2443e(Appendable appendable) throws IOException {
        m2441d(this, appendable);
        m2439b(this.f6109e, new a(appendable));
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String string;
        String string2;
        StringBuilder sb = new StringBuilder();
        sb.append(super.getMessage());
        String string3 = "";
        if (this.f6112h != null) {
            StringBuilder sbM112a = C0413b.m112a(", ");
            sbM112a.append(this.f6112h);
            string = sbM112a.toString();
        } else {
            string = "";
        }
        sb.append(string);
        if (this.f6111g != null) {
            StringBuilder sbM112a2 = C0413b.m112a(", ");
            sbM112a2.append(this.f6111g);
            string2 = sbM112a2.toString();
        } else {
            string2 = "";
        }
        sb.append(string2);
        if (this.f6110f != null) {
            StringBuilder sbM112a3 = C0413b.m112a(", ");
            sbM112a3.append(this.f6110f);
            string3 = sbM112a3.toString();
        }
        sb.append(string3);
        return sb.toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace() throws IOException {
        m2443e(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) throws IOException {
        m2443e(printStream);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) throws IOException {
        m2443e(printWriter);
    }

    /* compiled from: GlideException.java */
    /* renamed from: x.n$a */
    public static final class a implements Appendable {

        /* renamed from: e */
        public final Appendable f6113e;

        /* renamed from: f */
        public boolean f6114f = true;

        public a(Appendable appendable) {
            this.f6113e = appendable;
        }

        @Override // java.lang.Appendable
        public Appendable append(char c7) throws IOException {
            if (this.f6114f) {
                this.f6114f = false;
                this.f6113e.append("  ");
            }
            this.f6114f = c7 == '\n';
            this.f6113e.append(c7);
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(@Nullable CharSequence charSequence) throws IOException {
            if (charSequence == null) {
                charSequence = "";
            }
            append(charSequence, 0, charSequence.length());
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(@Nullable CharSequence charSequence, int i7, int i8) throws IOException {
            if (charSequence == null) {
                charSequence = "";
            }
            boolean z6 = false;
            if (this.f6114f) {
                this.f6114f = false;
                this.f6113e.append("  ");
            }
            if (charSequence.length() > 0 && charSequence.charAt(i8 - 1) == '\n') {
                z6 = true;
            }
            this.f6114f = z6;
            this.f6113e.append(charSequence, i7, i8);
            return this;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2053n(String str, Exception exc) {
        super(str);
        List<Exception> listSingletonList = Collections.singletonList(exc);
        setStackTrace(f6108i);
        this.f6109e = listSingletonList;
    }

    public C2053n(String str, List<Exception> list) {
        super(str);
        setStackTrace(f6108i);
        this.f6109e = list;
    }
}
