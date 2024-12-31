import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame mainFrame = new JFrame("WF Trading");
        Retriever retriever = new Retriever();
        Calculator calculator = new Calculator();
        DataManager dataManager = new DataManager();
        Window window = new Window(retriever, calculator, dataManager);

        mainFrame.add(window.render());
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}