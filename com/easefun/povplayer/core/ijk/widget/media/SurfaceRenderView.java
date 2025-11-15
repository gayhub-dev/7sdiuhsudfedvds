package com.easefun.povplayer.core.ijk.widget.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p079j3.C1202e;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.ISurfaceTextureHolder;

/* loaded from: classes.dex */
public class SurfaceRenderView extends SurfaceView implements InterfaceC0736a {

    /* renamed from: e */
    public C1202e f1062e;

    /* renamed from: f */
    public SurfaceHolderCallbackC0732b f1063f;

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.SurfaceRenderView$a */
    public static final class C0731a implements InterfaceC0736a.b {

        /* renamed from: a */
        public SurfaceRenderView f1064a;

        /* renamed from: b */
        public SurfaceHolder f1065b;

        public C0731a(@NonNull SurfaceRenderView surfaceRenderView, @Nullable SurfaceHolder surfaceHolder) {
            this.f1064a = surfaceRenderView;
            this.f1065b = surfaceHolder;
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        /* renamed from: a */
        public void mo566a(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                if (iMediaPlayer instanceof ISurfaceTextureHolder) {
                    ((ISurfaceTextureHolder) iMediaPlayer).setSurfaceTexture(null);
                }
                iMediaPlayer.setDisplay(this.f1065b);
            }
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        @NonNull
        public InterfaceC0736a getRenderView() {
            return this.f1064a;
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return this.f1065b;
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.SurfaceRenderView$b */
    public static final class SurfaceHolderCallbackC0732b implements SurfaceHolder.Callback {

        /* renamed from: e */
        public SurfaceHolder f1066e;

        /* renamed from: f */
        public boolean f1067f;

        /* renamed from: g */
        public int f1068g;

        /* renamed from: h */
        public int f1069h;

        /* renamed from: i */
        public int f1070i;

        /* renamed from: j */
        public WeakReference<SurfaceRenderView> f1071j;

        /* renamed from: k */
        public Map<InterfaceC0736a.a, Object> f1072k = new ConcurrentHashMap();

        public SurfaceHolderCallbackC0732b(@NonNull SurfaceRenderView surfaceRenderView) {
            this.f1071j = new WeakReference<>(surfaceRenderView);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i7, int i8, int i9) {
            this.f1066e = surfaceHolder;
            this.f1067f = true;
            this.f1068g = i7;
            this.f1069h = i8;
            this.f1070i = i9;
            C0731a c0731a = new C0731a(this.f1071j.get(), this.f1066e);
            Iterator<InterfaceC0736a.a> it = this.f1072k.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo557a(c0731a, i7, i8, i9);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.f1066e = surfaceHolder;
            this.f1067f = false;
            this.f1068g = 0;
            this.f1069h = 0;
            this.f1070i = 0;
            C0731a c0731a = new C0731a(this.f1071j.get(), this.f1066e);
            Iterator<InterfaceC0736a.a> it = this.f1072k.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo559c(c0731a, 0, 0);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.f1066e = null;
            this.f1067f = false;
            this.f1068g = 0;
            this.f1069h = 0;
            this.f1070i = 0;
            C0731a c0731a = new C0731a(this.f1071j.get(), this.f1066e);
            Iterator<InterfaceC0736a.a> it = this.f1072k.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo558b(c0731a);
            }
        }
    }

    public SurfaceRenderView(Context context) {
        super(context);
        m567a();
    }

    /* renamed from: a */
    public final void m567a() {
        this.f1062e = new C1202e(this);
        this.f1063f = new SurfaceHolderCallbackC0732b(this);
        getHolder().addCallback(this.f1063f);
        getHolder().setType(0);
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: b */
    public void mo561b(InterfaceC0736a.a aVar) {
        C0731a c0731a;
        SurfaceHolderCallbackC0732b surfaceHolderCallbackC0732b = this.f1063f;
        surfaceHolderCallbackC0732b.f1072k.put(aVar, aVar);
        if (surfaceHolderCallbackC0732b.f1066e != null) {
            c0731a = new C0731a(surfaceHolderCallbackC0732b.f1071j.get(), surfaceHolderCallbackC0732b.f1066e);
            aVar.mo559c(c0731a, surfaceHolderCallbackC0732b.f1069h, surfaceHolderCallbackC0732b.f1070i);
        } else {
            c0731a = null;
        }
        if (surfaceHolderCallbackC0732b.f1067f) {
            if (c0731a == null) {
                c0731a = new C0731a(surfaceHolderCallbackC0732b.f1071j.get(), surfaceHolderCallbackC0732b.f1066e);
            }
            aVar.mo557a(c0731a, surfaceHolderCallbackC0732b.f1068g, surfaceHolderCallbackC0732b.f1069h, surfaceHolderCallbackC0732b.f1070i);
        }
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: c */
    public void mo562c(int i7, int i8) {
        if (i7 <= 0 || i8 <= 0) {
            return;
        }
        C1202e c1202e = this.f1062e;
        c1202e.f2626a = i7;
        c1202e.f2627b = i8;
        getHolder().setFixedSize(i7, i8);
        requestLayout();
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: d */
    public void mo563d(int i7, int i8) {
        if (i7 <= 0 || i8 <= 0) {
            return;
        }
        C1202e c1202e = this.f1062e;
        c1202e.f2628c = i7;
        c1202e.f2629d = i8;
        requestLayout();
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: e */
    public boolean mo564e() {
        return true;
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: f */
    public void mo565f(InterfaceC0736a.a aVar) {
        this.f1063f.f1072k.remove(aVar);
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public View getView() {
        return this;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SurfaceRenderView.class.getName());
    }

    @Override // android.view.View
    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(SurfaceRenderView.class.getName());
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onMeasure(int i7, int i8) {
        this.f1062e.m1429a(i7, i8);
        C1202e c1202e = this.f1062e;
        setMeasuredDimension(c1202e.f2631f, c1202e.f2632g);
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public void setAspectRatio(int i7) {
        this.f1062e.f2633h = i7;
        requestLayout();
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public void setVideoRotation(int i7) {
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m567a();
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m567a();
    }
}
