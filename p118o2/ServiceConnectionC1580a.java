package p118o2;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import org.fourthline.cling.android.AndroidUpnpService;
import org.fourthline.cling.model.DiscoveryOptions;
import p144r2.C1832e;
import p186x2.C2073a;

/* compiled from: CtvitDlna.java */
/* renamed from: o2.a */
/* loaded from: classes.dex */
public class ServiceConnectionC1580a implements ServiceConnection {

    /* renamed from: a */
    public final /* synthetic */ boolean f4741a;

    /* renamed from: b */
    public final /* synthetic */ C1581b f4742b;

    public ServiceConnectionC1580a(C1581b c1581b, boolean z6) {
        this.f4742b = c1581b;
        this.f4741a = z6;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C2073a.m2459d("onServiceConnected");
        C1581b c1581b = this.f4742b;
        c1581b.f4745a = (AndroidUpnpService) iBinder;
        if (this.f4741a) {
            C1581b.m1836e();
            c1581b.f4746b = new C1832e(1, C1581b.f4744m);
            this.f4742b.f4745a.getRegistry().addDevice(this.f4742b.f4746b.f5336h, new DiscoveryOptions(true, true));
        }
        C1581b.m1838g();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        C2073a.m2459d("onServiceDisconnected");
        this.f4742b.f4745a.getRegistry().removeAllLocalDevices();
        C1581b c1581b = this.f4742b;
        c1581b.f4745a = null;
        c1581b.f4746b = null;
        C1581b.m1838g();
    }
}
