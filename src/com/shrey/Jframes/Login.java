package com.shrey.Jframes;

import com.shrey.models.User;
import com.shrey.utilities.AuthUserSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {
    JPasswordField passwordField;
    JTextField textField;
    JButton login;
    public Login() throws HeadlessException {
        super("Student Signin");
        setMinimumSize(new Dimension(400,400));
        setBounds(350,200,400,400);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(150,150,100,50);
        login = new JButton("Login");
        buttonPanel.add(login);
        add(buttonPanel);

        passwordField = new JPasswordField("",20);
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBounds(0,100,400,50);
        passwordPanel.add(new JLabel("Password"));

        passwordPanel.add(passwordField);
        add(passwordPanel);

        JPanel enrollemntPanel = new JPanel();
        enrollemntPanel.setBounds(0,50,400,50);
        enrollemntPanel.add(new JLabel("Enrollment"));
        textField = new JTextField("",20);
        enrollemntPanel.add(textField);
        add(enrollemntPanel);
        setVisible(true);

        login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User user = User.getUserByEnrollemnt(textField.getText());
        if (user != null && Arrays.equals(user.getPassword().toCharArray(),(passwordField.getPassword()))){
            AuthUserSingleton.userLoggedIn(user);
            new ProjectsList();
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Invalid Credentials");
        }
    }
}
