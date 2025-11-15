package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.internal.view.SupportMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* loaded from: classes.dex */
class MenuWrapperICS extends BaseMenuWrapper<SupportMenu> implements Menu {
    public MenuWrapperICS(Context context, SupportMenu supportMenu) {
        super(context, supportMenu);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(charSequence));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i7, int i8, int i9, ComponentName componentName, Intent[] intentArr, Intent intent, int i10, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = ((SupportMenu) this.mWrappedObject).addIntentOptions(i7, i8, i9, componentName, intentArr, intent, i10, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i11 = 0; i11 < length; i11++) {
                menuItemArr[i11] = getMenuItemWrapper(menuItemArr2[i11]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public void clear() {
        internalClear();
        ((SupportMenu) this.mWrappedObject).clear();
    }

    @Override // android.view.Menu
    public void close() {
        ((SupportMenu) this.mWrappedObject).close();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i7) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).findItem(i7));
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i7) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).getItem(i7));
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return ((SupportMenu) this.mWrappedObject).hasVisibleItems();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i7, KeyEvent keyEvent) {
        return ((SupportMenu) this.mWrappedObject).isShortcutKey(i7, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i7, int i8) {
        return ((SupportMenu) this.mWrappedObject).performIdentifierAction(i7, i8);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i7, KeyEvent keyEvent, int i8) {
        return ((SupportMenu) this.mWrappedObject).performShortcut(i7, keyEvent, i8);
    }

    @Override // android.view.Menu
    public void removeGroup(int i7) {
        internalRemoveGroup(i7);
        ((SupportMenu) this.mWrappedObject).removeGroup(i7);
    }

    @Override // android.view.Menu
    public void removeItem(int i7) {
        internalRemoveItem(i7);
        ((SupportMenu) this.mWrappedObject).removeItem(i7);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i7, boolean z6, boolean z7) {
        ((SupportMenu) this.mWrappedObject).setGroupCheckable(i7, z6, z7);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i7, boolean z6) {
        ((SupportMenu) this.mWrappedObject).setGroupEnabled(i7, z6);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i7, boolean z6) {
        ((SupportMenu) this.mWrappedObject).setGroupVisible(i7, z6);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z6) {
        ((SupportMenu) this.mWrappedObject).setQwertyMode(z6);
    }

    @Override // android.view.Menu
    public int size() {
        return ((SupportMenu) this.mWrappedObject).size();
    }

    @Override // android.view.Menu
    public MenuItem add(int i7) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i7));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i7) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i7));
    }

    @Override // android.view.Menu
    public MenuItem add(int i7, int i8, int i9, CharSequence charSequence) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i7, i8, i9, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i7, int i8, int i9, CharSequence charSequence) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i7, i8, i9, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i7, int i8, int i9, int i10) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i7, i8, i9, i10));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i7, int i8, int i9, int i10) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i7, i8, i9, i10));
    }
}
