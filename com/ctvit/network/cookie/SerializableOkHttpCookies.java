package com.ctvit.network.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import okhttp3.Cookie;

/* loaded from: classes.dex */
public class SerializableOkHttpCookies implements Serializable {
    private transient Cookie clientCookies;
    private final transient Cookie cookies;

    public SerializableOkHttpCookies(Cookie cookie) {
        this.cookies = cookie;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        String str = (String) objectInputStream.readObject();
        String str2 = (String) objectInputStream.readObject();
        long j7 = objectInputStream.readLong();
        String str3 = (String) objectInputStream.readObject();
        String str4 = (String) objectInputStream.readObject();
        boolean z6 = objectInputStream.readBoolean();
        boolean z7 = objectInputStream.readBoolean();
        boolean z8 = objectInputStream.readBoolean();
        Cookie.Builder builderExpiresAt = new Cookie.Builder().name(str).value(str2).expiresAt(j7);
        Cookie.Builder builderPath = (z8 ? builderExpiresAt.hostOnlyDomain(str3) : builderExpiresAt.domain(str3)).path(str4);
        if (z6) {
            builderPath = builderPath.secure();
        }
        if (z7) {
            builderPath = builderPath.httpOnly();
        }
        this.clientCookies = builderPath.build();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.cookies.name());
        objectOutputStream.writeObject(this.cookies.value());
        objectOutputStream.writeLong(this.cookies.expiresAt());
        objectOutputStream.writeObject(this.cookies.domain());
        objectOutputStream.writeObject(this.cookies.path());
        objectOutputStream.writeBoolean(this.cookies.secure());
        objectOutputStream.writeBoolean(this.cookies.httpOnly());
        objectOutputStream.writeBoolean(this.cookies.hostOnly());
        objectOutputStream.writeBoolean(this.cookies.persistent());
    }

    public Cookie getCookies() {
        Cookie cookie = this.cookies;
        Cookie cookie2 = this.clientCookies;
        return cookie2 != null ? cookie2 : cookie;
    }
}
