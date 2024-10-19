import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class Weapon implements ActionListener {
    private LinkedList<Bullet> bullets = new LinkedList<>();
    private final int bulletDamage;
    private final int bulletSpeed;
    private final Timer timer;
    private final Image image;

    public Weapon(int bulletDamage, int bulletSpeed, int cooldown, Image image) {
        this.bulletDamage = bulletDamage;
        this.bulletSpeed = bulletSpeed;
        this.timer = new Timer(cooldown, this);
        this.image = image;
    }

    public void shoot(int x, int y, double angle) {
        if (this.timer.isRunning()) {
            return;
        }
        this.timer.start();
        int bulletSize = this.image.getWidth(null) / 2;
        bullets.addLast(new Bullet(x - bulletSize, y - bulletSize, this.bulletSpeed, angle, this.image,
                this.bulletDamage));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timer.stop();
    }

    public void moveBullets(ArrayList<HealthPoint> enemies) {
        var newBullets = new LinkedList<Bullet>();
        for (var bullet : this.bullets) {
            bullet.move();
            newBullets.addLast(bullet);
            for (var target : enemies) {
                if (target.hasCollision(bullet.x, bullet.y)) {
                    bullet.toAttack(target);
                    newBullets.removeLast();
                    break;
                }
            }
        }
        this.bullets = newBullets;
    }

    public void drawBullets(Graphics g) {
        for (var bullet : this.bullets) {
            bullet.draw(g);
        }
    }
}
