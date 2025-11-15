package android.support.v4.media;

import android.media.VolumeProvider;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
/* loaded from: classes.dex */
class VolumeProviderCompatApi21 {

    public interface Delegate {
        void onAdjustVolume(int i7);

        void onSetVolumeTo(int i7);
    }

    private VolumeProviderCompatApi21() {
    }

    public static Object createVolumeProvider(int i7, int i8, int i9, final Delegate delegate) {
        return new VolumeProvider(i7, i8, i9) { // from class: android.support.v4.media.VolumeProviderCompatApi21.1
            @Override // android.media.VolumeProvider
            public void onAdjustVolume(int i10) {
                delegate.onAdjustVolume(i10);
            }

            @Override // android.media.VolumeProvider
            public void onSetVolumeTo(int i10) {
                delegate.onSetVolumeTo(i10);
            }
        };
    }

    public static void setCurrentVolume(Object obj, int i7) {
        ((VolumeProvider) obj).setCurrentVolume(i7);
    }
}
