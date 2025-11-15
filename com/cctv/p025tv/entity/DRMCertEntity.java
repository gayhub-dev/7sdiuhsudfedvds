package com.cctv.p025tv.entity;

import java.util.List;

/* loaded from: classes.dex */
public class DRMCertEntity {
    private List<DataBean> data;
    private String message;
    private String result;
    private Long time;

    public static class DataBean {
        private List<String> clientSign;
        private String emergencyUrl;
        private String hwLicenseUrl;
        private String licenseUrl;
        private String provisionUrl;

        public List<String> getClientSign() {
            return this.clientSign;
        }

        public String getEmergencyUrl() {
            return this.emergencyUrl;
        }

        public String getHwLicenseUrl() {
            return this.hwLicenseUrl;
        }

        public String getLicenseUrl() {
            return this.licenseUrl;
        }

        public String getProvisionUrl() {
            return this.provisionUrl;
        }

        public void setClientSign(List<String> list) {
            this.clientSign = list;
        }

        public void setEmergencyUrl(String str) {
            this.emergencyUrl = str;
        }

        public void setHwLicenseUrl(String str) {
            this.hwLicenseUrl = str;
        }

        public void setLicenseUrl(String str) {
            this.licenseUrl = str;
        }

        public void setProvisionUrl(String str) {
            this.provisionUrl = str;
        }
    }

    public List<DataBean> getData() {
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

    public void setData(List<DataBean> list) {
        this.data = list;
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
