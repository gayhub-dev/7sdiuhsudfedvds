package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public final class JsonStreamParser implements Iterator<JsonElement> {
    private final Object lock;
    private final JsonReader parser;

    public JsonStreamParser(String str) {
        this(new StringReader(str));
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        boolean z6;
        synchronized (this.lock) {
            try {
                try {
                    try {
                        z6 = this.parser.peek() != JsonToken.END_DOCUMENT;
                    } catch (MalformedJsonException e7) {
                        throw new JsonSyntaxException(e7);
                    }
                } catch (IOException e8) {
                    throw new JsonIOException(e8);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z6;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public JsonStreamParser(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        this.parser = jsonReader;
        jsonReader.setLenient(true);
        this.lock = new Object();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public JsonElement next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return Streams.parse(this.parser);
        } catch (JsonParseException e7) {
            if (e7.getCause() instanceof EOFException) {
                throw new NoSuchElementException();
            }
            throw e7;
        } catch (OutOfMemoryError e8) {
            throw new JsonParseException("Failed parsing JSON source to Json", e8);
        } catch (StackOverflowError e9) {
            throw new JsonParseException("Failed parsing JSON source to Json", e9);
        }
    }
}
