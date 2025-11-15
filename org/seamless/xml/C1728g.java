package org.seamless.xml;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/* compiled from: SAXParser.java */
/* renamed from: org.seamless.xml.g */
/* loaded from: classes.dex */
public class C1728g {

    /* renamed from: xr */
    private final XMLReader f4909xr;
    private static final Logger log = Logger.getLogger(C1728g.class.getName());
    public static final URI XML_SCHEMA_NAMESPACE = URI.create("http://www.w3.org/2001/xml.xsd");
    public static final URL XML_SCHEMA_RESOURCE = Thread.currentThread().getContextClassLoader().getResource("org/seamless/schemas/xml.xsd");

    /* compiled from: SAXParser.java */
    /* renamed from: org.seamless.xml.g$a */
    public class a extends HashMap<URI, URL> {
        public a(C1728g c1728g) {
            put(C1728g.XML_SCHEMA_NAMESPACE, C1728g.XML_SCHEMA_RESOURCE);
        }
    }

    /* compiled from: SAXParser.java */
    /* renamed from: org.seamless.xml.g$b */
    public static class b<I> extends DefaultHandler {
        public Attributes attributes;
        public StringBuilder characters;
        public I instance;
        public b parent;
        public C1728g parser;

        public b(I i7) {
            this(i7, null, null);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i7, int i8) {
            this.characters.append(cArr, i7, i8);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            if (!isLastElement(str, str2, str3)) {
                C1728g.log.finer(getClass().getSimpleName() + " ending: " + str2);
                return;
            }
            C1728g.log.finer(getClass().getSimpleName() + ": last element, switching to parent: " + str2);
            switchToParent();
        }

        public Attributes getAttributes() {
            return this.attributes;
        }

        public String getCharacters() {
            return this.characters.toString();
        }

        public I getInstance() {
            return this.instance;
        }

        public b getParent() {
            return this.parent;
        }

        public C1728g getParser() {
            return this.parser;
        }

        public boolean isLastElement(String str, String str2, String str3) {
            return false;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            this.characters = new StringBuilder();
            this.attributes = new AttributesImpl(attributes);
            C1728g.log.finer(getClass().getSimpleName() + " starting: " + str2);
        }

        public void switchToParent() {
            b bVar;
            C1728g c1728g = this.parser;
            if (c1728g == null || (bVar = this.parent) == null) {
                return;
            }
            c1728g.setContentHandler(bVar);
            this.attributes = null;
        }

        public b(I i7, C1728g c1728g) {
            this(i7, c1728g, null);
        }

        public b(I i7, b bVar) {
            this(i7, bVar.getParser(), bVar);
        }

        public b(I i7, C1728g c1728g, b bVar) {
            this.characters = new StringBuilder();
            this.instance = i7;
            this.parser = c1728g;
            this.parent = bVar;
            if (c1728g != null) {
                c1728g.setContentHandler(this);
            }
        }
    }

    /* compiled from: SAXParser.java */
    /* renamed from: org.seamless.xml.g$c */
    public class c implements ErrorHandler {
        public c(C1728g c1728g) {
        }

        @Override // org.xml.sax.ErrorHandler
        public void error(SAXParseException sAXParseException) throws SAXException {
            throw new SAXException(sAXParseException);
        }

        @Override // org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException sAXParseException) throws SAXException {
            throw new SAXException(sAXParseException);
        }

        @Override // org.xml.sax.ErrorHandler
        public void warning(SAXParseException sAXParseException) throws SAXException {
            throw new SAXException(sAXParseException);
        }
    }

    public C1728g() {
        this(null);
    }

    public XMLReader create() throws SAXException {
        try {
            if (getSchemaSources() == null) {
                return XMLReaderFactory.createXMLReader();
            }
            SAXParserFactory sAXParserFactoryNewInstance = SAXParserFactory.newInstance();
            sAXParserFactoryNewInstance.setNamespaceAware(true);
            sAXParserFactoryNewInstance.setSchema(createSchema(getSchemaSources()));
            XMLReader xMLReader = sAXParserFactoryNewInstance.newSAXParser().getXMLReader();
            xMLReader.setErrorHandler(getErrorHandler());
            return xMLReader;
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public Schema createSchema(Source[] sourceArr) {
        try {
            SchemaFactory schemaFactoryNewInstance = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            schemaFactoryNewInstance.setResourceResolver(new C1722a(new a(this)));
            return schemaFactoryNewInstance.newSchema(sourceArr);
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public ErrorHandler getErrorHandler() {
        return new c(this);
    }

    public Source[] getSchemaSources() {
        return null;
    }

    public void parse(InputSource inputSource) throws C1727f, SAXException, IOException {
        try {
            this.f4909xr.parse(inputSource);
        } catch (Exception e7) {
            throw new C1727f(e7);
        }
    }

    public void setContentHandler(ContentHandler contentHandler) {
        this.f4909xr.setContentHandler(contentHandler);
    }

    public C1728g(DefaultHandler defaultHandler) throws SAXException {
        XMLReader xMLReaderCreate = create();
        this.f4909xr = xMLReaderCreate;
        if (defaultHandler != null) {
            xMLReaderCreate.setContentHandler(defaultHandler);
        }
    }
}
