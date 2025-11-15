package p185x1;

import android.text.TextUtils;
import com.cctv.p025tv.R;
import com.cctv.p025tv.module.player.WarmVideoPlayer;
import com.ctvit.dlna.entity.DlnaContentEntity;
import java.util.ArrayList;
import java.util.Objects;
import p045f1.C0992a;
import p045f1.C0993b;
import p118o2.C1581b;
import p144r2.C1830c;
import p179w2.C2026b;

/* compiled from: PlayerDlna.java */
/* renamed from: x1.e */
/* loaded from: classes.dex */
public class C2070e {
    /* renamed from: a */
    public void m2453a(DlnaContentEntity dlnaContentEntity, WarmVideoPlayer warmVideoPlayer) {
        if (C1581b.m1833b() != null) {
            ((C1830c.b) C1581b.m1833b()).m2074a();
        }
        C0992a c0992a = new C0992a();
        c0992a.f1841e = true;
        c0992a.f1837a = dlnaContentEntity.getId();
        c0992a.f1838b = dlnaContentEntity.getName();
        c0992a.f1839c = false;
        c0992a.f1840d = false;
        if ((TextUtils.isEmpty(dlnaContentEntity.getProtocolInfo()) || !dlnaContentEntity.getProtocolInfo().contains(":audio")) && (TextUtils.isEmpty(dlnaContentEntity.getUrl()) || !dlnaContentEntity.getUrl().contains(".mp3"))) {
            c0992a.f1845i = true;
        } else {
            c0992a.f1845i = false;
        }
        ArrayList arrayList = new ArrayList(1);
        C0993b c0993b = new C0993b();
        c0993b.f1850a = dlnaContentEntity.getUrl();
        String strM2379b = C2026b.m2379b(R.string.code_rate_hd);
        c0993b.f1851b = strM2379b;
        c0993b.f1852c = strM2379b;
        c0993b.f1853d = false;
        arrayList.add(c0993b);
        c0992a.f1843g = arrayList;
        Objects.requireNonNull(warmVideoPlayer);
        warmVideoPlayer.f514v = 0;
        warmVideoPlayer.getMediaController().m368s();
        warmVideoPlayer.setPlay(c0992a);
        warmVideoPlayer.mo388s(c0992a.m1011e(false), false, null, false);
    }
}
