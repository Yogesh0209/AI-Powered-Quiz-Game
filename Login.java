package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JButton rules, back;
    JTextField tfname;
    JPasswordField tfpassword;

    Login() {

        setTitle("AI-powered Quiz Game - Login");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);

        JLabel heading = new JLabel("Simple Minds");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 36));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(775, 60, 400, 45);
        add(heading);

        JLabel name = new JLabel("Enter your name :");
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        name.setForeground(new Color(220, 200, 255)); 
        name.setBounds(735, 150, 300, 20);
        add(name);

        tfname = new JTextField();
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tfname.setBounds(735, 180, 300, 30);
        tfname.setBackground(new Color(60, 0, 100)); 
        tfname.setForeground(new Color(220, 220, 255)); 
        add(tfname);
        
        JLabel password = new JLabel("Enter your password :");
        password.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        password.setForeground(new Color(220, 200, 255)); 
        password.setBounds(735, 250, 300, 20);
        add(password);
        
        tfpassword = new JPasswordField();
        tfpassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tfpassword.setBounds(735, 280, 300, 30);
        tfpassword.setBackground(new Color(60, 0, 100)); 
        tfpassword.setForeground(new Color(220, 220, 255)); 
        add(tfpassword);

        rules = new JButton("Rules");
        rules.setBounds(735, 380, 120, 25);
        rules.setBackground(new Color(95, 0, 160)); 
        rules.setForeground(Color.WHITE);
        rules.addActionListener(this);
        add(rules);

        back = new JButton("Back");
        back.setBounds(915, 380, 120, 25);
        back.setBackground(new Color(95, 0, 160)); 
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        getRootPane().setDefaultButton(rules);
        setSize(1200, 500);
        setLocation(200, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
            String name = tfname.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.");
                return;
            }
            setVisible(false);
            new Rules(name);
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
