package p106m6;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

/* compiled from: IO.java */
/* renamed from: m6.c */
/* loaded from: classes.dex */
public class C1500c {

    /* renamed from: a */
    public static final /* synthetic */ int f4301a = 0;

    static {
        StringWriter stringWriter = new StringWriter(4);
        new PrintWriter(stringWriter).println();
        stringWriter.toString();
    }

    /* renamed from: a */
    public static byte[] m1664a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int i7 = inputStream.read(bArr);
            if (-1 == i7) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i7);
        }
    }

    /* renamed from: b */
    public static String m1665b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Inputstream was null");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            sb.append(System.getProperty("line.separator"));
        }
        return sb.length() > 0 ? sb.toString() : "";
    }
}
