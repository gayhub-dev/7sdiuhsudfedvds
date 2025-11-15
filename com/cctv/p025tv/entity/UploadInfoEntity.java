package com.cctv.p025tv.entity;

/* loaded from: classes.dex */
public class UploadInfoEntity {
    private DataBean data;
    private String message;
    private String result;
    private Long time;

    public static class DataBean {
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
        private String directoryPrefix;
        private String endpoint;
        private String expiration;
        private String securityToken;

        public String getAccessKeyId() {
            return this.accessKeyId;
        }

        public String getAccessKeySecret() {
            return this.accessKeySecret;
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public String getDirectoryPrefix() {
            return this.directoryPrefix;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public String getExpiration() {
            return this.expiration;
        }

        public String getSecurityToken() {
            return this.securityToken;
        }

        public void setAccessKeyId(String str) {
            this.accessKeyId = str;
        }

        public void setAccessKeySecret(String str) {
            this.accessKeySecret = str;
        }

        public void setBucketName(String str) {
            this.bucketName = str;
        }

        public void setDirectoryPrefix(String str) {
            this.directoryPrefix = str;
        }

        public void setEndpoint(String str) {
            this.endpoint = str;
        }

        public void setExpiration(String str) {
            this.expiration = str;
        }

        public void setSecurityToken(String str) {
            this.securityToken = str;
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

    public Long getTime() {
        return this.time;
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

    public void setTime(Long l7) {
        this.time = l7;
    }
}
