package com.alibaba.sdk.android.oss.internal;

import android.arch.lifecycle.C0063n;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.common.HttpMethod;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.alibaba.sdk.android.oss.common.utils.HttpUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.model.GeneratePresignedUrlRequest;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import org.fourthline.cling.model.ServiceReference;

/* loaded from: classes.dex */
public class ObjectURLPresigner {
    private ClientConfiguration conf;
    private OSSCredentialProvider credentialProvider;
    private URI endpoint;

    public ObjectURLPresigner(URI uri, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        this.endpoint = uri;
        this.credentialProvider = oSSCredentialProvider;
        this.conf = clientConfiguration;
    }

    private String buildCanonicalHost(URI uri, String str, ClientConfiguration clientConfiguration) {
        String host = uri.getHost();
        String path = uri.getPath();
        int port = uri.getPort();
        String strValueOf = port != -1 ? String.valueOf(port) : null;
        boolean zIsValidateIP = false;
        String strM97a = !TextUtils.isEmpty(strValueOf) ? C0096a.m97a(host, ":", strValueOf) : host;
        if (!TextUtils.isEmpty(str)) {
            if (OSSUtils.isOssOriginHost(host)) {
                strM97a = C0096a.m97a(str, ".", host);
            } else if (!OSSUtils.isInCustomCnameExcludeList(host, clientConfiguration.getCustomCnameExcludeList())) {
                try {
                    zIsValidateIP = OSSUtils.isValidateIP(host);
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            } else if (clientConfiguration.isPathStyleAccessEnable()) {
                zIsValidateIP = true;
            } else {
                strM97a = C0096a.m97a(str, ".", host);
            }
        }
        if (clientConfiguration.isCustomPathPrefixEnable() && path != null) {
            strM97a = C0063n.m88a(strM97a, path);
        }
        return zIsValidateIP ? C0096a.m97a(strM97a, ServiceReference.DELIMITER, str) : strM97a;
    }

    public String presignConstrainedURL(GeneratePresignedUrlRequest generatePresignedUrlRequest) throws ClientException {
        String strSign;
        String bucketName = generatePresignedUrlRequest.getBucketName();
        String key = generatePresignedUrlRequest.getKey();
        String strValueOf = String.valueOf(generatePresignedUrlRequest.getExpiration() + (DateUtil.getFixedSkewedTimeMillis() / 1000));
        HttpMethod method = generatePresignedUrlRequest.getMethod() != null ? generatePresignedUrlRequest.getMethod() : HttpMethod.GET;
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setEndpoint(this.endpoint);
        requestMessage.setMethod(method);
        requestMessage.setBucketName(bucketName);
        requestMessage.setObjectKey(key);
        requestMessage.getHeaders().put("Date", strValueOf);
        if (generatePresignedUrlRequest.getContentType() != null && !generatePresignedUrlRequest.getContentType().trim().equals("")) {
            requestMessage.getHeaders().put("Content-Type", generatePresignedUrlRequest.getContentType());
        }
        if (generatePresignedUrlRequest.getContentMD5() != null && !generatePresignedUrlRequest.getContentMD5().trim().equals("")) {
            requestMessage.getHeaders().put(HttpHeaders.CONTENT_MD5, generatePresignedUrlRequest.getContentMD5());
        }
        if (generatePresignedUrlRequest.getQueryParameter() != null && generatePresignedUrlRequest.getQueryParameter().size() > 0) {
            for (Map.Entry<String, String> entry : generatePresignedUrlRequest.getQueryParameter().entrySet()) {
                requestMessage.getParameters().put(entry.getKey(), entry.getValue());
            }
        }
        if (generatePresignedUrlRequest.getProcess() != null && !generatePresignedUrlRequest.getProcess().trim().equals("")) {
            requestMessage.getParameters().put(RequestParameters.X_OSS_PROCESS, generatePresignedUrlRequest.getProcess());
        }
        OSSFederationToken federationToken = null;
        OSSCredentialProvider oSSCredentialProvider = this.credentialProvider;
        if (oSSCredentialProvider instanceof OSSFederationCredentialProvider) {
            federationToken = ((OSSFederationCredentialProvider) oSSCredentialProvider).getValidFederationToken();
            requestMessage.getParameters().put(RequestParameters.SECURITY_TOKEN, federationToken.getSecurityToken());
        } else if (oSSCredentialProvider instanceof OSSStsTokenCredentialProvider) {
            federationToken = ((OSSStsTokenCredentialProvider) oSSCredentialProvider).getFederationToken();
            requestMessage.getParameters().put(RequestParameters.SECURITY_TOKEN, federationToken.getSecurityToken());
        }
        String strBuildCanonicalString = OSSUtils.buildCanonicalString(requestMessage);
        OSSCredentialProvider oSSCredentialProvider2 = this.credentialProvider;
        if ((oSSCredentialProvider2 instanceof OSSFederationCredentialProvider) || (oSSCredentialProvider2 instanceof OSSStsTokenCredentialProvider)) {
            strSign = OSSUtils.sign(federationToken.getTempAK(), federationToken.getTempSK(), strBuildCanonicalString);
        } else if (oSSCredentialProvider2 instanceof OSSPlainTextAKSKCredentialProvider) {
            strSign = OSSUtils.sign(((OSSPlainTextAKSKCredentialProvider) oSSCredentialProvider2).getAccessKeyId(), ((OSSPlainTextAKSKCredentialProvider) this.credentialProvider).getAccessKeySecret(), strBuildCanonicalString);
        } else {
            if (!(oSSCredentialProvider2 instanceof OSSCustomSignerCredentialProvider)) {
                throw new ClientException("Unknown credentialProvider!");
            }
            strSign = ((OSSCustomSignerCredentialProvider) oSSCredentialProvider2).signContent(strBuildCanonicalString);
        }
        String strSubstring = strSign.split(":")[0].substring(4);
        String str = strSign.split(":")[1];
        String strBuildCanonicalHost = buildCanonicalHost(this.endpoint, bucketName, this.conf);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Expires", strValueOf);
        linkedHashMap.put(RequestParameters.OSS_ACCESS_KEY_ID, strSubstring);
        linkedHashMap.put(RequestParameters.SIGNATURE, str);
        linkedHashMap.putAll(requestMessage.getParameters());
        return this.endpoint.getScheme() + "://" + strBuildCanonicalHost + ServiceReference.DELIMITER + HttpUtil.urlEncode(key, "utf-8") + "?" + HttpUtil.paramToQueryString(linkedHashMap, "utf-8");
    }

    public String presignPublicURL(String str, String str2) {
        return this.endpoint.getScheme() + "://" + buildCanonicalHost(this.endpoint, str, this.conf) + ServiceReference.DELIMITER + HttpUtil.urlEncode(str2, "utf-8");
    }

    public String presignConstrainedURL(String str, String str2, long j7) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(str, str2);
        generatePresignedUrlRequest.setExpiration(j7);
        return presignConstrainedURL(generatePresignedUrlRequest);
    }
}
