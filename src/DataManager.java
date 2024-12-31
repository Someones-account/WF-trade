import java.io.*;
import java.util.ArrayList;

public class DataManager {
    public ArrayList<String> readLineByLine(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        ArrayList<String> records = new ArrayList<>();

        while (line != null) {
            records.add(line);
            line = reader.readLine();
        }

        reader.close();
        return records;
    }

    public void writeLine(String filename, String newLine, String amount, String priceGoal) throws IOException {
        FileWriter myWriter = new FileWriter(filename, true);
        myWriter.write(newLine + "-" + amount + "-" + priceGoal + "\n");
        myWriter.close();
    }

    public void deleteLine(String filename, String itemName, String amount, String priceGoal) throws IOException {
        File initialFile = new File(filename);
        File tempFile = new File("tempFile.txt");
        FileWriter myWriter = new FileWriter(tempFile);
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String currentLine = reader.readLine();
        String eliminateLine = itemName + "-" + amount + "-" + priceGoal;

        while (currentLine != null) {
            if (!currentLine.trim().equals(eliminateLine)) {
                myWriter.write(currentLine + "\n");
            }
            currentLine = reader.readLine();
        }

        reader.close();
        myWriter.close();
        initialFile.delete();
        tempFile.renameTo(initialFile);
    }

}
