package com.ctvit.network.cookie;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/* loaded from: classes.dex */
public class CookieManger implements CookieJar {
    private static PersistentCookieStore cookieStore;
    private static Context mContext;

    public CookieManger(Context context) {
        mContext = context;
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(context);
        }
    }

    public void addCookies(List<Cookie> list) {
        cookieStore.addCookies(list);
    }

    public PersistentCookieStore getCookieStore() {
        return cookieStore;
    }

    @Override // okhttp3.CookieJar
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> list = cookieStore.get(httpUrl);
        return list != null ? list : new ArrayList();
    }

    public void remove(HttpUrl httpUrl, Cookie cookie) {
        cookieStore.remove(httpUrl, cookie);
    }

    public void removeAll() {
        cookieStore.removeAll();
    }

    public void saveFromResponse(HttpUrl httpUrl, Cookie cookie) {
        if (cookie != null) {
            cookieStore.add(httpUrl, cookie);
        }
    }

    @Override // okhttp3.CookieJar
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<Cookie> it = list.iterator();
        while (it.hasNext()) {
            cookieStore.add(httpUrl, it.next());
        }
    }
}
