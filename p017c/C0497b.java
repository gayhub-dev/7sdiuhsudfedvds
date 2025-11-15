package p017c;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.constraint.C0072a;
import android.util.SparseIntArray;

/* compiled from: VersionedParcelParcel.java */
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: c.b */
/* loaded from: classes.dex */
public class C0497b extends AbstractC0496a {

    /* renamed from: b */
    public final Parcel f356b;

    /* renamed from: c */
    public final int f357c;

    /* renamed from: d */
    public final int f358d;

    /* renamed from: e */
    public final String f359e;

    /* renamed from: g */
    public int f361g;

    /* renamed from: a */
    public final SparseIntArray f355a = new SparseIntArray();

    /* renamed from: f */
    public int f360f = -1;

    public C0497b(Parcel parcel, int i7, int i8, String str) {
        this.f361g = 0;
        this.f356b = parcel;
        this.f357c = i7;
        this.f358d = i8;
        this.f361g = i7;
        this.f359e = str;
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: a */
    public void mo297a() {
        int i7 = this.f360f;
        if (i7 >= 0) {
            int i8 = this.f355a.get(i7);
            int iDataPosition = this.f356b.dataPosition();
            this.f356b.setDataPosition(i8);
            this.f356b.writeInt(iDataPosition - i8);
            this.f356b.setDataPosition(iDataPosition);
        }
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: b */
    public AbstractC0496a mo298b() {
        Parcel parcel = this.f356b;
        int iDataPosition = parcel.dataPosition();
        int i7 = this.f361g;
        if (i7 == this.f357c) {
            i7 = this.f358d;
        }
        return new C0497b(parcel, iDataPosition, i7, C0072a.m92a(new StringBuilder(), this.f359e, "  "));
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: d */
    public byte[] mo299d() {
        int i7 = this.f356b.readInt();
        if (i7 < 0) {
            return null;
        }
        byte[] bArr = new byte[i7];
        this.f356b.readByteArray(bArr);
        return bArr;
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: e */
    public boolean mo300e(int i7) {
        int iDataPosition;
        while (true) {
            int i8 = this.f361g;
            if (i8 >= this.f358d) {
                iDataPosition = -1;
                break;
            }
            this.f356b.setDataPosition(i8);
            int i9 = this.f356b.readInt();
            int i10 = this.f356b.readInt();
            this.f361g += i9;
            if (i10 == i7) {
                iDataPosition = this.f356b.dataPosition();
                break;
            }
        }
        if (iDataPosition == -1) {
            return false;
        }
        this.f356b.setDataPosition(iDataPosition);
        return true;
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: f */
    public int mo301f() {
        return this.f356b.readInt();
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: h */
    public <T extends Parcelable> T mo303h() {
        return (T) this.f356b.readParcelable(C0497b.class.getClassLoader());
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: j */
    public String mo305j() {
        return this.f356b.readString();
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: l */
    public void mo307l(int i7) {
        mo297a();
        this.f360f = i7;
        this.f355a.put(i7, this.f356b.dataPosition());
        this.f356b.writeInt(0);
        this.f356b.writeInt(i7);
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: m */
    public void mo308m(byte[] bArr) {
        if (bArr == null) {
            this.f356b.writeInt(-1);
        } else {
            this.f356b.writeInt(bArr.length);
            this.f356b.writeByteArray(bArr);
        }
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: n */
    public void mo309n(int i7) {
        this.f356b.writeInt(i7);
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: o */
    public void mo310o(Parcelable parcelable) {
        this.f356b.writeParcelable(parcelable, 0);
    }

    @Override // p017c.AbstractC0496a
    /* renamed from: p */
    public void mo311p(String str) {
        this.f356b.writeString(str);
    }
}
