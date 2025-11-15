package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class GsonBuilder {
    private String datePattern;
    private Excluder excluder = Excluder.DEFAULT;
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;
    private FieldNamingStrategy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    private final Map<Type, InstanceCreator<?>> instanceCreators = new HashMap();
    private final List<TypeAdapterFactory> factories = new ArrayList();
    private final List<TypeAdapterFactory> hierarchyFactories = new ArrayList();
    private boolean serializeNulls = false;
    private int dateStyle = 2;
    private int timeStyle = 2;
    private boolean complexMapKeySerialization = false;
    private boolean serializeSpecialFloatingPointValues = false;
    private boolean escapeHtmlChars = true;
    private boolean prettyPrinting = false;
    private boolean generateNonExecutableJson = false;
    private boolean lenient = false;

    private void addTypeAdaptersForDate(String str, int i7, int i8, List<TypeAdapterFactory> list) {
        DefaultDateTypeAdapter defaultDateTypeAdapter;
        DefaultDateTypeAdapter defaultDateTypeAdapter2;
        DefaultDateTypeAdapter defaultDateTypeAdapter3;
        if (str != null && !"".equals(str.trim())) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter((Class<? extends Date>) Date.class, str);
            defaultDateTypeAdapter2 = new DefaultDateTypeAdapter((Class<? extends Date>) Timestamp.class, str);
            defaultDateTypeAdapter3 = new DefaultDateTypeAdapter((Class<? extends Date>) java.sql.Date.class, str);
        } else {
            if (i7 == 2 || i8 == 2) {
                return;
            }
            DefaultDateTypeAdapter defaultDateTypeAdapter4 = new DefaultDateTypeAdapter((Class<? extends Date>) Date.class, i7, i8);
            DefaultDateTypeAdapter defaultDateTypeAdapter5 = new DefaultDateTypeAdapter((Class<? extends Date>) Timestamp.class, i7, i8);
            DefaultDateTypeAdapter defaultDateTypeAdapter6 = new DefaultDateTypeAdapter((Class<? extends Date>) java.sql.Date.class, i7, i8);
            defaultDateTypeAdapter = defaultDateTypeAdapter4;
            defaultDateTypeAdapter2 = defaultDateTypeAdapter5;
            defaultDateTypeAdapter3 = defaultDateTypeAdapter6;
        }
        list.add(TypeAdapters.newFactory(Date.class, defaultDateTypeAdapter));
        list.add(TypeAdapters.newFactory(Timestamp.class, defaultDateTypeAdapter2));
        list.add(TypeAdapters.newFactory(java.sql.Date.class, defaultDateTypeAdapter3));
    }

    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy exclusionStrategy) {
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, false, true);
        return this;
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy exclusionStrategy) {
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, true, false);
        return this;
    }

    public Gson create() {
        ArrayList arrayList = new ArrayList(this.hierarchyFactories.size() + this.factories.size() + 3);
        arrayList.addAll(this.factories);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.hierarchyFactories);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, arrayList);
        return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.lenient, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, arrayList);
    }

    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public GsonBuilder excludeFieldsWithModifiers(int... iArr) {
        this.excluder = this.excluder.withModifiers(iArr);
        return this;
    }

    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public GsonBuilder registerTypeAdapter(Type type, Object obj) {
        boolean z6 = obj instanceof JsonSerializer;
        C$Gson$Preconditions.checkArgument(z6 || (obj instanceof JsonDeserializer) || (obj instanceof InstanceCreator) || (obj instanceof TypeAdapter));
        if (obj instanceof InstanceCreator) {
            this.instanceCreators.put(type, (InstanceCreator) obj);
        }
        if (z6 || (obj instanceof JsonDeserializer)) {
            this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type), obj));
        }
        if (obj instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter) obj));
        }
        return this;
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory typeAdapterFactory) {
        this.factories.add(typeAdapterFactory);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class<?> cls, Object obj) {
        boolean z6 = obj instanceof JsonSerializer;
        C$Gson$Preconditions.checkArgument(z6 || (obj instanceof JsonDeserializer) || (obj instanceof TypeAdapter));
        if ((obj instanceof JsonDeserializer) || z6) {
            this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(cls, obj));
        }
        if (obj instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newTypeHierarchyFactory(cls, (TypeAdapter) obj));
        }
        return this;
    }

    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public GsonBuilder setDateFormat(String str) {
        this.datePattern = str;
        return this;
    }

    public GsonBuilder setExclusionStrategies(ExclusionStrategy... exclusionStrategyArr) {
        for (ExclusionStrategy exclusionStrategy : exclusionStrategyArr) {
            this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, true, true);
        }
        return this;
    }

    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
        this.fieldNamingPolicy = fieldNamingPolicy;
        return this;
    }

    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        this.fieldNamingPolicy = fieldNamingStrategy;
        return this;
    }

    public GsonBuilder setLenient() {
        this.lenient = true;
        return this;
    }

    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy) {
        this.longSerializationPolicy = longSerializationPolicy;
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        this.prettyPrinting = true;
        return this;
    }

    public GsonBuilder setVersion(double d7) {
        this.excluder = this.excluder.withVersion(d7);
        return this;
    }

    public GsonBuilder setDateFormat(int i7) {
        this.dateStyle = i7;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(int i7, int i8) {
        this.dateStyle = i7;
        this.timeStyle = i8;
        this.datePattern = null;
        return this;
    }
}
