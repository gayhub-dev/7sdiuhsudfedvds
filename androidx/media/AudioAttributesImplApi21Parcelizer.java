package androidx.media;

import android.media.AudioAttributes;
import android.support.annotation.RestrictTo;
import android.support.v4.media.AudioAttributesImplApi21;
import java.util.Objects;
import p017c.AbstractC0496a;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(AbstractC0496a abstractC0496a) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.mAudioAttributes = (AudioAttributes) abstractC0496a.m304i(audioAttributesImplApi21.mAudioAttributes, 1);
        audioAttributesImplApi21.mLegacyStreamType = abstractC0496a.m302g(audioAttributesImplApi21.mLegacyStreamType, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, AbstractC0496a abstractC0496a) {
        Objects.requireNonNull(abstractC0496a);
        AudioAttributes audioAttributes = audioAttributesImplApi21.mAudioAttributes;
        abstractC0496a.mo307l(1);
        abstractC0496a.mo310o(audioAttributes);
        int i7 = audioAttributesImplApi21.mLegacyStreamType;
        abstractC0496a.mo307l(2);
        abstractC0496a.mo309n(i7);
    }
}
