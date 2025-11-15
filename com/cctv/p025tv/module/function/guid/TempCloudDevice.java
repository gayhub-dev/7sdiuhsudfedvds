package com.cctv.p025tv.module.function.guid;

import android.os.Build;
import android.text.TextUtils;
import com.cctv.p025tv.app.MyApplication;
import com.tencent.mars.xlog.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import p009b.C0413b;
import p086k2.C1231b;
import p093l1.C1415a;
import p101m1.C1458b;
import p156t0.C1896a;
import p186x2.C2073a;
import p193y2.C2106a;
import p198z0.C2146b;
import p200z2.C2150a;

/* loaded from: classes.dex */
public class TempCloudDevice {
    private static final int DEVICE_TYPE_CHANNEL_CCTV_ID = 2;
    private static final int DEVICE_TYPE_CHANNEL_TIME_BRAND = 3;
    private static final int DEVICE_TYPE_UUID_DEVICE_ID = 1;
    private static final String EXTERNAL_FILES_DIR = "cloud";
    private static final String SAVE_DEVICE_ID_FILE_NAME = "yspdstpzs";

    public static String createDeviceId() {
        return C2106a.m2549b();
    }

    public static String createNewDeviceId(int i7) {
        String string;
        if (i7 == 1) {
            try {
                Log.m655i("XLog_APP ", "cloud createNewDeviceId 方案一");
                string = UUID.randomUUID() + "-" + C2106a.m2549b();
            } catch (Exception e7) {
                StringBuilder sbM112a = C0413b.m112a("cloud createNewDeviceId 方案一 错误 e = ");
                sbM112a.append(e7.getMessage());
                Log.m651e("XLog_APP ", sbM112a.toString());
            }
        } else {
            string = "";
        }
        if (i7 == 2 || TextUtils.isEmpty(string)) {
            try {
                Log.m655i("XLog_APP ", "cloud createNewDeviceId 方案二");
                StringBuilder sb = new StringBuilder();
                sb.append(C1415a.f4150b);
                sb.append("-");
                C1896a c1896aM2197a = C1896a.m2197a();
                MyApplication myApplication = MyApplication.f561e;
                Objects.requireNonNull(c1896aM2197a);
                sb.append(C2146b.m2588b(myApplication));
                sb.append("-");
                sb.append(System.currentTimeMillis());
                string = sb.toString();
            } catch (Exception e8) {
                StringBuilder sbM112a2 = C0413b.m112a("cloud createNewDeviceId 方案二 错误 e = ");
                sbM112a2.append(e8.getMessage());
                Log.m651e("XLog_APP ", sbM112a2.toString());
            }
        }
        if (i7 == 3 || TextUtils.isEmpty(string)) {
            try {
                Log.m655i("XLog_APP ", "cloud createNewDeviceId 方案三");
                string = C1415a.f4150b + "-" + System.currentTimeMillis() + "-" + Build.BRAND;
            } catch (Exception e9) {
                StringBuilder sbM112a3 = C0413b.m112a("cloud createNewDeviceId 方案三 错误 e = ");
                sbM112a3.append(e9.getMessage());
                Log.m651e("XLog_APP ", sbM112a3.toString());
            }
        }
        C1458b.m1642a("cloud createNewDeviceId id = ", string);
        return string;
    }

    public static String getDeviceId() {
        String str = (String) C2150a.m2590a("CLOUD_REGISTER_ID", "");
        return !TextUtils.isEmpty(str) ? str : "";
    }

    public static String getGuid() {
        return (String) C2150a.m2590a("CLOUD_GUID", "");
    }

    public static String getSecret() {
        return (String) C2150a.m2590a("CLOUD_SECRET", "");
    }

    public static boolean hasDeviceId() {
        return !TextUtils.isEmpty(getDeviceId());
    }

    private static String readData(File file, String str, boolean z6) throws IOException {
        String string;
        String strReplace = "";
        try {
            File file2 = new File(file, str);
            Log.m655i("XLog_APP ", "readData fileInFilesDir =  " + file2.getAbsolutePath());
            if (!file2.exists()) {
                return "";
            }
            if (z6) {
                FileInputStream fileInputStreamOpenFileInput = C1231b.f2761c.openFileInput(file2.getName());
                byte[] bArr = new byte[fileInputStreamOpenFileInput.available()];
                fileInputStreamOpenFileInput.read(bArr);
                fileInputStreamOpenFileInput.close();
                string = new String(bArr);
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append("\n");
                }
                string = sb.toString();
            }
            try {
                string = string.replace("\n", "").replace("\r", "");
                strReplace = string.replace(" ", "");
                C2073a.m2459d("File Content: " + strReplace);
                return strReplace;
            } catch (IOException e7) {
                e = e7;
                StringBuilder sbM112a = C0413b.m112a("readData Exception: ");
                sbM112a.append(e.getMessage());
                Log.m651e("XLog_APP ", sbM112a.toString());
                return string;
            }
        } catch (IOException e8) {
            e = e8;
            string = strReplace;
        }
    }

    public static void saveDeviceId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        C2150a.m2591b("CLOUD_REGISTER_ID", str);
    }

    public static void saveGuid(String str) {
        C2150a.m2591b("CLOUD_GUID", str);
    }

    public static void saveSecret(String str) {
        C2150a.m2591b("CLOUD_SECRET", str);
    }

    private static void writeData(File file, String str, String str2, boolean z6) throws IOException {
        try {
            File file2 = new File(file, str);
            Log.m655i("XLog_APP ", "writeData file =  " + file2.getAbsolutePath());
            if (file2.isFile() && file2.exists()) {
                C2073a.m2459d(" cloud delete = " + file2.delete());
            }
            String strReplace = str2.replace("\n", "").replace("\r", "").replace(" ", "");
            FileOutputStream fileOutputStreamOpenFileOutput = z6 ? C1231b.f2761c.openFileOutput(file2.getName(), 0) : new FileOutputStream(file2);
            fileOutputStreamOpenFileOutput.write(strReplace.getBytes());
            fileOutputStreamOpenFileOutput.close();
            C2073a.m2459d("File path: " + file2.getAbsolutePath());
        } catch (IOException e7) {
            StringBuilder sbM112a = C0413b.m112a("writeData Exception: ");
            sbM112a.append(e7.getMessage());
            Log.m651e("XLog_APP ", sbM112a.toString());
        }
    }
}
