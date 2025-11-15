package p036e0;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p190y.InterfaceC2086d;

/* compiled from: TransformationUtils.java */
/* renamed from: e0.q */
/* loaded from: classes.dex */
public final class C0918q {

    /* renamed from: a */
    public static final Paint f1674a = new Paint(6);

    /* renamed from: b */
    public static final Lock f1675b;

    /* compiled from: TransformationUtils.java */
    /* renamed from: e0.q$a */
    public static final class a implements Lock {
        @Override // java.util.concurrent.locks.Lock
        public void lock() {
        }

        @Override // java.util.concurrent.locks.Lock
        public void lockInterruptibly() {
        }

        @Override // java.util.concurrent.locks.Lock
        @NonNull
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock() {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock(long j7, @NonNull TimeUnit timeUnit) {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
        }
    }

    static {
        new Paint(7);
        f1675b = (Arrays.asList("XT1097", "XT1085").contains(Build.MODEL) && Build.VERSION.SDK_INT == 22) ? new ReentrantLock() : new a();
        new Paint(7).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    /* renamed from: a */
    public static void m850a(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2, Matrix matrix) {
        Lock lock = f1675b;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawBitmap(bitmap, matrix, f1674a);
            canvas.setBitmap(null);
            lock.unlock();
        } catch (Throwable th) {
            f1675b.unlock();
            throw th;
        }
    }

    /* renamed from: b */
    public static Bitmap m851b(@NonNull InterfaceC2086d interfaceC2086d, @NonNull Bitmap bitmap, int i7, int i8) {
        if (bitmap.getWidth() == i7 && bitmap.getHeight() == i8) {
            Log.isLoggable("TransformationUtils", 2);
            return bitmap;
        }
        float fMin = Math.min(i7 / bitmap.getWidth(), i8 / bitmap.getHeight());
        int width = (int) (bitmap.getWidth() * fMin);
        int height = (int) (bitmap.getHeight() * fMin);
        if (bitmap.getWidth() == width && bitmap.getHeight() == height) {
            Log.isLoggable("TransformationUtils", 2);
            return bitmap;
        }
        Bitmap bitmapMo2519d = interfaceC2086d.mo2519d(width, height, m852c(bitmap));
        bitmapMo2519d.setHasAlpha(bitmap.hasAlpha());
        if (Log.isLoggable("TransformationUtils", 2)) {
            bitmap.getWidth();
            bitmap.getHeight();
            bitmapMo2519d.getWidth();
            bitmapMo2519d.getHeight();
        }
        Matrix matrix = new Matrix();
        matrix.setScale(fMin, fMin);
        m850a(bitmap, bitmapMo2519d, matrix);
        return bitmapMo2519d;
    }

    /* renamed from: c */
    public static Bitmap.Config m852c(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }
}
