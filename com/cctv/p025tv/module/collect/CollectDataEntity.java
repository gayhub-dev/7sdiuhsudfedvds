package com.cctv.p025tv.module.collect;

import android.os.Build;
import android.text.TextUtils;
import java.util.List;
import p013b3.C0440a;
import p085k1.C1229c;
import p179w2.C2025a;
import p193y2.C2106a;

/* loaded from: classes.dex */
public class CollectDataEntity {
    private C0574a common;
    private List<C0575b> video;

    /* renamed from: com.cctv.tv.module.collect.CollectDataEntity$a */
    public static class C0574a {
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

        /* renamed from: com.cctv.tv.module.collect.CollectDataEntity$a$a */
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

        /* renamed from: com.cctv.tv.module.collect.CollectDataEntity$a$b */
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

    /* renamed from: com.cctv.tv.module.collect.CollectDataEntity$b */
    public static class C0575b {
        private String content_id;
        private String content_name;
        private String current_position;
        private String duration;
        private String network_type;
        private String occur_time;
        private String player_id;
        private String player_rate;
        private String player_status;
        private String player_type;
        private String player_url;
        private String total_duration;

        public String getContent_id() {
            return this.content_id;
        }

        public String getContent_name() {
            return this.content_name;
        }

        public String getCurrent_position() {
            return this.current_position;
        }

        public String getDuration() {
            return this.duration;
        }

        public String getNetwork_type() {
            return this.network_type;
        }

        public String getOccur_time() {
            return this.occur_time;
        }

        public String getPlayer_id() {
            return this.player_id;
        }

        public String getPlayer_rate() {
            return this.player_rate;
        }

        public String getPlayer_status() {
            return this.player_status;
        }

        public String getPlayer_type() {
            return this.player_type;
        }

        public String getPlayer_url() {
            return this.player_url;
        }

        public String getTotal_duration() {
            return this.total_duration;
        }

        public void setContent_id(String str) {
            this.content_id = str;
        }

        public void setContent_name(String str) {
            this.content_name = str;
        }

        public void setCurrent_position(String str) {
            this.current_position = str;
        }

        public void setDuration(String str) {
            this.duration = str;
        }

        public void setNetwork_type(String str) {
            this.network_type = str;
        }

        public void setOccur_time(String str) {
            this.occur_time = str;
        }

        public void setPlayer_id(String str) {
            this.player_id = str;
        }

        public void setPlayer_rate(String str) {
            this.player_rate = str;
        }

        public void setPlayer_status(String str) {
            this.player_status = str;
        }

        public void setPlayer_type(String str) {
            this.player_type = str;
        }

        public void setPlayer_url(String str) {
            this.player_url = str;
        }

        public void setTotal_duration(String str) {
            this.total_duration = str;
        }
    }

    public CollectDataEntity() {
        C0574a c0574a = new C0574a();
        c0574a.setManufacturer(Build.BRAND);
        c0574a.setProduct_model(Build.MODEL);
        c0574a.setResolution(C2106a.m2550c());
        c0574a.setApp_version_name(C2025a.m2376d());
        c0574a.setApp_version_code(C2025a.m2375c() + "");
        c0574a.setOs_version(Build.VERSION.RELEASE);
        c0574a.setSend_time(C0440a.m151b(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", "GMT+08"));
        C0574a.a aVar = new C0574a.a();
        aVar.setImei("");
        C2106a.m2548a();
        aVar.setAndroid_id("");
        aVar.setSerial("");
        c0574a.setDevice_id(aVar);
        setCommon(c0574a);
    }

    public C0574a getCommon() {
        return this.common;
    }

    public List<C0575b> getVideo() {
        return this.video;
    }

    public void setCommon(C0574a c0574a) {
        this.common = c0574a;
    }

    public void setVideo(List<C0575b> list) {
        this.video = list;
    }
}
