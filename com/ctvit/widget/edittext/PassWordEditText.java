package com.ctvit.widget.edittext;

import android.content.Context;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import com.ctvit.widget.R$string;
import com.ctvit.widget.edittext.CommonEditText;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public class PassWordEditText extends CommonEditText {

    /* renamed from: f */
    public C0707a f979f;

    /* renamed from: com.ctvit.widget.edittext.PassWordEditText$a */
    public static class C0707a extends CommonEditText.C0704c {
        public C0707a(CommonEditText commonEditText) {
            super(commonEditText);
        }
    }

    public PassWordEditText(Context context) {
        this(context, null);
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: a */
    public CommonEditText.C0704c mo530a() {
        if (this.f979f == null) {
            C0707a c0707a = new C0707a(this);
            this.f979f = c0707a;
            c0707a.m534a();
        }
        return this.f979f;
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: b */
    public boolean mo531b() {
        return super.mo531b();
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: c */
    public void mo532c() {
        if (this.f979f == null) {
            C0707a c0707a = new C0707a(this);
            this.f979f = c0707a;
            c0707a.m534a();
        }
        C0707a c0707a2 = this.f979f;
        c0707a2.f971b = 6;
        c0707a2.f972c = 16;
        c0707a2.m537d(R$string.password_is_null_tips);
        c0707a2.m536c(R$string.password_length_min_tips);
        c0707a2.m535b(R$string.password_length_max_tips);
        c0707a2.m534a();
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: d */
    public void mo533d() {
        super.mo533d();
    }

    public PassWordEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PassWordEditText(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setInputType(IjkMediaMeta.FF_PROFILE_H264_HIGH_444);
        setTransformationMethod(PasswordTransformationMethod.getInstance());
        setSelection(getTextTrim().length());
    }
}
