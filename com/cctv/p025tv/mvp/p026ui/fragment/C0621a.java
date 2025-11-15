package com.cctv.p025tv.mvp.p026ui.fragment;

import android.content.res.Resources;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.cctv.p025tv.R;
import java.util.TimerTask;

/* compiled from: VideoFragment.java */
/* renamed from: com.cctv.tv.mvp.ui.fragment.a */
/* loaded from: classes.dex */
public class C0621a extends TimerTask {

    /* renamed from: e */
    public final /* synthetic */ VideoFragment f885e;

    /* compiled from: VideoFragment.java */
    /* renamed from: com.cctv.tv.mvp.ui.fragment.a$a */
    public class a implements Runnable {

        /* compiled from: VideoFragment.java */
        /* renamed from: com.cctv.tv.mvp.ui.fragment.a$a$a */
        public class AnimationAnimationListenerC2163a implements Animation.AnimationListener {
            public AnimationAnimationListenerC2163a() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                C0621a.this.f885e.f873y.setVisibility(8);
                C0621a.this.f885e.m508l().setHideLoading(false);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws Resources.NotFoundException {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(C0621a.this.f885e.getContext(), R.anim.push_bottom_out);
            animationLoadAnimation.setAnimationListener(new AnimationAnimationListenerC2163a());
            C0621a.this.f885e.f873y.startAnimation(animationLoadAnimation);
        }
    }

    public C0621a(VideoFragment videoFragment) {
        this.f885e = videoFragment;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.f885e.getActivity().runOnUiThread(new a());
    }
}
