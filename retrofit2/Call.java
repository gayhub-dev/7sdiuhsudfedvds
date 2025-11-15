package retrofit2;

import okhttp3.Request;

/* loaded from: classes.dex */
public interface Call<T> extends Cloneable {
    void cancel();

    /* renamed from: clone */
    Call<T> mo2611clone();

    void enqueue(Callback<T> callback);

    Response<T> execute();

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
