package LastYear.Day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static java.util.Arrays.sort;

public class Day5 {
    public String[] allSeats = new String[850];
    public int[] allIDs = new int[850];

    public int checkAll(int[] allIDs) {
        for (int i = 1; i < allIDs.length - 1; i++) {
            int one = allIDs[i-1];
            int two = allIDs[i];
            int three = allIDs[i+1];
            if (!(one == two - 1 && three == two + 1)) {
                return two;
            }
        }
        return 0;
    }

    public int calculateRow(String a) {
        double max = 127;
        double min = 0;
        for (int i = 0; i < 6; i++) {
            if (a.charAt(i) == 'F') {
                max = Math.floor((min + max) / 2);
            }
            else {
                min = Math.ceil((min + max) / 2);
            }
        }
        if (a.charAt(6) == 'F') {
            return (int) min;
        } else {
            return (int) max;
        }
    }

    public int calculateColumn(String a) {
        double max = 7;
        double min = 0;
        for (int i = 7; i < 9; i++) {
            if (a.charAt(i) == 'L') {
                max = Math.floor((min + max) / 2);
            }
            else {
                min = Math.ceil((min + max) / 2);
            }
        }
        if (a.charAt(9) == 'L') {
            return (int) min;
        } else {
            return (int) max;
        }    }
    public static void main(String[] args) {
        Day5 d = new Day5();
        BufferedReader reader;
        File input = new File("src/LastYear/Day5/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                d.allSeats[i] = line;
                i++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int result = 0;
        int i = 0;
        for (String a : d.allSeats) {
            int row = d.calculateRow(a);
            int column = d.calculateColumn(a);
            int id = row * 8 + column;
            d.allIDs[i] = id;
            i++;
        }
        sort(d.allIDs);
        int mySeat = d.checkAll(d.allIDs);
        System.out.println(mySeat);
        System.out.println(result);
    }
}
