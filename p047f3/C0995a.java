package p047f3;

import android.text.InputFilter;
import android.text.Spanned;

/* compiled from: EnglishCharFilter.java */
/* renamed from: f3.a */
/* loaded from: classes.dex */
public class C0995a implements InputFilter {

    /* renamed from: a */
    public int f1866a;

    public C0995a(int i7) {
        this.f1866a = 0;
        this.f1866a = i7;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i7, int i8, Spanned spanned, int i9, int i10) {
        int i11 = 0;
        int i12 = 0;
        while (i11 <= this.f1866a && i12 < spanned.length()) {
            int i13 = i12 + 1;
            i11 = (spanned.charAt(i12) & 65535) <= 255 ? i11 + 1 : i11 + 2;
            i12 = i13;
        }
        if (i11 > this.f1866a) {
            return spanned.subSequence(0, i12 - 1);
        }
        int i14 = 0;
        while (i11 <= this.f1866a && i14 < charSequence.length()) {
            int i15 = i14 + 1;
            i11 = (charSequence.charAt(i14) & 65535) <= 255 ? i11 + 1 : i11 + 2;
            i14 = i15;
        }
        if (i11 > this.f1866a) {
            return charSequence.subSequence(0, i14 - 1);
        }
        return null;
    }
}
