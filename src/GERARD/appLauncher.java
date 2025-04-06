import guis.LoginFormGUI;
import guis.RegistreFormGUI;
import bbdd.MyJDBC;
import javax.swing.*;

public class appLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new LoginFormGUI().setVisible(true);
               //System.out.println(MyJDBC.checkUser("marc tassini"));
                // System.out.println(MyJDBC.register("username1234", "password1234"));
                //System.out.println(MyJDBC.validateLogin("username1234", "password1234"));

            }
        });
    }
}
