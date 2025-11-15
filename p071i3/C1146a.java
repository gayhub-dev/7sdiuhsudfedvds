package p071i3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.easefun.povplayer.core.R$string;

/* compiled from: Settings.java */
/* renamed from: i3.a */
/* loaded from: classes.dex */
public class C1146a {

    /* renamed from: a */
    public Context f2518a;

    /* renamed from: b */
    public SharedPreferences f2519b;

    public C1146a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f2518a = applicationContext;
        this.f2519b = PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    /* renamed from: a */
    public int m1311a() {
        try {
            return Integer.valueOf(this.f2519b.getString(this.f2518a.getString(R$string.pref_key_player), "")).intValue();
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    /* renamed from: b */
    public int m1312b() {
        return this.f2519b.getInt("polyv.pref.render_view_type", 2);
    }

    /* renamed from: c */
    public boolean m1313c() {
        return this.f2519b.getBoolean(this.f2518a.getString(R$string.pref_key_using_mediadatasource), false);
    }
}
