package guis;

import bbdd.MyJDBC;
import constants.CommonCostants;
import guis.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistreFormGUI extends Form {

    public RegistreFormGUI() {
        super("Register");
        addGuiComponents();
    }

    private void addGuiComponents() {

        setLayout(null);
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(0,25,520,100);
        registerLabel.setForeground(CommonCostants.TEXT_COLOR);

        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        //Login lable
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30,150,400,25);
        usernameLabel.setForeground(CommonCostants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.BOLD, 18));

        //text field username
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30,185,450,55);
        usernameField.setBackground(CommonCostants.SECONDARY_COLOR);
        usernameField.setForeground(CommonCostants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        // Email label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(30,255,400,25);
        emailLabel.setForeground(CommonCostants.TEXT_COLOR);
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 18));

        // Email field
        JTextField emailField = new JTextField();
        emailField.setBounds(30,285,450,55);
        emailField.setBackground(CommonCostants.SECONDARY_COLOR);
        emailField.setForeground(CommonCostants.TEXT_COLOR);
        emailField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(emailLabel);
        add(emailField);


        //Password lable
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30,345,400,25);
        passwordLabel.setForeground(CommonCostants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.BOLD, 18));

        //text field password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30,375,450,55);
        passwordField.setBackground(CommonCostants.SECONDARY_COLOR);
        passwordField.setForeground(CommonCostants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        //reenter password

        JLabel repasswordLabel = new JLabel("Re-enter Password");
        repasswordLabel.setBounds(30,455,400,25);
        repasswordLabel.setForeground(CommonCostants.TEXT_COLOR);
        repasswordLabel.setFont(new Font("Dialog", Font.BOLD, 18));

        //text field password
        JPasswordField repasswordField = new JPasswordField();
        repasswordField.setBounds(30,485,450,55);
        repasswordField.setBackground(CommonCostants.SECONDARY_COLOR);
        repasswordField.setForeground(CommonCostants.TEXT_COLOR);
        repasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(repasswordLabel);
        add(repasswordField);

        //register button

        JButton registerButton = new JButton("Login");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));

        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonCostants.TEXT_COLOR);
        registerButton.setBounds(125,610,250,50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String rePassword = new String(repasswordField.getPassword());
                String email = emailField.getText();

                if(validateUserInput(username,email,password,rePassword)){
                    if(MyJDBC.register(username,email,password)){
                        RegistreFormGUI.this.dispose();
                        //per tornar al login gui
                        guis.LoginFormGUI loginFormGUI = new guis.LoginFormGUI();
                        loginFormGUI.setVisible(true);

                        JOptionPane.showMessageDialog(loginFormGUI, "Register account Successfully!!");

                    }else{
                        JOptionPane.showMessageDialog(RegistreFormGUI.this, "Error:Username already exists");
                    }
                }else{
                    JOptionPane.showMessageDialog(RegistreFormGUI.this, "Error:Username must be 6 characters at least \n" + "and/or passwords must match");
                }


            }
        });
        add(registerButton);

        JLabel loginLabel = new JLabel("Have an account? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonCostants.TEXT_COLOR);

        //canvi de pantalla al login

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegistreFormGUI.this.dispose();

                new guis.LoginFormGUI().setVisible(true);


            }
        });
        loginLabel.setBounds(125,600,250,30);
        add(loginLabel);




    }

    private boolean validateUserInput(String username, String email,String password, String rePassword) {

        if(username.length() == 0 || password.length() == 0 || rePassword.length() == 0|| email.length() == 0) {
            return false;
        }

        if(username.length() < 6){
            return false;
        }

        if(!password.equals(rePassword)){
            return false;
        }


        return true;
    }
}
