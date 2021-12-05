package Day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day7 {
    public static void main(String[] args) {
        Day7 d = new Day7();
        BufferedReader reader;
        File input = new File("src/Day7/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                /**
                 * Do some stuff here tomorrow
                 */
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
