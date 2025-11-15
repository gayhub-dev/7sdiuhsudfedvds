package p034d6;

import java.io.IOException;
import java.io.ObjectInputStream;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0475f;
import p016b6.AbstractC0477h;
import p159t3.AbstractC1904c;

/* compiled from: AssembledChronology.java */
/* renamed from: d6.a */
/* loaded from: classes.dex */
public abstract class AbstractC0874a extends AbstractC0875b {
    private static final long serialVersionUID = -6728465968995518215L;

    /* renamed from: A */
    public transient AbstractC0471b f1346A;

    /* renamed from: B */
    public transient AbstractC0471b f1347B;

    /* renamed from: C */
    public transient AbstractC0471b f1348C;

    /* renamed from: D */
    public transient AbstractC0471b f1349D;

    /* renamed from: E */
    public transient AbstractC0471b f1350E;

    /* renamed from: F */
    public transient AbstractC0471b f1351F;

    /* renamed from: G */
    public transient AbstractC0471b f1352G;

    /* renamed from: H */
    public transient AbstractC0471b f1353H;

    /* renamed from: I */
    public transient AbstractC0471b f1354I;

    /* renamed from: J */
    public transient AbstractC0471b f1355J;

    /* renamed from: K */
    public transient AbstractC0471b f1356K;

    /* renamed from: L */
    public transient AbstractC0471b f1357L;

    /* renamed from: M */
    public transient AbstractC0471b f1358M;

    /* renamed from: N */
    public transient AbstractC0471b f1359N;

    /* renamed from: O */
    public transient AbstractC0471b f1360O;

    /* renamed from: P */
    public transient int f1361P;

    /* renamed from: e */
    public final AbstractC1904c f1362e;

    /* renamed from: f */
    public final Object f1363f;

    /* renamed from: g */
    public transient AbstractC0477h f1364g;

    /* renamed from: h */
    public transient AbstractC0477h f1365h;

    /* renamed from: i */
    public transient AbstractC0477h f1366i;

    /* renamed from: j */
    public transient AbstractC0477h f1367j;

    /* renamed from: k */
    public transient AbstractC0477h f1368k;

    /* renamed from: l */
    public transient AbstractC0477h f1369l;

    /* renamed from: m */
    public transient AbstractC0477h f1370m;

    /* renamed from: n */
    public transient AbstractC0477h f1371n;

    /* renamed from: o */
    public transient AbstractC0477h f1372o;

    /* renamed from: p */
    public transient AbstractC0477h f1373p;

    /* renamed from: q */
    public transient AbstractC0477h f1374q;

    /* renamed from: r */
    public transient AbstractC0477h f1375r;

    /* renamed from: s */
    public transient AbstractC0471b f1376s;

    /* renamed from: t */
    public transient AbstractC0471b f1377t;

    /* renamed from: u */
    public transient AbstractC0471b f1378u;

    /* renamed from: v */
    public transient AbstractC0471b f1379v;

    /* renamed from: w */
    public transient AbstractC0471b f1380w;

    /* renamed from: x */
    public transient AbstractC0471b f1381x;

    /* renamed from: y */
    public transient AbstractC0471b f1382y;

    /* renamed from: z */
    public transient AbstractC0471b f1383z;

    /* compiled from: AssembledChronology.java */
    /* renamed from: d6.a$a */
    public static final class a {

        /* renamed from: A */
        public AbstractC0471b f1384A;

        /* renamed from: B */
        public AbstractC0471b f1385B;

        /* renamed from: C */
        public AbstractC0471b f1386C;

        /* renamed from: D */
        public AbstractC0471b f1387D;

        /* renamed from: E */
        public AbstractC0471b f1388E;

        /* renamed from: F */
        public AbstractC0471b f1389F;

        /* renamed from: G */
        public AbstractC0471b f1390G;

        /* renamed from: H */
        public AbstractC0471b f1391H;

        /* renamed from: I */
        public AbstractC0471b f1392I;

        /* renamed from: a */
        public AbstractC0477h f1393a;

        /* renamed from: b */
        public AbstractC0477h f1394b;

        /* renamed from: c */
        public AbstractC0477h f1395c;

        /* renamed from: d */
        public AbstractC0477h f1396d;

        /* renamed from: e */
        public AbstractC0477h f1397e;

        /* renamed from: f */
        public AbstractC0477h f1398f;

        /* renamed from: g */
        public AbstractC0477h f1399g;

        /* renamed from: h */
        public AbstractC0477h f1400h;

        /* renamed from: i */
        public AbstractC0477h f1401i;

        /* renamed from: j */
        public AbstractC0477h f1402j;

        /* renamed from: k */
        public AbstractC0477h f1403k;

        /* renamed from: l */
        public AbstractC0477h f1404l;

        /* renamed from: m */
        public AbstractC0471b f1405m;

        /* renamed from: n */
        public AbstractC0471b f1406n;

        /* renamed from: o */
        public AbstractC0471b f1407o;

        /* renamed from: p */
        public AbstractC0471b f1408p;

        /* renamed from: q */
        public AbstractC0471b f1409q;

        /* renamed from: r */
        public AbstractC0471b f1410r;

        /* renamed from: s */
        public AbstractC0471b f1411s;

        /* renamed from: t */
        public AbstractC0471b f1412t;

        /* renamed from: u */
        public AbstractC0471b f1413u;

        /* renamed from: v */
        public AbstractC0471b f1414v;

        /* renamed from: w */
        public AbstractC0471b f1415w;

        /* renamed from: x */
        public AbstractC0471b f1416x;

        /* renamed from: y */
        public AbstractC0471b f1417y;

        /* renamed from: z */
        public AbstractC0471b f1418z;

        /* renamed from: a */
        public static boolean m719a(AbstractC0471b abstractC0471b) {
            if (abstractC0471b == null) {
                return false;
            }
            return abstractC0471b.mo216s();
        }

        /* renamed from: b */
        public static boolean m720b(AbstractC0477h abstractC0477h) {
            if (abstractC0477h == null) {
                return false;
            }
            return abstractC0477h.mo258l();
        }
    }

    public AbstractC0874a(AbstractC1904c abstractC1904c, Object obj) {
        this.f1362e = abstractC1904c;
        this.f1363f = obj;
        m695R();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        m695R();
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: A */
    public final AbstractC0477h mo681A() {
        return this.f1372o;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: B */
    public final AbstractC0471b mo682B() {
        return this.f1379v;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: C */
    public final AbstractC0471b mo683C() {
        return this.f1378u;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: D */
    public final AbstractC0477h mo684D() {
        return this.f1365h;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: F */
    public final AbstractC0471b mo685F() {
        return this.f1352G;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: G */
    public final AbstractC0477h mo686G() {
        return this.f1370m;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: H */
    public final AbstractC0471b mo687H() {
        return this.f1353H;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: I */
    public final AbstractC0471b mo688I() {
        return this.f1354I;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: J */
    public final AbstractC0477h mo689J() {
        return this.f1371n;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: M */
    public final AbstractC0471b mo690M() {
        return this.f1356K;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: N */
    public final AbstractC0471b mo691N() {
        return this.f1358M;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: O */
    public final AbstractC0471b mo692O() {
        return this.f1357L;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: P */
    public final AbstractC0477h mo693P() {
        return this.f1373p;
    }

    /* renamed from: Q */
    public abstract void mo694Q(a aVar);

    /* renamed from: R */
    public final void m695R() {
        a aVar = new a();
        AbstractC1904c abstractC1904c = this.f1362e;
        if (abstractC1904c != null) {
            AbstractC0477h abstractC0477hMo712t = abstractC1904c.mo712t();
            if (a.m720b(abstractC0477hMo712t)) {
                aVar.f1393a = abstractC0477hMo712t;
            }
            AbstractC0477h abstractC0477hMo684D = abstractC1904c.mo684D();
            if (a.m720b(abstractC0477hMo684D)) {
                aVar.f1394b = abstractC0477hMo684D;
            }
            AbstractC0477h abstractC0477hMo717y = abstractC1904c.mo717y();
            if (a.m720b(abstractC0477hMo717y)) {
                aVar.f1395c = abstractC0477hMo717y;
            }
            AbstractC0477h abstractC0477hMo711s = abstractC1904c.mo711s();
            if (a.m720b(abstractC0477hMo711s)) {
                aVar.f1396d = abstractC0477hMo711s;
            }
            AbstractC0477h abstractC0477hMo708p = abstractC1904c.mo708p();
            if (a.m720b(abstractC0477hMo708p)) {
                aVar.f1397e = abstractC0477hMo708p;
            }
            AbstractC0477h abstractC0477hMo703h = abstractC1904c.mo703h();
            if (a.m720b(abstractC0477hMo703h)) {
                aVar.f1398f = abstractC0477hMo703h;
            }
            AbstractC0477h abstractC0477hMo686G = abstractC1904c.mo686G();
            if (a.m720b(abstractC0477hMo686G)) {
                aVar.f1399g = abstractC0477hMo686G;
            }
            AbstractC0477h abstractC0477hMo689J = abstractC1904c.mo689J();
            if (a.m720b(abstractC0477hMo689J)) {
                aVar.f1400h = abstractC0477hMo689J;
            }
            AbstractC0477h abstractC0477hMo681A = abstractC1904c.mo681A();
            if (a.m720b(abstractC0477hMo681A)) {
                aVar.f1401i = abstractC0477hMo681A;
            }
            AbstractC0477h abstractC0477hMo693P = abstractC1904c.mo693P();
            if (a.m720b(abstractC0477hMo693P)) {
                aVar.f1402j = abstractC0477hMo693P;
            }
            AbstractC0477h abstractC0477hMo696a = abstractC1904c.mo696a();
            if (a.m720b(abstractC0477hMo696a)) {
                aVar.f1403k = abstractC0477hMo696a;
            }
            AbstractC0477h abstractC0477hMo705j = abstractC1904c.mo705j();
            if (a.m720b(abstractC0477hMo705j)) {
                aVar.f1404l = abstractC0477hMo705j;
            }
            AbstractC0471b abstractC0471bMo714v = abstractC1904c.mo714v();
            if (a.m719a(abstractC0471bMo714v)) {
                aVar.f1405m = abstractC0471bMo714v;
            }
            AbstractC0471b abstractC0471bMo713u = abstractC1904c.mo713u();
            if (a.m719a(abstractC0471bMo713u)) {
                aVar.f1406n = abstractC0471bMo713u;
            }
            AbstractC0471b abstractC0471bMo683C = abstractC1904c.mo683C();
            if (a.m719a(abstractC0471bMo683C)) {
                aVar.f1407o = abstractC0471bMo683C;
            }
            AbstractC0471b abstractC0471bMo682B = abstractC1904c.mo682B();
            if (a.m719a(abstractC0471bMo682B)) {
                aVar.f1408p = abstractC0471bMo682B;
            }
            AbstractC0471b abstractC0471bMo716x = abstractC1904c.mo716x();
            if (a.m719a(abstractC0471bMo716x)) {
                aVar.f1409q = abstractC0471bMo716x;
            }
            AbstractC0471b abstractC0471bMo715w = abstractC1904c.mo715w();
            if (a.m719a(abstractC0471bMo715w)) {
                aVar.f1410r = abstractC0471bMo715w;
            }
            AbstractC0471b abstractC0471bMo709q = abstractC1904c.mo709q();
            if (a.m719a(abstractC0471bMo709q)) {
                aVar.f1411s = abstractC0471bMo709q;
            }
            AbstractC0471b abstractC0471bMo698c = abstractC1904c.mo698c();
            if (a.m719a(abstractC0471bMo698c)) {
                aVar.f1412t = abstractC0471bMo698c;
            }
            AbstractC0471b abstractC0471bMo710r = abstractC1904c.mo710r();
            if (a.m719a(abstractC0471bMo710r)) {
                aVar.f1413u = abstractC0471bMo710r;
            }
            AbstractC0471b abstractC0471bMo699d = abstractC1904c.mo699d();
            if (a.m719a(abstractC0471bMo699d)) {
                aVar.f1414v = abstractC0471bMo699d;
            }
            AbstractC0471b abstractC0471bMo707o = abstractC1904c.mo707o();
            if (a.m719a(abstractC0471bMo707o)) {
                aVar.f1415w = abstractC0471bMo707o;
            }
            AbstractC0471b abstractC0471bMo701f = abstractC1904c.mo701f();
            if (a.m719a(abstractC0471bMo701f)) {
                aVar.f1416x = abstractC0471bMo701f;
            }
            AbstractC0471b abstractC0471bMo700e = abstractC1904c.mo700e();
            if (a.m719a(abstractC0471bMo700e)) {
                aVar.f1417y = abstractC0471bMo700e;
            }
            AbstractC0471b abstractC0471bMo702g = abstractC1904c.mo702g();
            if (a.m719a(abstractC0471bMo702g)) {
                aVar.f1418z = abstractC0471bMo702g;
            }
            AbstractC0471b abstractC0471bMo685F = abstractC1904c.mo685F();
            if (a.m719a(abstractC0471bMo685F)) {
                aVar.f1384A = abstractC0471bMo685F;
            }
            AbstractC0471b abstractC0471bMo687H = abstractC1904c.mo687H();
            if (a.m719a(abstractC0471bMo687H)) {
                aVar.f1385B = abstractC0471bMo687H;
            }
            AbstractC0471b abstractC0471bMo688I = abstractC1904c.mo688I();
            if (a.m719a(abstractC0471bMo688I)) {
                aVar.f1386C = abstractC0471bMo688I;
            }
            AbstractC0471b abstractC0471bMo718z = abstractC1904c.mo718z();
            if (a.m719a(abstractC0471bMo718z)) {
                aVar.f1387D = abstractC0471bMo718z;
            }
            AbstractC0471b abstractC0471bMo690M = abstractC1904c.mo690M();
            if (a.m719a(abstractC0471bMo690M)) {
                aVar.f1388E = abstractC0471bMo690M;
            }
            AbstractC0471b abstractC0471bMo692O = abstractC1904c.mo692O();
            if (a.m719a(abstractC0471bMo692O)) {
                aVar.f1389F = abstractC0471bMo692O;
            }
            AbstractC0471b abstractC0471bMo691N = abstractC1904c.mo691N();
            if (a.m719a(abstractC0471bMo691N)) {
                aVar.f1390G = abstractC0471bMo691N;
            }
            AbstractC0471b abstractC0471bMo697b = abstractC1904c.mo697b();
            if (a.m719a(abstractC0471bMo697b)) {
                aVar.f1391H = abstractC0471bMo697b;
            }
            AbstractC0471b abstractC0471bMo704i = abstractC1904c.mo704i();
            if (a.m719a(abstractC0471bMo704i)) {
                aVar.f1392I = abstractC0471bMo704i;
            }
        }
        mo694Q(aVar);
        AbstractC0477h abstractC0477hMo712t2 = aVar.f1393a;
        if (abstractC0477hMo712t2 == null) {
            abstractC0477hMo712t2 = super.mo712t();
        }
        this.f1364g = abstractC0477hMo712t2;
        AbstractC0477h abstractC0477hMo684D2 = aVar.f1394b;
        if (abstractC0477hMo684D2 == null) {
            abstractC0477hMo684D2 = super.mo684D();
        }
        this.f1365h = abstractC0477hMo684D2;
        AbstractC0477h abstractC0477hMo717y2 = aVar.f1395c;
        if (abstractC0477hMo717y2 == null) {
            abstractC0477hMo717y2 = super.mo717y();
        }
        this.f1366i = abstractC0477hMo717y2;
        AbstractC0477h abstractC0477hMo711s2 = aVar.f1396d;
        if (abstractC0477hMo711s2 == null) {
            abstractC0477hMo711s2 = super.mo711s();
        }
        this.f1367j = abstractC0477hMo711s2;
        AbstractC0477h abstractC0477hMo708p2 = aVar.f1397e;
        if (abstractC0477hMo708p2 == null) {
            abstractC0477hMo708p2 = super.mo708p();
        }
        this.f1368k = abstractC0477hMo708p2;
        AbstractC0477h abstractC0477hMo703h2 = aVar.f1398f;
        if (abstractC0477hMo703h2 == null) {
            abstractC0477hMo703h2 = super.mo703h();
        }
        this.f1369l = abstractC0477hMo703h2;
        AbstractC0477h abstractC0477hMo686G2 = aVar.f1399g;
        if (abstractC0477hMo686G2 == null) {
            abstractC0477hMo686G2 = super.mo686G();
        }
        this.f1370m = abstractC0477hMo686G2;
        AbstractC0477h abstractC0477hMo689J2 = aVar.f1400h;
        if (abstractC0477hMo689J2 == null) {
            abstractC0477hMo689J2 = super.mo689J();
        }
        this.f1371n = abstractC0477hMo689J2;
        AbstractC0477h abstractC0477hMo681A2 = aVar.f1401i;
        if (abstractC0477hMo681A2 == null) {
            abstractC0477hMo681A2 = super.mo681A();
        }
        this.f1372o = abstractC0477hMo681A2;
        AbstractC0477h abstractC0477hMo693P2 = aVar.f1402j;
        if (abstractC0477hMo693P2 == null) {
            abstractC0477hMo693P2 = super.mo693P();
        }
        this.f1373p = abstractC0477hMo693P2;
        AbstractC0477h abstractC0477hMo696a2 = aVar.f1403k;
        if (abstractC0477hMo696a2 == null) {
            abstractC0477hMo696a2 = super.mo696a();
        }
        this.f1374q = abstractC0477hMo696a2;
        AbstractC0477h abstractC0477hMo705j2 = aVar.f1404l;
        if (abstractC0477hMo705j2 == null) {
            abstractC0477hMo705j2 = super.mo705j();
        }
        this.f1375r = abstractC0477hMo705j2;
        AbstractC0471b abstractC0471bMo714v2 = aVar.f1405m;
        if (abstractC0471bMo714v2 == null) {
            abstractC0471bMo714v2 = super.mo714v();
        }
        this.f1376s = abstractC0471bMo714v2;
        AbstractC0471b abstractC0471bMo713u2 = aVar.f1406n;
        if (abstractC0471bMo713u2 == null) {
            abstractC0471bMo713u2 = super.mo713u();
        }
        this.f1377t = abstractC0471bMo713u2;
        AbstractC0471b abstractC0471bMo683C2 = aVar.f1407o;
        if (abstractC0471bMo683C2 == null) {
            abstractC0471bMo683C2 = super.mo683C();
        }
        this.f1378u = abstractC0471bMo683C2;
        AbstractC0471b abstractC0471bMo682B2 = aVar.f1408p;
        if (abstractC0471bMo682B2 == null) {
            abstractC0471bMo682B2 = super.mo682B();
        }
        this.f1379v = abstractC0471bMo682B2;
        AbstractC0471b abstractC0471bMo716x2 = aVar.f1409q;
        if (abstractC0471bMo716x2 == null) {
            abstractC0471bMo716x2 = super.mo716x();
        }
        this.f1380w = abstractC0471bMo716x2;
        AbstractC0471b abstractC0471bMo715w2 = aVar.f1410r;
        if (abstractC0471bMo715w2 == null) {
            abstractC0471bMo715w2 = super.mo715w();
        }
        this.f1381x = abstractC0471bMo715w2;
        AbstractC0471b abstractC0471bMo709q2 = aVar.f1411s;
        if (abstractC0471bMo709q2 == null) {
            abstractC0471bMo709q2 = super.mo709q();
        }
        this.f1382y = abstractC0471bMo709q2;
        AbstractC0471b abstractC0471bMo698c2 = aVar.f1412t;
        if (abstractC0471bMo698c2 == null) {
            abstractC0471bMo698c2 = super.mo698c();
        }
        this.f1383z = abstractC0471bMo698c2;
        AbstractC0471b abstractC0471bMo710r2 = aVar.f1413u;
        if (abstractC0471bMo710r2 == null) {
            abstractC0471bMo710r2 = super.mo710r();
        }
        this.f1346A = abstractC0471bMo710r2;
        AbstractC0471b abstractC0471bMo699d2 = aVar.f1414v;
        if (abstractC0471bMo699d2 == null) {
            abstractC0471bMo699d2 = super.mo699d();
        }
        this.f1347B = abstractC0471bMo699d2;
        AbstractC0471b abstractC0471bMo707o2 = aVar.f1415w;
        if (abstractC0471bMo707o2 == null) {
            abstractC0471bMo707o2 = super.mo707o();
        }
        this.f1348C = abstractC0471bMo707o2;
        AbstractC0471b abstractC0471bMo701f2 = aVar.f1416x;
        if (abstractC0471bMo701f2 == null) {
            abstractC0471bMo701f2 = super.mo701f();
        }
        this.f1349D = abstractC0471bMo701f2;
        AbstractC0471b abstractC0471bMo700e2 = aVar.f1417y;
        if (abstractC0471bMo700e2 == null) {
            abstractC0471bMo700e2 = super.mo700e();
        }
        this.f1350E = abstractC0471bMo700e2;
        AbstractC0471b abstractC0471bMo702g2 = aVar.f1418z;
        if (abstractC0471bMo702g2 == null) {
            abstractC0471bMo702g2 = super.mo702g();
        }
        this.f1351F = abstractC0471bMo702g2;
        AbstractC0471b abstractC0471bMo685F2 = aVar.f1384A;
        if (abstractC0471bMo685F2 == null) {
            abstractC0471bMo685F2 = super.mo685F();
        }
        this.f1352G = abstractC0471bMo685F2;
        AbstractC0471b abstractC0471bMo687H2 = aVar.f1385B;
        if (abstractC0471bMo687H2 == null) {
            abstractC0471bMo687H2 = super.mo687H();
        }
        this.f1353H = abstractC0471bMo687H2;
        AbstractC0471b abstractC0471bMo688I2 = aVar.f1386C;
        if (abstractC0471bMo688I2 == null) {
            abstractC0471bMo688I2 = super.mo688I();
        }
        this.f1354I = abstractC0471bMo688I2;
        AbstractC0471b abstractC0471bMo718z2 = aVar.f1387D;
        if (abstractC0471bMo718z2 == null) {
            abstractC0471bMo718z2 = super.mo718z();
        }
        this.f1355J = abstractC0471bMo718z2;
        AbstractC0471b abstractC0471bMo690M2 = aVar.f1388E;
        if (abstractC0471bMo690M2 == null) {
            abstractC0471bMo690M2 = super.mo690M();
        }
        this.f1356K = abstractC0471bMo690M2;
        AbstractC0471b abstractC0471bMo692O2 = aVar.f1389F;
        if (abstractC0471bMo692O2 == null) {
            abstractC0471bMo692O2 = super.mo692O();
        }
        this.f1357L = abstractC0471bMo692O2;
        AbstractC0471b abstractC0471bMo691N2 = aVar.f1390G;
        if (abstractC0471bMo691N2 == null) {
            abstractC0471bMo691N2 = super.mo691N();
        }
        this.f1358M = abstractC0471bMo691N2;
        AbstractC0471b abstractC0471bMo697b2 = aVar.f1391H;
        if (abstractC0471bMo697b2 == null) {
            abstractC0471bMo697b2 = super.mo697b();
        }
        this.f1359N = abstractC0471bMo697b2;
        AbstractC0471b abstractC0471bMo704i2 = aVar.f1392I;
        if (abstractC0471bMo704i2 == null) {
            abstractC0471bMo704i2 = super.mo704i();
        }
        this.f1360O = abstractC0471bMo704i2;
        AbstractC1904c abstractC1904c2 = this.f1362e;
        int i7 = 0;
        if (abstractC1904c2 != null) {
            int i8 = ((this.f1382y == abstractC1904c2.mo709q() && this.f1380w == this.f1362e.mo716x() && this.f1378u == this.f1362e.mo683C() && this.f1376s == this.f1362e.mo714v()) ? 1 : 0) | (this.f1377t == this.f1362e.mo713u() ? 2 : 0);
            if (this.f1356K == this.f1362e.mo690M() && this.f1355J == this.f1362e.mo718z() && this.f1350E == this.f1362e.mo700e()) {
                i7 = 4;
            }
            i7 |= i8;
        }
        this.f1361P = i7;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: a */
    public final AbstractC0477h mo696a() {
        return this.f1374q;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: b */
    public final AbstractC0471b mo697b() {
        return this.f1359N;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: c */
    public final AbstractC0471b mo698c() {
        return this.f1383z;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: d */
    public final AbstractC0471b mo699d() {
        return this.f1347B;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: e */
    public final AbstractC0471b mo700e() {
        return this.f1350E;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: f */
    public final AbstractC0471b mo701f() {
        return this.f1349D;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: g */
    public final AbstractC0471b mo702g() {
        return this.f1351F;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: h */
    public final AbstractC0477h mo703h() {
        return this.f1369l;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: i */
    public final AbstractC0471b mo704i() {
        return this.f1360O;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: j */
    public final AbstractC0477h mo705j() {
        return this.f1375r;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: m */
    public long mo706m(long j7, int i7, int i8, int i9, int i10) {
        AbstractC1904c abstractC1904c = this.f1362e;
        return (abstractC1904c == null || (this.f1361P & 1) != 1) ? super.mo706m(j7, i7, i8, i9, i10) : abstractC1904c.mo706m(j7, i7, i8, i9, i10);
    }

    @Override // p159t3.AbstractC1904c
    /* renamed from: n */
    public AbstractC0475f mo230n() {
        AbstractC1904c abstractC1904c = this.f1362e;
        if (abstractC1904c != null) {
            return abstractC1904c.mo230n();
        }
        return null;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: o */
    public final AbstractC0471b mo707o() {
        return this.f1348C;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: p */
    public final AbstractC0477h mo708p() {
        return this.f1368k;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: q */
    public final AbstractC0471b mo709q() {
        return this.f1382y;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: r */
    public final AbstractC0471b mo710r() {
        return this.f1346A;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: s */
    public final AbstractC0477h mo711s() {
        return this.f1367j;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: t */
    public final AbstractC0477h mo712t() {
        return this.f1364g;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: u */
    public final AbstractC0471b mo713u() {
        return this.f1377t;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: v */
    public final AbstractC0471b mo714v() {
        return this.f1376s;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: w */
    public final AbstractC0471b mo715w() {
        return this.f1381x;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: x */
    public final AbstractC0471b mo716x() {
        return this.f1380w;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: y */
    public final AbstractC0477h mo717y() {
        return this.f1366i;
    }

    @Override // p034d6.AbstractC0875b, p159t3.AbstractC1904c
    /* renamed from: z */
    public final AbstractC0471b mo718z() {
        return this.f1355J;
    }
}
