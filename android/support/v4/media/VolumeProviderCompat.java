package android.support.v4.media;

import android.support.annotation.RestrictTo;
import android.support.v4.media.VolumeProviderCompatApi21;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private Object mVolumeProviderObj;

    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface ControlType {
    }

    public VolumeProviderCompat(int i7, int i8, int i9) {
        this.mControlType = i7;
        this.mMaxVolume = i8;
        this.mCurrentVolume = i9;
    }

    public final int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public final int getMaxVolume() {
        return this.mMaxVolume;
    }

    public final int getVolumeControl() {
        return this.mControlType;
    }

    public Object getVolumeProvider() {
        if (this.mVolumeProviderObj == null) {
            this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, new VolumeProviderCompatApi21.Delegate() { // from class: android.support.v4.media.VolumeProviderCompat.1
                @Override // android.support.v4.media.VolumeProviderCompatApi21.Delegate
                public void onAdjustVolume(int i7) {
                    VolumeProviderCompat.this.onAdjustVolume(i7);
                }

                @Override // android.support.v4.media.VolumeProviderCompatApi21.Delegate
                public void onSetVolumeTo(int i7) {
                    VolumeProviderCompat.this.onSetVolumeTo(i7);
                }
            });
        }
        return this.mVolumeProviderObj;
    }

    public void onAdjustVolume(int i7) {
    }

    public void onSetVolumeTo(int i7) {
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public final void setCurrentVolume(int i7) {
        this.mCurrentVolume = i7;
        Object volumeProvider = getVolumeProvider();
        if (volumeProvider != null) {
            VolumeProviderCompatApi21.setCurrentVolume(volumeProvider, i7);
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onVolumeChanged(this);
        }
    }
}
