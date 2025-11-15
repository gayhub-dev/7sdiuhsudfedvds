package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Preconditions;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ActivityChooserModel;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    public static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final String EXTRA_INT1 = "int1";
    private static final String EXTRA_INT2 = "int2";
    private static final String EXTRA_OBJ = "obj";
    private static final String EXTRA_TINT_LIST = "tint_list";
    private static final String EXTRA_TINT_MODE = "tint_mode";
    private static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.9166667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_UNKNOWN = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public byte[] mData;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt1;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mInt2;
    public Object mObj1;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Parcelable mParcelable;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ColorStateList mTintList = null;
    public PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String mTintModeStr;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mType;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface IconType {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public IconCompat() {
    }

    @Nullable
    public static IconCompat createFromBundle(@NonNull Bundle bundle) {
        int i7 = bundle.getInt("type");
        IconCompat iconCompat = new IconCompat(i7);
        iconCompat.mInt1 = bundle.getInt(EXTRA_INT1);
        iconCompat.mInt2 = bundle.getInt(EXTRA_INT2);
        if (bundle.containsKey(EXTRA_TINT_LIST)) {
            iconCompat.mTintList = (ColorStateList) bundle.getParcelable(EXTRA_TINT_LIST);
        }
        if (bundle.containsKey(EXTRA_TINT_MODE)) {
            iconCompat.mTintMode = PorterDuff.Mode.valueOf(bundle.getString(EXTRA_TINT_MODE));
        }
        if (i7 == -1 || i7 == 1) {
            iconCompat.mObj1 = bundle.getParcelable(EXTRA_OBJ);
        } else if (i7 == 2) {
            iconCompat.mObj1 = bundle.getString(EXTRA_OBJ);
        } else if (i7 != 3) {
            if (i7 != 4) {
                if (i7 != 5) {
                    return null;
                }
                iconCompat.mObj1 = bundle.getParcelable(EXTRA_OBJ);
            }
            iconCompat.mObj1 = bundle.getString(EXTRA_OBJ);
        } else {
            iconCompat.mObj1 = bundle.getByteArray(EXTRA_OBJ);
        }
        return iconCompat;
    }

    @RequiresApi(23)
    @Nullable
    public static IconCompat createFromIcon(@NonNull Context context, @NonNull Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            String resPackage = getResPackage(icon);
            try {
                return createWithResource(getResources(context, resPackage), resPackage, getResId(icon));
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        }
        if (type == 4) {
            return createWithContentUri(getUri(icon));
        }
        IconCompat iconCompat = new IconCompat(-1);
        iconCompat.mObj1 = icon;
        return iconCompat;
    }

    @VisibleForTesting
    public static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap, boolean z6) {
        int iMin = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * DEFAULT_VIEW_PORT_SCALE);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(3);
        float f7 = iMin;
        float f8 = 0.5f * f7;
        float f9 = ICON_DIAMETER_FACTOR * f8;
        if (z6) {
            float f10 = BLUR_FACTOR * f7;
            paint.setColor(0);
            paint.setShadowLayer(f10, 0.0f, f7 * KEY_SHADOW_OFFSET_FACTOR, 1023410176);
            canvas.drawCircle(f8, f8, f9, paint);
            paint.setShadowLayer(f10, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f8, f8, f9, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - iMin)) / 2, (-(bitmap.getHeight() - iMin)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f8, f8, f9, paint);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(5);
        iconCompat.mObj1 = bitmap;
        return iconCompat;
    }

    public static IconCompat createWithBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.mObj1 = bitmap;
        return iconCompat;
    }

    public static IconCompat createWithContentUri(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }
        IconCompat iconCompat = new IconCompat(4);
        iconCompat.mObj1 = str;
        return iconCompat;
    }

    public static IconCompat createWithData(byte[] bArr, int i7, int i8) {
        if (bArr == null) {
            throw new IllegalArgumentException("Data must not be null.");
        }
        IconCompat iconCompat = new IconCompat(3);
        iconCompat.mObj1 = bArr;
        iconCompat.mInt1 = i7;
        iconCompat.mInt2 = i8;
        return iconCompat;
    }

    public static IconCompat createWithResource(Context context, @DrawableRes int i7) {
        if (context != null) {
            return createWithResource(context.getResources(), context.getPackageName(), i7);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private static Resources getResources(Context context, String str) throws PackageManager.NameNotFoundException {
        if ("android".equals(str)) {
            return Resources.getSystem();
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return packageManager.getResourcesForApplication(applicationInfo);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            String.format("Unable to find pkg=%s for icon", str);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.drawable.Drawable loadDrawableInner(android.content.Context r7) throws java.io.FileNotFoundException {
        /*
            r6 = this;
            int r0 = r6.mType
            r1 = 1
            if (r0 == r1) goto Lcf
            r2 = 0
            r3 = 0
            r4 = 2
            if (r0 == r4) goto L9e
            r1 = 3
            if (r0 == r1) goto L88
            r1 = 4
            if (r0 == r1) goto L27
            r1 = 5
            if (r0 == r1) goto L15
            goto Lce
        L15:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            java.lang.Object r1 = r6.mObj1
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            android.graphics.Bitmap r1 = createLegacyIconFromAdaptiveIcon(r1, r2)
            r0.<init>(r7, r1)
            return r0
        L27:
            java.lang.Object r0 = r6.mObj1
            java.lang.String r0 = (java.lang.String) r0
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.String r1 = r0.getScheme()
            java.lang.String r2 = "content"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L61
            java.lang.String r2 = "file"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L44
            goto L61
        L44:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L53
            java.io.File r2 = new java.io.File     // Catch: java.io.FileNotFoundException -> L53
            java.lang.Object r4 = r6.mObj1     // Catch: java.io.FileNotFoundException -> L53
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.io.FileNotFoundException -> L53
            r2.<init>(r4)     // Catch: java.io.FileNotFoundException -> L53
            r1.<init>(r2)     // Catch: java.io.FileNotFoundException -> L53
            goto L78
        L53:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to load image from path: "
            r1.append(r2)
            r1.append(r0)
            goto L77
        L61:
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch: java.lang.Exception -> L6a
            java.io.InputStream r1 = r1.openInputStream(r0)     // Catch: java.lang.Exception -> L6a
            goto L78
        L6a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to load image from URI: "
            r1.append(r2)
            r1.append(r0)
        L77:
            r1 = r3
        L78:
            if (r1 == 0) goto Lce
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r1)
            r0.<init>(r7, r1)
            return r0
        L88:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            java.lang.Object r1 = r6.mObj1
            byte[] r1 = (byte[]) r1
            int r2 = r6.mInt1
            int r3 = r6.mInt2
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r1, r2, r3)
            r0.<init>(r7, r1)
            return r0
        L9e:
            java.lang.String r0 = r6.getResPackage()
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 == 0) goto Lac
            java.lang.String r0 = r7.getPackageName()
        Lac:
            android.content.res.Resources r0 = getResources(r7, r0)
            int r5 = r6.mInt1     // Catch: java.lang.RuntimeException -> Lbb
            android.content.res.Resources$Theme r7 = r7.getTheme()     // Catch: java.lang.RuntimeException -> Lbb
            android.graphics.drawable.Drawable r7 = android.support.v4.content.res.ResourcesCompat.getDrawable(r0, r5, r7)     // Catch: java.lang.RuntimeException -> Lbb
            return r7
        Lbb:
            java.lang.Object[] r7 = new java.lang.Object[r4]
            int r0 = r6.mInt1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7[r2] = r0
            java.lang.Object r0 = r6.mObj1
            r7[r1] = r0
            java.lang.String r0 = "Unable to load resource 0x%08x from pkg=%s"
            java.lang.String.format(r0, r7)
        Lce:
            return r3
        Lcf:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r7 = r7.getResources()
            java.lang.Object r1 = r6.mObj1
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            r0.<init>(r7, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.loadDrawableInner(android.content.Context):android.graphics.drawable.Drawable");
    }

    private static String typeToString(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? "UNKNOWN" : "BITMAP_MASKABLE" : "URI" : "DATA" : "RESOURCE" : "BITMAP";
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(@NonNull Intent intent, @Nullable Drawable drawable, @NonNull Context context) throws PackageManager.NameNotFoundException {
        Bitmap bitmapCopy;
        checkResource(context);
        int i7 = this.mType;
        if (i7 == 1) {
            bitmapCopy = (Bitmap) this.mObj1;
            if (drawable != null) {
                bitmapCopy = bitmapCopy.copy(bitmapCopy.getConfig(), true);
            }
        } else if (i7 == 2) {
            try {
                Context contextCreatePackageContext = context.createPackageContext(getResPackage(), 0);
                if (drawable == null) {
                    intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(contextCreatePackageContext, this.mInt1));
                    return;
                }
                Drawable drawable2 = ContextCompat.getDrawable(contextCreatePackageContext, this.mInt1);
                if (drawable2.getIntrinsicWidth() <= 0 || drawable2.getIntrinsicHeight() <= 0) {
                    int launcherLargeIconSize = ((ActivityManager) contextCreatePackageContext.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).getLauncherLargeIconSize();
                    bitmapCopy = Bitmap.createBitmap(launcherLargeIconSize, launcherLargeIconSize, Bitmap.Config.ARGB_8888);
                } else {
                    bitmapCopy = Bitmap.createBitmap(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                }
                drawable2.setBounds(0, 0, bitmapCopy.getWidth(), bitmapCopy.getHeight());
                drawable2.draw(new Canvas(bitmapCopy));
            } catch (PackageManager.NameNotFoundException e7) {
                StringBuilder sbM112a = C0413b.m112a("Can't find package ");
                sbM112a.append(this.mObj1);
                throw new IllegalArgumentException(sbM112a.toString(), e7);
            }
        } else {
            if (i7 != 5) {
                throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
            }
            bitmapCopy = createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
        }
        if (drawable != null) {
            int width = bitmapCopy.getWidth();
            int height = bitmapCopy.getHeight();
            drawable.setBounds(width / 2, height / 2, width, height);
            drawable.draw(new Canvas(bitmapCopy));
        }
        intent.putExtra("android.intent.extra.shortcut.ICON", bitmapCopy);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void checkResource(Context context) {
        if (this.mType == 2) {
            String str = (String) this.mObj1;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split(ServiceReference.DELIMITER, -1)[0];
                String str4 = str2.split(ServiceReference.DELIMITER, -1)[1];
                String str5 = str.split(":", -1)[0];
                int identifier = getResources(context, str5).getIdentifier(str4, str3, str5);
                if (this.mInt1 != identifier) {
                    this.mInt1 = identifier;
                }
            }
        }
    }

    @IdRes
    public int getResId() {
        int i7 = this.mType;
        if (i7 == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResId((Icon) this.mObj1);
        }
        if (i7 == 2) {
            return this.mInt1;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    @NonNull
    public String getResPackage() {
        int i7 = this.mType;
        if (i7 == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResPackage((Icon) this.mObj1);
        }
        if (i7 == 2) {
            return ((String) this.mObj1).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int getType() {
        int i7 = this.mType;
        return (i7 != -1 || Build.VERSION.SDK_INT < 23) ? i7 : getType((Icon) this.mObj1);
    }

    @NonNull
    public Uri getUri() {
        return (this.mType != -1 || Build.VERSION.SDK_INT < 23) ? Uri.parse((String) this.mObj1) : getUri((Icon) this.mObj1);
    }

    public Drawable loadDrawable(Context context) throws FileNotFoundException {
        checkResource(context);
        if (Build.VERSION.SDK_INT >= 23) {
            return toIcon().loadDrawable(context);
        }
        Drawable drawableLoadDrawableInner = loadDrawableInner(context);
        if (drawableLoadDrawableInner != null && (this.mTintList != null || this.mTintMode != DEFAULT_TINT_MODE)) {
            drawableLoadDrawableInner.mutate();
            DrawableCompat.setTintList(drawableLoadDrawableInner, this.mTintList);
            DrawableCompat.setTintMode(drawableLoadDrawableInner, this.mTintMode);
        }
        return drawableLoadDrawableInner;
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPostParceling() {
        this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
        int i7 = this.mType;
        if (i7 == -1) {
            Parcelable parcelable = this.mParcelable;
            if (parcelable == null) {
                throw new IllegalArgumentException("Invalid icon");
            }
            this.mObj1 = parcelable;
            return;
        }
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 == 3) {
                    this.mObj1 = this.mData;
                    return;
                } else if (i7 != 4) {
                    if (i7 != 5) {
                        return;
                    }
                }
            }
            this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
            return;
        }
        Parcelable parcelable2 = this.mParcelable;
        if (parcelable2 != null) {
            this.mObj1 = parcelable2;
            return;
        }
        byte[] bArr = this.mData;
        this.mObj1 = bArr;
        this.mType = 3;
        this.mInt1 = 0;
        this.mInt2 = bArr.length;
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPreParceling(boolean z6) {
        this.mTintModeStr = this.mTintMode.name();
        int i7 = this.mType;
        if (i7 == -1) {
            if (z6) {
                throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
            }
            this.mParcelable = (Parcelable) this.mObj1;
            return;
        }
        if (i7 != 1) {
            if (i7 == 2) {
                this.mData = ((String) this.mObj1).getBytes(Charset.forName("UTF-16"));
                return;
            }
            if (i7 == 3) {
                this.mData = (byte[]) this.mObj1;
                return;
            } else if (i7 == 4) {
                this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                return;
            } else if (i7 != 5) {
                return;
            }
        }
        if (!z6) {
            this.mParcelable = (Parcelable) this.mObj1;
            return;
        }
        Bitmap bitmap = (Bitmap) this.mObj1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
        this.mData = byteArrayOutputStream.toByteArray();
    }

    public IconCompat setTint(@ColorInt int i7) {
        return setTintList(ColorStateList.valueOf(i7));
    }

    public IconCompat setTintList(ColorStateList colorStateList) {
        this.mTintList = colorStateList;
        return this;
    }

    public IconCompat setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i7 = this.mType;
        if (i7 == -1) {
            bundle.putParcelable(EXTRA_OBJ, (Parcelable) this.mObj1);
        } else if (i7 == 1) {
            bundle.putParcelable(EXTRA_OBJ, (Bitmap) this.mObj1);
        } else if (i7 == 2) {
            bundle.putString(EXTRA_OBJ, (String) this.mObj1);
        } else if (i7 != 3) {
            if (i7 != 4) {
                if (i7 != 5) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                bundle.putParcelable(EXTRA_OBJ, (Bitmap) this.mObj1);
            }
            bundle.putString(EXTRA_OBJ, (String) this.mObj1);
        } else {
            bundle.putByteArray(EXTRA_OBJ, (byte[]) this.mObj1);
        }
        bundle.putInt("type", this.mType);
        bundle.putInt(EXTRA_INT1, this.mInt1);
        bundle.putInt(EXTRA_INT2, this.mInt2);
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            bundle.putParcelable(EXTRA_TINT_LIST, colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            bundle.putString(EXTRA_TINT_MODE, mode.name());
        }
        return bundle;
    }

    @RequiresApi(23)
    public Icon toIcon() {
        Icon iconCreateWithBitmap;
        int i7 = this.mType;
        if (i7 == -1) {
            return (Icon) this.mObj1;
        }
        if (i7 == 1) {
            iconCreateWithBitmap = Icon.createWithBitmap((Bitmap) this.mObj1);
        } else if (i7 == 2) {
            iconCreateWithBitmap = Icon.createWithResource(getResPackage(), this.mInt1);
        } else if (i7 == 3) {
            iconCreateWithBitmap = Icon.createWithData((byte[]) this.mObj1, this.mInt1, this.mInt2);
        } else if (i7 == 4) {
            iconCreateWithBitmap = Icon.createWithContentUri((String) this.mObj1);
        } else {
            if (i7 != 5) {
                throw new IllegalArgumentException("Unknown type");
            }
            iconCreateWithBitmap = Build.VERSION.SDK_INT >= 26 ? Icon.createWithAdaptiveBitmap((Bitmap) this.mObj1) : Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
        }
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            iconCreateWithBitmap.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            iconCreateWithBitmap.setTintMode(mode);
        }
        return iconCreateWithBitmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r4 = this;
            int r0 = r4.mType
            r1 = -1
            if (r0 != r1) goto Lc
            java.lang.Object r0 = r4.mObj1
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        Lc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Icon(typ="
            r0.<init>(r1)
            int r1 = r4.mType
            java.lang.String r1 = typeToString(r1)
            r0.append(r1)
            int r1 = r4.mType
            r2 = 1
            if (r1 == r2) goto L7a
            r3 = 2
            if (r1 == r3) goto L52
            r2 = 3
            if (r1 == r2) goto L39
            r2 = 4
            if (r1 == r2) goto L2e
            r2 = 5
            if (r1 == r2) goto L7a
            goto L9a
        L2e:
            java.lang.String r1 = " uri="
            r0.append(r1)
            java.lang.Object r1 = r4.mObj1
            r0.append(r1)
            goto L9a
        L39:
            java.lang.String r1 = " len="
            r0.append(r1)
            int r1 = r4.mInt1
            r0.append(r1)
            int r1 = r4.mInt2
            if (r1 == 0) goto L9a
            java.lang.String r1 = " off="
            r0.append(r1)
            int r1 = r4.mInt2
            r0.append(r1)
            goto L9a
        L52:
            java.lang.String r1 = " pkg="
            r0.append(r1)
            java.lang.String r1 = r4.getResPackage()
            r0.append(r1)
            java.lang.String r1 = " id="
            r0.append(r1)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r2 = 0
            int r3 = r4.getResId()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "0x%08x"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.append(r1)
            goto L9a
        L7a:
            java.lang.String r1 = " size="
            r0.append(r1)
            java.lang.Object r1 = r4.mObj1
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getWidth()
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            java.lang.Object r1 = r4.mObj1
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getHeight()
            r0.append(r1)
        L9a:
            android.content.res.ColorStateList r1 = r4.mTintList
            if (r1 == 0) goto La8
            java.lang.String r1 = " tint="
            r0.append(r1)
            android.content.res.ColorStateList r1 = r4.mTintList
            r0.append(r1)
        La8:
            android.graphics.PorterDuff$Mode r1 = r4.mTintMode
            android.graphics.PorterDuff$Mode r2 = android.support.v4.graphics.drawable.IconCompat.DEFAULT_TINT_MODE
            if (r1 == r2) goto Lb8
            java.lang.String r1 = " mode="
            r0.append(r1)
            android.graphics.PorterDuff$Mode r1 = r4.mTintMode
            r0.append(r1)
        Lb8:
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.IconCompat.toString():java.lang.String");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static IconCompat createWithResource(Resources resources, String str, @DrawableRes int i7) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if (i7 != 0) {
            IconCompat iconCompat = new IconCompat(2);
            iconCompat.mInt1 = i7;
            if (resources != null) {
                try {
                    iconCompat.mObj1 = resources.getResourceName(i7);
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.mObj1 = str;
            }
            return iconCompat;
        }
        throw new IllegalArgumentException("Drawable resource ID must not be 0");
    }

    @RequiresApi(23)
    private static int getType(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            return -1;
        } catch (NoSuchMethodException unused2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to get icon type ");
            sb2.append(icon);
            return -1;
        } catch (InvocationTargetException unused3) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unable to get icon type ");
            sb3.append(icon);
            return -1;
        }
    }

    private IconCompat(int i7) {
        this.mType = i7;
    }

    public static IconCompat createWithContentUri(Uri uri) {
        if (uri != null) {
            return createWithContentUri(uri.toString());
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    @RequiresApi(23)
    @Nullable
    private static Uri getUri(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    @DrawableRes
    @RequiresApi(23)
    @IdRes
    private static int getResId(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return 0;
        }
    }

    @RequiresApi(23)
    @Nullable
    private static String getResPackage(@NonNull Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    @RequiresApi(23)
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static IconCompat createFromIcon(@NonNull Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            return createWithResource(null, getResPackage(icon), getResId(icon));
        }
        if (type != 4) {
            IconCompat iconCompat = new IconCompat(-1);
            iconCompat.mObj1 = icon;
            return iconCompat;
        }
        return createWithContentUri(getUri(icon));
    }
}
