package p129p3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huan.appstore.third.ThirdUpgradeInfo;

/* compiled from: IThirdUpgradeService.java */
/* renamed from: p3.a */
/* loaded from: classes.dex */
public interface InterfaceC1737a extends IInterface {

    /* compiled from: IThirdUpgradeService.java */
    /* renamed from: p3.a$a */
    public static abstract class a extends Binder implements InterfaceC1737a {

        /* renamed from: a */
        public static final /* synthetic */ int f4921a = 0;

        /* compiled from: IThirdUpgradeService.java */
        /* renamed from: p3.a$a$a, reason: collision with other inner class name */
        public static class C2190a implements InterfaceC1737a {

            /* renamed from: a */
            public IBinder f4922a;

            public C2190a(IBinder iBinder) {
                this.f4922a = iBinder;
            }

            @Override // p129p3.InterfaceC1737a
            /* renamed from: a */
            public void mo1887a(String str, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.huan.appstore.third.IThirdUpgradeService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.f4922a.transact(2, parcelObtain, parcelObtain2, 0)) {
                        int i7 = a.f4921a;
                    }
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4922a;
            }

            @Override // p129p3.InterfaceC1737a
            /* renamed from: b */
            public ThirdUpgradeInfo mo1888b(String str, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.huan.appstore.third.IThirdUpgradeService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.f4922a.transact(1, parcelObtain, parcelObtain2, 0)) {
                        int i7 = a.f4921a;
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ThirdUpgradeInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    /* renamed from: a */
    void mo1887a(String str, String str2);

    /* renamed from: b */
    ThirdUpgradeInfo mo1888b(String str, String str2);
}
