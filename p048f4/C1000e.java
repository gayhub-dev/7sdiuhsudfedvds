package p048f4;

/* compiled from: BlockingFirstObserver.java */
/* renamed from: f4.e */
/* loaded from: classes.dex */
public final class C1000e<T> extends AbstractC0999d<T> {

    /* renamed from: i */
    public final /* synthetic */ int f1876i;

    public C1000e(int i7) {
        this.f1876i = i7;
        if (i7 != 1) {
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        switch (this.f1876i) {
            case 0:
                if (this.f1872e == null) {
                    this.f1873f = th;
                }
                countDown();
                break;
            default:
                this.f1872e = null;
                this.f1873f = th;
                countDown();
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p194y3.InterfaceC2127s
    public void onNext(Object obj) {
        switch (this.f1876i) {
            case 0:
                if (this.f1872e == null) {
                    this.f1872e = obj;
                    this.f1874g.dispose();
                    countDown();
                    break;
                }
                break;
            default:
                this.f1872e = obj;
                break;
        }
    }
}
