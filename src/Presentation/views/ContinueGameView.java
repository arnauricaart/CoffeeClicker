package Presentation.views;

import Presentation.views.GameData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ContinueGameView extends JFrame {

    private JTable gameTable;

    public ContinueGameView(List<GameData> games) {
        setTitle("Continue Game");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Game ID", "Coffees"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (GameData game : games) {
            Object[] row = {game.getId(), game.getCoffees()};
            tableModel.addRow(row);
        }

        gameTable = new JTable(tableModel);
        gameTable.setFont(new Font("Arial", Font.PLAIN, 16));
        gameTable.setRowHeight(30);
        gameTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        gameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Alternar colors per files
        gameTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(230, 230, 230) : Color.WHITE);
                } else {
                    c.setBackground(new Color(184, 207, 229));
                }
                return c;
            }
        });

        // Afegir acció de doble clic per accedir a la partida
        gameTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && gameTable.getSelectedRow() != -1) {
                    int selectedRow = gameTable.getSelectedRow();
                    int gameId = (int) gameTable.getValueAt(selectedRow, 0);
                    int coffees = (int) gameTable.getValueAt(selectedRow, 1);

                    // Aquí pots cridar al controlador o gestionar l'obertura de la partida
                    JOptionPane.showMessageDialog(null,
                            "Carregant partida amb ID: " + gameId + " (" + coffees + " coffees)",
                            "Continuar Partida",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Exemple: controller.loadGame(gameId);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(gameTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
