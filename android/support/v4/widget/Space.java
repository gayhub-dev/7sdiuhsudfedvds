package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

@Deprecated
/* loaded from: classes.dex */
public class Space extends View {
    @Deprecated
    public Space(@NonNull Context context, @Nullable AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        if (getVisibility() == 0) {
            setVisibility(4);
        }
    }

    private static int getDefaultSize2(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? i7 : size : Math.min(i7, size);
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    @Deprecated
    public void draw(Canvas canvas) {
    }

    @Override // android.view.View
    @Deprecated
    public void onMeasure(int i7, int i8) {
        setMeasuredDimension(getDefaultSize2(getSuggestedMinimumWidth(), i7), getDefaultSize2(getSuggestedMinimumHeight(), i8));
    }

    @Deprecated
    public Space(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public Space(@NonNull Context context) {
        this(context, null);
    }
}
