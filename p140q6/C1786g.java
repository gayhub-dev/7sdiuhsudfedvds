package p140q6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import p082j6.C1212a;
import p159t3.C1903b;
import p186x2.C2074b;

/* compiled from: ExchangeImpl.java */
/* renamed from: q6.g */
/* loaded from: classes.dex */
public class C1786g {

    /* renamed from: t */
    public static TimeZone f5077t = TimeZone.getTimeZone("GMT");

    /* renamed from: u */
    public static DateFormat f5078u;

    /* renamed from: a */
    public C1903b f5079a;

    /* renamed from: c */
    public C1797r f5081c;

    /* renamed from: d */
    public String f5082d;

    /* renamed from: e */
    public URI f5083e;

    /* renamed from: f */
    public C1789j f5084f;

    /* renamed from: g */
    public int f5085g;

    /* renamed from: h */
    public InputStream f5086h;

    /* renamed from: i */
    public OutputStream f5087i;

    /* renamed from: j */
    public boolean f5088j;

    /* renamed from: k */
    public boolean f5089k;

    /* renamed from: m */
    public InputStream f5091m;

    /* renamed from: n */
    public OutputStream f5092n;

    /* renamed from: o */
    public AbstractC1795p f5093o;

    /* renamed from: p */
    public C1796q f5094p;

    /* renamed from: q */
    public boolean f5095q;

    /* renamed from: r */
    public C1801v f5096r;

    /* renamed from: l */
    public boolean f5090l = false;

    /* renamed from: s */
    public byte[] f5097s = new byte[128];

    /* renamed from: b */
    public C1903b f5080b = new C1903b();

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        f5078u = simpleDateFormat;
        simpleDateFormat.setTimeZone(f5077t);
    }

    public C1786g(String str, URI uri, C1797r c1797r, int i7, C1789j c1789j) {
        this.f5081c = c1797r;
        this.f5079a = c1797r.m2000b();
        this.f5082d = str;
        this.f5083e = uri;
        this.f5084f = c1789j;
        this.f5085g = i7;
        this.f5087i = c1797r.f5128d;
        this.f5086h = c1797r.f5127c;
        C1801v c1801v = c1789j.f5102a.f5115d;
        this.f5096r = c1801v;
        synchronized (c1801v) {
            c1801v.f5196u++;
        }
    }

    /* renamed from: a */
    public final byte[] m1977a(String str, int i7) {
        int length = str.length() + i7;
        byte[] bArr = this.f5097s;
        if (length > bArr.length) {
            this.f5097s = new byte[(bArr.length + (length - bArr.length)) * 2];
        }
        char[] charArray = str.toCharArray();
        for (int i8 = 0; i8 < charArray.length; i8++) {
            this.f5097s[i8] = (byte) charArray[i8];
        }
        return this.f5097s;
    }

    /* renamed from: b */
    public InetSocketAddress m1978b() {
        Socket socket = this.f5084f.f5107f.socket();
        return new InetSocketAddress(socket.getLocalAddress(), socket.getLocalPort());
    }

    /* renamed from: c */
    public InetSocketAddress m1979c() {
        Socket socket = this.f5084f.f5107f.socket();
        return new InetSocketAddress(socket.getInetAddress(), socket.getPort());
    }

    /* renamed from: d */
    public InputStream m1980d() {
        InputStream inputStream = this.f5091m;
        if (inputStream != null) {
            return inputStream;
        }
        if (this.f5085g == -1) {
            C1781b c1781b = new C1781b(this, this.f5086h);
            this.f5093o = c1781b;
            this.f5091m = c1781b;
        } else {
            C1787h c1787h = new C1787h(this, this.f5086h, this.f5085g);
            this.f5093o = c1787h;
            this.f5091m = c1787h;
        }
        return this.f5091m;
    }

    /* renamed from: e */
    public OutputStream m1981e() {
        if (this.f5092n == null) {
            C1796q c1796q = new C1796q(null);
            this.f5094p = c1796q;
            this.f5092n = c1796q;
        }
        return this.f5092n;
    }

    /* renamed from: f */
    public void m1982f(int i7, long j7) throws IOException {
        boolean z6;
        if (this.f5095q) {
            throw new IOException("headers already sent");
        }
        String str = "HTTP/1.1 " + i7 + C2074b.m2499v(i7) + "\r\n";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.f5087i);
        m1981e();
        C1796q c1796q = this.f5094p;
        bufferedOutputStream.write(m1977a(str, 0), 0, str.length());
        this.f5080b.mo2019g("Date", f5078u.format(new Date()));
        long j8 = 0;
        if (j7 == 0) {
            if (this.f5090l) {
                c1796q.f5124e = new C1803x(this, this.f5087i);
                this.f5088j = true;
            } else {
                this.f5080b.mo2019g("Transfer-encoding", "chunked");
                c1796q.f5124e = new C1782c(this, this.f5087i);
            }
            z6 = false;
        } else {
            if (j7 == -1) {
                z6 = true;
            } else {
                j8 = j7;
                z6 = false;
            }
            if (this.f5080b.mo2016c("Content-length") == null) {
                this.f5080b.mo2019g("Content-length", Long.toString(j8));
            }
            c1796q.f5124e = new C1788i(this, this.f5087i, j8);
        }
        for (Map.Entry<String, List<String>> entry : this.f5080b.entrySet()) {
            String key = entry.getKey();
            for (String str2 : entry.getValue()) {
                int length = key.length();
                byte[] bArrM1977a = m1977a(key, 2);
                int i8 = length + 1;
                bArrM1977a[length] = 58;
                bArrM1977a[i8] = 32;
                bufferedOutputStream.write(bArrM1977a, 0, i8 + 1);
                byte[] bArrM1977a2 = m1977a(str2, 2);
                int length2 = str2.length();
                int i9 = length2 + 1;
                bArrM1977a2[length2] = C1212a.f2735CR;
                bArrM1977a2[i9] = 10;
                bufferedOutputStream.write(bArrM1977a2, 0, i9 + 1);
            }
        }
        bufferedOutputStream.write(13);
        bufferedOutputStream.write(10);
        bufferedOutputStream.flush();
        this.f5095q = true;
        if (z6) {
            this.f5096r.m2007a(new C1805z(this));
            this.f5089k = true;
        }
        this.f5096r.m2008c(i7, this.f5081c.f5125a, null);
    }
}
