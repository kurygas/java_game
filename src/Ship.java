import java.awt.*;
import java.awt.image.BufferedImage;

public class Ship extends Unit implements HealthPoint {
    public Weapon weapon;
    public int hp = 1;

    public Ship(int x, int y, int speed, double angle, BufferedImage image, Weapon weapon, int hp) {
        super(x, y, speed, angle, image);
        this.weapon = weapon;
        this.hp = hp;
    }

    @Override
    public void move() {
        var dx = (int) (Math.sin(this.angle) * this.speed);
        var dy = (int) (Math.cos(this.angle) * this.speed);

        if (this.direction.up) {
            x += dx;
            y -= dy;
        }
        if (this.direction.down) {
            x -= dx;
            y += dy;
        }
        if (this.direction.left) {
            angle -= 0.1;
        }
        if (this.direction.right) {
            angle += 0.1;
        }

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        var xCenter = x + (double) image.getWidth(null) / 2;
        var yCenter = y + (double) image.getHeight(null) / 2;
        graphics2D.rotate(angle, xCenter, yCenter);
        g.drawImage(image, x, y, null);
        graphics2D.rotate(-angle, xCenter, yCenter);
    }

    @Override
    public int getHP() {
        return this.hp;
    }

    @Override
    public void setHP(int healthPoint) {
        this.hp = healthPoint;
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }
}
