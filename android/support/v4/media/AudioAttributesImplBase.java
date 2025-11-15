package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {
    public int mContentType;
    public int mFlags;
    public int mLegacyStream;
    public int mUsage;

    public AudioAttributesImplBase() {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mFlags = 0;
        this.mLegacyStream = -1;
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new AudioAttributesImplBase(bundle.getInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_CONTENT_TYPE, 0), bundle.getInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_FLAGS, 0), bundle.getInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_USAGE, 0), bundle.getInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_LEGACY_STREAM_TYPE, -1));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        return this.mContentType == audioAttributesImplBase.getContentType() && this.mFlags == audioAttributesImplBase.getFlags() && this.mUsage == audioAttributesImplBase.getUsage() && this.mLegacyStream == audioAttributesImplBase.mLegacyStream;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public Object getAudioAttributes() {
        return null;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getContentType() {
        return this.mContentType;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getFlags() {
        int i7 = this.mFlags;
        int legacyStreamType = getLegacyStreamType();
        if (legacyStreamType == 6) {
            i7 |= 4;
        } else if (legacyStreamType == 7) {
            i7 |= 1;
        }
        return i7 & AudioAttributesCompat.FLAG_ALL_PUBLIC;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i7 = this.mLegacyStream;
        return i7 != -1 ? i7 : AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.mLegacyStream;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getUsage() {
        return this.mUsage;
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
    }

    @Override // android.support.v4.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_USAGE, this.mUsage);
        bundle.putInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_CONTENT_TYPE, this.mContentType);
        bundle.putInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_FLAGS, this.mFlags);
        int i7 = this.mLegacyStream;
        if (i7 != -1) {
            bundle.putInt(AudioAttributesCompat.AUDIO_ATTRIBUTES_LEGACY_STREAM_TYPE, i7);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.mLegacyStream != -1) {
            sb.append(" stream=");
            sb.append(this.mLegacyStream);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.usageToString(this.mUsage));
        sb.append(" content=");
        sb.append(this.mContentType);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.mFlags).toUpperCase());
        return sb.toString();
    }

    public AudioAttributesImplBase(int i7, int i8, int i9, int i10) {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mFlags = 0;
        this.mLegacyStream = -1;
        this.mContentType = i7;
        this.mFlags = i8;
        this.mUsage = i9;
        this.mLegacyStream = i10;
    }
}
