package com.alibaba.sdk.android.oss;

import com.ctvit.network.CtvitHttp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public class ClientConfiguration {
    private static final int DEFAULT_MAX_RETRIES = 2;
    private String ipWithHeader;
    private String mUserAgentMark;
    private String proxyHost;
    private int proxyPort;
    private int maxConcurrentRequest = 5;
    private int socketTimeout = CtvitHttp.DEFAULT_MILLISECONDS;
    private int connectionTimeout = CtvitHttp.DEFAULT_MILLISECONDS;
    private long max_log_size = 5242880;
    private int maxErrorRetry = 2;
    private List<String> customCnameExcludeList = new ArrayList();
    private boolean httpDnsEnable = false;
    private boolean checkCRC64 = false;
    private boolean pathStyleAccessEnable = false;
    private boolean customPathPrefixEnable = false;
    private boolean followRedirectsEnable = false;
    private OkHttpClient okHttpClient = null;

    public static ClientConfiguration getDefaultConf() {
        return new ClientConfiguration();
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public List<String> getCustomCnameExcludeList() {
        return Collections.unmodifiableList(this.customCnameExcludeList);
    }

    public String getCustomUserMark() {
        return this.mUserAgentMark;
    }

    public String getIpWithHeader() {
        return this.ipWithHeader;
    }

    public int getMaxConcurrentRequest() {
        return this.maxConcurrentRequest;
    }

    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    public long getMaxLogSize() {
        return this.max_log_size;
    }

    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public boolean isCheckCRC64() {
        return this.checkCRC64;
    }

    public boolean isCustomPathPrefixEnable() {
        return this.customPathPrefixEnable;
    }

    public boolean isFollowRedirectsEnable() {
        return this.followRedirectsEnable;
    }

    public boolean isHttpDnsEnable() {
        return this.httpDnsEnable;
    }

    public boolean isPathStyleAccessEnable() {
        return this.pathStyleAccessEnable;
    }

    public void setCheckCRC64(boolean z6) {
        this.checkCRC64 = z6;
    }

    public void setConnectionTimeout(int i7) {
        this.connectionTimeout = i7;
    }

    public void setCustomCnameExcludeList(List<String> list) {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("cname exclude list should not be null.");
        }
        this.customCnameExcludeList.clear();
        for (String str : list) {
            if (str.contains("://")) {
                this.customCnameExcludeList.add(str.substring(str.indexOf("://") + 3));
            } else {
                this.customCnameExcludeList.add(str);
            }
        }
    }

    public void setCustomPathPrefixEnable(boolean z6) {
        this.customPathPrefixEnable = z6;
    }

    public void setFollowRedirectsEnable(boolean z6) {
        this.followRedirectsEnable = z6;
    }

    public void setHttpDnsEnable(boolean z6) {
        this.httpDnsEnable = z6;
    }

    public void setIpWithHeader(String str) {
        this.ipWithHeader = str;
    }

    public void setMaxConcurrentRequest(int i7) {
        this.maxConcurrentRequest = i7;
    }

    public void setMaxErrorRetry(int i7) {
        this.maxErrorRetry = i7;
    }

    public void setMaxLogSize(long j7) {
        this.max_log_size = j7;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public void setPathStyleAccessEnable(boolean z6) {
        this.pathStyleAccessEnable = z6;
    }

    public void setProxyHost(String str) {
        this.proxyHost = str;
    }

    public void setProxyPort(int i7) {
        this.proxyPort = i7;
    }

    public void setSocketTimeout(int i7) {
        this.socketTimeout = i7;
    }

    public void setUserAgentMark(String str) {
        this.mUserAgentMark = str;
    }
}
