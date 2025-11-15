package org.fourthline.cling.support.renderingcontrol.lastchange;

import org.fourthline.cling.support.model.Channel;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ChannelLoudness {
    public Channel channel;
    public Boolean loudness;

    public ChannelLoudness(Channel channel, Boolean bool) {
        this.channel = channel;
        this.loudness = bool;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public Boolean getLoudness() {
        return this.loudness;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Loudness: ");
        sbM112a.append(getLoudness());
        sbM112a.append(" (");
        sbM112a.append(getChannel());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
