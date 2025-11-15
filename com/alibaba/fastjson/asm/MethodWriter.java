package com.alibaba.fastjson.asm;

/* loaded from: classes.dex */
public class MethodWriter implements MethodVisitor {
    private int access;
    private ByteVector code = new ByteVector();

    /* renamed from: cw */
    public final ClassWriter f416cw;
    private final int desc;
    public int exceptionCount;
    public int[] exceptions;
    private int maxLocals;
    private int maxStack;
    private final int name;
    public MethodWriter next;

    public MethodWriter(ClassWriter classWriter, int i7, String str, String str2, String str3, String[] strArr) {
        if (classWriter.firstMethod == null) {
            classWriter.firstMethod = this;
        } else {
            classWriter.lastMethod.next = this;
        }
        classWriter.lastMethod = this;
        this.f416cw = classWriter;
        this.access = i7;
        this.name = classWriter.newUTF8(str);
        this.desc = classWriter.newUTF8(str2);
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        int length = strArr.length;
        this.exceptionCount = length;
        this.exceptions = new int[length];
        for (int i8 = 0; i8 < this.exceptionCount; i8++) {
            this.exceptions[i8] = classWriter.newClassItem(strArr[i8]).index;
        }
    }

    public final int getSize() {
        int i7;
        if (this.code.length > 0) {
            this.f416cw.newUTF8("Code");
            i7 = this.code.length + 18 + 0 + 8;
        } else {
            i7 = 8;
        }
        if (this.exceptionCount <= 0) {
            return i7;
        }
        this.f416cw.newUTF8("Exceptions");
        return i7 + (this.exceptionCount * 2) + 8;
    }

    public final void put(ByteVector byteVector) {
        byteVector.putShort(this.access & (-393217)).putShort(this.name).putShort(this.desc);
        int i7 = this.code.length > 0 ? 1 : 0;
        if (this.exceptionCount > 0) {
            i7++;
        }
        byteVector.putShort(i7);
        int i8 = this.code.length;
        if (i8 > 0) {
            byteVector.putShort(this.f416cw.newUTF8("Code")).putInt(i8 + 12 + 0);
            byteVector.putShort(this.maxStack).putShort(this.maxLocals);
            ByteVector byteVectorPutInt = byteVector.putInt(this.code.length);
            ByteVector byteVector2 = this.code;
            byteVectorPutInt.putByteArray(byteVector2.data, 0, byteVector2.length);
            byteVector.putShort(0);
            byteVector.putShort(0);
        }
        if (this.exceptionCount > 0) {
            byteVector.putShort(this.f416cw.newUTF8("Exceptions")).putInt((this.exceptionCount * 2) + 2);
            byteVector.putShort(this.exceptionCount);
            for (int i9 = 0; i9 < this.exceptionCount; i9++) {
                byteVector.putShort(this.exceptions[i9]);
            }
        }
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitEnd() {
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitFieldInsn(int i7, String str, String str2, String str3) {
        this.code.put12(i7, this.f416cw.newFieldItem(str, str2, str3).index);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitIincInsn(int i7, int i8) {
        this.code.putByte(132).put11(i7, i8);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitInsn(int i7) {
        this.code.putByte(i7);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitIntInsn(int i7, int i8) {
        this.code.put11(i7, i8);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitJumpInsn(int i7, Label label) {
        if ((label.status & 2) != 0 && label.position - this.code.length < -32768) {
            throw new UnsupportedOperationException();
        }
        this.code.putByte(i7);
        label.put(this, this.code, r3.length - 1);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitLabel(Label label) {
        ByteVector byteVector = this.code;
        label.resolve(this, byteVector.length, byteVector.data);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        Item itemNewConstItem = this.f416cw.newConstItem(obj);
        int i7 = itemNewConstItem.index;
        int i8 = itemNewConstItem.type;
        if (i8 == 5 || i8 == 6) {
            this.code.put12(20, i7);
        } else if (i7 >= 256) {
            this.code.put12(19, i7);
        } else {
            this.code.put11(18, i7);
        }
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitMaxs(int i7, int i8) {
        this.maxStack = i7;
        this.maxLocals = i8;
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitMethodInsn(int i7, String str, String str2, String str3) {
        boolean z6 = i7 == 185;
        Item itemNewMethodItem = this.f416cw.newMethodItem(str, str2, str3, z6);
        int argumentsAndReturnSizes = itemNewMethodItem.intVal;
        if (!z6) {
            this.code.put12(i7, itemNewMethodItem.index);
            return;
        }
        if (argumentsAndReturnSizes == 0) {
            argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
            itemNewMethodItem.intVal = argumentsAndReturnSizes;
        }
        this.code.put12(Opcodes.INVOKEINTERFACE, itemNewMethodItem.index).put11(argumentsAndReturnSizes >> 2, 0);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitTypeInsn(int i7, String str) {
        this.code.put12(i7, this.f416cw.newClassItem(str).index);
    }

    @Override // com.alibaba.fastjson.asm.MethodVisitor
    public void visitVarInsn(int i7, int i8) {
        if (i8 < 4 && i7 != 169) {
            this.code.putByte((i7 < 54 ? ((i7 - 21) << 2) + 26 : ((i7 - 54) << 2) + 59) + i8);
        } else if (i8 >= 256) {
            this.code.putByte(196).put12(i7, i8);
        } else {
            this.code.put11(i7, i8);
        }
    }
}
