import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeMap;

public class SearchTab extends JPanel implements ActionListener {
    JTextField searchField = new JTextField(25);
    JButton button = new JButton("Search");
    JPanel panel = new JPanel();
    JPanel searchPanel = new JPanel();
    JPanel dataPanel = new JPanel();
    JPanel wrapper = new JPanel();
    String itemName;
    Retriever retriever;
    Calculator calculator;

    public SearchTab(Retriever retriever, Calculator calculator) {
        this.retriever = retriever;
        this.calculator = calculator;
        this.button.addActionListener(this);
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.wrapper.setLayout(new GridBagLayout());
        this.dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        this.searchField.setMaximumSize(new Dimension(300, 75));
        this.searchField.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public JPanel initialRender() {
        searchPanel.add(searchField);
        searchPanel.add(button);
        panel.add(searchPanel);
        panel.add(dataPanel);
        return panel;
    }

    public void generateResultPanel() {
        wrapper.removeAll();
        dataPanel.removeAll();

        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 22));
        TreeMap<Integer, Integer> priceStats = calculator.occurrences(retriever.activeSellOrders);
        ArrayList<Integer> selling = retriever.getPrices("sell");
        ArrayList<Integer> buying = retriever.getPrices("buy");
        Integer[] mode = calculator.getMode(priceStats);

        JLabel modeLabel = new JLabel("Mode (most occurring price): " + mode[0] + ", occurrences: " + mode[1]);
        JLabel dropLabel = new JLabel("Mode-lowest difference: " + calculator.priceDrop(priceStats) + "%");
        JLabel spreadLabel = new JLabel("Sell-buy difference: " + calculator.priceSpread(selling, buying));
        JLabel priceLabel = new JLabel("Maximal selling price: " + calculator.maxPrice(selling) + ", Minimal selling price: " + calculator.minPrice(selling));
        EmptyBorder border = new EmptyBorder(10, 5, 10, 5);
        modeLabel.setBorder(border);
        dropLabel.setBorder(border);
        spreadLabel.setBorder(border);
        priceLabel.setBorder(border);

        dataPanel.add(modeLabel);
        dataPanel.add(dropLabel);
        dataPanel.add(spreadLabel);
        dataPanel.add(priceLabel);

        wrapper.add(dataPanel);
        panel.add(wrapper);
    }

    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        String input = searchField.getText().toLowerCase(Locale.ROOT);
        if (value.equals("Search") && !input.isEmpty()) {
            itemName = input.trim();
            searchField.setText("");
            try {
                retriever.getActiveOrders(itemName);
                generateResultPanel();
                super.update(this.getGraphics());
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
