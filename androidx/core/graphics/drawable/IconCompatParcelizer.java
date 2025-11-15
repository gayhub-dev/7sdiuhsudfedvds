package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.IconCompat;
import java.util.Objects;
import p017c.AbstractC0496a;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(AbstractC0496a abstractC0496a) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.mType = abstractC0496a.m302g(iconCompat.mType, 1);
        byte[] bArrMo299d = iconCompat.mData;
        if (abstractC0496a.mo300e(2)) {
            bArrMo299d = abstractC0496a.mo299d();
        }
        iconCompat.mData = bArrMo299d;
        iconCompat.mParcelable = abstractC0496a.m304i(iconCompat.mParcelable, 3);
        iconCompat.mInt1 = abstractC0496a.m302g(iconCompat.mInt1, 4);
        iconCompat.mInt2 = abstractC0496a.m302g(iconCompat.mInt2, 5);
        iconCompat.mTintList = (ColorStateList) abstractC0496a.m304i(iconCompat.mTintList, 6);
        String strMo305j = iconCompat.mTintModeStr;
        if (abstractC0496a.mo300e(7)) {
            strMo305j = abstractC0496a.mo305j();
        }
        iconCompat.mTintModeStr = strMo305j;
        iconCompat.onPostParceling();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, AbstractC0496a abstractC0496a) {
        Objects.requireNonNull(abstractC0496a);
        iconCompat.onPreParceling(false);
        int i7 = iconCompat.mType;
        abstractC0496a.mo307l(1);
        abstractC0496a.mo309n(i7);
        byte[] bArr = iconCompat.mData;
        abstractC0496a.mo307l(2);
        abstractC0496a.mo308m(bArr);
        Parcelable parcelable = iconCompat.mParcelable;
        abstractC0496a.mo307l(3);
        abstractC0496a.mo310o(parcelable);
        int i8 = iconCompat.mInt1;
        abstractC0496a.mo307l(4);
        abstractC0496a.mo309n(i8);
        int i9 = iconCompat.mInt2;
        abstractC0496a.mo307l(5);
        abstractC0496a.mo309n(i9);
        ColorStateList colorStateList = iconCompat.mTintList;
        abstractC0496a.mo307l(6);
        abstractC0496a.mo310o(colorStateList);
        String str = iconCompat.mTintModeStr;
        abstractC0496a.mo307l(7);
        abstractC0496a.mo311p(str);
    }
}
