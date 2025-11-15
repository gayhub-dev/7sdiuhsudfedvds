package okhttp3.logging;

import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

/* compiled from: HttpLoggingInterceptor.java */
/* renamed from: okhttp3.logging.a */
/* loaded from: classes.dex */
public final /* synthetic */ class C1638a {
    static {
        HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;
    }

    /* renamed from: b */
    public static /* synthetic */ void m1867b(String str) {
        Platform.get().log(4, str, null);
    }
}
