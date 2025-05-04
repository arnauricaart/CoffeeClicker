package Presentation.views;

import Business.Entities.Game;

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
    private ActionListener showStatsActionListener;

    public ShowGamesView(List<Game> games, boolean isFinishedGames) {
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

        for (Game game : games) {
            Object[] row = {game.getGameID(), game.getName(), game.getCoffees(), game.getLastAccess()};
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

        gameTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && gameTable.getSelectedRow() != -1) {
                    showStatsActionListener.actionPerformed(null);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(gameTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        add(buttonPanel, BorderLayout.SOUTH);
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
