import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {
    public static ArrayList<String> readLineByLine(String filename) throws IOException {
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

    public static void writeLine(String filename, String newLine) throws IOException {
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write(newLine);
        myWriter.close();
    }
}
