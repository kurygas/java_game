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
    private final Ship player1;
    private final Ship player2;
    private final Image spaceImage;
    private Ship winner;

    public GamePanel() {
        addKeyListener(this);
        var timer = new Timer(50, this);
        timer.start();

        try {
            var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.spaceImage = ImageIO.read(new File("src/space.png")).getScaledInstance(screenSize.width,
                    screenSize.height, Image.SCALE_DEFAULT);
            var shipImage = ImageIO.read(new File("src/ship.png")).getScaledInstance(100, 100,
                    Image.SCALE_DEFAULT);
            var bulletImage = ImageIO.read(new File("src/bullet.png")).getScaledInstance(50, 50,
                    Image.SCALE_DEFAULT);
            var yPos = screenSize.height / 2 - shipImage.getHeight(null) / 2;
            var weapon1 = new Weapon(1, 20, 1000, bulletImage);
            var weapon2 = new Weapon(1, 20, 1000, bulletImage);
            this.player1 = new Ship(25, yPos, 10, Math.PI / 2, shipImage, weapon1, 10, Color.BLUE,
                    "Player 1");
            this.player2 = new Ship( screenSize.width - 125, yPos, 10, -Math.PI / 2, shipImage, weapon2,
                    10, Color.RED, "Player 2");
            this.player1.addEnemy(this.player2);
            this.player2.addEnemy(this.player1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.spaceImage, 0, 0, null);

        if (this.winner != null) {
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.setColor(Color.WHITE);
            var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            var label = this.winner.getName().concat(" wins!");
            g.drawString(label, screenSize.width / 2 - label.length() * 15,
                    screenSize.height / 2 - 30);
        }
        else {
            this.player1.draw(g);
            this.player2.draw(g);
        }
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
            case KeyEvent.VK_SHIFT -> this.player1.shoot();

            case KeyEvent.VK_UP -> this.player2.direction.up = true;
            case KeyEvent.VK_DOWN -> this.player2.direction.down = true;
            case KeyEvent.VK_RIGHT -> this.player2.direction.right = true;
            case KeyEvent.VK_LEFT -> this.player2.direction.left = true;
            case KeyEvent.VK_SPACE -> this.player2.shoot();
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
        if (this.winner == null && this.player1 != null && this.player2 != null) {
            this.player1.move();
            this.player2.move();

            if (this.player1.getHP() <= 0) {
                this.winner = this.player2;
            }

            if (this.player2.getHP() <= 0) {
                this.winner = this.player1;
            }
        }

        repaint();
    }
}
