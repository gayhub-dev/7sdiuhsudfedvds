package com.cctv.p025tv.module.service.bean;

import java.util.List;

/* loaded from: classes.dex */
public class WebSocketMsgInfo {
    private C0597a deviceId;
    private String rate;
    private int state;
    private List<C0598b> total;
    private String url;

    /* renamed from: com.cctv.tv.module.service.bean.WebSocketMsgInfo$a */
    public static class C0597a {
        private String android_id;
        private String imei;
        private String serial;

        public String getAndroid_id() {
            return this.android_id;
        }

        public String getImei() {
            return this.imei;
        }

        public String getSerial() {
            return this.serial;
        }

        public void setAndroid_id(String str) {
            this.android_id = str;
        }

        public void setImei(String str) {
            this.imei = str;
        }

        public void setSerial(String str) {
            this.serial = str;
        }
    }

    /* renamed from: com.cctv.tv.module.service.bean.WebSocketMsgInfo$b */
    public static class C0598b {
        private String key;
        private String value;

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }

    public C0597a getDeviceId() {
        return this.deviceId;
    }

    public String getRate() {
        return this.rate;
    }

    public int getState() {
        return this.state;
    }

    public List<C0598b> getTotal() {
        return this.total;
    }

    public String getUrl() {
        return this.url;
    }

    public void setDeviceId(C0597a c0597a) {
        this.deviceId = c0597a;
    }

    public void setRate(String str) {
        this.rate = str;
    }

    public void setState(int i7) {
        this.state = i7;
    }

    public void setTotal(List<C0598b> list) {
        this.total = list;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
