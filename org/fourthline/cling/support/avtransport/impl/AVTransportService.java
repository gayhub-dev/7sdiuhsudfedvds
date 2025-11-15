package org.fourthline.cling.support.avtransport.impl;

import android.arch.lifecycle.C0063n;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.AVTransportErrorCode;
import org.fourthline.cling.support.avtransport.AVTransportException;
import org.fourthline.cling.support.avtransport.AbstractAVTransportService;
import org.fourthline.cling.support.avtransport.impl.state.AbstractState;
import org.fourthline.cling.support.lastchange.LastChange;
import org.fourthline.cling.support.model.AVTransport;
import org.fourthline.cling.support.model.DeviceCapabilities;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PlayMode;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.RecordQualityMode;
import org.fourthline.cling.support.model.SeekMode;
import org.fourthline.cling.support.model.StorageMedium;
import org.fourthline.cling.support.model.TransportAction;
import org.fourthline.cling.support.model.TransportInfo;
import org.fourthline.cling.support.model.TransportSettings;
import p090k6.C1403b;
import p090k6.C1405d;
import p090k6.InterfaceC1402a;
import p090k6.InterfaceC1404c;

/* loaded from: classes.dex */
public class AVTransportService<T extends AVTransport> extends AbstractAVTransportService {
    private static final Logger log = Logger.getLogger(AVTransportService.class.getName());
    public final Class<? extends AbstractState> initialState;
    public final Class<? extends AVTransportStateMachine> stateMachineDefinition;
    private final Map<Long, AVTransportStateMachine> stateMachines;
    public final Class<? extends AVTransport> transportClass;

    public AVTransportService(Class<? extends AVTransportStateMachine> cls, Class<? extends AbstractState> cls2) {
        this(cls, cls2, AVTransport.class);
    }

    public AVTransportStateMachine createStateMachine(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        Class<? extends AVTransportStateMachine> cls = this.stateMachineDefinition;
        return (AVTransportStateMachine) ((InterfaceC1402a) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C1403b(Arrays.asList(((InterfaceC1404c) cls.getAnnotation(InterfaceC1404c.class)).value()), this.initialState, new Class[]{this.transportClass}, new Object[]{createTransport(unsignedIntegerFourBytes, getLastChange())})));
    }

    public AVTransport createTransport(UnsignedIntegerFourBytes unsignedIntegerFourBytes, LastChange lastChange) {
        return new AVTransport(unsignedIntegerFourBytes, lastChange, StorageMedium.NETWORK);
    }

    public AVTransportStateMachine findStateMachine(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        return findStateMachine(unsignedIntegerFourBytes, true);
    }

    @Override // org.fourthline.cling.support.lastchange.LastChangeDelegator
    public UnsignedIntegerFourBytes[] getCurrentInstanceIds() {
        UnsignedIntegerFourBytes[] unsignedIntegerFourBytesArr;
        synchronized (this.stateMachines) {
            unsignedIntegerFourBytesArr = new UnsignedIntegerFourBytes[this.stateMachines.size()];
            int i7 = 0;
            Iterator<Long> it = this.stateMachines.keySet().iterator();
            while (it.hasNext()) {
                unsignedIntegerFourBytesArr[i7] = new UnsignedIntegerFourBytes(it.next().longValue());
                i7++;
            }
        }
        return unsignedIntegerFourBytesArr;
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public TransportAction[] getCurrentTransportActions(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        try {
            return ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getCurrentTransportActions();
        } catch (C1405d unused) {
            return new TransportAction[0];
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public DeviceCapabilities getDeviceCapabilities(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        return ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getTransport().getDeviceCapabilities();
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public MediaInfo getMediaInfo(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        return ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getTransport().getMediaInfo();
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public PositionInfo getPositionInfo(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        return ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getTransport().getPositionInfo();
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public TransportInfo getTransportInfo(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        return ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getTransport().getTransportInfo();
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public TransportSettings getTransportSettings(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        return ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getTransport().getTransportSettings();
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void next(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        try {
            findStateMachine(unsignedIntegerFourBytes).next();
        } catch (C1405d e7) {
            throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void pause(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        try {
            findStateMachine(unsignedIntegerFourBytes).pause();
        } catch (C1405d e7) {
            throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void play(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) throws AVTransportException {
        try {
            findStateMachine(unsignedIntegerFourBytes).play(str);
        } catch (C1405d e7) {
            throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void previous(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        try {
            findStateMachine(unsignedIntegerFourBytes).previous();
        } catch (C1405d e7) {
            throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void record(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        try {
            findStateMachine(unsignedIntegerFourBytes).record();
        } catch (C1405d e7) {
            throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void seek(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str, String str2) throws AVTransportException {
        try {
            try {
                findStateMachine(unsignedIntegerFourBytes).seek(SeekMode.valueOrExceptionOf(str), str2);
            } catch (C1405d e7) {
                throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
            }
        } catch (IllegalArgumentException unused) {
            throw new AVTransportException(AVTransportErrorCode.SEEKMODE_NOT_SUPPORTED, C0063n.m88a("Unsupported seek mode: ", str));
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setAVTransportURI(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str, String str2) throws AVTransportException {
        try {
            try {
                findStateMachine(unsignedIntegerFourBytes, true).setTransportURI(new URI(str), str2);
            } catch (C1405d e7) {
                throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
            }
        } catch (Exception unused) {
            throw new AVTransportException(ErrorCode.INVALID_ARGS, "CurrentURI can not be null or malformed");
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setNextAVTransportURI(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str, String str2) throws AVTransportException {
        try {
            try {
                findStateMachine(unsignedIntegerFourBytes, true).setNextTransportURI(new URI(str), str2);
            } catch (C1405d e7) {
                throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
            }
        } catch (Exception unused) {
            throw new AVTransportException(ErrorCode.INVALID_ARGS, "NextURI can not be null or malformed");
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setPlayMode(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) throws AVTransportException {
        AVTransport transport = ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getTransport();
        try {
            transport.setTransportSettings(new TransportSettings(PlayMode.valueOf(str), transport.getTransportSettings().getRecQualityMode()));
        } catch (IllegalArgumentException unused) {
            throw new AVTransportException(AVTransportErrorCode.PLAYMODE_NOT_SUPPORTED, C0063n.m88a("Unsupported play mode: ", str));
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setRecordQualityMode(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) throws AVTransportException {
        AVTransport transport = ((AbstractState) findStateMachine(unsignedIntegerFourBytes).getCurrentState()).getTransport();
        try {
            transport.setTransportSettings(new TransportSettings(transport.getTransportSettings().getPlayMode(), RecordQualityMode.valueOrExceptionOf(str)));
        } catch (IllegalArgumentException unused) {
            throw new AVTransportException(AVTransportErrorCode.RECORDQUALITYMODE_NOT_SUPPORTED, C0063n.m88a("Unsupported record quality mode: ", str));
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void stop(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        try {
            findStateMachine(unsignedIntegerFourBytes).stop();
        } catch (C1405d e7) {
            throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE, e7.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AVTransportService(Class<? extends AVTransportStateMachine> cls, Class<? extends AbstractState> cls2, Class<T> cls3) {
        this.stateMachines = new ConcurrentHashMap();
        this.stateMachineDefinition = cls;
        this.initialState = cls2;
        this.transportClass = cls3;
    }

    public AVTransportStateMachine findStateMachine(UnsignedIntegerFourBytes unsignedIntegerFourBytes, boolean z6) {
        AVTransportStateMachine aVTransportStateMachineCreateStateMachine;
        synchronized (this.stateMachines) {
            long jLongValue = unsignedIntegerFourBytes.getValue().longValue();
            aVTransportStateMachineCreateStateMachine = this.stateMachines.get(Long.valueOf(jLongValue));
            if (aVTransportStateMachineCreateStateMachine == null && jLongValue == 0 && z6) {
                log.fine("Creating default transport instance with ID '0'");
                aVTransportStateMachineCreateStateMachine = createStateMachine(unsignedIntegerFourBytes);
                this.stateMachines.put(Long.valueOf(jLongValue), aVTransportStateMachineCreateStateMachine);
            } else if (aVTransportStateMachineCreateStateMachine == null) {
                throw new AVTransportException(AVTransportErrorCode.INVALID_INSTANCE_ID);
            }
            log.fine("Found transport control with ID '" + jLongValue + "'");
        }
        return aVTransportStateMachineCreateStateMachine;
    }
}
