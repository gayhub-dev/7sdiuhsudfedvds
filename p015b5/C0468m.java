package p015b5;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import p006a5.AbstractC0030p;
import p006a5.InterfaceC0037w;

/* compiled from: HttpServlet.java */
/* renamed from: b5.m */
/* loaded from: classes.dex */
public class C0468m extends C0461f {

    /* renamed from: g */
    public static final ResourceBundle f281g = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");

    /* renamed from: c */
    public C0467l f282c;

    /* renamed from: d */
    public PrintWriter f283d;

    /* renamed from: e */
    public boolean f284e;

    /* renamed from: f */
    public boolean f285f;

    public C0468m(InterfaceC0460e interfaceC0460e) {
        super(interfaceC0460e);
        this.f282c = new C0467l();
    }

    @Override // p035e.C0892e, p006a5.InterfaceC0037w
    /* renamed from: F */
    public PrintWriter mo41F() {
        if (this.f285f) {
            throw new IllegalStateException(f281g.getString("err.ise.getWriter"));
        }
        if (this.f283d == null) {
            this.f283d = new PrintWriter(new OutputStreamWriter(this.f282c, ((InterfaceC0037w) this.f1523b).mo46j()));
        }
        return this.f283d;
    }

    @Override // p035e.C0892e, p006a5.InterfaceC0037w
    /* renamed from: H */
    public void mo42H(int i7) {
        super.mo42H(i7);
        this.f284e = true;
    }

    @Override // p035e.C0892e, p006a5.InterfaceC0037w
    /* renamed from: i */
    public AbstractC0030p mo45i() {
        if (this.f283d != null) {
            throw new IllegalStateException(f281g.getString("err.ise.getOutputStream"));
        }
        this.f285f = true;
        return this.f282c;
    }
}
