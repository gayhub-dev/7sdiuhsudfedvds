package android.support.v4.app;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private final Bundle mExtras = new Bundle();

    public NotificationCompatBuilder(NotificationCompat.Builder builder) {
        this.mBuilderCompat = builder;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder = new Notification.Builder(builder.mContext, builder.mChannelId);
        } else {
            this.mBuilder = new Notification.Builder(builder.mContext);
        }
        Notification notification = builder.mNotification;
        this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(builder.mContentTitle).setContentText(builder.mContentText).setContentInfo(builder.mContentInfo).setContentIntent(builder.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(builder.mFullScreenIntent, (notification.flags & 128) != 0).setLargeIcon(builder.mLargeIcon).setNumber(builder.mNumber).setProgress(builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate);
        this.mBuilder.setSubText(builder.mSubText).setUsesChronometer(builder.mUseChronometer).setPriority(builder.mPriority);
        Iterator<NotificationCompat.Action> it = builder.mActions.iterator();
        while (it.hasNext()) {
            addAction(it.next());
        }
        Bundle bundle = builder.mExtras;
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        this.mContentView = builder.mContentView;
        this.mBigContentView = builder.mBigContentView;
        this.mBuilder.setShowWhen(builder.mShowWhen);
        this.mBuilder.setLocalOnly(builder.mLocalOnly).setGroup(builder.mGroupKey).setGroupSummary(builder.mGroupSummary).setSortKey(builder.mSortKey);
        this.mGroupAlertBehavior = builder.mGroupAlertBehavior;
        this.mBuilder.setCategory(builder.mCategory).setColor(builder.mColor).setVisibility(builder.mVisibility).setPublicVersion(builder.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
        Iterator<String> it2 = builder.mPeople.iterator();
        while (it2.hasNext()) {
            this.mBuilder.addPerson(it2.next());
        }
        this.mHeadsUpContentView = builder.mHeadsUpContentView;
        if (builder.mInvisibleActions.size() > 0) {
            Bundle bundle2 = builder.getExtras().getBundle(NotificationCompat.CarExtender.EXTRA_CAR_EXTENDER);
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle();
            for (int i7 = 0; i7 < builder.mInvisibleActions.size(); i7++) {
                bundle3.putBundle(Integer.toString(i7), NotificationCompatJellybean.getBundleForAction(builder.mInvisibleActions.get(i7)));
            }
            bundle2.putBundle(NotificationCompat.CarExtender.EXTRA_INVISIBLE_ACTIONS, bundle3);
            builder.getExtras().putBundle(NotificationCompat.CarExtender.EXTRA_CAR_EXTENDER, bundle2);
            this.mExtras.putBundle(NotificationCompat.CarExtender.EXTRA_CAR_EXTENDER, bundle2);
        }
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 24) {
            this.mBuilder.setExtras(builder.mExtras).setRemoteInputHistory(builder.mRemoteInputHistory);
            RemoteViews remoteViews = builder.mContentView;
            if (remoteViews != null) {
                this.mBuilder.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = builder.mBigContentView;
            if (remoteViews2 != null) {
                this.mBuilder.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = builder.mHeadsUpContentView;
            if (remoteViews3 != null) {
                this.mBuilder.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (i8 >= 26) {
            this.mBuilder.setBadgeIconType(builder.mBadgeIcon).setShortcutId(builder.mShortcutId).setTimeoutAfter(builder.mTimeout).setGroupAlertBehavior(builder.mGroupAlertBehavior);
            if (builder.mColorizedSet) {
                this.mBuilder.setColorized(builder.mColorized);
            }
            if (TextUtils.isEmpty(builder.mChannelId)) {
                return;
            }
            this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
    }

    private void addAction(NotificationCompat.Action action) {
        Notification.Action.Builder builder = new Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
        if (action.getRemoteInputs() != null) {
            for (android.app.RemoteInput remoteInput : RemoteInput.fromCompat(action.getRemoteInputs())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
        bundle.putBoolean(NotificationCompatJellybean.EXTRA_ALLOW_GENERATED_REPLIES, action.getAllowGeneratedReplies());
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 24) {
            builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        }
        bundle.putInt(NotificationCompat.Action.EXTRA_SEMANTIC_ACTION, action.getSemanticAction());
        if (i7 >= 28) {
            builder.setSemanticAction(action.getSemanticAction());
        }
        bundle.putBoolean(NotificationCompat.Action.EXTRA_SHOWS_USER_INTERFACE, action.getShowsUserInterface());
        builder.addExtras(bundle);
        this.mBuilder.addAction(builder.build());
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i7 = notification.defaults & (-2);
        notification.defaults = i7;
        notification.defaults = i7 & (-3);
    }

    public Notification build() {
        Bundle extras;
        RemoteViews remoteViewsMakeHeadsUpContentView;
        RemoteViews remoteViewsMakeBigContentView;
        NotificationCompat.Style style = this.mBuilderCompat.mStyle;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews remoteViewsMakeContentView = style != null ? style.makeContentView(this) : null;
        Notification notificationBuildInternal = buildInternal();
        if (remoteViewsMakeContentView != null) {
            notificationBuildInternal.contentView = remoteViewsMakeContentView;
        } else {
            RemoteViews remoteViews = this.mBuilderCompat.mContentView;
            if (remoteViews != null) {
                notificationBuildInternal.contentView = remoteViews;
            }
        }
        if (style != null && (remoteViewsMakeBigContentView = style.makeBigContentView(this)) != null) {
            notificationBuildInternal.bigContentView = remoteViewsMakeBigContentView;
        }
        if (style != null && (remoteViewsMakeHeadsUpContentView = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this)) != null) {
            notificationBuildInternal.headsUpContentView = remoteViewsMakeHeadsUpContentView;
        }
        if (style != null && (extras = NotificationCompat.getExtras(notificationBuildInternal)) != null) {
            style.addCompatExtras(extras);
        }
        return notificationBuildInternal;
    }

    public Notification buildInternal() {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 26) {
            return this.mBuilder.build();
        }
        if (i7 >= 24) {
            Notification notificationBuild = this.mBuilder.build();
            if (this.mGroupAlertBehavior != 0) {
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                    removeSoundAndVibration(notificationBuild);
                }
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                    removeSoundAndVibration(notificationBuild);
                }
            }
            return notificationBuild;
        }
        this.mBuilder.setExtras(this.mExtras);
        Notification notificationBuild2 = this.mBuilder.build();
        RemoteViews remoteViews = this.mContentView;
        if (remoteViews != null) {
            notificationBuild2.contentView = remoteViews;
        }
        RemoteViews remoteViews2 = this.mBigContentView;
        if (remoteViews2 != null) {
            notificationBuild2.bigContentView = remoteViews2;
        }
        RemoteViews remoteViews3 = this.mHeadsUpContentView;
        if (remoteViews3 != null) {
            notificationBuild2.headsUpContentView = remoteViews3;
        }
        if (this.mGroupAlertBehavior != 0) {
            if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
                removeSoundAndVibration(notificationBuild2);
            }
            if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
                removeSoundAndVibration(notificationBuild2);
            }
        }
        return notificationBuild2;
    }

    @Override // android.support.v4.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }
}
