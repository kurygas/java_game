import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayerPanel extends JPanel {
    private final ButtonGroup weaponGroup;

    public PlayerPanel(int x, int y, int width, int height, String playerName) throws IOException {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(null, playerName,
                0, 0, new Font("Arial", Font.BOLD, 26), Color.BLACK));
        setOpaque(false);
        this.weaponGroup = new ButtonGroup();

        for (var weapon : Weapon.getAllWeapons()) {
            JPanel weaponPanel = new JPanel(new BorderLayout());
            weaponPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 10));
            weaponPanel.setOpaque(false);
            var weaponOption = new WeaponButton(weapon);
            add(weaponOption, BorderLayout.SOUTH);
            add(weaponPanel);
            this.weaponGroup.add(weaponOption);
        }

        setBounds(x, y, width, height);
        setOpaque(false);
    }

    public Weapon getChosenWeapon() throws IOException {
        var playerButtons = this.weaponGroup.getElements();

        for (int i = 0; i < this.weaponGroup.getButtonCount(); ++i) {
            var button = (WeaponButton) playerButtons.nextElement();
            if (button.isSelected()) {
                return button.getWeapon();
            }
        }

        return null;
    }
}
