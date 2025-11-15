package com.cctv.p025tv.module.collect.report.event;

import org.json.JSONException;
import org.json.JSONObject;
import p009b.C0413b;
import p127p1.C1735a;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class ReportPageEvent implements toJson {
    private PageD1Bean page_d1;

    public static class PageD1Bean extends CommentEvent {
        private String duration;
        private String end_time;
        private String network_type;
        private String page_name;
        private String session_id;
        private String start_time;

        public String getDuration() {
            String str = this.duration;
            return str == null ? "" : str;
        }

        public String getEnd_time() {
            String str = this.end_time;
            return str == null ? "" : str;
        }

        public String getNetwork_type() {
            String str = this.network_type;
            return str == null ? "" : str;
        }

        public String getPage_name() {
            String str = this.page_name;
            return str == null ? "" : str;
        }

        public String getSession_id() {
            String str = this.session_id;
            return str == null ? "" : str;
        }

        public String getStart_time() {
            String str = this.start_time;
            return str == null ? "" : str;
        }

        public void setDuration(String str) {
            this.duration = str;
        }

        public void setEnd_time(String str) {
            this.end_time = str;
        }

        public void setNetwork_type(String str) {
            this.network_type = str;
        }

        public void setPage_name(String str) {
            this.page_name = str;
        }

        public void setSession_id(String str) {
            this.session_id = str;
        }

        public void setStart_time(String str) {
            this.start_time = str;
        }

        public JSONObject toJson() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                setCommentEvent(jSONObject);
                jSONObject.put("start_time", C1735a.m1886j(getStart_time(), 13));
                jSONObject.put("end_time", C1735a.m1886j(getEnd_time(), 13));
                jSONObject.put("duration", C1735a.m1886j(getDuration(), 13));
                jSONObject.put("page_name", C1735a.m1886j(getPage_name(), 256));
                jSONObject.put("session_id", C1735a.m1886j(getSession_id(), 64));
                jSONObject.put("network_type", C1735a.m1886j(getNetwork_type(), 10));
            } catch (JSONException e7) {
                StringBuilder sbM112a = C0413b.m112a("report ReportPageEvent.PageD1Bean toJson e = ");
                sbM112a.append(e7.getMessage());
                C2073a.m2456a(sbM112a.toString());
            }
            return jSONObject;
        }
    }

    public PageD1Bean getPage_d1() {
        return this.page_d1;
    }

    public void setPage_d1(PageD1Bean pageD1Bean) {
        this.page_d1 = pageD1Bean;
    }

    @Override // com.cctv.p025tv.module.collect.report.event.toJson
    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", "page_d1");
            jSONObject.put("value", getPage_d1().toJson());
        } catch (JSONException e7) {
            StringBuilder sbM112a = C0413b.m112a("report ReportPageEvent toJson e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
        }
        return jSONObject.toString();
    }
}
