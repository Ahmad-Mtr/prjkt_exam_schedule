package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage implements ActionListener {
    private ExamTokenizer objkt = new ExamTokenizer();
    private JFrame frame;
    private JPanel panel, x, x1;
    private JLabel L_CourseCode, L_CourseName;
    private JTextField TF_CourseCode, TF_CourseName;
    private JButton FindInfo, ShowAllCourses;
    private JCheckBox c1 = new JCheckBox("Search Multiple Courses (separate with\",\" only!!) Exp: CS116,CS201", false);

    public void makeframe() {
        frame = new JFrame("Exam Finder");
        panel = new JPanel();
        x = new JPanel();
        x1 = new JPanel();
        L_CourseCode = new JLabel("Course Code: ");
        L_CourseName = new JLabel("Course Name: ");

        TF_CourseCode = new JTextField();
        TF_CourseName = new JTextField();
        FindInfo = new JButton("Find Course info");
        ShowAllCourses = new JButton("Show All Courses");

        FindInfo.addActionListener(this);
        ShowAllCourses.addActionListener(this);

        panel.setLayout(new GridLayout(6, 2));

        x.setLayout(new GridLayout(4, 1));
        x1.setLayout(new GridLayout(1, 2));
        x.add(L_CourseCode);
        x.add(TF_CourseCode);
        x.add(L_CourseName);
        x.add(TF_CourseName);

        x1.add(FindInfo);
        x1.add(ShowAllCourses);

        panel.add(x);
        panel.add(c1);
        panel.add(x1);

        frame.getContentPane().add(panel);
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        objkt.read();
        if (e.getSource() == FindInfo) {
            String TF_Code = TF_CourseCode.getText(), TF_Name = TF_CourseName.getText();
            boolean isChecked = c1.isSelected();

            if (isChecked) {    //Multiple Courses
                // Analyze
                objkt.getTokens(TF_Code, TF_Name);
                /*look code || Name  ^  */

            } else {            // Single Course
                /*look code || Name*/
                objkt.find(TF_Code, TF_Name);
            }
        } else if (e.getSource() == ShowAllCourses) {
            objkt.showCourses();
        }
    }
}
