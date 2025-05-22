package presentation.views;

import javax.swing.JOptionPane;
import java.awt.Component; // Necesario para el parentComponent

/**
 * Clase de utilidad para mostrar diversos tipos de diálogos emergentes (pop-ups)
 * utilizando JOptionPane.
 */
public class MessageDialogs {

    // Constructor privado para evitar que se instancie la clase (solo métodos estáticos)

    /**
     * Constructor of the class, not used.
     */
    private MessageDialogs() {
    }

    /**
     * Muestra un diálogo de mensaje de error.
     *
     * @param parentComponent El componente padre sobre el cual se centrará el diálogo.
     * Puede ser null para centrar en la pantalla.
     * @param title           El título de la ventana del diálogo.
     * @param message         El mensaje de error a mostrar.
     */
    public static void showErrorDialog(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un diálogo de mensaje informativo.
     *
     * @param parentComponent El componente padre.
     * @param title           El título de la ventana.
     * @param message         El mensaje informativo.
     */
    public static void showInformationDialog(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un diálogo de mensaje de advertencia.
     *
     * @param parentComponent El componente padre.
     * @param title           El título de la ventana.
     * @param message         El mensaje de advertencia.
     */
    public static void showWarningDialog(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra un diálogo de confirmación con opciones Sí/No.
     *
     * @param parentComponent El componente padre.
     * @param title           El título de la ventana.
     * @param message         La pregunta de confirmación.
     * @return Un entero: JOptionPane.YES_OPTION, JOptionPane.NO_OPTION.
     */
    public static int showConfirmDialog(Component parentComponent, String title, String message) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION);
    }

    /**
     * Muestra un diálogo de confirmación con opciones Sí/No/Cancelar.
     *
     * @param parentComponent El componente padre.
     * @param title           El título de la ventana.
     * @param message         La pregunta de confirmación.
     * @return Un entero: JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION.
     */
    public static int showConfirmYesNoCancelDialog(Component parentComponent, String title, String message) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
    }

    /**
     * Muestra un diálogo para que el usuario ingrese texto.
     *
     * @param parentComponent El componente padre.
     * @param title           El título de la ventana.
     * @param message         El mensaje que solicita la entrada.
     * @return El String ingresado por el usuario, o null si el usuario cancela.
     */
    public static String showInputDialog(Component parentComponent, String title, String message) {
        return JOptionPane.showInputDialog(parentComponent, message, title, JOptionPane.PLAIN_MESSAGE);
    }
}