package org.fourthline.cling.model;

import p009b.C0413b;

/* loaded from: classes.dex */
public class DiscoveryOptions {
    private static String simpleName = "DiscoveryOptions";
    public boolean advertised;
    public boolean byeByeBeforeFirstAlive;

    public DiscoveryOptions(boolean z6) {
        this.advertised = z6;
    }

    public boolean isAdvertised() {
        return this.advertised;
    }

    public boolean isByeByeBeforeFirstAlive() {
        return this.byeByeBeforeFirstAlive;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(simpleName);
        sbM112a.append(") advertised: ");
        sbM112a.append(isAdvertised());
        sbM112a.append(" byebyeBeforeFirstAlive: ");
        sbM112a.append(isByeByeBeforeFirstAlive());
        return sbM112a.toString();
    }

    public DiscoveryOptions(boolean z6, boolean z7) {
        this.advertised = z6;
        this.byeByeBeforeFirstAlive = z7;
    }
}
