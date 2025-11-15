package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        public Adapter(ObjectConstructor<T> objectConstructor, Map<String, BoundField> map) {
            this.constructor = objectConstructor;
            this.boundFields = map;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            T tConstruct = this.constructor.construct();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    BoundField boundField = this.boundFields.get(jsonReader.nextName());
                    if (boundField == null || !boundField.deserialized) {
                        jsonReader.skipValue();
                    } else {
                        boundField.read(jsonReader, tConstruct);
                    }
                }
                jsonReader.endObject();
                return tConstruct;
            } catch (IllegalAccessException e7) {
                throw new AssertionError(e7);
            } catch (IllegalStateException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t6) throws IOException {
            if (t6 == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.writeField(t6)) {
                        jsonWriter.name(boundField.name);
                        boundField.write(jsonWriter, t6);
                    }
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e7) {
                throw new AssertionError(e7);
            }
        }
    }

    public static abstract class BoundField {
        public final boolean deserialized;
        public final String name;
        public final boolean serialized;

        public BoundField(String str, boolean z6, boolean z7) {
            this.name = str;
            this.serialized = z6;
            this.deserialized = z7;
        }

        public abstract void read(JsonReader jsonReader, Object obj);

        public abstract void write(JsonWriter jsonWriter, Object obj);

        public abstract boolean writeField(Object obj);
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingStrategy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
    }

    private BoundField createBoundField(final Gson gson, final Field field, String str, final TypeToken<?> typeToken, boolean z6, boolean z7) {
        final boolean zIsPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> typeAdapter = jsonAdapter != null ? this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter) : null;
        final boolean z8 = typeAdapter != null;
        if (typeAdapter == null) {
            typeAdapter = gson.getAdapter(typeToken);
        }
        final TypeAdapter<?> typeAdapter2 = typeAdapter;
        return new BoundField(str, z6, z7) { // from class: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.1
            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            public void read(JsonReader jsonReader, Object obj) throws IllegalAccessException, IllegalArgumentException {
                Object obj2 = typeAdapter2.read(jsonReader);
                if (obj2 == null && zIsPrimitive) {
                    return;
                }
                field.set(obj, obj2);
            }

            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            public void write(JsonWriter jsonWriter, Object obj) throws IllegalAccessException, IllegalArgumentException {
                (z8 ? typeAdapter2 : new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, typeToken.getType())).write(jsonWriter, field.get(obj));
            }

            @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
            public boolean writeField(Object obj) {
                return this.serialized && field.get(obj) != obj;
            }
        };
    }

    private Map<String, BoundField> getBoundFields(Gson gson, TypeToken<?> typeToken, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = typeToken.getType();
        TypeToken<?> typeToken2 = typeToken;
        Class<?> rawType = cls;
        while (rawType != Object.class) {
            Field[] declaredFields = rawType.getDeclaredFields();
            int length = declaredFields.length;
            boolean z6 = false;
            int i7 = 0;
            while (i7 < length) {
                Field field = declaredFields[i7];
                boolean zExcludeField = excludeField(field, true);
                boolean zExcludeField2 = excludeField(field, z6);
                if (zExcludeField || zExcludeField2) {
                    field.setAccessible(true);
                    Type typeResolve = C$Gson$Types.resolve(typeToken2.getType(), rawType, field.getGenericType());
                    List<String> fieldNames = getFieldNames(field);
                    int size = fieldNames.size();
                    BoundField boundField = null;
                    int i8 = 0;
                    while (i8 < size) {
                        String str = fieldNames.get(i8);
                        boolean z7 = i8 != 0 ? false : zExcludeField;
                        int i9 = i8;
                        BoundField boundField2 = boundField;
                        int i10 = size;
                        List<String> list = fieldNames;
                        Field field2 = field;
                        boundField = boundField2 == null ? (BoundField) linkedHashMap.put(str, createBoundField(gson, field, str, TypeToken.get(typeResolve), z7, zExcludeField2)) : boundField2;
                        i8 = i9 + 1;
                        zExcludeField = z7;
                        fieldNames = list;
                        size = i10;
                        field = field2;
                    }
                    BoundField boundField3 = boundField;
                    if (boundField3 != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + boundField3.name);
                    }
                }
                i7++;
                z6 = false;
            }
            typeToken2 = TypeToken.get(C$Gson$Types.resolve(typeToken2.getType(), rawType, rawType.getGenericSuperclass()));
            rawType = typeToken2.getRawType();
        }
        return linkedHashMap;
    }

    private List<String> getFieldNames(Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(field));
        }
        String strValue = serializedName.value();
        String[] strArrAlternate = serializedName.alternate();
        if (strArrAlternate.length == 0) {
            return Collections.singletonList(strValue);
        }
        ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
        arrayList.add(strValue);
        for (String str : strArrAlternate) {
            arrayList.add(str);
        }
        return arrayList;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new Adapter(this.constructorConstructor.get(typeToken), getBoundFields(gson, typeToken, rawType));
        }
        return null;
    }

    public boolean excludeField(Field field, boolean z6) {
        return excludeField(field, z6, this.excluder);
    }

    public static boolean excludeField(Field field, boolean z6, Excluder excluder) {
        return (excluder.excludeClass(field.getType(), z6) || excluder.excludeField(field, z6)) ? false : true;
    }
}
