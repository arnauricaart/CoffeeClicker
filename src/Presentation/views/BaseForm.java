package Presentation.views;


import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame {
    public BaseForm(String title) {
        super(title);
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#FFFFFF"));
    }
}
