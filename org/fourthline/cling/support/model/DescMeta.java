package org.fourthline.cling.support.model;

import java.net.URI;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/* loaded from: classes.dex */
public class DescMeta<M> {

    /* renamed from: id */
    public String f4897id;
    public M metadata;
    public URI nameSpace;
    public String type;

    public DescMeta() {
    }

    public Document createMetadataDocument() {
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
            documentNewDocument.appendChild(documentNewDocument.createElementNS(DIDLContent.DESC_WRAPPER_NAMESPACE_URI, "desc-wrapper"));
            return documentNewDocument;
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    public String getId() {
        return this.f4897id;
    }

    public M getMetadata() {
        return this.metadata;
    }

    public URI getNameSpace() {
        return this.nameSpace;
    }

    public String getType() {
        return this.type;
    }

    public void setId(String str) {
        this.f4897id = str;
    }

    public void setMetadata(M m7) {
        this.metadata = m7;
    }

    public void setNameSpace(URI uri) {
        this.nameSpace = uri;
    }

    public void setType(String str) {
        this.type = str;
    }

    public DescMeta(String str, String str2, URI uri, M m7) {
        this.f4897id = str;
        this.type = str2;
        this.nameSpace = uri;
        this.metadata = m7;
    }
}
