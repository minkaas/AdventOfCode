package Day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day12 {
    public static void main(String[] args) {
        Day12 d = new Day12();
        BufferedReader reader;
        File input = new File("src/Day12/input");
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
