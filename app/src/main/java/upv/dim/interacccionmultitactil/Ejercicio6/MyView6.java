package upv.dim.interacccionmultitactil.Ejercicio6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import upv.dim.interacccionmultitactil.Circle;
import upv.dim.interacccionmultitactil.Finger;


public class MyView6 extends View {

    Paint paint = new Paint();
    int color = Color.BLUE;
    float circleRadius = 120;
    float x, y;
    private HashMap<Integer, Circle> circles = new HashMap<>();
    private HashMap<Integer, Finger> fingers = new HashMap<>();


    public MyView6(Context context, AttributeSet attrs) {
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
        for(Integer i : fingers.keySet()){
            Finger finger1 = fingers.get(i);
            Finger finger2 = fingers.get(i+1);
            paint.setColor(color);
            if (finger2 != null)
                canvas.drawLine(finger1.getX(), finger1.getY(), finger2.getX(), finger2.getY(), paint);
            else if (finger1 != null && fingers.get(0) != null)
                canvas.drawLine(finger1.getX(), finger1.getY(), fingers.get(0).getX(), fingers.get(0).getY(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                fingers.clear(); //limpiamos para poder dibujar otra figura
                x = event.getX();
                y = event.getY();
                circles.put(0, new Circle(x, y, color));
                fingers.put(0, new Finger(x, y));
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                x = event.getX(id);
                y = event.getY(id);
                circles.put(id, new Circle(x, y, color));
                fingers.put(id, new Finger(x, y));
                break;

            case MotionEvent.ACTION_MOVE:
                for(int i = 0; i < event.getPointerCount(); i++) {
                    x = event.getX(i);
                    y = event.getY(i);
                    Circle circle = new Circle(x, y, color);
                    Finger finger = new Finger(x, y);
                    circles.put(i, circle);
                    fingers.put(i, finger);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                circles.remove(id);
                break;
        }
        invalidate();
        return true;
    }
}
