package p037e1;

import com.cctv.cctvplayer.CCTVVideoView;
import java.util.Objects;
import p103m3.InterfaceC1465e;

/* compiled from: CCTVVideoView.java */
/* renamed from: e1.q */
/* loaded from: classes.dex */
public class C0936q implements InterfaceC1465e {

    /* renamed from: a */
    public final /* synthetic */ CCTVVideoView f1700a;

    public C0936q(CCTVVideoView cCTVVideoView) {
        this.f1700a = cCTVVideoView;
    }

    @Override // p103m3.InterfaceC1465e
    public void callback() {
        CCTVVideoView cCTVVideoView = this.f1700a;
        if (cCTVVideoView.f496I == null) {
            return;
        }
        Objects.requireNonNull(cCTVVideoView.getPlayEntity());
        this.f1700a.f501i.getCurrentPlayPath();
        throw null;
    }
}
