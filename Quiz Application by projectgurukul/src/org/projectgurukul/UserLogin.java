package org.projectgurukul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserLogin {
    private JFrame frame;
    private JTextField idField;
    private JPasswordField passwordField;

    public UserLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setTitle("User Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setLayout(new BorderLayout());

        // Set Background Color
        frame.getContentPane().setBackground(new Color(255, 255, 255));

        // Create Panel for Login Form
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // User ID Label and TextField
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblId, gbc);

        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        idField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(idField, gbc);

        // Password Label and PasswordField
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPassword, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(new Color(60, 179, 113));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(100, 40));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (DataBase.validatePassword(idField.getText(), new String(passwordField.getPassword()))) {
                        frame.dispose();
                        Quiz quiz = new Quiz();
                    } else {
                        JOptionPane.showMessageDialog(btnLogin, "ID or Password does not match", "Invalid ID/Password", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnLogin, gbc);

        // New User Text and Register Button
        JLabel lblNewUser = new JLabel("New User?");
        lblNewUser.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblNewUser, gbc);

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegister.setBackground(new Color(70, 130, 180));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setPreferredSize(new Dimension(100, 40));
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Register register = new Register();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnRegister, gbc);
        
        // Add some spacing below the buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(new JLabel(), gbc);

        // Set the frame visible
        frame.setVisible(true);
    }
}
