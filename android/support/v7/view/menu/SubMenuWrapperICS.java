package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
    public SubMenuWrapperICS(Context context, SupportSubMenu supportSubMenu) {
        super(context, supportSubMenu);
    }

    @Override // android.view.SubMenu
    public void clearHeader() {
        getWrappedObject().clearHeader();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return getMenuItemWrapper(getWrappedObject().getItem());
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i7) {
        getWrappedObject().setHeaderIcon(i7);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i7) {
        getWrappedObject().setHeaderTitle(i7);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        getWrappedObject().setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i7) {
        getWrappedObject().setIcon(i7);
        return this;
    }

    @Override // android.support.v7.view.menu.BaseWrapper
    public SupportSubMenu getWrappedObject() {
        return (SupportSubMenu) this.mWrappedObject;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        getWrappedObject().setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        getWrappedObject().setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        getWrappedObject().setIcon(drawable);
        return this;
    }
}
