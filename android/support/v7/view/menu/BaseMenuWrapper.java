package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
abstract class BaseMenuWrapper<T> extends BaseWrapper<T> {
    public final Context mContext;
    private Map<SupportMenuItem, MenuItem> mMenuItems;
    private Map<SupportSubMenu, SubMenu> mSubMenus;

    public BaseMenuWrapper(Context context, T t6) {
        super(t6);
        this.mContext = context;
    }

    public final MenuItem getMenuItemWrapper(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return menuItem;
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
        if (this.mMenuItems == null) {
            this.mMenuItems = new ArrayMap();
        }
        MenuItem menuItem2 = this.mMenuItems.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItem menuItemWrapSupportMenuItem = MenuWrapperFactory.wrapSupportMenuItem(this.mContext, supportMenuItem);
        this.mMenuItems.put(supportMenuItem, menuItemWrapSupportMenuItem);
        return menuItemWrapSupportMenuItem;
    }

    public final SubMenu getSubMenuWrapper(SubMenu subMenu) {
        if (!(subMenu instanceof SupportSubMenu)) {
            return subMenu;
        }
        SupportSubMenu supportSubMenu = (SupportSubMenu) subMenu;
        if (this.mSubMenus == null) {
            this.mSubMenus = new ArrayMap();
        }
        SubMenu subMenu2 = this.mSubMenus.get(supportSubMenu);
        if (subMenu2 != null) {
            return subMenu2;
        }
        SubMenu subMenuWrapSupportSubMenu = MenuWrapperFactory.wrapSupportSubMenu(this.mContext, supportSubMenu);
        this.mSubMenus.put(supportSubMenu, subMenuWrapSupportSubMenu);
        return subMenuWrapSupportSubMenu;
    }

    public final void internalClear() {
        Map<SupportMenuItem, MenuItem> map = this.mMenuItems;
        if (map != null) {
            map.clear();
        }
        Map<SupportSubMenu, SubMenu> map2 = this.mSubMenus;
        if (map2 != null) {
            map2.clear();
        }
    }

    public final void internalRemoveGroup(int i7) {
        Map<SupportMenuItem, MenuItem> map = this.mMenuItems;
        if (map == null) {
            return;
        }
        Iterator<SupportMenuItem> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i7 == it.next().getGroupId()) {
                it.remove();
            }
        }
    }

    public final void internalRemoveItem(int i7) {
        Map<SupportMenuItem, MenuItem> map = this.mMenuItems;
        if (map == null) {
            return;
        }
        Iterator<SupportMenuItem> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i7 == it.next().getItemId()) {
                it.remove();
                return;
            }
        }
    }
}
