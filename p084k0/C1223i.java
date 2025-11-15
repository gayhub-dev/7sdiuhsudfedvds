package p084k0;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.bumptech.glide.manager.RequestManagerFragment;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import p043f.C0984a;
import p141r.C1814i;
import p141r.ComponentCallbacks2C1808c;
import p142r0.C1823h;

/* compiled from: RequestManagerRetriever.java */
/* renamed from: k0.i */
/* loaded from: classes.dex */
public class C1223i implements Handler.Callback {

    /* renamed from: f */
    public static final b f2747f = new a();

    /* renamed from: a */
    public volatile C1814i f2748a;

    /* renamed from: b */
    public final Map<FragmentManager, RequestManagerFragment> f2749b = new HashMap();

    /* renamed from: c */
    public final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> f2750c = new HashMap();

    /* renamed from: d */
    public final Handler f2751d;

    /* renamed from: e */
    public final b f2752e;

    /* compiled from: RequestManagerRetriever.java */
    /* renamed from: k0.i$a */
    public static class a implements b {
    }

    /* compiled from: RequestManagerRetriever.java */
    /* renamed from: k0.i$b */
    public interface b {
    }

    public C1223i(@Nullable b bVar) {
        new ArrayMap();
        new ArrayMap();
        new Bundle();
        this.f2752e = bVar == null ? f2747f : bVar;
        this.f2751d = new Handler(Looper.getMainLooper(), this);
    }

    /* renamed from: a */
    public C1814i m1449a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (C1823h.m2064h() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                FragmentActivity fragmentActivity = (FragmentActivity) context;
                if (C1823h.m2063g()) {
                    return m1449a(fragmentActivity.getApplicationContext());
                }
                if (fragmentActivity.isDestroyed()) {
                    throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
                }
                SupportRequestManagerFragment supportRequestManagerFragmentM1451c = m1451c(fragmentActivity.getSupportFragmentManager(), null);
                C1814i c1814i = supportRequestManagerFragmentM1451c.f442i;
                if (c1814i == null) {
                    ComponentCallbacks2C1808c componentCallbacks2C1808cM2022c = ComponentCallbacks2C1808c.m2022c(fragmentActivity);
                    b bVar = this.f2752e;
                    C1215a c1215a = supportRequestManagerFragmentM1451c.f438e;
                    InterfaceC1224j interfaceC1224j = supportRequestManagerFragmentM1451c.f439f;
                    Objects.requireNonNull((a) bVar);
                    c1814i = new C1814i(componentCallbacks2C1808cM2022c, c1215a, interfaceC1224j);
                    supportRequestManagerFragmentM1451c.f442i = c1814i;
                }
                return c1814i;
            }
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (C1823h.m2063g()) {
                    return m1449a(activity.getApplicationContext());
                }
                if (activity.isDestroyed()) {
                    throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
                }
                RequestManagerFragment requestManagerFragmentM1450b = m1450b(activity.getFragmentManager(), null);
                C1814i c1814i2 = requestManagerFragmentM1450b.f434h;
                if (c1814i2 == null) {
                    ComponentCallbacks2C1808c componentCallbacks2C1808cM2022c2 = ComponentCallbacks2C1808c.m2022c(activity);
                    b bVar2 = this.f2752e;
                    C1215a c1215a2 = requestManagerFragmentM1450b.f431e;
                    InterfaceC1224j interfaceC1224j2 = requestManagerFragmentM1450b.f432f;
                    Objects.requireNonNull((a) bVar2);
                    c1814i2 = new C1814i(componentCallbacks2C1808cM2022c2, c1215a2, interfaceC1224j2);
                    requestManagerFragmentM1450b.f434h = c1814i2;
                }
                return c1814i2;
            }
            if (context instanceof ContextWrapper) {
                return m1449a(((ContextWrapper) context).getBaseContext());
            }
        }
        if (this.f2748a == null) {
            synchronized (this) {
                if (this.f2748a == null) {
                    ComponentCallbacks2C1808c componentCallbacks2C1808cM2022c3 = ComponentCallbacks2C1808c.m2022c(context);
                    b bVar3 = this.f2752e;
                    C0984a c0984a = new C0984a(6);
                    C0984a c0984a2 = new C0984a(7);
                    Objects.requireNonNull((a) bVar3);
                    this.f2748a = new C1814i(componentCallbacks2C1808cM2022c3, c0984a, c0984a2);
                }
            }
        }
        return this.f2748a;
    }

    @TargetApi(17)
    /* renamed from: b */
    public RequestManagerFragment m1450b(FragmentManager fragmentManager, Fragment fragment) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (requestManagerFragment == null && (requestManagerFragment = this.f2749b.get(fragmentManager)) == null) {
            requestManagerFragment = new RequestManagerFragment();
            requestManagerFragment.f436j = fragment;
            if (fragment != null && fragment.getActivity() != null) {
                requestManagerFragment.m344a(fragment.getActivity());
            }
            this.f2749b.put(fragmentManager, requestManagerFragment);
            fragmentManager.beginTransaction().add(requestManagerFragment, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.f2751d.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return requestManagerFragment;
    }

    /* renamed from: c */
    public SupportRequestManagerFragment m1451c(android.support.v4.app.FragmentManager fragmentManager, android.support.v4.app.Fragment fragment) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (supportRequestManagerFragment == null && (supportRequestManagerFragment = this.f2750c.get(fragmentManager)) == null) {
            supportRequestManagerFragment = new SupportRequestManagerFragment();
            supportRequestManagerFragment.f443j = fragment;
            if (fragment != null && fragment.getActivity() != null) {
                supportRequestManagerFragment.m346e(fragment.getActivity());
            }
            this.f2750c.put(fragmentManager, supportRequestManagerFragment);
            fragmentManager.beginTransaction().add(supportRequestManagerFragment, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.f2751d.obtainMessage(2, fragmentManager).sendToTarget();
        }
        return supportRequestManagerFragment;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Object obj;
        Object objRemove;
        Object obj2;
        int i7 = message.what;
        Object obj3 = null;
        boolean z6 = true;
        if (i7 == 1) {
            obj = (FragmentManager) message.obj;
            objRemove = this.f2749b.remove(obj);
        } else {
            if (i7 != 2) {
                z6 = false;
                obj2 = null;
                if (z6 && obj3 == null && Log.isLoggable("RMRetriever", 5)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to remove expected request manager fragment, manager: ");
                    sb.append(obj2);
                }
                return z6;
            }
            obj = (android.support.v4.app.FragmentManager) message.obj;
            objRemove = this.f2750c.remove(obj);
        }
        Object obj4 = obj;
        obj3 = objRemove;
        obj2 = obj4;
        if (z6) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to remove expected request manager fragment, manager: ");
            sb2.append(obj2);
        }
        return z6;
    }
}
