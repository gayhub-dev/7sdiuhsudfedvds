package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackRecord;
import android.text.TextUtils;
import java.util.ArrayList;

/* compiled from: BackStackRecord.java */
/* loaded from: classes.dex */
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator<BackStackState>() { // from class: android.support.v4.app.BackStackState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState[] newArray(int i7) {
            return new BackStackState[i7];
        }
    };
    public final int mBreadCrumbShortTitleRes;
    public final CharSequence mBreadCrumbShortTitleText;
    public final int mBreadCrumbTitleRes;
    public final CharSequence mBreadCrumbTitleText;
    public final int mIndex;
    public final String mName;
    public final int[] mOps;
    public final boolean mReorderingAllowed;
    public final ArrayList<String> mSharedElementSourceNames;
    public final ArrayList<String> mSharedElementTargetNames;
    public final int mTransition;
    public final int mTransitionStyle;

    public BackStackState(BackStackRecord backStackRecord) {
        int size = backStackRecord.mOps.size();
        this.mOps = new int[size * 6];
        if (!backStackRecord.mAddToBackStack) {
            throw new IllegalStateException("Not on back stack");
        }
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            BackStackRecord.C0134Op c0134Op = backStackRecord.mOps.get(i8);
            int[] iArr = this.mOps;
            int i9 = i7 + 1;
            iArr[i7] = c0134Op.cmd;
            int i10 = i9 + 1;
            Fragment fragment = c0134Op.fragment;
            iArr[i9] = fragment != null ? fragment.mIndex : -1;
            int i11 = i10 + 1;
            iArr[i10] = c0134Op.enterAnim;
            int i12 = i11 + 1;
            iArr[i11] = c0134Op.exitAnim;
            int i13 = i12 + 1;
            iArr[i12] = c0134Op.popEnterAnim;
            i7 = i13 + 1;
            iArr[i13] = c0134Op.popExitAnim;
        }
        this.mTransition = backStackRecord.mTransition;
        this.mTransitionStyle = backStackRecord.mTransitionStyle;
        this.mName = backStackRecord.mName;
        this.mIndex = backStackRecord.mIndex;
        this.mBreadCrumbTitleRes = backStackRecord.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = backStackRecord.mBreadCrumbTitleText;
        this.mBreadCrumbShortTitleRes = backStackRecord.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = backStackRecord.mBreadCrumbShortTitleText;
        this.mSharedElementSourceNames = backStackRecord.mSharedElementSourceNames;
        this.mSharedElementTargetNames = backStackRecord.mSharedElementTargetNames;
        this.mReorderingAllowed = backStackRecord.mReorderingAllowed;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl fragmentManagerImpl) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManagerImpl);
        int i7 = 0;
        int i8 = 0;
        while (i7 < this.mOps.length) {
            BackStackRecord.C0134Op c0134Op = new BackStackRecord.C0134Op();
            int i9 = i7 + 1;
            c0134Op.cmd = this.mOps[i7];
            if (FragmentManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("Instantiate ");
                sb.append(backStackRecord);
                sb.append(" op #");
                sb.append(i8);
                sb.append(" base fragment #");
                sb.append(this.mOps[i9]);
            }
            int i10 = i9 + 1;
            int i11 = this.mOps[i9];
            if (i11 >= 0) {
                c0134Op.fragment = fragmentManagerImpl.mActive.get(i11);
            } else {
                c0134Op.fragment = null;
            }
            int[] iArr = this.mOps;
            int i12 = i10 + 1;
            int i13 = iArr[i10];
            c0134Op.enterAnim = i13;
            int i14 = i12 + 1;
            int i15 = iArr[i12];
            c0134Op.exitAnim = i15;
            int i16 = i14 + 1;
            int i17 = iArr[i14];
            c0134Op.popEnterAnim = i17;
            int i18 = iArr[i16];
            c0134Op.popExitAnim = i18;
            backStackRecord.mEnterAnim = i13;
            backStackRecord.mExitAnim = i15;
            backStackRecord.mPopEnterAnim = i17;
            backStackRecord.mPopExitAnim = i18;
            backStackRecord.addOp(c0134Op);
            i8++;
            i7 = i16 + 1;
        }
        backStackRecord.mTransition = this.mTransition;
        backStackRecord.mTransitionStyle = this.mTransitionStyle;
        backStackRecord.mName = this.mName;
        backStackRecord.mIndex = this.mIndex;
        backStackRecord.mAddToBackStack = true;
        backStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        backStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        backStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        backStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        backStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
        backStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
        backStackRecord.mReorderingAllowed = this.mReorderingAllowed;
        backStackRecord.bumpBackStackNesting(1);
        return backStackRecord;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeIntArray(this.mOps);
        parcel.writeInt(this.mTransition);
        parcel.writeInt(this.mTransitionStyle);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(this.mSharedElementSourceNames);
        parcel.writeStringList(this.mSharedElementTargetNames);
        parcel.writeInt(this.mReorderingAllowed ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mTransitionStyle = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        this.mBreadCrumbTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSharedElementSourceNames = parcel.createStringArrayList();
        this.mSharedElementTargetNames = parcel.createStringArrayList();
        this.mReorderingAllowed = parcel.readInt() != 0;
    }
}
