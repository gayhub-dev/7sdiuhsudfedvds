package com.cctv.p025tv.module.collect.report.event;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p009b.C0413b;
import p127p1.C1735a;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class ReportCustomEvent extends CommentEvent implements toJson {
    private String event_id;
    private String event_name;
    private String event_time;
    private Map<String, String> map;
    private String network_type;

    public String getEvent_id() {
        String str = this.event_id;
        return str == null ? "" : str;
    }

    public String getEvent_name() {
        String str = this.event_name;
        return str == null ? "" : str;
    }

    public String getEvent_time() {
        String str = this.event_time;
        return str == null ? "" : str;
    }

    public Map<String, String> getMap() {
        return this.map;
    }

    public String getNetwork_type() {
        String str = this.network_type;
        return str == null ? "" : str;
    }

    public void setEvent_id(String str) {
        this.event_id = str;
    }

    public void setEvent_name(String str) {
        this.event_name = str;
    }

    public void setEvent_time(String str) {
        this.event_time = str;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setNetwork_type(String str) {
        this.network_type = str;
    }

    @Override // com.cctv.p025tv.module.collect.report.event.toJson
    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            setCommentEvent(jSONObject);
            jSONObject.put("event_id", C1735a.m1886j(getEvent_id(), 256));
            jSONObject.put("event_name", C1735a.m1886j(getEvent_name(), 64));
            jSONObject.put("event_time", C1735a.m1886j(getEvent_time(), 13));
            jSONObject.put("network_type", C1735a.m1886j(getNetwork_type(), 10));
            if (getMap() != null && getMap().size() > 0) {
                for (String str : getMap().keySet()) {
                    try {
                        if (TextUtils.isEmpty(getMap().get(str))) {
                            jSONObject.put(C1735a.m1886j(str, 50), "");
                        } else {
                            jSONObject.put(C1735a.m1886j(str, 50), C1735a.m1886j(getMap().get(str).replace("\"", "'"), 1024));
                        }
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                }
            }
        } catch (JSONException e8) {
            StringBuilder sbM112a = C0413b.m112a("report ReportCustomEvent toJson 1 e = ");
            sbM112a.append(e8.getMessage());
            C2073a.m2456a(sbM112a.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("key", NotificationCompat.CATEGORY_EVENT);
            jSONObject2.put("value", jSONObject);
        } catch (JSONException e9) {
            StringBuilder sbM112a2 = C0413b.m112a("report ReportCustomEvent toJson 2 e = ");
            sbM112a2.append(e9.getMessage());
            C2073a.m2456a(sbM112a2.toString());
        }
        return jSONObject2.toString();
    }
}
