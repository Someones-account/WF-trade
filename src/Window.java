import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JPanel {
    private final Calculator calculator;
    private final Retriever retriever;
    private final DataManager dataManager;
    JTabbedPane mainPanel = new JTabbedPane();
    ArrayList<Integer> prices;
    StorageTab storage;
    SearchTab search;
    AboutTab about;

    public Window(Retriever ret, Calculator calc, DataManager dm) {
        this.retriever = ret;
        this.dataManager = dm;
        this.calculator = calc;
        this.storage = new StorageTab(retriever, dataManager);
        this.search = new SearchTab(retriever, calculator);
        this.about = new AboutTab();
    }

    public JTabbedPane render() throws IOException {
        mainPanel.add("Your storage", storage.initialRender());
        mainPanel.add("Search items", search.initialRender());
        mainPanel.add("About", about.render());
        return mainPanel;
    }



}
