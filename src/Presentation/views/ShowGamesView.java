package Presentation.views;

import Business.GameData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ShowGamesView extends JFrame {

    private JTable gameTable;
    private JButton loadButton;
    private JButton deleteButton;
    private ActionListener showStatsActionListener;

    public ShowGamesView(List<GameData> games, boolean isFinishedGames) {
        setTitle("Continue Game");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"IdPartida", "Name", "Coffees", "Last Access"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (GameData game : games) {
            Object[] row = {game.getId(), game.getNombre(), game.getCoffees(), game.getUltimoAcceso()};
            tableModel.addRow(row);
        }

        gameTable = new JTable(tableModel);
        gameTable.setFont(new Font("Arial", Font.PLAIN, 16));
        gameTable.setRowHeight(30);
        gameTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        gameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        gameTable.getColumnModel().getColumn(1).setCellRenderer(new AlternatingColorRenderer(JLabel.LEFT));
        gameTable.getColumnModel().getColumn(2).setCellRenderer(new AlternatingColorRenderer(JLabel.CENTER));
        gameTable.getColumnModel().getColumn(3).setCellRenderer(new AlternatingColorRenderer(JLabel.RIGHT));

        TableColumnModel columnModel = gameTable.getColumnModel();
        TableColumn hiddenColumn = columnModel.getColumn(0);
        columnModel.removeColumn(hiddenColumn);


        // Acción de doble clic para cargar partida o mostrar estadisticas
        if (!isFinishedGames) {
            gameTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && gameTable.getSelectedRow() != -1) {
                        loadSelectedGame();
                    }
                }
            });
        } else {
            gameTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && gameTable.getSelectedRow() != -1) {
                        showStatsActionListener.actionPerformed(null);
                    }
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(gameTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        if(!isFinishedGames){
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
                    String gameName = (String)gameTable.getValueAt(selectedRow, 0);

                    // Aquí deberías llamar al controlador para eliminar la partida
                    JOptionPane.showMessageDialog(this,
                            "Partida con ID " + gameName + " eliminada.",
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
        }

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadSelectedGame() {
        int selectedRow = gameTable.getSelectedRow();
        if (selectedRow != -1) {
            String gameName = (String)gameTable.getValueAt(selectedRow, 0);
            int coffees = (int) gameTable.getValueAt(selectedRow, 1);

            // Aquí puedes llamar al controlador para cargar la partida
            JOptionPane.showMessageDialog(this,
                    "Cargando partida con nombre: " + gameName + " (" + coffees + " coffees) e Id:" + getCurrentPartidaId(),
                    "Continuar Partida",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void addDeleteActionListener(ActionListener l) {
        deleteButton.addActionListener(l);
    }
    public int getCurrentPartidaId(){
        int selectedRow = gameTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow= gameTable.convertRowIndexToModel(selectedRow);
            Integer hiddenValue = (Integer) gameTable.getModel().getValueAt(modelRow, 0);
            return hiddenValue;
        }
        return -1;
    }

    public void setShowStatsActionListener(ActionListener l) {
        this.showStatsActionListener = l;
    }

    public String getSelectedGameName()  {
        int selectedRow = gameTable.getSelectedRow();
        String gameName = null;
        if (selectedRow != -1) {
            gameName = (String) gameTable.getValueAt(selectedRow, 0);
        }
        return gameName;
    }

    private static class AlternatingColorRenderer extends DefaultTableCellRenderer {
        private final int alignment;

        public AlternatingColorRenderer(int alignment) {
            this.alignment = alignment;
            setHorizontalAlignment(alignment);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                c.setBackground(row % 2 == 0 ? new Color(230, 230, 230) : Color.WHITE);
            } else {
                c.setBackground(new Color(184, 207, 229));
            }
            return c;
        }
    }

}
