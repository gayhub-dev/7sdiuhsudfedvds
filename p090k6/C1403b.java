package p090k6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import p009b.C0413b;

/* compiled from: StateMachineInvocationHandler.java */
/* renamed from: k6.b */
/* loaded from: classes.dex */
public class C1403b implements InvocationHandler {

    /* renamed from: g */
    public static Logger f4136g = Logger.getLogger(C1403b.class.getName());

    /* renamed from: e */
    public final Map<Class, Object> f4137e = new ConcurrentHashMap();

    /* renamed from: f */
    public Object f4138f;

    public C1403b(List<Class<?>> list, Class<?> cls, Class[] clsArr, Object[] objArr) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        f4136g.fine("Creating state machine with initial state: " + cls);
        for (Class<?> cls2 : list) {
            if (clsArr != null) {
                try {
                    objNewInstance = cls2.getConstructor(clsArr).newInstance(objArr);
                } catch (NoSuchMethodException e7) {
                    StringBuilder sbM112a = C0413b.m112a("State ");
                    sbM112a.append(cls2.getName());
                    sbM112a.append(" has the wrong constructor: ");
                    sbM112a.append(e7);
                    throw new RuntimeException(sbM112a.toString(), e7);
                } catch (Exception e8) {
                    StringBuilder sbM112a2 = C0413b.m112a("State ");
                    sbM112a2.append(cls2.getName());
                    sbM112a2.append(" can't be instantiated: ");
                    sbM112a2.append(e8);
                    throw new RuntimeException(sbM112a2.toString(), e8);
                }
            } else {
                objNewInstance = cls2.newInstance();
            }
            f4136g.fine("Adding state instance: " + objNewInstance.getClass().getName());
            this.f4137e.put(cls2, objNewInstance);
        }
        if (!this.f4137e.containsKey(cls)) {
            throw new RuntimeException("Initial state not in list of states: " + cls);
        }
        this.f4138f = this.f4137e.get(cls);
        synchronized (this) {
            m1586b(this.f4138f);
        }
    }

    /* renamed from: a */
    public final Method m1585a(Method method) {
        try {
            return this.f4138f.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException unused) {
            StringBuilder sbM112a = C0413b.m112a("State '");
            sbM112a.append(this.f4138f.getClass().getName());
            sbM112a.append("' doesn't support signal '");
            sbM112a.append(method.getName());
            sbM112a.append("'");
            throw new C1405d(sbM112a.toString());
        }
    }

    /* renamed from: b */
    public final void m1586b(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Logger logger = f4136g;
        StringBuilder sbM112a = C0413b.m112a("Trying to invoke entry method of state: ");
        sbM112a.append(obj.getClass().getName());
        logger.fine(sbM112a.toString());
        try {
            obj.getClass().getMethod("onEntry", new Class[0]).invoke(obj, new Object[0]);
        } catch (NoSuchMethodException unused) {
            Logger logger2 = f4136g;
            StringBuilder sbM112a2 = C0413b.m112a("No entry method found on state: ");
            sbM112a2.append(obj.getClass().getName());
            logger2.finer(sbM112a2.toString());
        } catch (Exception e7) {
            StringBuilder sbM112a3 = C0413b.m112a("State '");
            sbM112a3.append(obj.getClass().getName());
            sbM112a3.append("' entry method threw exception: ");
            sbM112a3.append(e7);
            throw new C1405d(sbM112a3.toString(), e7);
        }
    }

    /* renamed from: c */
    public final void m1587c(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Logger logger = f4136g;
        StringBuilder sbM112a = C0413b.m112a("Trying to invoking exit method of state: ");
        sbM112a.append(obj.getClass().getName());
        logger.finer(sbM112a.toString());
        try {
            obj.getClass().getMethod("onExit", new Class[0]).invoke(obj, new Object[0]);
        } catch (NoSuchMethodException unused) {
            Logger logger2 = f4136g;
            StringBuilder sbM112a2 = C0413b.m112a("No exit method found on state: ");
            sbM112a2.append(obj.getClass().getName());
            logger2.finer(sbM112a2.toString());
        } catch (Exception e7) {
            StringBuilder sbM112a3 = C0413b.m112a("State '");
            sbM112a3.append(obj.getClass().getName());
            sbM112a3.append("' exit method threw exception: ");
            sbM112a3.append(e7);
            throw new C1405d(sbM112a3.toString(), e7);
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        synchronized (this) {
            if ("getCurrentState".equals(method.getName()) && method.getParameterTypes().length == 0) {
                return this.f4138f;
            }
            if ("forceState".equals(method.getName()) && method.getParameterTypes().length == 1 && objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof Class)) {
                Object obj2 = this.f4137e.get((Class) objArr[0]);
                if (obj2 == null) {
                    throw new C1405d("Can't force to invalid state: " + objArr[0]);
                }
                f4136g.finer("Forcing state machine into state: " + obj2.getClass().getName());
                m1587c(this.f4138f);
                this.f4138f = obj2;
                m1586b(obj2);
                return null;
            }
            Method methodM1585a = m1585a(method);
            f4136g.fine("Invoking signal method of current state: " + methodM1585a.toString());
            Object objInvoke = methodM1585a.invoke(this.f4138f, objArr);
            if (objInvoke != null && (objInvoke instanceof Class)) {
                Class cls = (Class) objInvoke;
                if (this.f4137e.containsKey(cls)) {
                    f4136g.fine("Executing transition to next state: " + cls.getName());
                    m1587c(this.f4138f);
                    Object obj3 = this.f4137e.get(cls);
                    this.f4138f = obj3;
                    m1586b(obj3);
                }
            }
            return objInvoke;
        }
    }
}
