package p132p6;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import p200z2.C2150a;

/* compiled from: ThemeUtils.java */
/* renamed from: p6.a */
/* loaded from: classes.dex */
public class C1749a {
    /* renamed from: a */
    public static boolean m1912a() {
        return ((Boolean) C2150a.m2590a("IS_MOURN_COLOR", Boolean.FALSE)).booleanValue();
    }

    /* renamed from: b */
    public static void m1913b(Activity activity, View view) {
        if (view == null) {
            view = activity.getWindow().getDecorView();
        }
        if (!m1912a()) {
            view.setLayerType(0, null);
            return;
        }
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        view.setLayerType(2, paint);
    }

    /* renamed from: c */
    public static void m1914c(Dialog dialog, View view) {
        View decorView = dialog.getWindow().getDecorView();
        if (!m1912a()) {
            decorView.setLayerType(0, null);
            return;
        }
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        decorView.setLayerType(2, paint);
    }
}
