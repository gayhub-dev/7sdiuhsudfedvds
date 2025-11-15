package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.cctv.cctvplayer.R$id;
import com.cctv.cctvplayer.R$layout;

/* loaded from: classes.dex */
public class LightTipsView extends FrameLayout {

    /* renamed from: e */
    public View f528e;

    /* renamed from: f */
    public TextView f529f;

    /* renamed from: g */
    public Handler f530g;

    /* renamed from: com.cctv.cctvplayer.widget.LightTipsView$a */
    public class HandlerC0568a extends Handler {
        public HandlerC0568a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 8) {
                LightTipsView.this.setVisibility(8);
            }
        }
    }

    public LightTipsView(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public void m394a(int i7, boolean z6) {
        this.f530g.removeMessages(8);
        if (z6) {
            this.f530g.sendEmptyMessageDelayed(8, 300L);
            return;
        }
        setVisibility(0);
        this.f529f.setText(i7 + "%");
    }

    public LightTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LightTipsView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f530g = new HandlerC0568a();
        this.f528e = LayoutInflater.from(context).inflate(R$layout.cctv_videoview_tips_view_light, this);
        setVisibility(8);
        this.f529f = (TextView) this.f528e.findViewById(R$id.tv_percent);
    }
}
