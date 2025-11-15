package p062h2;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;
import java.util.List;

/* compiled from: MyAnimationDrawable.java */
/* renamed from: h2.c */
/* loaded from: classes.dex */
public class C1084c {

    /* compiled from: MyAnimationDrawable.java */
    /* renamed from: h2.c$a */
    public class a implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ ImageView f2193e;

        /* renamed from: f */
        public final /* synthetic */ c f2194f;

        /* renamed from: g */
        public final /* synthetic */ int f2195g;

        /* renamed from: h */
        public final /* synthetic */ List f2196h;

        /* renamed from: i */
        public final /* synthetic */ Runnable f2197i;

        public a(ImageView imageView, c cVar, int i7, List list, Runnable runnable) {
            this.f2193e = imageView;
            this.f2194f = cVar;
            this.f2195g = i7;
            this.f2196h = list;
            this.f2197i = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2193e.getDrawable() == this.f2194f.f2204c) {
                if (this.f2195g + 1 >= this.f2196h.size()) {
                    Runnable runnable = this.f2197i;
                    if (runnable != null) {
                        runnable.run();
                        return;
                    }
                    return;
                }
                c cVar = (c) this.f2196h.get(this.f2195g + 1);
                if (cVar.f2205d) {
                    C1084c.m1174a(this.f2196h, this.f2193e, this.f2197i, this.f2195g + 1);
                } else {
                    cVar.f2205d = true;
                }
            }
        }
    }

    /* compiled from: MyAnimationDrawable.java */
    /* renamed from: h2.c$b */
    public class b implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ List f2198e;

        /* renamed from: f */
        public final /* synthetic */ int f2199f;

        /* renamed from: g */
        public final /* synthetic */ ImageView f2200g;

        /* renamed from: h */
        public final /* synthetic */ Runnable f2201h;

        public b(List list, int i7, ImageView imageView, Runnable runnable) {
            this.f2198e = list;
            this.f2199f = i7;
            this.f2200g = imageView;
            this.f2201h = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar = (c) this.f2198e.get(this.f2199f + 1);
            Resources resources = this.f2200g.getContext().getResources();
            byte[] bArr = cVar.f2202a;
            cVar.f2204c = new BitmapDrawable(resources, BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            if (cVar.f2205d) {
                C1084c.m1174a(this.f2198e, this.f2200g, this.f2201h, this.f2199f + 1);
            } else {
                cVar.f2205d = true;
            }
        }
    }

    /* compiled from: MyAnimationDrawable.java */
    /* renamed from: h2.c$c */
    public static class c {

        /* renamed from: a */
        public byte[] f2202a;

        /* renamed from: b */
        public int f2203b;

        /* renamed from: c */
        public Drawable f2204c;

        /* renamed from: d */
        public boolean f2205d = false;
    }

    /* compiled from: MyAnimationDrawable.java */
    /* renamed from: h2.c$d */
    public interface d {
    }

    /* renamed from: a */
    public static void m1174a(List<c> list, ImageView imageView, Runnable runnable, int i7) {
        c cVar = list.get(i7);
        if (i7 == 0) {
            Resources resources = imageView.getContext().getResources();
            byte[] bArr = cVar.f2202a;
            cVar.f2204c = new BitmapDrawable(resources, BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
        } else {
            c cVar2 = list.get(i7 - 1);
            ((BitmapDrawable) cVar2.f2204c).getBitmap().recycle();
            cVar2.f2204c = null;
            cVar2.f2205d = false;
        }
        imageView.setImageDrawable(cVar.f2204c);
        new Handler().postDelayed(new a(imageView, cVar, i7, list, runnable), cVar.f2203b);
        if (i7 + 1 < list.size()) {
            new Thread(new b(list, i7, imageView, runnable)).run();
        }
    }
}
