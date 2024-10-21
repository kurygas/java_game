import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuPanel extends JPanel {
    private final JPanel player1Panel;
    private final JPanel player2Panel;
    private final JButton startButton;
    private String player1Weapon = "";
    private String player2Weapon = "";

    public MenuPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setLayout(null);

        // Создаем панели для игроков
        this.player1Panel = createPlayerPanel("Игрок 1", true);
        // Располагаем player1Panel от 5% до 40% по оси X, и от 5% до 80% по оси Y
        int player1X = (int) (screenWidth * 0.05);
        int player1Y = (int) (screenHeight * 0.05);
        int player1Width = (int) (screenWidth * 0.35); // Ширина от 5% до 40%
        int player1Height = (int) (screenHeight * 0.75);
        this.player1Panel.setBounds(player1X, player1Y, player1Width, player1Height);
        this.player1Panel.setOpaque(false);
        add(player1Panel);

        this.player2Panel = createPlayerPanel("Игрок 2", false);

        // Располагаем player2Panel от 60% до 95% по оси X, и от 5% до 80% по оси Y
        int player2X = (int) (screenWidth * 0.6);
        int player2Y = (int) (screenHeight * 0.05);
        int player2Width = (int) (screenWidth * 0.35); // Ширина от 60% до 95%
        int player2Height = (int) (screenHeight * 0.75);
        this.player2Panel.setBounds(player2X, player2Y, player2Width, player2Height);
        this.player2Panel.setOpaque(false);
        add(player2Panel);

        this.startButton = new JButton("Начать игру");
        this.startButton.setBackground(Color.GREEN);
        this.startButton.setForeground(Color.BLACK); // Черный текст
        this.startButton.setFont(new Font("Arial", Font.BOLD, 20));

        int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonX = (screenWidth - buttonWidth) / 2;
        int buttonY = (int) (screenHeight * 0.4); // Поднимаем выше
        this.startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        add(startButton);

        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    JButton getButton() {
        return startButton;
    }

    private JPanel createPlayerPanel(String playerName, boolean isPlayer1) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createTitledBorder(null, playerName,
                0, 0, new Font("Arial", Font.BOLD, 26), Color.BLACK));
        playerPanel.setOpaque(false); // Прозрачность панели

        ButtonGroup weaponGroup = new ButtonGroup();

        String[] weaponNames = {"Пулемет", "Автомат", "Снайперка"};
        String[] weaponDescriptions = {
                "Быстрая перезарядка, маленький урон",
                "Средняя перезарядка, средний урон",
                "Медленная перезарядка, большой урон"
        };
        //String[] weaponImages = {"src/machine_gun.png", "src/ak47.png", "src/awp.png"};

        for (int i = 0; i < weaponNames.length; i++) {
            JPanel weaponPanel = new JPanel(new BorderLayout());
            weaponPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 50, 10));
            weaponPanel.setOpaque(false);

            // Загружаем и масштабируем изображение оружия
            JLabel weaponImage = new JLabel();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            boolean isAutoWidth = false;
            int screenWidth = screenSize.width * 5 / 20;
            if (!isAutoWidth) {
                screenWidth = 350;
            }
//            try {
//                BufferedImage originalImage = ImageIO.read(new File(weaponImages[i]));
//                Image scaledImage = originalImage.getScaledInstance(screenWidth, 150, Image.SCALE_SMOOTH);
//                weaponImage.setIcon(new ImageIcon(scaledImage));
//            } catch (IOException e) {
//                e.printStackTrace();
//                weaponImage.setText("Image not found");
//            }

            weaponImage.setHorizontalAlignment(SwingConstants.CENTER);
            weaponImage.setPreferredSize(new Dimension(screenWidth, 150));

            String weaponText = "<html><b style='font-size: 16px;'>" + weaponNames[i] + "</b>: " +
                    "<span style='font-size: 16px;'>" + weaponDescriptions[i] + "</span></html>";

            JRadioButton weaponOption = new JRadioButton(weaponText);
            weaponOption.setOpaque(false);
            weaponOption.setForeground(Color.BLACK);
            weaponOption.setFont(new Font("Arial", Font.PLAIN, 20));

            // Добавляем слушатель к радиокнопке и изображению
            int finalI = i;
            ActionListener selectWeapon = _ -> {
                weaponOption.setSelected(true);
                if (isPlayer1) {
                    player1Weapon = weaponNames[finalI];
                } else {
                    player2Weapon = weaponNames[finalI];
                }
            };

            if (weaponNames[i].equals("Автомат")) {
                weaponOption.setSelected(true);
                if (isPlayer1) {
                    player1Weapon = weaponNames[i];
                } else {
                    player2Weapon = weaponNames[i];
                }
            }

            // Обработчик нажатий для всей панели и изображения
            weaponPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectWeapon.actionPerformed(null); // Обрабатываем клик по панели
                    requestFocusInWindow(); // Принудительный запрос фокуса
                }
            });

            weaponPanel.add(weaponImage, BorderLayout.NORTH);
            weaponPanel.add(weaponOption, BorderLayout.SOUTH);
            playerPanel.add(weaponPanel);
            weaponGroup.add(weaponOption);
        }

        return playerPanel;
    }

    public String getPlayer1Weapon() {
        return player1Weapon;
    }

    public String getPlayer2Weapon() {
        return player2Weapon;
    }
}
