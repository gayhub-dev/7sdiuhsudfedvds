package p122o6;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import pub.devrel.easypermissions.RationaleDialogFragment;

/* compiled from: ActivityPermissionHelper.java */
/* renamed from: o6.a */
/* loaded from: classes.dex */
public class C1592a extends AbstractC1595d<Activity> {

    /* renamed from: b */
    public final /* synthetic */ int f4846b = 0;

    public C1592a(Object obj) {
        super(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p122o6.AbstractC1595d
    /* renamed from: a */
    public void mo1859a(int i7, String... strArr) {
        switch (this.f4846b) {
            case 0:
                ActivityCompat.requestPermissions((Activity) this.f4848a, strArr, i7);
                return;
            default:
                throw new IllegalStateException("Should never be requesting permissions on API < 23!");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p122o6.AbstractC1595d
    /* renamed from: b */
    public Context mo1860b() {
        switch (this.f4846b) {
            case 0:
                return (Context) this.f4848a;
            default:
                T t6 = this.f4848a;
                if (t6 instanceof Activity) {
                    return (Context) t6;
                }
                if (t6 instanceof Fragment) {
                    return ((Fragment) t6).getContext();
                }
                throw new IllegalStateException("Unknown host: " + this.f4848a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p122o6.AbstractC1595d
    /* renamed from: d */
    public boolean mo1861d(String str) {
        switch (this.f4846b) {
            case 0:
                return ActivityCompat.shouldShowRequestPermissionRationale((Activity) this.f4848a, str);
            default:
                return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p122o6.AbstractC1595d
    /* renamed from: e */
    public void mo1862e(String str, String str2, String str3, int i7, int i8, String... strArr) {
        switch (this.f4846b) {
            case 0:
                FragmentManager fragmentManager = ((Activity) this.f4848a).getFragmentManager();
                if (fragmentManager.findFragmentByTag("RationaleDialogFragment") instanceof RationaleDialogFragment) {
                    return;
                }
                RationaleDialogFragment rationaleDialogFragment = new RationaleDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("positiveButton", str2);
                bundle.putString("negativeButton", str3);
                bundle.putString("rationaleMsg", str);
                bundle.putInt("theme", i7);
                bundle.putInt("requestCode", i8);
                bundle.putStringArray("permissions", strArr);
                rationaleDialogFragment.setArguments(bundle);
                if ((Build.VERSION.SDK_INT < 26 || !fragmentManager.isStateSaved()) && !rationaleDialogFragment.f4977g) {
                    rationaleDialogFragment.show(fragmentManager, "RationaleDialogFragment");
                    return;
                }
                return;
            default:
                throw new IllegalStateException("Should never be requesting permissions on API < 23!");
        }
    }

    public C1592a(Activity activity) {
        super(activity);
    }
}
