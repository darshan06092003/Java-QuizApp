package org.projectgurukul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPanel extends JFrame {
    private JTextField option1Field;
    private JTextField option2Field;
    private JTextField option3Field;
    private JTextField option4Field;
    private JTextField answerField;
    private JTextField remIDfield;

    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 150, 243));
        JLabel headerLabel = new JLabel("Admin Panel", SwingConstants.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Question"));
        inputPanel.setBackground(Color.WHITE);

        inputPanel.add(new JLabel("Question:"));
        JTextArea queTextArea = new JTextArea();
        queTextArea.setLineWrap(true);
        queTextArea.setWrapStyleWord(true);
        JScrollPane questionScrollPane = new JScrollPane(queTextArea);
        inputPanel.add(questionScrollPane);

        inputPanel.add(new JLabel("Option 1:"));
        option1Field = new JTextField();
        inputPanel.add(option1Field);

        inputPanel.add(new JLabel("Option 2:"));
        option2Field = new JTextField();
        inputPanel.add(option2Field);

        inputPanel.add(new JLabel("Option 3:"));
        option3Field = new JTextField();
        inputPanel.add(option3Field);

        inputPanel.add(new JLabel("Option 4:"));
        option4Field = new JTextField();
        inputPanel.add(option4Field);

        inputPanel.add(new JLabel("Answer:"));
        answerField = new JTextField();
        inputPanel.add(answerField);

        add(inputPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonsPanel.setBackground(new Color(33, 150, 243));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton btnAddQue = new JButton("Add Question");
        btnAddQue.setBackground(new Color(76, 175, 80));
        btnAddQue.setForeground(Color.WHITE);
        btnAddQue.setFocusPainted(false);
        btnAddQue.addActionListener(e -> {
            try {
                String[] options = {option1Field.getText(), option2Field.getText(), option3Field.getText(), option4Field.getText()};
                DataBase.addQuestion(queTextArea.getText(), options, answerField.getText());
                JOptionPane.showMessageDialog(this, "Question Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Can't Add Question\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        buttonsPanel.add(btnAddQue);

        JButton btnRemove = new JButton("Remove Question");
        btnRemove.setBackground(new Color(244, 67, 54));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFocusPainted(false);
        btnRemove.addActionListener(e -> {
            try {
                JComboBox<String> comboBox = new JComboBox<>(new String[]{"users", "question"});
                DataBase.delete(remIDfield.getText(), (String) comboBox.getSelectedItem());
                JOptionPane.showMessageDialog(this, "Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Delete Question Failed\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        buttonsPanel.add(btnRemove);

        JButton btnViewAllQuestions = new JButton("View All Questions");
        btnViewAllQuestions.setBackground(new Color(33, 150, 243));
        btnViewAllQuestions.setForeground(Color.WHITE);
        btnViewAllQuestions.setFocusPainted(false);
        btnViewAllQuestions.addActionListener(e -> showAllQuestions());
        buttonsPanel.add(btnViewAllQuestions);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBackground(new Color(255, 193, 7));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(e -> {
            dispose();
            new AdminLogin();
        });
        buttonsPanel.add(btnLogout);

        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(96, 125, 139));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(e -> System.exit(0));
        buttonsPanel.add(btnExit);

        add(buttonsPanel, BorderLayout.EAST);

        // Footer Panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(new Color(245, 245, 245));
        JLabel footerLabel = new JLabel("Quiz Admin Panel v1.0");
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    protected void showAllQuestions() {
        JFrame frame = new JFrame("All Questions");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        try {
            ArrayList<Question> questions = DataBase.getQuestionAns();
            JTextArea qTextArea = new JTextArea();
            qTextArea.setEditable(false);
            qTextArea.setLineWrap(true);
            qTextArea.setWrapStyleWord(true);
            for (Question question : questions) {
                qTextArea.append("Q: " + question.getQuestion() + "\n" +
                        "1: " + question.getOp1() + "\n" +
                        "2: " + question.getOp2() + "\n" +
                        "3: " + question.getOp3() + "\n" +
                        "4: " + question.getOp4() + "\n" +
                        "Ans: " + question.getAns() + "\n" +
                        "-----------------------------\n");
            }
            frame.add(new JScrollPane(qTextArea));
            frame.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Fetching Questions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminPanel());
    }
}
