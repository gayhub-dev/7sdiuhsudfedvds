package org.fourthline.cling.registry;

import org.fourthline.cling.model.ExpirationDetails;
import p009b.C0413b;

/* loaded from: classes.dex */
class RegistryItem<K, I> {
    private ExpirationDetails expirationDetails;
    private I item;
    private K key;

    public RegistryItem(K k7) {
        this.expirationDetails = new ExpirationDetails();
        this.key = k7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.key.equals(((RegistryItem) obj).key);
    }

    public ExpirationDetails getExpirationDetails() {
        return this.expirationDetails;
    }

    public I getItem() {
        return this.item;
    }

    public K getKey() {
        return this.key;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") ");
        sbM112a.append(getExpirationDetails());
        sbM112a.append(" KEY: ");
        sbM112a.append(getKey());
        sbM112a.append(" ITEM: ");
        sbM112a.append(getItem());
        return sbM112a.toString();
    }

    public RegistryItem(K k7, I i7, int i8) {
        this.expirationDetails = new ExpirationDetails();
        this.key = k7;
        this.item = i7;
        this.expirationDetails = new ExpirationDetails(i8);
    }
}
