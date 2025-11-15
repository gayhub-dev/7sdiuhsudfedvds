package org.fourthline.cling.support.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/* loaded from: classes.dex */
public abstract class DIDLObject {
    public Class clazz;
    public String creator;
    public List<DescMeta> descMetadata;

    /* renamed from: id */
    public String f4896id;
    public String parentID;
    public List<Property> properties;
    public List<Res> resources;
    public boolean restricted;
    public String title;
    public WriteStatus writeStatus;

    public static class Class {
        public String friendlyName;
        public boolean includeDerived;
        public String value;

        public Class() {
        }

        public boolean equals(DIDLObject dIDLObject) {
            return getValue().equals(dIDLObject.getClazz().getValue());
        }

        public String getFriendlyName() {
            return this.friendlyName;
        }

        public String getValue() {
            return this.value;
        }

        public boolean isIncludeDerived() {
            return this.includeDerived;
        }

        public void setFriendlyName(String str) {
            this.friendlyName = str;
        }

        public void setIncludeDerived(boolean z6) {
            this.includeDerived = z6;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public Class(String str) {
            this.value = str;
        }

        public Class(String str, String str2) {
            this.value = str;
            this.friendlyName = str2;
        }

        public Class(String str, String str2, boolean z6) {
            this.value = str;
            this.friendlyName = str2;
            this.includeDerived = z6;
        }
    }

    public static abstract class Property<V> {
        private final List<Property<DIDLAttribute>> attributes;
        private final String descriptorName;
        private V value;

        /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC */
        public static class C1707DC {

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$CONTRIBUTOR */
            public static class CONTRIBUTOR extends Property<Person> implements NAMESPACE {
                public CONTRIBUTOR() {
                }

                public CONTRIBUTOR(Person person) {
                    super(person, null);
                }
            }

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$DATE */
            public static class DATE extends Property<String> implements NAMESPACE {
                public DATE() {
                }

                public DATE(String str) {
                    super(str, null);
                }
            }

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$DESCRIPTION */
            public static class DESCRIPTION extends Property<String> implements NAMESPACE {
                public DESCRIPTION() {
                }

                public DESCRIPTION(String str) {
                    super(str, null);
                }
            }

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$LANGUAGE */
            public static class LANGUAGE extends Property<String> implements NAMESPACE {
                public LANGUAGE() {
                }

                public LANGUAGE(String str) {
                    super(str, null);
                }
            }

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$NAMESPACE */
            public interface NAMESPACE extends NAMESPACE {
                public static final String URI = "http://purl.org/dc/elements/1.1/";
            }

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$PUBLISHER */
            public static class PUBLISHER extends Property<Person> implements NAMESPACE {
                public PUBLISHER() {
                }

                public PUBLISHER(Person person) {
                    super(person, null);
                }
            }

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$RELATION */
            public static class RELATION extends Property<URI> implements NAMESPACE {
                public RELATION() {
                }

                public RELATION(URI uri) {
                    super(uri, null);
                }
            }

            /* renamed from: org.fourthline.cling.support.model.DIDLObject$Property$DC$RIGHTS */
            public static class RIGHTS extends Property<String> implements NAMESPACE {
                public RIGHTS() {
                }

                public RIGHTS(String str) {
                    super(str, null);
                }
            }
        }

        public static abstract class DLNA {

            public interface NAMESPACE extends NAMESPACE {
                public static final String URI = "urn:schemas-dlna-org:metadata-1-0/";
            }

            public static class PROFILE_ID extends Property<DIDLAttribute> implements NAMESPACE {
                public PROFILE_ID() {
                    this(null);
                }

                public PROFILE_ID(DIDLAttribute dIDLAttribute) {
                    super(dIDLAttribute, "profileID");
                }
            }
        }

        public interface NAMESPACE {
        }

        public static class PropertyPersonWithRole extends Property<PersonWithRole> {
            public PropertyPersonWithRole() {
            }

            @Override // org.fourthline.cling.support.model.DIDLObject.Property
            public void setOnElement(Element element) throws DOMException {
                if (getValue() != null) {
                    getValue().setOnElement(element);
                }
            }

            public PropertyPersonWithRole(String str) {
                super(str);
            }

            public PropertyPersonWithRole(PersonWithRole personWithRole, String str) {
                super(personWithRole, str);
            }
        }

        public static abstract class SEC {

            public static class CAPTIONINFO extends Property<URI> implements NAMESPACE {
                public CAPTIONINFO() {
                    this(null);
                }

                public CAPTIONINFO(URI uri) {
                    super(uri, "CaptionInfo");
                }

                public CAPTIONINFO(URI uri, List<Property<DIDLAttribute>> list) {
                    super(uri, "CaptionInfo", list);
                }
            }

            public static class CAPTIONINFOEX extends Property<URI> implements NAMESPACE {
                public CAPTIONINFOEX() {
                    this(null);
                }

                public CAPTIONINFOEX(URI uri) {
                    super(uri, "CaptionInfoEx");
                }

                public CAPTIONINFOEX(URI uri, List<Property<DIDLAttribute>> list) {
                    super(uri, "CaptionInfoEx", list);
                }
            }

            public interface NAMESPACE extends NAMESPACE {
                public static final String URI = "http://www.sec.co.kr/";
            }

            public static class TYPE extends Property<DIDLAttribute> implements NAMESPACE {
                public TYPE() {
                    this(null);
                }

                public TYPE(DIDLAttribute dIDLAttribute) {
                    super(dIDLAttribute, "type");
                }
            }
        }

        public static abstract class UPNP {

            public static class ACTOR extends PropertyPersonWithRole implements NAMESPACE {
                public ACTOR() {
                }

                public ACTOR(PersonWithRole personWithRole) {
                    super(personWithRole, null);
                }
            }

            public static class ALBUM extends Property<String> implements NAMESPACE {
                public ALBUM() {
                }

                public ALBUM(String str) {
                    super(str, null);
                }
            }

            public static class ALBUM_ART_URI extends Property<URI> implements NAMESPACE {
                public ALBUM_ART_URI() {
                    this(null);
                }

                public ALBUM_ART_URI(URI uri) {
                    super(uri, "albumArtURI");
                }

                public ALBUM_ART_URI(URI uri, List<Property<DIDLAttribute>> list) {
                    super(uri, "albumArtURI", list);
                }
            }

            public static class ARTIST extends PropertyPersonWithRole implements NAMESPACE {
                public ARTIST() {
                }

                public ARTIST(PersonWithRole personWithRole) {
                    super(personWithRole, null);
                }
            }

            public static class ARTIST_DISCO_URI extends Property<URI> implements NAMESPACE {
                public ARTIST_DISCO_URI() {
                    this(null);
                }

                public ARTIST_DISCO_URI(URI uri) {
                    super(uri, "artistDiscographyURI");
                }
            }

            public static class AUTHOR extends PropertyPersonWithRole implements NAMESPACE {
                public AUTHOR() {
                }

                public AUTHOR(PersonWithRole personWithRole) {
                    super(personWithRole, null);
                }
            }

            public static class CHANNEL_NAME extends Property<String> implements NAMESPACE {
                public CHANNEL_NAME() {
                    this(null);
                }

                public CHANNEL_NAME(String str) {
                    super(str, "channelName");
                }
            }

            public static class CHANNEL_NR extends Property<Integer> implements NAMESPACE {
                public CHANNEL_NR() {
                    this(null);
                }

                public CHANNEL_NR(Integer num) {
                    super(num, "channelNr");
                }
            }

            public static class DIRECTOR extends Property<Person> implements NAMESPACE {
                public DIRECTOR() {
                }

                public DIRECTOR(Person person) {
                    super(person, null);
                }
            }

            public static class DVD_REGION_CODE extends Property<Integer> implements NAMESPACE {
                public DVD_REGION_CODE() {
                    this(null);
                }

                public DVD_REGION_CODE(Integer num) {
                    super(num, "DVDRegionCode");
                }
            }

            public static class GENRE extends Property<String> implements NAMESPACE {
                public GENRE() {
                }

                public GENRE(String str) {
                    super(str, null);
                }
            }

            public static class ICON extends Property<URI> implements NAMESPACE {
                public ICON() {
                    this(null);
                }

                public ICON(URI uri) {
                    super(uri, "icon");
                }
            }

            public static class LONG_DESCRIPTION extends Property<String> implements NAMESPACE {
                public LONG_DESCRIPTION() {
                    this(null);
                }

                public LONG_DESCRIPTION(String str) {
                    super(str, "longDescription");
                }
            }

            public static class LYRICS_URI extends Property<URI> implements NAMESPACE {
                public LYRICS_URI() {
                    this(null);
                }

                public LYRICS_URI(URI uri) {
                    super(uri, "lyricsURI");
                }
            }

            public interface NAMESPACE extends NAMESPACE {
                public static final String URI = "urn:schemas-upnp-org:metadata-1-0/upnp/";
            }

            public static class ORIGINAL_TRACK_NUMBER extends Property<Integer> implements NAMESPACE {
                public ORIGINAL_TRACK_NUMBER() {
                    this(null);
                }

                public ORIGINAL_TRACK_NUMBER(Integer num) {
                    super(num, "originalTrackNumber");
                }
            }

            public static class PLAYLIST extends Property<String> implements NAMESPACE {
                public PLAYLIST() {
                }

                public PLAYLIST(String str) {
                    super(str, null);
                }
            }

            public static class PRODUCER extends Property<Person> implements NAMESPACE {
                public PRODUCER() {
                }

                public PRODUCER(Person person) {
                    super(person, null);
                }
            }

            public static class RADIO_BAND extends Property<String> implements NAMESPACE {
                public RADIO_BAND() {
                    this(null);
                }

                public RADIO_BAND(String str) {
                    super(str, "radioBand");
                }
            }

            public static class RADIO_CALL_SIGN extends Property<String> implements NAMESPACE {
                public RADIO_CALL_SIGN() {
                    this(null);
                }

                public RADIO_CALL_SIGN(String str) {
                    super(str, "radioCallSign");
                }
            }

            public static class RADIO_STATION_ID extends Property<String> implements NAMESPACE {
                public RADIO_STATION_ID() {
                    this(null);
                }

                public RADIO_STATION_ID(String str) {
                    super(str, "radioStationID");
                }
            }

            public static class RATING extends Property<String> implements NAMESPACE {
                public RATING() {
                }

                public RATING(String str) {
                    super(str, null);
                }
            }

            public static class REGION extends Property<String> implements NAMESPACE {
                public REGION() {
                }

                public REGION(String str) {
                    super(str, null);
                }
            }

            public static class SCHEDULED_END_TIME extends Property<String> implements NAMESPACE {
                public SCHEDULED_END_TIME() {
                    this(null);
                }

                public SCHEDULED_END_TIME(String str) {
                    super(str, "scheduledEndTime");
                }
            }

            public static class SCHEDULED_START_TIME extends Property<String> implements NAMESPACE {
                public SCHEDULED_START_TIME() {
                    this(null);
                }

                public SCHEDULED_START_TIME(String str) {
                    super(str, "scheduledStartTime");
                }
            }

            public static class STORAGE_FREE extends Property<Long> implements NAMESPACE {
                public STORAGE_FREE() {
                    this(null);
                }

                public STORAGE_FREE(Long l7) {
                    super(l7, "storageFree");
                }
            }

            public static class STORAGE_MAX_PARTITION extends Property<Long> implements NAMESPACE {
                public STORAGE_MAX_PARTITION() {
                    this(null);
                }

                public STORAGE_MAX_PARTITION(Long l7) {
                    super(l7, "storageMaxPartition");
                }
            }

            public static class STORAGE_MEDIUM extends Property<StorageMedium> implements NAMESPACE {
                public STORAGE_MEDIUM() {
                    this(null);
                }

                public STORAGE_MEDIUM(StorageMedium storageMedium) {
                    super(storageMedium, "storageMedium");
                }
            }

            public static class STORAGE_TOTAL extends Property<Long> implements NAMESPACE {
                public STORAGE_TOTAL() {
                    this(null);
                }

                public STORAGE_TOTAL(Long l7) {
                    super(l7, "storageTotal");
                }
            }

            public static class STORAGE_USED extends Property<Long> implements NAMESPACE {
                public STORAGE_USED() {
                    this(null);
                }

                public STORAGE_USED(Long l7) {
                    super(l7, "storageUsed");
                }
            }

            public static class TOC extends Property<String> implements NAMESPACE {
                public TOC() {
                }

                public TOC(String str) {
                    super(str, null);
                }
            }

            public static class USER_ANNOTATION extends Property<String> implements NAMESPACE {
                public USER_ANNOTATION() {
                    this(null);
                }

                public USER_ANNOTATION(String str) {
                    super(str, "userAnnotation");
                }
            }
        }

        public Property() {
            this(null, null);
        }

        public void addAttribute(Property<DIDLAttribute> property) {
            this.attributes.add(property);
        }

        public Property<DIDLAttribute> getAttribute(String str) {
            for (Property<DIDLAttribute> property : this.attributes) {
                if (property.getDescriptorName().equals(str)) {
                    return property;
                }
            }
            return null;
        }

        public String getDescriptorName() {
            return this.descriptorName;
        }

        public V getValue() {
            return this.value;
        }

        public void removeAttribute(Property<DIDLAttribute> property) {
            this.attributes.remove(property);
        }

        public void setOnElement(Element element) throws DOMException {
            element.setTextContent(toString());
            for (Property<DIDLAttribute> property : this.attributes) {
                element.setAttributeNS(property.getValue().getNamespaceURI(), property.getValue().getPrefix() + ':' + property.getDescriptorName(), property.getValue().getValue());
            }
        }

        public void setValue(V v6) {
            this.value = v6;
        }

        public String toString() {
            return getValue() != null ? getValue().toString() : "";
        }

        public Property(String str) {
            this(null, str);
        }

        public void removeAttribute(String str) {
            for (Property<DIDLAttribute> property : this.attributes) {
                if (property.getDescriptorName().equals(str)) {
                    removeAttribute(property);
                    return;
                }
            }
        }

        public Property(V v6, String str) {
            this.attributes = new ArrayList();
            this.value = v6;
            this.descriptorName = str == null ? getClass().getSimpleName().toLowerCase(Locale.ROOT).replace("didlobject$property$upnp$", "") : str;
        }

        public Property(V v6, String str, List<Property<DIDLAttribute>> list) {
            ArrayList arrayList = new ArrayList();
            this.attributes = arrayList;
            this.value = v6;
            this.descriptorName = str == null ? getClass().getSimpleName().toLowerCase(Locale.ROOT).replace("didlobject$property$upnp$", "") : str;
            arrayList.addAll(list);
        }
    }

    public DIDLObject() {
        this.restricted = true;
        this.resources = new ArrayList();
        this.properties = new ArrayList();
        this.descMetadata = new ArrayList();
    }

    public DIDLObject addDescMetadata(DescMeta descMeta) {
        getDescMetadata().add(descMeta);
        return this;
    }

    public DIDLObject addProperties(Property[] propertyArr) {
        if (propertyArr == null) {
            return this;
        }
        for (Property property : propertyArr) {
            addProperty(property);
        }
        return this;
    }

    public DIDLObject addProperty(Property property) {
        if (property == null) {
            return this;
        }
        getProperties().add(property);
        return this;
    }

    public DIDLObject addResource(Res res) {
        getResources().add(res);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f4896id.equals(((DIDLObject) obj).f4896id);
    }

    public Class getClazz() {
        return this.clazz;
    }

    public String getCreator() {
        return this.creator;
    }

    public List<DescMeta> getDescMetadata() {
        return this.descMetadata;
    }

    public <V> Property<V> getFirstProperty(java.lang.Class<? extends Property<V>> cls) {
        for (Property<V> property : getProperties()) {
            if (cls.isInstance(property)) {
                return property;
            }
        }
        return null;
    }

    public <V> V getFirstPropertyValue(java.lang.Class<? extends Property<V>> cls) {
        Property<V> firstProperty = getFirstProperty(cls);
        if (firstProperty == null) {
            return null;
        }
        return firstProperty.getValue();
    }

    public Res getFirstResource() {
        if (getResources().size() > 0) {
            return getResources().get(0);
        }
        return null;
    }

    public String getId() {
        return this.f4896id;
    }

    public <V> Property<V> getLastProperty(java.lang.Class<? extends Property<V>> cls) {
        Property<V> property = null;
        for (Property<V> property2 : getProperties()) {
            if (cls.isInstance(property2)) {
                property = property2;
            }
        }
        return property;
    }

    public String getParentID() {
        return this.parentID;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public <V> Property<V>[] getPropertiesByNamespace(java.lang.Class<? extends Property.NAMESPACE> cls) {
        ArrayList arrayList = new ArrayList();
        for (Property property : getProperties()) {
            if (cls.isInstance(property)) {
                arrayList.add(property);
            }
        }
        return (Property[]) arrayList.toArray(new Property[arrayList.size()]);
    }

    public <V> List<V> getPropertyValues(java.lang.Class<? extends Property<V>> cls) {
        ArrayList arrayList = new ArrayList();
        for (Property<V> property : getProperties(cls)) {
            arrayList.add(property.getValue());
        }
        return arrayList;
    }

    public List<Res> getResources() {
        return this.resources;
    }

    public String getTitle() {
        return this.title;
    }

    public WriteStatus getWriteStatus() {
        return this.writeStatus;
    }

    public boolean hasProperty(java.lang.Class<? extends Property> cls) {
        Iterator<Property> it = getProperties().iterator();
        while (it.hasNext()) {
            if (cls.isInstance(it.next())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.f4896id.hashCode();
    }

    public boolean isRestricted() {
        return this.restricted;
    }

    public DIDLObject removeProperties(java.lang.Class<? extends Property> cls) {
        Iterator<Property> it = getProperties().iterator();
        while (it.hasNext()) {
            if (cls.isInstance(it.next())) {
                it.remove();
            }
        }
        return this;
    }

    public DIDLObject replaceFirstProperty(Property property) {
        if (property == null) {
            return this;
        }
        Iterator<Property> it = getProperties().iterator();
        while (it.hasNext()) {
            if (it.next().getClass().isAssignableFrom(property.getClass())) {
                it.remove();
            }
        }
        addProperty(property);
        return this;
    }

    public DIDLObject replaceProperties(java.lang.Class<? extends Property> cls, Property[] propertyArr) {
        if (propertyArr.length == 0) {
            return this;
        }
        removeProperties(cls);
        return addProperties(propertyArr);
    }

    public DIDLObject setClazz(Class r12) {
        this.clazz = r12;
        return this;
    }

    public DIDLObject setCreator(String str) {
        this.creator = str;
        return this;
    }

    public void setDescMetadata(List<DescMeta> list) {
        this.descMetadata = list;
    }

    public DIDLObject setId(String str) {
        this.f4896id = str;
        return this;
    }

    public DIDLObject setParentID(String str) {
        this.parentID = str;
        return this;
    }

    public DIDLObject setProperties(List<Property> list) {
        this.properties = list;
        return this;
    }

    public DIDLObject setResources(List<Res> list) {
        this.resources = list;
        return this;
    }

    public DIDLObject setRestricted(boolean z6) {
        this.restricted = z6;
        return this;
    }

    public DIDLObject setTitle(String str) {
        this.title = str;
        return this;
    }

    public DIDLObject setWriteStatus(WriteStatus writeStatus) {
        this.writeStatus = writeStatus;
        return this;
    }

    public <V> Property<V>[] getProperties(java.lang.Class<? extends Property<V>> cls) {
        ArrayList arrayList = new ArrayList();
        for (Property property : getProperties()) {
            if (cls.isInstance(property)) {
                arrayList.add(property);
            }
        }
        return (Property[]) arrayList.toArray(new Property[arrayList.size()]);
    }

    public DIDLObject(DIDLObject dIDLObject) {
        this(dIDLObject.getId(), dIDLObject.getParentID(), dIDLObject.getTitle(), dIDLObject.getCreator(), dIDLObject.isRestricted(), dIDLObject.getWriteStatus(), dIDLObject.getClazz(), dIDLObject.getResources(), dIDLObject.getProperties(), dIDLObject.getDescMetadata());
    }

    public DIDLObject(String str, String str2, String str3, String str4, boolean z6, WriteStatus writeStatus, Class r8, List<Res> list, List<Property> list2, List<DescMeta> list3) {
        this.restricted = true;
        this.resources = new ArrayList();
        this.properties = new ArrayList();
        this.descMetadata = new ArrayList();
        this.f4896id = str;
        this.parentID = str2;
        this.title = str3;
        this.creator = str4;
        this.restricted = z6;
        this.writeStatus = writeStatus;
        this.clazz = r8;
        this.resources = list;
        this.properties = list2;
        this.descMetadata = list3;
    }
}
