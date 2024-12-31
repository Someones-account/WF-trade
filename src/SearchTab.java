import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;

public class SearchTab extends JPanel implements ActionListener {
    JTextField addItemField = new JTextField(16);
    JButton submitter;
    String itemName;
    JLabel resultField;
    Retriever retriever;
    Calculator calculator;

    public SearchTab(Retriever retriever, Calculator calculator) {
        this.retriever = retriever;
        this.calculator = calculator;
        this.submitter = new JButton("Submit");
        this.resultField = new JLabel();
        this.submitter.addActionListener(this);
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
}
