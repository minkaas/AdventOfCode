package Day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

    public static void main(String[] args) {
        BufferedReader reader;
        File input = new File("src/Day2/input");
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        int i = 0;
        String[] parsing;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                parsing = line.split(" ");
                switch (parsing[0]) {
                    case "forward" -> {
                        horizontal += Integer.parseInt(parsing[1]);
                        depth += aim * Integer.parseInt(parsing[1]);
                    }
                    case "down" -> {
                        //depth += Integer.parseInt(parsing[1]);
                        aim += Integer.parseInt(parsing[1]);
                    }
                    case "up" -> {
                        //depth -= Integer.parseInt(parsing[1]);
                        aim -= Integer.parseInt(parsing[1]);
                    }
                }
                line = reader.readLine();
                i++;
            }
            System.out.println("Final depth = " + depth);
            System.out.println("Final horizontal distance = " + horizontal);
            System.out.println("Final aim is = " + aim);
            System.out.println("Answer is = " + depth*horizontal);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
