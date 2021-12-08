package Day10;

import Day9.Day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day10 {
    public static void main(String[] args) {
        Day10 d = new Day10();
        BufferedReader reader;
        File input = new File("src/Day10/input");
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
