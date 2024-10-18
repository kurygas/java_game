import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Unit {
    public int damage;

    public Bullet(int x, int y, int speed, double angle, BufferedImage image, int damage) {
        super(x, y, speed, angle, image);
        this.damage = damage;
    }

    @Override
    public void draw(Graphics g) {}

    @Override
    public void move() {}
}
