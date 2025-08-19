package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz extends JFrame implements ActionListener {

    String questions[][] = new String[10][6];
    String useranswers[] = new String[10];
    String answers[] = new String[10];

    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;
    JLabel timerLabel;
    Timer timer;

    int timeLeft = 30;
    int count = 0;
    int score = 0;

    String name;

    Quiz(String name) {
        this.name = name;
        setBounds(50, 0, 1440, 800);

        setTitle("AI-powered Quiz Game - Quiz Page");
        getContentPane().setBackground(new Color(35, 0, 50));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);

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

        timerLabel = new JLabel("Time Left : 30 seconds.");
        timerLabel.setBounds(1000, 420, 250, 40);
        timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        // Load questions from your QuestionBank
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

        next = createButton("Next", 1100, 500);
        next.addActionListener(this);
        add(next);

        JButton back = createButton("Back", 1100, 560);
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
        setVisible(true);

        // Stop the timer if user closes the quiz window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                dispose(); // close the frame properly
            }
        });

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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            saveUserAnswer();
            count++;
            if (count == 9) {
                next.setEnabled(false);
                submit.setEnabled(true);

                getRootPane().setDefaultButton(submit);
            }
            start(count);

        } else if (ae.getSource() == lifeline) {
            if (count % 2 == 0) {
                opt2.setEnabled(false);
                opt3.setEnabled(false);
            } else {
                opt1.setEnabled(false);
                opt4.setEnabled(false);
            }
            lifeline.setEnabled(false);

        } else if (ae.getSource() == submit) {
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
            saveUserAnswer();
            calculateScore();
            setVisible(false);
            new Score(name, score, questions, useranswers, answers);
        } else if (ae.getActionCommand().equals("Back")) {
            if (count > 0) {
                saveUserAnswer();
                count--;
                start(count);   

                String prevAnswer = useranswers[count];
                if (prevAnswer != null) {
                    if (prevAnswer.equals(opt1.getText())) {
                        opt1.setSelected(true);
                    } else if (prevAnswer.equals(opt2.getText())) {
                        opt2.setSelected(true);
                    } else if (prevAnswer.equals(opt3.getText())) {
                        opt3.setSelected(true);
                    } else if (prevAnswer.equals(opt4.getText())) {
                        opt4.setSelected(true);
                    }
                }

                next.setEnabled(true);
                submit.setEnabled(count == 9);
            }
        }

    }

    private void saveUserAnswer() {
        if (groupoptions.getSelection() == null) {
            useranswers[count] = "";
        } else {
            useranswers[count] = groupoptions.getSelection().getActionCommand();
        }
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i] != null && useranswers[i].equals(answers[i])) {
                score += 10;
            }
        }
    }

    private void start(int count) {
        qno.setText("" + (count + 1) + ". ");
        question.setText(questions[count][0]);

        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);

        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);

        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);

        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);

        groupoptions.clearSelection();

        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);

        timeLeft = 30;
        timerLabel.setText("Time Left : " + timeLeft + " seconds.");

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left : " + timeLeft + " seconds.");

                if (timeLeft <= 0) {
                    timer.stop();
                    saveUserAnswer();
                    Quiz.this.count++;

                    if (Quiz.this.count == 10) {
                        calculateScore();
                        setVisible(false);
                        new Score(name, score, questions, useranswers, answers);
                    } else {
                        if (Quiz.this.count == 9) {
                            next.setEnabled(false);
                            submit.setEnabled(true);
                        }
                        start(Quiz.this.count);
                    }
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
