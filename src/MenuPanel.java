import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private final ArrayList<Weapon> weapons = new ArrayList<>();
    private final JButton startButton;
    private final PlayerPanel player1;
    private final PlayerPanel player2;

    public MenuPanel() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLayout(null);

        // Создаем панели для игроков
        // Располагаем player1Panel от 5% до 40% по оси X, и от 5% до 80% по оси Y
        int player1X = (int) (screenWidth * 0.05);
        int player1Y = (int) (screenHeight * 0.05);
        int player1Width = (int) (screenWidth * 0.35); // Ширина от 5% до 40%
        int player1Height = (int) (screenHeight * 0.75);
        this.player1 = new PlayerPanel(player1X, player1Y, player1Width, player1Height, "Игрок 1");
        add(this.player1);

        // Располагаем player2Panel от 60% до 95% по оси X, и от 5% до 80% по оси Y
        int player2X = (int) (screenWidth * 0.6);
        int player2Y = (int) (screenHeight * 0.05);
        int player2Width = (int) (screenWidth * 0.35); // Ширина от 60% до 95%
        int player2Height = (int) (screenHeight * 0.75);
        this.player2 = new PlayerPanel(player2X, player2Y, player2Width, player2Height, "Игрок 2");
        add(this.player2);

        this.startButton = new JButton("Начать игру");
        this.startButton.setBackground(Color.GREEN);
        this.startButton.setForeground(Color.BLACK); // Черный текст
        this.startButton.setFont(new Font("Arial", Font.BOLD, 20));

        int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonX = (screenWidth - buttonWidth) / 2;
        int buttonY = (int) (screenHeight * 0.4); // Поднимаем выше
        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        add(startButton);

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    public Weapon getPlayer1Weapon() throws IOException {
        return this.player1.getChosenWeapon();
    }

    public Weapon getPlayer2Weapon() throws IOException {
        return this.player2.getChosenWeapon();
    }
}
