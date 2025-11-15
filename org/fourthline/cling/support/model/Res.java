package org.fourthline.cling.support.model;

import java.net.URI;
import p098l6.C1448b;

/* loaded from: classes.dex */
public class Res {
    public Long bitrate;
    public Long bitsPerSample;
    public Long colorDepth;
    public String duration;
    public URI importUri;
    public Long nrAudioChannels;
    public String protection;
    public ProtocolInfo protocolInfo;
    public String resolution;
    public Long sampleFrequency;
    public Long size;
    public String value;

    public Res() {
    }

    public Long getBitrate() {
        return this.bitrate;
    }

    public Long getBitsPerSample() {
        return this.bitsPerSample;
    }

    public Long getColorDepth() {
        return this.colorDepth;
    }

    public String getDuration() {
        return this.duration;
    }

    public URI getImportUri() {
        return this.importUri;
    }

    public Long getNrAudioChannels() {
        return this.nrAudioChannels;
    }

    public String getProtection() {
        return this.protection;
    }

    public ProtocolInfo getProtocolInfo() {
        return this.protocolInfo;
    }

    public String getResolution() {
        return this.resolution;
    }

    public int getResolutionX() {
        if (getResolution() == null || getResolution().split("x").length != 2) {
            return 0;
        }
        return Integer.valueOf(getResolution().split("x")[0]).intValue();
    }

    public int getResolutionY() {
        if (getResolution() == null || getResolution().split("x").length != 2) {
            return 0;
        }
        return Integer.valueOf(getResolution().split("x")[1]).intValue();
    }

    public Long getSampleFrequency() {
        return this.sampleFrequency;
    }

    public Long getSize() {
        return this.size;
    }

    public String getValue() {
        return this.value;
    }

    public void setBitrate(Long l7) {
        this.bitrate = l7;
    }

    public void setBitsPerSample(Long l7) {
        this.bitsPerSample = l7;
    }

    public void setColorDepth(Long l7) {
        this.colorDepth = l7;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public void setImportUri(URI uri) {
        this.importUri = uri;
    }

    public void setNrAudioChannels(Long l7) {
        this.nrAudioChannels = l7;
    }

    public void setProtection(String str) {
        this.protection = str;
    }

    public void setProtocolInfo(ProtocolInfo protocolInfo) {
        this.protocolInfo = protocolInfo;
    }

    public void setResolution(String str) {
        this.resolution = str;
    }

    public void setSampleFrequency(Long l7) {
        this.sampleFrequency = l7;
    }

    public void setSize(Long l7) {
        this.size = l7;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public Res(String str, Long l7, String str2, Long l8, String str3) {
        this(new ProtocolInfo(Protocol.HTTP_GET, "*", str, "*"), l7, str2, l8, str3);
    }

    public void setResolution(int i7, int i8) {
        this.resolution = i7 + "x" + i8;
    }

    public Res(C1448b c1448b, Long l7, String str, Long l8, String str2) {
        this(new ProtocolInfo(c1448b), l7, str, l8, str2);
    }

    public Res(C1448b c1448b, Long l7, String str) {
        this(new ProtocolInfo(c1448b), l7, str);
    }

    public Res(ProtocolInfo protocolInfo, Long l7, String str) {
        this.protocolInfo = protocolInfo;
        this.size = l7;
        this.value = str;
    }

    public Res(ProtocolInfo protocolInfo, Long l7, String str, Long l8, String str2) {
        this.protocolInfo = protocolInfo;
        this.size = l7;
        this.duration = str;
        this.bitrate = l8;
        this.value = str2;
    }

    public Res(URI uri, ProtocolInfo protocolInfo, Long l7, String str, Long l8, Long l9, Long l10, Long l11, Long l12, String str2, String str3, String str4) {
        this.importUri = uri;
        this.protocolInfo = protocolInfo;
        this.size = l7;
        this.duration = str;
        this.bitrate = l8;
        this.sampleFrequency = l9;
        this.bitsPerSample = l10;
        this.nrAudioChannels = l11;
        this.colorDepth = l12;
        this.protection = str2;
        this.resolution = str3;
        this.value = str4;
    }
}
