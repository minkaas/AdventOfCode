package Day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day14 {
    public static void main(String[] args) {
        Day14 d = new Day14();
        BufferedReader reader;
        File input = new File("src/Day14/input");
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
