import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(int size) throws HeadlessException {
        setBounds(100, 100, size, size);
        add(new GamePanel());
        setVisible(true);
    }
}
