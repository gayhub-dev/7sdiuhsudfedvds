package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Internal;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class ConnectionSpecSelector {
    private final List<ConnectionSpec> connectionSpecs;
    private boolean isFallback;
    private boolean isFallbackPossible;
    private int nextModeIndex = 0;

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.connectionSpecs = list;
    }

    private boolean isFallbackPossible(SSLSocket sSLSocket) {
        for (int i7 = this.nextModeIndex; i7 < this.connectionSpecs.size(); i7++) {
            if (this.connectionSpecs.get(i7).isCompatible(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public ConnectionSpec configureSecureSocket(SSLSocket sSLSocket) throws UnknownServiceException {
        ConnectionSpec connectionSpec;
        int i7 = this.nextModeIndex;
        int size = this.connectionSpecs.size();
        while (true) {
            if (i7 >= size) {
                connectionSpec = null;
                break;
            }
            connectionSpec = this.connectionSpecs.get(i7);
            if (connectionSpec.isCompatible(sSLSocket)) {
                this.nextModeIndex = i7 + 1;
                break;
            }
            i7++;
        }
        if (connectionSpec != null) {
            this.isFallbackPossible = isFallbackPossible(sSLSocket);
            Internal.instance.apply(connectionSpec, sSLSocket, this.isFallback);
            return connectionSpec;
        }
        StringBuilder sbM112a = C0413b.m112a("Unable to find acceptable protocols. isFallback=");
        sbM112a.append(this.isFallback);
        sbM112a.append(", modes=");
        sbM112a.append(this.connectionSpecs);
        sbM112a.append(", supported protocols=");
        sbM112a.append(Arrays.toString(sSLSocket.getEnabledProtocols()));
        throw new UnknownServiceException(sbM112a.toString());
    }

    public boolean connectionFailed(IOException iOException) {
        this.isFallback = true;
        if (!this.isFallbackPossible || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z6 = iOException instanceof SSLHandshakeException;
        if ((z6 && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z6 || (iOException instanceof SSLProtocolException) || (iOException instanceof SSLException);
    }
}
