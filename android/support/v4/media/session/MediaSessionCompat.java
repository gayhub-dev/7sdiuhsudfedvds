package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaSessionManager;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompatApi21;
import android.support.v4.media.session.MediaSessionCompatApi23;
import android.support.v4.media.session.MediaSessionCompatApi24;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p009b.C0413b;

/* loaded from: classes.dex */
public class MediaSessionCompat {

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    private static final String DATA_CALLING_PACKAGE = "data_calling_pkg";
    private static final String DATA_CALLING_PID = "data_calling_pid";
    private static final String DATA_CALLING_UID = "data_calling_uid";
    private static final String DATA_EXTRAS = "data_extras";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_SESSION_TOKEN2_BUNDLE = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_TOKEN = "android.support.v4.media.session.TOKEN";
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    public static final String TAG = "MediaSessionCompat";
    public static int sMaxBitmapSize;
    private final ArrayList<OnActiveChangeListener> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;

    public static abstract class Callback {
        private CallbackHandler mCallbackHandler = null;
        public final Object mCallbackObj;
        private boolean mMediaPlayPauseKeyPending;
        public WeakReference<MediaSessionImpl> mSessionImpl;

        public class CallbackHandler extends Handler {
            private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;

            public CallbackHandler(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    Callback.this.handleMediaPlayPauseKeySingleTapIfPending((MediaSessionManager.RemoteUserInfo) message.obj);
                }
            }
        }

        @RequiresApi(21)
        public class StubApi21 implements MediaSessionCompatApi21.Callback {
            public StubApi21() {
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                try {
                    QueueItem queueItem = null;
                    IBinder iBinderAsBinder = null;
                    queueItem = null;
                    if (str.equals(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER)) {
                        MediaSessionImplApi21 mediaSessionImplApi21 = (MediaSessionImplApi21) Callback.this.mSessionImpl.get();
                        if (mediaSessionImplApi21 != null) {
                            Bundle bundle2 = new Bundle();
                            Token sessionToken = mediaSessionImplApi21.getSessionToken();
                            IMediaSession extraBinder = sessionToken.getExtraBinder();
                            if (extraBinder != null) {
                                iBinderAsBinder = extraBinder.asBinder();
                            }
                            BundleCompat.putBinder(bundle2, MediaSessionCompat.KEY_EXTRA_BINDER, iBinderAsBinder);
                            bundle2.putBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE, sessionToken.getSessionToken2Bundle());
                            resultReceiver.send(0, bundle2);
                            return;
                        }
                        return;
                    }
                    if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM)) {
                        Callback.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
                        return;
                    }
                    if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT)) {
                        Callback.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION), bundle.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX));
                        return;
                    }
                    if (str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM)) {
                        Callback.this.onRemoveQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
                        return;
                    }
                    if (!str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM_AT)) {
                        Callback.this.onCommand(str, bundle, resultReceiver);
                        return;
                    }
                    MediaSessionImplApi21 mediaSessionImplApi212 = (MediaSessionImplApi21) Callback.this.mSessionImpl.get();
                    if (mediaSessionImplApi212 == null || mediaSessionImplApi212.mQueue == null) {
                        return;
                    }
                    int i7 = bundle.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX, -1);
                    if (i7 >= 0 && i7 < mediaSessionImplApi212.mQueue.size()) {
                        queueItem = mediaSessionImplApi212.mQueue.get(i7);
                    }
                    if (queueItem != null) {
                        Callback.this.onRemoveQueueItem(queueItem.getDescription());
                    }
                } catch (BadParcelableException unused) {
                }
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onCustomAction(String str, Bundle bundle) {
                Bundle bundle2 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                MediaSessionCompat.ensureClassLoader(bundle2);
                if (str.equals(MediaSessionCompat.ACTION_PLAY_FROM_URI)) {
                    Callback.this.onPlayFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                    return;
                }
                if (str.equals(MediaSessionCompat.ACTION_PREPARE)) {
                    Callback.this.onPrepare();
                    return;
                }
                if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                    Callback.this.onPrepareFromMediaId(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID), bundle2);
                    return;
                }
                if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    Callback.this.onPrepareFromSearch(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_QUERY), bundle2);
                    return;
                }
                if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_URI)) {
                    Callback.this.onPrepareFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                    return;
                }
                if (str.equals(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED)) {
                    Callback.this.onSetCaptioningEnabled(bundle.getBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED));
                    return;
                }
                if (str.equals(MediaSessionCompat.ACTION_SET_REPEAT_MODE)) {
                    Callback.this.onSetRepeatMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE));
                } else if (str.equals(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE)) {
                    Callback.this.onSetShuffleMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE));
                } else if (!str.equals(MediaSessionCompat.ACTION_SET_RATING)) {
                    Callback.this.onCustomAction(str, bundle);
                } else {
                    Callback.this.onSetRating((RatingCompat) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING), bundle2);
                }
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onFastForward() {
                Callback.this.onFastForward();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public boolean onMediaButtonEvent(Intent intent) {
                return Callback.this.onMediaButtonEvent(intent);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onPause() {
                Callback.this.onPause();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onPlay() {
                Callback.this.onPlay();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onPlayFromMediaId(String str, Bundle bundle) {
                Callback.this.onPlayFromMediaId(str, bundle);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onPlayFromSearch(String str, Bundle bundle) {
                Callback.this.onPlayFromSearch(str, bundle);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onRewind() {
                Callback.this.onRewind();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onSeekTo(long j7) {
                Callback.this.onSeekTo(j7);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onSetRating(Object obj) {
                Callback.this.onSetRating(RatingCompat.fromRating(obj));
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onSetRating(Object obj, Bundle bundle) {
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onSkipToNext() {
                Callback.this.onSkipToNext();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onSkipToPrevious() {
                Callback.this.onSkipToPrevious();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onSkipToQueueItem(long j7) {
                Callback.this.onSkipToQueueItem(j7);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi21.Callback
            public void onStop() {
                Callback.this.onStop();
            }
        }

        @RequiresApi(23)
        public class StubApi23 extends StubApi21 implements MediaSessionCompatApi23.Callback {
            public StubApi23() {
                super();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback
            public void onPlayFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPlayFromUri(uri, bundle);
            }
        }

        @RequiresApi(24)
        public class StubApi24 extends StubApi23 implements MediaSessionCompatApi24.Callback {
            public StubApi24() {
                super();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepare() {
                Callback.this.onPrepare();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepareFromMediaId(String str, Bundle bundle) {
                Callback.this.onPrepareFromMediaId(str, bundle);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepareFromSearch(String str, Bundle bundle) {
                Callback.this.onPrepareFromSearch(str, bundle);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPrepareFromUri(uri, bundle);
            }
        }

        public Callback() {
            int i7 = Build.VERSION.SDK_INT;
            if (i7 >= 24) {
                this.mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
            } else if (i7 >= 23) {
                this.mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
            } else {
                this.mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
            }
        }

        public void handleMediaPlayPauseKeySingleTapIfPending(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            if (this.mMediaPlayPauseKeyPending) {
                this.mMediaPlayPauseKeyPending = false;
                this.mCallbackHandler.removeMessages(1);
                MediaSessionImpl mediaSessionImpl = this.mSessionImpl.get();
                if (mediaSessionImpl == null) {
                    return;
                }
                PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                long actions = playbackState == null ? 0L : playbackState.getActions();
                boolean z6 = playbackState != null && playbackState.getState() == 3;
                boolean z7 = (516 & actions) != 0;
                boolean z8 = (actions & 514) != 0;
                mediaSessionImpl.setCurrentControllerInfo(remoteUserInfo);
                if (z6 && z8) {
                    onPause();
                } else if (!z6 && z7) {
                    onPlay();
                }
                mediaSessionImpl.setCurrentControllerInfo(null);
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i7) {
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            MediaSessionImpl mediaSessionImpl;
            KeyEvent keyEvent;
            if (Build.VERSION.SDK_INT >= 27 || (mediaSessionImpl = this.mSessionImpl.get()) == null || this.mCallbackHandler == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
                return false;
            }
            MediaSessionManager.RemoteUserInfo currentControllerInfo = mediaSessionImpl.getCurrentControllerInfo();
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 79 && keyCode != 85) {
                handleMediaPlayPauseKeySingleTapIfPending(currentControllerInfo);
                return false;
            }
            if (keyEvent.getRepeatCount() > 0) {
                handleMediaPlayPauseKeySingleTapIfPending(currentControllerInfo);
            } else if (this.mMediaPlayPauseKeyPending) {
                this.mCallbackHandler.removeMessages(1);
                this.mMediaPlayPauseKeyPending = false;
                PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                if (((playbackState == null ? 0L : playbackState.getActions()) & 32) != 0) {
                    onSkipToNext();
                }
            } else {
                this.mMediaPlayPauseKeyPending = true;
                CallbackHandler callbackHandler = this.mCallbackHandler;
                callbackHandler.sendMessageDelayed(callbackHandler.obtainMessage(1, currentControllerInfo), ViewConfiguration.getDoubleTapTimeout());
            }
            return true;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int i7) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long j7) {
        }

        public void onSetCaptioningEnabled(boolean z6) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }

        public void onSetRepeatMode(int i7) {
        }

        public void onSetShuffleMode(int i7) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long j7) {
        }

        public void onStop() {
        }

        public void setSessionImpl(MediaSessionImpl mediaSessionImpl, Handler handler) {
            this.mSessionImpl = new WeakReference<>(mediaSessionImpl);
            CallbackHandler callbackHandler = this.mCallbackHandler;
            if (callbackHandler != null) {
                callbackHandler.removeCallbacksAndMessages(null);
            }
            this.mCallbackHandler = new CallbackHandler(handler.getLooper());
        }
    }

    public interface MediaSessionImpl {
        String getCallingPackage();

        MediaSessionManager.RemoteUserInfo getCurrentControllerInfo();

        Object getMediaSession();

        PlaybackStateCompat getPlaybackState();

        Object getRemoteControlClient();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String str, Bundle bundle);

        void setActive(boolean z6);

        void setCallback(Callback callback, Handler handler);

        void setCaptioningEnabled(boolean z6);

        void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo);

        void setExtras(Bundle bundle);

        void setFlags(int i7);

        void setMediaButtonReceiver(PendingIntent pendingIntent);

        void setMetadata(MediaMetadataCompat mediaMetadataCompat);

        void setPlaybackState(PlaybackStateCompat playbackStateCompat);

        void setPlaybackToLocal(int i7);

        void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat);

        void setQueue(List<QueueItem> list);

        void setQueueTitle(CharSequence charSequence);

        void setRatingType(int i7);

        void setRepeatMode(int i7);

        void setSessionActivity(PendingIntent pendingIntent);

        void setShuffleMode(int i7);
    }

    @RequiresApi(18)
    public static class MediaSessionImplApi18 extends MediaSessionImplBase {
        private static boolean sIsMbrPendingIntentSupported = true;

        public MediaSessionImplApi18(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase
        public int getRccTransportControlFlagsFromActions(long j7) {
            int rccTransportControlFlagsFromActions = super.getRccTransportControlFlagsFromActions(j7);
            return (j7 & 256) != 0 ? rccTransportControlFlagsFromActions | 256 : rccTransportControlFlagsFromActions;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase
        public void registerMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName componentName) {
            if (sIsMbrPendingIntentSupported) {
                try {
                    this.mAudioManager.registerMediaButtonEventReceiver(pendingIntent);
                } catch (NullPointerException unused) {
                    sIsMbrPendingIntentSupported = false;
                }
            }
            if (sIsMbrPendingIntentSupported) {
                return;
            }
            super.registerMediaButtonEventReceiver(pendingIntent, componentName);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase, android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCallback(Callback callback, Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.mRcc.setPlaybackPositionUpdateListener(null);
            } else {
                this.mRcc.setPlaybackPositionUpdateListener(new RemoteControlClient.OnPlaybackPositionUpdateListener() { // from class: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi18.1
                    @Override // android.media.RemoteControlClient.OnPlaybackPositionUpdateListener
                    public void onPlaybackPositionUpdate(long j7) {
                        MediaSessionImplApi18.this.postToHandler(18, -1, -1, Long.valueOf(j7), null);
                    }
                });
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase
        public void setRccState(PlaybackStateCompat playbackStateCompat) {
            long position = playbackStateCompat.getPosition();
            float playbackSpeed = playbackStateCompat.getPlaybackSpeed();
            long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (playbackStateCompat.getState() == 3) {
                long j7 = 0;
                if (position > 0) {
                    if (lastPositionUpdateTime > 0) {
                        j7 = jElapsedRealtime - lastPositionUpdateTime;
                        if (playbackSpeed > 0.0f && playbackSpeed != 1.0f) {
                            j7 = (long) (j7 * playbackSpeed);
                        }
                    }
                    position += j7;
                }
            }
            this.mRcc.setPlaybackState(getRccStateFromState(playbackStateCompat.getState()), position, playbackSpeed);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase
        public void unregisterMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName componentName) {
            if (sIsMbrPendingIntentSupported) {
                this.mAudioManager.unregisterMediaButtonEventReceiver(pendingIntent);
            } else {
                super.unregisterMediaButtonEventReceiver(pendingIntent, componentName);
            }
        }
    }

    @RequiresApi(19)
    public static class MediaSessionImplApi19 extends MediaSessionImplApi18 {
        public MediaSessionImplApi19(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase
        public RemoteControlClient.MetadataEditor buildRccMetadata(Bundle bundle) throws IllegalArgumentException {
            RemoteControlClient.MetadataEditor metadataEditorBuildRccMetadata = super.buildRccMetadata(bundle);
            PlaybackStateCompat playbackStateCompat = this.mState;
            if (((playbackStateCompat == null ? 0L : playbackStateCompat.getActions()) & 128) != 0) {
                metadataEditorBuildRccMetadata.addEditableKey(268435457);
            }
            if (bundle == null) {
                return metadataEditorBuildRccMetadata;
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_YEAR)) {
                metadataEditorBuildRccMetadata.putLong(8, bundle.getLong(MediaMetadataCompat.METADATA_KEY_YEAR));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_RATING)) {
                metadataEditorBuildRccMetadata.putObject(101, (Object) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_RATING));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_USER_RATING)) {
                metadataEditorBuildRccMetadata.putObject(268435457, (Object) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_USER_RATING));
            }
            return metadataEditorBuildRccMetadata;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi18, android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase
        public int getRccTransportControlFlagsFromActions(long j7) {
            int rccTransportControlFlagsFromActions = super.getRccTransportControlFlagsFromActions(j7);
            return (j7 & 128) != 0 ? rccTransportControlFlagsFromActions | 512 : rccTransportControlFlagsFromActions;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi18, android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase, android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCallback(Callback callback, Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.mRcc.setMetadataUpdateListener(null);
            } else {
                this.mRcc.setMetadataUpdateListener(new RemoteControlClient.OnMetadataUpdateListener() { // from class: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi19.1
                    @Override // android.media.RemoteControlClient.OnMetadataUpdateListener
                    public void onMetadataUpdate(int i7, Object obj) {
                        if (i7 == 268435457 && (obj instanceof Rating)) {
                            MediaSessionImplApi19.this.postToHandler(19, -1, -1, RatingCompat.fromRating(obj), null);
                        }
                    }
                });
            }
        }
    }

    @RequiresApi(28)
    public static class MediaSessionImplApi28 extends MediaSessionImplApi21 {
        public MediaSessionImplApi28(Context context, String str, Bundle bundle) {
            super(context, str, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21, android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        @NonNull
        public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            return new MediaSessionManager.RemoteUserInfo(((MediaSession) this.mSessionObj).getCurrentControllerInfo());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImplApi21, android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }

        public MediaSessionImplApi28(Object obj) {
            super(obj);
        }
    }

    public static class MediaSessionImplBase implements MediaSessionImpl {
        public static final int RCC_PLAYSTATE_NONE = 0;
        public final AudioManager mAudioManager;
        public volatile Callback mCallback;
        public boolean mCaptioningEnabled;
        private final Context mContext;
        public Bundle mExtras;
        public int mFlags;
        private MessageHandler mHandler;
        public int mLocalStream;
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        public MediaMetadataCompat mMetadata;
        public final String mPackageName;
        public List<QueueItem> mQueue;
        public CharSequence mQueueTitle;
        public int mRatingType;
        public final RemoteControlClient mRcc;
        private MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
        public int mRepeatMode;
        public PendingIntent mSessionActivity;
        public int mShuffleMode;
        public PlaybackStateCompat mState;
        private final MediaSessionStub mStub;
        public final String mTag;
        private final Token mToken;
        public VolumeProviderCompat mVolumeProvider;
        public int mVolumeType;
        public final Object mLock = new Object();
        public final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks = new RemoteCallbackList<>();
        public boolean mDestroyed = false;
        public boolean mIsActive = false;
        private boolean mIsMbrRegistered = false;
        private boolean mIsRccRegistered = false;
        private VolumeProviderCompat.Callback mVolumeCallback = new VolumeProviderCompat.Callback() { // from class: android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.1
            @Override // android.support.v4.media.VolumeProviderCompat.Callback
            public void onVolumeChanged(VolumeProviderCompat volumeProviderCompat) {
                if (MediaSessionImplBase.this.mVolumeProvider != volumeProviderCompat) {
                    return;
                }
                MediaSessionImplBase mediaSessionImplBase = MediaSessionImplBase.this;
                MediaSessionImplBase.this.sendVolumeInfoChanged(new ParcelableVolumeInfo(mediaSessionImplBase.mVolumeType, mediaSessionImplBase.mLocalStream, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
            }
        };

        public static final class Command {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;

            public Command(String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.command = str;
                this.extras = bundle;
                this.stub = resultReceiver;
            }
        }

        public class MediaSessionStub extends IMediaSession.Stub {
            public MediaSessionStub() {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                postToHandler(25, mediaDescriptionCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i7) {
                postToHandler(26, mediaDescriptionCompat, i7);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void adjustVolume(int i7, int i8, String str) {
                MediaSessionImplBase.this.adjustVolume(i7, i8);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void fastForward() {
                postToHandler(16);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle getExtras() {
                Bundle bundle;
                synchronized (MediaSessionImplBase.this.mLock) {
                    bundle = MediaSessionImplBase.this.mExtras;
                }
                return bundle;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public long getFlags() {
                long j7;
                synchronized (MediaSessionImplBase.this.mLock) {
                    j7 = MediaSessionImplBase.this.mFlags;
                }
                return j7;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PendingIntent getLaunchPendingIntent() {
                PendingIntent pendingIntent;
                synchronized (MediaSessionImplBase.this.mLock) {
                    pendingIntent = MediaSessionImplBase.this.mSessionActivity;
                }
                return pendingIntent;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public MediaMetadataCompat getMetadata() {
                return MediaSessionImplBase.this.mMetadata;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getPackageName() {
                return MediaSessionImplBase.this.mPackageName;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                PlaybackStateCompat playbackStateCompat;
                MediaMetadataCompat mediaMetadataCompat;
                synchronized (MediaSessionImplBase.this.mLock) {
                    MediaSessionImplBase mediaSessionImplBase = MediaSessionImplBase.this;
                    playbackStateCompat = mediaSessionImplBase.mState;
                    mediaMetadataCompat = mediaSessionImplBase.mMetadata;
                }
                return MediaSessionCompat.getStateWithUpdatedPosition(playbackStateCompat, mediaMetadataCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public List<QueueItem> getQueue() {
                List<QueueItem> list;
                synchronized (MediaSessionImplBase.this.mLock) {
                    list = MediaSessionImplBase.this.mQueue;
                }
                return list;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public CharSequence getQueueTitle() {
                return MediaSessionImplBase.this.mQueueTitle;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRatingType() {
                return MediaSessionImplBase.this.mRatingType;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRepeatMode() {
                return MediaSessionImplBase.this.mRepeatMode;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getShuffleMode() {
                return MediaSessionImplBase.this.mShuffleMode;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getTag() {
                return MediaSessionImplBase.this.mTag;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public ParcelableVolumeInfo getVolumeAttributes() {
                int i7;
                int i8;
                int i9;
                int streamMaxVolume;
                int streamVolume;
                synchronized (MediaSessionImplBase.this.mLock) {
                    MediaSessionImplBase mediaSessionImplBase = MediaSessionImplBase.this;
                    i7 = mediaSessionImplBase.mVolumeType;
                    i8 = mediaSessionImplBase.mLocalStream;
                    VolumeProviderCompat volumeProviderCompat = mediaSessionImplBase.mVolumeProvider;
                    i9 = 2;
                    if (i7 == 2) {
                        int volumeControl = volumeProviderCompat.getVolumeControl();
                        int maxVolume = volumeProviderCompat.getMaxVolume();
                        streamVolume = volumeProviderCompat.getCurrentVolume();
                        streamMaxVolume = maxVolume;
                        i9 = volumeControl;
                    } else {
                        streamMaxVolume = mediaSessionImplBase.mAudioManager.getStreamMaxVolume(i8);
                        streamVolume = MediaSessionImplBase.this.mAudioManager.getStreamVolume(i8);
                    }
                }
                return new ParcelableVolumeInfo(i7, i8, i9, streamMaxVolume, streamVolume);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isCaptioningEnabled() {
                return MediaSessionImplBase.this.mCaptioningEnabled;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isTransportControlEnabled() {
                return (MediaSessionImplBase.this.mFlags & 2) != 0;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void next() {
                postToHandler(14);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void pause() {
                postToHandler(12);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void play() {
                postToHandler(7);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromMediaId(String str, Bundle bundle) {
                postToHandler(8, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromSearch(String str, Bundle bundle) {
                postToHandler(9, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromUri(Uri uri, Bundle bundle) {
                postToHandler(10, uri, bundle);
            }

            public void postToHandler(int i7) {
                MediaSessionImplBase.this.postToHandler(i7, 0, 0, null, null);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepare() {
                postToHandler(3);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromMediaId(String str, Bundle bundle) {
                postToHandler(4, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromSearch(String str, Bundle bundle) {
                postToHandler(5, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromUri(Uri uri, Bundle bundle) {
                postToHandler(6, uri, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void previous() {
                postToHandler(15);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rate(RatingCompat ratingCompat) {
                postToHandler(19, ratingCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) {
                postToHandler(31, ratingCompat, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                if (MediaSessionImplBase.this.mDestroyed) {
                    try {
                        iMediaControllerCallback.onSessionDestroyed();
                    } catch (Exception unused) {
                    }
                } else {
                    MediaSessionImplBase.this.mControllerCallbacks.register(iMediaControllerCallback, new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, Binder.getCallingPid(), Binder.getCallingUid()));
                }
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                postToHandler(27, mediaDescriptionCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItemAt(int i7) {
                postToHandler(28, i7);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rewind() {
                postToHandler(17);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void seekTo(long j7) {
                postToHandler(18, Long.valueOf(j7));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                postToHandler(1, new Command(str, bundle, resultReceiverWrapper.mResultReceiver));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCustomAction(String str, Bundle bundle) {
                postToHandler(20, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean sendMediaButton(KeyEvent keyEvent) {
                boolean z6 = (MediaSessionImplBase.this.mFlags & 1) != 0;
                if (z6) {
                    postToHandler(21, keyEvent);
                }
                return z6;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setCaptioningEnabled(boolean z6) {
                postToHandler(29, Boolean.valueOf(z6));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setRepeatMode(int i7) {
                postToHandler(23, i7);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleMode(int i7) {
                postToHandler(30, i7);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleModeEnabledRemoved(boolean z6) {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setVolumeTo(int i7, int i8, String str) {
                MediaSessionImplBase.this.setVolumeTo(i7, i8);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void skipToQueueItem(long j7) {
                postToHandler(11, Long.valueOf(j7));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void stop() {
                postToHandler(13);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplBase.this.mControllerCallbacks.unregister(iMediaControllerCallback);
            }

            public void postToHandler(int i7, int i8) {
                MediaSessionImplBase.this.postToHandler(i7, i8, 0, null, null);
            }

            public void postToHandler(int i7, Object obj) {
                MediaSessionImplBase.this.postToHandler(i7, 0, 0, obj, null);
            }

            public void postToHandler(int i7, Object obj, int i8) {
                MediaSessionImplBase.this.postToHandler(i7, i8, 0, obj, null);
            }

            public void postToHandler(int i7, Object obj, Bundle bundle) {
                MediaSessionImplBase.this.postToHandler(i7, 0, 0, obj, bundle);
            }
        }

        public class MessageHandler extends Handler {
            private static final int KEYCODE_MEDIA_PAUSE = 127;
            private static final int KEYCODE_MEDIA_PLAY = 126;
            private static final int MSG_ADD_QUEUE_ITEM = 25;
            private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
            private static final int MSG_ADJUST_VOLUME = 2;
            private static final int MSG_COMMAND = 1;
            private static final int MSG_CUSTOM_ACTION = 20;
            private static final int MSG_FAST_FORWARD = 16;
            private static final int MSG_MEDIA_BUTTON = 21;
            private static final int MSG_NEXT = 14;
            private static final int MSG_PAUSE = 12;
            private static final int MSG_PLAY = 7;
            private static final int MSG_PLAY_MEDIA_ID = 8;
            private static final int MSG_PLAY_SEARCH = 9;
            private static final int MSG_PLAY_URI = 10;
            private static final int MSG_PREPARE = 3;
            private static final int MSG_PREPARE_MEDIA_ID = 4;
            private static final int MSG_PREPARE_SEARCH = 5;
            private static final int MSG_PREPARE_URI = 6;
            private static final int MSG_PREVIOUS = 15;
            private static final int MSG_RATE = 19;
            private static final int MSG_RATE_EXTRA = 31;
            private static final int MSG_REMOVE_QUEUE_ITEM = 27;
            private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
            private static final int MSG_REWIND = 17;
            private static final int MSG_SEEK_TO = 18;
            private static final int MSG_SET_CAPTIONING_ENABLED = 29;
            private static final int MSG_SET_REPEAT_MODE = 23;
            private static final int MSG_SET_SHUFFLE_MODE = 30;
            private static final int MSG_SET_VOLUME = 22;
            private static final int MSG_SKIP_TO_ITEM = 11;
            private static final int MSG_STOP = 13;

            public MessageHandler(Looper looper) {
                super(looper);
            }

            private void onMediaButtonEvent(KeyEvent keyEvent, Callback callback) {
                if (keyEvent == null || keyEvent.getAction() != 0) {
                    return;
                }
                PlaybackStateCompat playbackStateCompat = MediaSessionImplBase.this.mState;
                long actions = playbackStateCompat == null ? 0L : playbackStateCompat.getActions();
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == 126) {
                    if ((actions & 4) != 0) {
                        callback.onPlay();
                    }
                    return;
                }
                if (keyCode == 127) {
                    if ((actions & 2) != 0) {
                        callback.onPause();
                        return;
                    }
                    return;
                }
                switch (keyCode) {
                    case 86:
                        if ((actions & 1) != 0) {
                            callback.onStop();
                            break;
                        }
                        break;
                    case 87:
                        if ((actions & 32) != 0) {
                            callback.onSkipToNext();
                            break;
                        }
                        break;
                    case 88:
                        if ((actions & 16) != 0) {
                            callback.onSkipToPrevious();
                            break;
                        }
                        break;
                    case 89:
                        if ((actions & 8) != 0) {
                            callback.onRewind();
                            break;
                        }
                        break;
                    case 90:
                        if ((actions & 64) != 0) {
                            callback.onFastForward();
                            break;
                        }
                        break;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Callback callback = MediaSessionImplBase.this.mCallback;
                if (callback == null) {
                    return;
                }
                Bundle data = message.getData();
                MediaSessionCompat.ensureClassLoader(data);
                MediaSessionImplBase.this.setCurrentControllerInfo(new MediaSessionManager.RemoteUserInfo(data.getString(MediaSessionCompat.DATA_CALLING_PACKAGE), data.getInt("data_calling_pid"), data.getInt("data_calling_uid")));
                Bundle bundle = data.getBundle(MediaSessionCompat.DATA_EXTRAS);
                MediaSessionCompat.ensureClassLoader(bundle);
                try {
                    switch (message.what) {
                        case 1:
                            Command command = (Command) message.obj;
                            callback.onCommand(command.command, command.extras, command.stub);
                            break;
                        case 2:
                            MediaSessionImplBase.this.adjustVolume(message.arg1, 0);
                            break;
                        case 3:
                            callback.onPrepare();
                            break;
                        case 4:
                            callback.onPrepareFromMediaId((String) message.obj, bundle);
                            break;
                        case 5:
                            callback.onPrepareFromSearch((String) message.obj, bundle);
                            break;
                        case 6:
                            callback.onPrepareFromUri((Uri) message.obj, bundle);
                            break;
                        case 7:
                            callback.onPlay();
                            break;
                        case 8:
                            callback.onPlayFromMediaId((String) message.obj, bundle);
                            break;
                        case 9:
                            callback.onPlayFromSearch((String) message.obj, bundle);
                            break;
                        case 10:
                            callback.onPlayFromUri((Uri) message.obj, bundle);
                            break;
                        case 11:
                            callback.onSkipToQueueItem(((Long) message.obj).longValue());
                            break;
                        case 12:
                            callback.onPause();
                            break;
                        case 13:
                            callback.onStop();
                            break;
                        case 14:
                            callback.onSkipToNext();
                            break;
                        case 15:
                            callback.onSkipToPrevious();
                            break;
                        case 16:
                            callback.onFastForward();
                            break;
                        case 17:
                            callback.onRewind();
                            break;
                        case 18:
                            callback.onSeekTo(((Long) message.obj).longValue());
                            break;
                        case 19:
                            callback.onSetRating((RatingCompat) message.obj);
                            break;
                        case 20:
                            callback.onCustomAction((String) message.obj, bundle);
                            break;
                        case 21:
                            KeyEvent keyEvent = (KeyEvent) message.obj;
                            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                            if (!callback.onMediaButtonEvent(intent)) {
                                onMediaButtonEvent(keyEvent, callback);
                                break;
                            }
                            break;
                        case 22:
                            MediaSessionImplBase.this.setVolumeTo(message.arg1, 0);
                            break;
                        case 23:
                            callback.onSetRepeatMode(message.arg1);
                            break;
                        case 25:
                            callback.onAddQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case 26:
                            callback.onAddQueueItem((MediaDescriptionCompat) message.obj, message.arg1);
                            break;
                        case 27:
                            callback.onRemoveQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case 28:
                            List<QueueItem> list = MediaSessionImplBase.this.mQueue;
                            if (list != null) {
                                int i7 = message.arg1;
                                QueueItem queueItem = (i7 < 0 || i7 >= list.size()) ? null : MediaSessionImplBase.this.mQueue.get(message.arg1);
                                if (queueItem != null) {
                                    callback.onRemoveQueueItem(queueItem.getDescription());
                                    break;
                                }
                            }
                            break;
                        case 29:
                            callback.onSetCaptioningEnabled(((Boolean) message.obj).booleanValue());
                            break;
                        case 30:
                            callback.onSetShuffleMode(message.arg1);
                            break;
                        case 31:
                            callback.onSetRating((RatingCompat) message.obj, bundle);
                            break;
                    }
                } finally {
                    MediaSessionImplBase.this.setCurrentControllerInfo(null);
                }
            }
        }

        public MediaSessionImplBase(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            if (componentName == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            }
            this.mContext = context;
            this.mPackageName = context.getPackageName();
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
            this.mTag = str;
            this.mMediaButtonReceiverComponentName = componentName;
            this.mMediaButtonReceiverIntent = pendingIntent;
            MediaSessionStub mediaSessionStub = new MediaSessionStub();
            this.mStub = mediaSessionStub;
            this.mToken = new Token(mediaSessionStub);
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            this.mRcc = new RemoteControlClient(pendingIntent);
        }

        private void sendCaptioningEnabled(boolean z6) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onCaptioningEnabledChanged(z6);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendEvent(String str, Bundle bundle) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onEvent(str, bundle);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendExtras(Bundle bundle) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onExtrasChanged(bundle);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendMetadata(MediaMetadataCompat mediaMetadataCompat) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onMetadataChanged(mediaMetadataCompat);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueue(List<QueueItem> list) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onQueueChanged(list);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueueTitle(CharSequence charSequence) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onQueueTitleChanged(charSequence);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendRepeatMode(int i7) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onRepeatModeChanged(i7);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendSessionDestroyed() {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onSessionDestroyed();
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
            this.mControllerCallbacks.kill();
        }

        private void sendShuffleMode(int i7) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onShuffleModeChanged(i7);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendState(PlaybackStateCompat playbackStateCompat) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        public void adjustVolume(int i7, int i8) {
            if (this.mVolumeType != 2) {
                this.mAudioManager.adjustStreamVolume(this.mLocalStream, i7, i8);
                return;
            }
            VolumeProviderCompat volumeProviderCompat = this.mVolumeProvider;
            if (volumeProviderCompat != null) {
                volumeProviderCompat.onAdjustVolume(i7);
            }
        }

        public RemoteControlClient.MetadataEditor buildRccMetadata(Bundle bundle) throws IllegalArgumentException {
            RemoteControlClient.MetadataEditor metadataEditorEditMetadata = this.mRcc.editMetadata(true);
            if (bundle == null) {
                return metadataEditorEditMetadata;
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ART)) {
                Bitmap bitmapCopy = (Bitmap) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_ART);
                if (bitmapCopy != null) {
                    bitmapCopy = bitmapCopy.copy(bitmapCopy.getConfig(), false);
                }
                metadataEditorEditMetadata.putBitmap(100, bitmapCopy);
            } else if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ART)) {
                Bitmap bitmapCopy2 = (Bitmap) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
                if (bitmapCopy2 != null) {
                    bitmapCopy2 = bitmapCopy2.copy(bitmapCopy2.getConfig(), false);
                }
                metadataEditorEditMetadata.putBitmap(100, bitmapCopy2);
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM)) {
                metadataEditorEditMetadata.putString(1, bundle.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST)) {
                metadataEditorEditMetadata.putString(13, bundle.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ARTIST)) {
                metadataEditorEditMetadata.putString(2, bundle.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_AUTHOR)) {
                metadataEditorEditMetadata.putString(3, bundle.getString(MediaMetadataCompat.METADATA_KEY_AUTHOR));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_COMPILATION)) {
                metadataEditorEditMetadata.putString(15, bundle.getString(MediaMetadataCompat.METADATA_KEY_COMPILATION));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_COMPOSER)) {
                metadataEditorEditMetadata.putString(4, bundle.getString(MediaMetadataCompat.METADATA_KEY_COMPOSER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DATE)) {
                metadataEditorEditMetadata.putString(5, bundle.getString(MediaMetadataCompat.METADATA_KEY_DATE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER)) {
                metadataEditorEditMetadata.putLong(14, bundle.getLong(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
                metadataEditorEditMetadata.putLong(9, bundle.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_GENRE)) {
                metadataEditorEditMetadata.putString(6, bundle.getString(MediaMetadataCompat.METADATA_KEY_GENRE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_TITLE)) {
                metadataEditorEditMetadata.putString(7, bundle.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER)) {
                metadataEditorEditMetadata.putLong(0, bundle.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_WRITER)) {
                metadataEditorEditMetadata.putString(11, bundle.getString(MediaMetadataCompat.METADATA_KEY_WRITER));
            }
            return metadataEditorEditMetadata;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public String getCallingPackage() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            synchronized (this.mLock) {
                remoteUserInfo = this.mRemoteUserInfo;
            }
            return remoteUserInfo;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Object getMediaSession() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public PlaybackStateCompat getPlaybackState() {
            PlaybackStateCompat playbackStateCompat;
            synchronized (this.mLock) {
                playbackStateCompat = this.mState;
            }
            return playbackStateCompat;
        }

        public int getRccStateFromState(int i7) {
            switch (i7) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        public int getRccTransportControlFlagsFromActions(long j7) {
            int i7 = (1 & j7) != 0 ? 32 : 0;
            if ((2 & j7) != 0) {
                i7 |= 16;
            }
            if ((4 & j7) != 0) {
                i7 |= 4;
            }
            if ((8 & j7) != 0) {
                i7 |= 2;
            }
            if ((16 & j7) != 0) {
                i7 |= 1;
            }
            if ((32 & j7) != 0) {
                i7 |= 128;
            }
            if ((64 & j7) != 0) {
                i7 |= 64;
            }
            return (j7 & 512) != 0 ? i7 | 8 : i7;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Object getRemoteControlClient() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Token getSessionToken() {
            return this.mToken;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public boolean isActive() {
            return this.mIsActive;
        }

        public void postToHandler(int i7, int i8, int i9, Object obj, Bundle bundle) {
            synchronized (this.mLock) {
                MessageHandler messageHandler = this.mHandler;
                if (messageHandler != null) {
                    Message messageObtainMessage = messageHandler.obtainMessage(i7, i8, i9, obj);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(MediaSessionCompat.DATA_CALLING_PACKAGE, MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER);
                    bundle2.putInt("data_calling_pid", Binder.getCallingPid());
                    bundle2.putInt("data_calling_uid", Binder.getCallingUid());
                    if (bundle != null) {
                        bundle2.putBundle(MediaSessionCompat.DATA_EXTRAS, bundle);
                    }
                    messageObtainMessage.setData(bundle2);
                    messageObtainMessage.sendToTarget();
                }
            }
        }

        public void registerMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName componentName) {
            this.mAudioManager.registerMediaButtonEventReceiver(componentName);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void release() {
            this.mIsActive = false;
            this.mDestroyed = true;
            update();
            sendSessionDestroyed();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void sendSessionEvent(String str, Bundle bundle) {
            sendEvent(str, bundle);
        }

        public void sendVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) {
            for (int iBeginBroadcast = this.mControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onVolumeInfoChanged(parcelableVolumeInfo);
                } catch (RemoteException unused) {
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setActive(boolean z6) {
            if (z6 == this.mIsActive) {
                return;
            }
            this.mIsActive = z6;
            if (update()) {
                setMetadata(this.mMetadata);
                setPlaybackState(this.mState);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCallback(Callback callback, Handler handler) {
            this.mCallback = callback;
            if (callback != null) {
                if (handler == null) {
                    handler = new Handler();
                }
                synchronized (this.mLock) {
                    MessageHandler messageHandler = this.mHandler;
                    if (messageHandler != null) {
                        messageHandler.removeCallbacksAndMessages(null);
                    }
                    this.mHandler = new MessageHandler(handler.getLooper());
                    this.mCallback.setSessionImpl(this, handler);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCaptioningEnabled(boolean z6) {
            if (this.mCaptioningEnabled != z6) {
                this.mCaptioningEnabled = z6;
                sendCaptioningEnabled(z6);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            synchronized (this.mLock) {
                this.mRemoteUserInfo = remoteUserInfo;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setExtras(Bundle bundle) {
            this.mExtras = bundle;
            sendExtras(bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setFlags(int i7) {
            synchronized (this.mLock) {
                this.mFlags = i7;
            }
            update();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                mediaMetadataCompat = new MediaMetadataCompat.Builder(mediaMetadataCompat, MediaSessionCompat.sMaxBitmapSize).build();
            }
            synchronized (this.mLock) {
                this.mMetadata = mediaMetadataCompat;
            }
            sendMetadata(mediaMetadataCompat);
            if (this.mIsActive) {
                buildRccMetadata(mediaMetadataCompat == null ? null : mediaMetadataCompat.getBundle()).apply();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            synchronized (this.mLock) {
                this.mState = playbackStateCompat;
            }
            sendState(playbackStateCompat);
            if (this.mIsActive) {
                if (playbackStateCompat == null) {
                    this.mRcc.setPlaybackState(0);
                    this.mRcc.setTransportControlFlags(0);
                } else {
                    setRccState(playbackStateCompat);
                    this.mRcc.setTransportControlFlags(getRccTransportControlFlagsFromActions(playbackStateCompat.getActions()));
                }
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToLocal(int i7) {
            VolumeProviderCompat volumeProviderCompat = this.mVolumeProvider;
            if (volumeProviderCompat != null) {
                volumeProviderCompat.setCallback(null);
            }
            this.mLocalStream = i7;
            this.mVolumeType = 1;
            int i8 = this.mVolumeType;
            int i9 = this.mLocalStream;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(i8, i9, 2, this.mAudioManager.getStreamMaxVolume(i9), this.mAudioManager.getStreamVolume(this.mLocalStream)));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            if (volumeProviderCompat == null) {
                throw new IllegalArgumentException("volumeProvider may not be null");
            }
            VolumeProviderCompat volumeProviderCompat2 = this.mVolumeProvider;
            if (volumeProviderCompat2 != null) {
                volumeProviderCompat2.setCallback(null);
            }
            this.mVolumeType = 2;
            this.mVolumeProvider = volumeProviderCompat;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
            volumeProviderCompat.setCallback(this.mVolumeCallback);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setQueue(List<QueueItem> list) {
            this.mQueue = list;
            sendQueue(list);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setQueueTitle(CharSequence charSequence) {
            this.mQueueTitle = charSequence;
            sendQueueTitle(charSequence);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setRatingType(int i7) {
            this.mRatingType = i7;
        }

        public void setRccState(PlaybackStateCompat playbackStateCompat) {
            this.mRcc.setPlaybackState(getRccStateFromState(playbackStateCompat.getState()));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setRepeatMode(int i7) {
            if (this.mRepeatMode != i7) {
                this.mRepeatMode = i7;
                sendRepeatMode(i7);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setSessionActivity(PendingIntent pendingIntent) {
            synchronized (this.mLock) {
                this.mSessionActivity = pendingIntent;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setShuffleMode(int i7) {
            if (this.mShuffleMode != i7) {
                this.mShuffleMode = i7;
                sendShuffleMode(i7);
            }
        }

        public void setVolumeTo(int i7, int i8) {
            if (this.mVolumeType != 2) {
                this.mAudioManager.setStreamVolume(this.mLocalStream, i7, i8);
                return;
            }
            VolumeProviderCompat volumeProviderCompat = this.mVolumeProvider;
            if (volumeProviderCompat != null) {
                volumeProviderCompat.onSetVolumeTo(i7);
            }
        }

        public void unregisterMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName componentName) {
            this.mAudioManager.unregisterMediaButtonEventReceiver(componentName);
        }

        public boolean update() {
            if (this.mIsActive) {
                boolean z6 = this.mIsMbrRegistered;
                if (!z6 && (this.mFlags & 1) != 0) {
                    registerMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = true;
                } else if (z6 && (this.mFlags & 1) == 0) {
                    unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }
                boolean z7 = this.mIsRccRegistered;
                if (!z7 && (this.mFlags & 2) != 0) {
                    this.mAudioManager.registerRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = true;
                    return true;
                }
                if (z7 && (this.mFlags & 2) == 0) {
                    this.mRcc.setPlaybackState(0);
                    this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = false;
                }
            } else {
                if (this.mIsMbrRegistered) {
                    unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }
                if (this.mIsRccRegistered) {
                    this.mRcc.setPlaybackState(0);
                    this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = false;
                }
            }
            return false;
        }
    }

    public interface OnActiveChangeListener {
        void onActiveChanged();
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new Parcelable.Creator<QueueItem>() { // from class: android.support.v4.media.session.MediaSessionCompat.QueueItem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QueueItem[] newArray(int i7) {
                return new QueueItem[i7];
            }
        };
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;

        public QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j7) {
            this(null, mediaDescriptionCompat, j7);
        }

        public static QueueItem fromQueueItem(Object obj) {
            if (obj != null) {
                return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(obj)), MediaSessionCompatApi21.QueueItem.getQueueId(obj));
            }
            return null;
        }

        public static List<QueueItem> fromQueueItemList(List<?> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(fromQueueItem(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public long getQueueId() {
            return this.mId;
        }

        public Object getQueueItem() {
            Object obj = this.mItem;
            if (obj != null) {
                return obj;
            }
            Object objCreateItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
            this.mItem = objCreateItem;
            return objCreateItem;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("MediaSession.QueueItem {Description=");
            sbM112a.append(this.mDescription);
            sbM112a.append(", Id=");
            sbM112a.append(this.mId);
            sbM112a.append(" }");
            return sbM112a.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            this.mDescription.writeToParcel(parcel, i7);
            parcel.writeLong(this.mId);
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j7) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }
            if (j7 == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.mDescription = mediaDescriptionCompat;
            this.mId = j7;
            this.mItem = obj;
        }

        public QueueItem(Parcel parcel) {
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.mId = parcel.readLong();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface SessionFlags {
    }

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new Parcelable.Creator<Token>() { // from class: android.support.v4.media.session.MediaSessionCompat.Token.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Token createFromParcel(Parcel parcel) {
                return new Token(parcel.readParcelable(null));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Token[] newArray(int i7) {
                return new Token[i7];
            }
        };
        private IMediaSession mExtraBinder;
        private final Object mInner;
        private Bundle mSessionToken2Bundle;

        public Token(Object obj) {
            this(obj, null, null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            IMediaSession iMediaSessionAsInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, MediaSessionCompat.KEY_EXTRA_BINDER));
            Bundle bundle2 = bundle.getBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE);
            Token token = (Token) bundle.getParcelable(MediaSessionCompat.KEY_TOKEN);
            if (token == null) {
                return null;
            }
            return new Token(token.mInner, iMediaSessionAsInterface, bundle2);
        }

        public static Token fromToken(Object obj) {
            return fromToken(obj, null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.mInner;
            if (obj2 == null) {
                return token.mInner == null;
            }
            Object obj3 = token.mInner;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public IMediaSession getExtraBinder() {
            return this.mExtraBinder;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle getSessionToken2Bundle() {
            return this.mSessionToken2Bundle;
        }

        public Object getToken() {
            return this.mInner;
        }

        public int hashCode() {
            Object obj = this.mInner;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setExtraBinder(IMediaSession iMediaSession) {
            this.mExtraBinder = iMediaSession;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setSessionToken2Bundle(Bundle bundle) {
            this.mSessionToken2Bundle = bundle;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaSessionCompat.KEY_TOKEN, this);
            IMediaSession iMediaSession = this.mExtraBinder;
            if (iMediaSession != null) {
                BundleCompat.putBinder(bundle, MediaSessionCompat.KEY_EXTRA_BINDER, iMediaSession.asBinder());
            }
            Bundle bundle2 = this.mSessionToken2Bundle;
            if (bundle2 != null) {
                bundle.putBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE, bundle2);
            }
            return bundle;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable((Parcelable) this.mInner, i7);
        }

        public Token(Object obj, IMediaSession iMediaSession) {
            this(obj, iMediaSession, null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromToken(Object obj, IMediaSession iMediaSession) {
            if (obj != null) {
                return new Token(MediaSessionCompatApi21.verifyToken(obj), iMediaSession);
            }
            return null;
        }

        public Token(Object obj, IMediaSession iMediaSession, Bundle bundle) {
            this.mInner = obj;
            this.mExtraBinder = iMediaSession;
            this.mSessionToken2Bundle = bundle;
        }
    }

    public MediaSessionCompat(Context context, String str) {
        this(context, str, null, null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void ensureClassLoader(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj) {
        if (context == null || obj == null) {
            return null;
        }
        return new MediaSessionCompat(context, new MediaSessionImplApi21(obj));
    }

    public static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        long j7 = -1;
        if (playbackStateCompat.getPosition() == -1) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.getState() != 3 && playbackStateCompat.getState() != 4 && playbackStateCompat.getState() != 5) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.getLastPositionUpdateTime() <= 0) {
            return playbackStateCompat;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long position = playbackStateCompat.getPosition() + ((long) (playbackStateCompat.getPlaybackSpeed() * (jElapsedRealtime - r0)));
        if (mediaMetadataCompat != null && mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
            j7 = mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
        }
        return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), (j7 < 0 || position <= j7) ? position < 0 ? 0L : position : j7, playbackStateCompat.getPlaybackSpeed(), jElapsedRealtime).build();
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.add(onActiveChangeListener);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }

    public MediaControllerCompat getController() {
        return this.mController;
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
        return this.mImpl.getCurrentControllerInfo();
    }

    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }

    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }

    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public boolean isActive() {
        return this.mImpl.isActive();
    }

    public void release() {
        this.mImpl.release();
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.remove(onActiveChangeListener);
    }

    public void sendSessionEvent(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.mImpl.sendSessionEvent(str, bundle);
    }

    public void setActive(boolean z6) {
        this.mImpl.setActive(z6);
        Iterator<OnActiveChangeListener> it = this.mActiveListeners.iterator();
        while (it.hasNext()) {
            it.next().onActiveChanged();
        }
    }

    public void setCallback(Callback callback) {
        setCallback(callback, null);
    }

    public void setCaptioningEnabled(boolean z6) {
        this.mImpl.setCaptioningEnabled(z6);
    }

    public void setExtras(Bundle bundle) {
        this.mImpl.setExtras(bundle);
    }

    public void setFlags(int i7) {
        this.mImpl.setFlags(i7);
    }

    public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        this.mImpl.setMediaButtonReceiver(pendingIntent);
    }

    public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
        this.mImpl.setMetadata(mediaMetadataCompat);
    }

    public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
        this.mImpl.setPlaybackState(playbackStateCompat);
    }

    public void setPlaybackToLocal(int i7) {
        this.mImpl.setPlaybackToLocal(i7);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
        if (volumeProviderCompat == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.mImpl.setPlaybackToRemote(volumeProviderCompat);
    }

    public void setQueue(List<QueueItem> list) {
        this.mImpl.setQueue(list);
    }

    public void setQueueTitle(CharSequence charSequence) {
        this.mImpl.setQueueTitle(charSequence);
    }

    public void setRatingType(int i7) {
        this.mImpl.setRatingType(i7);
    }

    public void setRepeatMode(int i7) {
        this.mImpl.setRepeatMode(i7);
    }

    public void setSessionActivity(PendingIntent pendingIntent) {
        this.mImpl.setSessionActivity(pendingIntent);
    }

    public void setShuffleMode(int i7) {
        this.mImpl.setShuffleMode(i7);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new Parcelable.Creator<ResultReceiverWrapper>() { // from class: android.support.v4.media.session.MediaSessionCompat.ResultReceiverWrapper.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ResultReceiverWrapper[] newArray(int i7) {
                return new ResultReceiverWrapper[i7];
            }
        };
        public ResultReceiver mResultReceiver;

        public ResultReceiverWrapper(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            this.mResultReceiver.writeToParcel(parcel, i7);
        }

        public ResultReceiverWrapper(Parcel parcel) {
            this.mResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this(context, str, componentName, pendingIntent, null);
    }

    public void setCallback(Callback callback, Handler handler) {
        if (callback == null) {
            this.mImpl.setCallback(null, null);
            return;
        }
        MediaSessionImpl mediaSessionImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mediaSessionImpl.setCallback(callback, handler);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MediaSessionCompat(Context context, String str, Bundle bundle) {
        this(context, str, null, null, bundle);
    }

    private MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        this.mActiveListeners = new ArrayList<>();
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                componentName = componentName == null ? MediaButtonReceiver.getMediaButtonReceiverComponent(context) : componentName;
                if (componentName != null && pendingIntent == null) {
                    Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.setComponent(componentName);
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    MediaSessionImplApi28 mediaSessionImplApi28 = new MediaSessionImplApi28(context, str, bundle);
                    this.mImpl = mediaSessionImplApi28;
                    setCallback(new Callback() { // from class: android.support.v4.media.session.MediaSessionCompat.1
                    });
                    mediaSessionImplApi28.setMediaButtonReceiver(pendingIntent);
                } else {
                    MediaSessionImplApi21 mediaSessionImplApi21 = new MediaSessionImplApi21(context, str, bundle);
                    this.mImpl = mediaSessionImplApi21;
                    setCallback(new Callback() { // from class: android.support.v4.media.session.MediaSessionCompat.2
                    });
                    mediaSessionImplApi21.setMediaButtonReceiver(pendingIntent);
                }
                this.mController = new MediaControllerCompat(context, this);
                if (sMaxBitmapSize == 0) {
                    sMaxBitmapSize = (int) (TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics()) + 0.5f);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        throw new IllegalArgumentException("context must not be null");
    }

    @RequiresApi(21)
    public static class MediaSessionImplApi21 implements MediaSessionImpl {
        public boolean mCaptioningEnabled;
        public boolean mDestroyed = false;
        public final RemoteCallbackList<IMediaControllerCallback> mExtraControllerCallbacks = new RemoteCallbackList<>();
        public MediaMetadataCompat mMetadata;
        public PlaybackStateCompat mPlaybackState;
        public List<QueueItem> mQueue;
        public int mRatingType;
        public int mRepeatMode;
        public final Object mSessionObj;
        public int mShuffleMode;
        public final Token mToken;

        public class ExtraSession extends IMediaSession.Stub {
            public ExtraSession() {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i7) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void adjustVolume(int i7, int i8, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void fastForward() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle getExtras() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public long getFlags() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getPackageName() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                MediaSessionImplApi21 mediaSessionImplApi21 = MediaSessionImplApi21.this;
                return MediaSessionCompat.getStateWithUpdatedPosition(mediaSessionImplApi21.mPlaybackState, mediaSessionImplApi21.mMetadata);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public List<QueueItem> getQueue() {
                return null;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRatingType() {
                return MediaSessionImplApi21.this.mRatingType;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRepeatMode() {
                return MediaSessionImplApi21.this.mRepeatMode;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getShuffleMode() {
                return MediaSessionImplApi21.this.mShuffleMode;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getTag() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isCaptioningEnabled() {
                return MediaSessionImplApi21.this.mCaptioningEnabled;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void next() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void pause() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void play() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromMediaId(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromSearch(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromUri(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepare() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromMediaId(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromSearch(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromUri(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void previous() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rate(RatingCompat ratingCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplApi21 mediaSessionImplApi21 = MediaSessionImplApi21.this;
                if (mediaSessionImplApi21.mDestroyed) {
                    return;
                }
                String callingPackage = mediaSessionImplApi21.getCallingPackage();
                if (callingPackage == null) {
                    callingPackage = MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER;
                }
                MediaSessionImplApi21.this.mExtraControllerCallbacks.register(iMediaControllerCallback, new MediaSessionManager.RemoteUserInfo(callingPackage, Binder.getCallingPid(), Binder.getCallingUid()));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItemAt(int i7) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rewind() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void seekTo(long j7) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCustomAction(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean sendMediaButton(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setCaptioningEnabled(boolean z6) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setRepeatMode(int i7) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleMode(int i7) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleModeEnabledRemoved(boolean z6) {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setVolumeTo(int i7, int i8, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void skipToQueueItem(long j7) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void stop() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplApi21.this.mExtraControllerCallbacks.unregister(iMediaControllerCallback);
            }
        }

        public MediaSessionImplApi21(Context context, String str, Bundle bundle) {
            Object objCreateSession = MediaSessionCompatApi21.createSession(context, str);
            this.mSessionObj = objCreateSession;
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(objCreateSession), new ExtraSession(), bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public String getCallingPackage() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Object getMediaSession() {
            return this.mSessionObj;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public PlaybackStateCompat getPlaybackState() {
            return this.mPlaybackState;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Object getRemoteControlClient() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public Token getSessionToken() {
            return this.mToken;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.mSessionObj);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void release() {
            this.mDestroyed = true;
            MediaSessionCompatApi21.release(this.mSessionObj);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void sendSessionEvent(String str, Bundle bundle) {
            if (Build.VERSION.SDK_INT < 23) {
                for (int iBeginBroadcast = this.mExtraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.mExtraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onEvent(str, bundle);
                    } catch (RemoteException unused) {
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
            MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, str, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setActive(boolean z6) {
            MediaSessionCompatApi21.setActive(this.mSessionObj, z6);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCallback(Callback callback, Handler handler) {
            MediaSessionCompatApi21.setCallback(this.mSessionObj, callback == null ? null : callback.mCallbackObj, handler);
            if (callback != null) {
                callback.setSessionImpl(this, handler);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCaptioningEnabled(boolean z6) {
            if (this.mCaptioningEnabled != z6) {
                this.mCaptioningEnabled = z6;
                for (int iBeginBroadcast = this.mExtraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.mExtraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onCaptioningEnabledChanged(z6);
                    } catch (RemoteException unused) {
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setExtras(Bundle bundle) {
            MediaSessionCompatApi21.setExtras(this.mSessionObj, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setFlags(int i7) {
            MediaSessionCompatApi21.setFlags(this.mSessionObj, i7);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            this.mMetadata = mediaMetadataCompat;
            MediaSessionCompatApi21.setMetadata(this.mSessionObj, mediaMetadataCompat == null ? null : mediaMetadataCompat.getMediaMetadata());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            this.mPlaybackState = playbackStateCompat;
            for (int iBeginBroadcast = this.mExtraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.mExtraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.mExtraControllerCallbacks.finishBroadcast();
            MediaSessionCompatApi21.setPlaybackState(this.mSessionObj, playbackStateCompat == null ? null : playbackStateCompat.getPlaybackState());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToLocal(int i7) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, i7);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setQueue(List<QueueItem> list) {
            ArrayList arrayList;
            this.mQueue = list;
            if (list != null) {
                arrayList = new ArrayList();
                Iterator<QueueItem> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getQueueItem());
                }
            } else {
                arrayList = null;
            }
            MediaSessionCompatApi21.setQueue(this.mSessionObj, arrayList);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setQueueTitle(CharSequence charSequence) {
            MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, charSequence);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setRatingType(int i7) {
            if (Build.VERSION.SDK_INT < 22) {
                this.mRatingType = i7;
            } else {
                MediaSessionCompatApi22.setRatingType(this.mSessionObj, i7);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setRepeatMode(int i7) {
            if (this.mRepeatMode != i7) {
                this.mRepeatMode = i7;
                for (int iBeginBroadcast = this.mExtraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.mExtraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onRepeatModeChanged(i7);
                    } catch (RemoteException unused) {
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setSessionActivity(PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.MediaSessionImpl
        public void setShuffleMode(int i7) {
            if (this.mShuffleMode != i7) {
                this.mShuffleMode = i7;
                for (int iBeginBroadcast = this.mExtraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.mExtraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onShuffleModeChanged(i7);
                    } catch (RemoteException unused) {
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public MediaSessionImplApi21(Object obj) {
            Object objVerifySession = MediaSessionCompatApi21.verifySession(obj);
            this.mSessionObj = objVerifySession;
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(objVerifySession), new ExtraSession());
        }
    }

    private MediaSessionCompat(Context context, MediaSessionImpl mediaSessionImpl) {
        this.mActiveListeners = new ArrayList<>();
        this.mImpl = mediaSessionImpl;
        if (!MediaSessionCompatApi21.hasCallback(mediaSessionImpl.getMediaSession())) {
            setCallback(new Callback() { // from class: android.support.v4.media.session.MediaSessionCompat.3
            });
        }
        this.mController = new MediaControllerCompat(context, this);
    }
}
