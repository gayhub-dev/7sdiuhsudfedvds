package android.support.v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.C0308R;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    private static final String TAG = "ListMenuPresenter";
    public static final String VIEWS_TAG = "android:menu:list";
    public MenuAdapter mAdapter;
    private MenuPresenter.Callback mCallback;
    public Context mContext;
    private int mId;
    public LayoutInflater mInflater;
    public int mItemIndexOffset;
    public int mItemLayoutRes;
    public MenuBuilder mMenu;
    public ExpandedMenuView mMenuView;
    public int mThemeRes;

    public class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex = -1;

        public MenuAdapter() {
            findExpandedIndex();
        }

        public void findExpandedIndex() {
            MenuItemImpl expandedItem = ListMenuPresenter.this.mMenu.getExpandedItem();
            if (expandedItem != null) {
                ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.mMenu.getNonActionItems();
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

        @Override // android.widget.Adapter
        public int getCount() {
            int size = ListMenuPresenter.this.mMenu.getNonActionItems().size() - ListMenuPresenter.this.mItemIndexOffset;
            return this.mExpandedIndex < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i7) {
            return i7;
        }

        @Override // android.widget.Adapter
        public View getView(int i7, View view, ViewGroup viewGroup) {
            if (view == null) {
                ListMenuPresenter listMenuPresenter = ListMenuPresenter.this;
                view = listMenuPresenter.mInflater.inflate(listMenuPresenter.mItemLayoutRes, viewGroup, false);
            }
            ((MenuView.ItemView) view).initialize(getItem(i7), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public MenuItemImpl getItem(int i7) {
            ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.mMenu.getNonActionItems();
            int i8 = i7 + ListMenuPresenter.this.mItemIndexOffset;
            int i9 = this.mExpandedIndex;
            if (i9 >= 0 && i8 >= i9) {
                i8++;
            }
            return nonActionItems.get(i8);
        }
    }

    public ListMenuPresenter(Context context, int i7) {
        this(i7, 0);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new MenuAdapter();
        }
        return this.mAdapter;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public int getId() {
        return this.mId;
    }

    public int getItemIndexOffset() {
        return this.mItemIndexOffset;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (ExpandedMenuView) this.mInflater.inflate(C0308R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new MenuAdapter();
            }
            this.mMenuView.setAdapter((ListAdapter) this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        if (this.mThemeRes != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.mThemeRes);
            this.mContext = contextThemeWrapper;
            this.mInflater = LayoutInflater.from(contextThemeWrapper);
        } else if (this.mContext != null) {
            this.mContext = context;
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(context);
            }
        }
        this.mMenu = menuBuilder;
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z6) {
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z6);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
        this.mMenu.performItemAction(this.mAdapter.getItem(i7), this, 0);
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        restoreHierarchyState((Bundle) parcelable);
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        if (this.mMenuView == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        saveHierarchyState(bundle);
        return bundle;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).show(null);
        MenuPresenter.Callback callback = this.mCallback;
        if (callback == null) {
            return true;
        }
        callback.onOpenSubMenu(subMenuBuilder);
        return true;
    }

    public void restoreHierarchyState(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(VIEWS_TAG);
        if (sparseParcelableArray != null) {
            this.mMenuView.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public void saveHierarchyState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.mMenuView;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray(VIEWS_TAG, sparseArray);
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mCallback = callback;
    }

    public void setId(int i7) {
        this.mId = i7;
    }

    public void setItemIndexOffset(int i7) {
        this.mItemIndexOffset = i7;
        if (this.mMenuView != null) {
            updateMenuView(false);
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void updateMenuView(boolean z6) {
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(int i7, int i8) {
        this.mItemLayoutRes = i7;
        this.mThemeRes = i8;
    }
}
