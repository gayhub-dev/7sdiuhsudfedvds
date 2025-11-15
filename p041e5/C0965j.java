package p041e5;

import android.support.constraint.motion.C0080b;
import android.support.v7.widget.ActivityChooserView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import org.fourthline.cling.model.ServiceReference;
import p007a6.C0045e;
import p065h5.C1101i;
import p065h5.C1107o;
import p065h5.C1110r;
import p065h5.C1113u;
import p065h5.C1114v;
import p073i5.C1153f;
import p073i5.C1158k;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1160m;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: HttpExchange.java */
/* renamed from: e5.j */
/* loaded from: classes.dex */
public class C0965j {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final InterfaceC2016c LOG;
    public static final int STATUS_CANCELLED = 11;
    public static final int STATUS_CANCELLING = 10;
    public static final int STATUS_COMPLETED = 7;
    public static final int STATUS_EXCEPTED = 9;
    public static final int STATUS_EXPIRED = 8;
    public static final int STATUS_PARSING_CONTENT = 6;
    public static final int STATUS_PARSING_HEADERS = 5;
    public static final int STATUS_SENDING_COMPLETED = 14;
    public static final int STATUS_SENDING_PARSING_CONTENT = 13;
    public static final int STATUS_SENDING_PARSING_HEADERS = 12;
    public static final int STATUS_SENDING_REQUEST = 3;
    public static final int STATUS_START = 0;
    public static final int STATUS_WAITING_FOR_COMMIT = 2;
    public static final int STATUS_WAITING_FOR_CONNECTION = 1;
    public static final int STATUS_WAITING_FOR_RESPONSE = 4;
    private C0957b _address;
    private volatile AbstractC0956a _connection;
    public boolean _onDone;
    public boolean _onRequestCompleteDone;
    public boolean _onResponseCompleteDone;
    private InterfaceC1152e _requestContent;
    private InputStream _requestContentSource;
    private volatile C0045e.a _timeoutTask;
    private String _uri;
    private String _method = "GET";
    private InterfaceC1152e _scheme = C1110r.f2362a;
    private int _version = 11;
    private final C1101i _requestFields = new C1101i();
    private AtomicInteger _status = new AtomicInteger(0);
    private boolean _retryStatus = false;
    private boolean _configureListeners = true;
    private InterfaceC0964i _listener = new b(null);
    private C0957b _localAddress = null;
    private long _timeout = -1;
    private long _lastStateChange = System.currentTimeMillis();
    private long _sent = -1;
    private int _lastState = -1;
    private int _lastStatePeriod = -1;

    /* compiled from: HttpExchange.java */
    /* renamed from: e5.j$a */
    public class a extends C0045e.a {

        /* renamed from: i */
        public final /* synthetic */ C0963h f1778i;

        public a(C0963h c0963h) {
            this.f1778i = c0963h;
        }

        @Override // p007a6.C0045e.a
        /* renamed from: c */
        public void mo57c() {
            C0965j.this.expire(this.f1778i);
        }
    }

    /* compiled from: HttpExchange.java */
    /* renamed from: e5.j$b */
    public class b implements InterfaceC0964i {
        public b(a aVar) {
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: a */
        public void mo875a() {
            C0965j.this.onRequestCommitted();
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: b */
        public void mo876b() {
            try {
                C0965j.this.onResponseComplete();
                synchronized (C0965j.this) {
                    C0965j c0965j = C0965j.this;
                    c0965j._onResponseCompleteDone = true;
                    boolean z6 = c0965j._onDone | c0965j._onRequestCompleteDone;
                    c0965j._onDone = z6;
                    if (z6) {
                        c0965j.disassociate();
                    }
                    C0965j.this.notifyAll();
                }
            } catch (Throwable th) {
                synchronized (C0965j.this) {
                    C0965j c0965j2 = C0965j.this;
                    c0965j2._onResponseCompleteDone = true;
                    boolean z7 = c0965j2._onDone | c0965j2._onRequestCompleteDone;
                    c0965j2._onDone = z7;
                    if (z7) {
                        c0965j2.disassociate();
                    }
                    C0965j.this.notifyAll();
                    throw th;
                }
            }
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: c */
        public void mo877c(InterfaceC1152e interfaceC1152e) {
            C0965j.this.onResponseContent(interfaceC1152e);
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: d */
        public void mo878d(Throwable th) {
            try {
                C0965j.this.onConnectionFailed(th);
            } finally {
                C0965j.this.done();
            }
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: e */
        public void mo879e(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
            C0965j.this.onResponseHeader(interfaceC1152e, interfaceC1152e2);
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: f */
        public void mo880f(Throwable th) {
            try {
                C0965j.this.onException(th);
            } finally {
                C0965j.this.done();
            }
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: g */
        public void mo881g() {
            C0965j.this.onResponseHeaderComplete();
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: h */
        public void mo882h() {
            try {
                C0965j.this.onExpire();
            } finally {
                C0965j.this.done();
            }
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: i */
        public void mo883i(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2) {
            C0965j.this.onResponseStatus(interfaceC1152e, i7, interfaceC1152e2);
        }

        @Override // p041e5.InterfaceC0964i
        /* renamed from: j */
        public void mo884j() {
            try {
                C0965j.this.onRequestComplete();
                synchronized (C0965j.this) {
                    C0965j c0965j = C0965j.this;
                    c0965j._onRequestCompleteDone = true;
                    boolean z6 = c0965j._onDone | c0965j._onResponseCompleteDone;
                    c0965j._onDone = z6;
                    if (z6) {
                        c0965j.disassociate();
                    }
                    C0965j.this.notifyAll();
                }
            } catch (Throwable th) {
                synchronized (C0965j.this) {
                    C0965j c0965j2 = C0965j.this;
                    c0965j2._onRequestCompleteDone = true;
                    boolean z7 = c0965j2._onDone | c0965j2._onResponseCompleteDone;
                    c0965j2._onDone = z7;
                    if (z7) {
                        c0965j2.disassociate();
                    }
                    C0965j.this.notifyAll();
                    throw th;
                }
            }
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        LOG = C2015b.m2349a(C0965j.class.getName());
    }

    private void abort() {
        AbstractC0956a abstractC0956a = this._connection;
        try {
            if (abstractC0956a != null) {
                try {
                    abstractC0956a.m863f();
                } catch (IOException e7) {
                    LOG.mo2359j(e7);
                }
            }
        } finally {
            disassociate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void done() {
        synchronized (this) {
            disassociate();
            this._onDone = true;
            notifyAll();
        }
    }

    private boolean isResponseCompleted() {
        boolean z6;
        synchronized (this) {
            z6 = this._onResponseCompleteDone;
        }
        return z6;
    }

    private boolean setStatusExpired(int i7, int i8) {
        boolean zCompareAndSet = this._status.compareAndSet(i8, i7);
        if (zCompareAndSet) {
            getEventListener().mo882h();
        }
        return zCompareAndSet;
    }

    public static String toState(int i7) {
        switch (i7) {
            case 0:
                return "START";
            case 1:
                return "CONNECTING";
            case 2:
                return "CONNECTED";
            case 3:
                return "SENDING";
            case 4:
                return "WAITING";
            case 5:
                return "HEADERS";
            case 6:
                return "CONTENT";
            case 7:
                return "COMPLETED";
            case 8:
                return "EXPIRED";
            case 9:
                return "EXCEPTED";
            case 10:
                return "CANCELLING";
            case 11:
                return "CANCELLED";
            case 12:
                return "SENDING+HEADERS";
            case 13:
                return "SENDING+CONTENT";
            case 14:
                return "SENDING+COMPLETED";
            default:
                return "UNKNOWN";
        }
    }

    public void addRequestHeader(String str, String str2) {
        C1101i requestFields = getRequestFields();
        Objects.requireNonNull(requestFields);
        if (str2 == null) {
            return;
        }
        requestFields.m1222a(C1107o.f2326d.m1360h(str), requestFields.m1224c(str2));
    }

    public void associate(AbstractC0956a abstractC0956a) {
        if (abstractC0956a.f2538a.mo914g() != null) {
            this._localAddress = new C0957b(abstractC0956a.f2538a.mo914g(), abstractC0956a.f2538a.mo913f());
        }
        this._connection = abstractC0956a;
        if (getStatus() == 10) {
            abort();
        }
    }

    public void cancel() {
        setStatus(10);
        abort();
    }

    public void cancelTimeout(C0962g c0962g) {
        C0045e.a aVar = this._timeoutTask;
        if (aVar != null) {
            Objects.requireNonNull(c0962g);
            aVar.m56b();
        }
        this._timeoutTask = null;
    }

    public boolean configureListeners() {
        return this._configureListeners;
    }

    public AbstractC0956a disassociate() {
        AbstractC0956a abstractC0956a = this._connection;
        this._connection = null;
        if (getStatus() == 10) {
            setStatus(11);
        }
        return abstractC0956a;
    }

    public void expire(C0963h c0963h) {
        AbstractC0956a abstractC0956a = this._connection;
        int status = getStatus();
        if (status < 7 || status == 12 || status == 13 || status == 14) {
            setStatus(8);
        }
        synchronized (c0963h) {
            c0963h.f1760a.remove(this);
        }
        if (abstractC0956a != null) {
            abstractC0956a.mo865h(this);
        }
    }

    public C0957b getAddress() {
        return this._address;
    }

    public InterfaceC0964i getEventListener() {
        return this._listener;
    }

    public C0957b getLocalAddress() {
        return this._localAddress;
    }

    public String getMethod() {
        return this._method;
    }

    public InterfaceC1152e getRequestContent() {
        return this._requestContent;
    }

    public InterfaceC1152e getRequestContentChunk(InterfaceC1152e interfaceC1152e) {
        synchronized (this) {
            if (this._requestContentSource != null) {
                if (interfaceC1152e == null) {
                    interfaceC1152e = new C1158k(8192);
                }
                int i7 = this._requestContentSource.read(interfaceC1152e.mo1349P(), interfaceC1152e.mo1322M(), interfaceC1152e.mo1317D());
                if (i7 >= 0) {
                    interfaceC1152e.mo1324Q(interfaceC1152e.mo1322M() + i7);
                    return interfaceC1152e;
                }
            }
            return null;
        }
    }

    public InputStream getRequestContentSource() {
        return this._requestContentSource;
    }

    public C1101i getRequestFields() {
        return this._requestFields;
    }

    public String getRequestURI() {
        return this._uri;
    }

    public boolean getRetryStatus() {
        return this._retryStatus;
    }

    public InterfaceC1152e getScheme() {
        return this._scheme;
    }

    public int getStatus() {
        return this._status.get();
    }

    public long getTimeout() {
        return this._timeout;
    }

    @Deprecated
    public String getURI() {
        return getRequestURI();
    }

    public int getVersion() {
        return this._version;
    }

    public boolean isAssociated() {
        return this._connection != null;
    }

    public boolean isDone() {
        boolean z6;
        synchronized (this) {
            z6 = this._onDone;
        }
        return z6;
    }

    public void onConnectionFailed(Throwable th) {
        LOG.mo2354e("CONNECTION FAILED " + this, th);
    }

    public void onException(Throwable th) {
        LOG.mo2354e("EXCEPTION " + this, th);
    }

    public void onExpire() {
        LOG.mo2356g("EXPIRED " + this, new Object[0]);
    }

    public void onRequestCommitted() {
    }

    public void onRequestComplete() {
    }

    public void onResponseComplete() {
    }

    public void onResponseContent(InterfaceC1152e interfaceC1152e) {
    }

    public void onResponseHeader(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
    }

    public void onResponseHeaderComplete() {
    }

    public void onResponseStatus(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2) {
    }

    public void onRetry() throws IOException {
        InputStream inputStream = this._requestContentSource;
        if (inputStream != null) {
            if (!inputStream.markSupported()) {
                throw new IOException("Unsupported retry attempt");
            }
            this._requestContent = null;
            this._requestContentSource.reset();
        }
    }

    public InterfaceC1160m onSwitchProtocol(InterfaceC1161n interfaceC1161n) {
        return null;
    }

    public void reset() {
        synchronized (this) {
            this._timeoutTask = null;
            this._onRequestCompleteDone = false;
            this._onResponseCompleteDone = false;
            this._onDone = false;
            setStatus(0);
        }
    }

    public void scheduleTimeout(C0963h c0963h) {
        this._timeoutTask = new a(c0963h);
        C0962g c0962g = c0963h.f1764e;
        long timeout = getTimeout();
        if (timeout <= 0) {
            c0962g.f1752s.m53d(this._timeoutTask, 0L);
        } else {
            C0045e.a aVar = this._timeoutTask;
            C0045e c0045e = c0962g.f1752s;
            c0045e.m53d(aVar, timeout - c0045e.f60b);
        }
    }

    public void setAddress(C0957b c0957b) {
        this._address = c0957b;
    }

    public void setConfigureListeners(boolean z6) {
        this._configureListeners = z6;
    }

    public void setEventListener(InterfaceC0964i interfaceC0964i) {
        this._listener = interfaceC0964i;
    }

    public void setMethod(String str) {
        this._method = str;
    }

    public void setRequestContent(InterfaceC1152e interfaceC1152e) {
        this._requestContent = interfaceC1152e;
    }

    public void setRequestContentSource(InputStream inputStream) {
        this._requestContentSource = inputStream;
        if (inputStream == null || !inputStream.markSupported()) {
            return;
        }
        this._requestContentSource.mark(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public void setRequestContentType(String str) {
        getRequestFields().m1229j(C1107o.f2331i, str);
    }

    public void setRequestHeader(String str, String str2) {
        getRequestFields().m1230k(str, str2);
    }

    public void setRequestURI(String str) {
        this._uri = str;
    }

    public void setRetryStatus(boolean z6) {
        this._retryStatus = z6;
    }

    public void setScheme(InterfaceC1152e interfaceC1152e) {
        this._scheme = interfaceC1152e;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x01ac A[Catch: IOException -> 0x022d, TryCatch #1 {IOException -> 0x022d, blocks: (B:3:0x0001, B:5:0x000a, B:7:0x001b, B:10:0x0028, B:129:0x0217, B:130:0x022c, B:12:0x002e, B:14:0x0032, B:15:0x003a, B:16:0x0040, B:23:0x0057, B:24:0x005f, B:25:0x0065, B:29:0x0078, B:37:0x0093, B:38:0x009b, B:39:0x00a1, B:43:0x00b4, B:52:0x00ce, B:59:0x00e3, B:60:0x00e7, B:63:0x00f3, B:65:0x00f7, B:67:0x00fd, B:68:0x0105, B:69:0x010d, B:70:0x0115, B:72:0x011a, B:73:0x0122, B:74:0x0128, B:80:0x013e, B:81:0x0146, B:82:0x014c, B:89:0x0164, B:90:0x016a, B:93:0x0176, B:95:0x017b, B:96:0x0182, B:97:0x0187, B:98:0x0190, B:105:0x01a7, B:106:0x01ac, B:108:0x01b5, B:110:0x01ba, B:111:0x01bf, B:115:0x01cc, B:117:0x01d1, B:118:0x01d6), top: B:135:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01bf A[Catch: IOException -> 0x022d, TryCatch #1 {IOException -> 0x022d, blocks: (B:3:0x0001, B:5:0x000a, B:7:0x001b, B:10:0x0028, B:129:0x0217, B:130:0x022c, B:12:0x002e, B:14:0x0032, B:15:0x003a, B:16:0x0040, B:23:0x0057, B:24:0x005f, B:25:0x0065, B:29:0x0078, B:37:0x0093, B:38:0x009b, B:39:0x00a1, B:43:0x00b4, B:52:0x00ce, B:59:0x00e3, B:60:0x00e7, B:63:0x00f3, B:65:0x00f7, B:67:0x00fd, B:68:0x0105, B:69:0x010d, B:70:0x0115, B:72:0x011a, B:73:0x0122, B:74:0x0128, B:80:0x013e, B:81:0x0146, B:82:0x014c, B:89:0x0164, B:90:0x016a, B:93:0x0176, B:95:0x017b, B:96:0x0182, B:97:0x0187, B:98:0x0190, B:105:0x01a7, B:106:0x01ac, B:108:0x01b5, B:110:0x01ba, B:111:0x01bf, B:115:0x01cc, B:117:0x01d1, B:118:0x01d6), top: B:135:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01d6 A[Catch: IOException -> 0x022d, TRY_LEAVE, TryCatch #1 {IOException -> 0x022d, blocks: (B:3:0x0001, B:5:0x000a, B:7:0x001b, B:10:0x0028, B:129:0x0217, B:130:0x022c, B:12:0x002e, B:14:0x0032, B:15:0x003a, B:16:0x0040, B:23:0x0057, B:24:0x005f, B:25:0x0065, B:29:0x0078, B:37:0x0093, B:38:0x009b, B:39:0x00a1, B:43:0x00b4, B:52:0x00ce, B:59:0x00e3, B:60:0x00e7, B:63:0x00f3, B:65:0x00f7, B:67:0x00fd, B:68:0x0105, B:69:0x010d, B:70:0x0115, B:72:0x011a, B:73:0x0122, B:74:0x0128, B:80:0x013e, B:81:0x0146, B:82:0x014c, B:89:0x0164, B:90:0x016a, B:93:0x0176, B:95:0x017b, B:96:0x0182, B:97:0x0187, B:98:0x0190, B:105:0x01a7, B:106:0x01ac, B:108:0x01b5, B:110:0x01ba, B:111:0x01bf, B:115:0x01cc, B:117:0x01d1, B:118:0x01d6), top: B:135:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01df A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x016a A[Catch: IOException -> 0x022d, TryCatch #1 {IOException -> 0x022d, blocks: (B:3:0x0001, B:5:0x000a, B:7:0x001b, B:10:0x0028, B:129:0x0217, B:130:0x022c, B:12:0x002e, B:14:0x0032, B:15:0x003a, B:16:0x0040, B:23:0x0057, B:24:0x005f, B:25:0x0065, B:29:0x0078, B:37:0x0093, B:38:0x009b, B:39:0x00a1, B:43:0x00b4, B:52:0x00ce, B:59:0x00e3, B:60:0x00e7, B:63:0x00f3, B:65:0x00f7, B:67:0x00fd, B:68:0x0105, B:69:0x010d, B:70:0x0115, B:72:0x011a, B:73:0x0122, B:74:0x0128, B:80:0x013e, B:81:0x0146, B:82:0x014c, B:89:0x0164, B:90:0x016a, B:93:0x0176, B:95:0x017b, B:96:0x0182, B:97:0x0187, B:98:0x0190, B:105:0x01a7, B:106:0x01ac, B:108:0x01b5, B:110:0x01ba, B:111:0x01bf, B:115:0x01cc, B:117:0x01d1, B:118:0x01d6), top: B:135:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setStatus(int r11) {
        /*
            Method dump skipped, instructions count: 718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p041e5.C0965j.setStatus(int):boolean");
    }

    public void setTimeout(long j7) {
        this._timeout = j7;
    }

    @Deprecated
    public void setURI(String str) {
        setRequestURI(str);
    }

    public void setURL(String str) {
        setURI(URI.create(str));
    }

    public void setVersion(int i7) {
        this._version = i7;
    }

    public String toString() {
        String state = toState(getStatus());
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j7 = jCurrentTimeMillis - this._lastStateChange;
        String str = this._lastState >= 0 ? String.format("%s@%x=%s//%s%s#%s(%dms)->%s(%dms)", getClass().getSimpleName(), Integer.valueOf(hashCode()), this._method, this._address, this._uri, toState(this._lastState), Integer.valueOf(this._lastStatePeriod), state, Long.valueOf(j7)) : String.format("%s@%x=%s//%s%s#%s(%dms)", getClass().getSimpleName(), Integer.valueOf(hashCode()), this._method, this._address, this._uri, state, Long.valueOf(j7));
        if (getStatus() < 3 || this._sent <= 0) {
            return str;
        }
        StringBuilder sbM94a = C0080b.m94a(str, "sent=");
        sbM94a.append(jCurrentTimeMillis - this._sent);
        sbM94a.append("ms");
        return sbM94a.toString();
    }

    public int waitForDone() {
        int i7;
        synchronized (this) {
            while (!isDone()) {
                wait();
            }
            i7 = this._status.get();
        }
        return i7;
    }

    @Deprecated
    public void waitForStatus(int i7) {
        throw new UnsupportedOperationException();
    }

    public void setRequestHeader(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        getRequestFields().m1228i(interfaceC1152e, interfaceC1152e2);
    }

    public void setScheme(String str) {
        if (str != null) {
            if ("http".equalsIgnoreCase(str)) {
                setScheme(C1110r.f2362a);
            } else if ("https".equalsIgnoreCase(str)) {
                setScheme(C1110r.f2363b);
            } else {
                setScheme(new C1158k(str));
            }
        }
    }

    public void setURI(URI uri) {
        if (!uri.isAbsolute()) {
            throw new IllegalArgumentException("!Absolute URI: " + uri);
        }
        if (uri.isOpaque()) {
            throw new IllegalArgumentException("Opaque URI: " + uri);
        }
        InterfaceC2016c interfaceC2016c = LOG;
        if (interfaceC2016c.mo2353d()) {
            interfaceC2016c.mo2351a("URI = {}", uri.toASCIIString());
        }
        String scheme = uri.getScheme();
        int port = uri.getPort();
        if (port <= 0) {
            port = "https".equalsIgnoreCase(scheme) ? 443 : 80;
        }
        setScheme(scheme);
        setAddress(new C0957b(uri.getHost(), port));
        C1113u c1113u = new C1113u(uri);
        int i7 = c1113u.f2374h;
        int i8 = c1113u.f2378l;
        String strM1256o = i7 == i8 ? null : c1113u.m1256o(i7, i8 - i7);
        if (strM1256o == null) {
            strM1256o = ServiceReference.DELIMITER;
        }
        setRequestURI(strM1256o);
    }

    public void setVersion(String str) {
        C1153f.a aVar = (C1153f.a) C1114v.f2380a.f2540b.m2245a(str);
        if (aVar == null) {
            this._version = 10;
        } else {
            this._version = aVar.f2542r;
        }
    }

    @Deprecated
    public boolean isDone(int i7) {
        return isDone();
    }

    public void addRequestHeader(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2) {
        getRequestFields().m1222a(interfaceC1152e, interfaceC1152e2);
    }
}
