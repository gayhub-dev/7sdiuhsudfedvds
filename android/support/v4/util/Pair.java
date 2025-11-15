package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Pair<F, S> {

    @Nullable
    public final F first;

    @Nullable
    public final S second;

    public Pair(@Nullable F f7, @Nullable S s6) {
        this.first = f7;
        this.second = s6;
    }

    @NonNull
    public static <A, B> Pair<A, B> create(@Nullable A a7, @Nullable B b7) {
        return new Pair<>(a7, b7);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return ObjectsCompat.equals(pair.first, this.first) && ObjectsCompat.equals(pair.second, this.second);
    }

    public int hashCode() {
        F f7 = this.first;
        int iHashCode = f7 == null ? 0 : f7.hashCode();
        S s6 = this.second;
        return iHashCode ^ (s6 != null ? s6.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Pair{");
        sbM112a.append(String.valueOf(this.first));
        sbM112a.append(" ");
        sbM112a.append(String.valueOf(this.second));
        sbM112a.append("}");
        return sbM112a.toString();
    }
}
