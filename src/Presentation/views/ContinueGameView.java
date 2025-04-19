package Presentation.views;

import Business.GameData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ContinueGameView extends JFrame {

    private JTable gameTable;
    private JButton loadButton;
    private JButton deleteButton;

    public ContinueGameView(List<GameData> games) {
        setTitle("Continue Game");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

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

        // Acción de doble clic para cargar partida
        gameTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && gameTable.getSelectedRow() != -1) {
                    loadSelectedGame();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(gameTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        loadButton = new JButton("Continue game");
        deleteButton = new JButton("Remove game");

        // Acción de cargar partida (igual que doble clic)
        loadButton.addActionListener(e -> {
            if (gameTable.getSelectedRow() != -1) {
                loadSelectedGame();
            } else {
                JOptionPane.showMessageDialog(this, "Select a game first.");
            }
        });

        // Acción de eliminar partida
        deleteButton.addActionListener(e -> {
            if (gameTable.getSelectedRow() != -1) {
                int selectedRow = gameTable.getSelectedRow();
                int gameId = (int) gameTable.getValueAt(selectedRow, 0);

                // Aquí deberías llamar al controlador para eliminar la partida
                JOptionPane.showMessageDialog(this,
                        "Partida con ID " + gameId + " eliminada.",
                        "Eliminar Partida",
                        JOptionPane.INFORMATION_MESSAGE);

                // Quitar del modelo de tabla
                ((DefaultTableModel) gameTable.getModel()).removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Select a game first.");
            }
        });

        buttonPanel.add(loadButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadSelectedGame() {
        int selectedRow = gameTable.getSelectedRow();
        if (selectedRow != -1) {
            int gameId = (int) gameTable.getValueAt(selectedRow, 0);
            int coffees = (int) gameTable.getValueAt(selectedRow, 1);

            // Aquí puedes llamar al controlador para cargar la partida
            JOptionPane.showMessageDialog(this,
                    "Cargando partida con ID: " + gameId + " (" + coffees + " coffees)",
                    "Continuar Partida",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
