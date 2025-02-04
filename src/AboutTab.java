import javax.swing.*;
import java.awt.*;

public class AboutTab extends JPanel {
    public JPanel render() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 22));

        panel.add(new JLabel("About this app:"));
        panel.add(new JLabel("Warframe trading"));
        panel.add(new JLabel("Version: 1.0.0"));
        panel.add(new JLabel("Author: Kvasha Oleksandr"));
        panel.add(new JLabel("2024-2025"));
        return panel;
    }
}
