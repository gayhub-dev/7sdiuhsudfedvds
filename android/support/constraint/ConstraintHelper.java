package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.constraint.C0071R;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Helper;
import android.support.constraint.solver.widgets.HelperWidget;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    public int mCount;
    public Helper mHelperWidget;
    public int[] mIds;
    private HashMap<Integer, String> mMap;
    public String mReferenceIds;
    public String mReferenceTags;
    public boolean mUseViewMeasure;
    private View[] mViews;
    public Context myContext;

    public ConstraintHelper(Context context) {
        super(context);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(null);
    }

    private void addID(String str) throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        if (str == null || str.length() == 0 || this.myContext == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iFindId = findId(strTrim);
        if (iFindId != 0) {
            this.mMap.put(Integer.valueOf(iFindId), strTrim);
            addRscID(iFindId);
        }
    }

    private void addRscID(int i7) {
        if (i7 == getId()) {
            return;
        }
        int i8 = this.mCount + 1;
        int[] iArr = this.mIds;
        if (i8 > iArr.length) {
            this.mIds = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.mIds;
        int i9 = this.mCount;
        iArr2[i9] = i7;
        this.mCount = i9 + 1;
    }

    private void addTag(String str) {
        if (str == null || str.length() == 0 || this.myContext == null) {
            return;
        }
        String strTrim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof ConstraintLayout.LayoutParams) && strTrim.equals(((ConstraintLayout.LayoutParams) layoutParams).constraintTag) && childAt.getId() != -1) {
                addRscID(childAt.getId());
            }
        }
    }

    private int[] convertReferenceString(View view, String str) throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        String[] strArrSplit = str.split(",");
        view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i7 = 0;
        for (String str2 : strArrSplit) {
            int iFindId = findId(str2.trim());
            if (iFindId != 0) {
                iArr[i7] = iFindId;
                i7++;
            }
        }
        return i7 != strArrSplit.length ? Arrays.copyOf(iArr, i7) : iArr;
    }

    private int findId(String str) throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int iFindId = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object designInformation = constraintLayout.getDesignInformation(0, str);
            if (designInformation instanceof Integer) {
                iFindId = ((Integer) designInformation).intValue();
            }
        }
        if (iFindId == 0 && constraintLayout != null) {
            iFindId = findId(constraintLayout, str);
        }
        if (iFindId == 0) {
            try {
                iFindId = C0071R.id.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iFindId == 0 ? this.myContext.getResources().getIdentifier(str, "id", this.myContext.getPackageName()) : iFindId;
    }

    public void addView(View view) {
        if (view == this || view.getId() == -1 || view.getParent() == null) {
            return;
        }
        this.mReferenceIds = null;
        addRscID(view.getId());
        requestLayout();
    }

    public void applyLayoutFeatures(ConstraintLayout constraintLayout) {
        int visibility = getVisibility();
        float elevation = getElevation();
        for (int i7 = 0; i7 < this.mCount; i7++) {
            View viewById = constraintLayout.getViewById(this.mIds[i7]);
            if (viewById != null) {
                viewById.setVisibility(visibility);
                if (elevation > 0.0f) {
                    viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public View[] getViews(ConstraintLayout constraintLayout) {
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != this.mCount) {
            this.mViews = new View[this.mCount];
        }
        for (int i7 = 0; i7 < this.mCount; i7++) {
            this.mViews[i7] = constraintLayout.getViewById(this.mIds[i7]);
        }
        return this.mViews;
    }

    public void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0071R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.mReferenceIds = string;
                    setIds(string);
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.mReferenceTags = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        ConstraintSet.Layout layout = constraint.layout;
        int[] iArr = layout.mReferenceIds;
        if (iArr != null) {
            setReferencedIds(iArr);
        } else {
            String str = layout.mReferenceIdString;
            if (str != null && str.length() > 0) {
                ConstraintSet.Layout layout2 = constraint.layout;
                layout2.mReferenceIds = convertReferenceString(this, layout2.mReferenceIdString);
            }
        }
        helperWidget.removeAllIds();
        if (constraint.layout.mReferenceIds == null) {
            return;
        }
        int i7 = 0;
        while (true) {
            int[] iArr2 = constraint.layout.mReferenceIds;
            if (i7 >= iArr2.length) {
                return;
            }
            ConstraintWidget constraintWidget = sparseArray.get(iArr2[i7]);
            if (constraintWidget != null) {
                helperWidget.add(constraintWidget);
            }
            i7++;
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        super.onAttachedToWindow();
        String str = this.mReferenceIds;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.mReferenceTags;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        if (this.mUseViewMeasure) {
            super.onMeasure(i7, i8);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void removeView(View view) {
        int i7;
        int id = view.getId();
        if (id == -1) {
            return;
        }
        this.mReferenceIds = null;
        int i8 = 0;
        while (true) {
            if (i8 >= this.mCount) {
                break;
            }
            if (this.mIds[i8] == id) {
                while (true) {
                    i7 = this.mCount;
                    if (i8 >= i7 - 1) {
                        break;
                    }
                    int[] iArr = this.mIds;
                    int i9 = i8 + 1;
                    iArr[i8] = iArr[i9];
                    i8 = i9;
                }
                this.mIds[i7 - 1] = 0;
                this.mCount = i7 - 1;
            } else {
                i8++;
            }
        }
        requestLayout();
    }

    public void resolveRtl(ConstraintWidget constraintWidget, boolean z6) {
    }

    public void setIds(String str) throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        this.mReferenceIds = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.mCount = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i7);
            if (iIndexOf == -1) {
                addID(str.substring(i7));
                return;
            } else {
                addID(str.substring(i7, iIndexOf));
                i7 = iIndexOf + 1;
            }
        }
    }

    public void setReferenceTags(String str) {
        this.mReferenceTags = str;
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.mCount = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i7);
            if (iIndexOf == -1) {
                addTag(str.substring(i7));
                return;
            } else {
                addTag(str.substring(i7, iIndexOf));
                i7 = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.mReferenceIds = null;
        this.mCount = 0;
        for (int i7 : iArr) {
            addRscID(i7);
        }
    }

    @Override // android.view.View
    public void setTag(int i7, Object obj) {
        super.setTag(i7, obj);
        if (obj == null && this.mReferenceIds == null) {
            addRscID(i7);
        }
    }

    public void updatePostConstraints(ConstraintLayout constraintLayout) {
    }

    public void updatePostLayout(ConstraintLayout constraintLayout) {
    }

    public void updatePostMeasure(ConstraintLayout constraintLayout) {
    }

    public void updatePreDraw(ConstraintLayout constraintLayout) {
    }

    public void updatePreLayout(ConstraintLayout constraintLayout) throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        String str;
        int iFindId;
        if (isInEditMode()) {
            setIds(this.mReferenceIds);
        }
        Helper helper = this.mHelperWidget;
        if (helper == null) {
            return;
        }
        helper.removeAllIds();
        for (int i7 = 0; i7 < this.mCount; i7++) {
            int i8 = this.mIds[i7];
            View viewById = constraintLayout.getViewById(i8);
            if (viewById == null && (iFindId = findId(constraintLayout, (str = this.mMap.get(Integer.valueOf(i8))))) != 0) {
                this.mIds[i7] = iFindId;
                this.mMap.put(Integer.valueOf(iFindId), str);
                viewById = constraintLayout.getViewById(iFindId);
            }
            if (viewById != null) {
                this.mHelperWidget.add(constraintLayout.getViewWidget(viewById));
            }
        }
        this.mHelperWidget.updateConstraints(constraintLayout.mLayoutWidget);
    }

    public void validateParams() {
        if (this.mHelperWidget == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).widget = (ConstraintWidget) this.mHelperWidget;
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(attributeSet);
    }

    public void applyLayoutFeatures() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ConstraintLayout)) {
            return;
        }
        applyLayoutFeatures((ConstraintLayout) parent);
    }

    private int findId(ConstraintLayout constraintLayout, String str) throws Resources.NotFoundException {
        Resources resources;
        if (str == null || constraintLayout == null || (resources = this.myContext.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            if (childAt.getId() != -1) {
                String resourceEntryName = null;
                try {
                    resourceEntryName = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                }
                if (str.equals(resourceEntryName)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.mViews = null;
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(attributeSet);
    }

    public void updatePreLayout(ConstraintWidgetContainer constraintWidgetContainer, Helper helper, SparseArray<ConstraintWidget> sparseArray) {
        helper.removeAllIds();
        for (int i7 = 0; i7 < this.mCount; i7++) {
            helper.add(sparseArray.get(this.mIds[i7]));
        }
    }
}
