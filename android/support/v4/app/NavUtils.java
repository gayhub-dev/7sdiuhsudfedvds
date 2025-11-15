package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class NavUtils {
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";

    private NavUtils() {
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Activity activity) {
        Intent parentActivityIntent = activity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        String parentActivityName = getParentActivityName(activity);
        if (parentActivityName == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(activity, parentActivityName);
        try {
            return getParentActivityName(activity, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Nullable
    public static String getParentActivityName(@NonNull Activity activity) {
        try {
            return getParentActivityName(activity, activity.getComponentName());
        } catch (PackageManager.NameNotFoundException e7) {
            throw new IllegalArgumentException(e7);
        }
    }

    public static void navigateUpFromSameTask(@NonNull Activity activity) {
        Intent parentActivityIntent = getParentActivityIntent(activity);
        if (parentActivityIntent != null) {
            navigateUpTo(activity, parentActivityIntent);
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("Activity ");
        sbM112a.append(activity.getClass().getSimpleName());
        sbM112a.append(" does not have a parent activity name specified.");
        sbM112a.append(" (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> ");
        sbM112a.append(" element in your manifest?)");
        throw new IllegalArgumentException(sbM112a.toString());
    }

    public static void navigateUpTo(@NonNull Activity activity, @NonNull Intent intent) {
        activity.navigateUpTo(intent);
    }

    public static boolean shouldUpRecreateTask(@NonNull Activity activity, @NonNull Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }

    @Nullable
    public static String getParentActivityName(@NonNull Context context, @NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
        String string;
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, 128);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString(PARENT_ACTIVITY)) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context, @NonNull Class<?> cls) throws PackageManager.NameNotFoundException {
        String parentActivityName = getParentActivityName(context, new ComponentName(context, cls));
        if (parentActivityName == null) {
            return null;
        }
        ComponentName componentName = new ComponentName(context, parentActivityName);
        if (getParentActivityName(context, componentName) == null) {
            return Intent.makeMainActivity(componentName);
        }
        return new Intent().setComponent(componentName);
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context, @NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
        String parentActivityName = getParentActivityName(context, componentName);
        if (parentActivityName == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), parentActivityName);
        if (getParentActivityName(context, componentName2) == null) {
            return Intent.makeMainActivity(componentName2);
        }
        return new Intent().setComponent(componentName2);
    }
}
