import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private final Ship player1;
    private final Ship player2;
    private ArrayList<Unit> bullets;

    public GamePanel() {
        addKeyListener(this);
        var timer = new Timer(50, this);
        timer.start();

        try {
            var image = ImageIO.read(new File("src/orc.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            var yPos = screenSize.height / 2 - image.getHeight(null) / 2;
            this.player1 = new Ship(25, yPos, 10, Math.PI / 2, image, null, 10, Color.GREEN);
            this.player2 = new Ship( screenSize.width - 125, yPos, 10, -Math.PI / 2, image, null, 10, Color.RED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.player1.draw(g);
        this.player2.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> this.player1.direction.up = true;
            case KeyEvent.VK_S -> this.player1.direction.down = true;
            case KeyEvent.VK_D -> this.player1.direction.right = true;
            case KeyEvent.VK_A -> this.player1.direction.left = true;
            case KeyEvent.VK_UP -> this.player2.direction.up = true;
            case KeyEvent.VK_DOWN -> this.player2.direction.down = true;
            case KeyEvent.VK_RIGHT -> this.player2.direction.right = true;
            case KeyEvent.VK_LEFT -> this.player2.direction.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> this.player1.direction.up = false;
            case KeyEvent.VK_S -> this.player1.direction.down = false;
            case KeyEvent.VK_D -> this.player1.direction.right = false;
            case KeyEvent.VK_A -> this.player1.direction.left = false;
            case KeyEvent.VK_UP -> this.player2.direction.up = false;
            case KeyEvent.VK_DOWN -> this.player2.direction.down = false;
            case KeyEvent.VK_RIGHT -> this.player2.direction.right = false;
            case KeyEvent.VK_LEFT -> this.player2.direction.left = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.player1.move();
        this.player2.move();
        repaint();
    }
}
