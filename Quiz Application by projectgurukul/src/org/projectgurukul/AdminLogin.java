package org.projectgurukul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin {

    private JFrame frame;
    private JTextField adminNameField;
    private JPasswordField passwordField;
    private final String adminName = "AAA";
    private final String password = "1234";

    public AdminLogin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Admin Login");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Removes the default window borders for a sleek look
        frame.setLayout(new BorderLayout());

        // Gradient Header Panel
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(41, 128, 185), getWidth(), getHeight(), new Color(84, 153, 199));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(500, 100));
        headerPanel.setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Admin Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle, BorderLayout.CENTER);

        // Add close button
        JButton btnClose = new JButton("X");
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnClose.setContentAreaFilled(false);
        btnClose.setBorderPainted(false);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> System.exit(0));
        headerPanel.add(btnClose, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Admin Name Label
        JLabel lblAdminName = new JLabel("Admin Name:");
        lblAdminName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblAdminName.setForeground(new Color(41, 128, 185));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblAdminName, gbc);

        // Admin Name Field
        adminNameField = new JTextField(15);
        adminNameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridx = 1;
        formPanel.add(adminNameField, gbc);

        // Password Label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblPassword.setForeground(new Color(41, 128, 185));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblPassword, gbc);

        // Password Field
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(41, 128, 185));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adminNameField.getText().equals(adminName) &&
                        password.equals(new String(passwordField.getPassword()))) {
                    frame.dispose();
                    new AdminPanel();
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        footerPanel.add(btnLogin);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(192, 57, 43));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCancel.addActionListener(e -> System.exit(0));
        footerPanel.add(btnCancel);

        frame.add(footerPanel, BorderLayout.SOUTH);

        // Add rounded corners
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
