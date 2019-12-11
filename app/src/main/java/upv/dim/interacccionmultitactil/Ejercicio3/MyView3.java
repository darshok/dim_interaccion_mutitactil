package upv.dim.interacccionmultitactil.Ejercicio3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import upv.dim.interacccionmultitactil.Line;
import upv.dim.interacccionmultitactil.Point;

public class MyView3 extends View {

    //en un hash map guardar las lineas que se componen de x e y
    Random random = new Random();
    Paint paint = new Paint();
    float prevX , prevY , newX , newY;
    int color = Color. BLACK;
    private HashMap<Integer, Path> lines = new HashMap<>();
    private Path path = new Path();

    public MyView3(Context context, AttributeSet attrs) {
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
        for(int i = 0; i < lines.size(); i++) {
            paint.setColor(this.color);
            canvas.drawPath(lines.get(i), paint);
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();
                path.moveTo(prevX, prevY);
                this.color = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
                break;
            case MotionEvent.ACTION_MOVE:
                newX = event.getX();
                newY = event.getY();
                for (int i = 0; i < event.getPointerCount(); i++) {
                    path.lineTo(event.getX(), event.getY());
                    lines.put(i, path);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                newX = newY = prevY = prevX = -1;
                break;
        }
        return true;
    }
}
