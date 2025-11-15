package android.support.constraint;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ConstraintProperties {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int END = 7;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int WRAP_CONTENT = -2;
    public ConstraintLayout.LayoutParams mParams;
    public View mView;

    public ConstraintProperties(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ConstraintLayout.LayoutParams)) {
            throw new RuntimeException("Only children of ConstraintLayout.LayoutParams supported");
        }
        this.mParams = (ConstraintLayout.LayoutParams) layoutParams;
        this.mView = view;
    }

    private String sideToString(int i7) {
        switch (i7) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public ConstraintProperties addToHorizontalChain(int i7, int i8) {
        connect(1, i7, i7 == 0 ? 1 : 2, 0);
        connect(2, i8, i8 == 0 ? 2 : 1, 0);
        if (i7 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i7)).connect(2, this.mView.getId(), 1, 0);
        }
        if (i8 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i8)).connect(1, this.mView.getId(), 2, 0);
        }
        return this;
    }

    public ConstraintProperties addToHorizontalChainRTL(int i7, int i8) {
        connect(6, i7, i7 == 0 ? 6 : 7, 0);
        connect(7, i8, i8 == 0 ? 7 : 6, 0);
        if (i7 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i7)).connect(7, this.mView.getId(), 6, 0);
        }
        if (i8 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i8)).connect(6, this.mView.getId(), 7, 0);
        }
        return this;
    }

    public ConstraintProperties addToVerticalChain(int i7, int i8) {
        connect(3, i7, i7 == 0 ? 3 : 4, 0);
        connect(4, i8, i8 == 0 ? 4 : 3, 0);
        if (i7 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i7)).connect(4, this.mView.getId(), 3, 0);
        }
        if (i8 != 0) {
            new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i8)).connect(3, this.mView.getId(), 4, 0);
        }
        return this;
    }

    public ConstraintProperties alpha(float f7) {
        this.mView.setAlpha(f7);
        return this;
    }

    public void apply() {
    }

    public ConstraintProperties center(int i7, int i8, int i9, int i10, int i11, int i12, float f7) {
        if (i9 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (i12 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (f7 <= 0.0f || f7 > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if (i8 == 1 || i8 == 2) {
            connect(1, i7, i8, i9);
            connect(2, i10, i11, i12);
            this.mParams.horizontalBias = f7;
        } else if (i8 == 6 || i8 == 7) {
            connect(6, i7, i8, i9);
            connect(7, i10, i11, i12);
            this.mParams.horizontalBias = f7;
        } else {
            connect(3, i7, i8, i9);
            connect(4, i10, i11, i12);
            this.mParams.verticalBias = f7;
        }
        return this;
    }

    public ConstraintProperties centerHorizontally(int i7, int i8, int i9, int i10, int i11, int i12, float f7) {
        connect(1, i7, i8, i9);
        connect(2, i10, i11, i12);
        this.mParams.horizontalBias = f7;
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int i7, int i8, int i9, int i10, int i11, int i12, float f7) {
        connect(6, i7, i8, i9);
        connect(7, i10, i11, i12);
        this.mParams.horizontalBias = f7;
        return this;
    }

    public ConstraintProperties centerVertically(int i7, int i8, int i9, int i10, int i11, int i12, float f7) {
        connect(3, i7, i8, i9);
        connect(4, i10, i11, i12);
        this.mParams.verticalBias = f7;
        return this;
    }

    public ConstraintProperties connect(int i7, int i8, int i9, int i10) {
        switch (i7) {
            case 1:
                if (i9 == 1) {
                    ConstraintLayout.LayoutParams layoutParams = this.mParams;
                    layoutParams.leftToLeft = i8;
                    layoutParams.leftToRight = -1;
                } else {
                    if (i9 != 2) {
                        throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("Left to "), sideToString(i9), " undefined"));
                    }
                    ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
                    layoutParams2.leftToRight = i8;
                    layoutParams2.leftToLeft = -1;
                }
                ((ViewGroup.MarginLayoutParams) this.mParams).leftMargin = i10;
                return this;
            case 2:
                if (i9 == 1) {
                    ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                    layoutParams3.rightToLeft = i8;
                    layoutParams3.rightToRight = -1;
                } else {
                    if (i9 != 2) {
                        throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("right to "), sideToString(i9), " undefined"));
                    }
                    ConstraintLayout.LayoutParams layoutParams4 = this.mParams;
                    layoutParams4.rightToRight = i8;
                    layoutParams4.rightToLeft = -1;
                }
                ((ViewGroup.MarginLayoutParams) this.mParams).rightMargin = i10;
                return this;
            case 3:
                if (i9 == 3) {
                    ConstraintLayout.LayoutParams layoutParams5 = this.mParams;
                    layoutParams5.topToTop = i8;
                    layoutParams5.topToBottom = -1;
                    layoutParams5.baselineToBaseline = -1;
                } else {
                    if (i9 != 4) {
                        throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("right to "), sideToString(i9), " undefined"));
                    }
                    ConstraintLayout.LayoutParams layoutParams6 = this.mParams;
                    layoutParams6.topToBottom = i8;
                    layoutParams6.topToTop = -1;
                    layoutParams6.baselineToBaseline = -1;
                }
                ((ViewGroup.MarginLayoutParams) this.mParams).topMargin = i10;
                return this;
            case 4:
                if (i9 == 4) {
                    ConstraintLayout.LayoutParams layoutParams7 = this.mParams;
                    layoutParams7.bottomToBottom = i8;
                    layoutParams7.bottomToTop = -1;
                    layoutParams7.baselineToBaseline = -1;
                } else {
                    if (i9 != 3) {
                        throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("right to "), sideToString(i9), " undefined"));
                    }
                    ConstraintLayout.LayoutParams layoutParams8 = this.mParams;
                    layoutParams8.bottomToTop = i8;
                    layoutParams8.bottomToBottom = -1;
                    layoutParams8.baselineToBaseline = -1;
                }
                ((ViewGroup.MarginLayoutParams) this.mParams).bottomMargin = i10;
                return this;
            case 5:
                if (i9 != 5) {
                    throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("right to "), sideToString(i9), " undefined"));
                }
                ConstraintLayout.LayoutParams layoutParams9 = this.mParams;
                layoutParams9.baselineToBaseline = i8;
                layoutParams9.bottomToBottom = -1;
                layoutParams9.bottomToTop = -1;
                layoutParams9.topToTop = -1;
                layoutParams9.topToBottom = -1;
                return this;
            case 6:
                if (i9 == 6) {
                    ConstraintLayout.LayoutParams layoutParams10 = this.mParams;
                    layoutParams10.startToStart = i8;
                    layoutParams10.startToEnd = -1;
                } else {
                    if (i9 != 7) {
                        throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("right to "), sideToString(i9), " undefined"));
                    }
                    ConstraintLayout.LayoutParams layoutParams11 = this.mParams;
                    layoutParams11.startToEnd = i8;
                    layoutParams11.startToStart = -1;
                }
                this.mParams.setMarginStart(i10);
                return this;
            case 7:
                if (i9 == 7) {
                    ConstraintLayout.LayoutParams layoutParams12 = this.mParams;
                    layoutParams12.endToEnd = i8;
                    layoutParams12.endToStart = -1;
                } else {
                    if (i9 != 6) {
                        throw new IllegalArgumentException(C0072a.m92a(C0413b.m112a("right to "), sideToString(i9), " undefined"));
                    }
                    ConstraintLayout.LayoutParams layoutParams13 = this.mParams;
                    layoutParams13.endToStart = i8;
                    layoutParams13.endToEnd = -1;
                }
                this.mParams.setMarginEnd(i10);
                return this;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(sideToString(i7));
                sb.append(" to ");
                throw new IllegalArgumentException(C0072a.m92a(sb, sideToString(i9), " unknown"));
        }
    }

    public ConstraintProperties constrainDefaultHeight(int i7) {
        this.mParams.matchConstraintDefaultHeight = i7;
        return this;
    }

    public ConstraintProperties constrainDefaultWidth(int i7) {
        this.mParams.matchConstraintDefaultWidth = i7;
        return this;
    }

    public ConstraintProperties constrainHeight(int i7) {
        ((ViewGroup.MarginLayoutParams) this.mParams).height = i7;
        return this;
    }

    public ConstraintProperties constrainMaxHeight(int i7) {
        this.mParams.matchConstraintMaxHeight = i7;
        return this;
    }

    public ConstraintProperties constrainMaxWidth(int i7) {
        this.mParams.matchConstraintMaxWidth = i7;
        return this;
    }

    public ConstraintProperties constrainMinHeight(int i7) {
        this.mParams.matchConstraintMinHeight = i7;
        return this;
    }

    public ConstraintProperties constrainMinWidth(int i7) {
        this.mParams.matchConstraintMinWidth = i7;
        return this;
    }

    public ConstraintProperties constrainWidth(int i7) {
        ((ViewGroup.MarginLayoutParams) this.mParams).width = i7;
        return this;
    }

    public ConstraintProperties dimensionRatio(String str) {
        this.mParams.dimensionRatio = str;
        return this;
    }

    public ConstraintProperties elevation(float f7) {
        this.mView.setElevation(f7);
        return this;
    }

    public ConstraintProperties goneMargin(int i7, int i8) {
        switch (i7) {
            case 1:
                this.mParams.goneLeftMargin = i8;
                return this;
            case 2:
                this.mParams.goneRightMargin = i8;
                return this;
            case 3:
                this.mParams.goneTopMargin = i8;
                return this;
            case 4:
                this.mParams.goneBottomMargin = i8;
                return this;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.mParams.goneStartMargin = i8;
                return this;
            case 7:
                this.mParams.goneEndMargin = i8;
                return this;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public ConstraintProperties horizontalBias(float f7) {
        this.mParams.horizontalBias = f7;
        return this;
    }

    public ConstraintProperties horizontalChainStyle(int i7) {
        this.mParams.horizontalChainStyle = i7;
        return this;
    }

    public ConstraintProperties horizontalWeight(float f7) {
        this.mParams.horizontalWeight = f7;
        return this;
    }

    public ConstraintProperties margin(int i7, int i8) {
        switch (i7) {
            case 1:
                ((ViewGroup.MarginLayoutParams) this.mParams).leftMargin = i8;
                return this;
            case 2:
                ((ViewGroup.MarginLayoutParams) this.mParams).rightMargin = i8;
                return this;
            case 3:
                ((ViewGroup.MarginLayoutParams) this.mParams).topMargin = i8;
                return this;
            case 4:
                ((ViewGroup.MarginLayoutParams) this.mParams).bottomMargin = i8;
                return this;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.mParams.setMarginStart(i8);
                return this;
            case 7:
                this.mParams.setMarginEnd(i8);
                return this;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public ConstraintProperties removeConstraints(int i7) {
        switch (i7) {
            case 1:
                ConstraintLayout.LayoutParams layoutParams = this.mParams;
                layoutParams.leftToRight = -1;
                layoutParams.leftToLeft = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = -1;
                layoutParams.goneLeftMargin = -1;
                return this;
            case 2:
                ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
                layoutParams2.rightToRight = -1;
                layoutParams2.rightToLeft = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin = -1;
                layoutParams2.goneRightMargin = -1;
                return this;
            case 3:
                ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                layoutParams3.topToBottom = -1;
                layoutParams3.topToTop = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = -1;
                layoutParams3.goneTopMargin = -1;
                return this;
            case 4:
                ConstraintLayout.LayoutParams layoutParams4 = this.mParams;
                layoutParams4.bottomToTop = -1;
                layoutParams4.bottomToBottom = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin = -1;
                layoutParams4.goneBottomMargin = -1;
                return this;
            case 5:
                this.mParams.baselineToBaseline = -1;
                return this;
            case 6:
                ConstraintLayout.LayoutParams layoutParams5 = this.mParams;
                layoutParams5.startToEnd = -1;
                layoutParams5.startToStart = -1;
                layoutParams5.setMarginStart(-1);
                this.mParams.goneStartMargin = -1;
                return this;
            case 7:
                ConstraintLayout.LayoutParams layoutParams6 = this.mParams;
                layoutParams6.endToStart = -1;
                layoutParams6.endToEnd = -1;
                layoutParams6.setMarginEnd(-1);
                this.mParams.goneEndMargin = -1;
                return this;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public ConstraintProperties removeFromHorizontalChain() {
        ConstraintLayout.LayoutParams layoutParams = this.mParams;
        int i7 = layoutParams.leftToRight;
        int i8 = layoutParams.rightToLeft;
        if (i7 == -1 && i8 == -1) {
            int i9 = layoutParams.startToEnd;
            int i10 = layoutParams.endToStart;
            if (i9 != -1 || i10 != -1) {
                ConstraintProperties constraintProperties = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i9));
                ConstraintProperties constraintProperties2 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i10));
                ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
                if (i9 != -1 && i10 != -1) {
                    constraintProperties.connect(7, i10, 6, 0);
                    constraintProperties2.connect(6, i7, 7, 0);
                } else if (i7 != -1 || i10 != -1) {
                    int i11 = layoutParams2.rightToRight;
                    if (i11 != -1) {
                        constraintProperties.connect(7, i11, 7, 0);
                    } else {
                        int i12 = layoutParams2.leftToLeft;
                        if (i12 != -1) {
                            constraintProperties2.connect(6, i12, 6, 0);
                        }
                    }
                }
            }
            removeConstraints(6);
            removeConstraints(7);
        } else {
            ConstraintProperties constraintProperties3 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i7));
            ConstraintProperties constraintProperties4 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i8));
            ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
            if (i7 != -1 && i8 != -1) {
                constraintProperties3.connect(2, i8, 1, 0);
                constraintProperties4.connect(1, i7, 2, 0);
            } else if (i7 != -1 || i8 != -1) {
                int i13 = layoutParams3.rightToRight;
                if (i13 != -1) {
                    constraintProperties3.connect(2, i13, 2, 0);
                } else {
                    int i14 = layoutParams3.leftToLeft;
                    if (i14 != -1) {
                        constraintProperties4.connect(1, i14, 1, 0);
                    }
                }
            }
            removeConstraints(1);
            removeConstraints(2);
        }
        return this;
    }

    public ConstraintProperties removeFromVerticalChain() {
        ConstraintLayout.LayoutParams layoutParams = this.mParams;
        int i7 = layoutParams.topToBottom;
        int i8 = layoutParams.bottomToTop;
        if (i7 != -1 || i8 != -1) {
            ConstraintProperties constraintProperties = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i7));
            ConstraintProperties constraintProperties2 = new ConstraintProperties(((ViewGroup) this.mView.getParent()).findViewById(i8));
            ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
            if (i7 != -1 && i8 != -1) {
                constraintProperties.connect(4, i8, 3, 0);
                constraintProperties2.connect(3, i7, 4, 0);
            } else if (i7 != -1 || i8 != -1) {
                int i9 = layoutParams2.bottomToBottom;
                if (i9 != -1) {
                    constraintProperties.connect(4, i9, 4, 0);
                } else {
                    int i10 = layoutParams2.topToTop;
                    if (i10 != -1) {
                        constraintProperties2.connect(3, i10, 3, 0);
                    }
                }
            }
        }
        removeConstraints(3);
        removeConstraints(4);
        return this;
    }

    public ConstraintProperties rotation(float f7) {
        this.mView.setRotation(f7);
        return this;
    }

    public ConstraintProperties rotationX(float f7) {
        this.mView.setRotationX(f7);
        return this;
    }

    public ConstraintProperties rotationY(float f7) {
        this.mView.setRotationY(f7);
        return this;
    }

    public ConstraintProperties scaleX(float f7) {
        this.mView.setScaleY(f7);
        return this;
    }

    public ConstraintProperties scaleY(float f7) {
        return this;
    }

    public ConstraintProperties transformPivot(float f7, float f8) {
        this.mView.setPivotX(f7);
        this.mView.setPivotY(f8);
        return this;
    }

    public ConstraintProperties transformPivotX(float f7) {
        this.mView.setPivotX(f7);
        return this;
    }

    public ConstraintProperties transformPivotY(float f7) {
        this.mView.setPivotY(f7);
        return this;
    }

    public ConstraintProperties translation(float f7, float f8) {
        this.mView.setTranslationX(f7);
        this.mView.setTranslationY(f8);
        return this;
    }

    public ConstraintProperties translationX(float f7) {
        this.mView.setTranslationX(f7);
        return this;
    }

    public ConstraintProperties translationY(float f7) {
        this.mView.setTranslationY(f7);
        return this;
    }

    public ConstraintProperties translationZ(float f7) {
        this.mView.setTranslationZ(f7);
        return this;
    }

    public ConstraintProperties verticalBias(float f7) {
        this.mParams.verticalBias = f7;
        return this;
    }

    public ConstraintProperties verticalChainStyle(int i7) {
        this.mParams.verticalChainStyle = i7;
        return this;
    }

    public ConstraintProperties verticalWeight(float f7) {
        this.mParams.verticalWeight = f7;
        return this;
    }

    public ConstraintProperties visibility(int i7) {
        this.mView.setVisibility(i7);
        return this;
    }

    public ConstraintProperties centerHorizontally(int i7) {
        if (i7 == 0) {
            center(0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(i7, 2, 0, i7, 1, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int i7) {
        if (i7 == 0) {
            center(0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(i7, 7, 0, i7, 6, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties centerVertically(int i7) {
        if (i7 == 0) {
            center(0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(i7, 4, 0, i7, 3, 0, 0.5f);
        }
        return this;
    }
}
