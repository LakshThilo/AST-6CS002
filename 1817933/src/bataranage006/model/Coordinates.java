package bataranage006.model;

public class Coordinates {

    private int x_cordinate;
    private int y_cordinate;
    private int diameter;
    private int width;
    private int height;
    private int num;

    public Coordinates() {

    }
    public Coordinates(int x_cordinate, int y_cordinate, int width, int height) {

        this.x_cordinate = x_cordinate;
        this.y_cordinate = y_cordinate;
        this.diameter = diameter;
        this.width = width;
        this.height = height;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public int getX_cordinate() {
        return x_cordinate;
    }

    public void setX_cordinate(int x_cordinate) {
        this.x_cordinate = x_cordinate;
    }

    public int getY_cordinate() {
        return y_cordinate;
    }

    public void setY_cordinate(int y_cordinate) {
        this.y_cordinate = y_cordinate;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
