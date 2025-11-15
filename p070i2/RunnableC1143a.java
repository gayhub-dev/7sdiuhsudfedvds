package p070i2;

import android.graphics.drawable.AnimationDrawable;
import com.cctv.p025tv.mvp.p026ui.view.versionupdate.VersionCheckingView;

/* compiled from: VersionCheckingView.java */
/* renamed from: i2.a */
/* loaded from: classes.dex */
public class RunnableC1143a implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ AnimationDrawable f2514e;

    public RunnableC1143a(VersionCheckingView versionCheckingView, AnimationDrawable animationDrawable) {
        this.f2514e = animationDrawable;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2514e.start();
    }
}
