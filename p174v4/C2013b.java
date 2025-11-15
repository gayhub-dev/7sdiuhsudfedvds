package p174v4;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p009b.C0413b;
import p032d4.C0871b;

/* compiled from: Timed.java */
/* renamed from: v4.b */
/* loaded from: classes.dex */
public final class C2013b<T> {

    /* renamed from: a */
    public final T f5860a;

    /* renamed from: b */
    public final long f5861b;

    /* renamed from: c */
    public final TimeUnit f5862c;

    public C2013b(T t6, long j7, TimeUnit timeUnit) {
        this.f5860a = t6;
        this.f5861b = j7;
        Objects.requireNonNull(timeUnit, "unit is null");
        this.f5862c = timeUnit;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C2013b)) {
            return false;
        }
        C2013b c2013b = (C2013b) obj;
        return C0871b.m676a(this.f5860a, c2013b.f5860a) && this.f5861b == c2013b.f5861b && C0871b.m676a(this.f5862c, c2013b.f5862c);
    }

    public int hashCode() {
        T t6 = this.f5860a;
        int iHashCode = t6 != null ? t6.hashCode() : 0;
        long j7 = this.f5861b;
        return this.f5862c.hashCode() + (((iHashCode * 31) + ((int) (j7 ^ (j7 >>> 31)))) * 31);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Timed[time=");
        sbM112a.append(this.f5861b);
        sbM112a.append(", unit=");
        sbM112a.append(this.f5862c);
        sbM112a.append(", value=");
        sbM112a.append(this.f5860a);
        sbM112a.append("]");
        return sbM112a.toString();
    }
}
