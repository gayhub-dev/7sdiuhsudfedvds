package p079j3;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import com.easefun.povplayer.core.R$id;

/* compiled from: TableLayoutBinder.java */
/* renamed from: j3.f */
/* loaded from: classes.dex */
public class C1203f {

    /* renamed from: a */
    public Context f2634a;

    /* renamed from: b */
    public TableLayout f2635b;

    /* compiled from: TableLayoutBinder.java */
    /* renamed from: j3.f$b */
    public static class b {

        /* renamed from: a */
        public TextView f2636a;

        /* renamed from: b */
        public TextView f2637b;

        public b() {
        }

        public b(a aVar) {
        }
    }

    public C1203f(Context context, TableLayout tableLayout) {
        this.f2634a = context;
        this.f2635b = tableLayout;
    }

    /* renamed from: a */
    public b m1430a(View view) {
        b bVar = (b) view.getTag();
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(null);
        bVar2.f2636a = (TextView) view.findViewById(R$id.name);
        bVar2.f2637b = (TextView) view.findViewById(R$id.value);
        view.setTag(bVar2);
        return bVar2;
    }
}
