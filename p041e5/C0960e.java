package p041e5;

import p065h5.C1101i;
import p073i5.InterfaceC1152e;

/* compiled from: CachedExchange.java */
/* renamed from: e5.e */
/* loaded from: classes.dex */
public class C0960e extends C0965j {
    private final C1101i _responseFields;
    private volatile int _responseStatus;

    public C0960e(boolean z6) {
        this._responseFields = z6 ? new C1101i() : null;
    }

    public synchronized C1101i getResponseFields() {
        if (getStatus() < 6) {
            throw new IllegalStateException("Headers not completely received yet");
        }
        return this._responseFields;
    }

    public synchronized int getResponseStatus() {
        if (getStatus() < 5) {
            throw new IllegalStateException("Response not received yet");
        }
        return this._responseStatus;
    }

    @Override // p041e5.C0965j
    public synchronized void onResponseHeader(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        C1101i c1101i = this._responseFields;
        if (c1101i != null) {
            c1101i.m1222a(interfaceC1152e, interfaceC1152e2.mo1325T());
        }
        super.onResponseHeader(interfaceC1152e, interfaceC1152e2);
    }

    @Override // p041e5.C0965j
    public synchronized void onResponseStatus(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2) {
        this._responseStatus = i7;
        super.onResponseStatus(interfaceC1152e, i7, interfaceC1152e2);
    }
}
