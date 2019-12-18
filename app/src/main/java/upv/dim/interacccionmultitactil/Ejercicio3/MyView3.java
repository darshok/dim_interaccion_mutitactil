package upv.dim.interacccionmultitactil.Ejercicio3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import upv.dim.interacccionmultitactil.Line;
import upv.dim.interacccionmultitactil.Point;

public class MyView3 extends View {

    //en un hash map guardar las lineas que se componen de x e y
    Random random = new Random();
    Paint paint = new Paint();
    int color = Color. BLACK;
    private HashMap<Integer, Path> lines = new HashMap<>();
    private HashMap<Integer, Integer> colors = new HashMap<>();

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
        paint.setColor(this.color);
        for(Integer i : lines.keySet()) {
            Path path = lines.get(i);
            if(path != null) {
                paint.setColor(colors.get(i));
                canvas.drawPath(path, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                Path path1 = new Path();
                this.color = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
                path1.moveTo(event.getX(index), event.getY(index));
                lines.put(id,path1);
                colors.put(id, color);
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i = 0; i < event.getPointerCount(); i++) {
                    Path path2 = lines.get(event.getPointerId(i));
                    if (path2 != null) {
                        path2.lineTo(event.getX(i), event.getY(i));
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                lines.remove(id);
                colors.remove(id);
                break;
        }
        invalidate();
        return true;
    }
}
