package p037e1;

import android.graphics.drawable.AnimationDrawable;
import com.cctv.cctvplayer.CCTVVideoView;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.r */
/* loaded from: classes.dex */
public class RunnableC0937r implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ AnimationDrawable f1701e;

    public RunnableC0937r(CCTVVideoView cCTVVideoView, AnimationDrawable animationDrawable) {
        this.f1701e = animationDrawable;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1701e.start();
    }
}
