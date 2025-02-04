import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.TreeMap;

public class AddItemWindow implements ActionListener {
    JFrame addWindow = new JFrame("Add new item");
    DataManager manager = new DataManager();
    JPanel panel = new JPanel();
    JTextField itemName = new JTextField(35);
    JTextField itemPrice = new JTextField(4);
    JTextField itemQuantity = new JTextField(4);
    JButton submit = new JButton("Submit");
    StorageTab storageTab;

    public AddItemWindow(StorageTab storageTab) {
        this.storageTab = storageTab;
    }

    public void render() {
        submit.addActionListener(this);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Item Name:"));
        panel.add(itemName);
        panel.add(new JLabel("Item Price:"));
        panel.add(itemPrice);
        panel.add(new JLabel("Item Quantity:"));
        panel.add(itemQuantity);
        panel.add(submit);

        addWindow.add(panel);
        addWindow.setSize(400, 300);
        addWindow.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        if (value.equals("Submit")) {
            try {
                if (!itemQuantity.getText().isEmpty() && !itemName.getText().isEmpty() && !itemPrice.getText().isEmpty()) {
                    if (itemQuantity.getText().matches("[0-9]+") && itemPrice.getText().matches("[0-9]+")) {
                        manager.writeLine("src/items.txt", itemName.getText(), itemQuantity.getText(), itemPrice.getText());
                        storageTab.rerender();
                        addWindow.dispatchEvent(new WindowEvent(addWindow, WindowEvent.WINDOW_CLOSING));
                    } else {
                        panel.setBackground(new Color(255, 123, 123));
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
