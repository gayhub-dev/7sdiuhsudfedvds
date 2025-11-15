package p116o0;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import p009b.C0413b;
import p108n0.C1514e;
import p108n0.InterfaceC1510a;

/* compiled from: ViewTarget.java */
/* renamed from: o0.g */
/* loaded from: classes.dex */
public abstract class AbstractC1578g<T extends View, Z> extends AbstractC1572a<Z> {

    /* renamed from: b */
    public final T f4734b;

    /* renamed from: c */
    public final a f4735c;

    /* compiled from: ViewTarget.java */
    /* renamed from: o0.g$a */
    public static class a {

        /* renamed from: a */
        public final View f4736a;

        /* renamed from: b */
        public final List<InterfaceC1576e> f4737b = new ArrayList();

        /* renamed from: c */
        @Nullable
        public ViewTreeObserverOnPreDrawListenerC2189a f4738c;

        /* compiled from: ViewTarget.java */
        /* renamed from: o0.g$a$a, reason: collision with other inner class name */
        public static class ViewTreeObserverOnPreDrawListenerC2189a implements ViewTreeObserver.OnPreDrawListener {

            /* renamed from: e */
            public final WeakReference<a> f4739e;

            public ViewTreeObserverOnPreDrawListenerC2189a(a aVar) {
                this.f4739e = new WeakReference<>(aVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", 2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("OnGlobalLayoutListener called listener=");
                    sb.append(this);
                }
                a aVar = this.f4739e.get();
                if (aVar == null || aVar.f4737b.isEmpty()) {
                    return true;
                }
                int iM1827d = aVar.m1827d();
                int iM1826c = aVar.m1826c();
                if (!aVar.m1829f(iM1827d, iM1826c)) {
                    return true;
                }
                Iterator it = new ArrayList(aVar.f4737b).iterator();
                while (it.hasNext()) {
                    ((InterfaceC1576e) it.next()).mo1695f(iM1827d, iM1826c);
                }
                aVar.m1824a();
                return true;
            }
        }

        public a(View view) {
            this.f4736a = view;
        }

        /* renamed from: a */
        public void m1824a() {
            ViewTreeObserver viewTreeObserver = this.f4736a.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f4738c);
            }
            this.f4738c = null;
            this.f4737b.clear();
        }

        /* renamed from: b */
        public final int m1825b(int i7, int i8, int i9) {
            int i10 = i7 - i9;
            if (m1828e(i10)) {
                return i10;
            }
            if (i8 == 0) {
                return 0;
            }
            if (i8 == -2) {
                return Integer.MIN_VALUE;
            }
            if (i8 > 0) {
                return i8 - i9;
            }
            return 0;
        }

        /* renamed from: c */
        public final int m1826c() {
            int paddingBottom = this.f4736a.getPaddingBottom() + this.f4736a.getPaddingTop();
            ViewGroup.LayoutParams layoutParams = this.f4736a.getLayoutParams();
            return m1825b(this.f4736a.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingBottom);
        }

        /* renamed from: d */
        public final int m1827d() {
            int paddingRight = this.f4736a.getPaddingRight() + this.f4736a.getPaddingLeft();
            ViewGroup.LayoutParams layoutParams = this.f4736a.getLayoutParams();
            return m1825b(this.f4736a.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingRight);
        }

        /* renamed from: e */
        public final boolean m1828e(int i7) {
            return i7 > 0 || i7 == Integer.MIN_VALUE;
        }

        /* renamed from: f */
        public final boolean m1829f(int i7, int i8) {
            return ((this.f4736a.getLayoutParams() == null || this.f4736a.getLayoutParams().width <= 0 || this.f4736a.getLayoutParams().height <= 0) ? this.f4736a.isLayoutRequested() ^ true : true) && m1828e(i7) && m1828e(i8);
        }
    }

    public AbstractC1578g(T t6) {
        Objects.requireNonNull(t6, "Argument must not be null");
        this.f4734b = t6;
        this.f4735c = new a(t6);
    }

    @Override // p116o0.InterfaceC1577f
    /* renamed from: c */
    public void mo1822c(InterfaceC1576e interfaceC1576e) {
        a aVar = this.f4735c;
        int iM1827d = aVar.m1827d();
        int iM1826c = aVar.m1826c();
        if (aVar.m1829f(iM1827d, iM1826c)) {
            ((C1514e) interfaceC1576e).mo1695f(iM1827d, iM1826c);
            return;
        }
        if (!aVar.f4737b.contains(interfaceC1576e)) {
            aVar.f4737b.add(interfaceC1576e);
        }
        if (aVar.f4738c == null) {
            ViewTreeObserver viewTreeObserver = aVar.f4736a.getViewTreeObserver();
            a.ViewTreeObserverOnPreDrawListenerC2189a viewTreeObserverOnPreDrawListenerC2189a = new a.ViewTreeObserverOnPreDrawListenerC2189a(aVar);
            aVar.f4738c = viewTreeObserverOnPreDrawListenerC2189a;
            viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC2189a);
        }
    }

    @Override // p116o0.InterfaceC1577f
    /* renamed from: d */
    public void mo1823d(InterfaceC1576e interfaceC1576e) {
        this.f4735c.f4737b.remove(interfaceC1576e);
    }

    @Override // p116o0.AbstractC1572a, p116o0.InterfaceC1577f
    /* renamed from: f */
    public void mo1817f(@Nullable InterfaceC1510a interfaceC1510a) {
        this.f4734b.setTag(interfaceC1510a);
    }

    @Override // p116o0.AbstractC1572a, p116o0.InterfaceC1577f
    @Nullable
    /* renamed from: h */
    public InterfaceC1510a mo1819h() {
        Object tag = this.f4734b.getTag();
        if (tag == null) {
            return null;
        }
        if (tag instanceof InterfaceC1510a) {
            return (InterfaceC1510a) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Target for: ");
        sbM112a.append(this.f4734b);
        return sbM112a.toString();
    }
}
