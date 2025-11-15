package android.support.constraint.motion;

/* compiled from: DesignTool.java */
/* loaded from: classes.dex */
interface ProxyInterface {
    int designAccess(int i7, String str, Object obj, float[] fArr, int i8, float[] fArr2, int i9);

    float getKeyFramePosition(Object obj, int i7, float f7, float f8);

    Object getKeyframeAtLocation(Object obj, float f7, float f8);

    Boolean getPositionKeyframe(Object obj, Object obj2, float f7, float f8, String[] strArr, float[] fArr);

    long getTransitionTimeMs();

    void setAttributes(int i7, String str, Object obj, Object obj2);

    void setKeyFrame(Object obj, int i7, String str, Object obj2);

    boolean setKeyFramePosition(Object obj, int i7, int i8, float f7, float f8);

    void setToolPosition(float f7);
}
