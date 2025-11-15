package com.easefun.povplayer.core.ijk.widget.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import p063h3.C1085a;
import p079j3.C1202e;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.ISurfaceTextureHolder;
import tv.danmaku.ijk.media.player.ISurfaceTextureHost;

@TargetApi(14)
/* loaded from: classes.dex */
public class TextureRenderView extends TextureView implements InterfaceC0736a {

    /* renamed from: e */
    public C1202e f1073e;

    /* renamed from: f */
    public int f1074f;

    /* renamed from: g */
    public Bitmap f1075g;

    /* renamed from: h */
    public List<Bitmap> f1076h;

    /* renamed from: i */
    public C1085a f1077i;

    /* renamed from: j */
    public int f1078j;

    /* renamed from: k */
    public Handler f1079k;

    /* renamed from: l */
    public TextureViewSurfaceTextureListenerC0735c f1080l;

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.TextureRenderView$a */
    public class HandlerC0733a extends Handler {
        public HandlerC0733a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 3) {
                TextureRenderView.this.m569g();
            }
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.TextureRenderView$b */
    public static final class C0734b implements InterfaceC0736a.b {

        /* renamed from: a */
        public TextureRenderView f1082a;

        /* renamed from: b */
        public SurfaceTexture f1083b;

        /* renamed from: c */
        public ISurfaceTextureHost f1084c;

        /* renamed from: d */
        public Surface f1085d;

        public C0734b(@NonNull TextureRenderView textureRenderView, @Nullable SurfaceTexture surfaceTexture, @NonNull ISurfaceTextureHost iSurfaceTextureHost) {
            this.f1082a = textureRenderView;
            this.f1083b = surfaceTexture;
            this.f1084c = iSurfaceTextureHost;
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        @TargetApi(16)
        /* renamed from: a */
        public void mo566a(IMediaPlayer iMediaPlayer) {
            Surface surface;
            if (iMediaPlayer == null) {
                return;
            }
            if (iMediaPlayer instanceof ISurfaceTextureHolder) {
                ISurfaceTextureHolder iSurfaceTextureHolder = (ISurfaceTextureHolder) iMediaPlayer;
                this.f1082a.f1080l.f1090i = false;
                SurfaceTexture surfaceTexture = iSurfaceTextureHolder.getSurfaceTexture();
                if (surfaceTexture != null) {
                    this.f1082a.setSurfaceTexture(surfaceTexture);
                    return;
                } else {
                    iSurfaceTextureHolder.setSurfaceTexture(this.f1083b);
                    iSurfaceTextureHolder.setSurfaceTextureHost(this.f1082a.f1080l);
                    return;
                }
            }
            if (this.f1083b == null) {
                surface = null;
            } else {
                Surface surface2 = this.f1085d;
                if (surface2 != null) {
                    surface2.release();
                }
                surface = new Surface(this.f1083b);
                this.f1085d = surface;
            }
            iMediaPlayer.setSurface(surface);
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        @NonNull
        public InterfaceC0736a getRenderView() {
            return this.f1082a;
        }

        @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a.b
        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return null;
        }
    }

    /* renamed from: com.easefun.povplayer.core.ijk.widget.media.TextureRenderView$c */
    public final class TextureViewSurfaceTextureListenerC0735c implements TextureView.SurfaceTextureListener, ISurfaceTextureHost {

        /* renamed from: e */
        public SurfaceTexture f1086e;

        /* renamed from: f */
        public boolean f1087f;

        /* renamed from: g */
        public int f1088g;

        /* renamed from: h */
        public int f1089h;

        /* renamed from: j */
        public WeakReference<TextureRenderView> f1091j;

        /* renamed from: i */
        public boolean f1090i = true;

        /* renamed from: k */
        public Map<InterfaceC0736a.a, Object> f1092k = new ConcurrentHashMap();

        public TextureViewSurfaceTextureListenerC0735c(@NonNull TextureRenderView textureRenderView) {
            this.f1091j = new WeakReference<>(textureRenderView);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i7, int i8) {
            this.f1086e = surfaceTexture;
            this.f1087f = false;
            this.f1088g = 0;
            this.f1089h = 0;
            C0734b c0734b = new C0734b(this.f1091j.get(), surfaceTexture, this);
            Iterator<InterfaceC0736a.a> it = this.f1092k.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo559c(c0734b, 0, 0);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.f1086e = surfaceTexture;
            this.f1087f = false;
            this.f1088g = 0;
            this.f1089h = 0;
            C0734b c0734b = new C0734b(this.f1091j.get(), surfaceTexture, this);
            Iterator<InterfaceC0736a.a> it = this.f1092k.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo558b(c0734b);
            }
            return this.f1090i;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i7, int i8) {
            this.f1086e = surfaceTexture;
            this.f1087f = true;
            this.f1088g = i7;
            this.f1089h = i8;
            C0734b c0734b = new C0734b(this.f1091j.get(), surfaceTexture, this);
            Iterator<InterfaceC0736a.a> it = this.f1092k.keySet().iterator();
            while (it.hasNext()) {
                it.next().mo557a(c0734b, 0, i7, i8);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            TextureRenderView textureRenderView = TextureRenderView.this;
            if (textureRenderView.f1074f == 1) {
                float videoOutputFramesPerSecond = textureRenderView.getVideoOutputFramesPerSecond();
                int iMax = videoOutputFramesPerSecond > 0.0f ? Math.max(3, (int) (videoOutputFramesPerSecond / 5.5d)) : 4;
                TextureRenderView textureRenderView2 = TextureRenderView.this;
                if (textureRenderView2.f1078j % iMax == 0) {
                    Objects.requireNonNull(textureRenderView2);
                    int iMin = (Math.min(TextureRenderView.this.getWidth(), TextureRenderView.this.getHeight()) * 420) / Math.max(TextureRenderView.this.getWidth(), TextureRenderView.this.getHeight());
                    boolean z6 = TextureRenderView.this.getWidth() > TextureRenderView.this.getHeight();
                    int i7 = z6 ? 420 : iMin;
                    int i8 = z6 ? iMin : 420;
                    TextureRenderView textureRenderView3 = TextureRenderView.this;
                    textureRenderView3.f1075g = textureRenderView3.getBitmap(i7, i8);
                    TextureRenderView textureRenderView4 = TextureRenderView.this;
                    textureRenderView4.f1076h.add(textureRenderView4.f1075g);
                }
                TextureRenderView.this.f1078j++;
            }
        }

        @Override // tv.danmaku.ijk.media.player.ISurfaceTextureHost
        public void releaseSurfaceTexture(SurfaceTexture surfaceTexture) {
        }
    }

    public TextureRenderView(Context context) {
        super(context);
        this.f1074f = 0;
        this.f1076h = new ArrayList();
        this.f1077i = new C1085a();
        this.f1079k = new HandlerC0733a();
        m570h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getVideoOutputFramesPerSecond() {
        return 0.0f;
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: b */
    public void mo561b(InterfaceC0736a.a aVar) {
        C0734b c0734b;
        TextureViewSurfaceTextureListenerC0735c textureViewSurfaceTextureListenerC0735c = this.f1080l;
        textureViewSurfaceTextureListenerC0735c.f1092k.put(aVar, aVar);
        if (textureViewSurfaceTextureListenerC0735c.f1086e != null) {
            c0734b = new C0734b(textureViewSurfaceTextureListenerC0735c.f1091j.get(), textureViewSurfaceTextureListenerC0735c.f1086e, textureViewSurfaceTextureListenerC0735c);
            aVar.mo559c(c0734b, textureViewSurfaceTextureListenerC0735c.f1088g, textureViewSurfaceTextureListenerC0735c.f1089h);
        } else {
            c0734b = null;
        }
        if (textureViewSurfaceTextureListenerC0735c.f1087f) {
            if (c0734b == null) {
                c0734b = new C0734b(textureViewSurfaceTextureListenerC0735c.f1091j.get(), textureViewSurfaceTextureListenerC0735c.f1086e, textureViewSurfaceTextureListenerC0735c);
            }
            aVar.mo557a(c0734b, 0, textureViewSurfaceTextureListenerC0735c.f1088g, textureViewSurfaceTextureListenerC0735c.f1089h);
        }
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: c */
    public void mo562c(int i7, int i8) {
        if (i7 <= 0 || i8 <= 0) {
            return;
        }
        C1202e c1202e = this.f1073e;
        c1202e.f2626a = i7;
        c1202e.f2627b = i8;
        requestLayout();
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: d */
    public void mo563d(int i7, int i8) {
        if (i7 <= 0 || i8 <= 0) {
            return;
        }
        C1202e c1202e = this.f1073e;
        c1202e.f2628c = i7;
        c1202e.f2629d = i8;
        requestLayout();
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: e */
    public boolean mo564e() {
        return false;
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    /* renamed from: f */
    public void mo565f(InterfaceC0736a.a aVar) {
        this.f1080l.f1092k.remove(aVar);
    }

    /* renamed from: g */
    public void m569g() {
        this.f1074f = 3;
        this.f1078j = 0;
        this.f1079k.removeMessages(3);
        Bitmap bitmap = this.f1075g;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f1075g.recycle();
            this.f1075g = null;
        }
        for (Bitmap bitmap2 : this.f1076h) {
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                bitmap2.recycle();
            }
        }
        this.f1076h.clear();
        System.gc();
    }

    public InterfaceC0736a.b getSurfaceHolder() {
        TextureViewSurfaceTextureListenerC0735c textureViewSurfaceTextureListenerC0735c = this.f1080l;
        return new C0734b(this, textureViewSurfaceTextureListenerC0735c.f1086e, textureViewSurfaceTextureListenerC0735c);
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public View getView() {
        return this;
    }

    /* renamed from: h */
    public final void m570h() {
        this.f1073e = new C1202e(this);
        TextureViewSurfaceTextureListenerC0735c textureViewSurfaceTextureListenerC0735c = new TextureViewSurfaceTextureListenerC0735c(this);
        this.f1080l = textureViewSurfaceTextureListenerC0735c;
        setSurfaceTextureListener(textureViewSurfaceTextureListenerC0735c);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        Objects.requireNonNull(this.f1080l);
        super.onDetachedFromWindow();
        Objects.requireNonNull(this.f1080l);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextureRenderView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextureRenderView.class.getName());
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        this.f1073e.m1429a(i7, i8);
        C1202e c1202e = this.f1073e;
        setMeasuredDimension(c1202e.f2631f, c1202e.f2632g);
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public void setAspectRatio(int i7) {
        this.f1073e.f2633h = i7;
        requestLayout();
    }

    public void setMirror(boolean z6) {
        if (getRotation() == 90.0f || getRotation() == 270.0f) {
            setScaleY(z6 ? -1.0f : 1.0f);
        } else {
            setScaleX(z6 ? -1.0f : 1.0f);
        }
    }

    @Override // com.easefun.povplayer.core.ijk.widget.media.InterfaceC0736a
    public void setVideoRotation(int i7) {
        this.f1073e.f2630e = i7;
        setRotation(i7);
    }

    public TextureRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1074f = 0;
        this.f1076h = new ArrayList();
        this.f1077i = new C1085a();
        this.f1079k = new HandlerC0733a();
        m570h();
    }

    public TextureRenderView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1074f = 0;
        this.f1076h = new ArrayList();
        this.f1077i = new C1085a();
        this.f1079k = new HandlerC0733a();
        m570h();
    }
}
