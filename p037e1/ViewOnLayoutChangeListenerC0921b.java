package p037e1;

import android.text.TextUtils;
import android.view.View;
import com.cctv.cctvplayer.CCTVVideoMediaController;
import p009b.C0413b;

/* compiled from: CCTVVideoMediaController.java */
/* renamed from: e1.b */
/* loaded from: classes.dex */
public class ViewOnLayoutChangeListenerC0921b implements View.OnLayoutChangeListener {

    /* renamed from: a */
    public final /* synthetic */ CCTVVideoMediaController f1685a;

    public ViewOnLayoutChangeListenerC0921b(CCTVVideoMediaController cCTVVideoMediaController) {
        this.f1685a = cCTVVideoMediaController;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        if (this.f1685a.f484z != view.getWidth()) {
            this.f1685a.f484z = view.getWidth();
            StringBuilder sbM112a = C0413b.m112a("进度条View改变了： ");
            sbM112a.append(this.f1685a.f484z);
            TextUtils.isEmpty(sbM112a.toString());
            CCTVVideoMediaController cCTVVideoMediaController = this.f1685a;
            cCTVVideoMediaController.setSeekBarThumbPosition(cCTVVideoMediaController.f481w.getProgress());
        }
    }
}
