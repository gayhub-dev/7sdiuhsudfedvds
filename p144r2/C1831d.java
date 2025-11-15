package p144r2;

import android.content.Context;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.lastchange.LastChange;
import org.fourthline.cling.support.model.TransportState;
import p009b.C0413b;

/* compiled from: ZxtMediaPlayers.java */
/* renamed from: r2.d */
/* loaded from: classes.dex */
public class C1831d extends ConcurrentHashMap<UnsignedIntegerFourBytes, C1830c> {

    /* renamed from: f */
    public static final Logger f5326f = Logger.getLogger(C1831d.class.getName());

    /* renamed from: e */
    public Context f5327e;

    /* compiled from: ZxtMediaPlayers.java */
    /* renamed from: r2.d$a */
    public class a extends C1830c {
        public a(UnsignedIntegerFourBytes unsignedIntegerFourBytes, Context context, LastChange lastChange, LastChange lastChange2) {
            super(unsignedIntegerFourBytes, context, lastChange, lastChange2);
        }

        @Override // p144r2.C1830c
        /* renamed from: f */
        public void mo2073f(TransportState transportState) {
            super.mo2073f(transportState);
            if (transportState.equals(TransportState.PLAYING)) {
                C1831d.this.mo2079a(this);
            } else if (transportState.equals(TransportState.STOPPED)) {
                C1831d.this.mo2080b(this);
            }
        }
    }

    public C1831d(int i7, Context context, LastChange lastChange, LastChange lastChange2) {
        super(i7);
        this.f5327e = context;
        for (int i8 = 0; i8 < i7; i8++) {
            a aVar = new a(new UnsignedIntegerFourBytes(i8), this.f5327e, lastChange, lastChange2);
            put(aVar.f5315a, aVar);
        }
    }

    /* renamed from: a */
    public void mo2079a(C1830c c1830c) {
        Logger logger = f5326f;
        StringBuilder sbM112a = C0413b.m112a("Player is playing: ");
        sbM112a.append(c1830c.f5315a);
        logger.fine(sbM112a.toString());
    }

    /* renamed from: b */
    public void mo2080b(C1830c c1830c) {
        Logger logger = f5326f;
        StringBuilder sbM112a = C0413b.m112a("Player is stopping: ");
        sbM112a.append(c1830c.f5315a);
        logger.fine(sbM112a.toString());
    }
}
