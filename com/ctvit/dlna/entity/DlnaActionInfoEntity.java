package com.ctvit.dlna.entity;

/* loaded from: classes.dex */
public class DlnaActionInfoEntity {
    private String dlna_type;
    private int http_status;
    private String request_body;
    private String response_body;
    private String stack_trace;

    public String getDlna_type() {
        return this.dlna_type;
    }

    public int getHttp_status() {
        return this.http_status;
    }

    public String getRequest_body() {
        return this.request_body;
    }

    public String getResponse_body() {
        return this.response_body;
    }

    public String getStack_trace() {
        return this.stack_trace;
    }

    public void setDlna_type(String str) {
        this.dlna_type = str;
    }

    public void setHttp_status(int i7) {
        this.http_status = i7;
    }

    public void setRequest_body(String str) {
        this.request_body = str;
    }

    public void setResponse_body(String str) {
        this.response_body = str;
    }

    public void setStack_trace(String str) {
        this.stack_trace = str;
    }
}
