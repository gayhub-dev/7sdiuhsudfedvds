package p161t5;

import java.io.IOException;

/* compiled from: Utf8StringBuffer.java */
/* renamed from: t5.w */
/* loaded from: classes.dex */
public class C1931w extends AbstractC1930v {

    /* renamed from: g */
    public final /* synthetic */ int f5712g;

    /* renamed from: h */
    public final Object f5713h;

    public C1931w() {
        this.f5712g = 1;
        StringBuilder sb = new StringBuilder();
        super(sb);
        this.f5713h = sb;
    }

    /* renamed from: f */
    public StringBuffer m2279f() throws IOException {
        m2277d();
        return (StringBuffer) this.f5713h;
    }

    /* renamed from: g */
    public StringBuilder m2280g() throws IOException {
        m2277d();
        return (StringBuilder) this.f5713h;
    }

    /* renamed from: h */
    public int m2281h() {
        return ((StringBuilder) this.f5713h).length();
    }

    /* renamed from: i */
    public void m2282i() {
        switch (this.f5712g) {
            case 1:
                this.f5710b = 0;
                ((StringBuilder) this.f5713h).setLength(0);
                break;
            default:
                this.f5710b = 0;
                break;
        }
    }

    public String toString() throws IOException {
        switch (this.f5712g) {
            case 0:
                m2277d();
                return ((StringBuffer) this.f5713h).toString();
            default:
                m2277d();
                return ((StringBuilder) this.f5713h).toString();
        }
    }

    public C1931w(int i7, int i8) {
        this.f5712g = i8;
        if (i8 != 1) {
            StringBuffer stringBuffer = new StringBuffer(i7);
            super(stringBuffer);
            this.f5713h = stringBuffer;
        } else {
            StringBuilder sb = new StringBuilder(i7);
            super(sb);
            this.f5713h = sb;
        }
    }
}
