import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuPanel extends AbstractPanel {
    private final JButton startButton = new JButton("Начать игру");
    private final PlayerPanel player1;
    private final PlayerPanel player2;

    public MenuPanel() throws IOException {
        setLayout(null);
        // Создаем панели для игроков
        // Располагаем player1Panel от 5% до 40% по оси X, и от 5% до 80% по оси Y
        var player1X = (int) (this.screenSize.width * 0.05);
        var player1Y = (int) (this.screenSize.height * 0.05);
        var player1Width = (int) (this.screenSize.width * 0.35); // Ширина от 5% до 40%
        var player1Height = (int) (this.screenSize.height * 0.75);
        this.player1 = new PlayerPanel(player1X, player1Y, player1Width, player1Height, "Игрок 1");
        add(this.player1);

        // Располагаем player2Panel от 60% до 95% по оси X, и от 5% до 80% по оси Y
        var player2X = (int) (this.screenSize.width * 0.6);
        var player2Y = (int) (this.screenSize.height * 0.05);
        var player2Width = (int) (this.screenSize.width * 0.35); // Ширина от 60% до 95%
        var player2Height = (int) (this.screenSize.height * 0.75);
        this.player2 = new PlayerPanel(player2X, player2Y, player2Width, player2Height, "Игрок 2");
        add(this.player2);

        this.startButton.setBackground(Color.GREEN);
        this.startButton.setForeground(Color.BLACK); // Черный текст
        this.startButton.setFont(new Font("Arial", Font.BOLD, 20));

        var buttonWidth = 200;
        var buttonHeight = 50;
        var buttonX = (this.screenSize.width - buttonWidth) / 2;
        var buttonY = (int) (this.screenSize.height * 0.4); // Поднимаем выше
        this.startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        add(this.startButton);

        setFocusable(true);
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
