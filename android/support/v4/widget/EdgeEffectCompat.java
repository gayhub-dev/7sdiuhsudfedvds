package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.widget.EdgeEffect;

/* loaded from: classes.dex */
public final class EdgeEffectCompat {
    private EdgeEffect mEdgeEffect;

    @Deprecated
    public EdgeEffectCompat(Context context) {
        this.mEdgeEffect = new EdgeEffect(context);
    }

    @Deprecated
    public boolean draw(Canvas canvas) {
        return this.mEdgeEffect.draw(canvas);
    }

    @Deprecated
    public void finish() {
        this.mEdgeEffect.finish();
    }

    @Deprecated
    public boolean isFinished() {
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public boolean onAbsorb(int i7) {
        this.mEdgeEffect.onAbsorb(i7);
        return true;
    }

    @Deprecated
    public boolean onPull(float f7) {
        this.mEdgeEffect.onPull(f7);
        return true;
    }

    @Deprecated
    public boolean onRelease() {
        this.mEdgeEffect.onRelease();
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public void setSize(int i7, int i8) {
        this.mEdgeEffect.setSize(i7, i8);
    }

    @Deprecated
    public boolean onPull(float f7, float f8) {
        onPull(this.mEdgeEffect, f7, f8);
        return true;
    }

    public static void onPull(@NonNull EdgeEffect edgeEffect, float f7, float f8) {
        edgeEffect.onPull(f7, f8);
    }
}
