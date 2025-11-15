package com.ctvit.encryptplay.entity;

/* loaded from: classes.dex */
public class VDNSKEntity {
    private C0626a data;
    private String message;
    private String result;
    private Long time;

    /* renamed from: com.ctvit.encryptplay.entity.VDNSKEntity$a */
    public static class C0626a {
        private String appSecret;

        public String getAppSecret() {
            return this.appSecret;
        }

        public void setAppSecret(String str) {
            this.appSecret = str;
        }
    }

    public C0626a getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResult() {
        return this.result;
    }

    public Long getTime() {
        return this.time;
    }

    public void setData(C0626a c0626a) {
        this.data = c0626a;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setTime(Long l7) {
        this.time = l7;
    }
}
