package p171v1;

import android.text.TextUtils;
import com.ctvit.dlna.entity.CctvEntity;
import java.util.List;
import p045f1.C0992a;
import p045f1.C0993b;
import p186x2.C2073a;

/* compiled from: ControlCctvPlayer.java */
/* renamed from: v1.b */
/* loaded from: classes.dex */
public class C2003b {
    /* renamed from: a */
    public static String m2340a(C0992a c0992a) {
        CctvEntity.PlayerBean player;
        if (c0992a == null) {
            return "";
        }
        try {
            Object obj = c0992a.f1844h;
            if ((obj instanceof CctvEntity) && (player = ((CctvEntity) obj).getPlayer()) != null) {
                return player.getHigh_bitrate_id();
            }
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
        return "";
    }

    /* renamed from: b */
    public static boolean m2341b(C0992a c0992a) {
        CctvEntity.PlayerBean player;
        if (c0992a == null) {
            return false;
        }
        try {
            Object obj = c0992a.f1844h;
            if (!(obj instanceof CctvEntity) || (player = ((CctvEntity) obj).getPlayer()) == null) {
                return false;
            }
            return !TextUtils.isEmpty(player.getChange_bitrate());
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m2342c(String str, String str2) {
        C2073a.m2459d("是否为同一个视频：curPlayerId=" + str + " | willPlayerId=" + str2);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            z = str2.contains(str) || str.contains(str2);
            C2073a.m2459d("是否为同一个视频：" + z);
        }
        return z;
    }

    /* renamed from: d */
    public static void m2343d(C0992a c0992a) throws NumberFormatException {
        CctvEntity.PlayerBean player;
        try {
            Object obj = c0992a.f1844h;
            if (!(obj instanceof CctvEntity) || (player = ((CctvEntity) obj).getPlayer()) == null || TextUtils.isEmpty(player.getChange_bitrate())) {
                return;
            }
            C2073a.m2459d("切换码率索引：" + player.getChange_bitrate());
            int i7 = Integer.parseInt(player.getChange_bitrate());
            List<C0993b> list = c0992a.f1843g;
            if (list == null || list.isEmpty() || i7 >= list.size() || i7 <= -1) {
                return;
            }
            for (int i8 = 0; i8 < list.size(); i8++) {
                C0993b c0993b = list.get(i8);
                c0993b.f1853d = false;
                if (i8 == i7) {
                    C2073a.m2459d("切换码率索引 - 设置默认码率：" + i8);
                    c0993b.f1853d = true;
                }
            }
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
    }

    /* renamed from: e */
    public static void m2344e(String str, String str2, CctvEntity.PlayerBean playerBean) {
        C2073a.m2459d("续播条件：curPlayerId=" + str + " | willPlayerId=" + str2 + " | playerBean=" + playerBean);
        boolean z6 = m2342c(str, str2) && playerBean.isKeep_on() && "vod".equals(playerBean.getType());
        playerBean.setKeep_on(z6);
        C2073a.m2459d("是否续播：" + z6);
    }
}
