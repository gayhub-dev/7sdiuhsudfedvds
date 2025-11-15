package p105m5;

import android.support.v4.app.NotificationCompat;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.BitSet;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import org.fourthline.cling.model.ServiceReference;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p097l5.AbstractC1443h;
import p097l5.C1444i;
import p097l5.C1445j;
import p097l5.InterfaceC1436a;
import p113n5.C1553n;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1561v;
import p161t5.C1911c;
import p161t5.C1923o;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import p189x5.AbstractC2082a;

/* compiled from: DigestAuthenticator.java */
/* renamed from: m5.d */
/* loaded from: classes.dex */
public class C1493d extends AbstractC1495f {

    /* renamed from: i */
    public static final InterfaceC2016c f4254i;

    /* renamed from: d */
    public SecureRandom f4255d = new SecureRandom();

    /* renamed from: e */
    public long f4256e = 60000;

    /* renamed from: f */
    public int f4257f = 1024;

    /* renamed from: g */
    public ConcurrentMap<String, b> f4258g = new ConcurrentHashMap();

    /* renamed from: h */
    public Queue<b> f4259h = new ConcurrentLinkedQueue();

    /* compiled from: DigestAuthenticator.java */
    /* renamed from: m5.d$a */
    public static class a extends AbstractC2082a {
        private static final long serialVersionUID = -2484639019549527724L;

        /* renamed from: f */
        public String f4260f = "";

        /* renamed from: g */
        public String f4261g = "";

        /* renamed from: h */
        public String f4262h = "";

        /* renamed from: i */
        public String f4263i = "";

        public a(String str) {
        }

        public String toString() {
            return this.f4260f + "," + this.f4263i;
        }
    }

    /* compiled from: DigestAuthenticator.java */
    /* renamed from: m5.d$b */
    public static class b {

        /* renamed from: a */
        public final String f4264a;

        /* renamed from: b */
        public final long f4265b;

        /* renamed from: c */
        public final BitSet f4266c;

        public b(String str, long j7, int i7) {
            this.f4264a = str;
            this.f4265b = j7;
            this.f4266c = new BitSet(i7);
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4254i = C2015b.m2349a(C1493d.class.getName());
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: a */
    public String mo1608a() {
        return "DIGEST";
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: b */
    public boolean mo1609b(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6, InterfaceC1543d.g gVar) {
        return true;
    }

    @Override // p105m5.AbstractC1495f, p097l5.InterfaceC1436a
    /* renamed from: c */
    public void mo1610c(InterfaceC1436a.a aVar) {
        super.mo1610c(aVar);
        String str = ((AbstractC1443h) aVar).f4193n.get("maxNonceAge");
        if (str != null) {
            this.f4256e = Long.valueOf(str).longValue();
        }
    }

    @Override // p097l5.InterfaceC1436a
    /* renamed from: d */
    public InterfaceC1543d mo1611d(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, boolean z6) throws C1444i, NumberFormatException {
        b bVar;
        String str;
        if (!z6) {
            return new C1492c(this);
        }
        InterfaceC0458c interfaceC0458c = (InterfaceC0458c) interfaceC0032r;
        InterfaceC0460e interfaceC0460e = (InterfaceC0460e) interfaceC0037w;
        String strMo173x = interfaceC0458c.mo173x(HttpHeaders.AUTHORIZATION);
        boolean z7 = true;
        if (strMo173x != null) {
            try {
                InterfaceC2016c interfaceC2016c = f4254i;
                if (interfaceC2016c.mo2353d()) {
                    interfaceC2016c.mo2351a("Credentials: " + strMo173x, new Object[0]);
                }
                C1923o c1923o = new C1923o(strMo173x, "=, ", true, false);
                a aVar = new a(interfaceC0458c.mo165N());
                String str2 = null;
                String str3 = null;
                while (c1923o.hasMoreTokens()) {
                    String strNextToken = c1923o.nextToken();
                    char cCharAt = strNextToken.length() == 1 ? strNextToken.charAt(0) : (char) 0;
                    if (cCharAt != ' ') {
                        if (cCharAt != ',') {
                            if (cCharAt == '=') {
                                str3 = str2;
                            } else if (str3 != null) {
                                if ("username".equalsIgnoreCase(str3)) {
                                    aVar.f4260f = strNextToken;
                                } else if (!"realm".equalsIgnoreCase(str3)) {
                                    if ("nonce".equalsIgnoreCase(str3)) {
                                        aVar.f4261g = strNextToken;
                                    } else if ("nc".equalsIgnoreCase(str3)) {
                                        aVar.f4262h = strNextToken;
                                    } else if (!"cnonce".equalsIgnoreCase(str3) && !"qop".equalsIgnoreCase(str3) && !NotificationCompat.MessagingStyle.Message.KEY_DATA_URI.equalsIgnoreCase(str3) && "response".equalsIgnoreCase(str3)) {
                                        aVar.f4263i = strNextToken;
                                    }
                                }
                                str3 = null;
                            }
                            str2 = strNextToken;
                        } else {
                            str3 = null;
                        }
                    }
                }
                int iM1653f = m1653f(aVar, (C1553n) interfaceC0458c);
                if (iM1653f > 0) {
                    InterfaceC1561v interfaceC1561vMo1654e = mo1654e(aVar.f4260f, aVar, interfaceC0032r);
                    if (interfaceC1561vMo1654e != null) {
                        return new C1445j("DIGEST", interfaceC1561vMo1654e);
                    }
                } else if (iM1653f == 0) {
                }
                z7 = false;
            } catch (IOException e7) {
                throw new C1444i(e7);
            }
        } else {
            z7 = false;
        }
        if (C1492c.m1651b(interfaceC0460e)) {
            return InterfaceC1543d.f4583a;
        }
        String strMo168d = interfaceC0458c.mo168d();
        if (strMo168d == null) {
            strMo168d = ServiceReference.DELIMITER;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Digest realm=\"");
        sb.append(this.f4273a.getName());
        sb.append("\", domain=\"");
        sb.append(strMo168d);
        sb.append("\", nonce=\"");
        C1553n c1553n = (C1553n) interfaceC0458c;
        do {
            byte[] bArr = new byte[24];
            this.f4255d.nextBytes(bArr);
            str = new String(C1911c.m2208b(bArr));
            bVar = new b(str, c1553n.f4628K, this.f4257f);
        } while (this.f4258g.putIfAbsent(str, bVar) != null);
        this.f4259h.add(bVar);
        sb.append(str);
        sb.append("\", algorithm=MD5, qop=\"auth\",");
        sb.append(" stale=");
        sb.append(z7);
        interfaceC0460e.mo177J("WWW-Authenticate", sb.toString());
        interfaceC0460e.mo183v(401);
        return InterfaceC1543d.f4585c;
    }

    /* renamed from: f */
    public final int m1653f(a aVar, C1553n c1553n) throws NumberFormatException {
        boolean z6;
        long j7 = c1553n.f4628K - this.f4256e;
        b bVarPeek = this.f4259h.peek();
        while (bVarPeek != null && bVarPeek.f4265b < j7) {
            this.f4259h.remove(bVarPeek);
            this.f4258g.remove(bVarPeek.f4264a);
            bVarPeek = this.f4259h.peek();
        }
        try {
            b bVar = this.f4258g.get(aVar.f4261g);
            if (bVar == null) {
                return 0;
            }
            long j8 = Long.parseLong(aVar.f4262h, 16);
            if (j8 >= this.f4257f) {
                return 0;
            }
            int i7 = (int) j8;
            synchronized (bVar) {
                if (i7 >= bVar.f4266c.size()) {
                    z6 = true;
                } else {
                    z6 = bVar.f4266c.get(i7);
                    bVar.f4266c.set(i7);
                }
            }
            return z6 ? -1 : 1;
        } catch (Exception e7) {
            f4254i.mo2360k(e7);
            return -1;
        }
    }
}
