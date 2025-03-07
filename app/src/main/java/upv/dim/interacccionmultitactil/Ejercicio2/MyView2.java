package upv.dim.interacccionmultitactil.Ejercicio2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import upv.dim.interacccionmultitactil.Line;

public class MyView2 extends View {

    Random random = new Random();
    Paint paint = new Paint();
    float prevX , prevY , newX , newY;
    int color = Color. BLACK;
    private ArrayList<Line> lines = new ArrayList<>();

    public MyView2(Context context, AttributeSet attrs) {
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
        canvas.drawLine(this.prevX, this.prevY, this.newX,this.newY, this.paint);
        for (Line l : lines) {
            paint.setColor(l.getColor());
            canvas.drawLine(l.getX_ini(), l.getY_ini(), l.getX_fin(), l.getY_fin(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();
                this.color = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
                break;
            case MotionEvent.ACTION_MOVE:
                newX = event.getX();
                newY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                Line l = new Line(prevX, prevY, newX, newY, color);
                lines.add(l);
                newX = newY = prevY = prevX = -1;
                break;
        }
        return true;
    }
}
