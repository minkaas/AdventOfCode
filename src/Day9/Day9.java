package Day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day9 {
    int[][] allPoints = new int[100][100];
    ArrayList<Integer> lowPoints = new ArrayList<>();
    ArrayList<int[]> lowPointsCoords = new ArrayList<>();

    public ArrayList<int[]> recurse(int[] og) {
        int i = og[0];
        int j = og[1];
        int down;
        int up;
        int left;
        int right;
        int current = allPoints[i][j];
        ArrayList<int[]> result = new ArrayList<>();
        result.add(new int[]{i, j});
        if (i != 99) {
            down = allPoints[i+1][j];
            if (current < down && down != 9) {
                result.addAll(recurse(new int[]{i+1, j}));
            }
        }
        if (i!= 0) {
            up = allPoints[i-1][j];
            if (current < up && up != 9) {
                result.addAll(recurse(new int[]{i-1, j}));
            }
        }
        if (j != 0) {
            left = allPoints[i][j-1];
            if (current < left && left != 9) {
                result.addAll(recurse(new int[]{i, j-1}));
            }
        }
        if (j != 99) {
            right = allPoints[i][j+1];
            if (current < right && right != 9) {
                result.addAll(recurse(new int[]{i, j+1}));
            }
        }
        return result;
    }

    public ArrayList<Integer> findAllBasins() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] coords : lowPointsCoords) {
            int i = coords[0];
            int j = coords[1];
            ArrayList<int[]> temp = recurse(new int[]{i, j});
            ArrayList<int[]> temp2 = new ArrayList<>();
            for (int[] arrays : temp) {
                boolean te = true;
                for (int[] arrays2 : temp2) {
                    if (Arrays.equals(arrays, arrays2)) {
                        te = false;
                    }
                }
                if (te) {
                    temp2.add(arrays);
                }
            }
            result.add(temp2.size());
        }
        return result;
    }


    public ArrayList<int[]> getLowPoints(int[][] points) {
        int i = 0;
        ArrayList<int[]> result = new ArrayList<>();
        while (i < 100) {
            if (i == 0) {
                if (points[i][0] < points[i][1] && points[i][0] < points[i + 1][0]) {
                    result.add(new int[]{i, 0});
                    lowPoints.add(points[i][0]);
                }
                if (points[i][99] < points[i][98] && points[i][99] < points[i + 1][99]) {
                    result.add(new int[]{i, 99});
                    lowPoints.add(points[i][99]);
                }
                for (int j = 1; j < 99; j++) {
                    int current = points[i][j];
                    int under = points[i + 1][j];
                    int left = points[i][j - 1];
                    int right = points[i][j + 1];
                    if (current < under && current < left && current < right) {
                        result.add(new int[]{i, j});
                        lowPoints.add(current);
                    }
                }
                i++;
            } else if (i == 99) {
                if (points[i][0] < points[i][1] && points[i][0] < points[i - 1][0]) {
                    result.add(new int[]{i, 0});
                    lowPoints.add(points[i][0]);
                }
                if (points[i][99] < points[i][98] && points[i][99] < points[i - 1][99]) {
                    result.add(new int[]{i, 99});
                    lowPoints.add(points[i][99]);
                }
                for (int j = 1; j < 99; j++) {
                    int current = points[i][j];
                    int over = points[i - 1][j];
                    int left = points[i][j - 1];
                    int right = points[i][j + 1];
                    if (current < over && current < left && current < right) {
                        result.add(new int[]{i, j});
                        lowPoints.add(current);
                    }
                }
                i++;
            } else {
                if (points[i][0] < points[i][1] && points[i][0] < points[i-1][0] &&
                points[i][0] < points[i+1][0]) {
                    result.add(new int[]{i, 0});
                    lowPoints.add(points[i][0]);
                }
                if (points[i][99] < points[i][98] && points[i][99] < points[i - 1][99] &&
                points[i][99] < points[i+1][99]) {
                    result.add(new int[]{i, 99});
                    lowPoints.add(points[i][99]);
                }
                for (int j = 1; j < 99; j++) {
                    int current = points[i][j];
                    int under = points[i+1][j];
                    int over = points[i - 1][j];
                    int left = points[i][j - 1];
                    int right = points[i][j + 1];
                    if (current < over && current < left && current < right && current < under) {
                        result.add(new int[]{i, j});
                        lowPoints.add(current);
                    }
                }
                i++;
            }
        }
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
        d.lowPointsCoords = d.getLowPoints(d.allPoints);
        ArrayList<Integer> allBasins = d.findAllBasins();
        Collections.sort(allBasins);
        Collections.reverse(allBasins);
        int multiply = 1;
        for (int p = 0; p < 3; p++) {
            System.out.println(allBasins.get(p));
            multiply = multiply*allBasins.get(p);
        }
        System.out.println(multiply);
    }
}
