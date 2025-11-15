package com.cctv.p025tv.module.collect;

import android.os.Build;
import android.text.TextUtils;
import java.util.List;
import p013b3.C0440a;
import p085k1.C1229c;
import p179w2.C2025a;
import p193y2.C2106a;

/* loaded from: classes.dex */
public class CollectEventDataEntity {
    private C0576a common;
    private List<C0577b> event;
    private List<C0578c> page;

    /* renamed from: com.cctv.tv.module.collect.CollectEventDataEntity$a */
    public static class C0576a {
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

        /* renamed from: com.cctv.tv.module.collect.CollectEventDataEntity$a$a */
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

        /* renamed from: com.cctv.tv.module.collect.CollectEventDataEntity$a$b */
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

    /* renamed from: com.cctv.tv.module.collect.CollectEventDataEntity$b */
    public static class C0577b {
        private String content_id;
        private String event_id;
        private String occur_time;
        private String proc_status;

        public String getContent_id() {
            return this.content_id;
        }

        public String getEvent_id() {
            return this.event_id;
        }

        public String getOccur_time() {
            return this.occur_time;
        }

        public String getProc_status() {
            return this.proc_status;
        }

        public void setContent_id(String str) {
            this.content_id = str;
        }

        public void setEvent_id(String str) {
            this.event_id = str;
        }

        public void setOccur_time(String str) {
            this.occur_time = str;
        }

        public void setProc_status(String str) {
            this.proc_status = str;
        }
    }

    /* renamed from: com.cctv.tv.module.collect.CollectEventDataEntity$c */
    public static class C0578c {
        private String cur_page;
        private String duration;
        private String page_start_time;
        private String prev_page;
        private String proc_status;
        private String session_id;
        private String session_start_time;

        public String getCur_page() {
            return this.cur_page;
        }

        public String getDuration() {
            return this.duration;
        }

        public String getPage_start_time() {
            return this.page_start_time;
        }

        public String getPrev_page() {
            return this.prev_page;
        }

        public String getProc_status() {
            return this.proc_status;
        }

        public String getSession_id() {
            return this.session_id;
        }

        public String getSession_start_time() {
            return this.session_start_time;
        }

        public void setCur_page(String str) {
            this.cur_page = str;
        }

        public void setDuration(String str) {
            this.duration = str;
        }

        public void setPage_start_time(String str) {
            this.page_start_time = str;
        }

        public void setPrev_page(String str) {
            this.prev_page = str;
        }

        public void setProc_status(String str) {
            this.proc_status = str;
        }

        public void setSession_id(String str) {
            this.session_id = str;
        }

        public void setSession_start_time(String str) {
            this.session_start_time = str;
        }
    }

    public CollectEventDataEntity() {
        C0576a c0576a = new C0576a();
        c0576a.setManufacturer(Build.BRAND);
        c0576a.setProduct_model(Build.MODEL);
        c0576a.setResolution(C2106a.m2550c());
        c0576a.setApp_version_name(C2025a.m2376d());
        c0576a.setApp_version_code(C2025a.m2375c() + "");
        c0576a.setOs_version(Build.VERSION.RELEASE);
        c0576a.setSend_time(C0440a.m151b(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", "GMT+08"));
        C0576a.a aVar = new C0576a.a();
        aVar.setImei("");
        C2106a.m2548a();
        aVar.setAndroid_id("");
        aVar.setSerial("");
        c0576a.setDevice_id(aVar);
        setCommon(c0576a);
    }

    public C0576a getCommon() {
        return this.common;
    }

    public List<C0577b> getEvent() {
        return this.event;
    }

    public List<C0578c> getPage() {
        return this.page;
    }

    public void setCommon(C0576a c0576a) {
        this.common = c0576a;
    }

    public void setEvent(List<C0577b> list) {
        this.event = list;
    }

    public void setPage(List<C0578c> list) {
        this.page = list;
    }
}
