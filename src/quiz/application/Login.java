package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {

    JButton main_menu, back;
    JTextField tfname;
    JPasswordField tfpassword;
    private Clip bgMusic;

    Login() {
        setTitle("AI-powered Quiz Game - Login Page");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);

        JLabel heading = new JLabel("Simple Minds");
        heading.setFont(new Font("Consolas", Font.BOLD, 46));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(730, 50, 400, 60);
        add(heading);

        JLabel name = new JLabel("Enter your name:");
        name.setFont(new Font("Segoe UI", Font.BOLD, 18));
        name.setForeground(new Color(220, 200, 255));
        name.setBounds(735, 150, 300, 25);
        add(name);

        tfname = new JTextField();
        tfname.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tfname.setBounds(735, 180, 300, 35);
        tfname.setBackground(new Color(50, 0, 90));
        tfname.setForeground(new Color(230, 230, 255));
        tfname.setCaretColor(Color.WHITE);
        tfname.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(tfname);

        JLabel password = new JLabel("Enter password:");
        password.setFont(new Font("Segoe UI", Font.BOLD, 18));
        password.setForeground(new Color(220, 200, 255));
        password.setBounds(735, 240, 300, 25);
        add(password);

        tfpassword = new JPasswordField();
        tfpassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tfpassword.setBounds(735, 270, 300, 35);
        tfpassword.setBackground(new Color(50, 0, 90));
        tfpassword.setForeground(new Color(230, 230, 255));
        tfpassword.setCaretColor(Color.WHITE);
        tfpassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(tfpassword);

        main_menu = new JButton("Login");
        main_menu.setBounds(735, 360, 130, 35);
        main_menu.setBackground(new Color(120, 20, 180));
        main_menu.setForeground(Color.WHITE);
        main_menu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        main_menu.setFocusPainted(false);
        main_menu.addActionListener(this);
        add(main_menu);

        back = new JButton("Back");
        back.setBounds(905, 360, 130, 35);
        back.setBackground(new Color(120, 20, 180));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.setFocusPainted(false);
        back.addActionListener(this);
        add(back);

        getRootPane().setDefaultButton(main_menu);

        setSize(1200, 500);
        setLocation(200, 150);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
            }
        });

        startBackgroundMusic("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Quiz Application\\src\\icons\\rules_bg.wav");
    }

    private void saveCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt"))) {
            writer.write(username);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            System.out.println("Credentials saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving credentials: " + e.getMessage());
        }
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
        if (ae.getSource() == main_menu) {
            String username = tfname.getText();
            String password = new String(tfpassword.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.");
                return;
            }
            saveCredentials(username, password);

            Session.setUsername(username);  // Save username

            stopBackgroundMusic();
            setVisible(false);
            new Main_Menu();
        } else if (ae.getSource() == back) {
            stopBackgroundMusic();
            setVisible(false);
            new Main_Menu();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
