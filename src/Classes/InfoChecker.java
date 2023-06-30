package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class InfoChecker {
    private FileReader FR;
    private BufferedReader Buff_FR;
    private ArrayList<String> Info;
    private final int value = 2;
    private String str;
    private final String delim = "\n\t";

    public void read() {
        String x;
        ArrayList<String> _Usernames = new ArrayList<>();
        ArrayList<String> _Passwords = new ArrayList<>();

        try {
            FR = new FileReader("C:\\Users\\YOUSEF MAHMOUD\\IdeaProjects\\prjkt_exam_schedule\\src\\Data\\Usernames_Passwords.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Buff_FR = new BufferedReader(FR);
        while (true) {
            try {
                if (!((x = Buff_FR.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            str = x;
        }
        StringTokenizer Tokenizer = new StringTokenizer(str, delim);
        for (int i = 0; Tokenizer.hasMoreTokens(); i += value) {
            String token = Tokenizer.nextToken();
            int val = i % value;
            switch (val) {
                case 0 -> {
                    _Usernames.add(token);
                    i++;
                }
                case 1 -> {
                    _Passwords.add(token);
                    i++;
                }
            }
        }




    }

    public static void main(String[] args) {
        InfoChecker ob = new InfoChecker();
        ob.read();
    }
}
