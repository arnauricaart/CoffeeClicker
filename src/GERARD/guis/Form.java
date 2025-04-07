package guis;
import constants.CommonCostants;
import java.awt.*;
import javax.swing.*;

public class Form extends JFrame{
    //constructor

    public Form(String title){

        super(title);
        setSize(1080,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(CommonCostants.PRIMARY_COLOR);

    }
}
