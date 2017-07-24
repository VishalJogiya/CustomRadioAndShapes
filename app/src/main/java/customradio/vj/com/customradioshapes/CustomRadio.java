package customradio.vj.com.customradioshapes;


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

import static customradio.vj.com.customradioshapes.CustomView.HEART;
import static customradio.vj.com.customradioshapes.CustomView.RHOMBUS1;
import static customradio.vj.com.customradioshapes.CustomView.RHOMBUS2;
import static customradio.vj.com.customradioshapes.CustomView.ROUNDED_RECTANGLE;
import static customradio.vj.com.customradioshapes.CustomView.SIMPLE_CIRCLE;
import static customradio.vj.com.customradioshapes.CustomView.SIMPLE_CIRCLE2;
import static customradio.vj.com.customradioshapes.CustomView.SQUARE;
import static customradio.vj.com.customradioshapes.CustomView.STAR;
import static customradio.vj.com.customradioshapes.CustomView.TRIANGLE;
import static customradio.vj.com.customradioshapes.CustomView.drawHeart;
import static customradio.vj.com.customradioshapes.CustomView.drawRhombus;
import static customradio.vj.com.customradioshapes.CustomView.drawRoundRectangle;
import static customradio.vj.com.customradioshapes.CustomView.drawStar;
import static customradio.vj.com.customradioshapes.CustomView.drawTriangle;

public class CustomRadio extends android.support.v7.widget.AppCompatRadioButton {

    private int color;
    private Path path;
    private final int strokeWidth;
    private Paint piePaint;
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
        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setAntiAlias(true);
        piePaint.setDither(true);
        piePaint.setStyle(Paint.Style.STROKE);
        piePaint.setColor(Color.BLUE);

        icon = BitmapFactory.decodeResource(getResources(), R.drawable.check);
        path = new Path();
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public void setPieColor(int color) {
        this.color = color;
    }

    public int getPieColor() {
        return piePaint.getColor();
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
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                    canvas.drawBitmap(icon, cx, cy, null);
                    break;

                case SIMPLE_CIRCLE2:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                    canvas.drawBitmap(icon, cx, cy, null);
                    break;
                case SQUARE:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawPaint(piePaint);
                    canvas.drawBitmap(icon, cx, cy, null);
                    break;
                case STAR:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                    piePaint.setColor(Color.WHITE);
                    piePaint.setStrokeWidth(strokeWidth);

                    piePaint.setStyle(Paint.Style.STROKE);
                    drawStar(canvas, piePaint, path, getWidth(), getHeight(), strokeWidth);
                    break;

                case TRIANGLE:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                    piePaint.setColor(Color.WHITE);
                    piePaint.setStrokeWidth(strokeWidth);
                    piePaint.setStyle(Paint.Style.STROKE);
                    drawTriangle(canvas, piePaint, getWidth() / 2, (getHeight() / 2) - 3, path, getWidth() - 30, getHeight(), strokeWidth);
                    break;

                case HEART:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), piePaint);

                    piePaint.setColor(Color.WHITE);
                    piePaint.setStrokeWidth(strokeWidth);
                    piePaint.setStyle(Paint.Style.STROKE);
                    drawHeart(canvas, piePaint, path, getWidth(), getHeight());
                    break;
                case ROUNDED_RECTANGLE:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    drawRoundRectangle(canvas, piePaint, getWidth(), getHeight());
                    canvas.drawBitmap(icon, cx, cy, null);

                    break;

                case RHOMBUS1:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawPaint(piePaint);

                    piePaint.setColor(Color.WHITE);
                    piePaint.setStyle(Paint.Style.STROKE);
                    piePaint.setStrokeWidth(strokeWidth);
                    canvas.drawRect(3, 3, 45, 45, piePaint);

                    piePaint.setStyle(Paint.Style.FILL);
                    drawRhombus(canvas, piePaint, getWidth() / 2, getHeight() / 2, getWidth() / 2);
                    break;

                case RHOMBUS2:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), piePaint);
                    piePaint.setColor(Color.WHITE);

                    piePaint.setStrokeWidth(strokeWidth);
                    piePaint.setStyle(Paint.Style.STROKE);
                    drawRhombus(canvas, piePaint, getWidth() / 2, getHeight() / 2, getWidth());
                    break;
            }
        } else {

            switch (shape) {
                case SIMPLE_CIRCLE:
//                    piePaint.setColor(Color.WHITE);
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                    break;
                case SIMPLE_CIRCLE2:
                    piePaint.setStyle(Paint.Style.STROKE);
                    piePaint.setStrokeWidth(2);
                    piePaint.setColor(color);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2)-2, piePaint);
                    break;
                case SQUARE:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(color);
                    canvas.drawPaint(piePaint);

                    break;
                case STAR:
                    piePaint.setStyle(Paint.Style.STROKE);
                    piePaint.setColor(Color.WHITE);
                    piePaint.setColor(color);
                    drawStar(canvas, piePaint, path, getWidth(), getHeight(), strokeWidth);
                    break;
                case TRIANGLE:
                    piePaint.setStyle(Paint.Style.STROKE);
                    piePaint.setColor(Color.WHITE);
                    piePaint.setColor(color);
                    drawTriangle(canvas, piePaint, getWidth() / 2, (getHeight() / 2) - 3, path, getWidth() - 30, getHeight(), strokeWidth);

                    break;
                case HEART:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(Color.WHITE);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                    piePaint.setColor(color);
                    drawHeart(canvas, piePaint, path, getWidth(), getHeight());
                    break;
                case ROUNDED_RECTANGLE:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(Color.WHITE);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), piePaint);
                    piePaint.setColor(color);
                    drawRoundRectangle(canvas, piePaint, getWidth(), getHeight());
                    break;


                case RHOMBUS1:
                    piePaint.setStyle(Paint.Style.STROKE);
                    piePaint.setStrokeWidth(5);
                    piePaint.setColor(color);
                    canvas.drawPaint(piePaint);
                    piePaint.setStyle(Paint.Style.FILL);
                    drawRhombus(canvas, piePaint, getWidth() / 2, getHeight() / 2, getWidth() / 2);
                    break;

                case RHOMBUS2:
                    piePaint.setStyle(Paint.Style.FILL);
                    piePaint.setColor(Color.WHITE);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), piePaint);
                    piePaint.setColor(color);
                    drawRhombus(canvas, piePaint, getWidth() / 2, getHeight() / 2, getWidth());
                    break;
            }
        }
    }
}
