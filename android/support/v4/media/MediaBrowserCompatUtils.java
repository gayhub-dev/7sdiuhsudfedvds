package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.ActivityChooserView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class MediaBrowserCompatUtils {
    private MediaBrowserCompatUtils() {
    }

    public static boolean areSameOptions(Bundle bundle, Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        return bundle == null ? bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1 : bundle2 == null ? bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1 : bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) && bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
    }

    public static boolean hasDuplicatedItems(Bundle bundle, Bundle bundle2) {
        int i7;
        int i8;
        int i9;
        int i10 = bundle == null ? -1 : bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i11 = bundle2 == null ? -1 : bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i12 = bundle == null ? -1 : bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        int i13 = bundle2 == null ? -1 : bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        int i14 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        if (i10 == -1 || i12 == -1) {
            i7 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            i8 = 0;
        } else {
            i8 = i10 * i12;
            i7 = (i12 + i8) - 1;
        }
        if (i11 == -1 || i13 == -1) {
            i9 = 0;
        } else {
            i9 = i11 * i13;
            i14 = (i13 + i9) - 1;
        }
        return i7 >= i9 && i14 >= i8;
    }
}
