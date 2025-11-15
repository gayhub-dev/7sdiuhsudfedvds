package android.support.v7.view.menu;

import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public interface MenuView {

    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl menuItemImpl, int i7);

        boolean prefersCondensedTitle();

        void setCheckable(boolean z6);

        void setChecked(boolean z6);

        void setEnabled(boolean z6);

        void setIcon(Drawable drawable);

        void setShortcut(boolean z6, char c7);

        void setTitle(CharSequence charSequence);

        boolean showsIcon();
    }

    int getWindowAnimations();

    void initialize(MenuBuilder menuBuilder);
}
