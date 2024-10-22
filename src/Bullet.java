import java.awt.*;

public class Bullet extends Unit {
    private final int damage;

    public Bullet(int x, int y, int speed, double angle, Image image, int damage) {
        super(x, y, speed, angle, image);
        this.damage = damage;
    }

    @Override
    public void draw(Graphics g) {
        rotate(g, this.angle - Math.PI / 2);
    }

    @Override
    public void move() {
        this.x += (int) (Math.sin(this.angle) * this.speed);
        this.y -= (int) (Math.cos(this.angle) * this.speed);
    }

    public void toAttack(HealthPoint enemy) {
        enemy.setHP(enemy.getHP() - this.damage);
    }

    public boolean onScreen() {
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return x >= 0 && x <= screenSize.width && y >= 0 && y <= screenSize.height;
    }
}
