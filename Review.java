package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Review extends JFrame implements ActionListener {

    JButton playAgain;

    public Review(String[][] questions, String[] useranswers, String[] answers) {
        setTitle("Review Answers");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(35, 0, 50));

        for (int i = 0; i < questions.length; i++) {
            String qText = (i + 1) + ". " + questions[i][0];
            String userAns = (useranswers[i] == null || useranswers[i].equals("")) ? "Not Answered" : useranswers[i];
            String correctAns = answers[i];

            JLabel qLabel = new JLabel(qText);
            qLabel.setForeground(Color.WHITE);
            qLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

            JLabel ansLabel = new JLabel("Your Answer: " + userAns);
            if (userAns.equals(correctAns)) {
                ansLabel.setForeground(Color.GREEN);
            } else {
                ansLabel.setForeground(Color.RED);
            }
            ansLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

            JLabel correctLabel = new JLabel("Correct Answer: " + correctAns);
            correctLabel.setForeground(Color.YELLOW);
            correctLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

            panel.add(qLabel);
            panel.add(ansLabel);
            panel.add(correctLabel);
            panel.add(Box.createRigidArea(new Dimension(10, 15)));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        playAgain = new JButton("Play Again");
        playAgain.addActionListener(this);
        playAgain.setBackground(new Color(95, 0, 160));
        playAgain.setForeground(Color.WHITE);
        playAgain.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(playAgain, BorderLayout.SOUTH);

        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == playAgain) {
            setVisible(false);
            new Login();
        }
    }
}