package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v13.view.DragAndDropPermissionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.view.DragEvent;
import android.view.View;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ActivityCompat extends ContextCompat {
    private static PermissionCompatDelegate sDelegate;

    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i7, @NonNull String[] strArr, @NonNull int[] iArr);
    }

    public interface PermissionCompatDelegate {
        boolean onActivityResult(@NonNull Activity activity, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7, int i8, @Nullable Intent intent);

        boolean requestPermissions(@NonNull Activity activity, @NonNull String[] strArr, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i7);
    }

    @RequiresApi(21)
    public static class SharedElementCallback21Impl extends android.app.SharedElementCallback {
        private final SharedElementCallback mCallback;

        public SharedElementCallback21Impl(SharedElementCallback sharedElementCallback) {
            this.mCallback = sharedElementCallback;
        }

        @Override // android.app.SharedElementCallback
        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            return this.mCallback.onCaptureSharedElementSnapshot(view, matrix, rectF);
        }

        @Override // android.app.SharedElementCallback
        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            return this.mCallback.onCreateSnapshotView(context, parcelable);
        }

        @Override // android.app.SharedElementCallback
        public void onMapSharedElements(List<String> list, Map<String, View> map) {
            this.mCallback.onMapSharedElements(list, map);
        }

        @Override // android.app.SharedElementCallback
        public void onRejectSharedElements(List<View> list) {
            this.mCallback.onRejectSharedElements(list);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
            this.mCallback.onSharedElementEnd(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
            this.mCallback.onSharedElementStart(list, list2, list3);
        }

        @Override // android.app.SharedElementCallback
        @RequiresApi(23)
        public void onSharedElementsArrived(List<String> list, List<View> list2, final SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
            this.mCallback.onSharedElementsArrived(list, list2, new SharedElementCallback.OnSharedElementsReadyListener() { // from class: android.support.v4.app.ActivityCompat.SharedElementCallback21Impl.1
                @Override // android.support.v4.app.SharedElementCallback.OnSharedElementsReadyListener
                public void onSharedElementsReady() {
                    onSharedElementsReadyListener.onSharedElementsReady();
                }
            });
        }
    }

    public static void finishAffinity(@NonNull Activity activity) {
        activity.finishAffinity();
    }

    public static void finishAfterTransition(@NonNull Activity activity) {
        activity.finishAfterTransition();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return sDelegate;
    }

    @Nullable
    public static Uri getReferrer(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= 22) {
            return activity.getReferrer();
        }
        Intent intent = activity.getIntent();
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.REFERRER");
        if (uri != null) {
            return uri;
        }
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        if (stringExtra != null) {
            return Uri.parse(stringExtra);
        }
        return null;
    }

    @Deprecated
    public static boolean invalidateOptionsMenu(Activity activity) {
        activity.invalidateOptionsMenu();
        return true;
    }

    public static void postponeEnterTransition(@NonNull Activity activity) {
        activity.postponeEnterTransition();
    }

    @Nullable
    public static DragAndDropPermissionsCompat requestDragAndDropPermissions(Activity activity, DragEvent dragEvent) {
        return DragAndDropPermissionsCompat.request(activity, dragEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void requestPermissions(@NonNull final Activity activity, @NonNull final String[] strArr, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) final int i7) {
        PermissionCompatDelegate permissionCompatDelegate = sDelegate;
        if (permissionCompatDelegate == null || !permissionCompatDelegate.requestPermissions(activity, strArr, i7)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (activity instanceof RequestPermissionsRequestCodeValidator) {
                    ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(i7);
                }
                activity.requestPermissions(strArr, i7);
            } else if (activity instanceof OnRequestPermissionsResultCallback) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: android.support.v4.app.ActivityCompat.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int[] iArr = new int[strArr.length];
                        PackageManager packageManager = activity.getPackageManager();
                        String packageName = activity.getPackageName();
                        int length = strArr.length;
                        for (int i8 = 0; i8 < length; i8++) {
                            iArr[i8] = packageManager.checkPermission(strArr[i8], packageName);
                        }
                        ((OnRequestPermissionsResultCallback) activity).onRequestPermissionsResult(i7, strArr, iArr);
                    }
                });
            }
        }
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull Activity activity, @IdRes int i7) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) activity.requireViewById(i7);
        }
        T t6 = (T) activity.findViewById(i7);
        if (t6 != null) {
            return t6;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Activity");
    }

    public static void setEnterSharedElementCallback(@NonNull Activity activity, @Nullable SharedElementCallback sharedElementCallback) {
        activity.setEnterSharedElementCallback(sharedElementCallback != null ? new SharedElementCallback21Impl(sharedElementCallback) : null);
    }

    public static void setExitSharedElementCallback(@NonNull Activity activity, @Nullable SharedElementCallback sharedElementCallback) {
        activity.setExitSharedElementCallback(sharedElementCallback != null ? new SharedElementCallback21Impl(sharedElementCallback) : null);
    }

    public static void setPermissionCompatDelegate(@Nullable PermissionCompatDelegate permissionCompatDelegate) {
        sDelegate = permissionCompatDelegate;
    }

    public static boolean shouldShowRequestPermissionRationale(@NonNull Activity activity, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i7, @Nullable Bundle bundle) {
        activity.startActivityForResult(intent, i7, bundle);
    }

    public static void startIntentSenderForResult(@NonNull Activity activity, @NonNull IntentSender intentSender, int i7, @Nullable Intent intent, int i8, int i9, int i10, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        activity.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10, bundle);
    }

    public static void startPostponedEnterTransition(@NonNull Activity activity) {
        activity.startPostponedEnterTransition();
    }
}
