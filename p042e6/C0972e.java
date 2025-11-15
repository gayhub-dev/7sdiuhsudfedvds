package p042e6;

/* compiled from: ConverterSet.java */
/* renamed from: e6.e */
/* loaded from: classes.dex */
public class C0972e {

    /* renamed from: a */
    public final InterfaceC0970c[] f1803a;

    /* renamed from: b */
    public a[] f1804b = new a[16];

    /* compiled from: ConverterSet.java */
    /* renamed from: e6.e$a */
    public static class a {

        /* renamed from: a */
        public final Class<?> f1805a;

        /* renamed from: b */
        public final InterfaceC0970c f1806b;

        public a(Class<?> cls, InterfaceC0970c interfaceC0970c) {
            this.f1805a = cls;
            this.f1806b = interfaceC0970c;
        }
    }

    public C0972e(InterfaceC0970c[] interfaceC0970cArr) {
        this.f1803a = interfaceC0970cArr;
    }

    /* renamed from: a */
    public C0972e m936a(int i7, InterfaceC0970c[] interfaceC0970cArr) {
        InterfaceC0970c[] interfaceC0970cArr2 = this.f1803a;
        int length = interfaceC0970cArr2.length;
        if (i7 >= length) {
            throw new IndexOutOfBoundsException();
        }
        InterfaceC0970c[] interfaceC0970cArr3 = new InterfaceC0970c[length - 1];
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            if (i9 != i7) {
                interfaceC0970cArr3[i8] = interfaceC0970cArr2[i9];
                i8++;
            }
        }
        return new C0972e(interfaceC0970cArr3);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0021 A[EDGE_INSN: B:76:0x0021->B:15:0x0021 BREAK  A[LOOP:0: B:6:0x0010->B:80:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x001d -> B:14:0x001f). Please report as a decompilation issue!!! */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p042e6.InterfaceC0970c m937b(java.lang.Class<?> r15) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p042e6.C0972e.m937b(java.lang.Class):e6.c");
    }
}
