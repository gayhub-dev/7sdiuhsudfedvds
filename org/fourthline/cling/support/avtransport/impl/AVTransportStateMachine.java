package org.fourthline.cling.support.avtransport.impl;

import java.net.URI;
import org.fourthline.cling.support.avtransport.impl.state.AbstractState;
import org.fourthline.cling.support.model.SeekMode;
import p090k6.InterfaceC1402a;

/* loaded from: classes.dex */
public interface AVTransportStateMachine extends InterfaceC1402a<AbstractState> {
    /* synthetic */ void forceState(Class<? extends S> cls);

    /* synthetic */ S getCurrentState();

    void next();

    void pause();

    void play(String str);

    void previous();

    void record();

    void seek(SeekMode seekMode, String str);

    void setNextTransportURI(URI uri, String str);

    void setTransportURI(URI uri, String str);

    void stop();
}
