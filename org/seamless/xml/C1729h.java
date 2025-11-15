package org.seamless.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: XmlPullParserUtils.java */
/* renamed from: org.seamless.xml.h */
/* loaded from: classes.dex */
public class C1729h {

    /* renamed from: a */
    public static final Logger f4910a = Logger.getLogger(C1729h.class.getName());

    /* renamed from: b */
    public static XmlPullParserFactory f4911b;

    static {
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            f4911b = xmlPullParserFactoryNewInstance;
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e7) {
            f4910a.severe("cannot create XmlPullParserFactory instance: " + e7);
        }
    }

    /* renamed from: a */
    public static XmlPullParser m1872a(String str) throws XmlPullParserException {
        XmlPullParserFactory xmlPullParserFactory = f4911b;
        if (xmlPullParserFactory == null) {
            throw new XmlPullParserException("no XML Pull parser factory");
        }
        XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactory.newPullParser();
        try {
            xmlPullParserNewPullParser.setInput(new ByteArrayInputStream(str.getBytes("UTF-8")), "UTF-8");
            return xmlPullParserNewPullParser;
        } catch (UnsupportedEncodingException unused) {
            throw new XmlPullParserException("UTF-8: unsupported encoding");
        }
    }

    /* renamed from: b */
    public static String m1873b(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        boolean z6 = false;
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt == '&') {
                String strSubstring = str.substring(i7, Math.min(i7 + 10, str.length()));
                if (strSubstring.startsWith("&#") || strSubstring.startsWith("&lt;") || strSubstring.startsWith("&gt;") || strSubstring.startsWith("&amp;") || strSubstring.startsWith("&apos;") || strSubstring.startsWith("&quot;")) {
                    sb.append(cCharAt);
                } else {
                    sb.append("&amp;");
                    z6 = true;
                }
            } else {
                sb.append(cCharAt);
            }
        }
        if (z6) {
            f4910a.warning("fixed badly encoded entities in XML");
        }
        return sb.toString();
    }

    /* renamed from: c */
    public static void m1874c(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                throw new IOException(String.format("tag '%s' not found", str));
            }
            if (next == 2 && xmlPullParser.getName().equals(str)) {
                return;
            }
        }
    }
}
