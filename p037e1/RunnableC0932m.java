package p037e1;

import android.graphics.drawable.AnimationDrawable;
import com.cctv.cctvplayer.CCTVVideoView;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.m */
/* loaded from: classes.dex */
public class RunnableC0932m implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ AnimationDrawable f1696e;

    public RunnableC0932m(CCTVVideoView cCTVVideoView, AnimationDrawable animationDrawable) {
        this.f1696e = animationDrawable;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1696e.start();
    }
}
