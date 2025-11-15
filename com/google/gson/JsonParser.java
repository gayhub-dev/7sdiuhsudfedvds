package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* loaded from: classes.dex */
public final class JsonParser {
    public JsonElement parse(String str) {
        return parse(new StringReader(str));
    }

    public JsonElement parse(Reader reader) {
        try {
            JsonReader jsonReader = new JsonReader(reader);
            JsonElement jsonElement = parse(jsonReader);
            if (!jsonElement.isJsonNull() && jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonSyntaxException("Did not consume the entire document.");
            }
            return jsonElement;
        } catch (MalformedJsonException e7) {
            throw new JsonSyntaxException(e7);
        } catch (IOException e8) {
            throw new JsonIOException(e8);
        } catch (NumberFormatException e9) {
            throw new JsonSyntaxException(e9);
        }
    }

    public JsonElement parse(JsonReader jsonReader) {
        boolean zIsLenient = jsonReader.isLenient();
        jsonReader.setLenient(true);
        try {
            try {
                return Streams.parse(jsonReader);
            } catch (OutOfMemoryError e7) {
                throw new JsonParseException("Failed parsing JSON source: " + jsonReader + " to Json", e7);
            } catch (StackOverflowError e8) {
                throw new JsonParseException("Failed parsing JSON source: " + jsonReader + " to Json", e8);
            }
        } finally {
            jsonReader.setLenient(zIsLenient);
        }
    }
}
