package android.arch.lifecycle;

import android.app.Fragment;
import android.arch.lifecycle.AbstractC0052c;
import android.content.ComponentCallbacks2;
import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ReportFragment extends Fragment {

    /* renamed from: e */
    public static final /* synthetic */ int f79e = 0;

    /* renamed from: a */
    public final void m69a(AbstractC0052c.a aVar) {
        ComponentCallbacks2 activity = getActivity();
        if (activity instanceof InterfaceC0056g) {
            ((InterfaceC0056g) activity).getLifecycle().m80d(aVar);
        } else if (activity instanceof InterfaceC0054e) {
            AbstractC0052c lifecycle = ((InterfaceC0054e) activity).getLifecycle();
            if (lifecycle instanceof C0055f) {
                ((C0055f) lifecycle).m80d(aVar);
            }
        }
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        m69a(AbstractC0052c.a.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        m69a(AbstractC0052c.a.ON_DESTROY);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        m69a(AbstractC0052c.a.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        m69a(AbstractC0052c.a.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        m69a(AbstractC0052c.a.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        m69a(AbstractC0052c.a.ON_STOP);
    }
}
