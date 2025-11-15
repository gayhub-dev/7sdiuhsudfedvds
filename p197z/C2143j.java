package p197z;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.ActivityChooserModel;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

/* compiled from: MemorySizeCalculator.java */
/* renamed from: z.j */
/* loaded from: classes.dex */
public final class C2143j {

    /* renamed from: a */
    public final int f6296a;

    /* renamed from: b */
    public final int f6297b;

    /* renamed from: c */
    public final Context f6298c;

    /* renamed from: d */
    public final int f6299d;

    /* compiled from: MemorySizeCalculator.java */
    /* renamed from: z.j$a */
    public static final class a {

        /* renamed from: e */
        public static final int f6300e;

        /* renamed from: a */
        public final Context f6301a;

        /* renamed from: b */
        public ActivityManager f6302b;

        /* renamed from: c */
        public c f6303c;

        /* renamed from: d */
        public float f6304d;

        static {
            f6300e = Build.VERSION.SDK_INT > 26 ? 4 : 1;
        }

        public a(Context context) {
            this.f6304d = f6300e;
            this.f6301a = context;
            this.f6302b = (ActivityManager) context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
            this.f6303c = new b(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !this.f6302b.isLowRamDevice()) {
                return;
            }
            this.f6304d = 0.0f;
        }
    }

    /* compiled from: MemorySizeCalculator.java */
    /* renamed from: z.j$b */
    public static final class b implements c {

        /* renamed from: a */
        public final DisplayMetrics f6305a;

        public b(DisplayMetrics displayMetrics) {
            this.f6305a = displayMetrics;
        }
    }

    /* compiled from: MemorySizeCalculator.java */
    /* renamed from: z.j$c */
    public interface c {
    }

    public C2143j(a aVar) {
        this.f6298c = aVar.f6301a;
        int i7 = aVar.f6302b.isLowRamDevice() ? 2097152 : 4194304;
        this.f6299d = i7;
        int iRound = Math.round(r1.getMemoryClass() * 1024 * 1024 * (aVar.f6302b.isLowRamDevice() ? 0.33f : 0.4f));
        DisplayMetrics displayMetrics = ((b) aVar.f6303c).f6305a;
        float f7 = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        int iRound2 = Math.round(aVar.f6304d * f7);
        int iRound3 = Math.round(f7 * 2.0f);
        int i8 = iRound - i7;
        if (iRound3 + iRound2 <= i8) {
            this.f6297b = iRound3;
            this.f6296a = iRound2;
        } else {
            float f8 = i8 / (aVar.f6304d + 2.0f);
            this.f6297b = Math.round(2.0f * f8);
            this.f6296a = Math.round(f8 * aVar.f6304d);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            m2571a(this.f6297b);
            m2571a(this.f6296a);
            m2571a(i7);
            m2571a(iRound);
            aVar.f6302b.getMemoryClass();
            aVar.f6302b.isLowRamDevice();
        }
    }

    /* renamed from: a */
    public final String m2571a(int i7) {
        return Formatter.formatFileSize(this.f6298c, i7);
    }
}
