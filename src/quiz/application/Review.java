package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Review extends JFrame implements ActionListener {

    JButton playAgain;
    private Clip bgMusic; // background music

    public Review(String[][] questions, String[] useranswers, String[] answers) {
        setTitle("AI-powered Quiz Game - Review Answers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            if (userAns != null && userAns.trim().equalsIgnoreCase(correctAns.trim())) {
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

        playAgain = new JButton("Main Menu");
        playAgain.addActionListener(this);
        playAgain.setBackground(new Color(95, 0, 160));
        playAgain.setForeground(Color.WHITE);
        playAgain.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(playAgain, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(playAgain);
                
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);

        // Stop music if frame is closed by 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
            }
        });
        // Start background music
        startBackgroundMusic("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Quiz Application\\src\\icons\\review_bg.wav"); // put your WAV file here

        // Stop music if user closes window manually
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
                dispose();
            }
        });
    }

    private void startBackgroundMusic(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            bgMusic = AudioSystem.getClip();
            bgMusic.open(audio);
            bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
            bgMusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (bgMusic != null && bgMusic.isRunning()) {
            bgMusic.stop();
            bgMusic.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        stopBackgroundMusic(); // stop music on button click
        if (ae.getSource() == playAgain) {
            setVisible(false);
            new Main_Menu();
        }
    }

    public static void main(String[] args) {
        // dummy test
        new Review(new String[10][6], new String[10], new String[10]);
    }
}
