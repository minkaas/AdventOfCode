package Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

    public static void main(String[] args) {
        BufferedReader reader;
        File input = new File("src/Day1/input");
        int[] parsedInput = new int[2001];
        int i = 0;
        int j = 0;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            int number = Integer.parseInt(line);
            while (line != null) {
                parsedInput[i] = number;
                line = reader.readLine();
                if (line != null) {
                    number = Integer.parseInt(line);
                }
                i++;
            }
            System.out.println(i);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (i = 2; i < 1999; i++) {
            int a = parsedInput[i-2] + parsedInput[i-1] + parsedInput[i];
            int b = parsedInput[i-1] + parsedInput[i] + parsedInput[i+1];
            if (a < b) {
                j++;
            }
        }
        System.out.println("Bigger inputs: " + j);
    }
}
