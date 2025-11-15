package p043f;

import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import p044f0.C0991b;
import p068i0.C1137c;
import p076j0.InterfaceC1182b;
import p084k0.InterfaceC1220f;
import p084k0.InterfaceC1221g;
import p084k0.InterfaceC1224j;
import p093l1.C1415a;
import p142r0.C1816a;
import p162u.C1966j;
import p162u.EnumC1959c;
import p162u.InterfaceC1960d;
import p162u.InterfaceC1968l;
import p183x.InterfaceC2057r;

/* compiled from: Fps.java */
/* renamed from: f.a */
/* loaded from: classes.dex */
public class C0984a implements InterfaceC1960d, InterfaceC1968l, InterfaceC1182b, InterfaceC1220f, InterfaceC1224j {

    /* renamed from: a */
    public final /* synthetic */ int f1816a;

    public C0984a(int i7) {
        this.f1816a = i7;
    }

    /* renamed from: e */
    public static Class m940e(Class cls, String str) throws ClassNotFoundException {
        HashSet hashSet = new HashSet();
        Class<?> cls2 = null;
        ClassNotFoundException classNotFoundException = null;
        for (ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader(); cls2 == null && contextClassLoader != null && hashSet.add(contextClassLoader); contextClassLoader = null) {
            try {
                cls2 = contextClassLoader.loadClass(str);
            } catch (ClassNotFoundException e7) {
                if (classNotFoundException == null) {
                    classNotFoundException = e7;
                }
            }
        }
        for (ClassLoader classLoader = cls.getClassLoader(); cls2 == null && classLoader != null && hashSet.add(classLoader); classLoader = null) {
            try {
                cls2 = classLoader.loadClass(str);
            } catch (ClassNotFoundException e8) {
                if (classNotFoundException == null) {
                    classNotFoundException = e8;
                }
            }
        }
        ClassLoader classLoader2 = C0984a.class.getClassLoader();
        if (cls2 == null && classLoader2 != null && hashSet.add(classLoader2)) {
            try {
                cls2 = Class.forName(str);
            } catch (ClassNotFoundException e9) {
                if (classNotFoundException == null) {
                    classNotFoundException = e9;
                }
            }
        }
        if (cls2 != null) {
            return cls2;
        }
        throw classNotFoundException;
    }

    @Override // p162u.InterfaceC1960d
    /* renamed from: D */
    public boolean mo784D(Object obj, File file, C1966j c1966j) throws Throwable {
        switch (this.f1816a) {
            case 3:
                try {
                    C1816a.m2047b((ByteBuffer) obj, file);
                    break;
                } catch (IOException unused) {
                    Log.isLoggable("ByteBufferEncoder", 3);
                    return false;
                }
            default:
                try {
                    C1816a.m2047b(((C1137c) ((InterfaceC2057r) obj).get()).f2478e.f2489b.f2491a.mo2186g().asReadOnlyBuffer(), file);
                    break;
                } catch (IOException unused2) {
                    Log.isLoggable("GifEncoder", 5);
                    return false;
                }
        }
        return false;
    }

    @Override // p084k0.InterfaceC1220f
    /* renamed from: a */
    public void mo941a(InterfaceC1221g interfaceC1221g) {
        interfaceC1221g.mo1446a();
    }

    @Override // p076j0.InterfaceC1182b
    /* renamed from: b */
    public InterfaceC2057r<byte[]> mo942b(InterfaceC2057r<C1137c> interfaceC2057r) {
        byte[] bArrArray;
        ByteBuffer byteBufferAsReadOnlyBuffer = interfaceC2057r.get().f2478e.f2489b.f2491a.mo2186g().asReadOnlyBuffer();
        int i7 = C1816a.f5280a;
        C1816a.b bVar = (byteBufferAsReadOnlyBuffer.isReadOnly() || !byteBufferAsReadOnlyBuffer.hasArray()) ? null : new C1816a.b(byteBufferAsReadOnlyBuffer.array(), byteBufferAsReadOnlyBuffer.arrayOffset(), byteBufferAsReadOnlyBuffer.limit());
        if (bVar != null && bVar.f5283a == 0 && bVar.f5284b == bVar.f5285c.length) {
            bArrArray = byteBufferAsReadOnlyBuffer.array();
        } else {
            ByteBuffer byteBufferAsReadOnlyBuffer2 = byteBufferAsReadOnlyBuffer.asReadOnlyBuffer();
            byte[] bArr = new byte[byteBufferAsReadOnlyBuffer2.limit()];
            byteBufferAsReadOnlyBuffer2.position(0);
            byteBufferAsReadOnlyBuffer2.get(bArr);
            bArrArray = bArr;
        }
        return new C0991b(bArrArray);
    }

    @Override // p084k0.InterfaceC1220f
    /* renamed from: c */
    public void mo943c(InterfaceC1221g interfaceC1221g) {
    }

    /* renamed from: d */
    public String m944d() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("channel", (Object) C1415a.f4150b);
        jSONObject.put("source", (Object) "application");
        return jSONObject.toJSONString();
    }

    @Override // p162u.InterfaceC1968l
    /* renamed from: s */
    public EnumC1959c mo821s(C1966j c1966j) {
        return EnumC1959c.SOURCE;
    }
}
