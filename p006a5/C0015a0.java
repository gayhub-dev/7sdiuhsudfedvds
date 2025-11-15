package p006a5;

/* compiled from: UnavailableException.java */
/* renamed from: a5.a0 */
/* loaded from: classes.dex */
public class C0015a0 extends C0029o {

    /* renamed from: f */
    public boolean f25f;

    /* renamed from: g */
    public int f26g;

    public C0015a0(String str) {
        super(str);
        this.f25f = true;
    }

    public C0015a0(String str, int i7) {
        super(str);
        if (i7 <= 0) {
            this.f26g = -1;
        } else {
            this.f26g = i7;
        }
        this.f25f = false;
    }
}
