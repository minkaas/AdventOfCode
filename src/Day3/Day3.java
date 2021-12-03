package Day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    ArrayList<String> consideringO2 = new ArrayList<>();
    ArrayList<String> consideringCO2 = new ArrayList<>();

    public String[] part1(ArrayList<String> a) {
        String[] gamandeps = new String[2];
        String gamma = "";
        String epsilon = "";
        double total = 0;
        double len = a.size();
        for (int i = 0; i < 12; i++) {
            for (String b : a) {
                total += Integer.parseInt(String.valueOf(b.charAt(i)));
            }
            if (total / len >= 0.5) {
                gamma += "1";
                epsilon += "0";
            } else {
                gamma += "0";
                epsilon += "1";
            }
            total = 0;
        }
        gamandeps[0] = gamma;
        gamandeps[1] = epsilon;
        return gamandeps;
    }

    public ArrayList<String> considerNextInteger(ArrayList<String> a, int index, boolean most) {
        char common = '0';
        double len = a.size();
        double total = 0;
        for (String c : a) {
            total += Integer.parseInt(String.valueOf(c.charAt(index)));
        }
        if (total / len >= 0.5) {
            common = '1';
        }

        ArrayList<String> b = new ArrayList<>();
        for (String all : a) {
           if (most && all.charAt(index) == common) {
               b.add(all);
           } else if (!most && all.charAt(index) != common) {
               b.add(all);
           }
        }
        return b;
    }

    public static void main(String[] args) {
        Day3 d = new Day3();
        BufferedReader reader;
        File input = new File("src/Day3/input");
        int[] numbers = new int[12];
        int i;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                d.consideringCO2.add(line);
                d.consideringO2.add(line);
                line = reader.readLine();
            }
            String[] Part1 = d.part1(d.consideringO2);
            System.out.println("The gamma value is: " + Part1[0]);
            System.out.println("The epsilon value is: " + Part1[1]);
            System.out.println("Those multiplied together (and thus the answer to part 1) is: " + Integer.parseInt(Part1[0], 2) * Integer.parseInt(Part1[1], 2));

            i = 0;
            while(d.consideringCO2.size() != 1) {
                d.consideringCO2 = d.considerNextInteger(d.consideringCO2, i, false);
                i++;
            }
            i = 0;
            while (d.consideringO2.size() != 1) {
                d.consideringO2 = d.considerNextInteger(d.consideringO2, i, true);
                i++;
            }

            System.out.println("\nThe final oxygen generator rating is: " + d.consideringO2.get(0));
            System.out.println("The final CO2 scrubber rating is: " + d.consideringCO2.get(0));
            int oxygen = Integer.parseInt(d.consideringO2.get(0), 2);
            int scrubber = Integer.parseInt(d.consideringCO2.get(0), 2);
            System.out.println("Those multiplied together is: " + oxygen * scrubber);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
