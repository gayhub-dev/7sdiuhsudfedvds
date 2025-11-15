package p114n6;

import android.os.Bundle;

/* compiled from: RationaleDialogConfig.java */
/* renamed from: n6.b */
/* loaded from: classes.dex */
public class C1563b {

    /* renamed from: a */
    public String f4685a;

    /* renamed from: b */
    public String f4686b;

    /* renamed from: c */
    public int f4687c;

    /* renamed from: d */
    public int f4688d;

    /* renamed from: e */
    public String f4689e;

    /* renamed from: f */
    public String[] f4690f;

    public C1563b(Bundle bundle) {
        this.f4685a = bundle.getString("positiveButton");
        this.f4686b = bundle.getString("negativeButton");
        this.f4689e = bundle.getString("rationaleMsg");
        this.f4687c = bundle.getInt("theme");
        this.f4688d = bundle.getInt("requestCode");
        this.f4690f = bundle.getStringArray("permissions");
    }
}
