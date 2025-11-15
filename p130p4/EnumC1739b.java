package p130p4;

import p040e4.InterfaceC0952e;

/* compiled from: EmptySubscription.java */
/* renamed from: p4.b */
/* loaded from: classes.dex */
public enum EnumC1739b implements InterfaceC0952e<Object> {
    INSTANCE;

    @Override // p074i6.InterfaceC1169c
    public void cancel() {
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return true;
    }

    @Override // p040e4.InterfaceC0955h
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p040e4.InterfaceC0955h
    public Object poll() {
        return null;
    }

    @Override // p074i6.InterfaceC1169c
    public void request(long j7) {
        EnumC1740c.m1889a(j7);
    }

    @Override // java.lang.Enum
    public String toString() {
        return "EmptySubscription";
    }
}
