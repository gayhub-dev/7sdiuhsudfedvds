package com.huan.appstore.third;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ThirdUpgradeInfo implements Parcelable {
    public static final Parcelable.Creator<ThirdUpgradeInfo> CREATOR = new C0857a();

    /* renamed from: e */
    public String f1275e;

    /* renamed from: f */
    public String f1276f;

    /* renamed from: g */
    public int f1277g;

    /* renamed from: h */
    public String f1278h;

    /* renamed from: com.huan.appstore.third.ThirdUpgradeInfo$a */
    public class C0857a implements Parcelable.Creator<ThirdUpgradeInfo> {
        @Override // android.os.Parcelable.Creator
        public ThirdUpgradeInfo createFromParcel(Parcel parcel) {
            return new ThirdUpgradeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ThirdUpgradeInfo[] newArray(int i7) {
            return new ThirdUpgradeInfo[i7];
        }
    }

    public ThirdUpgradeInfo(Parcel parcel) {
        this.f1275e = parcel.readString();
        this.f1276f = parcel.readString();
        this.f1277g = parcel.readInt();
        this.f1278h = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f1275e);
        parcel.writeString(this.f1276f);
        parcel.writeInt(this.f1277g);
        parcel.writeString(this.f1278h);
    }

    public ThirdUpgradeInfo() {
    }
}
