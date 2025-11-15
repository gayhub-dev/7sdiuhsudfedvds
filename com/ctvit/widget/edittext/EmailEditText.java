package com.ctvit.widget.edittext;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import com.ctvit.widget.R$string;
import com.ctvit.widget.edittext.CommonEditText;
import java.util.Objects;
import p086k2.C1231b;
import p172v2.C2004a;

/* loaded from: classes.dex */
public class EmailEditText extends CommonEditText {

    /* renamed from: f */
    public C0706b f977f;

    /* renamed from: com.ctvit.widget.edittext.EmailEditText$a */
    public class C0705a extends NumberKeyListener {
        public C0705a(EmailEditText emailEditText) {
        }

        @Override // android.text.method.NumberKeyListener
        public char[] getAcceptedChars() {
            return "ABCDEFGHIJKLMNOPQRSTUVWXYZ@._-0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 32;
        }
    }

    /* renamed from: com.ctvit.widget.edittext.EmailEditText$b */
    public static class C0706b extends CommonEditText.C0704c {

        /* renamed from: h */
        public String f978h;

        public C0706b(CommonEditText commonEditText) {
            super(commonEditText);
        }
    }

    public EmailEditText(Context context) {
        this(context, null);
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: b */
    public boolean mo531b() {
        boolean z6;
        if (!super.mo531b()) {
            return false;
        }
        String textTrim = getTextTrim();
        if (TextUtils.isEmpty(textTrim) ? false : textTrim.matches("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$")) {
            z6 = true;
        } else {
            if (!TextUtils.isEmpty(mo530a().f978h)) {
                new C2004a(mo530a().f978h).m2345a();
            }
            Objects.requireNonNull(mo530a());
            z6 = false;
        }
        return z6;
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: c */
    public void mo532c() {
        C0706b c0706bMo530a = mo530a();
        int i7 = R$string.email_format_tips;
        Objects.requireNonNull(c0706bMo530a);
        c0706bMo530a.f978h = C1231b.f2761c.getResources().getString(i7);
        c0706bMo530a.f971b = 0;
        c0706bMo530a.f972c = 64;
        c0706bMo530a.m537d(i7);
        c0706bMo530a.m536c(i7);
        c0706bMo530a.m535b(i7);
        c0706bMo530a.m534a();
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: d */
    public void mo533d() {
        super.mo533d();
        setKeyListener(new C0705a(this));
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public C0706b mo530a() {
        if (this.f977f == null) {
            C0706b c0706b = new C0706b(this);
            this.f977f = c0706b;
            c0706b.m534a();
        }
        return this.f977f;
    }

    public EmailEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmailEditText(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }
}
