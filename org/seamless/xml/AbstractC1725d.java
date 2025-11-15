package org.seamless.xml;

import android.support.constraint.motion.C0080b;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.seamless.xml.AbstractC1723b;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import p009b.C0413b;

/* compiled from: DOMParser.java */
/* renamed from: org.seamless.xml.d */
/* loaded from: classes.dex */
public abstract class AbstractC1725d<D extends AbstractC1723b> implements ErrorHandler, EntityResolver {
    public Schema schema;
    public Source[] schemaSources;
    private static Logger log = Logger.getLogger(AbstractC1725d.class.getName());
    public static final URL XML_SCHEMA_RESOURCE = Thread.currentThread().getContextClassLoader().getResource("org/seamless/schemas/xml.xsd");

    /* compiled from: DOMParser.java */
    /* renamed from: org.seamless.xml.d$a */
    public class a extends HashMap<URI, URL> {
        public a(AbstractC1725d abstractC1725d) {
            put(AbstractC1723b.XML_SCHEMA_NAMESPACE, AbstractC1725d.XML_SCHEMA_RESOURCE);
        }
    }

    /* compiled from: DOMParser.java */
    /* renamed from: org.seamless.xml.d$b */
    public class b extends c {
        public b(AbstractC1725d abstractC1725d, short s6) {
            super(s6);
        }
    }

    /* compiled from: DOMParser.java */
    /* renamed from: org.seamless.xml.d$c */
    public static abstract class c {

        /* renamed from: a */
        public short f4908a;

        public c(short s6) {
            this.f4908a = s6;
        }
    }

    public AbstractC1725d() {
        this(null);
    }

    public static void accept(Node node, c cVar) throws DOMException {
        if (node == null) {
            return;
        }
        Objects.requireNonNull(cVar);
        NodeList childNodes = node.getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == cVar.f4908a) {
                CDATASection cDATASection = (CDATASection) nodeItem;
                cDATASection.getParentNode().setTextContent(cDATASection.getData());
            }
            accept(nodeItem, cVar);
        }
    }

    public static String escape(String str) {
        return escape(str, false, false);
    }

    public static String stripElements(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("<([a-zA-Z]|/).*?>", "");
    }

    public static String wrap(String str, String str2) {
        return wrap(str, null, str2);
    }

    public abstract D createDOM(Document document);

    public D createDocument() {
        try {
            return (D) createDOM(createFactory(false).newDocumentBuilder().newDocument());
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public DocumentBuilderFactory createFactory(boolean z6) throws C1727f, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            if (z6) {
                documentBuilderFactoryNewInstance.setXIncludeAware(true);
                documentBuilderFactoryNewInstance.setFeature("http://apache.org/xml/features/xinclude/fixup-base-uris", false);
                documentBuilderFactoryNewInstance.setFeature("http://apache.org/xml/features/xinclude/fixup-language", false);
                documentBuilderFactoryNewInstance.setSchema(getSchema());
                documentBuilderFactoryNewInstance.setFeature("http://apache.org/xml/features/validation/dynamic", true);
            }
            return documentBuilderFactoryNewInstance;
        } catch (ParserConfigurationException e7) {
            throw new C1727f(e7);
        }
    }

    public Transformer createTransformer(String str, int i7, boolean z6) throws C1727f, TransformerConfigurationException, TransformerFactoryConfigurationError, IllegalArgumentException {
        try {
            TransformerFactory transformerFactoryNewInstance = TransformerFactory.newInstance();
            if (i7 > 0) {
                try {
                    transformerFactoryNewInstance.setAttribute("indent-number", Integer.valueOf(i7));
                } catch (IllegalArgumentException unused) {
                }
            }
            Transformer transformerNewTransformer = transformerFactoryNewInstance.newTransformer();
            transformerNewTransformer.setOutputProperty("omit-xml-declaration", z6 ? "no" : "yes");
            if (z6) {
                try {
                    transformerNewTransformer.setOutputProperty("http://www.oracle.com/xml/is-standalone", "yes");
                } catch (IllegalArgumentException unused2) {
                }
            }
            transformerNewTransformer.setOutputProperty("indent", i7 > 0 ? "yes" : "no");
            if (i7 > 0) {
                transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(i7));
            }
            transformerNewTransformer.setOutputProperty("method", str);
            return transformerNewTransformer;
        } catch (Exception e7) {
            throw new C1727f(e7);
        }
    }

    public XPath createXPath(NamespaceContext namespaceContext) {
        XPath xPathNewXPath = createXPathFactory().newXPath();
        xPathNewXPath.setNamespaceContext(namespaceContext);
        return xPathNewXPath;
    }

    public XPathFactory createXPathFactory() {
        return XPathFactory.newInstance();
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        throw new SAXException(new C1727f(sAXParseException));
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        throw new SAXException(new C1727f(sAXParseException));
    }

    public Schema getSchema() {
        if (this.schema == null) {
            try {
                SchemaFactory schemaFactoryNewInstance = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                schemaFactoryNewInstance.setResourceResolver(new C1722a(new a(this)));
                Source[] sourceArr = this.schemaSources;
                if (sourceArr != null) {
                    this.schema = schemaFactoryNewInstance.newSchema(sourceArr);
                } else {
                    this.schema = schemaFactoryNewInstance.newSchema();
                }
            } catch (Exception e7) {
                throw new RuntimeException(e7);
            }
        }
        return this.schema;
    }

    public Object getXPathResult(AbstractC1723b abstractC1723b, XPath xPath, String str, QName qName) {
        return getXPathResult(abstractC1723b.getW3CDocument(), xPath, str, qName);
    }

    public boolean isIgnorableWSNode(Node node) {
        return node.getNodeType() == 3 && node.getTextContent().matches("[\\t\\n\\x0B\\f\\r\\s]+");
    }

    public D parse(URL url) {
        return (D) parse(url, true);
    }

    public String print(AbstractC1723b abstractC1723b) {
        return print(abstractC1723b, 4, true);
    }

    public String printHTML(Document document) {
        return printHTML(document, 4, true, true);
    }

    public void removeIgnorableWSNodes(Element element) {
        Node firstChild = element.getFirstChild();
        while (firstChild != null) {
            Node nextSibling = firstChild.getNextSibling();
            if (isIgnorableWSNode(firstChild)) {
                element.removeChild(firstChild);
            } else if (firstChild.getNodeType() == 1) {
                removeIgnorableWSNodes((Element) firstChild);
            }
            firstChild = nextSibling;
        }
    }

    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String str, String str2) {
        InputSource inputSource = str2.startsWith("file://") ? new InputSource(new FileInputStream(new File(URI.create(str2)))) : new InputSource(new ByteArrayInputStream(new byte[0]));
        inputSource.setPublicId(str);
        inputSource.setSystemId(str2);
        return inputSource;
    }

    public C1727f unwrapException(Exception exc) {
        return (exc.getCause() == null || !(exc.getCause() instanceof C1727f)) ? new C1727f(exc) : (C1727f) exc.getCause();
    }

    public void validate(URL url) throws C1727f, SAXException, IOException {
        if (url == null) {
            throw new IllegalArgumentException("Can't validate null URL");
        }
        log.fine("Validating XML of URL: " + url);
        validate(new StreamSource(url.toString()));
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) {
        log.warning(sAXParseException.toString());
    }

    public AbstractC1725d(Source[] sourceArr) {
        this.schemaSources = sourceArr;
    }

    public static String escape(String str, boolean z6, boolean z7) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            String str2 = cCharAt != '\"' ? cCharAt != '&' ? cCharAt != '<' ? cCharAt != '>' ? null : "&#62;" : "&#60;" : "&#38;" : "&#34;";
            if (str2 != null) {
                sb.append(str2);
            } else {
                sb.append(cCharAt);
            }
        }
        String string = sb.toString();
        if (z7) {
            Matcher matcher = Pattern.compile("(\\n+)(\\s*)(.*)").matcher(string);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                String strGroup = matcher.group(2);
                StringBuilder sb2 = new StringBuilder();
                for (int i8 = 0; i8 < strGroup.length(); i8++) {
                    sb2.append("&#160;");
                }
                StringBuilder sbM112a = C0413b.m112a("$1");
                sbM112a.append(sb2.toString());
                sbM112a.append("$3");
                matcher.appendReplacement(stringBuffer, sbM112a.toString());
            }
            matcher.appendTail(stringBuffer);
            string = stringBuffer.toString();
        }
        return z6 ? string.replaceAll("\n", "<br/>") : string;
    }

    public static String wrap(String str, String str2, String str3) {
        StringBuilder sbM94a = C0080b.m94a("<", str);
        if (str2 != null) {
            sbM94a.append(" xmlns=\"");
            sbM94a.append(str2);
            sbM94a.append("\"");
        }
        sbM94a.append(">");
        sbM94a.append(str3);
        sbM94a.append("</");
        sbM94a.append(str);
        sbM94a.append(">");
        return sbM94a.toString();
    }

    public Object getXPathResult(AbstractC1724c abstractC1724c, XPath xPath, String str, QName qName) {
        return getXPathResult(abstractC1724c.getW3CElement(), xPath, str, qName);
    }

    public D parse(String str) {
        return (D) parse(str, true);
    }

    public String print(AbstractC1723b abstractC1723b, int i7) {
        return print(abstractC1723b, i7, true);
    }

    public String printHTML(Document document, int i7, boolean z6, boolean z7) throws C1727f, TransformerException, DOMException, TransformerFactoryConfigurationError, IllegalArgumentException {
        Document document2 = (Document) document.cloneNode(true);
        accept(document2.getDocumentElement(), new b(this, (short) 4));
        removeIgnorableWSNodes(document2.getDocumentElement());
        try {
            Transformer transformerCreateTransformer = createTransformer("html", i7, z6);
            if (z7) {
                transformerCreateTransformer.setOutputProperty("doctype-public", "-//W3C//DTD HTML 4.01 Transitional//EN");
                transformerCreateTransformer.setOutputProperty("doctype-system", "http://www.w3.org/TR/html4/loose.dtd");
            }
            StringWriter stringWriter = new StringWriter();
            transformerCreateTransformer.transform(new DOMSource(document2), new StreamResult(stringWriter));
            stringWriter.flush();
            return stringWriter.toString().replaceFirst("\\s*<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">", "").replaceFirst("<html xmlns=\"http://www.w3.org/1999/xhtml\">", "<html>");
        } catch (Exception e7) {
            throw new C1727f(e7);
        }
    }

    public XPath createXPath(XPathFactory xPathFactory, NamespaceContext namespaceContext) {
        XPath xPathNewXPath = xPathFactory.newXPath();
        xPathNewXPath.setNamespaceContext(namespaceContext);
        return xPathNewXPath;
    }

    public Object getXPathResult(Node node, XPath xPath, String str, QName qName) {
        try {
            log.fine("Evaluating xpath query: " + str);
            return xPath.evaluate(str, node, qName);
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public D parse(File file) {
        return (D) parse(file, true);
    }

    public String print(AbstractC1723b abstractC1723b, boolean z6) {
        return print(abstractC1723b, 4, z6);
    }

    public D parse(InputStream inputStream) {
        return (D) parse(inputStream, true);
    }

    public String print(AbstractC1723b abstractC1723b, int i7, boolean z6) {
        return print(abstractC1723b.getW3CDocument(), i7, z6);
    }

    public void validate(String str) throws C1727f, SAXException, IOException {
        if (str != null) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Validating XML string characters: ");
            sbM112a.append(str.length());
            logger.fine(sbM112a.toString());
            validate(new SAXSource(new InputSource(new StringReader(str))));
            return;
        }
        throw new IllegalArgumentException("Can't validate null string");
    }

    public D parse(URL url, boolean z6) throws C1727f {
        if (url != null) {
            try {
                return (D) parse(url.openStream(), z6);
            } catch (Exception e7) {
                throw new C1727f("Parsing URL failed: " + url, e7);
            }
        }
        throw new IllegalArgumentException("Can't parse null URL");
    }

    public String print(Document document, int i7, boolean z6) {
        removeIgnorableWSNodes(document.getDocumentElement());
        return print(new DOMSource(document.getDocumentElement()), i7, z6);
    }

    public String print(String str, int i7, boolean z6) {
        return print(new StreamSource(new StringReader(str)), i7, z6);
    }

    public void validate(Document document) throws C1727f, SAXException, IOException {
        validate(new DOMSource(document));
    }

    public D parse(String str, boolean z6) {
        if (str != null) {
            return (D) parse(new InputSource(new StringReader(str)), z6);
        }
        throw new IllegalArgumentException("Can't parse null string");
    }

    public String print(Source source, int i7, boolean z6) throws C1727f, TransformerException, TransformerFactoryConfigurationError, IllegalArgumentException {
        try {
            Transformer transformerCreateTransformer = createTransformer("xml", i7, z6);
            transformerCreateTransformer.setOutputProperty("encoding", "utf-8");
            StringWriter stringWriter = new StringWriter();
            transformerCreateTransformer.transform(source, new StreamResult(stringWriter));
            stringWriter.flush();
            return stringWriter.toString();
        } catch (Exception e7) {
            throw new C1727f(e7);
        }
    }

    public void validate(AbstractC1723b abstractC1723b) throws C1727f, SAXException, IOException {
        validate(new DOMSource(abstractC1723b.getW3CDocument()));
    }

    public void validate(Source source) throws C1727f, SAXException, IOException {
        try {
            Validator validatorNewValidator = getSchema().newValidator();
            validatorNewValidator.setErrorHandler(this);
            validatorNewValidator.validate(source);
        } catch (Exception e7) {
            throw unwrapException(e7);
        }
    }

    public D parse(File file, boolean z6) throws C1727f {
        if (file != null) {
            try {
                return (D) parse(file.toURI().toURL(), z6);
            } catch (Exception e7) {
                throw new C1727f("Parsing file failed: " + file, e7);
            }
        }
        throw new IllegalArgumentException("Can't parse null file");
    }

    public D parse(InputStream inputStream, boolean z6) {
        return (D) parse(new InputSource(inputStream), z6);
    }

    public D parse(InputSource inputSource, boolean z6) throws C1727f, ParserConfigurationException, SAXException, IOException {
        try {
            DocumentBuilder documentBuilderNewDocumentBuilder = createFactory(z6).newDocumentBuilder();
            documentBuilderNewDocumentBuilder.setEntityResolver(this);
            documentBuilderNewDocumentBuilder.setErrorHandler(this);
            Document document = documentBuilderNewDocumentBuilder.parse(inputSource);
            document.normalizeDocument();
            return (D) createDOM(document);
        } catch (Exception e7) {
            throw unwrapException(e7);
        }
    }
}
