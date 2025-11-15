package org.fourthline.cling.support.model.dlna.types;

/* loaded from: classes.dex */
public class ScmsFlagType {
    private boolean copyright;
    private boolean original;

    public ScmsFlagType() {
        this.copyright = true;
        this.original = true;
    }

    public boolean isCopyright() {
        return this.copyright;
    }

    public boolean isOriginal() {
        return this.original;
    }

    public ScmsFlagType(boolean z6, boolean z7) {
        this.copyright = z6;
        this.original = z7;
    }
}
