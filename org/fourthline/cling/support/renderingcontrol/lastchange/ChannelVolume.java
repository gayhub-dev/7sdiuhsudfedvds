package org.fourthline.cling.support.renderingcontrol.lastchange;

import org.fourthline.cling.support.model.Channel;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ChannelVolume {
    public Channel channel;
    public Integer volume;

    public ChannelVolume(Channel channel, Integer num) {
        this.channel = channel;
        this.volume = num;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public Integer getVolume() {
        return this.volume;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Volume: ");
        sbM112a.append(getVolume());
        sbM112a.append(" (");
        sbM112a.append(getChannel());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
