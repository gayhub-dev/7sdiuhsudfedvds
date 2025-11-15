package retrofit2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class Invocation {
    private final List<?> arguments;
    private final Method method;

    public Invocation(Method method, List<?> list) {
        this.method = method;
        this.arguments = Collections.unmodifiableList(list);
    }

    /* renamed from: of */
    public static Invocation m2110of(Method method, List<?> list) {
        Utils.checkNotNull(method, "method == null");
        Utils.checkNotNull(list, "arguments == null");
        return new Invocation(method, new ArrayList(list));
    }

    public List<?> arguments() {
        return this.arguments;
    }

    public Method method() {
        return this.method;
    }

    public String toString() {
        return String.format("%s.%s() %s", this.method.getDeclaringClass().getName(), this.method.getName(), this.arguments);
    }
}
