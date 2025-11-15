package android.support.v7.widget.helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
public interface ItemTouchUIUtil {
    void clearView(View view);

    void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f7, float f8, int i7, boolean z6);

    void onDrawOver(Canvas canvas, RecyclerView recyclerView, View view, float f7, float f8, int i7, boolean z6);

    void onSelected(View view);
}
