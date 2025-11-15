package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.utils.CaseInsensitiveHashMap;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ObjectMetadata {
    public static final String AES_256_SERVER_SIDE_ENCRYPTION = "AES256";
    private Map<String, String> userMetadata = new CaseInsensitiveHashMap();
    private Map<String, Object> metadata = new CaseInsensitiveHashMap();

    public void addUserMetadata(String str, String str2) {
        this.userMetadata.put(str, str2);
    }

    public String getCacheControl() {
        return (String) this.metadata.get("Cache-Control");
    }

    public String getContentDisposition() {
        return (String) this.metadata.get("Content-Disposition");
    }

    public String getContentEncoding() {
        return (String) this.metadata.get("Content-Encoding");
    }

    public long getContentLength() {
        Long l7 = (Long) this.metadata.get("Content-Length");
        if (l7 == null) {
            return 0L;
        }
        return l7.longValue();
    }

    public String getContentMD5() {
        return (String) this.metadata.get(HttpHeaders.CONTENT_MD5);
    }

    public String getContentType() {
        return (String) this.metadata.get("Content-Type");
    }

    public String getETag() {
        return (String) this.metadata.get("ETag");
    }

    public Date getExpirationTime() {
        return DateUtil.parseRfc822Date((String) this.metadata.get("Expires"));
    }

    public Date getLastModified() {
        return (Date) this.metadata.get("Last-Modified");
    }

    public String getObjectType() {
        return (String) this.metadata.get(OSSHeaders.OSS_OBJECT_TYPE);
    }

    public String getRawExpiresValue() {
        return (String) this.metadata.get("Expires");
    }

    public Map<String, Object> getRawMetadata() {
        return Collections.unmodifiableMap(this.metadata);
    }

    public String getSHA1() {
        return (String) this.metadata.get(OSSHeaders.OSS_HASH_SHA1);
    }

    public String getServerSideEncryption() {
        return (String) this.metadata.get(OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION);
    }

    public Map<String, String> getUserMetadata() {
        return this.userMetadata;
    }

    public void setCacheControl(String str) {
        this.metadata.put("Cache-Control", str);
    }

    public void setContentDisposition(String str) {
        this.metadata.put("Content-Disposition", str);
    }

    public void setContentEncoding(String str) {
        this.metadata.put("Content-Encoding", str);
    }

    public void setContentLength(long j7) {
        if (j7 > OSSConstants.DEFAULT_FILE_SIZE_LIMIT) {
            throw new IllegalArgumentException("The content length could not be more than 5GB.");
        }
        this.metadata.put("Content-Length", Long.valueOf(j7));
    }

    public void setContentMD5(String str) {
        this.metadata.put(HttpHeaders.CONTENT_MD5, str);
    }

    public void setContentType(String str) {
        this.metadata.put("Content-Type", str);
    }

    public void setExpirationTime(Date date) {
        this.metadata.put("Expires", DateUtil.formatRfc822Date(date));
    }

    public void setHeader(String str, Object obj) {
        this.metadata.put(str, obj);
    }

    public void setLastModified(Date date) {
        this.metadata.put("Last-Modified", date);
    }

    public void setSHA1(String str) {
        this.metadata.put(OSSHeaders.OSS_HASH_SHA1, str);
    }

    public void setServerSideEncryption(String str) {
        this.metadata.put(OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION, str);
    }

    public void setUserMetadata(Map<String, String> map) {
        this.userMetadata.clear();
        if (map == null || map.isEmpty()) {
            return;
        }
        this.userMetadata.putAll(map);
    }

    public String toString() {
        String string;
        try {
            string = getExpirationTime().toString();
        } catch (Exception unused) {
            string = "";
        }
        StringBuilder sbM112a = C0413b.m112a("Last-Modified:");
        sbM112a.append(getLastModified());
        sbM112a.append("\n");
        sbM112a.append("Expires");
        sbM112a.append(":");
        sbM112a.append(string);
        sbM112a.append("\nrawExpires:");
        sbM112a.append(getRawExpiresValue());
        sbM112a.append("\n");
        sbM112a.append(HttpHeaders.CONTENT_MD5);
        sbM112a.append(":");
        sbM112a.append(getContentMD5());
        sbM112a.append("\n");
        sbM112a.append(OSSHeaders.OSS_OBJECT_TYPE);
        sbM112a.append(":");
        sbM112a.append(getObjectType());
        sbM112a.append("\n");
        sbM112a.append(OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION);
        sbM112a.append(":");
        sbM112a.append(getServerSideEncryption());
        sbM112a.append("\n");
        sbM112a.append("Content-Disposition");
        sbM112a.append(":");
        sbM112a.append(getContentDisposition());
        sbM112a.append("\n");
        sbM112a.append("Content-Encoding");
        sbM112a.append(":");
        sbM112a.append(getContentEncoding());
        sbM112a.append("\n");
        sbM112a.append("Cache-Control");
        sbM112a.append(":");
        sbM112a.append(getCacheControl());
        sbM112a.append("\n");
        sbM112a.append("ETag");
        sbM112a.append(":");
        sbM112a.append(getETag());
        sbM112a.append("\n");
        return sbM112a.toString();
    }
}
