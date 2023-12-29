package moneymanager.customs;

import javax.swing.*;
import java.awt.*;

public class CustomScrollPane extends JScrollBar {
    public CustomScrollPane() {
        setUI(new ModerScrollBar());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(180, 180, 180));
        setBackground(Color.WHITE);
        setUnitIncrement(20);
    }
}
