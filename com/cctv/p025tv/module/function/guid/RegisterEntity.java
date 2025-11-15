package com.cctv.p025tv.module.function.guid;

/* loaded from: classes.dex */
public class RegisterEntity {
    private DataBean data;
    private String message;
    private int result;

    public static class DataBean {
        private String guid;
        private String secretKey;

        public String getGuid() {
            return this.guid;
        }

        public String getSecretKey() {
            return this.secretKey;
        }

        public void setGuid(String str) {
            this.guid = str;
        }

        public void setSecretKey(String str) {
            this.secretKey = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public int getResult() {
        return this.result;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResult(int i7) {
        this.result = i7;
    }
}
