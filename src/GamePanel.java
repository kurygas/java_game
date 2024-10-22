import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends AbstractPanel implements KeyListener, ActionListener {
    private Ship player1;
    private Ship player2;
    private final Image spaceImage;
    private Ship winner;
    private final JButton menuButton = new JButton("Вернуться в меню");
    private String winnerLabel = "";
    private final Timer timer = new Timer(50, this);
    private final Image shipImage;
    private final Font labelFont = new Font("Arial", Font.BOLD, 60);

    public GamePanel() {
        addKeyListener(this);
        setLayout(null);

        try {
            this.spaceImage = ImageIO.read(new File("src/space.png")).getScaledInstance(screenSize.width,
                    this.screenSize.height, Image.SCALE_DEFAULT);
            this.shipImage = ImageIO.read(new File("src/ship.png")).getScaledInstance(100, 100,
                    Image.SCALE_DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.menuButton.setVisible(false);
        var buttonWidth = 300;
        var buttonHeight = 100;
        var buttonX = (this.screenSize.width - buttonWidth) / 2;
        var buttonY = (int) (this.screenSize.height * 0.6);
        this.menuButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        this.menuButton.setBackground(Color.GREEN);
        this.menuButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(this.menuButton);

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.spaceImage, 0, 0, null);
        g.setFont(this.labelFont);
        g.setColor(Color.WHITE);
        var labelWidth = 35 * this.winnerLabel.length();
        var labelX = (this.screenSize.width - labelWidth) / 2;
        var labelY = (int) (this.screenSize.height * 0.4);
        g.drawString(this.winnerLabel, labelX, labelY);

        if (this.winner == null) {
            this.player1.draw(g);
            this.player2.draw(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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

            if (this.winner == null && (this.player1.isDead() || this.player2.isDead())) {
                this.menuButton.setVisible(true);

                if (this.player1.isDead()) {
                    this.winner = this.player2;
                    this.winnerLabel = "Игрок 2 победил!";
                } else {
                    this.winner = this.player1;
                    this.winnerLabel = "Игрок 1 победил!";
                }
            }
        }

        repaint();
    }

    public JButton getMenuButton() {
        return this.menuButton;
    }

    public void endGame() {
        this.timer.stop();
        this.winner = null;
    }

    public void startGame(Weapon player1Weapon, Weapon player2Weapon) {
        var yPos = screenSize.height / 2 - shipImage.getHeight(null) / 2;
        this.player1 = new Ship(25, yPos, 10, Math.PI / 2, shipImage, player1Weapon, 18, Color.BLUE,
                "Player 1");
        this.player2 = new Ship(screenSize.width - 125, yPos, 10, -Math.PI / 2, shipImage,
                player2Weapon, 18, Color.RED, "Player 2");
        this.player1.addEnemy(this.player2);
        this.player2.addEnemy(this.player1);
        this.timer.start();
        this.menuButton.setVisible(false);
        this.winnerLabel = "";
    }
}
