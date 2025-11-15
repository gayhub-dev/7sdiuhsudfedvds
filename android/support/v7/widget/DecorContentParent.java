package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.MenuPresenter;
import android.util.SparseArray;
import android.view.Menu;
import android.view.Window;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    CharSequence getTitle();

    boolean hasIcon();

    boolean hasLogo();

    boolean hideOverflowMenu();

    void initFeature(int i7);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void restoreToolbarHierarchyState(SparseArray<Parcelable> sparseArray);

    void saveToolbarHierarchyState(SparseArray<Parcelable> sparseArray);

    void setIcon(int i7);

    void setIcon(Drawable drawable);

    void setLogo(int i7);

    void setMenu(Menu menu, MenuPresenter.Callback callback);

    void setMenuPrepared();

    void setUiOptions(int i7);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
