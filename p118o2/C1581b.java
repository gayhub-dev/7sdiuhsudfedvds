package p118o2;

import android.content.Context;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.ctvit.dlna.R$string;
import java.util.Objects;
import org.fourthline.cling.android.AndroidUpnpService;
import p054g2.C1057h;
import p136q2.InterfaceC1761a;
import p136q2.InterfaceC1763c;
import p136q2.InterfaceC1764d;
import p136q2.InterfaceC1765e;
import p144r2.C1832e;

/* compiled from: CtvitDlna.java */
/* renamed from: o2.b */
/* loaded from: classes.dex */
public final class C1581b {

    /* renamed from: l */
    public static volatile C1581b f4743l;

    /* renamed from: m */
    public static Context f4744m;

    /* renamed from: a */
    public AndroidUpnpService f4745a;

    /* renamed from: b */
    public C1832e f4746b;

    /* renamed from: c */
    public InterfaceC1763c f4747c;

    /* renamed from: d */
    public InterfaceC1761a f4748d;

    /* renamed from: e */
    public C1057h f4749e;

    /* renamed from: f */
    public C1057h f4750f;

    /* renamed from: g */
    public C1057h f4751g;

    /* renamed from: h */
    public C1057h f4752h;

    /* renamed from: i */
    public InterfaceC1764d f4753i;

    /* renamed from: j */
    public ServiceConnection f4754j;

    /* renamed from: k */
    public boolean f4755k;

    /* renamed from: a */
    public static InterfaceC1761a m1832a() {
        return m1836e().f4748d;
    }

    /* renamed from: b */
    public static InterfaceC1763c m1833b() {
        return m1836e().f4747c;
    }

    /* renamed from: c */
    public static void m1834c() {
        Objects.requireNonNull(m1836e());
    }

    /* renamed from: d */
    public static InterfaceC1764d m1835d() {
        return m1836e().f4753i;
    }

    /* renamed from: e */
    public static C1581b m1836e() {
        if (f4743l == null) {
            synchronized (C1581b.class) {
                if (f4743l == null) {
                    f4743l = new C1581b();
                }
            }
        }
        return f4743l;
    }

    /* renamed from: f */
    public static String m1837f() {
        m1836e();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(f4744m);
        m1836e();
        return defaultSharedPreferences.getString("dlan_player_name", f4744m.getString(R$string.dlan_player_name));
    }

    /* renamed from: g */
    public static InterfaceC1765e m1838g() {
        Objects.requireNonNull(m1836e());
        return null;
    }
}
