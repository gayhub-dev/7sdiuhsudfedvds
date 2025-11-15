package com.alibaba.sdk.android.oss.common.utils;

import com.alibaba.sdk.android.oss.common.OSSLog;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p009b.C0413b;

/* loaded from: classes.dex */
public class HttpdnsMini {
    private static final String ACCOUNT_ID = "181345";
    private static final int EMPTY_RESULT_HOST_TTL = 30;
    private static final int MAX_HOLD_HOST_NUM = 100;
    private static final int MAX_THREAD_NUM = 5;
    private static final int RESOLVE_TIMEOUT_IN_SEC = 10;
    private static final String SERVER_IP = "203.107.1.1";
    private static final String TAG = "HttpDnsMini";
    private static HttpdnsMini instance;
    private ConcurrentMap<String, HostObject> hostManager = new ConcurrentHashMap();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    public class HostObject {
        private String hostName;

        /* renamed from: ip */
        private String f426ip;
        private long queryTime;
        private long ttl;

        public HostObject() {
        }

        public String getHostName() {
            return this.hostName;
        }

        public String getIp() {
            return this.f426ip;
        }

        public long getQueryTime() {
            return this.queryTime;
        }

        public long getTtl() {
            return this.ttl;
        }

        public boolean isExpired() {
            return getQueryTime() + this.ttl < System.currentTimeMillis() / 1000;
        }

        public boolean isStillAvailable() {
            return (getQueryTime() + this.ttl) + 600 > System.currentTimeMillis() / 1000;
        }

        public void setHostName(String str) {
            this.hostName = str;
        }

        public void setIp(String str) {
            this.f426ip = str;
        }

        public void setQueryTime(long j7) {
            this.queryTime = j7;
        }

        public void setTtl(long j7) {
            this.ttl = j7;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("[hostName=");
            sbM112a.append(getHostName());
            sbM112a.append(", ip=");
            sbM112a.append(this.f426ip);
            sbM112a.append(", ttl=");
            sbM112a.append(getTtl());
            sbM112a.append(", queryTime=");
            sbM112a.append(this.queryTime);
            sbM112a.append("]");
            return sbM112a.toString();
        }
    }

    public class QueryHostTask implements Callable<String> {
        private boolean hasRetryed = false;
        private String hostName;

        public QueryHostTask(String str) {
            this.hostName = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:48:0x015d  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0165 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x016a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String call() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 371
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.common.utils.HttpdnsMini.QueryHostTask.call():java.lang.String");
        }
    }

    private HttpdnsMini() {
    }

    public static HttpdnsMini getInstance() {
        if (instance == null) {
            synchronized (HttpdnsMini.class) {
                if (instance == null) {
                    instance = new HttpdnsMini();
                }
            }
        }
        return instance;
    }

    public String getIpByHostAsync(String str) {
        HostObject hostObject = this.hostManager.get(str);
        if (hostObject == null || hostObject.isExpired()) {
            OSSLog.logDebug("[httpdnsmini] - refresh host: " + str);
            this.pool.submit(new QueryHostTask(str));
        }
        if (hostObject == null || !hostObject.isStillAvailable()) {
            return null;
        }
        return hostObject.getIp();
    }
}
