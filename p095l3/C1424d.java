package p095l3;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.easefun.povplayer.core.video.PolyvVideoView;
import okhttp3.internal.cache.DiskLruCache;
import p103m3.InterfaceC1469i;
import p103m3.InterfaceC1470j;
import p103m3.InterfaceC1471k;
import p103m3.InterfaceC1472l;

/* compiled from: PolyvVideoView.java */
/* renamed from: l3.d */
/* loaded from: classes.dex */
public class C1424d extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a */
    public final /* synthetic */ PolyvVideoView f4165a;

    public C1424d(PolyvVideoView polyvVideoView) {
        this.f4165a = polyvVideoView;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.f4165a.m623p();
        this.f4165a.f1153G = 0;
        return false;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
        return false;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        int width = this.f4165a.getWidth() / 2;
        int height = this.f4165a.getHeight() / 2;
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        if (x6 > width) {
            if (y6 > height) {
                PolyvVideoView polyvVideoView = this.f4165a;
                polyvVideoView.f1153G = 7;
                InterfaceC1471k interfaceC1471k = polyvVideoView.f1214y;
                if (interfaceC1471k != null) {
                    interfaceC1471k.m1645a(true, false);
                    return;
                }
                return;
            }
            PolyvVideoView polyvVideoView2 = this.f4165a;
            polyvVideoView2.f1153G = 8;
            InterfaceC1472l interfaceC1472l = polyvVideoView2.f1215z;
            if (interfaceC1472l != null) {
                interfaceC1472l.m1646a(true, false);
                return;
            }
            return;
        }
        if (y6 > height) {
            PolyvVideoView polyvVideoView3 = this.f4165a;
            polyvVideoView3.f1153G = 9;
            InterfaceC1469i interfaceC1469i = polyvVideoView3.f1204A;
            if (interfaceC1469i != null) {
                interfaceC1469i.m1643a(true, false);
                return;
            }
            return;
        }
        PolyvVideoView polyvVideoView4 = this.f4165a;
        polyvVideoView4.f1153G = 10;
        InterfaceC1470j interfaceC1470j = polyvVideoView4.f1205B;
        if (interfaceC1470j != null) {
            interfaceC1470j.m1644a(true, false);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) throws ClassNotFoundException {
        int i7;
        int i8;
        int i9;
        int i10;
        if (this.f4165a.f1153G == 0) {
            if (motionEvent.getRawY() <= (this.f4165a.f1154H.getResources().getIdentifier("status_bar_height", "dimen", "android") > 0 ? r5.getDimensionPixelSize(r6) : 0)) {
                return false;
            }
            Resources resources = this.f4165a.f1154H.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            boolean z6 = identifier > 0 ? resources.getBoolean(identifier) : false;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
                if (DiskLruCache.VERSION_1.equals(str)) {
                    z6 = false;
                } else if ("0".equals(str)) {
                    z6 = true;
                }
            } catch (Exception unused) {
            }
            if (z6 && this.f4165a.f1154H.getResources().getConfiguration().orientation == 2 && (this.f4165a.f1154H instanceof Activity)) {
                float x6 = motionEvent.getX() + (this.f4165a.f1154H.getResources().getIdentifier("navigation_bar_height", "dimen", "android") > 0 ? r5.getDimensionPixelSize(r6) : 0);
                ((Activity) this.f4165a.f1154H).getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                if (x6 >= new int[]{r6.widthPixels, r6.heightPixels}[0]) {
                    return false;
                }
            }
        }
        PolyvVideoView polyvVideoView = this.f4165a;
        if (polyvVideoView.f1149C == 0.0f || polyvVideoView.f1150D == 0.0f) {
            polyvVideoView.f1149C = motionEvent.getX();
            this.f4165a.f1150D = motionEvent.getY();
        }
        int measuredWidth = ((ViewGroup) this.f4165a.getParent()).getMeasuredWidth();
        int measuredHeight = ((ViewGroup) this.f4165a.getParent()).getMeasuredHeight();
        int i11 = measuredWidth / 2;
        float fAbs = Math.abs(this.f4165a.f1149C - motionEvent2.getX());
        double d7 = measuredWidth * 0.01d;
        boolean z7 = ((double) Math.abs(this.f4165a.f1150D - motionEvent2.getY())) > ((double) measuredHeight) * 0.05d;
        double d8 = fAbs;
        boolean z8 = d8 > d7;
        int iMax = Math.max(1, (int) (d8 / d7));
        double d9 = f8;
        double dSqrt = d9 / Math.sqrt(Math.pow(d9, 2.0d) + Math.pow(f7, 2.0d));
        if (Math.abs(dSqrt) > 0.7853981633974483d && z7) {
            PolyvVideoView polyvVideoView2 = this.f4165a;
            float f9 = polyvVideoView2.f1149C;
            float f10 = i11;
            if ((f9 <= f10 || !((i10 = polyvVideoView2.f1153G) == 0 || i10 == 2 || i10 == 1)) && (f9 > f10 || !((i9 = polyvVideoView2.f1153G) == 2 || i9 == 1))) {
                if ((f9 <= f10 && ((i8 = polyvVideoView2.f1153G) == 0 || i8 == 4 || i8 == 3)) || (f9 > f10 && ((i7 = polyvVideoView2.f1153G) == 4 || i7 == 3))) {
                    if (polyvVideoView2.f1150D > motionEvent2.getY()) {
                        PolyvVideoView polyvVideoView3 = this.f4165a;
                        polyvVideoView3.f1153G = 4;
                        polyvVideoView3.m625r(true, false);
                    } else {
                        PolyvVideoView polyvVideoView4 = this.f4165a;
                        polyvVideoView4.f1153G = 3;
                        polyvVideoView4.m624q(true, false);
                    }
                }
            } else if (polyvVideoView2.f1150D > motionEvent2.getY()) {
                PolyvVideoView polyvVideoView5 = this.f4165a;
                polyvVideoView5.f1153G = 2;
                polyvVideoView5.m627t(true, false);
            } else {
                PolyvVideoView polyvVideoView6 = this.f4165a;
                polyvVideoView6.f1153G = 1;
                polyvVideoView6.m626s(true, false);
            }
            this.f4165a.f1149C = motionEvent2.getX();
            this.f4165a.f1150D = motionEvent2.getY();
        } else if (Math.abs(dSqrt) <= 0.7853981633974483d && z8) {
            PolyvVideoView polyvVideoView7 = this.f4165a;
            int i12 = polyvVideoView7.f1153G;
            if (i12 == 0 || i12 == 5 || i12 == 6) {
                if (polyvVideoView7.f1149C > motionEvent2.getX()) {
                    PolyvVideoView polyvVideoView8 = this.f4165a;
                    polyvVideoView8.f1153G = 5;
                    polyvVideoView8.m628u(true, iMax, false);
                } else {
                    PolyvVideoView polyvVideoView9 = this.f4165a;
                    polyvVideoView9.f1153G = 6;
                    polyvVideoView9.m629v(true, iMax, false);
                }
            }
            this.f4165a.f1149C = motionEvent2.getX();
            this.f4165a.f1150D = motionEvent2.getY();
        }
        return false;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.f4165a.m572n();
        this.f4165a.m622R();
        this.f4165a.f1153G = 0;
        return false;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (!this.f4165a.f1161O.m594w()) {
            return false;
        }
        this.f4165a.f1161O.m572n();
        return false;
    }
}
