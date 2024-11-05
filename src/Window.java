import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JTextField addItemField = new JTextField(16);
    JButton submitter = new JButton("submit");
    String itemName;

    public Window() {
        mainPanel.add(addItemField);
        mainPanel.add(submitter);
    }

    public JPanel render() {
        return mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        if (value.equals("submit")) {
            itemName = addItemField.getText();
            addItemField.setText("");
        }
    }
}
