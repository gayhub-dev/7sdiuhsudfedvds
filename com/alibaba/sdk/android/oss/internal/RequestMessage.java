package com.alibaba.sdk.android.oss.internal;

import android.net.Uri;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import com.alibaba.sdk.android.oss.common.HttpMethod;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.alibaba.sdk.android.oss.common.utils.HttpdnsMini;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.model.BucketLifecycleRule;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import p009b.C0413b;

/* loaded from: classes.dex */
public class RequestMessage extends HttpMessage {
    private String bucketName;
    private boolean checkCRC64;
    private OSSCredentialProvider credentialProvider;
    private URI endpoint;
    private String ipWithHeader;
    private HttpMethod method;
    private String objectKey;
    private URI service;
    private byte[] uploadData;
    private String uploadFilePath;
    private Uri uploadUri;
    private boolean isAuthorizationRequired = true;
    private Map<String, String> parameters = new LinkedHashMap();
    private boolean httpDnsEnable = false;
    private boolean pathStyleAccessEnable = false;
    private boolean customPathPrefixEnable = false;
    private boolean isInCustomCnameExcludeList = false;

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ void addHeader(String str, String str2) {
        super.addHeader(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String buildCanonicalURL() {
        /*
            Method dump skipped, instructions count: 496
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.RequestMessage.buildCanonicalURL():java.lang.String");
    }

    public String buildOSSServiceURL() {
        OSSUtils.assertTrue(this.service != null, "Service haven't been set!");
        String host = this.service.getHost();
        String scheme = this.service.getScheme();
        String ipByHostAsync = null;
        if (isHttpDnsEnable() && scheme.equalsIgnoreCase("http")) {
            ipByHostAsync = HttpdnsMini.getInstance().getIpByHostAsync(host);
        } else {
            OSSLog.logDebug("[buildOSSServiceURL], disable httpdns or http is not need httpdns");
        }
        if (ipByHostAsync == null) {
            ipByHostAsync = host;
        }
        getHeaders().put(HttpHeaders.HOST, host);
        String str = scheme + "://" + ipByHostAsync;
        String strParamToQueryString = OSSUtils.paramToQueryString(this.parameters, "utf-8");
        return OSSUtils.isEmptyString(strParamToQueryString) ? str : C0096a.m97a(str, "?", strParamToQueryString);
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    public void createBucketRequestBodyMarshall(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        if (map != null) {
            stringBuffer.append("<CreateBucketConfiguration>");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                StringBuilder sbM112a = C0413b.m112a("<");
                sbM112a.append(entry.getKey());
                sbM112a.append(">");
                sbM112a.append(entry.getValue());
                sbM112a.append("</");
                sbM112a.append(entry.getKey());
                sbM112a.append(">");
                stringBuffer.append(sbM112a.toString());
            }
            stringBuffer.append("</CreateBucketConfiguration>");
            byte[] bytes = stringBuffer.toString().getBytes("utf-8");
            long length = bytes.length;
            setContent(new ByteArrayInputStream(bytes));
            setContentLength(length);
        }
    }

    public byte[] deleteMultipleObjectRequestBodyMarshall(List<String> list, boolean z6) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<Delete>");
        if (z6) {
            stringBuffer.append("<Quiet>true</Quiet>");
        } else {
            stringBuffer.append("<Quiet>false</Quiet>");
        }
        for (String str : list) {
            stringBuffer.append("<Object>");
            stringBuffer.append("<Key>");
            stringBuffer.append(str);
            stringBuffer.append("</Key>");
            stringBuffer.append("</Object>");
        }
        stringBuffer.append("</Delete>");
        byte[] bytes = stringBuffer.toString().getBytes("utf-8");
        long length = bytes.length;
        setContent(new ByteArrayInputStream(bytes));
        setContentLength(length);
        return bytes;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ InputStream getContent() {
        return super.getContent();
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ long getContentLength() {
        return super.getContentLength();
    }

    public OSSCredentialProvider getCredentialProvider() {
        return this.credentialProvider;
    }

    public URI getEndpoint() {
        return this.endpoint;
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ Map getHeaders() {
        return super.getHeaders();
    }

    public String getIpWithHeader() {
        return this.ipWithHeader;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public URI getService() {
        return this.service;
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ String getStringBody() {
        return super.getStringBody();
    }

    public byte[] getUploadData() {
        return this.uploadData;
    }

    public String getUploadFilePath() {
        return this.uploadFilePath;
    }

    public Uri getUploadUri() {
        return this.uploadUri;
    }

    public boolean isAuthorizationRequired() {
        return this.isAuthorizationRequired;
    }

    public boolean isCheckCRC64() {
        return this.checkCRC64;
    }

    public boolean isCustomPathPrefixEnable() {
        return this.customPathPrefixEnable;
    }

    public boolean isHttpDnsEnable() {
        return this.httpDnsEnable;
    }

    public boolean isInCustomCnameExcludeList() {
        return this.isInCustomCnameExcludeList;
    }

    public boolean isPathStyleAccessEnable() {
        return this.pathStyleAccessEnable;
    }

    public void putBucketLifecycleRequestBodyMarshall(ArrayList<BucketLifecycleRule> arrayList) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<LifecycleConfiguration>");
        Iterator<BucketLifecycleRule> it = arrayList.iterator();
        while (it.hasNext()) {
            BucketLifecycleRule next = it.next();
            stringBuffer.append("<Rule>");
            if (next.getIdentifier() != null) {
                StringBuilder sbM112a = C0413b.m112a("<ID>");
                sbM112a.append(next.getIdentifier());
                sbM112a.append("</ID>");
                stringBuffer.append(sbM112a.toString());
            }
            if (next.getPrefix() != null) {
                StringBuilder sbM112a2 = C0413b.m112a("<Prefix>");
                sbM112a2.append(next.getPrefix());
                sbM112a2.append("</Prefix>");
                stringBuffer.append(sbM112a2.toString());
            }
            StringBuilder sbM112a3 = C0413b.m112a("<Status>");
            sbM112a3.append(next.getStatus() ? "Enabled" : "Disabled");
            sbM112a3.append("</Status>");
            stringBuffer.append(sbM112a3.toString());
            if (next.getDays() != null) {
                StringBuilder sbM112a4 = C0413b.m112a("<Days>");
                sbM112a4.append(next.getDays());
                sbM112a4.append("</Days>");
                stringBuffer.append(sbM112a4.toString());
            } else if (next.getExpireDate() != null) {
                StringBuilder sbM112a5 = C0413b.m112a("<Date>");
                sbM112a5.append(next.getExpireDate());
                sbM112a5.append("</Date>");
                stringBuffer.append(sbM112a5.toString());
            }
            if (next.getMultipartDays() != null) {
                StringBuilder sbM112a6 = C0413b.m112a("<AbortMultipartUpload><Days>");
                sbM112a6.append(next.getMultipartDays());
                sbM112a6.append("</Days></AbortMultipartUpload>");
                stringBuffer.append(sbM112a6.toString());
            } else if (next.getMultipartExpireDate() != null) {
                StringBuilder sbM112a7 = C0413b.m112a("<AbortMultipartUpload><Date>");
                sbM112a7.append(next.getMultipartDays());
                sbM112a7.append("</Date></AbortMultipartUpload>");
                stringBuffer.append(sbM112a7.toString());
            }
            if (next.getIADays() != null) {
                StringBuilder sbM112a8 = C0413b.m112a("<Transition><Days>");
                sbM112a8.append(next.getIADays());
                sbM112a8.append("</Days><StorageClass>IA</StorageClass></Transition>");
                stringBuffer.append(sbM112a8.toString());
            } else if (next.getIAExpireDate() != null) {
                StringBuilder sbM112a9 = C0413b.m112a("<Transition><Date>");
                sbM112a9.append(next.getIAExpireDate());
                sbM112a9.append("</Date><StorageClass>IA</StorageClass></Transition>");
                stringBuffer.append(sbM112a9.toString());
            } else if (next.getArchiveDays() != null) {
                StringBuilder sbM112a10 = C0413b.m112a("<Transition><Days>");
                sbM112a10.append(next.getArchiveDays());
                sbM112a10.append("</Days><StorageClass>Archive</StorageClass></Transition>");
                stringBuffer.append(sbM112a10.toString());
            } else if (next.getArchiveExpireDate() != null) {
                StringBuilder sbM112a11 = C0413b.m112a("<Transition><Date>");
                sbM112a11.append(next.getArchiveExpireDate());
                sbM112a11.append("</Date><StorageClass>Archive</StorageClass></Transition>");
                stringBuffer.append(sbM112a11.toString());
            }
            stringBuffer.append("</Rule>");
        }
        stringBuffer.append("</LifecycleConfiguration>");
        byte[] bytes = stringBuffer.toString().getBytes("utf-8");
        long length = bytes.length;
        setContent(new ByteArrayInputStream(bytes));
        setContentLength(length);
    }

    public void putBucketLoggingRequestBodyMarshall(String str, String str2) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<BucketLoggingStatus>");
        if (str != null) {
            stringBuffer.append("<LoggingEnabled><TargetBucket>" + str + "</TargetBucket>");
            if (str2 != null) {
                stringBuffer.append("<TargetPrefix>" + str2 + "</TargetPrefix>");
            }
            stringBuffer.append("</LoggingEnabled>");
        }
        stringBuffer.append("</BucketLoggingStatus>");
        byte[] bytes = stringBuffer.toString().getBytes("utf-8");
        long length = bytes.length;
        setContent(new ByteArrayInputStream(bytes));
        setContentLength(length);
    }

    public void putBucketRefererRequestBodyMarshall(ArrayList<String> arrayList, boolean z6) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<RefererConfiguration>");
        StringBuilder sbM112a = C0413b.m112a("<AllowEmptyReferer>");
        sbM112a.append(z6 ? "true" : "false");
        sbM112a.append("</AllowEmptyReferer>");
        stringBuffer.append(sbM112a.toString());
        if (arrayList != null && arrayList.size() > 0) {
            stringBuffer.append("<RefererList>");
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuffer.append("<Referer>" + it.next() + "</Referer>");
            }
            stringBuffer.append("</RefererList>");
        }
        stringBuffer.append("</RefererConfiguration>");
        byte[] bytes = stringBuffer.toString().getBytes("utf-8");
        long length = bytes.length;
        setContent(new ByteArrayInputStream(bytes));
        setContentLength(length);
    }

    public byte[] putObjectTaggingRequestBodyMarshall(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<Tagging>");
        stringBuffer.append("<TagSet>");
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                stringBuffer.append("<Tag>");
                stringBuffer.append("<Key>");
                stringBuffer.append(entry.getKey());
                stringBuffer.append("</Key>");
                stringBuffer.append("<Value>");
                stringBuffer.append(entry.getValue());
                stringBuffer.append("</Value>");
                stringBuffer.append("</Tag>");
            }
        }
        stringBuffer.append("</TagSet>");
        stringBuffer.append("</Tagging>");
        byte[] bytes = stringBuffer.toString().getBytes("utf-8");
        long length = bytes.length;
        setContent(new ByteArrayInputStream(bytes));
        setContentLength(length);
        return bytes;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCheckCRC64(boolean z6) {
        this.checkCRC64 = z6;
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ void setContent(InputStream inputStream) {
        super.setContent(inputStream);
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ void setContentLength(long j7) {
        super.setContentLength(j7);
    }

    public void setCredentialProvider(OSSCredentialProvider oSSCredentialProvider) {
        this.credentialProvider = oSSCredentialProvider;
    }

    public void setCustomPathPrefixEnable(boolean z6) {
        this.customPathPrefixEnable = z6;
    }

    public void setEndpoint(URI uri) {
        this.endpoint = uri;
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ void setHeaders(Map map) {
        super.setHeaders(map);
    }

    public void setHttpDnsEnable(boolean z6) {
        this.httpDnsEnable = z6;
    }

    public void setIpWithHeader(String str) {
        this.ipWithHeader = str;
    }

    public void setIsAuthorizationRequired(boolean z6) {
        this.isAuthorizationRequired = z6;
    }

    public void setIsInCustomCnameExcludeList(boolean z6) {
        this.isInCustomCnameExcludeList = z6;
    }

    public void setMethod(HttpMethod httpMethod) {
        this.method = httpMethod;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setParameters(Map<String, String> map) {
        this.parameters = map;
    }

    public void setPathStyleAccessEnable(boolean z6) {
        this.pathStyleAccessEnable = z6;
    }

    public void setService(URI uri) {
        this.service = uri;
    }

    @Override // com.alibaba.sdk.android.oss.internal.HttpMessage
    public /* bridge */ /* synthetic */ void setStringBody(String str) {
        super.setStringBody(str);
    }

    public void setUploadData(byte[] bArr) {
        this.uploadData = bArr;
    }

    public void setUploadFilePath(String str) {
        this.uploadFilePath = str;
    }

    public void setUploadUri(Uri uri) {
        this.uploadUri = uri;
    }
}
