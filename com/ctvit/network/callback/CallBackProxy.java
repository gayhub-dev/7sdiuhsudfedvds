package com.ctvit.network.callback;

import com.ctvit.network.cache.model.CacheResult;
import com.ctvit.network.model.ApiResult;
import com.ctvit.network.utils.Utils;
import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;

/* loaded from: classes.dex */
public abstract class CallBackProxy<T extends ApiResult<R>, R> implements IType<T> {
    public CallBack<R> mCallBack;

    public CallBackProxy(CallBack<R> callBack) {
        this.mCallBack = callBack;
    }

    public CallBack getCallBack() {
        return this.mCallBack;
    }

    @Override // com.ctvit.network.callback.IType
    public Type getType() {
        Type type;
        CallBack<R> callBack = this.mCallBack;
        if (callBack != null) {
            Type rawType = callBack.getRawType();
            type = (List.class.isAssignableFrom(Utils.getClass(rawType, 0)) || Map.class.isAssignableFrom(Utils.getClass(rawType, 0))) ? this.mCallBack.getType() : CacheResult.class.isAssignableFrom(Utils.getClass(rawType, 0)) ? Utils.getParameterizedType(this.mCallBack.getType(), 0) : Utils.getClass(this.mCallBack.getType(), 0);
        } else {
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
