package Day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// geef het als deafgbc
// bovenste, linker, rechter, middelste, linker, rechter, onderste
public class Day8 {
    ArrayList<Integer> allOutputs = new ArrayList<>();

    public String[] determinePattern(String[] inputs) {
        String[] result = new String[10];
        String zero = null;
        String one = null;
        String two = null;
        String three = null;
        String four = null;
        String five = null;
        String six = null;
        String seven = null;
        String eight = null;
        String nine = null;
        for (String in : inputs) {
            if (in.length() == 2) {
                one = in;
            } else if (in.length() == 3) {
                seven = in;
            } else if (in.length() == 4) {
                four = in;
            } else if (in.length() == 7) {
                eight = in;
            }
        }
        for (String in : inputs) {
            if (in.length() == 6) {
                if (!(in.contains(one.substring(0, 1)) && in.contains(one.substring(1, 2)))) {
                    six = in;
                } else if (in.contains(four.substring(0, 1)) && in.contains(four.substring(1, 2))
                && in.contains(four.substring(2, 3)) && in.contains(four.substring(3, 4))) {
                    nine = in;
                } else {
                    zero = in;
                }
            } else if (in.length() == 5) {
                if ((in.contains(one.substring(0, 1)) && in.contains(one.substring(1, 2)))) {
                    three = in;
                } else if (two == null) {
                    two = in;
                } else {
                    five = in;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (five.contains(six.substring(i, i + 1))) {
                count++;
            }
        }
        if (!(count >= 5)) {
            String temp = two;
            two = five;
            five = temp;
        }

        result[0] = zero;
        result[1] = one;
        result[2] = two;
        result[3] = three;
        result[4] = four;
        result[5] = five;
        result[6] = six;
        result[7] = seven;
        result[8] = eight;
        result[9] = nine;
        return result;
    }

    public int whichNum(String[] patterns, String input) {
        for (int i = 0; i < 10; i++) {
            String p = patterns[i];
            if (p.length() == input.length()) {
                boolean result = true;
                for (int j = 0; j < p.length(); j++) {
                    if (!(input.contains(p.substring(j, j + 1)))) {
                        result = false;
                    }
                }
                if (result) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Day8 d = new Day8();
        BufferedReader reader;
        File input = new File("src/Day8/input");
        int counter = 0;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                String[] inputs = line.split(" . ");
                String[] displayed = inputs[1].split(" ");
                String[] neededPatterns = d.determinePattern(inputs[0].split(" "));
                String output = "";
                for (String dis : displayed) {
                    output += d.whichNum(neededPatterns, dis);
                }
                d.allOutputs.add(Integer.parseInt(output));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long sum = 0;
        for (int i : d.allOutputs) {
            sum += i;
        }
        System.out.println(sum);
    }
}
