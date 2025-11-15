package p177w0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import org.json.JSONException;
import org.json.JSONObject;
import p009b.C0413b;
import p043f.C0988e;
import p163u0.C1970a;
import p170v0.C2000e;
import p198z0.C2145a;
import p198z0.C2146b;

/* compiled from: NetworkUtility.java */
/* renamed from: w0.c */
/* loaded from: classes.dex */
public class C2023c {
    /* renamed from: a */
    public static HttpsURLConnection m2370a(String str) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, new TrustManager[]{new C2022b()}, null);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
        httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
        httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: w0.a
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str2, SSLSession sSLSession) {
                try {
                    return HttpsURLConnection.getDefaultHostnameVerifier().verify(str2, sSLSession);
                } catch (Exception unused) {
                    return false;
                }
            }
        });
        return httpsURLConnection;
    }

    /* renamed from: b */
    public static HttpURLConnection m2371b(String str) throws IOException {
        try {
            return str.startsWith("https") ? m2370a(str) : (HttpURLConnection) new URL(str).openConnection();
        } catch (KeyManagementException | NoSuchAlgorithmException | CertificateException e7) {
            throw new IOException("SSL/TLS配置失败", e7);
        }
    }

    /* renamed from: c */
    public static String m2372c(String str, String str2) {
        HttpURLConnection httpURLConnectionM2371b;
        BufferedWriter bufferedWriter;
        if ("无网络".equals(C2145a.m2580h(C1970a.f5748f))) {
            return "";
        }
        StringBuilder sbM112a = C0413b.m112a("info=");
        sbM112a.append(URLEncoder.encode(str2, "utf-8"));
        String string = sbM112a.toString();
        BufferedReader bufferedReader = null;
        try {
            httpURLConnectionM2371b = m2371b(str);
            try {
                httpURLConnectionM2371b.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                httpURLConnectionM2371b.setRequestMethod("POST");
                httpURLConnectionM2371b.setRequestProperty("Charset", "UTF-8");
                httpURLConnectionM2371b.setDoOutput(true);
                httpURLConnectionM2371b.setDoInput(true);
                httpURLConnectionM2371b.setUseCaches(false);
                httpURLConnectionM2371b.setConnectTimeout(15000);
                httpURLConnectionM2371b.connect();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnectionM2371b.getOutputStream(), "UTF-8"));
                try {
                    bufferedWriter.write(string);
                    bufferedWriter.flush();
                    int responseCode = httpURLConnectionM2371b.getResponseCode();
                    if (responseCode != 200) {
                        Exception exc = new Exception("HTTP请求状态异常：" + responseCode);
                        C2000e.a aVar = new C2000e.a();
                        aVar.f5840b = "接口请求失败";
                        aVar.f5839a = "S10000";
                        aVar.f5841c = exc.getMessage();
                        aVar.f5842d = C2145a.m2576d(exc);
                        aVar.f5843e = C2145a.m2574b();
                        aVar.f5844f = C2145a.m2580h(C1970a.f5748f);
                        C2146b.m2589c(C1970a.f5748f);
                        C2146b.m2588b(C1970a.f5748f);
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("key", "sdk_error_d1");
                            jSONObject.put("value", aVar.m2336b());
                        } catch (JSONException e7) {
                            C0988e.m977c(new C2000e(e7));
                            if (C1970a.f5743a) {
                                e7.printStackTrace();
                            }
                        }
                        C0988e.m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject.toString());
                        try {
                            bufferedWriter.close();
                        } catch (IOException unused) {
                        }
                        httpURLConnectionM2371b.disconnect();
                        return "";
                    }
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnectionM2371b.getInputStream(), "UTF-8"));
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                        }
                        String string2 = sb.toString();
                        try {
                            bufferedReader2.close();
                        } catch (IOException unused2) {
                        }
                        try {
                            bufferedWriter.close();
                        } catch (IOException unused3) {
                        }
                        httpURLConnectionM2371b.disconnect();
                        return string2;
                    } catch (Exception unused4) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused5) {
                            }
                        }
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException unused6) {
                            }
                        }
                        if (httpURLConnectionM2371b != null) {
                            httpURLConnectionM2371b.disconnect();
                        }
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused7) {
                            }
                        }
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException unused8) {
                            }
                        }
                        if (httpURLConnectionM2371b == null) {
                            throw th;
                        }
                        httpURLConnectionM2371b.disconnect();
                        throw th;
                    }
                } catch (Exception unused9) {
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception unused10) {
                bufferedWriter = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = null;
            }
        } catch (Exception unused11) {
            httpURLConnectionM2371b = null;
            bufferedWriter = null;
        } catch (Throwable th4) {
            th = th4;
            httpURLConnectionM2371b = null;
            bufferedWriter = null;
        }
    }
}
