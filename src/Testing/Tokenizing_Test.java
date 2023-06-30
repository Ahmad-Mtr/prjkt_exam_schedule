package Testing;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tokenizing_Test {



    public static void main(String[] args) {
        ArrayList<String> _CourseCode = new ArrayList<>();
        ArrayList<String> _CourseName = new ArrayList<>();
        ArrayList<String> _CourseTime = new ArrayList<>();
        ArrayList<String> _CourseDate = new ArrayList<>();
        ArrayList<String> _CourseDay = new ArrayList<>();
        ArrayList<String> _CourseRooms = new ArrayList<>();

        String str =
                "CS415\tSystems Programming\t08:30-10:30\t21-01-2023 , Sat\tM424+M426\n" +
                        "ACC598B\tSpecial Topics in Accounting\t08:30-10:30\t21-01-2023 , Sat\tB018\n";
        String delimeter = "\t\n,";
        StringTokenizer Tokenizer = new StringTokenizer(str, delimeter);
        for (int i = 0; Tokenizer.hasMoreTokens(); i += 6) {
            String token = Tokenizer.nextToken();
            //String tokenLast = Tokenizer.nextToken("\n");
            int val = i % 6;
            switch (val) {
                case 0:
                    _CourseCode.add(token);
                    i++;
                    break;
                case 1:
                    _CourseName.add(token);
                    i++;
                    break;
                case 2:
                    _CourseTime.add(token);
                    i++;
                    break;
                case 3:
                    _CourseDate.add(token);
                    i++;
                    break;
                case 4:
                    _CourseDay.add(token);
                    i++;
                    break;
                case 5:
                    _CourseRooms.add(token);
                    i++;
                    break;
            }
            //System.out.println(token);
        }
        System.out.println("\nCourse code List:");
        for (String x : _CourseCode) {
            System.out.println(x);
        }

        System.out.println("\nCourse Name List:");
        for (String x : _CourseName) {
            System.out.println(x);
        }

        System.out.println("\nCourse Time List:");
        for (String x : _CourseTime) {
            System.out.println(x);
        }

        System.out.println("\nCourse Date List:");
        for (String x : _CourseDate) {
            System.out.println(x);
        }
        System.out.println("\nCourse Dayss List:");
        for (String x : _CourseDay) {
            System.out.println(x);
        }

        System.out.println("\nCourse Rooms List:");
        for (String x : _CourseRooms) {
            System.out.println(x);
        }
        System.out.println("Hello world!");
    }
}