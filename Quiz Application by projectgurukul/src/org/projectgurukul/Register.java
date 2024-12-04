package org.projectgurukul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Register {
    private JFrame frame;
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Register() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("User Registration");
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(10, 10));

        // Header
        JLabel headerLabel = new JLabel("Register a New User", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(33, 150, 243));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.getContentPane().add(headerLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        frame.getContentPane().add(formPanel, BorderLayout.CENTER);

        JLabel lblId = new JLabel("User ID:");
        lblId.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(lblId);

        userIdField = new JTextField();
        userIdField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(userIdField);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(lblName);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(nameField);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(lblEmail);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(emailField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(passwordField);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setBackground(new Color(244, 67, 54));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(120, 40));
        btnBack.addActionListener(e -> {
            frame.dispose();
            new UserLogin();
        });
        buttonsPanel.add(btnBack);

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegister.setBackground(new Color(76, 175, 80));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setPreferredSize(new Dimension(120, 40));
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DataBase.addUser(Integer.parseInt(userIdField.getText()), nameField.getText(), emailField.getText(), new String(passwordField.getPassword()));
                    JOptionPane.showMessageDialog(frame, "User Registered Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(frame, "Error: " + e1.getMessage(), "Registration Failed", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid User ID. Please enter a numeric value.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonsPanel.add(btnRegister);

        frame.setVisible(true);
    }

    // Clears the form fields after successful registration
    private void clearFields() {
        userIdField.setText("");
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }
}
