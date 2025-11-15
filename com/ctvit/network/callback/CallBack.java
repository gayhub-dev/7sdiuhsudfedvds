package com.ctvit.network.callback;

import com.ctvit.network.exception.ApiException;
import com.ctvit.network.utils.Utils;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public abstract class CallBack<T> implements IType<T> {
    public Type getRawType() {
        return Utils.findRawType(getClass());
    }

    @Override // com.ctvit.network.callback.IType
    public Type getType() {
        return Utils.findNeedClass(getClass());
    }

    public abstract void onCompleted();

    public abstract void onError(ApiException apiException);

    public abstract void onStart();

    public abstract void onSuccess(T t6);
}
