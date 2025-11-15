package p055g3;

import java.util.HashMap;
import java.util.Map;
import p009b.C0413b;

/* compiled from: PolyvPlayOption.java */
/* renamed from: g3.a */
/* loaded from: classes.dex */
public class C1058a {

    /* renamed from: a */
    public Map<String, Object> f1993a = new HashMap();

    /* compiled from: PolyvPlayOption.java */
    /* renamed from: g3.a$a */
    public static class a {

        /* renamed from: a */
        public final int f1994a;

        /* renamed from: b */
        public final int f1995b;

        public a(int i7, int i8) {
            this.f1994a = i7;
            this.f1995b = i8;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("TailAdOption{enableAVFrameSpeed=");
            sbM112a.append(this.f1994a);
            sbM112a.append(", duration=");
            sbM112a.append(this.f1995b);
            sbM112a.append('}');
            return sbM112a.toString();
        }
    }

    /* compiled from: PolyvPlayOption.java */
    /* renamed from: g3.a$c */
    public static class c {
        public String toString() {
            return "HeadAdOption{headAdPath='null', headAdDuration=0}";
        }
    }

    /* compiled from: PolyvPlayOption.java */
    /* renamed from: g3.a$d */
    public static class d {

        /* renamed from: a */
        public final String f2003a;

        /* renamed from: b */
        public final int f2004b;

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("TailAdOption{tailAdPath='");
            sbM112a.append(this.f2003a);
            sbM112a.append('\'');
            sbM112a.append(", tailAdDuration=");
            sbM112a.append(this.f2004b);
            sbM112a.append('}');
            return sbM112a.toString();
        }
    }

    /* renamed from: a */
    public static C1058a m1053a() {
        C1058a c1058a = new C1058a();
        c1058a.f1993a.put("KEY_DECODEMODE", 0);
        c1058a.f1993a.put("KEY_PLAYMODE", 4);
        c1058a.f1993a.put("KEY_FRAMEDROP", 1);
        c1058a.f1993a.put("KEY_TIMEOUT", 20);
        c1058a.f1993a.put("KEY_RECONNECTION_COUNT", 3);
        c1058a.f1993a.put("KEY_PRELOADTIME", 3);
        c1058a.f1993a.put("KEY_LOADINGVIEW_DELAY", 0);
        Boolean bool = Boolean.FALSE;
        c1058a.f1993a.put("KEY_VR_ON", bool);
        c1058a.f1993a.put("KEY_ENABLE_AMBISONIC", bool);
        return c1058a;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("PolyvPlayOption{options=");
        sbM112a.append(this.f1993a);
        sbM112a.append('}');
        return sbM112a.toString();
    }

    /* compiled from: PolyvPlayOption.java */
    /* renamed from: g3.a$b */
    public static class b {

        /* renamed from: a */
        public final int f1996a;

        /* renamed from: b */
        public String f1997b;

        /* renamed from: c */
        public String f1998c;

        /* renamed from: d */
        public final String f1999d;

        /* renamed from: e */
        public final String f2000e;

        /* renamed from: f */
        public final String f2001f;

        /* renamed from: g */
        public final String f2002g;

        public b() {
            this.f1996a = 0;
            this.f1997b = "";
            this.f1998c = "";
            this.f1999d = "";
            this.f2000e = "";
            this.f2001f = "";
            this.f2002g = "";
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("TailAdOption{chinadrmType=");
            sbM112a.append(this.f1996a);
            sbM112a.append(", licenseSavePath=");
            sbM112a.append(this.f1997b);
            sbM112a.append(", deviceId=");
            sbM112a.append(this.f1998c);
            sbM112a.append(", provisionUrl=");
            sbM112a.append(this.f1999d);
            sbM112a.append(", contentId=");
            sbM112a.append(this.f2000e);
            sbM112a.append(", licenseUrl=");
            sbM112a.append(this.f2001f);
            sbM112a.append(", emergencyUrl=");
            sbM112a.append(this.f2002g);
            sbM112a.append('}');
            return sbM112a.toString();
        }

        public b(int i7, String str, String str2, String str3, String str4, String str5, String str6) {
            this.f1996a = i7;
            this.f1997b = str;
            this.f1998c = str2;
            this.f1999d = str3;
            this.f2000e = str4;
            this.f2001f = str5;
            this.f2002g = str6;
        }
    }
}
