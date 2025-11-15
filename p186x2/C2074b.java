package p186x2;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.constraint.motion.C0080b;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.security.cert.CRL;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p000a.C0000a;
import p005a4.C0012d;
import p009b.C0413b;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0446f;
import p014b4.InterfaceC0454n;
import p016b6.AbstractC0471b;
import p016b6.C0479j;
import p022c4.EnumC0516d;
import p032d4.C0870a;
import p040e4.InterfaceC0955h;
import p048f4.AbstractC1011p;
import p048f4.C1002g;
import p048f4.C1010o;
import p050f6.AbstractC1015b;
import p058g6.C1075m;
import p058g6.InterfaceC1076n;
import p058g6.InterfaceC1077o;
import p072i4.C1147a;
import p086k2.C1231b;
import p096l4.C1435b;
import p138q4.C1771c;
import p138q4.C1774f;
import p138q4.EnumC1776h;
import p160t4.C1908a;
import p182w5.AbstractC2038e;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2112d;
import p194y3.InterfaceC2118j;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2131w;
import p201z3.InterfaceC2153b;
import p203z5.C2158b;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.MediaPlayerProxy;

/* compiled from: CtvitStringUtils.java */
/* renamed from: x2.b */
/* loaded from: classes.dex */
public class C2074b {

    /* renamed from: a */
    public static C2158b f6173a;

    /* renamed from: A */
    public static void m2461A(Class<?> cls) {
        String name = cls.getName();
        C1908a.m2205b(new C0012d("It is not allowed to subscribe with a(n) " + name + " multiple times. Please create a fresh instance of " + name + " and subscribe that to the target source instead."));
    }

    /* renamed from: B */
    public static int m2462B(int i7) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i7 - 1));
    }

    /* renamed from: C */
    public static long m2463C(long j7, long j8) {
        long j9 = j7 + j8;
        if ((j7 ^ j9) >= 0 || (j7 ^ j8) < 0) {
            return j9;
        }
        throw new ArithmeticException("The calculation caused an overflow: " + j7 + " + " + j8);
    }

    /* renamed from: D */
    public static long m2464D(long j7, int i7) {
        if (i7 == -1) {
            if (j7 != Long.MIN_VALUE) {
                return -j7;
            }
            throw new ArithmeticException("Multiplication overflows a long: " + j7 + " * " + i7);
        }
        if (i7 == 0) {
            return 0L;
        }
        if (i7 == 1) {
            return j7;
        }
        long j8 = i7;
        long j9 = j7 * j8;
        if (j9 / j8 == j7) {
            return j9;
        }
        throw new ArithmeticException("Multiplication overflows a long: " + j7 + " * " + i7);
    }

    /* renamed from: E */
    public static long m2465E(long j7, long j8) {
        long j9 = j7 - j8;
        if ((j7 ^ j9) >= 0 || (j7 ^ j8) >= 0) {
            return j9;
        }
        throw new ArithmeticException("The calculation caused an overflow: " + j7 + " - " + j8);
    }

    /* renamed from: F */
    public static int m2466F(long j7) {
        if (-2147483648L > j7 || j7 > 2147483647L) {
            throw new ArithmeticException(C0534b.m341a("Value cannot fit in an int: ", j7));
        }
        return (int) j7;
    }

    /* renamed from: G */
    public static C2158b m2467G() {
        if (f6173a == null) {
            C1075m c1075m = new C1075m();
            c1075m.m1153d();
            C1075m.e eVar = new C1075m.e("P");
            c1075m.m1150a(eVar, eVar);
            c1075m.m1151b(0);
            c1075m.m1152c("Y");
            c1075m.m1151b(1);
            c1075m.m1152c("M");
            c1075m.m1151b(2);
            c1075m.m1152c("W");
            c1075m.m1151b(3);
            c1075m.m1152c("D");
            c1075m.m1153d();
            List<Object> listSubList = c1075m.f2141d;
            if (listSubList.size() == 0) {
                C1075m.e eVar2 = C1075m.e.f2159b;
                C1075m.g gVar = new C1075m.g("T", "T", null, eVar2, eVar2, false, true);
                c1075m.m1150a(gVar, gVar);
            } else {
                C1075m.g gVar2 = null;
                int size = listSubList.size();
                while (true) {
                    int i7 = size - 1;
                    if (i7 < 0) {
                        break;
                    }
                    if (listSubList.get(i7) instanceof C1075m.g) {
                        gVar2 = (C1075m.g) listSubList.get(i7);
                        listSubList = listSubList.subList(i7 + 1, listSubList.size());
                        break;
                    }
                    size = i7 - 1;
                }
                if (gVar2 != null && listSubList.size() == 0) {
                    throw new IllegalStateException("Cannot have two adjacent separators");
                }
                Object[] objArrM1148e = C1075m.m1148e(listSubList);
                listSubList.clear();
                C1075m.g gVar3 = new C1075m.g("T", "T", null, (InterfaceC1077o) objArrM1148e[0], (InterfaceC1076n) objArrM1148e[1], false, true);
                listSubList.add(gVar3);
                listSubList.add(gVar3);
            }
            c1075m.m1151b(4);
            c1075m.m1152c("H");
            c1075m.m1151b(5);
            c1075m.m1152c("M");
            c1075m.m1151b(9);
            c1075m.m1152c("S");
            C2158b c2158bM1149f = C1075m.m1149f(c1075m.f2141d, c1075m.f2142e, c1075m.f2143f);
            for (C1075m.c cVar : c1075m.f2144g) {
                if (cVar != null) {
                    C1075m.c[] cVarArr = c1075m.f2144g;
                    HashSet hashSet = new HashSet();
                    HashSet hashSet2 = new HashSet();
                    for (C1075m.c cVar2 : cVarArr) {
                        if (cVar2 != null && !cVar.equals(cVar2)) {
                            hashSet.add(cVar2.f2156g);
                            hashSet2.add(cVar2.f2157h);
                        }
                    }
                    C1075m.f fVar = cVar.f2156g;
                    if (fVar != null) {
                        fVar.mo1168c(hashSet);
                    }
                    C1075m.f fVar2 = cVar.f2157h;
                    if (fVar2 != null) {
                        fVar2.mo1168c(hashSet2);
                    }
                }
            }
            c1075m.f2144g = (C1075m.c[]) c1075m.f2144g.clone();
            f6173a = c2158bM1149f;
        }
        return f6173a;
    }

    /* renamed from: H */
    public static <T> void m2468H(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a) {
        Objects.requireNonNull(interfaceC0446f, "onNext is null");
        Objects.requireNonNull(interfaceC0446f2, "onError is null");
        Objects.requireNonNull(interfaceC0441a, "onComplete is null");
        m2469I(interfaceC2125q, new C1010o(interfaceC0446f, interfaceC0446f2, interfaceC0441a, C0870a.f1301d));
    }

    /* renamed from: I */
    public static <T> void m2469I(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2127s<? super T> interfaceC2127s) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        C1002g c1002g = new C1002g(linkedBlockingQueue);
        interfaceC2127s.onSubscribe(c1002g);
        interfaceC2125q.subscribe(c1002g);
        while (!c1002g.isDisposed()) {
            Object objPoll = linkedBlockingQueue.poll();
            if (objPoll == null) {
                try {
                    objPoll = linkedBlockingQueue.take();
                } catch (InterruptedException e7) {
                    c1002g.dispose();
                    interfaceC2127s.onError(e7);
                    return;
                }
            }
            if (c1002g.isDisposed() || interfaceC2125q == C1002g.f1881f || EnumC1776h.m1963b(objPoll, interfaceC2127s)) {
                return;
            }
        }
    }

    /* renamed from: J */
    public static void m2470J(Throwable th) {
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        }
        if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        }
        if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }

    /* renamed from: K */
    public static String m2471K(Member member) {
        StringBuilder sb = new StringBuilder();
        String name = member.getDeclaringClass().getName();
        sb.append(name.substring(name.lastIndexOf(46) + 1, name.length()));
        sb.append('.');
        sb.append(member.getName());
        return sb.toString();
    }

    /* renamed from: L */
    public static <T> boolean m2472L(Object obj, InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, InterfaceC2111c interfaceC2111c) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        if (!(obj instanceof Callable)) {
            return false;
        }
        InterfaceC2112d interfaceC2112d = null;
        try {
            C0000a c0000a = (Object) ((Callable) obj).call();
            if (c0000a != null) {
                InterfaceC2112d interfaceC2112dApply = interfaceC0454n.apply(c0000a);
                Objects.requireNonNull(interfaceC2112dApply, "The mapper returned a null CompletableSource");
                interfaceC2112d = interfaceC2112dApply;
            }
            if (interfaceC2112d == null) {
                interfaceC2111c.onSubscribe(enumC0516d);
                interfaceC2111c.onComplete();
            } else {
                interfaceC2112d.mo2552b(interfaceC2111c);
            }
            return true;
        } catch (Throwable th) {
            m2470J(th);
            interfaceC2111c.onSubscribe(enumC0516d);
            interfaceC2111c.onError(th);
            return true;
        }
    }

    /* renamed from: M */
    public static <T, R> boolean m2473M(Object obj, InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n, InterfaceC2127s<? super R> interfaceC2127s) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        if (!(obj instanceof Callable)) {
            return false;
        }
        InterfaceC2118j<? extends R> interfaceC2118j = null;
        try {
            C0000a c0000a = (Object) ((Callable) obj).call();
            if (c0000a != null) {
                InterfaceC2118j<? extends R> interfaceC2118jApply = interfaceC0454n.apply(c0000a);
                Objects.requireNonNull(interfaceC2118jApply, "The mapper returned a null MaybeSource");
                interfaceC2118j = interfaceC2118jApply;
            }
            if (interfaceC2118j == null) {
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onComplete();
            } else {
                interfaceC2118j.mo2555b(new C1147a(interfaceC2127s));
            }
            return true;
        } catch (Throwable th) {
            m2470J(th);
            interfaceC2127s.onSubscribe(enumC0516d);
            interfaceC2127s.onError(th);
            return true;
        }
    }

    /* renamed from: N */
    public static <T, R> boolean m2474N(Object obj, InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, InterfaceC2127s<? super R> interfaceC2127s) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        if (!(obj instanceof Callable)) {
            return false;
        }
        InterfaceC2131w<? extends R> interfaceC2131w = null;
        try {
            C0000a c0000a = (Object) ((Callable) obj).call();
            if (c0000a != null) {
                InterfaceC2131w<? extends R> interfaceC2131wApply = interfaceC0454n.apply(c0000a);
                Objects.requireNonNull(interfaceC2131wApply, "The mapper returned a null SingleSource");
                interfaceC2131w = interfaceC2131wApply;
            }
            if (interfaceC2131w == null) {
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onComplete();
            } else {
                interfaceC2131w.mo2562b(new C1435b.a(interfaceC2127s));
            }
            return true;
        } catch (Throwable th) {
            m2470J(th);
            interfaceC2127s.onSubscribe(enumC0516d);
            interfaceC2127s.onError(th);
            return true;
        }
    }

    /* renamed from: O */
    public static Throwable m2475O(Throwable th) {
        if (th == null) {
            throw new IllegalArgumentException("Cannot unwrap null throwable");
        }
        Throwable th2 = th;
        while (th != null) {
            th2 = th;
            th = th.getCause();
        }
        return th2;
    }

    /* renamed from: P */
    public static void m2476P(AtomicLong atomicLong, long j7) {
        long j8 = atomicLong.get();
        while (j7 > j8 && !atomicLong.compareAndSet(j8, j7)) {
            j8 = atomicLong.get();
        }
    }

    /* renamed from: Q */
    public static void m2477Q(AbstractC0471b abstractC0471b, int i7, int i8, int i9) {
        if (i7 < i8 || i7 > i9) {
            throw new C0479j(((AbstractC1015b) abstractC0471b).f1911a, Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9));
        }
    }

    /* renamed from: a */
    public static long m2478a(AtomicLong atomicLong, long j7) {
        long j8;
        long j9;
        do {
            j8 = atomicLong.get();
            j9 = RecyclerView.FOREVER_NS;
            if (j8 == RecyclerView.FOREVER_NS) {
                return RecyclerView.FOREVER_NS;
            }
            long j10 = j8 + j7;
            if (j10 >= 0) {
                j9 = j10;
            }
        } while (!atomicLong.compareAndSet(j8, j9));
        return j8;
    }

    /* renamed from: b */
    public static String m2479b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i7 = 0; i7 < bArr.length; i7++) {
            int i8 = bArr[i7];
            if (i8 < 0) {
                i8 += 256;
            }
            if (i8 < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i8));
        }
        return stringBuffer.toString();
    }

    /* renamed from: c */
    public static <T, U> boolean m2480c(boolean z6, boolean z7, InterfaceC2127s<?> interfaceC2127s, boolean z8, InterfaceC0955h<?> interfaceC0955h, InterfaceC2153b interfaceC2153b, AbstractC1011p abstractC1011p) {
        if (abstractC1011p.f1907h) {
            interfaceC0955h.clear();
            interfaceC2153b.dispose();
            return true;
        }
        if (!z6) {
            return false;
        }
        if (z8) {
            if (!z7) {
                return false;
            }
            if (interfaceC2153b != null) {
                interfaceC2153b.dispose();
            }
            Throwable th = abstractC1011p.f1909j;
            if (th != null) {
                interfaceC2127s.onError(th);
            } else {
                interfaceC2127s.onComplete();
            }
            return true;
        }
        Throwable th2 = abstractC1011p.f1909j;
        if (th2 != null) {
            interfaceC0955h.clear();
            if (interfaceC2153b != null) {
                interfaceC2153b.dispose();
            }
            interfaceC2127s.onError(th2);
            return true;
        }
        if (!z7) {
            return false;
        }
        if (interfaceC2153b != null) {
            interfaceC2153b.dispose();
        }
        interfaceC2127s.onComplete();
        return true;
    }

    /* renamed from: d */
    public static String m2481d(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0 || (str.length() > 1 && Character.isUpperCase(str.charAt(1)))) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
    
        r1 = r15.m1025f(-r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if (r1 != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
    
        return;
     */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T, U> void m2482e(p040e4.InterfaceC0954g<T> r11, p194y3.InterfaceC2127s<? super U> r12, boolean r13, p201z3.InterfaceC2153b r14, p048f4.AbstractC1011p r15) {
        /*
            r0 = 1
            r1 = 1
        L2:
            boolean r2 = r15.f1908i
            boolean r3 = r11.isEmpty()
            r4 = r12
            r5 = r13
            r6 = r11
            r7 = r14
            r8 = r15
            boolean r2 = m2480c(r2, r3, r4, r5, r6, r7, r8)
            if (r2 == 0) goto L14
            return
        L14:
            boolean r3 = r15.f1908i
            java.lang.Object r2 = r11.poll()
            if (r2 != 0) goto L1e
            r10 = 1
            goto L20
        L1e:
            r4 = 0
            r10 = 0
        L20:
            r4 = r10
            r5 = r12
            r6 = r13
            r7 = r11
            r8 = r14
            r9 = r15
            boolean r3 = m2480c(r3, r4, r5, r6, r7, r8, r9)
            if (r3 == 0) goto L2d
            return
        L2d:
            if (r10 == 0) goto L37
            int r1 = -r1
            int r1 = r15.m1025f(r1)
            if (r1 != 0) goto L2
            return
        L37:
            r15.mo1020a(r12, r2)
            goto L14
        */
        throw new UnsupportedOperationException("Method not decompiled: p186x2.C2074b.m2482e(e4.g, y3.s, boolean, z3.b, f4.p):void");
    }

    /* renamed from: f */
    public static boolean m2483f(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    /* renamed from: g */
    public static String m2484g(Context context, String str) throws IOException {
        File file = new File(context.getCacheDir(), str);
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i7 = inputStreamOpen.read(bArr);
                        if (i7 <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i7);
                    }
                } finally {
                    fileOutputStream.close();
                }
            } finally {
                inputStreamOpen.close();
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    /* renamed from: h */
    public static Class<?> m2485h(Type type) {
        Class<?> clsM2485h;
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return m2485h(((ParameterizedType) type).getRawType());
        }
        if (!(type instanceof GenericArrayType) || (clsM2485h = m2485h(((GenericArrayType) type).getGenericComponentType())) == null) {
            return null;
        }
        return Array.newInstance(clsM2485h, 0).getClass();
    }

    /* renamed from: i */
    public static DisplayMetrics m2486i() {
        WindowManager windowManager = (WindowManager) C1231b.f2761c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: j */
    public static String m2487j() {
        File externalFilesDir = C1231b.f2761c.getExternalFilesDir("");
        if (externalFilesDir != null) {
            return externalFilesDir.getAbsolutePath();
        }
        Application application = C1231b.f2761c;
        if (!Environment.getExternalStorageState().equals("mounted") && Environment.isExternalStorageRemovable()) {
            return application.getCacheDir().getAbsolutePath();
        }
        StringBuilder sb = new StringBuilder();
        File externalCacheDir = C1231b.f2761c.getExternalCacheDir();
        if (externalCacheDir != null) {
            sb.append(externalCacheDir.getAbsolutePath());
        } else {
            sb.append(Environment.getExternalStorageDirectory().getPath());
            sb.append("/Android/data/");
            sb.append(C1231b.f2761c.getPackageName());
            sb.append("/cache");
        }
        return sb.toString();
    }

    /* renamed from: k */
    public static Method m2488k(Class cls, String str) throws SecurityException {
        while (cls != null && cls != Object.class) {
            for (Method method : cls.getDeclaredMethods()) {
                String name = method.getName();
                if (method.getParameterTypes().length == 0) {
                    if (name.startsWith("get")) {
                        if (m2481d(name.substring(3)).equals(str)) {
                            return method;
                        }
                    } else if (name.startsWith("is") && m2481d(name.substring(2)).equals(str)) {
                        return method;
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    /* renamed from: l */
    public static int m2489l() {
        WindowManager windowManager = (WindowManager) C1231b.f2761c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /* renamed from: m */
    public static IjkMediaPlayer m2490m(IMediaPlayer iMediaPlayer) {
        if (iMediaPlayer == null) {
            return null;
        }
        if (iMediaPlayer instanceof IjkMediaPlayer) {
            return (IjkMediaPlayer) iMediaPlayer;
        }
        if (!(iMediaPlayer instanceof MediaPlayerProxy)) {
            return null;
        }
        MediaPlayerProxy mediaPlayerProxy = (MediaPlayerProxy) iMediaPlayer;
        if (mediaPlayerProxy.getInternalMediaPlayer() instanceof IjkMediaPlayer) {
            return (IjkMediaPlayer) mediaPlayerProxy.getInternalMediaPlayer();
        }
        return null;
    }

    /* renamed from: n */
    public static KeyStore m2491n(InputStream inputStream, String str, String str2, String str3, String str4) throws IOException {
        char[] charArray = null;
        if (inputStream == null && str == null) {
            return null;
        }
        if (inputStream == null) {
            try {
                inputStream = AbstractC2038e.m2398d(str).mo2393b();
            } catch (Throwable th) {
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }
        KeyStore keyStore = str3 != null ? KeyStore.getInstance(str2, str3) : KeyStore.getInstance(str2);
        if (str4 != null) {
            charArray = str4.toCharArray();
        }
        keyStore.load(inputStream, charArray);
        if (inputStream != null) {
            inputStream.close();
        }
        return keyStore;
    }

    /* renamed from: o */
    public static Method m2492o(Class cls, String str) {
        for (Class superclass = cls; superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                return superclass.getDeclaredMethod(str, new Class[0]);
            } catch (NoSuchMethodException unused) {
            }
        }
        StringBuilder sbM112a = C0413b.m112a("No such method: ");
        sbM112a.append(cls.getName());
        sbM112a.append('.');
        sbM112a.append(str);
        throw new IllegalArgumentException(sbM112a.toString());
    }

    /* renamed from: p */
    public static String m2493p(String str) {
        if (str.startsWith("get")) {
            return m2481d(str.substring(3));
        }
        if (str.startsWith("is")) {
            return m2481d(str.substring(2));
        }
        if (str.startsWith("set")) {
            return m2481d(str.substring(3));
        }
        return null;
    }

    /* renamed from: q */
    public static List<Method> m2494q(Class cls, Class cls2) throws SecurityException {
        ArrayList arrayList = new ArrayList();
        while (cls != null && cls != Object.class) {
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(cls2)) {
                    arrayList.add(method);
                }
            }
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    /* renamed from: r */
    public static int m2495r() {
        WindowManager windowManager = (WindowManager) C1231b.f2761c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* renamed from: s */
    public static void m2496s(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) C1231b.f2761c.getSystemService("input_method");
        if (view == null || inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /* renamed from: t */
    public static Object m2497t(Method method, Object obj, Object... objArr) throws Exception {
        String strSubstring;
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalArgumentException e7) {
            StringBuilder sbM112a = C0413b.m112a("Could not invoke method by reflection: ");
            sbM112a.append(m2471K(method));
            String string = sbM112a.toString();
            if (objArr != null && objArr.length > 0) {
                StringBuilder sbM94a = C0080b.m94a(string, " with parameters: (");
                if (objArr.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Object obj2 : objArr) {
                        sb.append(", ");
                        if (obj2 == null) {
                            sb.append("null");
                        } else {
                            sb.append(obj2.getClass().getName());
                        }
                    }
                    strSubstring = sb.substring(2);
                } else {
                    strSubstring = "";
                }
                sbM94a.append(strSubstring);
                sbM94a.append(')');
                string = sbM94a.toString();
            }
            StringBuilder sbM94a2 = C0080b.m94a(string, " on: ");
            sbM94a2.append(obj.getClass().getName());
            throw new IllegalArgumentException(sbM94a2.toString(), e7);
        } catch (InvocationTargetException e8) {
            if (e8.getCause() instanceof Exception) {
                throw ((Exception) e8.getCause());
            }
            throw e8;
        }
    }

    /* renamed from: u */
    public static Collection<? extends CRL> m2498u(String str) throws IOException {
        InputStream inputStreamMo2393b = null;
        if (str == null) {
            return null;
        }
        try {
            inputStreamMo2393b = AbstractC2038e.m2398d(str).mo2393b();
            return CertificateFactory.getInstance("X.509").generateCRLs(inputStreamMo2393b);
        } finally {
            if (inputStreamMo2393b != null) {
                inputStreamMo2393b.close();
            }
        }
    }

    /* renamed from: v */
    public static String m2499v(int i7) {
        if (i7 == 100) {
            return " Continue";
        }
        switch (i7) {
            case 200:
                return " OK";
            case 201:
                return " Created";
            case 202:
                return " Accepted";
            case 203:
                return " Non-Authoritative Information";
            case 204:
                return " No Content";
            case 205:
                return " Reset Content";
            case 206:
                return " Partial Content";
            default:
                switch (i7) {
                    case IjkMediaCodecInfo.RANK_SECURE /* 300 */:
                        return " Multiple Choices";
                    case 301:
                        return " Moved Permanently";
                    case 302:
                        return " Temporary Redirect";
                    case 303:
                        return " See Other";
                    case 304:
                        return " Not Modified";
                    case 305:
                        return " Use Proxy";
                    default:
                        switch (i7) {
                            case 400:
                                return " Bad Request";
                            case 401:
                                return " Unauthorized";
                            case 402:
                                return " Payment Required";
                            case 403:
                                return " Forbidden";
                            case 404:
                                return " Not Found";
                            case 405:
                                return " Method Not Allowed";
                            case 406:
                                return " Not Acceptable";
                            case 407:
                                return " Proxy Authentication Required";
                            case 408:
                                return " Request Time-Out";
                            case 409:
                                return " Conflict";
                            case 410:
                                return " Gone";
                            case 411:
                                return " Length Required";
                            case 412:
                                return " Precondition Failed";
                            case 413:
                                return " Request Entity Too Large";
                            case 414:
                                return " Request-URI Too Large";
                            case 415:
                                return " Unsupported Media Type";
                            default:
                                switch (i7) {
                                    case 500:
                                        return " Internal Server Error";
                                    case 501:
                                        return " Not Implemented";
                                    case 502:
                                        return " Bad Gateway";
                                    case 503:
                                        return " Service Unavailable";
                                    case 504:
                                        return " Gateway Timeout";
                                    case 505:
                                        return " HTTP Version Not Supported";
                                    default:
                                        return "";
                                }
                        }
                }
        }
    }

    /* renamed from: w */
    public static void m2500w(InterfaceC2127s<?> interfaceC2127s, AtomicInteger atomicInteger, C1771c c1771c) {
        if (atomicInteger.getAndIncrement() == 0) {
            Throwable thM1959b = C1774f.m1959b(c1771c);
            if (thM1959b != null) {
                interfaceC2127s.onError(thM1959b);
            } else {
                interfaceC2127s.onComplete();
            }
        }
    }

    /* renamed from: x */
    public static void m2501x(InterfaceC2127s<?> interfaceC2127s, Throwable th, AtomicInteger atomicInteger, C1771c c1771c) {
        if (!C1774f.m1958a(c1771c, th)) {
            C1908a.m2205b(th);
        } else if (atomicInteger.getAndIncrement() == 0) {
            interfaceC2127s.onError(C1774f.m1959b(c1771c));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: y */
    public static <T> void m2502y(InterfaceC2127s<? super T> interfaceC2127s, T t6, AtomicInteger atomicInteger, C1771c c1771c) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            interfaceC2127s.onNext(t6);
            if (atomicInteger.decrementAndGet() != 0) {
                Throwable thM1959b = C1774f.m1959b(c1771c);
                if (thM1959b != null) {
                    interfaceC2127s.onError(thM1959b);
                } else {
                    interfaceC2127s.onComplete();
                }
            }
        }
    }

    /* renamed from: z */
    public static long m2503z(AtomicLong atomicLong, long j7) {
        long j8;
        long j9;
        do {
            j8 = atomicLong.get();
            if (j8 == RecyclerView.FOREVER_NS) {
                return RecyclerView.FOREVER_NS;
            }
            j9 = j8 - j7;
            if (j9 < 0) {
                C1908a.m2205b(new IllegalStateException(C0534b.m341a("More produced than requested: ", j9)));
                j9 = 0;
            }
        } while (!atomicLong.compareAndSet(j8, j9));
        return j9;
    }
}
