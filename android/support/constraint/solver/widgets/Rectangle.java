package android.support.constraint.solver.widgets;

/* loaded from: classes.dex */
public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f142x;

    /* renamed from: y */
    public int f143y;

    public boolean contains(int i7, int i8) {
        int i9;
        int i10 = this.f142x;
        return i7 >= i10 && i7 < i10 + this.width && i8 >= (i9 = this.f143y) && i8 < i9 + this.height;
    }

    public int getCenterX() {
        return (this.f142x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f143y + this.height) / 2;
    }

    public void grow(int i7, int i8) {
        this.f142x -= i7;
        this.f143y -= i8;
        this.width = (i7 * 2) + this.width;
        this.height = (i8 * 2) + this.height;
    }

    public boolean intersects(Rectangle rectangle) {
        int i7;
        int i8;
        int i9 = this.f142x;
        int i10 = rectangle.f142x;
        return i9 >= i10 && i9 < i10 + rectangle.width && (i7 = this.f143y) >= (i8 = rectangle.f143y) && i7 < i8 + rectangle.height;
    }

    public void setBounds(int i7, int i8, int i9, int i10) {
        this.f142x = i7;
        this.f143y = i8;
        this.width = i9;
        this.height = i10;
    }
}
