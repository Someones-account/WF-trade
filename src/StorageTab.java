import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class StorageTab implements ActionListener {
    private final DataManager dataManager;
    JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    JButton addButton = new JButton("+ Add");
    JButton deleteButton = new JButton("Delete");
    JButton saveButton = new JButton("Save");
    JTable listedItems;
    Integer currentRow;
    String[] editedRow = new String[3];

    public StorageTab(DataManager dm) {
        this.dataManager = dm;
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    public JPanel initialRender () throws IOException {
        ArrayList<String> items = this.dataManager.readLineByLine("src/items.txt");
        ArrayList<String[]> parsedData = new ArrayList<>();
        String[] names = {"Name", "Amount", "Price"};
        for (String item : items) {
            parsedData.add(item.split("-"));
        }
        String[][] DataAsList = parsedData.toArray(new String[parsedData.size()][]);
        listedItems = new JTable(DataAsList, names);

        // Visual settings
        listedItems.setFont(new Font("Arial", Font.PLAIN, 30));
        listedItems.setRowHeight(40);
        listedItems.getColumnModel().getColumn(0).setPreferredWidth(400);
        JScrollPane scrollPane = new JScrollPane(listedItems);
        listedItems.setPreferredScrollableViewportSize(listedItems.getPreferredSize());
        listedItems.setFillsViewportHeight(true);

        // Find a row clicked for deletion
        listedItems.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent e) {
                System.out.println(listedItems.rowAtPoint(e.getPoint()));
                if (listedItems.rowAtPoint(e.getPoint()) != -1) {
                    currentRow = listedItems.rowAtPoint(e.getPoint());
                    for (int i = 0; i < 3; i++) {
                        editedRow[i] = listedItems.getModel().getValueAt(currentRow, i).toString();
                    }
                }
            }
        });

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(lowerButtonPanel(), BorderLayout.SOUTH);
        return mainPanel;
    }
    public void rerender () throws IOException {
        mainPanel.removeAll();
        mainPanel = initialRender();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel lowerButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.setMaximumSize(new Dimension(300, 100));
        buttonPanel.setPreferredSize(new Dimension(300, 100));
        buttonPanel.setSize(new Dimension(300, 100));
        return buttonPanel;
    }

    public void deleteChosenRow () throws IOException {
        if (currentRow != null && listedItems.getSelectedRow() != -1) {
            String[] values = new String[3];
            for (int i = 0; i < 3; i++) {
                values[i] = listedItems.getModel().getValueAt(currentRow, i).toString();
            }
            dataManager.deleteLine("src/items.txt", values[0], values[1], values[2]);
            rerender();
        }
    }

    public void changeRow () throws IOException {
        if (currentRow != null) {
            String[] values = new String[3];
            for (int i = 0; i < 3; i++) {
                values[i] = listedItems.getModel().getValueAt(currentRow, i).toString();
            }
            dataManager.deleteLine("src/items.txt",  editedRow[0], editedRow[1], editedRow[2]);
            dataManager.writeLine("src/items.txt", values[0], values[1], values[2]);
            rerender();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            AddItemWindow add = new AddItemWindow(this);
            add.render();
        }
        if (e.getSource() == deleteButton) {
            try {
                deleteChosenRow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == saveButton) {
            try {
                changeRow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
