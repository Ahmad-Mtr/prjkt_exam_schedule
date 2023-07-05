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
    
    private Colors ColsHolder = new Colors();
    public void getTokens(String TF_Code, String TF_Name) {
        String str1 = TF_Code, str2 = TF_Name;
        String delimeter = ",";
        ArrayList<String> _Codes = new ArrayList<>();
        ArrayList<String> _Names = new ArrayList<>();
        StringTokenizer Tokenizer1 = new StringTokenizer(str1, delimeter);
        StringTokenizer Tokenizer2 = new StringTokenizer(str2, delimeter);

        while (Tokenizer1.hasMoreTokens()) {
            String token1 = Tokenizer1.nextToken();
            _Codes.add(token1);
        }
        while (Tokenizer2.hasMoreTokens()) {
            String token2 = Tokenizer2.nextToken();
            _Names.add(token2);
        }
        Set<Integer> inndices = lookfor(_Codes, _Names);
        printmulti(inndices);
    }

    public Set<Integer> lookfor(ArrayList<String> cdz, ArrayList<String> nmz) {
        Set<Integer> Indices = new HashSet<>();

        for (String Target : cdz) {
            String curVal;
            for (int i = 0; i < _CourseCode.size(); i++) {
                curVal = _CourseCode.get(i);
                if (containsIgnoreCase(curVal, Target) && (Target.length() > 1)) {
                    Indices.add(i);
                }
            }
        }
        for (String Target : nmz) {
            String curVal;
            for (int i = 0; i < _CourseName.size(); i++) {
                curVal = _CourseName.get(i);
                if (containsIgnoreCase(curVal, Target) && (Target.length() > 1)) {
                    Indices.add(i);
                }
            }
        }
        return Indices;
    }

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
        String curVal;
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
        String curVal;
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

        fr.setSize(750, 300);
        JTextArea str = new JTextArea();
        str.setSize(750, 150);
        str.setFont(new Font("Helvetica", Font.PLAIN, 15));
        str.setForeground(ColsHolder.getForeground_());
        str.setBackground(ColsHolder.getBackground_());
        fr.getContentPane().add(str);

        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Course Code", "Course Name", "Time", "Date", "Day", "Room");
        rows.add(headers);
        rows.add(Arrays.asList(_CourseCode.get(index), _CourseName.get(index), _CourseTime.get(index), _CourseDate.get(index),
                _CourseDay.get(index), _CourseRooms.get(index)));

        str.setText(formatAsTable(rows));


        fr.setVisible(true);
    }

    public void printmulti(Set<Integer> indices) {
        Iterator<Integer> itr = indices.iterator();
        String[] columns = {"Course Code", "Course Name", "Time", "Date", "Day", "Room"};
        String[][] data = new String[indices.size()][6];

        int x = 0;
        for (int i = 0; i < indices.size(); i++) {      //row
            if (itr.hasNext())
                x = itr.next();
            for (int j = 0; j < 6; j++) {   // Info of row
                switch (j) {
                    case 0 -> data[i][j] = _CourseCode.get(x);
                    case 1 -> data[i][j] = _CourseName.get(x);
                    case 2 -> data[i][j] = _CourseTime.get(x);
                    case 3 -> data[i][j] = _CourseDate.get(x);
                    case 4 -> data[i][j] = _CourseDay.get(x);
                    case 5 -> data[i][j] = _CourseRooms.get(x);
                }
            }
        }
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        final JFrame frame = new JFrame("Info");

        JLabel lblHeading = new JLabel("List of Exams");

        lblHeading.setFont(new Font("Helvetica", Font.PLAIN, 18));
        
        frame.getContentPane().setBackground(ColsHolder.getBackground_());
        frame.getContentPane().setForeground(ColsHolder.getForeground_());
        lblHeading.setForeground(ColsHolder.getForeground_());
        table.setBackground(ColsHolder.getBackground_());
        table.setForeground(ColsHolder.getForeground_());

        frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(lblHeading, BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setSize(550, 200);
        frame.setVisible(true);
    }
    public void showCourses(){
        String[] columns = {"Course Code", "Course Name", "Time", "Date", "Day", "Room"};
        String[][] data = new String[_CourseCode.size()][6];

        for (int i = 0; i < _CourseCode.size(); i++) {      //row

            for (int j = 0; j < 6; j++) {   // Info of row
                switch (j) {
                    case 0 -> data[i][j] = _CourseCode.get(i);
                    case 1 -> data[i][j] = _CourseName.get(i);
                    case 2 -> data[i][j] = _CourseTime.get(i);
                    case 3 -> data[i][j] = _CourseDate.get(i);
                    case 4 -> data[i][j] = _CourseDay.get(i);
                    case 5 -> data[i][j] = _CourseRooms.get(i);
                }
            }
        }
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        final JFrame frame = new JFrame("Info");

        JLabel lblHeading = new JLabel("All Exams:");

        lblHeading.setFont(new Font("Helvetica", Font.PLAIN, 18));

        frame.getContentPane().setBackground(ColsHolder.getBackground_());
        frame.getContentPane().setForeground(ColsHolder.getForeground_());
        lblHeading.setForeground(ColsHolder.getForeground_());
        table.setBackground(ColsHolder.getBackground_());
        table.setForeground(ColsHolder.getForeground_());

        frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(lblHeading, BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setSize(600, 800);
        frame.setVisible(true);
    }
}
