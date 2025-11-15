package android.support.constraint.motion;

import android.arch.lifecycle.C0063n;
import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Debug {
    public static void dumpLayoutParams(ViewGroup viewGroup, String str) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbM112a = C0413b.m112a(".(");
        sbM112a.append(stackTraceElement.getFileName());
        sbM112a.append(":");
        sbM112a.append(stackTraceElement.getLineNumber());
        sbM112a.append(") ");
        sbM112a.append(str);
        sbM112a.append("  ");
        String string = sbM112a.toString();
        int childCount = viewGroup.getChildCount();
        System.out.println(str + " children " + childCount);
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            PrintStream printStream = System.out;
            StringBuilder sbM94a = C0080b.m94a(string, "     ");
            sbM94a.append(getName(childAt));
            printStream.println(sbM94a.toString());
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            for (Field field : layoutParams.getClass().getFields()) {
                try {
                    Object obj = field.get(layoutParams);
                    if (field.getName().contains("To") && !obj.toString().equals("-1")) {
                        System.out.println(string + "       " + field.getName() + " " + obj);
                    }
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    public static void dumpPoc(Object obj) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbM112a = C0413b.m112a(".(");
        sbM112a.append(stackTraceElement.getFileName());
        sbM112a.append(":");
        sbM112a.append(stackTraceElement.getLineNumber());
        sbM112a.append(")");
        String string = sbM112a.toString();
        Class<?> cls = obj.getClass();
        PrintStream printStream = System.out;
        StringBuilder sbM94a = C0080b.m94a(string, "------------- ");
        sbM94a.append(cls.getName());
        sbM94a.append(" --------------------");
        printStream.println(sbM94a.toString());
        for (Field field : cls.getFields()) {
            try {
                Object obj2 = field.get(obj);
                if (field.getName().startsWith("layout_constraint") && ((!(obj2 instanceof Integer) || !obj2.toString().equals("-1")) && ((!(obj2 instanceof Integer) || !obj2.toString().equals("0")) && ((!(obj2 instanceof Float) || !obj2.toString().equals("1.0")) && (!(obj2 instanceof Float) || !obj2.toString().equals("0.5")))))) {
                    System.out.println(string + "    " + field.getName() + " " + obj2);
                }
            } catch (IllegalAccessException unused) {
            }
        }
        PrintStream printStream2 = System.out;
        StringBuilder sbM94a2 = C0080b.m94a(string, "------------- ");
        sbM94a2.append(cls.getSimpleName());
        sbM94a2.append(" --------------------");
        printStream2.println(sbM94a2.toString());
    }

    public static String getActionType(MotionEvent motionEvent) throws SecurityException {
        int action = motionEvent.getAction();
        for (Field field : MotionEvent.class.getFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(Integer.TYPE) && field.getInt(null) == action) {
                return field.getName();
            }
        }
        return "---";
    }

    public static String getCallFrom(int i7) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i7 + 2];
        StringBuilder sbM112a = C0413b.m112a(".(");
        sbM112a.append(stackTraceElement.getFileName());
        sbM112a.append(":");
        sbM112a.append(stackTraceElement.getLineNumber());
        sbM112a.append(")");
        return sbM112a.toString();
    }

    public static String getLoc() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbM112a = C0413b.m112a(".(");
        sbM112a.append(stackTraceElement.getFileName());
        sbM112a.append(":");
        sbM112a.append(stackTraceElement.getLineNumber());
        sbM112a.append(") ");
        sbM112a.append(stackTraceElement.getMethodName());
        sbM112a.append("()");
        return sbM112a.toString();
    }

    public static String getLocation() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbM112a = C0413b.m112a(".(");
        sbM112a.append(stackTraceElement.getFileName());
        sbM112a.append(":");
        sbM112a.append(stackTraceElement.getLineNumber());
        sbM112a.append(")");
        return sbM112a.toString();
    }

    public static String getLocation2() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        StringBuilder sbM112a = C0413b.m112a(".(");
        sbM112a.append(stackTraceElement.getFileName());
        sbM112a.append(":");
        sbM112a.append(stackTraceElement.getLineNumber());
        sbM112a.append(")");
        return sbM112a.toString();
    }

    public static String getName(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static String getState(MotionLayout motionLayout, int i7) {
        return i7 == -1 ? "UNDEFINED" : motionLayout.getContext().getResources().getResourceEntryName(i7);
    }

    public static void logStack(String str, String str2, int i7) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(i7, stackTrace.length - 1);
        String str3 = " ";
        for (int i8 = 1; i8 <= iMin; i8++) {
            StackTraceElement stackTraceElement = stackTrace[i8];
            StringBuilder sbM112a = C0413b.m112a(".(");
            sbM112a.append(stackTrace[i8].getFileName());
            sbM112a.append(":");
            sbM112a.append(stackTrace[i8].getLineNumber());
            sbM112a.append(") ");
            sbM112a.append(stackTrace[i8].getMethodName());
            str3 = str3 + " ";
        }
    }

    public static void printStack(String str, int i7) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(i7, stackTrace.length - 1);
        String strM88a = " ";
        for (int i8 = 1; i8 <= iMin; i8++) {
            StackTraceElement stackTraceElement = stackTrace[i8];
            StringBuilder sbM112a = C0413b.m112a(".(");
            sbM112a.append(stackTrace[i8].getFileName());
            sbM112a.append(":");
            sbM112a.append(stackTrace[i8].getLineNumber());
            sbM112a.append(") ");
            String string = sbM112a.toString();
            strM88a = C0063n.m88a(strM88a, " ");
            System.out.println(str + strM88a + string + strM88a);
        }
    }

    public static String getName(Context context, int i7) {
        if (i7 == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i7);
        } catch (Exception unused) {
            return C0079a.m93a("?", i7);
        }
    }

    public static String getName(Context context, int[] iArr) throws Resources.NotFoundException {
        String resourceEntryName;
        try {
            String str = iArr.length + "[";
            int i7 = 0;
            while (i7 < iArr.length) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(i7 == 0 ? "" : " ");
                String string = sb.toString();
                try {
                    resourceEntryName = context.getResources().getResourceEntryName(iArr[i7]);
                } catch (Resources.NotFoundException unused) {
                    resourceEntryName = "? " + iArr[i7] + " ";
                }
                str = string + resourceEntryName;
                i7++;
            }
            return str + "]";
        } catch (Exception e7) {
            e7.toString();
            return "UNKNOWN";
        }
    }

    public static void dumpLayoutParams(ViewGroup.LayoutParams layoutParams, String str) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbM112a = C0413b.m112a(".(");
        sbM112a.append(stackTraceElement.getFileName());
        sbM112a.append(":");
        sbM112a.append(stackTraceElement.getLineNumber());
        sbM112a.append(") ");
        sbM112a.append(str);
        sbM112a.append("  ");
        String string = sbM112a.toString();
        PrintStream printStream = System.out;
        StringBuilder sbM95a = C0081c.m95a(" >>>>>>>>>>>>>>>>>>. dump ", string, "  ");
        sbM95a.append(layoutParams.getClass().getName());
        printStream.println(sbM95a.toString());
        for (Field field : layoutParams.getClass().getFields()) {
            try {
                Object obj = field.get(layoutParams);
                String name = field.getName();
                if (name.contains("To") && !obj.toString().equals("-1")) {
                    System.out.println(string + "       " + name + " " + obj);
                }
            } catch (IllegalAccessException unused) {
            }
        }
        System.out.println(" <<<<<<<<<<<<<<<<< dump " + string);
    }
}
