package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.view.Gravity;

@RequiresApi(21)
/* loaded from: classes.dex */
class RoundedBitmapDrawable21 extends RoundedBitmapDrawable {
    public RoundedBitmapDrawable21(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        updateDstRect();
        outline.setRoundRect(this.mDstRect, getCornerRadius());
    }

    @Override // android.support.v4.graphics.drawable.RoundedBitmapDrawable
    public void gravityCompatApply(int i7, int i8, int i9, Rect rect, Rect rect2) {
        Gravity.apply(i7, i8, i9, rect, rect2, 0);
    }

    @Override // android.support.v4.graphics.drawable.RoundedBitmapDrawable
    public boolean hasMipMap() {
        Bitmap bitmap = this.mBitmap;
        return bitmap != null && bitmap.hasMipMap();
    }

    @Override // android.support.v4.graphics.drawable.RoundedBitmapDrawable
    public void setMipMap(boolean z6) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.setHasMipMap(z6);
            invalidateSelf();
        }
    }
}
