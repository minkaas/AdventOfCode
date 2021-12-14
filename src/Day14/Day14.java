package Day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day14 {
    ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
    long[] pairAmounts;
    int last;
    HashMap<Integer, ArrayList<Integer>> possiblePairs = new HashMap<>();
    HashMap<ArrayList<Integer>, Integer> insertions = new HashMap<>();
    HashMap<String, Integer> numString = new HashMap<>();

    public void possiblePairs() {
        int count = 0;
        for (int a : numString.values()) {
            for (int b : numString.values()) {
                possiblePairs.put(count, new ArrayList<>( Arrays.asList(a, b)));
                count++;
            }
        }
        pairAmounts = new long[possiblePairs.size()];
        for (ArrayList<Integer> a : pairs) {
            for (Integer b : possiblePairs.keySet()) {
                if (possiblePairs.get(b).equals(a)) {
                    pairAmounts[b]++;
                }
            }
        }
    }

    public void doOneStep() {
        long[] result = new long[pairAmounts.length];
        for (int i = 0; i < pairAmounts.length; i++) {
            if (pairAmounts[i] != 0) {
                ArrayList<Integer> a = possiblePairs.get(i);
                if (insertions.get(a) != null) {
                    ArrayList<Integer> one = new ArrayList<>(Arrays.asList(a.get(0), insertions.get(a)));
                    ArrayList<Integer> two = new ArrayList<>(Arrays.asList(insertions.get(a), a.get(1)));
                    for (Integer b : possiblePairs.keySet()) {
                        if (possiblePairs.get(b).equals(one) || possiblePairs.get(b).equals(two)) {
                            result[b] = result[b] + pairAmounts[i];
                        }
                    }
                } else {
                    result[i] = result[i] + pairAmounts[i];
                }
            }
        }
        System.arraycopy(result, 0, pairAmounts, 0, pairAmounts.length);
    }

    public long[] leastAndMost() {
        long[] counts = new long[numString.size()];
        for (int i = 0; i < pairAmounts.length; i++) {
            if (pairAmounts[i] != 0) {
                ArrayList<Integer> a = possiblePairs.get(i);
                counts[a.get(0)] = counts[a.get(0)] + pairAmounts[i];
            }
        }
        counts[last]++;
        long least = counts[0];
        long most = counts[0];
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < least) {
                least = counts[i];
            }
            if (counts[i] > most) {
                most = counts[i];
            }
        }
        return new long[]{least, most};
    }

    public static void main(String[] args) {
        Day14 d = new Day14();
        BufferedReader reader;
        File input = new File("src/Day14/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            int i = 0;
            int nums = 0;
            while (line != null) {
                if (i==0) {
                    String[] polymer = line.split("");
                    for (int j = 0; j < polymer.length; j++) {
                        if (!(d.numString.containsKey(polymer[j]))) {
                            d.numString.put(polymer[j], nums);
                            nums++;
                        }
                        d.last = nums;
                    }
                    for (int j = 0; j < polymer.length - 1; j++) {
                        int one = d.numString.get(polymer[j]);
                        int two = d.numString.get(polymer[j+1]);
                        d.pairs.add(new ArrayList<>(Arrays.asList(one, two)));
                    }
                    i++;
                } else {
                    String[] insertions = line.split(" -> ");
                    String[] pairs = insertions[0].split("");
                    if (!(d.numString.containsKey(insertions[1]))) {
                        d.numString.put(insertions[1], nums);
                        nums++;
                    } else if (!(d.numString.containsKey(pairs[0]))) {
                        d.numString.put(pairs[0], nums);
                        nums++;
                    } else if (!(d.numString.containsKey(pairs[1]))) {
                        d.numString.put(pairs[1], nums);
                        nums++;
                    }
                    int one = d.numString.get(pairs[0]);
                    int two = d.numString.get(pairs[1]);
                    int three = d.numString.get(insertions[1]);
                    d.insertions.put(new ArrayList<>(Arrays.asList(one, two)), three);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.possiblePairs();
        for (int i = 0; i < 40; i++) {
            d.doOneStep();
            System.out.println("Did step: " + (i + 1));
        }
        long[] leastAndMost = d.leastAndMost();
        System.out.println("least: " + leastAndMost[0] + "\nmost: " + leastAndMost[1] + "\nresult: " + (leastAndMost[1] - leastAndMost[0]));
    }
}
