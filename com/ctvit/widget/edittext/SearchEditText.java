package com.ctvit.widget.edittext;

import android.content.Context;
import android.util.AttributeSet;
import com.ctvit.widget.R$string;
import com.ctvit.widget.edittext.CommonEditText;

/* loaded from: classes.dex */
public class SearchEditText extends CommonEditText {

    /* renamed from: f */
    public C0710a f982f;

    /* renamed from: com.ctvit.widget.edittext.SearchEditText$a */
    public static class C0710a extends CommonEditText.C0704c {
        public C0710a(CommonEditText commonEditText) {
            super(commonEditText);
        }
    }

    public SearchEditText(Context context) {
        this(context, null);
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: a */
    public CommonEditText.C0704c mo530a() {
        if (this.f982f == null) {
            C0710a c0710a = new C0710a(this);
            this.f982f = c0710a;
            c0710a.m534a();
        }
        return this.f982f;
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: b */
    public boolean mo531b() {
        return super.mo531b();
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: c */
    public void mo532c() {
        if (this.f982f == null) {
            C0710a c0710a = new C0710a(this);
            this.f982f = c0710a;
            c0710a.m534a();
        }
        C0710a c0710a2 = this.f982f;
        c0710a2.f971b = 0;
        c0710a2.f972c = 80;
        c0710a2.f973d = true;
        c0710a2.m535b(R$string.search_length_max_tips);
        c0710a2.m534a();
    }

    @Override // com.ctvit.widget.edittext.CommonEditText
    /* renamed from: d */
    public void mo533d() {
        super.mo533d();
    }

    public SearchEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SearchEditText(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setInputType(1);
        setImeOptions(3);
    }
}
