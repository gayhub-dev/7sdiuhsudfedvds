package p062h2;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Handler;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;
import p023c5.C0519a;
import p062h2.C1084c;

/* compiled from: MyAnimationDrawable.java */
/* renamed from: h2.b */
/* loaded from: classes.dex */
public class RunnableC1083b implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ Context f2188e;

    /* renamed from: f */
    public final /* synthetic */ int f2189f;

    /* renamed from: g */
    public final /* synthetic */ C1084c.d f2190g;

    /* compiled from: MyAnimationDrawable.java */
    /* renamed from: h2.b$a */
    public class a implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ ArrayList f2191e;

        public a(ArrayList arrayList) {
            this.f2191e = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            C1084c.d dVar = RunnableC1083b.this.f2190g;
            if (dVar != null) {
                ArrayList arrayList = this.f2191e;
                C1082a c1082a = (C1082a) dVar;
                Runnable runnable = c1082a.f2185a;
                if (runnable != null) {
                    runnable.run();
                }
                C1084c.m1174a(arrayList, c1082a.f2186b, c1082a.f2187c, 0);
            }
        }
    }

    public RunnableC1083b(Context context, int i7, C1084c.d dVar) {
        this.f2188e = context;
        this.f2189f = i7;
        this.f2190g = dVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Resources.NotFoundException, NumberFormatException {
        ArrayList arrayList = new ArrayList();
        XmlResourceParser xml = this.f2188e.getResources().getXml(this.f2189f);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType != 0 && eventType == 2 && xml.getName().equals("item")) {
                    byte[] bArrM333a = null;
                    int attributeIntValue = 1000;
                    for (int i7 = 0; i7 < xml.getAttributeCount(); i7++) {
                        if (xml.getAttributeName(i7).equals("drawable")) {
                            bArrM333a = C0519a.m333a(this.f2188e.getResources().openRawResource(Integer.parseInt(xml.getAttributeValue(i7).substring(1))));
                        } else if (xml.getAttributeName(i7).equals("duration")) {
                            attributeIntValue = xml.getAttributeIntValue(i7, 1000);
                        }
                    }
                    C1084c.c cVar = new C1084c.c();
                    cVar.f2202a = bArrM333a;
                    cVar.f2203b = attributeIntValue;
                    arrayList.add(cVar);
                }
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
        }
        new Handler(this.f2188e.getMainLooper()).post(new a(arrayList));
    }
}
