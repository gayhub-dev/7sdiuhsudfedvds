package p135q1;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p009b.C0413b;
import p101m1.C1458b;
import p186x2.C2073a;
import p200z2.C2150a;

/* compiled from: ReportCache.java */
/* renamed from: q1.b */
/* loaded from: classes.dex */
public class C1759b {

    /* renamed from: g */
    public static C1759b f5002g;

    /* renamed from: a */
    public final ExecutorService f5003a = Executors.newSingleThreadExecutor();

    /* renamed from: b */
    public int f5004b = -1;

    /* renamed from: c */
    public String f5005c = "report_number";

    /* renamed from: d */
    public String f5006d;

    /* renamed from: e */
    public String f5007e;

    /* renamed from: f */
    public long f5008f;

    /* renamed from: a */
    public static C1759b m1924a() {
        if (f5002g == null) {
            synchronized (C1759b.class) {
                if (f5002g == null) {
                    f5002g = new C1759b();
                }
            }
        }
        return f5002g;
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00b7: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:45:0x00b7 */
    /* renamed from: b */
    public final void m1925b(String str) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        Exception e7;
        InputStreamReader inputStreamReader2;
        StringBuilder sb;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        C1458b.m1642a("report readFileAndUpload filePath = ", str);
        String str2 = "";
        synchronized (this) {
            InputStreamReader inputStreamReader3 = null;
            try {
                try {
                    try {
                        fileInputStream = new FileInputStream(str);
                    } catch (Exception e8) {
                        inputStreamReader = null;
                        e7 = e8;
                        fileInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = null;
                    }
                    try {
                        inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    } catch (Exception e9) {
                        inputStreamReader = null;
                        e7 = e9;
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStreamReader3 != null) {
                            try {
                                inputStreamReader3.close();
                            } catch (IOException e10) {
                                C2073a.m2456a("report ReportCache uploadCache2 e = " + e10.getMessage());
                                throw th;
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                    try {
                        StringBuffer stringBuffer = new StringBuffer(10240);
                        while (inputStreamReader.ready()) {
                            stringBuffer.append((char) inputStreamReader.read());
                        }
                        inputStreamReader.close();
                        fileInputStream.close();
                        stringBuffer.trimToSize();
                        str2 = String.format("[%s]", stringBuffer);
                    } catch (Exception e11) {
                        e7 = e11;
                        C2073a.m2456a("report ReportCache uploadCache1 e = " + e7.getMessage());
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e12) {
                                sb = new StringBuilder();
                                sb.append("report ReportCache uploadCache2 e = ");
                                sb.append(e12.getMessage());
                                C2073a.m2456a(sb.toString());
                                C1760c.m1928b(str2, str);
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        C1760c.m1928b(str2, str);
                    }
                    try {
                        inputStreamReader.close();
                        fileInputStream.close();
                    } catch (IOException e13) {
                        sb = new StringBuilder();
                        sb.append("report ReportCache uploadCache2 e = ");
                        sb.append(e13.getMessage());
                        C2073a.m2456a(sb.toString());
                        C1760c.m1928b(str2, str);
                    }
                    C1760c.m1928b(str2, str);
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader3 = inputStreamReader2;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }

    /* renamed from: c */
    public void m1926c(final String str) {
        if (str.contains("app_heartbeat") || str.contains("play_heartbeat")) {
            C2073a.m2456a("report saveData 剔除数据");
            return;
        }
        if (this.f5004b > 49) {
            this.f5004b = 0;
            StringBuilder sbM112a = C0413b.m112a("report_data_");
            sbM112a.append(System.currentTimeMillis());
            this.f5006d = sbM112a.toString();
        }
        String str2 = this.f5007e;
        String str3 = this.f5006d;
        File file = new File(str2, str3);
        if (!file.exists() || file.length() < 1) {
            try {
                file.createNewFile();
            } catch (IOException e7) {
                StringBuilder sbM112a2 = C0413b.m112a("report ReportCache getFilePath e = ");
                sbM112a2.append(e7.getMessage());
                C2073a.m2456a(sbM112a2.toString());
            }
        }
        final String absolutePath = file.getAbsolutePath();
        if (TextUtils.isEmpty(absolutePath)) {
            return;
        }
        C2073a.m2459d("report writeFile dir = " + str2);
        C2073a.m2459d("report writeFile fileName = " + str3);
        this.f5003a.execute(new Runnable() { // from class: q1.a
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public final void run() {
                Throwable th;
                FileOutputStream fileOutputStream;
                Exception e8;
                StringBuilder sb;
                String message;
                OutputStreamWriter outputStreamWriter;
                StringBuilder sb2;
                C1759b c1759b = this.f4999e;
                String str4 = absolutePath;
                String str5 = str;
                synchronized (c1759b) {
                    OutputStreamWriter outputStreamWriter2 = null;
                    outputStreamWriter = null;
                    OutputStreamWriter outputStreamWriter3 = null;
                    OutputStreamWriter outputStreamWriter4 = null;
                    try {
                        try {
                            try {
                                fileOutputStream = new FileOutputStream(str4, true);
                                try {
                                    outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                                    try {
                                        sb2 = new StringBuilder();
                                        sb2.append(",");
                                        sb2.append(str5);
                                        outputStreamWriter.write(sb2.toString());
                                        outputStreamWriter.close();
                                    } catch (Exception e9) {
                                        e8 = e9;
                                        outputStreamWriter3 = outputStreamWriter;
                                        C2073a.m2456a("report ReportCache writeFile1 e = " + e8.getMessage());
                                        if (outputStreamWriter3 != null) {
                                            try {
                                                outputStreamWriter3.close();
                                            } catch (IOException e10) {
                                                sb = new StringBuilder();
                                                sb.append("report ReportCache writeFile2 e = ");
                                                message = e10.getMessage();
                                                sb.append(message);
                                                C2073a.m2456a(sb.toString());
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (c1759b.f5004b == -1) {
                                            c1759b.f5004b = ((Integer) C2150a.m2590a(c1759b.f5005c, 0)).intValue();
                                        }
                                        int i7 = c1759b.f5004b + 1;
                                        c1759b.f5004b = i7;
                                        C2150a.m2591b(c1759b.f5005c, Integer.valueOf(i7));
                                        outputStreamWriter2 = outputStreamWriter3;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        outputStreamWriter4 = outputStreamWriter;
                                        if (outputStreamWriter4 != null) {
                                            try {
                                                outputStreamWriter4.close();
                                            } catch (IOException e11) {
                                                C2073a.m2456a("report ReportCache writeFile2 e = " + e11.getMessage());
                                                throw th;
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (c1759b.f5004b == -1) {
                                            c1759b.f5004b = ((Integer) C2150a.m2590a(c1759b.f5005c, 0)).intValue();
                                        }
                                        int i8 = c1759b.f5004b + 1;
                                        c1759b.f5004b = i8;
                                        C2150a.m2591b(c1759b.f5005c, Integer.valueOf(i8));
                                        throw th;
                                    }
                                } catch (Exception e12) {
                                    e8 = e12;
                                }
                            } catch (Exception e13) {
                                e8 = e13;
                                fileOutputStream = null;
                            } catch (Throwable th3) {
                                th = th3;
                                fileOutputStream = null;
                            }
                            try {
                                outputStreamWriter.close();
                                fileOutputStream.close();
                                if (c1759b.f5004b == -1) {
                                    c1759b.f5004b = ((Integer) C2150a.m2590a(c1759b.f5005c, 0)).intValue();
                                }
                                int i9 = c1759b.f5004b + 1;
                                c1759b.f5004b = i9;
                                C2150a.m2591b(c1759b.f5005c, Integer.valueOf(i9));
                                outputStreamWriter2 = sb2;
                            } catch (IOException e14) {
                                sb = new StringBuilder();
                                sb.append("report ReportCache writeFile2 e = ");
                                message = e14.getMessage();
                                sb.append(message);
                                C2073a.m2456a(sb.toString());
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            outputStreamWriter4 = outputStreamWriter2;
                        }
                    } catch (Throwable th5) {
                        throw th5;
                    }
                }
            }
        });
    }
}
