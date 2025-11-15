package okhttp3;

import android.arch.lifecycle.C0063n;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.ByteString;

/* loaded from: classes.dex */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        return C0063n.m88a("Basic ", ByteString.encodeString(str + ":" + str2, charset).base64());
    }
}
