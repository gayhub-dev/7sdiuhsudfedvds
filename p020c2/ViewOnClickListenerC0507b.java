package p020c2;

import android.view.View;
import com.cctv.p025tv.mvp.p026ui.activity.MainActivity;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import p054g2.RunnableC1054e;

/* renamed from: c2.b */
/* loaded from: classes.dex */
public final /* synthetic */ class ViewOnClickListenerC0507b implements View.OnClickListener {

    /* renamed from: e */
    public final /* synthetic */ int f377e = 0;

    /* renamed from: f */
    public final /* synthetic */ Object f378f;

    public /* synthetic */ ViewOnClickListenerC0507b(VideoFragment videoFragment) {
        this.f378f = videoFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f377e) {
            case 0:
                MainActivity mainActivity = (MainActivity) this.f378f;
                mainActivity.f684w.dismiss();
                mainActivity.finish();
                break;
            default:
                VideoFragment videoFragment = (VideoFragment) this.f378f;
                videoFragment.f862n.dismiss();
                videoFragment.m508l().m446I("EXIT", 0L);
                videoFragment.getActivity().runOnUiThread(new RunnableC1054e(videoFragment, 3));
                break;
        }
    }
}
