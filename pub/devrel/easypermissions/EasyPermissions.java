package pub.devrel.easypermissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import p122o6.AbstractC1595d;

/* loaded from: classes.dex */
public class EasyPermissions {

    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
        /* renamed from: a */
        void mo463a(int i7, @NonNull List<String> list);

        /* renamed from: b */
        void mo464b(int i7, @NonNull List<String> list);
    }

    /* renamed from: pub.devrel.easypermissions.EasyPermissions$a */
    public interface InterfaceC1751a {
        /* renamed from: a */
        void m1919a(int i7);

        /* renamed from: b */
        void m1920b(int i7);
    }

    /* renamed from: a */
    public static boolean m1915a(@NonNull Context context, @Size(min = 1) @NonNull String... strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context");
        }
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1916b(int r10, @android.support.annotation.NonNull java.lang.String[] r11, @android.support.annotation.NonNull int[] r12, @android.support.annotation.NonNull java.lang.Object... r13) throws java.lang.IllegalAccessException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            r3 = 0
        Lc:
            int r4 = r11.length
            if (r3 >= r4) goto L1f
            r4 = r11[r3]
            r5 = r12[r3]
            if (r5 != 0) goto L19
            r0.add(r4)
            goto L1c
        L19:
            r1.add(r4)
        L1c:
            int r3 = r3 + 1
            goto Lc
        L1f:
            int r11 = r13.length
            r12 = 0
        L21:
            if (r12 >= r11) goto Ld3
            r3 = r13[r12]
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L35
            boolean r4 = r3 instanceof pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
            if (r4 == 0) goto L35
            r4 = r3
            pub.devrel.easypermissions.EasyPermissions$PermissionCallbacks r4 = (pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks) r4
            r4.mo464b(r10, r0)
        L35:
            boolean r4 = r1.isEmpty()
            if (r4 != 0) goto L45
            boolean r4 = r3 instanceof pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
            if (r4 == 0) goto L45
            r4 = r3
            pub.devrel.easypermissions.EasyPermissions$PermissionCallbacks r4 = (pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks) r4
            r4.mo463a(r10, r1)
        L45:
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto Lcf
            boolean r4 = r1.isEmpty()
            if (r4 == 0) goto Lcf
            java.lang.Class r4 = r3.getClass()
            java.lang.Class r5 = r3.getClass()
            java.lang.String r5 = r5.getSimpleName()
            java.lang.String r6 = "_"
            boolean r5 = r5.endsWith(r6)
            if (r5 != 0) goto L66
            goto L71
        L66:
            java.lang.String r5 = "org.androidannotations.api.view.HasViews"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch: java.lang.ClassNotFoundException -> L71
            boolean r5 = r5.isInstance(r3)     // Catch: java.lang.ClassNotFoundException -> L71
            goto L72
        L71:
            r5 = 0
        L72:
            if (r5 == 0) goto L78
            java.lang.Class r4 = r4.getSuperclass()
        L78:
            if (r4 == 0) goto Lcf
            java.lang.reflect.Method[] r5 = r4.getDeclaredMethods()
            int r6 = r5.length
            r7 = 0
        L80:
            if (r7 >= r6) goto Lca
            r8 = r5[r7]
            java.lang.Class<n6.a> r9 = p114n6.InterfaceC1562a.class
            java.lang.annotation.Annotation r9 = r8.getAnnotation(r9)
            n6.a r9 = (p114n6.InterfaceC1562a) r9
            if (r9 == 0) goto Lc7
            int r9 = r9.value()
            if (r9 != r10) goto Lc7
            java.lang.Class[] r9 = r8.getParameterTypes()
            int r9 = r9.length
            if (r9 > 0) goto Lab
            boolean r9 = r8.isAccessible()     // Catch: java.lang.Throwable -> Lc7
            if (r9 != 0) goto La5
            r9 = 1
            r8.setAccessible(r9)     // Catch: java.lang.Throwable -> Lc7
        La5:
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lc7
            r8.invoke(r3, r9)     // Catch: java.lang.Throwable -> Lc7
            goto Lc7
        Lab:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.String r11 = "Cannot execute method "
            java.lang.StringBuilder r11 = p009b.C0413b.m112a(r11)
            java.lang.String r12 = r8.getName()
            r11.append(r12)
            java.lang.String r12 = " because it is non-void method and/or has input parameters."
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        Lc7:
            int r7 = r7 + 1
            goto L80
        Lca:
            java.lang.Class r4 = r4.getSuperclass()
            goto L78
        Lcf:
            int r12 = r12 + 1
            goto L21
        Ld3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: pub.devrel.easypermissions.EasyPermissions.m1916b(int, java.lang.String[], int[], java.lang.Object[]):void");
    }

    /* renamed from: c */
    public static void m1917c(C1753b c1753b) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        boolean z6 = true;
        if (m1915a(c1753b.f4985a.mo1860b(), c1753b.m1922a())) {
            Object obj = c1753b.f4985a.f4848a;
            int i7 = c1753b.f4987c;
            String[] strArrM1922a = c1753b.m1922a();
            int[] iArr = new int[strArrM1922a.length];
            for (int i8 = 0; i8 < strArrM1922a.length; i8++) {
                iArr[i8] = 0;
            }
            m1916b(i7, strArrM1922a, iArr, obj);
            return;
        }
        AbstractC1595d abstractC1595d = c1753b.f4985a;
        String str = c1753b.f4988d;
        String str2 = c1753b.f4989e;
        String str3 = c1753b.f4990f;
        int i9 = c1753b.f4991g;
        int i10 = c1753b.f4987c;
        String[] strArrM1922a2 = c1753b.m1922a();
        Objects.requireNonNull(abstractC1595d);
        int length = strArrM1922a2.length;
        int i11 = 0;
        while (true) {
            if (i11 >= length) {
                z6 = false;
                break;
            } else if (abstractC1595d.mo1861d(strArrM1922a2[i11])) {
                break;
            } else {
                i11++;
            }
        }
        if (z6) {
            abstractC1595d.mo1862e(str, str2, str3, i9, i10, strArrM1922a2);
        } else {
            abstractC1595d.mo1859a(i10, strArrM1922a2);
        }
    }

    /* renamed from: d */
    public static boolean m1918d(@NonNull Activity activity, @NonNull List<String> list) {
        AbstractC1595d<? extends Activity> abstractC1595dM1863c = AbstractC1595d.m1863c(activity);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (!abstractC1595dM1863c.mo1861d(it.next())) {
                return true;
            }
        }
        return false;
    }
}
