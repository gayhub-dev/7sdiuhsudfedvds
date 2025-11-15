package org.fourthline.cling.support.model.dlna;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class DLNAAttribute<T> {
    private static final Logger log = Logger.getLogger(DLNAAttribute.class.getName());
    private T value;

    public enum Type {
        DLNA_ORG_PN("DLNA.ORG_PN", DLNAProfileAttribute.class),
        DLNA_ORG_OP("DLNA.ORG_OP", DLNAOperationsAttribute.class),
        DLNA_ORG_PS("DLNA.ORG_PS", DLNAPlaySpeedAttribute.class),
        DLNA_ORG_CI("DLNA.ORG_CI", DLNAConversionIndicatorAttribute.class),
        DLNA_ORG_FLAGS("DLNA.ORG_FLAGS", DLNAFlagsAttribute.class);

        private static Map<String, Type> byName = new HashMap<String, Type>() { // from class: org.fourthline.cling.support.model.dlna.DLNAAttribute.Type.1
            {
                for (Type type : Type.values()) {
                    put(type.getAttributeName().toUpperCase(Locale.ROOT), type);
                }
            }
        };
        private String attributeName;
        private Class<? extends DLNAAttribute>[] attributeTypes;

        @SafeVarargs
        Type(String str, Class... clsArr) {
            this.attributeName = str;
            this.attributeTypes = clsArr;
        }

        public static Type valueOfAttributeName(String str) {
            if (str == null) {
                return null;
            }
            return byName.get(str.toUpperCase(Locale.ROOT));
        }

        public String getAttributeName() {
            return this.attributeName;
        }

        public Class<? extends DLNAAttribute>[] getAttributeTypes() {
            return this.attributeTypes;
        }
    }

    public static DLNAAttribute newInstance(Type type, String str, String str2) throws IllegalAccessException, InstantiationException {
        DLNAAttribute dLNAAttributeNewInstance;
        Exception e7;
        DLNAAttribute dLNAAttribute = null;
        for (int i7 = 0; i7 < type.getAttributeTypes().length && dLNAAttribute == null; i7++) {
            Class<? extends DLNAAttribute> cls = type.getAttributeTypes()[i7];
            try {
                try {
                    log.finest("Trying to parse DLNA '" + type + "' with class: " + cls.getSimpleName());
                    dLNAAttributeNewInstance = cls.newInstance();
                    if (str != null) {
                        try {
                            dLNAAttributeNewInstance.setString(str, str2);
                        } catch (Exception e8) {
                            e7 = e8;
                            Logger logger = log;
                            logger.severe("Error instantiating DLNA attribute of type '" + type + "' with value: " + str);
                            logger.log(Level.SEVERE, "Exception root cause: ", C2074b.m2475O(e7));
                            dLNAAttribute = dLNAAttributeNewInstance;
                        }
                    }
                } catch (Exception e9) {
                    dLNAAttributeNewInstance = dLNAAttribute;
                    e7 = e9;
                }
                dLNAAttribute = dLNAAttributeNewInstance;
            } catch (InvalidDLNAProtocolAttributeException e10) {
                Logger logger2 = log;
                StringBuilder sbM112a = C0413b.m112a("Invalid DLNA attribute value for tested type: ");
                sbM112a.append(cls.getSimpleName());
                sbM112a.append(" - ");
                sbM112a.append(e10.getMessage());
                logger2.finest(sbM112a.toString());
                dLNAAttribute = null;
            }
        }
        return dLNAAttribute;
    }

    public abstract String getString();

    public T getValue() {
        return this.value;
    }

    public abstract void setString(String str, String str2);

    public void setValue(T t6) {
        this.value = t6;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") '");
        sbM112a.append(getValue());
        sbM112a.append("'");
        return sbM112a.toString();
    }
}
