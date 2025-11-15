package android.support.v4.app;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class FragmentPagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;
    private final FragmentManager mFragmentManager;

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    private static String makeFragmentName(int i7, long j7) {
        return "android:switcher:" + i7 + ":" + j7;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i7, @NonNull Object obj) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        this.mCurTransaction.detach((Fragment) obj);
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

    public long getItemId(int i7) {
        return i7;
    }

    @Override // android.support.v4.view.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i7) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        long itemId = getItemId(i7);
        Fragment fragmentFindFragmentByTag = this.mFragmentManager.findFragmentByTag(makeFragmentName(viewGroup.getId(), itemId));
        if (fragmentFindFragmentByTag != null) {
            this.mCurTransaction.attach(fragmentFindFragmentByTag);
        } else {
            fragmentFindFragmentByTag = getItem(i7);
            this.mCurTransaction.add(viewGroup.getId(), fragmentFindFragmentByTag, makeFragmentName(viewGroup.getId(), itemId));
        }
        if (fragmentFindFragmentByTag != this.mCurrentPrimaryItem) {
            fragmentFindFragmentByTag.setMenuVisibility(false);
            fragmentFindFragmentByTag.setUserVisibleHint(false);
        }
        return fragmentFindFragmentByTag;
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // android.support.v4.view.PagerAdapter
    public Parcelable saveState() {
        return null;
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
