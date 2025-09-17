package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.*;

public class Profile extends JFrame {

    JTextField tfname;
    JTextField tfpassword;
    JButton btnBack;
    DefaultListModel<String> matchListModel;
    JList<String> matchList;
    private Clip bgMusic;

    Profile() {
        setTitle("AI-powered Quiz Game - Profile Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Your Profile");
        heading.setFont(new Font("Consolas", Font.BOLD, 46));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(50, 50, 400, 60);
        add(heading);

        // Username label and text field
        JLabel lblName = new JLabel("Username:");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblName.setForeground(new Color(220, 200, 255));
        lblName.setBounds(50, 152, 180, 25);
        add(lblName);

        tfname = new JTextField();
        tfname.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tfname.setBounds(160, 150, 300, 35);
        tfname.setBackground(new Color(50, 0, 90));
        tfname.setForeground(new Color(230, 230, 255));
        tfname.setCaretColor(Color.WHITE);
        tfname.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tfname.setEditable(false);
        add(tfname);

        // Password label and password field
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblPassword.setForeground(new Color(220, 200, 255));
        lblPassword.setBounds(50, 202, 180, 25);
        add(lblPassword);

        tfpassword = new JTextField();
        tfpassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tfpassword.setBounds(160, 200, 300, 35);
        tfpassword.setBackground(new Color(50, 0, 90));
        tfpassword.setForeground(new Color(230, 230, 255));
        tfpassword.setCaretColor(Color.WHITE);
        tfpassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tfpassword.setEditable(false);
        add(tfpassword);

        // Previous matches label and list
        JLabel lblMatches = new JLabel("Previous Matches:");
        lblMatches.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblMatches.setForeground(new Color(220, 200, 255));
        lblMatches.setBounds(50, 260, 300, 25);
        add(lblMatches);

        matchListModel = new DefaultListModel<>();
        matchList = new JList<>(matchListModel);
        matchList.setBackground(new Color(50, 0, 90));
        matchList.setForeground(new Color(230, 230, 255));
        matchList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(matchList);
        scrollPane.setBounds(50, 290, 410, 120);
        add(scrollPane);

        // Back button
        btnBack = new JButton("Back");
        btnBack.setBounds(890, 380, 130, 35);
        btnBack.setBackground(new Color(120, 20, 180));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> {
            stopBackgroundMusic();
            setVisible(false);
            new Main_Menu();
        });
        add(btnBack);

        // Load credentials and previous matches
        loadCredentials();
        loadPreviousMatches();

        getRootPane().setDefaultButton(btnBack);

        setSize(1100, 510);
        setLocation(220, 150);
        setResizable(false);
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

    private void loadCredentials() {
        try (BufferedReader br = new BufferedReader(new FileReader("credentials.txt"))) {
            String username = br.readLine();
            String password = br.readLine();
            if (username != null) {
                tfname.setText(username);
            }
            if (password != null) {
                tfpassword.setText(password);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading profile: " + e.getMessage());
        }
    }

    private void loadPreviousMatches() {
        matchListModel.clear();
        String filename = "game_history.txt";
        int count = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(tfname.getText())) {
                    String[] dateTimeParts = parts[2].split(" ");
                    String date = dateTimeParts.length > 0 ? dateTimeParts[0] : "";
                    String time = dateTimeParts.length > 1 ? dateTimeParts[1] : "";

                    String display = count + ". Score: " + parts[1] + " | Date: " + date + " | Time: " + time;
                    matchListModel.addElement(display);
                    count++;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No game history found or error reading history.");
        }
    }

    public static void main(String[] args) {
        new Profile();
    }
}
