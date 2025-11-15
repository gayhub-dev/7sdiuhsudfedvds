package io.reactivex.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import io.reactivex.android.plugins.RxAndroidPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;
import p194y3.AbstractC2128t;

/* loaded from: classes.dex */
public final class AndroidSchedulers {
    private static final AbstractC2128t MAIN_THREAD = RxAndroidPlugins.initMainThreadScheduler(new Callable<AbstractC2128t>() { // from class: io.reactivex.android.schedulers.AndroidSchedulers.1
        @Override // java.util.concurrent.Callable
        public AbstractC2128t call() {
            return MainHolder.DEFAULT;
        }
    });

    public static final class MainHolder {
        public static final AbstractC2128t DEFAULT = new HandlerScheduler(new Handler(Looper.getMainLooper()), false);

        private MainHolder() {
        }
    }

    private AndroidSchedulers() {
        throw new AssertionError("No instances.");
    }

    public static AbstractC2128t from(Looper looper) {
        return from(looper, false);
    }

    public static AbstractC2128t mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(MAIN_THREAD);
    }

    @SuppressLint({"NewApi"})
    public static AbstractC2128t from(Looper looper, boolean z6) {
        Objects.requireNonNull(looper, "looper == null");
        int i7 = Build.VERSION.SDK_INT;
        if (z6 && i7 < 22) {
            Message messageObtain = Message.obtain();
            try {
                messageObtain.setAsynchronous(true);
            } catch (NoSuchMethodError unused) {
                z6 = false;
            }
            messageObtain.recycle();
        }
        return new HandlerScheduler(new Handler(looper), z6);
    }
}
