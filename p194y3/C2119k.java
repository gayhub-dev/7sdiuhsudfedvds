package p194y3;

import p009b.C0413b;
import p032d4.C0871b;
import p138q4.EnumC1776h;

/* compiled from: Notification.java */
/* renamed from: y3.k */
/* loaded from: classes.dex */
public final class C2119k<T> {

    /* renamed from: b */
    public static final C2119k<Object> f6247b = new C2119k<>(null);

    /* renamed from: a */
    public final Object f6248a;

    public C2119k(Object obj) {
        this.f6248a = obj;
    }

    /* renamed from: a */
    public Throwable m2556a() {
        Object obj = this.f6248a;
        if (obj instanceof EnumC1776h.b) {
            return ((EnumC1776h.b) obj).f5061e;
        }
        return null;
    }

    /* renamed from: b */
    public T m2557b() {
        T t6 = (T) this.f6248a;
        if (t6 == null || (t6 instanceof EnumC1776h.b)) {
            return null;
        }
        return t6;
    }

    /* renamed from: c */
    public boolean m2558c() {
        Object obj = this.f6248a;
        return (obj == null || (obj instanceof EnumC1776h.b)) ? false : true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C2119k) {
            return C0871b.m676a(this.f6248a, ((C2119k) obj).f6248a);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f6248a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public String toString() {
        Object obj = this.f6248a;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (obj instanceof EnumC1776h.b) {
            StringBuilder sbM112a = C0413b.m112a("OnErrorNotification[");
            sbM112a.append(((EnumC1776h.b) obj).f5061e);
            sbM112a.append("]");
            return sbM112a.toString();
        }
        StringBuilder sbM112a2 = C0413b.m112a("OnNextNotification[");
        sbM112a2.append(this.f6248a);
        sbM112a2.append("]");
        return sbM112a2.toString();
    }
}
