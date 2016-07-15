package ext.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class DrawablesClickTextView extends TextView {

    public static final int DRAWABLE_LEFT = 0;
    public static final int DRAWABLE_TOP = 1;
    public static final int DRAWABLE_RIGHT = 2;
    public static final int DRAWABLE_BOTTOM = 3;
    /** 增大Drawable点击区域的padding */
    private int mDrawableClickPadding;

    private OnDrawablesClickListener mDrawablesClickListener;

    public DrawablesClickTextView(Context context) {
        this(context, null, 0);
    }

    public DrawablesClickTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawablesClickTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DrawablesClickTextView);
            mDrawableClickPadding = ta.getDimensionPixelSize(R.styleable.DrawablesClickTextView_drawableClickPadding, 0);
            ta.recycle();
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void setDrawableClickPadding(int clickPadding) {
        this.mDrawableClickPadding = clickPadding;
    }

    public void setOnDrawablesClickListener(OnDrawablesClickListener listener) {
        mDrawablesClickListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mDrawablesClickListener != null) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    Drawable left = getCompoundDrawables()[DRAWABLE_LEFT];
                    Drawable top = getCompoundDrawables()[DRAWABLE_TOP];
                    Drawable right = getCompoundDrawables()[DRAWABLE_RIGHT];
                    Drawable bottom = getCompoundDrawables()[DRAWABLE_BOTTOM];
                    int drawable = -1;
                    if (left != null
                            && contains(x, y, left.getIntrinsicWidth(), left.getIntrinsicHeight(), DRAWABLE_LEFT)) {
                        drawable = DRAWABLE_LEFT;
                    } else if (top != null
                            && contains(x, y, top.getIntrinsicWidth(), top.getIntrinsicHeight(), DRAWABLE_TOP)) {
                        drawable = DRAWABLE_TOP;
                    } else if (right != null
                            && contains(x, y, right.getIntrinsicWidth(), right.getIntrinsicHeight(), DRAWABLE_RIGHT)) {
                        drawable = DRAWABLE_RIGHT;
                    } else if (bottom != null
                            && contains(x, y, bottom.getIntrinsicWidth(), bottom.getIntrinsicHeight(), DRAWABLE_BOTTOM)) {
                        drawable = DRAWABLE_BOTTOM;
                    }
                    if (drawable > -1) {
                        mDrawablesClickListener.onDrawablesClick(drawable);
                        return true;
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean contains(int x, int y, int w, int h, int direction) {
        int x0 = 0;
        int y0 = 0;
        switch (direction) {
            case DRAWABLE_LEFT:
                x0 = getPaddingLeft();
                y0 = getPaddingTop();
                if (getGravity() == (Gravity.CENTER | Gravity.CENTER_VERTICAL)) {
                    y0 = (getHeight() - h) /2;
                }
                break;

            case DRAWABLE_RIGHT:
                x0 = getWidth() - getPaddingRight() - w;
                y0 = getPaddingTop();
                if (getGravity() == (Gravity.CENTER | Gravity.CENTER_VERTICAL)) {
                    y0 = (getHeight() - h) /2;
                }
                break;

            case DRAWABLE_TOP:
                x0 = getPaddingLeft();
                if (getGravity() == (Gravity.CENTER | Gravity.CENTER_HORIZONTAL)) {
                    x0 = (getWidth() - w) / 2;
                }
                y0 = getPaddingTop();
                break;

            case DRAWABLE_BOTTOM:
                x0 = getPaddingLeft();
                if (getGravity() == (Gravity.CENTER | Gravity.CENTER_HORIZONTAL)) {
                    x0 = (getWidth() - w) / 2;
                }
                y0 = getHeight() - getPaddingBottom() - h;
                break;
        }
        int x1 = x0 + w;
        int y1 = y0 + h;
        x0 -= mDrawableClickPadding;
        y0 -= mDrawableClickPadding;
        x1 += mDrawableClickPadding;
        y1 += mDrawableClickPadding;
        return x0 < x1 && y0 < y1 &&
                x >= x0 && x <= x1 &&
                y >= y0 && y <= y1;
    }

    public interface OnDrawablesClickListener {
        void onDrawablesClick(int drawable);
    }
}
