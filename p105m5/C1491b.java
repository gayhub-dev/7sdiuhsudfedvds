package p105m5;

import java.security.Principal;
import java.security.cert.X509Certificate;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p097l5.C1444i;
import p097l5.C1445j;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1561v;
import p161t5.C1911c;

/* compiled from: ClientCertAuthenticator.java */
/* renamed from: m5.b */
/* loaded from: classes.dex */
public class C1491b extends AbstractC1495f {
    @Override // p097l5.InterfaceC1436a
    /* renamed from: a */
    public String mo1608a() {
        return "CLIENT_CERT";
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: b */
    public boolean mo1609b(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6, InterfaceC1543d.g gVar) {
        return true;
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: d */
    public InterfaceC1543d mo1611d(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6) throws C1444i {
        if (!z6) {
            return new C1492c(this);
        }
        InterfaceC0460e interfaceC0460e = (InterfaceC0460e) interfaceC0037w;
        X509Certificate[] x509CertificateArr = (X509Certificate[]) ((InterfaceC0458c) interfaceC0032r).mo26a("javax.servlet.request.X509Certificate");
        if (x509CertificateArr != null) {
            try {
                if (x509CertificateArr.length > 0) {
                    for (X509Certificate x509Certificate : x509CertificateArr) {
                        if (x509Certificate != null) {
                            Principal subjectDN = x509Certificate.getSubjectDN();
                            if (subjectDN == null) {
                                subjectDN = x509Certificate.getIssuerDN();
                            }
                            InterfaceC1561v interfaceC1561vMo1654e = mo1654e(subjectDN == null ? "clientcert" : subjectDN.getName(), C1911c.m2208b(x509Certificate.getSignature()), interfaceC0032r);
                            if (interfaceC1561vMo1654e != null) {
                                return new C1445j("CLIENT_CERT", interfaceC1561vMo1654e);
                            }
                        }
                    }
                }
            } catch (Exception e7) {
                throw new C1444i(e7.getMessage());
            }
        }
        if (C1492c.m1651b(interfaceC0460e)) {
            return InterfaceC1543d.f4583a;
        }
        interfaceC0460e.mo183v(403);
        return InterfaceC1543d.f4586d;
    }
}
