package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Quiz extends JFrame implements ActionListener {

    String questions[][] = new String[10][6];
    String useranswers[] = new String[10];
    String answers[] = new String[10];

    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    JTextField fillAnswer;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline, back;
    JLabel timerLabel;
    javax.swing.Timer globalTimer;
    javax.swing.Timer perQuestionTimer;
    JButton[] trackerButtons = new JButton[10];
    boolean[] visited = new boolean[10];

    int totalTimeLeft; // for global timer in seconds, 0 = no timer
    int perQuestionTimeLeft; // for rapid fire per question timer
    int count = 0;
    int score = 0;
    String name;
    private Clip bgMusic;
    private boolean lifelineUsed = false;

    private final int RAPID_FIRE_TIME = 15;  // seconds per question
    private boolean isRapidFireMode = false;

    public Quiz(String name, int timerSeconds) {
        this.name = name;

        if (timerSeconds == RAPID_FIRE_TIME) {
            isRapidFireMode = true;
            perQuestionTimeLeft = RAPID_FIRE_TIME;
            totalTimeLeft = 0;
        } else {
            isRapidFireMode = false;
            totalTimeLeft = timerSeconds;
        }

        setBounds(50, 0, 1440, 800);
        setTitle("AI-powered Quiz Game - Quiz Page");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);

        JPanel trackerPanel = new JPanel();
        trackerPanel.setBounds(100, 350, 1200, 40);
        trackerPanel.setLayout(new GridLayout(1, 10, 10, 10));
        trackerPanel.setBackground(new Color(35, 0, 50));
        add(trackerPanel);

        for (int i = 0; i < 10; i++) {
            trackerButtons[i] = new JButton("Q" + (i + 1));
            trackerButtons[i].setBackground(Color.LIGHT_GRAY);
            trackerButtons[i].setForeground(Color.BLACK);
            trackerButtons[i].setFont(new Font("Tahoma", Font.BOLD, 14));
            final int qIndex = i;
            trackerButtons[i].addActionListener(e -> {
                saveUserAnswer();
                count = qIndex;
                start(count);
                if (isRapidFireMode) {
                    startPerQuestionTimer();
                }
            });
            trackerPanel.add(trackerButtons[i]);
        }

        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        qno.setForeground(new Color(220, 220, 255));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        question.setForeground(new Color(220, 220, 255));
        add(question);

        timerLabel = new JLabel();
        timerLabel.setBounds(1100, 420, 250, 40);
        timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        questions = QuestionBank.getRandomQuestions(10);
        for (int i = 0; i < 10; i++) {
            answers[i] = questions[i][5];
        }

        opt1 = createRadioButton(170, 520);
        opt2 = createRadioButton(170, 560);
        opt3 = createRadioButton(170, 600);
        opt4 = createRadioButton(170, 640);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        fillAnswer = new JTextField();
        fillAnswer.setBounds(170, 520, 400, 30);
        fillAnswer.setFont(new Font("Dialog", Font.PLAIN, 20));
        fillAnswer.setBackground(new Color(60, 0, 100));
        fillAnswer.setForeground(new Color(220, 220, 255));
        fillAnswer.setVisible(false);
        add(fillAnswer);

        next = createButton("Next", 1100, 500);
        next.addActionListener(this);
        add(next);

        back = createButton("Back", 1100, 560);
        back.addActionListener(this);
        add(back);

        lifeline = createButton("50-50 Lifeline", 1100, 620);
        lifeline.addActionListener(this);
        add(lifeline);

        submit = createButton("Submit", 1100, 680);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        getRootPane().setDefaultButton(next);

        start(count);

        if (isRapidFireMode) {
            startPerQuestionTimer();
        } else if (totalTimeLeft > 0) {
            startGlobalTimer();
        } else {
            timerLabel.setText("No Timer Mode");
        }

        setVisible(true);

        startBackgroundMusic("C:\\Users\\Lenovo\\OneDrive\\Documents\\NetBeansProjects\\Quiz Application\\src\\icons\\quiz_ctd.wav");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
                if (globalTimer != null && globalTimer.isRunning()) {
                    globalTimer.stop();
                }
                if (perQuestionTimer != null && perQuestionTimer.isRunning()) {
                    perQuestionTimer.stop();
                }
                dispose();
            }
        });
    }

    private void startGlobalTimer() {
        if (totalTimeLeft <= 0) {
            return;
        }
        timerLabel.setText(formatTime(totalTimeLeft));
        globalTimer = new Timer(1000, e -> {
            totalTimeLeft--;
            timerLabel.setText(formatTime(totalTimeLeft));
            if (totalTimeLeft <= 0) {
                globalTimer.stop();
                stopBackgroundMusic();
                saveUserAnswer();
                calculateScore();
                setVisible(false);
                new Score(name, score, questions, useranswers, answers);
            }
        });
        globalTimer.start();
    }

    private void startPerQuestionTimer() {
        if (perQuestionTimer != null && perQuestionTimer.isRunning()) {
            perQuestionTimer.stop();
        }
        perQuestionTimeLeft = RAPID_FIRE_TIME;
        timerLabel.setText(formatTime(perQuestionTimeLeft));
        perQuestionTimer = new Timer(1000, e -> {
            perQuestionTimeLeft--;
            timerLabel.setText(formatTime(perQuestionTimeLeft));
            if (perQuestionTimeLeft <= 0) {
                perQuestionTimer.stop();
                saveUserAnswer();
                if (count < 9) {
                    count++;
                    start(count);
                    next.setEnabled(count != 9);
                    submit.setEnabled(count == 9);
                    startPerQuestionTimer(); // Restart timer immediately for next question
                } else {
                    calculateScore();
                    setVisible(false);
                    new Score(name, score, questions, useranswers, answers);
                }
            }
        });
        perQuestionTimer.start();
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("Time Left: %02d:%02d", minutes, seconds);
    }

    private void saveUserAnswer() {
        boolean isFill = (questions[count][1].equals("") && questions[count][2].equals("")
                && questions[count][3].equals("") && questions[count][4].equals(""));
        if (isFill) {
            useranswers[count] = fillAnswer.getText().trim();
        } else {
            if (groupoptions.getSelection() == null) {
                useranswers[count] = "";
            } else {
                useranswers[count] = groupoptions.getSelection().getActionCommand();
            }
        }
        updateTrackerColors(count);
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i] != null) {
                boolean isFill = (questions[i][1].equals("") && questions[i][2].equals("")
                        && questions[i][3].equals("") && questions[i][4].equals(""));
                if (isFill) {
                    if (useranswers[i].trim().equalsIgnoreCase(answers[i].trim())) {
                        score += 10;
                    }
                } else {
                    if (useranswers[i].equals(answers[i])) {
                        score += 10;
                    }
                }
            }
        }
    }

    private void start(int count) {
        visited[count] = true; // mark visited
        qno.setText("" + (count + 1) + ". ");
        question.setText(questions[count][0]);
        boolean isFill = (questions[count][1].equals("") && questions[count][2].equals("")
                && questions[count][3].equals("") && questions[count][4].equals(""));
        if (isFill) {
            opt1.setVisible(false);
            opt2.setVisible(false);
            opt3.setVisible(false);
            opt4.setVisible(false);
            fillAnswer.setVisible(true);
            fillAnswer.setText(useranswers[count] != null ? useranswers[count] : "");
            lifeline.setEnabled(false);
            lifeline.setToolTipText("50-50 not available for fill-in-the-blank");
        } else {
            opt1.setVisible(true);
            opt2.setVisible(true);
            opt3.setVisible(true);
            opt4.setVisible(true);
            fillAnswer.setVisible(false);
            opt1.setText(questions[count][1]);
            opt1.setActionCommand(questions[count][1]);
            opt2.setText(questions[count][2]);
            opt2.setActionCommand(questions[count][2]);
            opt3.setText(questions[count][3]);
            opt3.setActionCommand(questions[count][3]);
            opt4.setText(questions[count][4]);
            opt4.setActionCommand(questions[count][4]);
            groupoptions.clearSelection();
            if (useranswers[count] != null) {
                if (useranswers[count].equals(opt1.getText())) {
                    opt1.setSelected(true);
                } else if (useranswers[count].equals(opt2.getText())) {
                    opt2.setSelected(true);
                } else if (useranswers[count].equals(opt3.getText())) {
                    opt3.setSelected(true);
                } else if (useranswers[count].equals(opt4.getText())) {
                    opt4.setSelected(true);
                }
            }
            lifeline.setEnabled(!lifelineUsed);
            lifeline.setToolTipText(lifelineUsed ? "50-50 already used" : "Use 50-50 lifeline");
        }
        updateTrackerColors(count);
    }

    private void updateTrackerColors(int currentIndex) {
        for (int i = 0; i < trackerButtons.length; i++) {
            if (i == currentIndex) {
                trackerButtons[i].setBackground(Color.YELLOW); // Current
            } else if (useranswers[i] != null && !useranswers[i].equals("")) {
                trackerButtons[i].setBackground(Color.GREEN); // Answered
            } else if (visited[i]) {
                trackerButtons[i].setBackground(Color.RED);   // Visited but not answered
            } else {
                trackerButtons[i].setBackground(Color.GRAY);  // Not visited yet
            }
        }
    }

    private JRadioButton createRadioButton(int x, int y) {
        JRadioButton btn = new JRadioButton();
        btn.setBounds(x, y, 700, 30);
        btn.setBackground(new Color(60, 0, 100));
        btn.setForeground(new Color(220, 220, 255));
        btn.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(btn);
        return btn;
    }

    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 200, 40);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btn.setBackground(new Color(95, 0, 160));
        btn.setForeground(Color.WHITE);
        return btn;
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
        if (ae.getSource() == next) {
            saveUserAnswer();
            visited[count] = true;
            count++;
            if (count == 9) {
                next.setEnabled(false);
                submit.setEnabled(true);
                getRootPane().setDefaultButton(submit);
            }
            start(count);
            if (isRapidFireMode) {
                startPerQuestionTimer();  // reset per question timer on next question
            }
        } else if (ae.getSource() == lifeline) {
            if ((questions[count][1].equals("") && questions[count][2].equals("")
                    && questions[count][3].equals("") && questions[count][4].equals("")) || lifelineUsed) {
                return;
            }
            String correctAnswer = answers[count];
            JRadioButton[] options = {opt1, opt2, opt3, opt4};
            JRadioButton[] wrongOptions = new JRadioButton[3];
            int wIndex = 0;
            for (int i = 0; i < options.length; i++) {
                if (!options[i].getText().equals(correctAnswer)) {
                    wrongOptions[wIndex++] = options[i];
                }
            }
            if (wIndex >= 2) {
                wrongOptions[0].setEnabled(false);
                wrongOptions[1].setEnabled(false);
            }
            lifelineUsed = true;
            lifeline.setEnabled(false);
        } else if (ae.getSource() == submit) {
            stopBackgroundMusic();
            if (globalTimer != null && globalTimer.isRunning()) {
                globalTimer.stop();
            }
            if (perQuestionTimer != null && perQuestionTimer.isRunning()) {
                perQuestionTimer.stop();
            }
            saveUserAnswer();
            calculateScore();
            setVisible(false);
            new Score(name, score, questions, useranswers, answers);
        } else if (ae.getActionCommand().equals("Back")) {
            if (count > 0) {
                saveUserAnswer();
                visited[count] = true;
                count--;
                start(count);
                next.setEnabled(true);
                submit.setEnabled(count == 9);
                if (isRapidFireMode) {
                    startPerQuestionTimer(); // reset timer on back button too
                }
            }
        }
    }

    public static void main(String[] args) {
        new Quiz("User", 0); // FreeRoam test
    }
}
