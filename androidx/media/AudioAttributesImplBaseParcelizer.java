package androidx.media;

import android.support.annotation.RestrictTo;
import android.support.v4.media.AudioAttributesImplBase;
import java.util.Objects;
import p017c.AbstractC0496a;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(AbstractC0496a abstractC0496a) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.mUsage = abstractC0496a.m302g(audioAttributesImplBase.mUsage, 1);
        audioAttributesImplBase.mContentType = abstractC0496a.m302g(audioAttributesImplBase.mContentType, 2);
        audioAttributesImplBase.mFlags = abstractC0496a.m302g(audioAttributesImplBase.mFlags, 3);
        audioAttributesImplBase.mLegacyStream = abstractC0496a.m302g(audioAttributesImplBase.mLegacyStream, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, AbstractC0496a abstractC0496a) {
        Objects.requireNonNull(abstractC0496a);
        int i7 = audioAttributesImplBase.mUsage;
        abstractC0496a.mo307l(1);
        abstractC0496a.mo309n(i7);
        int i8 = audioAttributesImplBase.mContentType;
        abstractC0496a.mo307l(2);
        abstractC0496a.mo309n(i8);
        int i9 = audioAttributesImplBase.mFlags;
        abstractC0496a.mo307l(3);
        abstractC0496a.mo309n(i9);
        int i10 = audioAttributesImplBase.mLegacyStream;
        abstractC0496a.mo307l(4);
        abstractC0496a.mo309n(i10);
    }
}
