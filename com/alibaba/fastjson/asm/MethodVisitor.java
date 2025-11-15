package com.alibaba.fastjson.asm;

/* loaded from: classes.dex */
public interface MethodVisitor {
    void visitEnd();

    void visitFieldInsn(int i7, String str, String str2, String str3);

    void visitIincInsn(int i7, int i8);

    void visitInsn(int i7);

    void visitIntInsn(int i7, int i8);

    void visitJumpInsn(int i7, Label label);

    void visitLabel(Label label);

    void visitLdcInsn(Object obj);

    void visitMaxs(int i7, int i8);

    void visitMethodInsn(int i7, String str, String str2, String str3);

    void visitTypeInsn(int i7, String str);

    void visitVarInsn(int i7, int i8);
}
