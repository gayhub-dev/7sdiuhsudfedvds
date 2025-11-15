package org.fourthline.cling.model;

/* loaded from: classes.dex */
public class ValidationError {
    private Class clazz;
    private String message;
    private String propertyName;

    public ValidationError(Class cls, String str) {
        this.clazz = cls;
        this.message = str;
    }

    public Class getClazz() {
        return this.clazz;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public String toString() {
        return getClass().getSimpleName() + " (Class: " + getClazz().getSimpleName() + ", propertyName: " + getPropertyName() + "): " + this.message;
    }

    public ValidationError(Class cls, String str, String str2) {
        this.clazz = cls;
        this.propertyName = str;
        this.message = str2;
    }
}
