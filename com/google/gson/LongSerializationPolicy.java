package com.google.gson;

/* loaded from: classes.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l7) {
            return new JsonPrimitive((Number) l7);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l7) {
            return new JsonPrimitive(String.valueOf(l7));
        }
    };

    public abstract JsonElement serialize(Long l7);
}
