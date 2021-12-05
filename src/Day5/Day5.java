package Day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day5 {
    int[][] allNums = new int[1000][1000];

    public int testAllNums(int[][] array) {
        int result = 0;
        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (array[i][j] > 1) {
                    result += 1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Day5 d = new Day5();

        BufferedReader reader;
        File input = new File("src/Day5/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                String[] numbers = line.split("->");
                numbers[0] = numbers[0].replace(',', '.');
                numbers[1] = numbers[1].replace(',', '.');
                float one = Float.parseFloat(numbers[0].substring(0, numbers[0].length() - 1));
                float two = Float.parseFloat(numbers[1]);
                int oney = Integer.parseInt(numbers[0].substring(0, numbers[0].length() - 1).split("\\.")[1]);
                int twoy = Integer.parseInt(numbers[1].split("\\.")[1]);
                int onex = (int) (Math.floor(one));
                int twox = (int) (Math.floor(two));
                int i;
                int j;
                if (onex == twox || oney == twoy) {
                    if (onex == twox) {
                        i = Math.min(oney, twoy);
                        j = onex;
                        for (; i <= Math.max(oney, twoy); i++) {
                            d.allNums[i][j] += 1;
                        }
                    } else {
                        i = Math.min(onex, twox);
                        j = oney;
                        for (; i <= Math.max(onex, twox); i++) {
                            d.allNums[j][i] += 1;
                        }
                    }
                } else {
                    if (two > one) {
                        j = oney;
                        for (i = onex; i <= twox; i++) {
                            if (oney < twoy) {
                                if (j <= twoy) {
                                    d.allNums[j][i] += 1;
                                    j++;
                                }
                            }
                            else {
                                if (j >= twoy) {
                                    d.allNums[j][i] += 1;
                                    j--;
                                }
                            }
                        }
                    } else {
                        j = twoy;
                        for (i = twox; i <= onex; i++) {
                            if (twoy > oney) {
                                if (j >= oney) {
                                    d.allNums[j][i] += 1;
                                }
                                j--;
                            } else {
                                if (j <= oney) {
                                    d.allNums[j][i] += 1;
                                }
                                j++;
                            }
                        }
                    }
                }
                line = reader.readLine();
            }
            int result = d.testAllNums(d.allNums);
            System.out.println(result);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

