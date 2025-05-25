package presentation.views;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 * This class implements a pop with different warning and error messages.
 */
public class PopUpView {
    /**
     * Constructor of the class. Creates the popup and sets the message.
     * @param message the message that we want to display in the popup.
     */
    public PopUpView(String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message, "Error window", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
