package Day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9 {
    int[][] allPoints = new int[100][100];
    ArrayList<int[]> uhhh = new ArrayList<>();

    public ArrayList<Integer> getLowPoints(int[][] points) {
        int i = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (i < 100) {
            if (i == 0) {
                if (points[i][0] < points[i][1] && points[i][0] < points[i + 1][0]) {
                    result.add(points[i][0]);
                }
                if (points[i][99] < points[i][98] && points[i][99] < points[i + 1][99]) {
                    result.add(points[i][99]);
                }
                for (int j = 1; j < 99; j++) {
                    int current = points[i][j];
                    int under = points[i + 1][j];
                    int left = points[i][j - 1];
                    int right = points[i][j + 1];
                    if (current < under && current < left && current < right) {
                        result.add(current);
                    }
                }
                i++;
            } else if (i == 99) {
                if (points[i][0] < points[i][1] && points[i][0] < points[i - 1][0]) {
                    result.add(points[i][0]);
                }
                if (points[i][99] < points[i][98] && points[i][99] < points[i - 1][99]) {
                    result.add(points[i][99]);
                }
                for (int j = 1; j < 99; j++) {
                    int current = points[i][j];
                    int over = points[i - 1][j];
                    int left = points[i][j - 1];
                    int right = points[i][j + 1];
                    if (current < over && current < left && current < right) {
                        result.add(current);
                    }
                }
                i++;
            } else {
                if (points[i][0] < points[i][1] && points[i][0] < points[i-1][0] &&
                points[i][0] < points[i+1][0]) {
                    result.add(points[i][0]);
                }
                if (points[i][99] < points[i][98] && points[i][99] < points[i - 1][99] &&
                points[i][99] < points[i+1][99]) {
                    result.add(points[i][99]);
                }
                for (int j = 1; j < 99; j++) {
                    int current = points[i][j];
                    int under = points[i+1][j];
                    int over = points[i - 1][j];
                    int left = points[i][j - 1];
                    int right = points[i][j + 1];
                    if (current < over && current < left && current < right && current < under) {
                        result.add(current);
                    }
                }
                i++;
            }
        }
        return result;
    }

    public ArrayList<Integer> findAllBasins(int[][] points) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        return result;
    }

    public static void main(String[] args) {
        Day9 d = new Day9();
        BufferedReader reader;
        File input = new File("src/Day9/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                char[] String = line.toCharArray();
                for (int j = 0; j < String.length; j++) {
                    d.allPoints[i][j] = Character.getNumericValue(String[j]);
                }
                line = reader.readLine();
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> lowPoints = d.getLowPoints(d.allPoints);
        int sum = lowPoints.stream().mapToInt(a -> a + 1).sum();
        System.out.println(lowPoints);
        System.out.println(sum);
    }
}
