package p154s5;

import java.util.Arrays;
import okhttp3.HttpUrl;

/* compiled from: ServletMapping.java */
/* renamed from: s5.g */
/* loaded from: classes.dex */
public class C1890g {

    /* renamed from: a */
    public String[] f5542a;

    /* renamed from: b */
    public String f5543b;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] strArr = this.f5542a;
        sb.append(strArr == null ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : Arrays.asList(strArr).toString());
        sb.append("=>");
        sb.append(this.f5543b);
        return sb.toString();
    }
}
