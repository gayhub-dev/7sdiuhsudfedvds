package retrofit2;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0002\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"T", "Lretrofit2/Retrofit;", "create", "(Lretrofit2/Retrofit;)Ljava/lang/Object;", "retrofit"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "-KotlinExtensions")
/* renamed from: retrofit2.-KotlinExtensions, reason: invalid class name */
/* loaded from: classes.dex */
public final class KotlinExtensions {
    private static final <T> T create(@NotNull Retrofit retrofit) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) retrofit.create(Object.class);
    }
}
