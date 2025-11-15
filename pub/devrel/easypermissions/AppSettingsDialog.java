package pub.devrel.easypermissions;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;

/* loaded from: classes.dex */
public class AppSettingsDialog implements Parcelable {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final Parcelable.Creator<AppSettingsDialog> CREATOR = new C1750a();

    /* renamed from: e */
    @StyleRes
    public final int f4964e;

    /* renamed from: f */
    public final String f4965f;

    /* renamed from: g */
    public final String f4966g;

    /* renamed from: h */
    public final String f4967h;

    /* renamed from: i */
    public final String f4968i;

    /* renamed from: j */
    public final int f4969j;

    /* renamed from: k */
    public final int f4970k;

    /* renamed from: l */
    public Context f4971l;

    /* renamed from: pub.devrel.easypermissions.AppSettingsDialog$a */
    public class C1750a implements Parcelable.Creator<AppSettingsDialog> {
        @Override // android.os.Parcelable.Creator
        public AppSettingsDialog createFromParcel(Parcel parcel) {
            return new AppSettingsDialog(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public AppSettingsDialog[] newArray(int i7) {
            return new AppSettingsDialog[i7];
        }
    }

    public AppSettingsDialog(Parcel parcel, C1750a c1750a) {
        this.f4964e = parcel.readInt();
        this.f4965f = parcel.readString();
        this.f4966g = parcel.readString();
        this.f4967h = parcel.readString();
        this.f4968i = parcel.readString();
        this.f4969j = parcel.readInt();
        this.f4970k = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i7) {
        parcel.writeInt(this.f4964e);
        parcel.writeString(this.f4965f);
        parcel.writeString(this.f4966g);
        parcel.writeString(this.f4967h);
        parcel.writeString(this.f4968i);
        parcel.writeInt(this.f4969j);
        parcel.writeInt(this.f4970k);
    }
}
