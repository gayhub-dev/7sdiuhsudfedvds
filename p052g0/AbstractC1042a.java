package p052g0;

import android.graphics.drawable.Drawable;
import java.util.Objects;
import p183x.InterfaceC2054o;
import p183x.InterfaceC2057r;

/* compiled from: DrawableResource.java */
/* renamed from: g0.a */
/* loaded from: classes.dex */
public abstract class AbstractC1042a<T extends Drawable> implements InterfaceC2057r<T>, InterfaceC2054o {

    /* renamed from: e */
    public final T f1975e;

    public AbstractC1042a(T t6) {
        Objects.requireNonNull(t6, "Argument must not be null");
        this.f1975e = t6;
    }

    @Override // p183x.InterfaceC2057r
    public Object get() {
        return this.f1975e.getConstantState().newDrawable();
    }
}
