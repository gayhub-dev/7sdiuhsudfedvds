package com.alibaba.sdk.android.oss.model;

import android.support.constraint.C0072a;
import java.util.Date;
import p009b.C0413b;

/* loaded from: classes.dex */
public class OSSBucketSummary {
    private CannedAccessControlList acl;
    public Date createDate;
    public String extranetEndpoint;
    public String intranetEndpoint;
    public String location;
    public String name;
    public Owner owner;
    public String storageClass;

    public String getAcl() {
        CannedAccessControlList cannedAccessControlList = this.acl;
        if (cannedAccessControlList != null) {
            return cannedAccessControlList.toString();
        }
        return null;
    }

    public void setAcl(String str) {
        this.acl = CannedAccessControlList.parseACL(str);
    }

    public String toString() {
        if (this.storageClass == null) {
            StringBuilder sbM112a = C0413b.m112a("OSSBucket [name=");
            sbM112a.append(this.name);
            sbM112a.append(", creationDate=");
            sbM112a.append(this.createDate);
            sbM112a.append(", owner=");
            sbM112a.append(this.owner.toString());
            sbM112a.append(", location=");
            return C0072a.m92a(sbM112a, this.location, "]");
        }
        StringBuilder sbM112a2 = C0413b.m112a("OSSBucket [name=");
        sbM112a2.append(this.name);
        sbM112a2.append(", creationDate=");
        sbM112a2.append(this.createDate);
        sbM112a2.append(", owner=");
        sbM112a2.append(this.owner.toString());
        sbM112a2.append(", location=");
        sbM112a2.append(this.location);
        sbM112a2.append(", storageClass=");
        return C0072a.m92a(sbM112a2, this.storageClass, "]");
    }
}
