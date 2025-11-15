package p172v2;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.ctvit.toast.R$color;
import com.ctvit.toast.R$drawable;
import com.ctvit.toast.R$id;
import com.ctvit.toast.R$layout;
import p165u2.C1974a;

/* compiled from: NormalToast.java */
/* renamed from: v2.a */
/* loaded from: classes.dex */
public class C2004a {

    /* renamed from: a */
    public int f5846a = 0;

    /* renamed from: b */
    public String f5847b;

    /* renamed from: c */
    public View f5848c;

    /* renamed from: d */
    public int f5849d;

    /* renamed from: e */
    public int f5850e;

    public C2004a(String str) {
        this.f5847b = str == null ? "" : str;
        this.f5848c = View.inflate(C1974a.f5773c, R$layout.view_normal_toast, null);
        this.f5849d = R$color.toast_text_color;
        this.f5850e = R$drawable.toast_normal_bg;
    }

    /* renamed from: a */
    public void m2345a() {
        if (C1974a.m2298a().f5774a != null && ((TextView) C1974a.m2298a().f5774a.getView().findViewById(R$id.ctvit_toast_message)) == null) {
            C1974a.m2298a().f5774a.cancel();
            C1974a.m2298a().f5774a = null;
        }
        if (C1974a.m2298a().f5774a == null) {
            C1974a.m2298a().f5774a = new Toast(C1974a.f5773c);
        }
        C1974a.m2298a().f5774a.setGravity(17, 0, 0);
        C1974a.m2298a().f5774a.setDuration(this.f5846a);
        C1974a.m2298a().f5774a.setView(this.f5848c);
        TextView textView = (TextView) C1974a.m2298a().f5774a.getView().findViewById(R$id.ctvit_toast_message);
        if (textView != null) {
            textView.setText(this.f5847b);
            textView.setTextColor(C1974a.f5773c.getResources().getColor(this.f5849d));
        }
        this.f5848c.setBackground(C1974a.f5773c.getResources().getDrawable(this.f5850e));
        C1974a.m2298a().f5774a.show();
    }
}
