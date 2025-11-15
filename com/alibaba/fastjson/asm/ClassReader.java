package com.alibaba.fastjson.asm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ClassReader {

    /* renamed from: b */
    public final byte[] f415b;
    public final int header;
    private final int[] items;
    private final int maxStringLength;
    private boolean readAnnotations;
    private final String[] strings;

    public ClassReader(InputStream inputStream, boolean z6) throws IOException {
        int i7;
        this.readAnnotations = z6;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i8 = inputStream.read(bArr);
            i7 = 0;
            if (i8 == -1) {
                break;
            } else if (i8 > 0) {
                byteArrayOutputStream.write(bArr, 0, i8);
            }
        }
        inputStream.close();
        this.f415b = byteArrayOutputStream.toByteArray();
        int[] iArr = new int[readUnsignedShort(8)];
        this.items = iArr;
        int length = iArr.length;
        this.strings = new String[length];
        int i9 = 10;
        int i10 = 1;
        while (i10 < length) {
            int i11 = i9 + 1;
            this.items[i10] = i11;
            byte b7 = this.f415b[i9];
            int unsignedShort = 5;
            if (b7 == 1) {
                unsignedShort = readUnsignedShort(i11) + 3;
                if (unsignedShort > i7) {
                    i7 = unsignedShort;
                }
            } else if (b7 == 15) {
                unsignedShort = 4;
            } else if (b7 != 18 && b7 != 3 && b7 != 4) {
                if (b7 != 5 && b7 != 6) {
                    switch (b7) {
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            break;
                        default:
                            unsignedShort = 3;
                            break;
                    }
                } else {
                    unsignedShort = 9;
                    i10++;
                }
            }
            i9 += unsignedShort;
            i10++;
        }
        this.maxStringLength = i7;
        this.header = i9;
    }

    private int getAttributes() {
        int i7 = this.header;
        int unsignedShort = (readUnsignedShort(i7 + 6) * 2) + i7 + 8;
        for (int unsignedShort2 = readUnsignedShort(unsignedShort); unsignedShort2 > 0; unsignedShort2--) {
            for (int unsignedShort3 = readUnsignedShort(unsignedShort + 8); unsignedShort3 > 0; unsignedShort3--) {
                unsignedShort += readInt(unsignedShort + 12) + 6;
            }
            unsignedShort += 8;
        }
        int i8 = unsignedShort + 2;
        for (int unsignedShort4 = readUnsignedShort(i8); unsignedShort4 > 0; unsignedShort4--) {
            for (int unsignedShort5 = readUnsignedShort(i8 + 8); unsignedShort5 > 0; unsignedShort5--) {
                i8 += readInt(i8 + 12) + 6;
            }
            i8 += 8;
        }
        return i8 + 2;
    }

    private int readInt(int i7) {
        byte[] bArr = this.f415b;
        return (bArr[i7 + 3] & 255) | ((bArr[i7] & 255) << 24) | ((bArr[i7 + 1] & 255) << 16) | ((bArr[i7 + 2] & 255) << 8);
    }

    private int readMethod(TypeCollector typeCollector, char[] cArr, int i7) {
        int unsignedShort = readUnsignedShort(i7);
        String utf8 = readUTF8(i7 + 2, cArr);
        String utf82 = readUTF8(i7 + 4, cArr);
        int i8 = i7 + 8;
        int i9 = 0;
        int i10 = 0;
        for (int unsignedShort2 = readUnsignedShort(i7 + 6); unsignedShort2 > 0; unsignedShort2--) {
            String utf83 = readUTF8(i8, cArr);
            int i11 = readInt(i8 + 2);
            int i12 = i8 + 6;
            if (utf83.equals("Code")) {
                i10 = i12;
            }
            i8 = i12 + i11;
        }
        MethodCollector methodCollectorVisitMethod = typeCollector.visitMethod(unsignedShort, utf8, utf82);
        if (methodCollectorVisitMethod != null && i10 != 0) {
            int i13 = i10 + 8 + readInt(i10 + 4);
            int i14 = i13 + 2;
            for (int unsignedShort3 = readUnsignedShort(i13); unsignedShort3 > 0; unsignedShort3--) {
                i14 += 8;
            }
            int i15 = i14 + 2;
            int i16 = 0;
            for (int unsignedShort4 = readUnsignedShort(i14); unsignedShort4 > 0; unsignedShort4--) {
                String utf84 = readUTF8(i15, cArr);
                if (utf84.equals("LocalVariableTable")) {
                    i9 = i15 + 6;
                } else if (utf84.equals("LocalVariableTypeTable")) {
                    i16 = i15 + 6;
                }
                i15 += readInt(i15 + 2) + 6;
            }
            if (i9 != 0) {
                if (i16 != 0) {
                    int unsignedShort5 = readUnsignedShort(i16) * 3;
                    int i17 = i16 + 2;
                    int[] iArr = new int[unsignedShort5];
                    while (unsignedShort5 > 0) {
                        int i18 = unsignedShort5 - 1;
                        iArr[i18] = i17 + 6;
                        int i19 = i18 - 1;
                        iArr[i19] = readUnsignedShort(i17 + 8);
                        unsignedShort5 = i19 - 1;
                        iArr[unsignedShort5] = readUnsignedShort(i17);
                        i17 += 10;
                    }
                }
                int i20 = i9 + 2;
                for (int unsignedShort6 = readUnsignedShort(i9); unsignedShort6 > 0; unsignedShort6--) {
                    methodCollectorVisitMethod.visitLocalVariable(readUTF8(i20 + 4, cArr), readUnsignedShort(i20 + 8));
                    i20 += 10;
                }
            }
        }
        return i8;
    }

    private String readUTF(int i7, int i8, char[] cArr) {
        int i9;
        int i10 = i8 + i7;
        byte[] bArr = this.f415b;
        int i11 = 0;
        char c7 = 0;
        char c8 = 0;
        while (i7 < i10) {
            int i12 = i7 + 1;
            byte b7 = bArr[i7];
            if (c7 == 0) {
                int i13 = b7 & 255;
                if (i13 < 128) {
                    cArr[i11] = (char) i13;
                    i11++;
                } else if (i13 >= 224 || i13 <= 191) {
                    c8 = (char) (i13 & 15);
                    c7 = 2;
                } else {
                    i9 = i13 & 31;
                    c8 = (char) i9;
                    c7 = 1;
                }
            } else if (c7 == 1) {
                cArr[i11] = (char) ((b7 & 63) | (c8 << 6));
                i11++;
                c7 = 0;
            } else if (c7 == 2) {
                i9 = (b7 & 63) | (c8 << 6);
                c8 = (char) i9;
                c7 = 1;
            }
            i7 = i12;
        }
        return new String(cArr, 0, i11);
    }

    private String readUTF8(int i7, char[] cArr) {
        int unsignedShort = readUnsignedShort(i7);
        String[] strArr = this.strings;
        String str = strArr[unsignedShort];
        if (str != null) {
            return str;
        }
        int i8 = this.items[unsignedShort];
        String utf = readUTF(i8 + 2, readUnsignedShort(i8), cArr);
        strArr[unsignedShort] = utf;
        return utf;
    }

    private int readUnsignedShort(int i7) {
        byte[] bArr = this.f415b;
        return (bArr[i7 + 1] & 255) | ((bArr[i7] & 255) << 8);
    }

    public void accept(TypeCollector typeCollector) {
        int i7;
        char[] cArr = new char[this.maxStringLength];
        if (this.readAnnotations) {
            int attributes = getAttributes();
            for (int unsignedShort = readUnsignedShort(attributes); unsignedShort > 0; unsignedShort--) {
                if ("RuntimeVisibleAnnotations".equals(readUTF8(attributes + 2, cArr))) {
                    i7 = attributes + 8;
                    break;
                }
                attributes += readInt(attributes + 4) + 6;
            }
            i7 = 0;
        } else {
            i7 = 0;
        }
        int i8 = this.header;
        int i9 = this.items[readUnsignedShort(i8 + 4)];
        int unsignedShort2 = readUnsignedShort(i8 + 6);
        int i10 = i8 + 8;
        for (int i11 = 0; i11 < unsignedShort2; i11++) {
            i10 += 2;
        }
        int i12 = i10 + 2;
        int i13 = i12;
        for (int unsignedShort3 = readUnsignedShort(i10); unsignedShort3 > 0; unsignedShort3--) {
            i13 += 8;
            for (int unsignedShort4 = readUnsignedShort(i13 + 6); unsignedShort4 > 0; unsignedShort4--) {
                i13 += readInt(i13 + 2) + 6;
            }
        }
        int i14 = i13 + 2;
        for (int unsignedShort5 = readUnsignedShort(i13); unsignedShort5 > 0; unsignedShort5--) {
            i14 += 8;
            for (int unsignedShort6 = readUnsignedShort(i14 + 6); unsignedShort6 > 0; unsignedShort6--) {
                i14 += readInt(i14 + 2) + 6;
            }
        }
        int i15 = i14 + 2;
        for (int unsignedShort7 = readUnsignedShort(i14); unsignedShort7 > 0; unsignedShort7--) {
            i15 += readInt(i15 + 2) + 6;
        }
        if (i7 != 0) {
            int i16 = i7 + 2;
            for (int unsignedShort8 = readUnsignedShort(i7); unsignedShort8 > 0; unsignedShort8--) {
                typeCollector.visitAnnotation(readUTF8(i16, cArr));
            }
        }
        for (int unsignedShort9 = readUnsignedShort(i10); unsignedShort9 > 0; unsignedShort9--) {
            i12 += 8;
            for (int unsignedShort10 = readUnsignedShort(i12 + 6); unsignedShort10 > 0; unsignedShort10--) {
                i12 += readInt(i12 + 2) + 6;
            }
        }
        int method = i12 + 2;
        for (int unsignedShort11 = readUnsignedShort(i12); unsignedShort11 > 0; unsignedShort11--) {
            method = readMethod(typeCollector, cArr, method);
        }
    }
}
