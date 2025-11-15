package p183x;

import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p009b.C0413b;
import p162u.C1966j;
import p169v.InterfaceC1987c;
import p183x.C2046g;

/* compiled from: LoadPath.java */
/* renamed from: x.p */
/* loaded from: classes.dex */
public class C2055p<Data, ResourceType, Transcode> {

    /* renamed from: a */
    public final Pools.Pool<List<Exception>> f6115a;

    /* renamed from: b */
    public final List<? extends C2046g<Data, ResourceType, Transcode>> f6116b;

    /* renamed from: c */
    public final String f6117c;

    public C2055p(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<C2046g<Data, ResourceType, Transcode>> list, Pools.Pool<List<Exception>> pool) {
        this.f6115a = pool;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        this.f6116b = list;
        StringBuilder sbM112a = C0413b.m112a("Failed LoadPath{");
        sbM112a.append(cls.getSimpleName());
        sbM112a.append("->");
        sbM112a.append(cls2.getSimpleName());
        sbM112a.append("->");
        sbM112a.append(cls3.getSimpleName());
        sbM112a.append("}");
        this.f6117c = sbM112a.toString();
    }

    /* renamed from: a */
    public InterfaceC2057r<Transcode> m2444a(InterfaceC1987c<Data> interfaceC1987c, C1966j c1966j, int i7, int i8, C2046g.a<ResourceType> aVar) {
        List<Exception> listAcquire = this.f6115a.acquire();
        try {
            int size = this.f6116b.size();
            InterfaceC2057r<Transcode> interfaceC2057rM2425a = null;
            for (int i9 = 0; i9 < size; i9++) {
                try {
                    interfaceC2057rM2425a = this.f6116b.get(i9).m2425a(interfaceC1987c, i7, i8, c1966j, aVar);
                } catch (C2053n e7) {
                    listAcquire.add(e7);
                }
                if (interfaceC2057rM2425a != null) {
                    break;
                }
            }
            if (interfaceC2057rM2425a != null) {
                return interfaceC2057rM2425a;
            }
            throw new C2053n(this.f6117c, new ArrayList(listAcquire));
        } finally {
            this.f6115a.release(listAcquire);
        }
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("LoadPath{decodePaths=");
        List<? extends C2046g<Data, ResourceType, Transcode>> list = this.f6116b;
        sbM112a.append(Arrays.toString(list.toArray(new C2046g[list.size()])));
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
