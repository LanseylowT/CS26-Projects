package moneymanager.customs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomRoundedPanel extends JPanel {

    private int cornerRadiusTopLeft = 10;
    private int cornerRadiusTopRight = 10;
    private int cornerRadiusBottomLeft = 0;
    private int cornerRadiusBottomRight = 0;

    public CustomRoundedPanel() {
        //super();
        setOpaque(false); // Make the panel transparent so that the custom painting works
    }

    public void setCornerRadius(int topLeft, int topRight, int bottomLeft, int bottomRight) {
        this.cornerRadiusTopLeft = topLeft;
        this.cornerRadiusTopRight = topRight;
        this.cornerRadiusBottomLeft = bottomLeft;
        this.cornerRadiusBottomRight = bottomRight;
        repaint(); // Trigger a repaint to reflect the changes
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Create a rounded rectangle shape
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1,
                cornerRadiusTopLeft + cornerRadiusTopRight,
                cornerRadiusTopLeft + cornerRadiusTopRight);

        // Set the background color
        g2d.setColor(getBackground());
        g2d.fill(roundedRectangle);

        // Draw the border
        g2d.setColor(getForeground());
        g2d.draw(roundedRectangle);

        g2d.dispose();
    }
}
