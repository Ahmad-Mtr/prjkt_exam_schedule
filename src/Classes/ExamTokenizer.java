package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ExamTokenizer {
    private FileReader FR;
    private BufferedReader Buff_FR;

    private ArrayList<String> _CourseCode = new ArrayList<String>();
    private ArrayList<String> _CourseName = new ArrayList<String>();
    private ArrayList<String> _CourseTime = new ArrayList<String>();
    private ArrayList<String> _CourseDate = new ArrayList<String>();
    private ArrayList<String> _CourseDay = new ArrayList<String>();
    private ArrayList<String> _CourseRooms = new ArrayList<String>();
    private final int value = 6;
    private String str = "";
    private final String delim = "\n\t,";

    public void read() {
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

        System.out.println("\nCourse Name List:");
        for (String x : _CourseName) {
            System.out.println(x + _CourseName.size());

        }
        System.out.println("Hello world!");


    }

    public static void main(String[] args) {

        ExamTokenizer ob = new ExamTokenizer();
        ob.read();

    }
}
