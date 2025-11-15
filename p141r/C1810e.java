package p141r;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import java.util.Map;
import p043f.C0984a;
import p108n0.C1512c;
import p183x.C2048i;

/* compiled from: GlideContext.java */
@TargetApi(14)
/* renamed from: r.e */
/* loaded from: classes.dex */
public class C1810e extends ContextWrapper {

    /* renamed from: g */
    @VisibleForTesting
    public static final AbstractC1815j<?, ?> f5234g = new C1807b();

    /* renamed from: a */
    public final C1812g f5235a;

    /* renamed from: b */
    public final C0984a f5236b;

    /* renamed from: c */
    public final C1512c f5237c;

    /* renamed from: d */
    public final Map<Class<?>, AbstractC1815j<?, ?>> f5238d;

    /* renamed from: e */
    public final C2048i f5239e;

    /* renamed from: f */
    public final int f5240f;

    public C1810e(Context context, C1812g c1812g, C0984a c0984a, C1512c c1512c, Map<Class<?>, AbstractC1815j<?, ?>> map, C2048i c2048i, int i7) {
        super(context.getApplicationContext());
        this.f5235a = c1812g;
        this.f5236b = c0984a;
        this.f5237c = c1512c;
        this.f5238d = map;
        this.f5239e = c2048i;
        this.f5240f = i7;
        new Handler(Looper.getMainLooper());
    }
}
