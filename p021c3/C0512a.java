package p021c3;

import com.ctvit.network.CtvitHttp;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import p031d3.C0869b;
import p031d3.InterfaceC0868a;
import p039e3.C0947a;
import p186x2.C2073a;

/* compiled from: CtvitWebSocket.java */
/* renamed from: c3.a */
/* loaded from: classes.dex */
public class C0512a {

    /* renamed from: a */
    public String f385a;

    /* renamed from: c */
    public WebSocket f387c;

    /* renamed from: e */
    public OkHttpClient f389e;

    /* renamed from: f */
    public InterfaceC0868a f390f;

    /* renamed from: g */
    public C0869b f391g;

    /* renamed from: b */
    public int f386b = CtvitHttp.DEFAULT_MILLISECONDS;

    /* renamed from: d */
    public C0947a f388d = new C0947a();

    /* renamed from: a */
    public void m319a() {
        if (this.f387c != null) {
            C2073a.m2459d("强制关闭连接");
            C0869b c0869b = this.f391g;
            if (c0869b != null) {
                c0869b.f1297c = true;
            }
            this.f387c.cancel();
        }
    }

    /* renamed from: b */
    public void m320b(String str) {
        WebSocket webSocket = this.f387c;
        if (webSocket == null || str == null) {
            return;
        }
        C2073a.m2459d("WebSocket Send Status：" + webSocket.send(str) + " | msg：" + str);
    }
}
