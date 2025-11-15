package android.support.constraint.motion;

import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class KeyCache {
    public HashMap<Object, HashMap<String, float[]>> map = new HashMap<>();

    public float getFloatValue(Object obj, String str, int i7) {
        if (!this.map.containsKey(obj)) {
            return Float.NaN;
        }
        HashMap<String, float[]> map = this.map.get(obj);
        if (!map.containsKey(str)) {
            return Float.NaN;
        }
        float[] fArr = map.get(str);
        if (fArr.length > i7) {
            return fArr[i7];
        }
        return Float.NaN;
    }

    public void setFloatValue(Object obj, String str, int i7, float f7) {
        if (!this.map.containsKey(obj)) {
            HashMap<String, float[]> map = new HashMap<>();
            float[] fArr = new float[i7 + 1];
            fArr[i7] = f7;
            map.put(str, fArr);
            this.map.put(obj, map);
            return;
        }
        HashMap<String, float[]> map2 = this.map.get(obj);
        if (!map2.containsKey(str)) {
            float[] fArr2 = new float[i7 + 1];
            fArr2[i7] = f7;
            map2.put(str, fArr2);
            this.map.put(obj, map2);
            return;
        }
        float[] fArrCopyOf = map2.get(str);
        if (fArrCopyOf.length <= i7) {
            fArrCopyOf = Arrays.copyOf(fArrCopyOf, i7 + 1);
        }
        fArrCopyOf[i7] = f7;
        map2.put(str, fArrCopyOf);
    }
}
