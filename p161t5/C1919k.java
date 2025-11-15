package p161t5;

import java.io.PrintStream;
import java.io.PrintWriter;
import okhttp3.HttpUrl;

/* compiled from: MultiException.java */
/* renamed from: t5.k */
/* loaded from: classes.dex */
public class C1919k extends Exception {

    /* renamed from: e */
    public Object f5649e;

    public C1919k() {
        super("Multiple exceptions");
    }

    /* renamed from: a */
    public void m2229a(Throwable th) {
        if (!(th instanceof C1919k)) {
            this.f5649e = C1918j.m2222b(this.f5649e, th);
            return;
        }
        C1919k c1919k = (C1919k) th;
        for (int i7 = 0; i7 < C1918j.m2228x(c1919k.f5649e); i7++) {
            this.f5649e = C1918j.m2222b(this.f5649e, C1918j.m2225j(c1919k.f5649e, i7));
        }
    }

    /* renamed from: b */
    public void m2230b() throws Exception {
        int iM2228x = C1918j.m2228x(this.f5649e);
        if (iM2228x != 0) {
            if (iM2228x != 1) {
                throw this;
            }
            Throwable th = (Throwable) C1918j.m2225j(this.f5649e, 0);
            if (th instanceof Error) {
                throw ((Error) th);
            }
            if (!(th instanceof Exception)) {
                throw this;
            }
            throw ((Exception) th);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        super.printStackTrace();
        for (int i7 = 0; i7 < C1918j.m2228x(this.f5649e); i7++) {
            ((Throwable) C1918j.m2225j(this.f5649e, i7)).printStackTrace();
        }
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (C1918j.m2228x(this.f5649e) > 0) {
            return C1919k.class.getSimpleName() + C1918j.m2226k(this.f5649e, false);
        }
        return C1919k.class.getSimpleName() + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        for (int i7 = 0; i7 < C1918j.m2228x(this.f5649e); i7++) {
            ((Throwable) C1918j.m2225j(this.f5649e, i7)).printStackTrace(printStream);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        for (int i7 = 0; i7 < C1918j.m2228x(this.f5649e); i7++) {
            ((Throwable) C1918j.m2225j(this.f5649e, i7)).printStackTrace(printWriter);
        }
    }
}
