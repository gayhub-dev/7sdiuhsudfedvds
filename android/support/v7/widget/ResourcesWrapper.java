package android.support.v7.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
class ResourcesWrapper extends Resources {
    private final Resources mResources;

    public ResourcesWrapper(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mResources = resources;
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i7) {
        return this.mResources.getAnimation(i7);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i7) {
        return this.mResources.getBoolean(i7);
    }

    @Override // android.content.res.Resources
    public int getColor(int i7) {
        return this.mResources.getColor(i7);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i7) {
        return this.mResources.getColorStateList(i7);
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.mResources.getConfiguration();
    }

    @Override // android.content.res.Resources
    public float getDimension(int i7) {
        return this.mResources.getDimension(i7);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i7) {
        return this.mResources.getDimensionPixelOffset(i7);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i7) {
        return this.mResources.getDimensionPixelSize(i7);
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.mResources.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i7) {
        return this.mResources.getDrawable(i7);
    }

    @Override // android.content.res.Resources
    @RequiresApi(15)
    public Drawable getDrawableForDensity(int i7, int i8) {
        return this.mResources.getDrawableForDensity(i7, i8);
    }

    @Override // android.content.res.Resources
    public float getFraction(int i7, int i8, int i9) {
        return this.mResources.getFraction(i7, i8, i9);
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.mResources.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i7) {
        return this.mResources.getIntArray(i7);
    }

    @Override // android.content.res.Resources
    public int getInteger(int i7) {
        return this.mResources.getInteger(i7);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i7) {
        return this.mResources.getLayout(i7);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i7) {
        return this.mResources.getMovie(i7);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i7, int i8, Object... objArr) {
        return this.mResources.getQuantityString(i7, i8, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int i7, int i8) {
        return this.mResources.getQuantityText(i7, i8);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i7) {
        return this.mResources.getResourceEntryName(i7);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i7) {
        return this.mResources.getResourceName(i7);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i7) {
        return this.mResources.getResourcePackageName(i7);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i7) {
        return this.mResources.getResourceTypeName(i7);
    }

    @Override // android.content.res.Resources
    public String getString(int i7) {
        return this.mResources.getString(i7);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i7) {
        return this.mResources.getStringArray(i7);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i7) {
        return this.mResources.getText(i7);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i7) {
        return this.mResources.getTextArray(i7);
    }

    @Override // android.content.res.Resources
    public void getValue(int i7, TypedValue typedValue, boolean z6) throws Resources.NotFoundException {
        this.mResources.getValue(i7, typedValue, z6);
    }

    @Override // android.content.res.Resources
    @RequiresApi(15)
    public void getValueForDensity(int i7, int i8, TypedValue typedValue, boolean z6) throws Resources.NotFoundException {
        this.mResources.getValueForDensity(i7, i8, typedValue, z6);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int i7) {
        return this.mResources.getXml(i7);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.mResources.obtainAttributes(attributeSet, iArr);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int i7) {
        return this.mResources.obtainTypedArray(i7);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i7) {
        return this.mResources.openRawResource(i7);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i7) {
        return this.mResources.openRawResourceFd(i7);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) throws XmlPullParserException {
        this.mResources.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) throws XmlPullParserException, IOException {
        this.mResources.parseBundleExtras(xmlResourceParser, bundle);
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.mResources;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    @RequiresApi(21)
    public Drawable getDrawable(int i7, Resources.Theme theme) {
        return this.mResources.getDrawable(i7, theme);
    }

    @Override // android.content.res.Resources
    @RequiresApi(21)
    public Drawable getDrawableForDensity(int i7, int i8, Resources.Theme theme) {
        return this.mResources.getDrawableForDensity(i7, i8, theme);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i7, int i8) {
        return this.mResources.getQuantityString(i7, i8);
    }

    @Override // android.content.res.Resources
    public String getString(int i7, Object... objArr) {
        return this.mResources.getString(i7, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i7, CharSequence charSequence) {
        return this.mResources.getText(i7, charSequence);
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z6) throws Resources.NotFoundException {
        this.mResources.getValue(str, typedValue, z6);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i7, TypedValue typedValue) {
        return this.mResources.openRawResource(i7, typedValue);
    }
}
