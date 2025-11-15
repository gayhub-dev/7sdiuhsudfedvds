package p061h1;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.cctv.cctvplayer.CCTVVideoMediaController;
import com.cctv.cctvplayer.CCTVVideoView;
import p053g1.InterfaceC1046d;

/* compiled from: Orientation.java */
/* renamed from: h1.a */
/* loaded from: classes.dex */
public class C1081a {

    /* renamed from: a */
    public Context f2178a;

    /* renamed from: b */
    public CCTVVideoMediaController f2179b;

    /* renamed from: c */
    public InterfaceC1046d f2180c;

    /* renamed from: d */
    public ViewGroup f2181d;

    /* renamed from: e */
    public ViewGroup f2182e;

    /* renamed from: f */
    public ViewGroup.LayoutParams f2183f;

    /* renamed from: g */
    public CCTVVideoView f2184g;

    public C1081a(Context context, CCTVVideoMediaController cCTVVideoMediaController) {
        this.f2178a = context;
        this.f2179b = cCTVVideoMediaController;
    }

    /* renamed from: a */
    public void m1173a(int i7) {
        ((Activity) this.f2178a).setRequestedOrientation(i7);
    }
}
