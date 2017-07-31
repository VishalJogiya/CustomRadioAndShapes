package customradio.vj.com.library;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import static customradio.vj.com.library.CustomView.HEART;
import static customradio.vj.com.library.CustomView.RHOMBUS1;
import static customradio.vj.com.library.CustomView.RHOMBUS2;
import static customradio.vj.com.library.CustomView.ROUNDED_RECTANGLE;
import static customradio.vj.com.library.CustomView.SIMPLE_CIRCLE;
import static customradio.vj.com.library.CustomView.SIMPLE_CIRCLE2;
import static customradio.vj.com.library.CustomView.SQUARE;
import static customradio.vj.com.library.CustomView.STAR;
import static customradio.vj.com.library.CustomView.TRIANGLE;
import static customradio.vj.com.library.CustomView.drawHeart;
import static customradio.vj.com.library.CustomView.drawRhombus;
import static customradio.vj.com.library.CustomView.drawRoundRectangle;
import static customradio.vj.com.library.CustomView.drawStar;
import static customradio.vj.com.library.CustomView.drawTriangle;

public class CustomRadio extends android.support.v7.widget.AppCompatRadioButton {

    private int color;
    private Path path;
    private final int strokeWidth;
    private Paint paintObject;
    private Bitmap icon;
    private int mWidth, mHeight;
    private int shape = SIMPLE_CIRCLE;


    public CustomRadio(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomRadio, 0, 0);
        try {
            color = ta.getColor(R.styleable.CustomRadio_radioColor, Color.RED);
            shape = ta.getInteger(R.styleable.CustomRadio_radioShape, SIMPLE_CIRCLE);
            strokeWidth = ta.getInteger(R.styleable.CustomView_strokeWidth, 2);
        } finally {
            ta.recycle();
        }
        initilizePaint();
    }

    public CustomRadio(Context context, int shape) {
        super(context);
        this.shape = shape;
        strokeWidth = 2;
        initilizePaint();
    }


    private void initilizePaint() {
        paintObject = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintObject.setAntiAlias(true);
        paintObject.setDither(true);
        paintObject.setStyle(Paint.Style.STROKE);
        paintObject.setColor(Color.BLUE);

        icon = BitmapFactory.decodeResource(getResources(), R.drawable.check);
        path = new Path();
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (this.isChecked()) {


            int cx = (mWidth - icon.getWidth()) >> 1; // same as (...) / 2
            int cy = (mHeight - icon.getHeight()) >> 1;

            switch (shape) {
                case SIMPLE_CIRCLE:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                    canvas.drawBitmap(icon, cx, cy, null);
                    break;

                case SIMPLE_CIRCLE2:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                    canvas.drawBitmap(icon, cx, cy, null);
                    break;
                case SQUARE:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawPaint(paintObject);
                    canvas.drawBitmap(icon, cx, cy, null);
                    break;
                case STAR:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                    paintObject.setColor(Color.WHITE);
                    paintObject.setStrokeWidth(strokeWidth);

                    paintObject.setStyle(Paint.Style.STROKE);
                    drawStar(canvas, paintObject, path, getWidth(), getHeight(), strokeWidth);
                    break;

                case TRIANGLE:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                    paintObject.setColor(Color.WHITE);
                    paintObject.setStrokeWidth(strokeWidth);
                    paintObject.setStyle(Paint.Style.STROKE);
                    drawTriangle(canvas, paintObject, getWidth() / 2, (getHeight() / 2) - 3, path, getWidth() - 30, getHeight(), strokeWidth);
                    break;

                case HEART:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);

                    paintObject.setColor(Color.WHITE);
                    paintObject.setStrokeWidth(strokeWidth);
                    paintObject.setStyle(Paint.Style.STROKE);
                    drawHeart(canvas, paintObject, path, getWidth(), getHeight());
                    break;
                case ROUNDED_RECTANGLE:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    drawRoundRectangle(canvas, paintObject, getWidth(), getHeight());
                    canvas.drawBitmap(icon, cx, cy, null);

                    break;

                case RHOMBUS1:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawPaint(paintObject);

                    paintObject.setColor(Color.WHITE);
                    paintObject.setStyle(Paint.Style.STROKE);
                    paintObject.setStrokeWidth(strokeWidth);
                    canvas.drawRect(3, 3, getWidth() - 3, getHeight()-3, paintObject);

                    paintObject.setStyle(Paint.Style.FILL);
                    drawRhombus(canvas, paintObject, getWidth() / 2, getHeight() / 2, getWidth() / 2);

                    break;

                case RHOMBUS2:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);
                    paintObject.setColor(Color.WHITE);

                    paintObject.setStrokeWidth(strokeWidth);
                    paintObject.setStyle(Paint.Style.STROKE);
                    drawRhombus(canvas, paintObject, getWidth() / 2, getHeight() / 2, getWidth());
                    break;
            }
        } else {

            switch (shape) {
                case SIMPLE_CIRCLE:
//                    paintObject.setColor(Color.WHITE);
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                    break;
                case SIMPLE_CIRCLE2:
                    paintObject.setStyle(Paint.Style.STROKE);
                    paintObject.setStrokeWidth(2);
                    paintObject.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2) - 2, paintObject);
                    break;
                case SQUARE:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(color);
                    canvas.drawPaint(paintObject);

                    break;
                case STAR:
                    paintObject.setStyle(Paint.Style.STROKE);
                    paintObject.setColor(Color.WHITE);
                    paintObject.setColor(color);
                    drawStar(canvas, paintObject, path, getWidth(), getHeight(), strokeWidth);
                    break;
                case TRIANGLE:
                    paintObject.setStyle(Paint.Style.STROKE);
                    paintObject.setColor(Color.WHITE);
                    paintObject.setColor(color);
                    drawTriangle(canvas, paintObject, getWidth() / 2, (getHeight() / 2) - 3, path, getWidth() - 30, getHeight(), strokeWidth);

                    break;
                case HEART:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(Color.WHITE);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                    paintObject.setColor(color);
                    drawHeart(canvas, paintObject, path, getWidth(), getHeight());
                    break;
                case ROUNDED_RECTANGLE:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(Color.WHITE);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);
                    paintObject.setColor(color);
                    drawRoundRectangle(canvas, paintObject, getWidth(), getHeight());
                    break;


                case RHOMBUS1:
                    paintObject.setStyle(Paint.Style.STROKE);
                    paintObject.setStrokeWidth(5);
                    paintObject.setColor(color);
                    canvas.drawPaint(paintObject);
                    paintObject.setStyle(Paint.Style.FILL);
                    drawRhombus(canvas, paintObject, getWidth() / 2, getHeight() / 2, getWidth() / 2);
                    break;

                case RHOMBUS2:
                    paintObject.setStyle(Paint.Style.FILL);
                    paintObject.setColor(Color.WHITE);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);
                    paintObject.setColor(color);
                    drawRhombus(canvas, paintObject, getWidth() / 2, getHeight() / 2, getWidth());
                    break;
            }
        }
    }
}
