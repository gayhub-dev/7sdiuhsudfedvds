package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class AppCompatDelegate {
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
    public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_FOLLOW_SYSTEM = -1;
    public static final int MODE_NIGHT_NO = 1;
    public static final int MODE_NIGHT_UNSPECIFIED = -100;
    public static final int MODE_NIGHT_YES = 2;
    public static final String TAG = "AppCompatDelegate";
    private static int sDefaultNightMode = -1;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface NightMode {
    }

    public static AppCompatDelegate create(Activity activity, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImpl(activity, activity.getWindow(), appCompatCallback);
    }

    public static int getDefaultNightMode() {
        return sDefaultNightMode;
    }

    public static boolean isCompatVectorFromResourcesEnabled() {
        return VectorEnabledTintResources.isCompatVectorFromResourcesEnabled();
    }

    public static void setCompatVectorFromResourcesEnabled(boolean z6) {
        VectorEnabledTintResources.setCompatVectorFromResourcesEnabled(z6);
    }

    public static void setDefaultNightMode(int i7) {
        if (i7 == -1 || i7 == 0 || i7 == 1 || i7 == 2) {
            sDefaultNightMode = i7;
        }
    }

    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean applyDayNight();

    public abstract View createView(@Nullable View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet);

    @Nullable
    public abstract <T extends View> T findViewById(@IdRes int i7);

    @Nullable
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();

    public abstract MenuInflater getMenuInflater();

    @Nullable
    public abstract ActionBar getSupportActionBar();

    public abstract boolean hasWindowFeature(int i7);

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract boolean isHandleNativeActionModesEnabled();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onCreate(Bundle bundle);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle bundle);

    public abstract void onPostResume();

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void onStart();

    public abstract void onStop();

    public abstract boolean requestWindowFeature(int i7);

    public abstract void setContentView(@LayoutRes int i7);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void setHandleNativeActionModesEnabled(boolean z6);

    public abstract void setLocalNightMode(int i7);

    public abstract void setSupportActionBar(@Nullable Toolbar toolbar);

    public abstract void setTitle(@Nullable CharSequence charSequence);

    @Nullable
    public abstract ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback);

    public static AppCompatDelegate create(Dialog dialog, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImpl(dialog.getContext(), dialog.getWindow(), appCompatCallback);
    }

    public static AppCompatDelegate create(Context context, Window window, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImpl(context, window, appCompatCallback);
    }
}
