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

    public abstract void draw(Graphics g);

    protected void rotate(Graphics g, double angle) {
        var graphics2D = (Graphics2D) g;
        var xCenter = this.x + (double) this.image.getWidth(null) / 2;
        var yCenter = this.y + (double) this.image.getHeight(null) / 2;
        graphics2D.rotate(angle, xCenter, yCenter);
        graphics2D.drawImage(this.image, this.x, this.y, null);
        graphics2D.rotate(-angle, xCenter, yCenter);
    }
}
