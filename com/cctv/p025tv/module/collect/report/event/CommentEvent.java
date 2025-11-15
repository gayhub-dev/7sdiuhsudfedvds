package com.cctv.p025tv.module.collect.report.event;

import android.os.Build;
import org.json.JSONException;
import org.json.JSONObject;
import p043f.C0988e;
import p127p1.C1735a;

/* loaded from: classes.dex */
public class CommentEvent {
    private String android_id;
    private String app_channel;
    private String app_key;
    private String app_language;
    private String app_version;
    private String cctv_id;
    private String data_time;
    private String device_board;
    private String device_brand;
    private String device_builder_type;
    private String device_display;
    private String device_fingerprint;
    private String device_hardware;
    private String device_host;
    private String device_id;
    private String device_manufacturer;
    private String device_model;
    private String device_params;
    private String device_product;
    private String device_resolution;
    private String device_tags;
    private String device_type;
    private String device_user;
    private String device_version_id;
    private String idfa;
    private String idfv;
    private String imei;
    private String mac;
    private String os_version;
    private String sdk_version;
    private String system_type;
    private String user_id;

    public String getAndroid_id() {
        String str = this.android_id;
        return str == null ? "" : str;
    }

    public String getApp_channel() {
        String str = this.app_channel;
        return str == null ? "" : str;
    }

    public String getApp_key() {
        String str = this.app_key;
        return str == null ? "" : str;
    }

    public String getApp_language() {
        String str = this.app_language;
        return str == null ? "" : str;
    }

    public String getApp_version() {
        String str = this.app_version;
        return str == null ? "" : str;
    }

    public String getCctv_id() {
        String str = this.cctv_id;
        return str == null ? "" : str;
    }

    public String getData_time() {
        String str = this.data_time;
        return str == null ? "" : str;
    }

    public String getDevice_board() {
        String str = this.device_board;
        return str == null ? "" : str;
    }

    public String getDevice_brand() {
        String str = this.device_brand;
        return str == null ? "" : str;
    }

    public String getDevice_builder_type() {
        String str = this.device_builder_type;
        return str == null ? "" : str;
    }

    public String getDevice_display() {
        String str = this.device_display;
        return str == null ? "" : str;
    }

    public String getDevice_fingerprint() {
        String str = this.device_fingerprint;
        return str == null ? "" : str;
    }

    public String getDevice_hardware() {
        String str = this.device_hardware;
        return str == null ? "" : str;
    }

    public String getDevice_host() {
        String str = this.device_host;
        return str == null ? "" : str;
    }

    public String getDevice_id() {
        String str = this.device_id;
        return str == null ? "" : str;
    }

    public String getDevice_manufacturer() {
        String str = this.device_manufacturer;
        return str == null ? "" : str;
    }

    public String getDevice_model() {
        String str = this.device_model;
        return str == null ? "" : str;
    }

    public String getDevice_params() {
        String str = this.device_params;
        return str == null ? "" : str;
    }

    public String getDevice_product() {
        String str = this.device_product;
        return str == null ? "" : str;
    }

    public String getDevice_resolution() {
        String str = this.device_resolution;
        return str == null ? "" : str;
    }

    public String getDevice_tags() {
        String str = this.device_tags;
        return str == null ? "" : str;
    }

    public String getDevice_type() {
        String str = this.device_type;
        return str == null ? "" : str;
    }

    public String getDevice_user() {
        String str = this.device_user;
        return str == null ? "" : str;
    }

    public String getDevice_version_id() {
        String str = this.device_version_id;
        return str == null ? "" : str;
    }

    public String getIdfa() {
        String str = this.idfa;
        return str == null ? "" : str;
    }

    public String getIdfv() {
        String str = this.idfv;
        return str == null ? "" : str;
    }

    public String getImei() {
        String str = this.imei;
        return str == null ? "" : str;
    }

    public String getMac() {
        String str = this.mac;
        return str == null ? "" : str;
    }

    public String getOs_version() {
        String str = this.os_version;
        return str == null ? "" : str;
    }

    public String getSdk_version() {
        String str = this.sdk_version;
        return str == null ? "" : str;
    }

    public String getSystem_type() {
        String str = this.system_type;
        return str == null ? "" : str;
    }

    public String getUser_id() {
        String str = this.user_id;
        return str == null ? "" : str;
    }

    public void setAndroid_id(String str) {
        this.android_id = str;
    }

    public void setApp_channel(String str) {
        this.app_channel = str;
    }

    public void setApp_key(String str) {
        this.app_key = str;
    }

    public void setApp_language(String str) {
        this.app_language = str;
    }

    public void setApp_version(String str) {
        this.app_version = str;
    }

    public void setCctv_id(String str) {
        this.cctv_id = str;
    }

    public void setCommentEvent(JSONObject jSONObject) throws JSONException {
        jSONObject.put("cctv_id", C1735a.m1886j(C0988e.m987m(C0988e.f1828f), 64));
        jSONObject.put("device_id", C1735a.m1886j(C0988e.m992r(C0988e.f1828f), 64));
        jSONObject.put("idfa", getIdfa());
        jSONObject.put("idfv", getIdfv());
        jSONObject.put("user_id", C1735a.m1886j(null, 64));
        jSONObject.put("app_key", C1735a.m1886j(C0988e.f1826d, 64));
        jSONObject.put("imei", C1735a.m1886j("", 64));
        jSONObject.put("android_id", C1735a.m1886j(C0988e.m986l(C0988e.f1828f), 64));
        jSONObject.put("mac", C1735a.m1886j(C1735a.m1879c(C0988e.f1828f), 64));
        jSONObject.put("device_builder_type", C1735a.m1886j(Build.TYPE, 64));
        jSONObject.put("device_hardware", C1735a.m1886j(Build.HARDWARE, 64));
        jSONObject.put("device_board", C1735a.m1886j(Build.BOARD, 64));
        jSONObject.put("device_brand", C1735a.m1886j(Build.BRAND, 64));
        jSONObject.put("device_params", C1735a.m1886j(Build.DEVICE, 64));
        jSONObject.put("device_display", C1735a.m1886j(Build.DISPLAY, 64));
        jSONObject.put("device_version_id", C1735a.m1886j(Build.ID, 64));
        jSONObject.put("device_host", C1735a.m1886j(Build.HOST, 128));
        jSONObject.put("device_product", C1735a.m1886j(Build.PRODUCT, 64));
        jSONObject.put("device_tags", C1735a.m1886j(Build.TAGS, 64));
        jSONObject.put("device_user", C1735a.m1886j(Build.USER, 30));
        jSONObject.put("device_fingerprint", C1735a.m1886j(Build.FINGERPRINT, 128));
        jSONObject.put("device_manufacturer", C1735a.m1886j(C1735a.m1880d(), 64));
        jSONObject.put("device_model", C1735a.m1886j(C1735a.m1883g(), 50));
        jSONObject.put("device_resolution", C1735a.m1886j(C1735a.m1884h(C0988e.f1828f), 20));
        jSONObject.put("system_type", C1735a.m1886j(C1735a.m1885i(), 24));
        int i7 = C1735a.f4920a;
        jSONObject.put("device_type", C1735a.m1886j("TV", 24));
        jSONObject.put("app_language", C1735a.m1886j("CHINESE", 24));
        jSONObject.put("app_version", C1735a.m1886j(C1735a.m1877a(C0988e.f1828f), 30));
        jSONObject.put("sdk_version", C1735a.m1886j("", 30));
        jSONObject.put("os_version", C1735a.m1886j(C1735a.m1882f(), 20));
        jSONObject.put("app_channel", C1735a.m1886j(C0988e.f1827e, 50));
        jSONObject.put("data_time", C1735a.m1886j(C1735a.m1878b(), 13));
    }

    public void setData_time(String str) {
        this.data_time = str;
    }

    public void setDevice_board(String str) {
        this.device_board = str;
    }

    public void setDevice_brand(String str) {
        this.device_brand = str;
    }

    public void setDevice_builder_type(String str) {
        this.device_builder_type = str;
    }

    public void setDevice_display(String str) {
        this.device_display = str;
    }

    public void setDevice_fingerprint(String str) {
        this.device_fingerprint = str;
    }

    public void setDevice_hardware(String str) {
        this.device_hardware = str;
    }

    public void setDevice_host(String str) {
        this.device_host = str;
    }

    public void setDevice_id(String str) {
        this.device_id = str;
    }

    public void setDevice_manufacturer(String str) {
        this.device_manufacturer = str;
    }

    public void setDevice_model(String str) {
        this.device_model = str;
    }

    public void setDevice_params(String str) {
        this.device_params = str;
    }

    public void setDevice_product(String str) {
        this.device_product = str;
    }

    public void setDevice_resolution(String str) {
        this.device_resolution = str;
    }

    public void setDevice_tags(String str) {
        this.device_tags = str;
    }

    public void setDevice_type(String str) {
        this.device_type = str;
    }

    public void setDevice_user(String str) {
        this.device_user = str;
    }

    public void setDevice_version_id(String str) {
        this.device_version_id = str;
    }

    public void setIdfa(String str) {
        this.idfa = str;
    }

    public void setIdfv(String str) {
        this.idfv = str;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setOs_version(String str) {
        this.os_version = str;
    }

    public void setSdk_version(String str) {
        this.sdk_version = str;
    }

    public void setSystem_type(String str) {
        this.system_type = str;
    }

    public void setUser_id(String str) {
        this.user_id = str;
    }
}
