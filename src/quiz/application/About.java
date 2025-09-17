package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class About extends JFrame implements ActionListener {
    JButton back;
    private Clip bgMusic;

    About() {
        setTitle("AI-powered Quiz Game - About Us");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("About This Project");
        heading.setFont(new Font("Consolas", Font.BOLD, 28));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(40, 30, 700, 40);
        add(heading);

        // About text
        JLabel aboutText = new JLabel();
        aboutText.setBounds(40, 80, 700, 320);
        aboutText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        aboutText.setForeground(new Color(220, 220, 255));
        aboutText.setText(
            "<html>" +
            "<p>This AI-powered Quiz Game is a comprehensive interactive " +
            "application designed to provide an engaging and educational quiz " +
            "experience.</p>" +
            "<p>Developed by a dedicated team of three passionate members, this " +
            "project showcases our abilities in software development, UI/UX design, " +
            "and applied AI concepts. Our goal was to transform an innovative idea " +
            "into a reality, while providing a smooth and user-friendly interface.</p>" +
            "<p>We are deeply thankful to our users for their enthusiasm in playing " +
            "and supporting the game. We also extend our gratitude to our college " +
            "for the learning opportunity and resources.</p>" +
            "<p>Special thanks to advanced AI chatbots and technologies that inspired " +
            "and assisted us throughout development, bridging our idea to an actual implementation.</p>" +
            "<br>" +
            "<b>Team Members:</b><br>" +
            "1. Yogesh Singh<br>" +
            "2. Raj Mehra<br>" +
            "3. VIpul Kumar MIshra<br><br>" +
            "<i>Thank you for being part of our journey!</i>" +
            "</html>"
        );
        add(aboutText);

        // Back button
        back = new JButton("Back");
        back.setBounds(310, 480, 120, 35);
        back.setBackground(new Color(95, 0, 160));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.addActionListener(this);
        add(back);

        getRootPane().setDefaultButton(back);

        setSize(800, 600);
        setLocation(350, 120);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
            }
        });

        startBackgroundMusic("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Quiz Application\\src\\icons\\rules_bg.wav");
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
        }
    }

    public static void main(String[] args) {
        new About();
    }
}
