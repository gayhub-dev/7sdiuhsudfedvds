package p200z2;

import android.content.SharedPreferences;
import java.lang.reflect.Method;
import java.util.Locale;
import p086k2.C1231b;

/* compiled from: CtvitSPUtils.java */
/* renamed from: z2.a */
/* loaded from: classes.dex */
public final class C2150a {

    /* renamed from: a */
    public static final String f6311a = C2150a.class.getName().replace(".", "_").toUpperCase(Locale.CHINA);

    /* compiled from: CtvitSPUtils.java */
    /* renamed from: z2.a$a */
    public static class a {

        /* renamed from: a */
        public static final Method f6312a;

        static {
            Method method;
            try {
                method = SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
            } catch (NoSuchMethodException unused) {
                method = null;
            }
            f6312a = method;
        }
    }

    /* renamed from: a */
    public static Object m2590a(String str, Object obj) {
        SharedPreferences sharedPreferences = C1231b.f2761c.getSharedPreferences(f6311a, 0);
        if (obj instanceof String) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        return null;
    }

    /* renamed from: b */
    public static void m2591b(String str, Object obj) {
        SharedPreferences.Editor editorEdit = C1231b.f2761c.getSharedPreferences(f6311a, 0).edit();
        if (obj instanceof String) {
            editorEdit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        } else {
            editorEdit.putString(str, obj.toString());
        }
        Method method = a.f6312a;
        editorEdit.commit();
    }
}
