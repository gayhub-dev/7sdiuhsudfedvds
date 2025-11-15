package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.AbstractC0052c;
import android.arch.lifecycle.C0055f;
import android.arch.lifecycle.InterfaceC0054e;
import android.arch.lifecycle.ReportFragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.KeyEventDispatcher;
import android.view.KeyEvent;
import android.view.View;

/* compiled from: ComponentActivity.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SupportActivity extends Activity implements InterfaceC0054e, KeyEventDispatcher.Component {
    private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap<>();
    private C0055f mLifecycleRegistry = new C0055f(this);

    /* compiled from: ComponentActivity.java */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class ExtraData {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return KeyEventDispatcher.dispatchKeyEvent(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public <T extends ExtraData> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    public AbstractC0052c getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        int i7 = ReportFragment.f79e;
        android.app.FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new ReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    @Override // android.app.Activity
    @CallSuper
    public void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.m81f(AbstractC0052c.b.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    @Override // android.support.v4.view.KeyEventDispatcher.Component
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
