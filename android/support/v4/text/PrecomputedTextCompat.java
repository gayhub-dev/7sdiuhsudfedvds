package android.support.v4.text;

import android.os.Build;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.UiThread;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Preconditions;
import android.support.v7.widget.ActivityChooserView;
import android.text.Layout;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PrecomputedTextCompat implements Spannable {
    private static final char LINE_FEED = '\n';

    @GuardedBy("sLock")
    @NonNull
    private static Executor sExecutor;
    private static final Object sLock = new Object();

    @NonNull
    private final int[] mParagraphEnds;

    @NonNull
    private final Params mParams;

    @NonNull
    private final Spannable mText;

    @Nullable
    private final PrecomputedText mWrapped;

    public static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat> {

        public static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {
            private Params mParams;
            private CharSequence mText;

            public PrecomputedTextCallback(@NonNull Params params, @NonNull CharSequence charSequence) {
                this.mParams = params;
                this.mText = charSequence;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public PrecomputedTextCompat call() {
                return PrecomputedTextCompat.create(this.mText, this.mParams);
            }
        }

        public PrecomputedTextFutureTask(@NonNull Params params, @NonNull CharSequence charSequence) {
            super(new PrecomputedTextCallback(params, charSequence));
        }
    }

    private PrecomputedTextCompat(@NonNull CharSequence charSequence, @NonNull Params params, @NonNull int[] iArr) {
        this.mText = new SpannableString(charSequence);
        this.mParams = params;
        this.mParagraphEnds = iArr;
        this.mWrapped = null;
    }

    public static PrecomputedTextCompat create(@NonNull CharSequence charSequence, @NonNull Params params) {
        PrecomputedText.Params params2;
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(params);
        try {
            TraceCompat.beginSection("PrecomputedText");
            if (Build.VERSION.SDK_INT >= 28 && (params2 = params.mWrapped) != null) {
                return new PrecomputedTextCompat(PrecomputedText.create(charSequence, params2), params);
            }
            ArrayList arrayList = new ArrayList();
            int length = charSequence.length();
            int i7 = 0;
            while (i7 < length) {
                int iIndexOf = TextUtils.indexOf(charSequence, LINE_FEED, i7, length);
                i7 = iIndexOf < 0 ? length : iIndexOf + 1;
                arrayList.add(Integer.valueOf(i7));
            }
            int[] iArr = new int[arrayList.size()];
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                iArr[i8] = ((Integer) arrayList.get(i8)).intValue();
            }
            if (Build.VERSION.SDK_INT >= 23) {
                StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), params.getTextPaint(), ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED).setBreakStrategy(params.getBreakStrategy()).setHyphenationFrequency(params.getHyphenationFrequency()).setTextDirection(params.getTextDirection()).build();
            } else {
                new StaticLayout(charSequence, params.getTextPaint(), ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            }
            return new PrecomputedTextCompat(charSequence, params, iArr);
        } finally {
            TraceCompat.endSection();
        }
    }

    private int findParaIndex(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7) {
        int i8 = 0;
        while (true) {
            int[] iArr = this.mParagraphEnds;
            if (i8 >= iArr.length) {
                StringBuilder sbM112a = C0413b.m112a("pos must be less than ");
                sbM112a.append(this.mParagraphEnds[r2.length - 1]);
                sbM112a.append(", gave ");
                sbM112a.append(i7);
                throw new IndexOutOfBoundsException(sbM112a.toString());
            }
            if (i7 < iArr[i8]) {
                return i8;
            }
            i8++;
        }
    }

    @UiThread
    public static Future<PrecomputedTextCompat> getTextFuture(@NonNull CharSequence charSequence, @NonNull Params params, @Nullable Executor executor) {
        PrecomputedTextFutureTask precomputedTextFutureTask = new PrecomputedTextFutureTask(params, charSequence);
        if (executor == null) {
            synchronized (sLock) {
                if (sExecutor == null) {
                    sExecutor = Executors.newFixedThreadPool(1);
                }
                executor = sExecutor;
            }
        }
        executor.execute(precomputedTextFutureTask);
        return precomputedTextFutureTask;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i7) {
        return this.mText.charAt(i7);
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    public int getParagraphCount() {
        return Build.VERSION.SDK_INT >= 28 ? this.mWrapped.getParagraphCount() : this.mParagraphEnds.length;
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    public int getParagraphEnd(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7) {
        Preconditions.checkArgumentInRange(i7, 0, getParagraphCount(), "paraIndex");
        return Build.VERSION.SDK_INT >= 28 ? this.mWrapped.getParagraphEnd(i7) : this.mParagraphEnds[i7];
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    public int getParagraphStart(@IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7) {
        Preconditions.checkArgumentInRange(i7, 0, getParagraphCount(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphStart(i7);
        }
        if (i7 == 0) {
            return 0;
        }
        return this.mParagraphEnds[i7 - 1];
    }

    @NonNull
    public Params getParams() {
        return this.mParams;
    }

    @RequiresApi(28)
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PrecomputedText getPrecomputedText() {
        Spannable spannable = this.mText;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.mText.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.mText.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.mText.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int i7, int i8, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 28 ? (T[]) this.mWrapped.getSpans(i7, i8, cls) : (T[]) this.mText.getSpans(i7, i8, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.mText.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i7, int i8, Class cls) {
        return this.mText.nextSpanTransition(i7, i8, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.mWrapped.removeSpan(obj);
        } else {
            this.mText.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i7, int i8, int i9) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.mWrapped.setSpan(obj, i7, i8, i9);
        } else {
            this.mText.setSpan(obj, i7, i8, i9);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i7, int i8) {
        return this.mText.subSequence(i7, i8);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.mText.toString();
    }

    @RequiresApi(28)
    private PrecomputedTextCompat(@NonNull PrecomputedText precomputedText, @NonNull Params params) {
        this.mText = precomputedText;
        this.mParams = params;
        this.mParagraphEnds = null;
        this.mWrapped = precomputedText;
    }

    public static final class Params {
        private final int mBreakStrategy;
        private final int mHyphenationFrequency;

        @NonNull
        private final TextPaint mPaint;

        @Nullable
        private final TextDirectionHeuristic mTextDir;
        public final PrecomputedText.Params mWrapped;

        public static class Builder {
            private int mBreakStrategy;
            private int mHyphenationFrequency;

            @NonNull
            private final TextPaint mPaint;
            private TextDirectionHeuristic mTextDir;

            public Builder(@NonNull TextPaint textPaint) {
                this.mPaint = textPaint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mBreakStrategy = 1;
                    this.mHyphenationFrequency = 1;
                } else {
                    this.mHyphenationFrequency = 0;
                    this.mBreakStrategy = 0;
                }
                this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }

            @NonNull
            public Params build() {
                return new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
            }

            @RequiresApi(23)
            public Builder setBreakStrategy(int i7) {
                this.mBreakStrategy = i7;
                return this;
            }

            @RequiresApi(23)
            public Builder setHyphenationFrequency(int i7) {
                this.mHyphenationFrequency = i7;
                return this;
            }

            @RequiresApi(18)
            public Builder setTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
                this.mTextDir = textDirectionHeuristic;
                return this;
            }
        }

        public Params(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i7, int i8) {
            if (Build.VERSION.SDK_INT >= 28) {
                this.mWrapped = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i7).setHyphenationFrequency(i8).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.mWrapped = null;
            }
            this.mPaint = textPaint;
            this.mTextDir = textDirectionHeuristic;
            this.mBreakStrategy = i7;
            this.mHyphenationFrequency = i8;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !(obj instanceof Params)) {
                return false;
            }
            Params params = (Params) obj;
            PrecomputedText.Params params2 = this.mWrapped;
            if (params2 != null) {
                return params2.equals(params.mWrapped);
            }
            int i7 = Build.VERSION.SDK_INT;
            if ((i7 >= 23 && (this.mBreakStrategy != params.getBreakStrategy() || this.mHyphenationFrequency != params.getHyphenationFrequency())) || this.mTextDir != params.getTextDirection() || this.mPaint.getTextSize() != params.getTextPaint().getTextSize() || this.mPaint.getTextScaleX() != params.getTextPaint().getTextScaleX() || this.mPaint.getTextSkewX() != params.getTextPaint().getTextSkewX() || this.mPaint.getLetterSpacing() != params.getTextPaint().getLetterSpacing() || !TextUtils.equals(this.mPaint.getFontFeatureSettings(), params.getTextPaint().getFontFeatureSettings()) || this.mPaint.getFlags() != params.getTextPaint().getFlags()) {
                return false;
            }
            if (i7 >= 24) {
                if (!this.mPaint.getTextLocales().equals(params.getTextPaint().getTextLocales())) {
                    return false;
                }
            } else if (!this.mPaint.getTextLocale().equals(params.getTextPaint().getTextLocale())) {
                return false;
            }
            if (this.mPaint.getTypeface() == null) {
                if (params.getTextPaint().getTypeface() != null) {
                    return false;
                }
            } else if (!this.mPaint.getTypeface().equals(params.getTextPaint().getTypeface())) {
                return false;
            }
            return true;
        }

        @RequiresApi(23)
        public int getBreakStrategy() {
            return this.mBreakStrategy;
        }

        @RequiresApi(23)
        public int getHyphenationFrequency() {
            return this.mHyphenationFrequency;
        }

        @RequiresApi(18)
        @Nullable
        public TextDirectionHeuristic getTextDirection() {
            return this.mTextDir;
        }

        @NonNull
        public TextPaint getTextPaint() {
            return this.mPaint;
        }

        public int hashCode() {
            return Build.VERSION.SDK_INT >= 24 ? ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency)) : ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            StringBuilder sbM112a = C0413b.m112a("textSize=");
            sbM112a.append(this.mPaint.getTextSize());
            sb.append(sbM112a.toString());
            sb.append(", textScaleX=" + this.mPaint.getTextScaleX());
            sb.append(", textSkewX=" + this.mPaint.getTextSkewX());
            int i7 = Build.VERSION.SDK_INT;
            StringBuilder sbM112a2 = C0413b.m112a(", letterSpacing=");
            sbM112a2.append(this.mPaint.getLetterSpacing());
            sb.append(sbM112a2.toString());
            sb.append(", elegantTextHeight=" + this.mPaint.isElegantTextHeight());
            if (i7 >= 24) {
                StringBuilder sbM112a3 = C0413b.m112a(", textLocale=");
                sbM112a3.append(this.mPaint.getTextLocales());
                sb.append(sbM112a3.toString());
            } else {
                StringBuilder sbM112a4 = C0413b.m112a(", textLocale=");
                sbM112a4.append(this.mPaint.getTextLocale());
                sb.append(sbM112a4.toString());
            }
            StringBuilder sbM112a5 = C0413b.m112a(", typeface=");
            sbM112a5.append(this.mPaint.getTypeface());
            sb.append(sbM112a5.toString());
            if (i7 >= 26) {
                StringBuilder sbM112a6 = C0413b.m112a(", variationSettings=");
                sbM112a6.append(this.mPaint.getFontVariationSettings());
                sb.append(sbM112a6.toString());
            }
            StringBuilder sbM112a7 = C0413b.m112a(", textDir=");
            sbM112a7.append(this.mTextDir);
            sb.append(sbM112a7.toString());
            sb.append(", breakStrategy=" + this.mBreakStrategy);
            sb.append(", hyphenationFrequency=" + this.mHyphenationFrequency);
            sb.append("}");
            return sb.toString();
        }

        @RequiresApi(28)
        public Params(@NonNull PrecomputedText.Params params) {
            this.mPaint = params.getTextPaint();
            this.mTextDir = params.getTextDirection();
            this.mBreakStrategy = params.getBreakStrategy();
            this.mHyphenationFrequency = params.getHyphenationFrequency();
            this.mWrapped = params;
        }
    }
}
