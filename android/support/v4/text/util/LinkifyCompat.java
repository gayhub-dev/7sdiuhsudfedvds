package android.support.v4.text.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.constraint.C0072a;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class LinkifyCompat {
    private static final String[] EMPTY_STRING = new String[0];
    private static final Comparator<LinkSpec> COMPARATOR = new Comparator<LinkSpec>() { // from class: android.support.v4.text.util.LinkifyCompat.1
        @Override // java.util.Comparator
        public int compare(LinkSpec linkSpec, LinkSpec linkSpec2) {
            int i7;
            int i8;
            int i9 = linkSpec.start;
            int i10 = linkSpec2.start;
            if (i9 < i10) {
                return -1;
            }
            if (i9 <= i10 && (i7 = linkSpec.end) >= (i8 = linkSpec2.end)) {
                return i7 > i8 ? -1 : 0;
            }
            return 1;
        }
    };

    public static class LinkSpec {
        public int end;
        public URLSpan frameworkAddedSpan;
        public int start;
        public String url;
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface LinkifyMask {
    }

    private LinkifyCompat() {
    }

    private static void addLinkMovementMethod(@NonNull TextView textView) {
        MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public static boolean addLinks(@NonNull Spannable spannable, int i7) throws UnsupportedEncodingException {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable, i7);
        }
        if (i7 == 0) {
            return false;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int length = uRLSpanArr.length - 1; length >= 0; length--) {
            spannable.removeSpan(uRLSpanArr[length]);
        }
        if ((i7 & 4) != 0) {
            Linkify.addLinks(spannable, 4);
        }
        ArrayList arrayList = new ArrayList();
        if ((i7 & 1) != 0) {
            gatherLinks(arrayList, spannable, PatternsCompat.AUTOLINK_WEB_URL, new String[]{"http://", "https://", "rtsp://"}, Linkify.sUrlMatchFilter, null);
        }
        if ((i7 & 2) != 0) {
            gatherLinks(arrayList, spannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{"mailto:"}, null, null);
        }
        if ((i7 & 8) != 0) {
            gatherMapLinks(arrayList, spannable);
        }
        pruneOverlaps(arrayList, spannable);
        if (arrayList.size() == 0) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            LinkSpec linkSpec = (LinkSpec) it.next();
            if (linkSpec.frameworkAddedSpan == null) {
                applyLink(linkSpec.url, linkSpec.start, linkSpec.end, spannable);
            }
        }
        return true;
    }

    private static void applyLink(String str, int i7, int i8, Spannable spannable) {
        spannable.setSpan(new URLSpan(str), i7, i8, 33);
    }

    private static String findAddress(String str) {
        return Build.VERSION.SDK_INT >= 28 ? WebView.findAddress(str) : FindAddress.findAddress(str);
    }

    private static void gatherLinks(ArrayList<LinkSpec> arrayList, Spannable spannable, Pattern pattern, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        Matcher matcher = pattern.matcher(spannable);
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (matchFilter == null || matchFilter.acceptMatch(spannable, iStart, iEnd)) {
                LinkSpec linkSpec = new LinkSpec();
                linkSpec.url = makeUrl(matcher.group(0), strArr, matcher, transformFilter);
                linkSpec.start = iStart;
                linkSpec.end = iEnd;
                arrayList.add(linkSpec);
            }
        }
    }

    private static void gatherMapLinks(ArrayList<LinkSpec> arrayList, Spannable spannable) throws UnsupportedEncodingException {
        int iIndexOf;
        String string = spannable.toString();
        int i7 = 0;
        while (true) {
            try {
                String strFindAddress = findAddress(string);
                if (strFindAddress != null && (iIndexOf = string.indexOf(strFindAddress)) >= 0) {
                    LinkSpec linkSpec = new LinkSpec();
                    int length = strFindAddress.length() + iIndexOf;
                    linkSpec.start = iIndexOf + i7;
                    i7 += length;
                    linkSpec.end = i7;
                    string = string.substring(length);
                    try {
                        linkSpec.url = "geo:0,0?q=" + URLEncoder.encode(strFindAddress, "UTF-8");
                        arrayList.add(linkSpec);
                    } catch (UnsupportedEncodingException unused) {
                    }
                }
                return;
            } catch (UnsupportedOperationException unused2) {
                return;
            }
        }
    }

    private static String makeUrl(@NonNull String str, @NonNull String[] strArr, Matcher matcher, @Nullable Linkify.TransformFilter transformFilter) {
        boolean z6;
        if (transformFilter != null) {
            str = transformFilter.transformUrl(matcher, str);
        }
        int i7 = 0;
        while (true) {
            z6 = true;
            if (i7 >= strArr.length) {
                z6 = false;
                break;
            }
            if (str.regionMatches(true, 0, strArr[i7], 0, strArr[i7].length())) {
                if (!str.regionMatches(false, 0, strArr[i7], 0, strArr[i7].length())) {
                    str = strArr[i7] + str.substring(strArr[i7].length());
                }
            } else {
                i7++;
            }
        }
        return (z6 || strArr.length <= 0) ? str : C0072a.m92a(new StringBuilder(), strArr[0], str);
    }

    private static void pruneOverlaps(ArrayList<LinkSpec> arrayList, Spannable spannable) {
        int i7;
        int i8 = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int i9 = 0; i9 < uRLSpanArr.length; i9++) {
            LinkSpec linkSpec = new LinkSpec();
            linkSpec.frameworkAddedSpan = uRLSpanArr[i9];
            linkSpec.start = spannable.getSpanStart(uRLSpanArr[i9]);
            linkSpec.end = spannable.getSpanEnd(uRLSpanArr[i9]);
            arrayList.add(linkSpec);
        }
        Collections.sort(arrayList, COMPARATOR);
        int size = arrayList.size();
        while (i8 < size - 1) {
            LinkSpec linkSpec2 = arrayList.get(i8);
            int i10 = i8 + 1;
            LinkSpec linkSpec3 = arrayList.get(i10);
            int i11 = linkSpec2.start;
            int i12 = linkSpec3.start;
            if (i11 <= i12 && (i7 = linkSpec2.end) > i12) {
                int i13 = linkSpec3.end;
                int i14 = (i13 > i7 && i7 - i11 <= i13 - i12) ? i7 - i11 < i13 - i12 ? i8 : -1 : i10;
                if (i14 != -1) {
                    Object obj = arrayList.get(i14).frameworkAddedSpan;
                    if (obj != null) {
                        spannable.removeSpan(obj);
                    }
                    arrayList.remove(i14);
                    size--;
                }
            }
            i8 = i10;
        }
    }

    private static boolean shouldAddLinksFallbackToFramework() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean addLinks(@NonNull TextView textView, int i7) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(textView, i7);
        }
        if (i7 == 0) {
            return false;
        }
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            if (!addLinks((Spannable) text, i7)) {
                return false;
            }
            addLinkMovementMethod(textView);
            return true;
        }
        SpannableString spannableStringValueOf = SpannableString.valueOf(text);
        if (!addLinks(spannableStringValueOf, i7)) {
            return false;
        }
        addLinkMovementMethod(textView);
        textView.setText(spannableStringValueOf);
        return true;
    }

    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str) {
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(textView, pattern, str);
        } else {
            addLinks(textView, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
    }

    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(textView, pattern, str, matchFilter, transformFilter);
        } else {
            addLinks(textView, pattern, str, (String[]) null, matchFilter, transformFilter);
        }
    }

    @SuppressLint({"NewApi"})
    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable String[] strArr, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            Linkify.addLinks(textView, pattern, str, strArr, matchFilter, transformFilter);
            return;
        }
        SpannableString spannableStringValueOf = SpannableString.valueOf(textView.getText());
        if (addLinks(spannableStringValueOf, pattern, str, strArr, matchFilter, transformFilter)) {
            textView.setText(spannableStringValueOf);
            addLinkMovementMethod(textView);
        }
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable, pattern, str);
        }
        return addLinks(spannable, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable, pattern, str, matchFilter, transformFilter);
        }
        return addLinks(spannable, pattern, str, (String[]) null, matchFilter, transformFilter);
    }

    @SuppressLint({"NewApi"})
    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str, @Nullable String[] strArr, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (shouldAddLinksFallbackToFramework()) {
            return Linkify.addLinks(spannable, pattern, str, strArr, matchFilter, transformFilter);
        }
        if (str == null) {
            str = "";
        }
        if (strArr == null || strArr.length < 1) {
            strArr = EMPTY_STRING;
        }
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = str.toLowerCase(Locale.ROOT);
        int i7 = 0;
        while (i7 < strArr.length) {
            String str2 = strArr[i7];
            i7++;
            strArr2[i7] = str2 == null ? "" : str2.toLowerCase(Locale.ROOT);
        }
        Matcher matcher = pattern.matcher(spannable);
        boolean z6 = false;
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (matchFilter != null ? matchFilter.acceptMatch(spannable, iStart, iEnd) : true) {
                applyLink(makeUrl(matcher.group(0), strArr2, matcher, transformFilter), iStart, iEnd, spannable);
                z6 = true;
            }
        }
        return z6;
    }
}
