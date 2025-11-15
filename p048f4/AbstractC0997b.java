package p048f4;

import java.util.concurrent.atomic.AtomicInteger;
import p040e4.InterfaceC0950c;

/* compiled from: BasicIntQueueDisposable.java */
/* renamed from: f4.b */
/* loaded from: classes.dex */
public abstract class AbstractC0997b<T> extends AtomicInteger implements InterfaceC0950c<T> {
    private static final long serialVersionUID = -1001730202384742097L;

    @Override // p040e4.InterfaceC0955h
    public final boolean offer(T t6) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
