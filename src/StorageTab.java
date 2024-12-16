import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class StorageTab {
    private Retriever retriever;
    private final DataManager dataManager;
    JPanel mainPanel = new JPanel(new GridLayout(0, 1));

    public StorageTab(Retriever ret, DataManager dm) {
        this.retriever = ret;
        this.dataManager = dm;
    }

    public JPanel initialRender () throws IOException {
        ArrayList<String> items = this.dataManager.readLineByLine("src/items.txt");
        for (String item : items) {
            String[] parts = item.split("-");
            JPanel row = new JPanel();
            for (String part : parts) {
                
                row.setBorder(new EmptyBorder(8,20,8,20));

            }
            mainPanel.add(row);
        }
        return mainPanel;
    }
}
