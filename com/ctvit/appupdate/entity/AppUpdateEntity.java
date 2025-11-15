package com.ctvit.appupdate.entity;

/* loaded from: classes.dex */
public class AppUpdateEntity {
    private String Forced;

    /* renamed from: android, reason: collision with root package name */
    private AndroidBean f6338android;
    private IosBean ios;

    public static class AndroidBean {
        private String appUrl;
        private String brief;
        private String openFun;
        private String size;
        private String title;
        private String updateTime;
        private String versionCode;
        private String versionName;

        public String getAppUrl() {
            return this.appUrl;
        }

        public String getBrief() {
            return this.brief;
        }

        public String getOpenFun() {
            return this.openFun;
        }

        public String getSize() {
            return this.size;
        }

        public String getTitle() {
            return this.title;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public String getVersionCode() {
            return this.versionCode;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public void setAppUrl(String str) {
            this.appUrl = str;
        }

        public void setBrief(String str) {
            this.brief = str;
        }

        public void setOpenFun(String str) {
            this.openFun = str;
        }

        public void setSize(String str) {
            this.size = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public void setVersionCode(String str) {
            this.versionCode = str;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }
    }

    public static class IosBean {
        private String appUrl;
        private String brief;
        private String openFun;
        private String title;
        private String updateTime;
        private String versionCode;
        private String versionName;

        public String getAppUrl() {
            return this.appUrl;
        }

        public String getBrief() {
            return this.brief;
        }

        public String getOpenFun() {
            return this.openFun;
        }

        public String getTitle() {
            return this.title;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public String getVersionCode() {
            return this.versionCode;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public void setAppUrl(String str) {
            this.appUrl = str;
        }

        public void setBrief(String str) {
            this.brief = str;
        }

        public void setOpenFun(String str) {
            this.openFun = str;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public void setVersionCode(String str) {
            this.versionCode = str;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }
    }

    public AndroidBean getAndroid() {
        return this.f6338android;
    }

    public String getForced() {
        return this.Forced;
    }

    public IosBean getIos() {
        return this.ios;
    }

    public void setAndroid(AndroidBean androidBean) {
        this.f6338android = androidBean;
    }

    public void setForced(String str) {
        this.Forced = str;
    }

    public void setIos(IosBean iosBean) {
        this.ios = iosBean;
    }
}
