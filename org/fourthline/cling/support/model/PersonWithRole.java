package org.fourthline.cling.support.model;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/* loaded from: classes.dex */
public class PersonWithRole extends Person {
    private String role;

    public PersonWithRole(String str) {
        super(str);
    }

    public String getRole() {
        return this.role;
    }

    public void setOnElement(Element element) throws DOMException {
        element.setTextContent(toString());
        if (getRole() != null) {
            element.setAttribute("role", getRole());
        }
    }

    public PersonWithRole(String str, String str2) {
        super(str);
        this.role = str2;
    }
}
