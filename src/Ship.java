import java.awt.*;
import java.awt.image.BufferedImage;

public class Ship extends Unit implements HealthPoint {
    private final Weapon weapon;
    private int currentHp;
    private final int maxHp;
    private final Color color;

    public Ship(int x, int y, int speed, double angle, Image image, Weapon weapon, int hp, Color color) {
        super(x, y, speed, angle, image);
        this.weapon = weapon;
        this.currentHp = hp - 1;
        this.maxHp = hp;
        this.color = color;
    }

    @Override
    public void move() {
        var dx = (int) (Math.sin(this.angle) * this.speed);
        var dy = (int) (Math.cos(this.angle) * this.speed);

        if (this.direction.up) {
            this.x += dx;
            this.y -= dy;
        }
        if (this.direction.down) {
            this.x -= dx;
            this.y += dy;
        }
        if (this.direction.left) {
            this.angle -= 0.1;
        }
        if (this.direction.right) {
            this.angle += 0.1;
        }

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        var xCenter = this.x + (double) this.image.getWidth(null) / 2;
        var yCenter = this.y + (double) this.image.getHeight(null) / 2;
        graphics2D.rotate(this.angle, xCenter, yCenter);
        graphics2D.drawImage(this.image, this.x, this.y, null);
        graphics2D.rotate(-this.angle, xCenter, yCenter);
        graphics2D.setColor(this.color);
        graphics2D.drawRect(this.x, this.y - 20, this.image.getWidth(null), 10);
        graphics2D.fillRect(this.x, this.y - 20, this.image.getWidth(null) * this.currentHp / this.maxHp, 10);
    }

    @Override
    public int getHP() {
        return this.currentHp;
    }

    @Override
    public void setHP(int healthPoint) {
        this.currentHp = healthPoint;
    }

    @Override
    public boolean isAlive() {
        return this.currentHp > 0;
    }
}
