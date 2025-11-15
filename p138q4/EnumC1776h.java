package p138q4;

import java.io.Serializable;
import p009b.C0413b;
import p032d4.C0871b;
import p074i6.InterfaceC1169c;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: NotificationLite.java */
/* renamed from: q4.h */
/* loaded from: classes.dex */
public enum EnumC1776h {
    COMPLETE;

    /* compiled from: NotificationLite.java */
    /* renamed from: q4.h$a */
    public static final class a implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;

        /* renamed from: e */
        public final InterfaceC2153b f5060e;

        public a(InterfaceC2153b interfaceC2153b) {
            this.f5060e = interfaceC2153b;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("NotificationLite.Disposable[");
            sbM112a.append(this.f5060e);
            sbM112a.append("]");
            return sbM112a.toString();
        }
    }

    /* compiled from: NotificationLite.java */
    /* renamed from: q4.h$b */
    public static final class b implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;

        /* renamed from: e */
        public final Throwable f5061e;

        public b(Throwable th) {
            this.f5061e = th;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return C0871b.m676a(this.f5061e, ((b) obj).f5061e);
            }
            return false;
        }

        public int hashCode() {
            return this.f5061e.hashCode();
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("NotificationLite.Error[");
            sbM112a.append(this.f5061e);
            sbM112a.append("]");
            return sbM112a.toString();
        }
    }

    /* compiled from: NotificationLite.java */
    /* renamed from: q4.h$c */
    public static final class c implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;

        /* renamed from: e */
        public final InterfaceC1169c f5062e;

        public c(InterfaceC1169c interfaceC1169c) {
            this.f5062e = interfaceC1169c;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("NotificationLite.Subscription[");
            sbM112a.append(this.f5062e);
            sbM112a.append("]");
            return sbM112a.toString();
        }
    }

    /* renamed from: a */
    public static <T> boolean m1962a(Object obj, InterfaceC2127s<? super T> interfaceC2127s) {
        if (obj == COMPLETE) {
            interfaceC2127s.onComplete();
            return true;
        }
        if (obj instanceof b) {
            interfaceC2127s.onError(((b) obj).f5061e);
            return true;
        }
        interfaceC2127s.onNext(obj);
        return false;
    }

    /* renamed from: b */
    public static <T> boolean m1963b(Object obj, InterfaceC2127s<? super T> interfaceC2127s) {
        if (obj == COMPLETE) {
            interfaceC2127s.onComplete();
            return true;
        }
        if (obj instanceof b) {
            interfaceC2127s.onError(((b) obj).f5061e);
            return true;
        }
        if (obj instanceof a) {
            interfaceC2127s.onSubscribe(((a) obj).f5060e);
            return false;
        }
        interfaceC2127s.onNext(obj);
        return false;
    }

    /* renamed from: c */
    public static boolean m1964c(Object obj) {
        return obj == COMPLETE;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }
}
