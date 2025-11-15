package com.ctvit.network.callback;

import com.ctvit.network.model.ApiResult;
import com.ctvit.network.utils.Utils;
import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;

/* loaded from: classes.dex */
public abstract class CallClazzProxy<T extends ApiResult<R>, R> implements IType<T> {
    private Type type;

    public CallClazzProxy(Type type) {
        this.type = type;
    }

    public Type getCallType() {
        return this.type;
    }

    @Override // com.ctvit.network.callback.IType
    public Type getType() {
        Type type = this.type;
        if (type == null) {
            type = null;
        }
        if (type == null) {
            type = ResponseBody.class;
        }
        Type typeFindNeedType = Utils.findNeedType(getClass());
        if (typeFindNeedType instanceof ParameterizedType) {
            typeFindNeedType = ((ParameterizedType) typeFindNeedType).getRawType();
        }
        return C$Gson$Types.newParameterizedTypeWithOwner(null, typeFindNeedType, type);
    }
}
