package p122o6;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

/* compiled from: PermissionHelper.java */
/* renamed from: o6.d */
/* loaded from: classes.dex */
public abstract class AbstractC1595d<T> {

    /* renamed from: a */
    public T f4848a;

    public AbstractC1595d(@NonNull T t6) {
        this.f4848a = t6;
    }

    @NonNull
    /* renamed from: c */
    public static AbstractC1595d<? extends Activity> m1863c(Activity activity) {
        return Build.VERSION.SDK_INT < 23 ? new C1592a((Object) activity) : activity instanceof AppCompatActivity ? new C1593b((AppCompatActivity) activity) : new C1592a(activity);
    }

    /* renamed from: a */
    public abstract void mo1859a(int i7, @NonNull String... strArr);

    /* renamed from: b */
    public abstract Context mo1860b();

    /* renamed from: d */
    public abstract boolean mo1861d(@NonNull String str);

    /* renamed from: e */
    public abstract void mo1862e(@NonNull String str, @NonNull String str2, @NonNull String str3, @StyleRes int i7, int i8, @NonNull String... strArr);
}
