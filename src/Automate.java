import java.io.IOException;

public class Automate extends Weapon {
    public Automate() throws IOException {
        super(4, 40, 600);
    }

    @Override
    public String getName() {
        return "Автомат";
    }

    @Override
    public String getDescription() {
        return "Средний урон, средняя перезарядка";
    }
}
