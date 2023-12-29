/*
 * Hi-hi!!!
 * Do not copy my code...
 * That's all...
 * Have a good time!!!
 * ^^
 * Created on Nov 25, 2023, 12:34:21 PM
 * for School purpose
 * Code by Dan Steve S. Bermejo
 */
package flashcard;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Eve
 */
public class UsualFrame extends JFrame {

    public static final DefaultLabel BACKGROUND_MENU = new DefaultLabel(new ImageIcon("src\\background\\Details.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND_DATABASE = new DefaultLabel(new ImageIcon("src\\background\\Database.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND_CREATE = new DefaultLabel(new ImageIcon("src\\background\\Menu.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND_REVIEW = new DefaultLabel(new ImageIcon("src\\background\\Multiple Choice.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND_DETAILS = new DefaultLabel(new ImageIcon("src\\background\\Spare.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND_MULTIPLE_CHOICE = new DefaultLabel(new ImageIcon("src\\background\\Typing Test.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND_INDENTIFICATION = new DefaultLabel(new ImageIcon("src\\background\\Typing Test (1).gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND1 = new DefaultLabel(new ImageIcon("src\\background\\Review.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND2 = new DefaultLabel(new ImageIcon("src\\background\\Spare 1.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND8 = new DefaultLabel(new ImageIcon("src\\background\\Spare 2.gif"), 0, 0, 800, 600);
    public static final DefaultLabel BACKGROUND9 = new DefaultLabel(new ImageIcon("src\\background\\Spare 3.gif"), 0, 0, 800, 600);
    public static final Color BACKGROUND_CARD = new Color(255, 255, 255, 170);
    public static final Border FRONT_CARD_COLOR = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(173, 239, 209, 170), 40),UsualFrame.setNewText("<h5 class=fz> ANSWER</h5>"));
    public static final Border BACK_CARD_COLOR = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 32, 63, 170), 40),UsualFrame.setNewText("<h5 class=fz> QUESTION</h5>"));
    private JPanel mainPanel;
    private Container content;

    UsualFrame() {
        this("No Title");
    }

    UsualFrame(String title) {
        this(title, 800, 600);
    }

    UsualFrame(String title, int width, int height) {
        super(title);
        setIconImage(new ImageIcon("src\\resource\\logo.png").getImage());
        content = this.getContentPane();

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setBackground(new Color(18, 30, 49));

    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public void executeLast() {
        content.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static String setNewText(String text) {
        String css = "<style>.fo{font-family: Forte;\n"
                + "font-size: 20px;\n"
                + "letter-spacing: 0px;\n"
                + "word-spacing: 0px;\n"
                + "color: #000000;\n"
                + "font-weight: normal;\n"
                + "text-decoration: none;\n"
                + "font-style: normal;\n"
                + "font-variant: normal;\n"
                + "text-transform: capitalize;}.fz{font-family: Forte;\n"
                + "font-size: 12px;\n"
                + "letter-spacing: 0px;\n"
                + "word-spacing: 0px;\n"
                + "font-weight: normal;\n"
                + "text-decoration: none;\n"
                + "font-variant: normal;\n"
                + "text-transform: capitalize;}"
                + ".fa{font-family: Calibri, cursive;\n"
                + "font-size: 15px;\n"
                + "letter-spacing: 0px;\n"
                + "word-spacing: 0px;\n"
                + "color: #000000;\n"
                + "font-weight: normal;\n"
                + "text-decoration: none;\n"
                + "font-style: normal;\n"
                + "font-variant: normal;\n"
                + "text-transform: capitalize;\n"
                + "}.fb{\n"
                + "font-family: Calibri, cursive;\n"
                + "font-size: 12px;\n"
                + "letter-spacing: 0px;\n"
                + "word-spacing: 0px;\n"
                + "color: #000000;\n"
                + "font-weight: normal;\n"
                + "text-decoration: none;\n"
                + "font-style: normal;\n"
                + "font-variant: normal;\n"
                + "text-transform: capitalize;\n"
                + "}.title {font-family: Comic Sans MS;\n"
                + "font-size: 30px;\n"
                + "letter-spacing: 0px;\n"
                + "word-spacing: 0px;\n"
                + "color: #00fffb;\n"
                + "font-weight: normal;\n"
                + "text-decoration: none;\n"
                + "font-style: normal;\n"
                + "font-variant: normal;\n"
                + "text-transform: capitalize;\n"
                + "}.card{font-family: Comic Sans MS;\n"
                + "font-size: 25px;\n"
                + "letter-spacing: 0px;\n"
                + "word-spacing: 0px;\n"
                + "color: #000000;\n"
                + "font-weight: normal;\n"
                + "text-decoration: none;\n"
                + "font-style: normal;\n"
                + "font-variant: normal;\n"
                + "text-transform: capitalize;\n"
                + "}.card2{font-family: Comic Sans MS;\n"
                + "font-size: 20px;\n"
                + "letter-spacing: 0px;\n"
                + "word-spacing: 0px;\n"
                + "color: #000000;\n"
                + "font-weight: normal;\n"
                + "text-decoration: none;\n"
                + "font-style: normal;\n"
                + "font-variant: normal;\n"
                + "text-transform: capitalize;\n"
                + "}</style>";
        return "<html>" + css + text + "</html>";
    }

    public static String setNameProperly(String name) {
        String value = "";
        name = name.trim();
        for (int i = 0; i < name.length(); i++) {//Super man
            if (i == 0) {
                value = value + Character.toUpperCase(name.charAt(i));
            } else if (name.charAt(i - 1) == '_') {
                value = value + Character.toUpperCase(name.charAt(i));
            } else if (name.charAt(i) == '_') {
                value = value + " ";
            } else {
                value = value + name.charAt(i);
            }
        }
        return value;
    }

    public static String getNameProperly(String name) {
        String value = "";
        name = name.trim().toLowerCase();
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ' || name.charAt(i) == '_') {
                value += "_";
            } else if (Character.isLetter(name.charAt(i)) || Character.isDigit(name.charAt(i))) {
                value += name.charAt(i);
            }
        }
        return value;
    }
}

class CardContainer {

    private JLabel name;
    private JPanel panel;
    private static int numIcon = 0;

    CardContainer() {
        this("<Default>");
    }

    CardContainer(String name) {
        this.name = new JLabel(name);
        this.name.setFont(new Font("Arial", Font.PLAIN, 20));
        setUp();
    }

    CardContainer(ImageIcon n) {
        this.name = new JLabel(n);
        setUp();
        panel.setBackground(Color.black);
    }

    private void setUp() {
        panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        panel.add(this.name);
        panel.setPreferredSize(new Dimension(166, 80));
    }

    public String getName() {
        return name.getText();
    }

    public JPanel getPanel() {
        return panel;
    }

}

class ConnectionDatabase {

    public Connection connection;
    private Statement statement;
    private ResultSet result;
    private String error;

    ConnectionDatabase() {
        String url = "jdbc:mysql://localhost:3306/flashcards";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            statement = connection.createStatement();

            error = "No error in connection to database";

        } catch (Exception e) {
            showError(e.toString());
        }
    }

    public void showError(String str) {
        JOptionPane.showMessageDialog(null, str, "Error Message", 2);
        JOptionPane.showMessageDialog(null, "Fail Safe Activating...\nQuiz Flash is closing.", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public ResultSet allCards() {
        try {
            result = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_type='BASE TABLE' AND table_schema = 'flashcards'");
        } catch (Exception e) {
            error = e.toString();
            showError(e.toString());
        }
        return result;
    }

    public ResultSet queryExecute(String str) {
        try {
            result = statement.executeQuery(str);
        } catch (Exception e) {
            showError(e.toString());
        }
        return result;
    }

    public void queryUpdate(String str) {
        try {
            statement.executeUpdate(str);
        } catch (Exception e) {
            showError(e.toString());
        }
    }

    public int numberOfTables() {
        int num = 0;
        try {
            result = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_type='BASE TABLE' AND table_schema = 'flashcards'");
            while (result.next()) {
                num++;
            }
        } catch (Exception e) {
            showError(e.toString());
        }
        return num;
    }

    public String[] nameOfTables() {
        String give[] = new String[numberOfTables()];
        try {
            result = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_type='BASE TABLE' AND table_schema = 'flashcards'");
            int count = 0;
            while (result.next()) {
                give[count] = result.getString(1);
                count++;
            }
        } catch (Exception e) {
            showError(e.toString());
        }
        return give;
    }

    public int getNumberOfCards(String tableName) {
        result = queryExecute("select * from flashcards." + tableName);
        int give = 0;
        try {
            while (result.next()) {
                give++;
            }
        } catch (Exception e) {
            showError(e.toString());
        }
        return give;
    }

    public String getCardFront(String tableName, int numCard) {
        result = queryExecute("select front from flashcards." + tableName);
        try {
            int count = 0;
            while (result.next()) {
                if (count == numCard - 1) {
                    break;
                }
                result.getString(1);
                count++;
            }
            return result.getString(1);
        } catch (Exception e) {
            showError(e.toString());
        }
        return "";
    }

    public String getCardBack(String tableName, int numCard) {
        result = queryExecute("select back from flashcards." + tableName);
        try {
            int count = 0;
            while (result.next()) {
                if (count == numCard - 1) {
                    break;
                }
                result.getString(1);
                count++;
            }
            return result.getString(1);
        } catch (Exception e) {
            showError(e.toString());
        }
        return "";
    }

    public void deleteCard(String tableName, int cardNumber) {
        ResultSet res = queryExecute("select front from " + tableName);
        int count = 0;
        try {
            while (res.next()) {
                count++;
                if (count == cardNumber) {
                    queryUpdate("DELETE FROM " + tableName + " WHERE front = '" + res.getString(1) + "'");
                    break;
                }
            }
        } catch (Exception e) {
            showError(e.toString());
        }
    }
}

class DefaultButton extends JButton {

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius;

    DefaultButton() {
        this("Default Button");
    }

    DefaultButton(String name) {
        this(name, 150, 50);
    }

    DefaultButton(String name, int width, int height) {
        this(name, width, height, 300, 250);
    }

    DefaultButton(String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(name);
        this.setBounds(xCoordinate, yCoordinate, width, height);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

        setColor(Color.WHITE);
        colorOver = new Color(179, 250, 160);
        colorClick = new Color(152, 184, 144);
        borderColor = new Color(22, 2, 87);
        radius = 30;
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                over = false;

            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public void buttonDisable() {
        colorOver = color;
    }

    public void buttonEnable() {
        colorOver = new Color(179, 250, 160);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}

class CardPanel extends JPanel {

    CardPanel() {
        this.setLayout(null);
        this.setBackground(new Color(18, 30, 48));
    }
}

class DefaultLabel extends JLabel {

    DefaultLabel() {
        this("new Label");
    }

    DefaultLabel(ImageIcon n) {
        super(n);
    }

    DefaultLabel(ImageIcon n, int x, int y, int w, int h) {
        super(n);
        this.setBounds(x, y, w, h);
    }

    DefaultLabel(String name) {
        super(name, SwingConstants.CENTER);
        this.setForeground(Color.white);
        this.setBounds(300, 20, 250, 100);
        this.setText(UsualFrame.setNewText(name));
        //this.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
        this.setBackground(Color.white);
    }

}
