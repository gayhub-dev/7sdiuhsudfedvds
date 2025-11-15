package com.alibaba.fastjson.asm;

/* loaded from: classes.dex */
public class ClassWriter {
    private int access;
    public FieldWriter firstField;
    public MethodWriter firstMethod;
    public int index;
    private int interfaceCount;
    private int[] interfaces;
    public Item[] items;
    public final Item key;
    public final Item key2;
    public final Item key3;
    public FieldWriter lastField;
    public MethodWriter lastMethod;
    private int name;
    public final ByteVector pool;
    private int superName;
    public String thisName;
    public int threshold;
    public Item[] typeTable;
    public int version;

    public ClassWriter() {
        this(0);
    }

    private Item get(Item item) {
        Item[] itemArr = this.items;
        Item item2 = itemArr[item.hashCode % itemArr.length];
        while (item2 != null && (item2.type != item.type || !item.isEqualTo(item2))) {
            item2 = item2.next;
        }
        return item2;
    }

    private Item newString(String str) {
        this.key2.set(8, str, null, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        this.pool.put12(8, newUTF8(str));
        int i7 = this.index;
        this.index = i7 + 1;
        Item item2 = new Item(i7, this.key2);
        put(item2);
        return item2;
    }

    private void put(Item item) {
        if (this.index > this.threshold) {
            int length = this.items.length;
            int i7 = (length * 2) + 1;
            Item[] itemArr = new Item[i7];
            for (int i8 = length - 1; i8 >= 0; i8--) {
                Item item2 = this.items[i8];
                while (item2 != null) {
                    int i9 = item2.hashCode % i7;
                    Item item3 = item2.next;
                    item2.next = itemArr[i9];
                    itemArr[i9] = item2;
                    item2 = item3;
                }
            }
            this.items = itemArr;
            this.threshold = (int) (i7 * 0.75d);
        }
        int i10 = item.hashCode;
        Item[] itemArr2 = this.items;
        int length2 = i10 % itemArr2.length;
        item.next = itemArr2[length2];
        itemArr2[length2] = item;
    }

    public Item newClassItem(String str) {
        this.key2.set(7, str, null, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        this.pool.put12(7, newUTF8(str));
        int i7 = this.index;
        this.index = i7 + 1;
        Item item2 = new Item(i7, this.key2);
        put(item2);
        return item2;
    }

    public Item newConstItem(Object obj) {
        if (!(obj instanceof Integer)) {
            if (obj instanceof String) {
                return newString((String) obj);
            }
            if (!(obj instanceof Type)) {
                throw new IllegalArgumentException(C0532a.m338a("value ", obj));
            }
            Type type = (Type) obj;
            return newClassItem(type.sort == 10 ? type.getInternalName() : type.getDescriptor());
        }
        int iIntValue = ((Integer) obj).intValue();
        this.key.set(iIntValue);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.putByte(3).putInt(iIntValue);
        int i7 = this.index;
        this.index = i7 + 1;
        Item item2 = new Item(i7, this.key);
        put(item2);
        return item2;
    }

    public Item newFieldItem(String str, String str2, String str3) {
        this.key3.set(9, str, str2, str3);
        Item item = get(this.key3);
        if (item != null) {
            return item;
        }
        int i7 = newClassItem(str).index;
        this.pool.put12(9, i7).putShort(newNameTypeItem(str2, str3).index);
        int i8 = this.index;
        this.index = i8 + 1;
        Item item2 = new Item(i8, this.key3);
        put(item2);
        return item2;
    }

    public Item newMethodItem(String str, String str2, String str3, boolean z6) {
        int i7 = z6 ? 11 : 10;
        this.key3.set(i7, str, str2, str3);
        Item item = get(this.key3);
        if (item != null) {
            return item;
        }
        this.pool.put12(i7, newClassItem(str).index).putShort(newNameTypeItem(str2, str3).index);
        int i8 = this.index;
        this.index = i8 + 1;
        Item item2 = new Item(i8, this.key3);
        put(item2);
        return item2;
    }

    public Item newNameTypeItem(String str, String str2) {
        this.key2.set(12, str, str2, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        int iNewUTF8 = newUTF8(str);
        this.pool.put12(12, iNewUTF8).putShort(newUTF8(str2));
        int i7 = this.index;
        this.index = i7 + 1;
        Item item2 = new Item(i7, this.key2);
        put(item2);
        return item2;
    }

    public int newUTF8(String str) {
        this.key.set(1, str, null, null);
        Item item = get(this.key);
        if (item == null) {
            this.pool.putByte(1).putUTF8(str);
            int i7 = this.index;
            this.index = i7 + 1;
            item = new Item(i7, this.key);
            put(item);
        }
        return item.index;
    }

    public byte[] toByteArray() {
        int size = (this.interfaceCount * 2) + 24;
        int i7 = 0;
        for (FieldWriter fieldWriter = this.firstField; fieldWriter != null; fieldWriter = fieldWriter.next) {
            i7++;
            size += fieldWriter.getSize();
        }
        int i8 = 0;
        for (MethodWriter methodWriter = this.firstMethod; methodWriter != null; methodWriter = methodWriter.next) {
            i8++;
            size += methodWriter.getSize();
        }
        ByteVector byteVector = new ByteVector(size + this.pool.length);
        byteVector.putInt(-889275714).putInt(this.version);
        ByteVector byteVectorPutShort = byteVector.putShort(this.index);
        ByteVector byteVector2 = this.pool;
        byteVectorPutShort.putByteArray(byteVector2.data, 0, byteVector2.length);
        byteVector.putShort(this.access & (-393217)).putShort(this.name).putShort(this.superName);
        byteVector.putShort(this.interfaceCount);
        for (int i9 = 0; i9 < this.interfaceCount; i9++) {
            byteVector.putShort(this.interfaces[i9]);
        }
        byteVector.putShort(i7);
        for (FieldWriter fieldWriter2 = this.firstField; fieldWriter2 != null; fieldWriter2 = fieldWriter2.next) {
            fieldWriter2.put(byteVector);
        }
        byteVector.putShort(i8);
        for (MethodWriter methodWriter2 = this.firstMethod; methodWriter2 != null; methodWriter2 = methodWriter2.next) {
            methodWriter2.put(byteVector);
        }
        byteVector.putShort(0);
        return byteVector.data;
    }

    public void visit(int i7, int i8, String str, String str2, String[] strArr) {
        this.version = i7;
        this.access = i8;
        this.name = newClassItem(str).index;
        this.thisName = str;
        this.superName = str2 == null ? 0 : newClassItem(str2).index;
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        int length = strArr.length;
        this.interfaceCount = length;
        this.interfaces = new int[length];
        for (int i9 = 0; i9 < this.interfaceCount; i9++) {
            this.interfaces[i9] = newClassItem(strArr[i9]).index;
        }
    }

    private ClassWriter(int i7) {
        this.index = 1;
        this.pool = new ByteVector();
        this.items = new Item[256];
        this.threshold = (int) (r5.length * 0.75d);
        this.key = new Item();
        this.key2 = new Item();
        this.key3 = new Item();
    }
}
