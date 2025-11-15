package p105m5;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p097l5.C1444i;
import p097l5.C1445j;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1561v;
import p161t5.C1911c;

/* compiled from: BasicAuthenticator.java */
/* renamed from: m5.a */
/* loaded from: classes.dex */
public class C1490a extends AbstractC1495f {
    @Override // p097l5.InterfaceC1436a
    /* renamed from: a */
    public String mo1608a() {
        return "BASIC";
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: b */
    public boolean mo1609b(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6, InterfaceC1543d.g gVar) {
        return true;
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: d */
    public InterfaceC1543d mo1611d(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6) throws C1444i {
        int iIndexOf;
        byte[] byteArray;
        InterfaceC1561v interfaceC1561vMo1654e;
        InterfaceC0458c interfaceC0458c = (InterfaceC0458c) interfaceC0032r;
        InterfaceC0460e interfaceC0460e = (InterfaceC0460e) interfaceC0037w;
        String strMo173x = interfaceC0458c.mo173x(HttpHeaders.AUTHORIZATION);
        try {
            if (!z6) {
                return new C1492c(this);
            }
            if (strMo173x != null && (iIndexOf = strMo173x.indexOf(32)) > 0 && "basic".equalsIgnoreCase(strMo173x.substring(0, iIndexOf))) {
                String strSubstring = strMo173x.substring(iIndexOf + 1);
                char[] cArr = C1911c.f5613a;
                if (strSubstring == null) {
                    byteArray = null;
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((strSubstring.length() * 4) / 3);
                    C1911c.m2207a(strSubstring, byteArrayOutputStream);
                    byteArray = byteArrayOutputStream.toByteArray();
                }
                String str = new String(byteArray, "ISO-8859-1");
                int iIndexOf2 = str.indexOf(58);
                if (iIndexOf2 > 0 && (interfaceC1561vMo1654e = mo1654e(str.substring(0, iIndexOf2), str.substring(iIndexOf2 + 1), interfaceC0458c)) != null) {
                    return new C1445j("BASIC", interfaceC1561vMo1654e);
                }
            }
            if (C1492c.m1651b(interfaceC0460e)) {
                return InterfaceC1543d.f4583a;
            }
            interfaceC0460e.mo177J("WWW-Authenticate", "basic realm=\"" + this.f4273a.getName() + '\"');
            interfaceC0460e.mo183v(401);
            return InterfaceC1543d.f4585c;
        } catch (IOException e7) {
            throw new C1444i(e7);
        }
    }
}
