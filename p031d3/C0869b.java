package p031d3;

import java.nio.charset.Charset;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import p039e3.C0947a;
import p101m1.C1458b;
import p186x2.C2073a;
import p192y1.C2104h;

/* compiled from: WSListener.java */
/* renamed from: d3.b */
/* loaded from: classes.dex */
public class C0869b extends WebSocketListener {

    /* renamed from: a */
    public C0947a f1295a;

    /* renamed from: b */
    public InterfaceC0868a f1296b;

    /* renamed from: c */
    public boolean f1297c;

    public C0869b(C0947a c0947a) {
        this.f1295a = c0947a;
    }

    @Override // okhttp3.WebSocketListener
    public void onClosed(WebSocket webSocket, int i7, String str) {
        C2073a.m2459d("连接关闭：code=" + i7 + ",reason=" + str);
        InterfaceC0868a interfaceC0868a = this.f1296b;
        if (interfaceC0868a != null) {
            ((C2104h) interfaceC0868a).m2547b(3);
        }
    }

    @Override // okhttp3.WebSocketListener
    public void onClosing(WebSocket webSocket, int i7, String str) {
        C2073a.m2459d("连接准备关闭：code=" + i7 + ",reason=" + str);
        InterfaceC0868a interfaceC0868a = this.f1296b;
        if (interfaceC0868a != null) {
            ((C2104h) interfaceC0868a).m2547b(4);
        }
    }

    @Override // okhttp3.WebSocketListener
    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
        if (this.f1297c) {
            this.f1297c = false;
            C2073a.m2459d("主动释放了连接！");
            return;
        }
        C2073a.m2457b("连接失败：", th);
        InterfaceC0868a interfaceC0868a = this.f1296b;
        if (interfaceC0868a != null) {
            ((C2104h) interfaceC0868a).m2547b(2);
        }
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, String str) {
        C1458b.m1642a("收到消息String：", str);
        C0947a c0947a = this.f1295a;
        if (c0947a != null) {
            c0947a.m859a(str);
        }
    }

    @Override // okhttp3.WebSocketListener
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        C2073a.m2459d("连接成功");
        InterfaceC0868a interfaceC0868a = this.f1296b;
        if (interfaceC0868a != null) {
            ((C2104h) interfaceC0868a).m2547b(1);
        }
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, ByteString byteString) {
        String strString = byteString.string(Charset.defaultCharset());
        C1458b.m1642a("收到消息Bytes：", strString);
        C0947a c0947a = this.f1295a;
        if (c0947a != null) {
            c0947a.m859a(strString);
        }
    }
}
