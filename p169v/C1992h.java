package p169v;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import p009b.C0413b;
import p010b0.C0420f;
import p141r.EnumC1811f;
import p142r0.C1817b;
import p142r0.C1819d;
import p162u.C1961e;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: HttpUrlFetcher.java */
/* renamed from: v.h */
/* loaded from: classes.dex */
public class C1992h implements InterfaceC1986b<InputStream> {

    /* renamed from: j */
    public static final b f5813j = new a();

    /* renamed from: e */
    public final C0420f f5814e;

    /* renamed from: f */
    public final int f5815f;

    /* renamed from: g */
    public HttpURLConnection f5816g;

    /* renamed from: h */
    public InputStream f5817h;

    /* renamed from: i */
    public volatile boolean f5818i;

    /* compiled from: HttpUrlFetcher.java */
    /* renamed from: v.h$a */
    public static class a implements b {
    }

    /* compiled from: HttpUrlFetcher.java */
    /* renamed from: v.h$b */
    public interface b {
    }

    public C1992h(C0420f c0420f, int i7) {
        this.f5814e = c0420f;
        this.f5815f = i7;
    }

    @Override // p169v.InterfaceC1986b
    @NonNull
    /* renamed from: a */
    public Class<InputStream> mo123a() {
        return InputStream.class;
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: b */
    public void mo124b() throws IOException {
        InputStream inputStream = this.f5817h;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.f5816g;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: c */
    public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super InputStream> aVar) {
        int i7 = C1819d.f5292b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        try {
            InputStream inputStreamM2328d = m2328d(this.f5814e.m132d(), 0, null, this.f5814e.f208b.mo133a());
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                StringBuilder sbM112a = C0413b.m112a("Finished http url fetcher fetch in ");
                sbM112a.append(C1819d.m2050a(jElapsedRealtimeNanos));
                sbM112a.append(" ms and loaded ");
                sbM112a.append(inputStreamM2328d);
            }
            aVar.mo141e(inputStreamM2328d);
        } catch (IOException e7) {
            Log.isLoggable("HttpUrlFetcher", 3);
            aVar.mo140d(e7);
        }
    }

    @Override // p169v.InterfaceC1986b
    public void cancel() {
        this.f5818i = true;
    }

    /* renamed from: d */
    public final InputStream m2328d(URL url, int i7, URL url2, Map<String, String> map) throws IOException {
        if (i7 >= 5) {
            throw new C1961e("Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new C1961e("In re-direct loop");
                }
            } catch (URISyntaxException unused) {
            }
        }
        this.f5816g = (HttpURLConnection) url.openConnection();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.f5816g.addRequestProperty(entry.getKey(), entry.getValue());
        }
        this.f5816g.setConnectTimeout(this.f5815f);
        this.f5816g.setReadTimeout(this.f5815f);
        this.f5816g.setUseCaches(false);
        this.f5816g.setDoInput(true);
        this.f5816g.setInstanceFollowRedirects(false);
        this.f5816g.connect();
        if (this.f5818i) {
            return null;
        }
        int responseCode = this.f5816g.getResponseCode();
        int i8 = responseCode / 100;
        if (i8 == 2) {
            HttpURLConnection httpURLConnection = this.f5816g;
            if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
                this.f5817h = new C1817b(httpURLConnection.getInputStream(), httpURLConnection.getContentLength());
            } else {
                if (Log.isLoggable("HttpUrlFetcher", 3)) {
                    httpURLConnection.getContentEncoding();
                }
                this.f5817h = httpURLConnection.getInputStream();
            }
            return this.f5817h;
        }
        if (i8 != 3) {
            if (responseCode == -1) {
                throw new C1961e(responseCode);
            }
            throw new C1961e(this.f5816g.getResponseMessage(), responseCode);
        }
        String headerField = this.f5816g.getHeaderField("Location");
        if (TextUtils.isEmpty(headerField)) {
            throw new C1961e("Received empty or null redirect url");
        }
        return m2328d(new URL(url, headerField), i7 + 1, url, map);
    }

    @Override // p169v.InterfaceC1986b
    @NonNull
    public EnumC1957a getDataSource() {
        return EnumC1957a.REMOTE;
    }
}
