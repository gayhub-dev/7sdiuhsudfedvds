package p093l1;

import android.app.Application;
import p136q2.InterfaceC1764d;

/* compiled from: CtvitInit.java */
/* renamed from: l1.d */
/* loaded from: classes.dex */
public class C1418d {

    /* renamed from: c */
    public static volatile C1418d f4153c;

    /* renamed from: a */
    public Application f4154a;

    /* renamed from: b */
    public InterfaceC1764d f4155b = new a(this);

    /* compiled from: CtvitInit.java */
    /* renamed from: l1.d$a */
    public class a implements InterfaceC1764d {
        public a(C1418d c1418d) {
        }
    }

    /* renamed from: a */
    public static C1418d m1602a() {
        if (f4153c == null) {
            synchronized (C1418d.class) {
                if (f4153c == null) {
                    f4153c = new C1418d();
                }
            }
        }
        return f4153c;
    }
}
