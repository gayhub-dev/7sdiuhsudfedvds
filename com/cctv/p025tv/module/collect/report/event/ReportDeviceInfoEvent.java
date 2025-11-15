package com.cctv.p025tv.module.collect.report.event;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.cctv.p025tv.module.function.guid.TempCloudDevice;
import org.fourthline.cling.model.ServiceReference;
import org.json.JSONException;
import org.json.JSONObject;
import p009b.C0413b;
import p043f.C0988e;
import p078j2.C1194i;
import p078j2.C1195j;
import p127p1.C1735a;
import p135q1.C1760c;
import p179w2.C2025a;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class ReportDeviceInfoEvent implements toJson {
    private AppStartD1Bean app_start_d1;

    public static class AppStartD1Bean extends CommentEvent {
        private String chip_info;
        private String cpu_info;
        private String device_info;
        private String guid;
        private String manufacturer;
        private String memory_info;
        private String network_status;
        private String ram_info;
        private String system_info;
        private String version;

        public static AppStartD1Bean create(Context context) {
            AppStartD1Bean appStartD1Bean = new AppStartD1Bean();
            appStartD1Bean.setCctv_id(C0988e.m987m(context));
            appStartD1Bean.setDevice_id(C0988e.m992r(context));
            appStartD1Bean.setUser_id(null);
            appStartD1Bean.setApp_key(C0988e.f1826d);
            appStartD1Bean.setImei("");
            appStartD1Bean.setAndroid_id(C0988e.m986l(context));
            appStartD1Bean.setMac(C1735a.m1879c(context));
            appStartD1Bean.setDevice_board(Build.BOARD);
            appStartD1Bean.setDevice_brand(Build.BRAND);
            appStartD1Bean.setDevice_display(Build.DISPLAY);
            String str = Build.TYPE;
            appStartD1Bean.setDevice_builder_type(str);
            appStartD1Bean.setDevice_fingerprint(Build.FINGERPRINT);
            appStartD1Bean.setDevice_version_id(Build.ID);
            appStartD1Bean.setDevice_hardware(Build.HARDWARE);
            appStartD1Bean.setDevice_user(Build.USER);
            appStartD1Bean.setDevice_product(Build.PRODUCT);
            appStartD1Bean.setDevice_type(str);
            appStartD1Bean.setDevice_tags(Build.TAGS);
            appStartD1Bean.setDevice_host(Build.HOST);
            appStartD1Bean.setDevice_manufacturer(C1735a.m1880d());
            appStartD1Bean.setDevice_model(C1735a.m1883g());
            appStartD1Bean.setDevice_resolution(C1735a.m1884h(context));
            appStartD1Bean.setSystem_type(C1735a.m1885i());
            int i7 = C1735a.f4920a;
            appStartD1Bean.setDevice_type("TV");
            appStartD1Bean.setApp_language("CHINESE");
            appStartD1Bean.setApp_version(C1735a.m1877a(context));
            appStartD1Bean.setSdk_version("");
            appStartD1Bean.setOs_version(C1735a.m1882f());
            appStartD1Bean.setApp_channel(C0988e.f1827e);
            appStartD1Bean.setData_time(C1735a.m1878b());
            return appStartD1Bean;
        }

        public String getChip_info() {
            return this.chip_info;
        }

        public String getCpu_info() {
            return this.cpu_info;
        }

        public String getDevice_info() {
            return this.device_info;
        }

        public String getGuid() {
            return this.guid;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public String getMemory_info() {
            return this.memory_info;
        }

        public String getNetwork_status() {
            return this.network_status;
        }

        public String getRam_info() {
            return this.ram_info;
        }

        public String getSystem_info() {
            return this.system_info;
        }

        public String getVersion() {
            return this.version;
        }

        public void setChip_info(String str) {
            this.chip_info = str;
        }

        public void setCpu_info(String str) {
            this.cpu_info = str;
        }

        public void setDevice_info(String str) {
            this.device_info = str;
        }

        public void setGuid(String str) {
            this.guid = str;
        }

        public void setManufacturer(String str) {
            this.manufacturer = str;
        }

        public void setMemory_info(String str) {
            this.memory_info = str;
        }

        public void setNetwork_status(String str) {
            this.network_status = str;
        }

        public void setRam_info(String str) {
            this.ram_info = str;
        }

        public void setSystem_info(String str) {
            this.system_info = str;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public JSONObject toJson() throws Throwable {
            JSONObject jSONObject = new JSONObject();
            try {
                setCommentEvent(jSONObject);
                String strM1418b = C1195j.m1418b();
                String str = TextUtils.isEmpty(strM1418b) ? "未连接" : "ETHERNET".equals(strM1418b) ? "有线网络" : "WiFi";
                String strM991q = C0988e.m991q("Hardware");
                if (strM991q == null) {
                    strM991q = "";
                }
                jSONObject.put("version", C1735a.m1886j(C2025a.m2376d(), 64));
                jSONObject.put("network_status", C1735a.m1886j(str, 64));
                jSONObject.put("device_info", C1735a.m1886j(Build.BRAND + "-" + Build.MODEL, 64));
                jSONObject.put("manufacturer", C1735a.m1886j(Build.MANUFACTURER, 64));
                jSONObject.put("cpu_info", C1735a.m1886j(Runtime.getRuntime().availableProcessors() + "核/" + C0988e.m988n(), 64));
                jSONObject.put("chip_info", C1735a.m1886j(strM991q, 64));
                jSONObject.put("ram_info", C1735a.m1886j(C1194i.m1415c(C0988e.f1828f), 64));
                jSONObject.put("memory_info", C1735a.m1886j(C1194i.m1413a() + "M/" + C1194i.m1416d() + "M", 64));
                StringBuilder sb = new StringBuilder();
                sb.append(Build.VERSION.RELEASE);
                sb.append(ServiceReference.DELIMITER);
                sb.append(Build.VERSION.SDK_INT);
                jSONObject.put("system_info", C1735a.m1886j(sb.toString(), 64));
                jSONObject.put("guid", C1735a.m1886j(TempCloudDevice.getGuid(), 64));
            } catch (JSONException e7) {
                StringBuilder sbM112a = C0413b.m112a("report ReportDeviceInfoEvent.AppStartD1Bean toJson JSONException e = ");
                sbM112a.append(e7.getMessage());
                C2073a.m2456a(sbM112a.toString());
            } catch (Exception e8) {
                StringBuilder sbM112a2 = C0413b.m112a("report ReportDeviceInfoEvent.AppStartD1Bean toJson Exception e = ");
                sbM112a2.append(e8.getMessage());
                C2073a.m2456a(sbM112a2.toString());
            }
            return jSONObject;
        }
    }

    public static void post(Context context) {
        ReportDeviceInfoEvent reportDeviceInfoEvent = new ReportDeviceInfoEvent();
        reportDeviceInfoEvent.setApp_start_d1(AppStartD1Bean.create(context));
        C1760c.m1927a(reportDeviceInfoEvent.toJson());
    }

    public AppStartD1Bean getApp_start_d1() {
        return this.app_start_d1;
    }

    public void setApp_start_d1(AppStartD1Bean appStartD1Bean) {
        this.app_start_d1 = appStartD1Bean;
    }

    @Override // com.cctv.p025tv.module.collect.report.event.toJson
    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", ReportOtherEvent.app_device_info);
            jSONObject.put("value", getApp_start_d1().toJson());
        } catch (JSONException e7) {
            StringBuilder sbM112a = C0413b.m112a("report ReportDeviceInfoEvent toJson e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
        }
        return jSONObject.toString();
    }
}
