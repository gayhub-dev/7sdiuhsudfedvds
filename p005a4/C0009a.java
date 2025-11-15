package p005a4;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* compiled from: CompositeException.java */
/* renamed from: a4.a */
/* loaded from: classes.dex */
public final class C0009a extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;

    /* renamed from: e */
    public final List<Throwable> f22e;

    /* renamed from: f */
    public final String f23f;

    /* renamed from: g */
    public Throwable f24g;

    /* compiled from: CompositeException.java */
    /* renamed from: a4.a$a */
    public static final class a extends RuntimeException {
        private static final long serialVersionUID = 3875212506787802066L;

        @Override // java.lang.Throwable
        public String getMessage() {
            return "Chain of Causes for CompositeException In Order Received =>";
        }
    }

    public C0009a(Throwable... thArr) {
        this(Arrays.asList(thArr));
    }

    /* renamed from: a */
    public final void m12a(StringBuilder sb, Throwable th, String str) {
        sb.append(str);
        sb.append(th);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append("\t\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        if (th.getCause() != null) {
            sb.append("\tCaused by: ");
            m12a(sb, th.getCause(), "");
        }
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        if (this.f24g == null) {
            a aVar = new a();
            HashSet hashSet = new HashSet();
            Iterator<Throwable> it = this.f22e.iterator();
            a aVar2 = aVar;
            while (it.hasNext()) {
                Throwable next = it.next();
                if (!hashSet.contains(next)) {
                    hashSet.add(next);
                    ArrayList arrayList = new ArrayList();
                    Throwable cause = next.getCause();
                    if (cause != null && cause != next) {
                        while (true) {
                            arrayList.add(cause);
                            Throwable cause2 = cause.getCause();
                            if (cause2 == null || cause2 == cause) {
                                break;
                            }
                            cause = cause2;
                        }
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        Throwable th = (Throwable) it2.next();
                        if (hashSet.contains(th)) {
                            next = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th);
                        }
                    }
                    try {
                        aVar2.initCause(next);
                    } catch (Throwable unused) {
                    }
                    Throwable cause3 = aVar2.getCause();
                    if (cause3 != null && aVar2 != cause3) {
                        while (true) {
                            Throwable cause4 = cause3.getCause();
                            if (cause4 == null || cause4 == cause3) {
                                break;
                            }
                            cause3 = cause4;
                        }
                        aVar2 = cause3;
                    }
                }
            }
            this.f24g = aVar;
        }
        return this.f24g;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f23f;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        int i7 = 1;
        for (Throwable th : this.f22e) {
            sb.append("  ComposedException ");
            sb.append(i7);
            sb.append(" :\n");
            m12a(sb, th, "\t");
            i7++;
        }
        printStream.println((Object) sb.toString());
    }

    public C0009a(Iterable<? extends Throwable> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (iterable != null) {
            for (Throwable th : iterable) {
                if (th instanceof C0009a) {
                    linkedHashSet.addAll(((C0009a) th).f22e);
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (!linkedHashSet.isEmpty()) {
            arrayList.addAll(linkedHashSet);
            List<Throwable> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            this.f22e = listUnmodifiableList;
            this.f23f = listUnmodifiableList.size() + " exceptions occurred. ";
            return;
        }
        throw new IllegalArgumentException("errors is empty");
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        int i7 = 1;
        for (Throwable th : this.f22e) {
            sb.append("  ComposedException ");
            sb.append(i7);
            sb.append(" :\n");
            m12a(sb, th, "\t");
            i7++;
        }
        printWriter.println((Object) sb.toString());
    }
}
