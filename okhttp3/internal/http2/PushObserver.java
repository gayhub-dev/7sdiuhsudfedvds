package okhttp3.internal.http2;

import java.util.List;
import okio.BufferedSource;

/* loaded from: classes.dex */
public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() { // from class: okhttp3.internal.http2.PushObserver.1
        @Override // okhttp3.internal.http2.PushObserver
        public boolean onData(int i7, BufferedSource bufferedSource, int i8, boolean z6) {
            bufferedSource.skip(i8);
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean onHeaders(int i7, List<Header> list, boolean z6) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean onRequest(int i7, List<Header> list) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public void onReset(int i7, ErrorCode errorCode) {
        }
    };

    boolean onData(int i7, BufferedSource bufferedSource, int i8, boolean z6);

    boolean onHeaders(int i7, List<Header> list, boolean z6);

    boolean onRequest(int i7, List<Header> list);

    void onReset(int i7, ErrorCode errorCode);
}
