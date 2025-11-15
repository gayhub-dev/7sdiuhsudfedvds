package p140q6;

import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.security.action.GetBooleanAction;
import sun.security.action.GetIntegerAction;
import sun.security.action.GetLongAction;

/* compiled from: ServerConfig.java */
/* renamed from: q6.u */
/* loaded from: classes.dex */
public class C1800u {

    /* renamed from: d */
    public static long f5167d = ((Long) AccessController.doPrivileged((PrivilegedAction) new GetLongAction("sun.net.httpserver.idleInterval", 300))).longValue() * 1000;

    /* renamed from: a */
    public static int f5164a = ((Integer) AccessController.doPrivileged((PrivilegedAction) new GetIntegerAction("sun.net.httpserver.clockTick", 10000))).intValue();

    /* renamed from: g */
    public static int f5170g = ((Integer) AccessController.doPrivileged((PrivilegedAction) new GetIntegerAction("sun.net.httpserver.maxIdleConnections", 200))).intValue();

    /* renamed from: b */
    public static long f5165b = ((Long) AccessController.doPrivileged((PrivilegedAction) new GetLongAction("sun.net.httpserver.readTimeout", 20))).longValue() * 1000;

    /* renamed from: e */
    public static long f5168e = ((Long) AccessController.doPrivileged((PrivilegedAction) new GetLongAction("sun.net.httpserver.selCacheTimeout", 120))).longValue() * 1000;

    /* renamed from: c */
    public static long f5166c = ((Long) AccessController.doPrivileged((PrivilegedAction) new GetLongAction("sun.net.httpserver.writeTimeout", 60))).longValue() * 1000;

    /* renamed from: f */
    public static long f5169f = ((Long) AccessController.doPrivileged((PrivilegedAction) new GetLongAction("sun.net.httpserver.drainAmount", 65536))).longValue();

    /* renamed from: h */
    public static boolean f5171h = ((Boolean) AccessController.doPrivileged((PrivilegedAction) new GetBooleanAction("sun.net.httpserver.debug"))).booleanValue();
}
