package com.cctv.p025tv.module.collect;

import android.os.Build;
import android.text.TextUtils;
import java.util.List;
import p013b3.C0440a;
import p085k1.C1229c;
import p179w2.C2025a;
import p193y2.C2106a;

/* loaded from: classes.dex */
public class CollectDLNADataEntity {
    private C0573a common;
    private List<Object> dlna;

    /* renamed from: com.cctv.tv.module.collect.CollectDLNADataEntity$a */
    public static class C0573a {
        private String app_key = C1229c.f2759a;
        private String app_version_code;
        private String app_version_name;
        private a device_id;
        private String manufacturer;
        private b mobile_id;
        private String os_version;
        private String product_model;
        private String resolution;
        private String send_time;
        private String userid;

        /* renamed from: com.cctv.tv.module.collect.CollectDLNADataEntity$a$a */
        public static class a {
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

        /* renamed from: com.cctv.tv.module.collect.CollectDLNADataEntity$a$b */
        public static class b {
            private String android_id;
            private String imei;
            private String serial;
            private String ud_id;

            public String getAndroid_id() {
                return this.android_id;
            }

            public String getImei() {
                return this.imei;
            }

            public String getSerial() {
                return this.serial;
            }

            public String getUd_id() {
                return this.ud_id;
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

            public void setUd_id(String str) {
                this.ud_id = str;
            }
        }

        public String getApp_key() {
            return this.app_key;
        }

        public String getApp_version_code() {
            return this.app_version_code;
        }

        public String getApp_version_name() {
            return this.app_version_name;
        }

        public a getDevice_id() {
            return this.device_id;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public b getMobile_id() {
            return this.mobile_id;
        }

        public String getOs_version() {
            return this.os_version;
        }

        public String getProduct_model() {
            return this.product_model;
        }

        public String getResolution() {
            return this.resolution;
        }

        public String getSend_time() {
            return this.send_time;
        }

        public String getUserid() {
            return this.userid;
        }

        public void setApp_key(String str) {
            if (TextUtils.isEmpty(str)) {
                str = C1229c.f2759a;
            }
            this.app_key = str;
        }

        public void setApp_version_code(String str) {
            this.app_version_code = str;
        }

        public void setApp_version_name(String str) {
            this.app_version_name = str;
        }

        public void setDevice_id(a aVar) {
            this.device_id = aVar;
        }

        public void setManufacturer(String str) {
            this.manufacturer = str;
        }

        public void setMobile_id(b bVar) {
            this.mobile_id = bVar;
        }

        public void setOs_version(String str) {
            this.os_version = str;
        }

        public void setProduct_model(String str) {
            this.product_model = str;
        }

        public void setResolution(String str) {
            this.resolution = str;
        }

        public void setSend_time(String str) {
            this.send_time = str;
        }

        public void setUserid(String str) {
            this.userid = str;
        }
    }

    public CollectDLNADataEntity() {
        C0573a c0573a = new C0573a();
        c0573a.setManufacturer(Build.BRAND);
        c0573a.setProduct_model(Build.MODEL);
        c0573a.setResolution(C2106a.m2550c());
        c0573a.setApp_version_name(C2025a.m2376d());
        c0573a.setApp_version_code(C2025a.m2375c() + "");
        c0573a.setOs_version(Build.VERSION.RELEASE);
        c0573a.setSend_time(C0440a.m151b(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", "GMT+08"));
        C0573a.a aVar = new C0573a.a();
        aVar.setImei("");
        C2106a.m2548a();
        aVar.setAndroid_id("");
        aVar.setSerial("");
        c0573a.setDevice_id(aVar);
        setCommon(c0573a);
    }

    public C0573a getCommon() {
        return this.common;
    }

    public List<Object> getDlna() {
        return this.dlna;
    }

    public void setCommon(C0573a c0573a) {
        this.common = c0573a;
    }

    public void setDlna(List<Object> list) {
        this.dlna = list;
    }
}
