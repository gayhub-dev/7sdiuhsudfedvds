package p013b3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* compiled from: CtvitTimeUtils.java */
/* renamed from: b3.a */
/* loaded from: classes.dex */
public class C0440a {

    /* renamed from: a */
    public static final String f273a = "yyyy-MM-dd".concat(" ").concat("HH:mm:ss");

    /* renamed from: a */
    public static String m150a(long j7, boolean z6) {
        int i7 = (int) (j7 / 1000);
        int i8 = i7 % 60;
        int i9 = (i7 / 60) % 60;
        int i10 = i7 / 3600;
        return z6 ? String.format("%02d:%02d:%02d", Integer.valueOf(i10), Integer.valueOf(i9), Integer.valueOf(i8)) : i10 > 0 ? String.format("%02d:%02d:%02d", Integer.valueOf(i10), Integer.valueOf(i9), Integer.valueOf(i8)) : String.format("%02d:%02d", Integer.valueOf(i9), Integer.valueOf(i8));
    }

    /* renamed from: b */
    public static String m151b(long j7, String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str2));
        return simpleDateFormat.format(new Date(j7));
    }
}
