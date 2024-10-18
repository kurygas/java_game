import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() throws HeadlessException {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new GamePanel());
        setVisible(true);
    }
}
