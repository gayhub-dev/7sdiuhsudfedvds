package com.easefun.povplayer.core.ijk.widget.media;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.View;
import com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a;
import com.google.android.apps.muzei.render.GLTextureView;
import p035e.C0901n;
import p051g.C1032b;
import p079j3.InterfaceC1200c;
import p115o.C1570g;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes.dex */
public class PolyvGLTextureRenderView extends GLTextureView implements InterfaceC0736a, C1570g.b {

    /* renamed from: q */
    public C0901n f1060q;

    /* renamed from: r */
    public InterfaceC1200c f1061r;

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.PolyvGLTextureRenderView$b */
    public static final class C0729b implements InterfaceC0736a.b {
        public C0729b(C0728a c0728a) {
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        /* renamed from: a */
        public void mo566a(IMediaPlayer iMediaPlayer) {
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        @NonNull
        public InterfaceC0736a getRenderView() {
            return null;
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return null;
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.PolyvGLTextureRenderView$c */
    public interface InterfaceC0730c {
    }

    public PolyvGLTextureRenderView(Context context) {
        super(context);
    }

    @Override // p115o.C1570g.b
    /* renamed from: a */
    public void mo560a(C1032b c1032b) {
        if (this.f1061r != null) {
            c1032b.toString();
            float f7 = c1032b.f1944b;
            if (f7 == 0.0d && c1032b.f1943a == 0.0d && c1032b.f1945c == 0.0d) {
                return;
            }
            ((IjkVideoView) this.f1061r).m556q(f7, c1032b.f1943a, c1032b.f1945c);
        }
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: b */
    public void mo561b(@NonNull InterfaceC0736a.a aVar) {
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: c */
    public void mo562c(int i7, int i8) {
        C0901n c0901n = this.f1060q;
        if (c0901n != null) {
            c0901n.f1594a.set(0.0f, 0.0f, i7, i8);
        }
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: d */
    public void mo563d(int i7, int i8) {
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: e */
    public boolean mo564e() {
        return false;
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: f */
    public void mo565f(@NonNull InterfaceC0736a.a aVar) {
        this.f1060q = null;
    }

    public InterfaceC0736a.b getSurfaceHolder() {
        return new C0729b(null);
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public View getView() {
        return this;
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public void setAspectRatio(int i7) {
    }

    public void setMdvrLibrary(C0901n c0901n) {
        this.f1060q = c0901n;
    }

    public void setOnSetGyroListener(InterfaceC1200c interfaceC1200c) {
        this.f1061r = interfaceC1200c;
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public void setVideoRotation(int i7) {
    }
}
