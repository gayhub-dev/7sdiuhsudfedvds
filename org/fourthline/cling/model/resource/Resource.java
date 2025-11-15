package org.fourthline.cling.model.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.fourthline.cling.model.ExpirationDetails;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Resource<M> {
    private M model;
    private URI pathQuery;

    public Resource(URI uri, M m7) {
        try {
            this.pathQuery = new URI(null, null, uri.getPath(), uri.getQuery(), null);
            this.model = m7;
            if (m7 == null) {
                throw new IllegalArgumentException("Model instance must not be null");
            }
        } catch (URISyntaxException e7) {
            throw new RuntimeException(e7);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && getPathQuery().equals(((Resource) obj).getPathQuery());
    }

    public M getModel() {
        return this.model;
    }

    public URI getPathQuery() {
        return this.pathQuery;
    }

    public int hashCode() {
        return getPathQuery().hashCode();
    }

    public void maintain(List<Runnable> list, ExpirationDetails expirationDetails) {
    }

    public boolean matches(URI uri) {
        return uri.equals(getPathQuery());
    }

    public void shutdown() {
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") URI: ");
        sbM112a.append(getPathQuery());
        return sbM112a.toString();
    }
}
