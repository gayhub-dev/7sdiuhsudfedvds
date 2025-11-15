package p130p4;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import p005a4.C0012d;
import p074i6.InterfaceC1169c;
import p160t4.C1908a;

/* compiled from: SubscriptionHelper.java */
/* renamed from: p4.c */
/* loaded from: classes.dex */
public enum EnumC1740c implements InterfaceC1169c {
    CANCELLED;

    /* renamed from: a */
    public static boolean m1889a(long j7) {
        if (j7 > 0) {
            return true;
        }
        C1908a.m2205b(new IllegalArgumentException(C0534b.m341a("n > 0 required but it was ", j7)));
        return false;
    }

    /* renamed from: b */
    public static boolean m1890b(InterfaceC1169c interfaceC1169c, InterfaceC1169c interfaceC1169c2) {
        if (interfaceC1169c2 == null) {
            C1908a.m2205b(new NullPointerException("next is null"));
            return false;
        }
        if (interfaceC1169c == null) {
            return true;
        }
        interfaceC1169c2.cancel();
        C1908a.m2205b(new C0012d("Subscription already set!"));
        return false;
    }

    @Override // p074i6.InterfaceC1169c
    public void cancel() {
    }

    @Override // p074i6.InterfaceC1169c
    public void request(long j7) {
    }
}
