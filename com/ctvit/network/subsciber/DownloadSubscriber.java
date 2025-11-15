package com.ctvit.network.subsciber;

import android.content.Context;
import android.support.constraint.C0072a;
import android.text.TextUtils;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.callback.CallBack;
import com.ctvit.network.callback.DownloadProgressCallBack;
import com.ctvit.network.exception.ApiException;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;
import p014b4.InterfaceC0446f;
import p101m1.C1458b;
import p186x2.C2073a;
import p194y3.AbstractC2120l;

/* loaded from: classes.dex */
public class DownloadSubscriber<ResponseBody extends ResponseBody> extends BaseSubscriber<ResponseBody> {
    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive";
    private static String JPG_CONTENTTYPE = "image/jpg";
    private static String PNG_CONTENTTYPE = "image/png";
    private static String fileSuffix = "";
    private Context context = CtvitHttp.getContext();
    private long lastRefreshUiTime = System.currentTimeMillis();
    public CallBack mCallBack;
    private String name;
    private String path;

    public DownloadSubscriber(String str, String str2, CallBack callBack) {
        this.path = str;
        this.name = str2;
        this.mCallBack = callBack;
    }

    private void finalonError(Exception exc) {
        if (this.mCallBack == null) {
            return;
        }
        AbstractC2120l.just(new ApiException(exc, 100)).observeOn(AndroidSchedulers.mainThread()).subscribe(new InterfaceC0446f<ApiException>() { // from class: com.ctvit.network.subsciber.DownloadSubscriber.5
            @Override // p014b4.InterfaceC0446f
            public void accept(ApiException apiException) {
                CallBack callBack = DownloadSubscriber.this.mCallBack;
                if (callBack != null) {
                    callBack.onError(apiException);
                }
            }
        }, new InterfaceC0446f<Throwable>() { // from class: com.ctvit.network.subsciber.DownloadSubscriber.6
            @Override // p014b4.InterfaceC0446f
            public void accept(Throwable th) {
            }
        });
    }

    private boolean writeResponseBodyToDisk(String str, String str2, Context context, ResponseBody responseBody) throws Throwable {
        String string;
        final String strReplaceAll;
        InputStream inputStreamByteStream;
        String str3;
        long j7;
        String str4;
        String str5 = "file downloaded: ";
        if (TextUtils.isEmpty(str2)) {
            string = System.currentTimeMillis() + fileSuffix;
        } else if (str2.contains(".")) {
            string = str2;
        } else {
            String string2 = responseBody.contentType().toString();
            if (string2.equals(APK_CONTENTTYPE)) {
                fileSuffix = ".apk";
            } else if (string2.equals(PNG_CONTENTTYPE)) {
                fileSuffix = ".png";
            } else if (string2.equals(JPG_CONTENTTYPE)) {
                fileSuffix = ".jpg";
            } else {
                StringBuilder sbM112a = C0413b.m112a(".");
                sbM112a.append(responseBody.contentType().subtype());
                fileSuffix = sbM112a.toString();
            }
            StringBuilder sbM112a2 = C0413b.m112a(str2);
            sbM112a2.append(fileSuffix);
            string = sbM112a2.toString();
        }
        FileOutputStream fileOutputStream = null;
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(context.getExternalFilesDir(null));
            strReplaceAll = C0072a.m92a(sb, File.separator, string);
        } else {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            StringBuilder sbM112a3 = C0413b.m112a(str);
            sbM112a3.append(File.separator);
            sbM112a3.append(string);
            strReplaceAll = sbM112a3.toString().replaceAll("//", ServiceReference.DELIMITER);
        }
        C1458b.m1642a("path:-->", strReplaceAll);
        int i7 = 0;
        try {
            File file2 = new File(strReplaceAll);
            try {
                try {
                    byte[] bArr = new byte[131072];
                    final long jContentLength = responseBody.contentLength();
                    long j8 = 0;
                    C2073a.m2459d("file length: " + jContentLength);
                    inputStreamByteStream = responseBody.byteStream();
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                        try {
                            final CallBack callBack = this.mCallBack;
                            while (true) {
                                int i8 = inputStreamByteStream.read(bArr);
                                if (i8 == -1) {
                                    break;
                                }
                                fileOutputStream2.write(bArr, i7, i8);
                                final long j9 = j8 + i8;
                                C2073a.m2459d("file download: " + j9 + " of " + jContentLength);
                                float f7 = (((float) j9) * 1.0f) / ((float) jContentLength);
                                if (System.currentTimeMillis() - this.lastRefreshUiTime >= 200 || f7 == 1.0f) {
                                    if (callBack != null) {
                                        j7 = j9;
                                        str3 = strReplaceAll;
                                        str4 = str5;
                                        AbstractC2120l.just(Long.valueOf(j9)).observeOn(AndroidSchedulers.mainThread()).subscribe(new InterfaceC0446f<Long>() { // from class: com.ctvit.network.subsciber.DownloadSubscriber.1
                                            @Override // p014b4.InterfaceC0446f
                                            public void accept(Long l7) {
                                                CallBack callBack2 = callBack;
                                                if (callBack2 instanceof DownloadProgressCallBack) {
                                                    DownloadProgressCallBack downloadProgressCallBack = (DownloadProgressCallBack) callBack2;
                                                    long j10 = j9;
                                                    long j11 = jContentLength;
                                                    downloadProgressCallBack.update(j10, j11, j10 == j11);
                                                }
                                            }
                                        }, new InterfaceC0446f<Throwable>() { // from class: com.ctvit.network.subsciber.DownloadSubscriber.2
                                            @Override // p014b4.InterfaceC0446f
                                            public void accept(Throwable th) {
                                            }
                                        });
                                    } else {
                                        str3 = strReplaceAll;
                                        j7 = j9;
                                        str4 = str5;
                                    }
                                    this.lastRefreshUiTime = System.currentTimeMillis();
                                } else {
                                    str3 = strReplaceAll;
                                    j7 = j9;
                                    str4 = str5;
                                }
                                i7 = 0;
                                strReplaceAll = str3;
                                j8 = j7;
                                str5 = str4;
                            }
                            fileOutputStream2.flush();
                            C2073a.m2459d(str5 + j8 + " of " + jContentLength);
                            if (callBack != null) {
                                AbstractC2120l.just(strReplaceAll).observeOn(AndroidSchedulers.mainThread()).subscribe(new InterfaceC0446f<String>() { // from class: com.ctvit.network.subsciber.DownloadSubscriber.3
                                    @Override // p014b4.InterfaceC0446f
                                    public void accept(String str6) {
                                        CallBack callBack2 = callBack;
                                        if (callBack2 instanceof DownloadProgressCallBack) {
                                            ((DownloadProgressCallBack) callBack2).onComplete(strReplaceAll);
                                        }
                                    }
                                }, new InterfaceC0446f<Throwable>() { // from class: com.ctvit.network.subsciber.DownloadSubscriber.4
                                    @Override // p014b4.InterfaceC0446f
                                    public void accept(Throwable th) {
                                    }
                                });
                                C2073a.m2459d(str5 + j8 + " of " + jContentLength);
                                C2073a.m2459d("file downloaded: is sucess");
                            }
                            fileOutputStream2.close();
                            inputStreamByteStream.close();
                            return true;
                        } catch (IOException e7) {
                            e = e7;
                            fileOutputStream = fileOutputStream2;
                            finalonError(e);
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (inputStreamByteStream == null) {
                                return false;
                            }
                            inputStreamByteStream.close();
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (inputStreamByteStream != null) {
                                inputStreamByteStream.close();
                            }
                            throw th;
                        }
                    } catch (IOException e8) {
                        e = e8;
                    }
                } catch (IOException e9) {
                    e = e9;
                    inputStreamByteStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamByteStream = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e10) {
            finalonError(e10);
            return false;
        }
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p194y3.InterfaceC2127s
    public final void onComplete() {
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber
    public void onError(ApiException apiException) {
        StringBuilder sbM112a = C0413b.m112a("DownSubscriber:>>>> onError:");
        sbM112a.append(apiException.getMessage());
        C2073a.m2456a(sbM112a.toString());
        finalonError(apiException);
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p153s4.AbstractC1880c
    public void onStart() {
        super.onStart();
        CallBack callBack = this.mCallBack;
        if (callBack != null) {
            callBack.onStart();
        }
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p194y3.InterfaceC2127s
    public void onNext(ResponseBody responsebody) throws Throwable {
        C2073a.m2459d("DownSubscriber:>>>> onNext");
        writeResponseBodyToDisk(this.path, this.name, this.context, responsebody);
    }
}
