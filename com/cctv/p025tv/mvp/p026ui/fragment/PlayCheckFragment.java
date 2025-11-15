package com.cctv.p025tv.mvp.p026ui.fragment;

import android.opengl.GLSurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.base.BaseFragment;
import p043f.C0988e;
import p078j2.C1193h;
import p132p6.C1749a;
import p179w2.C2026b;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class PlayCheckFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: h */
    public TextView f815h;

    /* renamed from: i */
    public Button f816i;

    /* renamed from: j */
    public Button f817j;

    /* renamed from: k */
    public RelativeLayout f818k;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_play_check;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f815h.setText(C2026b.m2379b(R.string.play_check));
        GLSurfaceView gLSurfaceView = (GLSurfaceView) this.f577f.findViewById(R.id.gl_surface_view_cp);
        gLSurfaceView.setEGLContextClientVersion(1);
        gLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        gLSurfaceView.setRenderer(new C1193h());
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f815h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f816i = (Button) this.f577f.findViewById(R.id.btn_check_start);
        this.f817j = (Button) this.f577f.findViewById(R.id.btn_check_cancel);
        this.f818k = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f818k.setBackgroundResource(R.drawable.left_four);
        this.f816i.requestFocus();
        this.f816i.setOnClickListener(this);
        this.f817j.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_check_cancel) {
            C0988e.m971O(getActivity().getSupportFragmentManager(), "SYSTEM_SETTING");
        } else {
            if (id != R.id.btn_check_start) {
                return;
            }
            C0988e.m971O(getActivity().getSupportFragmentManager(), "CHECK_PLAYER");
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (z6) {
            return;
        }
        Button button = this.f816i;
        if (button != null) {
            button.requestFocus();
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }
}
