package upv.dim.interacccionmultitactil;

public class Line {
    private float x_ini;
    private float y_ini;
    private float x_fin;
    private float y_fin;
    private int color;

    public Line(float x_ini, float y_ini, float x_fin, float y_fin, int color) {
        this.x_ini = x_ini;
        this.y_ini = y_ini;
        this.x_fin = x_fin;
        this.y_fin = y_fin;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getX_ini() {
        return x_ini;
    }

    public void setX_ini(float x_ini) {
        this.x_ini = x_ini;
    }

    public float getY_ini() {
        return y_ini;
    }

    public void setY_ini(float y_ini) {
        this.y_ini = y_ini;
    }

    public float getX_fin() {
        return x_fin;
    }

    public void setX_fin(float x_fin) {
        this.x_fin = x_fin;
    }

    public float getY_fin() {
        return y_fin;
    }

    public void setY_fin(float y_fin) {
        this.y_fin = y_fin;
    }
}
