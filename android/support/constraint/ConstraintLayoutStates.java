package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.fourthline.cling.model.ServiceReference;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class ConstraintLayoutStates {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";
    private final ConstraintLayout mConstraintLayout;
    public ConstraintSet mDefaultConstraintSet;
    public int mCurrentStateId = -1;
    public int mCurrentConstraintNumber = -1;
    private SparseArray<State> mStateList = new SparseArray<>();
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;

    public static class State {
        public int mConstraintID;
        public ConstraintSet mConstraintSet;
        public int mId;
        public ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
            this.mConstraintID = -1;
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
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
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
        public ConstraintSet mConstraintSet;
        public int mId;
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
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), C0071R.styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.Variant_constraints) {
                    this.mConstraintID = typedArrayObtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
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

    public ConstraintLayoutStates(Context context, ConstraintLayout constraintLayout, int i7) throws XmlPullParserException, Resources.NotFoundException, IOException, NumberFormatException {
        this.mConstraintLayout = constraintLayout;
        load(context, i7);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void load(Context context, int i7) throws XmlPullParserException, Resources.NotFoundException, IOException, NumberFormatException {
        XmlResourceParser xml = context.getResources().getXml(i7);
        State state = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    char c7 = 65535;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c7 = 4;
                                break;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                c7 = 2;
                                break;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c7 = 1;
                                break;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                c7 = 0;
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
                            state = new State(context, xml);
                            this.mStateList.put(state.mId, state);
                        } else if (c7 == 3) {
                            Variant variant = new Variant(context, xml);
                            if (state != null) {
                                state.add(variant);
                            }
                        } else if (c7 == 4) {
                            parseConstraintSet(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
    }

    private void parseConstraintSet(Context context, XmlPullParser xmlPullParser) throws NumberFormatException {
        ConstraintSet constraintSet = new ConstraintSet();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i7 = 0; i7 < attributeCount; i7++) {
            if ("id".equals(xmlPullParser.getAttributeName(i7))) {
                String attributeValue = xmlPullParser.getAttributeValue(i7);
                int identifier = attributeValue.contains(ServiceReference.DELIMITER) ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1 && attributeValue.length() > 1) {
                    identifier = Integer.parseInt(attributeValue.substring(1));
                }
                constraintSet.load(context, xmlPullParser);
                this.mConstraintSetMap.put(identifier, constraintSet);
                return;
            }
        }
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

    public void updateConstraints(int i7, float f7, float f8) {
        int iFindMatch;
        int i8 = this.mCurrentStateId;
        if (i8 != i7) {
            this.mCurrentStateId = i7;
            State state = this.mStateList.get(i7);
            int iFindMatch2 = state.findMatch(f7, f8);
            ConstraintSet constraintSet = iFindMatch2 == -1 ? state.mConstraintSet : state.mVariants.get(iFindMatch2).mConstraintSet;
            int i9 = iFindMatch2 == -1 ? state.mConstraintID : state.mVariants.get(iFindMatch2).mConstraintID;
            if (constraintSet == null) {
                return;
            }
            this.mCurrentConstraintNumber = iFindMatch2;
            ConstraintsChangedListener constraintsChangedListener = this.mConstraintsChangedListener;
            if (constraintsChangedListener != null) {
                constraintsChangedListener.preLayoutChange(i7, i9);
            }
            constraintSet.applyTo(this.mConstraintLayout);
            ConstraintsChangedListener constraintsChangedListener2 = this.mConstraintsChangedListener;
            if (constraintsChangedListener2 != null) {
                constraintsChangedListener2.postLayoutChange(i7, i9);
                return;
            }
            return;
        }
        State stateValueAt = i7 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i8);
        int i10 = this.mCurrentConstraintNumber;
        if ((i10 == -1 || !stateValueAt.mVariants.get(i10).match(f7, f8)) && this.mCurrentConstraintNumber != (iFindMatch = stateValueAt.findMatch(f7, f8))) {
            ConstraintSet constraintSet2 = iFindMatch == -1 ? this.mDefaultConstraintSet : stateValueAt.mVariants.get(iFindMatch).mConstraintSet;
            int i11 = iFindMatch == -1 ? stateValueAt.mConstraintID : stateValueAt.mVariants.get(iFindMatch).mConstraintID;
            if (constraintSet2 == null) {
                return;
            }
            this.mCurrentConstraintNumber = iFindMatch;
            ConstraintsChangedListener constraintsChangedListener3 = this.mConstraintsChangedListener;
            if (constraintsChangedListener3 != null) {
                constraintsChangedListener3.preLayoutChange(-1, i11);
            }
            constraintSet2.applyTo(this.mConstraintLayout);
            ConstraintsChangedListener constraintsChangedListener4 = this.mConstraintsChangedListener;
            if (constraintsChangedListener4 != null) {
                constraintsChangedListener4.postLayoutChange(-1, i11);
            }
        }
    }
}
