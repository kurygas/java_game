import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship orc;
    public Timer timer;

    public GamePanel() {
        addKeyListener(this);
        this.timer = new Timer(50, this);
        this.timer.start();

        try {
            var image = ImageIO.read(new File("src/orc.png"));
            this.orc = new Ship(50, 50, 10, 0, image, null, 1);
        } catch (IOException e) {
            System.out.println("Облом.");
            throw new RuntimeException(e);
        }

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.orc.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.orc.direction.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.orc.direction.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.orc.direction.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.orc.direction.left = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.orc.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.orc.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.orc.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.orc.direction.left = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.orc.move();
        repaint();
    }
}
