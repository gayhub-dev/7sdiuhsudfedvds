package android.support.v4.graphics;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class PathSegment {
    private final PointF mEnd;
    private final float mEndFraction;
    private final PointF mStart;
    private final float mStartFraction;

    public PathSegment(@NonNull PointF pointF, float f7, @NonNull PointF pointF2, float f8) {
        this.mStart = (PointF) Preconditions.checkNotNull(pointF, "start == null");
        this.mStartFraction = f7;
        this.mEnd = (PointF) Preconditions.checkNotNull(pointF2, "end == null");
        this.mEndFraction = f8;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) obj;
        return Float.compare(this.mStartFraction, pathSegment.mStartFraction) == 0 && Float.compare(this.mEndFraction, pathSegment.mEndFraction) == 0 && this.mStart.equals(pathSegment.mStart) && this.mEnd.equals(pathSegment.mEnd);
    }

    @NonNull
    public PointF getEnd() {
        return this.mEnd;
    }

    public float getEndFraction() {
        return this.mEndFraction;
    }

    @NonNull
    public PointF getStart() {
        return this.mStart;
    }

    public float getStartFraction() {
        return this.mStartFraction;
    }

    public int hashCode() {
        int iHashCode = this.mStart.hashCode() * 31;
        float f7 = this.mStartFraction;
        int iHashCode2 = (this.mEnd.hashCode() + ((iHashCode + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31)) * 31;
        float f8 = this.mEndFraction;
        return iHashCode2 + (f8 != 0.0f ? Float.floatToIntBits(f8) : 0);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("PathSegment{start=");
        sbM112a.append(this.mStart);
        sbM112a.append(", startFraction=");
        sbM112a.append(this.mStartFraction);
        sbM112a.append(", end=");
        sbM112a.append(this.mEnd);
        sbM112a.append(", endFraction=");
        sbM112a.append(this.mEndFraction);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
