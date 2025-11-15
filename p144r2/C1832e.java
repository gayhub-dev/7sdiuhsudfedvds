package p144r2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.ctvit.dlna.R$drawable;
import com.ctvit.dlna.moudle.dmr.AudioRenderingControl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.fourthline.cling.binding.LocalServiceBinder;
import org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.fourthline.cling.model.DefaultServiceManager;
import org.fourthline.cling.model.ServiceManager;
import org.fourthline.cling.model.meta.DeviceDetails;
import org.fourthline.cling.model.meta.DeviceIdentity;
import org.fourthline.cling.model.meta.Icon;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.ManufacturerDetails;
import org.fourthline.cling.model.meta.ModelDetails;
import org.fourthline.cling.model.types.DLNACaps;
import org.fourthline.cling.model.types.DLNADoc;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.lastchange.AVTransportLastChangeParser;
import org.fourthline.cling.support.lastchange.LastChange;
import org.fourthline.cling.support.lastchange.LastChangeAwareServiceManager;
import org.fourthline.cling.support.lastchange.LastChangeParser;
import org.fourthline.cling.support.model.dlna.DLNAProfiles;
import org.fourthline.cling.support.renderingcontrol.lastchange.RenderingControlLastChangeParser;
import p043f.C0988e;
import p118o2.C1581b;
import p151s2.C1869a;

/* compiled from: ZxtMediaRenderer.java */
/* renamed from: r2.e */
/* loaded from: classes.dex */
public class C1832e {

    /* renamed from: a */
    public final LocalServiceBinder f5329a;

    /* renamed from: b */
    public final LastChange f5330b;

    /* renamed from: c */
    public final LastChange f5331c;

    /* renamed from: d */
    public final Map<UnsignedIntegerFourBytes, C1830c> f5332d;

    /* renamed from: e */
    public final ServiceManager<C1829b> f5333e;

    /* renamed from: f */
    public final LastChangeAwareServiceManager<C1828a> f5334f;

    /* renamed from: g */
    public final LastChangeAwareServiceManager<AudioRenderingControl> f5335g;

    /* renamed from: h */
    public final LocalDevice f5336h;

    /* compiled from: ZxtMediaRenderer.java */
    /* renamed from: r2.e$a */
    public class a extends C1831d {
        public a(C1832e c1832e, int i7, Context context, LastChange lastChange, LastChange lastChange2) {
            super(i7, context, lastChange, lastChange2);
        }

        @Override // p144r2.C1831d
        /* renamed from: a */
        public void mo2079a(C1830c c1830c) {
        }

        @Override // p144r2.C1831d
        /* renamed from: b */
        public void mo2080b(C1830c c1830c) {
        }
    }

    /* compiled from: ZxtMediaRenderer.java */
    /* renamed from: r2.e$b */
    public class b extends DefaultServiceManager {
        public b(C1832e c1832e, LocalService localService) {
            super(localService);
        }

        @Override // org.fourthline.cling.model.DefaultServiceManager
        public Object createServiceInstance() {
            return new C1829b();
        }
    }

    /* compiled from: ZxtMediaRenderer.java */
    /* renamed from: r2.e$c */
    public class c extends LastChangeAwareServiceManager<C1828a> {
        public c(LocalService localService, LastChangeParser lastChangeParser) {
            super(localService, lastChangeParser);
        }

        @Override // org.fourthline.cling.model.DefaultServiceManager
        public Object createServiceInstance() {
            C1832e c1832e = C1832e.this;
            return new C1828a(c1832e.f5330b, c1832e.f5332d);
        }
    }

    /* compiled from: ZxtMediaRenderer.java */
    /* renamed from: r2.e$d */
    public class d extends LastChangeAwareServiceManager<AudioRenderingControl> {
        public d(LocalService localService, LastChangeParser lastChangeParser) {
            super(localService, lastChangeParser);
        }

        @Override // org.fourthline.cling.model.DefaultServiceManager
        public Object createServiceInstance() {
            C1832e c1832e = C1832e.this;
            return new AudioRenderingControl(c1832e.f5331c, c1832e.f5332d);
        }
    }

    public C1832e(int i7, Context context) {
        AnnotationLocalServiceBinder annotationLocalServiceBinder = new AnnotationLocalServiceBinder();
        this.f5329a = annotationLocalServiceBinder;
        LastChange lastChange = new LastChange(new AVTransportLastChangeParser());
        this.f5330b = lastChange;
        LastChange lastChange2 = new LastChange(new RenderingControlLastChangeParser());
        this.f5331c = lastChange2;
        this.f5332d = new a(this, i7, context, lastChange, lastChange2);
        LocalService localService = annotationLocalServiceBinder.read(C1829b.class);
        b bVar = new b(this, localService);
        this.f5333e = bVar;
        localService.setManager(bVar);
        LocalService localService2 = annotationLocalServiceBinder.read(C1828a.class);
        c cVar = new c(localService2, new AVTransportLastChangeParser());
        this.f5334f = cVar;
        localService2.setManager(cVar);
        LocalService localService3 = annotationLocalServiceBinder.read(AudioRenderingControl.class);
        d dVar = new d(localService3, new RenderingControlLastChangeParser());
        this.f5335g = dVar;
        localService3.setManager(dVar);
        try {
            String strM2142b = C1869a.m2142b(context);
            if (!TextUtils.isEmpty(strM2142b) && (strM2142b.equals("<unknown>") || strM2142b.equals("unknown"))) {
                strM2142b = "";
            }
            LocalDevice localDevice = new LocalDevice(new DeviceIdentity(C0988e.m972P()), new UDADeviceType("MediaRenderer", 1), new DeviceDetails(C1581b.m1837f(), new ManufacturerDetails(C1869a.f5449a), new ModelDetails(Build.MODEL, "MPI MediaPlayer", "v1", String.format("http://%s:%s", strM2142b, "8191")), new DLNADoc[]{new DLNADoc("DMR", DLNADoc.Version.V1_5)}, new DLNACaps(new String[]{"av-upload", "image-upload", "audio-upload"})), m2081a(context), new LocalService[]{localService2, localService3, localService});
            this.f5336h = localDevice;
            localDevice.getType().toString();
            new C1833f(this).start();
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: a */
    public Icon[] m2081a(Context context) {
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) ContextCompat.getDrawable(context, R$drawable.dlna_ic_launcher);
            if (bitmapDrawable != null) {
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                return new Icon[]{new Icon(DLNAProfiles.DLNAMimeTypes.MIME_IMAGE_PNG, 48, 48, 8, "icon.png", new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))};
            }
        } catch (IOException unused) {
        }
        return null;
    }
}
