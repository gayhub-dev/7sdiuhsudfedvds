package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = new Excluder();
    private static final double IGNORE_VERSIONS = -1.0d;
    private boolean requireExpose;
    private double version = IGNORE_VERSIONS;
    private int modifiers = 136;
    private boolean serializeInnerClasses = true;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();

    private boolean isAnonymousOrLocal(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean isInnerClass(Class<?> cls) {
        return cls.isMemberClass() && !isStatic(cls);
    }

    private boolean isStatic(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean isValidSince(Since since) {
        return since == null || since.value() <= this.version;
    }

    private boolean isValidUntil(Until until) {
        return until == null || until.value() > this.version;
    }

    private boolean isValidVersion(Since since, Until until) {
        return isValidSince(since) && isValidUntil(until);
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        final boolean zExcludeClass = excludeClass(rawType, true);
        final boolean zExcludeClass2 = excludeClass(rawType, false);
        if (zExcludeClass || zExcludeClass2) {
            return new TypeAdapter<T>() { // from class: com.google.gson.internal.Excluder.1
                private TypeAdapter<T> delegate;

                private TypeAdapter<T> delegate() {
                    TypeAdapter<T> typeAdapter = this.delegate;
                    if (typeAdapter != null) {
                        return typeAdapter;
                    }
                    TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(Excluder.this, typeToken);
                    this.delegate = delegateAdapter;
                    return delegateAdapter;
                }

                @Override // com.google.gson.TypeAdapter
                public T read(JsonReader jsonReader) throws IOException {
                    if (!zExcludeClass2) {
                        return delegate().read(jsonReader);
                    }
                    jsonReader.skipValue();
                    return null;
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, T t6) throws IOException {
                    if (zExcludeClass) {
                        jsonWriter.nullValue();
                    } else {
                        delegate().write(jsonWriter, t6);
                    }
                }
            };
        }
        return null;
    }

    public Excluder disableInnerClassSerialization() {
        Excluder excluderM2609clone = m2609clone();
        excluderM2609clone.serializeInnerClasses = false;
        return excluderM2609clone;
    }

    public boolean excludeClass(Class<?> cls, boolean z6) {
        if (this.version != IGNORE_VERSIONS && !isValidVersion((Since) cls.getAnnotation(Since.class), (Until) cls.getAnnotation(Until.class))) {
            return true;
        }
        if ((!this.serializeInnerClasses && isInnerClass(cls)) || isAnonymousOrLocal(cls)) {
            return true;
        }
        Iterator<ExclusionStrategy> it = (z6 ? this.serializationStrategies : this.deserializationStrategies).iterator();
        while (it.hasNext()) {
            if (it.next().shouldSkipClass(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean excludeField(Field field, boolean z6) {
        Expose expose;
        if ((this.modifiers & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.version != IGNORE_VERSIONS && !isValidVersion((Since) field.getAnnotation(Since.class), (Until) field.getAnnotation(Until.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.requireExpose && ((expose = (Expose) field.getAnnotation(Expose.class)) == null || (!z6 ? expose.deserialize() : expose.serialize()))) {
            return true;
        }
        if ((!this.serializeInnerClasses && isInnerClass(field.getType())) || isAnonymousOrLocal(field.getType())) {
            return true;
        }
        List<ExclusionStrategy> list = z6 ? this.serializationStrategies : this.deserializationStrategies;
        if (list.isEmpty()) {
            return false;
        }
        FieldAttributes fieldAttributes = new FieldAttributes(field);
        Iterator<ExclusionStrategy> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().shouldSkipField(fieldAttributes)) {
                return true;
            }
        }
        return false;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder excluderM2609clone = m2609clone();
        excluderM2609clone.requireExpose = true;
        return excluderM2609clone;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean z6, boolean z7) {
        Excluder excluderM2609clone = m2609clone();
        if (z6) {
            ArrayList arrayList = new ArrayList(this.serializationStrategies);
            excluderM2609clone.serializationStrategies = arrayList;
            arrayList.add(exclusionStrategy);
        }
        if (z7) {
            ArrayList arrayList2 = new ArrayList(this.deserializationStrategies);
            excluderM2609clone.deserializationStrategies = arrayList2;
            arrayList2.add(exclusionStrategy);
        }
        return excluderM2609clone;
    }

    public Excluder withModifiers(int... iArr) {
        Excluder excluderM2609clone = m2609clone();
        excluderM2609clone.modifiers = 0;
        for (int i7 : iArr) {
            excluderM2609clone.modifiers = i7 | excluderM2609clone.modifiers;
        }
        return excluderM2609clone;
    }

    public Excluder withVersion(double d7) {
        Excluder excluderM2609clone = m2609clone();
        excluderM2609clone.version = d7;
        return excluderM2609clone;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Excluder m2609clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e7) {
            throw new AssertionError(e7);
        }
    }
}
