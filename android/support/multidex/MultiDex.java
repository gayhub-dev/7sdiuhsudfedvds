package android.support.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.constraint.motion.C0081c;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class MultiDex {
    private static final String CODE_CACHE_NAME = "code_cache";
    private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = "secondary-dexes";
    private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    private static final int MIN_SDK_VERSION = 4;
    private static final String NO_KEY_PREFIX = "";
    private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
    public static final String TAG = "MultiDex";
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    private static final Set<File> installedApk = new HashSet();
    private static final boolean IS_VM_MULTIDEX_CAPABLE = isVMMultidexCapable(System.getProperty("java.vm.version"));

    public static final class V14 {
        private static final int EXTRACTED_SUFFIX_LENGTH = 4;
        private final ElementConstructor elementConstructor;

        public interface ElementConstructor {
            Object newInstance(File file, DexFile dexFile);
        }

        public static class ICSElementConstructor implements ElementConstructor {
            private final Constructor<?> elementConstructor;

            public ICSElementConstructor(Class<?> cls) throws NoSuchMethodException, SecurityException {
                Constructor<?> constructor = cls.getConstructor(File.class, ZipFile.class, DexFile.class);
                this.elementConstructor = constructor;
                constructor.setAccessible(true);
            }

            @Override // android.support.multidex.MultiDex.V14.ElementConstructor
            public Object newInstance(File file, DexFile dexFile) {
                return this.elementConstructor.newInstance(file, new ZipFile(file), dexFile);
            }
        }

        public static class JBMR11ElementConstructor implements ElementConstructor {
            private final Constructor<?> elementConstructor;

            public JBMR11ElementConstructor(Class<?> cls) throws NoSuchMethodException, SecurityException {
                Constructor<?> constructor = cls.getConstructor(File.class, File.class, DexFile.class);
                this.elementConstructor = constructor;
                constructor.setAccessible(true);
            }

            @Override // android.support.multidex.MultiDex.V14.ElementConstructor
            public Object newInstance(File file, DexFile dexFile) {
                return this.elementConstructor.newInstance(file, file, dexFile);
            }
        }

        public static class JBMR2ElementConstructor implements ElementConstructor {
            private final Constructor<?> elementConstructor;

            public JBMR2ElementConstructor(Class<?> cls) throws NoSuchMethodException, SecurityException {
                Constructor<?> constructor = cls.getConstructor(File.class, Boolean.TYPE, File.class, DexFile.class);
                this.elementConstructor = constructor;
                constructor.setAccessible(true);
            }

            @Override // android.support.multidex.MultiDex.V14.ElementConstructor
            public Object newInstance(File file, DexFile dexFile) {
                return this.elementConstructor.newInstance(file, Boolean.FALSE, file, dexFile);
            }
        }

        private V14() throws ClassNotFoundException {
            ElementConstructor jBMR2ElementConstructor;
            Class<?> cls = Class.forName("dalvik.system.DexPathList$Element");
            try {
                try {
                    jBMR2ElementConstructor = new ICSElementConstructor(cls);
                } catch (NoSuchMethodException unused) {
                    jBMR2ElementConstructor = new JBMR2ElementConstructor(cls);
                }
            } catch (NoSuchMethodException unused2) {
                jBMR2ElementConstructor = new JBMR11ElementConstructor(cls);
            }
            this.elementConstructor = jBMR2ElementConstructor;
        }

        public static void install(ClassLoader classLoader, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            Object obj = MultiDex.findField(classLoader, "pathList").get(classLoader);
            Object[] objArrMakeDexElements = new V14().makeDexElements(list);
            try {
                MultiDex.expandFieldArray(obj, "dexElements", objArrMakeDexElements);
            } catch (NoSuchFieldException unused) {
                MultiDex.expandFieldArray(obj, "pathElements", objArrMakeDexElements);
            }
        }

        private Object[] makeDexElements(List<? extends File> list) {
            int size = list.size();
            Object[] objArr = new Object[size];
            for (int i7 = 0; i7 < size; i7++) {
                File file = list.get(i7);
                objArr[i7] = this.elementConstructor.newInstance(file, DexFile.loadDex(file.getPath(), optimizedPathFor(file), 0));
            }
            return objArr;
        }

        private static String optimizedPathFor(File file) {
            File parentFile = file.getParentFile();
            String name = file.getName();
            return new File(parentFile, name.substring(0, name.length() - EXTRACTED_SUFFIX_LENGTH) + MultiDexExtractor.DEX_SUFFIX).getPath();
        }
    }

    public static final class V19 {
        private V19() {
        }

        public static void install(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
            IOException[] iOExceptionArr;
            Object obj = MultiDex.findField(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            MultiDex.expandFieldArray(obj, "dexElements", makeDexElements(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                }
                Field fieldFindField = MultiDex.findField(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) fieldFindField.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                fieldFindField.set(obj, iOExceptionArr);
                IOException iOException = new IOException("I/O exception during makeDexElement");
                iOException.initCause((Throwable) arrayList.get(0));
                throw iOException;
            }
        }

        private static Object[] makeDexElements(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) {
            return (Object[]) MultiDex.findMethod(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(obj, arrayList, file, arrayList2);
        }
    }

    /* renamed from: android.support.multidex.MultiDex$V4 */
    public static final class C0121V4 {
        private C0121V4() {
        }

        public static void install(ClassLoader classLoader, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            int size = list.size();
            Field fieldFindField = MultiDex.findField(classLoader, "path");
            StringBuilder sb = new StringBuilder((String) fieldFindField.get(classLoader));
            String[] strArr = new String[size];
            File[] fileArr = new File[size];
            ZipFile[] zipFileArr = new ZipFile[size];
            DexFile[] dexFileArr = new DexFile[size];
            ListIterator<? extends File> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                File next = listIterator.next();
                String absolutePath = next.getAbsolutePath();
                sb.append(':');
                sb.append(absolutePath);
                int iPreviousIndex = listIterator.previousIndex();
                strArr[iPreviousIndex] = absolutePath;
                fileArr[iPreviousIndex] = next;
                zipFileArr[iPreviousIndex] = new ZipFile(next);
                dexFileArr[iPreviousIndex] = DexFile.loadDex(absolutePath, absolutePath + MultiDexExtractor.DEX_SUFFIX, 0);
            }
            fieldFindField.set(classLoader, sb.toString());
            MultiDex.expandFieldArray(classLoader, "mPaths", strArr);
            MultiDex.expandFieldArray(classLoader, "mFiles", fileArr);
            MultiDex.expandFieldArray(classLoader, "mZips", zipFileArr);
            MultiDex.expandFieldArray(classLoader, "mDexs", dexFileArr);
        }
    }

    private MultiDex() {
    }

    private static void clearOldDexDir(Context context) {
        File file = new File(context.getFilesDir(), "secondary-dexes");
        if (file.isDirectory()) {
            file.getPath();
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                file.getPath();
                return;
            }
            for (File file2 : fileArrListFiles) {
                file2.getPath();
                file2.length();
                if (file2.delete()) {
                    file2.getPath();
                } else {
                    file2.getPath();
                }
            }
            if (file.delete()) {
                file.getPath();
            } else {
                file.getPath();
            }
        }
    }

    private static void doInstallation(Context context, File file, File file2, String str, String str2, boolean z6) {
        Set<File> set = installedApk;
        synchronized (set) {
            if (set.contains(file)) {
                return;
            }
            set.add(file);
            System.getProperty("java.vm.version");
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (classLoader == null) {
                    return;
                }
                try {
                    clearOldDexDir(context);
                } catch (Throwable unused) {
                }
                File dexDir = getDexDir(context, file2, str);
                MultiDexExtractor multiDexExtractor = new MultiDexExtractor(file, dexDir);
                IOException e7 = null;
                try {
                    try {
                        installSecondaryDexes(classLoader, dexDir, multiDexExtractor.load(context, str2, false));
                    } catch (IOException e8) {
                        if (!z6) {
                            throw e8;
                        }
                        installSecondaryDexes(classLoader, dexDir, multiDexExtractor.load(context, str2, true));
                    }
                    try {
                    } catch (IOException e9) {
                        e7 = e9;
                    }
                    if (e7 != null) {
                        throw e7;
                    }
                } finally {
                    try {
                        multiDexExtractor.close();
                    } catch (IOException unused2) {
                    }
                }
            } catch (RuntimeException unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void expandFieldArray(Object obj, String str, Object[] objArr) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field fieldFindField = findField(obj, str);
        Object[] objArr2 = (Object[]) fieldFindField.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        fieldFindField.set(obj, objArr3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Field findField(Object obj, String str) throws NoSuchFieldException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        StringBuilder sbM95a = C0081c.m95a("Field ", str, " not found in ");
        sbM95a.append(obj.getClass());
        throw new NoSuchFieldException(sbM95a.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method findMethod(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        StringBuilder sbM95a = C0081c.m95a("Method ", str, " with parameters ");
        sbM95a.append(Arrays.asList(clsArr));
        sbM95a.append(" not found in ");
        sbM95a.append(obj.getClass());
        throw new NoSuchMethodException(sbM95a.toString());
    }

    private static ApplicationInfo getApplicationInfo(Context context) {
        try {
            return context.getApplicationInfo();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    private static File getDexDir(Context context, File file, String str) throws IOException {
        File file2 = new File(file, CODE_CACHE_NAME);
        try {
            mkdirChecked(file2);
        } catch (IOException unused) {
            file2 = new File(context.getFilesDir(), CODE_CACHE_NAME);
            mkdirChecked(file2);
        }
        File file3 = new File(file2, str);
        mkdirChecked(file3);
        return file3;
    }

    public static void install(Context context) {
        if (IS_VM_MULTIDEX_CAPABLE) {
            return;
        }
        try {
            ApplicationInfo applicationInfo = getApplicationInfo(context);
            if (applicationInfo == null) {
                return;
            }
            doInstallation(context, new File(applicationInfo.sourceDir), new File(applicationInfo.dataDir), "secondary-dexes", "", true);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("MultiDex installation failed (");
            sbM112a.append(e7.getMessage());
            sbM112a.append(").");
            throw new RuntimeException(sbM112a.toString());
        }
    }

    public static void installInstrumentation(Context context, Context context2) {
        ApplicationInfo applicationInfo;
        if (IS_VM_MULTIDEX_CAPABLE) {
            return;
        }
        try {
            ApplicationInfo applicationInfo2 = getApplicationInfo(context);
            if (applicationInfo2 == null || (applicationInfo = getApplicationInfo(context2)) == null) {
                return;
            }
            String str = context.getPackageName() + ".";
            File file = new File(applicationInfo.dataDir);
            doInstallation(context2, new File(applicationInfo2.sourceDir), file, str + "secondary-dexes", str, false);
            doInstallation(context2, new File(applicationInfo.sourceDir), file, "secondary-dexes", "", false);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("MultiDex installation failed (");
            sbM112a.append(e7.getMessage());
            sbM112a.append(").");
            throw new RuntimeException(sbM112a.toString());
        }
    }

    private static void installSecondaryDexes(ClassLoader classLoader, File file, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
        if (list.isEmpty()) {
            return;
        }
        V19.install(classLoader, list, file);
    }

    public static boolean isVMMultidexCapable(String str) throws NumberFormatException {
        if (str == null) {
            return false;
        }
        Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        try {
            int i7 = Integer.parseInt(matcher.group(1));
            return i7 > 2 || (i7 == 2 && Integer.parseInt(matcher.group(2)) >= 1);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static void mkdirChecked(File file) throws IOException {
        file.mkdir();
        if (file.isDirectory()) {
            return;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            file.getPath();
        } else {
            file.getPath();
            parentFile.isDirectory();
            parentFile.isFile();
            parentFile.exists();
            parentFile.canRead();
            parentFile.canWrite();
        }
        StringBuilder sbM112a = C0413b.m112a("Failed to create directory ");
        sbM112a.append(file.getPath());
        throw new IOException(sbM112a.toString());
    }
}
