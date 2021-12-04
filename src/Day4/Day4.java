package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {
    public ArrayList<Integer> allNumbers = new ArrayList<>();
    public ArrayList<int[]> allBingos = new ArrayList<>();

    public int checkBingo(int[] bingoCard, ArrayList<Integer> allNumbers) {
        int[] foundNums = new int[25];
        int result = allNumbers.size();
        for (int i = 0; i < allNumbers.size(); i++) {
            for (int j = 0; j < 25; j++) {
                if (bingoCard[j] == allNumbers.get(i)) {
                    foundNums[j] = 1;
                }
                if (bingoFound(foundNums)) {
                    result = i;
                    return result;
                }
            }
        }
        return result;
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

    public int calculateSum(int[] bingoCard, ArrayList<Integer> allNums, int howMany) {
        int sum = 0;
        for (int a : bingoCard) {
            sum += a;
        }
        for (int i = 0; i <= howMany; i++) {
            for (int j = 0; j < bingoCard.length; j++) {
                if (bingoCard[j] == allNums.get(i)) {
                    sum -= allNums.get(i);
                }
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
                        for (int i = 0; i < bingoCard.length; i++) {
                            if (!(bingoCard[i].equals(""))) {
                                bingo[j] = Integer.parseInt(bingoCard[i]);
                                j++;
                            }
                        }
                        result = "";
                        d.allBingos.add(bingo);
                    }
                }
                /**
                 * Do some stuff here tomorrow
                 */
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
            result = "";
            d.allBingos.add(bingo);
            int least = 100;
            int[] winner = new int[25];
            for (int[] bingos : d.allBingos) {
                int i = d.checkBingo(bingos, d.allNumbers);
                if (i < least) {
                    winner = bingos;
                    least = i;
                }
            }
            int lastNum = d.allNumbers.get(least);
            int sum = d.calculateSum(winner, d.allNumbers, least);
            System.out.println(sum);
            System.out.println(lastNum);
            System.out.println(sum * lastNum);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}