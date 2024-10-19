import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ship extends Unit implements HealthPoint {
    private final Weapon weapon;
    private int currentHp;
    private final int maxHp;
    private final Color color;
    private final ArrayList<HealthPoint> enemies = new ArrayList<>();
    private final String playerName;

    public Ship(int x, int y, int speed, double angle, Image image, Weapon weapon, int hp, Color color, String name) {
        super(x, y, speed, angle, image);
        this.weapon = weapon;
        this.currentHp = hp;
        this.maxHp = hp;
        this.color = color;
        this.playerName = name;
    }

    public void addEnemy(HealthPoint enemy) {
        enemies.addLast(enemy);
    }

    public void shoot() {
        int imageSize = this.image.getWidth(null) / 2;
        this.weapon.shoot(this.x + imageSize, this.y + imageSize, this.angle);
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

        var imageSize = this.image.getWidth(null);
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        if (this.x + imageSize < 0) {
            this.x = screenSize.width;
        }
        else if (this.x > screenSize.width) {
            this.x = -imageSize;
        }
        if (this.y + imageSize < 0) {
            this.y = screenSize.height;
        }
        else if (this.y > screenSize.height) {
            this.y = -imageSize;
        }

        if (this.weapon != null) {
            this.weapon.moveBullets(this.enemies);
        }
    }

    @Override
    public void draw(Graphics g) {
        rotate(g, angle);
        g.setColor(this.color);
        g.drawRect(this.x, this.y - 20, this.image.getWidth(null), 10);
        g.fillRect(this.x, this.y - 20, this.image.getWidth(null) * this.currentHp / this.maxHp,
                10);
        this.weapon.drawBullets(g);
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

    @Override
    public boolean hasCollision(int x, int y) {
        var rad = (double) this.image.getWidth(null) / 2;
        return Math.pow(x - rad - this.x, 2) + Math.pow(y - rad - this.y, 2) <= Math.pow(rad, 2);
    }

    public String getName() {
        return this.playerName;
    }
}
