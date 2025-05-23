import presentation.controllers.LoginController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            LoginController controller = new LoginController();
        });
    }
}