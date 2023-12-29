package moneymanager.customs;

import javax.swing.*;
import java.awt.*;

public class CustomFadingPanel extends JPanel {
    private float alpha = 1.0f;
    private Timer timer;

    public CustomFadingPanel() {
        timer = new Timer(50, (e) -> {
            alpha -= 0.05f;
            if (alpha <= 0.0f){
                alpha = 0.0f;
                timer.stop();
            }
            repaint();
        });
    }

    public void startFadeOut(){
        alpha = 1.0f;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.dispose();
    }
}
