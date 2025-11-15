package org.fourthline.cling.support.contentdirectory;

import android.support.constraint.motion.C0080b;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.binding.xml.Descriptor;
import org.fourthline.cling.model.XMLUtil;
import org.fourthline.cling.model.types.Datatype;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.support.model.DIDLAttribute;
import org.fourthline.cling.support.model.DIDLContent;
import org.fourthline.cling.support.model.DIDLObject;
import org.fourthline.cling.support.model.DescMeta;
import org.fourthline.cling.support.model.Person;
import org.fourthline.cling.support.model.PersonWithRole;
import org.fourthline.cling.support.model.ProtocolInfo;
import org.fourthline.cling.support.model.Res;
import org.fourthline.cling.support.model.StorageMedium;
import org.fourthline.cling.support.model.WriteStatus;
import org.fourthline.cling.support.model.container.Container;
import org.fourthline.cling.support.model.item.Item;
import org.seamless.xml.C1727f;
import org.seamless.xml.C1728g;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import p009b.C0413b;
import p106m6.C1500c;
import p186x2.C2074b;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public class DIDLParser extends C1728g {
    public static final String UNKNOWN_TITLE = "Unknown Title";
    private static final Logger log = Logger.getLogger(DIDLParser.class.getName());

    public class ContainerHandler extends DIDLObjectHandler<Container> {
        public ContainerHandler(Container container, C1728g.b bVar) {
            super(container, bVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.fourthline.cling.support.contentdirectory.DIDLParser.DIDLObjectHandler, org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            super.endElement(str, str2, str3);
            if (DIDLObject.Property.UPNP.NAMESPACE.URI.equals(str)) {
                if ("searchClass".equals(str2)) {
                    ((Container) getInstance()).getSearchClasses().add(new DIDLObject.Class(getCharacters(), getAttributes().getValue("name"), "true".equals(getAttributes().getValue("includeDerived"))));
                } else if ("createClass".equals(str2)) {
                    ((Container) getInstance()).getCreateClasses().add(new DIDLObject.Class(getCharacters(), getAttributes().getValue("name"), "true".equals(getAttributes().getValue("includeDerived"))));
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            if (!DIDLContent.NAMESPACE_URI.equals(str) || !"container".equals(str2)) {
                return false;
            }
            if (((Container) getInstance()).getTitle() == null) {
                Logger logger = DIDLParser.log;
                StringBuilder sbM112a = C0413b.m112a("In DIDL content, missing 'dc:title' element for container: ");
                sbM112a.append(((Container) getInstance()).getId());
                logger.warning(sbM112a.toString());
            }
            if (((Container) getInstance()).getClazz() != null) {
                return true;
            }
            Logger logger2 = DIDLParser.log;
            StringBuilder sbM112a2 = C0413b.m112a("In DIDL content, missing 'upnp:class' element for container: ");
            sbM112a2.append(((Container) getInstance()).getId());
            logger2.warning(sbM112a2.toString());
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            Res resCreateResource;
            super.startElement(str, str2, str3, attributes);
            if (DIDLContent.NAMESPACE_URI.equals(str)) {
                if (str2.equals("item")) {
                    Item itemCreateItem = DIDLParser.this.createItem(attributes);
                    ((Container) getInstance()).addItem(itemCreateItem);
                    DIDLParser.this.createItemHandler(itemCreateItem, this);
                } else if (str2.equals("desc")) {
                    DescMeta descMetaCreateDescMeta = DIDLParser.this.createDescMeta(attributes);
                    ((Container) getInstance()).addDescMetadata(descMetaCreateDescMeta);
                    DIDLParser.this.createDescMetaHandler(descMetaCreateDescMeta, this);
                } else {
                    if (!str2.equals("res") || (resCreateResource = DIDLParser.this.createResource(attributes)) == null) {
                        return;
                    }
                    ((Container) getInstance()).addResource(resCreateResource);
                    DIDLParser.this.createResHandler(resCreateResource, this);
                }
            }
        }
    }

    public abstract class DIDLObjectHandler<I extends DIDLObject> extends C1728g.b<I> {
        public DIDLObjectHandler(I i7, C1728g.b bVar) {
            super(i7, bVar);
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            super.endElement(str, str2, str3);
            if (DIDLObject.Property.C1707DC.NAMESPACE.URI.equals(str)) {
                if ("title".equals(str2)) {
                    getInstance().setTitle(getCharacters());
                    return;
                }
                if ("creator".equals(str2)) {
                    getInstance().setCreator(getCharacters());
                    return;
                }
                if ("description".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.C1707DC.DESCRIPTION(getCharacters()));
                    return;
                }
                if ("publisher".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.C1707DC.PUBLISHER(new Person(getCharacters())));
                    return;
                }
                if ("contributor".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.C1707DC.CONTRIBUTOR(new Person(getCharacters())));
                    return;
                }
                if ("date".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.C1707DC.DATE(getCharacters()));
                    return;
                }
                if (IjkMediaMeta.IJKM_KEY_LANGUAGE.equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.C1707DC.LANGUAGE(getCharacters()));
                    return;
                } else if ("rights".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.C1707DC.RIGHTS(getCharacters()));
                    return;
                } else {
                    if ("relation".equals(str2)) {
                        getInstance().addProperty(new DIDLObject.Property.C1707DC.RELATION(URI.create(getCharacters())));
                        return;
                    }
                    return;
                }
            }
            if (DIDLObject.Property.UPNP.NAMESPACE.URI.equals(str)) {
                if ("writeStatus".equals(str2)) {
                    try {
                        getInstance().setWriteStatus(WriteStatus.valueOf(getCharacters()));
                        return;
                    } catch (Exception unused) {
                        Logger logger = DIDLParser.log;
                        StringBuilder sbM112a = C0413b.m112a("Ignoring invalid writeStatus value: ");
                        sbM112a.append(getCharacters());
                        logger.info(sbM112a.toString());
                        return;
                    }
                }
                if ("class".equals(str2)) {
                    getInstance().setClazz(new DIDLObject.Class(getCharacters(), getAttributes().getValue("name")));
                    return;
                }
                if ("artist".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.ARTIST(new PersonWithRole(getCharacters(), getAttributes().getValue("role"))));
                    return;
                }
                if ("actor".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.ACTOR(new PersonWithRole(getCharacters(), getAttributes().getValue("role"))));
                    return;
                }
                if ("author".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.AUTHOR(new PersonWithRole(getCharacters(), getAttributes().getValue("role"))));
                    return;
                }
                if ("producer".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.PRODUCER(new Person(getCharacters())));
                    return;
                }
                if ("director".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.DIRECTOR(new Person(getCharacters())));
                    return;
                }
                if ("longDescription".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.LONG_DESCRIPTION(getCharacters()));
                    return;
                }
                if ("storageUsed".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.STORAGE_USED(Long.valueOf(getCharacters())));
                    return;
                }
                if ("storageTotal".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.STORAGE_TOTAL(Long.valueOf(getCharacters())));
                    return;
                }
                if ("storageFree".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.STORAGE_FREE(Long.valueOf(getCharacters())));
                    return;
                }
                if ("storageMaxPartition".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.STORAGE_MAX_PARTITION(Long.valueOf(getCharacters())));
                    return;
                }
                if ("storageMedium".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.STORAGE_MEDIUM(StorageMedium.valueOrVendorSpecificOf(getCharacters())));
                    return;
                }
                if ("genre".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.GENRE(getCharacters()));
                    return;
                }
                if ("album".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.ALBUM(getCharacters()));
                    return;
                }
                if ("playlist".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.PLAYLIST(getCharacters()));
                    return;
                }
                if ("region".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.REGION(getCharacters()));
                    return;
                }
                if ("rating".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.RATING(getCharacters()));
                    return;
                }
                if ("toc".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.TOC(getCharacters()));
                    return;
                }
                if ("albumArtURI".equals(str2)) {
                    DIDLObject.Property.UPNP.ALBUM_ART_URI album_art_uri = new DIDLObject.Property.UPNP.ALBUM_ART_URI(URI.create(getCharacters()));
                    Attributes attributes = getAttributes();
                    for (int i7 = 0; i7 < attributes.getLength(); i7++) {
                        if ("profileID".equals(attributes.getLocalName(i7))) {
                            album_art_uri.addAttribute(new DIDLObject.Property.DLNA.PROFILE_ID(new DIDLAttribute(DIDLObject.Property.DLNA.NAMESPACE.URI, Descriptor.Device.DLNA_PREFIX, attributes.getValue(i7))));
                        }
                    }
                    getInstance().addProperty(album_art_uri);
                    return;
                }
                if ("artistDiscographyURI".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.ARTIST_DISCO_URI(URI.create(getCharacters())));
                    return;
                }
                if ("lyricsURI".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.LYRICS_URI(URI.create(getCharacters())));
                    return;
                }
                if ("icon".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.ICON(URI.create(getCharacters())));
                    return;
                }
                if ("radioCallSign".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.RADIO_CALL_SIGN(getCharacters()));
                    return;
                }
                if ("radioStationID".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.RADIO_STATION_ID(getCharacters()));
                    return;
                }
                if ("radioBand".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.RADIO_BAND(getCharacters()));
                    return;
                }
                if ("channelNr".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.CHANNEL_NR(Integer.valueOf(getCharacters())));
                    return;
                }
                if ("channelName".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.CHANNEL_NAME(getCharacters()));
                    return;
                }
                if ("scheduledStartTime".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.SCHEDULED_START_TIME(getCharacters()));
                    return;
                }
                if ("scheduledEndTime".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.SCHEDULED_END_TIME(getCharacters()));
                    return;
                }
                if ("DVDRegionCode".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.DVD_REGION_CODE(Integer.valueOf(getCharacters())));
                } else if ("originalTrackNumber".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.ORIGINAL_TRACK_NUMBER(Integer.valueOf(getCharacters())));
                } else if ("userAnnotation".equals(str2)) {
                    getInstance().addProperty(new DIDLObject.Property.UPNP.USER_ANNOTATION(getCharacters()));
                }
            }
        }
    }

    public class DescMetaHandler extends C1728g.b<DescMeta> {
        public Element current;

        public DescMetaHandler(DescMeta descMeta, C1728g.b bVar) {
            super(descMeta, bVar);
            descMeta.setMetadata(descMeta.createMetadataDocument());
            this.current = ((Document) getInstance().getMetadata()).getDocumentElement();
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            super.endElement(str, str2, str3);
            if (isLastElement(str, str2, str3)) {
                return;
            }
            if (getCharacters().length() > 0 && !getCharacters().matches("[\\t\\n\\x0B\\f\\r\\s]+")) {
                this.current.appendChild(((Document) getInstance().getMetadata()).createTextNode(getCharacters()));
            }
            this.current = (Element) this.current.getParentNode();
            this.characters = new StringBuilder();
            this.attributes = null;
        }

        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            return DIDLContent.NAMESPACE_URI.equals(str) && "desc".equals(str2);
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws DOMException {
            super.startElement(str, str2, str3, attributes);
            Element elementCreateElementNS = ((Document) getInstance().getMetadata()).createElementNS(str, str3);
            for (int i7 = 0; i7 < attributes.getLength(); i7++) {
                elementCreateElementNS.setAttributeNS(attributes.getURI(i7), attributes.getQName(i7), attributes.getValue(i7));
            }
            this.current.appendChild(elementCreateElementNS);
            this.current = elementCreateElementNS;
        }

        @Override // org.seamless.xml.C1728g.b
        public DescMeta getInstance() {
            return (DescMeta) super.getInstance();
        }
    }

    public class ItemHandler extends DIDLObjectHandler<Item> {
        public ItemHandler(Item item, C1728g.b bVar) {
            super(item, bVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            if (!DIDLContent.NAMESPACE_URI.equals(str) || !"item".equals(str2)) {
                return false;
            }
            if (((Item) getInstance()).getTitle() == null) {
                Logger logger = DIDLParser.log;
                StringBuilder sbM112a = C0413b.m112a("In DIDL content, missing 'dc:title' element for item: ");
                sbM112a.append(((Item) getInstance()).getId());
                logger.warning(sbM112a.toString());
            }
            if (((Item) getInstance()).getClazz() != null) {
                return true;
            }
            Logger logger2 = DIDLParser.log;
            StringBuilder sbM112a2 = C0413b.m112a("In DIDL content, missing 'upnp:class' element for item: ");
            sbM112a2.append(((Item) getInstance()).getId());
            logger2.warning(sbM112a2.toString());
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            super.startElement(str, str2, str3, attributes);
            if (DIDLContent.NAMESPACE_URI.equals(str)) {
                if (str2.equals("res")) {
                    Res resCreateResource = DIDLParser.this.createResource(attributes);
                    if (resCreateResource != null) {
                        ((Item) getInstance()).addResource(resCreateResource);
                        DIDLParser.this.createResHandler(resCreateResource, this);
                        return;
                    }
                    return;
                }
                if (str2.equals("desc")) {
                    DescMeta descMetaCreateDescMeta = DIDLParser.this.createDescMeta(attributes);
                    ((Item) getInstance()).addDescMetadata(descMetaCreateDescMeta);
                    DIDLParser.this.createDescMetaHandler(descMetaCreateDescMeta, this);
                }
            }
        }
    }

    public class ResHandler extends C1728g.b<Res> {
        public ResHandler(Res res, C1728g.b bVar) {
            super(res, bVar);
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) {
            super.endElement(str, str2, str3);
            getInstance().setValue(getCharacters());
        }

        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            return DIDLContent.NAMESPACE_URI.equals(str) && "res".equals(str2);
        }
    }

    public class RootHandler extends C1728g.b<DIDLContent> {
        public RootHandler(DIDLContent dIDLContent, C1728g c1728g) {
            super(dIDLContent, c1728g);
        }

        @Override // org.seamless.xml.C1728g.b
        public boolean isLastElement(String str, String str2, String str3) {
            if (!DIDLContent.NAMESPACE_URI.equals(str) || !"DIDL-Lite".equals(str2)) {
                return false;
            }
            getInstance().replaceGenericContainerAndItems();
            return true;
        }

        @Override // org.seamless.xml.C1728g.b, org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) {
            super.startElement(str, str2, str3, attributes);
            if (DIDLContent.NAMESPACE_URI.equals(str)) {
                if (str2.equals("container")) {
                    Container containerCreateContainer = DIDLParser.this.createContainer(attributes);
                    getInstance().addContainer(containerCreateContainer);
                    DIDLParser.this.createContainerHandler(containerCreateContainer, this);
                } else if (str2.equals("item")) {
                    Item itemCreateItem = DIDLParser.this.createItem(attributes);
                    getInstance().addItem(itemCreateItem);
                    DIDLParser.this.createItemHandler(itemCreateItem, this);
                } else if (str2.equals("desc")) {
                    DescMeta descMetaCreateDescMeta = DIDLParser.this.createDescMeta(attributes);
                    getInstance().addDescMetadata(descMetaCreateDescMeta);
                    DIDLParser.this.createDescMetaHandler(descMetaCreateDescMeta, this);
                }
            }
        }
    }

    private Long toLongOrNull(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public void appendClass(Document document, Element element, DIDLObject.Class r52, String str, boolean z6) throws DOMException {
        Element elementAppendNewElementIfNotNull = XMLUtil.appendNewElementIfNotNull(document, element, str, r52.getValue(), DIDLObject.Property.UPNP.NAMESPACE.URI);
        if (r52.getFriendlyName() != null && r52.getFriendlyName().length() > 0) {
            elementAppendNewElementIfNotNull.setAttribute("name", r52.getFriendlyName());
        }
        if (z6) {
            elementAppendNewElementIfNotNull.setAttribute("includeDerived", Boolean.toString(r52.isIncludeDerived()));
        }
    }

    public void appendProperties(Document document, Element element, DIDLObject dIDLObject, String str, Class<? extends DIDLObject.Property.NAMESPACE> cls, String str2) throws DOMException {
        for (DIDLObject.Property property : dIDLObject.getPropertiesByNamespace(cls)) {
            StringBuilder sbM94a = C0080b.m94a(str, ":");
            sbM94a.append(property.getDescriptorName());
            Element elementCreateElementNS = document.createElementNS(str2, sbM94a.toString());
            element.appendChild(elementCreateElementNS);
            property.setOnElement(elementCreateElementNS);
        }
    }

    public String booleanToInt(boolean z6) {
        return z6 ? DiskLruCache.VERSION_1 : "0";
    }

    public Document buildDOM(DIDLContent dIDLContent, boolean z6) throws DOMException {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setNamespaceAware(true);
        Document documentNewDocument = documentBuilderFactoryNewInstance.newDocumentBuilder().newDocument();
        generateRoot(dIDLContent, documentNewDocument, z6);
        return documentNewDocument;
    }

    public Container createContainer(Attributes attributes) {
        Container container = new Container();
        container.setId(attributes.getValue("id"));
        container.setParentID(attributes.getValue("parentID"));
        if (attributes.getValue("childCount") != null) {
            container.setChildCount(Integer.valueOf(attributes.getValue("childCount")));
        }
        try {
            Datatype.Builtin builtin = Datatype.Builtin.BOOLEAN;
            Boolean bool = (Boolean) builtin.getDatatype().valueOf(attributes.getValue("restricted"));
            if (bool != null) {
                container.setRestricted(bool.booleanValue());
            }
            Boolean bool2 = (Boolean) builtin.getDatatype().valueOf(attributes.getValue("searchable"));
            if (bool2 != null) {
                container.setSearchable(bool2.booleanValue());
            }
        } catch (Exception unused) {
        }
        return container;
    }

    public ContainerHandler createContainerHandler(Container container, C1728g.b bVar) {
        return new ContainerHandler(container, bVar);
    }

    public DescMeta createDescMeta(Attributes attributes) {
        DescMeta descMeta = new DescMeta();
        descMeta.setId(attributes.getValue("id"));
        if (attributes.getValue("type") != null) {
            descMeta.setType(attributes.getValue("type"));
        }
        if (attributes.getValue("nameSpace") != null) {
            descMeta.setNameSpace(URI.create(attributes.getValue("nameSpace")));
        }
        return descMeta;
    }

    public DescMetaHandler createDescMetaHandler(DescMeta descMeta, C1728g.b bVar) {
        return new DescMetaHandler(descMeta, bVar);
    }

    public Item createItem(Attributes attributes) {
        Item item = new Item();
        item.setId(attributes.getValue("id"));
        item.setParentID(attributes.getValue("parentID"));
        try {
            Boolean bool = (Boolean) Datatype.Builtin.BOOLEAN.getDatatype().valueOf(attributes.getValue("restricted"));
            if (bool != null) {
                item.setRestricted(bool.booleanValue());
            }
        } catch (Exception unused) {
        }
        if (attributes.getValue("refID") != null) {
            item.setRefID(attributes.getValue("refID"));
        }
        return item;
    }

    public ItemHandler createItemHandler(Item item, C1728g.b bVar) {
        return new ItemHandler(item, bVar);
    }

    public ResHandler createResHandler(Res res, C1728g.b bVar) {
        return new ResHandler(res, bVar);
    }

    public Res createResource(Attributes attributes) {
        Res res = new Res();
        if (attributes.getValue("importUri") != null) {
            res.setImportUri(URI.create(attributes.getValue("importUri")));
        }
        try {
            res.setProtocolInfo(new ProtocolInfo(attributes.getValue("protocolInfo")));
            if (attributes.getValue("size") != null) {
                res.setSize(toLongOrNull(attributes.getValue("size")));
            }
            if (attributes.getValue("duration") != null) {
                res.setDuration(attributes.getValue("duration"));
            }
            if (attributes.getValue(IjkMediaMeta.IJKM_KEY_BITRATE) != null) {
                res.setBitrate(toLongOrNull(attributes.getValue(IjkMediaMeta.IJKM_KEY_BITRATE)));
            }
            if (attributes.getValue("sampleFrequency") != null) {
                res.setSampleFrequency(toLongOrNull(attributes.getValue("sampleFrequency")));
            }
            if (attributes.getValue("bitsPerSample") != null) {
                res.setBitsPerSample(toLongOrNull(attributes.getValue("bitsPerSample")));
            }
            if (attributes.getValue("nrAudioChannels") != null) {
                res.setNrAudioChannels(toLongOrNull(attributes.getValue("nrAudioChannels")));
            }
            if (attributes.getValue("colorDepth") != null) {
                res.setColorDepth(toLongOrNull(attributes.getValue("colorDepth")));
            }
            if (attributes.getValue("protection") != null) {
                res.setProtection(attributes.getValue("protection"));
            }
            if (attributes.getValue("resolution") != null) {
                res.setResolution(attributes.getValue("resolution"));
            }
            return res;
        } catch (InvalidValueException e7) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("In DIDL content, invalid resource protocol info: ");
            sbM112a.append(C2074b.m2475O(e7));
            logger.warning(sbM112a.toString());
            return null;
        }
    }

    public RootHandler createRootHandler(DIDLContent dIDLContent, C1728g c1728g) {
        return new RootHandler(dIDLContent, c1728g);
    }

    public void debugXML(String str) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("-------------------------------------------------------------------------------------");
            logger.fine("\n" + str);
            logger.fine("-------------------------------------------------------------------------------------");
        }
    }

    public String documentToString(Document document, boolean z6) throws TransformerException, IllegalArgumentException {
        Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
        if (z6) {
            transformerNewTransformer.setOutputProperty("omit-xml-declaration", "yes");
        }
        StringWriter stringWriter = new StringWriter();
        transformerNewTransformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    public String generate(DIDLContent dIDLContent) {
        return generate(dIDLContent, false);
    }

    public void generateContainer(Container container, Document document, Element element, boolean z6) throws DOMException {
        if (container.getClazz() == null) {
            StringBuilder sbM112a = C0413b.m112a("Missing 'upnp:class' element for container: ");
            sbM112a.append(container.getId());
            throw new RuntimeException(sbM112a.toString());
        }
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, "container");
        if (container.getId() == null) {
            throw new NullPointerException("Missing id on container: " + container);
        }
        elementAppendNewElement.setAttribute("id", container.getId());
        if (container.getParentID() == null) {
            throw new NullPointerException("Missing parent id on container: " + container);
        }
        elementAppendNewElement.setAttribute("parentID", container.getParentID());
        if (container.getChildCount() != null) {
            elementAppendNewElement.setAttribute("childCount", Integer.toString(container.getChildCount().intValue()));
        }
        elementAppendNewElement.setAttribute("restricted", booleanToInt(container.isRestricted()));
        elementAppendNewElement.setAttribute("searchable", booleanToInt(container.isSearchable()));
        String title = container.getTitle();
        if (title == null) {
            Logger logger = log;
            StringBuilder sbM112a2 = C0413b.m112a("Missing 'dc:title' element for container: ");
            sbM112a2.append(container.getId());
            logger.warning(sbM112a2.toString());
            title = UNKNOWN_TITLE;
        }
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, "dc:title", title, DIDLObject.Property.C1707DC.NAMESPACE.URI);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, "dc:creator", container.getCreator(), DIDLObject.Property.C1707DC.NAMESPACE.URI);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, "upnp:writeStatus", container.getWriteStatus(), DIDLObject.Property.UPNP.NAMESPACE.URI);
        appendClass(document, elementAppendNewElement, container.getClazz(), "upnp:class", false);
        Iterator<DIDLObject.Class> it = container.getSearchClasses().iterator();
        while (it.hasNext()) {
            appendClass(document, elementAppendNewElement, it.next(), "upnp:searchClass", true);
        }
        Iterator<DIDLObject.Class> it2 = container.getCreateClasses().iterator();
        while (it2.hasNext()) {
            appendClass(document, elementAppendNewElement, it2.next(), "upnp:createClass", true);
        }
        appendProperties(document, elementAppendNewElement, container, "upnp", DIDLObject.Property.UPNP.NAMESPACE.class, DIDLObject.Property.UPNP.NAMESPACE.URI);
        appendProperties(document, elementAppendNewElement, container, "dc", DIDLObject.Property.C1707DC.NAMESPACE.class, DIDLObject.Property.C1707DC.NAMESPACE.URI);
        if (z6) {
            for (Item item : container.getItems()) {
                if (item != null) {
                    generateItem(item, document, elementAppendNewElement);
                }
            }
        }
        for (Res res : container.getResources()) {
            if (res != null) {
                generateResource(res, document, elementAppendNewElement);
            }
        }
        for (DescMeta descMeta : container.getDescMetadata()) {
            if (descMeta != null) {
                generateDescMetadata(descMeta, document, elementAppendNewElement);
            }
        }
    }

    public void generateDescMetadata(DescMeta descMeta, Document document, Element element) throws DOMException {
        if (descMeta.getId() == null) {
            throw new RuntimeException("Missing id of description metadata: " + descMeta);
        }
        if (descMeta.getNameSpace() == null) {
            throw new RuntimeException("Missing namespace of description metadata: " + descMeta);
        }
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, "desc");
        elementAppendNewElement.setAttribute("id", descMeta.getId());
        elementAppendNewElement.setAttribute("nameSpace", descMeta.getNameSpace().toString());
        if (descMeta.getType() != null) {
            elementAppendNewElement.setAttribute("type", descMeta.getType());
        }
        populateDescMetadata(elementAppendNewElement, descMeta);
    }

    public void generateItem(Item item, Document document, Element element) throws DOMException {
        if (item.getClazz() == null) {
            StringBuilder sbM112a = C0413b.m112a("Missing 'upnp:class' element for item: ");
            sbM112a.append(item.getId());
            throw new RuntimeException(sbM112a.toString());
        }
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, "item");
        if (item.getId() == null) {
            throw new NullPointerException("Missing id on item: " + item);
        }
        elementAppendNewElement.setAttribute("id", item.getId());
        if (item.getParentID() == null) {
            throw new NullPointerException("Missing parent id on item: " + item);
        }
        elementAppendNewElement.setAttribute("parentID", item.getParentID());
        if (item.getRefID() != null) {
            elementAppendNewElement.setAttribute("refID", item.getRefID());
        }
        elementAppendNewElement.setAttribute("restricted", booleanToInt(item.isRestricted()));
        String title = item.getTitle();
        if (title == null) {
            Logger logger = log;
            StringBuilder sbM112a2 = C0413b.m112a("Missing 'dc:title' element for item: ");
            sbM112a2.append(item.getId());
            logger.warning(sbM112a2.toString());
            title = UNKNOWN_TITLE;
        }
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, "dc:title", title, DIDLObject.Property.C1707DC.NAMESPACE.URI);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, "dc:creator", item.getCreator(), DIDLObject.Property.C1707DC.NAMESPACE.URI);
        XMLUtil.appendNewElementIfNotNull(document, elementAppendNewElement, "upnp:writeStatus", item.getWriteStatus(), DIDLObject.Property.UPNP.NAMESPACE.URI);
        appendClass(document, elementAppendNewElement, item.getClazz(), "upnp:class", false);
        appendProperties(document, elementAppendNewElement, item, "upnp", DIDLObject.Property.UPNP.NAMESPACE.class, DIDLObject.Property.UPNP.NAMESPACE.URI);
        appendProperties(document, elementAppendNewElement, item, "dc", DIDLObject.Property.C1707DC.NAMESPACE.class, DIDLObject.Property.C1707DC.NAMESPACE.URI);
        appendProperties(document, elementAppendNewElement, item, Descriptor.Device.SEC_PREFIX, DIDLObject.Property.SEC.NAMESPACE.class, DIDLObject.Property.SEC.NAMESPACE.URI);
        for (Res res : item.getResources()) {
            if (res != null) {
                generateResource(res, document, elementAppendNewElement);
            }
        }
        for (DescMeta descMeta : item.getDescMetadata()) {
            if (descMeta != null) {
                generateDescMetadata(descMeta, document, elementAppendNewElement);
            }
        }
    }

    public void generateResource(Res res, Document document, Element element) throws DOMException {
        if (res.getValue() == null) {
            throw new RuntimeException("Missing resource URI value" + res);
        }
        if (res.getProtocolInfo() == null) {
            throw new RuntimeException("Missing resource protocol info: " + res);
        }
        Element elementAppendNewElement = XMLUtil.appendNewElement(document, element, "res", res.getValue());
        elementAppendNewElement.setAttribute("protocolInfo", res.getProtocolInfo().toString());
        if (res.getImportUri() != null) {
            elementAppendNewElement.setAttribute("importUri", res.getImportUri().toString());
        }
        if (res.getSize() != null) {
            elementAppendNewElement.setAttribute("size", res.getSize().toString());
        }
        if (res.getDuration() != null) {
            elementAppendNewElement.setAttribute("duration", res.getDuration());
        }
        if (res.getBitrate() != null) {
            elementAppendNewElement.setAttribute(IjkMediaMeta.IJKM_KEY_BITRATE, res.getBitrate().toString());
        }
        if (res.getSampleFrequency() != null) {
            elementAppendNewElement.setAttribute("sampleFrequency", res.getSampleFrequency().toString());
        }
        if (res.getBitsPerSample() != null) {
            elementAppendNewElement.setAttribute("bitsPerSample", res.getBitsPerSample().toString());
        }
        if (res.getNrAudioChannels() != null) {
            elementAppendNewElement.setAttribute("nrAudioChannels", res.getNrAudioChannels().toString());
        }
        if (res.getColorDepth() != null) {
            elementAppendNewElement.setAttribute("colorDepth", res.getColorDepth().toString());
        }
        if (res.getProtection() != null) {
            elementAppendNewElement.setAttribute("protection", res.getProtection());
        }
        if (res.getResolution() != null) {
            elementAppendNewElement.setAttribute("resolution", res.getResolution());
        }
    }

    public void generateRoot(DIDLContent dIDLContent, Document document, boolean z6) throws DOMException {
        Element elementCreateElementNS = document.createElementNS(DIDLContent.NAMESPACE_URI, "DIDL-Lite");
        document.appendChild(elementCreateElementNS);
        elementCreateElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:upnp", DIDLObject.Property.UPNP.NAMESPACE.URI);
        elementCreateElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:dc", DIDLObject.Property.C1707DC.NAMESPACE.URI);
        elementCreateElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sec", DIDLObject.Property.SEC.NAMESPACE.URI);
        for (Container container : dIDLContent.getContainers()) {
            if (container != null) {
                generateContainer(container, document, elementCreateElementNS, z6);
            }
        }
        for (Item item : dIDLContent.getItems()) {
            if (item != null) {
                generateItem(item, document, elementCreateElementNS);
            }
        }
        for (DescMeta descMeta : dIDLContent.getDescMetadata()) {
            if (descMeta != null) {
                generateDescMetadata(descMeta, document, elementCreateElementNS);
            }
        }
    }

    public DIDLContent parse(String str) throws C1727f, SAXException, IOException {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("Null or empty XML");
        }
        DIDLContent dIDLContent = new DIDLContent();
        createRootHandler(dIDLContent, this);
        log.fine("Parsing DIDL XML content");
        parse(new InputSource(new StringReader(str)));
        return dIDLContent;
    }

    public DIDLContent parseResource(String str) throws IOException {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
            DIDLContent dIDLContent = parse(C1500c.m1665b(resourceAsStream));
            resourceAsStream.close();
            return dIDLContent;
        } catch (Throwable th) {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
            throw th;
        }
    }

    public void populateDescMetadata(Element element, DescMeta descMeta) {
        if (!(descMeta.getMetadata() instanceof Document)) {
            Logger logger = log;
            StringBuilder sbM112a = C0413b.m112a("Unknown desc metadata content, please override populateDescMetadata(): ");
            sbM112a.append(descMeta.getMetadata());
            logger.warning(sbM112a.toString());
            return;
        }
        NodeList childNodes = ((Document) descMeta.getMetadata()).getDocumentElement().getChildNodes();
        for (int i7 = 0; i7 < childNodes.getLength(); i7++) {
            Node nodeItem = childNodes.item(i7);
            if (nodeItem.getNodeType() == 1) {
                element.appendChild(element.getOwnerDocument().importNode(nodeItem, true));
            }
        }
    }

    public String generate(DIDLContent dIDLContent, boolean z6) {
        return documentToString(buildDOM(dIDLContent, z6), true);
    }
}
