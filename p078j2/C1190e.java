package p078j2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* compiled from: DlnaNameUtils.java */
/* renamed from: j2.e */
/* loaded from: classes.dex */
public class C1190e {
    /* renamed from: a */
    public static void m1406a(Context context, String str) {
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.putString("dlna_name", str);
        editorEdit.commit();
    }
}
