package p190y;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import p009b.C0413b;
import p142r0.C1823h;

/* compiled from: SizeConfigStrategy.java */
@TargetApi(19)
/* renamed from: y.l */
/* loaded from: classes.dex */
public class C2094l implements InterfaceC2092j {

    /* renamed from: d */
    public static final Bitmap.Config[] f6206d = {Bitmap.Config.ARGB_8888, null};

    /* renamed from: e */
    public static final Bitmap.Config[] f6207e = {Bitmap.Config.RGB_565};

    /* renamed from: f */
    public static final Bitmap.Config[] f6208f = {Bitmap.Config.ARGB_4444};

    /* renamed from: g */
    public static final Bitmap.Config[] f6209g = {Bitmap.Config.ALPHA_8};

    /* renamed from: a */
    public final c f6210a = new c();

    /* renamed from: b */
    public final C2089g<b, Bitmap> f6211b = new C2089g<>();

    /* renamed from: c */
    public final Map<Bitmap.Config, NavigableMap<Integer, Integer>> f6212c = new HashMap();

    /* compiled from: SizeConfigStrategy.java */
    /* renamed from: y.l$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f6213a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            f6213a = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6213a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6213a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6213a[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* compiled from: SizeConfigStrategy.java */
    /* renamed from: y.l$b */
    public static final class b implements InterfaceC2093k {

        /* renamed from: a */
        public final c f6214a;

        /* renamed from: b */
        public int f6215b;

        /* renamed from: c */
        public Bitmap.Config f6216c;

        public b(c cVar) {
            this.f6214a = cVar;
        }

        @Override // p190y.InterfaceC2093k
        /* renamed from: a */
        public void mo2529a() {
            this.f6214a.m2515d(this);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f6215b == bVar.f6215b && C1823h.m2058b(this.f6216c, bVar.f6216c);
        }

        public int hashCode() {
            int i7 = this.f6215b * 31;
            Bitmap.Config config = this.f6216c;
            return i7 + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return C2094l.m2535c(this.f6215b, this.f6216c);
        }
    }

    /* compiled from: SizeConfigStrategy.java */
    /* renamed from: y.l$c */
    public static class c extends AbstractC2085c<b> {
        public c() {
            super(0);
        }

        @Override // p190y.AbstractC2085c
        /* renamed from: a */
        public InterfaceC2093k mo2512a() {
            return new b(this);
        }

        /* renamed from: e */
        public b m2541e(int i7, Bitmap.Config config) {
            b bVarM2513b = m2513b();
            bVarM2513b.f6215b = i7;
            bVarM2513b.f6216c = config;
            return bVarM2513b;
        }
    }

    /* renamed from: c */
    public static String m2535c(int i7, Bitmap.Config config) {
        return "[" + i7 + "](" + config + ")";
    }

    /* renamed from: a */
    public final void m2536a(Integer num, Bitmap bitmap) {
        NavigableMap<Integer, Integer> navigableMapM2538d = m2538d(bitmap.getConfig());
        Integer num2 = (Integer) navigableMapM2538d.get(num);
        if (num2 != null) {
            if (num2.intValue() == 1) {
                navigableMapM2538d.remove(num);
                return;
            } else {
                navigableMapM2538d.put(num, Integer.valueOf(num2.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + m2539e(bitmap) + ", this: " + this);
    }

    @Nullable
    /* renamed from: b */
    public Bitmap m2537b(int i7, int i8, Bitmap.Config config) {
        int iM2059c = C1823h.m2059c(i7, i8, config);
        b bVarM2513b = this.f6210a.m2513b();
        bVarM2513b.f6215b = iM2059c;
        bVarM2513b.f6216c = config;
        int i9 = a.f6213a[config.ordinal()];
        int i10 = 0;
        Bitmap.Config[] configArr = i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? new Bitmap.Config[]{config} : f6209g : f6208f : f6207e : f6206d;
        int length = configArr.length;
        while (true) {
            if (i10 >= length) {
                break;
            }
            Bitmap.Config config2 = configArr[i10];
            Integer numCeilingKey = m2538d(config2).ceilingKey(Integer.valueOf(iM2059c));
            if (numCeilingKey == null || numCeilingKey.intValue() > iM2059c * 8) {
                i10++;
            } else if (numCeilingKey.intValue() != iM2059c || config2 == null || !config2.equals(config)) {
                this.f6210a.m2515d(bVarM2513b);
                bVarM2513b = this.f6210a.m2541e(numCeilingKey.intValue(), config2);
            }
        }
        Bitmap bitmapM2521a = this.f6211b.m2521a(bVarM2513b);
        if (bitmapM2521a != null) {
            m2536a(Integer.valueOf(bVarM2513b.f6215b), bitmapM2521a);
            bitmapM2521a.reconfigure(i7, i8, bitmapM2521a.getConfig() != null ? bitmapM2521a.getConfig() : Bitmap.Config.ARGB_8888);
        }
        return bitmapM2521a;
    }

    /* renamed from: d */
    public final NavigableMap<Integer, Integer> m2538d(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.f6212c.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f6212c.put(config, treeMap);
        return treeMap;
    }

    /* renamed from: e */
    public String m2539e(Bitmap bitmap) {
        return m2535c(C1823h.m2060d(bitmap), bitmap.getConfig());
    }

    /* renamed from: f */
    public void m2540f(Bitmap bitmap) {
        b bVarM2541e = this.f6210a.m2541e(C1823h.m2060d(bitmap), bitmap.getConfig());
        this.f6211b.m2522b(bVarM2541e, bitmap);
        NavigableMap<Integer, Integer> navigableMapM2538d = m2538d(bitmap.getConfig());
        Integer num = (Integer) navigableMapM2538d.get(Integer.valueOf(bVarM2541e.f6215b));
        navigableMapM2538d.put(Integer.valueOf(bVarM2541e.f6215b), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("SizeConfigStrategy{groupedMap=");
        sbM112a.append(this.f6211b);
        sbM112a.append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.f6212c.entrySet()) {
            sbM112a.append(entry.getKey());
            sbM112a.append('[');
            sbM112a.append(entry.getValue());
            sbM112a.append("], ");
        }
        if (!this.f6212c.isEmpty()) {
            sbM112a.replace(sbM112a.length() - 2, sbM112a.length(), "");
        }
        sbM112a.append(")}");
        return sbM112a.toString();
    }
}
