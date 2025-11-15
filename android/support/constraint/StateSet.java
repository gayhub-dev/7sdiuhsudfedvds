package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class StateSet {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";
    public ConstraintSet mDefaultConstraintSet;
    public int mDefaultState = -1;
    public int mCurrentStateId = -1;
    public int mCurrentConstraintNumber = -1;
    private SparseArray<State> mStateList = new SparseArray<>();
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;

    public static class State {
        public int mConstraintID;
        public int mId;
        public boolean mIsLayout;
        public ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
            this.mConstraintID = -1;
            this.mIsLayout = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0071R.styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.State_android_id) {
                    this.mId = typedArrayObtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == C0071R.styleable.State_constraints) {
                    this.mConstraintID = typedArrayObtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void add(Variant variant) {
            this.mVariants.add(variant);
        }

        public int findMatch(float f7, float f8) {
            for (int i7 = 0; i7 < this.mVariants.size(); i7++) {
                if (this.mVariants.get(i7).match(f7, f8)) {
                    return i7;
                }
            }
            return -1;
        }
    }

    public static class Variant {
        public int mConstraintID;
        public int mId;
        public boolean mIsLayout;
        public float mMaxHeight;
        public float mMaxWidth;
        public float mMinHeight;
        public float mMinWidth;

        public Variant(Context context, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
            this.mMinWidth = Float.NaN;
            this.mMinHeight = Float.NaN;
            this.mMaxWidth = Float.NaN;
            this.mMaxHeight = Float.NaN;
            this.mConstraintID = -1;
            this.mIsLayout = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0071R.styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.Variant_constraints) {
                    this.mConstraintID = typedArrayObtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                } else if (index == C0071R.styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = typedArrayObtainStyledAttributes.getDimension(index, this.mMaxHeight);
                } else if (index == C0071R.styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = typedArrayObtainStyledAttributes.getDimension(index, this.mMinHeight);
                } else if (index == C0071R.styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = typedArrayObtainStyledAttributes.getDimension(index, this.mMaxWidth);
                } else if (index == C0071R.styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = typedArrayObtainStyledAttributes.getDimension(index, this.mMinWidth);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean match(float f7, float f8) {
            if (!Float.isNaN(this.mMinWidth) && f7 < this.mMinWidth) {
                return false;
            }
            if (!Float.isNaN(this.mMinHeight) && f8 < this.mMinHeight) {
                return false;
            }
            if (Float.isNaN(this.mMaxWidth) || f7 <= this.mMaxWidth) {
                return Float.isNaN(this.mMaxHeight) || f8 <= this.mMaxHeight;
            }
            return false;
        }
    }

    public StateSet(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        load(context, xmlPullParser);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void load(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0071R.styleable.StateSet);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            if (index == C0071R.styleable.StateSet_defaultState) {
                this.mDefaultState = typedArrayObtainStyledAttributes.getResourceId(index, this.mDefaultState);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        State state = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 0) {
                    xmlPullParser.getName();
                } else if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    char c7 = 65535;
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                c7 = 2;
                                break;
                            }
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                c7 = 0;
                                break;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c7 = 1;
                                break;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                c7 = 3;
                                break;
                            }
                            break;
                    }
                    if (c7 != 0 && c7 != 1) {
                        if (c7 == 2) {
                            state = new State(context, xmlPullParser);
                            this.mStateList.put(state.mId, state);
                        } else if (c7 == 3) {
                            Variant variant = new Variant(context, xmlPullParser);
                            if (state != null) {
                                state.add(variant);
                            }
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("StateSet".equals(xmlPullParser.getName())) {
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

    public int convertToConstraintSet(int i7, int i8, float f7, float f8) {
        State state = this.mStateList.get(i8);
        if (state == null) {
            return i8;
        }
        if (f7 == -1.0f || f8 == -1.0f) {
            if (state.mConstraintID == i7) {
                return i7;
            }
            Iterator<Variant> it = state.mVariants.iterator();
            while (it.hasNext()) {
                if (i7 == it.next().mConstraintID) {
                    return i7;
                }
            }
            return state.mConstraintID;
        }
        Variant variant = null;
        Iterator<Variant> it2 = state.mVariants.iterator();
        while (it2.hasNext()) {
            Variant next = it2.next();
            if (next.match(f7, f8)) {
                if (i7 == next.mConstraintID) {
                    return i7;
                }
                variant = next;
            }
        }
        return variant != null ? variant.mConstraintID : state.mConstraintID;
    }

    public boolean needsToChange(int i7, float f7, float f8) {
        int i8 = this.mCurrentStateId;
        if (i8 != i7) {
            return true;
        }
        State stateValueAt = i7 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i8);
        int i9 = this.mCurrentConstraintNumber;
        return (i9 == -1 || !stateValueAt.mVariants.get(i9).match(f7, f8)) && this.mCurrentConstraintNumber != stateValueAt.findMatch(f7, f8);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    public int stateGetConstraintID(int i7, int i8, int i9) {
        return updateConstraints(-1, i7, i8, i9);
    }

    public int updateConstraints(int i7, int i8, float f7, float f8) {
        int iFindMatch;
        if (i7 == i8) {
            State stateValueAt = i8 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(this.mCurrentStateId);
            if (stateValueAt == null) {
                return -1;
            }
            return ((this.mCurrentConstraintNumber == -1 || !stateValueAt.mVariants.get(i7).match(f7, f8)) && i7 != (iFindMatch = stateValueAt.findMatch(f7, f8))) ? iFindMatch == -1 ? stateValueAt.mConstraintID : stateValueAt.mVariants.get(iFindMatch).mConstraintID : i7;
        }
        State state = this.mStateList.get(i8);
        if (state == null) {
            return -1;
        }
        int iFindMatch2 = state.findMatch(f7, f8);
        return iFindMatch2 == -1 ? state.mConstraintID : state.mVariants.get(iFindMatch2).mConstraintID;
    }
}
