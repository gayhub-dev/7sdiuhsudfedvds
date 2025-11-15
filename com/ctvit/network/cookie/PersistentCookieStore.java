package com.ctvit.network.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import p009b.C0413b;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class PersistentCookieStore {
    private static final String COOKIE_PREFS = "Ctvit_Cookies_Prefs";
    private final SharedPreferences cookiePrefs;
    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies;

    public PersistentCookieStore(Context context) {
        Cookie cookieDecodeCookie;
        SharedPreferences sharedPreferences = context.getSharedPreferences(COOKIE_PREFS, 0);
        this.cookiePrefs = sharedPreferences;
        this.cookies = new HashMap();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            for (String str : TextUtils.split((String) entry.getValue(), ",")) {
                String string = this.cookiePrefs.getString(str, null);
                if (string != null && (cookieDecodeCookie = decodeCookie(string)) != null) {
                    if (!this.cookies.containsKey(entry.getKey())) {
                        this.cookies.put(entry.getKey(), new ConcurrentHashMap<>());
                    }
                    this.cookies.get(entry.getKey()).put(str, cookieDecodeCookie);
                }
            }
        }
    }

    public void add(HttpUrl httpUrl, Cookie cookie) {
        String cookieToken = getCookieToken(cookie);
        if (!this.cookies.containsKey(httpUrl.host())) {
            this.cookies.put(httpUrl.host(), new ConcurrentHashMap<>());
        }
        if (this.cookies.containsKey(httpUrl.host())) {
            this.cookies.get(httpUrl.host()).remove(cookieToken);
        }
        this.cookies.get(httpUrl.host()).put(cookieToken, cookie);
        if (cookie.persistent()) {
            SharedPreferences.Editor editorEdit = this.cookiePrefs.edit();
            editorEdit.putString(httpUrl.host(), TextUtils.join(",", this.cookies.get(httpUrl.host()).keySet()));
            editorEdit.putString(cookieToken, encodeCookie(new SerializableOkHttpCookies(cookie)));
            editorEdit.apply();
            return;
        }
        SharedPreferences.Editor editorEdit2 = this.cookiePrefs.edit();
        editorEdit2.remove(httpUrl.host());
        editorEdit2.remove(cookieToken);
        editorEdit2.apply();
    }

    public void addCookies(List<Cookie> list) {
        Iterator<Cookie> it = list.iterator();
        while (it.hasNext()) {
            String strDomain = it.next().domain();
            if (this.cookies.get(strDomain) == null) {
                this.cookies.put(strDomain, new ConcurrentHashMap<>());
            }
        }
    }

    public String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b7 : bArr) {
            int i7 = b7 & 255;
            if (i7 < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i7));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    public Cookie decodeCookie(String str) {
        try {
            return ((SerializableOkHttpCookies) new ObjectInputStream(new ByteArrayInputStream(hexStringToByteArray(str))).readObject()).getCookies();
        } catch (IOException e7) {
            StringBuilder sbM112a = C0413b.m112a("IOException in decodeCookie");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
            return null;
        } catch (ClassNotFoundException e8) {
            StringBuilder sbM112a2 = C0413b.m112a("ClassNotFoundException in decodeCookie");
            sbM112a2.append(e8.getMessage());
            C2073a.m2456a(sbM112a2.toString());
            return null;
        }
    }

    public String encodeCookie(SerializableOkHttpCookies serializableOkHttpCookies) throws IOException {
        if (serializableOkHttpCookies == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableOkHttpCookies);
            return byteArrayToHexString(byteArrayOutputStream.toByteArray());
        } catch (IOException e7) {
            StringBuilder sbM112a = C0413b.m112a("IOException in encodeCookie");
            sbM112a.append(e7.getMessage());
            C2073a.m2459d(sbM112a.toString());
            return null;
        }
    }

    public List<Cookie> get(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        if (this.cookies.containsKey(httpUrl.host())) {
            arrayList.addAll(this.cookies.get(httpUrl.host()).values());
        }
        return arrayList;
    }

    public String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public List<Cookie> getCookies() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.cookies.keySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(this.cookies.get(it.next()).values());
        }
        return arrayList;
    }

    public byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i7 = 0; i7 < length; i7 += 2) {
            bArr[i7 / 2] = (byte) (Character.digit(str.charAt(i7 + 1), 16) + (Character.digit(str.charAt(i7), 16) << 4));
        }
        return bArr;
    }

    public boolean remove(HttpUrl httpUrl, Cookie cookie) {
        String cookieToken = getCookieToken(cookie);
        if (!this.cookies.containsKey(httpUrl.host()) || !this.cookies.get(httpUrl.host()).containsKey(cookieToken)) {
            return false;
        }
        this.cookies.get(httpUrl.host()).remove(cookieToken);
        SharedPreferences.Editor editorEdit = this.cookiePrefs.edit();
        if (this.cookiePrefs.contains(cookieToken)) {
            editorEdit.remove(cookieToken);
        }
        editorEdit.putString(httpUrl.host(), TextUtils.join(",", this.cookies.get(httpUrl.host()).keySet()));
        editorEdit.apply();
        return true;
    }

    public boolean removeAll() {
        SharedPreferences.Editor editorEdit = this.cookiePrefs.edit();
        editorEdit.clear();
        editorEdit.apply();
        this.cookies.clear();
        return true;
    }
}
