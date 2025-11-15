package com.ctvit.dlna.moudle.dmr;

import android.arch.lifecycle.C0063n;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.model.types.UnsignedIntegerTwoBytes;
import org.fourthline.cling.support.lastchange.LastChange;
import org.fourthline.cling.support.model.Channel;
import org.fourthline.cling.support.renderingcontrol.AbstractAudioRenderingControl;
import org.fourthline.cling.support.renderingcontrol.RenderingControlErrorCode;
import org.fourthline.cling.support.renderingcontrol.RenderingControlException;
import p043f.C0988e;
import p144r2.C1830c;
import p186x2.C2073a;
import p200z2.C2150a;

/* loaded from: classes.dex */
public class AudioRenderingControl extends AbstractAudioRenderingControl {
    private static final Logger log = Logger.getLogger(AudioRenderingControl.class.getName());
    private final Map<UnsignedIntegerFourBytes, C1830c> players;

    public AudioRenderingControl(LastChange lastChange, Map<UnsignedIntegerFourBytes, C1830c> map) {
        super(lastChange);
        this.players = map;
    }

    public void checkChannel(String str) throws RenderingControlException {
        if (!getChannel(str).equals(Channel.Master)) {
            throw new RenderingControlException(ErrorCode.ARGUMENT_VALUE_INVALID, C0063n.m88a("Unsupported audio channel: ", str));
        }
    }

    @Override // org.fourthline.cling.support.renderingcontrol.AbstractAudioRenderingControl
    public Channel[] getCurrentChannels() {
        return new Channel[]{Channel.Master};
    }

    @Override // org.fourthline.cling.support.lastchange.LastChangeDelegator
    public UnsignedIntegerFourBytes[] getCurrentInstanceIds() {
        UnsignedIntegerFourBytes[] unsignedIntegerFourBytesArr = new UnsignedIntegerFourBytes[getPlayers().size()];
        Iterator<UnsignedIntegerFourBytes> it = getPlayers().keySet().iterator();
        int i7 = 0;
        while (it.hasNext()) {
            unsignedIntegerFourBytesArr[i7] = it.next();
            i7++;
        }
        return unsignedIntegerFourBytesArr;
    }

    public C1830c getInstance(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws RenderingControlException {
        C1830c c1830c = getPlayers().get(unsignedIntegerFourBytes);
        if (c1830c != null) {
            return c1830c;
        }
        throw new RenderingControlException(RenderingControlErrorCode.INVALID_INSTANCE_ID);
    }

    @Override // org.fourthline.cling.support.renderingcontrol.AbstractAudioRenderingControl
    public boolean getMute(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) throws RenderingControlException {
        checkChannel(str);
        return getInstance(unsignedIntegerFourBytes).m2069b() == 0.0d;
    }

    public Map<UnsignedIntegerFourBytes, C1830c> getPlayers() {
        return this.players;
    }

    @Override // org.fourthline.cling.support.renderingcontrol.AbstractAudioRenderingControl
    public UnsignedIntegerTwoBytes getVolume(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) throws RenderingControlException {
        checkChannel(str);
        double dM2069b = getInstance(unsignedIntegerFourBytes).m2069b();
        int i7 = (int) (100.0d * dM2069b);
        log.info(dM2069b + "Getting backend volume: " + i7);
        return new UnsignedIntegerTwoBytes(i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    @Override // org.fourthline.cling.support.renderingcontrol.AbstractAudioRenderingControl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setMute(org.fourthline.cling.model.types.UnsignedIntegerFourBytes r5, java.lang.String r6, boolean r7) throws org.fourthline.cling.support.renderingcontrol.RenderingControlException {
        /*
            r4 = this;
            r4.checkChannel(r6)
            java.util.logging.Logger r6 = com.ctvit.dlna.moudle.dmr.AudioRenderingControl.log
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Setting backend mute to: "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            r6.fine(r0)
            r2.c r5 = r4.getInstance(r5)
            monitor-enter(r5)
            r0 = 0
            if (r7 == 0) goto L37
            double r2 = r5.m2069b()     // Catch: java.lang.Throwable -> L35
            int r6 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r6 <= 0) goto L37
            java.util.logging.Logger r6 = p144r2.C1830c.f5314j     // Catch: java.lang.Throwable -> L35
            java.lang.String r7 = "Switching mute ON"
            r6.fine(r7)     // Catch: java.lang.Throwable -> L35
            r5.m2072e(r0)     // Catch: java.lang.Throwable -> L35
            goto L61
        L35:
            r6 = move-exception
            goto L5f
        L37:
            if (r7 != 0) goto L61
            double r6 = r5.m2069b()     // Catch: java.lang.Throwable -> L35
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 != 0) goto L61
            java.util.logging.Logger r6 = p144r2.C1830c.f5314j     // Catch: java.lang.Throwable -> L35
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L35
            r7.<init>()     // Catch: java.lang.Throwable -> L35
            java.lang.String r0 = "Switching mute OFF, restoring: "
            r7.append(r0)     // Catch: java.lang.Throwable -> L35
            double r0 = r5.f5321g     // Catch: java.lang.Throwable -> L35
            r7.append(r0)     // Catch: java.lang.Throwable -> L35
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L35
            r6.fine(r7)     // Catch: java.lang.Throwable -> L35
            double r6 = r5.f5321g     // Catch: java.lang.Throwable -> L35
            r5.m2072e(r6)     // Catch: java.lang.Throwable -> L35
            goto L61
        L5f:
            monitor-exit(r5)
            throw r6
        L61:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ctvit.dlna.moudle.dmr.AudioRenderingControl.setMute(org.fourthline.cling.model.types.UnsignedIntegerFourBytes, java.lang.String, boolean):void");
    }

    @Override // org.fourthline.cling.support.renderingcontrol.AbstractAudioRenderingControl
    public void setVolume(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str, UnsignedIntegerTwoBytes unsignedIntegerTwoBytes) throws RenderingControlException {
        checkChannel(str);
        long jLongValue = unsignedIntegerTwoBytes.getValue().longValue();
        double dDoubleValue = jLongValue / 100.0d;
        boolean z6 = true;
        if ((dDoubleValue + "").length() > 3) {
            dDoubleValue = new BigDecimal(dDoubleValue).setScale(1, 1).doubleValue();
        }
        log.info(jLongValue + "Setting backend volume to: " + dDoubleValue);
        StringBuilder sb = new StringBuilder();
        sb.append("vol = ");
        sb.append(dDoubleValue);
        C2073a.m2459d(sb.toString());
        C2073a.m2459d("value = " + jLongValue);
        if (!C0988e.m996v() && ((Boolean) C2150a.m2590a("VIDEO_VISIBLE", Boolean.FALSE)).booleanValue()) {
            z6 = false;
        }
        if (z6) {
            return;
        }
        getInstance(unsignedIntegerFourBytes).m2072e(dDoubleValue);
    }
}
