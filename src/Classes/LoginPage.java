package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener {
    private JFrame frame;
    private JPanel panel, x;
    private JLabel L_Username, L_Password;
    private JTextField TF_Username, TF_Password;
    private JButton FindInfo;

    public void makeframe(){
        frame = new JFrame("Login page");
        panel = new JPanel();
        x = new JPanel();
        L_CourseCode= new JLabel("    Username: ");
        L_CourseName = new JLabel("   Password: ");

        TF_CourseCode = new JTextField();
        TF_CourseName= new JTextField();
        FindInfo = new JButton("Find Course info");

        FindInfo.addActionListener(this);

        panel.setLayout(new GridLayout(3,2));
        panel.add(L_Username);
        panel.add(TF_Username);
        panel.add(L_Password);
        panel.add(TF_Password);
        x.setLayout(new GridLayout(1,1));
        x.add(FindInfo);
        panel.add(x);
        frame.getContentPane().add(panel);
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FindInfo){
            /*if (checkpass()){
                2ndpage() new;
                ^ make();
            }*/
        }
    }
}
