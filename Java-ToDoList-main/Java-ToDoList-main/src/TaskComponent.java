import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import static java.awt.Font.SANS_SERIF;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() {
        return taskField;
    }

    // this panel is used so that we can make updates to the task component panel when deleting tasks
    private JPanel parentPanel;

    public TaskComponent(JPanel parentPanel){
        this.parentPanel = parentPanel;
        // task field
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(MyColors.GRAY_2)); // CHANGED THE COLOR OF DIS
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setContentType("text/html");
        taskField.addFocusListener(new FocusListener() {
            // indicate which task field is currently being edited
            @Override
            public void focusGained(FocusEvent e) {
                // SET NEW COLOR TO DIS YEAH
                taskField.setBackground(MyColors.GRAY_3);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // SET NEW COLOR TO DIS TO MATCH THE BACKGROUND
                taskField.setBackground(MyColors.WHITE);
            }
        });

        // checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.addActionListener(this);
        checkBox.setBackground(MyColors.BLACK_3);

        // delete button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);

        //ADDED COLOR TO DELETE BUTTON
        deleteButton.setBackground(MyColors.LIGHT_BLUE);
        deleteButton.setForeground(MyColors.WHITE);

        // add to this taskcomponent
        add(checkBox);
        add(taskField);
        add(deleteButton);

        // SET THE NEW COLOR
        setBackground(MyColors.BLACK_3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            // replaces all html tags to empty string to grab the main text
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            // add strikethrough text
            taskField.setText("<html><s>"+ taskText + "</s></html>");

        }else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
        }

        if(e.getActionCommand().equalsIgnoreCase("X")){
            // delete this component from the parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
