package android.arch.lifecycle;

import android.arch.lifecycle.AbstractC0052c;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import p000a.C0000a;
import p009b.C0414c;

/* loaded from: classes.dex */
public abstract class LiveData<T> {
    private static final Object NOT_SET = new Object();
    public static final int START_VERSION = -1;
    private volatile Object mData;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private volatile Object mPendingData;
    private final Runnable mPostValueRunnable;
    private int mVersion;
    private final Object mDataLock = new Object();
    private C0414c<InterfaceC0060k<T>, LiveData<T>.AbstractC0049c> mObservers = new C0414c<>();
    private int mActiveCount = 0;

    public class LifecycleBoundObserver extends LiveData<T>.AbstractC0049c implements GenericLifecycleObserver {

        /* renamed from: i */
        @NonNull
        public final InterfaceC0054e f70i;

        public LifecycleBoundObserver(@NonNull InterfaceC0054e interfaceC0054e, InterfaceC0060k<T> interfaceC0060k) {
            super(interfaceC0060k);
            this.f70i = interfaceC0054e;
        }

        @Override // android.arch.lifecycle.GenericLifecycleObserver
        /* renamed from: d */
        public void mo58d(InterfaceC0054e interfaceC0054e, AbstractC0052c.a aVar) {
            if (((C0055f) this.f70i.getLifecycle()).f95b == AbstractC0052c.b.DESTROYED) {
                LiveData.this.removeObserver(this.f73e);
            } else {
                m68h(mo67k());
            }
        }

        @Override // android.arch.lifecycle.LiveData.AbstractC0049c
        /* renamed from: i */
        public void mo65i() {
            ((C0055f) this.f70i.getLifecycle()).f94a.mo111d(this);
        }

        @Override // android.arch.lifecycle.LiveData.AbstractC0049c
        /* renamed from: j */
        public boolean mo66j(InterfaceC0054e interfaceC0054e) {
            return this.f70i == interfaceC0054e;
        }

        @Override // android.arch.lifecycle.LiveData.AbstractC0049c
        /* renamed from: k */
        public boolean mo67k() {
            return ((C0055f) this.f70i.getLifecycle()).f95b.compareTo(AbstractC0052c.b.STARTED) >= 0;
        }
    }

    /* renamed from: android.arch.lifecycle.LiveData$a */
    public class RunnableC0047a implements Runnable {
        public RunnableC0047a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.mDataLock) {
                obj = LiveData.this.mPendingData;
                LiveData.this.mPendingData = LiveData.NOT_SET;
            }
            LiveData.this.setValue(obj);
        }
    }

    /* renamed from: android.arch.lifecycle.LiveData$b */
    public class C0048b extends LiveData<T>.AbstractC0049c {
        public C0048b(LiveData liveData, InterfaceC0060k<T> interfaceC0060k) {
            super(interfaceC0060k);
        }

        @Override // android.arch.lifecycle.LiveData.AbstractC0049c
        /* renamed from: k */
        public boolean mo67k() {
            return true;
        }
    }

    /* renamed from: android.arch.lifecycle.LiveData$c */
    public abstract class AbstractC0049c {

        /* renamed from: e */
        public final InterfaceC0060k<T> f73e;

        /* renamed from: f */
        public boolean f74f;

        /* renamed from: g */
        public int f75g = -1;

        public AbstractC0049c(InterfaceC0060k<T> interfaceC0060k) {
            this.f73e = interfaceC0060k;
        }

        /* renamed from: h */
        public void m68h(boolean z6) {
            if (z6 == this.f74f) {
                return;
            }
            this.f74f = z6;
            boolean z7 = LiveData.this.mActiveCount == 0;
            LiveData.this.mActiveCount += this.f74f ? 1 : -1;
            if (z7 && this.f74f) {
                LiveData.this.onActive();
            }
            if (LiveData.this.mActiveCount == 0 && !this.f74f) {
                LiveData.this.onInactive();
            }
            if (this.f74f) {
                LiveData.this.dispatchingValue(this);
            }
        }

        /* renamed from: i */
        public void mo65i() {
        }

        /* renamed from: j */
        public boolean mo66j(InterfaceC0054e interfaceC0054e) {
            return false;
        }

        /* renamed from: k */
        public abstract boolean mo67k();
    }

    public LiveData() {
        Object obj = NOT_SET;
        this.mData = obj;
        this.mPendingData = obj;
        this.mVersion = -1;
        this.mPostValueRunnable = new RunnableC0047a();
    }

    private static void assertMainThread(String str) {
        if (C0000a.m0c().f1a.mo1a()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void considerNotify(LiveData<T>.AbstractC0049c abstractC0049c) {
        if (abstractC0049c.f74f) {
            if (!abstractC0049c.mo67k()) {
                abstractC0049c.m68h(false);
                return;
            }
            int i7 = abstractC0049c.f75g;
            int i8 = this.mVersion;
            if (i7 >= i8) {
                return;
            }
            abstractC0049c.f75g = i8;
            abstractC0049c.f73e.onChanged(this.mData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchingValue(@Nullable LiveData<T>.AbstractC0049c abstractC0049c) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (abstractC0049c != null) {
                considerNotify(abstractC0049c);
                abstractC0049c = null;
            } else {
                C0414c<InterfaceC0060k<T>, LiveData<T>.AbstractC0049c> c0414c = this.mObservers;
                C0414c.e eVar = new C0414c.e(null);
                c0414c.f179g.put(eVar, Boolean.FALSE);
                while (eVar.hasNext()) {
                    considerNotify((AbstractC0049c) ((Map.Entry) eVar.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @Nullable
    public T getValue() {
        T t6 = (T) this.mData;
        if (t6 != NOT_SET) {
            return t6;
        }
        return null;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    public boolean hasObservers() {
        return this.mObservers.f180h > 0;
    }

    @MainThread
    public void observe(@NonNull InterfaceC0054e interfaceC0054e, @NonNull InterfaceC0060k<T> interfaceC0060k) {
        if (((C0055f) interfaceC0054e.getLifecycle()).f95b == AbstractC0052c.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(interfaceC0054e, interfaceC0060k);
        LiveData<T>.AbstractC0049c abstractC0049cMo110c = this.mObservers.mo110c(interfaceC0060k, lifecycleBoundObserver);
        if (abstractC0049cMo110c != null && !abstractC0049cMo110c.mo66j(interfaceC0054e)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (abstractC0049cMo110c != null) {
            return;
        }
        interfaceC0054e.getLifecycle().mo75a(lifecycleBoundObserver);
    }

    @MainThread
    public void observeForever(@NonNull InterfaceC0060k<T> interfaceC0060k) {
        C0048b c0048b = new C0048b(this, interfaceC0060k);
        LiveData<T>.AbstractC0049c abstractC0049cMo110c = this.mObservers.mo110c(interfaceC0060k, c0048b);
        if (abstractC0049cMo110c != null && (abstractC0049cMo110c instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (abstractC0049cMo110c != null) {
            return;
        }
        c0048b.m68h(true);
    }

    public void onActive() {
    }

    public void onInactive() {
    }

    public void postValue(T t6) {
        boolean z6;
        synchronized (this.mDataLock) {
            z6 = this.mPendingData == NOT_SET;
            this.mPendingData = t6;
        }
        if (z6) {
            C0000a.m0c().f1a.mo2b(this.mPostValueRunnable);
        }
    }

    @MainThread
    public void removeObserver(@NonNull InterfaceC0060k<T> interfaceC0060k) {
        assertMainThread("removeObserver");
        LiveData<T>.AbstractC0049c abstractC0049cMo111d = this.mObservers.mo111d(interfaceC0060k);
        if (abstractC0049cMo111d == null) {
            return;
        }
        abstractC0049cMo111d.mo65i();
        abstractC0049cMo111d.m68h(false);
    }

    @MainThread
    public void removeObservers(@NonNull InterfaceC0054e interfaceC0054e) {
        assertMainThread("removeObservers");
        Iterator<Map.Entry<InterfaceC0060k<T>, LiveData<T>.AbstractC0049c>> it = this.mObservers.iterator();
        while (true) {
            C0414c.f fVar = (C0414c.f) it;
            if (!fVar.hasNext()) {
                return;
            }
            Map.Entry entry = (Map.Entry) fVar.next();
            if (((AbstractC0049c) entry.getValue()).mo66j(interfaceC0054e)) {
                removeObserver((InterfaceC0060k) entry.getKey());
            }
        }
    }

    @MainThread
    public void setValue(T t6) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = t6;
        dispatchingValue(null);
    }
}
