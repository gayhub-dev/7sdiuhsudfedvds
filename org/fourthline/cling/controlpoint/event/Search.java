package org.fourthline.cling.controlpoint.event;

import org.fourthline.cling.model.message.header.MXHeader;
import org.fourthline.cling.model.message.header.STAllHeader;
import org.fourthline.cling.model.message.header.UpnpHeader;

/* loaded from: classes.dex */
public class Search {
    public int mxSeconds;
    public UpnpHeader searchType;

    public Search() {
        this.searchType = new STAllHeader();
        this.mxSeconds = MXHeader.DEFAULT_VALUE.intValue();
    }

    public int getMxSeconds() {
        return this.mxSeconds;
    }

    public UpnpHeader getSearchType() {
        return this.searchType;
    }

    public Search(UpnpHeader upnpHeader) {
        this.searchType = new STAllHeader();
        this.mxSeconds = MXHeader.DEFAULT_VALUE.intValue();
        this.searchType = upnpHeader;
    }

    public Search(UpnpHeader upnpHeader, int i7) {
        this.searchType = new STAllHeader();
        this.mxSeconds = MXHeader.DEFAULT_VALUE.intValue();
        this.searchType = upnpHeader;
        this.mxSeconds = i7;
    }

    public Search(int i7) {
        this.searchType = new STAllHeader();
        this.mxSeconds = MXHeader.DEFAULT_VALUE.intValue();
        this.mxSeconds = i7;
    }
}
