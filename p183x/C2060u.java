package p183x;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import p142r0.C1823h;

/* compiled from: ResourceRecycler.java */
/* renamed from: x.u */
/* loaded from: classes.dex */
public class C2060u {

    /* renamed from: a */
    public boolean f6141a;

    /* renamed from: b */
    public final Handler f6142b = new Handler(Looper.getMainLooper(), new a());

    /* compiled from: ResourceRecycler.java */
    /* renamed from: x.u$a */
    public static class a implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((InterfaceC2057r) message.obj).recycle();
            return true;
        }
    }

    /* renamed from: a */
    public void m2447a(InterfaceC2057r<?> interfaceC2057r) {
        C1823h.m2057a();
        if (this.f6141a) {
            this.f6142b.obtainMessage(1, interfaceC2057r).sendToTarget();
            return;
        }
        this.f6141a = true;
        interfaceC2057r.recycle();
        this.f6141a = false;
    }
}
