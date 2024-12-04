package org.projectgurukul;

import java.awt.*;
import javax.swing.*;

public class ProfileChooser {

    private JFrame frmQuizApplication;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ProfileChooser window = new ProfileChooser();
                window.frmQuizApplication.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ProfileChooser() {
        DataBase.dbInit(); // Initialize database
        initialize();
    }

    private void initialize() {
        frmQuizApplication = new JFrame();
        frmQuizApplication.getContentPane().setBackground(new Color(240, 240, 240));
        frmQuizApplication.setTitle("Quiz Application By ProjectGurukul");
        frmQuizApplication.setBounds(100, 100, 500, 300);
        frmQuizApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmQuizApplication.getContentPane().setLayout(null);

        // Header Label
        JLabel lblWelcome = new JLabel("Welcome to Quiz Application");
        lblWelcome.setForeground(new Color(33, 150, 243));
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(10, 20, 460, 40);
        frmQuizApplication.getContentPane().add(lblWelcome);

        // Instruction Label
        JLabel lblInstruction = new JLabel("Please Select Your Login Profile:");
        lblInstruction.setForeground(new Color(33, 150, 243));
        lblInstruction.setFont(new Font("Arial", Font.PLAIN, 18));
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstruction.setBounds(10, 80, 460, 30);
        frmQuizApplication.getContentPane().add(lblInstruction);

        // Admin Button
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 16));
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setBackground(new Color(76, 175, 80));
        btnAdmin.setFocusPainted(false);
        btnAdmin.setBounds(130, 150, 100, 40);
        btnAdmin.addActionListener(e -> {
            frmQuizApplication.dispose(); // Close current window
            new AdminLogin(); // Open Admin Login
        });
        frmQuizApplication.getContentPane().add(btnAdmin);

        // User Button
        JButton btnUser = new JButton("User");
        btnUser.setFont(new Font("Arial", Font.BOLD, 16));
        btnUser.setForeground(Color.WHITE);
        btnUser.setBackground(new Color(33, 150, 243));
        btnUser.setFocusPainted(false);
        btnUser.setBounds(270, 150, 100, 40);
        btnUser.addActionListener(e -> {
            frmQuizApplication.dispose(); // Close current window
            new UserLogin(); // Open User Login
        });
        frmQuizApplication.getContentPane().add(btnUser);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBounds(10, 220, 460, 40);
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel footerLabel = new JLabel("ProjectGurukul Quiz App © 2024");
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        footerLabel.setForeground(new Color(120, 120, 120));
        footerPanel.add(footerLabel);
        frmQuizApplication.getContentPane().add(footerPanel);
    }
}
