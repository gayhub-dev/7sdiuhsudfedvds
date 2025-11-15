package p140q6;

import java.util.HashMap;
import java.util.LinkedList;
import p159t3.AbstractC1902a;
import p159t3.AbstractC1904c;
import p159t3.InterfaceC1906e;

/* compiled from: HttpContextImpl.java */
/* renamed from: q6.k */
/* loaded from: classes.dex */
public class C1790k extends AbstractC1904c {

    /* renamed from: a */
    public String f5112a;

    /* renamed from: b */
    public String f5113b;

    /* renamed from: c */
    public InterfaceC1906e f5114c;

    /* renamed from: d */
    public C1801v f5115d;

    /* renamed from: e */
    public LinkedList<AbstractC1902a> f5116e;

    /* renamed from: f */
    public LinkedList<AbstractC1902a> f5117f;

    public C1790k(String str, String str2, InterfaceC1906e interfaceC1906e, C1801v c1801v) {
        super(0);
        new HashMap();
        this.f5116e = new LinkedList<>();
        this.f5117f = new LinkedList<>();
        if (str == null || str2.length() < 1 || str2.charAt(0) != '/') {
            throw new IllegalArgumentException("Illegal value for path or protocol");
        }
        String lowerCase = str.toLowerCase();
        this.f5113b = lowerCase;
        this.f5112a = str2;
        if (!lowerCase.equals("http") && !this.f5113b.equals("https")) {
            throw new IllegalArgumentException("Illegal value for protocol");
        }
        this.f5114c = interfaceC1906e;
        this.f5115d = c1801v;
        this.f5116e.add(new C1780a());
    }
}
