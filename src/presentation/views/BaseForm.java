package presentation.views;


import javax.swing.*;
import java.awt.*;

/**
 * This class extends from JFrame, it will be used as a parent class for RemoveAccountView and NewGameView.
 */
public class BaseForm extends JFrame {

    /**
     * Constructor of the class, sets the title, size, default close operation, layout, etc.
     * @param title String with the title of the view.
     */
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
