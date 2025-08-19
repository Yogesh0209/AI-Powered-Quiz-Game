package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton back, start;

    Rules(String name) {

        this.name = name;

        setTitle("AI-powered Quiz Game - Rules Page");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        JLabel heading = new JLabel("Welcome " + name + " to Simple Minds");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(30, 25, 700, 30);
        add(heading);

        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 760, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setForeground(new Color(220, 220, 255));
        rules.setText(
            "<html>" +
                "1. This is not a group project – your brain is your only teammate." + "<br><br>" +
                "2. Googling won't help here – you're offline (and under watch...).<br><br>" +
                "3. Skipping questions isn't brave – it's just not allowed.<br><br>" +
                "4. Ctrl + Z won't undo a wrong answer – think before you click.<br><br>" +
                "5. If you're confused, welcome to the club – but no lifelines here!<br><br>" +
                "6. Confidence is good. Overconfidence? Not so much.<br><br>" +
                "7. This quiz runs on Java, but your brain needs to be the JVM.<br><br>" +
                "8. Take a deep breath. Remember: It’s just you vs. the code gods now.<br><br>" +
                "Good luck, and may your logic be strong!" +
            "</html>"
        );
        add(rules);

        back = new JButton("Back");
        back.setBounds(200, 500, 120, 35);
        back.setBackground(new Color(95, 0, 160));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        start = new JButton("Start");
        start.setBounds(450, 500, 120, 35);
        start.setBackground(new Color(95, 0, 160));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);

        getRootPane().setDefaultButton(start);

        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
