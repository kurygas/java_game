import javax.swing.*;
import java.awt.*;

public class WeaponButton extends JRadioButton {
    Weapon weapon;

    public WeaponButton(Weapon weapon) {
        super(weapon.getWeaponLabel());
        setOpaque(false);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.PLAIN, 20));
        this.weapon = weapon;
        setSelected(this.weapon instanceof Automate);
    }

    public Weapon getWeapon() {
        return this.weapon;
    }
}
