/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moneymanager.customs;

import moneymanager.constants.Fonts;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author donla
 */
public class CustomButton3 extends JButton {
    private Color buttonColor = new Color(33, 150, 243);
    private Color buttonColorHovered = new Color(51, 181, 229);
    private static int BORDER_RADIUS = 30;
    private static final int FIXED_WIDTH = 170;
    private static final int FIXED_HEIGHT = 40;
    
    public void mutateText(String text){
        this.setText(text);
    }

    // region Getters and Setters
    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public void setButtonColorHovered(Color buttonColorHovered) {
        this.buttonColorHovered = buttonColorHovered;
    }

    public void setRadius(int radius){
        BORDER_RADIUS = radius;
        setBorder(new RoundBorder(BORDER_RADIUS));
    }
    // endregion
    
    public CustomButton3() {
//        customFont = new Fonts();
        initComponents();
    }
    
    private void initComponents() {
        setFocusPainted(false);
        setBackground(buttonColor); // Button color
        setForeground(Color.WHITE); // Text color
        setFont(new Font("Arial", Font.PLAIN, 12));
        setPreferredSize(new Dimension(FIXED_WIDTH, FIXED_HEIGHT));
        setBorderPainted(false);
        setMargin(new Insets(10, 15, 10, 15));
        setBorder(new RoundBorder(BORDER_RADIUS));
        setCursor(new Cursor(Cursor.HAND_CURSOR));


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(buttonColorHovered); // Hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(buttonColor); // Default color
            }
        });
    }
    
    private static class RoundBorder implements Border {
        private final int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(c.getBackground());
            g2d.fillRoundRect(x, y, width, height, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
}
