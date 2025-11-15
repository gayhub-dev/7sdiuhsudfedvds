package org.fourthline.cling.model.meta;

import java.net.URI;

/* loaded from: classes.dex */
public class ManufacturerDetails {
    private String manufacturer;
    private URI manufacturerURI;

    public ManufacturerDetails() {
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public URI getManufacturerURI() {
        return this.manufacturerURI;
    }

    public ManufacturerDetails(String str) {
        this.manufacturer = str;
    }

    public ManufacturerDetails(URI uri) {
        this.manufacturerURI = uri;
    }

    public ManufacturerDetails(String str, URI uri) {
        this.manufacturer = str;
        this.manufacturerURI = uri;
    }

    public ManufacturerDetails(String str, String str2) {
        this.manufacturer = str;
        this.manufacturerURI = URI.create(str2);
    }
}
