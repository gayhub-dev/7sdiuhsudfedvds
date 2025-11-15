package p168u5;

import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import p168u5.InterfaceC1984e;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractLifeCycle.java */
/* renamed from: u5.a */
/* loaded from: classes.dex */
public abstract class AbstractC1980a implements InterfaceC1984e {
    public static final String FAILED = "FAILED";
    private static final InterfaceC2016c LOG;
    public static final String RUNNING = "RUNNING";
    public static final String STARTED = "STARTED";
    public static final String STARTING = "STARTING";
    public static final String STOPPED = "STOPPED";
    public static final String STOPPING = "STOPPING";
    private final Object _lock = new Object();
    private final int __FAILED = -1;
    private final int __STOPPED = 0;
    private final int __STARTING = 1;
    private final int __STARTED = 2;
    private final int __STOPPING = 3;
    private volatile int _state = 0;
    public final CopyOnWriteArrayList<InterfaceC1984e.a> _listeners = new CopyOnWriteArrayList<>();

    static {
        Properties properties = C2015b.f5863a;
        LOG = C2015b.m2349a(AbstractC1980a.class.getName());
    }

    private void setFailed(Throwable th) {
        this._state = -1;
        LOG.mo2354e("FAILED " + this + ": " + th, th);
        Iterator<InterfaceC1984e.a> it = this._listeners.iterator();
        while (it.hasNext()) {
            it.next().m2321B(this, th);
        }
    }

    private void setStarted() {
        this._state = 2;
        LOG.mo2351a("STARTED {}", this);
        Iterator<InterfaceC1984e.a> it = this._listeners.iterator();
        while (it.hasNext()) {
            it.next().m2324r(this);
        }
    }

    private void setStarting() {
        LOG.mo2351a("starting {}", this);
        this._state = 1;
        Iterator<InterfaceC1984e.a> it = this._listeners.iterator();
        while (it.hasNext()) {
            it.next().m2323q(this);
        }
    }

    private void setStopped() {
        this._state = 0;
        LOG.mo2351a("{} {}", STOPPED, this);
        Iterator<InterfaceC1984e.a> it = this._listeners.iterator();
        while (it.hasNext()) {
            it.next().m2325s(this);
        }
    }

    private void setStopping() {
        LOG.mo2351a("stopping {}", this);
        this._state = 3;
        Iterator<InterfaceC1984e.a> it = this._listeners.iterator();
        while (it.hasNext()) {
            it.next().m2322l(this);
        }
    }

    public void addLifeCycleListener(InterfaceC1984e.a aVar) {
        this._listeners.add(aVar);
    }

    public void doStart() {
    }

    public void doStop() {
    }

    public String getState() {
        int i7 = this._state;
        if (i7 == -1) {
            return FAILED;
        }
        if (i7 == 0) {
            return STOPPED;
        }
        if (i7 == 1) {
            return STARTING;
        }
        if (i7 == 2) {
            return STARTED;
        }
        if (i7 != 3) {
            return null;
        }
        return STOPPING;
    }

    public boolean isFailed() {
        return this._state == -1;
    }

    @Override // p168u5.InterfaceC1984e
    public boolean isRunning() {
        int i7 = this._state;
        return i7 == 2 || i7 == 1;
    }

    @Override // p168u5.InterfaceC1984e
    public boolean isStarted() {
        return this._state == 2;
    }

    @Override // p168u5.InterfaceC1984e
    public boolean isStarting() {
        return this._state == 1;
    }

    @Override // p168u5.InterfaceC1984e
    public boolean isStopped() {
        return this._state == 0;
    }

    @Override // p168u5.InterfaceC1984e
    public boolean isStopping() {
        return this._state == 3;
    }

    public void removeLifeCycleListener(InterfaceC1984e.a aVar) {
        this._listeners.remove(aVar);
    }

    @Override // p168u5.InterfaceC1984e
    public final void start() {
        synchronized (this._lock) {
            try {
                try {
                    if (this._state != 2 && this._state != 1) {
                        setStarting();
                        doStart();
                        setStarted();
                    }
                } catch (Error e7) {
                    setFailed(e7);
                    throw e7;
                } catch (Exception e8) {
                    setFailed(e8);
                    throw e8;
                }
            } finally {
            }
        }
    }

    @Override // p168u5.InterfaceC1984e
    public final void stop() {
        synchronized (this._lock) {
            try {
                try {
                    if (this._state != 3 && this._state != 0) {
                        setStopping();
                        doStop();
                        setStopped();
                    }
                } catch (Error e7) {
                    setFailed(e7);
                    throw e7;
                } catch (Exception e8) {
                    setFailed(e8);
                    throw e8;
                }
            } finally {
            }
        }
    }

    public static String getState(InterfaceC1984e interfaceC1984e) {
        return interfaceC1984e.isStarting() ? STARTING : interfaceC1984e.isStarted() ? STARTED : interfaceC1984e.isStopping() ? STOPPING : interfaceC1984e.isStopped() ? STOPPED : FAILED;
    }
}
