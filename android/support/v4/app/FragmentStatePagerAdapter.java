package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.motion.C0079a;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentStatePagerAdapt";
    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private ArrayList<Fragment.SavedState> mSavedState = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private Fragment mCurrentPrimaryItem = null;

    public FragmentStatePagerAdapter(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i7, @NonNull Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        while (this.mSavedState.size() <= i7) {
            this.mSavedState.add(null);
        }
        this.mSavedState.set(i7, fragment.isAdded() ? this.mFragmentManager.saveFragmentInstanceState(fragment) : null);
        this.mFragments.set(i7, null);
        this.mCurTransaction.remove(fragment);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void finishUpdate(@NonNull ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.mCurTransaction;
        if (fragmentTransaction != null) {
            fragmentTransaction.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int i7);

    @Override // android.support.v4.view.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i7) {
        Fragment.SavedState savedState;
        Fragment fragment;
        if (this.mFragments.size() > i7 && (fragment = this.mFragments.get(i7)) != null) {
            return fragment;
        }
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        Fragment item = getItem(i7);
        if (this.mSavedState.size() > i7 && (savedState = this.mSavedState.get(i7)) != null) {
            item.setInitialSavedState(savedState);
        }
        while (this.mFragments.size() <= i7) {
            this.mFragments.add(null);
        }
        item.setMenuVisibility(false);
        item.setUserVisibleHint(false);
        this.mFragments.set(i7, item);
        this.mCurTransaction.add(viewGroup.getId(), item);
        return item;
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) throws NumberFormatException {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.mSavedState.clear();
            this.mFragments.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.mSavedState.add((Fragment.SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int i7 = Integer.parseInt(str.substring(1));
                    Fragment fragment = this.mFragmentManager.getFragment(bundle, str);
                    if (fragment != null) {
                        while (this.mFragments.size() <= i7) {
                            this.mFragments.add(null);
                        }
                        fragment.setMenuVisibility(false);
                        this.mFragments.set(i7, fragment);
                    }
                }
            }
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public Parcelable saveState() {
        Bundle bundle;
        if (this.mSavedState.size() > 0) {
            bundle = new Bundle();
            Fragment.SavedState[] savedStateArr = new Fragment.SavedState[this.mSavedState.size()];
            this.mSavedState.toArray(savedStateArr);
            bundle.putParcelableArray("states", savedStateArr);
        } else {
            bundle = null;
        }
        for (int i7 = 0; i7 < this.mFragments.size(); i7++) {
            Fragment fragment = this.mFragments.get(i7);
            if (fragment != null && fragment.isAdded()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.mFragmentManager.putFragment(bundle, C0079a.m93a("f", i7), fragment);
            }
        }
        return bundle;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i7, @NonNull Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.mCurrentPrimaryItem;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            this.mCurrentPrimaryItem = fragment;
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public void startUpdate(@NonNull ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }
}
