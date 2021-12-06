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

    public ArrayList<Integer> iteratePart1(ArrayList<Integer> fish) {
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

    public long[] iteratePart2(long[] totalFish) {
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
                    d.totalFish[k] += 1;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < days; i++){
            d.totalFish = d.iteratePart2(d.totalFish);
        }
        System.out.println(d.sum(d.totalFish));
    }
}
