package com.alibaba.fastjson.serializer;

/* loaded from: classes.dex */
public class SerialContext {
    public final int features;
    public final Object fieldName;
    public final Object object;
    public final SerialContext parent;

    public SerialContext(SerialContext serialContext, Object obj, Object obj2, int i7, int i8) {
        this.parent = serialContext;
        this.object = obj;
        this.fieldName = obj2;
        this.features = i7;
    }

    public Object getFieldName() {
        return this.fieldName;
    }

    public Object getObject() {
        return this.object;
    }

    public SerialContext getParent() {
        return this.parent;
    }

    public String getPath() {
        return toString();
    }

    public String toString() {
        if (this.parent == null) {
            return "$";
        }
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    public void toString(StringBuilder sb) {
        boolean z6;
        SerialContext serialContext = this.parent;
        if (serialContext == null) {
            sb.append('$');
            return;
        }
        serialContext.toString(sb);
        Object obj = this.fieldName;
        if (obj == null) {
            sb.append(".null");
            return;
        }
        if (obj instanceof Integer) {
            sb.append('[');
            sb.append(((Integer) this.fieldName).intValue());
            sb.append(']');
            return;
        }
        sb.append('.');
        String string = this.fieldName.toString();
        int i7 = 0;
        while (true) {
            if (i7 >= string.length()) {
                z6 = false;
                break;
            }
            char cCharAt = string.charAt(i7);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && cCharAt <= 128))) {
                z6 = true;
                break;
            }
            i7++;
        }
        if (z6) {
            for (int i8 = 0; i8 < string.length(); i8++) {
                char cCharAt2 = string.charAt(i8);
                if (cCharAt2 == '\\') {
                    sb.append('\\');
                    sb.append('\\');
                    sb.append('\\');
                } else if ((cCharAt2 >= '0' && cCharAt2 <= '9') || ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || ((cCharAt2 >= 'a' && cCharAt2 <= 'z') || cCharAt2 > 128))) {
                    sb.append(cCharAt2);
                } else {
                    sb.append('\\');
                    sb.append('\\');
                }
                sb.append(cCharAt2);
            }
            return;
        }
        sb.append(string);
    }
}
