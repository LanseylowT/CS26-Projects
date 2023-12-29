/*
 * Hi-hi!!!
 * Do not copy my code...
 * That's all...
 * Have a good time!!!
 * ^^
 * Created on Dec 5, 2023, 3:45:01 PM
 * for School purpose
 * Code by Dan Steve S. Bermejo
 */

package flashcard;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author Eve
 */
public class PlayCards extends UsualFrame implements MouseListener {
    private String deckName;
    private CardLayout card;
    private JTextField create_TableName, typingAnswer;
    private ConnectionDatabase connection;
    private DefaultLabel create_Deck;
    private DefaultButton general_BackB;
    private DefaultButton view_delete, view_createCard;
    private DefaultButton typingTest, multipleChoiceTest, flip, cardDelete, cardEdit, quitTest;
    private CardPanel create, view, write, multipleChoice;
    private JPanel review_panelCard, multipleChoice_panelCard, typingTest_panelCard;
    private DefaultLabel cardPage, onQuestion;
    private DefaultButton typingEnter, view_left, view_right, choice_box1, choice_box2, choice_box3, choice_box4, genNext;
    private int cardNumber, cardPageNumber = 1;
    private DefaultLabel rev_front, rev_back, testScore;
    private Stack<Integer> testShuffle;
    private int currentTestNum = 0, testScoreNum = 0;
    private int panelCard;

    PlayCards() {
        this("sample", 1);
    }

    PlayCards(String name, int panelCard) {
        super(UsualFrame.setNameProperly(name));
        name = UsualFrame.getNameProperly(name);
        deckName = name;
        this.panelCard = panelCard;
        //ConnectionTODatabase
        connection = new ConnectionDatabase();
        boolean verify = false;
        if (panelCard != 0) {
            for (String var : connection.nameOfTables()) {
                if (var.equalsIgnoreCase(UsualFrame.getNameProperly(name))) {
                    verify = true;
                    break;
                }
            }
            if (!verify) {
                JOptionPane.showMessageDialog(null, "Deck not available in database", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }

        general_BackB = new DefaultButton("Back", 10, 10, 150, 30);
        general_BackB.addMouseListener(this);
        quitTest = new DefaultButton("Quit Test", 10, 10, 150, 30);
        quitTest.addMouseListener(this);
        quitTest.setForeground(Color.red);
        typingTest = new DefaultButton("Identification Test");
        typingTest.addMouseListener(this);
        multipleChoiceTest = new DefaultButton("Multiple Choice");
        multipleChoiceTest.addMouseListener(this);
        genNext = new DefaultButton("Next >", 650, 90, 100, 40);
        genNext.setVisible(false);
        genNext.addMouseListener(this);
        testScore = new DefaultLabel();
        testScore.setFont(new Font("Arial", Font.PLAIN, 18));
        testScore.setBounds(620, 10, 170, 80);
        cardPage = new DefaultLabel();
        cardPage.setBounds(40, 90, 170, 50);
        cardPage.setFont(new Font("Arial", Font.PLAIN, 25));
        cardPage.setBorder(null);

        //create
        create = new CardPanel();
        DefaultLabel create_Title = new DefaultLabel(new ImageIcon("src\\resource\\create2.gif"));
        create_Title.setBounds(260, 25, 280, 70);
        create_TableName = new JTextField();
        create_TableName.setFont(new Font("Arial", Font.PLAIN, 25));
        create_TableName.setBounds(250, 200, 300, 50);
        DefaultLabel label2 = new DefaultLabel("<body class=fo bgColor=rgba(255,255,255,0.8)>Name of this set of Card</body>");
        label2.setBounds(250, 260, 300, 40);
        label2.setBorder(null);
        create_Deck = new DefaultLabel(new ImageIcon("src\\resource\\add.gif"));
        create_Deck.setBounds(240, 400, 300, 30);
        create_Deck.addMouseListener(this);
        create.add(create_Title);
        create.add(create_TableName);
        create.add(create_Deck);
        create.add(label2);

        //ViewCards
        view = new CardPanel();
        DefaultLabel view_Title = new DefaultLabel("<h1 class=title>" + UsualFrame.setNameProperly(name) + "</h1>");
        DefaultLabel view2_Title = new DefaultLabel(new ImageIcon("src\\resource\\rev.gif"));
        view_Title.setBounds(200, 50, 400, 120);
        view2_Title.setBounds(180, -20, 400, 120);
        view_delete = new DefaultButton("Delete Deck", 620, 40, 150, 30);
        view_delete.setForeground(Color.red);
        view_delete.addMouseListener(this);
        view_createCard = new DefaultButton("Create Card", 620, 85, 150, 30);
        view_createCard.setForeground(Color.blue);
        view_createCard.addMouseListener(this);
        view.add(view2_Title);
        view.add(view_Title);
        view.add(view_delete);
        view.add(view_createCard);
        view.add(typingTest);
        view.add(multipleChoiceTest);

        //PlayWrite
        write = new CardPanel();
        DefaultLabel write2_Title = new DefaultLabel(new ImageIcon("src\\resource\\yp.gif"));
        DefaultLabel write_Title = new DefaultLabel("<h1 class=title>" + UsualFrame.setNameProperly(name) + "</h1>");
        write2_Title.setBounds(200, 0, 400, 120);
        ;
        write_Title.setBounds(200, 50, 400, 120);
        ;
        write.add(write2_Title);
        write.add(write_Title);

        //PlayMultipleChoice
        multipleChoice = new CardPanel();
        DefaultLabel multipleChoice_Title = new DefaultLabel("<h1 class=title>" + UsualFrame.setNameProperly(name) + "</h1>");
        DefaultLabel multipleChoice2_Title = new DefaultLabel(new ImageIcon("src\\resource\\mul.gif"));
        multipleChoice2_Title.setBounds(200, 0, 400, 120);
        multipleChoice_Title.setBounds(200, 50, 400, 120);
        multipleChoice.add(multipleChoice2_Title);
        multipleChoice.add(multipleChoice_Title);

        typingTest.setBounds(180, 540, 230, 40);
        view.add(typingTest);
        multipleChoiceTest.setBounds(420, 540, 200, 40);
        view.add(multipleChoiceTest);

        card = new CardLayout();
        this.getPanel().setLayout(card);
        this.getPanel().add(create, "Create");
        this.getPanel().add(view, "View");
        this.getPanel().add(write, "Write");
        this.getPanel().add(multipleChoice, "Choice");


        if (panelCard == 0) {
            card.show(this.getPanel(), "Create");
            create.add(general_BackB);
            create.add(UsualFrame.BACKGROUND_CREATE);
        } else {
            cardNumber = connection.getNumberOfCards(name);
        }
        if (panelCard == 1) {
            card.show(this.getPanel(), "View");
            view.add(cardPage);
            cardPage.setText("Card " + cardPageNumber + " of " + cardNumber);
            view.add(general_BackB);
            generateReview();
            view.add(UsualFrame.BACKGROUND_REVIEW);
        } else if (panelCard == 2) {
            write.add(quitTest);
            write.add(testScore);
            write.add(cardPage);
            card.show(this.getPanel(), "Write");
            generateTypingTest();
            write.add(genNext);
            testScore.setText("Your Score is: " + testScoreNum + "/" + cardNumber);
            cardPage.setText("Card " + cardPageNumber + " of " + cardNumber);
            write.add(UsualFrame.BACKGROUND_INDENTIFICATION);
        } else if (panelCard == 3) {
            multipleChoice.add(quitTest);
            multipleChoice.add(testScore);
            multipleChoice.add(cardPage);
            card.show(this.getPanel(), "Choice");
            cardPage.setText("Card " + cardPageNumber + " of " + cardNumber);
            multipleChoice.add(genNext);
            testScore.setText("Your Score is: " + testScoreNum + "/" + cardNumber);
            generateMultipleTest();
            multipleChoice.add(UsualFrame.BACKGROUND_MULTIPLE_CHOICE);
        }
        this.executeLast();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == general_BackB) {
            new QuizFlash(1);
            dispose();
        }
        if (e.getSource() == create_Deck) {
            if (create_TableName.getText().length() > 18) {
                JOptionPane.showMessageDialog(null, "Characters must not exceed 18", "Error Message", 2);
            } else if (create_TableName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty", "Error Message", 2);
            } else {
                ResultSet tblnames = connection.allCards();
                boolean named = false;
                try {
                    while (tblnames.next()) {
                        if (UsualFrame.getNameProperly(create_TableName.getText()).equalsIgnoreCase(tblnames.getString(1))) {
                            named = true;
                        }
                    }
                } catch (Exception el) {
                    JOptionPane.showMessageDialog(null, el, "Error Message", 2);
                }
                if (named) {
                    JOptionPane.showMessageDialog(null, "There is already a name " + UsualFrame.setNameProperly(create_TableName.getText()) + " in Database", "Error Message", 2);
                } else {
                    connection.queryUpdate("CREATE TABLE flashcards." + UsualFrame.getNameProperly(create_TableName.getText()) + " (id INT NOT NULL AUTO_INCREMENT , front VARCHAR(50) NOT NULL , back VARCHAR(200) NOT NULL , PRIMARY KEY (id)) ENGINE = InnoDB;");
                    JOptionPane.showMessageDialog(null, "Deck name '" + UsualFrame.setNameProperly(create_TableName.getText()) + "' is added in Database", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
                    new QuizFlash(1);
                    dispose();
                }

            }
        }
        if (e.getSource() == quitTest) {
            int ansd = JOptionPane.showConfirmDialog(null, "Do you want to quit the test?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (ansd == JOptionPane.YES_OPTION) {
                write.remove(multipleChoiceTest);
                multipleChoice.remove(typingTest);

                typingTest.setBounds(220, 540, 150, 40);
                multipleChoiceTest.setBounds(420, 540, 200, 40);

                new PlayCards(deckName, 1);
                dispose();
            }
        }
        if (e.getSource() == typingTest) {
            if (cardNumber > 0) {
                new PlayCards(deckName, 2);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "To start Typing Test you must have\nAtleast 1 card", "Insuficient Cards", JOptionPane.OK_OPTION);
            }
        }
        if (e.getSource() == multipleChoiceTest) {
            if (cardNumber > 3) {
                new PlayCards(deckName, 3);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "To start Multiple Choice Test you must have\nAtleast 4 cards", "Insuficient Cards", JOptionPane.OK_OPTION);
            }
        }
        if (e.getSource() == view_delete) {
            int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete '" + UsualFrame.setNameProperly(deckName) + "' Deck?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (i == JOptionPane.YES_OPTION) {
                connection.queryUpdate("drop table flashcards." + deckName);
                JOptionPane.showMessageDialog(null, "Successfully Deleted", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
                new QuizFlash(1);
                dispose();
            }
        }
        if (e.getSource() == view_createCard) {
            JPanel fieldInput = new JPanel();
            fieldInput.setLayout(null);
            fieldInput.setPreferredSize(new Dimension(400, 500));
            fieldInput.setBackground(new Color(18, 30, 48));
            JTextArea box1 = new JTextArea("");
            box1.setLineWrap(true);
            box1.setWrapStyleWord(true);
            box1.setBounds(10, 20, 370, 230);
            box1.setBorder(UsualFrame.FRONT_CARD_COLOR);
            JTextArea box2 = new JTextArea("");
            box2.setLineWrap(true);
            box2.setWrapStyleWord(true);
            box2.setBounds(10, 250, 370, 230);
            box2.setBorder(UsualFrame.BACK_CARD_COLOR);
            box1.setFont(new Font("Arial", Font.PLAIN, 22));
            box2.setFont(new Font("Arial", Font.PLAIN, 22));
            fieldInput.add(box1);
            fieldInput.add(box2);
            box1.requestFocus();
            JOptionPane.showMessageDialog(null, fieldInput, "Create Card", JOptionPane.PLAIN_MESSAGE);
            if (box1.getText().isEmpty() || box2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Card must not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (box1.getText().length() > 50 || box2.getText().length() > 200) {
                JOptionPane.showMessageDialog(null, "Front and Back of the card must not exceed 50 and 200 respectively.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                connection.queryUpdate("insert into flashcards." + deckName + "( `front`, `back`) values('" + box1.getText().trim() + "', '" + box2.getText().trim() + "')");
                JOptionPane.showMessageDialog(null, "Card successfully added", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
                new PlayCards(deckName, 1);
                dispose();
            }
        }
        if (e.getSource() == cardEdit) {
            int id = 0;
            try {
                ResultSet rs = connection.queryExecute("select id from " + deckName + " where front = '" + connection.getCardFront(deckName, cardPageNumber) + "'");
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PlayCards.class.getName()).log(Level.SEVERE, null, ex);
            }
            JPanel fieldInput = new JPanel();
            fieldInput.setLayout(null);
            fieldInput.setPreferredSize(new Dimension(400, 500));
            fieldInput.setBackground(new Color(18, 30, 48));
            JTextArea box1 = new JTextArea(connection.getCardFront(deckName, cardPageNumber));
            box1.setLineWrap(true);
            box1.setWrapStyleWord(true);
            box1.setBounds(10, 20, 370, 230);
            box1.setBorder(UsualFrame.FRONT_CARD_COLOR);
            JTextArea box2 = new JTextArea(connection.getCardBack(deckName, cardPageNumber));
            box2.setLineWrap(true);
            box2.setWrapStyleWord(true);
            box2.setBounds(10, 250, 370, 230);
            box2.setBorder(UsualFrame.BACK_CARD_COLOR);
            box1.setFont(new Font("Arial", Font.PLAIN, 22));
            box2.setFont(new Font("Arial", Font.PLAIN, 22));
            fieldInput.add(box1);
            fieldInput.add(box2);
            JOptionPane.showMessageDialog(null, fieldInput, "Edit Card", JOptionPane.PLAIN_MESSAGE);
            if (box1.getText().isEmpty() || box2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Card must not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (box1.getText().length() > 50 || box2.getText().length() > 200) {
                JOptionPane.showMessageDialog(null, "Front and Back of the card must not exceed 50 and 200 respectively.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                connection.queryUpdate("UPDATE " + deckName + " SET front = '" + box1.getText().trim() + "', back = '" + box2.getText().trim() + "' WHERE id = " + id);
                JOptionPane.showMessageDialog(null, "Card successfully Edited", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
                new PlayCards(deckName, 1);
                dispose();
            }
        }
        if (e.getSource() == view_left) {
            if (cardPageNumber > 0) {
                if (cardPageNumber != 1) {
                    cardPageNumber--;
                }
                cardPage.setText("Card " + cardPageNumber + " of " + cardNumber);
                rev_front.setText(UsualFrame.setNewText("<p class=card align=center>" + connection.getCardFront(deckName, cardPageNumber) + "</p></html>"));
                rev_back.setText(UsualFrame.setNewText("<p class=card align=center>" + connection.getCardBack(deckName, cardPageNumber) + "</p></html>"));
            }
            if (rev_front.isVisible()) {
                flip();
            }
        }
        if (e.getSource() == view_right) {
            if (cardPageNumber <= cardNumber) {
                if (cardPageNumber != cardNumber) {
                    cardPageNumber++;
                }
                cardPage.setText("Card " + cardPageNumber + " of " + cardNumber);
                rev_front.setText(UsualFrame.setNewText("<p class=card align=center>" + connection.getCardFront(deckName, cardPageNumber) + "</p>"));
                rev_back.setText(UsualFrame.setNewText("<p class=card align=center>" + connection.getCardBack(deckName, cardPageNumber) + "</p>"));
            }
            if (rev_front.isVisible()) {
                flip();
            }
        }
        if (e.getSource() == flip) {
            flip();
        }
        if (e.getSource() == cardDelete) {
            int i = JOptionPane.showConfirmDialog(null, "Delete this card?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (i == JOptionPane.YES_OPTION) {
                connection.deleteCard(deckName, cardPageNumber);
                JOptionPane.showMessageDialog(null, "Card successfully deleted", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
                new PlayCards(deckName, 1);
                dispose();
            }
        }
        if (e.getSource() == genNext) {
            if (cardPageNumber != cardNumber) {
                if (panelCard == 2) {

                    genNext.setVisible(false);
                    typingEnter.setVisible(true);
                    typingAnswer.setEditable(true);
                    typingAnswer.setBackground(Color.white);
                    typingAnswer.setText("");
                    currentTestNum = testShuffle.pop();
                    onQuestion.setText(UsualFrame.setNewText("<p class=card2 align=center>" + connection.getCardBack(deckName, currentTestNum) + "</p>"));

                    cardPageNumber++;
                    cardPage.setText("Card " + cardPageNumber + " of " + cardNumber);

                } else {
                    genNext.setVisible(false);
                    currentTestNum = testShuffle.pop();

                    onQuestion.setText(UsualFrame.setNewText("<p class=card2 align=center>" + connection.getCardBack(deckName, currentTestNum) + "</p>"));
                    onQuestion.requestFocus();
                    buttonOn();
                    shuffleWrongAns();
                    choice_box1.setText(connection.getCardFront(deckName, currentTestNum));
                    shuffleLocation();
                    cardPageNumber++;
                    cardPage.setText("Card " + cardPageNumber + " of " + cardNumber);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Your Score: " + testScoreNum + "/" + cardNumber, "Test Complete", JOptionPane.INFORMATION_MESSAGE);
                new PlayCards(deckName, 1);
                dispose();
            }
        }
        if (e.getSource() == choice_box1) {
            genNext.setVisible(true);
            choice_box1.setColor(Color.GREEN);
            buttonoff();
            testScore.setText("Your Score is: " + ++testScoreNum + "/" + cardNumber);
        }
        if (e.getSource() == choice_box2) {
            genNext.setVisible(true);
            choice_box1.setColor(Color.GREEN);
            choice_box2.setColor(Color.red);
            choice_box3.setColor(Color.lightGray);
            choice_box4.setColor(Color.lightGray);
            buttonoff();
        }
        if (e.getSource() == choice_box3) {
            genNext.setVisible(true);
            choice_box1.setColor(Color.GREEN);
            choice_box2.setColor(Color.lightGray);
            choice_box3.setColor(Color.red);
            choice_box4.setColor(Color.lightGray);
            buttonoff();
        }
        if (e.getSource() == choice_box4) {
            genNext.setVisible(true);
            choice_box1.setColor(Color.GREEN);
            choice_box2.setColor(Color.lightGray);
            choice_box3.setColor(Color.lightGray);
            choice_box4.setColor(Color.red);
            buttonoff();
        }
        if (e.getSource() == typingEnter) {
            typingEnter.setVisible(false);
            genNext.setVisible(true);
            typingAnswer.setEditable(false);
            if (typingAnswer.getText().trim().equalsIgnoreCase(connection.getCardFront(deckName, currentTestNum))) {
                typingAnswer.setBackground(Color.green);
                testScore.setText("Your Score is: " + ++testScoreNum + "/" + cardNumber);
            } else {
                typingAnswer.setBackground(Color.red);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void generateReview() {
        review_panelCard = new JPanel();
        review_panelCard.setLayout(null);
        view.remove(review_panelCard);
        review_panelCard.setBorder(UsualFrame.BACK_CARD_COLOR);
        review_panelCard.setBackground(UsualFrame.BACKGROUND_CARD);
        review_panelCard.setBounds(50, 150, 700, 375);
        if (cardNumber > 0) {

            view_left = new DefaultButton("<", 0, 150, 50, 100);
            view_right = new DefaultButton(">", 650, 150, 50, 100);
            view_left.addMouseListener(this);
            view_right.addMouseListener(this);
            rev_front = new DefaultLabel("<p class=card align=center>" + connection.getCardFront(deckName, cardPageNumber) + "</p>");
            rev_front.setBounds(50, 50, 600, 280);
            rev_back = new DefaultLabel("<p class=card align=center>" + connection.getCardBack(deckName, cardPageNumber) + "</p>");
            rev_back.setBounds(50, 50, 600, 280);
            flip = new DefaultButton("Flip Card");
            flip.setBounds(510, 50, 140, 30);
            flip.addMouseListener(this);
            cardDelete = new DefaultButton("Delete");
            cardDelete.setBounds(150, 50, 100, 30);
            cardDelete.addMouseListener(this);
            cardDelete.setForeground(Color.red);
            cardEdit = new DefaultButton("Edit");
            cardEdit.setBounds(50, 50, 80, 30);
            cardEdit.addMouseListener(this);
            cardEdit.setForeground(Color.black);
            review_panelCard.add(view_left);
            review_panelCard.add(view_right);
            review_panelCard.add(rev_front);
            review_panelCard.add(rev_back);
            rev_front.setVisible(false);
            review_panelCard.add(flip);
            review_panelCard.add(cardDelete);
            review_panelCard.add(cardEdit);

        } else {
            DefaultLabel add_card = new DefaultLabel("<p class=card>This deck is empty<br>Click Create Card</p>");
            add_card.setBounds(50, 50, 600, 280);
            review_panelCard.add(add_card);
            cardPage.setText("");
        }
        view.add(review_panelCard);
    }

    public void generateMultipleTest() {
        multipleChoice_panelCard = new JPanel();
        multipleChoice_panelCard.setLayout(null);
        multipleChoice.remove(multipleChoice_panelCard);
        multipleChoice_panelCard.setBorder(UsualFrame.BACK_CARD_COLOR);
        multipleChoice_panelCard.setBounds(50, 150, 700, 275);
        multipleChoice_panelCard.setBackground(UsualFrame.BACKGROUND_CARD);
        shuffle(cardNumber);
        currentTestNum = testShuffle.pop();
        onQuestion = new DefaultLabel("<p class=card2 align=center>" + connection.getCardBack(deckName, currentTestNum) + "</p>");
        onQuestion.setBounds(50, 50, 600, 175);

        multipleChoice_panelCard.add(onQuestion);

        choice_box1 = new DefaultButton(connection.getCardFront(deckName, currentTestNum));
        choice_box2 = new DefaultButton("Gamay");
        choice_box3 = new DefaultButton("just smile");
        choice_box4 = new DefaultButton("all of the above");
        buttonOn();
        shuffleLocation();
        shuffleWrongAns();

        multipleChoice.add(choice_box1);
        multipleChoice.add(choice_box2);
        multipleChoice.add(choice_box3);
        multipleChoice.add(choice_box4);

        multipleChoice.add(multipleChoice_panelCard);
    }

    public void generateTypingTest() {
        typingTest_panelCard = new JPanel();
        typingTest_panelCard.setLayout(null);
        write.remove(typingTest_panelCard);
        typingTest_panelCard.setBorder(UsualFrame.BACK_CARD_COLOR);
        typingTest_panelCard.setBounds(50, 150, 700, 275);
        typingTest_panelCard.setBackground(UsualFrame.BACKGROUND_CARD);
        typingAnswer = new JTextField();
        typingAnswer.setBounds(200, 450, 400, 80);
        typingAnswer.setFont(new Font("Arial", Font.PLAIN, 28));
        typingAnswer.requestFocus();
        typingEnter = new DefaultButton("Submit");
        typingEnter.setBounds(300, 540, 200, 50);
        typingEnter.addMouseListener(this);
        typingEnter.setBackground(Color.white);

        shuffle(cardNumber);
        currentTestNum = testShuffle.pop();
        onQuestion = new DefaultLabel("<p class=card2 align=center>" + connection.getCardBack(deckName, currentTestNum) + "</p>");
        onQuestion.setBounds(50, 50, 600, 175);
        onQuestion.setForeground(Color.black);
        onQuestion.setFont(new Font("Arial", Font.PLAIN, 28));

        typingTest_panelCard.add(onQuestion);

        genNext.setBounds(300, 540, 200, 50);
        genNext.setVisible(false);
        write.add(genNext);
        write.add(typingEnter);
        write.add(typingAnswer);
        write.add(typingTest_panelCard);
    }

    public void flip() {
        if (rev_back.isVisible()) {
            rev_back.setVisible(false);
            rev_front.setVisible(true);
            review_panelCard.setBorder(UsualFrame.FRONT_CARD_COLOR);
        } else {
            rev_back.setVisible(true);
            rev_front.setVisible(false);
            review_panelCard.setBorder(UsualFrame.BACK_CARD_COLOR);
        }
    }

    public void shuffle(int num) {
        testShuffle = new Stack<Integer>();
        for (int i = 1; i <= num; i++) {
            testShuffle.push(i);
        }
        Collections.shuffle(testShuffle);
    }

    public void shuffleLocation() {
        LinkedList<Rectangle> r = new LinkedList<Rectangle>();
        r.push(new Rectangle(50, 440, 345, 70));
        r.push(new Rectangle(405, 440, 345, 70));
        r.push(new Rectangle(50, 520, 345, 70));
        r.push(new Rectangle(405, 520, 345, 70));
        Collections.shuffle(r);
        choice_box1.setBounds(r.pop());
        choice_box2.setBounds(r.pop());
        choice_box3.setBounds(r.pop());
        choice_box4.setBounds(r.pop());
    }

    public void buttonOn() {
        choice_box1.addMouseListener(this);
        choice_box2.addMouseListener(this);
        choice_box3.addMouseListener(this);
        choice_box4.addMouseListener(this);
        choice_box1.setColor(Color.white);
        choice_box2.setColor(Color.white);
        choice_box3.setColor(Color.white);
        choice_box4.setColor(Color.white);
        choice_box1.buttonEnable();
        choice_box2.buttonEnable();
        choice_box3.buttonEnable();
        choice_box4.buttonEnable();
    }

    public void buttonoff() {
        choice_box1.removeMouseListener(this);
        choice_box2.removeMouseListener(this);
        choice_box3.removeMouseListener(this);
        choice_box4.removeMouseListener(this);
        choice_box1.buttonDisable();
        choice_box2.buttonDisable();
        choice_box3.buttonDisable();
        choice_box4.buttonDisable();
    }

    public void shuffleWrongAns() {
        LinkedList<Integer> num = new LinkedList<Integer>();
        for (int i = 0; i < cardNumber; i++) {
            if (i + 1 != currentTestNum) {
                num.push(i + 1);
            }
        }
        Collections.shuffle(num);
        choice_box2.setText(connection.getCardFront(deckName, num.pop()));
        choice_box3.setText(connection.getCardFront(deckName, num.pop()));
        choice_box4.setText(connection.getCardFront(deckName, num.pop()));
    }
}
