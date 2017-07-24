package customradio.vj.com.customradioshapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Main on 7/6/2017.
 */

public class CustomView extends View {

    public static final int SIMPLE_CIRCLE = 1;
    public static final int SIMPLE_CIRCLE2 = 17;
    public static final int SQUARE = 2;
    public static final int STAR = 3;
    public static final int TRIANGLE = 4;
    public static final int HEART = 5;
    public static final int ROUNDED_RECTANGLE = 6;
    public static final int RHOMBUS1 = 7;
    public static final int RHOMBUS2 = 8;
    private static final int SQUARE2 = 9;
    private static final int STAR2 = 10;
    private static final int TRIANGLE2 = 11;
    private static final int HEART2 = 12;
    private static final int ROUNDED_RECTANGLE2 = 13;
    private static final int RHOMBUS3 = 14;
    private static final int RHOMBUS4 = 15;
    private static final int RING = 16;


    private final int color;
    private final Path path;
    private final int strokeWidth;
    private Paint piePaint;
    private Bitmap icon;
    private int mWidth, mHeight;
    private int shape = SIMPLE_CIRCLE;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        try {
            color = ta.getColor(R.styleable.CustomView_viewColor, Color.RED);
            shape = ta.getInteger(R.styleable.CustomView_viewShape, SIMPLE_CIRCLE);
            strokeWidth = ta.getInteger(R.styleable.CustomView_strokeWidth, 2);
        } finally {
            ta.recycle();
        }


        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setAntiAlias(true);
        piePaint.setDither(true);
        piePaint.setStyle(Paint.Style.STROKE);
        piePaint.setColor(Color.BLUE);

        icon = BitmapFactory.decodeResource(getResources(), R.drawable.check);
        path = new Path();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }


    public static void drawTriangle(Canvas canvas, Paint paint, int x, int y, Path path, int width, int mHeight, int strokeWidth) {
        int halfWidth = width / 2;
        float mid = width / 2;
        float min = Math.min(width, mHeight);
        float fat = min / 17;
        float half = min / 2;
        float rad = half - fat;
        mid = mid - half;

        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);


        path.reset();

        paint.setStyle(Paint.Style.FILL);


        path = new Path();
        path.moveTo(x, y - (halfWidth)); // Top
        path.lineTo(x - (halfWidth), y + (halfWidth)); // Bottom left
        path.lineTo(x + (halfWidth), y + (halfWidth)); // Bottom right
        path.lineTo(x, y - (halfWidth)); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }

    public static void drawRhombus(Canvas canvas, Paint paint, int x, int y, int width) {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y + halfWidth); // Top
        path.lineTo(x - halfWidth, y); // Left
        path.lineTo(x, y - halfWidth); // Bottom
        path.lineTo(x + halfWidth, y); // Right
        path.lineTo(x, y + halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }

    public static void drawRoundRectangle(Canvas canvas, Paint paint, int mWidth, int mHeight) {
        // draw round rectangle
        RectF rectf = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rectf, 15, 15, paint);
    }


    public static void drawStar(Canvas canvas, Paint paint, Path path, int mWidth, int mHeight, int strokeWidth) {
        float mid = mWidth / 2;
        float min = Math.min(mWidth, mHeight);
        float fat = min / 17;
        float half = min / 2;
        float rad = half - fat;
        mid = mid - half;

        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(mid + half, half, rad, paint);

        path.reset();

        paint.setStyle(Paint.Style.FILL);


        // top left
        path.moveTo(mid + half * 0.5f, half * 0.84f);
        // top right
        path.lineTo(mid + half * 1.5f, half * 0.84f);
        // bottom left
        path.lineTo(mid + half * 0.68f, half * 1.45f);
        // top tip
        path.lineTo(mid + half * 1.0f, half * 0.5f);
        // bottom right
        path.lineTo(mid + half * 1.32f, half * 1.45f);
        // top left
        path.lineTo(mid + half * 0.5f, half * 0.84f);

        path.close();
        canvas.drawPath(path, paint);
    }


    public static void drawHeart(Canvas canvas, Paint paint, Path path, int mWidth, int mHeight) {

        // Starting point
        path.moveTo((float) mWidth / 2, (float) mHeight / 5);

        // Upper left path
        path.cubicTo(5 * (float) mWidth / 14, 0,
                0, (float) mHeight / 15,
                (float) mWidth / 28, 2 * (float) mHeight / 5);

        // Lower left path
        path.cubicTo((float) mWidth / 14, 2 * (float) mHeight / 3,
                3 * (float) mWidth / 7, 5 * (float) mHeight / 6,
                (float) mWidth / 2, (float) mHeight);

        // Lower right path
        path.cubicTo(4 * (float) mWidth / 7, 5 * (float) mHeight / 6,
                13 * (float) mWidth / 14, 2 * (float) mHeight / 3,
                27 * (float) mWidth / 28, 2 * (float) mHeight / 5);

        // Upper right path
        path.cubicTo((float) mWidth, (float) mHeight / 15,
                9 * (float) mWidth / 14, 0,
                (float) mWidth / 2, (float) mHeight / 5);

//        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = (mWidth - icon.getWidth()) >> 1; // same as (...) / 2
        int cy = (mHeight - icon.getHeight()) >> 1;

        switch (shape) {
            case SIMPLE_CIRCLE:
                piePaint.setStyle(Paint.Style.FILL);
                piePaint.setColor(color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                break;

            case SIMPLE_CIRCLE2:
                piePaint.setStyle(Paint.Style.FILL);
                piePaint.setColor(color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                piePaint.setStyle(Paint.Style.STROKE);
                piePaint.setStrokeWidth(3);
                piePaint.setColor(Color.WHITE);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2) - 5, piePaint);
                break;
            case RING:
                piePaint.setStyle(Paint.Style.STROKE);
                piePaint.setColor(color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                break;
            case SQUARE:
                piePaint.setStyle(Paint.Style.FILL);
                piePaint.setColor(color);
                canvas.drawPaint(piePaint);
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

                break;

            case RHOMBUS1:
                piePaint.setStyle(Paint.Style.FILL);
                piePaint.setColor(color);

                canvas.drawPaint(piePaint);
                piePaint.setColor(Color.WHITE);
                piePaint.setStyle(Paint.Style.STROKE);
                piePaint.setStrokeWidth(strokeWidth);
                canvas.drawRect(3, 3, getWidth() - 3, getHeight() - 3, piePaint);

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

            case SQUARE2:
                piePaint.setStyle(Paint.Style.STROKE);
                piePaint.setColor(color);
                canvas.drawPaint(piePaint);

                break;
            case STAR2:
                piePaint.setStyle(Paint.Style.STROKE);
                piePaint.setColor(Color.WHITE);
                piePaint.setColor(color);
                drawStar(canvas, piePaint, path, getWidth(), getHeight(), strokeWidth);
                break;
            case TRIANGLE2:
                piePaint.setStyle(Paint.Style.STROKE);
                piePaint.setColor(Color.WHITE);
                piePaint.setColor(color);
                drawTriangle(canvas, piePaint, getWidth() / 2, (getHeight() / 2) - 3, path, getWidth() - 30, getHeight(), strokeWidth);

                break;
            case HEART2:
                piePaint.setStyle(Paint.Style.FILL);
                piePaint.setColor(Color.WHITE);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), piePaint);
                piePaint.setColor(color);
                drawHeart(canvas, piePaint, path, getWidth(), getHeight());
                break;
            case ROUNDED_RECTANGLE2:
                piePaint.setStyle(Paint.Style.FILL);
                piePaint.setColor(Color.WHITE);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), piePaint);
                piePaint.setColor(color);
                drawRoundRectangle(canvas, piePaint, getWidth(), getHeight());
                break;


            case RHOMBUS3:
                piePaint.setStyle(Paint.Style.STROKE);
                piePaint.setStrokeWidth(strokeWidth);
                piePaint.setColor(color);
                canvas.drawPaint(piePaint);
                piePaint.setStyle(Paint.Style.FILL);
                drawRhombus(canvas, piePaint, getWidth() / 2, getHeight() / 2, getWidth() / 2);
                break;

            case RHOMBUS4:
                piePaint.setStyle(Paint.Style.FILL);
                piePaint.setColor(Color.WHITE);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), piePaint);
                piePaint.setColor(color);
                drawRhombus(canvas, piePaint, getWidth() / 2, getHeight() / 2, getWidth());
                break;
        }
    }
}
