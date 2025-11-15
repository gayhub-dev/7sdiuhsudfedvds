package com.ctvit.network.utils;

import com.ctvit.network.model.HttpHeaders;
import p193y2.C2106a;

/* loaded from: classes.dex */
public final class CtvitHttpHeaders {
    private static volatile CtvitHttpHeaders singleton;
    private HttpHeaders headers = new HttpHeaders();
    private String userAgent;

    public static CtvitHttpHeaders getInstance() {
        if (singleton == null) {
            synchronized (CtvitHttpHeaders.class) {
                if (singleton == null) {
                    singleton = new CtvitHttpHeaders();
                }
            }
        }
        return singleton;
    }

    public HttpHeaders headers() {
        if (this.headers.isEmpty()) {
            this.headers.put("UID", C2106a.m2549b());
            this.headers.put("Referer", "api.cctv.cn");
            this.headers.put("User-Agent", this.userAgent);
        }
        return this.headers;
    }

    public CtvitHttpHeaders userAgent(String str) {
        this.userAgent = str;
        return this;
    }
}
