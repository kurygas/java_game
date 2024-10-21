import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame extends JFrame {
    private GamePanel game;
    private MenuPanel menu;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public GameFrame() throws HeadlessException {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        game = new GamePanel(this);
        menu = new MenuPanel();

        cardPanel.add(menu, "MenuPanel");
        cardPanel.add(game, "GamePanel");

        add(cardPanel);

        menu.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Переключение на игровой экран");
                System.out.println(menu.getPlayer1Weapon());
                System.out.println(menu.getPlayer2Weapon());

                try {
                    game.setPlayer1Weapon(menu.getPlayer1Weapon());
                    game.setPlayer2Weapon(menu.getPlayer2Weapon());

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                cardLayout.show(cardPanel, "GamePanel");
                game.requestFocusInWindow();
            }
        });

        setVisible(true);
    }

    public void SwitchToMenu() throws IOException {
        System.out.println("Переключение на меню");
        // game.Restart();
        cardLayout.show(cardPanel, "MenuPanel");
        menu.requestFocusInWindow();
    }
}
