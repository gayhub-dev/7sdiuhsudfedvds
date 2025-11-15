package android.support.v7.view.menu;

import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class MenuAdapter extends BaseAdapter {
    public MenuBuilder mAdapterMenu;
    private int mExpandedIndex = -1;
    private boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final int mItemLayoutRes;
    private final boolean mOverflowOnly;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z6, int i7) {
        this.mOverflowOnly = z6;
        this.mInflater = layoutInflater;
        this.mAdapterMenu = menuBuilder;
        this.mItemLayoutRes = i7;
        findExpandedIndex();
    }

    public void findExpandedIndex() {
        MenuItemImpl expandedItem = this.mAdapterMenu.getExpandedItem();
        if (expandedItem != null) {
            ArrayList<MenuItemImpl> nonActionItems = this.mAdapterMenu.getNonActionItems();
            int size = nonActionItems.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (nonActionItems.get(i7) == expandedItem) {
                    this.mExpandedIndex = i7;
                    return;
                }
            }
        }
        this.mExpandedIndex = -1;
    }

    public MenuBuilder getAdapterMenu() {
        return this.mAdapterMenu;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mExpandedIndex < 0 ? (this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems()).size() : r0.size() - 1;
    }

    public boolean getForceShowIcon() {
        return this.mForceShowIcon;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i7) {
        return i7;
    }

    @Override // android.widget.Adapter
    public View getView(int i7, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(this.mItemLayoutRes, viewGroup, false);
        }
        int groupId = getItem(i7).getGroupId();
        int i8 = i7 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.mAdapterMenu.isGroupDividerEnabled() && groupId != (i8 >= 0 ? getItem(i8).getGroupId() : groupId));
        MenuView.ItemView itemView = (MenuView.ItemView) view;
        if (this.mForceShowIcon) {
            listMenuItemView.setForceShowIcon(true);
        }
        itemView.initialize(getItem(i7), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public void setForceShowIcon(boolean z6) {
        this.mForceShowIcon = z6;
    }

    @Override // android.widget.Adapter
    public MenuItemImpl getItem(int i7) {
        ArrayList<MenuItemImpl> nonActionItems = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        int i8 = this.mExpandedIndex;
        if (i8 >= 0 && i7 >= i8) {
            i7++;
        }
        return nonActionItems.get(i7);
    }
}
