package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class JsonTreeReader extends JsonReader {
    private int[] pathIndices;
    private String[] pathNames;
    private Object[] stack;
    private int stackSize;
    private static final Reader UNREADABLE_READER = new Reader() { // from class: com.google.gson.internal.bind.JsonTreeReader.1
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i7, int i8) {
            throw new AssertionError();
        }
    };
    private static final Object SENTINEL_CLOSED = new Object();

    public JsonTreeReader(JsonElement jsonElement) {
        super(UNREADABLE_READER);
        this.stack = new Object[32];
        this.stackSize = 0;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        push(jsonElement);
    }

    private void expect(JsonToken jsonToken) {
        if (peek() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + peek() + locationString());
    }

    private String locationString() {
        StringBuilder sbM112a = C0413b.m112a(" at path ");
        sbM112a.append(getPath());
        return sbM112a.toString();
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] objArr = this.stack;
        int i7 = this.stackSize - 1;
        this.stackSize = i7;
        Object obj = objArr[i7];
        objArr[i7] = null;
        return obj;
    }

    private void push(Object obj) {
        int i7 = this.stackSize;
        Object[] objArr = this.stack;
        if (i7 == objArr.length) {
            Object[] objArr2 = new Object[i7 * 2];
            int[] iArr = new int[i7 * 2];
            String[] strArr = new String[i7 * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i7);
            System.arraycopy(this.pathIndices, 0, iArr, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, strArr, 0, this.stackSize);
            this.stack = objArr2;
            this.pathIndices = iArr;
            this.pathNames = strArr;
        }
        Object[] objArr3 = this.stack;
        int i8 = this.stackSize;
        this.stackSize = i8 + 1;
        objArr3[i8] = obj;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginArray() {
        expect(JsonToken.BEGIN_ARRAY);
        push(((JsonArray) peekStack()).iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginObject() {
        expect(JsonToken.BEGIN_OBJECT);
        push(((JsonObject) peekStack()).entrySet().iterator());
    }

    @Override // com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.stack = new Object[]{SENTINEL_CLOSED};
        this.stackSize = 1;
    }

    @Override // com.google.gson.stream.JsonReader
    public void endArray() {
        expect(JsonToken.END_ARRAY);
        popStack();
        popStack();
        int i7 = this.stackSize;
        if (i7 > 0) {
            int[] iArr = this.pathIndices;
            int i8 = i7 - 1;
            iArr[i8] = iArr[i8] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public void endObject() {
        expect(JsonToken.END_OBJECT);
        popStack();
        popStack();
        int i7 = this.stackSize;
        if (i7 > 0) {
            int[] iArr = this.pathIndices;
            int i8 = i7 - 1;
            iArr[i8] = iArr[i8] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i7 = 0;
        while (i7 < this.stackSize) {
            Object[] objArr = this.stack;
            if (objArr[i7] instanceof JsonArray) {
                i7++;
                if (objArr[i7] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.pathIndices[i7]);
                    sb.append(']');
                }
            } else if (objArr[i7] instanceof JsonObject) {
                i7++;
                if (objArr[i7] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.pathNames;
                    if (strArr[i7] != null) {
                        sb.append(strArr[i7]);
                    }
                }
            }
            i7++;
        }
        return sb.toString();
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean hasNext() {
        JsonToken jsonTokenPeek = peek();
        return (jsonTokenPeek == JsonToken.END_OBJECT || jsonTokenPeek == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean nextBoolean() {
        expect(JsonToken.BOOLEAN);
        boolean asBoolean = ((JsonPrimitive) popStack()).getAsBoolean();
        int i7 = this.stackSize;
        if (i7 > 0) {
            int[] iArr = this.pathIndices;
            int i8 = i7 - 1;
            iArr[i8] = iArr[i8] + 1;
        }
        return asBoolean;
    }

    @Override // com.google.gson.stream.JsonReader
    public double nextDouble() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        double asDouble = ((JsonPrimitive) peekStack()).getAsDouble();
        if (!isLenient() && (Double.isNaN(asDouble) || Double.isInfinite(asDouble))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + asDouble);
        }
        popStack();
        int i7 = this.stackSize;
        if (i7 > 0) {
            int[] iArr = this.pathIndices;
            int i8 = i7 - 1;
            iArr[i8] = iArr[i8] + 1;
        }
        return asDouble;
    }

    @Override // com.google.gson.stream.JsonReader
    public int nextInt() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        int asInt = ((JsonPrimitive) peekStack()).getAsInt();
        popStack();
        int i7 = this.stackSize;
        if (i7 > 0) {
            int[] iArr = this.pathIndices;
            int i8 = i7 - 1;
            iArr[i8] = iArr[i8] + 1;
        }
        return asInt;
    }

    @Override // com.google.gson.stream.JsonReader
    public long nextLong() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        long asLong = ((JsonPrimitive) peekStack()).getAsLong();
        popStack();
        int i7 = this.stackSize;
        if (i7 > 0) {
            int[] iArr = this.pathIndices;
            int i8 = i7 - 1;
            iArr[i8] = iArr[i8] + 1;
        }
        return asLong;
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextName() {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        String str = (String) entry.getKey();
        this.pathNames[this.stackSize - 1] = str;
        push(entry.getValue());
        return str;
    }

    @Override // com.google.gson.stream.JsonReader
    public void nextNull() {
        expect(JsonToken.NULL);
        popStack();
        int i7 = this.stackSize;
        if (i7 > 0) {
            int[] iArr = this.pathIndices;
            int i8 = i7 - 1;
            iArr[i8] = iArr[i8] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextString() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenPeek == jsonToken || jsonTokenPeek == JsonToken.NUMBER) {
            String asString = ((JsonPrimitive) popStack()).getAsString();
            int i7 = this.stackSize;
            if (i7 > 0) {
                int[] iArr = this.pathIndices;
                int i8 = i7 - 1;
                iArr[i8] = iArr[i8] + 1;
            }
            return asString;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
    }

    @Override // com.google.gson.stream.JsonReader
    public JsonToken peek() {
        if (this.stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objPeekStack = peekStack();
        if (objPeekStack instanceof Iterator) {
            boolean z6 = this.stack[this.stackSize - 2] instanceof JsonObject;
            Iterator it = (Iterator) objPeekStack;
            if (!it.hasNext()) {
                return z6 ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z6) {
                return JsonToken.NAME;
            }
            push(it.next());
            return peek();
        }
        if (objPeekStack instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objPeekStack instanceof JsonArray) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objPeekStack instanceof JsonPrimitive)) {
            if (objPeekStack instanceof JsonNull) {
                return JsonToken.NULL;
            }
            if (objPeekStack == SENTINEL_CLOSED) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) objPeekStack;
        if (jsonPrimitive.isString()) {
            return JsonToken.STRING;
        }
        if (jsonPrimitive.isBoolean()) {
            return JsonToken.BOOLEAN;
        }
        if (jsonPrimitive.isNumber()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }

    public void promoteNameToValue() {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        push(entry.getValue());
        push(new JsonPrimitive((String) entry.getKey()));
    }

    @Override // com.google.gson.stream.JsonReader
    public void skipValue() {
        if (peek() == JsonToken.NAME) {
            nextName();
            this.pathNames[this.stackSize - 2] = "null";
        } else {
            popStack();
            int i7 = this.stackSize;
            if (i7 > 0) {
                this.pathNames[i7 - 1] = "null";
            }
        }
        int i8 = this.stackSize;
        if (i8 > 0) {
            int[] iArr = this.pathIndices;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String toString() {
        return "JsonTreeReader";
    }
}
