import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JTextField addItemField = new JTextField(16);
    JButton submitter;
    String itemName;
    Retriever retriever;
    JLabel resultField;
    ArrayList<Integer> prices;

    public Window(Retriever ret) {
        this.submitter = new JButton("Submit");
        this.resultField = new JLabel();
        this.retriever = ret;
        this.submitter.addActionListener(this);
        mainPanel.add(addItemField);
        mainPanel.add(submitter);
        mainPanel.add(this.resultField);
    }

    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        String input = addItemField.getText();
        if (value.equals("Submit") && !input.isEmpty()) {
            itemName = input;
            addItemField.setText("");
            try {
                retriever.getActiveOrders(itemName);
                prices = retriever.getPrices("sell");
                this.resultField.setText(prices.toString());
                super.update(this.getGraphics());
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }



    public JPanel render() {
        return mainPanel;
    }

}
