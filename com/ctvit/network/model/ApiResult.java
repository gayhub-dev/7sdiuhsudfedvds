package com.ctvit.network.model;

import p009b.C0413b;

/* loaded from: classes.dex */
public class ApiResult<T> {
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean isOk() {
        return this.code == 0;
    }

    public void setCode(int i7) {
        this.code = i7;
    }

    public void setData(T t6) {
        this.data = t6;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("ApiResult{code='");
        sbM112a.append(this.code);
        sbM112a.append('\'');
        sbM112a.append(", msg='");
        sbM112a.append(this.msg);
        sbM112a.append('\'');
        sbM112a.append(", data=");
        sbM112a.append(this.data);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
