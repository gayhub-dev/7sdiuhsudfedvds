package com.ctvit.network.body;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class UIProgressResponseCallBack implements ProgressResponseCallBack {
    private static final int RESPONSE_UPDATE = 2;
    private final Handler mHandler = new UIHandler(Looper.getMainLooper(), this);

    public class ProgressModel implements Serializable {
        private long contentLength;
        private long currentBytes;
        private boolean done;

        public ProgressModel(long j7, long j8, boolean z6) {
            this.currentBytes = j7;
            this.contentLength = j8;
            this.done = z6;
        }

        public long getContentLength() {
            return this.contentLength;
        }

        public long getCurrentBytes() {
            return this.currentBytes;
        }

        public boolean isDone() {
            return this.done;
        }

        public ProgressModel setContentLength(long j7) {
            this.contentLength = j7;
            return this;
        }

        public ProgressModel setCurrentBytes(long j7) {
            this.currentBytes = j7;
            return this;
        }

        public ProgressModel setDone(boolean z6) {
            this.done = z6;
            return this;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("ProgressModel{currentBytes=");
            sbM112a.append(this.currentBytes);
            sbM112a.append(", contentLength=");
            sbM112a.append(this.contentLength);
            sbM112a.append(", done=");
            sbM112a.append(this.done);
            sbM112a.append('}');
            return sbM112a.toString();
        }
    }

    public static class UIHandler extends Handler {
        private final WeakReference<UIProgressResponseCallBack> mUIProgressResponseListenerWeakReference;

        public UIHandler(Looper looper, UIProgressResponseCallBack uIProgressResponseCallBack) {
            super(looper);
            this.mUIProgressResponseListenerWeakReference = new WeakReference<>(uIProgressResponseCallBack);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 2) {
                super.handleMessage(message);
                return;
            }
            UIProgressResponseCallBack uIProgressResponseCallBack = this.mUIProgressResponseListenerWeakReference.get();
            if (uIProgressResponseCallBack != null) {
                ProgressModel progressModel = (ProgressModel) message.obj;
                uIProgressResponseCallBack.onUIResponseProgress(progressModel.getCurrentBytes(), progressModel.getContentLength(), progressModel.isDone());
            }
        }
    }

    @Override // com.ctvit.network.body.ProgressResponseCallBack
    public void onResponseProgress(long j7, long j8, boolean z6) {
        Message messageObtain = Message.obtain();
        messageObtain.obj = new ProgressModel(j7, j8, z6);
        messageObtain.what = 2;
        this.mHandler.sendMessage(messageObtain);
    }

    public abstract void onUIResponseProgress(long j7, long j8, boolean z6);
}
