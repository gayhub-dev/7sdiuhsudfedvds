package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();
    private Class<T> type;

    public FastJsonRedisSerializer(Class<T> cls) {
        this.type = cls;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.springframework.data.redis.serializer.SerializationException */
    public T deserialize(byte[] bArr) throws SerializationException {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            return (T) JSON.parseObject(bArr, this.type, this.fastJsonConfig.getFeatures());
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Could not deserialize: ");
            sbM112a.append(e7.getMessage());
            throw new SerializationException(sbM112a.toString(), e7);
        }
    }

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.springframework.data.redis.serializer.SerializationException */
    public byte[] serialize(T t6) throws SerializationException {
        if (t6 == null) {
            return new byte[0];
        }
        try {
            return JSON.toJSONBytes(t6, this.fastJsonConfig.getSerializeConfig(), this.fastJsonConfig.getSerializerFeatures());
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Could not serialize: ");
            sbM112a.append(e7.getMessage());
            throw new SerializationException(sbM112a.toString(), e7);
        }
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }
}
