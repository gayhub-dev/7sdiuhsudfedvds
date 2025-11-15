package p142r0;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: Util.java */
/* renamed from: r0.h */
/* loaded from: classes.dex */
public final class C1823h {

    /* renamed from: a */
    public static final char[] f5300a = "0123456789abcdef".toCharArray();

    /* renamed from: b */
    public static final char[] f5301b = new char[64];

    /* compiled from: Util.java */
    /* renamed from: r0.h$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f5302a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            f5302a = iArr;
            try {
                iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5302a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5302a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5302a[Bitmap.Config.ARGB_8888.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: a */
    public static void m2057a() {
        if (!m2064h()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    /* renamed from: b */
    public static boolean m2058b(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    /* renamed from: c */
    public static int m2059c(int i7, int i8, Bitmap.Config config) {
        int i9 = i7 * i8;
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        int i10 = a.f5302a[config.ordinal()];
        int i11 = 2;
        if (i10 == 1) {
            i11 = 1;
        } else if (i10 != 2 && i10 != 3) {
            i11 = 4;
        }
        return i9 * i11;
    }

    @TargetApi(19)
    /* renamed from: d */
    public static int m2060d(Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException unused) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        }
        throw new IllegalStateException("Cannot obtain size for recycled Bitmap: " + bitmap + "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig());
    }

    /* renamed from: e */
    public static <T> List<T> m2061e(Collection<T> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    /* renamed from: f */
    public static int m2062f(Object obj, int i7) {
        return (i7 * 31) + (obj == null ? 0 : obj.hashCode());
    }

    /* renamed from: g */
    public static boolean m2063g() {
        return !m2064h();
    }

    /* renamed from: h */
    public static boolean m2064h() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: i */
    public static boolean m2065i(int i7, int i8) {
        if (i7 > 0 || i7 == Integer.MIN_VALUE) {
            if (i8 > 0 || i8 == Integer.MIN_VALUE) {
                return true;
            }
        }
        return false;
    }
}
