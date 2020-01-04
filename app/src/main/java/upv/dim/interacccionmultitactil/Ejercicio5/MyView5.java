package upv.dim.interacccionmultitactil.Ejercicio5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MyView5 extends View implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    Random random = new Random();
    Paint paint = new Paint();
    int color = Color.BLUE;
    float x, y, xTouch, yTouch;;
    private GestureDetector gestureDetector;
    private Integer numSquares = 0;
    private HashMap<Integer, Rect> squares = new HashMap<>();
    private HashMap<Integer, Integer> colors = new HashMap<>();
    private Rect rect = new Rect();
    private ScaleGestureDetector scaleDetector;
    private float scaleFactor = 1.f;

    public MyView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, this);
        gestureDetector.setOnDoubleTapListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Integer i : squares.keySet()) {
            Rect rect = squares.get(i);
            paint.setColor(colors.get(i));
            if (rect != null) {
                canvas.drawRect(rect, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        scaleDetector.onTouchEvent(event);

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                xTouch = event.getX();
                yTouch = event.getY();
                rect = getRectTouched((int)xTouch, (int)yTouch);
                break;

            case MotionEvent.ACTION_MOVE:
                xTouch = event.getX();
                yTouch = event.getY();
                if (rect != null) {
                    Integer key = getKey(rect);
                    if(scaleDetector.isInProgress()) {
                        xTouch = scaleDetector.getFocusX();
                        yTouch = scaleDetector.getFocusY();
                        rect = new Rect((int) xTouch - 100, (int) yTouch - 100, (int) xTouch + 100, (int) yTouch + 100);
                        squares.put(key, rect);
                    }
                    else {
                        rect = new Rect((int) xTouch - 100, (int) yTouch - 100, (int) xTouch + 100, (int) yTouch + 100);
                        squares.put(key, rect);
                    }
                }
                break;
        }

        invalidate();
        return true;
    }

    private Integer getKey (Rect rect){
        for (Map.Entry<Integer, Rect> entry : squares.entrySet()) {
            if (entry.getValue().equals(rect)) {
                Integer k = entry.getKey();
                return k;
            }
        }
        return null;
    }

    private Rect getRectTouched(final int xTouch, final int yTouch) {
        for(Integer i : squares.keySet()) {
            Rect rect = squares.get(i);
            if (xTouch > rect.left && xTouch < rect.right && yTouch < rect.bottom && yTouch > rect.top) {
                return rect;
            }
        }
        return null;
    }

    @Override
    public boolean onDoubleTap (MotionEvent event){
        x = event.getX();
        y = event.getY();
        color = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        Rect r = new Rect((int) x - 100, (int) y - 100, (int) x + 100, (int) y + 100);
        squares.put(numSquares,r);
        colors.put(numSquares,color);
        numSquares++;
        return true;
    }

    @Override
    public boolean onDown (MotionEvent event){
        return false;
    }

    @Override
    public boolean onFling (MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
        return false;
    }

    @Override
    public void onLongPress (MotionEvent event){
    }

    @Override
    public boolean onScroll (MotionEvent event1, MotionEvent event2, float distanceX, float distanceY){
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed (MotionEvent event){
        return false;
    }

    @Override
    public void onShowPress (MotionEvent event){
    }

    @Override
    public boolean onSingleTapUp (MotionEvent event){
        return false;
    }

    @Override
    public boolean onDoubleTapEvent (MotionEvent event){
        return false;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));

            invalidate();
            return true;
        }
    }
}