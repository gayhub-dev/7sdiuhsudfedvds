package pub.devrel.easypermissions;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.constraint.motion.C0079a;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import java.util.Objects;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class AppSettingsDialogHolderActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    /* renamed from: e */
    public AlertDialog f4972e;

    /* renamed from: f */
    public int f4973f;

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i7, int i8, Intent intent) {
        super.onActivityResult(i7, i8, intent);
        setResult(i8, intent);
        finish();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i7) {
        if (i7 == -1) {
            Intent data = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", getPackageName(), null));
            data.addFlags(this.f4973f);
            startActivityForResult(data, 7534);
        } else {
            if (i7 != -2) {
                throw new IllegalStateException(C0079a.m93a("Unknown button type: ", i7));
            }
            setResult(0);
            finish();
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppSettingsDialog appSettingsDialog = (AppSettingsDialog) getIntent().getParcelableExtra("extra_app_settings");
        Objects.requireNonNull(appSettingsDialog);
        appSettingsDialog.f4971l = this;
        this.f4973f = appSettingsDialog.f4970k;
        int i7 = appSettingsDialog.f4964e;
        this.f4972e = (i7 > 0 ? new AlertDialog.Builder(this, i7) : new AlertDialog.Builder(this)).setCancelable(false).setTitle(appSettingsDialog.f4966g).setMessage(appSettingsDialog.f4965f).setPositiveButton(appSettingsDialog.f4967h, this).setNegativeButton(appSettingsDialog.f4968i, this).show();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.f4972e;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.f4972e.dismiss();
    }
}
