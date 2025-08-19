package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener {

    JButton submit, review;
    String name;
    int score;
    String[][] questions;
    String[] useranswers;
    String[] answers;

    Score(String name, int score, String[][] questions, String[] useranswers, String[] answers) {
        this.name = name;
        this.score = score;
        this.questions = questions;
        this.useranswers = useranswers;
        this.answers = answers;

        setBounds(400, 150, 750, 550);
        setTitle("AI-powered Quiz Game - Score Page");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/new.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 150, 300, 250);
        add(image);

        JLabel heading = new JLabel("Thank you " + name + " for playing Simple Minds.");
        heading.setBounds(125, 50, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setForeground(new Color(220, 220, 255));
        add(heading);

        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(400, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblscore.setForeground(new Color(220, 220, 255));
        add(lblscore);

        review = new JButton("Review");
        review.setBounds(410, 270, 140, 40);
        review.setBackground(new Color(95, 0, 160));
        review.setForeground(Color.WHITE);
        review.setFont(new Font("Tahoma", Font.BOLD, 16));
        review.addActionListener(this);
        add(review);

        submit = new JButton("Play Again");
        submit.setBounds(410, 350, 140, 40);
        submit.setBackground(new Color(95, 0, 160));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Tahoma", Font.BOLD, 16));
        submit.addActionListener(this);
        add(submit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == review) {
            setVisible(false);
            new Review(questions, useranswers, answers);
        } else if (ae.getSource() == submit) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        // dummy test
        new Score("User", 0, new String[10][6], new String[10], new String[10]);
    }
}
