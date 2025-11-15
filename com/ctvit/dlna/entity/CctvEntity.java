package com.ctvit.dlna.entity;

/* loaded from: classes.dex */
public class CctvEntity {
    private ClientBean client;
    private EventBean event;
    private String item_id;
    private PlayerBean player;

    public static class ClientBean {
        private AndroidDeviceIdBean android_device_id;
        private String app_key;
        private IosDeviceIdBean ios_device_id;
        private String system;
        private String user_id;

        public static class AndroidDeviceIdBean {
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

        public static class IosDeviceIdBean {
            private String ud_id;

            public String getUd_id() {
                return this.ud_id;
            }

            public void setUd_id(String str) {
                this.ud_id = str;
            }
        }

        public AndroidDeviceIdBean getAndroid_device_id() {
            return this.android_device_id;
        }

        public String getApp_key() {
            return this.app_key;
        }

        public IosDeviceIdBean getIos_device_id() {
            return this.ios_device_id;
        }

        public String getSystem() {
            return this.system;
        }

        public String getUser_id() {
            return this.user_id;
        }

        public void setAndroid_device_id(AndroidDeviceIdBean androidDeviceIdBean) {
            this.android_device_id = androidDeviceIdBean;
        }

        public void setApp_key(String str) {
            this.app_key = str;
        }

        public void setIos_device_id(IosDeviceIdBean iosDeviceIdBean) {
            this.ios_device_id = iosDeviceIdBean;
        }

        public void setSystem(String str) {
            this.system = str;
        }

        public void setUser_id(String str) {
            this.user_id = str;
        }
    }

    public static class EventBean {
        private String type;
        private String value;

        public String getType() {
            return this.type;
        }

        public String getValue() {
            return this.value;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }

    public static class PlayerBean {
        private String change_bitrate;
        private String high_bitrate_id;
        private boolean keep_on;
        private String seek_to;
        private String speed;
        private String type;

        public String getChange_bitrate() {
            return this.change_bitrate;
        }

        public String getHigh_bitrate_id() {
            return this.high_bitrate_id;
        }

        public String getSeek_to() {
            return this.seek_to;
        }

        public String getSpeed() {
            return this.speed;
        }

        public String getType() {
            return this.type;
        }

        public boolean isKeep_on() {
            return this.keep_on;
        }

        public void setChange_bitrate(String str) {
            this.change_bitrate = str;
        }

        public void setHigh_bitrate_id(String str) {
            this.high_bitrate_id = str;
        }

        public void setKeep_on(boolean z6) {
            this.keep_on = z6;
        }

        public void setSeek_to(String str) {
            this.seek_to = str;
        }

        public void setSpeed(String str) {
            this.speed = str;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    public ClientBean getClient() {
        return this.client;
    }

    public EventBean getEvent() {
        return this.event;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public PlayerBean getPlayer() {
        return this.player;
    }

    public void setClient(ClientBean clientBean) {
        this.client = clientBean;
    }

    public void setEvent(EventBean eventBean) {
        this.event = eventBean;
    }

    public void setItem_id(String str) {
        this.item_id = str;
    }

    public void setPlayer(PlayerBean playerBean) {
        this.player = playerBean;
    }
}
