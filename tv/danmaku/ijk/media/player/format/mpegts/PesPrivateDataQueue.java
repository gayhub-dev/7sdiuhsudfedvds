package tv.danmaku.ijk.media.player.format.mpegts;

import java.util.PriorityQueue;
import java.util.Queue;

/* loaded from: classes.dex */
public class PesPrivateDataQueue {
    private OnTimeStampListener onTimeStampListener;
    private final Queue<PesPrivateDataValue> values = new PriorityQueue();

    public interface OnTimeStampListener {
        void onCurrentTimeStamp(PesPrivateDataTimeStamp pesPrivateDataTimeStamp);
    }

    public static class PesPrivateDataValue implements Comparable<PesPrivateDataValue> {
        private PesPrivateDataTimeStamp pesPrivateDataTimeStamp;
        private long pts;

        private PesPrivateDataValue() {
        }

        @Override // java.lang.Comparable
        public int compareTo(PesPrivateDataValue pesPrivateDataValue) {
            return Long.compare(this.pts, pesPrivateDataValue.pts);
        }
    }

    public void onParsePesPrivateData(long j7, byte[] bArr) {
        PesPrivateDataTimeStamp pesPrivateDataTimeStamp = PesPrivateDataTimeStamp.parse(bArr);
        if (pesPrivateDataTimeStamp == null) {
            return;
        }
        PesPrivateDataValue pesPrivateDataValue = new PesPrivateDataValue();
        pesPrivateDataValue.pts = j7;
        pesPrivateDataValue.pesPrivateDataTimeStamp = pesPrivateDataTimeStamp;
        this.values.add(pesPrivateDataValue);
    }

    public void onVideoFrameRendered(long j7) {
        while (!this.values.isEmpty()) {
            PesPrivateDataValue pesPrivateDataValuePeek = this.values.peek();
            if (pesPrivateDataValuePeek.pts >= j7) {
                OnTimeStampListener onTimeStampListener = this.onTimeStampListener;
                if (onTimeStampListener != null) {
                    onTimeStampListener.onCurrentTimeStamp(pesPrivateDataValuePeek.pesPrivateDataTimeStamp);
                    return;
                }
                return;
            }
            this.values.poll();
        }
    }

    public PesPrivateDataQueue setOnTimeStampListener(OnTimeStampListener onTimeStampListener) {
        this.onTimeStampListener = onTimeStampListener;
        return this;
    }
}
