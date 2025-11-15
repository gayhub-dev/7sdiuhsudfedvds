package p036e0;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Objects;
import p162u.C1965i;
import p162u.C1966j;
import p162u.InterfaceC1967k;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2086d;

/* compiled from: VideoBitmapDecoder.java */
/* renamed from: e0.r */
/* loaded from: classes.dex */
public class C0919r implements InterfaceC1967k<ParcelFileDescriptor, Bitmap> {

    /* renamed from: c */
    public static final C1965i<Long> f1676c = new C1965i<>("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new a());

    /* renamed from: d */
    public static final C1965i<Integer> f1677d = new C1965i<>("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", null, new b());

    /* renamed from: e */
    public static final c f1678e = new c();

    /* renamed from: a */
    public final InterfaceC2086d f1679a;

    /* renamed from: b */
    public final c f1680b;

    /* compiled from: VideoBitmapDecoder.java */
    /* renamed from: e0.r$a */
    public static class a implements C1965i.b<Long> {

        /* renamed from: a */
        public final ByteBuffer f1681a = ByteBuffer.allocate(8);

        @Override // p162u.C1965i.b
        /* renamed from: a */
        public void mo853a(byte[] bArr, Long l7, MessageDigest messageDigest) {
            Long l8 = l7;
            messageDigest.update(bArr);
            synchronized (this.f1681a) {
                this.f1681a.position(0);
                messageDigest.update(this.f1681a.putLong(l8.longValue()).array());
            }
        }
    }

    /* compiled from: VideoBitmapDecoder.java */
    /* renamed from: e0.r$b */
    public static class b implements C1965i.b<Integer> {

        /* renamed from: a */
        public final ByteBuffer f1682a = ByteBuffer.allocate(4);

        @Override // p162u.C1965i.b
        /* renamed from: a */
        public void mo853a(byte[] bArr, Integer num, MessageDigest messageDigest) {
            Integer num2 = num;
            if (num2 == null) {
                return;
            }
            messageDigest.update(bArr);
            synchronized (this.f1682a) {
                this.f1682a.position(0);
                messageDigest.update(this.f1682a.putInt(num2.intValue()).array());
            }
        }
    }

    /* compiled from: VideoBitmapDecoder.java */
    /* renamed from: e0.r$c */
    public static class c {
    }

    public C0919r(InterfaceC2086d interfaceC2086d) {
        c cVar = f1678e;
        this.f1679a = interfaceC2086d;
        this.f1680b = cVar;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: a */
    public boolean mo819a(ParcelFileDescriptor parcelFileDescriptor, C1966j c1966j) throws IOException {
        boolean z6;
        ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        Objects.requireNonNull(this.f1680b);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor2.getFileDescriptor());
            z6 = true;
        } catch (RuntimeException unused) {
            z6 = false;
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
        mediaMetadataRetriever.release();
        return z6;
    }

    @Override // p162u.InterfaceC1967k
    /* renamed from: b */
    public InterfaceC2057r<Bitmap> mo820b(ParcelFileDescriptor parcelFileDescriptor, int i7, int i8, C1966j c1966j) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        long jLongValue = ((Long) c1966j.m2296c(f1676c)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException(C0534b.m341a("Requested frame must be non-negative, or DEFAULT_FRAME, given: ", jLongValue));
        }
        Integer num = (Integer) c1966j.m2296c(f1677d);
        Objects.requireNonNull(this.f1680b);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor2.getFileDescriptor());
            Bitmap frameAtTime = jLongValue == -1 ? mediaMetadataRetriever.getFrameAtTime() : num == null ? mediaMetadataRetriever.getFrameAtTime(jLongValue) : mediaMetadataRetriever.getFrameAtTime(jLongValue, num.intValue());
            mediaMetadataRetriever.release();
            parcelFileDescriptor2.close();
            return C0905d.m822c(frameAtTime, this.f1679a);
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
    }
}
