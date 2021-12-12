package Day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day12 {
    ArrayList<String[]> allConnections = new ArrayList<>();
    ArrayList<String> allCaves = new ArrayList<>();
    HashMap<String, ArrayList<String>> analyzedConnections = new HashMap<>();
    ArrayList<String[]> allPaths = new ArrayList<>();


    public boolean smallVisitTwice(ArrayList<String> path) {
        for (String a : path) {
            if (Character.isLowerCase(a.charAt(0))) {
                int i = 0;
                for (String b : path) {
                    if (a.equals(b)) {
                        i++;
                    }
                }
                if (i >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public void analyzeConnections() {
        for (String[] a : allConnections) {
            analyzedConnections.putIfAbsent(a[0], new ArrayList<>());
            analyzedConnections.get(a[0]).add(a[1]);
            analyzedConnections.putIfAbsent(a[1], new ArrayList<>());
            analyzedConnections.get(a[1]).add(a[0]);
        }
    }

    public void findPathPart1(String current, ArrayList<String> path) {
        ArrayList<String> temp = new ArrayList<>(path);
        for (String b : analyzedConnections.get(current)) {
            if (!(b.equals("start"))) {
                if (b.equals("end")) {
                    temp.add(b);
                    String[] temp1 = new String[temp.size()];
                    for (int i = 0; i < temp.size(); i++) {
                        temp1[i] = temp.get(i);
                    }
                    allPaths.add(temp1);
                } else if (Character.isUpperCase(b.charAt(0))) {
                    temp.add(b);
                    findPathPart1(b, temp);
                } else if (!(temp.contains(b))) {
                    temp.add(b);
                    findPathPart1(b, temp);
                }
                temp.clear();
                temp.addAll(path);
            }
        }
    }

    public void findPathPart2(String current, ArrayList<String> path) {
        ArrayList<String> temp = new ArrayList<>(path);
        for (String b : analyzedConnections.get(current)) {
            if (!(b.equals("start"))) {
                if (b.equals("end")) {
                    temp.add(b);
                    String[] temp1 = new String[temp.size()];
                    for (int i = 0; i < temp.size(); i++) {
                        temp1[i] = temp.get(i);
                    }
                    allPaths.add(temp1);
                } else if (Character.isUpperCase(b.charAt(0))) {
                    temp.add(b);
                    findPathPart2(b, temp);
                } else if (!(smallVisitTwice(temp)) || !(temp.contains(b))) {
                    temp.add(b);
                    findPathPart2(b, temp);
                }
                temp.clear();
                temp.addAll(path);
            }
        }
    }

    public static void main(String[] args) {
        Day12 d = new Day12();
        BufferedReader reader;
        File input = new File("src/Day12/input");
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                String[] connection = line.split("-");
                d.allConnections.add(connection);
                if (!(d.allCaves.contains(connection[0]))) {
                    d.allCaves.add(connection[0]);
                }
                if (!(d.allCaves.contains(connection[1]))) {
                    d.allCaves.add(connection[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.analyzeConnections();
        ArrayList<String> path = new ArrayList<>();
        path.add("start");
        d.findPathPart1("start", path);
        System.out.println("Part 1: " + d.allPaths.size());
        d.allPaths.clear();
        d.findPathPart2("start", path);
        System.out.println("Part 2: " + d.allPaths.size());
    }
}
