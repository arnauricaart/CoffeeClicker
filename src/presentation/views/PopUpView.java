package presentation.views;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PopUpView {
    public PopUpView(String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message, "Error window", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
