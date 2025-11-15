package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationManagerCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.cctv.cctvplayer.CCTVVideoMediaController;
import com.cctv.cctvplayer.R$id;
import com.cctv.cctvplayer.R$layout;
import com.cctv.cctvplayer.player.EnumC0567a;
import p043f.C0988e;

/* loaded from: classes.dex */
public class ProgressTipsView extends FrameLayout {

    /* renamed from: e */
    public View f539e;

    /* renamed from: f */
    public TextView f540f;

    /* renamed from: g */
    public TextView f541g;

    /* renamed from: h */
    public TextView f542h;

    /* renamed from: i */
    public ImageView f543i;

    /* renamed from: j */
    public ImageView f544j;

    /* renamed from: k */
    public CCTVVideoMediaController f545k;

    /* renamed from: l */
    public Handler f546l;

    /* renamed from: com.cctv.cctvplayer.widget.ProgressTipsView$a */
    public class HandlerC0570a extends Handler {
        public HandlerC0570a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 8) {
                ProgressTipsView.this.setVisibility(8);
            }
        }
    }

    public ProgressTipsView(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public void m396a() {
        this.f546l.removeMessages(8);
        this.f546l.sendEmptyMessageDelayed(8, 300L);
    }

    /* renamed from: b */
    public void m397b(boolean z6, boolean z7) {
        this.f546l.removeMessages(8);
        this.f541g.setVisibility(8);
        this.f542h.setVisibility(8);
        if (z6) {
            this.f546l.sendEmptyMessageDelayed(8, 300L);
            CCTVVideoMediaController cCTVVideoMediaController = this.f545k;
            if (cCTVVideoMediaController != null) {
                cCTVVideoMediaController.m357h();
                this.f545k.m366q(EnumC0567a.OTHER);
                return;
            }
            return;
        }
        this.f545k.m369t();
        setVisibility(0);
        if (z7) {
            this.f543i.setVisibility(8);
            this.f544j.setVisibility(0);
            this.f545k.setLiveProgress(1000);
        } else {
            this.f543i.setVisibility(0);
            this.f544j.setVisibility(8);
            this.f545k.setLiveProgress(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        }
        CCTVVideoMediaController cCTVVideoMediaController2 = this.f545k;
        if (cCTVVideoMediaController2 != null) {
            this.f540f.setText(C0988e.m984j(cCTVVideoMediaController2.getLivePlayedMs(), "HH:mm:ss"));
        }
    }

    /* renamed from: c */
    public void m398c(int i7, int i8, boolean z6, boolean z7) {
        this.f546l.removeMessages(8);
        this.f541g.setVisibility(0);
        this.f542h.setVisibility(0);
        if (z6) {
            this.f546l.sendEmptyMessageDelayed(8, 300L);
            return;
        }
        setVisibility(0);
        if (z7) {
            this.f543i.setVisibility(8);
            this.f544j.setVisibility(0);
        } else {
            this.f543i.setVisibility(0);
            this.f544j.setVisibility(8);
        }
        if (i7 < 0) {
            i7 = 0;
        }
        if (i7 > i8) {
            i7 = i8;
        }
        this.f540f.setText(C0988e.m985k(i7, false));
        this.f541g.setText(C0988e.m985k(i8, false));
    }

    public void setMediaController(CCTVVideoMediaController cCTVVideoMediaController) {
        this.f545k = cCTVVideoMediaController;
    }

    public ProgressTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProgressTipsView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f546l = new HandlerC0570a(Looper.getMainLooper());
        this.f539e = LayoutInflater.from(context).inflate(R$layout.cctv_progress_tips_view, this);
        setVisibility(8);
        this.f540f = (TextView) this.f539e.findViewById(R$id.tv_currenttime);
        this.f541g = (TextView) this.f539e.findViewById(R$id.tv_totaltime);
        this.f542h = (TextView) this.f539e.findViewById(R$id.tv_sp);
        this.f543i = (ImageView) this.f539e.findViewById(R$id.iv_left);
        this.f544j = (ImageView) this.f539e.findViewById(R$id.iv_right);
    }
}
