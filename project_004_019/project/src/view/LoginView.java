/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author MODERN
 */
import controller.LoginController;
import javax.swing.*;
import java.awt.event.*;

public class LoginView extends JFrame {
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton loginBtn = new JButton("Login");

    public LoginView() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(null);

        JLabel uLabel = new JLabel("Username");
        uLabel.setBounds(30, 30, 80, 25);
        username.setBounds(120, 30, 130, 25);

        JLabel pLabel = new JLabel("Password");
        pLabel.setBounds(30, 70, 80, 25);
        password.setBounds(120, 70, 130, 25);

        loginBtn.setBounds(90, 110, 100, 25);
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginController controller = new LoginController();
                if (controller.login(username.getText(), new String(password.getPassword()))) {
                    new TransaksiView();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Login gagal");
                }
            }
        });

        add(uLabel); add(username);
        add(pLabel); add(password);
        add(loginBtn);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

