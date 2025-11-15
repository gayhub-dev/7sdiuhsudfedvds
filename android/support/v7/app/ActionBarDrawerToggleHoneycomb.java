package android.support.v7.app;

import android.R;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class ActionBarDrawerToggleHoneycomb {
    private static final String TAG = "ActionBarDrawerToggleHC";
    private static final int[] THEME_ATTRS = {R.attr.homeAsUpIndicator};

    public static class SetIndicatorInfo {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;

        public SetIndicatorInfo(Activity activity) {
            try {
                this.setHomeAsUpIndicator = android.app.ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.setHomeActionContentDescription = android.app.ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException unused) {
                View viewFindViewById = activity.findViewById(R.id.home);
                if (viewFindViewById == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) viewFindViewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                View childAt = viewGroup.getChildAt(0);
                childAt = childAt.getId() == 16908332 ? viewGroup.getChildAt(1) : childAt;
                if (childAt instanceof ImageView) {
                    this.upIndicatorView = (ImageView) childAt;
                }
            }
        }
    }

    private ActionBarDrawerToggleHoneycomb() {
    }

    public static Drawable getThemeUpIndicator(Activity activity) {
        TypedArray typedArrayObtainStyledAttributes = activity.obtainStyledAttributes(THEME_ATTRS);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        return drawable;
    }

    public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo setIndicatorInfo, Activity activity, int i7) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (setIndicatorInfo == null) {
            setIndicatorInfo = new SetIndicatorInfo(activity);
        }
        if (setIndicatorInfo.setHomeAsUpIndicator != null) {
            try {
                setIndicatorInfo.setHomeActionContentDescription.invoke(activity.getActionBar(), Integer.valueOf(i7));
            } catch (Exception unused) {
            }
        }
        return setIndicatorInfo;
    }

    public static SetIndicatorInfo setActionBarUpIndicator(SetIndicatorInfo setIndicatorInfo, Activity activity, Drawable drawable, int i7) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SetIndicatorInfo setIndicatorInfo2 = new SetIndicatorInfo(activity);
        if (setIndicatorInfo2.setHomeAsUpIndicator != null) {
            try {
                android.app.ActionBar actionBar = activity.getActionBar();
                setIndicatorInfo2.setHomeAsUpIndicator.invoke(actionBar, drawable);
                setIndicatorInfo2.setHomeActionContentDescription.invoke(actionBar, Integer.valueOf(i7));
            } catch (Exception unused) {
            }
        } else {
            ImageView imageView = setIndicatorInfo2.upIndicatorView;
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
            }
        }
        return setIndicatorInfo2;
    }
}
