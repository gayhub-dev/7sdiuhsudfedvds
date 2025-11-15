package p192y1;

import android.os.Looper;
import okhttp3.logging.C1638a;
import okhttp3.logging.HttpLoggingInterceptor;
import p014b4.InterfaceC0445e;
import p014b4.InterfaceC0446f;
import p180w3.C2027a;
import p186x2.C2073a;

/* renamed from: y1.g */
/* loaded from: classes.dex */
public final /* synthetic */ class C2103g implements InterfaceC0446f, InterfaceC0445e, HttpLoggingInterceptor.Logger {

    /* renamed from: e */
    public static final /* synthetic */ C2103g f6234e = new C2103g(0);

    /* renamed from: f */
    public static final /* synthetic */ C2103g f6235f = new C2103g(1);

    /* renamed from: g */
    public static final /* synthetic */ C2103g f6236g = new C2103g(2);

    /* renamed from: h */
    public static final /* synthetic */ C2103g f6237h = new C2103g(3);

    public /* synthetic */ C2103g(int i7) {
    }

    /* renamed from: a */
    public boolean m2546a() {
        InterfaceC0445e interfaceC0445e = C2027a.f5895a;
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @Override // p014b4.InterfaceC0446f
    public void accept(Object obj) {
        Throwable th = (Throwable) obj;
        th.printStackTrace();
        C2073a.m2456a("MyApplication setRxJavaErrorHandler " + th.getMessage());
    }

    @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
    public void log(String str) {
        C1638a.m1867b(str);
    }
}
