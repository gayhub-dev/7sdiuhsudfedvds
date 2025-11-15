package org.fourthline.cling.model.types;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.logging.Logger;
import org.fourthline.cling.model.ModelUtil;
import p009b.C0413b;

/* loaded from: classes.dex */
public class UDN {
    public static final String PREFIX = "uuid:";
    private static final Logger log = Logger.getLogger(UDN.class.getName());
    private String identifierString;

    public UDN(String str) {
        this.identifierString = str;
    }

    public static UDN uniqueSystemIdentifier(String str) {
        StringBuilder sb = new StringBuilder();
        if (ModelUtil.ANDROID_RUNTIME) {
            throw new RuntimeException("This method does not create a unique identifier on Android, see the Javadoc and use new UDN(UUID) instead!");
        }
        try {
            sb.append(new String(ModelUtil.getFirstNetworkInterfaceHardwareAddress(), "UTF-8"));
            try {
                return new UDN(new UUID(new BigInteger(-1, MessageDigest.getInstance("MD5").digest(sb.toString().getBytes("UTF-8"))).longValue(), str.hashCode()));
            } catch (Exception e7) {
                throw new RuntimeException(e7);
            }
        } catch (UnsupportedEncodingException e8) {
            throw new RuntimeException(e8);
        }
    }

    public static UDN valueOf(String str) {
        if (str.startsWith("uuid:")) {
            str = str.substring(5);
        }
        return new UDN(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UDN)) {
            return false;
        }
        return this.identifierString.equals(((UDN) obj).identifierString);
    }

    public String getIdentifierString() {
        return this.identifierString;
    }

    public int hashCode() {
        return this.identifierString.hashCode();
    }

    public boolean isUDA11Compliant() {
        try {
            UUID.fromString(this.identifierString);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("uuid:");
        sbM112a.append(getIdentifierString());
        return sbM112a.toString();
    }

    public UDN(UUID uuid) {
        this.identifierString = uuid.toString();
    }
}
