package org.fourthline.cling.support.model;

import android.support.v7.widget.ActivityChooserView;
import java.util.Map;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.action.ActionArgumentValue;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PositionInfo {
    private int absCount;
    private String absTime;
    private String item_id;
    private String live_time;
    private int relCount;
    private String relTime;
    private UnsignedIntegerFourBytes track;
    private String trackDuration;
    private String trackMetaData;
    private String trackURI;
    private String vod_time;

    public PositionInfo() {
        this.track = new UnsignedIntegerFourBytes(0L);
        this.trackDuration = "00:00:00";
        this.trackMetaData = "NOT_IMPLEMENTED";
        this.trackURI = "";
        this.relTime = "00:00:00";
        this.absTime = "00:00:00";
        this.relCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.absCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    public int getAbsCount() {
        return this.absCount;
    }

    public String getAbsTime() {
        return this.absTime;
    }

    public int getElapsedPercent() {
        long trackElapsedSeconds = getTrackElapsedSeconds();
        long trackDurationSeconds = getTrackDurationSeconds();
        if (trackElapsedSeconds == 0 || trackDurationSeconds == 0) {
            return 0;
        }
        return new Double(trackElapsedSeconds / (trackDurationSeconds / 100.0d)).intValue();
    }

    public String getItem_id() {
        return this.item_id;
    }

    public String getLive_time() {
        return this.live_time;
    }

    public int getRelCount() {
        return this.relCount;
    }

    public String getRelTime() {
        return this.relTime;
    }

    public UnsignedIntegerFourBytes getTrack() {
        return this.track;
    }

    public String getTrackDuration() {
        return this.trackDuration;
    }

    public long getTrackDurationSeconds() {
        if (getTrackDuration() == null) {
            return 0L;
        }
        return ModelUtil.fromTimeString(getTrackDuration());
    }

    public long getTrackElapsedSeconds() {
        if (getRelTime() == null || getRelTime().equals("NOT_IMPLEMENTED")) {
            return 0L;
        }
        return ModelUtil.fromTimeString(getRelTime());
    }

    public String getTrackMetaData() {
        return this.trackMetaData;
    }

    public long getTrackRemainingSeconds() {
        return getTrackDurationSeconds() - getTrackElapsedSeconds();
    }

    public String getTrackURI() {
        return this.trackURI;
    }

    public String getVod_time() {
        return this.vod_time;
    }

    public void setItem_id(String str) {
        this.item_id = str;
    }

    public void setLive_time(String str) {
        this.live_time = str;
    }

    public void setRelTime(String str) {
        this.relTime = str;
    }

    public void setTrackDuration(String str) {
        this.trackDuration = str;
    }

    public void setVod_time(String str) {
        this.vod_time = str;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(PositionInfo) Track: ");
        sbM112a.append(getTrack());
        sbM112a.append(" RelTime: ");
        sbM112a.append(getRelTime());
        sbM112a.append(" Duration: ");
        sbM112a.append(getTrackDuration());
        sbM112a.append(" Percent: ");
        sbM112a.append(getElapsedPercent());
        return sbM112a.toString();
    }

    public PositionInfo(Map<String, ActionArgumentValue> map) {
        this(((UnsignedIntegerFourBytes) map.get("Track").getValue()).getValue().longValue(), (String) map.get("TrackDuration").getValue(), (String) map.get("TrackMetaData").getValue(), (String) map.get("TrackURI").getValue(), (String) map.get("RelTime").getValue(), (String) map.get("AbsTime").getValue(), ((Integer) map.get("RelCount").getValue()).intValue(), ((Integer) map.get("AbsCount").getValue()).intValue());
    }

    public PositionInfo(PositionInfo positionInfo, String str, String str2) {
        this.track = new UnsignedIntegerFourBytes(0L);
        this.trackDuration = "00:00:00";
        this.trackMetaData = "NOT_IMPLEMENTED";
        this.trackURI = "";
        this.relTime = "00:00:00";
        this.absTime = "00:00:00";
        this.relCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.absCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.track = positionInfo.track;
        this.trackDuration = positionInfo.trackDuration;
        this.trackMetaData = positionInfo.trackMetaData;
        this.trackURI = positionInfo.trackURI;
        this.relTime = str;
        this.absTime = str2;
        this.relCount = positionInfo.relCount;
        this.absCount = positionInfo.absCount;
    }

    public PositionInfo(PositionInfo positionInfo, long j7, long j8) {
        this.track = new UnsignedIntegerFourBytes(0L);
        this.trackDuration = "00:00:00";
        this.trackMetaData = "NOT_IMPLEMENTED";
        this.trackURI = "";
        this.relTime = "00:00:00";
        this.absTime = "00:00:00";
        this.relCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.absCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.track = positionInfo.track;
        this.trackDuration = positionInfo.trackDuration;
        this.trackMetaData = positionInfo.trackMetaData;
        this.trackURI = positionInfo.trackURI;
        this.relTime = ModelUtil.toTimeString(j7);
        this.absTime = ModelUtil.toTimeString(j8);
        this.relCount = positionInfo.relCount;
        this.absCount = positionInfo.absCount;
    }

    public PositionInfo(long j7, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.track = new UnsignedIntegerFourBytes(0L);
        this.trackDuration = "00:00:00";
        this.trackMetaData = "NOT_IMPLEMENTED";
        this.trackURI = "";
        this.relTime = "00:00:00";
        this.absTime = "00:00:00";
        this.relCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.absCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.track = new UnsignedIntegerFourBytes(j7);
        this.trackDuration = str;
        this.trackURI = str2;
        this.relTime = str3;
        this.absTime = str4;
        this.vod_time = str5;
        this.live_time = str6;
        this.item_id = str7;
    }

    public PositionInfo(long j7, String str, String str2, String str3, String str4, String str5, int i7, int i8) {
        this.track = new UnsignedIntegerFourBytes(0L);
        this.trackDuration = "00:00:00";
        this.trackMetaData = "NOT_IMPLEMENTED";
        this.trackURI = "";
        this.relTime = "00:00:00";
        this.absTime = "00:00:00";
        this.relCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.absCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.track = new UnsignedIntegerFourBytes(j7);
        this.trackDuration = str;
        this.trackMetaData = str2;
        this.trackURI = str3;
        this.relTime = str4;
        this.absTime = str5;
        this.relCount = i7;
        this.absCount = i8;
    }

    public PositionInfo(long j7, String str, String str2) {
        this.track = new UnsignedIntegerFourBytes(0L);
        this.trackDuration = "00:00:00";
        this.trackMetaData = "NOT_IMPLEMENTED";
        this.trackURI = "";
        this.relTime = "00:00:00";
        this.absTime = "00:00:00";
        this.relCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.absCount = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.track = new UnsignedIntegerFourBytes(j7);
        this.trackMetaData = str;
        this.trackURI = str2;
    }
}
