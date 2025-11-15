package p161t5;

import android.arch.lifecycle.C0063n;
import java.io.IOException;
import java.util.Properties;
import p009b.C0413b;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Utf8Appendable.java */
/* renamed from: t5.v */
/* loaded from: classes.dex */
public abstract class AbstractC1930v {

    /* renamed from: d */
    public static final InterfaceC2016c f5706d;

    /* renamed from: e */
    public static final byte[] f5707e;

    /* renamed from: f */
    public static final byte[] f5708f;

    /* renamed from: a */
    public final Appendable f5709a;

    /* renamed from: b */
    public int f5710b = 0;

    /* renamed from: c */
    public int f5711c;

    /* compiled from: Utf8Appendable.java */
    /* renamed from: t5.v$a */
    public static class a extends IllegalArgumentException {
        public a(String str) {
            super(C0063n.m88a("Not valid UTF8! ", str));
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5706d = C2015b.m2349a(AbstractC1930v.class.getName());
        f5707e = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 11, 6, 6, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        f5708f = new byte[]{0, 12, 24, 36, 60, 96, 84, 12, 12, 12, 48, 72, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 0, 12, 12, 12, 12, 12, 0, 12, 0, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, 36, 12, 36, 12, 12, 12, 36, 12, 12, 12, 12, 12, 36, 12, 36, 12, 12, 12, 36, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
    }

    public AbstractC1930v(Appendable appendable) {
        this.f5709a = appendable;
    }

    /* renamed from: a */
    public void m2274a(byte b7) {
        try {
            m2276c(b7);
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: b */
    public void m2275b(byte[] bArr, int i7, int i8) {
        int i9 = i8 + i7;
        while (i7 < i9) {
            try {
                m2276c(bArr[i7]);
                i7++;
            } catch (IOException e7) {
                throw new RuntimeException(e7);
            }
        }
    }

    /* renamed from: c */
    public void m2276c(byte b7) throws IOException {
        if (b7 > 0 && this.f5710b == 0) {
            this.f5709a.append((char) (b7 & 255));
            return;
        }
        int i7 = b7 & 255;
        byte b8 = f5707e[i7];
        int i8 = this.f5710b;
        int i9 = i8 == 0 ? i7 & (255 >> b8) : (i7 & 63) | (this.f5711c << 6);
        this.f5711c = i9;
        byte b9 = f5708f[i8 + b8];
        if (b9 == 0) {
            this.f5710b = b9;
            if (i9 < 55296) {
                this.f5709a.append((char) i9);
                return;
            }
            for (char c7 : Character.toChars(i9)) {
                this.f5709a.append(c7);
            }
            return;
        }
        if (b9 != 12) {
            this.f5710b = b9;
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("byte ");
        InterfaceC2016c interfaceC2016c = C1927s.f5702a;
        byte[] bArr = {b7};
        StringBuilder sb = new StringBuilder();
        for (int i10 = 0; i10 < 1; i10++) {
            int i11 = bArr[i10] & 255;
            int i12 = ((i11 / 16) % 16) + 48;
            if (i12 > 57) {
                i12 = ((i12 - 48) - 10) + 65;
            }
            sb.append((char) i12);
            int i13 = (i11 % 16) + 48;
            if (i13 > 57) {
                i13 = ((i13 - 48) - 10) + 97;
            }
            sb.append((char) i13);
        }
        sbM112a.append(sb.toString());
        sbM112a.append(" in state ");
        sbM112a.append(this.f5710b / 12);
        String string = sbM112a.toString();
        this.f5711c = 0;
        this.f5710b = 0;
        this.f5709a.append((char) 65533);
        throw new a(string);
    }

    /* renamed from: d */
    public void m2277d() throws IOException {
        if (this.f5710b == 0) {
            return;
        }
        this.f5711c = 0;
        this.f5710b = 0;
        try {
            this.f5709a.append((char) 65533);
            throw new a("incomplete UTF8 sequence");
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: e */
    public String m2278e() throws IOException {
        if (!(this.f5710b == 0)) {
            this.f5711c = 0;
            this.f5710b = 0;
            try {
                this.f5709a.append((char) 65533);
                a aVar = new a("incomplete UTF8 sequence");
                InterfaceC2016c interfaceC2016c = f5706d;
                interfaceC2016c.mo2356g(aVar.toString(), new Object[0]);
                interfaceC2016c.mo2359j(aVar);
            } catch (IOException e7) {
                throw new RuntimeException(e7);
            }
        }
        return this.f5709a.toString();
    }
}
