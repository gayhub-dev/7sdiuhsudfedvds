package tv.danmaku.ijk.media.player;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import p009b.C0413b;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.annotations.AccessedByNative;
import tv.danmaku.ijk.media.player.annotations.CalledByNative;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataQueue;
import tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataTimeStamp;
import tv.danmaku.ijk.media.player.misc.IAndroidIO;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
import tv.danmaku.ijk.media.player.pragma.DebugLog;

/* loaded from: classes.dex */
public final class IjkMediaPlayer extends AbstractMediaPlayer {
    public static final int FFP_PROPV_DECODER_AVCODEC = 1;
    public static final int FFP_PROPV_DECODER_MEDIACODEC = 2;
    public static final int FFP_PROPV_DECODER_UNKNOWN = 0;
    public static final int FFP_PROPV_DECODER_VIDEOTOOLBOX = 3;
    public static final int FFP_PROP_FLOAT_AUDIO_SPEED = 10008;
    public static final int FFP_PROP_FLOAT_DROP_FRAME_RATE = 10007;
    public static final int FFP_PROP_FLOAT_PLAYBACK_RATE = 10003;
    public static final int FFP_PROP_FLOAT_VIDEO_SPEED = 10009;
    public static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_BACKWARDS = 20201;
    public static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_CAPACITY = 20203;
    public static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_FORWARDS = 20202;
    public static final int FFP_PROP_INT64_AUDIO_CACHED_BYTES = 20008;
    public static final int FFP_PROP_INT64_AUDIO_CACHED_DURATION = 20006;
    public static final int FFP_PROP_INT64_AUDIO_CACHED_PACKETS = 20010;
    public static final int FFP_PROP_INT64_AUDIO_DECODER = 20004;
    public static final int FFP_PROP_INT64_BIT_RATE = 20100;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_COUNT_BYTES = 20208;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_FILE_FORWARDS = 20206;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_FILE_POS = 20207;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_PHYSICAL_POS = 20205;
    public static final int FFP_PROP_INT64_IMMEDIATE_RECONNECT = 20211;
    public static final int FFP_PROP_INT64_LATEST_SEEK_LOAD_DURATION = 20300;
    public static final int FFP_PROP_INT64_LOGICAL_FILE_SIZE = 20209;
    public static final int FFP_PROP_INT64_SELECTED_AUDIO_STREAM = 20002;
    public static final int FFP_PROP_INT64_SELECTED_TIMEDTEXT_STREAM = 20011;
    public static final int FFP_PROP_INT64_SELECTED_VIDEO_STREAM = 20001;
    public static final int FFP_PROP_INT64_SHARE_CACHE_DATA = 20210;
    public static final int FFP_PROP_INT64_TCP_SPEED = 20200;
    public static final int FFP_PROP_INT64_TRAFFIC_STATISTIC_BYTE_COUNT = 20204;
    public static final int FFP_PROP_INT64_VIDEO_CACHED_BYTES = 20007;
    public static final int FFP_PROP_INT64_VIDEO_CACHED_DURATION = 20005;
    public static final int FFP_PROP_INT64_VIDEO_CACHED_PACKETS = 20009;
    public static final int FFP_PROP_INT64_VIDEO_DECODER = 20003;
    public static final int IJK_LOG_DEBUG = 3;
    public static final int IJK_LOG_DEFAULT = 1;
    public static final int IJK_LOG_ERROR = 6;
    public static final int IJK_LOG_FATAL = 7;
    public static final int IJK_LOG_INFO = 4;
    public static final int IJK_LOG_SILENT = 8;
    public static final int IJK_LOG_UNKNOWN = 0;
    public static final int IJK_LOG_VERBOSE = 2;
    public static final int IJK_LOG_WARN = 5;
    private static final int MEDIA_BUFFERING_UPDATE = 3;
    private static final int MEDIA_ERROR = 100;
    private static final int MEDIA_INFO = 200;
    private static final int MEDIA_NOP = 0;
    private static final int MEDIA_PLAYBACK_COMPLETE = 2;
    private static final int MEDIA_PREPARED = 1;
    private static final int MEDIA_SEEK_COMPLETE = 4;
    private static final int MEDIA_SEI_DATA = 201;
    public static final int MEDIA_SET_VIDEO_SAR = 10001;
    private static final int MEDIA_SET_VIDEO_SIZE = 5;
    private static final int MEDIA_TIMED_TEXT = 99;
    private static final int MSG_AV3A_METADATA_CHANGED = 200001;
    private static final int MSG_CHINADRM_ERROR = 30000;
    private static final int MSG_CHINADRM_ERROR_MARK = 30100;
    private static final int MSG_MPEG_TS_PES_PRIVATE_DATA = 200002;
    private static final int MSG_VIDEO_FRAME_RENDERED = 200003;
    public static final int OPT_CATEGORY_CODEC = 2;
    public static final int OPT_CATEGORY_FORMAT = 1;
    public static final int OPT_CATEGORY_PLAYER = 4;
    public static final int OPT_CATEGORY_SWS = 3;
    public static final int PROP_FLOAT_VIDEO_DECODE_FRAMES_PER_SECOND = 10001;
    public static final int PROP_FLOAT_VIDEO_OUTPUT_FRAMES_PER_SECOND = 10002;
    public static final int SDL_FCC_RV16 = 909203026;
    public static final int SDL_FCC_RV32 = 842225234;
    public static final int SDL_FCC_YV12 = 842094169;
    public static final int SDL_FCC__GLES2 = 844318047;
    private static final String TAG = "tv.danmaku.ijk.media.player.IjkMediaPlayer";
    private boolean enableHdrVivid;
    private Surface hdrVividProcessSurface;
    private SurfaceTexture hdrVividSurfaceTexture;
    private String mDataSource;
    private EventHandler mEventHandler;

    @AccessedByNative
    private int mListenerContext;

    @AccessedByNative
    private long mNativeAndroidIO;

    @AccessedByNative
    private long mNativeMediaDataSource;

    @AccessedByNative
    private long mNativeMediaPlayer;

    @AccessedByNative
    private int mNativeSurfaceTexture;
    private OnControlMessageListener mOnControlMessageListener;
    private OnMediaCodecSelectListener mOnMediaCodecSelectListener;
    private OnNativeInvokeListener mOnNativeInvokeListener;
    private boolean mScreenOnWhilePlaying;
    private boolean mStayAwake;
    private SurfaceHolder mSurfaceHolder;
    private int mVideoHeight;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private PowerManager.WakeLock mWakeLock;
    private Surface mediaOutputSurface;
    private final PesPrivateDataQueue pesPrivateDataQueue;
    private static final IjkLibLoader sLocalLibLoader = new IjkLibLoader() { // from class: tv.danmaku.ijk.media.player.IjkMediaPlayer.1
        @Override // tv.danmaku.ijk.media.player.IjkLibLoader
        public void loadLibrary(String str) {
            System.loadLibrary(str);
        }
    };
    private static volatile boolean mIsLibLoaded = false;
    private static volatile boolean mIsNativeInitialized = false;

    public static class DefaultMediaCodecSelector implements OnMediaCodecSelectListener {
        public static final DefaultMediaCodecSelector sInstance = new DefaultMediaCodecSelector();

        @Override // tv.danmaku.ijk.media.player.IjkMediaPlayer.OnMediaCodecSelectListener
        @TargetApi(16)
        public String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i7, int i8) {
            String[] supportedTypes;
            IjkMediaCodecInfo ijkMediaCodecInfo;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String unused = IjkMediaPlayer.TAG;
            String.format(Locale.US, "onSelectCodec: mime=%s, profile=%d, level=%d", str, Integer.valueOf(i7), Integer.valueOf(i8));
            ArrayList arrayList = new ArrayList();
            int codecCount = MediaCodecList.getCodecCount();
            for (int i9 = 0; i9 < codecCount; i9++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i9);
                String unused2 = IjkMediaPlayer.TAG;
                String.format(Locale.US, "  found codec: %s", codecInfoAt.getName());
                if (!codecInfoAt.isEncoder() && (supportedTypes = codecInfoAt.getSupportedTypes()) != null) {
                    for (String str2 : supportedTypes) {
                        if (!TextUtils.isEmpty(str2)) {
                            String unused3 = IjkMediaPlayer.TAG;
                            Locale locale = Locale.US;
                            String.format(locale, "    mime: %s", str2);
                            if (str2.equalsIgnoreCase(str) && (ijkMediaCodecInfo = IjkMediaCodecInfo.setupCandidate(codecInfoAt, str)) != null) {
                                arrayList.add(ijkMediaCodecInfo);
                                String unused4 = IjkMediaPlayer.TAG;
                                String.format(locale, "candidate codec: %s rank=%d", codecInfoAt.getName(), Integer.valueOf(ijkMediaCodecInfo.mRank));
                                ijkMediaCodecInfo.dumpProfileLevels(str);
                            }
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            IjkMediaCodecInfo ijkMediaCodecInfo2 = (IjkMediaCodecInfo) arrayList.get(0);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                IjkMediaCodecInfo ijkMediaCodecInfo3 = (IjkMediaCodecInfo) it.next();
                if (ijkMediaCodecInfo3.mRank > ijkMediaCodecInfo2.mRank) {
                    ijkMediaCodecInfo2 = ijkMediaCodecInfo3;
                }
            }
            if (ijkMediaCodecInfo2.mRank < 600) {
                String unused5 = IjkMediaPlayer.TAG;
                String.format(Locale.US, "unaccetable codec: %s", ijkMediaCodecInfo2.mCodecInfo.getName());
                return null;
            }
            String unused6 = IjkMediaPlayer.TAG;
            String.format(Locale.US, "selected codec: %s rank=%d", ijkMediaCodecInfo2.mCodecInfo.getName(), Integer.valueOf(ijkMediaCodecInfo2.mRank));
            return ijkMediaCodecInfo2.mCodecInfo.getName();
        }
    }

    public static class EventHandler extends Handler {
        private final WeakReference<IjkMediaPlayer> mWeakPlayer;

        public EventHandler(IjkMediaPlayer ijkMediaPlayer, Looper looper) {
            super(looper);
            this.mWeakPlayer = new WeakReference<>(ijkMediaPlayer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            IjkMediaPlayer ijkMediaPlayer = this.mWeakPlayer.get();
            if (ijkMediaPlayer != null) {
                if (ijkMediaPlayer.mNativeMediaPlayer != 0) {
                    int i7 = message.what;
                    if (i7 != 0) {
                        if (i7 == 1) {
                            ijkMediaPlayer.notifyOnPrepared();
                            return;
                        }
                        if (i7 == 2) {
                            ijkMediaPlayer.stayAwake(false);
                            ijkMediaPlayer.notifyOnCompletion();
                            return;
                        }
                        if (i7 == 3) {
                            long j7 = message.arg1;
                            if (j7 < 0) {
                                j7 = 0;
                            }
                            long duration = ijkMediaPlayer.getDuration();
                            long j8 = duration > 0 ? (j7 * 100) / duration : 0L;
                            ijkMediaPlayer.notifyOnBufferingUpdate((int) (j8 < 100 ? j8 : 100L));
                            return;
                        }
                        if (i7 == 4) {
                            ijkMediaPlayer.notifyOnSeekComplete();
                            return;
                        }
                        if (i7 == 5) {
                            ijkMediaPlayer.mVideoWidth = message.arg1;
                            ijkMediaPlayer.mVideoHeight = message.arg2;
                            ijkMediaPlayer.notifyOnVideoSizeChanged(ijkMediaPlayer.mVideoWidth, ijkMediaPlayer.mVideoHeight, ijkMediaPlayer.mVideoSarNum, ijkMediaPlayer.mVideoSarDen);
                            return;
                        }
                        if (i7 == 99) {
                            if (message.obj == null) {
                                ijkMediaPlayer.notifyOnTimedText(null);
                                return;
                            } else {
                                ijkMediaPlayer.notifyOnTimedText(new IjkTimedText(new Rect(0, 0, 1, 1), (String) message.obj));
                                return;
                            }
                        }
                        if (i7 == 100) {
                            String str = IjkMediaPlayer.TAG;
                            StringBuilder sbM112a = C0413b.m112a("Error (");
                            sbM112a.append(message.arg1);
                            sbM112a.append(",");
                            sbM112a.append(message.arg2);
                            sbM112a.append(")");
                            DebugLog.m2285e(str, sbM112a.toString());
                            if (!ijkMediaPlayer.notifyOnError(message.arg1, message.arg2)) {
                                ijkMediaPlayer.notifyOnCompletion();
                            }
                            ijkMediaPlayer.stayAwake(false);
                            return;
                        }
                        if (i7 == 200) {
                            if (message.arg1 == 3) {
                                DebugLog.m2287i(IjkMediaPlayer.TAG, "Info: MEDIA_INFO_VIDEO_RENDERING_START\n");
                            }
                            ijkMediaPlayer.notifyOnInfo(message.arg1, Integer.valueOf(message.arg2));
                            return;
                        }
                        if (i7 == IjkMediaPlayer.MEDIA_SEI_DATA) {
                            ijkMediaPlayer.notifyOnSEIRefresh(message.arg1, message.arg2);
                            return;
                        }
                        if (i7 == 10001) {
                            ijkMediaPlayer.mVideoSarNum = message.arg1;
                            ijkMediaPlayer.mVideoSarDen = message.arg2;
                            ijkMediaPlayer.notifyOnVideoSizeChanged(ijkMediaPlayer.mVideoWidth, ijkMediaPlayer.mVideoHeight, ijkMediaPlayer.mVideoSarNum, ijkMediaPlayer.mVideoSarDen);
                            return;
                        }
                        if (i7 == IjkMediaPlayer.MSG_CHINADRM_ERROR) {
                            String str2 = IjkMediaPlayer.TAG;
                            StringBuilder sbM112a2 = C0413b.m112a("ChinadrmError (");
                            sbM112a2.append(message.arg1);
                            sbM112a2.append(",");
                            sbM112a2.append(message.arg2);
                            sbM112a2.append(")");
                            DebugLog.m2285e(str2, sbM112a2.toString());
                            if (!ijkMediaPlayer.notifyOnError(message.arg1, IjkMediaPlayer.MSG_CHINADRM_ERROR_MARK)) {
                                ijkMediaPlayer.notifyOnCompletion();
                            }
                            ijkMediaPlayer.stayAwake(false);
                            return;
                        }
                        switch (i7) {
                            case IjkMediaPlayer.MSG_AV3A_METADATA_CHANGED /* 200001 */:
                                ijkMediaPlayer.notifyOnAudioVividMetadataChanged();
                                break;
                            case IjkMediaPlayer.MSG_MPEG_TS_PES_PRIVATE_DATA /* 200002 */:
                                ijkMediaPlayer.pesPrivateDataQueue.onParsePesPrivateData((message.arg1 << 32) + message.arg2, (byte[]) message.obj);
                                break;
                            case IjkMediaPlayer.MSG_VIDEO_FRAME_RENDERED /* 200003 */:
                                ijkMediaPlayer.pesPrivateDataQueue.onVideoFrameRendered((message.arg1 << 32) + message.arg2);
                                break;
                            default:
                                String str3 = IjkMediaPlayer.TAG;
                                StringBuilder sbM112a3 = C0413b.m112a("Unknown message type ");
                                sbM112a3.append(message.what);
                                DebugLog.m2285e(str3, sbM112a3.toString());
                                break;
                        }
                        return;
                    }
                    return;
                }
            }
            DebugLog.m2291w(IjkMediaPlayer.TAG, "IjkMediaPlayer went away with unhandled events");
        }
    }

    public interface OnControlMessageListener {
        String onControlResolveSegmentUrl(int i7);
    }

    public interface OnMediaCodecSelectListener {
        String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i7, int i8);
    }

    public interface OnNativeInvokeListener {
        public static final String ARG_ERROR = "error";
        public static final String ARG_FAMILIY = "family";
        public static final String ARG_FD = "fd";
        public static final String ARG_FILE_SIZE = "file_size";
        public static final String ARG_HTTP_CODE = "http_code";
        public static final String ARG_IP = "ip";
        public static final String ARG_OFFSET = "offset";
        public static final String ARG_PORT = "port";
        public static final String ARG_RETRY_COUNTER = "retry_counter";
        public static final String ARG_SEGMENT_INDEX = "segment_index";
        public static final String ARG_URL = "url";
        public static final int CTRL_DID_TCP_OPEN = 131074;
        public static final int CTRL_WILL_CONCAT_RESOLVE_SEGMENT = 131079;
        public static final int CTRL_WILL_HTTP_OPEN = 131075;
        public static final int CTRL_WILL_LIVE_OPEN = 131077;
        public static final int CTRL_WILL_TCP_OPEN = 131073;
        public static final int EVENT_DID_HTTP_OPEN = 2;
        public static final int EVENT_DID_HTTP_SEEK = 4;
        public static final int EVENT_WILL_HTTP_OPEN = 1;
        public static final int EVENT_WILL_HTTP_SEEK = 3;

        boolean onNativeInvoke(int i7, Bundle bundle);
    }

    public IjkMediaPlayer() {
        this(sLocalLibLoader);
    }

    private native String _getAudioCodecInfo();

    private static native String _getColorFormatName(int i7);

    private native int _getLoopCount();

    private native Bundle _getMediaMeta();

    private native float _getPropertyFloat(int i7, float f7);

    private native long _getPropertyLong(int i7, long j7);

    private native String _getVideoCodecInfo();

    private native void _pause();

    private native void _release();

    private native void _reset();

    private native void _setAndroidIOCallback(IAndroidIO iAndroidIO);

    private native void _setDataSource(String str, String[] strArr, String[] strArr2);

    private native void _setDataSource(IMediaDataSource iMediaDataSource);

    private native void _setDataSourceFd(int i7);

    private native void _setFrameAtTime(String str, long j7, long j8, int i7, int i8);

    private native void _setGyro(float f7, float f8, float f9);

    private native void _setLoopCount(int i7);

    private native void _setOption(int i7, String str, long j7);

    private native void _setOption(int i7, String str, String str2);

    private native void _setPropertyFloat(int i7, float f7);

    private native void _setPropertyLong(int i7, long j7);

    private native void _setStreamSelected(int i7, boolean z6);

    private native int _setStreamSelectedPLV(int i7, boolean z6);

    private native void _setVideoSurface(Surface surface);

    private native void _start();

    private native void _stop();

    public static String getColorFormatName(int i7) {
        return _getColorFormatName(i7);
    }

    private static void initNativeOnce() {
        synchronized (IjkMediaPlayer.class) {
            if (!mIsNativeInitialized) {
                native_init();
                mIsNativeInitialized = true;
            }
        }
    }

    private void initPlayer(IjkLibLoader ijkLibLoader) {
        loadLibrariesOnce(ijkLibLoader);
        initNativeOnce();
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mEventHandler = new EventHandler(this, looperMyLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(this, mainLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        native_setup(new WeakReference(this));
        prepareHdrVividSurfaceTexture();
        observePesTimeStamp();
    }

    public static void loadLibrariesOnce(IjkLibLoader ijkLibLoader) {
        synchronized (IjkMediaPlayer.class) {
            if (!mIsLibLoaded) {
                if (ijkLibLoader == null) {
                    ijkLibLoader = sLocalLibLoader;
                }
                ijkLibLoader.loadLibrary("ijkffmpeg");
                ijkLibLoader.loadLibrary("ijksdl");
                ijkLibLoader.loadLibrary("ijkplayer");
                mIsLibLoaded = true;
            }
        }
    }

    private native void native_finalize();

    private native int native_generateHdrVividTextureId();

    private native float native_getAv3aMetadataFloat(int i7, int i8);

    private native int native_getAv3aMetadataInt(int i7, int i8);

    private native String native_getAv3aMetadataString(int i7, int i8);

    /* JADX INFO: Access modifiers changed from: private */
    public native void native_hdrVividUpdateTexture();

    private static native void native_init();

    private native void native_message_loop(Object obj);

    public static native void native_profileBegin(String str);

    public static native void native_profileEnd();

    private native int native_setAv3aMetadataFloat(int i7, int i8, float f7);

    private native void native_setHdrVividEnable(boolean z6);

    private native void native_setHdrVividInputSurface(Surface surface);

    private native void native_setHdrVividOutputSurface(Surface surface);

    public static native void native_setLogLevel(int i7);

    private native void native_setup(Object obj);

    private void observePesTimeStamp() {
        this.pesPrivateDataQueue.setOnTimeStampListener(new PesPrivateDataQueue.OnTimeStampListener() { // from class: tv.danmaku.ijk.media.player.IjkMediaPlayer.3
            @Override // tv.danmaku.ijk.media.player.format.mpegts.PesPrivateDataQueue.OnTimeStampListener
            public void onCurrentTimeStamp(PesPrivateDataTimeStamp pesPrivateDataTimeStamp) {
                IjkMediaPlayer.this.notifyOnMpegTsPesPrivateData(pesPrivateDataTimeStamp);
            }
        });
    }

    @CalledByNative
    private long onGetTexImageInfo() {
        SurfaceTexture surfaceTexture = this.hdrVividSurfaceTexture;
        if (surfaceTexture == null) {
            return 0L;
        }
        surfaceTexture.updateTexImage();
        return this.hdrVividSurfaceTexture.getTimestamp();
    }

    @CalledByNative
    private static boolean onNativeInvoke(Object obj, int i7, Bundle bundle) {
        OnControlMessageListener onControlMessageListener;
        DebugLog.ifmt(TAG, "onNativeInvoke %d", Integer.valueOf(i7));
        if (obj == null || !(obj instanceof WeakReference)) {
            throw new IllegalStateException("<null weakThiz>.onNativeInvoke()");
        }
        IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get();
        if (ijkMediaPlayer == null) {
            throw new IllegalStateException("<null weakPlayer>.onNativeInvoke()");
        }
        OnNativeInvokeListener onNativeInvokeListener = ijkMediaPlayer.mOnNativeInvokeListener;
        if (onNativeInvokeListener != null && onNativeInvokeListener.onNativeInvoke(i7, bundle)) {
            return true;
        }
        if (i7 != 131079 || (onControlMessageListener = ijkMediaPlayer.mOnControlMessageListener) == null) {
            return false;
        }
        int i8 = bundle.getInt(OnNativeInvokeListener.ARG_SEGMENT_INDEX, -1);
        if (i8 < 0) {
            throw new InvalidParameterException("onNativeInvoke(invalid segment index)");
        }
        String strOnControlResolveSegmentUrl = onControlMessageListener.onControlResolveSegmentUrl(i8);
        if (strOnControlResolveSegmentUrl == null) {
            throw new RuntimeException(new IOException("onNativeInvoke() = <NULL newUrl>"));
        }
        bundle.putString(OnNativeInvokeListener.ARG_URL, strOnControlResolveSegmentUrl);
        return true;
    }

    @CalledByNative
    private static String onSelectCodec(Object obj, String str, int i7, int i8) {
        IjkMediaPlayer ijkMediaPlayer;
        if (obj == null || !(obj instanceof WeakReference) || (ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get()) == null) {
            return null;
        }
        OnMediaCodecSelectListener onMediaCodecSelectListener = ijkMediaPlayer.mOnMediaCodecSelectListener;
        if (onMediaCodecSelectListener == null) {
            onMediaCodecSelectListener = DefaultMediaCodecSelector.sInstance;
        }
        return onMediaCodecSelectListener.onMediaCodecSelect(ijkMediaPlayer, str, i7, i8);
    }

    @CalledByNative
    private static void postEventFromNative(Object obj, int i7, int i8, int i9, Object obj2) {
        IjkMediaPlayer ijkMediaPlayer;
        if (obj == null || (ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get()) == null) {
            return;
        }
        if (i7 == 200 && i8 == 2) {
            ijkMediaPlayer.start();
        }
        EventHandler eventHandler = ijkMediaPlayer.mEventHandler;
        if (eventHandler != null) {
            ijkMediaPlayer.mEventHandler.sendMessage(eventHandler.obtainMessage(i7, i8, i9, obj2));
        }
    }

    private void prepareHdrVividSurfaceTexture() {
        int iNative_generateHdrVividTextureId = native_generateHdrVividTextureId();
        if (iNative_generateHdrVividTextureId <= 0) {
            return;
        }
        SurfaceTexture surfaceTexture = new SurfaceTexture(iNative_generateHdrVividTextureId);
        this.hdrVividSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: tv.danmaku.ijk.media.player.IjkMediaPlayer.2
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                IjkMediaPlayer.this.native_hdrVividUpdateTexture();
            }
        });
        this.hdrVividProcessSurface = new Surface(this.hdrVividSurfaceTexture);
    }

    private void setVideoSurface(Surface surface) {
        this.mediaOutputSurface = surface;
        updateSurface();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"Wakelock"})
    public void stayAwake(boolean z6) {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (z6 && !wakeLock.isHeld()) {
                this.mWakeLock.acquire();
            } else if (!z6 && this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
        }
        this.mStayAwake = z6;
        updateSurfaceScreenOn();
    }

    private void updateSurfaceScreenOn() {
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.setKeepScreenOn(this.mScreenOnWhilePlaying && this.mStayAwake);
        }
    }

    public native void _prepareAsync();

    public void deselectTrack(int i7) {
        _setStreamSelected(i7, false);
    }

    public void finalize() throws Throwable {
        super.finalize();
        native_finalize();
    }

    public long getAsyncStatisticBufBackwards() {
        return _getPropertyLong(FFP_PROP_INT64_ASYNC_STATISTIC_BUF_BACKWARDS, 0L);
    }

    public long getAsyncStatisticBufCapacity() {
        return _getPropertyLong(FFP_PROP_INT64_ASYNC_STATISTIC_BUF_CAPACITY, 0L);
    }

    public long getAsyncStatisticBufForwards() {
        return _getPropertyLong(FFP_PROP_INT64_ASYNC_STATISTIC_BUF_FORWARDS, 0L);
    }

    public long getAudioCachedBytes() {
        return _getPropertyLong(FFP_PROP_INT64_AUDIO_CACHED_BYTES, 0L);
    }

    public long getAudioCachedDuration() {
        return _getPropertyLong(FFP_PROP_INT64_AUDIO_CACHED_DURATION, 0L);
    }

    public long getAudioCachedPackets() {
        return _getPropertyLong(FFP_PROP_INT64_AUDIO_CACHED_PACKETS, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public float getAudioFrameSpeed() {
        return _getPropertyFloat(10008, 1.0f);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native int getAudioSessionId();

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public float getAv3aMetadataFloat(int i7, int i8) {
        return native_getAv3aMetadataFloat(i7, i8);
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public int getAv3aMetadataInt(int i7, int i8) {
        return native_getAv3aMetadataInt(i7, i8);
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public String getAv3aMetadataString(int i7, int i8) {
        return native_getAv3aMetadataString(i7, i8);
    }

    public long getBitRate() {
        return _getPropertyLong(FFP_PROP_INT64_BIT_RATE, 0L);
    }

    public long getCacheStatisticCountBytes() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_COUNT_BYTES, 0L);
    }

    public long getCacheStatisticFileForwards() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_FILE_FORWARDS, 0L);
    }

    public long getCacheStatisticFilePos() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_FILE_POS, 0L);
    }

    public long getCacheStatisticPhysicalPos() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_PHYSICAL_POS, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native long getCurrentPosition();

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public String getDataSource() {
        return this.mDataSource;
    }

    public float getDropFrameRate() {
        return _getPropertyFloat(10007, 0.0f);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native long getDuration();

    public long getFileSize() {
        return _getPropertyLong(FFP_PROP_INT64_LOGICAL_FILE_SIZE, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.mMediaPlayerName = "ijkplayer";
        String str_getVideoCodecInfo = _getVideoCodecInfo();
        if (!TextUtils.isEmpty(str_getVideoCodecInfo)) {
            String[] strArrSplit = str_getVideoCodecInfo.split(",");
            if (strArrSplit.length >= 2) {
                mediaInfo.mVideoDecoder = strArrSplit[0];
                mediaInfo.mVideoDecoderImpl = strArrSplit[1];
            } else if (strArrSplit.length >= 1) {
                mediaInfo.mVideoDecoder = strArrSplit[0];
                mediaInfo.mVideoDecoderImpl = "";
            }
        }
        String str_getAudioCodecInfo = _getAudioCodecInfo();
        if (!TextUtils.isEmpty(str_getAudioCodecInfo)) {
            String[] strArrSplit2 = str_getAudioCodecInfo.split(",");
            if (strArrSplit2.length >= 2) {
                mediaInfo.mAudioDecoder = strArrSplit2[0];
                mediaInfo.mAudioDecoderImpl = strArrSplit2[1];
            } else if (strArrSplit2.length >= 1) {
                mediaInfo.mAudioDecoder = strArrSplit2[0];
                mediaInfo.mAudioDecoderImpl = "";
            }
        }
        try {
            mediaInfo.mMeta = IjkMediaMeta.parse(_getMediaMeta());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return mediaInfo;
    }

    public Bundle getMediaMeta() {
        return _getMediaMeta();
    }

    public long getSeekLoadDuration() {
        return _getPropertyLong(FFP_PROP_INT64_LATEST_SEEK_LOAD_DURATION, 0L);
    }

    public int getSelectedTrack(int i7) {
        long j_getPropertyLong;
        if (i7 == 1) {
            j_getPropertyLong = _getPropertyLong(FFP_PROP_INT64_SELECTED_VIDEO_STREAM, -1L);
        } else if (i7 == 2) {
            j_getPropertyLong = _getPropertyLong(FFP_PROP_INT64_SELECTED_AUDIO_STREAM, -1L);
        } else {
            if (i7 != 3) {
                return -1;
            }
            j_getPropertyLong = _getPropertyLong(FFP_PROP_INT64_SELECTED_TIMEDTEXT_STREAM, -1L);
        }
        return (int) j_getPropertyLong;
    }

    public float getSpeed(float f7) {
        return _getPropertyFloat(10003, 0.0f);
    }

    public long getTcpSpeed() {
        return _getPropertyLong(FFP_PROP_INT64_TCP_SPEED, 0L);
    }

    public long getTrafficStatisticByteCount() {
        return _getPropertyLong(FFP_PROP_INT64_TRAFFIC_STATISTIC_BYTE_COUNT, 0L);
    }

    public long getVideoCachedBytes() {
        return _getPropertyLong(FFP_PROP_INT64_VIDEO_CACHED_BYTES, 0L);
    }

    public long getVideoCachedDuration() {
        return _getPropertyLong(FFP_PROP_INT64_VIDEO_CACHED_DURATION, 0L);
    }

    public long getVideoCachedPackets() {
        return _getPropertyLong(FFP_PROP_INT64_VIDEO_CACHED_PACKETS, 0L);
    }

    public float getVideoDecodeFramesPerSecond() {
        return _getPropertyFloat(10001, 0.0f);
    }

    public int getVideoDecoder() {
        return (int) _getPropertyLong(FFP_PROP_INT64_VIDEO_DECODER, 0L);
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public float getVideoFrameSpeed() {
        return _getPropertyFloat(10009, 1.0f);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public float getVideoOutputFramesPerSecond() {
        return _getPropertyFloat(10002, 0.0f);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoSarDen() {
        return this.mVideoSarDen;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoSarNum() {
        return this.mVideoSarNum;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public void httphookReconnect() {
        _setPropertyLong(FFP_PROP_INT64_IMMEDIATE_RECONNECT, 1L);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public boolean isLooping() {
        return _getLoopCount() != 1;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public boolean isPlayable() {
        return true;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native boolean isPlaying();

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void pause() {
        stayAwake(false);
        _pause();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void prepareAsync() {
        _prepareAsync();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void release() {
        stayAwake(false);
        updateSurfaceScreenOn();
        resetListeners();
        _release();
        SurfaceTexture surfaceTexture = this.hdrVividSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.hdrVividSurfaceTexture = null;
        }
        this.mEventHandler.removeCallbacksAndMessages(null);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void reset() {
        stayAwake(false);
        _reset();
        this.mEventHandler.removeCallbacksAndMessages(null);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer
    public void resetListeners() {
        super.resetListeners();
        this.mOnMediaCodecSelectListener = null;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native void seekTo(long j7);

    public void selectTrack(int i7) {
        _setStreamSelected(i7, true);
    }

    public int selectTrackPLV(int i7) {
        return _setStreamSelectedPLV(i7, true);
    }

    public void setAndroidIOCallback(IAndroidIO iAndroidIO) {
        _setAndroidIOCallback(iAndroidIO);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setAudioStreamType(int i7) {
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public int setAv3aMetadataFloat(int i7, int i8, float f7) {
        return native_setAv3aMetadataFloat(i7, i8, f7);
    }

    public void setCacheShare(int i7) {
        _setPropertyLong(FFP_PROP_INT64_SHARE_CACHE_DATA, i7);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(Context context, Uri uri) throws IOException {
        setDataSource(context, uri, (Map<String, String>) null);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        setVideoSurface(surfaceHolder != null ? surfaceHolder.getSurface() : null);
        updateSurfaceScreenOn();
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void setEnableHdrVivid(boolean z6) {
        this.enableHdrVivid = z6;
    }

    public void setGyro(float f7, float f8, float f9) {
        _setGyro(f7, f8, f9);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setKeepInBackground(boolean z6) {
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setLogEnabled(boolean z6) {
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setLooping(boolean z6) {
        int i7 = !z6 ? 1 : 0;
        setOption(4, "loop", i7);
        _setLoopCount(i7);
    }

    public void setOnControlMessageListener(OnControlMessageListener onControlMessageListener) {
        this.mOnControlMessageListener = onControlMessageListener;
    }

    public void setOnMediaCodecSelectListener(OnMediaCodecSelectListener onMediaCodecSelectListener) {
        this.mOnMediaCodecSelectListener = onMediaCodecSelectListener;
    }

    public void setOnNativeInvokeListener(OnNativeInvokeListener onNativeInvokeListener) {
        this.mOnNativeInvokeListener = onNativeInvokeListener;
    }

    public void setOption(int i7, String str, String str2) {
        _setOption(i7, str, str2);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setScreenOnWhilePlaying(boolean z6) {
        if (this.mScreenOnWhilePlaying != z6) {
            if (z6 && this.mSurfaceHolder == null) {
                DebugLog.m2291w(TAG, "setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder");
            }
            this.mScreenOnWhilePlaying = z6;
            updateSurfaceScreenOn();
        }
    }

    public void setSpeed(float f7) {
        _setPropertyFloat(10003, f7);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setSurface(Surface surface) {
        if (this.mScreenOnWhilePlaying && surface != null) {
            DebugLog.m2291w(TAG, "setScreenOnWhilePlaying(true) is ineffective for Surface");
        }
        this.mSurfaceHolder = null;
        setVideoSurface(surface);
        updateSurfaceScreenOn();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public native void setVolume(float f7, float f8);

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @SuppressLint({"Wakelock"})
    public void setWakeMode(Context context, int i7) {
        boolean z6;
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                z6 = true;
                this.mWakeLock.release();
            } else {
                z6 = false;
            }
            this.mWakeLock = null;
        } else {
            z6 = false;
        }
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i7 | 536870912, IjkMediaPlayer.class.getName());
        this.mWakeLock = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        if (z6) {
            this.mWakeLock.acquire();
        }
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void start() {
        stayAwake(true);
        _start();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void stop() {
        stayAwake(false);
        _stop();
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void updateSurface() {
        Surface surface;
        if (!this.enableHdrVivid || (surface = this.hdrVividProcessSurface) == null) {
            _setVideoSurface(this.mediaOutputSurface);
            return;
        }
        _setVideoSurface(surface);
        native_setHdrVividInputSurface(this.hdrVividProcessSurface);
        native_setHdrVividOutputSurface(this.mediaOutputSurface);
    }

    public IjkMediaPlayer(IjkLibLoader ijkLibLoader) {
        this.pesPrivateDataQueue = new PesPrivateDataQueue();
        this.mWakeLock = null;
        this.enableHdrVivid = false;
        this.hdrVividSurfaceTexture = null;
        this.mediaOutputSurface = null;
        this.hdrVividProcessSurface = null;
        initPlayer(ijkLibLoader);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public IjkTrackInfo[] getTrackInfo() {
        IjkMediaMeta ijkMediaMeta;
        Bundle mediaMeta = getMediaMeta();
        if (mediaMeta == null || (ijkMediaMeta = IjkMediaMeta.parse(mediaMeta)) == null || ijkMediaMeta.mStreams == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<IjkMediaMeta.IjkStreamMeta> it = ijkMediaMeta.mStreams.iterator();
        while (it.hasNext()) {
            IjkMediaMeta.IjkStreamMeta next = it.next();
            IjkTrackInfo ijkTrackInfo = new IjkTrackInfo(next);
            if (next.mType.equalsIgnoreCase("video")) {
                ijkTrackInfo.setTrackType(1);
            } else if (next.mType.equalsIgnoreCase("audio")) {
                ijkTrackInfo.setTrackType(2);
            } else if (next.mType.equalsIgnoreCase("timedtext")) {
                ijkTrackInfo.setTrackType(3);
            }
            arrayList.add(ijkTrackInfo);
        }
        return (IjkTrackInfo[]) arrayList.toArray(new IjkTrackInfo[arrayList.size()]);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0082  */
    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @android.annotation.TargetApi(14)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setDataSource(android.content.Context r8, android.net.Uri r9, java.util.Map<java.lang.String, java.lang.String> r10) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = r9.getScheme()
            java.lang.String r1 = "file"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L14
            java.lang.String r8 = r9.getPath()
            r7.setDataSource(r8)
            return
        L14:
            java.lang.String r1 = "content"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L3b
            java.lang.String r0 = r9.getAuthority()
            java.lang.String r1 = "settings"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L3b
            int r9 = android.media.RingtoneManager.getDefaultType(r9)
            android.net.Uri r9 = android.media.RingtoneManager.getActualDefaultRingtoneUri(r8, r9)
            if (r9 == 0) goto L33
            goto L3b
        L33:
            java.io.FileNotFoundException r8 = new java.io.FileNotFoundException
            java.lang.String r9 = "Failed to resolve default ringtone"
            r8.<init>(r9)
            throw r8
        L3b:
            r0 = 0
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            java.lang.String r1 = "r"
            android.content.res.AssetFileDescriptor r0 = r8.openAssetFileDescriptor(r9, r1)     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            if (r0 != 0) goto L4e
            if (r0 == 0) goto L4d
            r0.close()
        L4d:
            return
        L4e:
            long r1 = r0.getDeclaredLength()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            r3 = 0
            int r8 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r8 >= 0) goto L60
            java.io.FileDescriptor r8 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            r7.setDataSource(r8)     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            goto L70
        L60:
            java.io.FileDescriptor r2 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            long r3 = r0.getStartOffset()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            long r5 = r0.getDeclaredLength()     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
            r1 = r7
            r1.setDataSource(r2, r3, r5)     // Catch: java.lang.Throwable -> L74 java.io.IOException -> L7b java.lang.SecurityException -> L7f
        L70:
            r0.close()
            return
        L74:
            r8 = move-exception
            if (r0 == 0) goto L7a
            r0.close()
        L7a:
            throw r8
        L7b:
            if (r0 == 0) goto L85
            goto L82
        L7f:
            if (r0 == 0) goto L85
        L82:
            r0.close()
        L85:
            java.lang.String r8 = r9.toString()
            r7.setDataSource(r8, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.player.IjkMediaPlayer.setDataSource(android.content.Context, android.net.Uri, java.util.Map):void");
    }

    public void setOption(int i7, String str, long j7) {
        _setOption(i7, str, j7);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(String str) {
        this.mDataSource = str;
        _setDataSource(str, null, null);
    }

    public void setDataSource(String str, Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append(":");
                if (!TextUtils.isEmpty(entry.getValue())) {
                    sb.append(entry.getValue());
                }
                sb.append("\r\n");
                setOption(1, "headers", sb.toString());
                setOption(1, "protocol_whitelist", "async,cache,crypto,file,http,https,ijkhttphook,ijkinject,ijklivehook,ijklongurl,ijksegment,ijktcphook,pipe,rtp,tcp,tls,udp,ijkurlhook,data");
            }
        }
        setDataSource(str);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer
    @TargetApi(13)
    public void setDataSource(FileDescriptor fileDescriptor) throws IOException {
        ParcelFileDescriptor parcelFileDescriptorDup = ParcelFileDescriptor.dup(fileDescriptor);
        try {
            _setDataSourceFd(parcelFileDescriptorDup.getFd());
        } finally {
            parcelFileDescriptorDup.close();
        }
    }

    private void setDataSource(FileDescriptor fileDescriptor, long j7, long j8) throws IOException {
        setDataSource(fileDescriptor);
    }

    @Override // tv.danmaku.ijk.media.player.AbstractMediaPlayer, tv.danmaku.ijk.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) {
        _setDataSource(iMediaDataSource);
    }
}
