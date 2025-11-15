package p138q4;

import p014b4.InterfaceC0455o;

/* compiled from: AppendOnlyLinkedArrayList.java */
/* renamed from: q4.a */
/* loaded from: classes.dex */
public class C1769a<T> {

    /* renamed from: a */
    public final Object[] f5047a;

    /* renamed from: b */
    public Object[] f5048b;

    /* renamed from: c */
    public int f5049c;

    /* compiled from: AppendOnlyLinkedArrayList.java */
    /* renamed from: q4.a$a */
    public interface a<T> extends InterfaceC0455o<T> {
        @Override // p014b4.InterfaceC0455o
        boolean test(T t6);
    }

    public C1769a(int i7) {
        Object[] objArr = new Object[i7 + 1];
        this.f5047a = objArr;
        this.f5048b = objArr;
    }

    /* renamed from: a */
    public void m1956a(T t6) {
        int i7 = this.f5049c;
        if (i7 == 4) {
            Object[] objArr = new Object[5];
            this.f5048b[4] = objArr;
            this.f5048b = objArr;
            i7 = 0;
        }
        this.f5048b[i7] = t6;
        this.f5049c = i7 + 1;
    }

    /* renamed from: b */
    public void m1957b(a<? super T> aVar) {
        Object obj;
        for (Object[] objArr = this.f5047a; objArr != null; objArr = (Object[]) objArr[4]) {
            for (int i7 = 0; i7 < 4 && (obj = objArr[i7]) != null; i7++) {
                if (aVar.test(obj)) {
                    return;
                }
            }
        }
    }
}
