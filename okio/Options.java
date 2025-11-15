package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public final ByteString[] byteStrings;
    public final int[] trie;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    private static void buildTrieRecursive(long j7, Buffer buffer, int i7, List<ByteString> list, int i8, int i9, List<Integer> list2) {
        int i10;
        int i11;
        int i12;
        Buffer buffer2;
        int i13 = i8;
        if (i13 >= i9) {
            throw new AssertionError();
        }
        for (int i14 = i13; i14 < i9; i14++) {
            if (list.get(i14).size() < i7) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i8);
        ByteString byteString2 = list.get(i9 - 1);
        int iIntValue = -1;
        if (i7 == byteString.size()) {
            iIntValue = list2.get(i13).intValue();
            i13++;
            byteString = list.get(i13);
        }
        int i15 = i13;
        if (byteString.getByte(i7) == byteString2.getByte(i7)) {
            int i16 = 0;
            int iMin = Math.min(byteString.size(), byteString2.size());
            for (int i17 = i7; i17 < iMin && byteString.getByte(i17) == byteString2.getByte(i17); i17++) {
                i16++;
            }
            long jIntCount = 1 + j7 + intCount(buffer) + 2 + i16;
            buffer.writeInt(-i16);
            buffer.writeInt(iIntValue);
            int i18 = i7;
            while (true) {
                i10 = i7 + i16;
                if (i18 >= i10) {
                    break;
                }
                buffer.writeInt(byteString.getByte(i18) & 255);
                i18++;
            }
            if (i15 + 1 == i9) {
                if (i10 != list.get(i15).size()) {
                    throw new AssertionError();
                }
                buffer.writeInt(list2.get(i15).intValue());
                return;
            } else {
                Buffer buffer3 = new Buffer();
                buffer.writeInt((int) ((intCount(buffer3) + jIntCount) * (-1)));
                buildTrieRecursive(jIntCount, buffer3, i10, list, i15, i9, list2);
                buffer.write(buffer3, buffer3.size());
                return;
            }
        }
        int i19 = 1;
        for (int i20 = i15 + 1; i20 < i9; i20++) {
            if (list.get(i20 - 1).getByte(i7) != list.get(i20).getByte(i7)) {
                i19++;
            }
        }
        long jIntCount2 = j7 + intCount(buffer) + 2 + (i19 * 2);
        buffer.writeInt(i19);
        buffer.writeInt(iIntValue);
        for (int i21 = i15; i21 < i9; i21++) {
            byte b7 = list.get(i21).getByte(i7);
            if (i21 == i15 || b7 != list.get(i21 - 1).getByte(i7)) {
                buffer.writeInt(b7 & 255);
            }
        }
        Buffer buffer4 = new Buffer();
        int i22 = i15;
        while (i22 < i9) {
            byte b8 = list.get(i22).getByte(i7);
            int i23 = i22 + 1;
            int i24 = i23;
            while (true) {
                if (i24 >= i9) {
                    i11 = i9;
                    break;
                } else {
                    if (b8 != list.get(i24).getByte(i7)) {
                        i11 = i24;
                        break;
                    }
                    i24++;
                }
            }
            if (i23 == i11 && i7 + 1 == list.get(i22).size()) {
                buffer.writeInt(list2.get(i22).intValue());
                i12 = i11;
                buffer2 = buffer4;
            } else {
                buffer.writeInt((int) ((intCount(buffer4) + jIntCount2) * (-1)));
                i12 = i11;
                buffer2 = buffer4;
                buildTrieRecursive(jIntCount2, buffer4, i7 + 1, list, i22, i11, list2);
            }
            buffer4 = buffer2;
            i22 = i12;
        }
        Buffer buffer5 = buffer4;
        buffer.write(buffer5, buffer5.size());
    }

    private static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00bc, code lost:
    
        continue;
     */
    /* renamed from: of */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static okio.Options m1871of(okio.ByteString... r10) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.m1871of(okio.ByteString[]):okio.Options");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i7) {
        return this.byteStrings[i7];
    }
}
