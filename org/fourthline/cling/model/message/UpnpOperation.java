package org.fourthline.cling.model.message;

/* loaded from: classes.dex */
public abstract class UpnpOperation {
    private int httpMinorVersion = 1;

    public int getHttpMinorVersion() {
        return this.httpMinorVersion;
    }

    public void setHttpMinorVersion(int i7) {
        this.httpMinorVersion = i7;
    }
}
