package android.support.constraint.motion;

import android.graphics.RectF;
import android.view.View;
import java.util.HashSet;

/* loaded from: classes.dex */
abstract class KeyPositionBase extends Key {
    public static final float SELECTION_SLOPE = 20.0f;
    public int mCurveFit = Key.UNSET;

    public abstract void calcPosition(int i7, int i8, float f7, float f8, float f9, float f10);

    @Override // android.support.constraint.motion.Key
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    public abstract float getPositionX();

    public abstract float getPositionY();

    public abstract boolean intersects(int i7, int i8, RectF rectF, RectF rectF2, float f7, float f8);

    public abstract void positionAttributes(View view, RectF rectF, RectF rectF2, float f7, float f8, String[] strArr, float[] fArr);
}
