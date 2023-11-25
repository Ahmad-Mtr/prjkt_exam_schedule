package Classes;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        MainPage LP = new MainPage();

        LP.makeframe();
        JOptionPane.showMessageDialog(LP.getFrame(), "Welcome to the Exam Finder!!\n\n" +
                "For single Course, You can search by typing the Course Code AlD by typing its Name only (No need for the full name).\n" +
                "(For multiple Courses, enable the check box)\nEnjoy!");
    }
}
