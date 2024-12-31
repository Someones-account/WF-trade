import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Window extends JPanel {
    private final Calculator calculator;
    private final Retriever retriever;
    private final DataManager dataManager;
    JTabbedPane mainPanel = new JTabbedPane();
    ArrayList<Integer> prices;
    StorageTab storage;

    public Window(Retriever ret, Calculator calc, DataManager dm) {
        this.retriever = ret;
        this.dataManager = dm;
        this.calculator = calc;
        this.storage = new StorageTab(retriever, dataManager);
    }

    public JTabbedPane render() throws IOException {
        mainPanel.add("Your storage", storage.initialRender());
        mainPanel.add("Search items", new JPanel());
        mainPanel.add("Optimal to sell", new JPanel());
        return mainPanel;
    }



}
