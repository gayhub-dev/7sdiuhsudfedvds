package org.fourthline.cling.support.model.container;

import org.fourthline.cling.support.model.DIDLObject;

/* loaded from: classes.dex */
public class StorageFolder extends Container {
    public static final DIDLObject.Class CLASS = new DIDLObject.Class("object.container.storageFolder");

    public StorageFolder() {
        setClazz(CLASS);
    }

    public Long getStorageUsed() {
        return (Long) getFirstPropertyValue(DIDLObject.Property.UPNP.STORAGE_USED.class);
    }

    public StorageFolder setStorageUsed(Long l7) {
        replaceFirstProperty(new DIDLObject.Property.UPNP.STORAGE_USED(l7));
        return this;
    }

    public StorageFolder(Container container) {
        super(container);
    }

    public StorageFolder(String str, Container container, String str2, String str3, Integer num, Long l7) {
        this(str, container.getId(), str2, str3, num, l7);
    }

    public StorageFolder(String str, String str2, String str3, String str4, Integer num, Long l7) {
        super(str, str2, str3, str4, CLASS, num);
        if (l7 != null) {
            setStorageUsed(l7);
        }
    }
}
