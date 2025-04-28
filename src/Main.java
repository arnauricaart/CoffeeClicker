import Presentation.controllers.LoginController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        //////////////CODIGO DE PRUEBA PARA EL FIXERO DE CONFIGURACION////////////////
        //(Quitar los includes y este codigo cuando se valide el funcionamiento)
        //final ConfigDAO configJSONDAO = new ConfigJSONDAO();

       /* int port = configJSONDAO.getDatabasePort();
        String serverIP = configJSONDAO.getDatabaseHost();
        String name = configJSONDAO.getDatabaseName();
        String user = configJSONDAO.getDatabaseUser();;
        String password = configJSONDAO.getDatabasePassword();

        System.out.println(port);
        System.out.println(serverIP);
        System.out.println(name);
        System.out.println(user);
        System.out.println(password);
        ////////////////////////////////////////////////////////////////////////////
*/
        SwingUtilities.invokeLater(() -> {
            new LoginController().start();
        });

        //SwingUtilities.invokeLater(() -> {
       //     MenuGUI menuView = new MenuGUI();
       //     MenuController menuController = new MenuController(menuView);
       //     menuController.initController();
      //  });


    }

}