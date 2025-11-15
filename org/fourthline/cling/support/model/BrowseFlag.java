package org.fourthline.cling.support.model;

/* loaded from: classes.dex */
public enum BrowseFlag {
    METADATA("BrowseMetadata"),
    DIRECT_CHILDREN("BrowseDirectChildren");

    private String protocolString;

    BrowseFlag(String str) {
        this.protocolString = str;
    }

    public static BrowseFlag valueOrNullOf(String str) {
        for (BrowseFlag browseFlag : values()) {
            if (browseFlag.toString().equals(str)) {
                return browseFlag;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocolString;
    }
}
