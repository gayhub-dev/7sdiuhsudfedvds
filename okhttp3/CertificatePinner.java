package okhttp3;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();

    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public Builder add(String str, String... strArr) {
            Objects.requireNonNull(str, "pattern == null");
            for (String str2 : strArr) {
                this.pins.add(new Pin(str, str2));
            }
            return this;
        }

        public CertificatePinner build() {
            return new CertificatePinner(new LinkedHashSet(this.pins), null);
        }
    }

    public static final class Pin {
        private static final String WILDCARD = "*.";
        public final String canonicalHostname;
        public final ByteString hash;
        public final String hashAlgorithm;
        public final String pattern;

        public Pin(String str, String str2) {
            String strHost;
            this.pattern = str;
            if (str.startsWith(WILDCARD)) {
                StringBuilder sbM112a = C0413b.m112a("http://");
                sbM112a.append(str.substring(2));
                strHost = HttpUrl.get(sbM112a.toString()).host();
            } else {
                strHost = HttpUrl.get("http://" + str).host();
            }
            this.canonicalHostname = strHost;
            if (str2.startsWith("sha1/")) {
                this.hashAlgorithm = "sha1/";
                this.hash = ByteString.decodeBase64(str2.substring(5));
            } else {
                if (!str2.startsWith("sha256/")) {
                    throw new IllegalArgumentException(C0063n.m88a("pins must start with 'sha256/' or 'sha1/': ", str2));
                }
                this.hashAlgorithm = "sha256/";
                this.hash = ByteString.decodeBase64(str2.substring(7));
            }
            if (this.hash == null) {
                throw new IllegalArgumentException(C0063n.m88a("pins must be base64: ", str2));
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof Pin) {
                Pin pin = (Pin) obj;
                if (this.pattern.equals(pin.pattern) && this.hashAlgorithm.equals(pin.hashAlgorithm) && this.hash.equals(pin.hash)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.hash.hashCode() + ((this.hashAlgorithm.hashCode() + ((this.pattern.hashCode() + 527) * 31)) * 31);
        }

        public boolean matches(String str) {
            if (!this.pattern.startsWith(WILDCARD)) {
                return str.equals(this.canonicalHostname);
            }
            int iIndexOf = str.indexOf(46);
            if ((str.length() - iIndexOf) - 1 == this.canonicalHostname.length()) {
                String str2 = this.canonicalHostname;
                if (str.regionMatches(false, iIndexOf + 1, str2, 0, str2.length())) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return this.hashAlgorithm + this.hash.base64();
        }
    }

    public CertificatePinner(Set<Pin> set, @Nullable CertificateChainCleaner certificateChainCleaner) {
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public static String pin(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        StringBuilder sbM112a = C0413b.m112a("sha256/");
        sbM112a.append(sha256((X509Certificate) certificate).base64());
        return sbM112a.toString();
    }

    public static ByteString sha1(X509Certificate x509Certificate) {
        return ByteString.m1869of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    public static ByteString sha256(X509Certificate x509Certificate) {
        return ByteString.m1869of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }

    public void check(String str, List<Certificate> list) {
        List<Pin> listFindMatchingPins = findMatchingPins(str);
        if (listFindMatchingPins.isEmpty()) {
            return;
        }
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        if (certificateChainCleaner != null) {
            list = certificateChainCleaner.clean(list, str);
        }
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i7);
            int size2 = listFindMatchingPins.size();
            ByteString byteStringSha256 = null;
            ByteString byteStringSha1 = null;
            for (int i8 = 0; i8 < size2; i8++) {
                Pin pin = listFindMatchingPins.get(i8);
                if (pin.hashAlgorithm.equals("sha256/")) {
                    if (byteStringSha256 == null) {
                        byteStringSha256 = sha256(x509Certificate);
                    }
                    if (pin.hash.equals(byteStringSha256)) {
                        return;
                    }
                } else {
                    if (!pin.hashAlgorithm.equals("sha1/")) {
                        StringBuilder sbM112a = C0413b.m112a("unsupported hashAlgorithm: ");
                        sbM112a.append(pin.hashAlgorithm);
                        throw new AssertionError(sbM112a.toString());
                    }
                    if (byteStringSha1 == null) {
                        byteStringSha1 = sha1(x509Certificate);
                    }
                    if (pin.hash.equals(byteStringSha1)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sbM94a = C0080b.m94a("Certificate pinning failure!", "\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i9 = 0; i9 < size3; i9++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i9);
            sbM94a.append("\n    ");
            sbM94a.append(pin(x509Certificate2));
            sbM94a.append(": ");
            sbM94a.append(x509Certificate2.getSubjectDN().getName());
        }
        sbM94a.append("\n  Pinned certificates for ");
        sbM94a.append(str);
        sbM94a.append(":");
        int size4 = listFindMatchingPins.size();
        for (int i10 = 0; i10 < size4; i10++) {
            Pin pin2 = listFindMatchingPins.get(i10);
            sbM94a.append("\n    ");
            sbM94a.append(pin2);
        }
        throw new SSLPeerUnverifiedException(sbM94a.toString());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (Util.equal(this.certificateChainCleaner, certificatePinner.certificateChainCleaner) && this.pins.equals(certificatePinner.pins)) {
                return true;
            }
        }
        return false;
    }

    public List<Pin> findMatchingPins(String str) {
        List<Pin> listEmptyList = Collections.emptyList();
        for (Pin pin : this.pins) {
            if (pin.matches(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                listEmptyList.add(pin);
            }
        }
        return listEmptyList;
    }

    public int hashCode() {
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        return this.pins.hashCode() + ((certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0) * 31);
    }

    public CertificatePinner withCertificateChainCleaner(@Nullable CertificateChainCleaner certificateChainCleaner) {
        return Util.equal(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public void check(String str, Certificate... certificateArr) {
        check(str, Arrays.asList(certificateArr));
    }
}
