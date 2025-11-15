package p054g2;

import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import p043f.C0988e;

/* renamed from: g2.e */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1054e implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ int f1982e;

    /* renamed from: f */
    public final /* synthetic */ VideoFragment f1983f;

    public /* synthetic */ RunnableC1054e(VideoFragment videoFragment, int i7) {
        this.f1982e = i7;
        if (i7 != 1) {
        }
        this.f1983f = videoFragment;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1982e) {
            case 0:
                VideoFragment videoFragment = this.f1983f;
                int i7 = VideoFragment.f854B;
                videoFragment.m517u();
                break;
            case 1:
                VideoFragment videoFragment2 = this.f1983f;
                int i8 = VideoFragment.f854B;
                C0988e.m971O(videoFragment2.getActivity().getSupportFragmentManager(), "MAIN_FRAGMENT");
                break;
            case 2:
                VideoFragment videoFragment3 = this.f1983f;
                int i9 = VideoFragment.f854B;
                videoFragment3.m516t();
                break;
            default:
                VideoFragment videoFragment4 = this.f1983f;
                int i10 = VideoFragment.f854B;
                C0988e.m971O(videoFragment4.getActivity().getSupportFragmentManager(), "MAIN_FRAGMENT");
                break;
        }
    }
}
