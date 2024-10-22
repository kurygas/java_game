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
        this.weaponGroup = new ButtonGroup();
        var weapons = Weapon.getAllWeapons();


        for (var weapon : weapons) {
            var weaponButton = new WeaponButton(weapon);
            add(new JPanel());
            add(weaponButton);
            this.weaponGroup.add(weaponButton);
        }

        add(new JPanel());
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
