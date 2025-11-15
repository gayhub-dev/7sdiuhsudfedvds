package p144r2;

import android.arch.lifecycle.C0063n;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.cctv.p025tv.module.collect.C0580b;
import com.cctv.p025tv.mvp.p026ui.fragment.VersionUpdateFragment;
import com.cctv.p025tv.mvp.p026ui.fragment.VideoFragment;
import com.ctvit.dlna.entity.CctvEntity;
import com.ctvit.dlna.entity.DlnaContentEntity;
import com.tencent.mars.xlog.Log;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.support.avtransport.AVTransportErrorCode;
import org.fourthline.cling.support.avtransport.AVTransportException;
import org.fourthline.cling.support.avtransport.AbstractAVTransportService;
import org.fourthline.cling.support.lastchange.LastChange;
import org.fourthline.cling.support.model.DeviceCapabilities;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PlayMode;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.SeekMode;
import org.fourthline.cling.support.model.StorageMedium;
import org.fourthline.cling.support.model.TransportAction;
import org.fourthline.cling.support.model.TransportInfo;
import org.fourthline.cling.support.model.TransportSettings;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import p054g2.RunnableC1054e;
import p078j2.C1186a;
import p118o2.C1581b;
import p151s2.C1869a;
import p186x2.C2073a;

/* compiled from: AVTransportService.java */
/* renamed from: r2.a */
/* loaded from: classes.dex */
public class C1828a extends AbstractAVTransportService {

    /* renamed from: b */
    public static final Logger f5311b = Logger.getLogger(C1828a.class.getName());

    /* renamed from: a */
    public final Map<UnsignedIntegerFourBytes, C1830c> f5312a;

    public C1828a(LastChange lastChange, Map<UnsignedIntegerFourBytes, C1830c> map) {
        super(lastChange);
        this.f5312a = map;
    }

    /* renamed from: a */
    public C1830c m2067a(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        C1830c c1830c = this.f5312a.get(unsignedIntegerFourBytes);
        if (c1830c != null) {
            return c1830c;
        }
        throw new AVTransportException(AVTransportErrorCode.INVALID_INSTANCE_ID);
    }

    @Override // org.fourthline.cling.support.lastchange.LastChangeDelegator
    public UnsignedIntegerFourBytes[] getCurrentInstanceIds() {
        UnsignedIntegerFourBytes[] unsignedIntegerFourBytesArr = new UnsignedIntegerFourBytes[this.f5312a.size()];
        int i7 = 0;
        Iterator<UnsignedIntegerFourBytes> it = this.f5312a.keySet().iterator();
        while (it.hasNext()) {
            unsignedIntegerFourBytesArr[i7] = it.next();
            i7++;
        }
        return unsignedIntegerFourBytesArr;
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public TransportAction[] getCurrentTransportActions(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        return m2067a(unsignedIntegerFourBytes).m2068a();
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public DeviceCapabilities getDeviceCapabilities(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        m2067a(unsignedIntegerFourBytes);
        return new DeviceCapabilities(new StorageMedium[]{StorageMedium.NETWORK});
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public MediaInfo getMediaInfo(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        MediaInfo mediaInfo;
        C1830c c1830cM2067a = m2067a(unsignedIntegerFourBytes);
        synchronized (c1830cM2067a) {
            mediaInfo = c1830cM2067a.f5320f;
        }
        return mediaInfo;
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public PositionInfo getPositionInfo(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        PositionInfo positionInfo;
        C1830c c1830cM2067a = m2067a(unsignedIntegerFourBytes);
        synchronized (c1830cM2067a) {
            positionInfo = c1830cM2067a.f5319e;
        }
        return positionInfo;
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public TransportInfo getTransportInfo(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        TransportInfo transportInfo;
        C1830c c1830cM2067a = m2067a(unsignedIntegerFourBytes);
        synchronized (c1830cM2067a) {
            transportInfo = c1830cM2067a.f5318d;
        }
        return transportInfo;
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public TransportSettings getTransportSettings(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        m2067a(unsignedIntegerFourBytes);
        return new TransportSettings(PlayMode.NORMAL);
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void next(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        f5311b.info("### TODO: Not implemented: Next");
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void pause(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        m2067a(unsignedIntegerFourBytes);
        if (C1581b.m1836e().f4749e != null) {
            VideoFragment videoFragment = C1581b.m1836e().f4749e.f1992e;
            int i7 = VideoFragment.f854B;
            Objects.requireNonNull(videoFragment);
            C2073a.m2459d("DLNA 暂停指令");
            Log.m655i("XLog_DLNA ", "fragment 暂停指令");
            if (videoFragment.getActivity() == null || !C1186a.m1389i()) {
                return;
            }
            videoFragment.getActivity().runOnUiThread(new RunnableC1054e(videoFragment, 2));
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void play(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) throws AVTransportException {
        m2067a(unsignedIntegerFourBytes);
        if (C1581b.m1836e().f4750f != null) {
            VideoFragment videoFragment = C1581b.m1836e().f4750f.f1992e;
            int i7 = VideoFragment.f854B;
            Objects.requireNonNull(videoFragment);
            C2073a.m2459d("DLNA 继续播放指令");
            Log.m655i("XLog_DLNA ", "fragment 继续播放指令");
            if (videoFragment.getActivity() == null || !C1186a.m1389i()) {
                return;
            }
            videoFragment.getActivity().runOnUiThread(new RunnableC1054e(videoFragment, 0));
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void previous(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        f5311b.info("### TODO: Not implemented: Previous");
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void record(UnsignedIntegerFourBytes unsignedIntegerFourBytes) {
        f5311b.info("### TODO: Not implemented: Record");
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void seek(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str, String str2) throws AVTransportException, NumberFormatException {
        try {
            if (!SeekMode.valueOrExceptionOf(str).equals(SeekMode.REL_TIME)) {
                throw new IllegalArgumentException();
            }
            String str3 = C1869a.f5449a;
            int i7 = 0;
            if (str2.indexOf(":") > 0) {
                String[] strArrSplit = str2.split(":");
                i7 = (Integer.parseInt(strArrSplit[0]) * 3600) + (Integer.parseInt(strArrSplit[1]) * 60) + Integer.parseInt(strArrSplit[2]);
            }
            m2067a(unsignedIntegerFourBytes).m2070c(i7 * 1000);
        } catch (IllegalArgumentException unused) {
            throw new AVTransportException(AVTransportErrorCode.SEEKMODE_NOT_SUPPORTED, C0063n.m88a("Unsupported seek mode: ", str));
        }
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setAVTransportURI(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str, String str2) throws XmlPullParserException, IOException {
        C2073a.m2459d("收到投屏数据...");
        Log.m655i("XLog_DLNA ", "收到投屏数据...");
        if (TextUtils.isEmpty(str2)) {
            C2073a.m2456a("currentURIMetaData is null");
            Log.m651e("XLog_DLNA ", "currentURIMetaData is null");
            return;
        }
        if (C1581b.m1832a() == null) {
            C2073a.m2456a("CtvitDlna.getDlnaClientSendDataListener() is null");
            Log.m651e("XLog_DLNA ", "CtvitDlna.getDlnaClientSendDataListener() is null");
            return;
        }
        String strReplace = str2.replace("&", "&amp;").replace(";amp;", ";");
        C2073a.m2459d("DLNA Server MetaData=" + strReplace);
        C2073a.m2459d("DLNA Server currentURI=" + str);
        Log.m655i("XLog_DLNA ", "DLNA Server MetaData=" + strReplace);
        Log.m655i("XLog_DLNA ", "DLNA Server currentURI=" + str);
        try {
            if (((CctvEntity) JSON.parseObject(strReplace, CctvEntity.class)).getEvent() != null) {
                C2073a.m2459d("自定义的投屏数据");
                Log.m655i("XLog_DLNA ", "自定义的投屏数据");
                DlnaContentEntity dlnaContentEntity = new DlnaContentEntity();
                dlnaContentEntity.setCctv(strReplace);
                C1581b.m1832a().mo318a(dlnaContentEntity);
                return;
            }
        } catch (Exception unused) {
        }
        C2073a.m2459d("DLNA 标准的投屏数据");
        Log.m655i("XLog_DLNA ", "DLNA 标准的投屏数据");
        DlnaContentEntity dlnaContentEntity2 = new DlnaContentEntity();
        try {
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(strReplace));
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if ("item".equals(name)) {
                        dlnaContentEntity2.setId(xmlPullParserNewPullParser.getAttributeValue(null, "id"));
                    } else if ("res".equals(name)) {
                        dlnaContentEntity2.setProtocolInfo(xmlPullParserNewPullParser.getAttributeValue(null, "protocolInfo"));
                        dlnaContentEntity2.setUrl(xmlPullParserNewPullParser.nextText());
                    } else if ("cctv".equals(name)) {
                        dlnaContentEntity2.setCctv(xmlPullParserNewPullParser.nextText());
                    } else if ("dc:title".equals(name)) {
                        dlnaContentEntity2.setName(xmlPullParserNewPullParser.nextText());
                    } else if ("upnp:creator".equals(name)) {
                        dlnaContentEntity2.setCreator(xmlPullParserNewPullParser.nextText());
                        if (TextUtils.isEmpty(dlnaContentEntity2.getCreator())) {
                            dlnaContentEntity2.setCreator(xmlPullParserNewPullParser.getAttributeValue(null, "artist"));
                        }
                    }
                }
            }
        } catch (Exception e7) {
            C2073a.m2458c(e7);
        }
        if (!TextUtils.isEmpty(str)) {
            dlnaContentEntity2.setUrl(str);
        }
        C2073a.m2459d(dlnaContentEntity2.toString());
        try {
            m2067a(unsignedIntegerFourBytes).m2071d(new URI(str), strReplace);
        } catch (Exception e8) {
            C2073a.m2458c(e8);
        }
        C1581b.m1832a().mo318a(dlnaContentEntity2);
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setNextAVTransportURI(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str, String str2) {
        f5311b.info("### TODO: Not implemented: SetNextAVTransportURI");
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setPlayMode(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) {
        f5311b.info("### TODO: Not implemented: SetPlayMode");
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void setRecordQualityMode(UnsignedIntegerFourBytes unsignedIntegerFourBytes, String str) {
        f5311b.info("### TODO: Not implemented: SetRecordQualityMode");
    }

    @Override // org.fourthline.cling.support.avtransport.AbstractAVTransportService
    public void stop(UnsignedIntegerFourBytes unsignedIntegerFourBytes) throws AVTransportException {
        m2067a(unsignedIntegerFourBytes);
        if (C1581b.m1836e().f4751g != null) {
            VideoFragment videoFragment = C1581b.m1836e().f4751g.f1992e;
            int i7 = VideoFragment.f854B;
            Objects.requireNonNull(videoFragment);
            C2073a.m2459d("DLNA 退出指令");
            Log.m655i("XLog_DLNA ", "fragment 退出指令");
            if (C1186a.m1389i()) {
                if (videoFragment.getActivity() == null || videoFragment.getActivity().getSupportFragmentManager() == null) {
                    C2073a.m2456a("DLNA 退出指令 - 终止");
                    return;
                }
                if (VersionUpdateFragment.f849l) {
                    C2073a.m2459d("更新页显示中 - Video - 退出指令");
                    return;
                }
                videoFragment.m508l().m441D();
                videoFragment.m508l().m446I("EXIT", 0L);
                C0580b.m417c("DLNA_EXIT", videoFragment.getClass().getSimpleName());
                videoFragment.getActivity().runOnUiThread(new RunnableC1054e(videoFragment, 1));
            }
        }
    }
}
