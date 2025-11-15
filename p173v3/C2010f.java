package p173v3;

/* compiled from: OutsideScopeException.java */
/* renamed from: v3.f */
/* loaded from: classes.dex */
public class C2010f extends RuntimeException {
    public C2010f(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return this;
    }
}
