package org.fourthline.cling.support.model.container;

import org.fourthline.cling.support.model.DIDLObject;
import org.fourthline.cling.support.model.StorageMedium;

/* loaded from: classes.dex */
public class StorageSystem extends Container {
    public static final DIDLObject.Class CLASS = new DIDLObject.Class("object.container.storageSystem");

    public StorageSystem() {
        setClazz(CLASS);
    }

    public Long getStorageFree() {
        return (Long) getFirstPropertyValue(DIDLObject.Property.UPNP.STORAGE_FREE.class);
    }

    public Long getStorageMaxPartition() {
        return (Long) getFirstPropertyValue(DIDLObject.Property.UPNP.STORAGE_MAX_PARTITION.class);
    }

    public StorageMedium getStorageMedium() {
        return (StorageMedium) getFirstPropertyValue(DIDLObject.Property.UPNP.STORAGE_MEDIUM.class);
    }

    public Long getStorageTotal() {
        return (Long) getFirstPropertyValue(DIDLObject.Property.UPNP.STORAGE_TOTAL.class);
    }

    public Long getStorageUsed() {
        return (Long) getFirstPropertyValue(DIDLObject.Property.UPNP.STORAGE_USED.class);
    }

    public StorageSystem setStorageFree(Long l7) {
        replaceFirstProperty(new DIDLObject.Property.UPNP.STORAGE_FREE(l7));
        return this;
    }

    public StorageSystem setStorageMaxPartition(Long l7) {
        replaceFirstProperty(new DIDLObject.Property.UPNP.STORAGE_MAX_PARTITION(l7));
        return this;
    }

    public StorageSystem setStorageMedium(StorageMedium storageMedium) {
        replaceFirstProperty(new DIDLObject.Property.UPNP.STORAGE_MEDIUM(storageMedium));
        return this;
    }

    public StorageSystem setStorageTotal(Long l7) {
        replaceFirstProperty(new DIDLObject.Property.UPNP.STORAGE_TOTAL(l7));
        return this;
    }

    public StorageSystem setStorageUsed(Long l7) {
        replaceFirstProperty(new DIDLObject.Property.UPNP.STORAGE_USED(l7));
        return this;
    }

    public StorageSystem(Container container) {
        super(container);
    }

    public StorageSystem(String str, Container container, String str2, String str3, Integer num, Long l7, Long l8, Long l9, Long l10, StorageMedium storageMedium) {
        this(str, container.getId(), str2, str3, num, l7, l8, l9, l10, storageMedium);
    }

    public StorageSystem(String str, String str2, String str3, String str4, Integer num, Long l7, Long l8, Long l9, Long l10, StorageMedium storageMedium) {
        super(str, str2, str3, str4, CLASS, num);
        if (l7 != null) {
            setStorageTotal(l7);
        }
        if (l8 != null) {
            setStorageUsed(l8);
        }
        if (l9 != null) {
            setStorageFree(l9);
        }
        if (l10 != null) {
            setStorageMaxPartition(l10);
        }
        if (storageMedium != null) {
            setStorageMedium(storageMedium);
        }
    }
}
