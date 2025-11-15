package org.fourthline.cling.registry.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import p202z4.AbstractC2156a;

/* loaded from: classes.dex */
public interface Phase {
    public static final AbstractC2156a<Alive> ALIVE = new AbstractC2156a<Alive>() { // from class: org.fourthline.cling.registry.event.Phase.1
    };
    public static final AbstractC2156a<Complete> COMPLETE = new AbstractC2156a<Complete>() { // from class: org.fourthline.cling.registry.event.Phase.2
    };
    public static final AbstractC2156a<Byebye> BYEBYE = new AbstractC2156a<Byebye>() { // from class: org.fourthline.cling.registry.event.Phase.3
    };
    public static final AbstractC2156a<Updated> UPDATED = new AbstractC2156a<Updated>() { // from class: org.fourthline.cling.registry.event.Phase.4
    };

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Alive {
    }

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Byebye {
    }

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Complete {
    }

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Updated {
    }
}
