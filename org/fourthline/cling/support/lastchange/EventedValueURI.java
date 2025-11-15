package org.fourthline.cling.support.lastchange;

import android.support.constraint.motion.C0081c;
import java.net.URI;
import java.util.Map;
import java.util.logging.Logger;
import org.fourthline.cling.model.types.Datatype;
import org.fourthline.cling.model.types.InvalidValueException;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class EventedValueURI extends EventedValue<URI> {
    private static final Logger log = Logger.getLogger(EventedValueURI.class.getName());

    public EventedValueURI(URI uri) {
        super(uri);
    }

    @Override // org.fourthline.cling.support.lastchange.EventedValue
    public Datatype getDatatype() {
        return Datatype.Builtin.URI.getDatatype();
    }

    public EventedValueURI(Map.Entry<String, String>[] entryArr) {
        super(entryArr);
    }

    @Override // org.fourthline.cling.support.lastchange.EventedValue
    public URI valueOf(String str) {
        try {
            return (URI) super.valueOf(str);
        } catch (InvalidValueException e7) {
            Logger logger = log;
            StringBuilder sbM95a = C0081c.m95a("Ignoring invalid URI in evented value '", str, "': ");
            sbM95a.append(C2074b.m2475O(e7));
            logger.info(sbM95a.toString());
            return null;
        }
    }
}
