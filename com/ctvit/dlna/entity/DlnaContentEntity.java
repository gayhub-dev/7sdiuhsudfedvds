package com.ctvit.dlna.entity;

import android.text.TextUtils;
import java.io.Serializable;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DlnaContentEntity implements Serializable {
    private String cctv;
    private String creator;

    /* renamed from: id */
    private String f957id;
    private String name;
    private String protocolInfo;
    private String url;

    public String getCctv() {
        return this.cctv;
    }

    public String getCreator() {
        return this.creator;
    }

    public String getId() {
        return this.f957id;
    }

    public String getName() {
        return this.name;
    }

    public String getProtocolInfo() {
        return this.protocolInfo;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isHasCctv() {
        return (TextUtils.isEmpty(getCctv()) || TextUtils.isEmpty(getUrl())) ? false : true;
    }

    public boolean isOnlyEventCctv() {
        return !TextUtils.isEmpty(getCctv()) && TextUtils.isEmpty(getUrl()) && TextUtils.isEmpty(getCreator()) && TextUtils.isEmpty(getName()) && TextUtils.isEmpty(getId());
    }

    public void setCctv(String str) {
        this.cctv = str;
    }

    public void setCreator(String str) {
        this.creator = str;
    }

    public void setId(String str) {
        this.f957id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProtocolInfo(String str) {
        this.protocolInfo = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("DlnaContentEntity{url='");
        sbM112a.append(this.url);
        sbM112a.append('\'');
        sbM112a.append(", id='");
        sbM112a.append(this.f957id);
        sbM112a.append('\'');
        sbM112a.append(", name='");
        sbM112a.append(this.name);
        sbM112a.append('\'');
        sbM112a.append(", creator='");
        sbM112a.append(this.creator);
        sbM112a.append('\'');
        sbM112a.append(", cctv='");
        sbM112a.append(this.cctv);
        sbM112a.append('\'');
        sbM112a.append(", protocolInfo='");
        sbM112a.append(this.protocolInfo);
        sbM112a.append('\'');
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
