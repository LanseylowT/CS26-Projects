import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class LaunchPage extends JFrame implements ActionListener {

    JButton myButton = new JButton("LISTIFY");
    ImageIcon image = new ImageIcon(getClass().getResource("/logo1.png"));
    LaunchPage(){

        myButton.setBounds(130,500,250,60);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        myButton.setForeground(MyColors.WHITE);
        myButton.setBackground(MyColors.LIGHT_BLUE);


        ImageIcon logo1 = new ImageIcon(getClass().getResource("/logo1.png"));
        add(myButton);

        setTitle("Listify");
        getContentPane().setBackground(MyColors.DARK_BLUE);

        setIconImage(logo1.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(CommonConstants.GUI_SIZE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);



        addLabel();


    }

    private void addLabel(){
        JLabel label = new JLabel();


        label.setText("<html><p>Hello, this is the final project of our group <br>composed of Joshua Abellanosa,Sandara Da-anoy and " +
                "Steven Rey Pipe." +
                "<br><br>Our app is a simple To-Do-List app named Listify inspired by the lack of time management we have noticed since becoming a student." +
                "<br><br>Our simple to do list up also has simple functions that even a kid knows how to use it.  " +
                "<br><br>But it may also have some bugs. Enjoy!</p><html>");


        label.setBounds(10,40,500,500);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(createFont("resources/LEMONMILK-Light.otf", 23f));
        label.setSize(CommonConstants.GUI_SIZE);

        repaint();
        revalidate();
        this.getContentPane().add(label);

    }

    private Font createFont(String resource, float size){
        // get the font file path
        String filePath = getClass().getClassLoader().getResource(resource).getPath();

        // check to see if the path contains a folder with spaces in them
        if(filePath.contains("%20")){
            filePath = getClass().getClassLoader().getResource(resource).getPath()
                    .replaceAll("%20", " ");
        }

        // create font
        try{
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==myButton){
            dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ToDoListGui().setVisible(true);
                }
            });
        }
    }
}
