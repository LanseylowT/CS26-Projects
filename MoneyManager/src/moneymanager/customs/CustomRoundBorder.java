package moneymanager.customs;

import moneymanager.constants.Colors;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Path2D;

public class CustomRoundBorder extends AbstractBorder {
    private int bottomLeftRadius;
    private int bottomRightRadius;

    public CustomRoundBorder(int bottomLeftRadius, int bottomRightRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Path2D path = new Path2D.Float();
        path.moveTo(x, y + bottomLeftRadius);
        path.lineTo(x, y + height - 1 - bottomRightRadius);
        path.quadTo(x, y + height - 1, x + bottomLeftRadius, y + height - 1);
        path.lineTo(x + width - 1 - bottomRightRadius, y + height - 1);
        path.quadTo(x + width - 1, y + height - 1, x + width - 1, y + height - 1 - bottomRightRadius);
        path.lineTo(x + width - 1, y + bottomLeftRadius);
        path.quadTo(x + width - 1, y, x + width - 1 - bottomLeftRadius, y);
        path.lineTo(x + bottomRightRadius, y);
        path.quadTo(x, y, x, y + bottomLeftRadius);

        g2d.setColor(Colors.mantis);
        g2d.fill(path);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(bottomLeftRadius, bottomLeftRadius, bottomRightRadius, bottomRightRadius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = 0;
        insets.top = bottomLeftRadius;
        insets.left = bottomLeftRadius;
        insets.bottom = bottomRightRadius;
        insets.right = bottomRightRadius;
        return insets;
    }
}