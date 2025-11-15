package p016b6;

/* compiled from: IllegalFieldValueException.java */
/* renamed from: b6.j */
/* loaded from: classes.dex */
public class C0479j extends IllegalArgumentException {
    private static final long serialVersionUID = 6305711765985447737L;

    /* renamed from: e */
    public String f336e;

    public C0479j(AbstractC0472c abstractC0472c, Number number, Number number2, Number number3) {
        super(m260a(abstractC0472c.f309e, number, number2, number3, null));
        this.f336e = super.getMessage();
    }

    /* renamed from: a */
    public static String m260a(String str, Number number, Number number2, Number number3, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Value ");
        sb.append(number);
        sb.append(" for ");
        sb.append(str);
        sb.append(' ');
        if (number2 == null) {
            if (number3 == null) {
                sb.append("is not supported");
            } else {
                sb.append("must not be larger than ");
                sb.append(number3);
            }
        } else if (number3 == null) {
            sb.append("must not be smaller than ");
            sb.append(number2);
        } else {
            sb.append("must be in the range [");
            sb.append(number2);
            sb.append(',');
            sb.append(number3);
            sb.append(']');
        }
        if (str2 != null) {
            sb.append(": ");
            sb.append(str2);
        }
        return sb.toString();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f336e;
    }

    public C0479j(AbstractC0472c abstractC0472c, Number number, String str) {
        super(m260a(abstractC0472c.f309e, number, null, null, str));
        this.f336e = super.getMessage();
    }

    public C0479j(AbstractC0472c abstractC0472c, String str) {
        String str2 = abstractC0472c.f309e;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Value ");
        if (str == null) {
            stringBuffer.append("null");
        } else {
            stringBuffer.append('\"');
            stringBuffer.append(str);
            stringBuffer.append('\"');
        }
        stringBuffer.append(" for ");
        stringBuffer.append(str2);
        stringBuffer.append(' ');
        stringBuffer.append("is not supported");
        super(stringBuffer.toString());
        this.f336e = super.getMessage();
    }
}
