package quiz.application;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.sound.sampled.*;

public class Leaderboard extends JFrame implements ActionListener {
    private JButton back;
    private Clip bgMusic;
    private JTable table;
    private DefaultTableModel tableModel;

    public Leaderboard() {
        setTitle("AI powered Quiz Game - Leaderboard");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        JLabel heading = new JLabel("Leaderboard - Top Scores");
        heading.setFont(new Font("Consolas", Font.BOLD, 28));
        heading.setForeground(new Color(200, 160, 255));
        heading.setBounds(40, 30, 700, 40);
        add(heading);

        String[] colNames = { "Rank", "Username", "Highest Score" };
        tableModel = new DefaultTableModel(null, colNames) {
            @Override public boolean isCellEditable(int r, int c) { return false;}
        };
        table = new JTable(tableModel);
        Color tableBg = new Color(50, 0, 90);

        table.setBackground(tableBg);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        table.setForeground(new Color(230, 230, 255));
        table.setRowHeight(30);

        // Enable crisp grid lines for a true table look
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 160, 255)); // Light purple lines
        table.setIntercellSpacing(new Dimension(1, 1)); // vertical and horizontal spacing

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setForeground(new Color(200, 160, 255));
        header.setBackground(new Color(35, 0, 50));
        header.setReorderingAllowed(false);

        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(70);
        colModel.getColumn(1).setPreferredWidth(240);
        colModel.getColumn(2).setPreferredWidth(140);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 100, 670, 400);
        scrollPane.getViewport().setBackground(tableBg);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(290, 520, 120, 35);
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
        loadLeaderboard();
    }

    private void loadLeaderboard() {
        Map<String, Integer> userHighestScores = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("game_history.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    if (username.isEmpty()) continue;
                    int score = Integer.parseInt(parts[1].trim());
                    userHighestScores.put(username, Math.max(userHighestScores.getOrDefault(username, 0), score));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No leaderboard data found or error reading history.");
            return;
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(userHighestScores.entrySet());
        sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        tableModel.setRowCount(0);
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            tableModel.addRow(new Object[]{ rank, entry.getKey(), entry.getValue() });
            rank++;
        }
        if (sortedEntries.isEmpty()) {
            tableModel.addRow(new Object[]{ "-", "No scores available yet", "-" });
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
        if (ae.getSource() == back) {
            stopBackgroundMusic();
            setVisible(false);
            new Main_Menu();
        }
    }

    public static void main(String[] args) {
        new Leaderboard();
    }
}