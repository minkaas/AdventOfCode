package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {
    public ArrayList<Integer> allNumbers = new ArrayList<>();
    public ArrayList<int[]> allBingos = new ArrayList<>();

    public int[] checkBingo(int[] bingoCard, ArrayList<Integer> allNumbers) {
        int[] foundNums = new int[26];
        for (int i = 0; i < allNumbers.size(); i++) {
            int num = allNumbers.get(i);
            for (int j = 0; j < 25; j++) {
                if (bingoCard[j] == num) {
                    foundNums[j] = 1;
                }
                if (bingoFound(foundNums)) {
                    foundNums[25] = i;
                    return foundNums;
                }
            }
        }
        return foundNums;
    }

    public boolean bingoFound(int[] foundBingos) {
        int result = 0;
        boolean res = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (foundBingos[i+j] == 1) {
                    result++;
                }
            }
            if (result == 5) {
                res = true;
            } else {
                result = 0;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 25; j = j + 5) {
                if (foundBingos[i+j] == 1) {
                    result++;
                }
            }
            if (result == 5) {
                res = true;
            } else {
                result = 0;
            }
        }
        return res;
    }

    public int calculateSum(int[] bingoCard, int[] foundNums) {
        int sum = 0;
        for (int i = 0; i < bingoCard.length; i++) {
            if (foundNums[i] == 0) {
                sum += bingoCard[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Day4 d = new Day4();

        BufferedReader reader;
        File input = new File("src/Day4/input");
        int first = 0;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            String result = "";
            while (line != null) {
                if (first == 0) {
                    String[] allNums = line.split(",");
                    for (String a : allNums) {
                        d.allNumbers.add(Integer.parseInt(a));
                    }
                    first++;
                } else {
                    if (!(line.equals(""))) {
                        result += line + " ";
                    } else if (!(result.equals(""))){
                        String[] bingoCard = result.split(" ");
                        int[] bingo = new int[25];
                        int j = 0;
                        for (String s : bingoCard) {
                            if (!(s.equals(""))) {
                                bingo[j] = Integer.parseInt(s);
                                j++;
                            }
                        }
                        result = "";
                        d.allBingos.add(bingo);
                    }
                }
                line = reader.readLine();
            }
            String[] bingoCard = result.split(" ");
            int[] bingo = new int[25];
            int j = 0;
            for (int i = 0; i < bingoCard.length; i++) {
                if (!(bingoCard[i].equals(""))) {
                    bingo[j] = Integer.parseInt(bingoCard[i]);
                    j++;
                }
            }
            d.allBingos.add(bingo);
            int least = 100;
            int[] winner = new int[25];
            for (int[] bingos : d.allBingos) {
                int i = d.checkBingo(bingos, d.allNumbers)[25];
                if (i <= least) {
                    winner = bingos;
                    least = i;
                }
            }
            int lastNum = d.allNumbers.get(least);
            int sum = d.calculateSum(winner, d.checkBingo(winner, d.allNumbers));
            System.out.println("The sum of the board is: " + sum);
            System.out.println("The number that got you the win is: " + lastNum);
            System.out.println("So the final result is: " + sum * lastNum);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}