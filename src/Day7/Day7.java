package Day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day7 {
    ArrayList<Integer> crabs = new ArrayList<>();

    public int calculateSumPart1(ArrayList<Integer> crabs, int fuel) {
        int sum = 0;
        for (int i : crabs) {
            sum += Math.abs(i - fuel);
        }
        return sum;
    }

    public long calculateSumPart2(ArrayList<Integer> crabs, long fuel) {
        long sum = 0;
        for (long i : crabs) {
            sum += previousAdding(Math.abs(i - fuel));
        }
        return sum;
    }

    public long previousAdding(long fuel) {
        long result = 0;
        for (long i = 1; i <= fuel; i++) {
            result += i;
        }
        return result;
    }


    public static void main(String[] args) {
        Day7 d = new Day7();
        BufferedReader reader;
        File input = new File("src/Day7/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                String [] crabNums = line.split(",");
                for (String a : crabNums) {
                    int number = Integer.parseInt(a);
                    d.crabs.add(number);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long least = d.calculateSumPart2(d.crabs, 1);
        for (long i = 1; i < 2000; i++) {
            long temp = d.calculateSumPart2(d.crabs, i);
            if (temp < least) {
                least = temp;
            }
        }

        System.out.println(least);
    }
}
