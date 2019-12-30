package upv.dim.interacccionmultitactil.Ejercicio4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

import upv.dim.interacccionmultitactil.Circle;


public class MyView4 extends View {

    Paint paint = new Paint();
    int color = Color.BLUE;
    float circleRadius = 120;
    float x, y;
    private HashMap<Integer, Circle> circles = new HashMap<>();

    public MyView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Integer i : circles.keySet()) {
            Circle circle = circles.get(i);
            if (circle != null) {
                paint.setColor(color);
                canvas.drawCircle(circle.getX(), circle.getY(), circleRadius, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN: //Contiene el mismo codigo que action_pointer_down
            case MotionEvent.ACTION_POINTER_DOWN:
                x = event.getX(index);
                y = event.getY(index);
                circles.put(id, new Circle(x, y, color));
                break;

            case MotionEvent.ACTION_MOVE:
                for(int i = 0; i < event.getPointerCount(); i++) {
                    Circle circle = new Circle(event.getX(i), event.getY(i), color);
                    circles.put(i, circle);
                }
                break;

            case MotionEvent.ACTION_UP:
                circles.clear(); //Vaciamos por completo el HashMap
                break;

            case MotionEvent.ACTION_POINTER_UP:
                circles.remove(id);
                break;
        }
        invalidate();
        return true;
    }
}
