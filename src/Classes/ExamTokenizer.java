package Classes;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class ExamTokenizer {
    private FileReader FR;
    private BufferedReader Buff_FR;
    private ArrayList<String> _CourseCode = new ArrayList<>();
    private ArrayList<String> _CourseName = new ArrayList<>();
    private ArrayList<String> _CourseTime = new ArrayList<>();
    private ArrayList<String> _CourseDate = new ArrayList<>();
    private ArrayList<String> _CourseDay = new ArrayList<>();
    private ArrayList<String> _CourseRooms = new ArrayList<>();
    private final int value = 6;
    private String str = "";
    private final String delim = "\n\t,";

    public boolean find(String Code, String Name) {
        boolean isFound = false;
        int index = -1;

        // Code
        index = searchCode(Code);
        //Name
        isFound = searchName(Name, index);
        if (isFound)
            printSingle(index);
        return isFound;
    }

    public int searchCode(String Target) {
        int isThere = -1;
        String curVal = "";
        for (int i = 0; i < _CourseCode.size(); i++) {
            curVal = _CourseCode.get(i);
            if (containsIgnoreCase(curVal, Target) && (Target.length() > 1)) {
                isThere = i;

            }
        }

        return isThere;
    }

    public boolean searchName(String Target, int i_CCode) {
        boolean isThere = false;
        String curVal = "";
        for (int i = 0; i < _CourseName.size(); i++) {
            curVal = _CourseName.get(i);
            if (containsIgnoreCase(curVal, Target) && (Target.length() > 1)) {
                if (i_CCode == i) {
                    isThere = true;
                    break;
                }
            }
        }
        return isThere;
    }

    public boolean containsIgnoreCase(String src, String what) {    // Special Thanks to Stackoverflow
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }

        return false;
    }

    public void read() {
        if (_CourseCode.size() > 3) return;
        str = "";
        String y;
        try {
            FR = new FileReader("src/Data/Exams2022_2023.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Buff_FR = new BufferedReader(FR);
        while (true) {
            try {
                y = Buff_FR.readLine();
                if (y == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            str += y + "\n";

        }

        StringTokenizer Tokenizer = new StringTokenizer(str, delim);
        for (int i = 0; Tokenizer.hasMoreTokens(); i += value) {
            String token = Tokenizer.nextToken();
            int val = i % value;
            switch (val) {
                case 0 -> {
                    _CourseCode.add(token);
                    i++;
                }
                case 1 -> {
                    _CourseName.add(token);
                    i++;
                }
                case 2 -> {
                    _CourseTime.add(token);
                    i++;
                }
                case 3 -> {
                    _CourseDate.add(token);
                    i++;

                }
                case 4 -> {
                    _CourseDay.add(token);
                    i++;
                }
                case 5 -> {
                    _CourseRooms.add(token);
                    i++;
                }
            }
        }


    }

    public String formatAsTable(List<List<String>> rows) {
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths) {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows) {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }

    public void printSingle(int index) {
        JFrame fr = new JFrame("Details");

        fr.setSize(600, 300);
        JTextArea str = new JTextArea();
        str.setSize(750,150);
        str.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
        fr.getContentPane().add(str);

        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Course Code", "Course Name", "Time", "Date", "Day", "Room");
        rows.add(headers);
        rows.add(Arrays.asList(_CourseCode.get(index), _CourseName.get(index), _CourseTime.get(index), _CourseDate.get(index),
                _CourseDay.get(index), _CourseRooms.get(index)));

        str.setText(formatAsTable(rows));


        fr.setVisible(true);
    }

    public void printmulti(ArrayList<Integer> indices) {
        //indices = new ArrayList<>();

        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Course Code", "Course Name", "Time", "Date", "Day", "Room");
        rows.add(headers);

        Iterator<Integer> itr = indices.iterator();
        for (int i : indices) {

            rows.add(Arrays.asList(_CourseCode.get(i), _CourseName.get(i), _CourseTime.get(i), _CourseDate.get(i),
                    _CourseDay.get(i), _CourseRooms.get(i)));

        }

        System.out.println(formatAsTable(rows));
    }
}
