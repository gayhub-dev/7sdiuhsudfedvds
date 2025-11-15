package p177w0;

import android.text.TextUtils;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import p163u0.C1970a;

/* compiled from: NetworkUtility.java */
/* renamed from: w0.b */
/* loaded from: classes.dex */
public final class C2022b implements X509TrustManager {
    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        boolean zEquals;
        if (x509CertificateArr == null || x509CertificateArr.length == 0) {
            throw new CertificateException("无服务器证书提供");
        }
        if (TextUtils.isEmpty(C1970a.f5753k)) {
            return;
        }
        for (X509Certificate x509Certificate : x509CertificateArr) {
            try {
                try {
                    zEquals = Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(x509Certificate.getPublicKey().getEncoded()), 2).equals(C1970a.f5753k);
                } catch (NoSuchAlgorithmException e7) {
                    throw new CertificateEncodingException("不支持的哈希算法", e7);
                }
            } catch (CertificateEncodingException unused) {
                zEquals = false;
            }
            if (zEquals) {
                return;
            }
        }
        throw new CertificateException("未找到匹配的证书公钥哈希");
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
