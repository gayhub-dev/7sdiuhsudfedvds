package androidx.media;

import android.support.annotation.RestrictTo;
import android.support.v4.media.AudioAttributesCompat;
import android.support.v4.media.AudioAttributesImpl;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import p017c.AbstractC0496a;
import p017c.InterfaceC0498c;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(AbstractC0496a abstractC0496a) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        InterfaceC0498c interfaceC0498cM306k = audioAttributesCompat.mImpl;
        if (abstractC0496a.mo300e(1)) {
            interfaceC0498cM306k = abstractC0496a.m306k();
        }
        audioAttributesCompat.mImpl = (AudioAttributesImpl) interfaceC0498cM306k;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, AbstractC0496a abstractC0496a) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Objects.requireNonNull(abstractC0496a);
        AudioAttributesImpl audioAttributesImpl = audioAttributesCompat.mImpl;
        abstractC0496a.mo307l(1);
        abstractC0496a.m312q(audioAttributesImpl);
    }
}
