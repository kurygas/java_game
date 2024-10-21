import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WeaponButton extends JRadioButton {
    Weapon weapon;

    public WeaponButton(Weapon weapon) {
        super(weapon.getWeaponLabel());
        setOpaque(false);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.PLAIN, 20));
        this.weapon = weapon;
        if (this.weapon instanceof Automate) {
            setSelected(true);
        }
    }

    public Weapon getWeapon() throws IOException {
        return this.weapon;
    }
}
