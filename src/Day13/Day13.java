package Day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {
    int coordsx = 0;
    int coordsy = 0;
    int[][] coords = new int[1500][1500];
    ArrayList<String> folds = new ArrayList<>();

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y <= coordsy; y++) {
            for (int x = 0; x <= coordsx; x++) {
                if (coords[y][x] > 0) {
                    result.append("| |");
                } else {
                    result.append("   ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void doFolds() {
        String fold = folds.remove(0);
        if (fold.contains("y")) {
            coordsy = coordsy / 2;
            for (int y = 0; y <= coordsy; y++) {
                for (int x = 0; x <= coordsx; x++) {
                    coords[y][x] += coords[coordsy*2-y][x];
                }
            }
        } else {
            coordsx = coordsx / 2;
            for (int y = 0; y <= coordsy; y++) {
                for (int x = 0; x <= coordsx; x++) {
                    coords[y][x] += coords[y][coordsx*2-x];
                }
            }
        }
    }

    public int countDots() {
        int result = 0;
        for (int x = 0; x <= coordsx; x++) {
            for (int y = 0; y <= coordsy; y++) {
                if (coords[y][x] > 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Day13 d = new Day13();
        BufferedReader reader;
        File input = new File("src/Day13/example");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                String[] numbers = line.split(",");
                if (numbers.length == 2) {
                    int x = Integer.parseInt(numbers[0]);
                    int y = Integer.parseInt(numbers[1]);
                    d.coordsy = Math.max(d.coordsy, y);
                    d.coordsx = Math.max(d.coordsx, x);
                    d.coords[y][x] = 1;
                } else {
                    String[] fold = line.split(" ");
                    d.folds.add(fold[2]);
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.doFolds();
        System.out.println("Part 1: " + d.countDots());
        while (!(d.folds.isEmpty())) {
            d.doFolds();
        }
        System.out.println("Part 2:\n" + d);
    }
}
