package p162u;

import android.support.constraint.motion.C0079a;
import java.io.IOException;

/* compiled from: HttpException.java */
/* renamed from: u.e */
/* loaded from: classes.dex */
public final class C1961e extends IOException {
    public C1961e(int i7) {
        super(C0079a.m93a("Http request failed with status code: ", i7), null);
    }

    public C1961e(String str) {
        super(str, null);
    }

    public C1961e(String str, int i7) {
        super(str, null);
    }
}
