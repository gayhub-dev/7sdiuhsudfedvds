package org.fourthline.cling.binding.xml;

import org.fourthline.cling.model.meta.Service;
import org.w3c.dom.Document;

/* loaded from: classes.dex */
public interface ServiceDescriptorBinder {
    Document buildDOM(Service service);

    <T extends Service> T describe(T t6, String str);

    <T extends Service> T describe(T t6, Document document);

    String generate(Service service);
}
