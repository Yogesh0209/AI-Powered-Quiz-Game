package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton back;
    private Clip bgMusic;

    Rules() {
        
        setTitle("AI-powered Quiz Game - Rules Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        // Heading (left aligned)
        JLabel heading = new JLabel("Welcome to the Quiz");
        heading.setFont(new Font("Consolas", Font.BOLD, 28));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(40, 30, 700, 40);
        add(heading);

        // Rules text (left aligned, non-overlapping)
        JLabel rules = new JLabel();
        rules.setBounds(40, 100, 700, 320);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setForeground(new Color(220, 220, 255));
        rules.setText(
            "<html>"
                + "<b>General Instructions:</b><br><br>"
                + "1. You will face 10 questions. Each correct answer gives 10 points.<br><br>"
                + "2. Use the tracker buttons on top to navigate between questions.<br><br>"
                + "3. A timer runs for each question. If it ends, you'll be moved to the next.<br><br>"
                + "<b>Tracker Color Codes:</b><br><br>"
                +"<font color='gray'>■</font> Gray : Not visited yet<br>" 
                +"<font color='yellow'>■</font> Yellow : Current Question<br>"
                +"<font color='red'>■</font> Red : Visited but not answered<br>" 
                +"<font color='green'>■</font> Green : Answered<br><br>"  
                + "Stay calm, manage your time, and give your best attempt. Good luck!"
                + "</html>"
        );
        add(rules);

        // Back button (bottom left)
        back = new JButton("Back");
        back.setBounds(310, 480, 120, 35);
        back.setBackground(new Color(95, 0, 160));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.addActionListener(this);
        add(back);
        
        getRootPane().setDefaultButton(back);

        // Frame setup
        setSize(800, 600);
        setLocation(350, 120);
        setVisible(true);

        // Stop music if frame is closed by 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
            }
        });

        // Start background music
        startBackgroundMusic("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Quiz Application\\src\\icons\\rules_bg.wav");
    }

    // Start music
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

    // Stop music
    private void stopBackgroundMusic() {
        if (bgMusic != null && bgMusic.isRunning()) {
            bgMusic.stop();
            bgMusic.close();
        }
    }

    // Button actions
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            stopBackgroundMusic();
            setVisible(false);
            new Main_Menu();
        }
    }

    public static void main(String[] args) {
        new Rules();
    }
}
