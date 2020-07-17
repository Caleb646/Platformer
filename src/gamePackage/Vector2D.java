package gamePackage;

public class Vector2D {
    private float x;
    private float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Vector2D v) {
        return Math.sqrt((Math.pow(getX()-v.getX(),2))/Tiles.tileWidth + (Math.pow(getY()-v.getY(),2))/Tiles.tileHeight);
    }

    public void addX(float x) {
        this.x += x;
    }

    public void addY(float y) {
        this.y += y;
    }

    //getters and setters

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setXandY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString() {
        return "("+x+", "+y+")";
    }
}