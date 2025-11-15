package p097l5;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import p009b.C0413b;

/* compiled from: RoleInfo.java */
/* renamed from: l5.g */
/* loaded from: classes.dex */
public class C1442g {

    /* renamed from: a */
    public boolean f4184a;

    /* renamed from: b */
    public boolean f4185b;

    /* renamed from: c */
    public boolean f4186c;

    /* renamed from: d */
    public EnumC1446k f4187d;

    /* renamed from: e */
    public final Set<String> f4188e = new CopyOnWriteArraySet();

    /* renamed from: a */
    public void m1626a(C1442g c1442g) {
        if (c1442g.f4186c) {
            m1627b(true);
        } else if (!c1442g.f4185b) {
            this.f4185b = true;
        } else if (c1442g.f4184a) {
            this.f4184a = true;
            this.f4185b = true;
            this.f4188e.clear();
        } else if (!this.f4184a) {
            Iterator<String> it = c1442g.f4188e.iterator();
            while (it.hasNext()) {
                this.f4188e.add(it.next());
            }
        }
        m1628c(c1442g.f4187d);
    }

    /* renamed from: b */
    public void m1627b(boolean z6) {
        this.f4186c = z6;
        if (z6) {
            this.f4185b = true;
            this.f4187d = null;
            this.f4184a = false;
            this.f4188e.clear();
        }
    }

    /* renamed from: c */
    public void m1628c(EnumC1446k enumC1446k) {
        Objects.requireNonNull(enumC1446k, "Null UserDataConstraint");
        EnumC1446k enumC1446k2 = this.f4187d;
        if (enumC1446k2 == null) {
            this.f4187d = enumC1446k;
            return;
        }
        if (enumC1446k2.compareTo(enumC1446k) < 0) {
            enumC1446k = enumC1446k2;
        }
        this.f4187d = enumC1446k;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("{RoleInfo");
        sbM112a.append(this.f4186c ? ",F" : "");
        sbM112a.append(this.f4185b ? ",C" : "");
        sbM112a.append(this.f4184a ? ",*" : this.f4188e);
        sbM112a.append("}");
        return sbM112a.toString();
    }
}
