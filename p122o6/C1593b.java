package p122o6;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/* compiled from: AppCompatActivityPermissionsHelper.java */
/* renamed from: o6.b */
/* loaded from: classes.dex */
public class C1593b extends AbstractC1594c<AppCompatActivity> {

    /* renamed from: b */
    public final /* synthetic */ int f4847b = 1;

    public C1593b(Fragment fragment) {
        super(fragment);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p122o6.AbstractC1595d
    /* renamed from: a */
    public void mo1859a(int i7, String... strArr) {
        switch (this.f4847b) {
            case 0:
                ActivityCompat.requestPermissions((Activity) this.f4848a, strArr, i7);
                break;
            default:
                ((Fragment) this.f4848a).requestPermissions(strArr, i7);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p122o6.AbstractC1595d
    /* renamed from: b */
    public Context mo1860b() {
        switch (this.f4847b) {
            case 0:
                return (Context) this.f4848a;
            default:
                return ((Fragment) this.f4848a).getActivity();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p122o6.AbstractC1595d
    /* renamed from: d */
    public boolean mo1861d(String str) {
        switch (this.f4847b) {
            case 0:
                return ActivityCompat.shouldShowRequestPermissionRationale((Activity) this.f4848a, str);
            default:
                return ((Fragment) this.f4848a).shouldShowRequestPermissionRationale(str);
        }
    }

    public C1593b(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }
}
