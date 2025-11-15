package p081j5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1161n;

/* compiled from: StreamEndPoint.java */
/* renamed from: j5.b */
/* loaded from: classes.dex */
public class C1211b implements InterfaceC1161n {

    /* renamed from: e */
    public InputStream f2730e;

    /* renamed from: f */
    public OutputStream f2731f;

    /* renamed from: g */
    public int f2732g;

    /* renamed from: h */
    public boolean f2733h;

    /* renamed from: i */
    public boolean f2734i;

    public C1211b(InputStream inputStream, OutputStream outputStream) {
        this.f2730e = inputStream;
        this.f2731f = outputStream;
    }

    @Override // p073i5.InterfaceC1161n
    public void close() throws IOException {
        InputStream inputStream = this.f2730e;
        if (inputStream != null) {
            inputStream.close();
        }
        this.f2730e = null;
        OutputStream outputStream = this.f2731f;
        if (outputStream != null) {
            outputStream.close();
        }
        this.f2731f = null;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: e */
    public String mo912e() {
        return null;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: f */
    public int mo913f() {
        return 0;
    }

    @Override // p073i5.InterfaceC1161n
    public void flush() throws IOException {
        OutputStream outputStream = this.f2731f;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: g */
    public String mo914g() {
        return null;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: i */
    public int mo916i() {
        return this.f2732g;
    }

    @Override // p073i5.InterfaceC1161n
    public boolean isOpen() {
        return this.f2730e != null;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: j */
    public int mo917j(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3) {
        int iMo931x;
        int length;
        int length2;
        if (interfaceC1152e == null || (length2 = interfaceC1152e.length()) <= 0) {
            iMo931x = 0;
        } else {
            iMo931x = mo931x(interfaceC1152e);
            if (iMo931x < length2) {
                return iMo931x;
            }
        }
        if (interfaceC1152e2 != null && (length = interfaceC1152e2.length()) > 0) {
            int iMo931x2 = mo931x(interfaceC1152e2);
            if (iMo931x2 < 0) {
                return iMo931x > 0 ? iMo931x : iMo931x2;
            }
            iMo931x += iMo931x2;
            if (iMo931x2 < length) {
            }
        }
        return iMo931x;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: k */
    public void mo918k(int i7) {
        this.f2732g = i7;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: l */
    public Object mo919l() {
        return null;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: m */
    public void mo920m() throws IOException {
        InputStream inputStream;
        this.f2733h = true;
        if (!this.f2734i || (inputStream = this.f2730e) == null) {
            return;
        }
        inputStream.close();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: n */
    public String mo921n() {
        return null;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: o */
    public boolean mo922o(long j7) {
        return true;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: p */
    public boolean mo923p() {
        return true;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: r */
    public boolean mo925r() {
        return this.f2734i;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: s */
    public boolean mo926s() {
        return this.f2733h;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: t */
    public void mo927t() throws IOException {
        OutputStream outputStream;
        this.f2734i = true;
        if (!this.f2733h || (outputStream = this.f2731f) == null) {
            return;
        }
        outputStream.close();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: v */
    public int mo929v(InterfaceC1152e interfaceC1152e) throws IOException {
        if (this.f2733h) {
            return -1;
        }
        if (this.f2730e == null) {
            return 0;
        }
        int iMo1317D = interfaceC1152e.mo1317D();
        if (iMo1317D <= 0) {
            if (interfaceC1152e.mo1314A()) {
                return 0;
            }
            throw new IOException("FULL");
        }
        try {
            int iMo1333n = interfaceC1152e.mo1333n(this.f2730e, iMo1317D);
            if (iMo1333n < 0) {
                mo920m();
            }
            return iMo1333n;
        } catch (SocketTimeoutException unused) {
            mo1438z();
            return -1;
        }
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: w */
    public boolean mo930w(long j7) {
        return true;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: x */
    public int mo931x(InterfaceC1152e interfaceC1152e) {
        if (this.f2734i) {
            return -1;
        }
        if (this.f2731f == null) {
            return 0;
        }
        int length = interfaceC1152e.length();
        if (length > 0) {
            interfaceC1152e.mo1335r(this.f2731f);
        }
        if (!interfaceC1152e.mo1329f()) {
            interfaceC1152e.clear();
        }
        return length;
    }

    /* renamed from: z */
    public void mo1438z() throws IOException {
        InputStream inputStream = this.f2730e;
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
