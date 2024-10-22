import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    private final GamePanel game;
    private final MenuPanel menu;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public GameFrame() throws HeadlessException, IOException {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(cardLayout);

        this.game = new GamePanel();
        this.menu = new MenuPanel();

        this.cardPanel.add(menu, "MenuPanel");
        this.cardPanel.add(game, "GamePanel");

        add(this.cardPanel);

        this.menu.getStartButton().addActionListener(_ -> {
            this.cardLayout.show(this.cardPanel, "GamePanel");

            try {
                this.game.startGame(this.menu.getPlayer1Weapon(), this.menu.getPlayer2Weapon());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.game.requestFocusInWindow();
        });

        this.game.getMenuButton().addActionListener(_ -> {
            this.game.endGame();
            this.cardLayout.show(this.cardPanel, "MenuPanel");
            this.game.endGame();
            this.menu.requestFocusInWindow();
        });

        setVisible(true);
    }
}
