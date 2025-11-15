package p036e0;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import p036e0.C0912k;
import p142r0.C1818c;
import p142r0.C1821f;
import p162u.C1966j;
import p162u.InterfaceC1967k;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2084b;
import p190y.InterfaceC2086d;

/* compiled from: StreamBitmapDecoder.java */
/* renamed from: e0.p */
/* loaded from: classes.dex */
public class C0917p implements InterfaceC1967k<InputStream, Bitmap> {

    /* renamed from: a */
    public final C0912k f1670a;

    /* renamed from: b */
    public final InterfaceC2084b f1671b;

    /* compiled from: StreamBitmapDecoder.java */
    /* renamed from: e0.p$a */
    public static class a implements C0912k.b {

        /* renamed from: a */
        public final C0916o f1672a;

        /* renamed from: b */
        public final C1818c f1673b;

        public a(C0916o c0916o, C1818c c1818c) {
            this.f1672a = c0916o;
            this.f1673b = c1818c;
        }

        @Override // p036e0.C0912k.b
        /* renamed from: a */
        public void mo845a(InterfaceC2086d interfaceC2086d, Bitmap bitmap) throws IOException {
            IOException iOException = this.f1673b.f5290f;
            if (iOException != null) {
                if (bitmap == null) {
                    throw iOException;
                }
                interfaceC2086d.mo2520e(bitmap);
                throw iOException;
            }
        }

        @Override // p036e0.C0912k.b
        /* renamed from: b */
        public void mo846b() {
            C0916o c0916o = this.f1672a;
            synchronized (c0916o) {
                c0916o.f1666g = c0916o.f1664e.length;
            }
        }
    }

    public C0917p(C0912k c0912k, InterfaceC2084b interfaceC2084b) {
        this.f1670a = c0912k;
        this.f1671b = interfaceC2084b;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: a */
    public boolean mo819a(InputStream inputStream, C1966j c1966j) {
        Objects.requireNonNull(this.f1670a);
        return true;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: b */
    public InterfaceC2057r<Bitmap> mo820b(InputStream inputStream, int i7, int i8, C1966j c1966j) {
        C0916o c0916o;
        boolean z6;
        C1818c c1818c;
        InputStream inputStream2 = inputStream;
        if (inputStream2 instanceof C0916o) {
            c0916o = (C0916o) inputStream2;
            z6 = false;
        } else {
            c0916o = new C0916o(inputStream2, this.f1671b);
            z6 = true;
        }
        Queue<C1818c> queue = C1818c.f5288g;
        synchronized (queue) {
            c1818c = (C1818c) ((ArrayDeque) queue).poll();
        }
        if (c1818c == null) {
            c1818c = new C1818c();
        }
        c1818c.f5289e = c0916o;
        try {
            return this.f1670a.m843a(new C1821f(c1818c), i7, i8, c1966j, new a(c0916o, c1818c));
        } finally {
            c1818c.m2049a();
            if (z6) {
                c0916o.m849b();
            }
        }
    }
}
