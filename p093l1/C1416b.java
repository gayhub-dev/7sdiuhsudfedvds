package p093l1;

import com.ctvit.network.callback.SimpleCallBack;
import com.ctvit.network.exception.ApiException;
import p186x2.C2073a;
import p200z2.C2150a;

/* compiled from: CrashHandler.java */
/* renamed from: l1.b */
/* loaded from: classes.dex */
public class C1416b extends SimpleCallBack<String> {
    public C1416b(C1417c c1417c) {
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        super.onError(apiException);
        C2073a.m2456a("error = onError");
    }

    @Override // com.ctvit.network.callback.SimpleCallBack, com.ctvit.network.callback.CallBack
    public void onSuccess(Object obj) {
        super.onSuccess((String) obj);
        C2073a.m2456a("error = onSuccess");
        C2150a.m2591b("error_info", "");
    }
}
