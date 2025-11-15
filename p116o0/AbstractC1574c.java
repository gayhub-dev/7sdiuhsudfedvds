package p116o0;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import p126p0.InterfaceC1733b;

/* compiled from: ImageViewTarget.java */
/* renamed from: o0.c */
/* loaded from: classes.dex */
public abstract class AbstractC1574c<Z> extends AbstractC1578g<ImageView, Z> {

    /* renamed from: d */
    @Nullable
    public Animatable f4731d;

    public AbstractC1574c(ImageView imageView) {
        super(imageView);
    }

    @Override // p116o0.AbstractC1572a, p084k0.InterfaceC1221g
    /* renamed from: a */
    public void mo1446a() {
        Animatable animatable = this.f4731d;
        if (animatable != null) {
            animatable.start();
        }
    }

    @Override // p116o0.AbstractC1572a, p116o0.InterfaceC1577f
    /* renamed from: b */
    public void mo1816b(@Nullable Drawable drawable) {
        m1821k(null);
        ((ImageView) this.f4734b).setImageDrawable(drawable);
    }

    @Override // p116o0.InterfaceC1577f
    /* renamed from: e */
    public void mo1308e(Z z6, @Nullable InterfaceC1733b<? super Z> interfaceC1733b) {
        m1821k(z6);
    }

    @Override // p116o0.AbstractC1572a, p116o0.InterfaceC1577f
    /* renamed from: g */
    public void mo1818g(@Nullable Drawable drawable) {
        m1821k(null);
        ((ImageView) this.f4734b).setImageDrawable(drawable);
    }

    @Override // p116o0.AbstractC1572a, p116o0.InterfaceC1577f
    /* renamed from: i */
    public void mo1820i(@Nullable Drawable drawable) {
        this.f4735c.m1824a();
        m1821k(null);
        ((ImageView) this.f4734b).setImageDrawable(drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: k */
    public final void m1821k(@Nullable Z z6) {
        if (z6 instanceof Animatable) {
            Animatable animatable = (Animatable) z6;
            this.f4731d = animatable;
            animatable.start();
        } else {
            this.f4731d = null;
        }
        C1573b c1573b = (C1573b) this;
        switch (c1573b.f4730e) {
            case 0:
                ((ImageView) c1573b.f4734b).setImageDrawable((Drawable) z6);
                break;
            default:
                ((ImageView) c1573b.f4734b).setImageBitmap((Bitmap) z6);
                break;
        }
    }

    @Override // p116o0.AbstractC1572a, p084k0.InterfaceC1221g
    public void onStop() {
        Animatable animatable = this.f4731d;
        if (animatable != null) {
            animatable.stop();
        }
    }
}
