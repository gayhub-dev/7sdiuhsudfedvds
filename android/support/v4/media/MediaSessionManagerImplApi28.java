package android.support.v4.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import android.support.annotation.RequiresApi;
import android.support.v4.media.MediaSessionManager;
import android.support.v4.util.ObjectsCompat;

@RequiresApi(28)
/* loaded from: classes.dex */
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    public android.media.session.MediaSessionManager mObject;

    public MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.mObject = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
    }

    @Override // android.support.v4.media.MediaSessionManagerImplApi21, android.support.v4.media.MediaSessionManagerImplBase, android.support.v4.media.MediaSessionManager.MediaSessionManagerImpl
    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        if (remoteUserInfoImpl instanceof RemoteUserInfoImplApi28) {
            return this.mObject.isTrustedForMediaControl(((RemoteUserInfoImplApi28) remoteUserInfoImpl).mObject);
        }
        return false;
    }

    public static final class RemoteUserInfoImplApi28 implements MediaSessionManager.RemoteUserInfoImpl {
        public final MediaSessionManager.RemoteUserInfo mObject;

        public RemoteUserInfoImplApi28(String str, int i7, int i8) {
            this.mObject = new MediaSessionManager.RemoteUserInfo(str, i7, i8);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RemoteUserInfoImplApi28) {
                return this.mObject.equals(((RemoteUserInfoImplApi28) obj).mObject);
            }
            return false;
        }

        @Override // android.support.v4.media.MediaSessionManager.RemoteUserInfoImpl
        public String getPackageName() {
            return this.mObject.getPackageName();
        }

        @Override // android.support.v4.media.MediaSessionManager.RemoteUserInfoImpl
        public int getPid() {
            return this.mObject.getPid();
        }

        @Override // android.support.v4.media.MediaSessionManager.RemoteUserInfoImpl
        public int getUid() {
            return this.mObject.getUid();
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mObject);
        }

        public RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.mObject = remoteUserInfo;
        }
    }
}
