package tv.danmaku.ijk.media.player.av3a;

import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes.dex */
public class AudioVividMetaDataHandler {
    private final WeakReference<IjkMediaPlayer> mediaPlayer;

    public static class MetaDataType<T> {
        private final Class<T> clazz;
        private final int type;
        public static final MetaDataType<Integer> AV3A_STATIC_META_CONTROL_CONTENT_NUMS = new MetaDataType<>(Integer.class, 10001);
        public static final MetaDataType<Integer> AV3A_STATIC_META_CONTROL_OBJECT_NUMS = new MetaDataType<>(Integer.class, 10002);
        public static final MetaDataType<Integer> AV3A_STATIC_META_CONTROL_PACK_NUMS = new MetaDataType<>(Integer.class, 10003);
        public static final MetaDataType<Integer> AV3A_STATIC_META_CONTROL_CHANNEL_NUMS = new MetaDataType<>(Integer.class, IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START);
        public static final MetaDataType<String> AV3A_STATIC_META_CONTROL_OBJECT_NAME = new MetaDataType<>(String.class, IMediaPlayer.MEDIA_INFO_OPEN_INPUT);
        public static final MetaDataType<Integer> AV3A_STATIC_META_CONTROL_OBJECT_INTERACTION = new MetaDataType<>(Integer.class, IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO);
        public static final MetaDataType<Integer> AV3A_STATIC_META_CONTROL_OBJECT_MUTE = new MetaDataType<>(Integer.class, 10007);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_GAIN_MIN = new MetaDataType<>(Float.class, 10008);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_GAIN_MAX = new MetaDataType<>(Float.class, 10009);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_AZIMUTH_MIN = new MetaDataType<>(Float.class, 10010);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_AZIMUTH_MAX = new MetaDataType<>(Float.class, 10011);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_ELEVATION_MIN = new MetaDataType<>(Float.class, 10012);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_ELEVATION_MAX = new MetaDataType<>(Float.class, 10013);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_DISTANCE_MIN = new MetaDataType<>(Float.class, 10014);
        public static final MetaDataType<Float> AV3A_STATIC_META_CONTROL_OBJECT_DISTANCE_MAX = new MetaDataType<>(Float.class, 10015);
        public static final MetaDataType<Integer> AV3A_DYNAMIC_META_CONTROL_CHANNEL_NUMS = new MetaDataType<>(Integer.class, IjkMediaPlayer.FFP_PROP_INT64_SELECTED_VIDEO_STREAM);
        public static final MetaDataType<Float> AV3A_DYNAMIC_L1_META_CONTROL_CHANNEL_GAIN = new MetaDataType<>(Float.class, IjkMediaPlayer.FFP_PROP_INT64_SELECTED_AUDIO_STREAM);
        public static final MetaDataType<Float> AV3A_DYNAMIC_L1_META_CONTROL_CHANNEL_AZIMUTH = new MetaDataType<>(Float.class, IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER);
        public static final MetaDataType<Float> AV3A_DYNAMIC_L1_META_CONTROL_CHANNEL_ELEVATION = new MetaDataType<>(Float.class, IjkMediaPlayer.FFP_PROP_INT64_AUDIO_DECODER);
        public static final MetaDataType<Float> AV3A_DYNAMIC_L1_META_CONTROL_CHANNEL_DISTANCE = new MetaDataType<>(Float.class, IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_DURATION);

        private MetaDataType(Class<T> cls, int i7) {
            this.clazz = cls;
            this.type = i7;
        }
    }

    public AudioVividMetaDataHandler(IjkMediaPlayer ijkMediaPlayer) {
        this.mediaPlayer = new WeakReference<>(ijkMediaPlayer);
    }

    public <T> T getValue(MetaDataType<T> metaDataType, int i7) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer.get();
        if (ijkMediaPlayer == null) {
            return null;
        }
        if (((MetaDataType) metaDataType).clazz.equals(Integer.class)) {
            return (T) ((MetaDataType) metaDataType).clazz.cast(Integer.valueOf(ijkMediaPlayer.getAv3aMetadataInt(((MetaDataType) metaDataType).type, i7)));
        }
        if (((MetaDataType) metaDataType).clazz.equals(Float.class)) {
            return (T) ((MetaDataType) metaDataType).clazz.cast(Float.valueOf(ijkMediaPlayer.getAv3aMetadataFloat(((MetaDataType) metaDataType).type, i7)));
        }
        if (((MetaDataType) metaDataType).clazz.equals(String.class)) {
            return (T) ((MetaDataType) metaDataType).clazz.cast(ijkMediaPlayer.getAv3aMetadataString(((MetaDataType) metaDataType).type, i7));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> int setValue(MetaDataType<T> metaDataType, int i7, T t6) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer.get();
        if (ijkMediaPlayer == null) {
            return -1;
        }
        if (((MetaDataType) metaDataType).clazz.equals(Float.class)) {
            return ijkMediaPlayer.setAv3aMetadataFloat(((MetaDataType) metaDataType).type, i7, ((Float) t6).floatValue());
        }
        if (((MetaDataType) metaDataType).clazz.equals(Integer.class)) {
            return ijkMediaPlayer.setAv3aMetadataFloat(((MetaDataType) metaDataType).type, i7, ((Integer) t6).intValue());
        }
        return -1;
    }
}
