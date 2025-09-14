package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Main_Menu extends JFrame implements ActionListener {

    JButton login, startQuiz, modes, leaderboard, profile, mode, help, about, exit;
    private Clip bgMusic;

    public Main_Menu() {
        initComponents();
    }

    private void initComponents() {
        setTitle("AI-powered Quiz Game - Main_Menu Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/mainmenu.jpg"));
        Image bgImage = bgIcon.getImage();
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        JLabel heading = new JLabel("Simple Minds");
        heading.setFont(new Font("Consolas", Font.BOLD, 44));
        heading.setForeground(new Color(0x22, 0x2E, 0x50));
        heading.setBounds(410, 50, 400, 60);
        bgPanel.add(heading);

        startQuiz = new JButton("Start Quiz");
        startQuiz.setBounds(50, 360, 200, 45);
        startQuiz.setBackground(new Color(0x41, 0x69, 0xe1));
        startQuiz.setForeground(Color.WHITE);
        startQuiz.setFont(new Font("Segoe UI", Font.BOLD, 16));
        startQuiz.setFocusPainted(false);
        startQuiz.addActionListener(this);
        bgPanel.add(startQuiz);

        login = new JButton("Login");
        login.setBounds(50, 440, 200, 45);
        login.setBackground(new Color(0x12, 0xca, 0xd6));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Segoe UI", Font.BOLD, 16));
        login.setFocusPainted(false);
        login.addActionListener(this);
        bgPanel.add(login);

        mode = new JButton("Mode");
        mode.setBounds(50, 520, 200, 45);
        mode.setBackground(new Color(0x8f, 0x5c, 0xf7));
        mode.setForeground(Color.WHITE);
        mode.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mode.setFocusPainted(false);
        mode.addActionListener(this);
        bgPanel.add(mode);

        profile = new JButton("Profile");
        profile.setBounds(50, 600, 200, 45);
        profile.setBackground(new Color(0x7c, 0x3A, 0xED));
        profile.setForeground(Color.WHITE);
        profile.setFont(new Font("Segoe UI", Font.BOLD, 16));
        profile.setFocusPainted(false);
        profile.addActionListener(this);
        bgPanel.add(profile);

        help = new JButton("Help");
        help.setBounds(900, 50, 130, 35);
        help.setBackground(new Color(0x21, 0x96, 0xf3));
        help.setForeground(Color.WHITE);
        help.setFont(new Font("Segoe UI", Font.BOLD, 14));
        help.setFocusPainted(false);
        help.addActionListener(this);
        bgPanel.add(help);

        about = new JButton("About");
        about.setBounds(900, 100, 130, 35);
        about.setBackground(new Color(0x21, 0x96, 0xf3));
        about.setForeground(Color.WHITE);
        about.setFont(new Font("Segoe UI", Font.BOLD, 14));
        about.setFocusPainted(false);
        about.addActionListener(this);
        bgPanel.add(about);

        leaderboard = new JButton("Leaderboard");
        leaderboard.setBounds(900, 150, 130, 35);
        leaderboard.setBackground(new Color(0x21, 0x96, 0xf3));
        leaderboard.setForeground(Color.WHITE);
        leaderboard.setFont(new Font("Segoe UI", Font.BOLD, 14));
        leaderboard.setFocusPainted(false);
        leaderboard.addActionListener(this);
        bgPanel.add(leaderboard);

        exit = new JButton("Exit");
        exit.setBounds(900, 600, 130, 35);
        exit.setBackground(new Color(0xf2, 0x5a, 0x67));
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        exit.setFocusPainted(false);
        exit.addActionListener(this);
        bgPanel.add(exit);

        setSize(1080, 720);
        setLocation(250, 80);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
            }
        });

        startBackgroundMusic("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Quiz Application\\src\\icons\\quiz_bg.wav");
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
        if (ae.getSource() == startQuiz) {
            String username = Session.getUsername();
            if (username == null) {
                username = "";
            }
            int timerSeconds = 0; // must reflect user’s saved preference

            stopBackgroundMusic();
            setVisible(false);
            new Quiz(username, timerSeconds); // starts quiz with proper timer
        } else if (ae.getSource() == login) {
            stopBackgroundMusic();
            setVisible(false);
            new Login();
        } else if (ae.getSource() == profile) {
            String username = Session.getUsername();
            if (username == null || username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please login first to access this feature.");
                return;
            }
            stopBackgroundMusic();
            setVisible(false);
            new Profile();
        } else if (ae.getSource() == leaderboard) {
            stopBackgroundMusic();
            setVisible(false);
            new Leaderboard();
        } else if (ae.getSource() == mode) {
            stopBackgroundMusic();
            setVisible(false);
            new Mode();
        } else if (ae.getSource() == help) {
            stopBackgroundMusic();
            setVisible(false);
            new Rules();
        } else if (ae.getSource() == about) {
            stopBackgroundMusic();
            setVisible(false);
            new About();
        } else if (ae.getSource() == exit) {
            stopBackgroundMusic();
            setVisible(false);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main_Menu();
    }
}
