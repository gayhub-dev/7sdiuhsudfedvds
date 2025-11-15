package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/* loaded from: classes.dex */
class Platform {
    private static final Platform PLATFORM = findPlatform();

    public static class Android extends Platform {

        public static class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                this.handler.post(runnable);
            }
        }

        @Override // retrofit2.Platform
        public List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor executor) {
            if (executor == null) {
                throw new AssertionError();
            }
            ExecutorCallAdapterFactory executorCallAdapterFactory = new ExecutorCallAdapterFactory(executor);
            return Build.VERSION.SDK_INT >= 24 ? Arrays.asList(CompletableFutureCallAdapterFactory.INSTANCE, executorCallAdapterFactory) : Collections.singletonList(executorCallAdapterFactory);
        }

        @Override // retrofit2.Platform
        public int defaultCallAdapterFactoriesSize() {
            return Build.VERSION.SDK_INT >= 24 ? 2 : 1;
        }

        @Override // retrofit2.Platform
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        @Override // retrofit2.Platform
        public List<? extends Converter.Factory> defaultConverterFactories() {
            return Build.VERSION.SDK_INT >= 24 ? Collections.singletonList(OptionalConverterFactory.INSTANCE) : Collections.emptyList();
        }

        @Override // retrofit2.Platform
        public int defaultConverterFactoriesSize() {
            return Build.VERSION.SDK_INT >= 24 ? 1 : 0;
        }

        @Override // retrofit2.Platform
        @IgnoreJRERequirement
        public boolean isDefaultMethod(Method method) {
            if (Build.VERSION.SDK_INT < 24) {
                return false;
            }
            return method.isDefault();
        }
    }

    @IgnoreJRERequirement
    public static class Java8 extends Platform {
        @Override // retrofit2.Platform
        public List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor executor) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(CompletableFutureCallAdapterFactory.INSTANCE);
            if (executor != null) {
                arrayList.add(new ExecutorCallAdapterFactory(executor));
            } else {
                arrayList.add(DefaultCallAdapterFactory.INSTANCE);
            }
            return Collections.unmodifiableList(arrayList);
        }

        @Override // retrofit2.Platform
        public int defaultCallAdapterFactoriesSize() {
            return 2;
        }

        @Override // retrofit2.Platform
        public List<? extends Converter.Factory> defaultConverterFactories() {
            return Collections.singletonList(OptionalConverterFactory.INSTANCE);
        }

        @Override // retrofit2.Platform
        public int defaultConverterFactoriesSize() {
            return 1;
        }

        @Override // retrofit2.Platform
        public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws NoSuchMethodException, SecurityException {
            Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            declaredConstructor.setAccessible(true);
            return ((MethodHandles.Lookup) declaredConstructor.newInstance(cls, -1)).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }

        @Override // retrofit2.Platform
        public boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }
    }

    private static Platform findPlatform() throws ClassNotFoundException {
        try {
            try {
                Class.forName("android.os.Build");
                return new Android();
            } catch (ClassNotFoundException unused) {
                Class.forName("java.util.Optional");
                return new Java8();
            }
        } catch (ClassNotFoundException unused2) {
            return new Platform();
        }
    }

    public static Platform get() {
        return PLATFORM;
    }

    public List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor executor) {
        return executor != null ? Collections.singletonList(new ExecutorCallAdapterFactory(executor)) : Collections.singletonList(DefaultCallAdapterFactory.INSTANCE);
    }

    public int defaultCallAdapterFactoriesSize() {
        return 1;
    }

    @Nullable
    public Executor defaultCallbackExecutor() {
        return null;
    }

    public List<? extends Converter.Factory> defaultConverterFactories() {
        return Collections.emptyList();
    }

    public int defaultConverterFactoriesSize() {
        return 0;
    }

    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) {
        throw new UnsupportedOperationException();
    }

    public boolean isDefaultMethod(Method method) {
        return false;
    }
}
