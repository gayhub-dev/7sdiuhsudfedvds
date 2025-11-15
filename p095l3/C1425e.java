package p095l3;

import android.view.Surface;
import p035e.C0901n;

/* compiled from: PolyvVideoView.java */
/* renamed from: l3.e */
/* loaded from: classes.dex */
public class C1425e implements C0901n.h {

    /* renamed from: a */
    public final /* synthetic */ C1428h f4166a;

    public C1425e(C1428h c1428h) {
        this.f4166a = c1428h;
    }

    @Override // p035e.C0901n.h
    /* renamed from: a */
    public void mo818a(Surface surface) {
        if (this.f4166a.f4169a.getMediaPlayer() != null) {
            this.f4166a.f4169a.getMediaPlayer().setSurface(surface);
        }
    }
}
