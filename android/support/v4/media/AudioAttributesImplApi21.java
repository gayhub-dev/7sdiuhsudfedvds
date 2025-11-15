package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p009b.C0413b;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(21)
/* loaded from: classes.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    private static final String TAG = "AudioAttributesCompat21";
    public static Method sAudioAttributesToLegacyStreamType;
    public AudioAttributes mAudioAttributes;
    public int mLegacyStreamType;

    public AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        AudioAttributes audioAttributes;
        if (bundle == null || (audioAttributes = (AudioAttributes) bundle.getParcelable(AudioAttributesCompat.AUDIO_ATTRIBUTES_FRAMEWORKS)) == null) {
            return null;
        }
        return new AudioAttributesImplApi21(audioAttributes, bundle.getInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_LEGACY_STREAM_TYPE, -1));
    }

    public static Method getAudioAttributesToLegacyStreamTypeMethod() {
        try {
            if (sAudioAttributesToLegacyStreamType == null) {
                sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", AudioAttributes.class);
            }
            return sAudioAttributesToLegacyStreamType;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
        }
        return false;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public Object getAudioAttributes() {
        return this.mAudioAttributes;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getContentType() {
        return this.mAudioAttributes.getContentType();
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getFlags() {
        return this.mAudioAttributes.getFlags();
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i7 = this.mLegacyStreamType;
        if (i7 != -1) {
            return i7;
        }
        Method audioAttributesToLegacyStreamTypeMethod = getAudioAttributesToLegacyStreamTypeMethod();
        if (audioAttributesToLegacyStreamTypeMethod == null) {
            return -1;
        }
        try {
            return ((Integer) audioAttributesToLegacyStreamTypeMethod.invoke(null, this.mAudioAttributes)).intValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return -1;
        }
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.mLegacyStreamType;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getUsage() {
        return this.mAudioAttributes.getUsage();
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return Build.VERSION.SDK_INT >= 26 ? this.mAudioAttributes.getVolumeControlStream() : AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(AudioAttributesCompat.AUDIO_ATTRIBUTES_FRAMEWORKS, this.mAudioAttributes);
        int i7 = this.mLegacyStreamType;
        if (i7 != -1) {
            bundle.putInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_LEGACY_STREAM_TYPE, i7);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("AudioAttributesCompat: audioattributes=");
        sbM112a.append(this.mAudioAttributes);
        return sbM112a.toString();
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes, int i7) {
        this.mLegacyStreamType = -1;
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = i7;
    }
}
