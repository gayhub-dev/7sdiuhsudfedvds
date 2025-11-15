package org.fourthline.cling.model.types;

import java.net.URI;
import java.net.URISyntaxException;

/* loaded from: classes.dex */
public class URIDatatype extends AbstractDatatype<URI> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public URI valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            return new URI(str);
        } catch (URISyntaxException e7) {
            throw new InvalidValueException(e7.getMessage(), e7);
        }
    }
}
