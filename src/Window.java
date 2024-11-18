import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JTextField addItemField = new JTextField(16);
    JButton submitter = new JButton("submit");
    String itemName;
    Retriever retriever;
    ArrayList<Integer> prices;

    public Window(Retriever ret) {
        mainPanel.add(addItemField);
        mainPanel.add(submitter);
        retriever = ret;
    }

    public JPanel render() {
        return mainPanel;
    }

    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        if (value.equals("submit")) {
            itemName = addItemField.getText();
            addItemField.setText("");
            try {
                retriever.getActiveOrders(itemName);
                prices = retriever.getPrices();
                mainPanel.add(new JLabel(String.valueOf(prices.get(0))));
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
