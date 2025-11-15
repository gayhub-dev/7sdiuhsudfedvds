package com.cctv.p025tv.app;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.fourthline.cling.model.ServiceReference;
import p043f.C0988e;

/* loaded from: classes.dex */
public class ProxyApplication extends Application {

    /* renamed from: e */
    public String f571e;

    /* renamed from: f */
    public String f572f;

    /* renamed from: g */
    public String f573g;

    /* renamed from: h */
    public Cipher f574h;

    public ProxyApplication() throws InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] bytes = "ZBpeqprrsAOztPv4".getBytes();
        byte[] bytes2 = "TkUd0VbOkze229up".getBytes();
        try {
            this.f574h = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.f574h.init(2, new SecretKeySpec(bytes, "AES"), new IvParameterSpec(bytes2));
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* renamed from: a */
    public final byte[] m400a(File file) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(getApplicationInfo().sourceDir)));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                zipInputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            InputStream inputStream = new ZipFile(file).getInputStream(nextEntry);
            byte[] bArrDoFinal = null;
            if (nextEntry.getName().equals("classes.dex")) {
                byte[] bArr = new byte[1024];
                if (Build.VERSION.SDK_INT >= 33) {
                    byte[] allBytes = inputStream.readAllBytes();
                    try {
                        SecureRandom secureRandom = new SecureRandom();
                        SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec("QN6DtgQetZz91M6A".getBytes()));
                        Cipher cipher = Cipher.getInstance("DES");
                        cipher.init(2, secretKeyGenerateSecret, secureRandom);
                        bArrDoFinal = cipher.doFinal(allBytes);
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                    bArr = bArrDoFinal;
                }
                while (true) {
                    int i7 = inputStream.read(bArr);
                    if (i7 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i7);
                }
            } else {
                byte[] bArr2 = new byte[1024];
                if (Build.VERSION.SDK_INT >= 33) {
                    try {
                        bArrDoFinal = this.f574h.doFinal(inputStream.readAllBytes());
                    } catch (Exception e8) {
                        e8.printStackTrace();
                    }
                    bArr2 = bArrDoFinal;
                }
                while (true) {
                    int i8 = inputStream.read(bArr2);
                    if (i8 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, i8);
                }
            }
            zipInputStream.closeEntry();
        }
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) throws IOException {
        super.attachBaseContext(context);
        try {
            File dir = getDir("demo_odex", 0);
            File dir2 = getDir("demo_lib", 0);
            this.f572f = dir.getAbsolutePath();
            this.f573g = dir2.getAbsolutePath();
            this.f571e = dir.getAbsolutePath() + "/shelldemo.apk";
            File file = new File(this.f571e);
            file.length();
            if (!file.exists()) {
                file.createNewFile();
                m401b(m400a(file));
            }
            WeakReference weakReference = (WeakReference) ((ArrayMap) C0988e.m993s("android.app.ActivityThread", C0988e.m959C("android.app.ActivityThread", "currentActivityThread", new Class[0], new Object[0]), "mPackages")).get(getPackageName());
            DexClassLoader dexClassLoader = new DexClassLoader(this.f571e, this.f572f, this.f573g, (ClassLoader) C0988e.m993s("android.app.LoadedApk", weakReference.get(), "mClassLoader"));
            C0988e.m968L("android.app.LoadedApk", "mClassLoader", weakReference.get(), dexClassLoader);
            StringBuilder sb = new StringBuilder();
            sb.append("classloader:");
            sb.append(dexClassLoader);
        } catch (Exception e7) {
            Log.getStackTraceString(e7);
            e7.printStackTrace();
        }
    }

    /* renamed from: b */
    public final void m401b(byte[] bArr) throws IOException {
        File file = new File(this.f571e);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    zipInputStream.close();
                    return;
                }
                String name = nextEntry.getName();
                if (name.startsWith("lib/") && name.endsWith(".so")) {
                    File file2 = new File(this.f573g + ServiceReference.DELIMITER + name.substring(name.lastIndexOf(47)));
                    file2.createNewFile();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int i7 = zipInputStream.read(bArr2);
                        if (i7 == -1) {
                            break;
                        } else {
                            fileOutputStream2.write(bArr2, 0, i7);
                        }
                    }
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                }
                zipInputStream.closeEntry();
            }
        } catch (IOException e7) {
            throw new RuntimeException(e7);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return super.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return super.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        return super.getTheme();
    }

    @Override // android.app.Application
    public void onCreate() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException, InvocationTargetException {
        String string;
        Bundle bundle;
        Object objInvoke = null;
        try {
            bundle = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e7) {
            Log.getStackTraceString(e7);
            e7.printStackTrace();
            string = null;
        }
        if (bundle == null || !bundle.containsKey("APPLICATION_CLASS_NAME")) {
            return;
        }
        string = bundle.getString("APPLICATION_CLASS_NAME");
        Object objM959C = C0988e.m959C("android.app.ActivityThread", "currentActivityThread", new Class[0], new Object[0]);
        Object objM993s = C0988e.m993s("android.app.ActivityThread", objM959C, "mBoundApplication");
        Object objM993s2 = C0988e.m993s("android.app.ActivityThread$AppBindData", objM993s, "info");
        C0988e.m968L("android.app.LoadedApk", "mApplication", objM993s2, null);
        ((ArrayList) C0988e.m993s("android.app.ActivityThread", objM959C, "mAllApplications")).remove(C0988e.m993s("android.app.ActivityThread", objM959C, "mInitialApplication"));
        ApplicationInfo applicationInfo = (ApplicationInfo) C0988e.m993s("android.app.LoadedApk", objM993s2, "mApplicationInfo");
        ApplicationInfo applicationInfo2 = (ApplicationInfo) C0988e.m993s("android.app.ActivityThread$AppBindData", objM993s, "appInfo");
        applicationInfo.className = string;
        applicationInfo2.className = string;
        try {
            objInvoke = Class.forName("android.app.LoadedApk").getMethod("makeApplication", Boolean.TYPE, Instrumentation.class).invoke(objM993s2, Boolean.FALSE, null);
        } catch (ClassNotFoundException e8) {
            e8.printStackTrace();
        } catch (IllegalAccessException e9) {
            e9.printStackTrace();
        } catch (IllegalArgumentException e10) {
            e10.printStackTrace();
        } catch (NoSuchMethodException e11) {
            e11.printStackTrace();
        } catch (SecurityException e12) {
            e12.printStackTrace();
        } catch (InvocationTargetException e13) {
            e13.printStackTrace();
        }
        Application application = (Application) objInvoke;
        C0988e.m968L("android.app.ActivityThread", "mInitialApplication", objM959C, application);
        Iterator it = ((ArrayMap) C0988e.m993s("android.app.ActivityThread", objM959C, "mProviderMap")).values().iterator();
        while (it.hasNext()) {
            Object objM993s3 = C0988e.m993s("android.app.ActivityThread$ProviderClientRecord", it.next(), "mLocalProvider");
            if (objM993s3 != null && application != null) {
                C0988e.m968L("android.content.ContentProvider", "mContext", objM993s3, application);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("app:");
        sb.append(application);
        application.onCreate();
    }
}
