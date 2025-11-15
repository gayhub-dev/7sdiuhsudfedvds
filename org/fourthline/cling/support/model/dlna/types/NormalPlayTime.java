package org.fourthline.cling.support.model.dlna.types;

import android.arch.lifecycle.C0063n;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fourthline.cling.model.types.InvalidValueException;

/* loaded from: classes.dex */
public class NormalPlayTime {
    public static final Pattern pattern = Pattern.compile("^(\\d+):(\\d{1,2}):(\\d{1,2})(\\.(\\d{1,3}))?|(\\d+)(\\.(\\d{1,3}))?$", 2);
    private long milliseconds;

    /* renamed from: org.fourthline.cling.support.model.dlna.types.NormalPlayTime$1 */
    public static /* synthetic */ class C17111 {

        /* renamed from: $SwitchMap$org$fourthline$cling$support$model$dlna$types$NormalPlayTime$Format */
        public static final /* synthetic */ int[] f4904x61cef210;

        static {
            int[] iArr = new int[Format.values().length];
            f4904x61cef210 = iArr;
            try {
                iArr[Format.TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum Format {
        SECONDS,
        TIME
    }

    public NormalPlayTime(long j7) {
        if (j7 < 0) {
            throw new InvalidValueException(C0534b.m341a("Invalid parameter milliseconds: ", j7));
        }
        this.milliseconds = j7;
    }

    public static NormalPlayTime valueOf(String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            try {
                if (matcher.group(1) != null) {
                    return new NormalPlayTime(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2)), Long.parseLong(matcher.group(3)), Long.parseLong(matcher.group(5)) * ((int) Math.pow(10.0d, 3 - matcher.group(5).length())));
                }
                return new NormalPlayTime((Long.parseLong(matcher.group(8)) * ((int) Math.pow(10.0d, 3 - matcher.group(8).length()))) + (Long.parseLong(matcher.group(6)) * 1000));
            } catch (NumberFormatException unused) {
            }
        }
        throw new InvalidValueException(C0063n.m88a("Can't parse NormalPlayTime: ", str));
    }

    public long getMilliseconds() {
        return this.milliseconds;
    }

    public String getString() {
        return getString(Format.SECONDS);
    }

    public void setMilliseconds(long j7) {
        if (j7 < 0) {
            throw new InvalidValueException(C0534b.m341a("Invalid parameter milliseconds: ", j7));
        }
        this.milliseconds = j7;
    }

    public String getString(Format format) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long seconds = timeUnit.toSeconds(this.milliseconds);
        long j7 = this.milliseconds % 1000;
        if (C17111.f4904x61cef210[format.ordinal()] != 1) {
            return String.format(Locale.ROOT, "%d.%03d", Long.valueOf(seconds), Long.valueOf(j7));
        }
        long seconds2 = timeUnit.toSeconds(this.milliseconds) - TimeUnit.MINUTES.toSeconds(timeUnit.toMinutes(this.milliseconds));
        return String.format(Locale.ROOT, "%d:%02d:%02d.%03d", Long.valueOf(timeUnit.toHours(this.milliseconds)), Long.valueOf(timeUnit.toMinutes(this.milliseconds) - TimeUnit.HOURS.toMinutes(timeUnit.toHours(this.milliseconds))), Long.valueOf(seconds2), Long.valueOf(j7));
    }

    public NormalPlayTime(long j7, long j8, long j9, long j10) {
        if (j7 < 0) {
            throw new InvalidValueException(C0534b.m341a("Invalid parameter hours: ", j7));
        }
        if (j8 < 0 || j8 > 59) {
            throw new InvalidValueException(C0534b.m341a("Invalid parameter minutes: ", j7));
        }
        if (j9 < 0 || j9 > 59) {
            throw new InvalidValueException(C0534b.m341a("Invalid parameter seconds: ", j7));
        }
        if (j10 >= 0 && j10 <= 999) {
            this.milliseconds = (((j8 * 60) + (j7 * 60 * 60) + j9) * 1000) + j10;
            return;
        }
        throw new InvalidValueException(C0534b.m341a("Invalid parameter milliseconds: ", j10));
    }
}
