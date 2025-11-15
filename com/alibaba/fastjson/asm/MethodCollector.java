package com.alibaba.fastjson.asm;

import p009b.C0413b;

/* loaded from: classes.dex */
public class MethodCollector {
    public boolean debugInfoPresent;
    private final int ignoreCount;
    private final int paramCount;
    private final StringBuilder result = new StringBuilder();
    private int currentParameter = 0;

    public MethodCollector(int i7, int i8) {
        this.ignoreCount = i7;
        this.paramCount = i8;
        this.debugInfoPresent = i8 == 0;
    }

    public String getResult() {
        return this.result.length() != 0 ? this.result.substring(1) : "";
    }

    public void visitLocalVariable(String str, int i7) {
        int i8 = this.ignoreCount;
        if (i7 < i8 || i7 >= i8 + this.paramCount) {
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("arg");
        sbM112a.append(this.currentParameter);
        if (!str.equals(sbM112a.toString())) {
            this.debugInfoPresent = true;
        }
        this.result.append(',');
        this.result.append(str);
        this.currentParameter++;
    }
}
