import java.awt.*;
import java.io.IOException;

public class MachineGun extends Weapon {
    public MachineGun() throws IOException {
        super(2, 60, 300);
    }

    @Override
    public String getName() {
        return "Пулемёт";
    }

    @Override
    public String getDescription() {
        return "Быстрая перезарядка, небольшой урон";
    }

    @Override
    public WeaponType getEnum() {
        return WeaponType.MachineGun;
    }
}
