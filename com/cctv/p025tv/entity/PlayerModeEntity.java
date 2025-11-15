package com.cctv.p025tv.entity;

import java.util.List;

/* loaded from: classes.dex */
public class PlayerModeEntity {
    private boolean checkRoot;
    private String defaultLive;
    private String defaultVod;
    private List<DeviceListBean> deviceList;

    public static class DeviceListBean {
        private String brand;
        private String live_core;
        private String model;
        private String vod_core;

        public String getBrand() {
            return this.brand;
        }

        public String getLive_core() {
            return this.live_core;
        }

        public String getModel() {
            return this.model;
        }

        public String getVod_core() {
            return this.vod_core;
        }

        public void setBrand(String str) {
            this.brand = str;
        }

        public void setLive_core(String str) {
            this.live_core = str;
        }

        public void setModel(String str) {
            this.model = str;
        }

        public void setVod_core(String str) {
            this.vod_core = str;
        }
    }

    public String getDefaultLive() {
        return this.defaultLive;
    }

    public String getDefaultVod() {
        return this.defaultVod;
    }

    public List<DeviceListBean> getDeviceList() {
        return this.deviceList;
    }

    public boolean isCheckRoot() {
        return this.checkRoot;
    }

    public void setCheckRoot(boolean z6) {
        this.checkRoot = z6;
    }

    public void setDefaultLive(String str) {
        this.defaultLive = str;
    }

    public void setDefaultVod(String str) {
        this.defaultVod = str;
    }

    public void setDeviceList(List<DeviceListBean> list) {
        this.deviceList = list;
    }
}
