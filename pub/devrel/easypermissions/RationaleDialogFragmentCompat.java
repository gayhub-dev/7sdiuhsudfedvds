package pub.devrel.easypermissions;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import p114n6.C1563b;
import p132p6.C1749a;
import pub.devrel.easypermissions.EasyPermissions;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class RationaleDialogFragmentCompat extends AppCompatDialogFragment {

    /* renamed from: e */
    public EasyPermissions.PermissionCallbacks f4978e;

    /* renamed from: f */
    public EasyPermissions.InterfaceC1751a f4979f;

    /* renamed from: e */
    public static RationaleDialogFragmentCompat m1921e(@NonNull String str, @NonNull String str2, @NonNull String str3, @StyleRes int i7, int i8, @NonNull String[] strArr) {
        RationaleDialogFragmentCompat rationaleDialogFragmentCompat = new RationaleDialogFragmentCompat();
        Bundle bundle = new Bundle();
        bundle.putString("positiveButton", str2);
        bundle.putString("negativeButton", str3);
        bundle.putString("rationaleMsg", str);
        bundle.putInt("theme", i7);
        bundle.putInt("requestCode", i8);
        bundle.putStringArray("permissions", strArr);
        rationaleDialogFragmentCompat.setArguments(bundle);
        return rationaleDialogFragmentCompat;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() != null) {
            if (getParentFragment() instanceof EasyPermissions.PermissionCallbacks) {
                this.f4978e = (EasyPermissions.PermissionCallbacks) getParentFragment();
            }
            if (getParentFragment() instanceof EasyPermissions.InterfaceC1751a) {
                this.f4979f = (EasyPermissions.InterfaceC1751a) getParentFragment();
            }
        }
        if (context instanceof EasyPermissions.PermissionCallbacks) {
            this.f4978e = (EasyPermissions.PermissionCallbacks) context;
        }
        if (context instanceof EasyPermissions.InterfaceC1751a) {
            this.f4979f = (EasyPermissions.InterfaceC1751a) context;
        }
    }

    @Override // android.support.v7.app.AppCompatDialogFragment, android.support.v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        setCancelable(false);
        C1563b c1563b = new C1563b(getArguments());
        ViewOnClickListenerC1752a viewOnClickListenerC1752a = new ViewOnClickListenerC1752a(this, c1563b, this.f4978e, this.f4979f);
        Context context = getContext();
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R$layout.alert_view, (ViewGroup) null);
        int i7 = c1563b.f4687c;
        AlertDialog.Builder builder = i7 > 0 ? new AlertDialog.Builder(context, i7) : new AlertDialog.Builder(context, R$style.EasyPermissions_Transparent);
        builder.setView(relativeLayout);
        C1749a.m1913b(null, relativeLayout);
        Button button = (Button) relativeLayout.findViewById(R$id.btn_agree);
        Button button2 = (Button) relativeLayout.findViewById(R$id.agree_long);
        TextView textView = (TextView) relativeLayout.findViewById(R$id.text_title);
        TextView textView2 = (TextView) relativeLayout.findViewById(R$id.text_all);
        button.setOnClickListener(viewOnClickListenerC1752a);
        button.setOnFocusChangeListener(viewOnClickListenerC1752a);
        button2.setOnClickListener(viewOnClickListenerC1752a);
        button.requestFocus();
        button.setText(c1563b.f4685a);
        button2.setText(c1563b.f4685a);
        textView2.setText(c1563b.f4689e);
        textView.setVisibility(0);
        Button button3 = (Button) relativeLayout.findViewById(R$id.btn_refuse);
        if ("".equals(c1563b.f4686b)) {
            textView.setText(R$string.permission_title_point);
            button3.setVisibility(8);
            button.setVisibility(8);
            button2.setVisibility(0);
            button2.requestFocus();
        } else {
            textView.setText(R$string.permission_title);
            button3.setVisibility(0);
            button.setVisibility(0);
            button2.setVisibility(8);
        }
        button3.setOnClickListener(viewOnClickListenerC1752a);
        button3.setOnFocusChangeListener(viewOnClickListenerC1752a);
        button3.setText(c1563b.f4686b);
        return builder.setCancelable(false).create();
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.f4978e = null;
        this.f4979f = null;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(getResources().getDimensionPixelOffset(R$dimen.per_500_dp), getResources().getDimensionPixelOffset(R$dimen.per_300_dp));
    }
}
