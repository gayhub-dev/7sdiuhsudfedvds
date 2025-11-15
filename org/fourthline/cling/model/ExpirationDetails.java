package org.fourthline.cling.model;

import java.util.Date;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ExpirationDetails {
    public static final int UNLIMITED_AGE = 0;
    private static String simpleName = "ExpirationDetails";
    private long lastRefreshTimestampSeconds;
    private int maxAgeSeconds;

    public ExpirationDetails() {
        this.maxAgeSeconds = 0;
        this.lastRefreshTimestampSeconds = getCurrentTimestampSeconds();
    }

    public long getCurrentTimestampSeconds() {
        return new Date().getTime() / 1000;
    }

    public long getLastRefreshTimestampSeconds() {
        return this.lastRefreshTimestampSeconds;
    }

    public int getMaxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public long getSecondsUntilExpiration() {
        int i7 = this.maxAgeSeconds;
        if (i7 == 0) {
            return 2147483647L;
        }
        return (this.lastRefreshTimestampSeconds + i7) - getCurrentTimestampSeconds();
    }

    public boolean hasExpired() {
        return hasExpired(false);
    }

    public void setLastRefreshTimestampSeconds(long j7) {
        this.lastRefreshTimestampSeconds = j7;
    }

    public void stampLastRefresh() {
        setLastRefreshTimestampSeconds(getCurrentTimestampSeconds());
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(simpleName);
        sbM112a.append(") MAX AGE: ");
        sbM112a.append(this.maxAgeSeconds);
        return sbM112a.toString();
    }

    public boolean hasExpired(boolean z6) {
        if (this.maxAgeSeconds != 0) {
            if (this.lastRefreshTimestampSeconds + (r0 / (z6 ? 2 : 1)) < getCurrentTimestampSeconds()) {
                return true;
            }
        }
        return false;
    }

    public ExpirationDetails(int i7) {
        this.maxAgeSeconds = 0;
        this.lastRefreshTimestampSeconds = getCurrentTimestampSeconds();
        this.maxAgeSeconds = i7;
    }
}
