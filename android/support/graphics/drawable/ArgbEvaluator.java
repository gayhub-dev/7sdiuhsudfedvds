package android.support.graphics.drawable;

import android.animation.TypeEvaluator;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance = new ArgbEvaluator();

    public static ArgbEvaluator getInstance() {
        return sInstance;
    }

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f7, Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        float f8 = ((iIntValue >> 24) & 255) / 255.0f;
        int iIntValue2 = ((Integer) obj2).intValue();
        float f9 = ((iIntValue2 >> 24) & 255) / 255.0f;
        float fPow = (float) Math.pow(((iIntValue >> 16) & 255) / 255.0f, 2.2d);
        float fPow2 = (float) Math.pow(((iIntValue >> 8) & 255) / 255.0f, 2.2d);
        float fPow3 = (float) Math.pow((iIntValue & 255) / 255.0f, 2.2d);
        float fPow4 = (float) Math.pow(((iIntValue2 >> 16) & 255) / 255.0f, 2.2d);
        float fPow5 = ((((float) Math.pow(((iIntValue2 >> 8) & 255) / 255.0f, 2.2d)) - fPow2) * f7) + fPow2;
        float fPow6 = ((((float) Math.pow((iIntValue2 & 255) / 255.0f, 2.2d)) - fPow3) * f7) + fPow3;
        float f10 = (((f9 - f8) * f7) + f8) * 255.0f;
        return Integer.valueOf((Math.round(((float) Math.pow(((fPow4 - fPow) * f7) + fPow, 0.45454545454545453d)) * 255.0f) << 16) | (Math.round(f10) << 24) | (Math.round(((float) Math.pow(fPow5, 0.45454545454545453d)) * 255.0f) << 8) | Math.round(((float) Math.pow(fPow6, 0.45454545454545453d)) * 255.0f));
    }
}
