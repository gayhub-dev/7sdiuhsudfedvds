package android.support.constraint.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.C0071R;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class MockView extends View {
    private int mDiagonalsColor;
    private boolean mDrawDiagonals;
    private boolean mDrawLabel;
    private int mMargin;
    private Paint mPaintDiagonals;
    private Paint mPaintText;
    private Paint mPaintTextBackground;
    public String mText;
    private int mTextBackgroundColor;
    private Rect mTextBounds;
    private int mTextColor;

    public MockView(Context context) {
        super(context);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(255, 0, 0, 0);
        this.mTextColor = Color.argb(255, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(255, 50, 50, 50);
        this.mMargin = 4;
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0071R.styleable.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.MockView_mock_label) {
                    this.mText = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == C0071R.styleable.MockView_mock_showDiagonals) {
                    this.mDrawDiagonals = typedArrayObtainStyledAttributes.getBoolean(index, this.mDrawDiagonals);
                } else if (index == C0071R.styleable.MockView_mock_diagonalsColor) {
                    this.mDiagonalsColor = typedArrayObtainStyledAttributes.getColor(index, this.mDiagonalsColor);
                } else if (index == C0071R.styleable.MockView_mock_labelBackgroundColor) {
                    this.mTextBackgroundColor = typedArrayObtainStyledAttributes.getColor(index, this.mTextBackgroundColor);
                } else if (index == C0071R.styleable.MockView_mock_labelColor) {
                    this.mTextColor = typedArrayObtainStyledAttributes.getColor(index, this.mTextColor);
                } else if (index == C0071R.styleable.MockView_mock_showLabel) {
                    this.mDrawLabel = typedArrayObtainStyledAttributes.getBoolean(index, this.mDrawLabel);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.mText == null) {
            try {
                this.mText = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.mPaintDiagonals.setColor(this.mDiagonalsColor);
        this.mPaintDiagonals.setAntiAlias(true);
        this.mPaintText.setColor(this.mTextColor);
        this.mPaintText.setAntiAlias(true);
        this.mPaintTextBackground.setColor(this.mTextBackgroundColor);
        this.mMargin = Math.round((getResources().getDisplayMetrics().xdpi / 160.0f) * this.mMargin);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.mDrawDiagonals) {
            width--;
            height--;
            float f7 = width;
            float f8 = height;
            canvas.drawLine(0.0f, 0.0f, f7, f8, this.mPaintDiagonals);
            canvas.drawLine(0.0f, f8, f7, 0.0f, this.mPaintDiagonals);
            canvas.drawLine(0.0f, 0.0f, f7, 0.0f, this.mPaintDiagonals);
            canvas.drawLine(f7, 0.0f, f7, f8, this.mPaintDiagonals);
            canvas.drawLine(f7, f8, 0.0f, f8, this.mPaintDiagonals);
            canvas.drawLine(0.0f, f8, 0.0f, 0.0f, this.mPaintDiagonals);
        }
        String str = this.mText;
        if (str == null || !this.mDrawLabel) {
            return;
        }
        this.mPaintText.getTextBounds(str, 0, str.length(), this.mTextBounds);
        float fWidth = (width - this.mTextBounds.width()) / 2.0f;
        float fHeight = ((height - this.mTextBounds.height()) / 2.0f) + this.mTextBounds.height();
        this.mTextBounds.offset((int) fWidth, (int) fHeight);
        Rect rect = this.mTextBounds;
        int i7 = rect.left;
        int i8 = this.mMargin;
        rect.set(i7 - i8, rect.top - i8, rect.right + i8, rect.bottom + i8);
        canvas.drawRect(this.mTextBounds, this.mPaintTextBackground);
        canvas.drawText(this.mText, fWidth, fHeight, this.mPaintText);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(255, 0, 0, 0);
        this.mTextColor = Color.argb(255, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(255, 50, 50, 50);
        this.mMargin = 4;
        init(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(255, 0, 0, 0);
        this.mTextColor = Color.argb(255, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(255, 50, 50, 50);
        this.mMargin = 4;
        init(context, attributeSet);
    }
}
