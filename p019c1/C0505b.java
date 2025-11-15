package p019c1;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.constraint.C0072a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.UUID;
import p009b.C0413b;
import p163u0.C1970a;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: UniqueIdUtil.java */
/* renamed from: c1.b */
/* loaded from: classes.dex */
public class C0505b {

    /* renamed from: a */
    public static final String f373a;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        f373a = C0072a.m92a(sb, File.separator, ".shape");
    }

    /* renamed from: a */
    public static String m315a() {
        try {
            File file = new File(f373a);
            return !file.exists() ? "" : new BufferedReader(new FileReader(file)).readLine();
        } catch (Exception e7) {
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
            return "";
        }
    }

    /* renamed from: b */
    public static String m316b(Context context) throws NoSuchAlgorithmException {
        byte[] bytes;
        StringBuilder sb = new StringBuilder();
        String strM2587a = C2146b.m2587a(context);
        StringBuilder sbM112a = C0413b.m112a("1698");
        sbM112a.append(Build.HARDWARE);
        sbM112a.append(Build.BOARD);
        sbM112a.append(Build.BRAND);
        sbM112a.append(Build.DEVICE);
        sbM112a.append(Build.MANUFACTURER);
        sbM112a.append(Build.MODEL);
        sbM112a.append(Build.PRODUCT);
        sbM112a.append(Build.TAGS);
        sbM112a.append(Build.TYPE);
        sbM112a.append(Build.USER);
        sbM112a.append(C2145a.m2583k(context));
        sbM112a.append(C2145a.m2578f(context));
        String strReplace = new UUID(sbM112a.toString().hashCode(), r3.hashCode()).toString().replace("-", "");
        if (strM2587a != null && strM2587a.length() > 0) {
            sb.append(strM2587a);
            sb.append("|");
        }
        if (strReplace != null && strReplace.length() > 0) {
            sb.append(strReplace);
        }
        if (sb.length() <= 0) {
            return null;
        }
        try {
            String string = sb.toString();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.reset();
                messageDigest.update(string.getBytes("UTF-8"));
                bytes = messageDigest.digest();
            } catch (Exception unused) {
                bytes = "".getBytes();
            }
            StringBuilder sb2 = new StringBuilder();
            for (byte b7 : bytes) {
                String hexString = Integer.toHexString(b7 & 255);
                if (hexString.length() == 1) {
                    sb2.append("0");
                }
                sb2.append(hexString);
            }
            String upperCase = sb2.toString().toUpperCase(Locale.CHINA);
            if (upperCase == null) {
                return null;
            }
            if (upperCase.length() > 0) {
                return upperCase;
            }
            return null;
        } catch (Exception e7) {
            if (!C1970a.f5743a) {
                return null;
            }
            e7.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static void m317c(String str) throws IOException {
        try {
            String str2 = f373a;
            File file = new File(str2);
            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
                return;
            }
            if (str.equals(m315a())) {
                return;
            }
            File file2 = new File(str2);
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            FileOutputStream fileOutputStream2 = new FileOutputStream(str2);
            fileOutputStream2.write(str.getBytes());
            fileOutputStream2.close();
        } catch (Exception e7) {
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
        }
    }
}
