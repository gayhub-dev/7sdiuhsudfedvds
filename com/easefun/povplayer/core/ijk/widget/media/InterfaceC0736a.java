package com.easefun.povplayer.core.ijk.widget.media;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.View;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: IRenderView.java */
/* renamed from: com.easefun.povplayer.core.ijk.widget.media.a */
/* loaded from: classes.dex */
public interface InterfaceC0736a {

    /* compiled from: IRenderView.java */
    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.a$a */
    public interface a {
        /* renamed from: a */
        void mo557a(@NonNull b bVar, int i7, int i8, int i9);

        /* renamed from: b */
        void mo558b(@NonNull b bVar);

        /* renamed from: c */
        void mo559c(@NonNull b bVar, int i7, int i8);
    }

    /* compiled from: IRenderView.java */
    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.a$b */
    public interface b {
        /* renamed from: a */
        void mo566a(IMediaPlayer iMediaPlayer);

        @NonNull
        InterfaceC0736a getRenderView();

        @Nullable
        SurfaceHolder getSurfaceHolder();
    }

    /* renamed from: b */
    void mo561b(@NonNull a aVar);

    /* renamed from: c */
    void mo562c(int i7, int i8);

    /* renamed from: d */
    void mo563d(int i7, int i8);

    /* renamed from: e */
    boolean mo564e();

    /* renamed from: f */
    void mo565f(@NonNull a aVar);

    View getView();

    void setAspectRatio(int i7);

    void setVideoRotation(int i7);
}
