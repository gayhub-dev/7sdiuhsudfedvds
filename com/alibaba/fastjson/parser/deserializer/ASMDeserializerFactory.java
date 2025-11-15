package com.alibaba.fastjson.parser.deserializer;

import android.support.constraint.C0072a;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.support.v7.view.C0319a;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ASMDeserializerFactory implements Opcodes {
    public static final String DefaultJSONParser = ASMUtils.type(DefaultJSONParser.class);
    public static final String JSONLexerBase = ASMUtils.type(JSONLexerBase.class);
    public final ASMClassLoader classLoader;
    public final AtomicLong seed = new AtomicLong();

    public ASMDeserializerFactory(ClassLoader classLoader) {
        this.classLoader = classLoader instanceof ASMClassLoader ? (ASMClassLoader) classLoader : new ASMClassLoader(classLoader);
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor) {
        _batchSet(context, methodVisitor, true);
    }

    private void _createInstance(Context context, MethodVisitor methodVisitor) {
        Constructor<?> constructor = context.beanInfo.defaultConstructor;
        if (Modifier.isPublic(constructor.getModifiers())) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(context.getInstClass()));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(constructor.getDeclaringClass()), "<init>", "()V");
            methodVisitor.visitVarInsn(58, context.var("instance"));
            return;
        }
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.type(JavaBeanDeserializer.class), "clazz", "Ljava/lang/Class;");
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(JavaBeanDeserializer.class), "createInstance", C0072a.m92a(C0413b.m112a("(L"), DefaultJSONParser, ";Ljava/lang/reflect/Type;)Ljava/lang/Object;"));
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(context.getInstClass()));
        methodVisitor.visitVarInsn(58, context.var("instance"));
    }

    private void _deserObject(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls, int i7) {
        _getFieldDeser(context, methodVisitor, fieldInfo);
        Label label = new Label();
        Label label2 = new Label();
        if ((fieldInfo.parserFeatures & Feature.SupportArrayToBean.mask) != 0) {
            methodVisitor.visitInsn(89);
            methodVisitor.visitTypeInsn(Opcodes.INSTANCEOF, ASMUtils.type(JavaBeanDeserializer.class));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(JavaBeanDeserializer.class));
            methodVisitor.visitVarInsn(25, 1);
            if (fieldInfo.fieldType instanceof Class) {
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
            } else {
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitLdcInsn(Integer.valueOf(i7));
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            }
            methodVisitor.visitLdcInsn(fieldInfo.name);
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.parserFeatures));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "deserialze", C0072a.m92a(C0413b.m112a("(L"), DefaultJSONParser, ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;"));
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
            methodVisitor.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
            methodVisitor.visitLabel(label);
        }
        methodVisitor.visitVarInsn(25, 1);
        if (fieldInfo.fieldType instanceof Class) {
            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitLdcInsn(Integer.valueOf(i7));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
        }
        methodVisitor.visitLdcInsn(fieldInfo.name);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(ObjectDeserializer.class), "deserialze", C0072a.m92a(C0413b.m112a("(L"), DefaultJSONParser, ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;"));
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
        methodVisitor.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        methodVisitor.visitLabel(label2);
    }

    private void _deserialize_endCheck(Context context, MethodVisitor methodVisitor, Label label) {
        methodVisitor.visitIntInsn(21, context.var("matchedCount"));
        methodVisitor.visitJumpInsn(Opcodes.IFLE, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
        methodVisitor.visitLdcInsn(13);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label);
        _quickNextTokenComma(context, methodVisitor);
    }

    /* JADX WARN: Removed duplicated region for block: B:322:0x0c36  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _deserialze(com.alibaba.fastjson.asm.ClassWriter r24, com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory.Context r25) {
        /*
            Method dump skipped, instructions count: 3585
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory._deserialze(com.alibaba.fastjson.asm.ClassWriter, com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory$Context):void");
    }

    private void _deserialzeArrayMapping(ClassWriter classWriter, Context context) {
        int i7;
        int i8;
        int i9;
        MethodWriter methodWriter = new MethodWriter(classWriter, 1, "deserialzeArrayMapping", C0072a.m92a(C0413b.m112a("(L"), DefaultJSONParser, ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), null, null);
        defineVarLexer(context, methodWriter);
        _createInstance(context, methodWriter);
        FieldInfo[] fieldInfoArr = context.beanInfo.sortedFields;
        int length = fieldInfoArr.length;
        int i10 = 0;
        while (i10 < length) {
            boolean z6 = i10 == length + (-1);
            int i11 = z6 ? 93 : 44;
            FieldInfo fieldInfo = fieldInfoArr[i10];
            Class<?> cls = fieldInfo.fieldClass;
            java.lang.reflect.Type type = fieldInfo.fieldType;
            FieldInfo[] fieldInfoArr2 = fieldInfoArr;
            if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE) {
                i7 = length;
                methodWriter.visitVarInsn(25, context.var("lexer"));
                methodWriter.visitVarInsn(16, i11);
                methodWriter.visitVarInsn(54, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanInt", "(C)I"), fieldInfo.name, "_asm")));
            } else {
                i7 = length;
                boolean z7 = z6;
                int i12 = i10;
                if (cls == Byte.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    String str = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "scanInt", "(C)I");
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;"), fieldInfo.name, "_asm")));
                    Label label = new Label();
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitFieldInsn(Opcodes.GETFIELD, str, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                    methodWriter.visitLabel(label);
                } else if (cls == Short.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    String str2 = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str2, "scanInt", "(C)I");
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;"), fieldInfo.name, "_asm")));
                    Label label2 = new Label();
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitFieldInsn(Opcodes.GETFIELD, str2, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label2);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                    methodWriter.visitLabel(label2);
                } else if (cls == Integer.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    String str3 = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, "scanInt", "(C)I");
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;"), fieldInfo.name, "_asm")));
                    Label label3 = new Label();
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitFieldInsn(Opcodes.GETFIELD, str3, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label3);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                    methodWriter.visitLabel(label3);
                } else if (cls == Long.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(55, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanLong", "(C)J"), fieldInfo.name, "_asm"), 2));
                } else if (cls == Long.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    String str4 = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str4, "scanLong", "(C)J");
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;"), fieldInfo.name, "_asm")));
                    Label label4 = new Label();
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitFieldInsn(Opcodes.GETFIELD, str4, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label4);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                    methodWriter.visitLabel(label4);
                } else if (cls == Boolean.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(54, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanBoolean", "(C)Z"), fieldInfo.name, "_asm")));
                } else if (cls == Float.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(56, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanFloat", "(C)F"), fieldInfo.name, "_asm")));
                } else if (cls == Float.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    String str5 = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "scanFloat", "(C)F");
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;"), fieldInfo.name, "_asm")));
                    Label label5 = new Label();
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitFieldInsn(Opcodes.GETFIELD, str5, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label5);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                    methodWriter.visitLabel(label5);
                } else if (cls == Double.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(57, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanDouble", "(C)D"), fieldInfo.name, "_asm"), 2));
                } else if (cls == Double.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    String str6 = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, "scanDouble", "(C)D");
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;"), fieldInfo.name, "_asm")));
                    Label label6 = new Label();
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitFieldInsn(Opcodes.GETFIELD, str6, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label6);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                    methodWriter.visitLabel(label6);
                } else if (cls == Character.TYPE) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanString", "(C)Ljava/lang/String;");
                    methodWriter.visitInsn(3);
                    methodWriter.visitVarInsn(54, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, "java/lang/String", "charAt", "(I)C"), fieldInfo.name, "_asm")));
                } else if (cls == String.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanString", "(C)Ljava/lang/String;"), fieldInfo.name, "_asm")));
                } else if (cls == BigDecimal.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanDecimal", "(C)Ljava/math/BigDecimal;"), fieldInfo.name, "_asm")));
                } else if (cls == Date.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanDate", "(C)Ljava/util/Date;"), fieldInfo.name, "_asm")));
                } else if (cls == UUID.class) {
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(C0533a.m340a(methodWriter, Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanUUID", "(C)Ljava/util/UUID;"), fieldInfo.name, "_asm")));
                } else if (cls.isEnum()) {
                    Label label7 = new Label();
                    Label label8 = new Label();
                    Label label9 = new Label();
                    Label label10 = new Label();
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    String str7 = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str7, "getCurrent", "()C");
                    methodWriter.visitInsn(89);
                    methodWriter.visitVarInsn(54, context.var("ch"));
                    methodWriter.visitLdcInsn(110);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPEQ, label10);
                    methodWriter.visitVarInsn(21, context.var("ch"));
                    methodWriter.visitLdcInsn(34);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label7);
                    methodWriter.visitLabel(label10);
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
                    methodWriter.visitVarInsn(25, 1);
                    String str8 = DefaultJSONParser;
                    StringBuilder sbM112a = C0413b.m112a("()");
                    sbM112a.append(ASMUtils.desc((Class<?>) SymbolTable.class));
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str8, "getSymbolTable", sbM112a.toString());
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str7, "scanEnum", "(Ljava/lang/Class;" + ASMUtils.desc((Class<?>) SymbolTable.class) + "C)Ljava/lang/Enum;");
                    methodWriter.visitJumpInsn(Opcodes.GOTO, label9);
                    methodWriter.visitLabel(label7);
                    methodWriter.visitVarInsn(21, context.var("ch"));
                    methodWriter.visitLdcInsn(48);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPLT, label8);
                    methodWriter.visitVarInsn(21, context.var("ch"));
                    methodWriter.visitLdcInsn(57);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPGT, label8);
                    _getFieldDeser(context, methodWriter, fieldInfo);
                    methodWriter.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(EnumDeserializer.class));
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str7, "scanInt", "(C)I");
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(EnumDeserializer.class), "valueOf", "(I)Ljava/lang/Enum;");
                    methodWriter.visitJumpInsn(Opcodes.GOTO, label9);
                    methodWriter.visitLabel(label8);
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitVarInsn(25, context.var("lexer"));
                    methodWriter.visitVarInsn(16, i11);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "scanEnum", C0096a.m97a("(L", str7, ";C)Ljava/lang/Enum;"));
                    methodWriter.visitLabel(label9);
                    methodWriter.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
                    methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                } else if (Collection.class.isAssignableFrom(cls)) {
                    Class<?> collectionItemClass = TypeUtils.getCollectionItemClass(type);
                    if (collectionItemClass == String.class) {
                        if (cls == List.class || cls == Collections.class || cls == ArrayList.class) {
                            methodWriter.visitTypeInsn(Opcodes.NEW, ASMUtils.type(ArrayList.class));
                            methodWriter.visitInsn(89);
                            methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(ArrayList.class), "<init>", "()V");
                        } else {
                            methodWriter.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
                            methodWriter.visitMethodInsn(Opcodes.INVOKESTATIC, ASMUtils.type(TypeUtils.class), "createCollection", "(Ljava/lang/Class;)Ljava/util/Collection;");
                        }
                        methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                        methodWriter.visitVarInsn(16, i11);
                        String str9 = JSONLexerBase;
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str9, "scanStringArray", "(Ljava/util/Collection;C)V");
                        Label label11 = new Label();
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitFieldInsn(Opcodes.GETFIELD, str9, "matchStat", "I");
                        methodWriter.visitLdcInsn(5);
                        methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label11);
                        methodWriter.visitInsn(1);
                        methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                        methodWriter.visitLabel(label11);
                    } else {
                        Label label12 = new Label();
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        String str10 = JSONLexerBase;
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str10, "token", "()I");
                        methodWriter.visitVarInsn(54, context.var("token"));
                        methodWriter.visitVarInsn(21, context.var("token"));
                        methodWriter.visitLdcInsn(Integer.valueOf(i12 == 0 ? 14 : 16));
                        methodWriter.visitJumpInsn(Opcodes.IF_ICMPEQ, label12);
                        methodWriter.visitVarInsn(25, 1);
                        methodWriter.visitVarInsn(21, context.var("token"));
                        String str11 = DefaultJSONParser;
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str11, "throwException", "(I)V");
                        methodWriter.visitLabel(label12);
                        Label label13 = new Label();
                        Label label14 = new Label();
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str10, "getCurrent", "()C");
                        methodWriter.visitVarInsn(16, 91);
                        methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label13);
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str10, "next", "()C");
                        methodWriter.visitInsn(87);
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitLdcInsn(14);
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str10, "setToken", "(I)V");
                        methodWriter.visitJumpInsn(Opcodes.GOTO, label14);
                        methodWriter.visitLabel(label13);
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitLdcInsn(14);
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str10, "nextToken", "(I)V");
                        methodWriter.visitLabel(label14);
                        i10 = i12;
                        _newCollection(methodWriter, cls, i10, false);
                        methodWriter.visitInsn(89);
                        methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                        _getCollectionFieldItemDeser(context, methodWriter, fieldInfo, collectionItemClass);
                        methodWriter.visitVarInsn(25, 1);
                        methodWriter.visitLdcInsn(Type.getType(ASMUtils.desc(collectionItemClass)));
                        methodWriter.visitVarInsn(25, 3);
                        String strType = ASMUtils.type(JavaBeanDeserializer.class);
                        StringBuilder sbM112a2 = C0413b.m112a("(Ljava/util/Collection;");
                        sbM112a2.append(ASMUtils.desc((Class<?>) ObjectDeserializer.class));
                        sbM112a2.append("L");
                        sbM112a2.append(str11);
                        sbM112a2.append(";Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
                        methodWriter.visitMethodInsn(Opcodes.INVOKESTATIC, strType, "parseArray", sbM112a2.toString());
                    }
                } else {
                    i10 = i12;
                    if (cls.isArray()) {
                        methodWriter.visitVarInsn(25, context.var("lexer"));
                        methodWriter.visitLdcInsn(14);
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", "(I)V");
                        methodWriter.visitVarInsn(25, 1);
                        methodWriter.visitVarInsn(25, 0);
                        methodWriter.visitLdcInsn(Integer.valueOf(i10));
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
                        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DefaultJSONParser, "parseObject", "(Ljava/lang/reflect/Type;)Ljava/lang/Object;");
                        methodWriter.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
                        methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                    } else {
                        Label label15 = new Label();
                        Label label16 = new Label();
                        if (cls == Date.class) {
                            methodWriter.visitVarInsn(25, context.var("lexer"));
                            String str12 = JSONLexerBase;
                            i9 = Opcodes.INVOKEVIRTUAL;
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str12, "getCurrent", "()C");
                            methodWriter.visitLdcInsn(49);
                            methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label15);
                            methodWriter.visitTypeInsn(Opcodes.NEW, ASMUtils.type(Date.class));
                            methodWriter.visitInsn(89);
                            i8 = 25;
                            methodWriter.visitVarInsn(25, context.var("lexer"));
                            methodWriter.visitVarInsn(16, i11);
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str12, "scanLong", "(C)J");
                            methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(Date.class), "<init>", "(J)V");
                            methodWriter.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                            methodWriter.visitJumpInsn(Opcodes.GOTO, label16);
                        } else {
                            i8 = 25;
                            i9 = Opcodes.INVOKEVIRTUAL;
                        }
                        methodWriter.visitLabel(label15);
                        _quickNextToken(context, methodWriter, 14);
                        _deserObject(context, methodWriter, fieldInfo, cls, i10);
                        methodWriter.visitVarInsn(i8, context.var("lexer"));
                        methodWriter.visitMethodInsn(i9, JSONLexerBase, "token", "()I");
                        methodWriter.visitLdcInsn(15);
                        methodWriter.visitJumpInsn(Opcodes.IF_ICMPEQ, label16);
                        methodWriter.visitVarInsn(i8, 0);
                        methodWriter.visitVarInsn(i8, context.var("lexer"));
                        if (z7) {
                            methodWriter.visitLdcInsn(15);
                        } else {
                            methodWriter.visitLdcInsn(16);
                        }
                        String strType2 = ASMUtils.type(JavaBeanDeserializer.class);
                        StringBuilder sbM112a3 = C0413b.m112a("(");
                        sbM112a3.append(ASMUtils.desc((Class<?>) JSONLexer.class));
                        sbM112a3.append("I)V");
                        methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, strType2, "check", sbM112a3.toString());
                        methodWriter.visitLabel(label16);
                    }
                }
                i10 = i12;
            }
            i10++;
            fieldInfoArr = fieldInfoArr2;
            length = i7;
        }
        _batchSet(context, methodWriter, false);
        Label label17 = new Label();
        Label label18 = new Label();
        Label label19 = new Label();
        Label label20 = new Label();
        methodWriter.visitVarInsn(25, context.var("lexer"));
        String str13 = JSONLexerBase;
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "getCurrent", "()C");
        methodWriter.visitInsn(89);
        methodWriter.visitVarInsn(54, context.var("ch"));
        methodWriter.visitVarInsn(16, 44);
        methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label18);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "next", "()C");
        methodWriter.visitInsn(87);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitLdcInsn(16);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "setToken", "(I)V");
        methodWriter.visitJumpInsn(Opcodes.GOTO, label20);
        methodWriter.visitLabel(label18);
        methodWriter.visitVarInsn(21, context.var("ch"));
        methodWriter.visitVarInsn(16, 93);
        methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label19);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "next", "()C");
        methodWriter.visitInsn(87);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitLdcInsn(15);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "setToken", "(I)V");
        methodWriter.visitJumpInsn(Opcodes.GOTO, label20);
        methodWriter.visitLabel(label19);
        methodWriter.visitVarInsn(21, context.var("ch"));
        methodWriter.visitVarInsn(16, 26);
        methodWriter.visitJumpInsn(Opcodes.IF_ICMPNE, label17);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "next", "()C");
        methodWriter.visitInsn(87);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitLdcInsn(20);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "setToken", "(I)V");
        methodWriter.visitJumpInsn(Opcodes.GOTO, label20);
        methodWriter.visitLabel(label17);
        methodWriter.visitVarInsn(25, context.var("lexer"));
        methodWriter.visitLdcInsn(16);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "nextToken", "(I)V");
        methodWriter.visitLabel(label20);
        methodWriter.visitVarInsn(25, context.var("instance"));
        methodWriter.visitInsn(Opcodes.ARETURN);
        methodWriter.visitMaxs(5, context.variantIndex);
        methodWriter.visitEnd();
    }

    private void _deserialze_list_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, Class<?> cls2, int i7) {
        String str;
        String str2;
        String str3;
        String str4;
        Label label2;
        int i8;
        Label label3 = new Label();
        String str5 = JSONLexerBase;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "matchField", "([C)Z");
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label3);
        _setFlag(methodVisitor, context, i7);
        Label label4 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "token", "()I");
        methodVisitor.visitLdcInsn(8);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label4);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(16);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "nextToken", "(I)V");
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
        methodVisitor.visitLabel(label4);
        Label label5 = new Label();
        Label label6 = new Label();
        Label label7 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "token", "()I");
        methodVisitor.visitLdcInsn(21);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label6);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(14);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "nextToken", "(I)V");
        _newCollection(methodVisitor, cls, i7, true);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label5);
        methodVisitor.visitLabel(label6);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "token", "()I");
        methodVisitor.visitLdcInsn(14);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, label7);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "token", "()I");
        methodVisitor.visitLdcInsn(12);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label);
        _newCollection(methodVisitor, cls, i7, false);
        methodVisitor.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        _getCollectionFieldItemDeser(context, methodVisitor, fieldInfo, cls2);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
        methodVisitor.visitInsn(3);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        String strType = ASMUtils.type(ObjectDeserializer.class);
        StringBuilder sbM112a = C0413b.m112a("(L");
        String str6 = DefaultJSONParser;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, strType, "deserialze", C0072a.m92a(sbM112a, str6, ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;"));
        methodVisitor.visitVarInsn(58, context.var("list_item_value"));
        methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        methodVisitor.visitVarInsn(25, context.var("list_item_value"));
        if (cls.isInterface()) {
            str = "list_item_value";
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            str = "list_item_value";
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        }
        methodVisitor.visitInsn(87);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
        methodVisitor.visitLabel(label7);
        _newCollection(methodVisitor, cls, i7, false);
        methodVisitor.visitLabel(label5);
        methodVisitor.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        boolean zIsPrimitive2 = ParserConfig.isPrimitive2(fieldInfo.fieldClass);
        _getCollectionFieldItemDeser(context, methodVisitor, fieldInfo, cls2);
        if (zIsPrimitive2) {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(ObjectDeserializer.class), "getFastMatchToken", "()I");
            methodVisitor.visitVarInsn(54, context.var("fastMatchToken"));
            methodVisitor.visitVarInsn(25, context.var("lexer"));
            methodVisitor.visitVarInsn(21, context.var("fastMatchToken"));
            str2 = "nextToken";
            str3 = str5;
            str4 = "(I)V";
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, str2, str4);
            label2 = label3;
        } else {
            str2 = "nextToken";
            str3 = str5;
            str4 = "(I)V";
            methodVisitor.visitInsn(87);
            methodVisitor.visitLdcInsn(12);
            label2 = label3;
            methodVisitor.visitVarInsn(54, context.var("fastMatchToken"));
            _quickNextToken(context, methodVisitor, 12);
        }
        methodVisitor.visitVarInsn(25, 1);
        String str7 = str4;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, "getContext", "()" + ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor.visitVarInsn(58, context.var("listContext"));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        methodVisitor.visitLdcInsn(fieldInfo.name);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor.visitInsn(87);
        Label label8 = new Label();
        Label label9 = new Label();
        methodVisitor.visitInsn(3);
        String str8 = str2;
        methodVisitor.visitVarInsn(54, context.var("i"));
        methodVisitor.visitLabel(label8);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, "token", "()I");
        methodVisitor.visitLdcInsn(15);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, label9);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_list_item_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
        methodVisitor.visitVarInsn(21, context.var("i"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(ObjectDeserializer.class), "deserialze", C0096a.m97a("(L", str6, ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;"));
        String str9 = str;
        methodVisitor.visitVarInsn(58, context.var(str9));
        methodVisitor.visitIincInsn(context.var("i"), 1);
        methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        methodVisitor.visitVarInsn(25, context.var(str9));
        if (cls.isInterface()) {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        }
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, "checkListResolve", "(Ljava/util/Collection;)V");
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, "token", "()I");
        methodVisitor.visitLdcInsn(16);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label8);
        if (zIsPrimitive2) {
            methodVisitor.visitVarInsn(25, context.var("lexer"));
            methodVisitor.visitVarInsn(21, context.var("fastMatchToken"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, str8, str7);
            i8 = Opcodes.GOTO;
        } else {
            _quickNextToken(context, methodVisitor, 12);
            i8 = Opcodes.GOTO;
        }
        methodVisitor.visitJumpInsn(i8, label8);
        methodVisitor.visitLabel(label9);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var("listContext"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, "setContext", "(" + ASMUtils.desc((Class<?>) ParseContext.class) + ")V");
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, "token", "()I");
        methodVisitor.visitLdcInsn(15);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label);
        _quickNextTokenComma(context, methodVisitor);
        methodVisitor.visitLabel(label2);
    }

    private void _deserialze_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, int i7) {
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_prefix__"), "[C");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "matchField", "([C)Z");
        methodVisitor.visitJumpInsn(Opcodes.IFNE, label2);
        methodVisitor.visitInsn(1);
        methodVisitor.visitVarInsn(58, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
        methodVisitor.visitLabel(label2);
        _setFlag(methodVisitor, context, i7);
        methodVisitor.visitVarInsn(21, context.var("matchedCount"));
        methodVisitor.visitInsn(4);
        methodVisitor.visitInsn(96);
        methodVisitor.visitVarInsn(54, context.var("matchedCount"));
        _deserObject(context, methodVisitor, fieldInfo, cls, i7);
        methodVisitor.visitVarInsn(25, 1);
        String str = DefaultJSONParser;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getResolveStatus", "()I");
        methodVisitor.visitLdcInsn(1);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label3);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getLastResolveTask", "()" + ASMUtils.desc((Class<?>) DefaultJSONParser.ResolveTask.class));
        methodVisitor.visitVarInsn(58, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getContext", "()" + ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(DefaultJSONParser.ResolveTask.class), "ownerContext", ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor.visitVarInsn(25, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitLdcInsn(fieldInfo.name);
        String strType = ASMUtils.type(JavaBeanDeserializer.class);
        StringBuilder sbM112a = C0413b.m112a("(Ljava/lang/String;)");
        sbM112a.append(ASMUtils.desc((Class<?>) FieldDeserializer.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, strType, "getFieldDeserializer", sbM112a.toString());
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(DefaultJSONParser.ResolveTask.class), "fieldDeserializer", ASMUtils.desc((Class<?>) FieldDeserializer.class));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setResolveStatus", "(I)V");
        methodVisitor.visitLabel(label3);
    }

    private void _getCollectionFieldItemDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_list_item_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        String str = DefaultJSONParser;
        StringBuilder sbM112a = C0413b.m112a("()");
        sbM112a.append(ASMUtils.desc((Class<?>) ParserConfig.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getConfig", sbM112a.toString());
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
        String strType = ASMUtils.type(ParserConfig.class);
        StringBuilder sbM112a2 = C0413b.m112a("(Ljava/lang/reflect/Type;)");
        sbM112a2.append(ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, strType, "getDeserializer", sbM112a2.toString());
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_list_item_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_list_item_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
    }

    private void _getFieldDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        String str = DefaultJSONParser;
        StringBuilder sbM112a = C0413b.m112a("()");
        sbM112a.append(ASMUtils.desc((Class<?>) ParserConfig.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getConfig", sbM112a.toString());
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        String strType = ASMUtils.type(ParserConfig.class);
        StringBuilder sbM112a2 = C0413b.m112a("(Ljava/lang/reflect/Type;)");
        sbM112a2.append(ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, strType, "getDeserializer", sbM112a2.toString());
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
    }

    private void _init(ClassWriter classWriter, Context context) {
        int length = context.fieldInfoList.length;
        for (int i7 = 0; i7 < length; i7++) {
            new FieldWriter(classWriter, 1, C0072a.m92a(new StringBuilder(), context.fieldInfoList[i7].name, "_asm_prefix__"), "[C").visitEnd();
        }
        int length2 = context.fieldInfoList.length;
        for (int i8 = 0; i8 < length2; i8++) {
            FieldInfo fieldInfo = context.fieldInfoList[i8];
            Class<?> cls = fieldInfo.fieldClass;
            if (!cls.isPrimitive()) {
                if (Collection.class.isAssignableFrom(cls)) {
                    new FieldWriter(classWriter, 1, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_list_item_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class)).visitEnd();
                } else {
                    new FieldWriter(classWriter, 1, C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm_deser__"), ASMUtils.desc((Class<?>) ObjectDeserializer.class)).visitEnd();
                }
            }
        }
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(ASMUtils.desc((Class<?>) ParserConfig.class));
        sbM112a.append(ASMUtils.desc((Class<?>) JavaBeanInfo.class));
        sbM112a.append(")V");
        MethodWriter methodWriter = new MethodWriter(classWriter, 1, "<init>", sbM112a.toString(), null, null);
        methodWriter.visitVarInsn(25, 0);
        methodWriter.visitVarInsn(25, 1);
        methodWriter.visitVarInsn(25, 2);
        String strType = ASMUtils.type(JavaBeanDeserializer.class);
        StringBuilder sbM112a2 = C0413b.m112a("(");
        sbM112a2.append(ASMUtils.desc((Class<?>) ParserConfig.class));
        sbM112a2.append(ASMUtils.desc((Class<?>) JavaBeanInfo.class));
        sbM112a2.append(")V");
        methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, strType, "<init>", sbM112a2.toString());
        int length3 = context.fieldInfoList.length;
        for (int i9 = 0; i9 < length3; i9++) {
            FieldInfo fieldInfo2 = context.fieldInfoList[i9];
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitLdcInsn("\"" + fieldInfo2.name + "\":");
            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "toCharArray", "()[C");
            methodWriter.visitFieldInsn(Opcodes.PUTFIELD, context.className, C0072a.m92a(new StringBuilder(), fieldInfo2.name, "_asm_prefix__"), "[C");
        }
        methodWriter.visitInsn(Opcodes.RETURN);
        methodWriter.visitMaxs(4, 4);
        methodWriter.visitEnd();
    }

    private void _isFlag(MethodVisitor methodVisitor, Context context, int i7, Label label) {
        StringBuilder sbM112a = C0413b.m112a("_asm_flag_");
        sbM112a.append(i7 / 32);
        methodVisitor.visitVarInsn(21, context.var(sbM112a.toString()));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i7));
        methodVisitor.visitInsn(126);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
    }

    private void _loadAndSet(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Class<?> cls = fieldInfo.fieldClass;
        java.lang.reflect.Type type = fieldInfo.fieldType;
        if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            _set(context, methodVisitor, fieldInfo);
            return;
        }
        if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE || cls == Character.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            _set(context, methodVisitor, fieldInfo);
            return;
        }
        if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(22, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm"), 2));
            if (fieldInfo.method == null) {
                methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
                return;
            }
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(context.getInstClass()), fieldInfo.method.getName(), ASMUtils.desc(fieldInfo.method));
            if (fieldInfo.method.getReturnType().equals(Void.TYPE)) {
                return;
            }
            methodVisitor.visitInsn(87);
            return;
        }
        if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(23, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            _set(context, methodVisitor, fieldInfo);
            return;
        }
        if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(24, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm"), 2));
            _set(context, methodVisitor, fieldInfo);
            return;
        }
        if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            _set(context, methodVisitor, fieldInfo);
            return;
        }
        if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            _set(context, methodVisitor, fieldInfo);
        } else if (!Collection.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            _set(context, methodVisitor, fieldInfo);
        } else {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            if (TypeUtils.getCollectionItemClass(type) == String.class) {
                methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
            } else {
                methodVisitor.visitVarInsn(25, context.var(C0072a.m92a(new StringBuilder(), fieldInfo.name, "_asm")));
            }
            _set(context, methodVisitor, fieldInfo);
        }
    }

    private void _newCollection(MethodVisitor methodVisitor, Class<?> cls, int i7, boolean z6) {
        if (cls.isAssignableFrom(ArrayList.class) && !z6) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList");
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedList.class) && !z6) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(LinkedList.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(LinkedList.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(HashSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(HashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(TreeSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(TreeSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(TreeSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedHashSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(LinkedHashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(LinkedHashSet.class), "<init>", "()V");
        } else if (z6) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(HashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitLdcInsn(Integer.valueOf(i7));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, ASMUtils.type(TypeUtils.class), "createCollection", "(Ljava/lang/reflect/Type;)Ljava/util/Collection;");
        }
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
    }

    private void _quickNextToken(Context context, MethodVisitor methodVisitor, int i7) {
        Label label = new Label();
        Label label2 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        String str = JSONLexerBase;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getCurrent", "()C");
        if (i7 == 12) {
            methodVisitor.visitVarInsn(16, 123);
        } else {
            if (i7 != 14) {
                throw new IllegalStateException();
            }
            methodVisitor.visitVarInsn(16, 91);
        }
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "next", "()C");
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(Integer.valueOf(i7));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setToken", "(I)V");
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(Integer.valueOf(i7));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "nextToken", "(I)V");
        methodVisitor.visitLabel(label2);
    }

    private void _quickNextTokenComma(Context context, MethodVisitor methodVisitor) {
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        String str = JSONLexerBase;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getCurrent", "()C");
        methodVisitor.visitInsn(89);
        methodVisitor.visitVarInsn(54, context.var("ch"));
        methodVisitor.visitVarInsn(16, 44);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label2);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "next", "()C");
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(16);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setToken", "(I)V");
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label5);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(21, context.var("ch"));
        methodVisitor.visitVarInsn(16, 125);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label3);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "next", "()C");
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(13);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setToken", "(I)V");
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label5);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(21, context.var("ch"));
        methodVisitor.visitVarInsn(16, 93);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label4);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "next", "()C");
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(15);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setToken", "(I)V");
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label5);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(21, context.var("ch"));
        methodVisitor.visitVarInsn(16, 26);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(20);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setToken", "(I)V");
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label5);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "nextToken", "()V");
        methodVisitor.visitLabel(label5);
    }

    private void _set(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method == null) {
            methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
            return;
        }
        methodVisitor.visitMethodInsn(method.getDeclaringClass().isInterface() ? Opcodes.INVOKEINTERFACE : Opcodes.INVOKEVIRTUAL, ASMUtils.type(fieldInfo.declaringClass), method.getName(), ASMUtils.desc(method));
        if (fieldInfo.method.getReturnType().equals(Void.TYPE)) {
            return;
        }
        methodVisitor.visitInsn(87);
    }

    private void _setContext(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var("context"));
        String str = DefaultJSONParser;
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(ASMUtils.desc((Class<?>) ParseContext.class));
        sbM112a.append(")V");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setContext", sbM112a.toString());
        Label label = new Label();
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitJumpInsn(Opcodes.IFNULL, label);
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitVarInsn(25, context.var("instance"));
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(ParseContext.class), "object", "Ljava/lang/Object;");
        methodVisitor.visitLabel(label);
    }

    private void _setFlag(MethodVisitor methodVisitor, Context context, int i7) {
        StringBuilder sbM112a = C0413b.m112a("_asm_flag_");
        sbM112a.append(i7 / 32);
        String string = sbM112a.toString();
        methodVisitor.visitVarInsn(21, context.var(string));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i7));
        methodVisitor.visitInsn(128);
        methodVisitor.visitVarInsn(54, context.var(string));
    }

    private void defineVarLexer(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, DefaultJSONParser, "lexer", ASMUtils.desc((Class<?>) JSONLexer.class));
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, JSONLexerBase);
        methodVisitor.visitVarInsn(58, context.var("lexer"));
    }

    public ObjectDeserializer createJavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        String strM97a;
        Class<?> cls = javaBeanInfo.clazz;
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException(C0319a.m107a(cls, C0413b.m112a("not support type :")));
        }
        StringBuilder sbM112a = C0413b.m112a("FastjsonASMDeserializer_");
        sbM112a.append(this.seed.incrementAndGet());
        sbM112a.append("_");
        sbM112a.append(cls.getSimpleName());
        String string = sbM112a.toString();
        Package r12 = ASMDeserializerFactory.class.getPackage();
        if (r12 != null) {
            String name = r12.getName();
            String str = name.replace('.', '/') + ServiceReference.DELIMITER + string;
            strM97a = C0096a.m97a(name, ".", string);
            string = str;
        } else {
            strM97a = string;
        }
        ClassWriter classWriter = new ClassWriter();
        classWriter.visit(49, 33, string, ASMUtils.type(JavaBeanDeserializer.class), null);
        _init(classWriter, new Context(string, parserConfig, javaBeanInfo, 3));
        _createInstance(classWriter, new Context(string, parserConfig, javaBeanInfo, 3));
        _deserialze(classWriter, new Context(string, parserConfig, javaBeanInfo, 5));
        _deserialzeArrayMapping(classWriter, new Context(string, parserConfig, javaBeanInfo, 4));
        byte[] byteArray = classWriter.toByteArray();
        return (ObjectDeserializer) this.classLoader.defineClassPublic(strM97a, byteArray, 0, byteArray.length).getConstructor(ParserConfig.class, JavaBeanInfo.class).newInstance(parserConfig, javaBeanInfo);
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor, boolean z6) {
        int length = context.fieldInfoList.length;
        for (int i7 = 0; i7 < length; i7++) {
            Label label = new Label();
            if (z6) {
                _isFlag(methodVisitor, context, i7, label);
            }
            _loadAndSet(context, methodVisitor, context.fieldInfoList[i7]);
            if (z6) {
                methodVisitor.visitLabel(label);
            }
        }
    }

    public static class Context {
        public static final int fieldName = 3;
        public static final int parser = 1;
        public static final int type = 2;
        private final JavaBeanInfo beanInfo;
        private final String className;
        private final Class<?> clazz;
        private FieldInfo[] fieldInfoList;
        private int variantIndex;
        private final Map<String, Integer> variants = new HashMap();

        public Context(String str, ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, int i7) {
            this.variantIndex = -1;
            this.className = str;
            this.clazz = javaBeanInfo.clazz;
            this.variantIndex = i7;
            this.beanInfo = javaBeanInfo;
            this.fieldInfoList = javaBeanInfo.fields;
        }

        public Class<?> getInstClass() {
            Class<?> cls = this.beanInfo.builderClass;
            return cls == null ? this.clazz : cls;
        }

        public int var(String str, int i7) {
            if (this.variants.get(str) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i7;
            }
            return this.variants.get(str).intValue();
        }

        public int var(String str) {
            if (this.variants.get(str) == null) {
                Map<String, Integer> map = this.variants;
                int i7 = this.variantIndex;
                this.variantIndex = i7 + 1;
                map.put(str, Integer.valueOf(i7));
            }
            return this.variants.get(str).intValue();
        }
    }

    private void _createInstance(ClassWriter classWriter, Context context) {
        if (Modifier.isPublic(context.beanInfo.defaultConstructor.getModifiers())) {
            MethodWriter methodWriter = new MethodWriter(classWriter, 1, "createInstance", C0072a.m92a(C0413b.m112a("(L"), DefaultJSONParser, ";Ljava/lang/reflect/Type;)Ljava/lang/Object;"), null, null);
            methodWriter.visitTypeInsn(Opcodes.NEW, ASMUtils.type(context.getInstClass()));
            methodWriter.visitInsn(89);
            methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(context.getInstClass()), "<init>", "()V");
            methodWriter.visitInsn(Opcodes.ARETURN);
            methodWriter.visitMaxs(3, 3);
            methodWriter.visitEnd();
        }
    }
}
