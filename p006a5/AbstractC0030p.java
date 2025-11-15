package p006a5;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/* compiled from: ServletOutputStream.java */
/* renamed from: a5.p */
/* loaded from: classes.dex */
public abstract class AbstractC0030p extends OutputStream {

    /* renamed from: e */
    public static ResourceBundle f32e = ResourceBundle.getBundle("javax.servlet.LocalStrings");

    /* renamed from: a */
    public void mo22a(String str) throws IOException {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if ((65280 & cCharAt) != 0) {
                throw new CharConversionException(MessageFormat.format(f32e.getString("err.not_iso8859_1"), Character.valueOf(cCharAt)));
            }
            write(cCharAt);
        }
    }
}
