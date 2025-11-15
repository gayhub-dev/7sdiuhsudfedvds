package org.fourthline.cling.support.avtransport.callback;

import java.util.logging.Logger;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.controlpoint.ActionCallback;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;

/* loaded from: classes.dex */
public abstract class Play extends ActionCallback {
    private static Logger log = Logger.getLogger(Play.class.getName());

    public Play(Service service) {
        this(new UnsignedIntegerFourBytes(0L), service, DiskLruCache.VERSION_1);
    }

    @Override // org.fourthline.cling.controlpoint.ActionCallback
    public void success(ActionInvocation actionInvocation) {
        log.fine("Execution successful");
    }

    public Play(Service service, String str) {
        this(new UnsignedIntegerFourBytes(0L), service, str);
    }

    public Play(UnsignedIntegerFourBytes unsignedIntegerFourBytes, Service service) {
        this(unsignedIntegerFourBytes, service, DiskLruCache.VERSION_1);
    }

    public Play(UnsignedIntegerFourBytes unsignedIntegerFourBytes, Service service, String str) {
        super(new ActionInvocation(service.getAction("Play")));
        getActionInvocation().setInput("InstanceID", unsignedIntegerFourBytes);
        getActionInvocation().setInput("Speed", str);
    }
}
