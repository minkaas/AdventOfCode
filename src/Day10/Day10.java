package Day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day10 {
    char[][] allCharacters = new char[102][150];

    public long completionScore(char[] line) {
        long result = 0;
        ArrayList<Character> open = new ArrayList<>();
        for (char a : line) {
            switch (a) {
                case '(', '[', '{', '<' -> open.add(a);
                case ')', ']', '}', '>' -> open.remove(open.size() - 1);
            }
        }
        for (int i = open.size() - 1; i >= 0; i--) {
            char b = open.get(i);
            result = result * 5;
            switch (b) {
                case '(' -> result += 1;
                case '[' -> result += 2;
                case '{' -> result += 3;
                case '<' -> result += 4;
            }
        }
        return result;
    }

    public boolean checkLine(char[] line) {
        ArrayList<Character> open = new ArrayList<>();
        char b;
        for (char a : line) {
            switch (a) {
                case '(', '[', '{', '<' -> open.add(a);
                case ')' -> {
                    b = open.remove(open.size() - 1);
                    if (b != '(') {
                        return false;
                    }
                }
                case ']' -> {
                    b = open.remove(open.size() - 1);
                    if (b != '[') {
                        return false;
                    }
                }
                case '}' -> {
                    b = open.remove(open.size() - 1);
                    if (b != '{') {
                        return false;
                    }
                }
                case '>' -> {
                    b = open.remove(open.size() - 1);
                    if (b != '<') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Day10 d = new Day10();
        BufferedReader reader;
        File input = new File("src/Day10/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                d.allCharacters[i] = line.toCharArray();
                line = reader.readLine();
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Long> scores = new ArrayList<>();
        for (char[] lines : d.allCharacters) {
            if (d.checkLine(lines)) {
                scores.add(d.completionScore(lines));
            }
        }
        Collections.sort(scores);
        long middleScore= scores.get(scores.size() / 2);
        System.out.println(middleScore);
    }
}
