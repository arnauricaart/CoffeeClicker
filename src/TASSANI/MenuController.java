package TASSANI;
import presentation.views.MenuGUI;


public class MenuController {
    private MenuGUI menuView;

    public MenuController(MenuGUI menuView) {
        this.menuView = menuView;
    }

    public void initController() {
        menuView.setNewGameButtonListener(e -> startNewGame());
        menuView.setStatisticsButtonListener(e -> showStatistics());
        menuView.setLogoutButtonListener(e -> logout());
        menuView.setDeleteAccountButtonListener(e -> deleteAccount());
    }

    private void startNewGame() {
        // Lógica para iniciar o continuar el juego
        System.out.println("Starting new game");
    }

    private void showStatistics() {
        // Lógica para mostrar las estadísticas del juego
    }

    private void logout() {
        // Lógica para cerrar sesión
    }

    private void deleteAccount() {
        // Lógica para eliminar la cuenta
    }
}