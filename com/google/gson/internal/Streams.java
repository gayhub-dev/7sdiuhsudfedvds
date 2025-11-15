package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public final class Streams {
    private Streams() {
        throw new UnsupportedOperationException();
    }

    public static JsonElement parse(JsonReader jsonReader) {
        boolean z6;
        try {
            try {
                jsonReader.peek();
                z6 = false;
                try {
                    return TypeAdapters.JSON_ELEMENT.read(jsonReader);
                } catch (EOFException e7) {
                    e = e7;
                    if (z6) {
                        return JsonNull.INSTANCE;
                    }
                    throw new JsonSyntaxException(e);
                }
            } catch (MalformedJsonException e8) {
                throw new JsonSyntaxException(e8);
            } catch (IOException e9) {
                throw new JsonIOException(e9);
            } catch (NumberFormatException e10) {
                throw new JsonSyntaxException(e10);
            }
        } catch (EOFException e11) {
            e = e11;
            z6 = true;
        }
    }

    public static void write(JsonElement jsonElement, JsonWriter jsonWriter) {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonElement);
    }

    public static Writer writerForAppendable(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new AppendableWriter(appendable);
    }

    public static final class AppendableWriter extends Writer {
        private final Appendable appendable;
        private final CurrentWrite currentWrite = new CurrentWrite();

        public static class CurrentWrite implements CharSequence {
            public char[] chars;

            @Override // java.lang.CharSequence
            public char charAt(int i7) {
                return this.chars[i7];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.chars.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i7, int i8) {
                return new String(this.chars, i7, i8 - i7);
            }
        }

        public AppendableWriter(Appendable appendable) {
            this.appendable = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i7, int i8) throws IOException {
            CurrentWrite currentWrite = this.currentWrite;
            currentWrite.chars = cArr;
            this.appendable.append(currentWrite, i7, i8 + i7);
        }

        @Override // java.io.Writer
        public void write(int i7) throws IOException {
            this.appendable.append((char) i7);
        }
    }
}
