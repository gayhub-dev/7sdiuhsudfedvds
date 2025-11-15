package android.support.v7.widget.helper;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.C0311R;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
    public static final ItemTouchUIUtil INSTANCE = new ItemTouchUIUtilImpl();

    private static float findMaxElevation(RecyclerView recyclerView, View view) {
        int childCount = recyclerView.getChildCount();
        float f7 = 0.0f;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = recyclerView.getChildAt(i7);
            if (childAt != view) {
                float elevation = ViewCompat.getElevation(childAt);
                if (elevation > f7) {
                    f7 = elevation;
                }
            }
        }
        return f7;
    }

    @Override // android.support.v7.widget.helper.ItemTouchUIUtil
    public void clearView(View view) {
        int i7 = C0311R.id.item_touch_helper_previous_elevation;
        Object tag = view.getTag(i7);
        if (tag != null && (tag instanceof Float)) {
            ViewCompat.setElevation(view, ((Float) tag).floatValue());
        }
        view.setTag(i7, null);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // android.support.v7.widget.helper.ItemTouchUIUtil
    public void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f7, float f8, int i7, boolean z6) {
        if (z6) {
            int i8 = C0311R.id.item_touch_helper_previous_elevation;
            if (view.getTag(i8) == null) {
                Float fValueOf = Float.valueOf(ViewCompat.getElevation(view));
                ViewCompat.setElevation(view, findMaxElevation(recyclerView, view) + 1.0f);
                view.setTag(i8, fValueOf);
            }
        }
        view.setTranslationX(f7);
        view.setTranslationY(f8);
    }

    @Override // android.support.v7.widget.helper.ItemTouchUIUtil
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, View view, float f7, float f8, int i7, boolean z6) {
    }

    @Override // android.support.v7.widget.helper.ItemTouchUIUtil
    public void onSelected(View view) {
    }
}
