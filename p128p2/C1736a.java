package p128p2;

import android.content.Context;
import org.fourthline.cling.UpnpServiceConfiguration;
import org.fourthline.cling.android.AndroidRouter;
import org.fourthline.cling.protocol.ProtocolFactory;

/* compiled from: DlnaAndroidRouter.java */
/* renamed from: p2.a */
/* loaded from: classes.dex */
public class C1736a extends AndroidRouter {
    public C1736a(UpnpServiceConfiguration upnpServiceConfiguration, ProtocolFactory protocolFactory, Context context) {
        super(upnpServiceConfiguration, protocolFactory, context);
    }

    @Override // org.fourthline.cling.android.AndroidRouter, org.fourthline.cling.transport.RouterImpl
    public int getLockTimeoutMillis() {
        return 65000;
    }
}
