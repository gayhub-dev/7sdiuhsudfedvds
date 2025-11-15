package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.C0308R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class AppCompatDrawableManager {
    private static final boolean DEBUG = false;
    private static AppCompatDrawableManager INSTANCE = null;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManag";
    private ArrayMap<String, InflateDelegate> mDelegates;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<>(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArrayCompat<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {C0308R.drawable.abc_textfield_search_default_mtrl_alpha, C0308R.drawable.abc_textfield_default_mtrl_alpha, C0308R.drawable.abc_ab_share_pack_mtrl_alpha};
    private static final int[] TINT_COLOR_CONTROL_NORMAL = {C0308R.drawable.abc_ic_commit_search_api_mtrl_alpha, C0308R.drawable.abc_seekbar_tick_mark_material, C0308R.drawable.abc_ic_menu_share_mtrl_alpha, C0308R.drawable.abc_ic_menu_copy_mtrl_am_alpha, C0308R.drawable.abc_ic_menu_cut_mtrl_alpha, C0308R.drawable.abc_ic_menu_selectall_mtrl_alpha, C0308R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {C0308R.drawable.abc_textfield_activated_mtrl_alpha, C0308R.drawable.abc_textfield_search_activated_mtrl_alpha, C0308R.drawable.abc_cab_background_top_mtrl_alpha, C0308R.drawable.abc_text_cursor_material, C0308R.drawable.abc_text_select_handle_left_mtrl_dark, C0308R.drawable.abc_text_select_handle_middle_mtrl_dark, C0308R.drawable.abc_text_select_handle_right_mtrl_dark, C0308R.drawable.abc_text_select_handle_left_mtrl_light, C0308R.drawable.abc_text_select_handle_middle_mtrl_light, C0308R.drawable.abc_text_select_handle_right_mtrl_light};
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {C0308R.drawable.abc_popup_background_mtrl_mult, C0308R.drawable.abc_cab_background_internal_bg, C0308R.drawable.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST = {C0308R.drawable.abc_tab_indicator_material, C0308R.drawable.abc_textfield_search_material};
    private static final int[] TINT_CHECKABLE_BUTTON_LIST = {C0308R.drawable.abc_btn_check_material, C0308R.drawable.abc_btn_radio_material};

    @RequiresApi(11)
    public static class AsldcInflateDelegate implements InflateDelegate {
        @Override // android.support.v7.widget.AppCompatDrawableManager.InflateDelegate
        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return AnimatedStateListDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public static class AvdcInflateDelegate implements InflateDelegate {
        @Override // android.support.v7.widget.AppCompatDrawableManager.InflateDelegate
        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int i7) {
            super(i7);
        }

        private static int generateCacheKey(int i7, PorterDuff.Mode mode) {
            return mode.hashCode() + ((i7 + 31) * 31);
        }

        public PorterDuffColorFilter get(int i7, PorterDuff.Mode mode) {
            return get(Integer.valueOf(generateCacheKey(i7, mode)));
        }

        public PorterDuffColorFilter put(int i7, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return put(Integer.valueOf(generateCacheKey(i7, mode)), porterDuffColorFilter);
        }
    }

    public interface InflateDelegate {
        Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme);
    }

    public static class VdcInflateDelegate implements InflateDelegate {
        @Override // android.support.v7.widget.AppCompatDrawableManager.InflateDelegate
        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    private void addDelegate(@NonNull String str, @NonNull InflateDelegate inflateDelegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new ArrayMap<>();
        }
        this.mDelegates.put(str, inflateDelegate);
    }

    private synchronized boolean addDrawableToCache(@NonNull Context context, long j7, @NonNull Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
        if (longSparseArray == null) {
            longSparseArray = new LongSparseArray<>();
            this.mDrawableCaches.put(context, longSparseArray);
        }
        longSparseArray.put(j7, new WeakReference<>(constantState));
        return true;
    }

    private void addTintListToCache(@NonNull Context context, @DrawableRes int i7, @NonNull ColorStateList colorStateList) {
        if (this.mTintLists == null) {
            this.mTintLists = new WeakHashMap<>();
        }
        SparseArrayCompat<ColorStateList> sparseArrayCompat = this.mTintLists.get(context);
        if (sparseArrayCompat == null) {
            sparseArrayCompat = new SparseArrayCompat<>();
            this.mTintLists.put(context, sparseArrayCompat);
        }
        sparseArrayCompat.append(i7, colorStateList);
    }

    private static boolean arrayContains(int[] iArr, int i7) {
        for (int i8 : iArr) {
            if (i8 == i7) {
                return true;
            }
        }
        return false;
    }

    private void checkVectorDrawableSetup(@NonNull Context context) {
        if (this.mHasCheckedVectorDrawableSetup) {
            return;
        }
        this.mHasCheckedVectorDrawableSetup = true;
        Drawable drawable = getDrawable(context, C0308R.drawable.abc_vector_test);
        if (drawable == null || !isVectorDrawable(drawable)) {
            this.mHasCheckedVectorDrawableSetup = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private ColorStateList createBorderlessButtonColorStateList(@NonNull Context context) {
        return createButtonColorStateList(context, 0);
    }

    private ColorStateList createButtonColorStateList(@NonNull Context context, @ColorInt int i7) {
        int themeAttrColor = ThemeUtils.getThemeAttrColor(context, C0308R.attr.colorControlHighlight);
        return new ColorStateList(new int[][]{ThemeUtils.DISABLED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{ThemeUtils.getDisabledThemeAttrColor(context, C0308R.attr.colorButtonNormal), ColorUtils.compositeColors(themeAttrColor, i7), ColorUtils.compositeColors(themeAttrColor, i7), i7});
    }

    private static long createCacheKey(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    private ColorStateList createColoredButtonColorStateList(@NonNull Context context) {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, C0308R.attr.colorAccent));
    }

    private ColorStateList createDefaultButtonColorStateList(@NonNull Context context) {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, C0308R.attr.colorButtonNormal));
    }

    private Drawable createDrawableIfNeeded(@NonNull Context context, @DrawableRes int i7) throws Resources.NotFoundException {
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue typedValue = this.mTypedValue;
        context.getResources().getValue(i7, typedValue, true);
        long jCreateCacheKey = createCacheKey(typedValue);
        Drawable cachedDrawable = getCachedDrawable(context, jCreateCacheKey);
        if (cachedDrawable != null) {
            return cachedDrawable;
        }
        if (i7 == C0308R.drawable.abc_cab_background_top_material) {
            cachedDrawable = new LayerDrawable(new Drawable[]{getDrawable(context, C0308R.drawable.abc_cab_background_internal_bg), getDrawable(context, C0308R.drawable.abc_cab_background_top_mtrl_alpha)});
        }
        if (cachedDrawable != null) {
            cachedDrawable.setChangingConfigurations(typedValue.changingConfigurations);
            addDrawableToCache(context, jCreateCacheKey, cachedDrawable);
        }
        return cachedDrawable;
    }

    private ColorStateList createSwitchThumbColorStateList(Context context) {
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        int i7 = C0308R.attr.colorSwitchThumbNormal;
        ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, i7);
        if (themeAttrColorStateList == null || !themeAttrColorStateList.isStateful()) {
            iArr[0] = ThemeUtils.DISABLED_STATE_SET;
            iArr2[0] = ThemeUtils.getDisabledThemeAttrColor(context, i7);
            iArr[1] = ThemeUtils.CHECKED_STATE_SET;
            iArr2[1] = ThemeUtils.getThemeAttrColor(context, C0308R.attr.colorControlActivated);
            iArr[2] = ThemeUtils.EMPTY_STATE_SET;
            iArr2[2] = ThemeUtils.getThemeAttrColor(context, i7);
        } else {
            iArr[0] = ThemeUtils.DISABLED_STATE_SET;
            iArr2[0] = themeAttrColorStateList.getColorForState(iArr[0], 0);
            iArr[1] = ThemeUtils.CHECKED_STATE_SET;
            iArr2[1] = ThemeUtils.getThemeAttrColor(context, C0308R.attr.colorControlActivated);
            iArr[2] = ThemeUtils.EMPTY_STATE_SET;
            iArr2[2] = themeAttrColorStateList.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return getPorterDuffColorFilter(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized AppCompatDrawableManager get() {
        if (INSTANCE == null) {
            AppCompatDrawableManager appCompatDrawableManager = new AppCompatDrawableManager();
            INSTANCE = appCompatDrawableManager;
            installDefaultInflateDelegates(appCompatDrawableManager);
        }
        return INSTANCE;
    }

    private synchronized Drawable getCachedDrawable(@NonNull Context context, long j7) {
        LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
        if (longSparseArray == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReference = longSparseArray.get(j7);
        if (weakReference != null) {
            Drawable.ConstantState constantState = weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            longSparseArray.delete(j7);
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int i7, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        ColorFilterLruCache colorFilterLruCache = COLOR_FILTER_CACHE;
        porterDuffColorFilter = colorFilterLruCache.get(i7, mode);
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i7, mode);
            colorFilterLruCache.put(i7, mode, porterDuffColorFilter);
        }
        return porterDuffColorFilter;
    }

    private ColorStateList getTintListFromCache(@NonNull Context context, @DrawableRes int i7) {
        SparseArrayCompat<ColorStateList> sparseArrayCompat;
        WeakHashMap<Context, SparseArrayCompat<ColorStateList>> weakHashMap = this.mTintLists;
        if (weakHashMap == null || (sparseArrayCompat = weakHashMap.get(context)) == null) {
            return null;
        }
        return sparseArrayCompat.get(i7);
    }

    public static PorterDuff.Mode getTintMode(int i7) {
        if (i7 == C0308R.drawable.abc_switch_thumb_material) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    private static void installDefaultInflateDelegates(@NonNull AppCompatDrawableManager appCompatDrawableManager) {
        if (Build.VERSION.SDK_INT < 24) {
            appCompatDrawableManager.addDelegate("vector", new VdcInflateDelegate());
            appCompatDrawableManager.addDelegate("animated-vector", new AvdcInflateDelegate());
            appCompatDrawableManager.addDelegate("animated-selector", new AsldcInflateDelegate());
        }
    }

    private static boolean isVectorDrawable(@NonNull Drawable drawable) {
        return (drawable instanceof VectorDrawableCompat) || PLATFORM_VD_CLAZZ.equals(drawable.getClass().getName());
    }

    private Drawable loadDrawableFromDelegates(@NonNull Context context, @DrawableRes int i7) throws XmlPullParserException, Resources.NotFoundException, IOException {
        int next;
        ArrayMap<String, InflateDelegate> arrayMap = this.mDelegates;
        if (arrayMap == null || arrayMap.isEmpty()) {
            return null;
        }
        SparseArrayCompat<String> sparseArrayCompat = this.mKnownDrawableIdTags;
        if (sparseArrayCompat != null) {
            String str = sparseArrayCompat.get(i7);
            if (SKIP_DRAWABLE_TAG.equals(str) || (str != null && this.mDelegates.get(str) == null)) {
                return null;
            }
        } else {
            this.mKnownDrawableIdTags = new SparseArrayCompat<>();
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue typedValue = this.mTypedValue;
        Resources resources = context.getResources();
        resources.getValue(i7, typedValue, true);
        long jCreateCacheKey = createCacheKey(typedValue);
        Drawable cachedDrawable = getCachedDrawable(context, jCreateCacheKey);
        if (cachedDrawable != null) {
            return cachedDrawable;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i7);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.mKnownDrawableIdTags.append(i7, name);
                InflateDelegate inflateDelegate = this.mDelegates.get(name);
                if (inflateDelegate != null) {
                    cachedDrawable = inflateDelegate.createFromXmlInner(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (cachedDrawable != null) {
                    cachedDrawable.setChangingConfigurations(typedValue.changingConfigurations);
                    addDrawableToCache(context, jCreateCacheKey, cachedDrawable);
                }
            } catch (Exception unused) {
            }
        }
        if (cachedDrawable == null) {
            this.mKnownDrawableIdTags.append(i7, SKIP_DRAWABLE_TAG);
        }
        return cachedDrawable;
    }

    private void removeDelegate(@NonNull String str, @NonNull InflateDelegate inflateDelegate) {
        ArrayMap<String, InflateDelegate> arrayMap = this.mDelegates;
        if (arrayMap == null || arrayMap.get(str) != inflateDelegate) {
            return;
        }
        this.mDelegates.remove(str);
    }

    private static void setPorterDuffColorFilter(Drawable drawable, int i7, PorterDuff.Mode mode) {
        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = DEFAULT_MODE;
        }
        drawable.setColorFilter(getPorterDuffColorFilter(i7, mode));
    }

    private Drawable tintDrawable(@NonNull Context context, @DrawableRes int i7, boolean z6, @NonNull Drawable drawable) {
        ColorStateList tintList = getTintList(context, i7);
        if (tintList != null) {
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable drawableWrap = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(drawableWrap, tintList);
            PorterDuff.Mode tintMode = getTintMode(i7);
            if (tintMode == null) {
                return drawableWrap;
            }
            DrawableCompat.setTintMode(drawableWrap, tintMode);
            return drawableWrap;
        }
        if (i7 == C0308R.drawable.abc_seekbar_track_material) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
            int i8 = C0308R.attr.colorControlNormal;
            int themeAttrColor = ThemeUtils.getThemeAttrColor(context, i8);
            PorterDuff.Mode mode = DEFAULT_MODE;
            setPorterDuffColorFilter(drawableFindDrawableByLayerId, themeAttrColor, mode);
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), ThemeUtils.getThemeAttrColor(context, i8), mode);
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(R.id.progress), ThemeUtils.getThemeAttrColor(context, C0308R.attr.colorControlActivated), mode);
            return drawable;
        }
        if (i7 != C0308R.drawable.abc_ratingbar_material && i7 != C0308R.drawable.abc_ratingbar_indicator_material && i7 != C0308R.drawable.abc_ratingbar_small_material) {
            if (tintDrawableUsingColorFilter(context, i7, drawable) || !z6) {
                return drawable;
            }
            return null;
        }
        LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
        Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.background);
        int disabledThemeAttrColor = ThemeUtils.getDisabledThemeAttrColor(context, C0308R.attr.colorControlNormal);
        PorterDuff.Mode mode2 = DEFAULT_MODE;
        setPorterDuffColorFilter(drawableFindDrawableByLayerId2, disabledThemeAttrColor, mode2);
        Drawable drawableFindDrawableByLayerId3 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
        int i9 = C0308R.attr.colorControlActivated;
        setPorterDuffColorFilter(drawableFindDrawableByLayerId3, ThemeUtils.getThemeAttrColor(context, i9), mode2);
        setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(R.id.progress), ThemeUtils.getThemeAttrColor(context, i9), mode2);
        return drawable;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean tintDrawableUsingColorFilter(@android.support.annotation.NonNull android.content.Context r6, @android.support.annotation.DrawableRes int r7, @android.support.annotation.NonNull android.graphics.drawable.Drawable r8) {
        /*
            android.graphics.PorterDuff$Mode r0 = android.support.v7.widget.AppCompatDrawableManager.DEFAULT_MODE
            int[] r1 = android.support.v7.widget.AppCompatDrawableManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL
            boolean r1 = arrayContains(r1, r7)
            r2 = 16842801(0x1010031, float:2.3693695E-38)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L15
            int r2 = android.support.v7.appcompat.C0308R.attr.colorControlNormal
        L12:
            r7 = -1
        L13:
            r1 = 1
            goto L42
        L15:
            int[] r1 = android.support.v7.widget.AppCompatDrawableManager.COLORFILTER_COLOR_CONTROL_ACTIVATED
            boolean r1 = arrayContains(r1, r7)
            if (r1 == 0) goto L20
            int r2 = android.support.v7.appcompat.C0308R.attr.colorControlActivated
            goto L12
        L20:
            int[] r1 = android.support.v7.widget.AppCompatDrawableManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY
            boolean r1 = arrayContains(r1, r7)
            if (r1 == 0) goto L2b
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L12
        L2b:
            int r1 = android.support.v7.appcompat.C0308R.drawable.abc_list_divider_mtrl_alpha
            if (r7 != r1) goto L3a
            r2 = 16842800(0x1010030, float:2.3693693E-38)
            r7 = 1109603123(0x42233333, float:40.8)
            int r7 = java.lang.Math.round(r7)
            goto L13
        L3a:
            int r1 = android.support.v7.appcompat.C0308R.drawable.abc_dialog_material_background
            if (r7 != r1) goto L3f
            goto L12
        L3f:
            r7 = -1
            r1 = 0
            r2 = 0
        L42:
            if (r1 == 0) goto L5f
            boolean r1 = android.support.v7.widget.DrawableUtils.canSafelyMutateDrawable(r8)
            if (r1 == 0) goto L4e
            android.graphics.drawable.Drawable r8 = r8.mutate()
        L4e:
            int r6 = android.support.v7.widget.ThemeUtils.getThemeAttrColor(r6, r2)
            android.graphics.PorterDuffColorFilter r6 = getPorterDuffColorFilter(r6, r0)
            r8.setColorFilter(r6)
            if (r7 == r3) goto L5e
            r8.setAlpha(r7)
        L5e:
            return r5
        L5f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatDrawableManager.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public synchronized Drawable getDrawable(@NonNull Context context, @DrawableRes int i7) {
        return getDrawable(context, i7, false);
    }

    public synchronized ColorStateList getTintList(@NonNull Context context, @DrawableRes int i7) {
        ColorStateList tintListFromCache;
        tintListFromCache = getTintListFromCache(context, i7);
        if (tintListFromCache == null) {
            if (i7 == C0308R.drawable.abc_edit_text_material) {
                tintListFromCache = AppCompatResources.getColorStateList(context, C0308R.color.abc_tint_edittext);
            } else if (i7 == C0308R.drawable.abc_switch_track_mtrl_alpha) {
                tintListFromCache = AppCompatResources.getColorStateList(context, C0308R.color.abc_tint_switch_track);
            } else if (i7 == C0308R.drawable.abc_switch_thumb_material) {
                tintListFromCache = createSwitchThumbColorStateList(context);
            } else if (i7 == C0308R.drawable.abc_btn_default_mtrl_shape) {
                tintListFromCache = createDefaultButtonColorStateList(context);
            } else if (i7 == C0308R.drawable.abc_btn_borderless_material) {
                tintListFromCache = createBorderlessButtonColorStateList(context);
            } else if (i7 == C0308R.drawable.abc_btn_colored_material) {
                tintListFromCache = createColoredButtonColorStateList(context);
            } else if (i7 == C0308R.drawable.abc_spinner_mtrl_am_alpha || i7 == C0308R.drawable.abc_spinner_textfield_background_material) {
                tintListFromCache = AppCompatResources.getColorStateList(context, C0308R.color.abc_tint_spinner);
            } else if (arrayContains(TINT_COLOR_CONTROL_NORMAL, i7)) {
                tintListFromCache = ThemeUtils.getThemeAttrColorStateList(context, C0308R.attr.colorControlNormal);
            } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, i7)) {
                tintListFromCache = AppCompatResources.getColorStateList(context, C0308R.color.abc_tint_default);
            } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, i7)) {
                tintListFromCache = AppCompatResources.getColorStateList(context, C0308R.color.abc_tint_btn_checkable);
            } else if (i7 == C0308R.drawable.abc_seekbar_thumb_material) {
                tintListFromCache = AppCompatResources.getColorStateList(context, C0308R.color.abc_tint_seek_thumb);
            }
            if (tintListFromCache != null) {
                addTintListToCache(context, i7, tintListFromCache);
            }
        }
        return tintListFromCache;
    }

    public synchronized void onConfigurationChanged(@NonNull Context context) {
        LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }

    public synchronized Drawable onDrawableLoadedFromResources(@NonNull Context context, @NonNull VectorEnabledTintResources vectorEnabledTintResources, @DrawableRes int i7) {
        Drawable drawableLoadDrawableFromDelegates = loadDrawableFromDelegates(context, i7);
        if (drawableLoadDrawableFromDelegates == null) {
            drawableLoadDrawableFromDelegates = vectorEnabledTintResources.superGetDrawable(i7);
        }
        if (drawableLoadDrawableFromDelegates == null) {
            return null;
        }
        return tintDrawable(context, i7, false, drawableLoadDrawableFromDelegates);
    }

    public synchronized Drawable getDrawable(@NonNull Context context, @DrawableRes int i7, boolean z6) {
        Drawable drawableLoadDrawableFromDelegates;
        checkVectorDrawableSetup(context);
        drawableLoadDrawableFromDelegates = loadDrawableFromDelegates(context, i7);
        if (drawableLoadDrawableFromDelegates == null) {
            drawableLoadDrawableFromDelegates = createDrawableIfNeeded(context, i7);
        }
        if (drawableLoadDrawableFromDelegates == null) {
            drawableLoadDrawableFromDelegates = ContextCompat.getDrawable(context, i7);
        }
        if (drawableLoadDrawableFromDelegates != null) {
            drawableLoadDrawableFromDelegates = tintDrawable(context, i7, z6, drawableLoadDrawableFromDelegates);
        }
        if (drawableLoadDrawableFromDelegates != null) {
            DrawableUtils.fixDrawable(drawableLoadDrawableFromDelegates);
        }
        return drawableLoadDrawableFromDelegates;
    }

    public static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        if (!DrawableUtils.canSafelyMutateDrawable(drawable) || drawable.mutate() == drawable) {
            boolean z6 = tintInfo.mHasTintList;
            if (z6 || tintInfo.mHasTintMode) {
                drawable.setColorFilter(createTintFilter(z6 ? tintInfo.mTintList : null, tintInfo.mHasTintMode ? tintInfo.mTintMode : DEFAULT_MODE, iArr));
            } else {
                drawable.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }
}
