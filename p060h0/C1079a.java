package p060h0;

import java.io.File;
import p162u.C1966j;
import p162u.InterfaceC1967k;
import p183x.InterfaceC2057r;

/* compiled from: FileDecoder.java */
/* renamed from: h0.a */
/* loaded from: classes.dex */
public class C1079a implements InterfaceC1967k<File, File> {
    @Override // p162u.InterfaceC1967k
    /* renamed from: a */
    public /* bridge */ /* synthetic */ boolean mo819a(File file, C1966j c1966j) {
        return true;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: b */
    public InterfaceC2057r<File> mo820b(File file, int i7, int i8, C1966j c1966j) {
        return new C1080b(file);
    }
}
