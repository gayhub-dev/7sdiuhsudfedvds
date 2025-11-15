package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.InvocationTargetException;
import p017c.C0497b;
import p017c.InterfaceC0498c;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new C0411a();

    /* renamed from: e */
    public final InterfaceC0498c f175e;

    /* renamed from: androidx.versionedparcelable.ParcelImpl$a */
    public static class C0411a implements Parcelable.Creator<ParcelImpl> {
        @Override // android.os.Parcelable.Creator
        public ParcelImpl createFromParcel(Parcel parcel) {
            return new ParcelImpl(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelImpl[] newArray(int i7) {
            return new ParcelImpl[i7];
        }
    }

    public ParcelImpl(Parcel parcel) {
        this.f175e = new C0497b(parcel, parcel.dataPosition(), parcel.dataSize(), "").m306k();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        new C0497b(parcel, parcel.dataPosition(), parcel.dataSize(), "").m312q(this.f175e);
    }
}
