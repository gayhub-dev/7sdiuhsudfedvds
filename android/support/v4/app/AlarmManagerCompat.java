package android.support.v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;
import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public final class AlarmManagerCompat {
    private AlarmManagerCompat() {
    }

    public static void setAlarmClock(@NonNull AlarmManager alarmManager, long j7, @NonNull PendingIntent pendingIntent, @NonNull PendingIntent pendingIntent2) {
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(j7, pendingIntent), pendingIntent2);
    }

    public static void setAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int i7, long j7, @NonNull PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setAndAllowWhileIdle(i7, j7, pendingIntent);
        } else {
            alarmManager.set(i7, j7, pendingIntent);
        }
    }

    public static void setExact(@NonNull AlarmManager alarmManager, int i7, long j7, @NonNull PendingIntent pendingIntent) {
        alarmManager.setExact(i7, j7, pendingIntent);
    }

    public static void setExactAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int i7, long j7, @NonNull PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(i7, j7, pendingIntent);
        } else {
            setExact(alarmManager, i7, j7, pendingIntent);
        }
    }
}
