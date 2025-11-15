package pub.devrel.easypermissions;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.Fragment;
import java.util.Arrays;
import p114n6.C1563b;
import p122o6.AbstractC1595d;
import p122o6.C1592a;
import p122o6.C1593b;
import pub.devrel.easypermissions.EasyPermissions;

/* compiled from: RationaleDialogClickListener.java */
/* renamed from: pub.devrel.easypermissions.c */
/* loaded from: classes.dex */
public class DialogInterfaceOnClickListenerC1754c implements DialogInterface.OnClickListener {

    /* renamed from: e */
    public Object f4992e;

    /* renamed from: f */
    public C1563b f4993f;

    /* renamed from: g */
    public EasyPermissions.PermissionCallbacks f4994g;

    /* renamed from: h */
    public EasyPermissions.InterfaceC1751a f4995h;

    public DialogInterfaceOnClickListenerC1754c(RationaleDialogFragment rationaleDialogFragment, C1563b c1563b, EasyPermissions.PermissionCallbacks permissionCallbacks, EasyPermissions.InterfaceC1751a interfaceC1751a) {
        this.f4992e = rationaleDialogFragment.getActivity();
        this.f4993f = c1563b;
        this.f4994g = permissionCallbacks;
        this.f4995h = interfaceC1751a;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        C1563b c1563b = this.f4993f;
        int i8 = c1563b.f4688d;
        if (i7 != -1) {
            EasyPermissions.InterfaceC1751a interfaceC1751a = this.f4995h;
            if (interfaceC1751a != null) {
                interfaceC1751a.m1920b(i8);
            }
            EasyPermissions.PermissionCallbacks permissionCallbacks = this.f4994g;
            if (permissionCallbacks != null) {
                C1563b c1563b2 = this.f4993f;
                permissionCallbacks.mo463a(c1563b2.f4688d, Arrays.asList(c1563b2.f4690f));
                return;
            }
            return;
        }
        String[] strArr = c1563b.f4690f;
        EasyPermissions.InterfaceC1751a interfaceC1751a2 = this.f4995h;
        if (interfaceC1751a2 != null) {
            interfaceC1751a2.m1919a(i8);
        }
        Object obj = this.f4992e;
        if (obj instanceof Fragment) {
            Fragment fragment = (Fragment) obj;
            (Build.VERSION.SDK_INT < 23 ? new C1592a(fragment) : new C1593b(fragment)).mo1859a(i8, strArr);
        } else {
            if (!(obj instanceof Activity)) {
                throw new RuntimeException("Host must be an Activity or Fragment!");
            }
            AbstractC1595d.m1863c((Activity) obj).mo1859a(i8, strArr);
        }
    }
}
