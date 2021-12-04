package LastYear;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {
    public ArrayList<String> passports = new ArrayList<>();

    /**
     * @param passport
     * @return
     */
    public boolean isValidPass(String passport) {
        String[] all = passport.split(" ");
        if (!(passport.contains("byr") && passport.contains("iyr") &&
                passport.contains("eyr") && passport.contains("hgt") &&
                passport.contains("hcl") && passport.contains("ecl") &&
                passport.contains("pid"))) {
            return false;
        } else {
            for (String each : all) {
                if (each.contains("byr")) {
                    String[] birth = each.split(":");
                    int birthyear = Integer.parseInt(birth[1]);
                    if (birthyear < 1920 || birthyear > 2002) {
                        return false;
                    }
                } else if (each.contains("iyr")) {
                    String[] birth = each.split(":");
                    int birthyear = Integer.parseInt(birth[1]);
                    if (birthyear < 2010 || birthyear > 2020) {
                        return false;
                    }
                } else if (each.contains("eyr")) {
                    String[] birth = each.split(":");
                    int birthyear = Integer.parseInt(birth[1]);
                    if (birthyear < 2020 || birthyear > 2030) {
                        return false;
                    }
                } else if (each.contains("hgt")) {
                    String[] heigth = each.split(":");
                    int len;
                    if (each.contains("cm")) {
                        String[] heigth2 = heigth[1].split("cm");
                        len = Integer.parseInt(heigth2[0]);
                        if (len < 150 || len > 193) {
                            return false;
                        }
                    } else {
                        String[] heigth2 = heigth[1].split("in");
                        len = Integer.parseInt(heigth2[0]);
                        if (len < 59 || len > 76) {
                            return false;
                        }
                    }
                } else if (each.contains("hcl")) {
                    String[] hcolor = each.split(":");
                    if (!(hcolor[1].charAt(0) == '#' && hcolor[1].length() == 7)) {
                        return false;
                    }
                    String[] finaltest = hcolor[1].split("#");
                    if (!(validLetter(finaltest[0]))) {
                        return false;
                    }
                } else if (each.contains("ecl")) {
                    String[] ecolor = each.split(":"); //amb blu brn gry grn hzl oth
                    if (!(ecolor[1].equals("amb") || ecolor[1].equals("blu") || ecolor[1].equals("brn") ||
                            ecolor[1].equals("gry") || ecolor[1].equals("grn") || ecolor[1].equals("hzl") ||
                            ecolor[1].equals("oth"))) {
                        return false;
                    }
                } else if (each.contains("pid")) {
                    String[] pid = each.split(":");
                    for (char a : pid[1].toCharArray()) {
                        if (!(Character.isDigit(a))) {
                            return false;
                        }
                    }
                    if (pid[1].length() != 9) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean validLetter(String a) {
        int len = a.length();
        boolean result = true;
        for (char b : a.toCharArray()) {
            if (!(Character.isDigit(b) || b == 'a' || b == 'b' || b == 'c' || b == 'd' ||
            b == 'e' || b == 'f')) {
                result = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Day4 d = new Day4();
        BufferedReader reader;
        File input = new File("src/Day4/input");
        int nullcount = 0;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            String password = "";
            while (line != null) {
                /**
                 * Do some stuff here tomorrow
                 */
                if (line.equals("")) {
                    nullcount++;
                    if (nullcount == 1) {
                        d.passports.add(password);
                        password = "";
                    }
                } else {
                    nullcount = 0;
                    password += " " + line;
                }
                line = reader.readLine();
            }
            d.passports.add(password);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        for (String pass : d.passports) {
            if (d.isValidPass(pass)) {
                i++;
            }
        }
        System.out.println(i);
    }
}
