package p001a0;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* compiled from: GlideExecutor.java */
/* renamed from: a0.a */
/* loaded from: classes.dex */
public final class C0003a implements FilenameFilter {

    /* renamed from: a */
    public final /* synthetic */ Pattern f5a;

    public C0003a(Pattern pattern) {
        this.f5a = pattern;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return this.f5a.matcher(str).matches();
    }
}
