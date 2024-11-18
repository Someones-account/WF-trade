import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame mainFrame = new JFrame("WF Trading");
        Retriever retriever = new Retriever();
        Window window = new Window(retriever);

        mainFrame.add(window.render());
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 500);

//        System.out.println(retriever.getActiveOrders(window.itemName));
//        System.out.println(retriever.getPrices());
    }
}