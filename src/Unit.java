import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Unit implements Moving, Drawing {
    protected int x = 0;
    protected int y = 0;
    protected int speed = 0;
    protected double angle = 0;
    protected Image image;
    protected Direction direction;

    public Unit(int x, int y, int speed, double angle, Image image) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.image = image;
        this.direction = new Direction();
    }
}
