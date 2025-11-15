package org.seamless.xml;

import org.xml.sax.SAXParseException;
import p009b.C0413b;

/* compiled from: ParserException.java */
/* renamed from: org.seamless.xml.f */
/* loaded from: classes.dex */
public class C1727f extends Exception {
    public C1727f() {
    }

    public C1727f(String str) {
        super(str);
    }

    public C1727f(String str, Throwable th) {
        super(str, th);
    }

    public C1727f(Throwable th) {
        super(th);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C1727f(SAXParseException sAXParseException) {
        StringBuilder sbM112a = C0413b.m112a("(Line/Column: ");
        sbM112a.append(sAXParseException.getLineNumber());
        sbM112a.append(":");
        sbM112a.append(sAXParseException.getColumnNumber());
        sbM112a.append(") ");
        sbM112a.append(sAXParseException.getMessage());
        super(sbM112a.toString());
    }
}
