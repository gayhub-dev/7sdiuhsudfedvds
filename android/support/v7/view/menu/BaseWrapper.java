package android.support.v7.view.menu;

/* loaded from: classes.dex */
class BaseWrapper<T> {
    public final T mWrappedObject;

    public BaseWrapper(T t6) {
        if (t6 == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = t6;
    }

    public T getWrappedObject() {
        return this.mWrappedObject;
    }
}
