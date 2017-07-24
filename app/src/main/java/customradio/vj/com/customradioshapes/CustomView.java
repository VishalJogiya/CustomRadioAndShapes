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

import static java.lang.Math.min;

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
    private static final int STAR_FILLED = 18;

    private int color;
    private int backgroundColor;
    private Path path;
    private final int strokeWidth;
    private Paint paintObject;
    private Bitmap icon;
    private int mWidth, mHeight;
    private int shape = SIMPLE_CIRCLE;
    private int starPoints = 5;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        try {
            color = ta.getColor(R.styleable.CustomView_viewColor, Color.RED);
            backgroundColor = ta.getColor(R.styleable.CustomView_bg_color, Color.TRANSPARENT);
            shape = ta.getInteger(R.styleable.CustomView_viewShape, SIMPLE_CIRCLE);
            strokeWidth = ta.getInteger(R.styleable.CustomView_strokeWidth, 2);
            starPoints = ta.getInteger(R.styleable.CustomView_star_points, 5);

        } finally {
            ta.recycle();
        }

        initilizePaint();

    }


    public CustomView(Context context, int shape) {
        super(context);
        this.shape = shape;
        strokeWidth = 2;
        initilizePaint();
    }

    public CustomView(Context context, int shape, int strokeWidth) {
        super(context);
        this.shape = shape;
        this.strokeWidth = strokeWidth;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }


    public static void drawTriangle(Canvas canvas, Paint paint, int x, int y, Path path, int width, int mHeight, int strokeWidth) {
        int halfWidth = width / 2;
        float mid = width / 2;
        float min = min(width, mHeight);
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
        float min = min(mWidth, mHeight);
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


    /**
     * Below Method is Used From this blog
     * http://android-er.blogspot.com/2014/05/draw-star-on-canvas.html
     */
    public static void setStar(Canvas canvas, Paint paintObject, Path path, float x, float y, float radius, float innerRadius, int numOfPt) {

        double section = 2.0 * Math.PI / numOfPt;

        path.reset();
        path.moveTo(
                (float) (x + radius * Math.cos(0)),
                (float) (y + radius * Math.sin(0)));
        path.lineTo(
                (float) (x + innerRadius * Math.cos(0 + section / 2.0)),
                (float) (y + innerRadius * Math.sin(0 + section / 2.0)));

        for (int i = 1; i < numOfPt; i++) {
            path.lineTo(
                    (float) (x + radius * Math.cos(section * i)),
                    (float) (y + radius * Math.sin(section * i)));
            path.lineTo(
                    (float) (x + innerRadius * Math.cos(section * i + section / 2.0)),
                    (float) (y + innerRadius * Math.sin(section * i + section / 2.0)));
        }

        path.close();
        canvas.drawPath(path, paintObject);

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

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = (mWidth - icon.getWidth()) >> 1; // same as (...) / 2
        int cy = (mHeight - icon.getHeight()) >> 1;

        switch (shape) {
            case SIMPLE_CIRCLE:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                break;

            case SIMPLE_CIRCLE2:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                paintObject.setStyle(Paint.Style.STROKE);
                paintObject.setStrokeWidth(3);
                paintObject.setColor(Color.WHITE);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2) - 5, paintObject);
                break;
            case RING:
                paintObject.setStyle(Paint.Style.STROKE);
                paintObject.setColor(color);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2), paintObject);
                break;
            case SQUARE:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(color);
                canvas.drawPaint(paintObject);
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

                break;

            case RHOMBUS1:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(color);

                canvas.drawPaint(paintObject);
                paintObject.setColor(Color.WHITE);
                paintObject.setStyle(Paint.Style.STROKE);
                paintObject.setStrokeWidth(strokeWidth);
                canvas.drawRect(3, 3, getWidth() - 3, getHeight() - 3, paintObject);

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

            case SQUARE2:
                paintObject.setStyle(Paint.Style.STROKE);
                paintObject.setColor(color);
                canvas.drawPaint(paintObject);

                break;
            case STAR2:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(backgroundColor);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);
                paintObject.setColor(color);
                drawStar(canvas, paintObject, path, getWidth(), getHeight(), strokeWidth);
                break;

            case STAR_FILLED:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(color);
                setStar(canvas, paintObject, path, getWidth() / 2, getHeight() / 2, getWidth() / 2, getWidth() / 6, starPoints);
                break;

            case TRIANGLE2:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(backgroundColor);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);
                paintObject.setColor(color);
                drawTriangle(canvas, paintObject, getWidth() / 2, (getHeight() / 2) - 3, path, getWidth() - 30, getHeight(), strokeWidth);

                break;

            case HEART2:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(Color.WHITE);
                paintObject.setColor(color);
                drawHeart(canvas, paintObject, path, getWidth(), getHeight());
                break;

            case ROUNDED_RECTANGLE2:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(Color.WHITE);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);
                paintObject.setColor(color);
                drawRoundRectangle(canvas, paintObject, getWidth(), getHeight());
                break;


            case RHOMBUS3:
                paintObject.setStyle(Paint.Style.STROKE);
                paintObject.setStrokeWidth(strokeWidth);
                paintObject.setColor(color);
                canvas.drawPaint(paintObject);
                paintObject.setStyle(Paint.Style.FILL);
                drawRhombus(canvas, paintObject, getWidth() / 2, getHeight() / 2, getWidth() / 2);
                break;

            case RHOMBUS4:
                paintObject.setStyle(Paint.Style.FILL);
                paintObject.setColor(backgroundColor);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 1.5), paintObject);
                paintObject.setColor(color);
                drawRhombus(canvas, paintObject, getWidth() / 2, getHeight() / 2, getWidth());
                break;

        }
    }
}
