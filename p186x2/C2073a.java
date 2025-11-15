package p186x2;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import p152s3.C1874e;
import p152s3.C1875f;
import p152s3.C1877h;

/* compiled from: CtvitLogUtils.java */
/* renamed from: x2.a */
/* loaded from: classes.dex */
public class C2073a {

    /* renamed from: a */
    public static C1875f f6172a;

    /* renamed from: a */
    public static void m2456a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m2460e(null);
        f6172a.m2147c(6, null, str, new Object[0]);
    }

    /* renamed from: b */
    public static void m2457b(String str, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            str = "异常";
        }
        m2460e(null);
        f6172a.m2147c(6, th, str, new Object[0]);
    }

    /* renamed from: c */
    public static void m2458c(Throwable th) {
        m2460e(null);
        f6172a.m2147c(6, th, "异常", new Object[0]);
    }

    /* renamed from: d */
    public static void m2459d(String str) {
        boolean z6;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m2460e(null);
        boolean z7 = true;
        try {
            JSON.parse(str);
            z6 = true;
        } catch (Exception unused) {
            z6 = false;
        }
        if (z6) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            m2460e(null);
            C1875f c1875f = f6172a;
            Objects.requireNonNull(c1875f);
            if (C1877h.m2151b(str)) {
                c1875f.m2145a("Empty/Null json content");
                return;
            }
            try {
                String strTrim = str.trim();
                if (strTrim.startsWith("{")) {
                    c1875f.m2145a(new JSONObject(strTrim).toString(2));
                } else if (strTrim.startsWith("[")) {
                    c1875f.m2145a(new JSONArray(strTrim).toString(2));
                } else {
                    c1875f.m2147c(6, null, "Invalid Json", new Object[0]);
                }
                return;
            } catch (JSONException unused2) {
                c1875f.m2147c(6, null, "Invalid Json", new Object[0]);
                return;
            }
        }
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception unused3) {
            z7 = false;
        }
        if (!z7) {
            f6172a.m2147c(4, null, str, new Object[0]);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m2460e(null);
        C1875f c1875f2 = f6172a;
        Objects.requireNonNull(c1875f2);
        if (C1877h.m2151b(str)) {
            c1875f2.m2145a("Empty/Null xml content");
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            transformerNewTransformer.setOutputProperty("indent", "yes");
            transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformerNewTransformer.transform(streamSource, streamResult);
            c1875f2.m2145a(streamResult.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException unused4) {
            c1875f2.m2147c(6, null, "Invalid xml", new Object[0]);
        }
    }

    /* renamed from: e */
    public static C1875f m2460e(String str) {
        C1875f c1875f = C1874e.f5451a;
        Objects.requireNonNull(c1875f);
        f6172a = c1875f;
        return c1875f;
    }
}
