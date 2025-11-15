package com.cctv.p025tv.entity;

import java.util.List;

/* loaded from: classes.dex */
public class CheckPlayListEntity {
    private DataBean data;
    private String message;
    private String result;
    private Long time;

    public static class DataBean {
        private List<VideoListBean> videoList;

        public static class VideoListBean {
            private String audioType;
            private Boolean isDefault;
            private String rate;
            private String rateName;
            private Integer rateValue;
            private String url;
            private String videoType;

            public String getAudioType() {
                return this.audioType;
            }

            public Boolean getIsDefault() {
                return this.isDefault;
            }

            public String getRate() {
                return this.rate;
            }

            public String getRateName() {
                return this.rateName;
            }

            public Integer getRateValue() {
                return this.rateValue;
            }

            public String getUrl() {
                return this.url;
            }

            public String getVideoType() {
                return this.videoType;
            }

            public void setAudioType(String str) {
                this.audioType = str;
            }

            public void setIsDefault(Boolean bool) {
                this.isDefault = bool;
            }

            public void setRate(String str) {
                this.rate = str;
            }

            public void setRateName(String str) {
                this.rateName = str;
            }

            public void setRateValue(Integer num) {
                this.rateValue = num;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public void setVideoType(String str) {
                this.videoType = str;
            }
        }

        public List<VideoListBean> getVideoList() {
            return this.videoList;
        }

        public void setVideoList(List<VideoListBean> list) {
            this.videoList = list;
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
