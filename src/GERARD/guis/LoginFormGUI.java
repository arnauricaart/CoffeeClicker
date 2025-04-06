package guis;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import bbdd.MyJDBC;
import constants.CommonCostants;

import javax.swing.*;

public class LoginFormGUI extends form {
    public LoginFormGUI() {
        super("Login");
        addGuiComponents();
    }

    private void addGuiComponents() {
        setLayout(null);
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setBounds(0,50,800,100);
        loginLabel.setForeground(CommonCostants.TEXT_COLOR);

        loginLabel.setFont(new Font("Dialog", Font.BOLD, 48));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        //Login lable
        JLabel usernameLabel = new JLabel("Username or Email");
        usernameLabel.setBounds(200,200,400,30);
        usernameLabel.setForeground(CommonCostants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 22));

        //text field username
        JTextField usernameField = new JTextField();
        usernameField.setBounds(200,240,400,50);
        usernameField.setBackground(CommonCostants.SECONDARY_COLOR);
        usernameField.setForeground(CommonCostants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));

        add(usernameLabel);
        add(usernameField);

        //Password lable
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(200,320,400,30);
        passwordLabel.setForeground(CommonCostants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        //text field password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(200,360,400,50);
        passwordField.setBackground(CommonCostants.SECONDARY_COLOR);
        passwordField.setForeground(CommonCostants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.BOLD, 22));

        add(passwordLabel);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 22));

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(CommonCostants.TEXT_COLOR);
        loginButton.setBounds(275,450,250,60);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if(MyJDBC.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Successful");
                }else{
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Failed");
                }

            }
        });
        add(loginButton);

        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(CommonCostants.TEXT_COLOR);

        //per passar de una pantalla a altre de login a registre

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFormGUI.this.dispose();

                new RegistreFormGUI().setVisible(true);
            }
        });
        registerLabel.setBounds(275,530,250,30);
        add(registerLabel);




    }
}