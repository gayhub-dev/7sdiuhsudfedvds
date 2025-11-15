package org.fourthline.cling.support.renderingcontrol.lastchange;

import org.fourthline.cling.support.model.Channel;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ChannelVolumeDB {
    public Channel channel;
    public Integer volumeDB;

    public ChannelVolumeDB(Channel channel, Integer num) {
        this.channel = channel;
        this.volumeDB = num;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public Integer getVolumeDB() {
        return this.volumeDB;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("VolumeDB: ");
        sbM112a.append(getVolumeDB());
        sbM112a.append(" (");
        sbM112a.append(getChannel());
        sbM112a.append(")");
        return sbM112a.toString();
    }
}
