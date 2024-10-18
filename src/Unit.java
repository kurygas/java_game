import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Unit implements Moving, Drawing {
    public int x = 0;
    public int y = 0;
    public int speed = 0;
    public double angle = 0;
    public BufferedImage image;
    public Direction direction;

    public Unit(int x, int y, int speed, double angle, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.image = image;
        this.direction = new Direction();
    }
}
