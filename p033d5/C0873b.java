package p033d5;

import java.io.Serializable;
import java.io.Writer;

/* compiled from: StringBuilderWriter.java */
/* renamed from: d5.b */
/* loaded from: classes.dex */
public class C0873b extends Writer implements Serializable {
    private static final long serialVersionUID = -146927496096066153L;

    /* renamed from: e */
    public final StringBuilder f1345e;

    public C0873b(int i7) {
        this.f1345e = new StringBuilder(i7);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c7) {
        this.f1345e.append(c7);
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    public String toString() {
        return this.f1345e.toString();
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str != null) {
            this.f1345e.append(str);
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Appendable append(char c7) {
        this.f1345e.append(c7);
        return this;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i7, int i8) {
        if (cArr != null) {
            this.f1345e.append(cArr, i7, i8);
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) {
        this.f1345e.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Appendable append(CharSequence charSequence) {
        this.f1345e.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int i7, int i8) {
        this.f1345e.append(charSequence, i7, i8);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Appendable append(CharSequence charSequence, int i7, int i8) {
        this.f1345e.append(charSequence, i7, i8);
        return this;
    }
}
