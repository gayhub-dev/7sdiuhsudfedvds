package p010b0;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import org.fourthline.cling.model.ServiceReference;
import p010b0.InterfaceC0426l;
import p162u.C1966j;

/* compiled from: StringLoader.java */
/* renamed from: b0.r */
/* loaded from: classes.dex */
public class C0432r<Data> implements InterfaceC0426l<String, Data> {

    /* renamed from: a */
    public final InterfaceC0426l<Uri, Data> f259a;

    /* compiled from: StringLoader.java */
    /* renamed from: b0.r$a */
    public static class a implements InterfaceC0427m<String, ParcelFileDescriptor> {
        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<String, ParcelFileDescriptor> mo120b(C0430p c0430p) {
            return new C0432r(c0430p.m144b(Uri.class, ParcelFileDescriptor.class));
        }
    }

    /* compiled from: StringLoader.java */
    /* renamed from: b0.r$b */
    public static class b implements InterfaceC0427m<String, InputStream> {
        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<String, InputStream> mo120b(C0430p c0430p) {
            return new C0432r(c0430p.m144b(Uri.class, InputStream.class));
        }
    }

    public C0432r(InterfaceC0426l<Uri, Data> interfaceC0426l) {
        this.f259a = interfaceC0426l;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(String str, int i7, int i8, C1966j c1966j) {
        Uri uriFromFile;
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            uriFromFile = null;
        } else if (str2.startsWith(ServiceReference.DELIMITER)) {
            uriFromFile = Uri.fromFile(new File(str2));
        } else {
            Uri uri = Uri.parse(str2);
            uriFromFile = uri.getScheme() == null ? Uri.fromFile(new File(str2)) : uri;
        }
        if (uriFromFile == null) {
            return null;
        }
        return this.f259a.mo117a(uriFromFile, i7, i8, c1966j);
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo118b(String str) {
        return true;
    }
}
