package android.support.constraint.motion;

import android.content.Context;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintLayout;
import android.util.Xml;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class KeyFrames {
    private static final String TAG = "KeyFrames";
    public static final int UNSET = -1;
    public static HashMap<String, Constructor<? extends Key>> sKeyMakers;
    private HashMap<Integer, ArrayList<Key>> mFramesMap = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends Key>> map = new HashMap<>();
        sKeyMakers = map;
        try {
            map.put(KeyAttributes.NAME, KeyAttributes.class.getConstructor(new Class[0]));
            sKeyMakers.put(KeyPosition.NAME, KeyPosition.class.getConstructor(new Class[0]));
            sKeyMakers.put(KeyCycle.NAME, KeyCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put(KeyTimeCycle.NAME, KeyTimeCycle.class.getConstructor(new Class[0]));
            sKeyMakers.put(KeyTrigger.NAME, KeyTrigger.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException unused) {
        }
    }

    public KeyFrames(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IllegalAccessException, InstantiationException, IOException, IllegalArgumentException, InvocationTargetException {
        HashMap<String, ConstraintAttribute> map;
        Key key = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (sKeyMakers.containsKey(name)) {
                        try {
                            Key keyNewInstance = sKeyMakers.get(name).newInstance(new Object[0]);
                            try {
                                keyNewInstance.load(context, Xml.asAttributeSet(xmlPullParser));
                                addKey(keyNewInstance);
                            } catch (Exception unused) {
                            }
                            key = keyNewInstance;
                        } catch (Exception unused2) {
                        }
                    } else if (name.equalsIgnoreCase("CustomAttribute") && key != null && (map = key.mCustomConstraints) != null) {
                        ConstraintAttribute.parse(context, xmlPullParser, map);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
    }

    private void addKey(Key key) {
        if (!this.mFramesMap.containsKey(Integer.valueOf(key.mTargetId))) {
            this.mFramesMap.put(Integer.valueOf(key.mTargetId), new ArrayList<>());
        }
        this.mFramesMap.get(Integer.valueOf(key.mTargetId)).add(key);
    }

    public static String name(int i7, Context context) {
        return context.getResources().getResourceEntryName(i7);
    }

    public void addFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.mFramesMap.get(Integer.valueOf(motionController.mId));
        if (arrayList != null) {
            motionController.addKeys(arrayList);
        }
        ArrayList<Key> arrayList2 = this.mFramesMap.get(-1);
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            while (it.hasNext()) {
                Key next = it.next();
                if (next.matches(((ConstraintLayout.LayoutParams) motionController.mView.getLayoutParams()).constraintTag)) {
                    motionController.addKey(next);
                }
            }
        }
    }

    public ArrayList<Key> getKeyFramesForView(int i7) {
        return this.mFramesMap.get(Integer.valueOf(i7));
    }

    public Set<Integer> getKeys() {
        return this.mFramesMap.keySet();
    }
}
