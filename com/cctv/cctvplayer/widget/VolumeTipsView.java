package com.cctv.cctvplayer.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.cctv.cctvplayer.R$drawable;
import com.cctv.cctvplayer.R$id;
import com.cctv.cctvplayer.R$layout;

/* loaded from: classes.dex */
public class VolumeTipsView extends FrameLayout {

    /* renamed from: e */
    public View f555e;

    /* renamed from: f */
    public TextView f556f;

    /* renamed from: g */
    public ImageView f557g;

    /* renamed from: h */
    public Handler f558h;

    /* renamed from: com.cctv.cctvplayer.widget.VolumeTipsView$a */
    public class HandlerC0571a extends Handler {
        public HandlerC0571a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 8) {
                VolumeTipsView.this.setVisibility(8);
            }
        }
    }

    public VolumeTipsView(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public void m399a(int i7, boolean z6) {
        this.f558h.removeMessages(8);
        if (z6) {
            this.f558h.sendEmptyMessageDelayed(8, 300L);
            return;
        }
        if (i7 < 1) {
            this.f557g.setImageResource(R$drawable.mute);
        } else {
            this.f557g.setImageResource(R$drawable.volume);
        }
        setVisibility(0);
        this.f556f.setText(i7 + "%");
    }

    public VolumeTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VolumeTipsView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f558h = new HandlerC0571a();
        this.f555e = LayoutInflater.from(context).inflate(R$layout.cctv_videoview_tips_view_volume, this);
        setVisibility(8);
        this.f556f = (TextView) this.f555e.findViewById(R$id.tv_percent);
        this.f557g = (ImageView) this.f555e.findViewById(R$id.volume);
    }
}
