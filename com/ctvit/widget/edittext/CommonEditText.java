package com.ctvit.widget.edittext;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Objects;
import p047f3.C0995a;
import p086k2.C1231b;

/* loaded from: classes.dex */
public class CommonEditText extends AppCompatEditText {

    /* renamed from: e */
    public C0704c f967e;

    /* renamed from: com.ctvit.widget.edittext.CommonEditText$a */
    public class ViewOnFocusChangeListenerC0702a implements View.OnFocusChangeListener {
        public ViewOnFocusChangeListenerC0702a() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z6) {
            Objects.requireNonNull(CommonEditText.this.mo530a());
        }
    }

    /* renamed from: com.ctvit.widget.edittext.CommonEditText$b */
    public class C0703b implements TextWatcher {
        public C0703b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            Objects.requireNonNull(CommonEditText.this.mo530a());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }
    }

    /* renamed from: com.ctvit.widget.edittext.CommonEditText$c */
    public static class C0704c {

        /* renamed from: a */
        public CommonEditText f970a;

        /* renamed from: b */
        public int f971b;

        /* renamed from: c */
        public int f972c = 50;

        /* renamed from: d */
        public boolean f973d;

        /* renamed from: e */
        public String f974e;

        /* renamed from: f */
        public String f975f;

        /* renamed from: g */
        public String f976g;

        public C0704c(CommonEditText commonEditText) {
            this.f970a = commonEditText;
        }

        /* renamed from: a */
        public void m534a() {
            this.f970a.setFilters(this.f973d ? new InputFilter[]{new C0995a(this.f972c)} : new InputFilter[]{new InputFilter.LengthFilter(this.f972c)});
        }

        /* renamed from: b */
        public C0704c m535b(int i7) {
            this.f976g = C1231b.f2761c.getResources().getString(i7);
            return this;
        }

        /* renamed from: c */
        public C0704c m536c(int i7) {
            this.f975f = C1231b.f2761c.getResources().getString(i7);
            return this;
        }

        /* renamed from: d */
        public C0704c m537d(int i7) {
            this.f974e = C1231b.f2761c.getResources().getString(i7);
            return this;
        }
    }

    public CommonEditText(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public C0704c mo530a() {
        if (this.f967e == null) {
            C0704c c0704c = new C0704c(this);
            this.f967e = c0704c;
            c0704c.m534a();
        }
        return this.f967e;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6 A[RETURN] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo531b() {
        /*
            r4 = this;
            java.lang.String r0 = r4.getTextTrim()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L34
            com.ctvit.widget.edittext.CommonEditText$c r0 = r4.mo530a()
            java.lang.String r0 = r0.f974e
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L28
            com.ctvit.widget.edittext.CommonEditText$c r0 = r4.mo530a()
            java.lang.String r0 = r0.f974e
            v2.a r3 = new v2.a
            r3.<init>(r0)
            r3.m2345a()
            r0 = 1
            goto L29
        L28:
            r0 = 0
        L29:
            com.ctvit.widget.edittext.CommonEditText$c r3 = r4.mo530a()
            java.util.Objects.requireNonNull(r3)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L34:
            r0 = 1
        L35:
            if (r0 == 0) goto La7
            int r0 = r4.getTextLength()
            com.ctvit.widget.edittext.CommonEditText$c r3 = r4.mo530a()
            int r3 = r3.f971b
            if (r0 >= r3) goto L6b
            com.ctvit.widget.edittext.CommonEditText$c r0 = r4.mo530a()
            java.lang.String r0 = r0.f975f
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L5f
            com.ctvit.widget.edittext.CommonEditText$c r0 = r4.mo530a()
            java.lang.String r0 = r0.f975f
            v2.a r3 = new v2.a
            r3.<init>(r0)
            r3.m2345a()
            r0 = 1
            goto L60
        L5f:
            r0 = 0
        L60:
            com.ctvit.widget.edittext.CommonEditText$c r3 = r4.mo530a()
            java.util.Objects.requireNonNull(r3)
            if (r0 == 0) goto L6b
            r0 = 0
            goto L6c
        L6b:
            r0 = 1
        L6c:
            if (r0 == 0) goto La7
            int r0 = r4.getTextLength()
            com.ctvit.widget.edittext.CommonEditText$c r3 = r4.mo530a()
            int r3 = r3.f972c
            if (r0 <= r3) goto La2
            com.ctvit.widget.edittext.CommonEditText$c r0 = r4.mo530a()
            java.lang.String r0 = r0.f976g
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L96
            com.ctvit.widget.edittext.CommonEditText$c r0 = r4.mo530a()
            java.lang.String r0 = r0.f976g
            v2.a r3 = new v2.a
            r3.<init>(r0)
            r3.m2345a()
            r0 = 1
            goto L97
        L96:
            r0 = 0
        L97:
            com.ctvit.widget.edittext.CommonEditText$c r3 = r4.mo530a()
            java.util.Objects.requireNonNull(r3)
            if (r0 == 0) goto La2
            r0 = 0
            goto La3
        La2:
            r0 = 1
        La3:
            if (r0 != 0) goto La6
            goto La7
        La6:
            return r2
        La7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ctvit.widget.edittext.CommonEditText.mo531b():boolean");
    }

    /* renamed from: c */
    public void mo532c() {
    }

    /* renamed from: d */
    public void mo533d() {
        setOnFocusChangeListener(new ViewOnFocusChangeListenerC0702a());
        addTextChangedListener(new C0703b());
    }

    public int getTextLength() {
        String textTrim = getTextTrim();
        if (!mo530a().f973d) {
            return textTrim.length();
        }
        if (textTrim == null) {
            return 0;
        }
        int i7 = 0;
        for (char c7 : textTrim.toCharArray()) {
            i7++;
            if (!(c7 / 128 == 0)) {
                i7++;
            }
        }
        return i7;
    }

    public String getTextTrim() {
        return getText() == null ? "" : getText().toString().trim();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getAction();
        return super.onTouchEvent(motionEvent);
    }

    public CommonEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonEditText(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        setInputType(1);
        mo532c();
        mo533d();
    }
}
