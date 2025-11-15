package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class ImageViewCompat {
    private ImageViewCompat() {
    }

    @Nullable
    public static ColorStateList getImageTintList(@NonNull ImageView imageView) {
        return imageView.getImageTintList();
    }

    @Nullable
    public static PorterDuff.Mode getImageTintMode(@NonNull ImageView imageView) {
        return imageView.getImageTintMode();
    }

    public static void setImageTintList(@NonNull ImageView imageView, @Nullable ColorStateList colorStateList) {
        int i7 = Build.VERSION.SDK_INT;
        imageView.setImageTintList(colorStateList);
        if (i7 == 21) {
            Drawable drawable = imageView.getDrawable();
            boolean z6 = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
            if (drawable == null || !z6) {
                return;
            }
            if (drawable.isStateful()) {
                drawable.setState(imageView.getDrawableState());
            }
            imageView.setImageDrawable(drawable);
        }
    }

    public static void setImageTintMode(@NonNull ImageView imageView, @Nullable PorterDuff.Mode mode) {
        int i7 = Build.VERSION.SDK_INT;
        imageView.setImageTintMode(mode);
        if (i7 == 21) {
            Drawable drawable = imageView.getDrawable();
            boolean z6 = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
            if (drawable == null || !z6) {
                return;
            }
            if (drawable.isStateful()) {
                drawable.setState(imageView.getDrawableState());
            }
            imageView.setImageDrawable(drawable);
        }
    }
}
