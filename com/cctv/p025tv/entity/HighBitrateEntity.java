package com.cctv.p025tv.entity;

import java.util.List;

/* loaded from: classes.dex */
public class HighBitrateEntity {
    private DataBean data;
    private String message;
    private String result;
    private Long time;

    public static class DataBean {
        private int duration;
        private String encrypt;
        private String endtime;
        private boolean hdrVividSwitch;

        /* renamed from: id */
        private String f579id;
        private String image;
        private LogoConfigBean logoConfig;
        private String logoImg;
        private String player;
        private String realUrl;
        private String starttime;
        private String title;
        private List<VideoListBean> videoList;

        public static class LogoConfigBean {
            private int height;
            private String position;
            private int width;

            public int getHeight() {
                return this.height;
            }

            public String getPosition() {
                return this.position;
            }

            public int getWidth() {
                return this.width;
            }

            public void setHeight(int i7) {
                this.height = i7;
            }

            public void setPosition(String str) {
                this.position = str;
            }

            public void setWidth(int i7) {
                this.width = i7;
            }
        }

        public static class VideoListBean {
            private String audioType;
            private boolean isDefault;
            private List<LogosBean> logos;
            private String rate;
            private String rateName;
            private int rateValue;
            private String url;
            private String urlExt;
            private String videoType;

            public static class LogosBean {
                private Integer height;
                private String horizontalBias;
                private String logoImg;
                private String percentHeight;
                private String percentWidth;
                private String type;
                private String verticalBias;
                private Integer width;

                public Integer getHeight() {
                    return this.height;
                }

                public String getHorizontalBias() {
                    return this.horizontalBias;
                }

                public String getLogoImg() {
                    return this.logoImg;
                }

                public String getPercentHeight() {
                    return this.percentHeight;
                }

                public String getPercentWidth() {
                    return this.percentWidth;
                }

                public String getType() {
                    return this.type;
                }

                public String getVerticalBias() {
                    return this.verticalBias;
                }

                public Integer getWidth() {
                    return this.width;
                }

                public void setHeight(Integer num) {
                    this.height = num;
                }

                public void setHorizontalBias(String str) {
                    this.horizontalBias = str;
                }

                public void setLogoImg(String str) {
                    this.logoImg = str;
                }

                public void setPercentHeight(String str) {
                    this.percentHeight = str;
                }

                public void setPercentWidth(String str) {
                    this.percentWidth = str;
                }

                public void setType(String str) {
                    this.type = str;
                }

                public void setVerticalBias(String str) {
                    this.verticalBias = str;
                }

                public void setWidth(Integer num) {
                    this.width = num;
                }
            }

            public String getAudioType() {
                return this.audioType;
            }

            public List<LogosBean> getLogos() {
                return this.logos;
            }

            public String getRate() {
                return this.rate;
            }

            public String getRateName() {
                return this.rateName;
            }

            public int getRateValue() {
                return this.rateValue;
            }

            public String getUrl() {
                return this.url;
            }

            public String getUrlExt() {
                return this.urlExt;
            }

            public String getVideoType() {
                return this.videoType;
            }

            public boolean isIsDefault() {
                return this.isDefault;
            }

            public void setAudioType(String str) {
                this.audioType = str;
            }

            public void setIsDefault(boolean z6) {
                this.isDefault = z6;
            }

            public void setLogos(List<LogosBean> list) {
                this.logos = list;
            }

            public void setRate(String str) {
                this.rate = str;
            }

            public void setRateName(String str) {
                this.rateName = str;
            }

            public void setRateValue(int i7) {
                this.rateValue = i7;
            }

            public void setUrl(String str) {
                this.url = str;
            }

            public void setUrlExt(String str) {
                this.urlExt = str;
            }

            public void setVideoType(String str) {
                this.videoType = str;
            }
        }

        public int getDuration() {
            return this.duration;
        }

        public String getEncrypt() {
            return this.encrypt;
        }

        public String getEndtime() {
            return this.endtime;
        }

        public String getId() {
            return this.f579id;
        }

        public String getImage() {
            return this.image;
        }

        public LogoConfigBean getLogoConfig() {
            return this.logoConfig;
        }

        public String getLogoImg() {
            return this.logoImg;
        }

        public String getPlayer() {
            return this.player;
        }

        public String getRealUrl() {
            return this.realUrl;
        }

        public String getStarttime() {
            return this.starttime;
        }

        public String getTitle() {
            return this.title;
        }

        public List<VideoListBean> getVideoList() {
            return this.videoList;
        }

        public boolean isHdrVividSwitch() {
            return this.hdrVividSwitch;
        }

        public void setDuration(int i7) {
            this.duration = i7;
        }

        public void setEncrypt(String str) {
            this.encrypt = str;
        }

        public void setEndtime(String str) {
            this.endtime = str;
        }

        public void setHdrVividSwitch(boolean z6) {
            this.hdrVividSwitch = z6;
        }

        public void setId(String str) {
            this.f579id = str;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public void setLogoConfig(LogoConfigBean logoConfigBean) {
            this.logoConfig = logoConfigBean;
        }

        public void setLogoImg(String str) {
            this.logoImg = str;
        }

        public void setPlayer(String str) {
            this.player = str;
        }

        public void setRealUrl(String str) {
            this.realUrl = str;
        }

        public void setStarttime(String str) {
            this.starttime = str;
        }

        public void setTitle(String str) {
            this.title = str;
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
