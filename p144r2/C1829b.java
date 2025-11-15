package p144r2;

import java.util.logging.Logger;
import org.fourthline.cling.support.connectionmanager.ConnectionManagerService;
import org.fourthline.cling.support.model.ProtocolInfo;
import org.fourthline.cling.support.model.dlna.DLNAProfiles;
import p009b.C0413b;
import p098l6.C1448b;

/* compiled from: ZxtConnectionManagerService.java */
/* renamed from: r2.b */
/* loaded from: classes.dex */
public class C1829b extends ConnectionManagerService {

    /* renamed from: a */
    public static final Logger f5313a = Logger.getLogger(C1829b.class.getName());

    public C1829b() {
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_IMAGE_JPEG)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_IMAGE_PNG)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("image/gif")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("image/bmp")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("image/pjpeg")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("image/tiff")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("image/x-ms-bmp")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_3GP)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_MPEG_4)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("video/3gp2")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_AVI)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("video/flv")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_MPEG)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_MKV)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_MATROSKA)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("video/msvideo")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("video/quicktime")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_XMS_AVI)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_VIDEO_WMV)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("audio/aac")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_AUDIO_3GP)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("audio/amr")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("audio/ogg")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_AUDIO_MPEG)));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("audio/midi")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("audio/x-midi")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("audio/x-mid")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a("audio/x-wav")));
        this.sinkProtocolInfo.add(new ProtocolInfo(C1448b.m1633a(DLNAProfiles.DLNAMimeTypes.MIME_AUDIO_WMA)));
        Logger logger = f5313a;
        StringBuilder sbM112a = C0413b.m112a("Supported MIME types: ");
        sbM112a.append(this.sinkProtocolInfo.size());
        logger.info(sbM112a.toString());
    }
}
