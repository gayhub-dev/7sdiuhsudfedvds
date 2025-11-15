package p122o6;

import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import pub.devrel.easypermissions.RationaleDialogFragmentCompat;

/* compiled from: BaseSupportPermissionsHelper.java */
/* renamed from: o6.c */
/* loaded from: classes.dex */
public abstract class AbstractC1594c<T> extends AbstractC1595d<T> {
    public AbstractC1594c(@NonNull T t6) {
        super(t6);
    }

    @Override // p122o6.AbstractC1595d
    /* renamed from: e */
    public void mo1862e(@NonNull String str, @NonNull String str2, @NonNull String str3, @StyleRes int i7, int i8, @NonNull String... strArr) {
        FragmentManager childFragmentManager;
        C1593b c1593b = (C1593b) this;
        switch (c1593b.f4847b) {
            case 0:
                childFragmentManager = ((AppCompatActivity) c1593b.f4848a).getSupportFragmentManager();
                break;
            default:
                childFragmentManager = ((Fragment) c1593b.f4848a).getChildFragmentManager();
                break;
        }
        if (childFragmentManager.findFragmentByTag("RationaleDialogFragmentCompat") instanceof RationaleDialogFragmentCompat) {
            return;
        }
        RationaleDialogFragmentCompat rationaleDialogFragmentCompatM1921e = RationaleDialogFragmentCompat.m1921e(str, str2, str3, i7, i8, strArr);
        if (childFragmentManager.isStateSaved()) {
            return;
        }
        rationaleDialogFragmentCompatM1921e.show(childFragmentManager, "RationaleDialogFragmentCompat");
    }
}
