import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.FileWriter;

public class Window extends JFrame implements ActionListener {
    private final Calculator calculator;
    private final Retriever retriever;
    JPanel mainPanel = new JPanel();
    JTextField addItemField = new JTextField(16);
    JButton submitter;
    String itemName;
    JLabel resultField;
    ArrayList<Integer> prices;

    public Window(Retriever ret, Calculator calc) {
        this.submitter = new JButton("Submit");
        this.resultField = new JLabel();
        this.retriever = ret;
        this.submitter.addActionListener(this);
        this.calculator = calc;
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
                TreeMap<Integer, Integer> priceStats = calculator.occurrences(retriever.activeSellOrders);
                this.resultField.setText(priceStats.toString());
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
