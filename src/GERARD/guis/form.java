package guis;
import constants.CommonCostants;

import javax.swing.*;
import java.awt.*;

public class form extends JFrame{
    //constructor

    public form(String title){

        super(title);
        setSize(1080,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(CommonCostants.PRIMARY_COLOR);

    }
}
