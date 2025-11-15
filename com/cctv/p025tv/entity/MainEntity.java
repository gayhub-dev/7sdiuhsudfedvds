package com.cctv.p025tv.entity;

import com.alibaba.fastjson.annotation.JSONField;

/* loaded from: classes.dex */
public class MainEntity {
    private DataBean data;
    private String message;
    private String result;

    public static class DataBean {
        private DeviceCheckBean deviceCheck;
        private boolean gray;
        private String image;
        private String sign;
        private String text;
        private boolean visible;

        public static class DeviceCheckBean {

            @JSONField(name = "CPU")
            private Integer CPU;
            private Integer freeMem;
            private Integer freeRAM;
            private Double versionAN;

            public Integer getCPU() {
                return this.CPU;
            }

            public Integer getFreeMem() {
                return this.freeMem;
            }

            public Integer getFreeRAM() {
                return this.freeRAM;
            }

            public Double getVersionAN() {
                return this.versionAN;
            }

            public void setCPU(Integer num) {
                this.CPU = num;
            }

            public void setFreeMem(Integer num) {
                this.freeMem = num;
            }

            public void setFreeRAM(Integer num) {
                this.freeRAM = num;
            }

            public void setVersionAN(Double d7) {
                this.versionAN = d7;
            }
        }

        public DeviceCheckBean getDeviceCheck() {
            return this.deviceCheck;
        }

        public String getImage() {
            return this.image;
        }

        public String getSign() {
            return this.sign;
        }

        public String getText() {
            return this.text;
        }

        public boolean isGray() {
            return this.gray;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void setDeviceCheck(DeviceCheckBean deviceCheckBean) {
            this.deviceCheck = deviceCheckBean;
        }

        public void setGray(boolean z6) {
            this.gray = z6;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public void setSign(String str) {
            this.sign = str;
        }

        public void setText(String str) {
            this.text = str;
        }

        public void setVisible(boolean z6) {
            this.visible = z6;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResult() {
        return this.result;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResult(String str) {
        this.result = str;
    }
}
