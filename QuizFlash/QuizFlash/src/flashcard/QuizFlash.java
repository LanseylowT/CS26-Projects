/*
 * Hi-hi!!!
 * Do not copy my code...
 * That's all...
 * Have a good time!!!
 * ^^
 * Created on Nov 25, 2023, 12:41:31 PM
 * for School purpose
 * Code by Dan Steve S. Bermejo
 */
package flashcard;

/**
 *
 * @author Eve
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class QuizFlash extends UsualFrame implements MouseListener {

    private CardLayout display;
    private DefaultLabel menu_PlayB, menu_DetailsB, menu_ExitB;
    private DefaultButton playMenu_BackB;
    private DefaultButton details_BackB;

    QuizFlash() {
        this(0);
    }

    QuizFlash(int num) {
        super("Quiz Flash");
        //ConnectionTODatabase
        ConnectionDatabase connection = new ConnectionDatabase();

        //card menu
        CardPanel menu = new CardPanel();
        DefaultLabel menu_Title = new DefaultLabel(new ImageIcon("src\\resource\\menu.gif"));
        DefaultLabel logo = new DefaultLabel(new ImageIcon("src\\resource\\logo.png"));
        DefaultLabel qf = new DefaultLabel(new ImageIcon("src\\resource\\qf.gif"));
        DefaultLabel fp = new DefaultLabel(new ImageIcon("src\\resource\\fp.gif"));
        DefaultLabel gr = new DefaultLabel(new ImageIcon("src\\resource\\group.gif"));
        qf.setBounds(100, 80, 300, 80);
        fp.setBounds(100, 150, 300, 80);
        menu_Title.setBounds(460, 110, 300, 100);
        logo.setBounds(100, 200, 300, 300);
        gr.setBounds(100, 350, 300, 300);
        menu_PlayB = new DefaultLabel(new ImageIcon("src\\resource\\play.gif"));
        menu_PlayB.setBounds(460, 230, 300, 70);
        menu_DetailsB = new DefaultLabel(new ImageIcon("src\\resource\\details2.gif"));
        menu_DetailsB.setBounds(460, 330, 300, 70);
        menu_ExitB = new DefaultLabel(new ImageIcon("src\\resource\\exit.gif"));
        menu_ExitB.setBounds(460, 430, 300, 70);

        //Cards to Play /database
        CardPanel playMenu = new CardPanel();
        DefaultLabel playMenu_Title = new DefaultLabel(new ImageIcon("src\\resource\\database.gif"));
        playMenu_Title.setBounds(270, 20, 280, 100);
        playMenu_BackB = new DefaultButton("Back", 10, 10, 150, 30);
        JPanel panelGames = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelGames.setBorder(BorderFactory.createLineBorder(Color.cyan, 40));
        panelGames.setBounds(100, 150, 600, 375);
        panelGames.setBackground(UsualFrame.BACKGROUND_CARD);
        CardContainer createCard = new CardContainer(new ImageIcon("src\\resource\\create.gif"));
        createCard.getPanel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                new PlayCards("+ Create Deck", 0);
                dispose();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createCard.getPanel().setBorder(BorderFactory.createLineBorder(Color.magenta, 5));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createCard.getPanel().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
            }
        });
        ResultSet result = connection.allCards();
        try {
            while (result.next()) {
                CardContainer sample = new CardContainer(UsualFrame.setNameProperly(result.getString(1)));
                panelGames.add(sample.getPanel());
                sample.getPanel().addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        new PlayCards(sample.getName(), 1);
                        dispose();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        sample.getPanel().setBorder(BorderFactory.createLineBorder(Color.magenta, 5));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        sample.getPanel().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
                    }
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error Message", 2);
        }
        panelGames.add(createCard.getPanel());

        //card details
        CardPanel details = new CardPanel();
        DefaultLabel details_Title = new DefaultLabel(new ImageIcon("src\\resource\\details.gif"));
        details_Title.setBounds(250, 0, 300, 100);
        DefaultLabel l1 = new DefaultLabel(new ImageIcon("src\\resource\\group.gif"));
        DefaultLabel l2 = new DefaultLabel(new ImageIcon("src\\resource\\member.gif"));
        DefaultLabel l3 = new DefaultLabel(new ImageIcon("src\\resource\\berm.gif"));
        DefaultLabel l4 = new DefaultLabel(new ImageIcon("src\\resource\\lan.gif"));
        DefaultLabel l5 = new DefaultLabel(new ImageIcon("src\\resource\\zec.gif"));
        l1.setBounds(500, 300, 300, 90);
        l2.setBounds(540, 360, 200, 100);
        l3.setBounds(500, 410, 300, 100);
        l4.setBounds(500, 450, 300, 100);
        l5.setBounds(500, 490, 300, 100);

        DefaultLabel detailsLabel = new DefaultLabel("<h1 class=fo align=center bgColor=rgba(255,255,255,0.8)>"
                + "Introducing Quiz Flash, your ultimate destination for engaging and dynamic"
                + " quizzes! Whether you're a trivia enthusiast or just looking to challenge"
                + " your knowledge, Quiz Flash is the app that caters to all your quizzing "
                + "needs. With a diverse range of features, Quiz Flash offers an interactive"
                + " and fun-filled learning experience."
                + "</h1>");
        DefaultLabel detailsLabel1 = new DefaultLabel("<div align=center bgColor=rgba(255,255,255,0.8)>"
                + "<h1 class=fa>Features:</h1>"
                + "<hr>"
                + "<h3 class=fb>Explore our extensive Database, filled with a wealth of information across "
                + "various categories, ensuring there's always something new to discover. "
                + "Quiz Flash empowers you to become the quizmaster by allowing you to create your "
                + "own set of cards. Craft personalized quizzes for your friends, family,"
                + " or even for your own study sessions.<br>"
                + "Dive into the world of Identification Tests, where your memorization skills "
                + "will be put to the test as you identify the cards. Challenge "
                + "yourself with our Multiple Choice Tests, designed to stimulate your intellect. "
                + "Whether you're on a quest for knowledge or simply seeking a good time, Quiz Flash "
                + "is the app that combines education with entertainment. Get ready to flash your quiz "
                + "skills and embark on a journey of discovery like never before!</h3>"
                + "</div>");
        detailsLabel.setForeground(Color.black);
        detailsLabel1.setForeground(Color.black);
        detailsLabel.setBounds(30, 50, 750, 250);
        detailsLabel1.setBounds(30, 180, 450, 500);

        details_BackB = new DefaultButton("Back", 20, 20, 140, 30);

        //add Listener
        menu_PlayB.addMouseListener(this);
        menu_DetailsB.addMouseListener(this);
        menu_ExitB.addMouseListener(this);
        playMenu_BackB.addMouseListener(this);
        details_BackB.addMouseListener(this);

        //add to menu, play, details
        menu.add(fp);
        menu.add(qf);
        menu.add(gr);
        menu.add(menu_PlayB);
        menu.add(menu_Title);
        menu.add(logo);
        menu.add(menu_DetailsB);
        menu.add(menu_ExitB);
        menu.add(UsualFrame.BACKGROUND_MENU);
        playMenu.add(playMenu_Title);
        playMenu.add(playMenu_BackB);
        playMenu.add(panelGames);
        playMenu.add(UsualFrame.BACKGROUND_DATABASE);
        details.add(details_BackB);
        details.add(details_Title);
        details.add(l1);
        details.add(l2);
        details.add(detailsLabel);
        details.add(detailsLabel1);
        details.add(l3);
        details.add(l4);
        details.add(l5);
        details.add(UsualFrame.BACKGROUND_DETAILS);

        display = new CardLayout();
        this.getPanel().setLayout(display);
        this.getPanel().add(menu, "Menu");
        this.getPanel().add(playMenu, "PlayMenu");
        this.getPanel().add(details, "Details");
        if (num == 0) {
            display.show(this.getPanel(), "Menu");
        } else {
            display.show(this.getPanel(), "PlayMenu");
        }

        this.executeLast();
    }


    public void shiftCard(String str) {
        display.show(this.getPanel(), str);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == menu_PlayB) {
            shiftCard("PlayMenu");
        }
        if (e.getSource() == menu_DetailsB) {
            shiftCard("Details");
        }
        if (e.getSource() == menu_ExitB) {
            int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit?", "Exiting", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (i == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
        if (e.getSource() == playMenu_BackB) {
            shiftCard("Menu");
        }
        if (e.getSource() == details_BackB) {
            shiftCard("Menu");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    public static void main(String zero[]) {
        new QuizFlash();
    }
}
