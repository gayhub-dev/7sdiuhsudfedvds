package android.support.v4.app;

import android.arch.lifecycle.C0065p;
import java.util.List;

/* loaded from: classes.dex */
public class FragmentManagerNonConfig {
    private final List<FragmentManagerNonConfig> mChildNonConfigs;
    private final List<Fragment> mFragments;
    private final List<C0065p> mViewModelStores;

    public FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2, List<C0065p> list3) {
        this.mFragments = list;
        this.mChildNonConfigs = list2;
        this.mViewModelStores = list3;
    }

    public List<FragmentManagerNonConfig> getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    public List<C0065p> getViewModelStores() {
        return this.mViewModelStores;
    }
}
