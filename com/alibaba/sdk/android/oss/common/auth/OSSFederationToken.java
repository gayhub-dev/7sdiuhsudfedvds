package com.alibaba.sdk.android.oss.common.auth;

import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import p009b.C0413b;

/* loaded from: classes.dex */
public class OSSFederationToken {
    private long expiration;
    private String securityToken;
    private String tempAk;
    private String tempSk;

    public OSSFederationToken(String str, String str2, String str3, long j7) {
        setTempAk(str);
        setTempSk(str2);
        setSecurityToken(str3);
        setExpiration(j7);
    }

    public long getExpiration() {
        return this.expiration;
    }

    public String getSecurityToken() {
        return this.securityToken;
    }

    public String getTempAK() {
        return this.tempAk;
    }

    public String getTempSK() {
        return this.tempSk;
    }

    public void setExpiration(long j7) {
        this.expiration = j7;
    }

    public void setExpirationInGMTFormat(String str) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            this.expiration = simpleDateFormat.parse(str).getTime() / 1000;
        } catch (ParseException e7) {
            if (OSSLog.isEnableLog()) {
                e7.printStackTrace();
            }
            this.expiration = (DateUtil.getFixedSkewedTimeMillis() / 1000) + 30;
        }
    }

    public void setSecurityToken(String str) {
        this.securityToken = str;
    }

    public void setTempAk(String str) {
        this.tempAk = str;
    }

    public void setTempSk(String str) {
        this.tempSk = str;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("OSSFederationToken [tempAk=");
        sbM112a.append(this.tempAk);
        sbM112a.append(", tempSk=");
        sbM112a.append(this.tempSk);
        sbM112a.append(", securityToken=");
        sbM112a.append(this.securityToken);
        sbM112a.append(", expiration=");
        sbM112a.append(this.expiration);
        sbM112a.append("]");
        return sbM112a.toString();
    }

    public OSSFederationToken(String str, String str2, String str3, String str4) {
        setTempAk(str);
        setTempSk(str2);
        setSecurityToken(str3);
        setExpirationInGMTFormat(str4);
    }
}
