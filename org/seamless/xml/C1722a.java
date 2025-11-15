package org.seamless.xml;

import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

/* compiled from: CatalogResourceResolver.java */
/* renamed from: org.seamless.xml.a */
/* loaded from: classes.dex */
public class C1722a implements LSResourceResolver {

    /* renamed from: b */
    public static Logger f4905b = Logger.getLogger(C1722a.class.getName());

    /* renamed from: a */
    public final Map<URI, URL> f4906a;

    /* compiled from: CatalogResourceResolver.java */
    /* renamed from: org.seamless.xml.a$a */
    public static final class a implements LSInput {

        /* renamed from: a */
        public InputStream f4907a;

        public a(InputStream inputStream) {
            this.f4907a = inputStream;
        }

        @Override // org.w3c.dom.ls.LSInput
        public String getBaseURI() {
            return null;
        }

        @Override // org.w3c.dom.ls.LSInput
        public InputStream getByteStream() {
            return this.f4907a;
        }

        @Override // org.w3c.dom.ls.LSInput
        public boolean getCertifiedText() {
            return false;
        }

        @Override // org.w3c.dom.ls.LSInput
        public Reader getCharacterStream() {
            return null;
        }

        @Override // org.w3c.dom.ls.LSInput
        public String getEncoding() {
            return null;
        }

        @Override // org.w3c.dom.ls.LSInput
        public String getPublicId() {
            return null;
        }

        @Override // org.w3c.dom.ls.LSInput
        public String getStringData() {
            return null;
        }

        @Override // org.w3c.dom.ls.LSInput
        public String getSystemId() {
            return null;
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setBaseURI(String str) {
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setByteStream(InputStream inputStream) {
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setCertifiedText(boolean z6) {
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setCharacterStream(Reader reader) {
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setEncoding(String str) {
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setPublicId(String str) {
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setStringData(String str) {
        }

        @Override // org.w3c.dom.ls.LSInput
        public void setSystemId(String str) {
        }
    }

    public C1722a(Map<URI, URL> map) {
        this.f4906a = map;
    }

    @Override // org.w3c.dom.ls.LSResourceResolver
    public LSInput resolveResource(String str, String str2, String str3, String str4, String str5) {
        f4905b.finest("Trying to resolve system identifier URI in catalog: " + str4);
        URL url = this.f4906a.get(URI.create(str4));
        if (url == null) {
            f4905b.info("System identifier not found in catalog, continuing with default resolution (this most likely means remote HTTP request!): " + str4);
            return null;
        }
        f4905b.finest("Loading catalog resource: " + url);
        try {
            return new a(url.openStream());
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }
}
