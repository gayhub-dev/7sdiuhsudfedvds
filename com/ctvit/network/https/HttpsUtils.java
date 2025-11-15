package com.ctvit.network.https;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class HttpsUtils {

    public static class MyTrustManager implements X509TrustManager {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public MyTrustManager(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            this.defaultTrustManager = HttpsUtils.chooseTrustManager(trustManagerFactory.getTrustManagers());
            this.localTrustManager = x509TrustManager;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            try {
                this.defaultTrustManager.checkServerTrusted(x509CertificateArr, str);
            } catch (CertificateException unused) {
                this.localTrustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static class SSLParams {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;
    }

    public static class UnSafeTrustManager implements X509TrustManager {
        private UnSafeTrustManager() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    public static SSLParams getSslSocketFactory(InputStream inputStream, String str, InputStream[] inputStreamArr) throws UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException, KeyManagementException {
        SSLParams sSLParams = new SSLParams();
        try {
            KeyManager[] keyManagerArrPrepareKeyManager = prepareKeyManager(inputStream, str);
            TrustManager[] trustManagerArrPrepareTrustManager = prepareTrustManager(inputStreamArr);
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            X509TrustManager myTrustManager = trustManagerArrPrepareTrustManager != null ? new MyTrustManager(chooseTrustManager(trustManagerArrPrepareTrustManager)) : new UnSafeTrustManager();
            sSLContext.init(keyManagerArrPrepareKeyManager, new TrustManager[]{myTrustManager}, null);
            sSLParams.sSLSocketFactory = sSLContext.getSocketFactory();
            sSLParams.trustManager = myTrustManager;
            return sSLParams;
        } catch (KeyManagementException e7) {
            throw new AssertionError(e7);
        } catch (KeyStoreException e8) {
            throw new AssertionError(e8);
        } catch (NoSuchAlgorithmException e9) {
            throw new AssertionError(e9);
        }
    }

    private static KeyManager[] prepareKeyManager(InputStream inputStream, String str) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException {
        if (inputStream != null && str != null) {
            try {
                KeyStore keyStore = KeyStore.getInstance("BKS");
                keyStore.load(inputStream, str.toCharArray());
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, str.toCharArray());
                return keyManagerFactory.getKeyManagers();
            } catch (IOException e7) {
                C2073a.m2458c(e7);
            } catch (KeyStoreException e8) {
                C2073a.m2458c(e8);
            } catch (NoSuchAlgorithmException e9) {
                C2073a.m2458c(e9);
            } catch (UnrecoverableKeyException e10) {
                C2073a.m2458c(e10);
            } catch (CertificateException e11) {
                C2073a.m2458c(e11);
            } catch (Exception e12) {
                C2073a.m2458c(e12);
            }
        }
        return null;
    }

    private static TrustManager[] prepareTrustManager(InputStream... inputStreamArr) throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException {
        if (inputStreamArr != null && inputStreamArr.length > 0) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null);
                int length = inputStreamArr.length;
                int i7 = 0;
                int i8 = 0;
                while (i7 < length) {
                    InputStream inputStream = inputStreamArr[i7];
                    int i9 = i8 + 1;
                    keyStore.setCertificateEntry(Integer.toString(i8), certificateFactory.generateCertificate(inputStream));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e7) {
                            C2073a.m2458c(e7);
                        }
                    }
                    i7++;
                    i8 = i9;
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                return trustManagerFactory.getTrustManagers();
            } catch (KeyStoreException e8) {
                C2073a.m2458c(e8);
            } catch (NoSuchAlgorithmException e9) {
                C2073a.m2458c(e9);
            } catch (CertificateException e10) {
                C2073a.m2458c(e10);
            } catch (Exception e11) {
                C2073a.m2458c(e11);
            }
        }
        return null;
    }
}
