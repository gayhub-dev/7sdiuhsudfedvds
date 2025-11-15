package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.compat.C0068R;
import android.support.v4.app.Person;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";

    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;

    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static class Action {
        public static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        public static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        public final Bundle mExtras;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        public boolean mShowsUserInterface;
        public CharSequence title;

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public Builder(int i7, CharSequence charSequence, PendingIntent pendingIntent) {
                this(i7, charSequence, pendingIntent, new Bundle(), null, true, 0, true);
            }

            public Builder addExtras(Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<>();
                }
                this.mRemoteInputs.add(remoteInput);
                return this;
            }

            public Action build() {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
                if (arrayList3 != null) {
                    Iterator<RemoteInput> it = arrayList3.iterator();
                    while (it.hasNext()) {
                        RemoteInput next = it.next();
                        if (next.isDataOnly()) {
                            arrayList.add(next);
                        } else {
                            arrayList2.add(next);
                        }
                    }
                }
                RemoteInput[] remoteInputArr = arrayList.isEmpty() ? null : (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]);
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayList2.isEmpty() ? null : (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]), remoteInputArr, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder setAllowGeneratedReplies(boolean z6) {
                this.mAllowGeneratedReplies = z6;
                return this;
            }

            public Builder setSemanticAction(int i7) {
                this.mSemanticAction = i7;
                return this;
            }

            public Builder setShowsUserInterface(boolean z6) {
                this.mShowsUserInterface = z6;
                return this;
            }

            public Builder(Action action) {
                this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.mShowsUserInterface);
            }

            private Builder(int i7, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z6, int i8, boolean z7) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = i7;
                this.mTitle = Builder.limitCharSequenceLength(charSequence);
                this.mIntent = pendingIntent;
                this.mExtras = bundle;
                this.mRemoteInputs = remoteInputArr == null ? null : new ArrayList<>(Arrays.asList(remoteInputArr));
                this.mAllowGeneratedReplies = z6;
                this.mSemanticAction = i8;
                this.mShowsUserInterface = z7;
            }
        }

        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
                this.mFlags = 1;
            }

            private void setFlag(int i7, boolean z6) {
                if (z6) {
                    this.mFlags = i7 | this.mFlags;
                } else {
                    this.mFlags = (~i7) & this.mFlags;
                }
            }

            @Override // android.support.v4.app.NotificationCompat.Action.Extender
            public Builder extend(Builder builder) {
                Bundle bundle = new Bundle();
                int i7 = this.mFlags;
                if (i7 != 1) {
                    bundle.putInt(KEY_FLAGS, i7);
                }
                CharSequence charSequence = this.mInProgressLabel;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, charSequence);
                }
                CharSequence charSequence2 = this.mConfirmLabel;
                if (charSequence2 != null) {
                    bundle.putCharSequence(KEY_CONFIRM_LABEL, charSequence2);
                }
                CharSequence charSequence3 = this.mCancelLabel;
                if (charSequence3 != null) {
                    bundle.putCharSequence(KEY_CANCEL_LABEL, charSequence3);
                }
                builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
                return builder;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            public WearableExtender setAvailableOffline(boolean z6) {
                setFlag(1, z6);
                return this;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence) {
                this.mCancelLabel = charSequence;
                return this;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence) {
                this.mConfirmLabel = charSequence;
                return this;
            }

            public WearableExtender setHintDisplayActionInline(boolean z6) {
                setFlag(4, z6);
                return this;
            }

            public WearableExtender setHintLaunchesActivity(boolean z6) {
                setFlag(2, z6);
                return this;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence) {
                this.mInProgressLabel = charSequence;
                return this;
            }

            /* renamed from: clone, reason: merged with bridge method [inline-methods] */
            public WearableExtender m2604clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }

            public WearableExtender(Action action) {
                this.mFlags = 1;
                Bundle bundle = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (bundle != null) {
                    this.mFlags = bundle.getInt(KEY_FLAGS, 1);
                    this.mInProgressLabel = bundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = bundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = bundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }
        }

        public Action(int i7, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i7, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true);
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public int getIcon() {
            return this.icon;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public Action(int i7, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z6, int i8, boolean z7) {
            this.mShowsUserInterface = true;
            this.icon = i7;
            this.title = Builder.limitCharSequenceLength(charSequence);
            this.actionIntent = pendingIntent;
            this.mExtras = bundle == null ? new Bundle() : bundle;
            this.mRemoteInputs = remoteInputArr;
            this.mDataOnlyRemoteInputs = remoteInputArr2;
            this.mAllowGeneratedReplies = z6;
            this.mSemanticAction = i8;
            this.mShowsUserInterface = z7;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface BadgeIconType {
    }

    public static class BigPictureStyle extends Style {
        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle() {
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigPictureStyle bigPictureStyleBigPicture = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
            if (this.mBigLargeIconSet) {
                bigPictureStyleBigPicture.bigLargeIcon(this.mBigLargeIcon);
            }
            if (this.mSummaryTextSet) {
                bigPictureStyleBigPicture.setSummaryText(this.mSummaryText);
            }
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap) {
            this.mBigLargeIcon = bitmap;
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap) {
            this.mPicture = bitmap;
            return this;
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }
    }

    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if (this.mSummaryTextSet) {
                bigTextStyleBigText.setSummaryText(this.mSummaryText);
            }
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.mBigText = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }
    }

    public static class Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ArrayList<Action> mActions;
        public int mBadgeIcon;
        public RemoteViews mBigContentView;
        public String mCategory;
        public String mChannelId;
        public int mColor;
        public boolean mColorized;
        public boolean mColorizedSet;
        public CharSequence mContentInfo;
        public PendingIntent mContentIntent;
        public CharSequence mContentText;
        public CharSequence mContentTitle;
        public RemoteViews mContentView;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Context mContext;
        public Bundle mExtras;
        public PendingIntent mFullScreenIntent;
        public int mGroupAlertBehavior;
        public String mGroupKey;
        public boolean mGroupSummary;
        public RemoteViews mHeadsUpContentView;
        public ArrayList<Action> mInvisibleActions;
        public Bitmap mLargeIcon;
        public boolean mLocalOnly;
        public Notification mNotification;
        public int mNumber;

        @Deprecated
        public ArrayList<String> mPeople;
        public int mPriority;
        public int mProgress;
        public boolean mProgressIndeterminate;
        public int mProgressMax;
        public Notification mPublicVersion;
        public CharSequence[] mRemoteInputHistory;
        public String mShortcutId;
        public boolean mShowWhen;
        public String mSortKey;
        public Style mStyle;
        public CharSequence mSubText;
        public RemoteViews mTickerView;
        public long mTimeout;
        public boolean mUseChronometer;
        public int mVisibility;

        public Builder(@NonNull Context context, @NonNull String str) {
            this.mActions = new ArrayList<>();
            this.mInvisibleActions = new ArrayList<>();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            Notification notification = new Notification();
            this.mNotification = notification;
            this.mContext = context;
            this.mChannelId = str;
            notification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList<>();
        }

        public static CharSequence limitCharSequenceLength(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > MAX_CHARSEQUENCE_LENGTH) ? charSequence.subSequence(0, MAX_CHARSEQUENCE_LENGTH) : charSequence;
        }

        private Bitmap reduceLargeIconSize(Bitmap bitmap) throws Resources.NotFoundException {
            if (bitmap == null || Build.VERSION.SDK_INT >= 27) {
                return bitmap;
            }
            Resources resources = this.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(C0068R.dimen.compat_notification_large_icon_max_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(C0068R.dimen.compat_notification_large_icon_max_height);
            if (bitmap.getWidth() <= dimensionPixelSize && bitmap.getHeight() <= dimensionPixelSize2) {
                return bitmap;
            }
            double dMin = Math.min(dimensionPixelSize / Math.max(1, bitmap.getWidth()), dimensionPixelSize2 / Math.max(1, bitmap.getHeight()));
            return Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(bitmap.getWidth() * dMin), (int) Math.ceil(bitmap.getHeight() * dMin), true);
        }

        private void setFlag(int i7, boolean z6) {
            if (z6) {
                Notification notification = this.mNotification;
                notification.flags = i7 | notification.flags;
            } else {
                Notification notification2 = this.mNotification;
                notification2.flags = (~i7) & notification2.flags;
            }
        }

        public Builder addAction(int i7, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i7, charSequence, pendingIntent));
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                Bundle bundle2 = this.mExtras;
                if (bundle2 == null) {
                    this.mExtras = new Bundle(bundle);
                } else {
                    bundle2.putAll(bundle);
                }
            }
            return this;
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(int i7, CharSequence charSequence, PendingIntent pendingIntent) {
            return addInvisibleAction(new Action(i7, charSequence, pendingIntent));
        }

        public Builder addPerson(String str) {
            this.mPeople.add(str);
            return this;
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getColor() {
            return this.mColor;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getContentView() {
            return this.mContentView;
        }

        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getPriority() {
            return this.mPriority;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public long getWhenIfShowing() {
            if (this.mShowWhen) {
                return this.mNotification.when;
            }
            return 0L;
        }

        public Builder setAutoCancel(boolean z6) {
            setFlag(16, z6);
            return this;
        }

        public Builder setBadgeIconType(int i7) {
            this.mBadgeIcon = i7;
            return this;
        }

        public Builder setCategory(String str) {
            this.mCategory = str;
            return this;
        }

        public Builder setChannelId(@NonNull String str) {
            this.mChannelId = str;
            return this;
        }

        public Builder setColor(@ColorInt int i7) {
            this.mColor = i7;
            return this;
        }

        public Builder setColorized(boolean z6) {
            this.mColorized = z6;
            this.mColorizedSet = true;
            return this;
        }

        public Builder setContent(RemoteViews remoteViews) {
            this.mNotification.contentView = remoteViews;
            return this;
        }

        public Builder setContentInfo(CharSequence charSequence) {
            this.mContentInfo = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteViews) {
            this.mBigContentView = remoteViews;
            return this;
        }

        public Builder setCustomContentView(RemoteViews remoteViews) {
            this.mContentView = remoteViews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteViews) {
            this.mHeadsUpContentView = remoteViews;
            return this;
        }

        public Builder setDefaults(int i7) {
            Notification notification = this.mNotification;
            notification.defaults = i7;
            if ((i7 & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.mNotification.deleteIntent = pendingIntent;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingIntent, boolean z6) {
            this.mFullScreenIntent = pendingIntent;
            setFlag(128, z6);
            return this;
        }

        public Builder setGroup(String str) {
            this.mGroupKey = str;
            return this;
        }

        public Builder setGroupAlertBehavior(int i7) {
            this.mGroupAlertBehavior = i7;
            return this;
        }

        public Builder setGroupSummary(boolean z6) {
            this.mGroupSummary = z6;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = reduceLargeIconSize(bitmap);
            return this;
        }

        public Builder setLights(@ColorInt int i7, int i8, int i9) {
            Notification notification = this.mNotification;
            notification.ledARGB = i7;
            notification.ledOnMS = i8;
            notification.ledOffMS = i9;
            notification.flags = ((i8 == 0 || i9 == 0) ? 0 : 1) | (notification.flags & (-2));
            return this;
        }

        public Builder setLocalOnly(boolean z6) {
            this.mLocalOnly = z6;
            return this;
        }

        public Builder setNumber(int i7) {
            this.mNumber = i7;
            return this;
        }

        public Builder setOngoing(boolean z6) {
            setFlag(2, z6);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean z6) {
            setFlag(8, z6);
            return this;
        }

        public Builder setPriority(int i7) {
            this.mPriority = i7;
            return this;
        }

        public Builder setProgress(int i7, int i8, boolean z6) {
            this.mProgressMax = i7;
            this.mProgress = i8;
            this.mProgressIndeterminate = z6;
            return this;
        }

        public Builder setPublicVersion(Notification notification) {
            this.mPublicVersion = notification;
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] charSequenceArr) {
            this.mRemoteInputHistory = charSequenceArr;
            return this;
        }

        public Builder setShortcutId(String str) {
            this.mShortcutId = str;
            return this;
        }

        public Builder setShowWhen(boolean z6) {
            this.mShowWhen = z6;
            return this;
        }

        public Builder setSmallIcon(int i7) {
            this.mNotification.icon = i7;
            return this;
        }

        public Builder setSortKey(String str) {
            this.mSortKey = str;
            return this;
        }

        public Builder setSound(Uri uri) {
            Notification notification = this.mNotification;
            notification.sound = uri;
            notification.audioStreamType = -1;
            notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                if (style != null) {
                    style.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setSubText(CharSequence charSequence) {
            this.mSubText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setTimeoutAfter(long j7) {
            this.mTimeout = j7;
            return this;
        }

        public Builder setUsesChronometer(boolean z6) {
            this.mUseChronometer = z6;
            return this;
        }

        public Builder setVibrate(long[] jArr) {
            this.mNotification.vibrate = jArr;
            return this;
        }

        public Builder setVisibility(int i7) {
            this.mVisibility = i7;
            return this;
        }

        public Builder setWhen(long j7) {
            this.mNotification.when = j7;
            return this;
        }

        public Builder addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(Action action) {
            this.mInvisibleActions.add(action);
            return this;
        }

        public Builder setSmallIcon(int i7, int i8) {
            Notification notification = this.mNotification;
            notification.icon = i7;
            notification.iconLevel = i8;
            return this;
        }

        public Builder setTicker(CharSequence charSequence, RemoteViews remoteViews) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            this.mTickerView = remoteViews;
            return this;
        }

        public Builder setSound(Uri uri, int i7) {
            Notification notification = this.mNotification;
            notification.sound = uri;
            notification.audioStreamType = i7;
            notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(i7).build();
            return this;
        }

        @Deprecated
        public Builder(Context context) {
            this(context, null);
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;

        private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z6) throws Resources.NotFoundException {
            ArrayList<Action> arrayList;
            int iMin;
            boolean z7 = true;
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(true, C0068R.layout.notification_template_custom_big, false);
            remoteViewsApplyStandardTemplate.removeAllViews(C0068R.id.actions);
            if (!z6 || (arrayList = this.mBuilder.mActions) == null || (iMin = Math.min(arrayList.size(), 3)) <= 0) {
                z7 = false;
            } else {
                for (int i7 = 0; i7 < iMin; i7++) {
                    remoteViewsApplyStandardTemplate.addView(C0068R.id.actions, generateActionButton(this.mBuilder.mActions.get(i7)));
                }
            }
            int i8 = z7 ? 0 : 8;
            remoteViewsApplyStandardTemplate.setViewVisibility(C0068R.id.actions, i8);
            remoteViewsApplyStandardTemplate.setViewVisibility(C0068R.id.action_divider, i8);
            buildIntoRemoteViews(remoteViewsApplyStandardTemplate, remoteViews);
            return remoteViewsApplyStandardTemplate;
        }

        private RemoteViews generateActionButton(Action action) {
            boolean z6 = action.actionIntent == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), z6 ? C0068R.layout.notification_action_tombstone : C0068R.layout.notification_action);
            remoteViews.setImageViewBitmap(C0068R.id.action_image, createColoredBitmap(action.getIcon(), this.mBuilder.mContext.getResources().getColor(C0068R.color.notification_action_color_filter)));
            remoteViews.setTextViewText(C0068R.id.action_text, action.title);
            if (!z6) {
                remoteViews.setOnClickPendingIntent(C0068R.id.action_container, action.actionIntent);
            }
            remoteViews.setContentDescription(C0068R.id.action_container, action.title);
            return remoteViews;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
            }
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.mBuilder.getBigContentView();
            if (bigContentView == null) {
                bigContentView = this.mBuilder.getContentView();
            }
            if (bigContentView == null) {
                return null;
            }
            return createRemoteViews(bigContentView, true);
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT < 24 && this.mBuilder.getContentView() != null) {
                return createRemoteViews(this.mBuilder.getContentView(), false);
            }
            return null;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUpContentView = this.mBuilder.getHeadsUpContentView();
            RemoteViews contentView = headsUpContentView != null ? headsUpContentView : this.mBuilder.getContentView();
            if (headsUpContentView == null) {
                return null;
            }
            return createRemoteViews(contentView, true);
        }
    }

    public interface Extender {
        Builder extend(Builder builder);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface GroupAlertBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface NotificationVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface StreamType {
    }

    public static abstract class Style {
        public CharSequence mBigContentTitle;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder mBuilder;
        public CharSequence mSummaryText;
        public boolean mSummaryTextSet = false;

        private int calculateTopPadding() throws Resources.NotFoundException {
            Resources resources = this.mBuilder.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(C0068R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(C0068R.dimen.notification_top_pad_large_text);
            float fConstrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round((fConstrain * dimensionPixelSize2) + ((1.0f - fConstrain) * dimensionPixelSize));
        }

        private static float constrain(float f7, float f8, float f9) {
            return f7 < f8 ? f8 : f7 > f9 ? f9 : f7;
        }

        private Bitmap createIconWithBackground(int i7, int i8, int i9, int i10) throws Resources.NotFoundException {
            int i11 = C0068R.drawable.notification_icon_background;
            if (i10 == 0) {
                i10 = 0;
            }
            Bitmap bitmapCreateColoredBitmap = createColoredBitmap(i11, i10, i8);
            Canvas canvas = new Canvas(bitmapCreateColoredBitmap);
            Drawable drawableMutate = this.mBuilder.mContext.getResources().getDrawable(i7).mutate();
            drawableMutate.setFilterBitmap(true);
            int i12 = (i8 - i9) / 2;
            int i13 = i9 + i12;
            drawableMutate.setBounds(i12, i12, i13, i13);
            drawableMutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            drawableMutate.draw(canvas);
            return bitmapCreateColoredBitmap;
        }

        private void hideNormalContent(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(C0068R.id.title, 8);
            remoteViews.setViewVisibility(C0068R.id.text2, 8);
            remoteViews.setViewVisibility(C0068R.id.text, 8);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void addCompatExtras(Bundle bundle) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x00fc  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0116  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0119  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x013d  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0174  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0179  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x017b  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0185  */
        @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.widget.RemoteViews applyStandardTemplate(boolean r12, int r13, boolean r14) throws android.content.res.Resources.NotFoundException {
            /*
                Method dump skipped, instructions count: 395
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompat.Style.applyStandardTemplate(boolean, int, boolean):android.widget.RemoteViews");
        }

        public Notification build() {
            Builder builder = this.mBuilder;
            if (builder != null) {
                return builder.build();
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            hideNormalContent(remoteViews);
            int i7 = C0068R.id.notification_main_column;
            remoteViews.removeAllViews(i7);
            remoteViews.addView(i7, remoteViews2.clone());
            remoteViews.setViewVisibility(i7, 0);
            remoteViews.setViewPadding(C0068R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bitmap createColoredBitmap(int i7, int i8) {
            return createColoredBitmap(i7, i8, 0);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void restoreFromCompatExtras(Bundle bundle) {
        }

        public void setBuilder(Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                if (builder != null) {
                    builder.setStyle(this);
                }
            }
        }

        private Bitmap createColoredBitmap(int i7, int i8, int i9) throws Resources.NotFoundException {
            Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(i7);
            int intrinsicWidth = i9 == 0 ? drawable.getIntrinsicWidth() : i9;
            if (i9 == 0) {
                i9 = drawable.getIntrinsicHeight();
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, i9, Bitmap.Config.ARGB_8888);
            drawable.setBounds(0, 0, intrinsicWidth, i9);
            if (i8 != 0) {
                drawable.mutate().setColorFilter(new PorterDuffColorFilter(i8, PorterDuff.Mode.SRC_IN));
            }
            drawable.draw(new Canvas(bitmapCreateBitmap));
            return bitmapCreateBitmap;
        }
    }

    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages;

        public WearableExtender() {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        @RequiresApi(20)
        private static Notification.Action getActionFromActionCompat(Action action) {
            Notification.Action.Builder builder = new Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
            bundle.putBoolean(NotificationCompatJellybean.EXTRA_ALLOW_GENERATED_REPLIES, action.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            builder.addExtras(bundle);
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (android.app.RemoteInput remoteInput : RemoteInput.fromCompat(remoteInputs)) {
                    builder.addRemoteInput(remoteInput);
                }
            }
            return builder.build();
        }

        private void setFlag(int i7, boolean z6) {
            if (z6) {
                this.mFlags = i7 | this.mFlags;
            } else {
                this.mFlags = (~i7) & this.mFlags;
            }
        }

        public WearableExtender addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }

        public WearableExtender addPage(Notification notification) {
            this.mPages.add(notification);
            return this;
        }

        public WearableExtender addPages(List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        @Override // android.support.v4.app.NotificationCompat.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.mActions.size());
                Iterator<Action> it = this.mActions.iterator();
                while (it.hasNext()) {
                    arrayList.add(getActionFromActionCompat(it.next()));
                }
                bundle.putParcelableArrayList(KEY_ACTIONS, arrayList);
            }
            int i7 = this.mFlags;
            if (i7 != 1) {
                bundle.putInt(KEY_FLAGS, i7);
            }
            PendingIntent pendingIntent = this.mDisplayIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(KEY_DISPLAY_INTENT, pendingIntent);
            }
            if (!this.mPages.isEmpty()) {
                ArrayList<Notification> arrayList2 = this.mPages;
                bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) arrayList2.toArray(new Notification[arrayList2.size()]));
            }
            Bitmap bitmap = this.mBackground;
            if (bitmap != null) {
                bundle.putParcelable(KEY_BACKGROUND, bitmap);
            }
            int i8 = this.mContentIcon;
            if (i8 != 0) {
                bundle.putInt(KEY_CONTENT_ICON, i8);
            }
            int i9 = this.mContentIconGravity;
            if (i9 != 8388613) {
                bundle.putInt(KEY_CONTENT_ICON_GRAVITY, i9);
            }
            int i10 = this.mContentActionIndex;
            if (i10 != -1) {
                bundle.putInt(KEY_CONTENT_ACTION_INDEX, i10);
            }
            int i11 = this.mCustomSizePreset;
            if (i11 != 0) {
                bundle.putInt(KEY_CUSTOM_SIZE_PRESET, i11);
            }
            int i12 = this.mCustomContentHeight;
            if (i12 != 0) {
                bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, i12);
            }
            int i13 = this.mGravity;
            if (i13 != 80) {
                bundle.putInt(KEY_GRAVITY, i13);
            }
            int i14 = this.mHintScreenTimeout;
            if (i14 != 0) {
                bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, i14);
            }
            String str = this.mDismissalId;
            if (str != null) {
                bundle.putString(KEY_DISMISSAL_ID, str);
            }
            String str2 = this.mBridgeTag;
            if (str2 != null) {
                bundle.putString(KEY_BRIDGE_TAG, str2);
            }
            builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
            return builder;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        public Bitmap getBackground() {
            return this.mBackground;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        public List<Notification> getPages() {
            return this.mPages;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public WearableExtender setBackground(Bitmap bitmap) {
            this.mBackground = bitmap;
            return this;
        }

        public WearableExtender setBridgeTag(String str) {
            this.mBridgeTag = str;
            return this;
        }

        public WearableExtender setContentAction(int i7) {
            this.mContentActionIndex = i7;
            return this;
        }

        @Deprecated
        public WearableExtender setContentIcon(int i7) {
            this.mContentIcon = i7;
            return this;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int i7) {
            this.mContentIconGravity = i7;
            return this;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean z6) {
            setFlag(1, z6);
            return this;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int i7) {
            this.mCustomContentHeight = i7;
            return this;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int i7) {
            this.mCustomSizePreset = i7;
            return this;
        }

        public WearableExtender setDismissalId(String str) {
            this.mDismissalId = str;
            return this;
        }

        public WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
            this.mDisplayIntent = pendingIntent;
            return this;
        }

        @Deprecated
        public WearableExtender setGravity(int i7) {
            this.mGravity = i7;
            return this;
        }

        public WearableExtender setHintAmbientBigPicture(boolean z6) {
            setFlag(32, z6);
            return this;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean z6) {
            setFlag(16, z6);
            return this;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean z6) {
            setFlag(64, z6);
            return this;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean z6) {
            setFlag(2, z6);
            return this;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int i7) {
            this.mHintScreenTimeout = i7;
            return this;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean z6) {
            setFlag(4, z6);
            return this;
        }

        public WearableExtender setStartScrollBottom(boolean z6) {
            setFlag(8, z6);
            return this;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public WearableExtender m2605clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            wearableExtender.mBridgeTag = this.mBridgeTag;
            return wearableExtender;
        }

        public WearableExtender(Notification notification) {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle = extras != null ? extras.getBundle(EXTRA_WEARABLE_EXTENSIONS) : null;
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_ACTIONS);
                if (parcelableArrayList != null) {
                    int size = parcelableArrayList.size();
                    Action[] actionArr = new Action[size];
                    for (int i7 = 0; i7 < size; i7++) {
                        actionArr[i7] = NotificationCompat.getActionCompatFromAction((Notification.Action) parcelableArrayList.get(i7));
                    }
                    Collections.addAll(this.mActions, actionArr);
                }
                this.mFlags = bundle.getInt(KEY_FLAGS, 1);
                this.mDisplayIntent = (PendingIntent) bundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] notificationArrayFromBundle = NotificationCompat.getNotificationArrayFromBundle(bundle, KEY_PAGES);
                if (notificationArrayFromBundle != null) {
                    Collections.addAll(this.mPages, notificationArrayFromBundle);
                }
                this.mBackground = (Bitmap) bundle.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = bundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = bundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = bundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = bundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = bundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = bundle.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = bundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = bundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = bundle.getString(KEY_BRIDGE_TAG);
            }
        }
    }

    @Deprecated
    public NotificationCompat() {
    }

    public static Action getAction(Notification notification, int i7) {
        return getActionCompatFromAction(notification.actions[i7]);
    }

    @RequiresApi(20)
    public static Action getActionCompatFromAction(Notification.Action action) {
        RemoteInput[] remoteInputArr;
        android.app.RemoteInput[] remoteInputs = action.getRemoteInputs();
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i7 = 0; i7 < remoteInputs.length; i7++) {
                android.app.RemoteInput remoteInput = remoteInputs[i7];
                remoteInputArr2[i7] = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), null);
            }
            remoteInputArr = remoteInputArr2;
        }
        int i8 = Build.VERSION.SDK_INT;
        return new Action(action.icon, action.title, action.actionIntent, action.getExtras(), remoteInputArr, null, i8 >= 24 ? action.getExtras().getBoolean(NotificationCompatJellybean.EXTRA_ALLOW_GENERATED_REPLIES) || action.getAllowGeneratedReplies() : action.getExtras().getBoolean(NotificationCompatJellybean.EXTRA_ALLOW_GENERATED_REPLIES), i8 >= 28 ? action.getSemanticAction() : action.getExtras().getInt(Action.EXTRA_SEMANTIC_ACTION, 0), action.getExtras().getBoolean(Action.EXTRA_SHOWS_USER_INTERFACE, true));
    }

    public static int getActionCount(Notification notification) {
        Notification.Action[] actionArr = notification.actions;
        if (actionArr != null) {
            return actionArr.length;
        }
        return 0;
    }

    public static int getBadgeIconType(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getBadgeIconType();
        }
        return 0;
    }

    public static String getCategory(Notification notification) {
        return notification.category;
    }

    public static String getChannelId(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getChannelId();
        }
        return null;
    }

    @RequiresApi(19)
    public static CharSequence getContentTitle(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    @Nullable
    public static Bundle getExtras(Notification notification) {
        return notification.extras;
    }

    public static String getGroup(Notification notification) {
        return notification.getGroup();
    }

    public static int getGroupAlertBehavior(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getGroupAlertBehavior();
        }
        return 0;
    }

    @RequiresApi(21)
    public static List<Action> getInvisibleActions(Notification notification) {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        Bundle bundle2 = notification.extras.getBundle(CarExtender.EXTRA_CAR_EXTENDER);
        if (bundle2 != null && (bundle = bundle2.getBundle(CarExtender.EXTRA_INVISIBLE_ACTIONS)) != null) {
            for (int i7 = 0; i7 < bundle.size(); i7++) {
                arrayList.add(NotificationCompatJellybean.getActionFromBundle(bundle.getBundle(Integer.toString(i7))));
            }
        }
        return arrayList;
    }

    public static boolean getLocalOnly(Notification notification) {
        return (notification.flags & 256) != 0;
    }

    public static Notification[] getNotificationArrayFromBundle(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Notification[]) || parcelableArray == null) {
            return (Notification[]) parcelableArray;
        }
        Notification[] notificationArr = new Notification[parcelableArray.length];
        for (int i7 = 0; i7 < parcelableArray.length; i7++) {
            notificationArr[i7] = (Notification) parcelableArray[i7];
        }
        bundle.putParcelableArray(str, notificationArr);
        return notificationArr;
    }

    public static String getShortcutId(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getShortcutId();
        }
        return null;
    }

    public static String getSortKey(Notification notification) {
        return notification.getSortKey();
    }

    public static long getTimeoutAfter(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getTimeoutAfter();
        }
        return 0L;
    }

    public static boolean isGroupSummary(Notification notification) {
        return (notification.flags & 512) != 0;
    }

    public static final class CarExtender implements Extender {

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public static class UnreadConversation {
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            public static class Builder {
                private long mLatestTimestamp;
                private final List<String> mMessages = new ArrayList();
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public Builder(String str) {
                    this.mParticipant = str;
                }

                public Builder addMessage(String str) {
                    this.mMessages.add(str);
                    return this;
                }

                public UnreadConversation build() {
                    List<String> list = this.mMessages;
                    return new UnreadConversation((String[]) list.toArray(new String[list.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }

                public Builder setLatestTimestamp(long j7) {
                    this.mLatestTimestamp = j7;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }
            }

            public UnreadConversation(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j7) {
                this.mMessages = strArr;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = pendingIntent2;
                this.mReplyPendingIntent = pendingIntent;
                this.mParticipants = strArr2;
                this.mLatestTimestamp = j7;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public String getParticipant() {
                String[] strArr = this.mParticipants;
                if (strArr.length > 0) {
                    return strArr[0];
                }
                return null;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
        }

        public CarExtender() {
            this.mColor = 0;
        }

        @RequiresApi(21)
        private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation unreadConversation) {
            Bundle bundle = new Bundle();
            String str = (unreadConversation.getParticipants() == null || unreadConversation.getParticipants().length <= 1) ? null : unreadConversation.getParticipants()[0];
            int length = unreadConversation.getMessages().length;
            Parcelable[] parcelableArr = new Parcelable[length];
            for (int i7 = 0; i7 < length; i7++) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("text", unreadConversation.getMessages()[i7]);
                bundle2.putString(KEY_AUTHOR, str);
                parcelableArr[i7] = bundle2;
            }
            bundle.putParcelableArray(KEY_MESSAGES, parcelableArr);
            RemoteInput remoteInput = unreadConversation.getRemoteInput();
            if (remoteInput != null) {
                bundle.putParcelable(KEY_REMOTE_INPUT, new RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build());
            }
            bundle.putParcelable(KEY_ON_REPLY, unreadConversation.getReplyPendingIntent());
            bundle.putParcelable(KEY_ON_READ, unreadConversation.getReadPendingIntent());
            bundle.putStringArray(KEY_PARTICIPANTS, unreadConversation.getParticipants());
            bundle.putLong(KEY_TIMESTAMP, unreadConversation.getLatestTimestamp());
            return bundle;
        }

        @RequiresApi(21)
        private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle bundle) {
            String[] strArr;
            if (bundle == null) {
                return null;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(KEY_MESSAGES);
            if (parcelableArray != null) {
                int length = parcelableArray.length;
                String[] strArr2 = new String[length];
                boolean z6 = false;
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        z6 = true;
                        break;
                    }
                    if (!(parcelableArray[i7] instanceof Bundle)) {
                        break;
                    }
                    strArr2[i7] = ((Bundle) parcelableArray[i7]).getString("text");
                    if (strArr2[i7] == null) {
                        break;
                    }
                    i7++;
                }
                if (!z6) {
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(KEY_ON_READ);
            PendingIntent pendingIntent2 = (PendingIntent) bundle.getParcelable(KEY_ON_REPLY);
            android.app.RemoteInput remoteInput = (android.app.RemoteInput) bundle.getParcelable(KEY_REMOTE_INPUT);
            String[] stringArray = bundle.getStringArray(KEY_PARTICIPANTS);
            if (stringArray == null || stringArray.length != 1) {
                return null;
            }
            return new UnreadConversation(strArr, remoteInput != null ? new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), null) : null, pendingIntent2, pendingIntent, stringArray, bundle.getLong(KEY_TIMESTAMP));
        }

        @Override // android.support.v4.app.NotificationCompat.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            Bitmap bitmap = this.mLargeIcon;
            if (bitmap != null) {
                bundle.putParcelable(EXTRA_LARGE_ICON, bitmap);
            }
            int i7 = this.mColor;
            if (i7 != 0) {
                bundle.putInt(EXTRA_COLOR, i7);
            }
            UnreadConversation unreadConversation = this.mUnreadConversation;
            if (unreadConversation != null) {
                bundle.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(unreadConversation));
            }
            builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
            return builder;
        }

        @ColorInt
        public int getColor() {
            return this.mColor;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        public CarExtender setColor(@ColorInt int i7) {
            this.mColor = i7;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap;
            return this;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        public CarExtender(Notification notification) {
            this.mColor = 0;
            Bundle bundle = NotificationCompat.getExtras(notification) == null ? null : NotificationCompat.getExtras(notification).getBundle(EXTRA_CAR_EXTENDER);
            if (bundle != null) {
                this.mLargeIcon = (Bitmap) bundle.getParcelable(EXTRA_LARGE_ICON);
                this.mColor = bundle.getInt(EXTRA_COLOR, 0);
                this.mUnreadConversation = getUnreadConversationFromBundle(bundle.getBundle(EXTRA_CONVERSATION));
            }
        }
    }

    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> mTexts = new ArrayList<>();

        public InboxStyle() {
        }

        public InboxStyle addLine(CharSequence charSequence) {
            this.mTexts.add(Builder.limitCharSequenceLength(charSequence));
            return this;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            if (this.mSummaryTextSet) {
                bigContentTitle.setSummaryText(this.mSummaryText);
            }
            Iterator<CharSequence> it = this.mTexts.iterator();
            while (it.hasNext()) {
                bigContentTitle.addLine(it.next());
            }
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;

        @Nullable
        private CharSequence mConversationTitle;

        @Nullable
        private Boolean mIsGroupConversation;
        private final List<Message> mMessages = new ArrayList();
        private Person mUser;

        private MessagingStyle() {
        }

        @Nullable
        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras != null && !extras.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME) && !extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                return null;
            }
            try {
                MessagingStyle messagingStyle = new MessagingStyle();
                messagingStyle.restoreFromCompatExtras(extras);
                return messagingStyle;
            } catch (ClassCastException unused) {
                return null;
            }
        }

        @Nullable
        private Message findLatestIncomingMessage() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
            if (this.mMessages.isEmpty()) {
                return null;
            }
            return this.mMessages.get(r0.size() - 1);
        }

        private boolean hasMessagesWithoutSender() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        @NonNull
        private TextAppearanceSpan makeFontColorSpan(int i7) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i7), null);
        }

        private CharSequence makeMessageLine(Message message) {
            BidiFormatter bidiFormatter = BidiFormatter.getInstance();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int color = ViewCompat.MEASURED_STATE_MASK;
            CharSequence name = message.getPerson() == null ? "" : message.getPerson().getName();
            if (TextUtils.isEmpty(name)) {
                name = this.mUser.getName();
                if (this.mBuilder.getColor() != 0) {
                    color = this.mBuilder.getColor();
                }
            }
            CharSequence charSequenceUnicodeWrap = bidiFormatter.unicodeWrap(name);
            spannableStringBuilder.append(charSequenceUnicodeWrap);
            spannableStringBuilder.setSpan(makeFontColorSpan(color), spannableStringBuilder.length() - charSequenceUnicodeWrap.length(), spannableStringBuilder.length(), 33);
            spannableStringBuilder.append((CharSequence) "  ").append(bidiFormatter.unicodeWrap(message.getText() != null ? message.getText() : ""));
            return spannableStringBuilder;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            bundle.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.mUser.getName());
            bundle.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.mUser.toBundle());
            bundle.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.mConversationTitle);
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                bundle.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                bundle.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.getBundleArrayForMessages(this.mMessages));
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                bundle.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, bool.booleanValue());
            }
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence charSequence, long j7, CharSequence charSequence2) {
            this.mMessages.add(new Message(charSequence, j7, new Person.Builder().setName(charSequence2).build()));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.MessagingStyle.Message message;
            setGroupConversation(isGroupConversation());
            int i7 = Build.VERSION.SDK_INT;
            if (i7 >= 24) {
                Notification.MessagingStyle messagingStyle = i7 >= 28 ? new Notification.MessagingStyle(this.mUser.toAndroidPerson()) : new Notification.MessagingStyle(this.mUser.getName());
                if (this.mIsGroupConversation.booleanValue() || i7 >= 28) {
                    messagingStyle.setConversationTitle(this.mConversationTitle);
                }
                if (i7 >= 28) {
                    messagingStyle.setGroupConversation(this.mIsGroupConversation.booleanValue());
                }
                for (Message message2 : this.mMessages) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        Person person = message2.getPerson();
                        message = new Notification.MessagingStyle.Message(message2.getText(), message2.getTimestamp(), person == null ? null : person.toAndroidPerson());
                    } else {
                        message = new Notification.MessagingStyle.Message(message2.getText(), message2.getTimestamp(), message2.getPerson() != null ? message2.getPerson().getName() : null);
                    }
                    if (message2.getDataMimeType() != null) {
                        message.setData(message2.getDataMimeType(), message2.getDataUri());
                    }
                    messagingStyle.addMessage(message);
                }
                messagingStyle.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
                return;
            }
            Message messageFindLatestIncomingMessage = findLatestIncomingMessage();
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(this.mConversationTitle);
            } else if (messageFindLatestIncomingMessage != null) {
                notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle("");
                if (messageFindLatestIncomingMessage.getPerson() != null) {
                    notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(messageFindLatestIncomingMessage.getPerson().getName());
                }
            }
            if (messageFindLatestIncomingMessage != null) {
                notificationBuilderWithBuilderAccessor.getBuilder().setContentText(this.mConversationTitle != null ? makeMessageLine(messageFindLatestIncomingMessage) : messageFindLatestIncomingMessage.getText());
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            boolean z6 = this.mConversationTitle != null || hasMessagesWithoutSender();
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message3 = this.mMessages.get(size);
                CharSequence charSequenceMakeMessageLine = z6 ? makeMessageLine(message3) : message3.getText();
                if (size != this.mMessages.size() - 1) {
                    spannableStringBuilder.insert(0, (CharSequence) "\n");
                }
                spannableStringBuilder.insert(0, charSequenceMakeMessageLine);
            }
            new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(null).bigText(spannableStringBuilder);
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public Person getUser() {
            return this.mUser;
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public boolean isGroupConversation() {
            Builder builder = this.mBuilder;
            if (builder != null && builder.mContext.getApplicationInfo().targetSdkVersion < 28 && this.mIsGroupConversation == null) {
                return this.mConversationTitle != null;
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void restoreFromCompatExtras(Bundle bundle) {
            this.mMessages.clear();
            if (bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                this.mUser = Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER));
            } else {
                this.mUser = new Person.Builder().setName(bundle.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            }
            CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            this.mConversationTitle = charSequence;
            if (charSequence == null) {
                this.mConversationTitle = bundle.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelableArray != null) {
                this.mMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        public MessagingStyle setConversationTitle(@Nullable CharSequence charSequence) {
            this.mConversationTitle = charSequence;
            return this;
        }

        public MessagingStyle setGroupConversation(boolean z6) {
            this.mIsGroupConversation = Boolean.valueOf(z6);
            return this;
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence charSequence) {
            this.mUser = new Person.Builder().setName(charSequence).build();
        }

        public static final class Message {
            public static final String KEY_DATA_MIME_TYPE = "type";
            public static final String KEY_DATA_URI = "uri";
            public static final String KEY_EXTRAS_BUNDLE = "extras";
            public static final String KEY_NOTIFICATION_PERSON = "sender_person";
            public static final String KEY_PERSON = "person";
            public static final String KEY_SENDER = "sender";
            public static final String KEY_TEXT = "text";
            public static final String KEY_TIMESTAMP = "time";

            @Nullable
            private String mDataMimeType;

            @Nullable
            private Uri mDataUri;
            private Bundle mExtras;

            @Nullable
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence charSequence, long j7, @Nullable Person person) {
                this.mExtras = new Bundle();
                this.mText = charSequence;
                this.mTimestamp = j7;
                this.mPerson = person;
            }

            @NonNull
            public static Bundle[] getBundleArrayForMessages(List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i7 = 0; i7 < size; i7++) {
                    bundleArr[i7] = list.get(i7).toBundle();
                }
                return bundleArr;
            }

            @Nullable
            public static Message getMessageFromBundle(Bundle bundle) {
                try {
                    if (bundle.containsKey(KEY_TEXT) && bundle.containsKey("time")) {
                        Message message = new Message(bundle.getCharSequence(KEY_TEXT), bundle.getLong("time"), bundle.containsKey(KEY_PERSON) ? Person.fromBundle(bundle.getBundle(KEY_PERSON)) : (!bundle.containsKey(KEY_NOTIFICATION_PERSON) || Build.VERSION.SDK_INT < 28) ? bundle.containsKey(KEY_SENDER) ? new Person.Builder().setName(bundle.getCharSequence(KEY_SENDER)).build() : null : Person.fromAndroidPerson((android.app.Person) bundle.getParcelable(KEY_NOTIFICATION_PERSON)));
                        if (bundle.containsKey("type") && bundle.containsKey(KEY_DATA_URI)) {
                            message.setData(bundle.getString("type"), (Uri) bundle.getParcelable(KEY_DATA_URI));
                        }
                        if (bundle.containsKey(KEY_EXTRAS_BUNDLE)) {
                            message.getExtras().putAll(bundle.getBundle(KEY_EXTRAS_BUNDLE));
                        }
                        return message;
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @NonNull
            public static List<Message> getMessagesFromBundleArray(Parcelable[] parcelableArr) {
                Message messageFromBundle;
                ArrayList arrayList = new ArrayList(parcelableArr.length);
                for (int i7 = 0; i7 < parcelableArr.length; i7++) {
                    if ((parcelableArr[i7] instanceof Bundle) && (messageFromBundle = getMessageFromBundle((Bundle) parcelableArr[i7])) != null) {
                        arrayList.add(messageFromBundle);
                    }
                }
                return arrayList;
            }

            private Bundle toBundle() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.mText;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_TEXT, charSequence);
                }
                bundle.putLong("time", this.mTimestamp);
                Person person = this.mPerson;
                if (person != null) {
                    bundle.putCharSequence(KEY_SENDER, person.getName());
                    if (Build.VERSION.SDK_INT >= 28) {
                        bundle.putParcelable(KEY_NOTIFICATION_PERSON, this.mPerson.toAndroidPerson());
                    } else {
                        bundle.putBundle(KEY_PERSON, this.mPerson.toBundle());
                    }
                }
                String str = this.mDataMimeType;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.mDataUri;
                if (uri != null) {
                    bundle.putParcelable(KEY_DATA_URI, uri);
                }
                Bundle bundle2 = this.mExtras;
                if (bundle2 != null) {
                    bundle.putBundle(KEY_EXTRAS_BUNDLE, bundle2);
                }
                return bundle;
            }

            @Nullable
            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            @Nullable
            public Uri getDataUri() {
                return this.mDataUri;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @Nullable
            public Person getPerson() {
                return this.mPerson;
            }

            @Nullable
            @Deprecated
            public CharSequence getSender() {
                Person person = this.mPerson;
                if (person == null) {
                    return null;
                }
                return person.getName();
            }

            @NonNull
            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Message setData(String str, Uri uri) {
                this.mDataMimeType = str;
                this.mDataUri = uri;
                return this;
            }

            @Deprecated
            public Message(CharSequence charSequence, long j7, CharSequence charSequence2) {
                this(charSequence, j7, new Person.Builder().setName(charSequence2).build());
            }
        }

        public MessagingStyle(@NonNull Person person) {
            if (!TextUtils.isEmpty(person.getName())) {
                this.mUser = person;
                return;
            }
            throw new IllegalArgumentException("User's name must not be empty.");
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j7, Person person) {
            addMessage(new Message(charSequence, j7, person));
            return this;
        }

        public MessagingStyle addMessage(Message message) {
            this.mMessages.add(message);
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }
    }
}
