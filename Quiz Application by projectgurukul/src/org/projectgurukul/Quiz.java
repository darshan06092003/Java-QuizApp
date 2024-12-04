package org.projectgurukul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Quiz extends JFrame {
    ArrayList<Question> questions = null;
    private int count = 0;
    private int score = 0;

    public Quiz() {
        setTitle("Welcome to Quiz");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Timer
        startTimer(600);

        // Question Panel
        JPanel quePanel = new JPanel();
        quePanel.setBackground(new Color(33, 150, 243));
        quePanel.setLayout(new BoxLayout(quePanel, BoxLayout.Y_AXIS));
        quePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(quePanel, BorderLayout.NORTH);

        JTextArea queTextArea = new JTextArea();
        queTextArea.setFont(new Font("Arial", Font.BOLD, 20));
        queTextArea.setForeground(Color.WHITE);
        queTextArea.setBackground(new Color(33, 150, 243));
        queTextArea.setLineWrap(true);
        queTextArea.setWrapStyleWord(true);
        queTextArea.setEditable(false);
        quePanel.add(queTextArea);

        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(240, 240, 240));
        optionsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        getContentPane().add(optionsPanel, BorderLayout.CENTER);

        JRadioButton rdbtnOp1 = createOptionButton();
        JRadioButton rdbtnOp2 = createOptionButton();
        JRadioButton rdbtnOp3 = createOptionButton();
        JRadioButton rdbtnOp4 = createOptionButton();
        optionsPanel.add(rdbtnOp1);
        optionsPanel.add(rdbtnOp2);
        optionsPanel.add(rdbtnOp3);
        optionsPanel.add(rdbtnOp4);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnOp1);
        bg.add(rdbtnOp2);
        bg.add(rdbtnOp3);
        bg.add(rdbtnOp4);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(240, 240, 240));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        JButton btnNext = new JButton("Next");
        btnNext.setFont(new Font("Arial", Font.BOLD, 18));
        btnNext.setForeground(Color.WHITE);
        btnNext.setBackground(new Color(76, 175, 80));
        btnNext.setFocusPainted(false);
        btnNext.setPreferredSize(new Dimension(150, 40));
        buttonsPanel.add(btnNext);

        try {
            questions = DataBase.getQuestionAns();
            updateQuestion(queTextArea, rdbtnOp1, rdbtnOp2, rdbtnOp3, rdbtnOp4);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        btnNext.addActionListener(e -> {
            if (bg.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Please select an answer!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                checkAnswer(bg);
                count++;
                if (count < questions.size()) {
                    updateQuestion(queTextArea, rdbtnOp1, rdbtnOp2, rdbtnOp3, rdbtnOp4);
                } else {
                    displayScore();
                }
            }
        });

        setVisible(true);
    }

    private void updateQuestion(JTextArea queTextArea, JRadioButton rdbtnOp1, JRadioButton rdbtnOp2, JRadioButton rdbtnOp3, JRadioButton rdbtnOp4) {
        queTextArea.setText(questions.get(count).getQuestion());
        rdbtnOp1.setText(questions.get(count).getOp1());
        rdbtnOp2.setText(questions.get(count).getOp2());
        rdbtnOp3.setText(questions.get(count).getOp3());
        rdbtnOp4.setText(questions.get(count).getOp4());
    }

    private JRadioButton createOptionButton() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        radioButton.setBackground(Color.WHITE);
        radioButton.setFocusPainted(false);
        return radioButton;
    }

    private void checkAnswer(ButtonGroup bg) {
        Enumeration<AbstractButton> buttons = bg.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected() && button.getText().equals(questions.get(count).getAns())) {
                score++;
            }
        }
    }

    private void displayScore() {
        dispose();
        JOptionPane.showMessageDialog(this, "Thanks for playing the Quiz!\nYour Score: " + score, "Quiz Completed", JOptionPane.PLAIN_MESSAGE);
    }

    private void startTimer(int timeInSecs) {
        JLabel timerLabel = new JLabel(String.format("Time: %02d:%02d", timeInSecs / 60, timeInSecs % 60));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(new Color(244, 67, 54));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(timerLabel, BorderLayout.EAST);

        Timer timer = new Timer(1000, new AbstractAction() {
            int timeLeft = timeInSecs;

            public void actionPerformed1(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText(String.format("Time: %02d:%02d", timeLeft / 60, timeLeft % 60));
                } else {
                    ((Timer) e.getSource()).stop();
                    displayScore();
                }
            }

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        });

        timer.start();
    }
}
