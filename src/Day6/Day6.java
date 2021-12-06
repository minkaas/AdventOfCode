package Day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    ArrayList<Integer> fish = new ArrayList<>();
    long[] totalFish = new long[9];
    public static int days = 256;

    public ArrayList<Integer> iterate(ArrayList<Integer> fish) {
        int addedEights = 0;

        for (int j = 0; j < fish.size(); j++) {
            int a = fish.get(j);
            if (a == 0) {
                fish.set(j, 6);
                addedEights++;
            } else {
                fish.set(j, a - 1);
            }
        }
        for (int i = 0; i < addedEights; i++) {
            fish.add(8);
        }
        return fish;
    }

    public long[] iterate2(long[] totalFish) {
        long i = totalFish[0];
        for (int j = 0; j < totalFish.length - 1; j++) {
            totalFish[j] = totalFish[j+1];
        }
        totalFish[6] += i;
        totalFish[8] = i;
        return totalFish;
    }

    public long sum(long[] totalFish) {
        long result = 0;
        for (long a : totalFish) {
            result += a;
        }
        return result;
    }


    public static void main(String[] args) {
        Day6 d = new Day6();

        int zeroes = 0;
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        int sevens = 0;
        int eights = 0;

        BufferedReader reader;
        File input = new File("src/Day6/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                String[] allNums = line.split(",");
                for (String a : allNums) {
                    int k = Integer.parseInt(a);
                    d.fish.add(k);
                    switch (k) {
                        case 0 -> {
                            zeroes++;
                        } case 1 -> {
                            ones++;
                        } case 2 -> {
                            twos++;
                        } case 3 -> {
                            threes++;
                        } case 4 -> {
                            fours++;
                        } case 5 -> {
                            fives++;
                        } case 6 -> {
                            sixes++;
                        } case 7 -> {
                            sevens++;
                        } case 8 -> {
                            eights++;
                        }
                    }
                    d.totalFish[0] = zeroes;
                    d.totalFish[1] = ones;
                    d.totalFish[2] = twos;
                    d.totalFish[3] = threes;
                    d.totalFish[4] = fours;
                    d.totalFish[5] = fives;
                    d.totalFish[6] = sixes;
                    d.totalFish[7] = sevens;
                    d.totalFish[8] = eights;

                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < days; i++){
            d.totalFish = d.iterate2(d.totalFish);
        }
        System.out.println(d.sum(d.totalFish));
    }
}
