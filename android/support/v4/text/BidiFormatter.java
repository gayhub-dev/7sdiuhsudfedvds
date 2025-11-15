package android.support.v4.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;
import p082j6.C1212a;

/* loaded from: classes.dex */
public final class BidiFormatter {
    private static final int DEFAULT_FLAGS = 2;
    public static final BidiFormatter DEFAULT_LTR_INSTANCE;
    public static final BidiFormatter DEFAULT_RTL_INSTANCE;
    public static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = 8234;
    private static final char LRM = 8206;
    private static final String LRM_STRING;
    private static final char PDF = 8236;
    private static final char RLE = 8235;
    private static final char RLM = 8207;
    private static final String RLM_STRING;
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    public static class DirectionalityEstimator {
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final CharSequence text;
        private static final int DIR_TYPE_CACHE_SIZE = 1792;
        private static final byte[] DIR_TYPE_CACHE = new byte[DIR_TYPE_CACHE_SIZE];

        static {
            for (int i7 = 0; i7 < DIR_TYPE_CACHE_SIZE; i7++) {
                DIR_TYPE_CACHE[i7] = Character.getDirectionality(i7);
            }
        }

        public DirectionalityEstimator(CharSequence charSequence, boolean z6) {
            this.text = charSequence;
            this.isHtml = z6;
            this.length = charSequence.length();
        }

        private static byte getCachedDirectionality(char c7) {
            return c7 < DIR_TYPE_CACHE_SIZE ? DIR_TYPE_CACHE[c7] : Character.getDirectionality(c7);
        }

        private byte skipEntityBackward() {
            char cCharAt;
            int i7 = this.charIndex;
            do {
                int i8 = this.charIndex;
                if (i8 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i9 = i8 - 1;
                this.charIndex = i9;
                cCharAt = charSequence.charAt(i9);
                this.lastChar = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.charIndex = i7;
            this.lastChar = ';';
            return C1212a.f2735CR;
        }

        private byte skipEntityForward() {
            char cCharAt;
            do {
                int i7 = this.charIndex;
                if (i7 >= this.length) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.text;
                this.charIndex = i7 + 1;
                cCharAt = charSequence.charAt(i7);
                this.lastChar = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        private byte skipTagBackward() {
            char cCharAt;
            int i7 = this.charIndex;
            while (true) {
                int i8 = this.charIndex;
                if (i8 <= 0) {
                    break;
                }
                CharSequence charSequence = this.text;
                int i9 = i8 - 1;
                this.charIndex = i9;
                char cCharAt2 = charSequence.charAt(i9);
                this.lastChar = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i10 = this.charIndex;
                        if (i10 > 0) {
                            CharSequence charSequence2 = this.text;
                            int i11 = i10 - 1;
                            this.charIndex = i11;
                            cCharAt = charSequence2.charAt(i11);
                            this.lastChar = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.charIndex = i7;
            this.lastChar = '>';
            return C1212a.f2735CR;
        }

        private byte skipTagForward() {
            char cCharAt;
            int i7 = this.charIndex;
            while (true) {
                int i8 = this.charIndex;
                if (i8 >= this.length) {
                    this.charIndex = i7;
                    this.lastChar = '<';
                    return C1212a.f2735CR;
                }
                CharSequence charSequence = this.text;
                this.charIndex = i8 + 1;
                char cCharAt2 = charSequence.charAt(i8);
                this.lastChar = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i9 = this.charIndex;
                        if (i9 < this.length) {
                            CharSequence charSequence2 = this.text;
                            this.charIndex = i9 + 1;
                            cCharAt = charSequence2.charAt(i9);
                            this.lastChar = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }

        public byte dirTypeBackward() {
            char cCharAt = this.text.charAt(this.charIndex - 1);
            this.lastChar = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.charIndex--;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (!this.isHtml) {
                return cachedDirectionality;
            }
            char c7 = this.lastChar;
            return c7 == '>' ? skipTagBackward() : c7 == ';' ? skipEntityBackward() : cachedDirectionality;
        }

        public byte dirTypeForward() {
            char cCharAt = this.text.charAt(this.charIndex);
            this.lastChar = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.text, this.charIndex);
                this.charIndex = Character.charCount(iCodePointAt) + this.charIndex;
                return Character.getDirectionality(iCodePointAt);
            }
            this.charIndex++;
            byte cachedDirectionality = getCachedDirectionality(this.lastChar);
            if (!this.isHtml) {
                return cachedDirectionality;
            }
            char c7 = this.lastChar;
            return c7 == '<' ? skipTagForward() : c7 == '&' ? skipEntityForward() : cachedDirectionality;
        }

        public int getEntryDir() {
            this.charIndex = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (this.charIndex < this.length && i7 == 0) {
                byte bDirTypeForward = dirTypeForward();
                if (bDirTypeForward != 0) {
                    if (bDirTypeForward == 1 || bDirTypeForward == 2) {
                        if (i9 == 0) {
                            return 1;
                        }
                    } else if (bDirTypeForward != 9) {
                        switch (bDirTypeForward) {
                            case 14:
                            case 15:
                                i9++;
                                i8 = -1;
                                continue;
                            case 16:
                            case 17:
                                i9++;
                                i8 = 1;
                                continue;
                            case 18:
                                i9--;
                                i8 = 0;
                                continue;
                        }
                    }
                } else if (i9 == 0) {
                    return -1;
                }
                i7 = i9;
            }
            if (i7 == 0) {
                return 0;
            }
            if (i8 != 0) {
                return i8;
            }
            while (this.charIndex > 0) {
                switch (dirTypeBackward()) {
                    case 14:
                    case 15:
                        if (i7 == i9) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i7 == i9) {
                            return 1;
                        }
                        break;
                    case 18:
                        i9++;
                        continue;
                }
                i9--;
            }
            return 0;
        }

        public int getExitDir() {
            this.charIndex = this.length;
            int i7 = 0;
            int i8 = 0;
            while (this.charIndex > 0) {
                byte bDirTypeBackward = dirTypeBackward();
                if (bDirTypeBackward != 0) {
                    if (bDirTypeBackward == 1 || bDirTypeBackward == 2) {
                        if (i7 == 0) {
                            return 1;
                        }
                        if (i8 == 0) {
                            i8 = i7;
                        }
                    } else if (bDirTypeBackward != 9) {
                        switch (bDirTypeBackward) {
                            case 14:
                            case 15:
                                if (i8 == i7) {
                                    return -1;
                                }
                                i7--;
                                break;
                            case 16:
                            case 17:
                                if (i8 == i7) {
                                    return 1;
                                }
                                i7--;
                                break;
                            case 18:
                                i7++;
                                break;
                            default:
                                if (i8 != 0) {
                                    break;
                                } else {
                                    i8 = i7;
                                    break;
                                }
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (i7 == 0) {
                        return -1;
                    }
                    if (i8 == 0) {
                        i8 = i7;
                    }
                }
            }
            return 0;
        }
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        DEFAULT_TEXT_DIRECTION_HEURISTIC = textDirectionHeuristicCompat;
        LRM_STRING = Character.toString(LRM);
        RLM_STRING = Character.toString(RLM);
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, textDirectionHeuristicCompat);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, textDirectionHeuristicCompat);
    }

    public BidiFormatter(boolean z6, int i7, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.mIsRtlContext = z6;
        this.mFlags = i7;
        this.mDefaultTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
    }

    private static int getEntryDir(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).getEntryDir();
    }

    private static int getExitDir(CharSequence charSequence) {
        return new DirectionalityEstimator(charSequence, false).getExitDir();
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static boolean isRtlLocale(Locale locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }

    private String markAfter(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean zIsRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.mIsRtlContext || !(zIsRtl || getExitDir(charSequence) == 1)) ? this.mIsRtlContext ? (!zIsRtl || getExitDir(charSequence) == -1) ? RLM_STRING : "" : "" : LRM_STRING;
    }

    private String markBefore(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean zIsRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.mIsRtlContext || !(zIsRtl || getEntryDir(charSequence) == 1)) ? this.mIsRtlContext ? (!zIsRtl || getEntryDir(charSequence) == -1) ? RLM_STRING : "" : "" : LRM_STRING;
    }

    public boolean getStereoReset() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isRtl(String str) {
        return isRtl((CharSequence) str);
    }

    public boolean isRtlContext() {
        return this.mIsRtlContext;
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z6) {
        if (str == null) {
            return null;
        }
        return unicodeWrap((CharSequence) str, textDirectionHeuristicCompat, z6).toString();
    }

    public static final class Builder {
        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
        }

        private static BidiFormatter getDefaultInstanceFromContext(boolean z6) {
            return z6 ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE;
        }

        private void initialize(boolean z6) {
            this.mIsRtlContext = z6;
            this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public BidiFormatter build() {
            return (this.mFlags == 2 && this.mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC) ? getDefaultInstanceFromContext(this.mIsRtlContext) : new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
            this.mTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
            return this;
        }

        public Builder stereoReset(boolean z6) {
            if (z6) {
                this.mFlags |= 2;
            } else {
                this.mFlags &= -3;
            }
            return this;
        }

        public Builder(boolean z6) {
            initialize(z6);
        }

        public Builder(Locale locale) {
            initialize(BidiFormatter.isRtlLocale(locale));
        }
    }

    public static BidiFormatter getInstance(boolean z6) {
        return new Builder(z6).build();
    }

    public boolean isRtl(CharSequence charSequence) {
        return this.mDefaultTextDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z6) {
        if (charSequence == null) {
            return null;
        }
        boolean zIsRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getStereoReset() && z6) {
            spannableStringBuilder.append((CharSequence) markBefore(charSequence, zIsRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        if (zIsRtl != this.mIsRtlContext) {
            spannableStringBuilder.append(zIsRtl ? RLE : LRE);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append(PDF);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z6) {
            spannableStringBuilder.append((CharSequence) markAfter(charSequence, zIsRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        return spannableStringBuilder;
    }

    public static BidiFormatter getInstance(Locale locale) {
        return new Builder(locale).build();
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(str, textDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(charSequence, textDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String str, boolean z6) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, z6);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, boolean z6) {
        return unicodeWrap(charSequence, this.mDefaultTextDirectionHeuristicCompat, z6);
    }

    public String unicodeWrap(String str) {
        return unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence) {
        return unicodeWrap(charSequence, this.mDefaultTextDirectionHeuristicCompat, true);
    }
}
