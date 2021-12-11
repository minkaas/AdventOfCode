package Day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {
    int[][] allOctopi = new int[10][10];
    ArrayList<int[]> explodedAt = new ArrayList<>();
    int steps = 1000;

    public int oneStep() {
        int flashes = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                allOctopi[i][j]++;
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (allOctopi[i][j] > 9) {
                    flashes += explodeAt(new int[]{i, j}).size();
                }
            }
        }
        for (int[] a : explodedAt) {
            allOctopi[a[0]][a[1]] = 0;
        }
        explodedAt.clear();
        return flashes;
    }
    public boolean containsArray(int[] b) {
        for (int[] a : explodedAt) {
            if (Arrays.equals(a, b)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<int[]> explodeAt(int[] arr) {
        ArrayList<int[]> explodedNow = new ArrayList<>();
        ArrayList<int[]> result = new ArrayList<>();
        explodedAt.add(arr);
        explodedNow.add(arr);
        allOctopi[arr[0]][arr[1]] = -10;
        for (int i = arr[0] - 1; i <= arr[0] + 1; i++) {
            for (int j = arr[1] - 1; j<= arr[1] + 1; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10 && (!containsArray(new int[]{i, j}))) {
                    allOctopi[i][j]++;
                    if (allOctopi[i][j] > 9 && (!containsArray(new int[]{i, j}))) {
                        explodedNow.addAll(explodeAt(new int[]{i, j}));
                    }
                }
            }
        }
        for (int[] b : explodedNow) {
            boolean c = true;
            for (int[] d : result) {
                if (Arrays.equals(b, d)) {
                    c = false;
                    break;
                }
            }
            if (c) {
                result.add(b);
            }
        }
        for (int[] a : result) {
            allOctopi[a[0]][a[1]] = 0;
        }
        explodedAt.addAll(result);
        return result;
    }

    public static void main(String[] args) {
        Day11 d = new Day11();
        BufferedReader reader;
        File input = new File("src/Day11/example");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                String[] temp = line.split("");
                for (int j = 0; j < temp.length; j++) {
                    d.allOctopi[i][j] = Integer.parseInt(temp[j]);
                }
                i++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i < d.steps; i++) {
            int flashes = d.oneStep();
            sum += flashes;
            if (flashes == 100) {
                System.out.println(i);
                System.out.println(d.allOctopi);
            }
            //System.out.println(sum);
        }
    }
}
