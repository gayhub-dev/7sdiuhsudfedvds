package android.support.v4.app;

import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerImpl;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import p009b.C0413b;

/* loaded from: classes.dex */
final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator {
    public static final int OP_ADD = 1;
    public static final int OP_ATTACH = 7;
    public static final int OP_DETACH = 6;
    public static final int OP_HIDE = 4;
    public static final int OP_NULL = 0;
    public static final int OP_REMOVE = 3;
    public static final int OP_REPLACE = 2;
    public static final int OP_SET_PRIMARY_NAV = 8;
    public static final int OP_SHOW = 5;
    public static final int OP_UNSET_PRIMARY_NAV = 9;
    public static final String TAG = "FragmentManager";
    public boolean mAddToBackStack;
    public int mBreadCrumbShortTitleRes;
    public CharSequence mBreadCrumbShortTitleText;
    public int mBreadCrumbTitleRes;
    public CharSequence mBreadCrumbTitleText;
    public ArrayList<Runnable> mCommitRunnables;
    public boolean mCommitted;
    public int mEnterAnim;
    public int mExitAnim;
    public final FragmentManagerImpl mManager;

    @Nullable
    public String mName;
    public int mPopEnterAnim;
    public int mPopExitAnim;
    public ArrayList<String> mSharedElementSourceNames;
    public ArrayList<String> mSharedElementTargetNames;
    public int mTransition;
    public int mTransitionStyle;
    public ArrayList<C0134Op> mOps = new ArrayList<>();
    public boolean mAllowAddToBackStack = true;
    public int mIndex = -1;
    public boolean mReorderingAllowed = false;

    /* renamed from: android.support.v4.app.BackStackRecord$Op */
    public static final class C0134Op {
        public int cmd;
        public int enterAnim;
        public int exitAnim;
        public Fragment fragment;
        public int popEnterAnim;
        public int popExitAnim;

        public C0134Op() {
        }

        public C0134Op(int i7, Fragment fragment) {
            this.cmd = i7;
            this.fragment = fragment;
        }
    }

    public BackStackRecord(FragmentManagerImpl fragmentManagerImpl) {
        this.mManager = fragmentManagerImpl;
    }

    private void doAddOp(int i7, Fragment fragment, @Nullable String str, int i8) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            StringBuilder sbM112a = C0413b.m112a("Fragment ");
            sbM112a.append(cls.getCanonicalName());
            sbM112a.append(" must be a public static class to be  properly recreated from");
            sbM112a.append(" instance state.");
            throw new IllegalStateException(sbM112a.toString());
        }
        fragment.mFragmentManager = this.mManager;
        if (str != null) {
            String str2 = fragment.mTag;
            if (str2 != null && !str.equals(str2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Can't change tag of fragment ");
                sb.append(fragment);
                sb.append(": was ");
                throw new IllegalStateException(C0164a.m99a(sb, fragment.mTag, " now ", str));
            }
            fragment.mTag = str;
        }
        if (i7 != 0) {
            if (i7 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i9 = fragment.mFragmentId;
            if (i9 != 0 && i9 != i7) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i7);
            }
            fragment.mFragmentId = i7;
            fragment.mContainerId = i7;
        }
        addOp(new C0134Op(i8, fragment));
    }

    private static boolean isFragmentPostponed(C0134Op c0134Op) {
        Fragment fragment = c0134Op.fragment;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction add(Fragment fragment, @Nullable String str) {
        doAddOp(0, fragment, str, 1);
        return this;
    }

    public void addOp(C0134Op c0134Op) {
        this.mOps.add(c0134Op);
        c0134Op.enterAnim = this.mEnterAnim;
        c0134Op.exitAnim = this.mExitAnim;
        c0134Op.popEnterAnim = this.mPopEnterAnim;
        c0134Op.popExitAnim = this.mPopExitAnim;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction addSharedElement(View view, String str) {
        if (FragmentTransition.supportsTransition()) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList<>();
                this.mSharedElementTargetNames = new ArrayList<>();
            } else {
                if (this.mSharedElementTargetNames.contains(str)) {
                    throw new IllegalArgumentException(C0096a.m97a("A shared element with the target name '", str, "' has already been added to the transaction."));
                }
                if (this.mSharedElementSourceNames.contains(transitionName)) {
                    throw new IllegalArgumentException(C0096a.m97a("A shared element with the source name '", transitionName, " has already been added to the transaction."));
                }
            }
            this.mSharedElementSourceNames.add(transitionName);
            this.mSharedElementTargetNames.add(str);
        }
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction addToBackStack(@Nullable String str) {
        if (!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = str;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction attach(Fragment fragment) {
        addOp(new C0134Op(7, fragment));
        return this;
    }

    public void bumpBackStackNesting(int i7) {
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bump nesting in ");
                sb.append(this);
                sb.append(" by ");
                sb.append(i7);
            }
            int size = this.mOps.size();
            for (int i8 = 0; i8 < size; i8++) {
                C0134Op c0134Op = this.mOps.get(i8);
                Fragment fragment = c0134Op.fragment;
                if (fragment != null) {
                    fragment.mBackStackNesting += i7;
                    if (FragmentManagerImpl.DEBUG) {
                        StringBuilder sbM112a = C0413b.m112a("Bump nesting of ");
                        sbM112a.append(c0134Op.fragment);
                        sbM112a.append(" to ");
                        sbM112a.append(c0134Op.fragment.mBackStackNesting);
                    }
                }
            }
        }
    }

    @Override // android.support.v4.app.FragmentTransaction
    public int commit() {
        return commitInternal(false);
    }

    @Override // android.support.v4.app.FragmentTransaction
    public int commitAllowingStateLoss() {
        return commitInternal(true);
    }

    public int commitInternal(boolean z6) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("Commit: ");
            sb.append(this);
            PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
            dump("  ", null, printWriter, null);
            printWriter.close();
        }
        this.mCommitted = true;
        if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex(this);
        } else {
            this.mIndex = -1;
        }
        this.mManager.enqueueAction(this, z6);
        return this.mIndex;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public void commitNow() {
        disallowAddToBackStack();
        this.mManager.execSingleAction(this, false);
    }

    @Override // android.support.v4.app.FragmentTransaction
    public void commitNowAllowingStateLoss() {
        disallowAddToBackStack();
        this.mManager.execSingleAction(this, true);
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction detach(Fragment fragment) {
        addOp(new C0134Op(6, fragment));
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        dump(str, printWriter, true);
    }

    public void executeOps() {
        int size = this.mOps.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0134Op c0134Op = this.mOps.get(i7);
            Fragment fragment = c0134Op.fragment;
            if (fragment != null) {
                fragment.setNextTransition(this.mTransition, this.mTransitionStyle);
            }
            switch (c0134Op.cmd) {
                case 1:
                    fragment.setNextAnim(c0134Op.enterAnim);
                    this.mManager.addFragment(fragment, false);
                    break;
                case 2:
                default:
                    StringBuilder sbM112a = C0413b.m112a("Unknown cmd: ");
                    sbM112a.append(c0134Op.cmd);
                    throw new IllegalArgumentException(sbM112a.toString());
                case 3:
                    fragment.setNextAnim(c0134Op.exitAnim);
                    this.mManager.removeFragment(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(c0134Op.exitAnim);
                    this.mManager.hideFragment(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(c0134Op.enterAnim);
                    this.mManager.showFragment(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(c0134Op.exitAnim);
                    this.mManager.detachFragment(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(c0134Op.enterAnim);
                    this.mManager.attachFragment(fragment);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment(fragment);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
            }
            if (!this.mReorderingAllowed && c0134Op.cmd != 1 && fragment != null) {
                this.mManager.moveFragmentToExpectedState(fragment);
            }
        }
        if (this.mReorderingAllowed) {
            return;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mManager;
        fragmentManagerImpl.moveToState(fragmentManagerImpl.mCurState, true);
    }

    public void executePopOps(boolean z6) {
        for (int size = this.mOps.size() - 1; size >= 0; size--) {
            C0134Op c0134Op = this.mOps.get(size);
            Fragment fragment = c0134Op.fragment;
            if (fragment != null) {
                fragment.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
            }
            switch (c0134Op.cmd) {
                case 1:
                    fragment.setNextAnim(c0134Op.popExitAnim);
                    this.mManager.removeFragment(fragment);
                    break;
                case 2:
                default:
                    StringBuilder sbM112a = C0413b.m112a("Unknown cmd: ");
                    sbM112a.append(c0134Op.cmd);
                    throw new IllegalArgumentException(sbM112a.toString());
                case 3:
                    fragment.setNextAnim(c0134Op.popEnterAnim);
                    this.mManager.addFragment(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(c0134Op.popEnterAnim);
                    this.mManager.showFragment(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(c0134Op.popExitAnim);
                    this.mManager.hideFragment(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(c0134Op.popEnterAnim);
                    this.mManager.attachFragment(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(c0134Op.popExitAnim);
                    this.mManager.detachFragment(fragment);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment(fragment);
                    break;
            }
            if (!this.mReorderingAllowed && c0134Op.cmd != 3 && fragment != null) {
                this.mManager.moveFragmentToExpectedState(fragment);
            }
        }
        if (this.mReorderingAllowed || !z6) {
            return;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mManager;
        fragmentManagerImpl.moveToState(fragmentManagerImpl.mCurState, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.support.v4.app.Fragment expandOps(java.util.ArrayList<android.support.v4.app.Fragment> r17, android.support.v4.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r5 = r0.mOps
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r5 = r0.mOps
            java.lang.Object r5 = r5.get(r4)
            android.support.v4.app.BackStackRecord$Op r5 = (android.support.v4.app.BackStackRecord.C0134Op) r5
            int r6 = r5.cmd
            r7 = 0
            r8 = 1
            if (r6 == r8) goto Lb2
            r9 = 2
            r10 = 3
            r11 = 9
            if (r6 == r9) goto L58
            if (r6 == r10) goto L41
            r9 = 6
            if (r6 == r9) goto L41
            r7 = 7
            if (r6 == r7) goto Lb2
            r7 = 8
            if (r6 == r7) goto L31
            goto Lb7
        L31:
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r6 = r0.mOps
            android.support.v4.app.BackStackRecord$Op r7 = new android.support.v4.app.BackStackRecord$Op
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            android.support.v4.app.Fragment r3 = r5.fragment
            goto Lb7
        L41:
            android.support.v4.app.Fragment r6 = r5.fragment
            r1.remove(r6)
            android.support.v4.app.Fragment r5 = r5.fragment
            if (r5 != r3) goto Lb7
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r3 = r0.mOps
            android.support.v4.app.BackStackRecord$Op r6 = new android.support.v4.app.BackStackRecord$Op
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            android.support.v4.app.Fragment r6 = r5.fragment
            int r9 = r6.mContainerId
            int r12 = r17.size()
            int r12 = r12 - r8
            r13 = 0
        L62:
            if (r12 < 0) goto La2
            java.lang.Object r14 = r1.get(r12)
            android.support.v4.app.Fragment r14 = (android.support.v4.app.Fragment) r14
            int r15 = r14.mContainerId
            if (r15 != r9) goto L9f
            if (r14 != r6) goto L72
            r13 = 1
            goto L9f
        L72:
            if (r14 != r3) goto L81
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r3 = r0.mOps
            android.support.v4.app.BackStackRecord$Op r15 = new android.support.v4.app.BackStackRecord$Op
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            android.support.v4.app.BackStackRecord$Op r15 = new android.support.v4.app.BackStackRecord$Op
            r15.<init>(r10, r14)
            int r2 = r5.enterAnim
            r15.enterAnim = r2
            int r2 = r5.popEnterAnim
            r15.popEnterAnim = r2
            int r2 = r5.exitAnim
            r15.exitAnim = r2
            int r2 = r5.popExitAnim
            r15.popExitAnim = r2
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r2 = r0.mOps
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r2 = r0.mOps
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.cmd = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            android.support.v4.app.Fragment r2 = r5.fragment
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.BackStackRecord.expandOps(java.util.ArrayList, android.support.v4.app.Fragment):android.support.v4.app.Fragment");
    }

    @Override // android.support.v4.app.FragmentManagerImpl.OpGenerator
    public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (FragmentManagerImpl.DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("Run: ");
            sb.append(this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.mAddToBackStack) {
            return true;
        }
        this.mManager.addBackStackState(this);
        return true;
    }

    @Override // android.support.v4.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbShortTitle() {
        return this.mBreadCrumbShortTitleRes != 0 ? this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes) : this.mBreadCrumbShortTitleText;
    }

    @Override // android.support.v4.app.FragmentManager.BackStackEntry
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }

    @Override // android.support.v4.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbTitle() {
        return this.mBreadCrumbTitleRes != 0 ? this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes) : this.mBreadCrumbTitleText;
    }

    @Override // android.support.v4.app.FragmentManager.BackStackEntry
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }

    @Override // android.support.v4.app.FragmentManager.BackStackEntry
    public int getId() {
        return this.mIndex;
    }

    @Override // android.support.v4.app.FragmentManager.BackStackEntry
    @Nullable
    public String getName() {
        return this.mName;
    }

    public int getTransition() {
        return this.mTransition;
    }

    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction hide(Fragment fragment) {
        addOp(new C0134Op(4, fragment));
        return this;
    }

    public boolean interactsWith(int i7) {
        int size = this.mOps.size();
        for (int i8 = 0; i8 < size; i8++) {
            Fragment fragment = this.mOps.get(i8).fragment;
            int i9 = fragment != null ? fragment.mContainerId : 0;
            if (i9 != 0 && i9 == i7) {
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    public boolean isPostponed() {
        for (int i7 = 0; i7 < this.mOps.size(); i7++) {
            if (isFragmentPostponed(this.mOps.get(i7))) {
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction remove(Fragment fragment) {
        addOp(new C0134Op(3, fragment));
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction replace(int i7, Fragment fragment) {
        return replace(i7, fragment, null);
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction runOnCommit(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("runnable cannot be null");
        }
        disallowAddToBackStack();
        if (this.mCommitRunnables == null) {
            this.mCommitRunnables = new ArrayList<>();
        }
        this.mCommitRunnables.add(runnable);
        return this;
    }

    public void runOnCommitRunnables() {
        ArrayList<Runnable> arrayList = this.mCommitRunnables;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.mCommitRunnables.get(i7).run();
            }
            this.mCommitRunnables = null;
        }
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setAllowOptimization(boolean z6) {
        return setReorderingAllowed(z6);
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbShortTitle(int i7) {
        this.mBreadCrumbShortTitleRes = i7;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbTitle(int i7) {
        this.mBreadCrumbTitleRes = i7;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setCustomAnimations(int i7, int i8) {
        return setCustomAnimations(i7, i8, 0, 0);
    }

    public void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener onStartEnterTransitionListener) {
        for (int i7 = 0; i7 < this.mOps.size(); i7++) {
            C0134Op c0134Op = this.mOps.get(i7);
            if (isFragmentPostponed(c0134Op)) {
                c0134Op.fragment.setOnStartEnterTransitionListener(onStartEnterTransitionListener);
            }
        }
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        addOp(new C0134Op(8, fragment));
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setReorderingAllowed(boolean z6) {
        this.mReorderingAllowed = z6;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setTransition(int i7) {
        this.mTransition = i7;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setTransitionStyle(int i7) {
        this.mTransitionStyle = i7;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction show(Fragment fragment) {
        addOp(new C0134Op(5, fragment));
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.support.v4.app.Fragment trackAddedFragmentsInPop(java.util.ArrayList<android.support.v4.app.Fragment> r5, android.support.v4.app.Fragment r6) {
        /*
            r4 = this;
            r0 = 0
        L1:
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r1 = r4.mOps
            int r1 = r1.size()
            if (r0 >= r1) goto L30
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r1 = r4.mOps
            java.lang.Object r1 = r1.get(r0)
            android.support.v4.app.BackStackRecord$Op r1 = (android.support.v4.app.BackStackRecord.C0134Op) r1
            int r2 = r1.cmd
            r3 = 1
            if (r2 == r3) goto L28
            r3 = 3
            if (r2 == r3) goto L22
            switch(r2) {
                case 6: goto L22;
                case 7: goto L28;
                case 8: goto L20;
                case 9: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L2d
        L1d:
            android.support.v4.app.Fragment r6 = r1.fragment
            goto L2d
        L20:
            r6 = 0
            goto L2d
        L22:
            android.support.v4.app.Fragment r1 = r1.fragment
            r5.add(r1)
            goto L2d
        L28:
            android.support.v4.app.Fragment r1 = r1.fragment
            r5.remove(r1)
        L2d:
            int r0 = r0 + 1
            goto L1
        L30:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.BackStackRecord.trackAddedFragmentsInPop(java.util.ArrayList, android.support.v4.app.Fragment):android.support.v4.app.Fragment");
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction add(int i7, Fragment fragment) {
        doAddOp(i7, fragment, null, 1);
        return this;
    }

    public void dump(String str, PrintWriter printWriter, boolean z6) {
        String string;
        if (z6) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (this.mOps.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.mOps.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0134Op c0134Op = this.mOps.get(i7);
            switch (c0134Op.cmd) {
                case 0:
                    string = "NULL";
                    break;
                case 1:
                    string = "ADD";
                    break;
                case 2:
                    string = "REPLACE";
                    break;
                case 3:
                    string = "REMOVE";
                    break;
                case 4:
                    string = "HIDE";
                    break;
                case 5:
                    string = "SHOW";
                    break;
                case 6:
                    string = "DETACH";
                    break;
                case 7:
                    string = "ATTACH";
                    break;
                case 8:
                    string = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    string = "UNSET_PRIMARY_NAV";
                    break;
                default:
                    StringBuilder sbM112a = C0413b.m112a("cmd=");
                    sbM112a.append(c0134Op.cmd);
                    string = sbM112a.toString();
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i7);
            printWriter.print(": ");
            printWriter.print(string);
            printWriter.print(" ");
            printWriter.println(c0134Op.fragment);
            if (z6) {
                if (c0134Op.enterAnim != 0 || c0134Op.exitAnim != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(c0134Op.enterAnim));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(c0134Op.exitAnim));
                }
                if (c0134Op.popEnterAnim != 0 || c0134Op.popExitAnim != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(c0134Op.popEnterAnim));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(c0134Op.popExitAnim));
                }
            }
        }
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction replace(int i7, Fragment fragment, @Nullable String str) {
        if (i7 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        doAddOp(i7, fragment, str, 2);
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setCustomAnimations(int i7, int i8, int i9, int i10) {
        this.mEnterAnim = i7;
        this.mExitAnim = i8;
        this.mPopEnterAnim = i9;
        this.mPopExitAnim = i10;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction add(int i7, Fragment fragment, @Nullable String str) {
        doAddOp(i7, fragment, str, 1);
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence charSequence) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = charSequence;
        return this;
    }

    @Override // android.support.v4.app.FragmentTransaction
    public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence charSequence) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = charSequence;
        return this;
    }

    public boolean interactsWith(ArrayList<BackStackRecord> arrayList, int i7, int i8) {
        if (i8 == i7) {
            return false;
        }
        int size = this.mOps.size();
        int i9 = -1;
        for (int i10 = 0; i10 < size; i10++) {
            Fragment fragment = this.mOps.get(i10).fragment;
            int i11 = fragment != null ? fragment.mContainerId : 0;
            if (i11 != 0 && i11 != i9) {
                for (int i12 = i7; i12 < i8; i12++) {
                    BackStackRecord backStackRecord = arrayList.get(i12);
                    int size2 = backStackRecord.mOps.size();
                    for (int i13 = 0; i13 < size2; i13++) {
                        Fragment fragment2 = backStackRecord.mOps.get(i13).fragment;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i11) {
                            return true;
                        }
                    }
                }
                i9 = i11;
            }
        }
        return false;
    }
}
