package com.cctv.p025tv.entity;

import java.util.List;

/* loaded from: classes.dex */
public class PlayerThresholdEntity {
    private DataBean data;
    private String message;
    private String result;

    public static class DataBean {
        private DefaultLiveBean defaultLive;
        private DefaultVodBean defaultVod;
        private List<DeviceListBean> deviceList;

        public static class DefaultLiveBean {
            private int level1;
            private int level2;

            public int getLevel1() {
                return this.level1;
            }

            public int getLevel2() {
                return this.level2;
            }

            public void setLevel1(int i7) {
                this.level1 = i7;
            }

            public void setLevel2(int i7) {
                this.level2 = i7;
            }
        }

        public static class DefaultVodBean {
            private int level1;
            private int level2;

            public int getLevel1() {
                return this.level1;
            }

            public int getLevel2() {
                return this.level2;
            }

            public void setLevel1(int i7) {
                this.level1 = i7;
            }

            public void setLevel2(int i7) {
                this.level2 = i7;
            }
        }

        public static class DeviceListBean {
            private String brand;
            private LiveBean live;
            private String model;
            private VodBean vod;

            public static class LiveBean {
                private int level1;
                private int level2;

                public int getLevel1() {
                    return this.level1;
                }

                public int getLevel2() {
                    return this.level2;
                }

                public void setLevel1(int i7) {
                    this.level1 = i7;
                }

                public void setLevel2(int i7) {
                    this.level2 = i7;
                }
            }

            public static class VodBean {
                private int level1;
                private int level2;

                public int getLevel1() {
                    return this.level1;
                }

                public int getLevel2() {
                    return this.level2;
                }

                public void setLevel1(int i7) {
                    this.level1 = i7;
                }

                public void setLevel2(int i7) {
                    this.level2 = i7;
                }
            }

            public String getBrand() {
                return this.brand;
            }

            public LiveBean getLive() {
                return this.live;
            }

            public String getModel() {
                return this.model;
            }

            public VodBean getVod() {
                return this.vod;
            }

            public void setBrand(String str) {
                this.brand = str;
            }

            public void setLive(LiveBean liveBean) {
                this.live = liveBean;
            }

            public void setModel(String str) {
                this.model = str;
            }

            public void setVod(VodBean vodBean) {
                this.vod = vodBean;
            }
        }

        public DefaultLiveBean getDefaultLive() {
            return this.defaultLive;
        }

        public DefaultVodBean getDefaultVod() {
            return this.defaultVod;
        }

        public List<DeviceListBean> getDeviceList() {
            return this.deviceList;
        }

        public void setDefaultLive(DefaultLiveBean defaultLiveBean) {
            this.defaultLive = defaultLiveBean;
        }

        public void setDefaultVod(DefaultVodBean defaultVodBean) {
            this.defaultVod = defaultVodBean;
        }

        public void setDeviceList(List<DeviceListBean> list) {
            this.deviceList = list;
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
