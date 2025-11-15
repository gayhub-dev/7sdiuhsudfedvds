package p161t5;

import android.support.constraint.solver.widgets.analyzer.C0096a;
import com.ctvit.network.CtvitHttp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: DateCache.java */
/* renamed from: t5.h */
/* loaded from: classes.dex */
public class C1916h {

    /* renamed from: a */
    public String f5631a;

    /* renamed from: b */
    public String f5632b;

    /* renamed from: c */
    public SimpleDateFormat f5633c;

    /* renamed from: d */
    public String f5634d;

    /* renamed from: e */
    public SimpleDateFormat f5635e;

    /* renamed from: f */
    public String f5636f;

    /* renamed from: g */
    public String f5637g;

    /* renamed from: h */
    public String f5638h;

    /* renamed from: i */
    public long f5639i;

    /* renamed from: j */
    public long f5640j;

    /* renamed from: k */
    public int f5641k;

    /* renamed from: l */
    public String f5642l;

    /* renamed from: m */
    public Locale f5643m;

    public C1916h(String str) {
        this.f5639i = -1L;
        this.f5640j = -1L;
        this.f5641k = -1;
        this.f5642l = null;
        this.f5643m = null;
        this.f5631a = str;
        m2217c(TimeZone.getDefault());
    }

    /* renamed from: a */
    public synchronized String m2215a(long j7) {
        long j8 = j7 / 1000;
        long j9 = this.f5640j;
        if (j8 >= j9 && (j9 <= 0 || j8 <= 3600 + j9)) {
            if (j9 == j8) {
                return this.f5642l;
            }
            Date date = new Date(j7);
            long j10 = j8 / 60;
            if (this.f5639i != j10) {
                this.f5639i = j10;
                String str = this.f5635e.format(date);
                this.f5636f = str;
                int iIndexOf = str.indexOf("ss");
                this.f5637g = this.f5636f.substring(0, iIndexOf);
                this.f5638h = this.f5636f.substring(iIndexOf + 2);
            }
            this.f5640j = j8;
            StringBuilder sb = new StringBuilder(this.f5636f.length());
            sb.append(this.f5637g);
            int i7 = (int) (j8 % 60);
            if (i7 < 10) {
                sb.append('0');
            }
            sb.append(i7);
            sb.append(this.f5638h);
            String string = sb.toString();
            this.f5642l = string;
            return string;
        }
        return this.f5633c.format(new Date(j7));
    }

    /* renamed from: b */
    public final void m2216b() {
        if (this.f5632b.indexOf("ss.SSS") >= 0) {
            throw new IllegalStateException("ms not supported");
        }
        int iIndexOf = this.f5632b.indexOf("ss");
        this.f5634d = C0096a.m97a(this.f5632b.substring(0, iIndexOf), "'ss'", this.f5632b.substring(iIndexOf + 2));
    }

    /* renamed from: c */
    public synchronized void m2217c(TimeZone timeZone) {
        m2218d(timeZone);
        if (this.f5643m != null) {
            this.f5633c = new SimpleDateFormat(this.f5632b, this.f5643m);
            this.f5635e = new SimpleDateFormat(this.f5634d, this.f5643m);
        } else {
            this.f5633c = new SimpleDateFormat(this.f5632b);
            this.f5635e = new SimpleDateFormat(this.f5634d);
        }
        this.f5633c.setTimeZone(timeZone);
        this.f5635e.setTimeZone(timeZone);
        this.f5640j = -1L;
        this.f5639i = -1L;
    }

    /* renamed from: d */
    public final synchronized void m2218d(TimeZone timeZone) {
        int iIndexOf = this.f5631a.indexOf("ZZZ");
        if (iIndexOf >= 0) {
            String strSubstring = this.f5631a.substring(0, iIndexOf);
            String strSubstring2 = this.f5631a.substring(iIndexOf + 3);
            int rawOffset = timeZone.getRawOffset();
            StringBuilder sb = new StringBuilder(this.f5631a.length() + 10);
            sb.append(strSubstring);
            sb.append("'");
            if (rawOffset >= 0) {
                sb.append('+');
            } else {
                rawOffset = -rawOffset;
                sb.append('-');
            }
            int i7 = rawOffset / CtvitHttp.DEFAULT_MILLISECONDS;
            int i8 = i7 / 60;
            int i9 = i7 % 60;
            if (i8 < 10) {
                sb.append('0');
            }
            sb.append(i8);
            if (i9 < 10) {
                sb.append('0');
            }
            sb.append(i9);
            sb.append('\'');
            sb.append(strSubstring2);
            this.f5632b = sb.toString();
        } else {
            this.f5632b = this.f5631a;
        }
        m2216b();
    }

    public C1916h(String str, Locale locale) {
        this.f5639i = -1L;
        this.f5640j = -1L;
        this.f5641k = -1;
        this.f5642l = null;
        this.f5643m = null;
        this.f5631a = str;
        this.f5643m = locale;
        m2217c(TimeZone.getDefault());
    }
}
