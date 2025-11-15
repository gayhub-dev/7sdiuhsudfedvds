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
public class PhoneNumberEditText extends CommonEditText {

    /* renamed from: f */
    public C0709b f980f;

    /* renamed from: com.ctvit.widget.edittext.PhoneNumberEditText$a */
    public class C0708a extends NumberKeyListener {
        public C0708a(PhoneNumberEditText phoneNumberEditText) {
        }

        @Override // android.text.method.NumberKeyListener
        public char[] getAcceptedChars() {
            return "0123456789".toCharArray();
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 2;
        }
    }

    /* renamed from: com.ctvit.widget.edittext.PhoneNumberEditText$b */
    public static class C0709b extends CommonEditText.C0704c {

        /* renamed from: h */
        public String f981h;

        public C0709b(CommonEditText commonEditText) {
            super(commonEditText);
        }
    }

    public PhoneNumberEditText(Context context) {
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
        if (TextUtils.isEmpty(textTrim) ? false : textTrim.matches("^[1][0-9][0-9]{9}$")) {
            z6 = true;
        } else {
            if (!TextUtils.isEmpty(mo530a().f981h)) {
                new C2004a(mo530a().f981h).m2345a();
            }
            Objects.requireNonNull(mo530a());
            z6 = false;
        }
        return z6;
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: c */
    public void mo532c() {
        C0709b c0709bMo530a = mo530a();
        int i7 = R$string.phone_number_format_tips;
        Objects.requireNonNull(c0709bMo530a);
        c0709bMo530a.f981h = C1231b.f2761c.getResources().getString(i7);
        c0709bMo530a.f971b = 11;
        c0709bMo530a.f972c = 11;
        c0709bMo530a.m537d(i7);
        c0709bMo530a.m536c(i7);
        c0709bMo530a.m535b(i7);
        c0709bMo530a.m534a();
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: d */
    public void mo533d() {
        super.mo533d();
        setKeyListener(new C0708a(this));
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public C0709b mo530a() {
        if (this.f980f == null) {
            C0709b c0709b = new C0709b(this);
            this.f980f = c0709b;
            c0709b.m534a();
        }
        return this.f980f;
    }

    public PhoneNumberEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhoneNumberEditText(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }
}
