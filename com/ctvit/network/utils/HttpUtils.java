package com.ctvit.network.utils;

import java.nio.charset.Charset;
import java.util.Map;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class HttpUtils {
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static String createUrlFromParams(String str, Map<String, String> map) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (str.indexOf(38) > 0 || str.indexOf(63) > 0) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String value = entry.getValue();
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (Exception e7) {
            C2073a.m2456a(e7.getMessage());
            return str;
        }
    }
}
