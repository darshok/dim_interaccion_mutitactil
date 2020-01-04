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
import upv.dim.interacccionmultitactil.Finger;
import upv.dim.interacccionmultitactil.Line;


public class MyView4 extends View {

    Paint paint = new Paint();
    int blue = Color.BLUE;
    int green = Color.GREEN;
    float circleRadius = 120;
    double distance1_2 = 0, distance2_3 = 0, distance3_1 = 0;
    float x, y;
    private HashMap<Integer, Circle> circles = new HashMap<>();
    private HashMap<Integer, Finger> fingers = new HashMap<>();


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
            Finger finger1 = fingers.get(i);
            Finger finger2 = fingers.get(i+1);
            Finger finger3 = fingers.get(i+2);
            if (circle != null) {
                paint.setColor(blue);
                canvas.drawCircle(circle.getX(), circle.getY(), circleRadius, paint);
            }
            if (finger2 != null && finger3 != null && i<1) { //si se detectan los 3 dedos, entonces dibujamos
                paint.setColor(green);
                distance1_2 = Math.hypot(finger2.getX() - finger1.getX(), finger2.getY() - finger1.getY());
                distance2_3 = Math.hypot(finger3.getX() - finger2.getX(), finger3.getY() - finger2.getY());
                distance3_1 = Math.hypot(finger3.getX() - finger1.getX(), finger3.getY() - finger1.getY());
                if (distance1_2 != 0 && distance2_3 != 0 && distance3_1 != 0 && distance1_2 < distance2_3 && distance1_2 < distance3_1){ //dibujammos la linea de 1 a 2 si la distancia entre estos es la menor
                    canvas.drawLine(finger1.getX(), finger1.getY(), finger2.getX(), finger2.getY(), paint);
                    canvas.drawLine((finger1.getX()+finger2.getX())/2, (finger1.getY()+finger2.getY())/2, finger3.getX(), finger3.getY(), paint);
                }
                else if (distance1_2 != 0 && distance2_3 != 0 && distance3_1 != 0 && distance2_3 < distance1_2 && distance2_3 < distance3_1) { //dibujamos la linea de 2 a 3 si la distancia entre estos es la menor
                    canvas.drawLine(finger3.getX(), finger3.getY(), finger2.getX(), finger2.getY(), paint);
                    canvas.drawLine((finger3.getX()+finger2.getX())/2, (finger3.getY()+finger2.getY())/2, finger1.getX(), finger1.getY(), paint);
                }
                else { //dibujamos la linea de 3 a 1
                    canvas.drawLine(finger3.getX(), finger3.getY(), finger1.getX(), finger1.getY(), paint);
                    canvas.drawLine((finger3.getX()+finger1.getX())/2, (finger3.getY()+finger1.getY())/2, finger2.getX(), finger2.getY(), paint);
                }

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        int numPointer = event.getPointerCount();

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN: //Contiene el mismo codigo que action_pointer_down
            case MotionEvent.ACTION_POINTER_DOWN:
                if(numPointer <= 3){
                    x = event.getX(index);
                    y = event.getY(index);
                    circles.put(id, new Circle(x, y, blue));
                    fingers.put(id, new Finger(x, y));
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (numPointer <= 3){
                    for(int i = 0; i < numPointer; i++) {
                        x = event.getX(i);
                        y = event.getY(i);
                        Circle circle = new Circle(x, y, blue);
                        Finger finger = new Finger(x, y);
                        circles.put(i, circle);
                        fingers.put(i, finger);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                circles.clear(); //Vaciamos por completo el HashMap
                fingers.clear();
                break;

            case MotionEvent.ACTION_POINTER_UP:
                circles.remove(id);
                fingers.remove(id);
                break;
        }
        invalidate();
        return true;
    }
}
