package p095l3;

import android.view.Surface;
import p035e.C0901n;

/* compiled from: PolyvVideoView.java */
/* renamed from: l3.i */
/* loaded from: classes.dex */
public class C1429i implements C0901n.h {

    /* renamed from: a */
    public final /* synthetic */ C1432l f4170a;

    public C1429i(C1432l c1432l) {
        this.f4170a = c1432l;
    }

    @Override // p035e.C0901n.h
    /* renamed from: a */
    public void mo818a(Surface surface) {
        if (this.f4170a.f4173a.getMediaPlayer() != null) {
            this.f4170a.f4173a.getMediaPlayer().setSurface(surface);
        }
    }
}
