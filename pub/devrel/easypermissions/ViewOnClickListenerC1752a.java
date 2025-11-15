package pub.devrel.easypermissions;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import java.util.Arrays;
import p114n6.C1563b;
import p122o6.AbstractC1595d;
import p122o6.C1592a;
import p122o6.C1593b;
import pub.devrel.easypermissions.EasyPermissions;

/* compiled from: ButtonClickListener.java */
/* renamed from: pub.devrel.easypermissions.a */
/* loaded from: classes.dex */
public class ViewOnClickListenerC1752a implements View.OnClickListener, View.OnFocusChangeListener {

    /* renamed from: e */
    public Object f4980e;

    /* renamed from: f */
    public RationaleDialogFragmentCompat f4981f;

    /* renamed from: g */
    public C1563b f4982g;

    /* renamed from: h */
    public EasyPermissions.PermissionCallbacks f4983h;

    /* renamed from: i */
    public EasyPermissions.InterfaceC1751a f4984i;

    public ViewOnClickListenerC1752a(RationaleDialogFragmentCompat rationaleDialogFragmentCompat, C1563b c1563b, EasyPermissions.PermissionCallbacks permissionCallbacks, EasyPermissions.InterfaceC1751a interfaceC1751a) {
        this.f4980e = rationaleDialogFragmentCompat.getParentFragment() != null ? rationaleDialogFragmentCompat.getParentFragment() : rationaleDialogFragmentCompat.getActivity();
        this.f4981f = rationaleDialogFragmentCompat;
        this.f4982g = c1563b;
        this.f4983h = permissionCallbacks;
        this.f4984i = interfaceC1751a;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i7 = this.f4982g.f4688d;
        this.f4981f.dismissAllowingStateLoss();
        if (view.getId() != R$id.btn_agree) {
            if (view.getId() == R$id.btn_refuse) {
                EasyPermissions.InterfaceC1751a interfaceC1751a = this.f4984i;
                if (interfaceC1751a != null) {
                    interfaceC1751a.m1920b(i7);
                }
                EasyPermissions.PermissionCallbacks permissionCallbacks = this.f4983h;
                if (permissionCallbacks != null) {
                    C1563b c1563b = this.f4982g;
                    permissionCallbacks.mo463a(c1563b.f4688d, Arrays.asList(c1563b.f4690f));
                    return;
                }
                return;
            }
            return;
        }
        String[] strArr = this.f4982g.f4690f;
        EasyPermissions.InterfaceC1751a interfaceC1751a2 = this.f4984i;
        if (interfaceC1751a2 != null) {
            interfaceC1751a2.m1919a(i7);
        }
        Object obj = this.f4980e;
        if (obj instanceof Fragment) {
            Fragment fragment = (Fragment) obj;
            (Build.VERSION.SDK_INT < 23 ? new C1592a(fragment) : new C1593b(fragment)).mo1859a(i7, strArr);
        } else {
            if (!(obj instanceof Activity)) {
                throw new RuntimeException("Host must be an Activity or Fragment!");
            }
            AbstractC1595d.m1863c((Activity) obj).mo1859a(i7, strArr);
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z6) {
        int id = view.getId();
        int i7 = R$id.btn_agree;
        if (id == i7) {
            return;
        }
        int id2 = view.getId();
        int i8 = R$id.btn_refuse;
        if (id2 == i8) {
        }
    }
}
