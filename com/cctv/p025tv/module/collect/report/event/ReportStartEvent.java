package com.cctv.p025tv.module.collect.report.event;

import android.content.Context;
import android.os.Build;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import p009b.C0413b;
import p043f.C0988e;
import p117o1.C1579a;
import p127p1.C1735a;
import p135q1.C1760c;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class ReportStartEvent implements toJson {
    private AppStartD1Bean app_start_d1;

    public static class AppStartD1Bean extends CommentEvent {
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

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                setCommentEvent(jSONObject);
            } catch (JSONException e7) {
                StringBuilder sbM112a = C0413b.m112a("report AppStartD1Bean toJson e = ");
                sbM112a.append(e7.getMessage());
                C2073a.m2456a(sbM112a.toString());
            }
            return jSONObject;
        }
    }

    public static void post(Context context) {
        ReportStartEvent reportStartEvent = new ReportStartEvent();
        reportStartEvent.setApp_start_d1(AppStartD1Bean.create(context));
        C1760c.m1927a(reportStartEvent.toJson());
        String strM1877a = C1735a.m1877a(context);
        HashMap map = new HashMap();
        map.put("cur_version", strM1877a);
        map.put("channel", C0988e.f1827e);
        map.put("pre_version", strM1877a);
        C1579a.m1830a().m1831b("app_start", "APP启动", map);
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
            jSONObject.put("key", "app_start_d1");
            jSONObject.put("value", getApp_start_d1().toJson());
        } catch (JSONException e7) {
            StringBuilder sbM112a = C0413b.m112a("report ReportStartEvent toJson e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
        }
        return jSONObject.toString();
    }
}
