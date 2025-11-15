package p077j1;

import android.view.View;
import android.widget.ImageView;
import com.cctv.cctvplayer.R$id;
import com.cctv.cctvplayer.player.EnumC0567a;
import com.cctv.cctvplayer.widget.RateTipsView;
import p045f1.C0993b;

/* compiled from: RateTipsView.java */
/* renamed from: j1.a */
/* loaded from: classes.dex */
public class ViewOnClickListenerC1185a implements View.OnClickListener {

    /* renamed from: e */
    public final /* synthetic */ String f2602e;

    /* renamed from: f */
    public final /* synthetic */ View f2603f;

    /* renamed from: g */
    public final /* synthetic */ RateTipsView f2604g;

    public ViewOnClickListenerC1185a(RateTipsView rateTipsView, String str, View view) {
        this.f2604g = rateTipsView;
        this.f2602e = str;
        this.f2603f = view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        EnumC0567a enumC0567a = EnumC0567a.RATE_CHANGE;
        for (C0993b c0993b : this.f2604g.f548e) {
            if (c0993b.f1851b.equals(this.f2602e)) {
                c0993b.f1853d = true;
            } else {
                c0993b.f1853d = false;
            }
        }
        View view2 = this.f2604g.f550g;
        if (view2 != null) {
            view2.setVisibility(4);
        }
        ImageView imageView = (ImageView) this.f2603f.findViewById(R$id.rateCheck);
        imageView.setVisibility(0);
        RateTipsView rateTipsView = this.f2604g;
        rateTipsView.f550g = imageView;
        if (rateTipsView.f551h.getPlayEntity() != null) {
            this.f2604g.f552i.m371v();
            this.f2604g.f551h.m392w(8);
            if (this.f2604g.f552i.m357h()) {
                this.f2604g.f552i.m366q(enumC0567a);
            } else {
                this.f2604g.f552i.m367r(true, enumC0567a);
            }
        }
    }
}
