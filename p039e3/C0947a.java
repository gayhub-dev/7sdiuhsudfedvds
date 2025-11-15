package p039e3;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.cctv.p025tv.module.service.WebSocketService;
import p101m1.C1458b;
import p192y1.C2103g;

/* compiled from: MsgHandle.java */
/* renamed from: e3.a */
/* loaded from: classes.dex */
public class C0947a {

    /* renamed from: a */
    public C2103g f1712a;

    /* renamed from: b */
    public Handler f1713b = new a(Looper.getMainLooper());

    /* compiled from: MsgHandle.java */
    /* renamed from: e3.a$a */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1 || C0947a.this.f1712a == null) {
                return;
            }
            String str = (String) message.obj;
            int i7 = WebSocketService.f653m;
            C1458b.m1642a("监听收到的消息 data= ", str);
        }
    }

    /* renamed from: a */
    public void m859a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Message message = new Message();
        message.what = 1;
        message.obj = str;
        this.f1713b.sendMessage(message);
    }
}
