package p075j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MDPluginManager.java */
/* renamed from: j.h */
/* loaded from: classes.dex */
public class C1180h {

    /* renamed from: a */
    public List<AbstractC1174b> f2594a;

    public C1180h(int i7) {
        if (i7 != 1) {
            this.f2594a = new CopyOnWriteArrayList();
        } else {
            this.f2594a = new ArrayList();
        }
    }
}
