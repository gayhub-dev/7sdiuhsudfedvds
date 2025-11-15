package pub.devrel.easypermissions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import p114n6.C1563b;
import pub.devrel.easypermissions.EasyPermissions;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class RationaleDialogFragment extends DialogFragment {

    /* renamed from: e */
    public EasyPermissions.PermissionCallbacks f4975e;

    /* renamed from: f */
    public EasyPermissions.InterfaceC1751a f4976f;

    /* renamed from: g */
    public boolean f4977g = false;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.DialogFragment, android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() != null) {
            if (getParentFragment() instanceof EasyPermissions.PermissionCallbacks) {
                this.f4975e = (EasyPermissions.PermissionCallbacks) getParentFragment();
            }
            if (getParentFragment() instanceof EasyPermissions.InterfaceC1751a) {
                this.f4976f = (EasyPermissions.InterfaceC1751a) getParentFragment();
            }
        }
        if (context instanceof EasyPermissions.PermissionCallbacks) {
            this.f4975e = (EasyPermissions.PermissionCallbacks) context;
        }
        if (context instanceof EasyPermissions.InterfaceC1751a) {
            this.f4976f = (EasyPermissions.InterfaceC1751a) context;
        }
    }

    @Override // android.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        setCancelable(false);
        C1563b c1563b = new C1563b(getArguments());
        DialogInterfaceOnClickListenerC1754c dialogInterfaceOnClickListenerC1754c = new DialogInterfaceOnClickListenerC1754c(this, c1563b, this.f4975e, this.f4976f);
        Activity activity = getActivity();
        return (c1563b.f4687c > 0 ? new AlertDialog.Builder(activity, c1563b.f4687c) : new AlertDialog.Builder(activity)).setCancelable(false).setPositiveButton(c1563b.f4685a, dialogInterfaceOnClickListenerC1754c).setNegativeButton(c1563b.f4686b, dialogInterfaceOnClickListenerC1754c).setMessage(c1563b.f4689e).create();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.f4975e = null;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.f4977g = true;
        super.onSaveInstanceState(bundle);
    }
}
