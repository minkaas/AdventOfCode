package Day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {

    public static ArrayList<String> considerNextInteger(ArrayList<String> a, int index, boolean most) {
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
        BufferedReader reader;
        File input = new File("src/Day3/input");
        int[] numbers = new int[12];
        ArrayList<String> consideringO2 = new ArrayList<>();
        ArrayList<String> consideringCO2 = new ArrayList<>();
        int i;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                char[] parsing = line.toCharArray();
                consideringCO2.add(line);
                consideringO2.add(line);
                for (i = 0; i < 12; i++) {
                    numbers[i] += Integer.parseInt(String.valueOf(parsing[i]));
                }
                line = reader.readLine();
            }
            i = 0;
            while(consideringCO2.size() != 1) {
                consideringCO2 = considerNextInteger(consideringCO2, i, false);
                i++;
            }
            i = 0;
            while (consideringO2.size() != 1) {
                consideringO2 = considerNextInteger(consideringO2, i, true);
                i++;
            }
            System.out.println(consideringO2.get(0));
            System.out.println(consideringCO2.get(0));
            int oxygen = Integer.parseInt(consideringO2.get(0), 2);
            int scrubber = Integer.parseInt(consideringCO2.get(0), 2);
            System.out.println(oxygen);
            System.out.println(scrubber);
            System.out.println(oxygen * scrubber);
            String gammas = "";
            String epsilon = "";
            for (int number : numbers) {
                if (number > 500) {
                    gammas += "1";
                } else {
                    gammas += "0";
                }
            }
            for (int number : numbers) {
                if (number > 500) {
                    epsilon += "0";
                } else {
                    epsilon += "1";
                }
            }
            int ep = Integer.parseInt(epsilon,2);
            int gam = Integer.parseInt(gammas, 2);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
