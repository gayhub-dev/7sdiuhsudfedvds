package com.cctv.p025tv.mvp.p026ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.base.BaseFragment;
import com.cctv.p025tv.module.collect.C0580b;
import p043f.C0988e;
import p078j2.C1186a;
import p078j2.C1197l;
import p132p6.C1749a;
import p179w2.C2026b;
import p190y.AbstractC2085c;

/* loaded from: classes.dex */
public class AudioTrackSwitchFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: h */
    public TextView f739h;

    /* renamed from: i */
    public Button f740i;

    /* renamed from: j */
    public Button f741j;

    /* renamed from: k */
    public RelativeLayout f742k;

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: e */
    public AbstractC2085c mo407e() {
        return null;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: f */
    public int mo408f() {
        return R.layout.fragment_audio_track_switch;
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: g */
    public void mo409g() {
        this.f739h.setText(C2026b.m2379b(R.string.audio_track_switch));
        this.f742k.setBackgroundResource(R.drawable.left_four);
        if (C1186a.m1381a()) {
            this.f740i.requestFocus();
        } else {
            this.f741j.requestFocus();
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: h */
    public void mo410h() {
        this.f739h = (TextView) this.f577f.findViewById(R.id.top_name);
        this.f740i = (Button) this.f577f.findViewById(R.id.btn_audio_track_yes);
        this.f741j = (Button) this.f577f.findViewById(R.id.btn_audio_track_no);
        this.f742k = (RelativeLayout) this.f577f.findViewById(R.id.rl_left_bg);
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment
    /* renamed from: i */
    public void mo411i() {
        this.f740i.setOnClickListener(this);
        this.f741j.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_audio_track_no /* 2131230781 */:
                C0580b.m417c("AUDIO_TRACK_SWITCH_NO", getClass().getSimpleName());
                C1197l.m1424d(MyApplication.f561e, "AUDIO_TRACK_AUTO_SWITCH", false);
                C0988e.m971O(getActivity().getSupportFragmentManager(), "SYSTEM_SETTING");
                break;
            case R.id.btn_audio_track_yes /* 2131230782 */:
                C0580b.m417c("AUDIO_TRACK_SWITCH_YES", getClass().getSimpleName());
                C1197l.m1424d(MyApplication.f561e, "AUDIO_TRACK_AUTO_SWITCH", true);
                C0988e.m971O(getActivity().getSupportFragmentManager(), "SYSTEM_SETTING");
                break;
        }
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z6) {
        super.onHiddenChanged(z6);
        if (z6) {
            return;
        }
        if (C1186a.m1381a()) {
            this.f740i.requestFocus();
        } else {
            this.f741j.requestFocus();
        }
        C1749a.m1913b(getActivity(), this.f577f);
    }

    @Override // com.cctv.p025tv.base.BaseFragment, android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
    }
}
