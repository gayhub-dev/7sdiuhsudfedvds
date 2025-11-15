package android.support.v7.view;

import android.app.Activity;
import android.arch.lifecycle.C0063n;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.annotation.LayoutRes;
import android.support.annotation.RestrictTo;
import android.support.constraint.motion.C0081c;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.C0308R;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SupportMenuInflater extends MenuInflater {
    public static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE;
    public static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    public static final String LOG_TAG = "SupportMenuInflater";
    public static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    public final Object[] mActionProviderConstructorArguments;
    public final Object[] mActionViewConstructorArguments;
    public Context mContext;
    private Object mRealOwner;

    public static class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = {MenuItem.class};
        private Method mMethod;
        private Object mRealOwner;

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            this.mRealOwner = obj;
            Class<?> cls = obj.getClass();
            try {
                this.mMethod = cls.getMethod(str, PARAM_TYPES);
            } catch (Exception e7) {
                InflateException inflateException = new InflateException(C0319a.m107a(cls, C0081c.m95a("Couldn't resolve menu item onClick handler ", str, " in class ")));
                inflateException.initCause(e7);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.mMethod.invoke(this.mRealOwner, menuItem)).booleanValue();
                }
                this.mMethod.invoke(this.mRealOwner, menuItem);
                return true;
            } catch (Exception e7) {
                throw new RuntimeException(e7);
            }
        }
    }

    public class MenuState {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        public ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList = null;
        private PorterDuff.Mode itemIconTintMode = null;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;

        public MenuState(Menu menu) {
            this.menu = menu;
            resetGroup();
        }

        private char getShortcut(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        private <T> T newInstance(String str, Class<?>[] clsArr, Object[] objArr) throws NoSuchMethodException, SecurityException {
            try {
                Constructor<?> constructor = SupportMenuInflater.this.mContext.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception unused) {
                return null;
            }
        }

        private void setItem(MenuItem menuItem) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            boolean z6 = false;
            menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            int i7 = this.itemShowAsAction;
            if (i7 >= 0) {
                menuItem.setShowAsAction(i7);
            }
            if (this.itemListenerMethodName != null) {
                if (SupportMenuInflater.this.mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
            }
            boolean z7 = menuItem instanceof MenuItemImpl;
            if (z7) {
            }
            if (this.itemCheckable >= 2) {
                if (z7) {
                    ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).setExclusiveCheckable(true);
                }
            }
            String str = this.itemActionViewClassName;
            if (str != null) {
                menuItem.setActionView((View) newInstance(str, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments));
                z6 = true;
            }
            int i8 = this.itemActionViewLayout;
            if (i8 > 0 && !z6) {
                menuItem.setActionView(i8);
            }
            ActionProvider actionProvider = this.itemActionProvider;
            if (actionProvider != null) {
                MenuItemCompat.setActionProvider(menuItem, actionProvider);
            }
            MenuItemCompat.setContentDescription(menuItem, this.itemContentDescription);
            MenuItemCompat.setTooltipText(menuItem, this.itemTooltipText);
            MenuItemCompat.setAlphabeticShortcut(menuItem, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
            MenuItemCompat.setNumericShortcut(menuItem, this.itemNumericShortcut, this.itemNumericModifiers);
            PorterDuff.Mode mode = this.itemIconTintMode;
            if (mode != null) {
                MenuItemCompat.setIconTintMode(menuItem, mode);
            }
            ColorStateList colorStateList = this.itemIconTintList;
            if (colorStateList != null) {
                MenuItemCompat.setIconTintList(menuItem, colorStateList);
            }
        }

        public void addItem() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.itemAdded = true;
            setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.itemAdded = true;
            SubMenu subMenuAddSubMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            setItem(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        public void readGroup(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, C0308R.styleable.MenuGroup);
            this.groupId = typedArrayObtainStyledAttributes.getResourceId(C0308R.styleable.MenuGroup_android_id, 0);
            this.groupCategory = typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.MenuGroup_android_visible, true);
            this.groupEnabled = typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void readItem(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, C0308R.styleable.MenuItem);
            this.itemId = typedArrayObtainStyledAttributes.getResourceId(C0308R.styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = (typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuItem_android_menuCategory, this.groupCategory) & SupportMenu.CATEGORY_MASK) | (typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuItem_android_orderInCategory, this.groupOrder) & 65535);
            this.itemTitle = typedArrayObtainStyledAttributes.getText(C0308R.styleable.MenuItem_android_title);
            this.itemTitleCondensed = typedArrayObtainStyledAttributes.getText(C0308R.styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = typedArrayObtainStyledAttributes.getResourceId(C0308R.styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = getShortcut(typedArrayObtainStyledAttributes.getString(C0308R.styleable.MenuItem_android_alphabeticShortcut));
            this.itemAlphabeticModifiers = typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuItem_alphabeticModifiers, 4096);
            this.itemNumericShortcut = getShortcut(typedArrayObtainStyledAttributes.getString(C0308R.styleable.MenuItem_android_numericShortcut));
            this.itemNumericModifiers = typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuItem_numericModifiers, 4096);
            int i7 = C0308R.styleable.MenuItem_android_checkable;
            if (typedArrayObtainStyledAttributes.hasValue(i7)) {
                this.itemCheckable = typedArrayObtainStyledAttributes.getBoolean(i7, false) ? 1 : 0;
            } else {
                this.itemCheckable = this.groupCheckable;
            }
            this.itemChecked = typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.MenuItem_android_checked, false);
            this.itemVisible = typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = typedArrayObtainStyledAttributes.getInt(C0308R.styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = typedArrayObtainStyledAttributes.getString(C0308R.styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = typedArrayObtainStyledAttributes.getResourceId(C0308R.styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = typedArrayObtainStyledAttributes.getString(C0308R.styleable.MenuItem_actionViewClass);
            String string = typedArrayObtainStyledAttributes.getString(C0308R.styleable.MenuItem_actionProviderClass);
            this.itemActionProviderClassName = string;
            if ((string != null) && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
                this.itemActionProvider = (ActionProvider) newInstance(string, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
            } else {
                this.itemActionProvider = null;
            }
            this.itemContentDescription = typedArrayObtainStyledAttributes.getText(C0308R.styleable.MenuItem_contentDescription);
            this.itemTooltipText = typedArrayObtainStyledAttributes.getText(C0308R.styleable.MenuItem_tooltipText);
            int i8 = C0308R.styleable.MenuItem_iconTintMode;
            if (typedArrayObtainStyledAttributes.hasValue(i8)) {
                this.itemIconTintMode = DrawableUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(i8, -1), this.itemIconTintMode);
            } else {
                this.itemIconTintMode = null;
            }
            int i9 = C0308R.styleable.MenuItem_iconTint;
            if (typedArrayObtainStyledAttributes.hasValue(i9)) {
                this.itemIconTintList = typedArrayObtainStyledAttributes.getColorStateList(i9);
            } else {
                this.itemIconTintList = null;
            }
            typedArrayObtainStyledAttributes.recycle();
            this.itemAdded = false;
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        ACTION_VIEW_CONSTRUCTOR_SIGNATURE = clsArr;
        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = clsArr;
    }

    public SupportMenuInflater(Context context) {
        super(context);
        this.mContext = context;
        Object[] objArr = {context};
        this.mActionViewConstructorArguments = objArr;
        this.mActionProviderConstructorArguments = objArr;
    }

    private Object findRealOwner(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? findRealOwner(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    private void parseMenu(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        MenuState menuState = new MenuState(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (!name.equals(XML_MENU)) {
                    throw new RuntimeException(C0063n.m88a("Expecting menu, got ", name));
                }
                eventType = xmlPullParser.next();
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z6 = false;
        boolean z7 = false;
        String str = null;
        while (!z6) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z7 && name2.equals(str)) {
                        str = null;
                        z7 = false;
                    } else if (name2.equals(XML_GROUP)) {
                        menuState.resetGroup();
                    } else if (name2.equals(XML_ITEM)) {
                        if (!menuState.hasAddedItem()) {
                            ActionProvider actionProvider = menuState.itemActionProvider;
                            if (actionProvider == null || !actionProvider.hasSubMenu()) {
                                menuState.addItem();
                            } else {
                                menuState.addSubMenuItem();
                            }
                        }
                    } else if (name2.equals(XML_MENU)) {
                        z6 = true;
                    }
                }
            } else if (!z7) {
                String name3 = xmlPullParser.getName();
                if (name3.equals(XML_GROUP)) {
                    menuState.readGroup(attributeSet);
                } else if (name3.equals(XML_ITEM)) {
                    menuState.readItem(attributeSet);
                } else if (name3.equals(XML_MENU)) {
                    parseMenu(xmlPullParser, attributeSet, menuState.addSubMenuItem());
                } else {
                    z7 = true;
                    str = name3;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public Object getRealOwner() {
        if (this.mRealOwner == null) {
            this.mRealOwner = findRealOwner(this.mContext);
        }
        return this.mRealOwner;
    }

    @Override // android.view.MenuInflater
    public void inflate(@LayoutRes int i7, Menu menu) {
        if (!(menu instanceof SupportMenu)) {
            super.inflate(i7, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.mContext.getResources().getLayout(i7);
                    parseMenu(layout, Xml.asAttributeSet(layout), menu);
                } catch (IOException e7) {
                    throw new InflateException("Error inflating menu XML", e7);
                }
            } catch (XmlPullParserException e8) {
                throw new InflateException("Error inflating menu XML", e8);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
