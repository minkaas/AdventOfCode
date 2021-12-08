package Day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
// 2 3 7 of 4
public class Day8 {
    public static void main(String[] args) {
        Day8 d = new Day8();
        BufferedReader reader;
        File input = new File("src/Day8/input");
        int counter = 0;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                String[] inputs = line.split(" . ");
                String[] displayed = inputs[1].split(" ");
                for (String dis : displayed) {
                    if (dis.length() == 2 || dis.length() == 3 || dis.length() == 4 || dis.length() == 7) {
                        counter++;
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }
}
