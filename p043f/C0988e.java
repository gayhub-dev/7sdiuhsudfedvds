package p043f;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.C0072a;
import android.support.constraint.motion.C0080b;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.module.player.VideoPlayer;
import com.cctv.p025tv.mvp.p026ui.fragment.AboutUsFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.AudioTrackSwitchFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.ChangeNameFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.CheckPlayerFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.DeviceCheckFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.DlnaModifyNameFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.MainFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.PlayCheckFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.PrivacyPolicyFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.SharpnessSwitchFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.SystemFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.UserAgreementFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.WelcomeFragment;
import com.ctvit.network.utils.CtvitHttpHeaders;
import com.tencent.mars.xlog.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.model.types.UDN;
import org.json.JSONException;
import org.json.JSONObject;
import p009b.C0413b;
import p078j2.C1186a;
import p078j2.C1197l;
import p117o1.C1579a;
import p118o2.C1581b;
import p145r3.C1836c;
import p150s1.C1868e;
import p151s2.C1869a;
import p156t0.C1896a;
import p163u0.C1970a;
import p170v0.C2000e;
import p186x2.C2073a;
import p198z0.C2145a;
import p198z0.C2146b;
import p200z2.C2150a;

/* compiled from: MDMainHandler.java */
/* renamed from: f.e */
/* loaded from: classes.dex */
public class C0988e {

    /* renamed from: a */
    public static Handler f1823a;

    /* renamed from: b */
    public static String f1824b;

    /* renamed from: c */
    public static int f1825c;

    /* renamed from: d */
    public static String f1826d;

    /* renamed from: e */
    public static String f1827e;

    /* renamed from: f */
    public static Context f1828f;

    /* renamed from: g */
    public static String f1829g;

    /* renamed from: h */
    public static String f1830h;

    /* renamed from: i */
    public static String f1831i;

    /* renamed from: A */
    public static int m957A(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* renamed from: B */
    public static void m958B(FragmentManager fragmentManager, String str) {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (str.equals(fragment.getTag()) && !fragment.isHidden()) {
                StringBuilder sbM112a = C0413b.m112a("隐藏Fragment：");
                sbM112a.append(fragment.getTag());
                C2073a.m2459d(sbM112a.toString());
                fragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
                return;
            }
        }
    }

    /* renamed from: C */
    public static Object m959C(String str, String str2, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr);
        } catch (ClassNotFoundException e7) {
            e7.printStackTrace();
            return null;
        } catch (IllegalAccessException e8) {
            e8.printStackTrace();
            return null;
        } catch (IllegalArgumentException e9) {
            e9.printStackTrace();
            return null;
        } catch (NoSuchMethodException e10) {
            e10.printStackTrace();
            return null;
        } catch (SecurityException e11) {
            e11.printStackTrace();
            return null;
        } catch (InvocationTargetException e12) {
            e12.printStackTrace();
            return null;
        }
    }

    /* renamed from: D */
    public static boolean m960D() {
        return C1197l.m1422b(MyApplication.f561e, "SHARPNESS_SWITCH_HINT", true);
    }

    /* renamed from: E */
    public static boolean m961E(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    /* renamed from: F */
    public static boolean m962F() {
        return C1197l.m1422b(MyApplication.f561e, "IS_NETSPEED_TEST", false);
    }

    /* renamed from: G */
    public static void m963G(VideoPlayer videoPlayer, String str, long j7, long j8, String str2) {
        HashMap map = new HashMap();
        m967K(videoPlayer, map);
        if (!TextUtils.isEmpty(str)) {
            map.put("state", str);
        }
        if (j8 > 0) {
            map.put("buffer_duration", String.valueOf(j8));
        }
        map.put("play_duration", String.valueOf(j7));
        if (!TextUtils.isEmpty(str2)) {
            map.put("play_error_info", str2);
        }
        m973Q("play_quality", "播放质量", map, videoPlayer);
    }

    /* renamed from: H */
    public static void m964H(VideoPlayer videoPlayer, String str, float f7) {
        if (videoPlayer == null) {
            return;
        }
        HashMap map = new HashMap();
        m967K(videoPlayer, map);
        if (!TextUtils.isEmpty(str)) {
            map.put("state", str);
        }
        map.put("switch_speed", String.valueOf(f7));
        C2073a.m2459d("qualityEventCCTVReport playState =" + str + "; switch_speed = " + f7 + "; cctvAgentData =" + map.toString());
        m966J("play_quality", "播放质量", map, videoPlayer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r13v8, types: [java.io.InputStreamReader] */
    /* renamed from: I */
    public static String m965I(String str) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e7;
        try {
            try {
                fileInputStream = new FileInputStream((String) str);
            } catch (Exception e8) {
                fileInputStream = null;
                e7 = e8;
                str = 0;
            } catch (Throwable th2) {
                fileInputStream = null;
                th = th2;
                str = 0;
            }
            try {
                str = new InputStreamReader(fileInputStream, "UTF-8");
            } catch (Exception e9) {
                e7 = e9;
                str = 0;
            } catch (Throwable th3) {
                th = th3;
                str = 0;
                if (str != 0) {
                    try {
                        str.close();
                    } catch (IOException e10) {
                        C2000e.a aVar = new C2000e.a();
                        aVar.f5839a = "S10001";
                        aVar.f5840b = "SDK容错异常";
                        aVar.f5841c = e10.getMessage();
                        aVar.f5842d = C2145a.m2576d(e10);
                        aVar.f5843e = C2145a.m2574b();
                        aVar.f5844f = C2145a.m2580h(C1970a.f5748f);
                        C2146b.m2589c(C1970a.f5748f);
                        C2146b.m2588b(C1970a.f5748f);
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("key", "sdk_error_d1");
                            jSONObject.put("value", aVar.m2336b());
                        } catch (JSONException e11) {
                            m977c(new C2000e(e11));
                            if (C1970a.f5743a) {
                                e11.printStackTrace();
                            }
                        }
                        m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject.toString());
                        if (C1970a.f5743a) {
                            e10.printStackTrace();
                        }
                        throw th;
                    }
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
            try {
                StringBuffer stringBuffer = new StringBuffer(10240);
                while (str.ready()) {
                    stringBuffer.append((char) str.read());
                }
                str.close();
                fileInputStream.close();
                stringBuffer.trimToSize();
                String string = stringBuffer.toString();
                try {
                    str.close();
                    fileInputStream.close();
                } catch (IOException e12) {
                    C2000e.a aVar2 = new C2000e.a();
                    aVar2.f5839a = "S10001";
                    aVar2.f5840b = "SDK容错异常";
                    aVar2.f5841c = e12.getMessage();
                    aVar2.f5842d = C2145a.m2576d(e12);
                    aVar2.f5843e = C2145a.m2574b();
                    aVar2.f5844f = C2145a.m2580h(C1970a.f5748f);
                    C2146b.m2589c(C1970a.f5748f);
                    C2146b.m2588b(C1970a.f5748f);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("key", "sdk_error_d1");
                        jSONObject2.put("value", aVar2.m2336b());
                    } catch (JSONException e13) {
                        m977c(new C2000e(e13));
                        if (C1970a.f5743a) {
                            e13.printStackTrace();
                        }
                    }
                    m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject2.toString());
                    if (C1970a.f5743a) {
                        e12.printStackTrace();
                    }
                }
                return string;
            } catch (Exception e14) {
                e7 = e14;
                C2000e.a aVar3 = new C2000e.a();
                aVar3.f5839a = "S10001";
                aVar3.f5840b = "SDK容错异常";
                aVar3.f5841c = e7.getMessage();
                aVar3.f5842d = C2145a.m2576d(e7);
                aVar3.f5843e = C2145a.m2574b();
                aVar3.f5844f = C2145a.m2580h(C1970a.f5748f);
                C2146b.m2589c(C1970a.f5748f);
                C2146b.m2588b(C1970a.f5748f);
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("key", "sdk_error_d1");
                    jSONObject3.put("value", aVar3.m2336b());
                } catch (JSONException e15) {
                    m977c(new C2000e(e15));
                    if (C1970a.f5743a) {
                        e15.printStackTrace();
                    }
                }
                m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject3.toString());
                if (C1970a.f5743a) {
                    e7.printStackTrace();
                }
                if (str != 0) {
                    try {
                        str.close();
                    } catch (IOException e16) {
                        C2000e.a aVar4 = new C2000e.a();
                        aVar4.f5839a = "S10001";
                        aVar4.f5840b = "SDK容错异常";
                        aVar4.f5841c = e16.getMessage();
                        aVar4.f5842d = C2145a.m2576d(e16);
                        aVar4.f5843e = C2145a.m2574b();
                        aVar4.f5844f = C2145a.m2580h(C1970a.f5748f);
                        C2146b.m2589c(C1970a.f5748f);
                        C2146b.m2588b(C1970a.f5748f);
                        JSONObject jSONObject4 = new JSONObject();
                        try {
                            jSONObject4.put("key", "sdk_error_d1");
                            jSONObject4.put("value", aVar4.m2336b());
                        } catch (JSONException e17) {
                            m977c(new C2000e(e17));
                            if (C1970a.f5743a) {
                                e17.printStackTrace();
                            }
                        }
                        m976b("sdk_exception_cache", "sdkExceptionEvent.txt", jSONObject4.toString());
                        if (!C1970a.f5743a) {
                            return "";
                        }
                        e16.printStackTrace();
                        return "";
                    }
                }
                if (fileInputStream == null) {
                    return "";
                }
                fileInputStream.close();
                return "";
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: J */
    public static void m966J(String str, String str2, Map<String, String> map, VideoPlayer videoPlayer) {
        if (videoPlayer.getPlayEntity() != null) {
            if (videoPlayer.getPlayEntity().m1008b() != null) {
                map.put("high_id", videoPlayer.getPlayEntity().m1008b().f1859j);
            }
            map.put("rate", videoPlayer.getPlayEntity().m1009c());
        }
        C1579a.m1830a().m1831b(str, str2, map);
    }

    /* renamed from: K */
    public static void m967K(VideoPlayer videoPlayer, Map<String, String> map) {
        map.put("play_id", videoPlayer.getPlayerId());
        String strSubstring = videoPlayer.getPlayEntity().f1837a;
        if (!TextUtils.isEmpty(strSubstring) && strSubstring.contains("://")) {
            strSubstring = strSubstring.substring(strSubstring.indexOf("://") + 3);
        }
        map.put("identify", strSubstring);
        map.put("name", videoPlayer.getPlayEntity().f1838b);
        boolean z6 = videoPlayer.getPlayEntity().f1839c;
        String str = DiskLruCache.VERSION_1;
        map.put("play_type", z6 ? DiskLruCache.VERSION_1 : "2");
        if (!videoPlayer.getPlayEntity().f1845i) {
            str = "2";
        }
        map.put("mime_type", str);
        map.put("resource", videoPlayer.getPlayerView().getCurrentPlayPath());
        map.put("is_dolby", "0");
        map.put("is_vr", "0");
        map.put("five_pg", videoPlayer.getPlayEntity().f1849m);
    }

    /* renamed from: L */
    public static void m968L(String str, String str2, Object obj, Object obj2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (ClassNotFoundException e7) {
            e7.printStackTrace();
        } catch (IllegalAccessException e8) {
            e8.printStackTrace();
        } catch (IllegalArgumentException e9) {
            e9.printStackTrace();
        } catch (NoSuchFieldException e10) {
            e10.printStackTrace();
        } catch (SecurityException e11) {
            e11.printStackTrace();
        }
    }

    /* renamed from: M */
    public static void m969M(boolean z6) {
        C2150a.m2591b("LISTEN_STATE", Boolean.valueOf(z6));
    }

    /* renamed from: N */
    public static void m970N(boolean z6) {
        C2150a.m2591b("VIDEO_VISIBLE", Boolean.valueOf(z6));
    }

    /* renamed from: O */
    public static void m971O(FragmentManager fragmentManager, String str) {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (!fragment.isHidden() && !str.equals(fragment.getTag())) {
                FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
                fragmentTransactionBeginTransaction.hide(fragment);
                fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
            }
        }
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(str);
        if (fragmentFindFragmentByTag != null) {
            if (fragmentFindFragmentByTag.isHidden()) {
                StringBuilder sbM112a = C0413b.m112a("显示Fragment：");
                sbM112a.append(fragmentFindFragmentByTag.getTag());
                C2073a.m2459d(sbM112a.toString());
                FragmentTransaction fragmentTransactionBeginTransaction2 = fragmentManager.beginTransaction();
                fragmentTransactionBeginTransaction2.show(fragmentFindFragmentByTag);
                fragmentTransactionBeginTransaction2.commitNowAllowingStateLoss();
                return;
            }
            return;
        }
        if ("MAIN_FRAGMENT".equals(str)) {
            fragmentFindFragmentByTag = new MainFragment();
        } else if ("VIDEO_FRAGMENT".equals(str)) {
            fragmentFindFragmentByTag = new VideoFragment();
        } else if ("VERSION_UPDATE_FRAGMENT".equals(str)) {
            fragmentFindFragmentByTag = new VersionUpdateFragment();
        } else if ("DLNA_MODIFY_FRAGMENT".equals(str)) {
            fragmentFindFragmentByTag = new DlnaModifyNameFragment();
        } else if ("SYSTEM_SETTING".equals(str)) {
            fragmentFindFragmentByTag = new SystemFragment();
        } else if ("TV_NAME".equals(str)) {
            fragmentFindFragmentByTag = new ChangeNameFragment();
        } else if ("SHARPNESS_SWITCH".equals(str)) {
            fragmentFindFragmentByTag = new SharpnessSwitchFragment();
        } else if ("ABOUT_US".equals(str)) {
            fragmentFindFragmentByTag = new AboutUsFragment();
        } else if ("PRIVACYPOLICY".equals(str)) {
            fragmentFindFragmentByTag = new PrivacyPolicyFragment();
        } else if ("PLAY_CHECK".equals(str)) {
            fragmentFindFragmentByTag = new PlayCheckFragment();
        } else if ("CHECK_PLAYER".equals(str)) {
            fragmentFindFragmentByTag = new CheckPlayerFragment();
        } else if ("DEVICE_CHECK".equals(str)) {
            fragmentFindFragmentByTag = new DeviceCheckFragment();
        } else if ("USER_AGREEMENT".equals(str)) {
            fragmentFindFragmentByTag = new UserAgreementFragment();
        } else if ("WELCOME".equals(str)) {
            fragmentFindFragmentByTag = new WelcomeFragment();
        } else if ("AUDIO_TRACK_SWITCH".equals(str)) {
            fragmentFindFragmentByTag = new AudioTrackSwitchFragment();
        }
        if (fragmentFindFragmentByTag != null) {
            C2073a.m2459d("添加Fragment：" + str);
            FragmentTransaction fragmentTransactionBeginTransaction3 = fragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction3.add(R.id.frameLayout, fragmentFindFragmentByTag, str);
            fragmentTransactionBeginTransaction3.show(fragmentFindFragmentByTag);
            fragmentTransactionBeginTransaction3.commitNowAllowingStateLoss();
        }
    }

    /* renamed from: P */
    public static UDN m972P() {
        C1581b.m1836e();
        StringBuilder sbM112a = C0413b.m112a(C1869a.m2142b(C1581b.f4744m));
        sbM112a.append(Build.MODEL);
        sbM112a.append(Build.MANUFACTURER);
        try {
            return new UDN(new UUID(new BigInteger(-1, MessageDigest.getInstance("SHA-256").digest(sbM112a.toString().getBytes())).longValue(), 1236935621) + "_cctv");
        } catch (Exception e7) {
            return new UDN(e7.getMessage() != null ? e7.getMessage() : "UNKNOWN");
        }
    }

    /* renamed from: Q */
    public static void m973Q(String str, String str2, Map<String, String> map, VideoPlayer videoPlayer) {
        C2073a.m2459d("id =" + str + "; name = " + str2 + "; cctvAgentData =" + map.toString());
        C1896a.m2197a().m2198b(str, str2, map);
        m966J(str, str2, map, videoPlayer);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0072 A[Catch: IOException -> 0x006e, TRY_LEAVE, TryCatch #7 {IOException -> 0x006e, blocks: (B:43:0x006a, B:47:0x0072), top: B:59:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: R */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m974R(java.lang.String r3, java.lang.String r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
            java.lang.String r2 = "UTF-8"
            r3.<init>(r1, r2)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L31
            r3.write(r4)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2d
            r3.close()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2d
            r3.close()     // Catch: java.io.IOException -> L1a
            r1.close()     // Catch: java.io.IOException -> L1a
            goto L64
        L1a:
            r3 = move-exception
            v0.e r4 = new v0.e
            r4.<init>(r3)
            m977c(r4)
            boolean r4 = p163u0.C1970a.f5743a
            if (r4 == 0) goto L64
        L27:
            r3.printStackTrace()
            goto L64
        L2b:
            r4 = move-exception
            goto L67
        L2d:
            r4 = move-exception
            goto L33
        L2f:
            r4 = move-exception
            goto L68
        L31:
            r4 = move-exception
            r3 = r0
        L33:
            r0 = r1
            goto L3a
        L35:
            r4 = move-exception
            r1 = r0
            goto L68
        L38:
            r4 = move-exception
            r3 = r0
        L3a:
            v0.e r1 = new v0.e     // Catch: java.lang.Throwable -> L65
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L65
            m977c(r1)     // Catch: java.lang.Throwable -> L65
            boolean r1 = p163u0.C1970a.f5743a     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L49
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L65
        L49:
            if (r3 == 0) goto L51
            r3.close()     // Catch: java.io.IOException -> L4f
            goto L51
        L4f:
            r3 = move-exception
            goto L57
        L51:
            if (r0 == 0) goto L64
            r0.close()     // Catch: java.io.IOException -> L4f
            goto L64
        L57:
            v0.e r4 = new v0.e
            r4.<init>(r3)
            m977c(r4)
            boolean r4 = p163u0.C1970a.f5743a
            if (r4 == 0) goto L64
            goto L27
        L64:
            return
        L65:
            r4 = move-exception
            r1 = r0
        L67:
            r0 = r3
        L68:
            if (r0 == 0) goto L70
            r0.close()     // Catch: java.io.IOException -> L6e
            goto L70
        L6e:
            r3 = move-exception
            goto L76
        L70:
            if (r1 == 0) goto L85
            r1.close()     // Catch: java.io.IOException -> L6e
            goto L85
        L76:
            v0.e r0 = new v0.e
            r0.<init>(r3)
            m977c(r0)
            boolean r0 = p163u0.C1970a.f5743a
            if (r0 == 0) goto L85
            r3.printStackTrace()
        L85:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p043f.C0988e.m974R(java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    public static void m975a(FragmentManager fragmentManager) {
        VideoFragment videoFragment;
        if (!C1868e.f5445a && C1186a.m1382b()) {
            Fragment fragment = null;
            for (Fragment fragment2 : fragmentManager.getFragments()) {
                if (!fragment2.isHidden()) {
                    StringBuilder sbM112a = C0413b.m112a("当前显示的Fragment：");
                    sbM112a.append(fragment2.getTag());
                    C2073a.m2459d(sbM112a.toString());
                    fragment = fragment2;
                }
            }
            if (fragment == null) {
                return;
            }
            if ("MAIN_FRAGMENT".equals(fragment.getTag())) {
                C2073a.m2459d("退出APP");
                C2073a.m2459d("Activity是否为Task根：" + fragment.getActivity().isTaskRoot());
                fragment.getActivity().moveTaskToBack(true);
            } else {
                if ("VIDEO_FRAGMENT".equals(fragment.getTag()) && (videoFragment = (VideoFragment) fragmentManager.findFragmentByTag("VIDEO_FRAGMENT")) != null && !videoFragment.isHidden()) {
                    videoFragment.m508l().m441D();
                }
                C2073a.m2459d("回退到首页");
                m971O(fragmentManager, "MAIN_FRAGMENT");
            }
            C0580b.m417c("BACK", fragment.getClass().getSimpleName());
        }
    }

    /* renamed from: b */
    public static String m976b(String str, String str2, String str3) throws Throwable {
        String strM989o = m989o(str);
        StringBuilder sbM112a = C0413b.m112a(strM989o);
        String str4 = File.separator;
        String strM92a = C0072a.m92a(sbM112a, str4, str2);
        StringBuilder sbM94a = C0080b.m94a(strM989o, str4);
        sbM94a.append(UUID.randomUUID().toString());
        String string = sbM94a.toString();
        File file = new File(string);
        if (!file.exists() || file.length() < 1) {
            try {
                file.createNewFile();
            } catch (IOException unused) {
            }
        }
        File file2 = new File(strM92a);
        if (!file2.exists() || file2.length() < 1) {
            try {
                file2.createNewFile();
            } catch (IOException unused2) {
            }
        }
        m974R(string, str3);
        m974R(strM92a, m965I(strM92a) + string + ",");
        return string;
    }

    /* renamed from: c */
    public static String m977c(C2000e c2000e) {
        return m976b("sdk_exception_cache", "sdkExceptionEvent.txt", c2000e.m2335a());
    }

    /* renamed from: d */
    public static void m978d(boolean z6, String str) {
        if (!z6) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: e */
    public static int m979e(Context context, int i7, int i8, int i9) {
        float fFloatValue;
        int iM957A = m957A(context) - i9;
        try {
            double dDoubleValue = Double.valueOf(i7 + "").doubleValue() / Double.valueOf(i8 + "").doubleValue();
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getPercentInstance();
            decimalFormat.applyPattern("00");
            decimalFormat.setMaximumFractionDigits(2);
            fFloatValue = Float.valueOf(decimalFormat.format(dDoubleValue)).floatValue();
        } catch (Exception unused) {
            fFloatValue = 1.0f;
        }
        return Math.round(iM957A / fFloatValue);
    }

    /* renamed from: f */
    public static double m980f(double d7, double d8, int i7) throws IllegalAccessException {
        if (i7 >= 0) {
            return new BigDecimal(Double.toString(d7)).divide(new BigDecimal(Double.toString(d8)), i7, 4).doubleValue();
        }
        throw new IllegalAccessException("精确度不能小于0");
    }

    /* renamed from: g */
    public static int m981g(Context context, float f7) {
        return (int) (((f7 >= 0.0f ? 1 : -1) * 0.5f) + (context.getResources().getDisplayMetrics().density * f7));
    }

    /* renamed from: h */
    public static void m982h(String str) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        f1824b = stackTrace[1].getMethodName();
        f1825c = stackTrace[1].getLineNumber();
        int length = str.length();
        int i7 = RecyclerView.MAX_SCROLL_DURATION;
        int i8 = 0;
        int i9 = 0;
        while (i8 < 100) {
            if (length <= i7) {
                str.substring(i9, length);
                return;
            }
            str.substring(i9, i7);
            i8++;
            i9 = i7;
            i7 += RecyclerView.MAX_SCROLL_DURATION;
        }
    }

    /* renamed from: i */
    public static void m983i(VideoPlayer videoPlayer, long j7) {
        HashMap map = new HashMap();
        m967K(videoPlayer, map);
        map.put("play_duration", String.valueOf(j7));
        m973Q("play_end", "播放结束", map, videoPlayer);
    }

    /* renamed from: j */
    public static String m984j(long j7, String str) {
        return new SimpleDateFormat(str).format(Long.valueOf(j7));
    }

    /* renamed from: k */
    public static String m985k(long j7, boolean z6) {
        int i7 = (int) (j7 / 1000);
        int i8 = i7 % 60;
        int i9 = (i7 / 60) % 60;
        int i10 = i7 / 3600;
        return (!z6 || i10 <= 0) ? String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(i9), Integer.valueOf(i8)) : String.format(Locale.getDefault(), "%02d:%02d:%02d", Integer.valueOf(i10), Integer.valueOf(i9), Integer.valueOf(i8));
    }

    /* renamed from: l */
    public static String m986l(Context context) {
        Objects.requireNonNull(C1896a.m2197a());
        return C2146b.m2587a(context);
    }

    /* renamed from: m */
    public static String m987m(Context context) {
        Objects.requireNonNull(C1896a.m2197a());
        return C2146b.m2588b(context);
    }

    /* renamed from: n */
    public static String m988n() throws Throwable {
        String strM991q = m991q("model name");
        if (strM991q == null || TextUtils.isEmpty(strM991q)) {
            return m998x("ro.product.cpu.abi", "错误");
        }
        if (!strM991q.contains("Processor")) {
            return strM991q;
        }
        String[] strArrSplit = strM991q.split("Processor");
        return strArrSplit.length > 0 ? strArrSplit[0] : strM991q;
    }

    /* renamed from: o */
    public static String m989o(String str) {
        String strM92a = C0072a.m92a(new StringBuilder(), C1970a.f5754l, str);
        File file = new File(strM92a);
        if (!file.exists()) {
            file.mkdirs();
        }
        return strM92a;
    }

    /* renamed from: p */
    public static String m990p(String str, String str2) {
        return C0072a.m92a(C0413b.m112a(m989o(str)), File.separator, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m991q(java.lang.String r4) throws java.lang.Throwable {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3a
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3a
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3a
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3a
        L14:
            java.lang.String r2 = r0.readLine()     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L4a
            if (r2 == 0) goto L41
            boolean r3 = r2.startsWith(r4)     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L4a
            if (r3 == 0) goto L14
            java.lang.String r4 = ":"
            java.lang.String[] r4 = r2.split(r4)     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L4a
            r2 = 1
            r4 = r4[r2]     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L4a
            java.lang.String r4 = r4.trim()     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L4a
            r0.close()     // Catch: java.io.IOException -> L31
            goto L35
        L31:
            r0 = move-exception
            r0.printStackTrace()
        L35:
            return r4
        L36:
            r4 = move-exception
            goto L3c
        L38:
            r4 = move-exception
            goto L4c
        L3a:
            r4 = move-exception
            r0 = r1
        L3c:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L4a
            if (r0 == 0) goto L49
        L41:
            r0.close()     // Catch: java.io.IOException -> L45
            goto L49
        L45:
            r4 = move-exception
            r4.printStackTrace()
        L49:
            return r1
        L4a:
            r4 = move-exception
            r1 = r0
        L4c:
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.io.IOException -> L52
            goto L56
        L52:
            r0 = move-exception
            r0.printStackTrace()
        L56:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p043f.C0988e.m991q(java.lang.String):java.lang.String");
    }

    /* renamed from: r */
    public static String m992r(Context context) {
        Objects.requireNonNull(C1896a.m2197a());
        return C2146b.m2589c(context);
    }

    /* renamed from: s */
    public static Object m993s(String str, Object obj, String str2) throws NoSuchFieldException {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (ClassNotFoundException e7) {
            e7.printStackTrace();
            return null;
        } catch (IllegalAccessException e8) {
            e8.printStackTrace();
            return null;
        } catch (IllegalArgumentException e9) {
            e9.printStackTrace();
            return null;
        } catch (NoSuchFieldException e10) {
            e10.printStackTrace();
            return null;
        } catch (SecurityException e11) {
            e11.printStackTrace();
            return null;
        }
    }

    /* renamed from: t */
    public static Fragment m994t(FragmentManager fragmentManager, String str) {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (str.equals(fragment.getTag())) {
                return fragment;
            }
        }
        return null;
    }

    /* renamed from: u */
    public static HashMap<String, String> m995u(String str, String str2, String str3) {
        HashMap<String, String> map = new HashMap<>();
        StringBuilder sbM112a = C0413b.m112a(" ");
        sbM112a.append(CtvitHttpHeaders.getInstance().headers().get("UID"));
        map.put("UID", sbM112a.toString());
        map.put("Referer", " api.cctv.cn");
        map.put("User-Agent", " cctv_app_tv");
        map.put("APPID", str2);
        map.put("APPSIGN", str3);
        map.put("APPRANDOMSTR", str);
        return map;
    }

    /* renamed from: v */
    public static boolean m996v() {
        return ((Boolean) C2150a.m2590a("LISTEN_STATE", Boolean.FALSE)).booleanValue();
    }

    /* renamed from: w */
    public static String m997w(Context context) {
        String string;
        String strM2084c = C1836c.m2084c(context, null);
        if (!TextUtils.isEmpty(strM2084c)) {
            return strM2084c;
        }
        try {
            string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("CCTV_TV_CHANNEL");
        } catch (Exception e7) {
            Log.m651e("XLog_APP ", "getMetaDataValue Exception = " + e7);
            string = "";
        }
        return !TextUtils.isEmpty(string) ? string : "CCTV";
    }

    /* renamed from: x */
    public static String m998x(String str, String str2) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
            } catch (Exception e7) {
                e7.printStackTrace();
                return str2;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    /* renamed from: y */
    public static String m999y(String str, String str2) {
        String strM92a = C0072a.m92a(C0413b.m112a(m989o(str)), File.separator, str2);
        return new File(strM92a).exists() ? m965I(strM92a) : "";
    }

    /* renamed from: z */
    public static int m1000z(Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NumberFormatException {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception unused) {
            return 0;
        }
    }
}
