package com.alibaba.sdk.android.oss.common.utils;

import android.os.Build;
import android.support.v4.app.C0164a;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alibaba.sdk.android.oss.common.OSSLog;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class VersionInfoUtils {
    private static String userAgent;

    private static String getSystemInfo() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(System.getProperty("os.name"));
        sbM112a.append("/Android " + Build.VERSION.RELEASE);
        sbM112a.append(ServiceReference.DELIMITER);
        sbM112a.append(HttpUtil.urlEncode(Build.MODEL, "utf-8") + ";" + HttpUtil.urlEncode(Build.ID, "utf-8"));
        sbM112a.append(")");
        String string = sbM112a.toString();
        OSSLog.logDebug("user agent : " + string);
        return OSSUtils.isEmptyString(string) ? System.getProperty("http.agent").replaceAll("[^\\p{ASCII}]", "?") : string;
    }

    public static String getUserAgent(String str) {
        if (OSSUtils.isEmptyString(userAgent)) {
            StringBuilder sbM112a = C0413b.m112a("aliyun-sdk-android/");
            sbM112a.append(getVersion());
            sbM112a.append(getSystemInfo());
            userAgent = sbM112a.toString();
        }
        return OSSUtils.isEmptyString(str) ? userAgent : C0164a.m99a(new StringBuilder(), userAgent, ServiceReference.DELIMITER, str);
    }

    public static String getVersion() {
        return OSSConstants.SDK_VERSION;
    }
}
