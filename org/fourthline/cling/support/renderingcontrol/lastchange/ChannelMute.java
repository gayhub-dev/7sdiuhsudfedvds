package org.fourthline.cling.support.renderingcontrol.lastchange;

import org.fourthline.cling.support.model.Channel;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ChannelMute {
    public Channel channel;
    public Boolean mute;

    public ChannelMute(Channel channel, Boolean bool) {
        this.channel = channel;
        this.mute = bool;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public Boolean getMute() {
        return this.mute;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Mute: ");
        sbM112a.append(getMute());
        sbM112a.append(" (");
        sbM112a.append(getChannel());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
