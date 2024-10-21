import java.awt.*;
import java.io.IOException;

public class SniperRiffle extends Weapon {
    public SniperRiffle() throws IOException {
        super(6, 100, 1800);
    }

    @Override
    public String getName() {
        return "Снайперская винтовка";
    }

    @Override
    public String getDescription() {
        return "Долгая перезарядка, высокий урон";
    }

    @Override
    public WeaponType getEnum() {
        return WeaponType.SniperRiffle;
    }
}
