package android.support.v4.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.res.Resources;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.mediacompat.C0120R;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;

/* loaded from: classes.dex */
public class NotificationCompat {

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        private void setBackgroundColor(RemoteViews remoteViews) {
            remoteViews.setInt(C0120R.id.status_bar_latest_event_content, "setBackgroundColor", this.mBuilder.getColor() != 0 ? this.mBuilder.getColor() : this.mBuilder.mContext.getResources().getColor(C0120R.color.notification_material_background_media_default_color));
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle, android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.DecoratedMediaCustomViewStyle()));
            } else {
                super.apply(notificationBuilderWithBuilderAccessor);
            }
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle
        public int getBigContentViewLayoutResource(int i7) {
            return i7 <= 3 ? C0120R.layout.notification_template_big_media_narrow_custom : C0120R.layout.notification_template_big_media_custom;
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle
        public int getContentViewLayoutResource() {
            return this.mBuilder.getContentView() != null ? C0120R.layout.notification_template_media_custom : super.getContentViewLayoutResource();
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle, android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) throws Resources.NotFoundException {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.mBuilder.getBigContentView() != null ? this.mBuilder.getBigContentView() : this.mBuilder.getContentView();
            if (bigContentView == null) {
                return null;
            }
            RemoteViews remoteViewsGenerateBigContentView = generateBigContentView();
            buildIntoRemoteViews(remoteViewsGenerateBigContentView, bigContentView);
            setBackgroundColor(remoteViewsGenerateBigContentView);
            return remoteViewsGenerateBigContentView;
        }

        @Override // android.support.v4.media.app.NotificationCompat.MediaStyle, android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) throws Resources.NotFoundException {
            RemoteViews remoteViewsGenerateContentView = null;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean z6 = true;
            boolean z7 = this.mBuilder.getContentView() != null;
            if (!z7 && this.mBuilder.getBigContentView() == null) {
                z6 = false;
            }
            if (z6) {
                remoteViewsGenerateContentView = generateContentView();
                if (z7) {
                    buildIntoRemoteViews(remoteViewsGenerateContentView, this.mBuilder.getContentView());
                }
                setBackgroundColor(remoteViewsGenerateContentView);
            }
            return remoteViewsGenerateContentView;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) throws Resources.NotFoundException {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUpContentView = this.mBuilder.getHeadsUpContentView() != null ? this.mBuilder.getHeadsUpContentView() : this.mBuilder.getContentView();
            if (headsUpContentView == null) {
                return null;
            }
            RemoteViews remoteViewsGenerateBigContentView = generateBigContentView();
            buildIntoRemoteViews(remoteViewsGenerateBigContentView, headsUpContentView);
            setBackgroundColor(remoteViewsGenerateBigContentView);
            return remoteViewsGenerateBigContentView;
        }
    }

    private NotificationCompat() {
    }

    public static class MediaStyle extends NotificationCompat.Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        public int[] mActionsToShowInCompact = null;
        public PendingIntent mCancelButtonIntent;
        public boolean mShowCancelButton;
        public MediaSessionCompat.Token mToken;

        public MediaStyle() {
        }

        private RemoteViews generateMediaActionButton(NotificationCompat.Action action) {
            boolean z6 = action.getActionIntent() == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), C0120R.layout.notification_media_action);
            int i7 = C0120R.id.action0;
            remoteViews.setImageViewResource(i7, action.getIcon());
            if (!z6) {
                remoteViews.setOnClickPendingIntent(i7, action.getActionIntent());
            }
            remoteViews.setContentDescription(i7, action.getTitle());
            return remoteViews;
        }

        public static MediaSessionCompat.Token getMediaSession(Notification notification) {
            Parcelable parcelable;
            Bundle extras = android.support.v4.app.NotificationCompat.getExtras(notification);
            if (extras == null || (parcelable = extras.getParcelable(android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION)) == null) {
                return null;
            }
            return MediaSessionCompat.Token.fromToken(parcelable);
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            notificationBuilderWithBuilderAccessor.getBuilder().setStyle(fillInMediaStyle(new Notification.MediaStyle()));
        }

        @RequiresApi(21)
        public Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle mediaStyle) {
            int[] iArr = this.mActionsToShowInCompact;
            if (iArr != null) {
                mediaStyle.setShowActionsInCompactView(iArr);
            }
            MediaSessionCompat.Token token = this.mToken;
            if (token != null) {
                mediaStyle.setMediaSession((MediaSession.Token) token.getToken());
            }
            return mediaStyle;
        }

        public RemoteViews generateBigContentView() throws Resources.NotFoundException {
            int iMin = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(false, getBigContentViewLayoutResource(iMin), false);
            remoteViewsApplyStandardTemplate.removeAllViews(C0120R.id.media_actions);
            if (iMin > 0) {
                for (int i7 = 0; i7 < iMin; i7++) {
                    remoteViewsApplyStandardTemplate.addView(C0120R.id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(i7)));
                }
            }
            if (this.mShowCancelButton) {
                int i8 = C0120R.id.cancel_action;
                remoteViewsApplyStandardTemplate.setViewVisibility(i8, 0);
                remoteViewsApplyStandardTemplate.setInt(i8, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0120R.integer.cancel_button_image_alpha));
                remoteViewsApplyStandardTemplate.setOnClickPendingIntent(i8, this.mCancelButtonIntent);
            } else {
                remoteViewsApplyStandardTemplate.setViewVisibility(C0120R.id.cancel_action, 8);
            }
            return remoteViewsApplyStandardTemplate;
        }

        public RemoteViews generateContentView() throws Resources.NotFoundException {
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int size = this.mBuilder.mActions.size();
            int[] iArr = this.mActionsToShowInCompact;
            int iMin = iArr == null ? 0 : Math.min(iArr.length, 3);
            remoteViewsApplyStandardTemplate.removeAllViews(C0120R.id.media_actions);
            if (iMin > 0) {
                for (int i7 = 0; i7 < iMin; i7++) {
                    if (i7 >= size) {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(i7), Integer.valueOf(size - 1)));
                    }
                    remoteViewsApplyStandardTemplate.addView(C0120R.id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(this.mActionsToShowInCompact[i7])));
                }
            }
            if (this.mShowCancelButton) {
                remoteViewsApplyStandardTemplate.setViewVisibility(C0120R.id.end_padder, 8);
                int i8 = C0120R.id.cancel_action;
                remoteViewsApplyStandardTemplate.setViewVisibility(i8, 0);
                remoteViewsApplyStandardTemplate.setOnClickPendingIntent(i8, this.mCancelButtonIntent);
                remoteViewsApplyStandardTemplate.setInt(i8, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0120R.integer.cancel_button_image_alpha));
            } else {
                remoteViewsApplyStandardTemplate.setViewVisibility(C0120R.id.end_padder, 0);
                remoteViewsApplyStandardTemplate.setViewVisibility(C0120R.id.cancel_action, 8);
            }
            return remoteViewsApplyStandardTemplate;
        }

        public int getBigContentViewLayoutResource(int i7) {
            return i7 <= 3 ? C0120R.layout.notification_template_big_media_narrow : C0120R.layout.notification_template_big_media;
        }

        public int getContentViewLayoutResource() {
            return C0120R.layout.notification_template_media;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // android.support.v4.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.mActionsToShowInCompact = iArr;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean z6) {
            return this;
        }

        public MediaStyle(NotificationCompat.Builder builder) {
            setBuilder(builder);
        }
    }
}
