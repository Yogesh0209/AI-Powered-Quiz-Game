package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Mode extends JFrame implements ActionListener {

    JButton casual, challenger, rapidFire, back;
    private Clip bgMusic;

    Mode() {
        setTitle("AI-powered Quiz Game - Select Mode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        JLabel heading = new JLabel("Select Quiz Mode");
        heading.setFont(new Font("Consolas", Font.BOLD, 32));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(310, 40, 400, 50);
        add(heading);

        // Casual button
        casual = new JButton("<html><center>1. Casual<br><small>No timer, take your time to answer</small></center></html>");
        casual.setBounds(250, 130, 400, 80);
        styleButton(casual);
        casual.addActionListener(this);
        add(casual);

        // Challenger button
        challenger = new JButton("<html><center>2. Challenger<br><small>8 minutes total to answer all questions</small></center></html>");
        challenger.setBounds(250, 230, 400, 80);
        styleButton(challenger);
        challenger.addActionListener(this);
        add(challenger);

        // Rapid Fire button
        rapidFire = new JButton("<html><center>3. Rapid Fire<br><small>15 seconds given per question</small></center></html>");
        rapidFire.setBounds(250, 330, 400, 80);
        styleButton(rapidFire);
        rapidFire.addActionListener(this);
        add(rapidFire);

        // Back button
        back = new JButton("Back");
        back.setBounds(380, 450, 130, 40);
        back.setBackground(new Color(95, 0, 160));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.setFocusPainted(false);
        back.addActionListener(this);
        add(back);

        setSize(900, 600);
        setLocation(300, 120);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
            }
        });

        startBackgroundMusic("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Quiz Application\\src\\icons\\rules_bg.wav");
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(60, 0, 100));
        btn.setForeground(new Color(220, 220, 255));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
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
        if (ae.getSource() == back) {
            stopBackgroundMusic();
            setVisible(false);
            new Main_Menu();
            return;
        }

        // Detect mode selected and save preference
        if (ae.getSource() == casual) {
            Session.setMode("Casual", 0);
        } else if (ae.getSource() == challenger) {
            Session.setMode("Challenger", 8 * 60);
        } else if (ae.getSource() == rapidFire) {
            Session.setMode("RapidFire", 15);
        } else {
            // Unhandled source
            return;
        }

        stopBackgroundMusic();
        setVisible(false);
        new Main_Menu(); // return to main menu after mode selection
        }
    
    public static void main(String[] args) {
        new Mode();
    }
}
