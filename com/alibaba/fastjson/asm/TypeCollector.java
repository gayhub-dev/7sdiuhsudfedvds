package com.alibaba.fastjson.asm;

import android.arch.lifecycle.C0063n;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.ASMUtils;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import p009b.C0413b;

/* loaded from: classes.dex */
public class TypeCollector {
    private static String JSONType = ASMUtils.desc((Class<?>) JSONType.class);
    private static final Map<String, String> primitives = new HashMap<String, String>() { // from class: com.alibaba.fastjson.asm.TypeCollector.1
        {
            put("int", "I");
            put("boolean", "Z");
            put("byte", "B");
            put("char", "C");
            put("short", "S");
            put("float", "F");
            put("long", "J");
            put("double", "D");
        }
    };
    public MethodCollector collector = null;
    public boolean jsonType;
    private final String methodName;
    private final Class<?>[] parameterTypes;

    public TypeCollector(String str, Class<?>[] clsArr) {
        this.methodName = str;
        this.parameterTypes = clsArr;
    }

    private boolean correctTypeName(Type type, String str) {
        String className = type.getClassName();
        String strM88a = "";
        while (className.endsWith(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            strM88a = C0063n.m88a(strM88a, "[");
            className = className.substring(0, className.length() - 2);
        }
        if (!strM88a.equals("")) {
            Map<String, String> map = primitives;
            if (map.containsKey(className)) {
                StringBuilder sbM112a = C0413b.m112a(strM88a);
                sbM112a.append(map.get(className));
                className = sbM112a.toString();
            } else {
                className = strM88a + "L" + className + ";";
            }
        }
        return className.equals(str);
    }

    public String[] getParameterNamesForMethod() {
        MethodCollector methodCollector = this.collector;
        return (methodCollector == null || !methodCollector.debugInfoPresent) ? new String[0] : methodCollector.getResult().split(",");
    }

    public boolean hasJsonType() {
        return this.jsonType;
    }

    public boolean matched() {
        return this.collector != null;
    }

    public void visitAnnotation(String str) {
        if (JSONType.equals(str)) {
            this.jsonType = true;
        }
    }

    public MethodCollector visitMethod(int i7, String str, String str2) {
        if (this.collector != null || !str.equals(this.methodName)) {
            return null;
        }
        Type[] argumentTypes = Type.getArgumentTypes(str2);
        int i8 = 0;
        for (Type type : argumentTypes) {
            String className = type.getClassName();
            if (className.equals("long") || className.equals("double")) {
                i8++;
            }
        }
        if (argumentTypes.length != this.parameterTypes.length) {
            return null;
        }
        for (int i9 = 0; i9 < argumentTypes.length; i9++) {
            if (!correctTypeName(argumentTypes[i9], this.parameterTypes[i9].getName())) {
                return null;
            }
        }
        MethodCollector methodCollector = new MethodCollector(!Modifier.isStatic(i7) ? 1 : 0, argumentTypes.length + i8);
        this.collector = methodCollector;
        return methodCollector;
    }
}
